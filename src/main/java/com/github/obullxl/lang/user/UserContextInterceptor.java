/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.obullxl.lang.Consts;
import com.github.obullxl.lang.biz.RightException;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.servlet.ServletUtils;

/**
 * 用户上下文拦截器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserContextInterceptor.java, V1.0.1 2013年12月12日 上午9:15:20 $
 */
public class UserContextInterceptor extends HandlerInterceptorAdapter implements InitializingBean {
    private static final Logger              logger  = LogUtils.get();

    /** 路径匹配器 */
    private static final PathMatcher         matcher = new AntPathMatcher();

    /** URI前缀 */
    private Set<String>                      uriPrefixs;

    /** 跳转页面 */
    private String                           gotoUrl;

    /** 
     * 权限配置
     * <p/>
     * /admin/**>RGT_LOGIN_NORMAL
     * <br/>
     * /admin/topic/create*>RGT_TOPIC_CREATE
     * <br/>
     * /admin/topic/create*.html>RGT_TOPIC_CREATE
     */
    private List<Resource>                   rightConfigs;

    /** 系统是否就绪 */
    private boolean                          ready   = false;

    /** URI权限映射 */
    private static final Map<String, String> PATHS   = new ConcurrentHashMap<String, String>();

    /**
     * 获取所有URI权限映射
     */
    public static final Map<String, String> findPaths() {
        return new ConcurrentHashMap<String, String>(PATHS);
    }

    /**
     * 根据URI获取所有权限码
     */
    public static final Set<String> findRights(String uri) {
        Set<String> rgts = new HashSet<String>();

        for (Map.Entry<String, String> entry : PATHS.entrySet()) {
            String path = entry.getKey();
            if (StringUtils.endsWith(path, "*")) {
                if (matcher.matchStart(path, uri)) {
                    rgts.add(entry.getValue());
                }
            } else {
                if (matcher.match(path, uri)) {
                    rgts.add(entry.getValue());
                }
            }
        }

        return rgts;
    }

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        if (this.uriPrefixs == null) {
            this.uriPrefixs = new HashSet<String>();
            this.uriPrefixs.add("/admin");
        }

        logger.warn("[权限拦截]-URI前缀-{}.", this.uriPrefixs);

        if (this.rightConfigs == null) {
            this.rightConfigs = new ArrayList<Resource>();
        }

        logger.warn("[权限拦截]-权限配置-{}.", this.rightConfigs);

        try {
            for (Resource config : this.rightConfigs) {
                InputStream input = null;
                try {
                    input = config.getInputStream();
                    List<String> lines = IOUtils.readLines(input, Consts.ENCODING);
                    for (String line : lines) {
                        if (StringUtils.isBlank(line) || StringUtils.startsWith(line, "#")) {
                            continue;
                        }

                        String[] kvs = StringUtils.split(line, ">", 2);
                        if (kvs == null || kvs.length != 2) {
                            logger.warn("URI权限配置错误-{}.", line);
                            continue;
                        }

                        PATHS.put(StringUtils.trimToEmpty(kvs[0]), StringUtils.trimToEmpty(kvs[1]));
                    }
                } finally {
                    IOUtils.closeQuietly(input);
                }
            }

            // 加载完成
            this.ready = true;
        } catch (Exception e) {
            throw new RuntimeException("URI权限解析异常.", e);
        }
    }

    /**
     * 检查是否需要检查权限
     */
    private boolean isNeedRights(String url) {
        for (String prefix : this.uriPrefixs) {
            if (StringUtils.startsWith(url, prefix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = this.getQueryURL(request);
        if (!this.isNeedRights(url)) {
            return true;
        }

        if (logger.isInfoEnabled()) {
            logger.info("拦截后台请求-" + url);
        }

        if (!this.ready) {
            throw new RuntimeException("权限未加载完成，禁止权限校验逻辑！");
        }

        // 上下文检查
        HttpSession session = request.getSession(true);
        UserContext ctx = UserContextUtils.findSessionContext(session);
        if (ctx != null) {
            UserContextHolder.set(ctx);
        }

        // 登录状态检查
        if (!UserContextUtils.isLogin()) {
            // 设置跳转页面
            String gotoUrl = this.gotoUrl;
            if (StringUtils.equalsIgnoreCase(request.getMethod(), "GET")) {
                if (StringUtils.isBlank(gotoUrl)) {
                    gotoUrl = url;
                }
            }

            // 保持会话
            UserContextUtils.setGotoURL(UserContextHolder.get(), gotoUrl);
            UserContextUtils.setSessionContext(session, ctx);

            // 页面跳转
            ServletUtils.redirectResponse(gotoUrl, request, response);

            // 禁止执行
            return false;
        }

        // URI权限检查
        Set<String> rgts = findRights(request.getRequestURI());
        if (!UserContextUtils.isRights(rgts)) {
            logger.warn("权限不足-{}", rgts);
            throw new RightException(new ArrayList<String>(rgts));
        }

        // 权限检查通过
        return true;
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        UserContextHolder.remove();
    }

    /**
     * 取得URI+QueryString字符串
     */
    private String getQueryURL(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String query = request.getQueryString();

        if (StringUtils.isNotBlank(query)) {
            uri += ("?" + query);
        }

        return uri;
    }

    // ~~~~~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~~ //

    public void setUriPrefixs(Set<String> uriPrefixs) {
        this.uriPrefixs = uriPrefixs;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }

    public void setRightConfigs(List<Resource> rightConfigs) {
        this.rightConfigs = rightConfigs;
    }

}

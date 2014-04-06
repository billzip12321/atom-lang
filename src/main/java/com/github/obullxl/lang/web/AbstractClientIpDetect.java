/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.servlet.ServletUtils;
import com.google.common.collect.Lists;

/**
 * 客户端访问过滤器
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractClientIpDetect.java, V1.0.1 2014年2月18日 上午11:39:38 $
 */
public abstract class AbstractClientIpDetect {
    private static final Logger logger         = LogUtils.get();

    /** 业务系统实现BEAN */
    public static final String  IP_DETECT_BEAN = "client_ip_detect_impl";

    /** IP黑名单 */
    private final List<String>  denyIps        = Lists.newArrayList();

    /** IP白名单 */
    private final List<String>  allowIps       = Lists.newArrayList();

    /**
     * 获取IP黑名单
     */
    public List<String> findDenyIps() {
        return this.denyIps;
    }

    /**
     * 获取IP白名单
     */
    public List<String> findAllowIps() {
        return this.allowIps;
    }

    /**
     * 处理请求的IP地址
     */
    public boolean onRequest(HttpServletRequest request, HttpServletResponse response) {
        String ip = ServletUtils.findRemoteIP(request);

        if (ipMatch(ip, this.denyIps)) {
            handleInvalidAccess(request, response, ip);
            return false;
        }

        if (!this.allowIps.isEmpty() && !ipMatch(ip, this.allowIps)) {
            handleInvalidAccess(request, response, ip);
            return false;
        }

        return true;
    }

    /** 
     * 销毁对象
     */
    public void destroy() {
        this.denyIps.clear();
        this.allowIps.clear();
    }

    /**
     * 禁止访问
     */
    public void handleInvalidAccess(HttpServletRequest request, HttpServletResponse response, String client) {
        String url = request.getRequestURL().toString();
        logger.warn("[IP防御]-IP[{}]禁止访问-URL[{}].", client, url);

        try {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (Exception e) {
            logger.error("[IP防御]-页面提示出错！");
        }
    }

    /**
     * 检查IP是否匹配
     */
    public static final boolean ipMatch(String client, List<String> ips) {
        for (String ip : ips) {
            if (client.matches(ip)) {
                return true;
            }
        }

        return false;
    }

}

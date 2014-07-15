/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.servlet;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.github.obullxl.lang.Profiler;
import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.config.ConfigFactory;
import com.github.obullxl.lang.sitex.SiteX;
import com.github.obullxl.lang.spring.ServletReadyEvent;
import com.github.obullxl.lang.spring.web.VelocityEngineFactory;
import com.github.obullxl.lang.spring.web.WebViewThemeHolder;
import com.github.obullxl.lang.user.UserContext;
import com.github.obullxl.lang.user.UserContextHolder;
import com.github.obullxl.lang.user.UserContextUtils;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.AbstractClientIpDetect;
import com.github.obullxl.lang.web.AbstractUserRightDetect;
import com.github.obullxl.lang.web.AbstractWebToolBox;
import com.github.obullxl.lang.web.WebContext;
import com.github.obullxl.lang.webx.WebX;
import com.github.obullxl.lang.webx.WebXUtils;

/**
 * Web空控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: DispatcherServletExt.java, V1.0.1 2013年11月25日 下午3:45:47 $
 */
public class DispatcherServletExt extends DispatcherServlet {
    private static final long       serialVersionUID                  = 3846370305975208566L;

    /** Logger */
    private static final Logger     logger                            = LoggerFactory.getLogger("SERVLET");

    /** Velocity引擎工厂Bean名称 */
    public static final String      VELOCITY_ENGINE_FACTORY_BEAN_NAME = "velocityEngineFactory";

    /** URL路径工具类 */
    private final UrlPathHelper     urlPathHelper                     = new UrlPathHelper();

    /** 性能时长 */
    private long                    perfThreshold                     = 5000;

    /** Velocity引擎工厂 */
    private VelocityEngine          velocityEngine;
    private VelocityEngineFactory   velocityEngineFactory;

    /** 客户端IP防御器 */
    private AbstractClientIpDetect  clientIpDetect;

    /** 用户权限检查器 */
    private AbstractUserRightDetect userRightDetect;

    /** 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        // 初始化上下文
        WebContext.init(config);

        // 系统参数
        String value = config.getInitParameter("perf.threshold");
        this.perfThreshold = NumberUtils.toLong(value, this.perfThreshold);

        // 上下文
        config.getServletContext().setAttribute("ctx", config.getServletContext().getContextPath());

        String rootPath = WebContext.getServletContext().getRealPath("/");
        rootPath = FilenameUtils.normalizeNoEndSeparator(rootPath, true);
        config.getServletContext().setAttribute("ctxRootPath", rootPath);
        logger.warn("[系统]-根目录-{}", rootPath);

        // 设置工具类
        String toolBoxClz = config.getInitParameter("tool.box.clazz");
        if (StringUtils.isNotBlank(toolBoxClz)) {
            // 加载指定工具
            try {
                Class<?> toolBoxCls = Class.forName(toolBoxClz);
                AbstractWebToolBox toolBox = (AbstractWebToolBox) toolBoxCls.newInstance();
                Map<String, Object> tools = toolBox.findToolBox();
                if (tools != null) {
                    for (Map.Entry<String, Object> entry : tools.entrySet()) {
                        config.getServletContext().setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception e) {
                logger.warn("[工具箱]-类[]加载异常！", toolBoxClz, e);
            }
        } else {
            // 加载默认工具
            Map<String, Object> tools = AbstractWebToolBox.findBasicTools();
            if (tools != null) {
                for (Map.Entry<String, Object> entry : tools.entrySet()) {
                    config.getServletContext().setAttribute(entry.getKey(), entry.getValue());
                }
            }
        }

        // 系统参数
        Map<String, String> cfgs = ConfigFactory.get().getConfig();
        for (Map.Entry<String, String> cfg : cfgs.entrySet()) {
            config.getServletContext().setAttribute(cfg.getKey(), cfg.getValue());
        }

        // 启动容器
        super.init(config);

        // 容器启动事件
        this.getWebApplicationContext().publishEvent(new ServletReadyEvent(Boolean.TRUE));
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#onRefresh(org.springframework.context.ApplicationContext)
     */
    protected void onRefresh(ApplicationContext context) {
        super.onRefresh(context);
        ServletConfig config = WebContext.getServletConfig();

        // 获取Velocity引擎
        this.velocityEngineFactory = context.getBean(VELOCITY_ENGINE_FACTORY_BEAN_NAME, VelocityEngineFactory.class);
        this.velocityEngine = this.velocityEngineFactory.get();

        // 获取X站点
        SiteX siteX = context.getBean(SiteX.SITE_X_BEAN, SiteX.class);
        if (siteX != null) {
            config.getServletContext().setAttribute(SiteX.class.getSimpleName(), siteX);
        }

        // 获取IP防御器
        String name = AbstractClientIpDetect.IP_DETECT_BEAN;
        if (context.containsBean(name)) {
            this.clientIpDetect = context.getBean(name, AbstractClientIpDetect.class);
        } else {
            logger.warn("[框架]-系统未设置IP防御器({}).", name);
        }

        // 获取用户权限检查器
        name = AbstractUserRightDetect.RIGHT_DETECT_BEAN;
        if (context.containsBean(name)) {
            this.userRightDetect = context.getBean(name, AbstractUserRightDetect.class);
        } else {
            logger.warn("[框架]-系统未设置访问权限控制({}).", name);
        }

        // 工具箱
        name = AbstractWebToolBox.TOOL_BOX_BEAN;
        Map<String, Object> tools = AbstractWebToolBox.findBasicTools();
        if (context.containsBean(name)) {
            AbstractWebToolBox toolBox = context.getBean(name, AbstractWebToolBox.class);
            tools = toolBox.findToolBox();
        }

        if (tools != null && !tools.isEmpty()) {
            for (Map.Entry<String, Object> entry : tools.entrySet()) {
                config.getServletContext().setAttribute(entry.getKey(), entry.getValue());
            }
        }
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // IP防御
        if (this.clientIpDetect != null) {
            if (!this.clientIpDetect.onRequest(request, response)) {
                return;
            }
        }

        // 计时
        Profiler.start("Web请求[" + request.getMethod() + "], URL[" + this.getQueryURL(request) + "].");
        try {
            LogUtils.updateLogID();

            // 打印请求参数
            this.dumpRequestParams(request);

            // 设置上下文
            WebContext.set(new WebContext(request, response));

            // 设置用户上下文
            this.updateUserContext(request);

            // 检查用户权限
            if (this.userRightDetect != null) {
                if (!this.userRightDetect.onRequest(request, response)) {
                    return;
                }
            }

            // 设置请求参数
            this.fetchRequestParams(request);

            // 执行业务逻辑
            super.doDispatch(request, response);
        } finally {
            // 清理上下文
            WebContext.remove();

            // 清理用户上下文
            UserContextHolder.remove();

            // 清理计时
            Profiler.release();
            this.dumpPerformance();
            Profiler.reset();

            LogUtils.removeLogID();
        }
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#noHandlerFound(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 尝试直接展示View视图
        String uri = this.urlPathHelper.getRequestUri(request);
        if (!StringUtils.startsWith(uri, "/")) {
            uri = "/" + uri;
        }

        uri = StringUtils.substringBeforeLast(uri, ".");

        // 直接展示视图
        if (this.velocityEngine.resourceExists(uri + ".vm")) {
            this.render(new ModelAndView(uri), request, response);
        }

        // 直接展示主页(home)
        else if (this.velocityEngine.resourceExists("/home" + uri + ".vm")) {
            this.render(new ModelAndView("/home" + uri), request, response);
        }

        // 直接展示视图(带主题)
        else if (this.velocityEngine.resourceExists("/" + WebViewThemeHolder.get() + uri + ".vm")) {
            this.render(new ModelAndView("/" + WebViewThemeHolder.get() + uri), request, response);
        }

        // 处理器没有找到错误
        else {
            super.noHandlerFound(request, response);
        }
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#render(org.springframework.web.servlet.ModelAndView, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Web数据值
        if (WebContext.get().isRequestRedirect()) {
            mv.getModelMap().clear();
        } else {
            // 参数
            mv.getModelMap().putAll(WebContext.get().getData());

            // 主题
            // mv.getModelMap().put(WebViewThemeHolder.THEME_KEY, WebViewThemeHolder.get());

            // 增加WebX工具类
            Map<String, WebX> xtools = WebXUtils.findAllWebX();
            for (Map.Entry<String, WebX> entry : xtools.entrySet()) {
                WebX webx = entry.getValue();
                mv.getModelMap().put(entry.getKey(), entry.getValue());

                if (logger.isDebugEnabled()) {
                    logger.debug("WebX容器-{}.", webx.getClass().getName());
                }
            }
        }

        super.render(mv, request, response);
    }

    /** 
     * @see org.springframework.web.servlet.FrameworkServlet#initFrameworkServlet()
     */
    protected void initFrameworkServlet() throws ServletException {
        super.initFrameworkServlet();

        ApplicationContext context = this.getWebApplicationContext();
        this.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
    }

    /**
     * 设置用户上下文
     */
    private void updateUserContext(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        UserContext ctx = UserContextUtils.findSessionContext(session);
        if (ctx != null) {
            UserContextHolder.set(ctx);
        } else {
            UserContextHolder.set(UserContext.newMockContext());
        }
    }

    /**
     * 打印性能分析日志 
     */
    private void dumpPerformance() {
        if (Profiler.getDuration() > this.perfThreshold) {
            logger.warn(Profiler.dump());
        }
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

    /**
     * 获取请求参数
     */
    private void fetchRequestParams(HttpServletRequest request) {
        // 请求参数
        Map<String, Object> data = WebContext.get().getData();

        // cookie中的参数
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                data.put(cookie.getName(), cookie.getValue());
            }
        }

        @SuppressWarnings("unchecked")
        Map<Object, Object> params = request.getParameterMap();
        for (Map.Entry<Object, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                String key = StringUtils.trimToEmpty(String.valueOf(entry.getKey()));
                String[] vs = (String[]) value;
                if (vs != null && vs.length == 1) {
                    data.put(key, vs[0]);
                } else {
                    data.put(key, value);
                }
            }
        }
    }

    /**
     * 打印请求参数
     */
    private void dumpRequestParams(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            StringBuilder txt = new StringBuilder(256);

            // request中参数
            txt.append("Web请求参数：").append(SystemUtils.LINE_SEPARATOR);
            @SuppressWarnings("unchecked")
            Map<Object, Object> params = request.getParameterMap();
            for (Map.Entry<Object, Object> entry : params.entrySet()) {
                txt.append("\t").append(entry.getKey()).append("=").append(ToString.toString(entry.getValue())).append(SystemUtils.LINE_SEPARATOR);
            }

            // cookie中的参数
            txt.append("Cookie请求参数：").append(SystemUtils.LINE_SEPARATOR);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    txt.append("\t").append(ToString.toString(cookie)).append(SystemUtils.LINE_SEPARATOR);
                }
            }

            // 打印
            logger.debug(txt.toString());
        }
    }

}

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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.github.obullxl.lang.Profiler;
import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.config.ConfigFactory;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.WebContext;
import com.github.obullxl.lang.xhelper.XHelper;
import com.github.obullxl.lang.xhelper.XHelperUtils;

/**
 * Web空控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: DispatcherServletExt.java, V1.0.1 2013年11月25日 下午3:45:47 $
 */
public class DispatcherServletExt extends DispatcherServlet {
    private static final long   serialVersionUID = 3846370305975208566L;

    /** Logger */
    private static final Logger logger           = LoggerFactory.getLogger("SERVLET");

    /** 性能时长 */
    private long                perfThreshold    = 5000;

    /** 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // 初始化上下文
        WebContext.init(config);

        // 系统参数
        String value = ConfigFactory.get().getPropertyValue("perfThreshold");
        this.perfThreshold = NumberUtils.toLong(value, this.perfThreshold);

        // 设置工具类
        config.getServletContext().setAttribute(IOUtils.class.getSimpleName(), new IOUtils());
        config.getServletContext().setAttribute(DateUtils.class.getSimpleName(), new DateUtils());
        config.getServletContext().setAttribute(StringUtils.class.getSimpleName(), new StringUtils());
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#onRefresh(org.springframework.context.ApplicationContext)
     */
    protected void onRefresh(ApplicationContext context) {
        super.onRefresh(context);

        // 增加XHelper工具类
        Map<String, XHelper> helpers = XHelperUtils.findXHelpers();
        for (Map.Entry<String, XHelper> entry : helpers.entrySet()) {
            String name = entry.getKey();
            XHelper helper = entry.getValue();

            this.getServletContext().setAttribute(name, helper);
            logger.warn("Web容器注册XHelper[{}]-{}.", name, helper.getClass().getName());
        }
    }

    /** 
     * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LogUtils.updateLogID();

        // 计时
        Profiler.start("Web请求，URL:" + this.getQueryURL(request));
        // 打印请求信息（DEBUG）
        this.dumpRequest(request);
        try {
            // 设置上下文
            WebContext.set(new WebContext(request, response));

            // 执行业务逻辑
            super.doDispatch(request, response);
        } finally {
            // 清理上下文
            WebContext.remove();

            // 清理计时
            Profiler.release();
            this.dumpPerformance();

            LogUtils.removeLogID();
        }
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

        if (StringUtils.isBlank(query)) {
            uri += ("?" + query);
        }

        return uri;
    }

    /**
     * 打印请求信息（DEBUG）
     */
    private void dumpRequest(HttpServletRequest request) {
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

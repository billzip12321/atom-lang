/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 视图主题拦截器
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebViewThemeInterceptor.java, 2012-8-11 下午3:29:35 Exp $
 */
public class WebViewThemeInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogUtils.get();

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String theme = "bootword";
        WebViewThemeHolder.set(theme);

        if (logger.isInfoEnabled()) {
            logger.info("[视图主题拦截器]-设置主题[" + WebViewThemeHolder.get() + "].");
        }

        return true;
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        if (logger.isInfoEnabled()) {
            logger.info("[视图主题拦截器]-清理主题.");
        }

        WebViewThemeHolder.remove();
    }

}

/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
    private static final Logger logger     = LogUtils.get();

    /** 忽略URI前缀 */
    private Set<String>         uriPrefixs = new HashSet<String>();

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURI();
        for (String prefix : this.uriPrefixs) {
            if (StringUtils.startsWith(url, prefix)) {
                return true;
            }
        }

        String theme = "bootword";
        WebViewThemeHolder.set(theme);

        if (logger.isDebugEnabled()) {
            logger.debug("[视图主题拦截器]-设置主题[" + WebViewThemeHolder.get() + "].");
        }

        return true;
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug("[视图主题拦截器]-清理主题.");
        }

        WebViewThemeHolder.remove();
    }

    // ~~~~~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~~ //

    public void setUriPrefixs(Set<String> uriPrefixs) {
        this.uriPrefixs = uriPrefixs;
    }

}

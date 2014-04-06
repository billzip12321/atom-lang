/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用户上下文拦截器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserContextInterceptor.java, V1.0.1 2013年12月12日 上午9:15:20 $
 */
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    /** URI后缀 */
    private Set<String> exts;

    /**
     * 检查是否需要检查权限
     */
    private boolean isNeedSetContext(String uri) {
        for (String ext : this.exts) {
            if (StringUtils.endsWith(uri, ext)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if (!this.isNeedSetContext(uri)) {
            return true;
        }

        // 上下文
        HttpSession session = request.getSession(true);
        UserContext ctx = UserContextUtils.findSessionContext(session);
        if (ctx != null) {
            UserContextHolder.set(ctx);
        } else {
            UserContextHolder.set(UserContext.newMockContext());
        }

        // 检查通过
        return true;
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        UserContextHolder.remove();
    }

    // ~~~~~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~~ //

    public void setExts(Set<String> exts) {
        this.exts = exts;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 用户上下文过滤器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserContextFilter.java, V1.0.1 2013年12月12日 上午9:15:20 $
 */
public class UserContextFilter extends OncePerRequestFilter {

    /** 
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            // 设置
            HttpSession session = request.getSession(true);
            UserContext ctx = UserContextUtils.findSessionContext(session);
            if (ctx != null) {
                UserContextHolder.set(ctx);
            } else {
                UserContextHolder.set(UserContext.newMockContext());
            }

            // 过滤
            chain.doFilter(request, response);
        } finally {
            // 清理
            UserContextHolder.remove();
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主机访问过滤器
 * 
 * @author obullxl@gmail.com
 * @version $Id: RemoteHostFilter.java, V1.0.1 2014年2月18日 上午11:39:38 $
 */
public class RemoteHostFilter implements Filter {

    /** 过滤器配置 */
    private FilterConfig filterConfig;

    private String[]     allow;
    private String[]     deny;

    /** 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.allow = extractRegExps(filterConfig.getInitParameter("allow"));
        this.deny = extractRegExps(filterConfig.getInitParameter("deny"));
    }

    /** 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ip = ServletUtils.findRemoteIP((HttpServletRequest) request);

        if (hasMatch(ip, deny)) {
            handleInvalidAccess(request, response, ip);
            return;
        }

        if ((allow.length > 0) && !hasMatch(ip, allow)) {
            handleInvalidAccess(request, response, ip);
            return;
        }

        chain.doFilter(request, response);
    }

    /** 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        this.filterConfig = null;
        this.allow = null;
        this.deny = null;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (filterConfig == null)
            return ("ClientAddrFilter()");
        StringBuffer sb = new StringBuffer("ClientAddrFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private String[] extractRegExps(String initParam) {
        if (initParam == null) {
            return new String[0];
        } else {
            return initParam.split(",");
        }
    }

    private void handleInvalidAccess(ServletRequest request, ServletResponse response, String clientAddr) throws IOException {
        // String url = ((HttpServletRequest) request).getRequestURL().toString();
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    private boolean hasMatch(String clientAddr, String[] regExps) {
        for (int i = 0; i < regExps.length; i++) {
            if (clientAddr.matches(regExps[i]))
                return true;
        }

        return false;
    }

}

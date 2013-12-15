/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.servlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * Web请求工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ServletUtils.java, V1.0.1 2013-6-30 下午9:31:34 $
 */
public class ServletUtils {
    /** Logger */
    protected static final Logger logger           = LogUtils.get();

    /** HttpSesstion值KEY */
    public static final String    SESSION_USER_KEY = "user_info";

    /**
     * 错误请求返回
     */
    public static BizResponse toErrorResponse(EnumBase error) {
        BizResponse response = new BizResponse();

        response.setBizLog(LogUtils.findLogID());
        response.setSuccess(false);
        response.setRespCode(error.code());
        response.setRespDesp(error.desp());

        return response;
    }

    /**
     * 构造返回结果
     */
    public static void writeResponse(ServletResponse response, EnumBase error) {
        writeResponse(response, toErrorResponse(error));
    }

    /**
     * 输出返回结果
     */
    public static void writeResponse(ServletResponse response, BizResponse data) {
        String value = JSON.toJSONString(data);
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("[Web请求]-JSON返回: {}", value);
            }

            response.getWriter().print(value);
        } catch (Exception e) {
            logger.error("HTTP输出JSON[" + value + "]异常！", e);
        }
    }

    /**
     * 页面重定向
     */
    public static void redirectResponse(String url, ServletRequest request, ServletResponse response) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            logger.warn("页面跳转异常-{}", url, e);
        }
    }

    /**
     * 获取请求参数
     */
    public static String findRequestParam(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    /**
     * 获取访问者IP。
     * <p/>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * </p>
     * 先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request.getRemoteAddr()。
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        if (StringUtils.isNotBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            return ip;
        }

        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            ip = StringUtils.substringBefore(ip, ",");
        } else {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 获取所有请求参数
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> findRequestParams(ServletRequest request) {
        Map<String, Object> params = new ConcurrentHashMap<String, Object>();

        Map<Object, Object> srcObjs = request.getParameterMap();
        for (Map.Entry<Object, Object> srcObj : srcObjs.entrySet()) {
            params.put(ObjectUtils.toString(srcObj.getKey()), srcObj.getValue());
        }

        return params;
    }

    /**
     * 打印出所有的请求参数
     */
    public static String dumpRequest(ServletRequest request) {
        StringBuilder txt = new StringBuilder();

        Map<String, Object> params = findRequestParams(request);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            txt.append("\t");
            txt.append(param.getKey());
            txt.append("=");
            txt.append(ToString.toString(param.getValue()));
            txt.append(SystemUtils.LINE_SEPARATOR);
        }

        return txt.toString();
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.github.obullxl.lang.biz.BizException;
import com.github.obullxl.lang.biz.ResponseEnum;
import com.github.obullxl.lang.biz.RightException;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.WebContext;
import com.github.obullxl.lang.web.servlet.ServletUtils;

/**
 * 异常处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExceptionResolver.java, V1.0.1 2013-7-17 下午8:31:49 $
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /** 权限提示页面 */
    private String              rightUrl;

    /** 业务提示页面 */
    private String              bizOptUrl;

    /** 未知异常页面 */
    private String              unknowUrl;

    /**
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        logger.error("系统发生异常，Handler:" + handler, e);

        // 权限异常
        if (RightException.class.isAssignableFrom(e.getClass())) {
            RightException ex = (RightException) e;
            logger.error("权限异常-{}.", ex.getRgtCodes(), e);

            if (WebContext.get().isRequestJSON()) {
                ServletUtils.writeResponse(response, ResponseEnum.PERMISSION_ERROR);
            } else {
                ServletUtils.redirectResponse(this.rightUrl, request, response);
            }
        }

        // 业务异常
        else if (BizException.class.isAssignableFrom(e.getClass())) {
            BizException ex = (BizException) e;
            logger.error("业务异常-{}.", ex, e);

            if (WebContext.get().isRequestJSON()) {
                ServletUtils.writeResponse(response, ex.getErrEnum());
            } else {
                ServletUtils.redirectResponse(this.bizOptUrl, request, response);
            }
        }

        // 未知异常
        else {
            logger.error("未知异常.", e);

            if (WebContext.get().isRequestJSON()) {
                ServletUtils.writeResponse(response, ResponseEnum.SYSTEM_ERROR);
            } else {
                ServletUtils.redirectResponse(this.unknowUrl, request, response);
            }
        }

        // 处理完成
        return new ModelAndView();
    }

    // ~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public void setBizOptUrl(String bizOptUrl) {
        this.bizOptUrl = bizOptUrl;
    }

    public void setUnknowUrl(String unknowUrl) {
        this.unknowUrl = unknowUrl;
    }

}

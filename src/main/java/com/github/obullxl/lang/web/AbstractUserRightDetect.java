/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限检查器
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractUserRightDetect.java, V1.0.1 2014年3月30日 下午6:58:31 $
 */
public abstract class AbstractUserRightDetect {
    /** Spring配置BEAN */
    public static final String RIGHT_DETECT_BEAN = "user_right_detect_impl";

    /**
     * 拦截请求
     */
    public abstract boolean onRequest(HttpServletRequest request, HttpServletResponse response);

}

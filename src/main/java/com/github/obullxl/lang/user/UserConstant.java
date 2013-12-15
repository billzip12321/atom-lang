/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

/**
 * 用户常量
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserConstant.java, V1.0.1 2013年12月12日 上午9:48:37 $
 */
public interface UserConstant {

    /** 上下文会话KEY */
    public static final String CTX_SESSION_KEY = "_context_session_key_";

    /** 登录KEY */
    public static final String USER_LOGIN_KEY  = "is.user.login";

    /** 管理员KEY */
    public static final String USER_ADMIN_KEY  = "is.user.admin";

    /** 登录跳转页面KEY */
    public static final String URL_GOTO_KEY    = "url.login.goto";

}

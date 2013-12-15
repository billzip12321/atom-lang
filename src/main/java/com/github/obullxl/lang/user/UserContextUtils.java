/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.BooleanUtils;

/**
 * 用户上下文工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserContextUtils.java, V1.0.1 2013年12月12日 上午9:55:50 $
 */
public class UserContextUtils implements UserConstant {

    /**
     * 获取用户所有权限码
     */
    public static final Set<String> findRights() {
        return UserContextHolder.get().getUserRights();
    }

    /**
     * 获取用户额外数据
     */
    public static final Map<String, Object> findExtData() {
        return UserContextHolder.get().getUserExtData();
    }

    /**
     * 获取额外数据String
     */
    public static final String findStringData(String key) {
        return String.valueOf(findExtData().get(key));
    }

    /**
     * 获取额外数据BOOLEAN
     */
    public static final boolean findBooleanData(String key) {
        return BooleanUtils.toBoolean(findStringData(key));
    }

    /**
     * 是否已经登录
     */
    public static final boolean isLogin() {
        return findBooleanData(USER_LOGIN_KEY);
    }

    /**
     * 设置是否已经登录
     */
    public static final void setLogin(UserContext ctx, boolean flag) {
        ctx.getUserExtData().put(USER_LOGIN_KEY, flag);
    }

    /**
     * 是否有后台权限
     */
    public static final boolean isAdmin() {
        return findBooleanData(USER_ADMIN_KEY);
    }

    /**
     * 设置是否为管理员
     */
    public static final void setAdmin(UserContext ctx, boolean flag) {
        ctx.getUserExtData().put(USER_ADMIN_KEY, flag);
    }

    /**
     * 获取登录调整URL
     */
    public static final String findGotoURL() {
        return findStringData(URL_GOTO_KEY);
    }

    /**
     * 设置登录调整URL
     */
    public static final void setGotoURL(UserContext ctx, String url) {
        ctx.getUserExtData().put(URL_GOTO_KEY, url);
    }

    /**
     * 获取会话上下文
     */
    public static final UserContext findSessionContext(HttpSession session) {
        if (session == null) {
            return null;
        }

        Object object = session.getAttribute(CTX_SESSION_KEY);
        if (object != null && UserContext.class.isAssignableFrom(object.getClass())) {
            return (UserContext) object;
        }

        return null;
    }

    /**
     * 设置会话上下文
     */
    public static final void setSessionContext(HttpSession session, UserContext context) {
        if (session != null) {
            session.setAttribute(CTX_SESSION_KEY, context);
        }
    }

    /**
     * 是否有权限码
     */
    public static final boolean isRight(String right) {
        if (isAdmin()) {
            return true;
        }

        if (!isLogin()) {
            return false;
        }

        return findRights().contains(right);
    }

    /**
     * 是否有任一的权限码OR
     */
    public static final boolean isRight(Set<String> rights) {
        if (isAdmin()) {
            return true;
        }

        if (!isLogin()) {
            return false;
        }

        for (String right : rights) {
            if (isRight(right)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 是否有所有的权限码AND
     */
    public static final boolean isRights(Set<String> rights) {
        if (isAdmin()) {
            return true;
        }

        if (!isLogin()) {
            return false;
        }

        for (String right : rights) {
            if (!isRight(right)) {
                return false;
            }
        }

        return true;
    }

}

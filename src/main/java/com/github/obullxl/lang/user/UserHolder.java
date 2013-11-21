/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

/**
 * 用户持有器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserInfo.java, V1.0.1 2013-1-18 上午10:59:34 $
 */
public class UserHolder {
    private static final ThreadLocal<UserInfo> _holder = new ThreadLocal<UserInfo>();

    public static void set(UserInfo user) {
        _holder.set(user);
    }

    public static void remove() {
        _holder.remove();
    }

    public static UserInfo get() {
        return _holder.get();
    }
}

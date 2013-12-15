/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

/**
 * 用户持有器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserContextHolder.java, V1.0.1 2013-1-18 上午10:59:34 $
 */
public class UserContextHolder {
    /** 用户上下文 */
    private static final ThreadLocal<UserContext> _holder = new ThreadLocal<UserContext>();

    /**
     * 设置用户上下文
     */
    public static void set(UserContext user) {
        _holder.set(user);
    }

    /**
     * 删除用户上下文
     */
    public static void remove() {
        _holder.remove();
    }

    /**
     * 获取用户上下文
     * <p/>
     * 如果上下文不存在，则模拟一个上下文！
     */
    public static UserContext get() {
        UserContext ctx = _holder.get();
        if (ctx == null) {
            set(UserContext.newMockContext());
        }

        return _holder.get();
    }
}

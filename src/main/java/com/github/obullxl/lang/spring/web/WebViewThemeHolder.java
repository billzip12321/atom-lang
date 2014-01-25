/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.apache.commons.lang.StringUtils;

/**
 * 页面主题持有器
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebViewThemeHolder.java, V1.0.1 2013年12月4日 下午7:34:03 $
 */
public class WebViewThemeHolder {
    /** 默认主题 */
    public static String                     DEFAULT   = "bootword";

    /** 主题参数KEY */
    public static final String               THEME_KEY = "_theme";

    /** 主题信息 */
    private static final ThreadLocal<String> holder    = new ThreadLocal<String>();

    /**
     * 设置主题
     */
    public static final void set(String theme) {
        holder.set(theme);
    }

    /**
     * 设置默认主题
     */
    public static final void setDefault(String theme) {
        DEFAULT = theme;
    }

    /**
     * 获取主题
     */
    public static final String get() {
        String theme = holder.get();
        if (StringUtils.isBlank(theme)) {
            theme = DEFAULT;
        }

        return theme;
    }

    /**
     * 清空主题
     */
    public static final void remove() {
        holder.remove();
    }

}

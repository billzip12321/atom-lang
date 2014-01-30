/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.webx;

import com.github.obullxl.lang.spring.web.WebViewThemeHolder;

/**
 * 主题相关WebX
 * 
 * @author obullxl@gmail.com
 * @version $Id: ThemeWebX.java, V1.0.1 2014年1月29日 下午12:29:07 $
 */
public class ThemeWebX implements WebX {
    
    /**
     * 获取主题
     */
    public String findTheme() {
        return WebViewThemeHolder.get();
    }
    
}

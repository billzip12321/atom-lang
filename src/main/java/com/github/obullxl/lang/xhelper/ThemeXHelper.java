/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.xhelper;

import com.github.obullxl.lang.spring.web.WebViewThemeHolder;

/**
 * 主题X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: ThemeXHelper.java, V1.0.1 2013年12月17日 下午12:30:15 $
 * @deprecated 请使用{@code WebX}接口
 */
public class ThemeXHelper extends AbstractXHelper {

    /**
     * 获取主题
     */
    public String findTheme() {
        return WebViewThemeHolder.get();
    }

}

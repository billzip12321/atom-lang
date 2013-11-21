/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.tpls;

import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * FreeMarker模板
 * 
 * @author obullxl@gmail.com
 * @version $Id: FreeMarkerTPL.java, V1.0.1 2013-3-24 下午2:15:05 $
 */
public class FreeMarkerTPL {
    /** FreeMarker全局配置 */
    private static final Configuration cfg = initConfig();

    /**
     * 初始化
     */
    private static final Configuration initConfig() {
        Configuration config = new Configuration();
        config.setObjectWrapper(new DefaultObjectWrapper());

        config.setDateFormat(DateUtils.DW);
        config.setDateTimeFormat(DateUtils.DL);
        
        config.setDefaultEncoding("UTF-8");
        config.setOutputEncoding("UTF-8");

        try {
            config.setSetting(Configuration.DEFAULT_ENCODING_KEY, "UTF-8");
            config.setSetting(Configuration.OUTPUT_ENCODING_KEY, "UTF-8");
        } catch (Exception e) {
            LogUtils.error("[模板]-初始化FreeMarker配置异常!", e);
        }

        return config;
    }

    /**
     * 获取配置
     */
    public static final Configuration findConfig() {
        return cfg;
    }

}

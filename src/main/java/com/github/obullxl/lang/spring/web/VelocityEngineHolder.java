/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * SpringMVC模板VelocityEngine持有器
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityEngineHolder.java, V1.0.1 2014年1月16日 下午2:06:32 $
 */
public final class VelocityEngineHolder {
    /** Logger */
    private static final Logger   logger = LogUtils.get();

    /** 实例 */
    private static VelocityEngine engine;

    /**
     * 初始化
     */
    public static void init(VelocityEngine instance) {
        if (instance == null) {
            RuntimeException e = new RuntimeException("VelocityEngine初始化为NULL.");
            logger.error("[VEngine初始化]-对象为NULL.", e);
            throw e;
        }

        if (engine != null) {
            RuntimeException e = new RuntimeException("VelocityEngine已经初始化.");
            logger.error("[VEngine初始化]-对象已经初始化.", e);
            throw e;
        }

        // 设置
        engine = instance;
        logger.warn("[VEngine初始化]-初始化完成!");
    }

    /**
     * 获取对象
     */
    public static final VelocityEngine get() {
        return engine;
    }

}

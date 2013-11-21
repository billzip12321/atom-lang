/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认日志工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: LogUtils.java, 2012-8-18 下午8:25:49 Exp $
 */
public class LogUtils {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger("LOGGER");

    /**
     * 获取Logger实例
     */
    public static final Logger get() {
        return logger;
    }

    // ~~~~~~~~~~ 公用日志类 ~~~~~~~~~~~~ //

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void debug(String msg, Throwable e) {
        logger.debug(msg, e);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void info(String msg, Throwable e) {
        logger.info(msg, e);
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void warn(String msg, Throwable e) {
        logger.warn(msg, e);
    }

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void error(String msg, Throwable e) {
        logger.error(msg, e);
    }

}

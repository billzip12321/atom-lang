/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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

    // ~~~~~~~~~~ 线程日志ID ~~~~~~~~~~~~ //

    /** 日志ID */
    public static final String LOG_ID_KEY = "LogID";

    /**
     * 获取线程LogID
     */
    public static final String newLogID() {
        return StringUtils.remove(UUID.randomUUID().toString(), "-");
    }

    /**
     * 更新线程LogID
     */
    public static final void updateLogID() {
        updateLogID(newLogID());
    }

    /**
     * 更新线程LogID
     */
    public static final void updateLogID(String value) {
        MDC.put(LOG_ID_KEY, value);
    }

    /**
     * 获取线程LogID
     */
    public static final String findLogID() {
        return MDC.get(LOG_ID_KEY);
    }

    /**
     * 删除线程LogID
     */
    public static final void removeLogID() {
        removeLogID(LOG_ID_KEY);
    }

    /**
     * 删除线程LogID
     */
    public static final void removeLogID(String logKey) {
        MDC.remove(logKey);
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

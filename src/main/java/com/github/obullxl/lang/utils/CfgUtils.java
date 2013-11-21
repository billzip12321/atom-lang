/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 默认配置工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgUtils.java, 2012-8-18 下午8:25:49 Exp $
 */
public class CfgUtils {

    /** 根目录 */
    private static String ROOT_PATH        = "";

    /** 配置目录 */
    private static String CONFIG_PATH      = "";
    private static String CONFIG_PATH_NAME = "config";

    /**
     * 设置配置目录名
     */
    public static final void setCfgPathName(String cfgPathName) {
        if (StringUtils.isBlank(cfgPathName)) {
            throw new RuntimeException("设置系统配置目录名错误！");
        }

        CONFIG_PATH = "";
        CONFIG_PATH_NAME = cfgPathName;
    }

    /**
     * 设置根目录
     */
    public static final void setRootPath(String rootPath) {
        if (StringUtils.isBlank(rootPath)) {
            throw new RuntimeException("设置系统根目录错误！");
        }

        ROOT_PATH = rootPath;
    }

    /**
     * 设置配置目录
     */
    public static final void setConfigPath(String configPath) {
        if (StringUtils.isBlank(configPath)) {
            throw new RuntimeException("设置系统配置目录错误！");
        }

        CONFIG_PATH = configPath;
    }

    /**
     * 应用系统根目录
     */
    public static final String findRootPath() {
        if (StringUtils.isBlank(ROOT_PATH)) {
            File root = new File(".");
            ROOT_PATH = FilenameUtils.normalizeNoEndSeparator(root.getAbsolutePath());
            LogUtils.info("系统根目录：" + ROOT_PATH);
        }

        return ROOT_PATH;
    }

    /**
     * 应用系统配置目录
     * <p/>
     * 查找当前或上一级‘config’目录
     */
    public static final String findConfigPath() {
        if (StringUtils.isBlank(CONFIG_PATH)) {
            File root = new File(".");

            try {
                // ./config
                File config = new File(root, CONFIG_PATH_NAME);
                if (config.exists() && config.isDirectory()) {
                    CONFIG_PATH = FilenameUtils.normalizeNoEndSeparator(config.getAbsolutePath());
                    return CONFIG_PATH;
                }

                // ../config
                root = root.getParentFile();
                if (root != null) {
                    config = new File(root, CONFIG_PATH_NAME);
                    if (config.exists() && config.isDirectory()) {
                        CONFIG_PATH = FilenameUtils.normalizeNoEndSeparator(config.getAbsolutePath());
                        return CONFIG_PATH;
                    }
                }

                // ../../config
                root = root.getParentFile();
                if (root != null) {
                    config = new File(root, CONFIG_PATH_NAME);
                    if (config.exists() && config.isDirectory()) {
                        CONFIG_PATH = FilenameUtils.normalizeNoEndSeparator(config.getAbsolutePath());
                        return CONFIG_PATH;
                    }
                }

                // ../../../config
                root = root.getParentFile();
                if (root != null) {
                    config = new File(root, CONFIG_PATH_NAME);
                    if (config.exists() && config.isDirectory()) {
                        CONFIG_PATH = FilenameUtils.normalizeNoEndSeparator(config.getAbsolutePath());
                        return CONFIG_PATH;
                    }
                }

                // 没有找到
                throw new RuntimeException("系统配置目录没有找到，请检查系统配置是否正确！");
            } finally {
                LogUtils.info("系统配置目录：" + CONFIG_PATH);
            }
        }

        // 配置目录
        return CONFIG_PATH;
    }

}

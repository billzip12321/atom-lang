/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 系统配置工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: SystemConfigUtils.java, 2011-12-24 下午12:02:10 Exp $
 */
public final class KVUtils {

    /**
     * 系统参数配置
     */
    private static final Map<String, String> config = new ConcurrentHashMap<String, String>();

    /**
     * 设置系统参数
     */
    public static final void setKeyValue(String key, String value) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("[系统参数]-设置系统参数-系统参数Key为NULL.");
        }

        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("[系统参数]-设置系统参数-系统参数Value为NULL.");
        }

        config.put(key, value);
        System.setProperty(key, value);
    }

    /**
     * 设置系统参数
     */
    public static final void setKeyValues(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                setKeyValue(key, value);
            }
        }
    }
    
    /**
     * 清空Key/Value配置
     */
    public static final void clearKeyValues() {
        config.clear();
    }

    /**
     * 检测是否存在参数
     */
    public static final boolean exist(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        boolean exist = config.containsKey(key);
        if (!exist) {
            String prop = System.getProperty(key);
            if (StringUtils.isNotBlank(prop)) {
                exist = true;
            }
        }

        return exist;
    }

    /**
     * 获取所有配置信息
     */
    public static final Map<String, String> getAll() {
        Map<String, String> all = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : config.entrySet()) {
            all.put(entry.getKey(), entry.getValue());
        }

        return all;
    }

    /**
     * 获取系统参数String
     */
    public static final String getString(String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("[系统参数]-获取系统参数-系统参数Key为NULL.");
        }

        String value = config.get(key);
        if (StringUtils.isBlank(value)) {
            value = System.getProperty(key);
        }

        return value;
    }

    /**
     * 获取系统参数String
     */
    public static final String getString(String key, String defaultValue) {
        String value = getString(key);

        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }

        return value;
    }

    /**
     * 获取系统参数Boolean
     */
    public static final boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key, String.valueOf(defaultValue));
        return BooleanUtils.toBoolean(value);
    }

    /**
     * 获取系统参数Integer
     */
    public static final int getInteger(String key, int defaultValue) {
        try {
            return Integer.parseInt(getString(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取系统参数Long
     */
    public static final long getLong(String key, long defaultValue) {
        try {
            return Long.parseLong(getString(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // ~~~~~~~~~~~ 个性化方法 ~~~~~~~~~~~ //

    /**
     * 从Properties对象中解析Key/Value内容
     */
    public static final Map<String, String> fromProps(Properties props) {
        try {

            Map<String, String> map = new ConcurrentHashMap<String, String>();
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                String key = StringUtils.trimToEmpty(ObjectUtils.toString(entry.getKey()));
                String value = StringUtils.trimToEmpty(ObjectUtils.toString(entry.getValue()));

                map.put(key, value);
            }

            return map;
        } catch (Exception e) {
            throw new RuntimeException("从Properties对象中解析Map异常!");
        }
    }

    /**
     * 从XML文件中解析Key/Value内容
     */
    public static final Map<String, String> fromXML(String path) {
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            Properties props = new Properties();
            props.loadFromXML(input);

            return fromProps(props);
        } catch (Exception e) {
            throw new RuntimeException("从XML文件[" + path + "]中解析Map异常!");
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
    
    /**
     * 从Property文件中解析Key/Value内容
     */
    public static final Map<String, String> fromProperty(String path) {
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            Properties props = new Properties();
            props.load(input);

            return fromProps(props);
        } catch (Exception e) {
            throw new RuntimeException("从Property文件[" + path + "]中解析Map异常!");
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

}

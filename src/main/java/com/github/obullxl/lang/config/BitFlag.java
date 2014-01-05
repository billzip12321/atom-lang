/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.config;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 位标志
 * 
 * @author obullxl@gmail.com
 * @version $Id: BitFlag.java, V1.0.1 2013年12月15日 下午6:56:35 $
 */
public abstract class BitFlag {
    /** 布尔值为真的字符串 */
    public static final List<String> TRUES          = Arrays.asList("1", "T", "Y");

    /** 标志字符串 */
    private final String             flag;

    /** 默认字符串 */
    private String                   defaultString  = StringUtils.EMPTY;

    /** 默认布尔值 */
    private boolean                  defaultBoolean = false;

    /**
     * CTOR
     */
    public BitFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取标志字符串
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * 获取默认字符串
     */
    public String getDefaultString() {
        return defaultString;
    }

    /**
     * 设置默认字符串
     */
    public void setDefaultString(String defaultString) {
        this.defaultString = defaultString;
    }

    /**
     * 获取默认布尔值
     */
    public boolean getDefaultBoolean() {
        return defaultBoolean;
    }

    /**
     * 设置默认布尔值
     */
    public void setDefaultBoolean(boolean defaultBoolean) {
        this.defaultBoolean = defaultBoolean;
    }

    /**
     * 获取索引位字符串
     */
    public String findString(int idx) {
        return this.findString(idx, this.defaultString);
    }

    /**
     * 获取索引位字符串
     */
    public String findString(int idx, String value) {
        idx = Math.abs(idx);
        String bit = StringUtils.substring(this.flag, idx, idx + 1);
        if (StringUtils.isBlank(bit)) {
            bit = value;
        }

        return bit;
    }

    /**
     * 获取索引位字符串
     */
    public boolean findBoolean(int idx) {
        return this.findBoolean(idx, this.defaultBoolean);
    }

    /**
     * 获取索引位字符串
     */
    public boolean findBoolean(int idx, boolean value) {
        String bit = this.findString(idx);
        if (StringUtils.isBlank(bit)) {
            return value;
        }

        return TRUES.contains(bit);
    }
    
}

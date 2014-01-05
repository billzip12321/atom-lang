/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 开关值类
 * 
 * @author obullxl@gmail.com
 * @version $Id: FlagValve.java, V1.0.1 2013年12月30日 上午11:43:35 $
 */
public class FlagValve {
    /** Logger */
    private static final Logger logger       = LogUtils.get();

    /** CHAR未知位值 */
    public static final char    NULL_CHAR    = '?';

    /** String未知位值 */
    public static final String  NULL_STRING  = String.valueOf(NULL_CHAR);

    /** CHAR布尔真位值 */
    public static final char    TRUE_CHAR    = '1';

    /** String布尔真位值 */
    public static final String  TRUE_STRING  = String.valueOf(TRUE_CHAR);

    /** CHAR布尔假位值 */
    public static final char    FALSE_CHAR   = '0';

    /** String布尔假位值 */
    public static final String  FALSE_STRING = String.valueOf(FALSE_CHAR);

    /** 开关字符串值 */
    private String              valve        = "";

    /**
     * CTOR.
     */
    public FlagValve() {
    }

    /**
     * CTOR.
     */
    public FlagValve(String valve) {
        this.setValve(valve);
    }

    /**
     * 取得整个开关值
     */
    public String getValve() {
        return this.valve;
    }

    /**
     * 设置整个开关值
     */
    public void setValve(String valve) {
        this.valve = StringUtils.defaultIfEmpty(valve, NULL_STRING);
    }
    
    /**
     * 根据索引得到CHAR，如果为空，则返回NULL_CHAR
     */
    public char gotChar(int index) {
        return gotChar(index, NULL_CHAR);
    }

    /**
     * 根据索引得到STRING，如果为空，则返回NULL_STRING
     */
    public String gotString(int index) {
        return gotString(index, NULL_STRING);
    }

    /**
     * 根据索引，取得CHAR值
     */
    public char gotChar(int index, char defaultValue) {
        int length = StringUtils.length(this.valve);

        if (index + 1 > length) {
            return defaultValue;
        }

        return this.valve.charAt(index);
    }

    /**
     * 根据索引，取得STRING值
     */
    public String gotString(int index, String defaultValue) {
        int length = StringUtils.length(this.valve);

        if (index + 1 > length) {
            return defaultValue;
        }

        return String.valueOf(this.valve.charAt(index));
    }

    /**
     * 根据索引值，取得BOOLEAN值，默认为FALSE
     */
    public boolean gotBoolean(int index) {
        return gotBoolean(index, false);
    }

    /**
     * 根据索引值，取得BOOLEAN值
     */
    public boolean gotBoolean(int index, boolean defaultValue) {
        return (this.gotChar(index) == TRUE_CHAR);
    }

    /**
     * 获取枚举类
     */
    @SuppressWarnings({ "unchecked" })
    public <T extends EnumBase> T gotEnumBase(int index, EnumBase[] values, Enum<? extends EnumBase> defaultEnum) {
        String value = this.gotString(index);

        try {
            for (EnumBase enm : values) {
                if (StringUtils.equals(enm.code(), value)) {
                    return (T) enm;
                }
            }
        } catch (Exception e) {
            logger.error("转换枚举异常", e);
        }

        return (T) defaultEnum;
    }

    /**
     * 设置开关值
     */
    public synchronized FlagValve sotValue(int index, String value) {
        if (StringUtils.length(value) != 1) {
            throw new RuntimeException("设置值异常[" + value + "]，值长度超过1");
        }

        if (StringUtils.length(this.valve) <= index) {
            while (StringUtils.length(this.valve) < index) {
                this.valve += NULL_STRING;
            }

            this.valve += value;
        } else {
            String start = StringUtils.substring(this.valve, 0, index);
            String finish = StringUtils.substring(this.valve, index + 1);
            this.valve = start + value + finish;
        }

        return this;
    }

    /**
     * 布尔值转换为字符串
     */
    public String toValue(boolean value) {
        return value ? TRUE_STRING : FALSE_STRING;
    }

    /**
     * 设置BOOLEAN开关值
     */
    public synchronized FlagValve sotValue(int index, boolean value) {
        return this.sotValue(index, this.toValue(value));
    }

    /**
     * 设置枚举开关值
     */
    public synchronized FlagValve sotValue(int index, EnumBase enm) {
        return this.sotValue(index, enm.code());
    }

    /**
     * 开关值是否为空
     */
    public boolean empty() {
        return StringUtils.isBlank(this.valve);
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.valve;
    }

}

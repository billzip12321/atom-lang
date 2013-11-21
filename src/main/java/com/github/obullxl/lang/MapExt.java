/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.github.obullxl.lang.utils.DateUtils;

/**
 * 对象扩展属性
 * 
 * @author obullxl@gmail.com
 * @version $Id: MapExt.java, V1.0.1 2013-2-3 上午10:57:13 $
 */
public class MapExt extends ConcurrentHashMap<String, String> implements Cloneable {
    private static final long  serialVersionUID = -2583709812151106668L;

    /** KV之间的分隔符 */
    public static final String ET_SEP           = " ";

    /** K/V分隔符 */
    public static final String KV_SEP           = "=";

    /**
     * 把字符串解析成扩展属性，K/V以‘空格’分隔，K与V间以‘=’分隔，自动去除格式不正确的字符串。
     */
    public static final MapExt parse(String prop) {
        return parse(prop, ET_SEP, KV_SEP);
    }

    /**
     * 把字符串解析成扩展属性，自动去除格式不正确的字符串。
     * 
     * @param prop K/V以‘空格’分隔，K与V间以‘=’分隔。
     * @param itmSep KV之间的分隔符，默认以‘空格’分隔。
     * @param kvSep K/V分隔符，默认为‘=’分隔。
     */
    public static final MapExt parse(String prop, String etSep, String kvSep) {
        MapExt ext = new MapExt();

        // 检查分隔符
        if (etSep == null) {
            etSep = ET_SEP;
        }
        if (kvSep == null) {
            kvSep = KV_SEP;
        }

        // ‘空格’分隔
        String[] kvs = StringUtils.split(prop, etSep);
        if (kvs == null || kvs.length == 0) {
            return ext;
        }

        // ‘=’分隔
        for (String kv : kvs) {
            String[] pair = StringUtils.split(kv, kvSep, 2);
            if (pair == null || pair.length != 2) {
                // 自动去除格式不正确的字符串对
                continue;
            }

            // 解析成功
            ext.put(pair[0], pair[1]);
        }

        // 成功返回
        return ext;
    }

    /**
     * 把扩展属性格式化成字符串
     * 
     * @param ext K/V以‘空格’分隔，K与V间以‘=’分隔；自动去除K/V之间的‘空格’和‘=’号。
     */
    public static final String format(MapExt ext) {
        return format(ext, ET_SEP, KV_SEP);
    }

    /**
     * 把扩展属性格式化成字符串
     * 
     * @param ext K/V以‘空格’分隔，K与V间以‘=’分隔；自动去除K/V之间的‘空格’和‘=’号。
     */
    public static final String format(MapExt ext, String etSep, String kvSep) {
        if (ext == null || ext.isEmpty()) {
            return StringUtils.EMPTY;
        }

        // 检查分隔符
        if (etSep == null) {
            etSep = ET_SEP;
        }
        if (kvSep == null) {
            kvSep = KV_SEP;
        }

        StringBuilder txt = new StringBuilder(128);

        for (Map.Entry<String, String> entry : ext.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // 去除‘空格’符
            key = StringUtils.remove(key, etSep);
            value = StringUtils.remove(value, etSep);

            // 去除‘=’符
            key = StringUtils.remove(key, kvSep);
            value = StringUtils.remove(value, kvSep);

            // 组装
            txt.append(key).append(kvSep).append(value).append(etSep);
        }

        // 去除前后空格
        return StringUtils.trimToEmpty(txt.toString());
    }

    /**
     * 从‘java.util.Properties’中转化为对象
     */
    public static final MapExt from(Properties props) {
        MapExt ext = new MapExt();

        if (props == null || props.isEmpty()) {
            return ext;
        }

        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            ext.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        return ext;
    }

    /**
     * 转化为‘java.util.Properties’对象
     */
    public static final Properties toProperties(MapExt ext) {
        Properties props = new Properties();

        if (ext == null || ext.isEmpty()) {
            return props;
        }

        for (Map.Entry<String, String> entry : ext.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key == null || value == null) {
                continue;
            }

            props.put(key, value);
        }

        return props;
    }

    // ~~~~~~~~~~ 私有方法 ~~~~~~~~~~~ //

    /** K/V之间分隔符，默认为‘空格’符 */
    private String entrySeparator    = ET_SEP;

    /** K/V值间分隔符，默认为‘=’符 */
    private String keyValueSeparator = KV_SEP;

    /**
     * 复制扩展属性
     * 
     * @see java.util.AbstractMap#clone()
     */
    public MapExt clone() {
        MapExt ext = new MapExt();

        for (Map.Entry<String, String> entry : this.entrySet()) {
            ext.put(entry.getKey(), entry.getValue());
        }

        return ext;
    }

    /**
     * 把扩展属性格式化成字符串
     * 
     * @param ext K/V以‘空格’分隔，K与V间以‘=’分隔；去除K/V之间的‘空格’和‘=’号；
     */
    public final String format() {
        return format(this, this.entrySeparator, this.keyValueSeparator);
    }

    /**
     * 取得DATE值
     */
    public Date getDate(String key) {
        return DateUtils.tryParseDate(this.get(key));
    }

    public Date getDate(String key, String format) {
        return DateUtils.toDate(this.get(key), format);
    }

    /**
     * 取得LIST值，默认逗号‘,’分隔
     */
    public List<String> getList(String key) {
        return this.getList(key, ",");
    }

    public List<String> getList(String key, String sep) {
        List<String> list = new ArrayList<String>();

        String txt = this.get(key);
        if (StringUtils.isBlank(txt)) {
            return list;
        }

        String[] values = StringUtils.split(txt, sep);
        if (values == null || values.length == 0) {
            return list;
        }

        for (String value : values) {
            list.add(value);
        }

        return list;
    }

    /**
     * 取得BOOL值
     */
    public boolean getBoolean(String key) {
        return BooleanUtils.toBoolean(this.get(key));
    }

    /**
     * 取得BYTE值，默认值为‘0’。
     */
    public byte getByte(String key) {
        return this.getByte(key, (byte) 0);
    }

    public byte getByte(String key, byte defaultValue) {
        return NumberUtils.toByte(this.get(key), defaultValue);
    }

    /**
     * 取得SHORT值，默认值为‘0’。
     */
    public short getShort(String key) {
        return this.getShort(key, (short) 0);
    }

    public short getShort(String key, short defaultValue) {
        return NumberUtils.toShort(this.get(key), defaultValue);
    }

    /**
     * 取得INT值，默认值为‘0’。
     */
    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return NumberUtils.toInt(this.get(key), defaultValue);
    }

    /**
     * 取得LONG值，默认值为‘0L’。
     */
    public long getLong(String key) {
        return this.getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
        return NumberUtils.toLong(this.get(key), defaultValue);
    }

    /**
     * 取得FLOAT值，默认值为‘0.0F’。
     */
    public float getFloat(String key) {
        return this.getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defaultValue) {
        return NumberUtils.toFloat(this.get(key), defaultValue);
    }

    /**
     * 取得DOUBLE值，默认值为‘0.0D’。
     */
    public double getDouble(String key) {
        return this.getDouble(key, 0.0D);
    }

    public double getDouble(String key, double defaultValue) {
        return NumberUtils.toDouble(this.get(key), defaultValue);
    }

    /**
     * 取得Money值，默认值为‘0.00元’。
     */
    public Money getMoney(String key) {
        String value = this.get(key);
        if (StringUtils.isBlank(value)) {
            return new Money(0, 0);
        } else {
            return new Money(value);
        }
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public String getEntrySeparator() {
        return entrySeparator;
    }

    public void setEntrySeparator(String entrySeparator) {
        this.entrySeparator = entrySeparator;
    }

    public String getKeyValueSeparator() {
        return keyValueSeparator;
    }

    public void setKeyValueSeparator(String keyValueSeparator) {
        this.keyValueSeparator = keyValueSeparator;
    }

}

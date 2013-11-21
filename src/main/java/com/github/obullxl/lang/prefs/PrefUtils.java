/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.prefs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.github.obullxl.lang.Money;
import com.github.obullxl.lang.utils.DateUtils;

/**
 * 偏好工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: PrefUtils.java, V1.0.1 2013-1-18 上午10:59:34 $
 */
public class PrefUtils {
    private static final Lock                 _lock = new ReentrantLock();
    private static final Map<String, PrefMap> _map  = new ConcurrentHashMap<String, PrefMap>();
    private static final ThreadLocal<String>  _catg = new ThreadLocal<String>();

    /**
     * 设置偏好分类于线程变更中。
     * <p/>
     * 该偏好分类存储于线程变量中，使用完成之后，请删除该变更：{@code removeCategory()}
     */
    public static void setCategory(String catg) {
        _catg.set(catg);
    }

    /**
     * 从线程变更中获取偏好分类
     */
    public static String getCategory() {
        return _catg.get();
    }

    /**
     * 从线程变量中清除偏好分类
     */
    public static void removeCategory() {
        _catg.remove();
    }

    /**
     * 获取‘String’偏好值
     */
    public static String get(String key) {
        _lock.lock();
        try {
            return ensure().get(key);
        } finally {
            _lock.unlock();
        }
    }

    /**
     * 获取‘Date’偏好值
     */
    public static Date getDate(String key) {
        return DateUtils.tryParseDate(get(key));
    }

    public static Date getDate(String key, String format) {
        return DateUtils.toDate(get(key), format);
    }
    
    /**
     * 获取‘List<String>’偏好值（默认逗号‘,’分隔）
     */
    public static List<String> getList(String key) {
        return getList(key, ",");
    }

    public static List<String> getList(String key, String sep) {
        List<String> list = new ArrayList<String>();

        String txt = get(key);
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
     * 获取‘Boolean’偏好值
     */
    public static boolean getBoolean(String key) {
        return BooleanUtils.toBoolean(get(key));
    }

    /**
     * 获取‘Byte’偏好值（默认值为‘0’）
     */
    public static byte getByte(String key) {
        return getByte(key, (byte) 0);
    }

    public static byte getByte(String key, byte defaultValue) {
        return NumberUtils.toByte(get(key), defaultValue);
    }

    /**
     * 获取‘Short’偏好值（默认值为‘0’）
     */
    public static short getShort(String key) {
        return getShort(key, (short) 0);
    }

    public static short getShort(String key, short defaultValue) {
        return NumberUtils.toShort(get(key), defaultValue);
    }

    /**
     * 获取‘Integer’偏好值（默认值为‘0’）
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return NumberUtils.toInt(get(key), defaultValue);
    }

    /**
     * 获取‘Long’偏好值（默认值为‘0L’）
     */
    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return NumberUtils.toLong(get(key), defaultValue);
    }

    /**
     * 获取‘Float’偏好值（默认值为‘0.0F’）
     */
    public static float getFloat(String key) {
        return getFloat(key, 0.0F);
    }

    public static float getFloat(String key, float defaultValue) {
        return NumberUtils.toFloat(get(key), defaultValue);
    }

    /**
     * 获取‘Double’偏好值（默认值为‘0.0D’）
     */
    public static double getDouble(String key) {
        return getDouble(key, 0.0D);
    }

    public static double getDouble(String key, double defaultValue) {
        return NumberUtils.toDouble(get(key), defaultValue);
    }

    /**
     * 取得Money值（默认为‘0.00元’）
     */
    public static Money getMoney(String key) {
        String value = get(key);
        if (StringUtils.isBlank(value)) {
            return new Money(0, 0);
        } else {
            return new Money(value);
        }
    }

    /**
     * 设置‘String’偏好值
     */
    public static void put(String key, String value) {
        _lock.lock();
        try {
            ensure().put(key, value);
        } finally {
            _lock.unlock();
        }
    }

    /**
     * 设置‘Boolean’偏好值
     */
    public static void put(String key, boolean value) {
        put(key, BooleanUtils.toStringTrueFalse(value));
    }

    /**
     * 设置‘Byte’偏好值
     */
    public static void put(String key, byte value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Short’偏好值
     */
    public static void put(String key, short value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Integer’偏好值
     */
    public static void put(String key, int value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Long’偏好值
     */
    public static void put(String key, long value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Float’偏好值
     */
    public static void put(String key, float value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Double’偏好值
     */
    public static void put(String key, double value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Date’偏好值（默认‘yyyy-MM-dd HH:mm:ss’格式）
     */
    public static void put(String key, Date value) {
        if (value != null) {
            put(key, DateUtils.toStringDL(value));
        }
    }

    /**
     * 设置‘List<String>’偏好值（默认以‘,’分隔）
     */
    public static void put(String key, List<String> values) {
        StringBuilder txt = new StringBuilder(256);
        if (values != null && !values.isEmpty()) {
            Iterator<String> iterator = values.iterator();
            while(iterator.hasNext()) {
                txt.append(iterator.next());
                if(iterator.hasNext()) {
                    txt.append(",");
                }
            }
        }

        put(key, txt.toString());
    }

    /**
     * 设置‘Money’偏好值
     */
    public static void put(String key, Money value) {
        put(key, String.valueOf(value));
    }

    /**
     * 设置‘Object’偏好值
     */
    public static void put(String key, Object value) {
        put(key, ObjectUtils.toString(value));
    }

    /**
     * 删除一个偏好值
     */
    public static String remove(String key) {
        _lock.lock();
        try {
            return ensure().remove(key);
        } finally {
            _lock.unlock();
        }
    }

    /**
     * 删除所有的偏好值
     */
    public static void removeAll() {
        _lock.lock();
        try {
            ensure().removeAll();
        } finally {
            _lock.unlock();
        }
    }

    /**
     * 确保偏好存在，若不存在，则创建
     */
    private static PrefMap ensure() {
        _lock.lock();
        try {
            String category = getCategory();
            if (StringUtils.isBlank(category)) {
                throw new RuntimeException("线程变量-偏好分类不存在，请先通过#setCategory(String)方法设置，通过#removeCategory()释放线程变量！");
            }

            PrefMap pref = _map.get(category);
            if (pref == null) {
                pref = new PrefMap(category);
                _map.put(category, pref);
            }

            return pref;
        } finally {
            _lock.unlock();
        }
    }

}

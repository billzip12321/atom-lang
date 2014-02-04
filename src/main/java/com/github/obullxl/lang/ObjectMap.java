/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象映射
 * 
 * @author obullxl@gmail.com
 * @version $Id: ObjectMap.java, V1.0.1 2014年2月3日 上午10:19:09 $
 */
public class ObjectMap extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = -6564528970300915826L;

    /**
     * 新建对象映射
     */
    public static ObjectMap newObjectMap() {
        return new ObjectMap();
    }

    /** 
     * @see java.util.concurrent.ConcurrentHashMap#put(java.lang.Object, java.lang.Object)
     */
    public Object put(String key, Object value) {
        if (key == null || value == null) {
            return null;
        }

        return super.put(key, value);
    }

    /**
     * 获取单个对象
     */
    @SuppressWarnings("unchecked")
    public <T> T findObject(String key) {
        if (key == null) {
            return null;
        }

        Object value = super.get(key);

        if (value == null) {
            return null;
        }

        return (T) value;
    }

    /**
     * 获取Map映射对象
     */
    @SuppressWarnings("unchecked")
    public <E, V> Map<E, V> findMap(String key) {
        if (key == null) {
            return null;
        }

        Object value = super.get(key);

        if (value == null) {
            return null;
        }

        if (!Map.class.isAssignableFrom(value.getClass())) {
            return null;
        }

        return (Map<E, V>) value;
    }

    /**
     * 获取Set集合对象
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> findSet(String key) {
        if (key == null) {
            return null;
        }

        Object value = super.get(key);

        if (value == null) {
            return null;
        }

        if (!Set.class.isAssignableFrom(value.getClass())) {
            return null;
        }

        return (Set<T>) value;
    }

    /**
     * 获取List列表对象
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> findList(String key) {
        if (key == null) {
            return null;
        }

        Object value = super.get(key);

        if (value == null) {
            return null;
        }

        if (!List.class.isAssignableFrom(value.getClass())) {
            return null;
        }

        return (List<T>) value;
    }

}

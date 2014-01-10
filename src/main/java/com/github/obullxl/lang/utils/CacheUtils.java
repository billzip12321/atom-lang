/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * 缓冲服务
 * <p/>
 * 目前基于EhCache框架！
 * 
 * @author obullxl@gmail.com
 * @version $Id: CacheUtils.java, V1.0.1 2014年1月6日 下午4:07:42 $
 */
public final class CacheUtils {
    /** Logger */
    // private static final Logger logger = LogUtils.get();

    /** 单例 */
    private static CacheManager cmngt;

    /**
     * 获取CacheManager单例
     */
    public static CacheManager getCacheManager() {
        if (cmngt != null) {
            return cmngt;
        }

        synchronized (CacheUtils.class) {
            if (cmngt != null) {
                return cmngt;
            } else {
                cmngt = new EhCacheCacheManager(net.sf.ehcache.CacheManager.getInstance());
                return cmngt;
            }
        }
    }

    /**
     * 清理缓存
     */
    public static void clear(String cache) {
        getCacheManager().getCache(cache).clear();
    }

    /**
     * 删除数据
     */
    public static void evict(String cache, Object key) {
        getCacheManager().getCache(cache).evict(key);
    }

    /**
     * 获取数据
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String cache, Object key) {
        ValueWrapper vw = getCacheManager().getCache(cache).get(key);
        if (vw != null) {
            return (T) vw.get();
        }

        return null;
    }

    /**
     * 缓存数据
     */
    public static void put(String cache, Object key, Object value) {
        getCacheManager().getCache(cache).put(key, value);
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统参数工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgUtils.java, V1.0.1 2014年1月26日 下午7:31:55 $
 */
public class CfgUtils {
    /** 缓存对象 */
    private static final Map<String, Map<String, CfgDTO>> cache = new ConcurrentHashMap<String, Map<String, CfgDTO>>();

    /**
     * 刷新缓存
     */
    public static final void updateCache(CfgDTO cfg) {
        Map<String, CfgDTO> catgs = cache.get(cfg.getCatg());

        if (catgs == null) {
            catgs = new ConcurrentHashMap<String, CfgDTO>();
            cache.put(cfg.getCatg(), catgs);
        }

        catgs.put(cfg.getName(), cfg);
    }

    /**
     * 删除缓存
     */
    public static final void removeCache() {
        cache.clear();
    }

    /**
     * 删除缓存
     */
    public static final void removeCache(String catg) {
        cache.remove(catg);
    }

    /**
     * 删除缓存
     */
    public static final void removeCache(String catg, String name) {
        Map<String, CfgDTO> catgs = cache.get(catg);

        if (catgs != null) {
            catgs.remove(name);
        }
    }

    /**
     * 查询系统参数
     */
    public static final List<CfgDTO> find() {
        List<CfgDTO> cfgs = new ArrayList<CfgDTO>();

        for (Map.Entry<String, Map<String, CfgDTO>> cfgEntry : cache.entrySet()) {
            if (cfgEntry != null) {
                for (Map.Entry<String, CfgDTO> entry : cfgEntry.getValue().entrySet()) {
                    cfgs.add(entry.getValue());
                }
            }
        }

        Collections.sort(cfgs);
        return cfgs;
    }

    /**
     * 查询系统参数
     */
    public static final List<CfgDTO> find(String catg) {
        List<CfgDTO> cfgs = new ArrayList<CfgDTO>();

        Map<String, CfgDTO> entry = cache.get(catg);
        if (entry != null) {
            cfgs.addAll(entry.values());
        }

        Collections.sort(cfgs);
        return cfgs;
    }

    /**
     * 查询系统参数
     */
    public static final CfgDTO find(String catg, String name) {
        Map<String, CfgDTO> entry = cache.get(catg);
        if (entry != null) {
            return entry.get(name);
        }

        return null;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

/**
 * 系统参数工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgUtils.java, V1.0.1 2014年1月26日 下午7:31:55 $
 */
public class CfgUtils {
    /** 缓存对象 */
    private static final Map<String, Map<String, CfgModel>> cache = new ConcurrentHashMap<String, Map<String, CfgModel>>();

    /**
     * 刷新缓存
     */
    public static final void updateCache(CfgModel cfg) {
        Map<String, CfgModel> catgs = cache.get(cfg.getCatg());

        if (catgs == null) {
            catgs = new ConcurrentHashMap<String, CfgModel>();
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
        Map<String, CfgModel> catgs = cache.get(catg);

        if (catgs != null) {
            catgs.remove(name);
        }
    }

    /**
     * 查询系统参数
     */
    public static final List<CfgModel> find() {
        List<CfgModel> cfgs = new ArrayList<CfgModel>();

        for (Map.Entry<String, Map<String, CfgModel>> cfgEntry : cache.entrySet()) {
            if (cfgEntry != null) {
                for (Map.Entry<String, CfgModel> entry : cfgEntry.getValue().entrySet()) {
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
    public static final List<CfgModel> find(String catg) {
        List<CfgModel> cfgs = new ArrayList<CfgModel>();

        Map<String, CfgModel> entry = cache.get(catg);
        if (entry != null) {
            cfgs.addAll(entry.values());
        }

        Collections.sort(cfgs);
        return cfgs;
    }

    /**
     * 查询系统参数
     */
    public static final CfgModel find(String catg, String name) {
        Map<String, CfgModel> entry = cache.get(catg);
        if (entry != null) {
            return entry.get(name);
        }

        return null;
    }

    /**
     * 获取系统参数值
     */
    public static final String findParamValue(String name) {
        CfgModel cfg = find(CfgModelEnum.PARAM.code(), name);
        if (cfg == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.trimToEmpty(cfg.getValue());
    }

    /**
     * 获取系统参数扩展值
     */
    public static final String findParamValueExt(String name) {
        CfgModel cfg = find(CfgModelEnum.PARAM.code(), name);
        if (cfg == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.trimToEmpty(cfg.getValueExt());
    }

}

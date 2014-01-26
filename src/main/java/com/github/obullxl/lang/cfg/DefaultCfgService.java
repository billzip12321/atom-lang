/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 系统参数默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultCfgService.java, V1.0.1 2014年1月26日 上午9:51:25 $
 */
public class DefaultCfgService implements CfgService {
    /** Logger */
    private static final Logger                           logger = LogUtils.get();

    /** 缓存对象 */
    private static final Map<String, Map<String, CfgDTO>> cache  = new ConcurrentHashMap<String, Map<String, CfgDTO>>();

    /** 系统参数DAO */
    private CfgDAO                                        cfgDAO;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.cfgDAO, "[系统参数]-CfgDAO注入失败!");
        
        // 刷新缓存
        this.onRefresh();
    }

    /**
     * 刷新缓存
     */
    public void onRefresh() {
        logger.warn("[系统参数]-开始刷新系统参数缓存......");

        long start = System.currentTimeMillis();
        try {
            List<CfgDTO> cfgs = this.cfgDAO.find();
            if (cfgs != null) {
                for (CfgDTO cfg : cfgs) {
                    this.updateCache(cfg);
                }
            }
        } finally {
            logger.warn("[系统参数]-系统参数缓存刷新完成, 耗时[{}]ms, 参数列表: \n{}", (System.currentTimeMillis() - start), cache);
        }
    }

    /**
     * 刷新缓存
     */
    private void updateCache(CfgDTO cfg) {
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
    private void removeCache() {
        cache.clear();
    }

    /**
     * 删除缓存
     */
    private void removeCache(String catg) {
        cache.remove(catg);
    }

    /**
     * 删除缓存
     */
    private void removeCache(String catg, String name) {
        Map<String, CfgDTO> catgs = cache.get(catg);

        if (catgs != null) {
            catgs.remove(name);
        }
    }

    /**
     * 新增系统参数
     */
    public void create(CfgDTO cfg) {
        this.cfgDAO.insert(cfg);
        this.updateCache(cfg);
    }

    /**
     * 更新系统参数
     */
    public void update(CfgDTO cfg) {
        this.cfgDAO.update(cfg);
        this.updateCache(cfg);
    }

    /**
     * 删除系统参数
     */
    public void remove() {
        this.cfgDAO.delete();
        this.removeCache();
    }

    /**
     * 删除系统参数
     */
    public void remove(String catg) {
        this.cfgDAO.delete(catg);
        this.removeCache(catg);
    }

    /**
     * 删除系统参数
     */
    public void remove(String catg, String name) {
        this.cfgDAO.delete(catg, name);
        this.removeCache(catg, name);
    }

    /**
     * 查询系统参数
     */
    public List<CfgDTO> find() {
        List<CfgDTO> cfgs = new ArrayList<CfgDTO>();

        for (Map.Entry<String, Map<String, CfgDTO>> cfgEntry : cache.entrySet()) {
            if (cfgEntry != null) {
                for (Map.Entry<String, CfgDTO> entry : cfgEntry.getValue().entrySet()) {
                    cfgs.add(entry.getValue());
                }
            }
        }

        return cfgs;
    }

    /**
     * 查询系统参数
     */
    public List<CfgDTO> find(String catg) {
        List<CfgDTO> cfgs = new ArrayList<CfgDTO>();

        Map<String, CfgDTO> entry = cache.get(catg);
        if (entry != null) {
            cfgs.addAll(entry.values());
        }

        return cfgs;
    }

    /**
     * 查询系统参数
     */
    public CfgDTO find(String catg, String name) {
        Map<String, CfgDTO> entry = cache.get(catg);
        if (entry != null) {
            return entry.get(name);
        }

        return null;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCfgDAO(CfgDAO cfgDAO) {
        this.cfgDAO = cfgDAO;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 模块分类默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultCfgService.java, V1.0.1 2014年1月26日 上午9:51:25 $
 */
public class DefaultCatgService implements CatgService {
    /** Logger */
    private static final Logger               logger = LogUtils.get();

    /** 缓存对象 */
    private static final List<CatgDTO>        roots  = new ArrayList<CatgDTO>();
    private static final Map<String, CatgDTO> cache  = new ConcurrentHashMap<String, CatgDTO>();

    /** 模块分类DAO */
    private CatgDAO                           catgDAO;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.catgDAO, "[模块分类]-CatgDAO注入失败!");

        // 刷新缓存
        this.onRefresh();
    }

    /**
     * 刷新缓存
     */
    public void onRefresh() {
        logger.warn("[模块分类]-开始刷新模块分类缓存......");

        long start = System.currentTimeMillis();
        try {
            // 查询所有
            List<CatgDTO> dtos = this.catgDAO.find();

            // 构建Map树
            roots.clear();
            cache.clear();

            for (CatgDTO dto : dtos) {
                cache.put(dto.getCode(), dto);
            }

            for (CatgDTO dto : dtos) {
                String catg = dto.getCatg();
                CatgDTO parent = cache.get(catg);

                if (parent != null) {
                    dto.setParent(parent);
                    parent.getChildren().add(dto);
                }
            }

            // 树根节点
            for (CatgDTO catg : dtos) {
                if (catg.getParent() == null) {
                    roots.add(catg);
                }
            }
        } finally {
            logger.warn("[模块分类]-模块分类缓存刷新完成, 耗时[{}]ms, 参数列表: \n{}", (System.currentTimeMillis() - start), cache);
        }
    }

    /**
     * 新增模块分类
     */
    public void create(CatgDTO catg) {
        this.catgDAO.insert(catg);
        this.onRefresh();
    }

    /**
     * 更新模块分类
     */
    public void update(CatgDTO catg) {
        this.catgDAO.update(catg);
        this.onRefresh();
    }

    /**
     * 删除模块分类
     */
    public void remove() {
        this.catgDAO.delete();
        roots.clear();
        cache.clear();
    }

    /**
     * 删除模块分类
     */
    public void remove(String code) {
        this.catgDAO.delete(code);
        this.onRefresh();
    }

    /**
     * 查询模块分类
     */
    public List<CatgDTO> find() {
        return new ArrayList<CatgDTO>(cache.values());
    }

    /**
     * 查询模块分类
     */
    public CatgDTO find(String code) {
        return cache.get(code);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCatgDAO(CatgDAO catgDAO) {
        this.catgDAO = catgDAO;
    }

}

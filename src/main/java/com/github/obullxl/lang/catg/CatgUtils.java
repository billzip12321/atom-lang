/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

/**
 * 模块分类工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgUtils.java, V1.0.1 2014年1月26日 下午7:37:35 $
 */
public class CatgUtils {
    /** 缓存对象 */
    private static final List<CatgDTO>        roots = new ArrayList<CatgDTO>();
    private static final Map<String, CatgDTO> cache = new ConcurrentHashMap<String, CatgDTO>();

    /**
     * 刷新缓存
     */
    public static final void onRefresh(List<CatgDTO> catgs) {
        // 构建Map树
        roots.clear();
        cache.clear();

        for (CatgDTO catg : catgs) {
            cache.put(catg.getCode(), catg);
        }

        for (CatgDTO dto : catgs) {
            String catg = dto.getCatg();
            CatgDTO parent = cache.get(catg);

            if (parent != null) {
                dto.setParent(parent);
                parent.getChildren().add(dto);
            }
        }

        // 树根节点
        for (CatgDTO catg : catgs) {
            if (catg.getParent() == null) {
                roots.add(catg);
            }
        }
    }

    /**
     * 删除模块分类
     */
    public static final void remove() {
        roots.clear();
        cache.clear();
    }

    /**
     * 查询模块分类
     */
    public static final List<CatgDTO> find() {
        List<CatgDTO> catgs = new ArrayList<CatgDTO>(cache.values());
        Collections.sort(catgs);
        return catgs;
    }

    /**
     * 查询模块分类
     */
    public static final CatgDTO find(String code) {
        return cache.get(code);
    }

    /**
     * 查询模块分类
     */
    public static final CatgDTO find(String catg, String code) {
        CatgDTO item = cache.get(code);

        if (item != null && StringUtils.equals(catg, item.getCatg())) {
            return item;
        }

        return null;
    }

    /**
     * 检测是否有下属分类
     */
    public static final boolean hasBranch(String code) {
        CatgDTO catg = find(code);
        return (catg != null && !catg.getChildren().isEmpty());
    }

    /**
     * 复制分类基本信息
     */
    public static final CatgDTO copy(CatgDTO src) {
        if (src == null) {
            return null;
        }

        CatgDTO dst = new CatgDTO();

        dst.setCatg(src.getCatg());
        dst.setCode(src.getCode());
        dst.setSort(src.getSort());
        dst.setTitle(src.getTitle());
        dst.setExtMap(src.getExtMap());
        dst.setSummary(src.getSummary());
        dst.setGmtCreate(src.getGmtCreate());
        dst.setGmtModify(src.getGmtModify());

        return dst;
    }

    /**
     * 合并分类基本信息
     */
    public static final void merge(CatgDTO dst, CatgDTO src) {
        if (dst == null || src == null) {
            return;
        }

        dst.setCatg(src.getCatg());
        dst.setCode(src.getCode());
        dst.setSort(src.getSort());
        dst.setTitle(src.getTitle());
        dst.setExtMap(src.getExtMap());
        dst.setSummary(src.getSummary());
        dst.setGmtCreate(src.getGmtCreate());
        dst.setGmtModify(src.getGmtModify());
    }

    /**
     * 获取下级所有模块分类
     */
    public static final List<CatgDTO> findBranches(String code) {
        List<CatgDTO> catgs = new ArrayList<CatgDTO>();

        CatgDTO catg = find(code);
        if (catg != null) {
            catgs.add(copy(catg));

            for (CatgDTO child : catg.getChildren()) {
                findBranches(catgs, child);
            }
        }

        Collections.sort(catgs);
        return catgs;
    }

    /**
     * 获取下级所有模块分类
     */
    private static final void findBranches(List<CatgDTO> catgs, CatgDTO catg) {
        if (catg == null) {
            return;
        }

        catgs.add(copy(catg));
        for (CatgDTO child : catg.getChildren()) {
            findBranches(catgs, child);
        }
    }

    /**
     * 获取下级所有模块分类代码
     */
    public static final List<String> findBranchCodes(String code) {
        Set<String> codes = new HashSet<String>();

        List<CatgDTO> catgs = findBranches(code);
        for (CatgDTO catg : catgs) {
            codes.add(catg.getCode());
        }

        return new ArrayList<String>(codes);
    }

}

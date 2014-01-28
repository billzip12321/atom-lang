/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.area;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

/**
 * 区域工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AreaUtils.java, V1.0.1 2014年1月26日 下午2:43:09 $
 */
public class AreaUtils {
    /** 区域长度 */
    public static final int                   ITEM_LENGTH = 2;

    /** 缓存对象 */
    private static final List<AreaDTO>        roots       = new ArrayList<AreaDTO>();
    private static final Map<String, AreaDTO> cache       = new ConcurrentHashMap<String, AreaDTO>();

    /**
     * 缓存刷新
     */
    public static final void onRefresh(List<AreaDTO> areas) {
        // 构建Map树
        roots.clear();
        cache.clear();

        for (AreaDTO area : areas) {
            cache.put(area.getNo(), area);
        }

        for (AreaDTO area : areas) {
            String pno = AreaUtils.findParentNo(area.getNo());
            if (StringUtils.isBlank(pno)) {
                continue;
            }

            AreaDTO parent = cache.get(pno);
            if (parent != null) {
                area.setParent(parent);
                parent.getChildren().add(area);
            }
        }

        // 树根节点
        for (AreaDTO area : areas) {
            if (area.getParent() == null) {
                roots.add(area);
            }
        }
    }

    /**
     * 编号是否合法
     */
    public static final boolean isValidNo(String no) {
        int length = StringUtils.length(no);
        return (length % ITEM_LENGTH == 0);
    }

    /**
     * 获取上级编号
     */
    public static final String findParentNo(String no) {
        int length = StringUtils.length(no);
        if (length <= ITEM_LENGTH) {
            return StringUtils.EMPTY;
        }

        return StringUtils.substring(no, 0, (length - ITEM_LENGTH));
    }

    /**
     * 复制区域对象
     */
    public static final AreaDTO copy(AreaDTO srcObj) {
        AreaDTO dstObj = new AreaDTO();

        dstObj.setNo(srcObj.getNo());
        dstObj.setSort(srcObj.getSort());
        dstObj.setName(srcObj.getName());

        return dstObj;
    }

    /**
     * 查询一级代码
     */
    public static final List<AreaDTO> root() {
        List<AreaDTO> areas = new ArrayList<AreaDTO>();

        for (AreaDTO root : roots) {
            areas.add(copy(root));
        }

        Collections.sort(areas);
        return areas;
    }

    /**
     * 查询区域代码
     */
    public static final List<AreaDTO> findAll() {
        return new ArrayList<AreaDTO>(cache.values());
    }

    /**
     * 查询区域代码
     */
    public static final AreaDTO find(String no) {
        AreaDTO area = cache.get(no);

        if (area != null) {
            return copy(area);
        }

        return null;
    }

    /**
     * 查询上级区域信息
     */
    public static final AreaDTO findParent(String no) {
        AreaDTO area = cache.get(no);

        if (area != null) {
            AreaDTO parent = area.getParent();
            if (parent != null) {
                return copy(parent);
            }
        }

        return null;
    }

    /**
     * 查询下级区域列表
     */
    public static final List<AreaDTO> findChildren(String no) {
        List<AreaDTO> areas = new ArrayList<AreaDTO>();

        AreaDTO area = cache.get(no);

        if (area != null) {
            List<AreaDTO> children = area.getChildren();
            for (AreaDTO child : children) {
                areas.add(copy(child));
            }

            Collections.sort(areas);
        }

        return areas;
    }

}

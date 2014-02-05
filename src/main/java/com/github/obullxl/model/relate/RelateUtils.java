/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 关联模型工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateUtils.java, V1.0.1 2014年2月5日 下午3:49:38 $
 */
public class RelateUtils {
    /** 缓存对象 */
    private static Map<String, List<RelateModel>> cache = new ConcurrentHashMap<String, List<RelateModel>>();

    /**
     * 刷新缓存
     */
    public static void updateCache(RelateModel relate) {
        List<RelateModel> catgs = cache.get(relate.getCatg());

        if (catgs == null) {
            catgs = new ArrayList<RelateModel>();
            cache.put(relate.getCatg(), catgs);
        }

        catgs.add(relate);
        Collections.sort(catgs);
    }

    /**
     * 删除缓存
     */
    public static void removeCache() {
        cache.clear();
    }

    /**
     * 删除缓存
     */
    public static void removeCache(String catg) {
        cache.remove(catg);
    }

    /**
     * 查询系统参数
     */
    public static List<RelateModel> find() {
        List<RelateModel> relates = new ArrayList<RelateModel>();

        for (List<RelateModel> catgs : cache.values()) {
            relates.addAll(catgs);
        }

        Collections.sort(relates);
        return relates;
    }

    /**
     * 根据分类查询关联模型
     */
    public static List<RelateModel> findByCatg(String catg) {
        return cache.get(catg);
    }

    /**
     * 根据分类+源编号查询关联模型
     */
    public static List<RelateModel> findBySrcCatg(String catg, String srcNo) {
        List<RelateModel> results = new ArrayList<RelateModel>();

        List<RelateModel> relates = findByCatg(catg);
        if (CollectionUtils.isEmpty(relates)) {
            return results;
        }

        for (RelateModel relate : relates) {
            if (StringUtils.equals(catg, relate.getCatg()) && StringUtils.equals(srcNo, relate.getSrcNo())) {
                results.add(relate);
            }
        }

        return results;
    }

    /**
     * 根据分类+目标编号查询关联模型
     */
    public static List<RelateModel> findByDstCatg(String catg, String dstNo) {
        List<RelateModel> results = new ArrayList<RelateModel>();

        List<RelateModel> relates = findByCatg(catg);
        if (CollectionUtils.isEmpty(relates)) {
            return results;
        }

        for (RelateModel relate : relates) {
            if (StringUtils.equals(catg, relate.getCatg()) && StringUtils.equals(dstNo, relate.getDstNo())) {
                results.add(relate);
            }
        }

        return results;
    }

    /**
     * 根据分类+源编号+目标编号查询单条关联模型
     */
    public static RelateModel findByUnique(String catg, String srcNo, String dstNo) {
        List<RelateModel> relates = findBySrcCatg(catg, srcNo);
        if (CollectionUtils.isEmpty(relates)) {
            return null;
        }

        for (RelateModel relate : relates) {
            if (StringUtils.equals(dstNo, relate.getDstNo())) {
                return relate;
            }
        }

        return null;
    }

}

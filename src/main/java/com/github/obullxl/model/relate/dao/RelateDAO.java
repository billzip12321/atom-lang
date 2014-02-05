/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.dao;

import java.util.List;

import com.github.obullxl.model.relate.RelateModel;

/**
 * 关联模型DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateDAO.java, V1.0.1 2014年1月29日 下午1:36:17 $
 */
public interface RelateDAO {
    public static final String NAME = "RelateDAO";

    /**
     * 插入关联模型
     */
    public void insert(final RelateModel relate);

    /**
     * 更新关联模型
     */
    public int update(final RelateModel relate);

    /**
     * 根据分类删除记录
     */
    public int deleteByCatg(final String catg);

    /**
     * 根据源编号删除记录
     */
    public int deleteBySrcNo(final String srcNo);

    /**
     * 根据目标编号删除记录
     */
    public int deleteByDstNo(final String dstNo);

    /**
     * 根据分类+源编号删除记录
     */
    public int deleteBySrcCatg(final String catg, final String srcNo);

    /**
     * 根据分类+目标编号删除记录
     */
    public int deleteByDstCatg(final String catg, final String dstNo);

    /**
     * 根据分类+源编号+目标编号删除记录
     */
    public int deleteByUnique(final String catg, final String srcNo, final String dstNo);

    /**
     * 根据分类查询关联模型
     */
    public List<RelateModel> findByCatg(final String catg);

    /**
     * 根据分类+源编号查询关联模型
     */
    public List<RelateModel> findBySrcCatg(final String catg, final String srcNo);

    /**
     * 根据分类+目标编号查询关联模型
     */
    public List<RelateModel> findByDstCatg(final String catg, final String dstNo);

    /**
     * 根据分类+源编号+目标编号查询单条关联模型
     */
    public RelateModel findByUnique(final String catg, final String srcNo, final String dstNo);

}

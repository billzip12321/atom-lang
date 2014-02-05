/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg.dao;

import java.util.List;

import com.github.obullxl.model.catg.CatgModel;

/**
 * 模块分类DAO实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgDAO.java, V1.0.1 2014年1月26日 上午11:59:19 $
 */
public interface CatgDAO {
    public static final String NAME = "CatgDAO";

    /**
     * 插入模块分类
     */
    public void insert(final CatgModel catg);

    /**
     * 更新模块分类
     */
    public int update(final CatgModel catg);

    /**
     * 根据代码查询模块分类
     */
    public CatgModel selectByCode(final String code);

    /**
     * 根据上级分类查询模块分类
     */
    public List<CatgModel> selectByCatg(final String catg);

    /**
     * 根据代码删除模块分类
     */
    public int deleteByCode(final String code);

    /**
     * 根据分类删除模块分类
     */
    public int deleteByCatg(final String catg);

    /**
     * 根据分类+代码删除模块分类
     */
    public int delete(final String catg, final String code);

}

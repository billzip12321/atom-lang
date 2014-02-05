/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg.service;

import java.util.List;

import com.github.obullxl.model.catg.CatgModel;

/**
 * 模块分类服务
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgService.java, V1.0.1 2014年1月26日 下午12:56:46 $
 */
public interface CatgService {

    /**
     * 刷新缓存
     */
    public void onRefresh();

    /**
     * 根据代码查询分类
     */
    public CatgModel findByCode(String code);

    /**
     * 根据分类查询分类
     */
    public List<CatgModel> findByCatg(String catg);

    /**
     * 新增模块分类
     */
    public void create(CatgModel catg);

    /**
     * 更新模块分类
     */
    public int update(CatgModel catg);

    /**
     * 删除模块分类
     */
    public int remove();

    /**
     * 根据代码删除模块分类
     */
    public int remove(String code);

    /**
     * 根据分类删除模块分类
     */
    public int removeByCatg(String catg);

    /**
     * 根据分类+代码删除模块分类
     */
    public int remove(String catg, String code);

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.List;

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
     * 新增模块分类
     */
    public void create(CatgDTO catg);

    /**
     * 更新模块分类
     */
    public void update(CatgDTO catg);

    /**
     * 删除模块分类
     */
    public void remove();

    /**
     * 删除模块分类
     */
    public void remove(String code);

    /**
     * 查询模块分类
     */
    public List<CatgDTO> find();

    /**
     * 查询模块分类
     */
    public CatgDTO find(String code);

}

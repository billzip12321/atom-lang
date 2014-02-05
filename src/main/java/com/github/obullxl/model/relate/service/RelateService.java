/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.service;

import com.github.obullxl.model.relate.RelateModel;

/**
 * 关联模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateService.java, V1.0.1 2014年1月31日 上午11:50:42 $
 */
public interface RelateService {

    /**
     * 创建关联模型
     */
    public void create(RelateModel relate);

    /**
     * 更新关联模型
     */
    public int update(RelateModel relate);

    /**
     * 根据分类删除记录
     */
    public int removeByCatg(String catg);

    /**
     * 根据源编号删除记录
     */
    public int removeBySrcNo(String srcNo);

    /**
     * 根据目标编号删除记录
     */
    public int removeByDstNo(String dstNo);

    /**
     * 根据分类+源编号删除记录
     */
    public int removeBySrcCatg(String catg, String srcNo);

    /**
     * 根据分类+目标编号删除记录
     */
    public int removeByDstCatg(String catg, String dstNo);

    /**
     * 根据分类+源编号+目标编号删除记录
     */
    public int removeByUnique(String catg, String srcNo, String dstNo);

}

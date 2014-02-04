/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.List;

/**
 * 关系模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateService.java, V1.0.1 2014年1月31日 上午11:50:42 $
 */
public interface RelateService {

    /**
     * 创建关系模型
     */
    public void create(RelateDTO relate);

    /**
     * 更新关系模型
     */
    public int update(RelateDTO relate);

    /**
     * 根据分类删除记录
     */
    public int removeByCatg(String catg);

    /**
     * 根据分类+源编号删除记录
     */
    public int removeBySrcCatg(String catg, String srcNo);

    /**
     * 根据分类+目标编号删除记录
     */
    public int removeByDstCatg(String catg, String dstNo);

    /**
     * 根据源编号删除记录
     */
    public int removeBySrcNo(String srcNo);

    /**
     * 根据目标编号删除记录
     */
    public int removeByDstNo(String dstNo);

    /**
     * 根据分类+源编号+目标编号删除记录
     */
    public int removeByUnique(String catg, String srcNo, String dstNo);

    /**
     * 根据分类查询关系模型
     */
    public List<RelateDTO> findByCatg(String catg);

    /**
     * 根据分类+源编号查询关系模型
     */
    public List<RelateDTO> findBySrcCatg(String catg, String srcNo);

    /**
     * 根据分类+目标编号查询关系模型
     */
    public List<RelateDTO> findByDstCatg(String catg, String dstNo);

    /**
     * 根据分类+源编号+目标编号查询单条关系模型
     */
    public RelateDTO findByUnique(String catg, String srcNo, String dstNo);

}

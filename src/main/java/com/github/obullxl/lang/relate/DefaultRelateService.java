/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.List;

import org.springframework.util.Assert;

/**
 * 关系模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultRelateService.java, V1.0.1 2014年1月31日 上午11:51:14 $
 */
public class DefaultRelateService implements RelateService {

    /** 关系模型DAO */
    private RelateDAO relateDAO;

    /**
     * 初始化
     */
    public void init() {
        Assert.notNull(this.relateDAO, "[关系模型]-关系模型DAO[RelateDAO]注入失败！");
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#create(com.github.obullxl.lang.relate.RelateDTO)
     */
    public void create(RelateDTO relate) {
        this.relateDAO.insert(relate);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#update(com.github.obullxl.lang.relate.RelateDTO)
     */
    public int update(RelateDTO relate) {
        return this.relateDAO.update(relate);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByCatg(java.lang.String)
     */
    public int removeByCatg(String catg) {
        return this.relateDAO.deleteByCatg(catg);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeBySrcCatg(java.lang.String, java.lang.String)
     */
    public int removeBySrcCatg(String catg, String srcNo) {
        return this.relateDAO.deleteBySrcCatg(catg, srcNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByDstCatg(java.lang.String, java.lang.String)
     */
    public int removeByDstCatg(String catg, String dstNo) {
        return this.relateDAO.deleteByDstCatg(catg, dstNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeBySrcNo(java.lang.String)
     */
    public int removeBySrcNo(String srcNo) {
        return this.relateDAO.deleteBySrcNo(srcNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByDstNo(java.lang.String)
     */
    public int removeByDstNo(String dstNo) {
        return this.relateDAO.deleteByDstNo(dstNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByUnique(java.lang.String, java.lang.String, java.lang.String)
     */
    public int removeByUnique(String catg, String srcNo, String dstNo) {
        return this.relateDAO.deleteByUnique(catg, srcNo, dstNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#findByCatg(java.lang.String)
     */
    public List<RelateDTO> findByCatg(String catg) {
        return this.relateDAO.findByCatg(catg);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#findBySrcCatg(java.lang.String, java.lang.String)
     */
    public List<RelateDTO> findBySrcCatg(String catg, String srcNo) {
        return this.relateDAO.findBySrcCatg(catg, srcNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#findByDstCatg(java.lang.String, java.lang.String)
     */
    public List<RelateDTO> findByDstCatg(String catg, String dstNo) {
        return this.relateDAO.findByDstCatg(catg, dstNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#findByUnique(java.lang.String, java.lang.String, java.lang.String)
     */
    public RelateDTO findByUnique(String catg, String srcNo, String dstNo) {
        return this.relateDAO.findByUnique(catg, srcNo, dstNo);
    }

    // ~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~ //

    public void setRelateDAO(RelateDAO relateDAO) {
        this.relateDAO = relateDAO;
    }

}

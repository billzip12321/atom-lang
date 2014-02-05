/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg;

import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 分类模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgModel.java, V1.0.1 2014年2月5日 上午9:35:33 $
 */
public class CatgModel extends BaseDTO implements Comparable<CatgModel> {
    private static final long serialVersionUID = -4160495779543487770L;

    /** 上级分类 */
    private CatgModel           parent;

    /** 下级分类 */
    private List<CatgModel>     children         = new ArrayList<CatgModel>();

    public CatgModel getParent() {
        return parent;
    }

    public void setParent(CatgModel parent) {
        this.parent = parent;
    }

    public List<CatgModel> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<CatgModel>();
        }

        return children;
    }

    public void setChildren(List<CatgModel> children) {
        this.children = children;
    }

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(CatgModel dst) {
        if (this.getSort() == null) {
            return -1;
        }

        if (dst == null || dst.getSort() == null) {
            return 1;
        }

        return this.getSort().compareTo(dst.getSort());
    }

    /** 上级分类 */
    private String catg;

    /** 分类代码 */
    private String code;

    /** 排序值 */
    private String sort;

    /** 分类说明 */
    private String title;

    /** 分类扩展属性 */
    private String extMap;

    /** 分类描述 */
    private String summary;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    
}

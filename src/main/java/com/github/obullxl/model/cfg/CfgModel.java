/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 参数配置模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgModel.java, V1.0.1 2014年1月26日 上午9:34:16 $
 */
public class CfgModel extends BaseDTO implements Comparable<CfgModel> {
    private static final long serialVersionUID = -7633475123340121704L;

    /** 参数分类 */
    private String            catg;

    /** 参数KEY */
    private String            name;

    /** 参数说明 */
    private String            title;

    /** 参数值 */
    private String            value;

    /** 参数扩展值 */
    private String            valueExt;

    /** 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compareTo(CfgModel dst) {
        if (dst == null) {
            return 1;
        }

        String scatg = StringUtils.trimToEmpty(this.getCatg()) + StringUtils.trimToEmpty(this.getName());
        String dcatg = StringUtils.trimToEmpty(dst.getCatg()) + StringUtils.trimToEmpty(dst.getName());

        return scatg.compareTo(dcatg);
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueExt() {
        return valueExt;
    }

    public void setValueExt(String valueExt) {
        this.valueExt = valueExt;
    }

}

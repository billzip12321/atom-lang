/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 关系模型数据对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateDTO.java, V1.0.1 2014年1月29日 下午1:28:42 $
 */
public class RelateDTO extends BaseDTO {
    private static final long serialVersionUID = 8930521443805973799L;

    /** 分类 */
    private String            catg;

    /** 源编号 */
    private String            srcNo;

    /** 源名称 */
    private String            srcName;

    /** 目标编号 */
    private String            dstNo;

    /** 目标名称 */
    private String            dstName;

    /** 扩展参数 */
    private String            extMap;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getSrcNo() {
        return srcNo;
    }

    public void setSrcNo(String srcNo) {
        this.srcNo = srcNo;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDstNo() {
        return dstNo;
    }

    public void setDstNo(String dstNo) {
        this.dstNo = dstNo;
    }

    public String getDstName() {
        return dstName;
    }

    public void setDstName(String dstName) {
        this.dstName = dstName;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

}

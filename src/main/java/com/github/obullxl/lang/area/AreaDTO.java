/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.area;

import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.lang.ToString;

/**
 * 区域数据模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: AreaDTO.java, V1.0.1 2014年1月26日 下午2:28:19 $
 */
public class AreaDTO extends ToString implements Comparable<AreaDTO> {
    private static final long serialVersionUID = 5562438838627414128L;

    /** parent */
    private AreaDTO           parent;

    /** children */
    private List<AreaDTO>     children         = new ArrayList<AreaDTO>();

    public AreaDTO getParent() {
        return parent;
    }

    public void setParent(AreaDTO parent) {
        this.parent = parent;
    }

    public List<AreaDTO> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<AreaDTO>();
        }

        return children;
    }

    public void setChildren(List<AreaDTO> children) {
        this.children = children;
    }

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AreaDTO dst) {
        if (this.getSort() == null) {
            return -1;
        }

        if (dst == null || dst.getSort() == null) {
            return 1;
        }

        return this.getSort().compareTo(dst.getSort());
    }

    /** 编号 */
    private String no;

    /** 排序值 */
    private String sort;

    /** 区域 */
    private String name;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

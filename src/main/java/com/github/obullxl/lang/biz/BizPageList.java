/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.biz;

import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.lang.Paginator;

/**
 * 分页列表
 * 
 * @author obullxl@gmail.com
 * @version $Id: BizPageList.java, V1.0.1 2013年12月6日 上午9:00:52 $
 */
public abstract class BizPageList<T> extends BizResponse {
    private static final long serialVersionUID = 1876568188422192453L;

    /** 分页信息 */
    private Paginator         pager;

    /** 信息列表 */
    private List<T>           items;

    /**
     * CTOR
     */
    public BizPageList(Paginator pager, List<T> items) {
        this.pager = pager;
        this.items = items;
    }

    // ~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public Paginator getPager() {
        if (this.pager == null) {
            this.pager = new Paginator();
        }

        return pager;
    }

    public void setPager(Paginator pager) {
        this.pager = pager;
    }

    public List<T> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<T>();
        }

        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}

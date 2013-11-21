/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.ids;

import com.github.obullxl.lang.MapExt;
import com.github.obullxl.lang.ToString;

/**
 * ID接口Long实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: LongID.java, 2012-8-18 下午8:25:49 Exp $
 */
public class LongID extends ToString implements ID<Long>, Comparable<LongID> {
    private static final long  serialVersionUID = 6563737303459840471L;

    /** 是否更新KEY */
    public static final String SAVE_KEY         = "_save_data_key";

    /** ID */
    private long               id;

    /** 扩展参数 */
    private MapExt             extMap           = new MapExt();

    /**
     * CTOR
     */
    public LongID() {
        this(0L);
    }

    public LongID(long id) {
        this.id = id;
    }

    /** 
     * @see com.github.obullxl.lang.ids.ID#getId()
     */
    public final Long getId() {
        return this.id;
    }

    /** 
     * @see com.github.obullxl.lang.ids.ID#setId(java.lang.Object)
     */
    public final void setId(Long id) {
        this.id = id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final MapExt getExtMap() {
        if (this.extMap == null) {
            this.extMap = new MapExt();
        }

        return this.extMap;
    }

    public final void setExtMap(MapExt extMap) {
        this.extMap = extMap;
    }

    /** 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.getId().hashCode();
    }

    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!LongID.class.isAssignableFrom(other.getClass())) {
            return false;
        }

        return this.getId() == ((LongID) other).getId();
    }

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public final int compareTo(LongID other) {
        if (other == null) {
            return 1;
        }

        return (int) (this.getId() - other.getId());
    }

    /**
     * 是否需要保存
     */
    public final boolean hasSaveFlag() {
        return this.getExtMap().getBoolean(SAVE_KEY);
    }

    /**
     * 设置保存标记
     */
    public void setSaveFlag() {
        this.getExtMap().put(SAVE_KEY, Boolean.toString(true));
    }

    /**
     * 去除保存标记
     */
    public void removeSaveFlag() {
        this.getExtMap().remove(SAVE_KEY);
    }

}

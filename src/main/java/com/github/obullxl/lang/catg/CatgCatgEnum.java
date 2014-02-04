/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 分类模型分类枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgCatgEnum.java, V1.0.1 2014年1月31日 下午1:14:47 $
 */
public enum CatgCatgEnum implements EnumBase {
    //
    MODULES(1, "模块分类"),
    //
    FORUMS(2, "论坛分类"),
    //
    ;

    private final int    id;
    private final String desp;

    /**
     * CTOR
     */
    private CatgCatgEnum(int id, String desp) {
        this.id = id;
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final CatgCatgEnum findDefault() {
        return MODULES;
    }

    /**
     * 转换为Map映射
     */
    public static final Map<String, EnumBase> toMap() {
        return EnumBaseUtils.toEnumMap(values());
    }

    /**
     * 根据代码获取枚举
     */
    public static final CatgCatgEnum findByCode(String code) {
        for (CatgCatgEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return null;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.id;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.name();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}

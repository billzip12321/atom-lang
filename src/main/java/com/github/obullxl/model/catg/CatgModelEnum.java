/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 分类模型顶级分类枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgModelEnum.java, V1.0.1 2014年1月31日 下午1:14:47 $
 */
public enum CatgModelEnum implements EnumBase {
    //
    MODULE("模块分类"),
    //
    BLOG("博客分类"),
    //
    FORUM("论坛分类"),
    //
    HELP_CMS("帮助中心"),
    //
    ;

    private final String desp;

    /**
     * CTOR
     */
    private CatgModelEnum(String desp) {
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final CatgModelEnum findDefault() {
        return MODULE;
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
    public static final CatgModelEnum findByCode(String code) {
        for (CatgModelEnum enm : values()) {
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
        return this.ordinal();
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

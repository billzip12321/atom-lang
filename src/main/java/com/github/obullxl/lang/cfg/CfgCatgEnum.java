/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 参数模型分类枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgCatgEnum.java, V1.0.1 2014年1月31日 下午1:14:47 $
 */
public enum CfgCatgEnum implements EnumBase {
    //
    PARAMS(1, "系统参数"),
    //
    RIGHTS(2, "权限模型"),
    //
    ;

    private final int    id;
    private final String desp;

    /**
     * CTOR
     */
    private CfgCatgEnum(int id, String desp) {
        this.id = id;
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final CfgCatgEnum findDefault() {
        return PARAMS;
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
    public static final CfgCatgEnum findByCode(String code) {
        for (CfgCatgEnum enm : values()) {
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

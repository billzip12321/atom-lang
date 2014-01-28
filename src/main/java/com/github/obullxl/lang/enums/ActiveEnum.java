/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.enums;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 激活状态枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: ActiveEnum.java, V1.0.1 2014年1月28日 上午9:42:08 $
 */
public enum ActiveEnum implements EnumBase {
    //
    WAITE_ACTIVE(1, "W", "等待激活"),
    //
    WAITE_AUTH(2, "A", "等待认证"),
    //
    ACTIVE_NORMAL(3, "Z", "激活认证正常"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    /**
     * CTOR
     */
    private ActiveEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final ActiveEnum findDefault() {
        return WAITE_ACTIVE;
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
    public static final ActiveEnum findByCode(String code) {
        for (ActiveEnum enm : values()) {
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
        return this.code;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.user.enums;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 用户性别枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserSexEnum.java, V1.0.1 2014年1月28日 上午9:31:16 $
 */
public enum UserSexEnum implements EnumBase {
    //
    MALE("M", "男"),
    //
    FEMALE("F", "女"),
    //
    ;

    private final String code;
    private final String desp;

    /**
     * CTOR
     */
    private UserSexEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final UserSexEnum findDefault() {
        return MALE;
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
    public static final UserSexEnum findDefault(String code) {
        for (UserSexEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return findDefault();
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
        return this.code;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}

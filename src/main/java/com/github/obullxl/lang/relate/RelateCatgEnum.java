/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 关系模型分类枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateCatgEnum.java, V1.0.1 2014年1月31日 下午1:14:47 $
 */
public enum RelateCatgEnum implements EnumBase {
    //
    USER_RIGHT(1, "用户权限模型"),
    //
    FORUM_USER(2, "论坛管理员模型"),
    //
    ;

    private final int    id;
    private final String desp;

    /**
     * CTOR
     */
    private RelateCatgEnum(int id, String desp) {
        this.id = id;
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final RelateCatgEnum findDefault() {
        return USER_RIGHT;
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
    public static final RelateCatgEnum findByCode(String code) {
        for (RelateCatgEnum enm : values()) {
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

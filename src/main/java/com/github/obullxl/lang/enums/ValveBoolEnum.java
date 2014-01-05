/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.enums;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 布尔开关枚举值
 * 
 * @author obullxl@gmail.com
 * @version $Id: ValveBoolEnum.java, V1.0.1 2014年1月1日 下午7:17:15 $
 */
public enum ValveBoolEnum implements EnumBase {
    //
    TRUE(1, "1", "是"),
    //
    FALSE(2, "0", "否"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    private ValveBoolEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 布尔值
     */
    public static final boolean is(String code) {
        return findDefault(code) == ValveBoolEnum.TRUE;
    }

    /**
     * 初始状态
     */
    public static final ValveBoolEnum findDefault() {
        return FALSE;
    }

    /**
     * 根据代码获取枚举
     */
    public static final ValveBoolEnum findDefault(String code) {
        for (ValveBoolEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return FALSE;
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

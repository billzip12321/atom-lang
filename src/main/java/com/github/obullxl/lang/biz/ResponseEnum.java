/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.biz;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 业务返回码枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: ResponseEnum.java, V1.0.1 2013年12月8日 下午8:24:37 $
 */
public enum ResponseEnum implements EnumBase {
    //
    SYSTEM_ERROR("系统异常"),
    //
    REQUIRE_PARAM("参数为空"),
    //
    PERMISSION_ERROR("权限不足"),
    //
    ;

    private final String desp;

    private ResponseEnum(String desp) {
        this.desp = desp;
    }

    public static final ResponseEnum findByCode(String code) {
        for (ResponseEnum enm : values()) {
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

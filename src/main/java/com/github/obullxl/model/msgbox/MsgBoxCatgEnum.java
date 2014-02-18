/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.msgbox;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 消息信箱分类
 * 
 * @author obullxl@gmail.com
 * @version $Id: MsgBoxCatgEnum.java, V1.0.1 2014年2月10日 下午3:35:13 $
 */
public enum MsgBoxCatgEnum implements EnumBase {
    //
    IN_BOX("IN", "收件箱"),
    //
    OUT_BOX("OUT", "发件箱"),
    //
    ;

    private final String code;
    private final String desp;

    /**
     * CTOR
     */
    private MsgBoxCatgEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    /**
     * 获取默认类型
     */
    public static final MsgBoxCatgEnum findDefault() {
        return IN_BOX;
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
    public static final MsgBoxCatgEnum findByCode(String code) {
        for (MsgBoxCatgEnum enm : values()) {
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
        return this.code;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}

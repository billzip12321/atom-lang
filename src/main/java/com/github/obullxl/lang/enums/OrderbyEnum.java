/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.enums;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 排序方式
 * 
 * @author obullxl@gmail.com
 * @version $Id: OrderbyEnum.java, V1.0.1 2014年2月6日 下午3:07:09 $
 */
public enum OrderbyEnum implements EnumBase {
    //
    ASC("升序"),
    //
    DESC("降序"),
    //
    ;

    private final String desp;

    private OrderbyEnum(String desp) {
        this.desp = desp;
    }

    /**
     * 初始状态
     */
    public static final OrderbyEnum findDefault() {
        return DESC;
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
    public static final OrderbyEnum findDefault(String code) {
        for (OrderbyEnum enm : values()) {
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
        return this.name();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }
}

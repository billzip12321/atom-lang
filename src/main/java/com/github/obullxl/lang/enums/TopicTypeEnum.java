/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.enums;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 主题类型类型
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTypeEnum.java, V1.0.1 2014年1月1日 下午4:56:18 $
 */
public enum TopicTypeEnum implements EnumBase {
    //
    TOPIC(1, "T", "主题"),
    //
    REPLY(2, "R", "评论"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    private TopicTypeEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 初始状态
     */
    public static final TopicTypeEnum findDefault() {
        return TOPIC;
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
    public static final TopicTypeEnum findDefault(String code) {
        for (TopicTypeEnum enm : values()) {
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

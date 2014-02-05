/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 主题置顶类型枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTopEnum.java, V1.0.1 2014年2月5日 下午5:13:13 $
 */
public enum TopicTopEnum implements EnumBase {
    //
    NORMAL("", "非置顶"),
    //
    GLOBAL("G", "全局置顶"),
    //
    CATEGORY("C", "分类置顶"),
    //
    ;

    private final String code;
    private final String desp;

    private TopicTopEnum( String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    /**
     * 获取所有代码
     */
    public static final String[] findAllCodes() {
        List<String> codes = new ArrayList<String>();

        for (EnumBase enm : values()) {
            codes.add(enm.code());
        }

        return codes.toArray(new String[0]);
    }

    /**
     * 初始状态
     */
    public static final TopicTopEnum findDefault() {
        return NORMAL;
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
    public static final TopicTopEnum findDefault(String code) {
        for (TopicTopEnum enm : values()) {
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

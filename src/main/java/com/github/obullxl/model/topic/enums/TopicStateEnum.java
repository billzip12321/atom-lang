/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.model.topic.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 主题状态枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicStateEnum.java, V1.0.1 2013年12月7日 下午12:06:35 $
 */
public enum TopicStateEnum implements EnumBase {
    //
    VALID(1, "T", "有效"),
    //
    AUDIT(2, "A", "审核"),
    //
    DRAFT(3, "D", "草稿"),
    //
    INVALID(4, "F", "无效"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    private TopicStateEnum(int id, String code, String desp) {
        this.id = id;
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
    public static final TopicStateEnum findDefault() {
        return AUDIT;
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
    public static final TopicStateEnum findDefault(String code) {
        for (TopicStateEnum enm : values()) {
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

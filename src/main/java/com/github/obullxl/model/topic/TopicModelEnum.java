/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.EnumBaseUtils;

/**
 * 主题类型类型
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTypeEnum.java, V1.0.1 2014年1月1日 下午4:56:18 $
 */
public enum TopicModelEnum implements EnumBase {
    //
    BLOG_TOPIC("BT", "博客主题"),
    //
    BLOG_REPLY("BR", "博客评论"),
    //
    HELP_TOPIC("HT", "帮助主题"),
    //
    FORUM_TOPIC("FT", "论坛主题"),
    //
    FORUM_REPLY("FR", "论坛评论"),
    //
    ;

    private final String code;
    private final String desp;

    private TopicModelEnum(String code, String desp) {
        this.code = code;
        this.desp = desp;
    }

    /**
     * 初始状态
     */
    public static final TopicModelEnum findDefault() {
        return BLOG_TOPIC;
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
    public static final TopicModelEnum findDefault(String code) {
        for (TopicModelEnum enm : values()) {
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

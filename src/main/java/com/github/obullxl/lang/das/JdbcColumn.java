/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import com.github.obullxl.lang.ToString;

/**
 * 数据表列信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcColumn.java, V1.0.1 2014年2月10日 下午4:25:35 $
 */
public class JdbcColumn extends ToString {
    private static final long serialVersionUID = -1137301803633937469L;

    public enum NullEnum {
        NO, YES, UNKNOWN
    };

    /** 列名-如gmt_create */
    private String   name;

    /** 描述 */
    private String   comment;

    /** SQL数据类型 */
    private int      sqlType;

    /** SQL类型-如VARCHAR/INT/DATETIME */
    private String   typeName;

    /** 精度/长度 */
    private int      size;

    /** JAVA列选-如java.lang.String/java.lang.Integer/java.sql.Timestamp */
    private String   className;

    /** 可空枚举 */
    private NullEnum nullEnum;

    /** 默认值 */
    private String   defaultValue;

    // ~~~~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~~~~~ //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public NullEnum getNullEnum() {
        return nullEnum;
    }

    public void setNullEnum(NullEnum nullEnum) {
        this.nullEnum = nullEnum;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}

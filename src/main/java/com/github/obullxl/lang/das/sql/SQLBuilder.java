/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

/**
 * SQL构造器
 * 
 * @author obullxl@gmail.com
 * @version $Id: SQLBuilder.java, V1.0.1 2014年2月6日 下午12:34:05 $
 */
public class SQLBuilder {
    private static final ThreadLocal<SQLContext> sql = new ThreadLocal<SQLContext>();

    /**
     * 必须-开始一个SQL上下文，只能调用一次
     */
    public static SQLBuilder newBuilder() {
        sql.remove();
        sql.set(new SQLContext());
        return new SQLBuilder();
    }

    /**
     * 必须-结束一个SQL上下文，只能调用一次
     */
    public SQLContext finishBuilder() {
        SQLContext ctxt = sql.get();
        sql.remove();
        return ctxt;
    }

    /**
     * 增加一个查询条件
     */
    public SQLBuilder where(String field, Object value) {
        return this.where(OP.EQ, field, value);
    }

    /**
     * 增加一个查询条件
     */
    public SQLBuilder where(OP op, String field, Object value) {
        if (value != null) {
            SQLContext ctxt = sql.get();
            ctxt.add(ParamSingle.to(op, field, value));
        }

        return this;
    }

    /**
     * 增加查询条件区间
     */
    public SQLBuilder where(String field, Object valueBegin, Object valueFinish) {
        if (valueBegin != null || valueFinish != null) {
            SQLContext ctxt = sql.get();
            ctxt.add(ParamRange.to(field, valueBegin, valueFinish));
        }

        return this;
    }

}

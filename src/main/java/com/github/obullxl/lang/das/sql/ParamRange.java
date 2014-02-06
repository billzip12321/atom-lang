/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 区间查询条件
 * 
 * @author obullxl@gmail.com
 * @version $Id: RangeParam.java, V1.0.1 2014年2月6日 下午2:30:11 $
 */
public class ParamRange implements ParamSQL {
    private String field;
    private Object valueBegin;
    private Object valueFinish;

    private ParamRange() {
    }

    public static ParamRange to(String field) {
        ParamRange sql = new ParamRange();
        sql.setField(field);

        return sql;
    }

    public static ParamRange to(String field, Object begin) {
        ParamRange sql = to(field);
        sql.setValueBegin(begin);

        return sql;
    }

    public static ParamRange to(String field, Object begin, Object finish) {
        ParamRange sql = to(field, begin);
        sql.setValueFinish(finish);

        return sql;
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#whereSQL()
     */
    public String whereSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("(");

        // 开始
        if (this.valueBegin != null) {
            sql.append(this.field).append(OP.GE.getOperate()).append("?");
        }

        // 结束
        if (this.valueFinish != null) {
            if (this.valueBegin != null) {
                sql.append(" AND ");
            }

            sql.append(this.field).append(OP.LT.getOperate()).append("?");
        }

        sql.append(")");
        return sql.toString();
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        // 开始
        if (this.valueBegin != null) {
            stmt.setObject(++idx, this.valueBegin);
        }

        // 结束
        if (this.valueFinish != null) {
            stmt.setObject(++idx, this.valueFinish);
        }

        return idx;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValueBegin() {
        return valueBegin;
    }

    public void setValueBegin(Object valueBegin) {
        this.valueBegin = valueBegin;
    }

    public Object getValueFinish() {
        return valueFinish;
    }

    public void setValueFinish(Object valueFinish) {
        this.valueFinish = valueFinish;
    }

}

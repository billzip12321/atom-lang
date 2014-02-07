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
public class ParamRange<T> implements ParamSQL {
    /** 字段名称 */
    private String field;

    /** 查询条件开始值 */
    private T      valueBegin;

    /** 查询条件结束值 */
    private T      valueFinish;

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

    // ~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~ //

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public T getValueBegin() {
        return valueBegin;
    }

    public void setValueBegin(T valueBegin) {
        this.valueBegin = valueBegin;
    }

    public T getValueFinish() {
        return valueFinish;
    }

    public void setValueFinish(T valueFinish) {
        this.valueFinish = valueFinish;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL参数
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamSQL.java, V1.0.1 2014年2月6日 下午12:48:52 $
 */
public class ParamSingle<T> implements ParamSQL {
    /** 操作符 */
    private OP     operate;

    /** 字段名称 */
    private String field;

    /** 查询条件值 */
    private T      value;

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#whereSQL()
     */
    public String whereSQL() {
        StringBuilder sql = new StringBuilder();

        sql.append("(");
        sql.append(this.field).append(this.operate.getOperate()).append("?");
        sql.append(")");

        return sql.toString();
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        stmt.setObject(++idx, this.value);
        return idx;
    }

    // ~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~ //

    public OP getOperate() {
        return operate;
    }

    public void setOperate(OP operate) {
        this.operate = operate;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
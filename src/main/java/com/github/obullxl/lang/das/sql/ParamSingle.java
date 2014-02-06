/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * SQL参数
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamSQL.java, V1.0.1 2014年2月6日 下午12:48:52 $
 */
public class ParamSingle implements ParamSQL {
    private OP     operate;
    private String field;
    private Object value;

    private ParamSingle() {
    }

    public static ParamSingle to(OP operate) {
        ParamSingle sql = new ParamSingle();
        sql.setOperate(operate);

        return sql;
    }

    public static ParamSingle to(OP operate, String field) {
        ParamSingle sql = to(operate);
        sql.setField(field);

        return sql;
    }

    public static ParamSingle to(OP operate, String field, Object value) {
        ParamSingle sql = to(operate, field);
        sql.setValue(value);

        return sql;
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#whereSQL()
     */
    @SuppressWarnings("unchecked")
    public String whereSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("(");
        sql.append(this.field).append(this.operate.getOperate());

        // IN 或者 NOT IN
        if (this.operate == OP.IN || this.operate == OP.NI) {
            Iterator<String> values = ((List<String>) this.value).iterator();
            sql.append("(");
            while (values.hasNext()) {
                sql.append("?");
                values.next();

                if (values.hasNext()) {
                    sql.append(",");
                }
            }
            sql.append(")");
        }

        // 普通单值
        else {
            sql.append("?");
        }

        sql.append(")");
        return sql.toString();
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#stmtValue(int, java.sql.PreparedStatement)
     */
    @SuppressWarnings("unchecked")
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        // IN 或者 NOT IN
        if (this.operate == OP.IN || this.operate == OP.NI) {
            List<String> values = (List<String>) this.value;
            for (String sv : values) {
                stmt.setString(++idx, sv);
            }
        }

        // 普通单值
        else {
            stmt.setObject(++idx, this.value);
        }

        return idx;
    }

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}

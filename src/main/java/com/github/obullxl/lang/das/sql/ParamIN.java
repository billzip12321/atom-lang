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
 * IN查询条件
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamIN.java, V1.0.1 2014年2月7日 下午2:28:12 $
 */
public class ParamIN<T> implements ParamSQL {
    /** 操作符 */
    private OP      operate;

    /** 字段名称 */
    private String  field;

    /** 查询条件值 */
    private List<T> values;

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#whereSQL()
     */
    public String whereSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("(");
        sql.append(this.field).append(this.operate.getOperate());

        Iterator<T> values = this.values.iterator();
        sql.append("(");
        while (values.hasNext()) {
            sql.append("?");
            values.next();

            if (values.hasNext()) {
                sql.append(",");
            }
        }
        sql.append(")");

        sql.append(")");
        return sql.toString();
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        for (T value : this.values) {
            stmt.setObject(++idx, value);
        }

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

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

}

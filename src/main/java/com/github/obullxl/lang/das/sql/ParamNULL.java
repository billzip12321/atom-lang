/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.obullxl.lang.das.sql.OP;
import com.github.obullxl.lang.das.sql.ParamSQL;

/**
 * NULL条件
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamNull.java, V1.0.1 2014年2月7日 下午2:16:14 $
 */
public class ParamNULL implements ParamSQL {
    /** 操作符 */
    private OP     operate;

    /** 字段名称 */
    private String field;
    
    /**
     * CTOR
     */
    private ParamNULL() {
    }
    
    public static ParamNULL to(OP operate, String field) {
        ParamNULL param = new ParamNULL();
        param.setOperate(operate);
        param.setField(field);
        
        return param;
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#whereSQL()
     */
    public String whereSQL() {
        StringBuilder sql = new StringBuilder();

        sql.append("(");
        sql.append(this.field).append(this.operate.getOperate());
        sql.append(")");

        return sql.toString();
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
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

}

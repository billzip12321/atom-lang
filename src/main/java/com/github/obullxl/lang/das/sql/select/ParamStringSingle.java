/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.obullxl.lang.das.sql.OP;
import com.github.obullxl.lang.das.sql.ParamSingle;

/**
 * 单个String查询条件
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamStringSingle.java, V1.0.1 2014年2月7日 下午1:41:23 $
 */
public class ParamStringSingle extends ParamSingle<String> {

    /**
     * CTOR
     */
    private ParamStringSingle() {
    }

    public static ParamStringSingle to(OP operate, String field, String value) {
        ParamStringSingle sql = new ParamStringSingle();
        
        sql.setOperate(operate);
        sql.setField(field);
        sql.setValue(value);

        return sql;
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSingle#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        stmt.setString(++idx, this.getValue());
        return idx;
    }

}

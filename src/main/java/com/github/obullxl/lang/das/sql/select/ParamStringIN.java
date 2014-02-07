/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.github.obullxl.lang.das.sql.OP;
import com.github.obullxl.lang.das.sql.ParamIN;

/**
 * String查询条件区间
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamStringIN.java, V1.0.1 2014年2月7日 下午1:41:23 $
 */
public class ParamStringIN extends ParamIN<String> {

    /**
     * CTOR
     */
    private ParamStringIN() {
    }

    public static ParamStringIN to(OP operate, String field, List<String> values) {
        ParamStringIN sql = new ParamStringIN();

        sql.setOperate(operate);
        sql.setField(field);
        sql.setValues(values);

        return sql;
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamIN#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        for (String value : this.getValues()) {
            stmt.setObject(++idx, value);
        }

        return idx;
    }

}

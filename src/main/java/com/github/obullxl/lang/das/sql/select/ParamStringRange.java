/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.obullxl.lang.das.sql.ParamRange;

/**
 * String查询条件区间
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamStringSingle.java, V1.0.1 2014年2月7日 下午1:41:23 $
 */
public class ParamStringRange extends ParamRange<String> {

    /**
     * CTOR
     */
    private ParamStringRange() {
    }

    public static ParamStringRange to(String field, String valueBegin, String valueFinish) {
        ParamStringRange sql = new ParamStringRange();

        sql.setField(field);
        sql.setValueBegin(valueBegin);
        sql.setValueFinish(valueFinish);

        return sql;
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamRange#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        // 开始
        if (this.getValueBegin() != null) {
            stmt.setString(++idx, this.getValueBegin());
        }

        // 结束
        if (this.getValueFinish() != null) {
            stmt.setString(++idx, this.getValueFinish());
        }

        return idx;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.github.obullxl.lang.das.sql.ParamRange;

/**
 * String查询条件区间
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamDateRange.java, V1.0.1 2014年2月7日 下午1:41:23 $
 */
public class ParamDateRange extends ParamRange<Date> {

    /**
     * CTOR
     */
    private ParamDateRange() {
    }

    public static ParamDateRange to(String field, Date valueBegin, Date valueFinish) {
        ParamDateRange sql = new ParamDateRange();

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
            stmt.setTimestamp(++idx, new Timestamp(this.getValueBegin().getTime()));
        }

        // 结束
        if (this.getValueFinish() != null) {
            stmt.setTimestamp(++idx, new Timestamp(this.getValueFinish().getTime()));
        }

        return idx;
    }

}

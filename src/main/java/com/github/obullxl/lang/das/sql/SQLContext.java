/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * SQL上线文
 * 
 * @author obullxl@gmail.com
 * @version $Id: SQLContext.java, V1.0.1 2014年2月6日 下午12:34:40 $
 */
public class SQLContext {
    private List<ParamSQL> params = new ArrayList<ParamSQL>();

    public SQLContext add(ParamSQL param) {
        this.params.add(param);
        return this;
    }

    public String whereSQL() {
        StringBuilder sql = new StringBuilder();

        for (ParamSQL param : this.params) {
            if (sql.length() > 0) {
                sql.append(" AND ");
            }

            sql.append(param.whereSQL());
        }

        if (sql.length() > 0) {
            return "(" + sql.toString() + ")";
        } else {
            return StringUtils.EMPTY;
        }
    }

    public void stmtValue(PreparedStatement stmt) throws SQLException {
        int idx = 0;
        for (ParamSQL param : params) {
            idx = param.stmtValue(idx, stmt);
        }
    }

}

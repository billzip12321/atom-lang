/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC设置值
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcStmtValue.java, V1.0.1 2014年1月28日 下午1:43:20 $
 */
public interface JdbcStmtValue {

    /**
     * 设置
     */
    public void set(PreparedStatement stmt) throws SQLException;

    /**
     * 默认JDBC查询设置值
     */
    public static class DefaultJdbcStmtValue implements JdbcStmtValue {
        public void set(PreparedStatement stmt) throws SQLException {
        }
    }
}

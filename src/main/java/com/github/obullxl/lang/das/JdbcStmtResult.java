/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.PreparedStatement;

/**
 * JDBC插入（INSERT）执行结果
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcStmtResult.java, V1.0.1 2014年1月28日 下午2:05:06 $
 */
public interface JdbcStmtResult {

    /**
     * 结果处理
     */
    public <T> T onResult(PreparedStatement stmt);

    /**
     * 默认实现
     */
    public static class DefaultJdbcStmtResult implements JdbcStmtResult {
        public <T> T onResult(PreparedStatement stmt) {
            return null;
        }
    }
    
}

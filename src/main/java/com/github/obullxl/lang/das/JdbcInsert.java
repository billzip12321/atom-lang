/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.slf4j.Logger;

import com.github.obullxl.lang.utils.DBUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * JDBC数据插入
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcInsert.java, V1.0.1 2014年1月28日 下午1:59:24 $
 */
public final class JdbcInsert {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /**
     * 数据插入
     */
    public static final void execute(DataSource dataSource, String sql, JdbcStmtValue ss) {
        execute(dataSource, sql, ss, null);
    }

    /**
     * 数据插入（处理插入结果）
     */
    public static final <T> T execute(DataSource dataSource, String sql, JdbcStmtValue ss, JdbcStmtResult sr) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            // 设置值
            ss.set(stmt);

            // 执行插入
            stmt.execute();

            // 结果处理
            if (sr != null) {
                return sr.onResult(stmt);
            }

            return null;
        } catch (Exception e) {
            String txt = "[数据插入]-插入[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

}

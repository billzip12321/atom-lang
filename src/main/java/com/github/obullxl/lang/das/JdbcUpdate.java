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
 * JDBC数据更新
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcUpdate.java, V1.0.1 2014年1月28日 下午1:39:14 $
 */
public final class JdbcUpdate {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /**
     * 执行更新
     */
    public static final int executeUpdate(DataSource dataSource, String sql, JdbcStmtValue ss) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // 连接
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            // 设置值
            ss.set(stmt);

            // 执行更新
            return stmt.executeUpdate();
        } catch (Exception e) {
            String txt = "[数据更新]-更新[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

}

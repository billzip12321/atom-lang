/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;

import com.github.obullxl.lang.utils.DBUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * JDBC数据查询
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcSelect.java, V1.0.1 2014年1月28日 下午1:07:14 $
 */
public final class JdbcSelect {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /**
     * 统计条数
     */
    public static final int count(DataSource dataSource, String sql, String field, JdbcStmtValue ss) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 连接
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            // 设置值
            ss.set(stmt);

            // 执行查询
            rs = stmt.executeQuery();

            // 对象映射
            rs.next();
            return rs.getInt(field);
        } catch (Exception e) {
            String txt = "[数据查询]-统计条数[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 查询单条记录
     */
    public static final <T> T selectOne(DataSource dataSource, String sql, JdbcRowMap rm, JdbcStmtValue ss) {
        List<T> items = selectList(dataSource, sql, rm, ss);

        if (items != null && !items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }

    /**
     * 查询多条记录
     */
    public static final <T> List<T> selectList(DataSource dataSource, String sql, JdbcRowMap rm, JdbcStmtValue ss) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 连接
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            // 设置值
            ss.set(stmt);

            // 执行查询
            rs = stmt.executeQuery();

            // 对象映射
            return rm.map(rs);
        } catch (Exception e) {
            String txt = "[数据查询]-查询[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * JDBC查询设置值
     */
    public static interface JdbcRowMap {
        public <T> List<T> map(ResultSet rs) throws SQLException;
    }

}

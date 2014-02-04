/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.das.SQLDialect;

/**
 * DB工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: DBUtils.java, V1.0.1 2013-3-22 下午8:32:13 $
 */
public final class DBUtils {

    /**
     * 关闭ResultSet
     */
    public static final void closeQuietly(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * 关闭Statement
     */
    public static final void closeQuietly(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * 关闭Connection
     */
    public static final void closeQuietly(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * "Guess" the {@link SQLDialect} from a {@link Connection} instance.
     * <p>
     * This method tries to guess the <code>SQLDialect</code> of a connection
     * from the its connection URL as obtained by
     * {@link DatabaseMetaData#getURL()}. If the dialect cannot be guessed from
     * the URL (e.g. when using an JDBC-ODBC bridge), further actions may be
     * implemented in the future.
     *
     * @see #dialect(String)
     */
    @SuppressWarnings("deprecation")
    public static final SQLDialect dialect(Connection connection) {
        SQLDialect result = SQLDialect.SQL99;

        try {
            String url = connection.getMetaData().getURL();
            result = dialect(url);
        } catch (SQLException ignore) {
        }

        if (result == SQLDialect.SQL99) {
            try {
                String prodName = connection.getMetaData().getDatabaseProductName();
                if (StringUtils.containsIgnoreCase(prodName, "oracle")) {
                    result = SQLDialect.ORACLE;
                }
            } catch (SQLException ignore) {
            }
        }

        return result;
    }

    /**
     * "Guess" the {@link SQLDialect} from a connection URL.
     */
    @SuppressWarnings("deprecation")
    public static final SQLDialect dialect(String url) {

        // The below list might not be accurate or complete. Feel free to
        // contribute fixes related to new / different JDBC driver configuraitons
        if (url.startsWith("jdbc:cubrid:")) {
            return SQLDialect.CUBRID;
        } else if (url.startsWith("jdbc:derby:")) {
            return SQLDialect.DERBY;
        } else if (url.startsWith("jdbc:firebirdsql:")) {
            return SQLDialect.FIREBIRD;
        } else if (url.startsWith("jdbc:h2:")) {
            return SQLDialect.H2;
        } else if (url.startsWith("jdbc:hsqldb:")) {
            return SQLDialect.HSQLDB;
        } else if (url.startsWith("jdbc:mariadb:")) {
            return SQLDialect.MARIADB;
        } else if (url.startsWith("jdbc:mysql:") || url.startsWith("jdbc:google:")) {
            return SQLDialect.MYSQL;
        } else if (url.startsWith("jdbc:postgresql:")) {
            return SQLDialect.POSTGRES;
        } else if (url.startsWith("jdbc:sqlite:")) {
            return SQLDialect.SQLITE;
        } else if (StringUtils.containsIgnoreCase(url, "oracle")) {
            return SQLDialect.ORACLE;
        }

        return SQLDialect.SQL99;
    }

    /**
     * Safely free a blob.
     */
    public static final void safeFree(Blob blob) {
        if (blob != null) {
            try {
                blob.free();
            } catch (Exception ignore) {
            }
        }
    }

    /**
     * Safely free a clob.
     */
    public static final void safeFree(Clob clob) {
        if (clob != null) {
            try {
                clob.free();
            } catch (Exception ignore) {
            }
        }
    }

    /**
     * Convenient way to check if a JDBC-originated record was <code>null</code>.
     * <p>
     * This is useful to check if primitive types obtained from the JDBC API
     * were actually SQL NULL values.
     *
     * @param stream The data source from which a value was read
     * @param value The value that was read
     * @return The <code>value</code> or <code>null</code> if the
     *         {@link SQLInput#wasNull()} is <code>true</code>
     */
    public static final <T> T wasNull(SQLInput stream, T value) throws SQLException {
        return stream.wasNull() ? null : value;
    }

    /**
     * Convenient way to check if a JDBC-originated record was <code>null</code>.
     * <p>
     * This is useful to check if primitive types obtained from the JDBC API
     * were actually SQL NULL values.
     *
     * @param rs The data source from which a value was read
     * @param value The value that was read
     * @return The <code>value</code> or <code>null</code> if the
     *         {@link ResultSet#wasNull()} is <code>true</code>
     */
    public static final <T> T wasNull(ResultSet rs, T value) throws SQLException {
        return rs.wasNull() ? null : value;
    }

    /**
     * Convenient way to check if a JDBC-originated record was <code>null</code>.
     * <p>
     * This is useful to check if primitive types obtained from the JDBC API
     * were actually SQL NULL values.
     *
     * @param statement The data source from which a value was read
     * @param value The value that was read
     * @return The <code>value</code> or <code>null</code> if the
     *         {@link CallableStatement#wasNull()} is <code>true</code>
     */
    public static final <T> T wasNull(CallableStatement statement, T value) throws SQLException {
        return statement.wasNull() ? null : value;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.das.JdbcColumn.NullEnum;
import com.github.obullxl.lang.utils.DBUtils;

/**
 * DB元数据
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcMetaData.java, V1.0.1 2014年2月10日 下午4:23:29 $
 */
public class JdbcMetaData extends ToString {
    private static final long             serialVersionUID = 4461923028654716493L;

    /** 数据库方言 */
    private SQLDialect                    dialect;

    /** 数据库SQL方言 */
    private JdbcDialect                   jdbcDialect;

    /** 数据表 */
    private String                        tableName;

    /** 数据列集合 */
    private final Map<String, JdbcColumn> colunms          = new LinkedHashMap<String, JdbcColumn>();

    /**
     * 新增加一列
     */
    public JdbcMetaData newColumn(JdbcColumn column) {
        this.colunms.put(StringUtils.lowerCase(column.getName()), column);
        return this;
    }

    /**
     * 根据列名查找列
     */
    public JdbcColumn findColumn(String name) {
        return this.colunms.get(StringUtils.lowerCase(name));
    }

    /**
     * 构建DB元数据信息
     */
    public static final JdbcMetaData newMetaData(DataSource ds, String tableName) {
        JdbcMetaData meta = new JdbcMetaData();
        meta.setTableName(tableName);

        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            meta.setDialect(DBUtils.dialect(dbmd.getURL()));
            meta.setJdbcDialect(JdbcDialect.findJdbcDialect(meta.getDialect()));

            rs = dbmd.getColumns(conn.getCatalog(), null, tableName, null);
            while (rs.next()) {
                JdbcColumn column = new JdbcColumn();

                column.setName(rs.getString("COLUMN_NAME"));
                column.setComment(rs.getString("REMARKS"));
                column.setSqlType(rs.getInt("DATA_TYPE"));
                column.setTypeName(rs.getString("TYPE_NAME"));
                column.setSize(rs.getInt("COLUMN_SIZE"));

                int nullable = rs.getInt("NULLABLE");
                if (nullable == DatabaseMetaData.columnNullable) {
                    column.setNullEnum(NullEnum.YES);
                } else if (nullable == DatabaseMetaData.columnNoNulls) {
                    column.setNullEnum(NullEnum.NO);
                }
                if (nullable == DatabaseMetaData.columnNullableUnknown) {
                    column.setNullEnum(NullEnum.UNKNOWN);
                }

                column.setDefaultValue(rs.getString("COLUMN_DEF"));

                // 增加一列
                meta.newColumn(column);
            }

        } catch (Exception e) {
            throw new RuntimeException("构建数据表[" + tableName + "]元数据异常!");
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(conn);
        }

        return meta;
    }

    // ~~~~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~~~~~ //

    public SQLDialect getDialect() {
        return dialect;
    }

    public void setDialect(SQLDialect dialect) {
        this.dialect = dialect;
    }

    public JdbcDialect getJdbcDialect() {
        return jdbcDialect;
    }

    public void setJdbcDialect(JdbcDialect jdbcDialect) {
        this.jdbcDialect = jdbcDialect;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, JdbcColumn> getColunms() {
        return colunms;
    }

}

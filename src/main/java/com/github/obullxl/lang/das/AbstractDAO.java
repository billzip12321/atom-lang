/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;

import com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap;
import com.github.obullxl.lang.das.JdbcStmtValue.DefaultJdbcStmtValue;
import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * DAO抽象基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractDAO.java, V1.0.1 2014年1月28日 下午4:13:56 $
 */
public abstract class AbstractDAO implements JdbcRowMap {
    /** Logger */
    protected static final Logger logger             = LogUtils.get();

    /** 数据源 */
    protected DataSource          dataSource;

    /** 参数数据表 */
    protected String              tableName;

    /** 创建数据表SQL */
    protected Resource            createTableSQL;

    /** CreateTime */
    protected String              gmtCreateFieldName = "gmt_create";

    /** ModifyTime */
    protected String              gmtModifyFieldName = "gmt_modify";

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.dataSource, "[模型DAO]-数据源注入失败!");
        Validate.notNull(this.tableName, "[模型DAO]-数据表名没有设置!");

        // 初始化数据表
        this.createTable();

        logger.warn("[{}]-数据表信息:{}({}).", //
            this.getClass().getSimpleName(), this.tableName, this.findTableFields());
    }

    /**
     * 获取数据插入SQL
     */
    public abstract String findInsertSQL();

    /**
     * 获取数据更新SQL
     */
    public abstract String findUpdateSQL();

    /**
     * 获取所有数据表字段
     */
    public abstract String findTableFields();

    /**
     * 填充时间戳值
     */
    public void fillStmtTimestampValue(int idx, PreparedStatement stmt, Date date) throws SQLException {
        if (date == null) {
            date = new Date();
        }

        stmt.setTimestamp(idx, new Timestamp(date.getTime()));
    }

    /**
     * 填充枚举值
     */
    public void fillStmtEnumBaseValue(int idx, PreparedStatement stmt, EnumBase enm) throws SQLException {
        if (enm != null) {
            stmt.setString(idx, enm.code());
        } else {
            stmt.setString(idx, StringUtils.EMPTY);
        }
    }

    /**
     * 初始化数据表
     */
    public void createTable() {
        if(this.createTableSQL == null || !this.createTableSQL.exists()) {
            // TODO:
        }
    }

    /**
     * 查询所有数据表记录
     */
    public <T> List<T> selectAll() {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new DefaultJdbcStmtValue());
    }

    /**
     * 删除所有数据表记录
     */
    public int deleteAll() {
        String sql = "DELETE FROM " + this.tableName;
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new DefaultJdbcStmtValue());
    }

    /**
     * 更新所有记录的单个字段
     */
    public int updateValues(String field, final Object value) {
        return this.updateValues(new String[] { field }, new Object[] { value });
    }

    /**
     * 更新所有记录的单个字段
     */
    public int updateValues(String[] fields, final Object[] values) {
        if (fields == null || fields.length < 1 || values == null || values.length < 1) {
            return 0;
        }

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName);
        sql.append(" SET ");

        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sql.append(",");
            }
            sql.append(fields[i]).append("=?");
        }

        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                for (Object value : values) {
                    stmt.setObject(++idx, value);
                }
            }
        });
    }

    /**
     * SQL执行（UPDATE/DELETE）
     */
    public int executeUpdate(String sql, final Object... args) {
        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                if (args != null && args.length > 0) {
                    int count = args.length;
                    for (int i = 1; i <= count; i++) {
                        stmt.setObject(i, args[i - 1]);
                    }
                }
            }
        });
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCreateTableSQL(Resource createTableSQL) {
        this.createTableSQL = createTableSQL;
    }

    public void setGmtCreateFieldName(String gmtCreateFieldName) {
        this.gmtCreateFieldName = gmtCreateFieldName;
    }

    public void setGmtModifyFieldName(String gmtModifyFieldName) {
        this.gmtModifyFieldName = gmtModifyFieldName;
    }

}

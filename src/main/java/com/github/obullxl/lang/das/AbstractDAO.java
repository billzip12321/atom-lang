/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * DAO抽象基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractDAO.java, V1.0.1 2014年1月28日 下午4:13:56 $
 */
public abstract class AbstractDAO {
    /** Logger */
    protected static final Logger logger             = LogUtils.get();

    /** 数据源 */
    protected DataSource          dataSource;

    /** 参数数据表 */
    protected String              tableName;

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

    public void setGmtCreateFieldName(String gmtCreateFieldName) {
        this.gmtCreateFieldName = gmtCreateFieldName;
    }

    public void setGmtModifyFieldName(String gmtModifyFieldName) {
        this.gmtModifyFieldName = gmtModifyFieldName;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcStmtValue.DefaultJdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;

/**
 * 模块分类DAO实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgDAO.java, V1.0.1 2014年1月26日 上午11:59:19 $
 */
public class CatgDAO extends AbstractDAO implements JdbcRowMap {
    public static final String NAME             = "CatgDAO";

    /** 上级分类 */
    private String             catgFieldName    = "catg";

    /** 分类代码 */
    private String             codeFieldName    = "code";

    /** 分类排序值 */
    private String             sortFieldName    = "sort";

    /** 分类说明 */
    private String             titleFieldName   = "title";

    /** 分类扩展值 */
    private String             extMapFieldName  = "ext_map";

    /** 分类摘要描述 */
    private String             summaryFieldName = "summary";

    /** 插入SQL */
    private String             insertSQL;

    /** 更新SQL */
    private String             updateSQL;

    /** 查询SQL */
    private String             tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#init()
     */
    public void init() {
        super.init();

        logger.warn("[模块分类]-数据表信息:{}({},{},{},{},{},{},{},{}).", //
            this.tableName, this.catgFieldName, this.codeFieldName, this.sortFieldName, this.titleFieldName, //
            this.extMapFieldName, this.summaryFieldName, this.gmtCreateFieldName, this.gmtModifyFieldName);

        this.findTableFields();
        logger.warn("[模块分类]-TableFields: {}", this.tableFields);
    }

    /**
     * 获取SELECT SQL
     */
    private String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.catgFieldName);
            sql.append(",").append(this.codeFieldName);
            sql.append(",").append(this.sortFieldName);
            sql.append(",").append(this.titleFieldName);
            sql.append(",").append(this.extMapFieldName);
            sql.append(",").append(this.summaryFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /**
     * 插入模块分类
     */
    public void insert(final CatgDTO catg) {
        // SQL
        if (this.insertSQL == null) {
            if (this.insertSQL == null) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO ").append(this.tableName).append("(");
                sql.append(this.findTableFields());
                sql.append(") VALUES(");
                sql.append("?,?,?,?,?,?,?,?");
                sql.append(")");

                this.insertSQL = sql.toString();
                logger.warn("[模块分类]-InsertSQL: {}", this.insertSQL);
            }
        }

        // 执行插入
        JdbcInsert.execute(this.dataSource, this.insertSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg.getCatg());
                stmt.setString(2, catg.getCode());
                stmt.setString(3, catg.getSort());
                stmt.setString(4, catg.getTitle());
                stmt.setString(5, catg.getExtMap());
                stmt.setString(6, catg.getSummary());

                if (catg.getGmtCreate() == null) {
                    stmt.setTimestamp(7, null);
                } else {
                    stmt.setTimestamp(7, new Timestamp(catg.getGmtCreate().getTime()));
                }

                if (catg.getGmtModify() == null) {
                    stmt.setTimestamp(8, null);
                } else {
                    stmt.setTimestamp(8, new Timestamp(catg.getGmtModify().getTime()));
                }
            }
        });
    }

    /**
     * 更新模块分类
     */
    public int update(final CatgDTO catg) {
        // SQL
        if (this.updateSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(this.tableName);
            sql.append(" SET ");
            sql.append(this.catgFieldName).append("=?");
            sql.append(",").append(this.sortFieldName).append("=?");
            sql.append(",").append(this.titleFieldName).append("=?");
            sql.append(",").append(this.extMapFieldName).append("=?");
            sql.append(",").append(this.summaryFieldName).append("=?");
            sql.append(",").append(this.gmtModifyFieldName).append("=?");
            sql.append(" WHERE ");
            sql.append(this.codeFieldName).append("=?");

            this.updateSQL = sql.toString();
            logger.warn("[模块分类]-UpdateSQL: {}", this.updateSQL);
        }

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, this.updateSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg.getCatg());
                stmt.setString(2, catg.getSort());
                stmt.setString(3, catg.getTitle());
                stmt.setString(4, catg.getExtMap());
                stmt.setString(5, catg.getSummary());

                if (catg.getGmtModify() == null) {
                    stmt.setTimestamp(6, null);
                } else {
                    stmt.setTimestamp(6, new Timestamp(catg.getGmtModify().getTime()));
                }

                stmt.setString(7, catg.getCode());
            }
        });
    }

    /**
     * 查询模块分类
     */
    public List<CatgDTO> find() {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);

        // 查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new DefaultJdbcStmtValue());
    }

    /**
     * 删除模块分类
     */
    public int delete() {
        // SQL
        String sql = "DELETE FROM " + this.tableName;

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new DefaultJdbcStmtValue());
    }

    /**
     * 根据代码删除模块分类
     */
    public int delete(final String code) {
        // SQL
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.codeFieldName + "=?";

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, code);
            }
        });
    }

    /**
     * 根据分类删除模块分类
     */
    public int deleteByCatg(final String catg) {
        // SQL
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.catgFieldName + "=?";

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<CatgDTO> map(ResultSet rs) throws SQLException {
        List<CatgDTO> catgs = new ArrayList<CatgDTO>();

        while (rs.next()) {
            CatgDTO catg = new CatgDTO();

            catg.setCatg(rs.getString(this.catgFieldName));
            catg.setCode(rs.getString(this.codeFieldName));
            catg.setSort(rs.getString(this.sortFieldName));
            catg.setTitle(rs.getString(this.titleFieldName));
            catg.setExtMap(rs.getString(this.extMapFieldName));
            catg.setSummary(rs.getString(this.summaryFieldName));
            catg.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            catg.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            catgs.add(catg);
        }

        return catgs;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setCodeFieldName(String codeFieldName) {
        this.codeFieldName = codeFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public void setTitleFieldName(String titleFieldName) {
        this.titleFieldName = titleFieldName;
    }

    public void setExtMapFieldName(String extMapFieldName) {
        this.extMapFieldName = extMapFieldName;
    }

    public void setSummaryFieldName(String summaryFieldName) {
        this.summaryFieldName = summaryFieldName;
    }

}

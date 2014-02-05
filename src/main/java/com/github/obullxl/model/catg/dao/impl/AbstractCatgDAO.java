/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;
import com.github.obullxl.model.catg.CatgModel;
import com.github.obullxl.model.catg.dao.CatgDAO;

/**
 * 分类模型DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractCatgDAO.java, V1.0.1 2014年2月5日 上午9:51:41 $
 */
public abstract class AbstractCatgDAO extends AbstractDAO implements CatgDAO {

    /** 上级分类 */
    protected String catgFieldName    = "catg";

    /** 分类代码 */
    protected String codeFieldName    = "code";

    /** 分类排序值 */
    protected String sortFieldName    = "sort";

    /** 分类说明 */
    protected String titleFieldName   = "title";

    /** 分类扩展值 */
    protected String extMapFieldName  = "ext_map";

    /** 分类摘要描述 */
    protected String summaryFieldName = "summary";

    /** 插入SQL */
    private String   insertSQL;

    /** 更新SQL */
    private String   updateSQL;

    /** 查询SQL */
    private String   tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findInsertSQL()
     */
    public String findInsertSQL() {
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

        return this.insertSQL;
    }

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findUpdateSQL()
     */
    public String findUpdateSQL() {
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

        return this.updateSQL;
    }

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
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
    public void insert(final CatgModel catg) {
        // 执行插入
        JdbcInsert.execute(this.dataSource, this.findInsertSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setString(++idx, catg.getCatg());
                stmt.setString(++idx, catg.getCode());
                stmt.setString(++idx, catg.getSort());
                stmt.setString(++idx, catg.getTitle());
                stmt.setString(++idx, catg.getExtMap());
                stmt.setString(++idx, catg.getSummary());

                Date create = catg.getGmtCreate();
                if (create == null) {
                    create = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(create.getTime()));

                Date modify = catg.getGmtModify();
                if (modify == null) {
                    modify = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(modify.getTime()));
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.catg.dao.CatgDAO#update(com.github.obullxl.model.catg.CatgModel)
     */
    public int update(final CatgModel catg) {
        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, this.findUpdateSQL(), new JdbcStmtValue() {
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
     * @see com.github.obullxl.model.catg.dao.CatgDAO#selectByCode(java.lang.String)
     */
    public CatgModel selectByCode(final String code) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ").append(this.codeFieldName).append("=?");

        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, code);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.catg.dao.CatgDAO#selectByCatg(java.lang.String)
     */
    public List<CatgModel> selectByCatg(final String catg) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ").append(this.catgFieldName).append("=?");

        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.catg.dao.CatgDAO#deleteByCode(java.lang.String)
     */
    public int deleteByCode(final String code) {
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
     * @see com.github.obullxl.model.catg.dao.CatgDAO#deleteByCatg(java.lang.String)
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
     * @see com.github.obullxl.model.catg.dao.CatgDAO#delete(java.lang.String, java.lang.String)
     */
    public int delete(final String catg, final String code) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ").append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.codeFieldName).append("=?");

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, code);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<CatgModel> map(ResultSet rs) throws SQLException {
        List<CatgModel> catgs = new ArrayList<CatgModel>();

        while (rs.next()) {
            CatgModel catg = new CatgModel();

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

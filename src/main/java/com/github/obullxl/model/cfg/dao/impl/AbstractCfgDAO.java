/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;
import com.github.obullxl.model.cfg.CfgModel;
import com.github.obullxl.model.cfg.dao.CfgDAO;

/**
 * 配置模型DAO基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractCfgDAO.java, V1.0.1 2014年2月5日 下午2:57:18 $
 */
public abstract class AbstractCfgDAO extends AbstractDAO implements CfgDAO {

    /** 参数分类 */
    protected String catgFieldName     = "catg";

    /** 参数KEY */
    protected String nameFieldName     = "name";

    /** 参数说明 */
    protected String titleFieldName    = "title";

    /** 参数值 */
    protected String valueFieldName    = "value";

    /** 参数扩展值 */
    protected String valueExtFieldName = "value_ext";

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
            sql.append("?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[配置模型]-InsertSQL: {}", this.insertSQL);
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
            sql.append(this.titleFieldName).append("=?");
            sql.append(",").append(this.valueFieldName).append("=?");
            sql.append(",").append(this.valueExtFieldName).append("=?");
            sql.append(",").append(this.gmtModifyFieldName).append("=?");
            sql.append(" WHERE ");
            sql.append(this.catgFieldName).append("=?");
            sql.append(" AND ").append(this.nameFieldName).append("=?");

            this.updateSQL = sql.toString();
            logger.warn("[配置模型]-UpdateSQL: {}", this.updateSQL);
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
            sql.append(",").append(this.nameFieldName);
            sql.append(",").append(this.titleFieldName);
            sql.append(",").append(this.valueFieldName);
            sql.append(",").append(this.valueExtFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /** 
     * @see com.github.obullxl.model.cfg.dao.CfgDAO#insert(com.github.obullxl.model.cfg.CfgModel)
     */
    public void insert(final CfgModel cfg) {
        // 执行插入
        JdbcInsert.execute(this.dataSource, this.findInsertSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setString(++idx, cfg.getCatg());
                stmt.setString(++idx, cfg.getName());
                stmt.setString(++idx, cfg.getTitle());
                stmt.setString(++idx, cfg.getValue());
                stmt.setString(++idx, cfg.getValueExt());

                Date create = cfg.getGmtCreate();
                if (create == null) {
                    create = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(create.getTime()));

                Date modify = cfg.getGmtModify();
                if (modify == null) {
                    modify = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(modify.getTime()));
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.cfg.dao.CfgDAO#update(com.github.obullxl.model.cfg.CfgModel)
     */
    public int update(final CfgModel cfg) {
        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, this.findUpdateSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, cfg.getTitle());
                stmt.setString(2, cfg.getValue());
                stmt.setString(3, cfg.getValueExt());

                Date modify = cfg.getGmtModify();
                if (modify == null) {
                    modify = new Date();
                }
                stmt.setTimestamp(4, new Timestamp(modify.getTime()));

                stmt.setString(5, cfg.getCatg());
                stmt.setString(6, cfg.getName());
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.cfg.dao.CfgDAO#delete(java.lang.String)
     */
    public int delete(final String catg) {
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
     * @see com.github.obullxl.model.cfg.dao.CfgDAO#delete(java.lang.String, java.lang.String)
     */
    public int delete(final String catg, final String name) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.nameFieldName).append("=?");

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, name);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<CfgModel> map(ResultSet rs) throws SQLException {
        List<CfgModel> cfgs = new ArrayList<CfgModel>();

        while (rs.next()) {
            CfgModel cfg = new CfgModel();

            cfg.setCatg(rs.getString(this.catgFieldName));
            cfg.setName(rs.getString(this.nameFieldName));
            cfg.setTitle(rs.getString(this.titleFieldName));
            cfg.setValue(rs.getString(this.valueFieldName));
            cfg.setValueExt(rs.getString(this.valueExtFieldName));
            cfg.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            cfg.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            cfgs.add(cfg);
        }

        return cfgs;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setNameFieldName(String nameFieldName) {
        this.nameFieldName = nameFieldName;
    }

    public void setTitleFieldName(String titleFieldName) {
        this.titleFieldName = titleFieldName;
    }

    public void setValueFieldName(String valueFieldName) {
        this.valueFieldName = valueFieldName;
    }

    public void setValueExtFieldName(String valueExtFieldName) {
        this.valueExtFieldName = valueExtFieldName;
    }

}

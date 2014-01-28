/**
 * obullxl@gmail.com
 */
package com.github.obullxl.lang.cfg;

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
 * 系统参数DAO实现
 */
public class CfgDAO extends AbstractDAO implements JdbcRowMap {
    public static final String NAME              = "CfgDAO";

    /** 参数分类 */
    private String             catgFieldName     = "catg";

    /** 参数KEY */
    private String             nameFieldName     = "name";

    /** 参数说明 */
    private String             titleFieldName    = "title";

    /** 参数值 */
    private String             valueFieldName    = "value";

    /** 参数扩展值 */
    private String             valueExtFieldName = "value_ext";

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

        logger.warn("[系统参数]-数据表信息:{}({},{},{},{},{},{},{}).", //
            this.tableName, this.catgFieldName, this.nameFieldName, this.titleFieldName, //
            this.valueFieldName, this.valueExtFieldName, this.gmtCreateFieldName, this.gmtModifyFieldName);

        this.findTableFields();
        logger.warn("[系统参数]-TableFieds: {}", this.tableFields);
    }

    /**
     * 获取SELECT SQL
     */
    private String findTableFields() {
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
     * 插入系统参数
     */
    public void insert(final CfgDTO cfg) {
        // SQL
        if (this.insertSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(this.tableName).append("(");
            sql.append(this.findTableFields());
            sql.append(") VALUES(");
            sql.append("?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[系统参数]-InsertSQL: {}", this.insertSQL);
        }

        // 执行插入
        JdbcInsert.execute(this.dataSource, this.insertSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, cfg.getCatg());
                stmt.setString(2, cfg.getName());
                stmt.setString(3, cfg.getTitle());
                stmt.setString(4, cfg.getValue());
                stmt.setString(5, cfg.getValueExt());

                if (cfg.getGmtCreate() == null) {
                    stmt.setTimestamp(6, null);
                } else {
                    stmt.setTimestamp(6, new Timestamp(cfg.getGmtCreate().getTime()));
                }

                if (cfg.getGmtModify() == null) {
                    stmt.setTimestamp(7, null);
                } else {
                    stmt.setTimestamp(7, new Timestamp(cfg.getGmtModify().getTime()));
                }
            }
        });
    }

    /**
     * 更新系统参数
     */
    public int update(final CfgDTO cfg) {
        // SQL
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
            logger.warn("[系统参数]-UpdateSQL: {}", this.updateSQL);
        }

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, this.updateSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, cfg.getTitle());
                stmt.setString(2, cfg.getValue());
                stmt.setString(3, cfg.getValueExt());

                if (cfg.getGmtModify() == null) {
                    stmt.setTimestamp(4, null);
                } else {
                    stmt.setTimestamp(4, new Timestamp(cfg.getGmtModify().getTime()));
                }

                stmt.setString(5, cfg.getCatg());
                stmt.setString(6, cfg.getName());
            }
        });
    }

    /**
     * 查询系统参数
     */
    public List<CfgDTO> find() {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new DefaultJdbcStmtValue());
    }

    /**
     * 删除系统参数
     */
    public int delete() {
        // SQL
        String sql = "DELETE FROM " + this.tableName;

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new DefaultJdbcStmtValue());
    }

    /**
     * 根据分类删除系统参数
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
     * 根据分类+名称删除系统参数
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
    public List<CfgDTO> map(ResultSet rs) throws SQLException {
        List<CfgDTO> cfgs = new ArrayList<CfgDTO>();

        while (rs.next()) {
            CfgDTO cfg = new CfgDTO();

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

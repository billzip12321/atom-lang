/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

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

/**
 * 关系模型DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateDAO.java, V1.0.1 2014年1月29日 下午1:36:17 $
 */
public class RelateDAO extends AbstractDAO {
    public static final String NAME             = "RelateDAO";

    /** 用户编号 */
    private String             catgFieldName    = "catg";

    /** 用户昵称 */
    private String             srcNoFieldName   = "src_no";

    /** 权限代码 */
    private String             srcNameFieldName = "src_name";

    /** 权限名称 */
    private String             dstNoFieldName   = "dst_no";

    /** 权限名称 */
    private String             dstNameFieldName = "dst_name";

    /** 扩展参数 */
    private String             extMapFieldName  = "ext_map";

    /** 查询SQL */
    private String             tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.catgFieldName);
            sql.append(",").append(this.srcNoFieldName);
            sql.append(",").append(this.srcNameFieldName);
            sql.append(",").append(this.dstNoFieldName);
            sql.append(",").append(this.dstNameFieldName);
            sql.append(",").append(this.extMapFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /**
     * 插入关系模型
     */
    public void insert(final RelateDTO relate) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(this.tableName).append("(");
        sql.append(this.findTableFields());
        sql.append(") VALUES(");
        sql.append("?,?,?,?,?,?,?,?");
        sql.append(")");

        // 执行插入
        JdbcInsert.execute(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setString(++idx, relate.getCatg());
                stmt.setString(++idx, relate.getSrcNo());
                stmt.setString(++idx, relate.getSrcName());
                stmt.setString(++idx, relate.getDstNo());
                stmt.setString(++idx, relate.getDstName());
                stmt.setString(++idx, relate.getExtMap());

                Date create = relate.getGmtCreate();
                if (create == null) {
                    create = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(create.getTime()));

                Date modify = relate.getGmtModify();
                if (modify == null) {
                    modify = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(modify.getTime()));
            }
        });
    }

    /**
     * 更新关系模型
     */
    public int update(final RelateDTO relate) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName);
        sql.append(" SET ");
        sql.append(this.srcNameFieldName).append("=?");
        sql.append(",").append(this.dstNameFieldName).append("=?");
        sql.append(",").append(this.extMapFieldName).append("=?");
        sql.append(",").append(this.gmtModifyFieldName).append("=?");
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.srcNoFieldName).append("=?");
        sql.append(" AND ").append(this.dstNoFieldName).append("=?");

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setString(++idx, relate.getSrcName());
                stmt.setString(++idx, relate.getDstName());
                stmt.setString(++idx, relate.getExtMap());

                Date modify = relate.getGmtModify();
                if (modify == null) {
                    modify = new Date();
                }
                stmt.setTimestamp(++idx, new Timestamp(modify.getTime()));

                stmt.setString(++idx, relate.getCatg());
                stmt.setString(++idx, relate.getSrcNo());
                stmt.setString(++idx, relate.getDstNo());
            }
        });
    }

    /**
     * 根据分类删除记录
     */
    public int deleteByCatg(final String catg) {
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.catgFieldName + "=?";
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
            }
        });
    }

    /**
     * 根据分类+源编号删除记录
     */
    public int deleteBySrcCatg(final String catg, final String srcNo) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.srcNoFieldName).append("=?");

        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, srcNo);
            }
        });
    }

    /**
     * 根据分类+目标编号删除记录
     */
    public int deleteByDstCatg(final String catg, final String dstNo) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.dstNoFieldName).append("=?");

        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, dstNo);
            }
        });
    }

    /**
     * 根据源编号删除记录
     */
    public int deleteBySrcNo(final String srcNo) {
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.srcNoFieldName + "=?";
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, srcNo);
            }
        });
    }

    /**
     * 根据目标编号删除记录
     */
    public int deleteByDstNo(final String dstNo) {
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.dstNoFieldName + "=?";
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, dstNo);
            }
        });
    }

    /**
     * 根据分类+源编号+目标编号删除记录
     */
    public int deleteByUnique(final String catg, final String srcNo, final String dstNo) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.srcNoFieldName).append("=?");
        sql.append(" AND ").append(this.dstNoFieldName).append("=?");

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, srcNo);
                stmt.setString(3, dstNo);
            }
        });
    }

    /**
     * 根据分类查询关系模型
     */
    public List<RelateDTO> findByCatg(final String catg) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
            }
        });
    }

    /**
     * 根据分类+源编号查询关系模型
     */
    public List<RelateDTO> findBySrcCatg(final String catg, final String srcNo) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.srcNoFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, srcNo);
            }
        });
    }

    /**
     * 根据分类+目标编号查询关系模型
     */
    public List<RelateDTO> findByDstCatg(final String catg, final String dstNo) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.dstNoFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, dstNo);
            }
        });
    }

    /**
     * 根据分类+源编号+目标编号查询单条关系模型
     */
    public RelateDTO findByUnique(final String catg, final String srcNo, final String dstNo) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.catgFieldName).append("=?");
        sql.append(" AND ").append(this.srcNoFieldName).append("=?");
        sql.append(" AND ").append(this.dstNoFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, catg);
                stmt.setString(2, srcNo);
                stmt.setString(3, dstNo);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<RelateDTO> map(ResultSet rs) throws SQLException {
        List<RelateDTO> relates = new ArrayList<RelateDTO>();

        while (rs.next()) {
            RelateDTO relate = new RelateDTO();

            relate.setCatg(rs.getString(this.catgFieldName));
            relate.setSrcNo(rs.getString(this.srcNoFieldName));
            relate.setSrcName(rs.getString(this.srcNameFieldName));
            relate.setDstNo(rs.getString(this.dstNoFieldName));
            relate.setDstName(rs.getString(this.dstNameFieldName));
            relate.setExtMap(rs.getString(this.extMapFieldName));
            relate.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            relate.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            relates.add(relate);
        }

        return relates;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setSrcNoFieldName(String srcNoFieldName) {
        this.srcNoFieldName = srcNoFieldName;
    }

    public void setSrcNameFieldName(String srcNameFieldName) {
        this.srcNameFieldName = srcNameFieldName;
    }

    public void setDstNoFieldName(String dstNoFieldName) {
        this.dstNoFieldName = dstNoFieldName;
    }

    public void setDstNameFieldName(String dstNameFieldName) {
        this.dstNameFieldName = dstNameFieldName;
    }

    public void setExtMapFieldName(String extMapFieldName) {
        this.extMapFieldName = extMapFieldName;
    }

}

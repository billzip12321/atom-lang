/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.right;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;

/**
 * 用户权限DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightDAO.java, V1.0.1 2014年1月29日 下午1:36:17 $
 */
public class UserRightDAO extends AbstractDAO {
    public static final String NAME              = "UserRightDAO";

    /** 用户编号 */
    private String             userNoFieldName   = "user_no";

    /** 用户昵称 */
    private String             nickNameFieldName = "nick_name";

    /** 权限代码 */
    private String             rgtCodeFieldName  = "rgt_code";

    /** 权限名称 */
    private String             rgtNameFieldName  = "rgt_name";

    /** 扩展参数 */
    private String             extMapFieldName   = "ext_map";

    /** 查询SQL */
    private String             tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.userNoFieldName);
            sql.append(",").append(this.nickNameFieldName);
            sql.append(",").append(this.rgtCodeFieldName);
            sql.append(",").append(this.rgtNameFieldName);
            sql.append(",").append(this.extMapFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /**
     * 插入用户权限
     */
    public void insert(final UserRightDTO urgt) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(this.tableName).append("(");
        sql.append(this.findTableFields());
        sql.append(") VALUES(");
        sql.append("?,?,?,?,?,?,?");
        sql.append(")");

        // 执行插入
        JdbcInsert.execute(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, urgt.getUserNo());
                stmt.setString(2, urgt.getNickName());
                stmt.setString(3, urgt.getRgtCode());
                stmt.setString(4, urgt.getRgtName());
                stmt.setString(5, urgt.getExtMap());

                if (urgt.getGmtCreate() == null) {
                    stmt.setTimestamp(6, null);
                } else {
                    stmt.setTimestamp(6, new Timestamp(urgt.getGmtCreate().getTime()));
                }

                if (urgt.getGmtModify() == null) {
                    stmt.setTimestamp(7, null);
                } else {
                    stmt.setTimestamp(7, new Timestamp(urgt.getGmtModify().getTime()));
                }
            }
        });
    }

    /**
     * 更新用户权限
     */
    public int update(final UserRightDTO urgt) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName);
        sql.append(" SET ");
        sql.append(this.nickNameFieldName).append("=?");
        sql.append(",").append(this.rgtNameFieldName).append("=?");
        sql.append(",").append(this.extMapFieldName).append("=?");
        sql.append(",").append(this.gmtModifyFieldName).append("=?");
        sql.append(" WHERE ");
        sql.append(this.userNoFieldName).append("=?");
        sql.append(" AND ").append(this.rgtCodeFieldName).append("=?");

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, urgt.getNickName());
                stmt.setString(2, urgt.getRgtName());
                stmt.setString(3, urgt.getExtMap());

                if (urgt.getGmtModify() == null) {
                    stmt.setTimestamp(4, null);
                } else {
                    stmt.setTimestamp(4, new Timestamp(urgt.getGmtModify().getTime()));
                }

                stmt.setString(5, urgt.getUserNo());
                stmt.setString(6, urgt.getRgtCode());
            }
        });
    }

    /**
     * 根据用户编号删除记录
     */
    public int deleteByUserNo(final String userNo) {
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.userNoFieldName + "=?";
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, userNo);
            }
        });
    }

    /**
     * 根据权限代码删除记录
     */
    public int deleteByRgtCode(final String rgtCode) {
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.rgtCodeFieldName + "=?";
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, rgtCode);
            }
        });
    }

    /**
     * 根据用户编号+权限代码删除记录
     */
    public int deleteByUnique(final String userNo, final String rgtCode) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.userNoFieldName).append("=?");
        sql.append(" AND ").append(this.rgtCodeFieldName).append("=?");

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, userNo);
                stmt.setString(2, rgtCode);
            }
        });
    }

    /**
     * 根据用户编号查询数据模型
     */
    public List<UserRightDTO> findByUserNo(final String userNo) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.userNoFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, userNo);
            }
        });
    }

    /**
     * 根据权限代码查询数据模型
     */
    public List<UserRightDTO> findByRgtCode(final String rgtCode) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.rgtCodeFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, rgtCode);
            }
        });
    }

    /**
     * 根据用户编号+权限代码查询单条数据模型
     */
    public UserRightDTO findByUnique(final String userNo, final String rgtCode) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.userNoFieldName).append("=?");
        sql.append(" AND ").append(this.rgtCodeFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, userNo);
                stmt.setString(2, rgtCode);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<UserRightDTO> map(ResultSet rs) throws SQLException {
        List<UserRightDTO> urgts = new ArrayList<UserRightDTO>();

        while (rs.next()) {
            UserRightDTO urgt = new UserRightDTO();

            urgt.setUserNo(rs.getString(this.userNoFieldName));
            urgt.setNickName(rs.getString(this.nickNameFieldName));
            urgt.setRgtCode(rs.getString(this.rgtCodeFieldName));
            urgt.setRgtName(rs.getString(this.rgtNameFieldName));
            urgt.setExtMap(rs.getString(this.extMapFieldName));
            urgt.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            urgt.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            urgts.add(urgt);
        }

        return urgts;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setUserNoFieldName(String userNoFieldName) {
        this.userNoFieldName = userNoFieldName;
    }

    public void setNickNameFieldName(String nickNameFieldName) {
        this.nickNameFieldName = nickNameFieldName;
    }

    public void setRgtCodeFieldName(String rgtCodeFieldName) {
        this.rgtCodeFieldName = rgtCodeFieldName;
    }

    public void setRgtNameFieldName(String rgtNameFieldName) {
        this.rgtNameFieldName = rgtNameFieldName;
    }

    public void setExtMapFieldName(String extMapFieldName) {
        this.extMapFieldName = extMapFieldName;
    }

}

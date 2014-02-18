/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.msgbox.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.model.msgbox.MsgBoxCatgEnum;
import com.github.obullxl.model.msgbox.MsgBoxModel;
import com.github.obullxl.model.msgbox.dao.MsgBoxDAO;
import com.github.obullxl.model.user.UserKey;

/**
 * 消息信箱DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractMsgBoxDAO.java, V1.0.1 2014年2月10日 下午3:48:04 $
 */
public abstract class AbstractMsgBoxDAO extends AbstractDAO implements MsgBoxDAO {

    /** ID */
    protected String idFieldName           = "id";

    /** 分类 */
    protected String catgFieldName         = "catg";

    /** 查看状态 */
    protected String viewStateFieldName    = "view_state";

    /** 查看时间 */
    protected String gmtViewFieldName      = "gmt_view";

    /** 发送方编号 */
    protected String postUserNoFieldName   = "post_user_no";

    /** 发送方昵称 */
    protected String postNickNameFieldName = "post_nick_name";

    /** 接收方编号 */
    protected String takeUserNoFieldName   = "take_user_no";

    /** 接收方昵称 */
    protected String takeNickNameFieldName = "take_nick_name";

    /** 接收方列表 */
    protected String takeUsersFieldName    = "take_users";

    /** 消息标题 */
    protected String titleFieldName        = "title";

    /** 消息内容 */
    protected String contentFieldName      = "content";

    /** 插入SQL */
    private String   insertSQL;

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
            sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[消息邮箱]-InsertSQL: {}", this.insertSQL);
        }

        return this.insertSQL;
    }

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findUpdateSQL()
     */
    public String findUpdateSQL() {
        // throw new UnsupportedOperationException("[消息邮箱]");
        return StringUtils.EMPTY;
    }

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.idFieldName);
            sql.append(",").append(this.catgFieldName);
            sql.append(",").append(this.viewStateFieldName);
            sql.append(",").append(this.gmtViewFieldName);
            sql.append(",").append(this.postUserNoFieldName);
            sql.append(",").append(this.postNickNameFieldName);
            sql.append(",").append(this.takeUserNoFieldName);
            sql.append(",").append(this.takeNickNameFieldName);
            sql.append(",").append(this.takeUsersFieldName);
            sql.append(",").append(this.titleFieldName);
            sql.append(",").append(this.contentFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /**
     * 插入消息邮箱
     */
    public void insert(final MsgBoxModel msgBox) {
        // 执行插入
        JdbcInsert.execute(this.dataSource, this.findInsertSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setString(++idx, msgBox.getId());
                fillStmtEnumBaseValue(++idx, stmt, msgBox.getCatgEnum());
                fillStmtEnumBaseValue(++idx, stmt, msgBox.getViewStateEnum());
                stmt.setTimestamp(++idx, null);
                stmt.setString(++idx, msgBox.getPostUser().getNo());
                stmt.setString(++idx, msgBox.getPostUser().getNickName());
                stmt.setString(++idx, msgBox.getTakeUser().getNo());
                stmt.setString(++idx, msgBox.getTakeUser().getNickName());
                stmt.setString(++idx, msgBox.formatTakeUsers());
                stmt.setString(++idx, msgBox.getTitle());
                stmt.setString(++idx, msgBox.getContent());
                fillStmtTimestampValue(++idx, stmt, msgBox.getGmtCreate());
                fillStmtTimestampValue(++idx, stmt, msgBox.getGmtModify());
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
     * 根据ID删除记录
     */
    public int deleteByID(String id) {
        return super.deleteValues(this.idFieldName, id);
    }

    /**
     * 根据发送方删除记录
     */
    public int deleteByPostUserNo(String postUserNo) {
        return super.deleteValues(this.postUserNoFieldName, postUserNo);
    }

    /**
     * 根据接收方删除记录
     */
    public int deleteByTakeUserNo(String takeUserNo) {
        return super.deleteValues(this.takeUserNoFieldName, takeUserNo);
    }

    /**
     * 查询发件箱Top消息
     */
    public List<MsgBoxModel> findTopPostBoxMsgs(final String postUserNo, final int limit) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.postUserNoFieldName).append("=?");
        sql.append(" ORDER BY ").append(this.gmtCreateFieldName).append(" DESC");
        sql.append(" LIMIT 0,?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, postUserNo);
                stmt.setInt(2, limit);
            }
        });
    }

    /**
     * 查询收件箱Top消息
     */
    public List<MsgBoxModel> findTopTakeBoxMsgs(final String takeUserNo, final int limit) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.takeUserNoFieldName).append("=?");
        sql.append(" ORDER BY ").append(this.gmtCreateFieldName).append(" DESC");
        sql.append(" LIMIT 0,?");

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, takeUserNo);
                stmt.setInt(2, limit);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<MsgBoxModel> map(ResultSet rs) throws SQLException {
        List<MsgBoxModel> msgBoxs = new ArrayList<MsgBoxModel>();

        while (rs.next()) {
            MsgBoxModel msgBox = new MsgBoxModel();

            msgBox.setId(rs.getString(this.idFieldName));
            msgBox.setCatgEnum(MsgBoxCatgEnum.findByCode(rs.getString(this.catgFieldName)));
            msgBox.setViewStateEnum(ValveBoolEnum.findDefault(rs.getString(this.viewStateFieldName)));
            msgBox.setGmtView(rs.getTimestamp(this.gmtViewFieldName));
            msgBox.setPostUser(UserKey.to(rs.getString(this.postUserNoFieldName), rs.getString(this.postNickNameFieldName)));
            msgBox.setTakeUser(UserKey.to(rs.getString(this.takeUserNoFieldName), rs.getString(this.takeNickNameFieldName)));
            msgBox.setTakeUsers(MsgBoxModel.parseTakeUsers(rs.getString(this.takeUsersFieldName)));
            msgBox.setTitle(rs.getString(this.titleFieldName));
            msgBox.setContent(rs.getString(this.contentFieldName));
            msgBox.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            msgBox.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            msgBoxs.add(msgBox);
        }

        return msgBoxs;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setViewStateFieldName(String viewStateFieldName) {
        this.viewStateFieldName = viewStateFieldName;
    }

    public void setGmtViewFieldName(String gmtViewFieldName) {
        this.gmtViewFieldName = gmtViewFieldName;
    }

    public void setPostUserNoFieldName(String postUserNoFieldName) {
        this.postUserNoFieldName = postUserNoFieldName;
    }

    public void setPostNickNameFieldName(String postNickNameFieldName) {
        this.postNickNameFieldName = postNickNameFieldName;
    }

    public void setTakeUserNoFieldName(String takeUserNoFieldName) {
        this.takeUserNoFieldName = takeUserNoFieldName;
    }

    public void setTakeNickNameFieldName(String takeNickNameFieldName) {
        this.takeNickNameFieldName = takeNickNameFieldName;
    }

    public void setTakeUsersFieldName(String takeUsersFieldName) {
        this.takeUsersFieldName = takeUsersFieldName;
    }

    public void setTitleFieldName(String titleFieldName) {
        this.titleFieldName = titleFieldName;
    }

    public void setContentFieldName(String contentFieldName) {
        this.contentFieldName = contentFieldName;
    }

}

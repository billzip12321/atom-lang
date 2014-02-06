/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic.dao.impl;

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
import com.github.obullxl.lang.das.sql.OP;
import com.github.obullxl.lang.das.sql.SQLBuilder;
import com.github.obullxl.lang.das.sql.SQLContext;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.TopicModelEnum;
import com.github.obullxl.model.topic.dao.TopicDAO;
import com.github.obullxl.model.topic.enums.TopicMediaEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;
import com.github.obullxl.model.topic.enums.TopicTopEnum;
import com.github.obullxl.model.topic.query.TopicQueryDAS;

/**
 * 主题模型DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicDAO.java, V1.0.1 2014年1月28日 下午4:23:32 $
 */
public abstract class AbstractTopicDAO extends AbstractDAO implements TopicDAO {
    public static final String NAME                   = "TopicDAO";

    /** ID */
    protected String           idFieldName            = "id";

    /** 模型 */
    protected String           modelFieldName         = "model";

    /** 状态标志 */
    protected String           stateFieldName         = "state";

    /** 置顶标志 */
    protected String           topFieldName           = "top";

    /** 加精标志 */
    protected String           eliteFieldName         = "elite";

    /** 原创标志 */
    protected String           originalFieldName      = "original";

    /** 多媒体标志 */
    protected String           mediaFieldName         = "media";

    /** 允许评论标志 */
    protected String           replyFieldName         = "reply";

    /** 分类代码 */
    protected String           catgFieldName          = "catg";

    /** 排序值 */
    protected String           sortFieldName          = "sort";

    /** 原主题ID */
    protected String           topicFieldName         = "topic";

    /** 引用URL */
    protected String           linkUrlFieldName       = "link_url";

    /** 多媒体URL */
    protected String           mediaUrlFieldName      = "media_url";

    /** 发布者No */
    protected String           postUserNoFieldName    = "post_user_no";

    /** 发布者昵称 */
    protected String           postNickNameFieldName  = "post_nick_name";

    /** 发布时间 */
    protected String           gmtPostFieldName       = "gmt_post";

    /** 查看数量 */
    protected String           visitCountFieldName    = "visit_count";

    /** 跟帖数量 */
    protected String           replyCountFieldName    = "reply_count";

    /** 跟帖者No */
    protected String           replyUserNoFieldName   = "reply_user_no";

    /** 跟帖者昵称 */
    protected String           replyNickNameFieldName = "reply_nick_name";

    /** 发布时间 */
    protected String           gmtReplyFieldName      = "gmt_reply";

    /** 扩展参数 */
    protected String           extMapFieldName        = "ext_map";

    /** 标题样式 */
    protected String           titleStyleFieldName    = "title_style";

    /** 标题 */
    protected String           titleFieldName         = "title";

    /** 摘要 */
    protected String           summaryFieldName       = "summary";

    /** 内容 */
    protected String           contentFieldName       = "content";

    /** 插入SQL */
    private String             insertSQL;

    /** 更新SQL */
    private String             updateSQL;

    /** 数据表字段 */
    private String             tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findInsertSQL()
     */
    public String findInsertSQL() {
        if (this.insertSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(this.tableName).append("(");
            sql.append(this.findTableFields());
            sql.append(") VALUES(");
            sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[主题模型]-InsertSQL: {}", this.insertSQL);
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
            sql.append(this.idFieldName).append("=?");
            sql.append(",").append(this.modelFieldName).append("=?");
            sql.append(",").append(this.stateFieldName).append("=?");
            sql.append(",").append(this.topFieldName).append("=?");
            sql.append(",").append(this.eliteFieldName).append("=?");
            sql.append(",").append(this.originalFieldName).append("=?");
            sql.append(",").append(this.mediaFieldName).append("=?");
            sql.append(",").append(this.replyFieldName).append("=?");
            sql.append(",").append(this.catgFieldName).append("=?");
            sql.append(",").append(this.sortFieldName).append("=?");
            sql.append(",").append(this.topicFieldName).append("=?");
            sql.append(",").append(this.linkUrlFieldName).append("=?");
            sql.append(",").append(this.mediaUrlFieldName).append("=?");
            sql.append(",").append(this.postUserNoFieldName).append("=?");
            sql.append(",").append(this.postNickNameFieldName).append("=?");
            sql.append(",").append(this.gmtPostFieldName).append("=?");
            sql.append(",").append(this.visitCountFieldName).append("=?");
            sql.append(",").append(this.replyCountFieldName).append("=?");
            sql.append(",").append(this.replyUserNoFieldName).append("=?");
            sql.append(",").append(this.replyNickNameFieldName).append("=?");
            sql.append(",").append(this.gmtReplyFieldName).append("=?");
            sql.append(",").append(this.extMapFieldName).append("=?");
            sql.append(",").append(this.titleStyleFieldName).append("=?");
            sql.append(",").append(this.titleFieldName).append("=?");
            sql.append(",").append(this.summaryFieldName).append("=?");
            sql.append(",").append(this.contentFieldName).append("=?");
            sql.append(",").append(this.gmtModifyFieldName).append("=?");
            sql.append(" WHERE ");
            sql.append(this.idFieldName).append("=?");

            this.updateSQL = sql.toString();
            logger.warn("[主题模型]-UpdateSQL: {}", this.updateSQL);
        }

        return this.updateSQL;
    }

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.idFieldName);
            sql.append(",").append(this.modelFieldName);
            sql.append(",").append(this.stateFieldName);
            sql.append(",").append(this.topFieldName);
            sql.append(",").append(this.eliteFieldName);
            sql.append(",").append(this.originalFieldName);
            sql.append(",").append(this.mediaFieldName);
            sql.append(",").append(this.replyFieldName);
            sql.append(",").append(this.catgFieldName);
            sql.append(",").append(this.sortFieldName);
            sql.append(",").append(this.topicFieldName);
            sql.append(",").append(this.linkUrlFieldName);
            sql.append(",").append(this.mediaUrlFieldName);
            sql.append(",").append(this.postUserNoFieldName);
            sql.append(",").append(this.postNickNameFieldName);
            sql.append(",").append(this.gmtPostFieldName);
            sql.append(",").append(this.visitCountFieldName);
            sql.append(",").append(this.replyCountFieldName);
            sql.append(",").append(this.replyUserNoFieldName);
            sql.append(",").append(this.replyNickNameFieldName);
            sql.append(",").append(this.gmtReplyFieldName);
            sql.append(",").append(this.extMapFieldName);
            sql.append(",").append(this.titleStyleFieldName);
            sql.append(",").append(this.titleFieldName);
            sql.append(",").append(this.summaryFieldName);
            sql.append(",").append(this.contentFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /**
     * 填充字段值
     */
    private int fillStatmentValue(PreparedStatement stmt, TopicModel topic) throws SQLException {
        int idx = 0;

        stmt.setString(++idx, topic.getId());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getModelEnum());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getStateEnum());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getTopEnum());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getEliteEnum());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getOriginalEnum());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getMediaEnum());
        this.fillStmtEnumBaseValue(++idx, stmt, topic.getReplyEnum());
        stmt.setString(++idx, topic.getCatg());
        stmt.setString(++idx, topic.getSort());
        stmt.setString(++idx, topic.getTopic());
        stmt.setString(++idx, topic.getLinkUrl());
        stmt.setString(++idx, topic.getMediaUrl());
        stmt.setString(++idx, topic.getPostUserNo());
        stmt.setString(++idx, topic.getPostNickName());
        this.fillStmtTimestampValue(++idx, stmt, topic.getGmtPost());
        stmt.setInt(++idx, topic.getVisitCount());
        stmt.setInt(++idx, topic.getReplyCount());
        stmt.setString(++idx, topic.getReplyUserNo());
        stmt.setString(++idx, topic.getReplyNickName());
        this.fillStmtTimestampValue(++idx, stmt, topic.getGmtReply());
        stmt.setString(++idx, topic.getExtMap());
        stmt.setString(++idx, topic.getTitleStyle());
        stmt.setString(++idx, topic.getTitle());
        stmt.setString(++idx, topic.getSummary());
        stmt.setString(++idx, topic.getContent());

        return idx;
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#insert(com.github.obullxl.model.topic.TopicModel)
     */
    public void insert(final TopicModel topic) {
        // 执行插入
        JdbcInsert.execute(this.dataSource, this.findInsertSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = fillStatmentValue(stmt, topic);
                fillStmtTimestampValue(++idx, stmt, topic.getGmtCreate());
                fillStmtTimestampValue(++idx, stmt, topic.getGmtModify());
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#update(com.github.obullxl.model.topic.TopicModel)
     */
    public int update(final TopicModel topic) {
        return JdbcUpdate.executeUpdate(this.dataSource, this.findUpdateSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = fillStatmentValue(stmt, topic);
                fillStmtTimestampValue(++idx, stmt, topic.getGmtModify());

                // 条件
                stmt.setString(++idx, topic.getId());
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deltaVisitCount(java.lang.String, int)
     */
    public int deltaVisitCount(final String id, final int deta) {
        if (deta == 0) {
            return 0;
        }

        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName).append(" SET ");
        sql.append(this.visitCountFieldName).append("=").append(this.visitCountFieldName);

        if (deta > 0) {
            sql.append("+?");
        } else {
            sql.append("-?");
        }

        sql.append(" WHERE ").append(this.idFieldName).append("=?");

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setInt(1, Math.abs(deta));
                stmt.setString(2, id);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deltaReplyCount(java.lang.String, int)
     */
    public int deltaReplyCount(final String id, final int deta) {
        if (deta == 0) {
            return 0;
        }

        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName).append(" SET ");
        sql.append(this.replyCountFieldName).append("=").append(this.replyCountFieldName);

        if (deta > 0) {
            sql.append("+?");
        } else {
            sql.append("-?");
        }

        sql.append(" WHERE ").append(this.idFieldName).append("=?");

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setInt(1, Math.abs(deta));
                stmt.setString(2, id);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deleteByID(java.lang.String)
     */
    public int deleteByID(String id) {
        return this.deleteValues(this.idFieldName, id);
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deleteByTopicID(java.lang.String)
     */
    public int deleteByTopicID(String topicId) {
        return this.deleteValues(this.topicFieldName, topicId);
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deleteByUserNo(java.lang.String)
     */
    public int deleteByUserNo(String userNo) {
        return this.deleteValues(new String[] { this.postUserNoFieldName, this.replyUserNoFieldName }, new String[] { userNo, userNo });
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deleteByPostUserNo(java.lang.String)
     */
    public int deleteByPostUserNo(String postUserNo) {
        return this.deleteValues(this.postUserNoFieldName, postUserNo);
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#deleteByReplyUserNo(java.lang.String)
     */
    public int deleteByReplyUserNo(String replyUserNo) {
        return this.deleteValues(this.replyUserNoFieldName, replyUserNo);
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#selectByID(java.lang.String)
     */
    public TopicModel selectByID(final String id) {
        return this.selectValue(this.idFieldName, id);
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#count(com.github.obullxl.model.topic.query.TopicQueryDAS)
     */
    public int count(TopicQueryDAS query) {
        final SQLContext ctxt = this.buildWhere(query);
        String where = ctxt.whereSQL();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) AS count FROM ").append(this.tableName);

        if (StringUtils.isNotBlank(where)) {
            sql.append(" WHERE ").append(where);
        }

        return JdbcSelect.count(this.dataSource, sql.toString(), "count", new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                ctxt.stmtValue(stmt);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.topic.dao.TopicDAO#selectPage(com.github.obullxl.model.topic.query.TopicQueryDAS)
     */
    public List<TopicModel> selectPage(TopicQueryDAS query) {
        final SQLContext ctxt = this.buildWhere(query);
        String where = ctxt.whereSQL();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);

        if (StringUtils.isNotBlank(where)) {
            sql.append(" WHERE ").append(where);
        }

        if (StringUtils.isNotBlank(query.getOrderbyField())) {
            sql.append(" ORDER BY ").append(query.getOrderbyField());

            if (StringUtils.isNotBlank(query.getOrderbyType())) {
                sql.append(" ").append(query.getOrderbyType());
            }
        }

        sql.append(" LIMIT ").append(query.getOffset()).append(",").append(query.getPageSize());

        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                ctxt.stmtValue(stmt);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<TopicModel> map(ResultSet rs) throws SQLException {
        List<TopicModel> topics = new ArrayList<TopicModel>();

        while (rs.next()) {
            TopicModel topic = new TopicModel();

            topic.setId(rs.getString(this.idFieldName));
            topic.setModelEnum(TopicModelEnum.findDefault(rs.getString(this.modelFieldName)));
            topic.setStateEnum(TopicStateEnum.findDefault(rs.getString(this.stateFieldName)));
            topic.setTopEnum(TopicTopEnum.findDefault(rs.getString(this.topFieldName)));
            topic.setEliteEnum(ValveBoolEnum.findDefault(rs.getString(this.eliteFieldName)));
            topic.setOriginalEnum(ValveBoolEnum.findDefault(rs.getString(this.originalFieldName)));
            topic.setMediaEnum(TopicMediaEnum.findDefault(rs.getString(this.mediaFieldName)));
            topic.setReplyEnum(ValveBoolEnum.findDefault(rs.getString(this.replyFieldName)));
            topic.setCatg(rs.getString(this.catgFieldName));
            topic.setSort(rs.getString(this.sortFieldName));
            topic.setTopic(rs.getString(this.topicFieldName));
            topic.setLinkUrl(rs.getString(this.linkUrlFieldName));
            topic.setMediaUrl(rs.getString(this.mediaUrlFieldName));
            topic.setPostUserNo(rs.getString(this.postUserNoFieldName));
            topic.setPostNickName(rs.getString(this.postNickNameFieldName));
            topic.setGmtPost(rs.getTimestamp(this.gmtPostFieldName));
            topic.setVisitCount(rs.getInt(this.visitCountFieldName));
            topic.setReplyCount(rs.getInt(this.replyCountFieldName));
            topic.setReplyUserNo(rs.getString(this.replyUserNoFieldName));
            topic.setReplyNickName(rs.getString(this.replyNickNameFieldName));
            topic.setGmtReply(rs.getTimestamp(this.gmtReplyFieldName));
            topic.setExtMap(rs.getString(this.extMapFieldName));
            topic.setTitleStyle(rs.getString(this.titleStyleFieldName));
            topic.setTitle(rs.getString(this.titleFieldName));
            topic.setSummary(rs.getString(this.summaryFieldName));
            topic.setContent(rs.getString(this.contentFieldName));
            topic.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            topic.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            topics.add(topic);
        }

        return topics;
    }

    /**
     * 构建Where条件
     */
    public SQLContext buildWhere(TopicQueryDAS query) {
        SQLBuilder sql = SQLBuilder.newBuilder();

        sql.where(this.idFieldName, query.getId());
        sql.where(this.modelFieldName, query.getModel());
        sql.where(this.stateFieldName, query.getState());
        sql.where(this.topFieldName, query.getTop());
        sql.where(this.eliteFieldName, query.getElite());
        sql.where(this.originalFieldName, query.getOriginal());
        sql.where(this.mediaFieldName, query.getMedia());
        sql.where(this.replyFieldName, query.getReply());
        sql.where(OP.IN, this.catgFieldName, query.getCatgs());
        sql.where(this.topicFieldName, query.getTopicId());
        sql.where(this.postUserNoFieldName, query.getPostUserNo());
        sql.where(this.gmtPostFieldName, query.getGmtPostBegin(), query.getGmtPostFinish());
        sql.where(this.replyUserNoFieldName, query.getReplyUserNo());
        sql.where(this.gmtReplyFieldName, query.getGmtReplyBegin(), query.getGmtReplyFinish());
        sql.where(OP.LK, this.extMapFieldName, query.getExtMap());
        sql.where(OP.LK, this.titleFieldName, query.getTitle());
        sql.where(OP.LK, this.summaryFieldName, query.getSummary());
        sql.where(OP.LK, this.contentFieldName, query.getContent());

        return sql.finishBuilder();
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public void setModelFieldName(String modelFieldName) {
        this.modelFieldName = modelFieldName;
    }

    public void setStateFieldName(String stateFieldName) {
        this.stateFieldName = stateFieldName;
    }

    public void setTopFieldName(String topFieldName) {
        this.topFieldName = topFieldName;
    }

    public void setEliteFieldName(String eliteFieldName) {
        this.eliteFieldName = eliteFieldName;
    }

    public void setOriginalFieldName(String originalFieldName) {
        this.originalFieldName = originalFieldName;
    }

    public void setMediaFieldName(String mediaFieldName) {
        this.mediaFieldName = mediaFieldName;
    }

    public void setReplyFieldName(String replyFieldName) {
        this.replyFieldName = replyFieldName;
    }

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public void setTopicFieldName(String topicFieldName) {
        this.topicFieldName = topicFieldName;
    }

    public void setLinkUrlFieldName(String linkUrlFieldName) {
        this.linkUrlFieldName = linkUrlFieldName;
    }

    public void setMediaUrlFieldName(String mediaUrlFieldName) {
        this.mediaUrlFieldName = mediaUrlFieldName;
    }

    public void setPostUserNoFieldName(String postUserNoFieldName) {
        this.postUserNoFieldName = postUserNoFieldName;
    }

    public void setPostNickNameFieldName(String postNickNameFieldName) {
        this.postNickNameFieldName = postNickNameFieldName;
    }

    public void setGmtPostFieldName(String gmtPostFieldName) {
        this.gmtPostFieldName = gmtPostFieldName;
    }

    public void setVisitCountFieldName(String visitCountFieldName) {
        this.visitCountFieldName = visitCountFieldName;
    }

    public void setReplyCountFieldName(String replyCountFieldName) {
        this.replyCountFieldName = replyCountFieldName;
    }

    public void setReplyUserNoFieldName(String replyUserNoFieldName) {
        this.replyUserNoFieldName = replyUserNoFieldName;
    }

    public void setReplyNickNameFieldName(String replyNickNameFieldName) {
        this.replyNickNameFieldName = replyNickNameFieldName;
    }

    public void setGmtReplyFieldName(String gmtReplyFieldName) {
        this.gmtReplyFieldName = gmtReplyFieldName;
    }

    public void setExtMapFieldName(String extMapFieldName) {
        this.extMapFieldName = extMapFieldName;
    }

    public void setTitleStyleFieldName(String titleStyleFieldName) {
        this.titleStyleFieldName = titleStyleFieldName;
    }

    public void setTitleFieldName(String titleFieldName) {
        this.titleFieldName = titleFieldName;
    }

    public void setSummaryFieldName(String summaryFieldName) {
        this.summaryFieldName = summaryFieldName;
    }

    public void setContentFieldName(String contentFieldName) {
        this.contentFieldName = contentFieldName;
    }

}

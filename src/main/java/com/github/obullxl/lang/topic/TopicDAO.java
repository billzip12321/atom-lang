/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.topic.TopicDTO;
import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcUpdate;
import com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap;
import com.github.obullxl.lang.das.JdbcStmtValue.DefaultJdbcStmtValue;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.enums.TopicTypeEnum;

/**
 * 主题模型DAO默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicDAO.java, V1.0.1 2014年1月28日 下午4:23:32 $
 */
public class TopicDAO extends AbstractDAO implements JdbcRowMap {
    public static final String NAME                   = "TopicDAO";

    /** ID */
    private String             idFieldName            = "id";

    /** 位标志 */
    private String             flagFieldName          = "flag";

    /** 分类代码 */
    private String             catgFieldName          = "catg";

    /** 主题分类 */
    private String             typeFieldName          = "type";

    /** 原主题ID */
    private String             topicFieldName         = "topic";

    /** 发布者No */
    private String             postUserNoFieldName    = "post_user_no";

    /** 发布者昵称 */
    private String             postNickNameFieldName  = "post_nick_name";

    /** 引用URL */
    private String             linkUrlFieldName       = "link_url";

    /** 多媒体URL */
    private String             mediaUrlFieldName      = "media_url";

    /** 发布时间 */
    private String             gmtPostFieldName       = "gmt_post";

    /** 查看数量 */
    private String             visitCountFieldName    = "visit_count";

    /** 跟帖数量 */
    private String             replyCountFieldName    = "reply_count";

    /** 跟帖者No */
    private String             replyUserNoFieldName   = "reply_user_no";

    /** 跟帖者昵称 */
    private String             replyNickNameFieldName = "reply_nick_name";

    /** 发布时间 */
    private String             gmtReplyFieldName      = "gmt_reply";

    /** 扩展参数 */
    private String             extMapFieldName        = "ext_map";

    /** 标题样式 */
    private String             titleStyleFieldName    = "title_style";

    /** 标题 */
    private String             titleFieldName         = "title";

    /** 摘要 */
    private String             summaryFieldName       = "summary";

    /** 内容 */
    private String             contentFieldName       = "content";

    /** 插入SQL */
    private String             insertSQL;

    /** 更新SQL */
    private String             updateSQL;

    /** 数据表字段 */
    private String             tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#init()
     */
    public void init() {
        logger.warn("[主题模型]-数据表信息:{}({},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}).", //
            this.tableName, this.idFieldName, this.flagFieldName, this.catgFieldName, this.typeFieldName, //
            this.topicFieldName, this.postUserNoFieldName, this.postNickNameFieldName, this.linkUrlFieldName, //
            this.mediaUrlFieldName, this.gmtPostFieldName, this.visitCountFieldName, this.replyCountFieldName, //
            this.replyUserNoFieldName, this.replyNickNameFieldName, this.gmtReplyFieldName, this.extMapFieldName, //
            this.titleStyleFieldName, this.titleFieldName, this.summaryFieldName, this.contentFieldName, //
            this.gmtCreateFieldName, this.gmtModifyFieldName);

        this.findTableFields();
        logger.warn("[主题模型]-TableFieds: {}", this.tableFields);
    }

    /**
     * 获取SELECT SQL
     */
    private String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.idFieldName);
            sql.append(",").append(this.flagFieldName);
            sql.append(",").append(this.catgFieldName);
            sql.append(",").append(this.typeFieldName);
            sql.append(",").append(this.topicFieldName);
            sql.append(",").append(this.postUserNoFieldName);
            sql.append(",").append(this.postNickNameFieldName);
            sql.append(",").append(this.linkUrlFieldName);
            sql.append(",").append(this.mediaUrlFieldName);
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
     * 插入主题模型
     */
    public void insert(final TopicDTO topic) {
        // SQL
        if (this.insertSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(this.tableName).append("(");
            sql.append(this.findTableFields());
            sql.append(") VALUES(");
            sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[主题模型]-InsertSQL: {}", this.insertSQL);
        }

        // 执行插入
        JdbcInsert.execute(this.dataSource, this.insertSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, topic.getId());
                stmt.setString(2, topic.findValve().getValve());
                stmt.setString(3, topic.getCatg());

                if (topic.getTypeEnum() != null) {
                    stmt.setString(4, topic.getTypeEnum().code());
                } else {
                    stmt.setString(4, StringUtils.EMPTY);
                }

                stmt.setString(5, topic.getTopic());
                stmt.setString(6, topic.getPostUserNo());
                stmt.setString(7, topic.getPostNickName());
                stmt.setString(8, topic.getLinkUrl());
                stmt.setString(9, topic.getMediaUrl());

                if (topic.getGmtPost() == null) {
                    stmt.setTimestamp(10, null);
                } else {
                    stmt.setTimestamp(10, new Timestamp(topic.getGmtPost().getTime()));
                }

                stmt.setInt(11, topic.getVisitCount());
                stmt.setInt(12, topic.getReplyCount());
                stmt.setString(13, topic.getReplyUserNo());
                stmt.setString(14, topic.getReplyNickName());

                if (topic.getGmtReply() == null) {
                    stmt.setTimestamp(15, null);
                } else {
                    stmt.setTimestamp(15, new Timestamp(topic.getGmtReply().getTime()));
                }

                stmt.setString(16, topic.getExtMap());
                stmt.setString(17, topic.getTitleStyle());
                stmt.setString(18, topic.getTitle());
                stmt.setString(19, topic.getSummary());
                stmt.setString(20, topic.getContent());

                if (topic.getGmtCreate() == null) {
                    stmt.setTimestamp(21, null);
                } else {
                    stmt.setTimestamp(21, new Timestamp(topic.getGmtCreate().getTime()));
                }

                if (topic.getGmtModify() == null) {
                    stmt.setTimestamp(22, null);
                } else {
                    stmt.setTimestamp(22, new Timestamp(topic.getGmtModify().getTime()));
                }
            }
        });
    }

    /**
     * 更新主题模型
     */
    public int update(final TopicDTO topic) {
        // SQL
        if (this.updateSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(this.tableName);
            sql.append(" SET ");
            sql.append(this.flagFieldName).append("=?");
            sql.append(",").append(this.catgFieldName).append("=?");
            sql.append(",").append(this.typeFieldName).append("=?");
            sql.append(",").append(this.topicFieldName).append("=?");
            sql.append(",").append(this.postUserNoFieldName).append("=?");
            sql.append(",").append(this.postNickNameFieldName).append("=?");
            sql.append(",").append(this.linkUrlFieldName).append("=?");
            sql.append(",").append(this.mediaUrlFieldName).append("=?");
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

        // 执行更新
        return JdbcUpdate.executeUpdate(this.dataSource, this.updateSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, topic.findValve().getValve());
                stmt.setString(2, topic.getCatg());

                if (topic.getTypeEnum() != null) {
                    stmt.setString(3, topic.getTypeEnum().code());
                } else {
                    stmt.setString(3, StringUtils.EMPTY);
                }

                stmt.setString(4, topic.getTopic());
                stmt.setString(5, topic.getPostUserNo());
                stmt.setString(6, topic.getPostNickName());
                stmt.setString(7, topic.getLinkUrl());
                stmt.setString(8, topic.getMediaUrl());

                if (topic.getGmtPost() == null) {
                    stmt.setTimestamp(9, null);
                } else {
                    stmt.setTimestamp(9, new Timestamp(topic.getGmtPost().getTime()));
                }

                stmt.setInt(10, topic.getVisitCount());
                stmt.setInt(11, topic.getReplyCount());
                stmt.setString(12, topic.getReplyUserNo());
                stmt.setString(13, topic.getReplyNickName());

                if (topic.getGmtReply() == null) {
                    stmt.setTimestamp(14, null);
                } else {
                    stmt.setTimestamp(14, new Timestamp(topic.getGmtReply().getTime()));
                }

                stmt.setString(15, topic.getExtMap());
                stmt.setString(16, topic.getTitleStyle());
                stmt.setString(17, topic.getTitle());
                stmt.setString(18, topic.getSummary());
                stmt.setString(19, topic.getContent());

                if (topic.getGmtModify() == null) {
                    stmt.setTimestamp(20, null);
                } else {
                    stmt.setTimestamp(20, new Timestamp(topic.getGmtModify().getTime()));
                }

                // 条件
                stmt.setString(21, topic.getId());
            }
        });
    }

    /**
     * 增加/减少主题浏览次数
     */
    public int detaVisitCount(final String id, final int deta) {
        if(deta == 0) {
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
     * 增加/减少主题跟帖次数
     */
    public int detaReplyCount(final String id, final int deta) {
        if(deta == 0) {
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
     * 查询所有主题模型
     */
    public List<TopicDTO> find() {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);

        // 执行查询
        return JdbcSelect.selectList(this.dataSource, sql.toString(), this, new DefaultJdbcStmtValue());
    }

    /**
     * 删除所有主题模型
     */
    public int delete() {
        // SQL
        String sql = "DELETE FROM " + this.tableName;

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new DefaultJdbcStmtValue());
    }

    /**
     * 根据ID删除主题模型
     */
    public int delete(final String id) {
        // SQL
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.idFieldName + "=?";

        // 执行删除
        return JdbcUpdate.executeUpdate(this.dataSource, sql, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, id);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<TopicDTO> map(ResultSet rs) throws SQLException {
        List<TopicDTO> topics = new ArrayList<TopicDTO>();

        while (rs.next()) {
            TopicDTO topic = new TopicDTO();

            topic.setId(rs.getString(this.idFieldName));
            topic.setFlag(rs.getString(this.flagFieldName));
            topic.setCatg(rs.getString(this.catgFieldName));
            topic.setTypeEnum(TopicTypeEnum.findDefault(rs.getString(this.typeFieldName)));
            topic.setTopic(rs.getString(this.topicFieldName));
            topic.setPostUserNo(rs.getString(this.postUserNoFieldName));
            topic.setPostNickName(rs.getString(this.postNickNameFieldName));
            topic.setLinkUrl(rs.getString(this.linkUrlFieldName));
            topic.setMediaUrl(rs.getString(this.mediaUrlFieldName));
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

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public void setFlagFieldName(String flagFieldName) {
        this.flagFieldName = flagFieldName;
    }

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setTypeFieldName(String typeFieldName) {
        this.typeFieldName = typeFieldName;
    }

    public void setTopicFieldName(String topicFieldName) {
        this.topicFieldName = topicFieldName;
    }

    public void setPostUserNoFieldName(String postUserNoFieldName) {
        this.postUserNoFieldName = postUserNoFieldName;
    }

    public void setPostNickNameFieldName(String postNickNameFieldName) {
        this.postNickNameFieldName = postNickNameFieldName;
    }

    public void setLinkUrlFieldName(String linkUrlFieldName) {
        this.linkUrlFieldName = linkUrlFieldName;
    }

    public void setMediaUrlFieldName(String mediaUrlFieldName) {
        this.mediaUrlFieldName = mediaUrlFieldName;
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

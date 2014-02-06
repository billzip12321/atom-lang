/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.model.topic.query;

import java.util.Date;
import java.util.List;

import com.github.obullxl.lang.ToString;

/**
 * 主题数据库查询对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicQuery.java, V1.0.1 2013年12月28日 下午12:43:30 $
 */
public class TopicQueryDAS extends ToString {
    private static final long serialVersionUID = 798651143541130345L;

    /** 分页偏移量 */
    private int               offset;

    /** 分页单页大小 */
    private int               pageSize;

    /** 排序字段 */
    private String            orderbyField;

    /** 排序方式 */
    private String            orderbyType;

    /** ID-精确查询 */
    private String            id;

    /** 模型 */
    private String            model;

    /** 状态 */
    private String            state;

    /** 全局置顶 */
    private String            top;

    /** 加精标志 */
    private String            elite;

    /** 原创标志 */
    private String            original;

    /** 多媒体标志 */
    private String            media;

    /** 评论标志 */
    private String            reply;

    /** 分类代码列表 */
    private List<String>      catgs;

    /** 原主题ID */
    private String            topicId;

    /** 发布者No */
    private String            postUserNo;

    /** 发布开始时间(包含) */
    private Date              gmtPostBegin;

    /** 发布开始时间(不包含) */
    private Date              gmtPostFinish;

    /** 跟帖者No */
    private String            replyUserNo;

    /** 跟帖开始时间(包含) */
    private Date              gmtReplyBegin;

    /** 跟帖结束时间(不包含) */
    private Date              gmtReplyFinish;

    /** 扩展参数LIKE */
    private String            extMap;

    /** 标题LIKE */
    private String            title;

    /** 摘要LIKE */
    private String            summary;

    /** 内容KIKE(无效) */
    private String            content;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderbyField() {
        return orderbyField;
    }

    public void setOrderbyField(String orderbyField) {
        this.orderbyField = orderbyField;
    }

    public String getOrderbyType() {
        return orderbyType;
    }

    public void setOrderbyType(String orderbyType) {
        this.orderbyType = orderbyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getElite() {
        return elite;
    }

    public void setElite(String elite) {
        this.elite = elite;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<String> getCatgs() {
        return catgs;
    }

    public void setCatgs(List<String> catgs) {
        this.catgs = catgs;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPostUserNo() {
        return postUserNo;
    }

    public void setPostUserNo(String postUserNo) {
        this.postUserNo = postUserNo;
    }

    public Date getGmtPostBegin() {
        return gmtPostBegin;
    }

    public void setGmtPostBegin(Date gmtPostBegin) {
        this.gmtPostBegin = gmtPostBegin;
    }

    public Date getGmtPostFinish() {
        return gmtPostFinish;
    }

    public void setGmtPostFinish(Date gmtPostFinish) {
        this.gmtPostFinish = gmtPostFinish;
    }

    public String getReplyUserNo() {
        return replyUserNo;
    }

    public void setReplyUserNo(String replyUserNo) {
        this.replyUserNo = replyUserNo;
    }

    public Date getGmtReplyBegin() {
        return gmtReplyBegin;
    }

    public void setGmtReplyBegin(Date gmtReplyBegin) {
        this.gmtReplyBegin = gmtReplyBegin;
    }

    public Date getGmtReplyFinish() {
        return gmtReplyFinish;
    }

    public void setGmtReplyFinish(Date gmtReplyFinish) {
        this.gmtReplyFinish = gmtReplyFinish;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

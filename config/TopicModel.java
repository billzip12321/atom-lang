/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic;

import java.util.Date;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 主题模型DTO信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicDTO.java, V1.0.1 2014年1月28日 下午3:35:25 $
 */
public class TopicModel extends BaseDTO {
    private static final long serialVersionUID = -2839011631015011675L;

    /** Valve */
    private TopicValve        valve;

    /**
     * FetchValve
     */
    public TopicValve findValve() {
        if (this.valve == null) {
            this.valve = new TopicValve(this);
        }

        return this.valve;
    }

    /**
     * ResetValve
     */
    public TopicModel resetValve() {
        this.valve = null;
        return this;
    }

    /** ID */
    private String        id;

    /** 位标志 */
    private String        flag;

    /** 分类代码 */
    private String        catg;

    /** 主题分类 */
    private TopicModelEnum typeEnum;

    /** 原主题ID */
    private String        topic;

    /** 发布者No */
    private String        postUserNo;

    /** 发布者昵称 */
    private String        postNickName;

    /** 引用URL */
    private String        linkUrl;

    /** 多媒体URL */
    private String        mediaUrl;

    /** 发布时间 */
    private Date          gmtPost;

    /** 查看数量 */
    private int           visitCount;

    /** 跟帖数量 */
    private int           replyCount;

    /** 跟帖者No */
    private String        replyUserNo;

    /** 跟帖者昵称 */
    private String        replyNickName;

    /** 发布时间 */
    private Date          gmtReply;

    /** 扩展参数 */
    private String        extMap;

    /** 标题样式 */
    private String        titleStyle;

    /** 标题 */
    private String        title;

    /** 摘要 */
    private String        summary;

    /** 内容 */
    private String        content;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public TopicValve getValve() {
        return valve;
    }

    public void setValve(TopicValve valve) {
        this.valve = valve;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public TopicModelEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(TopicModelEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPostUserNo() {
        return postUserNo;
    }

    public void setPostUserNo(String postUserNo) {
        this.postUserNo = postUserNo;
    }

    public String getPostNickName() {
        return postNickName;
    }

    public void setPostNickName(String postNickName) {
        this.postNickName = postNickName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Date getGmtPost() {
        return gmtPost;
    }

    public void setGmtPost(Date gmtPost) {
        this.gmtPost = gmtPost;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getReplyUserNo() {
        return replyUserNo;
    }

    public void setReplyUserNo(String replyUserNo) {
        this.replyUserNo = replyUserNo;
    }

    public String getReplyNickName() {
        return replyNickName;
    }

    public void setReplyNickName(String replyNickName) {
        this.replyNickName = replyNickName;
    }

    public Date getGmtReply() {
        return gmtReply;
    }

    public void setGmtReply(Date gmtReply) {
        this.gmtReply = gmtReply;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

    public String getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(String titleStyle) {
        this.titleStyle = titleStyle;
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

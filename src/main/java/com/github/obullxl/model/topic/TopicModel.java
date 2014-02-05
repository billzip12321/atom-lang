/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic;

import java.util.Date;

import com.github.obullxl.lang.biz.BaseDTO;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.model.topic.enums.TopicMediaEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;
import com.github.obullxl.model.topic.enums.TopicTopEnum;

/**
 * 主题模型DTO信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicDTO.java, V1.0.1 2014年1月28日 下午3:35:25 $
 */
public class TopicModel extends BaseDTO {
    private static final long serialVersionUID = -2839011631015011675L;

    /** ID */
    private String            id;

    /** 模型分类 */
    private TopicModelEnum    modelEnum;

    /** 状态 */
    private TopicStateEnum    stateEnum;

    /** 全局置顶 */
    private TopicTopEnum      topEnum;

    /** 加精标志 */
    private ValveBoolEnum     eliteEnum;

    /** 原创标志 */
    private ValveBoolEnum     originalEnum;

    /** 多媒体标志 */
    private TopicMediaEnum    mediaEnum;

    /** 评论标志 */
    private ValveBoolEnum     replyEnum;

    /** 分类代码 */
    private String            catg;

    /** 排序值 */
    private String            sort;

    /** 原主题ID */
    private String            topic;

    /** 引用URL */
    private String            linkUrl;

    /** 多媒体URL */
    private String            mediaUrl;

    /** 发布者No */
    private String            postUserNo;

    /** 发布者昵称 */
    private String            postNickName;

    /** 发布时间 */
    private Date              gmtPost;

    /** 查看数量 */
    private int               visitCount;

    /** 跟帖数量 */
    private int               replyCount;

    /** 跟帖者No */
    private String            replyUserNo;

    /** 跟帖者昵称 */
    private String            replyNickName;

    /** 发布时间 */
    private Date              gmtReply;

    /** 扩展参数 */
    private String            extMap;

    /** 标题样式 */
    private String            titleStyle;

    /** 标题 */
    private String            title;

    /** 摘要 */
    private String            summary;

    /** 内容 */
    private String            content;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TopicModelEnum getModelEnum() {
        return modelEnum;
    }

    public void setModelEnum(TopicModelEnum modelEnum) {
        this.modelEnum = modelEnum;
    }

    public TopicStateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(TopicStateEnum stateEnum) {
        this.stateEnum = stateEnum;
    }

    public TopicTopEnum getTopEnum() {
        return topEnum;
    }

    public void setTopEnum(TopicTopEnum topEnum) {
        this.topEnum = topEnum;
    }

    public ValveBoolEnum getEliteEnum() {
        return eliteEnum;
    }

    public void setEliteEnum(ValveBoolEnum eliteEnum) {
        this.eliteEnum = eliteEnum;
    }

    public ValveBoolEnum getOriginalEnum() {
        return originalEnum;
    }

    public void setOriginalEnum(ValveBoolEnum originalEnum) {
        this.originalEnum = originalEnum;
    }

    public TopicMediaEnum getMediaEnum() {
        return mediaEnum;
    }

    public void setMediaEnum(TopicMediaEnum mediaEnum) {
        this.mediaEnum = mediaEnum;
    }

    public ValveBoolEnum getReplyEnum() {
        return replyEnum;
    }

    public void setReplyEnum(ValveBoolEnum replyEnum) {
        this.replyEnum = replyEnum;
    }

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

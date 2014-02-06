/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.model.topic.query;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.das.DAS;
import com.github.obullxl.lang.enums.OrderbyEnum;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.utils.QueryLikeUtils;
import com.github.obullxl.model.QueryUtils;
import com.github.obullxl.model.topic.TopicModelEnum;
import com.github.obullxl.model.topic.enums.TopicMediaEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;
import com.github.obullxl.model.topic.enums.TopicTopEnum;

/**
 * 主题查询表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicQueryForm.java, V1.0.1 2013年12月28日 下午12:47:49 $
 */
public class TopicQueryForm extends ToString {
    private static final long serialVersionUID = 3566482674275741781L;

    // public static final String FUZZY            = "fuzzy";
    // public static final String SINGLE           = "single";

    /** 页号-从1开始 */
    private int               page;

    /** 分页大小 */
    private int               pageSize;

    /** 统计 */
    private boolean           count;

    /** 排序字段 */
    private String            orderbyField     = DAS.TOPIC.ID;

    /** 排序方式 */
    private OrderbyEnum       orderbyEnum      = OrderbyEnum.findDefault();

    /** ID-精确查询 */
    private String            id;

    /** 模型 */
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

    public TopicQueryDAS to() {
        TopicQueryDAS dst = new TopicQueryDAS();

        // 精确查询
        if (StringUtils.isNotBlank(this.id)) {
            dst.setId(this.id);
            return dst;
        }

        // 模糊查询
        dst.setPageSize(this.pageSize);
        dst.setOrderbyField(StringUtils.trimToNull(this.orderbyField));
        dst.setOrderbyType(QueryUtils.findEnumCode(this.orderbyEnum));
        
        dst.setModel(QueryUtils.findEnumCode(this.modelEnum));
        dst.setState(QueryUtils.findEnumCode(this.stateEnum));
        dst.setTop(QueryUtils.findEnumCode(this.topEnum));
        dst.setElite(QueryUtils.findEnumCode(this.eliteEnum));
        dst.setOriginal(QueryUtils.findEnumCode(this.originalEnum));
        dst.setMedia(QueryUtils.findEnumCode(this.mediaEnum));
        dst.setReply(QueryUtils.findEnumCode(this.replyEnum));
        dst.setCatgs(QueryUtils.findList(this.catgs));
        dst.setTopicId(StringUtils.trimToNull(this.topicId));
        dst.setPostUserNo(StringUtils.trimToNull(this.postUserNo));
        dst.setGmtPostBegin(this.gmtPostBegin);
        dst.setGmtPostFinish(this.gmtPostFinish);
        dst.setReplyUserNo(StringUtils.trimToNull(this.replyUserNo));
        dst.setGmtReplyBegin(this.gmtReplyBegin);
        dst.setGmtReplyFinish(this.gmtReplyFinish);
        dst.setExtMap(QueryLikeUtils.format(this.extMap));
        dst.setTitle(QueryLikeUtils.format(this.title));
        dst.setSummary(QueryLikeUtils.format(this.summary));
        dst.setContent(QueryLikeUtils.format(this.content));

        return dst;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public String getOrderbyField() {
        return orderbyField;
    }

    public void setOrderbyField(String orderbyField) {
        this.orderbyField = orderbyField;
    }

    public OrderbyEnum getOrderbyEnum() {
        return orderbyEnum;
    }

    public void setOrderbyEnum(OrderbyEnum orderbyEnum) {
        this.orderbyEnum = orderbyEnum;
    }

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

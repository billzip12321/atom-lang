/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic.dao;

import java.util.List;

import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.query.TopicQueryDAS;

/**
 * 主题模型DAO
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicDAO.java, V1.0.1 2014年1月28日 下午4:23:32 $
 */
public interface TopicDAO {
    public static final String NAME = "TopicDAO";

    /**
     * 插入主题模型
     */
    public void insert(TopicModel topic);

    /**
     * 更新主题模型
     */
    public int update(TopicModel topic);

    /**
     * 增加/减少主题浏览次数
     */
    public int deltaVisitCount(String id, int deta);

    /**
     * 增加/减少主题跟帖次数
     */
    public int deltaReplyCount(String id, int deta);

    /**
     * 根据ID删除主题模型
     */
    public int deleteByID(String id);
    
    /**
     * 根据主题ID删除主题模型
     */
    public int deleteByTopicID(String topicId);
    
    /**
     * 根据发/跟帖者删除主题模型
     */
    public int deleteByUserNo(String userNo);
    
    /**
     * 根据发帖者删除主题模型
     */
    public int deleteByPostUserNo(String postUserNo);
    
    /**
     * 根据跟帖者删除主题模型
     */
    public int deleteByReplyUserNo(String replyUserNo);

    /**
     * 根据ID查询主题
     */
    public TopicModel selectByID(String id);

    /**
     * 统计
     */
    public int count(TopicQueryDAS query);

    /**
     * 分页查询
     */
    public List<TopicModel> selectPage(TopicQueryDAS query);

}

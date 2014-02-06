/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic.service;

import java.util.List;

import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.query.TopicPageList;
import com.github.obullxl.model.topic.query.TopicQueryForm;

/**
 * 主题模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicService.java, V1.0.1 2014年1月28日 下午5:22:28 $
 */
public interface TopicService {

    /**
     * 插入主题模型
     */
    public void create(TopicModel topic);

    /**
     * 更新主题模型
     */
    public int update(TopicModel topic);

    /**
     * 增加/减少主题浏览次数
     */
    public int deltaVisitCount(String id, final int deta);

    /**
     * 增加/减少主题跟帖次数
     */
    public int deltaReplyCount(String id, final int deta);

    /**
     * 删除所有主题模型
     */
    public int removeAll();

    /**
     * 根据ID删除主题模型
     */
    public int removeByID(String id);
    
    /**
     * 根据主题ID删除主题模型
     */
    public int removeByTopicID(String topicId);

    /**
     * 查询主题模型
     */
    public TopicModel findByID(String id);

    /**
     * 统计主题模型
     */
    public int count(TopicQueryForm form);

    /**
     * 查询主题模型列表
     */
    public List<TopicModel> findPage(TopicQueryForm form);

    /**
     * 分页查询主题模型
     */
    public TopicPageList findPageList(TopicQueryForm form);

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic.service.impl;

import org.apache.commons.lang.Validate;

import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.dao.impl.AbstractTopicDAO;
import com.github.obullxl.model.topic.service.TopicService;

/**
 * 主题模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicServiceImpl.java, V1.0.1 2014年1月28日 下午5:24:35 $
 */
public class TopicServiceImpl implements TopicService {

    /** 主题模型DAO */
    private AbstractTopicDAO topicDAO;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.topicDAO, "[主题模型]-DAO[TopicDAO]注入失败!");
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#create(com.github.obullxl.model.topic.TopicModel)
     */
    public void create(TopicModel topic) {
        this.topicDAO.insert(topic);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#update(com.github.obullxl.model.topic.TopicModel)
     */
    public int update(TopicModel topic) {
        return this.topicDAO.update(topic);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#deltaVisitCount(java.lang.String, int)
     */
    public int deltaVisitCount(final String id, final int deta) {
        return this.topicDAO.deltaVisitCount(id, deta);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#deltaReplyCount(java.lang.String, int)
     */
    public int deltaReplyCount(final String id, final int deta) {
        return this.topicDAO.deltaReplyCount(id, deta);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#removeAll()
     */
    public int removeAll() {
        return this.topicDAO.deleteAll();
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#removeByID(java.lang.String)
     */
    public int removeByID(String id) {
        return this.topicDAO.deleteByID(id);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#findByID(java.lang.String)
     */
    public TopicModel findByID(String id) {
        return this.topicDAO.selectByID(id);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setTopicDAO(AbstractTopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

}

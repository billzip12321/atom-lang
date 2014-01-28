/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.topic;

import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * 主题模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultTopicService.java, V1.0.1 2014年1月28日 下午5:24:35 $
 */
public class DefaultTopicService implements TopicService {

    /** 主题模型DAO */
    private TopicDAO topicDAO;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.topicDAO, "[主题模型]-DAO[TopicDAO]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#create(com.github.obullxl.lang.topic.TopicDTO)
     */
    public void create(TopicDTO topic) {
        this.topicDAO.insert(topic);
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#update(com.github.obullxl.lang.topic.TopicDTO)
     */
    public int update(TopicDTO topic) {
        return this.topicDAO.update(topic);
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#detaVisitCount(java.lang.String, int)
     */
    public int detaVisitCount(final String id, final int deta) {
        return this.topicDAO.detaVisitCount(id, deta);
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#detaReplyCount(java.lang.String, int)
     */
    public int detaReplyCount(final String id, final int deta) {
        return this.topicDAO.detaReplyCount(id, deta);
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#find()
     */
    public List<TopicDTO> find() {
        return this.topicDAO.find();
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#remove()
     */
    public int remove() {
        return this.topicDAO.delete();
    }

    /** 
     * @see com.github.obullxl.lang.topic.TopicService#remove(java.lang.String)
     */
    public int remove(String id) {
        return this.topicDAO.delete(id);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setTopicDAO(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

}

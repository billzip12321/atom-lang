/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.topic.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.github.obullxl.lang.Paginator;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.dao.impl.AbstractTopicDAO;
import com.github.obullxl.model.topic.query.TopicPageList;
import com.github.obullxl.model.topic.query.TopicQueryDAS;
import com.github.obullxl.model.topic.query.TopicQueryForm;
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
     * @see com.github.obullxl.model.topic.service.TopicService#removeByTopicID(java.lang.String)
     */
    public int removeByTopicID(String topicId) {
        return this.topicDAO.deleteByTopicID(topicId);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#findByID(java.lang.String)
     */
    public TopicModel findByID(String id) {
        return this.topicDAO.selectByID(id);
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#count(com.github.obullxl.model.topic.query.TopicQueryForm)
     */
    public int count(TopicQueryForm form) {
        return this.topicDAO.count(form.to());
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#findPage(com.github.obullxl.model.topic.query.TopicQueryForm)
     */
    public List<TopicModel> findPage(TopicQueryForm form) {
        if (StringUtils.isNotBlank(form.getId())) {
            return Arrays.asList(this.findByID(form.getId()));
        }

        return this.topicDAO.selectPage(form.to());
    }

    /** 
     * @see com.github.obullxl.model.topic.service.TopicService#findPageList(com.github.obullxl.model.topic.query.TopicQueryForm)
     */
    public TopicPageList findPageList(TopicQueryForm form) {
        if (StringUtils.isNotBlank(form.getId())) {
            List<TopicModel> topics = Arrays.asList(this.findByID(form.getId()));
            
            Paginator pager = new Paginator(form.getPageSize(), topics.size());
            pager.setPageNo(form.getPage());
            
            return new TopicPageList(pager, topics);
        }
        
        int count = 0;
        if(form.isCount()) {
            count = this.count(form);
        }
        
        Paginator pager = new Paginator(form.getPageSize(), count);
        pager.setPageNo(form.getPage());
        
        TopicQueryDAS qdas = form.to();
        qdas.setOffset(pager.getOffset());
        qdas.setPageSize(pager.getPageSize());
        
        List<TopicModel> topics = this.topicDAO.selectPage(qdas);
        
        return new TopicPageList(pager, topics);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setTopicDAO(AbstractTopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

}

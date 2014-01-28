/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.topic;

import java.util.List;

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
    public void create(TopicDTO topic);

    /**
     * 更新主题模型
     */
    public int update(TopicDTO topic);

    /**
     * 增加/减少主题浏览次数
     */
    public int detaVisitCount(final String id, final int deta);

    /**
     * 增加/减少主题跟帖次数
     */
    public int detaReplyCount(final String id, final int deta);

    /**
     * 查询所有主题模型
     */
    public List<TopicDTO> find();

    /**
     * 删除所有主题模型
     */
    public int remove();

    /**
     * 根据ID删除主题模型
     */
    public int remove(String id);

}

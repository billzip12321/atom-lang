/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

/**
 * 论坛模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumService.java, V1.0.1 2014年1月30日 下午4:03:03 $
 */
public interface ForumService {
    /** 论坛分类模型KEY */
    public static final String CATG = ForumUtils.CATG;

    /**
     * 创建论坛模型
     */
    public void create(ForumDTO forum);

    /**
     * 更新论坛模型
     */
    public void update(ForumDTO forum);

    /**
     * 删除所有论坛模型
     */
    public int remove();

    /**
     * 删除单个论坛模型
     */
    public int remove(String code);

}

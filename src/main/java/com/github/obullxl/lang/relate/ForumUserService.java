/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.List;

/**
 * 论坛管理员模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumUserService.java, V1.0.1 2014年1月31日 下午1:28:24 $
 */
public interface ForumUserService {
    /** 论坛管理员分类KEY */
    public static final String CATG = ForumUserUtils.CATG;

    /**
     * 插入论坛管理员
     */
    public void create(ForumUserDTO fuser);

    /**
     * 更新论坛管理员
     */
    public int update(ForumUserDTO fuser);

    /**
     * 删除所有论坛管理员
     */
    public int remove();

    /**
     * 根据用户编号删除记录
     */
    public int removeByForumCode(String code);

    /**
     * 根据权限代码删除记录
     */
    public int removeByUserNo(String userNo);

    /**
     * 根据用户编号+权限代码删除记录
     */
    public int removeByUnique(String code, String userNo);

    /**
     * 查询所有论坛管理员数据模型
     */
    public List<ForumUserDTO> find();

    /**
     * 根据用户编号查询数据模型
     */
    public List<ForumUserDTO> findByForumCode(String code);

    /**
     * 根据权限代码查询数据模型
     */
    public List<ForumUserDTO> findByUserNo(String userNo);

    /**
     * 根据用户编号+权限代码查询单条数据模型
     */
    public ForumUserDTO findByUnique(String code, String userNo);

}

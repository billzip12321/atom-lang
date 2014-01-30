/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.right;

import java.util.List;

/**
 * 用户权限服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightService.java, V1.0.1 2014年1月29日 下午2:09:03 $
 */
public interface UserRightService {

    /**
     * 插入用户权限
     */
    public void create(final UserRightDTO urgt);
    
    /**
     * 更新用户权限
     */
    public int update(final UserRightDTO urgt);
    
    /**
     * 删除所有用户权限
     */
    public int remove();
    
    /**
     * 根据用户编号删除记录
     */
    public int removeByUserNo(final String userNo);
    
    /**
     * 根据权限代码删除记录
     */
    public int removeByRgtCode(final String rgtCode);
    
    /**
     * 根据用户编号+权限代码删除记录
     */
    public int removeByUnique(final String userNo, final String rgtCode);
    
    /**
     * 查询所有用户权限数据模型
     */
    public List<UserRightDTO> find();
    
    /**
     * 根据用户编号查询数据模型
     */
    public List<UserRightDTO> findByUserNo(final String userNo);
    
    /**
     * 根据权限代码查询数据模型
     */
    public List<UserRightDTO> findByRgtCode(final String rgtCode);
    
    /**
     * 根据用户编号+权限代码查询单条数据模型
     */
    public UserRightDTO findByUnique(final String userNo, final String rgtCode);
    
}

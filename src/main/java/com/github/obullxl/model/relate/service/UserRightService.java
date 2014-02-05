/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.service;

import com.github.obullxl.model.relate.userright.UserRightModel;
import com.github.obullxl.model.relate.userright.UserRightUtils;

/**
 * 用户权限服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightService.java, V1.0.1 2014年1月29日 下午2:09:03 $
 */
public interface UserRightService {
    /** 用户权限分类KEY */
    public static final String CATG = UserRightUtils.CATG;

    /**
     * 插入用户权限
     */
    public void create(UserRightModel urgt);

    /**
     * 更新用户权限
     */
    public int update(UserRightModel urgt);

    /**
     * 删除所有用户权限
     */
    public int remove();

    /**
     * 根据用户编号删除用户权限
     */
    public int removeByUserNo(String userNo);

    /**
     * 根据权限代码删除用户权限
     */
    public int removeByRgtCode(String rgtCode);

    /**
     * 根据用户编号+权限代码删除用户权限
     */
    public int removeByUnique(String userNo, String rgtCode);

}

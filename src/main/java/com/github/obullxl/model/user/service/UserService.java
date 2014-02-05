/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.user.service;

import com.github.obullxl.model.user.UserModel;

/**
 * 用户模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserService.java, V1.0.1 2014年1月28日 上午11:33:12 $
 */
public interface UserService {

    /**
     * 新增用户模型
     */
    public void create(UserModel user);

    /**
     * 更新用户模型
     */
    public int update(UserModel user);

    /**
     * 更新登录密码
     */
    public int updatePasswd(String no, String passwd);

    /**
     * 更新登录密码错误次数
     */
    public int updatePasswdErrCount(String no, int passwdErrCount);

    /**
     * 根据编号查询单个用户模型
     */
    public UserModel findByNo(String no);

    /**
     * 根据编号查询单个用户模型
     */
    public UserModel findByUnique(String unique);

    /**
     * 根据昵称查询单个用户模型
     */
    public UserModel findByNickName(String nickName);

    /**
     * 根据手机查询单个用户模型
     */
    public UserModel findByMobile(String mobile);

    /**
     * 根据电子邮箱查询单个用户模型
     */
    public UserModel findByEmail(String email);

    /**
     * 删除单个用户模型
     */
    public int removeByNo(String no);

}

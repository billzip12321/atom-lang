/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.user.dao;

import com.github.obullxl.model.user.UserModel;

/**
 * 用户模型DAO
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserDAO.java, V1.0.1 2014年1月28日 上午9:57:57 $
 */
public interface UserDAO {
    public static final String NAME = "UserDAO";

    /**
     * 插入用户模型
     */
    public void insert(UserModel user);

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
     * 根据(编号/昵称/邮箱/手机)查询单个用户模型
     */
    public UserModel findByUnique(String unique);

    /**
     * 根据昵称查询单个用户模型
     */
    public UserModel findByNickName(String nickName);

    /**
     * 根据电子邮箱查询单个用户模型
     */
    public UserModel findByEmail(String email);

    /**
     * 根据手机查询单个用户模型
     */
    public UserModel findByMobile(String mobile);

    /**
     * 删除单个用户模型
     */
    public int deleteByNo(String no);

}

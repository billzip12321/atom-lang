/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.Date;
import java.util.List;

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
    public void create(UserDTO user);

    /**
     * 更新用户模型
     */
    public int update(UserDTO user);

    /**
     * 更新登录密码
     */
    public int updatePasswd(String no, String passwd, Date gmtModify);

    /**
     * 更新登录密码错误次数
     */
    public int updatePasswdErrCount(String no, int passwdErrCount, Date gmtModify);

    /**
     * SQL执行（UPDATE/DELETE）
     */
    public int executeUpdate(String sql, Object... args);

    /**
     * 查询所有用户模型
     */
    public List<UserDTO> find();

    /**
     * 根据编号查询单个用户模型
     */
    public UserDTO findUnique(String unique);

    /**
     * 根据编号查询单个用户模型
     */
    public UserDTO findByNo(String no);

    /**
     * 根据昵称查询单个用户模型
     */
    public UserDTO findByNickName(String nickName);

    /**
     * 根据手机查询单个用户模型
     */
    public UserDTO findByMobile(String mobile);

    /**
     * 根据电子邮箱查询单个用户模型
     */
    public UserDTO findByEmail(String email);

    /**
     * 删除所有用户模型
     */
    public int remove();

    /**
     * 删除单个用户模型
     */
    public int remove(String no);

}

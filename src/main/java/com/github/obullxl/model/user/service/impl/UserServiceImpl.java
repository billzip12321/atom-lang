/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.user.service.impl;

import org.apache.commons.lang.Validate;

import com.github.obullxl.model.user.UserModel;
import com.github.obullxl.model.user.dao.impl.AbstractUserDAO;
import com.github.obullxl.model.user.service.UserService;

/**
 * 用户模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserServiceImpl.java, V1.0.1 2014年1月28日 上午11:33:49 $
 */
public class UserServiceImpl implements UserService {

    /** 用户模型DAO */
    private AbstractUserDAO userDAO;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.userDAO, "[用户模型]-DAO[UserDAO]注入失败!");
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#create(com.github.obullxl.model.user.UserModel)
     */
    public void create(UserModel user) {
        this.userDAO.insert(user);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#update(com.github.obullxl.model.user.UserModel)
     */
    public int update(UserModel user) {
        return this.userDAO.update(user);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#updatePasswd(java.lang.String, java.lang.String)
     */
    public int updatePasswd(String no, String passwd) {
        return this.userDAO.updatePasswd(no, passwd);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#updatePasswdErrCount(java.lang.String, int)
     */
    public int updatePasswdErrCount(String no, int passwdErrCount) {
        passwdErrCount = Math.max(passwdErrCount, 0);
        return this.userDAO.updatePasswdErrCount(no, passwdErrCount);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#findByNo(java.lang.String)
     */
    public UserModel findByNo(String no) {
        return this.userDAO.findByNo(no);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#findByUnique(java.lang.String)
     */
    public UserModel findByUnique(String unique) {
        return this.userDAO.findByUnique(unique);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#findByNickName(java.lang.String)
     */
    public UserModel findByNickName(String nickName) {
        return this.userDAO.findByNickName(nickName);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#findByMobile(java.lang.String)
     */
    public UserModel findByMobile(String mobile) {
        return this.userDAO.findByMobile(mobile);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#findByEmail(java.lang.String)
     */
    public UserModel findByEmail(String email) {
        return this.userDAO.findByEmail(email);
    }

    /** 
     * @see com.github.obullxl.model.user.service.UserService#removeByNo(java.lang.String)
     */
    public int removeByNo(String no) {
        return this.userDAO.deleteByNo(no);
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setUserDAO(AbstractUserDAO userDAO) {
        this.userDAO = userDAO;
    }

}

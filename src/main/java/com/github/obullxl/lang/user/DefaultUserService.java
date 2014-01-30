/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * 用户模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultUserService.java, V1.0.1 2014年1月28日 上午11:33:49 $
 */
public class DefaultUserService implements UserService {

    /** 用户模型DAO */
    private UserDAO userDAO;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.userDAO, "[用户模型]-DAO[UserDAO]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#create(com.github.obullxl.lang.user.UserDTO)
     */
    public void create(UserDTO user) {
        this.userDAO.insert(user);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#update(com.github.obullxl.lang.user.UserDTO)
     */
    public int update(UserDTO user) {
        return this.userDAO.update(user);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#updatePasswd(java.lang.String, java.lang.String, java.util.Date)
     */
    public int updatePasswd(String no, String passwd, Date gmtModify) {
        if (gmtModify == null) {
            gmtModify = new Date();
        }

        return this.userDAO.updatePasswd(no, passwd, gmtModify);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#updatePasswdErrCount(java.lang.String, int, java.util.Date)
     */
    public int updatePasswdErrCount(String no, int passwdErrCount, Date gmtModify) {
        if (gmtModify == null) {
            gmtModify = new Date();
        }

        passwdErrCount = Math.max(passwdErrCount, 0);

        return this.userDAO.updatePasswdErrCount(no, passwdErrCount, gmtModify);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#executeUpdate(java.lang.String, java.lang.Object[])
     */
    public int executeUpdate(String sql, Object... args) {
        return this.userDAO.executeUpdate(sql, args);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#find()
     */
    public List<UserDTO> find() {
        return this.userDAO.selectAll();
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#findUnique(java.lang.String)
     */
    public UserDTO findUnique(String unique) {
        return this.userDAO.findUnique(unique);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#findByNo(java.lang.String)
     */
    public UserDTO findByNo(String no) {
        return this.userDAO.findByNo(no);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#findByNickName(java.lang.String)
     */
    public UserDTO findByNickName(String nickName) {
        return this.userDAO.findByNickName(nickName);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#findByMobile(java.lang.String)
     */
    public UserDTO findByMobile(String mobile) {
        return this.userDAO.findByMobile(mobile);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#findByEmail(java.lang.String)
     */
    public UserDTO findByEmail(String email) {
        return this.userDAO.findByEmail(email);
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#remove()
     */
    public int remove() {
        return this.userDAO.deleteAll();
    }

    /** 
     * @see com.github.obullxl.lang.user.UserService#remove(java.lang.String)
     */
    public int remove(String no) {
        return this.userDAO.delete(no);
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}

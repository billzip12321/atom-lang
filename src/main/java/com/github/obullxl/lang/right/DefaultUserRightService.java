/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.right;

import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * 用户权限服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultUserRightService.java, V1.0.1 2014年1月29日 下午2:09:37 $
 */
public class DefaultUserRightService implements UserRightService {

    /** 用户权限模型DAO */
    private UserRightDAO userRightDAO;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.userRightDAO, "[用户权限]-DAO[UserRightDAO]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#create(com.github.obullxl.lang.right.UserRightDTO)
     */
    public void create(final UserRightDTO urgt) {
        this.userRightDAO.insert(urgt);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#update(com.github.obullxl.lang.right.UserRightDTO)
     */
    public int update(final UserRightDTO urgt) {
        return this.userRightDAO.update(urgt);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#remove()
     */
    public int remove() {
        return this.userRightDAO.deleteAll();
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#removeByUserNo(java.lang.String)
     */
    public int removeByUserNo(final String userNo) {
        return this.userRightDAO.deleteByUserNo(userNo);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#removeByRgtCode(java.lang.String)
     */
    public int removeByRgtCode(final String rgtCode) {
        return this.userRightDAO.deleteByRgtCode(rgtCode);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#removeByUnique(java.lang.String, java.lang.String)
     */
    public int removeByUnique(final String userNo, final String rgtCode) {
        return this.userRightDAO.deleteByUnique(userNo, rgtCode);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#find()
     */
    public List<UserRightDTO> find() {
        return this.userRightDAO.selectAll();
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#findByUserNo(java.lang.String)
     */
    public List<UserRightDTO> findByUserNo(final String userNo) {
        return this.userRightDAO.findByUserNo(userNo);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#findByRgtCode(java.lang.String)
     */
    public List<UserRightDTO> findByRgtCode(final String rgtCode) {
        return this.userRightDAO.findByRgtCode(rgtCode);
    }

    /** 
     * @see com.github.obullxl.lang.right.UserRightService#findByUnique(java.lang.String, java.lang.String)
     */
    public UserRightDTO findByUnique(final String userNo, final String rgtCode) {
        return this.userRightDAO.findByUnique(userNo, rgtCode);
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setUserRightDAO(UserRightDAO userRightDAO) {
        this.userRightDAO = userRightDAO;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

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
    private RelateService relateService;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.relateService, "[用户权限]-关系服务[RelateService]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#create(com.github.obullxl.lang.right.UserRightDTO)
     */
    public void create(final UserRightDTO urgt) {
        this.relateService.create(UserRightUtils.convert(urgt));
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#update(com.github.obullxl.lang.right.UserRightDTO)
     */
    public int update(final UserRightDTO urgt) {
        return this.relateService.update(UserRightUtils.convert(urgt));
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#remove()
     */
    public int remove() {
        return this.relateService.removeByCatg(CATG);
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#removeByUserNo(java.lang.String)
     */
    public int removeByUserNo(final String userNo) {
        return this.relateService.removeBySrcCatg(CATG, userNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#removeByRgtCode(java.lang.String)
     */
    public int removeByRgtCode(final String rgtCode) {
        return this.relateService.removeByDstCatg(CATG, rgtCode);
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#removeByUnique(java.lang.String, java.lang.String)
     */
    public int removeByUnique(final String userNo, final String rgtCode) {
        return this.relateService.removeByUnique(CATG, userNo, rgtCode);
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#find()
     */
    public List<UserRightDTO> find() {
        return UserRightUtils.convert(this.relateService.findByCatg(CATG));
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#findByUserNo(java.lang.String)
     */
    public List<UserRightDTO> findByUserNo(final String userNo) {
        return UserRightUtils.convert(this.relateService.findBySrcCatg(CATG, userNo));
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#findByRgtCode(java.lang.String)
     */
    public List<UserRightDTO> findByRgtCode(final String rgtCode) {
        return UserRightUtils.convert(this.relateService.findByDstCatg(CATG, rgtCode));
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#findByUnique(java.lang.String, java.lang.String)
     */
    public UserRightDTO findByUnique(final String userNo, final String rgtCode) {
        return UserRightUtils.convert(this.relateService.findByUnique(CATG, userNo, rgtCode));
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setRelateService(RelateService relateService) {
        this.relateService = relateService;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.service.impl;

import org.apache.commons.lang.Validate;

import com.github.obullxl.model.relate.service.RelateService;
import com.github.obullxl.model.relate.service.UserRightService;
import com.github.obullxl.model.relate.userright.UserRightModel;
import com.github.obullxl.model.relate.userright.UserRightUtils;

/**
 * 用户权限服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightServiceImpl.java, V1.0.1 2014年1月29日 下午2:09:37 $
 */
public class UserRightServiceImpl implements UserRightService {

    /** 用户权限模型DAO */
    private RelateService relateService;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.relateService, "[用户权限]-关系服务[RelateService]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#create(com.github.obullxl.lang.UserRightModel.UserRightDTO)
     */
    public void create(final UserRightModel urgt) {
        this.relateService.create(UserRightUtils.from(urgt));
    }

    /** 
     * @see com.github.obullxl.lang.relate.UserRightService#update(com.github.obullxl.lang.UserRightModel.UserRightDTO)
     */
    public int update(final UserRightModel urgt) {
        return this.relateService.update(UserRightUtils.from(urgt));
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

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setRelateService(RelateService relateService) {
        this.relateService = relateService;
    }

}

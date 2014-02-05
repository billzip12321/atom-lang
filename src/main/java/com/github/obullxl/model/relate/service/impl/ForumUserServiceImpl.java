/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.service.impl;

import org.apache.commons.lang.Validate;

import com.github.obullxl.model.relate.forumuser.ForumUserModel;
import com.github.obullxl.model.relate.forumuser.ForumUserUtils;
import com.github.obullxl.model.relate.service.ForumUserService;
import com.github.obullxl.model.relate.service.RelateService;

/**
 * 论坛管理员模型服务实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumUserServiceImpl.java, V1.0.1 2014年1月31日 下午1:32:32 $
 */
public class ForumUserServiceImpl implements ForumUserService {

    /** 用户权限模型DAO */
    private RelateService relateService;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.relateService, "[用户权限]-关系服务[RelateService]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#create(com.github.obullxl.lang.relate.ForumUserModel)
     */
    public void create(ForumUserModel fuser) {
        this.relateService.create(ForumUserUtils.from(fuser));
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#update(com.github.obullxl.lang.relate.ForumUserModel)
     */
    public int update(ForumUserModel fuser) {
        return this.relateService.update(ForumUserUtils.from(fuser));
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#remove()
     */
    public int remove() {
        return this.relateService.removeByCatg(CATG);
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#removeByForumCode(java.lang.String)
     */
    public int removeByForumCode(String code) {
        return this.relateService.removeBySrcCatg(CATG, code);
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#removeByUserNo(java.lang.String)
     */
    public int removeByUserNo(String userNo) {
        return this.relateService.removeByDstCatg(CATG, userNo);
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#removeByUnique(java.lang.String, java.lang.String)
     */
    public int removeByUnique(String code, String userNo) {
        return this.relateService.removeByUnique(CATG, code, userNo);
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setRelateService(RelateService relateService) {
        this.relateService = relateService;
    }

}

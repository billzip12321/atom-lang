/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * 论坛管理员模型服务实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultForumUserService.java, V1.0.1 2014年1月31日 下午1:32:32 $
 */
public class DefaultForumUserService implements ForumUserService {

    /** 用户权限模型DAO */
    private RelateService relateService;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.relateService, "[用户权限]-关系服务[RelateService]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#create(com.github.obullxl.lang.relate.ForumUserDTO)
     */
    public void create(ForumUserDTO fuser) {
        this.relateService.create(ForumUserUtils.convert(fuser));
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#update(com.github.obullxl.lang.relate.ForumUserDTO)
     */
    public int update(ForumUserDTO fuser) {
        return this.relateService.update(ForumUserUtils.convert(fuser));
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

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#find()
     */
    public List<ForumUserDTO> find() {
        return ForumUserUtils.convert(this.relateService.findByCatg(CATG));
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#findByForumCode(java.lang.String)
     */
    public List<ForumUserDTO> findByForumCode(String code) {
        return ForumUserUtils.convert(this.relateService.findBySrcCatg(CATG, code));
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#findByUserNo(java.lang.String)
     */
    public List<ForumUserDTO> findByUserNo(String userNo) {
        return ForumUserUtils.convert(this.relateService.findByDstCatg(CATG, userNo));
    }

    /** 
     * @see com.github.obullxl.lang.relate.ForumUserService#findByUnique(java.lang.String, java.lang.String)
     */
    public ForumUserDTO findByUnique(String code, String userNo) {
        return ForumUserUtils.convert(this.relateService.findByUnique(CATG, code, userNo));
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setRelateService(RelateService relateService) {
        this.relateService = relateService;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.forumuser;

import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.model.relate.RelateModel;
import com.github.obullxl.model.relate.RelateUtils;

/**
 * 论坛管理员工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumUserUtils.java, V1.0.1 2014年1月31日 下午1:30:50 $
 */
public class ForumUserUtils {
    /** 论坛管理员分类KEY */
    public static final String CATG = "FORUM_USER";

    /**
     * 论坛管理员对象转换
     */
    public static ForumUserModel from(RelateModel srcObj) {
        if (srcObj == null) {
            return null;
        }

        ForumUserModel dstObj = new ForumUserModel();

        dstObj.setCode(srcObj.getSrcNo());
        dstObj.setName(srcObj.getSrcName());
        dstObj.setUserNo(srcObj.getDstNo());
        dstObj.setNickName(srcObj.getDstName());
        dstObj.setExtMap(srcObj.getExtMap());

        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 论坛管理员对象列表转换
     */
    public static List<ForumUserModel> from(List<RelateModel> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<ForumUserModel> dstObjs = new ArrayList<ForumUserModel>();

        for (RelateModel srcObj : srcObjs) {
            ForumUserModel dstObj = from(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    /**
     * 关系模型对象转换
     */
    public static RelateModel from(ForumUserModel srcObj) {
        if (srcObj == null) {
            return null;
        }

        RelateModel dstObj = new RelateModel();

        dstObj.setCatg(CATG);
        dstObj.setSrcNo(srcObj.getCode());
        dstObj.setSrcName(srcObj.getName());
        dstObj.setDstNo(srcObj.getUserNo());
        dstObj.setDstName(srcObj.getNickName());
        dstObj.setExtMap(srcObj.getExtMap());

        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 获取所有论坛管理员
     */
    public static List<ForumUserModel> find() {
        List<RelateModel> relates = RelateUtils.findByCatg(CATG);
        return from(relates);
    }

    /**
     * 根据论坛代码获取论坛管理员
     */
    public static List<ForumUserModel> findByForumCode(String code) {
        List<RelateModel> relates = RelateUtils.findBySrcCatg(CATG, code);
        return from(relates);
    }

    /**
     * 根据用户编号获取论坛管理员
     */
    public static List<ForumUserModel> findByUserNo(String userNo) {
        List<RelateModel> relates = RelateUtils.findByDstCatg(CATG, userNo);
        return from(relates);
    }

    /**
     * 是否存在论坛管理员
     */
    public static boolean existForumUser(String code, String userNo) {
        RelateModel relate = RelateUtils.findByUnique(CATG, code, userNo);
        return (relate != null);
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.userright;

import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.model.relate.RelateModel;
import com.github.obullxl.model.relate.RelateUtils;

/**
 * 用户权限工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightUtils.java, V1.0.1 2014年1月31日 下午12:07:42 $
 */
public class UserRightUtils {
    /** 用户权限分类KEY */
    public static final String CATG = "USER_RIGHT";

    /**
     * 用户权限对象转换
     */
    public static UserRightModel from(RelateModel srcObj) {
        if (srcObj == null) {
            return null;
        }

        UserRightModel dstObj = new UserRightModel();

        dstObj.setUserNo(srcObj.getSrcNo());
        dstObj.setNickName(srcObj.getSrcName());
        dstObj.setRgtCode(srcObj.getDstNo());
        dstObj.setRgtName(srcObj.getDstName());
        dstObj.setExtMap(srcObj.getExtMap());

        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 用户权限对象列表转换
     */
    public static List<UserRightModel> from(List<RelateModel> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<UserRightModel> dstObjs = new ArrayList<UserRightModel>();

        for (RelateModel srcObj : srcObjs) {
            UserRightModel dstObj = from(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    /**
     * 关系模型对象转换
     */
    public static RelateModel from(UserRightModel srcObj) {
        if (srcObj == null) {
            return null;
        }

        RelateModel dstObj = new RelateModel();

        dstObj.setCatg(CATG);
        dstObj.setSrcNo(srcObj.getUserNo());
        dstObj.setSrcName(srcObj.getNickName());
        dstObj.setDstNo(srcObj.getRgtCode());
        dstObj.setDstName(srcObj.getRgtName());
        dstObj.setExtMap(srcObj.getExtMap());

        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 获取所有用户权限
     */
    public static List<UserRightModel> find() {
        List<RelateModel> relates = RelateUtils.findByCatg(CATG);
        return from(relates);
    }

    /**
     * 根据用户编号获取用户权限
     */
    public static List<UserRightModel> findByUserNo(String userNo) {
        List<RelateModel> relates = RelateUtils.findBySrcCatg(CATG, userNo);
        return from(relates);
    }

    /**
     * 根据权限代码获取用户权限
     */
    public static List<UserRightModel> findByRightCode(String rgtCode) {
        List<RelateModel> relates = RelateUtils.findByDstCatg(CATG, rgtCode);
        return from(relates);
    }

    /**
     * 根据用户编号+权限代码获取模型
     */
    public static UserRightModel findByUnique(String userNo, String rgtCode) {
        RelateModel relate = RelateUtils.findByUnique(CATG, userNo, rgtCode);
        return from(relate);
    }

    /**
     * 是否存在用户权限
     */
    public static boolean existUserRight(String userNo, String rgtCode) {
        RelateModel relate = RelateUtils.findByUnique(CATG, userNo, rgtCode);
        return (relate != null);
    }

}

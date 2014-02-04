/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.ArrayList;
import java.util.List;

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
    public static UserRightDTO convert(RelateDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        UserRightDTO dstObj = new UserRightDTO();

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
    public static List<UserRightDTO> convert(List<RelateDTO> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<UserRightDTO> dstObjs = new ArrayList<UserRightDTO>();

        for (RelateDTO srcObj : srcObjs) {
            UserRightDTO dstObj = convert(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    /**
     * 关系模型对象转换
     */
    public static RelateDTO convert(UserRightDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        RelateDTO dstObj = new RelateDTO();

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

}

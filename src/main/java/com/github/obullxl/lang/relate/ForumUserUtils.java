/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.ArrayList;
import java.util.List;

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
     * 用户权限对象转换
     */
    public static ForumUserDTO convert(RelateDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        ForumUserDTO dstObj = new ForumUserDTO();

        dstObj.setCode(srcObj.getDstNo());
        dstObj.setName(srcObj.getDstName());
        dstObj.setUserNo(srcObj.getSrcNo());
        dstObj.setNickName(srcObj.getSrcName());
        dstObj.setExtMap(srcObj.getExtMap());

        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 用户权限对象列表转换
     */
    public static List<ForumUserDTO> convert(List<RelateDTO> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<ForumUserDTO> dstObjs = new ArrayList<ForumUserDTO>();

        for (RelateDTO srcObj : srcObjs) {
            ForumUserDTO dstObj = convert(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    /**
     * 关系模型对象转换
     */
    public static RelateDTO convert(ForumUserDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        RelateDTO dstObj = new RelateDTO();

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

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限模型工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightUtils.java, V1.0.1 2014年1月30日 下午3:44:12 $
 */
public class RightUtils {
    /** 权限系统参数分类KEY */
    public static final String CATG = "RIGHTS";

    /**
     * 权限模型对象转换
     */
    public static RightDTO convert(CfgDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        RightDTO dstObj = new RightDTO();

        dstObj.setCode(srcObj.getName());
        dstObj.setName(srcObj.getTitle());
        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 权限模型对象列表转换
     */
    public static List<RightDTO> convert(List<CfgDTO> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<RightDTO> dstObjs = new ArrayList<RightDTO>();

        for (CfgDTO srcObj : srcObjs) {
            RightDTO dstObj = convert(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    /**
     * 参数模型对象转换
     */
    public static CfgDTO convert(RightDTO srcObj) {
        if (srcObj == null) {
            return null;
        }

        CfgDTO dstObj = new CfgDTO();

        dstObj.setCatg(CATG);
        dstObj.setName(srcObj.getCode());
        dstObj.setTitle(srcObj.getName());
        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 查询单个权限模型
     */
    public static RightDTO find(String name) {
        return convert(CfgUtils.find(RightService.CATG, name));
    }

    /**
     * 查询所有权限模型
     */
    public static List<RightDTO> find() {
        return convert(CfgUtils.find(RightService.CATG));
    }
}

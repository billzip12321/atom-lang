/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg.right;

import java.util.ArrayList;
import java.util.List;

import com.github.obullxl.model.cfg.CfgModel;
import com.github.obullxl.model.cfg.CfgUtils;

/**
 * 权限模型工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightUtils.java, V1.0.1 2014年1月30日 下午3:44:12 $
 */
public class RightUtils {
    /** 权限系统参数分类KEY */
    public static final String CATG = "RIGHT";

    /**
     * 权限模型对象转换
     */
    public static RightModel from(CfgModel srcObj) {
        if (srcObj == null) {
            return null;
        }

        RightModel dstObj = new RightModel();

        dstObj.setCode(srcObj.getName());
        dstObj.setName(srcObj.getTitle());
        dstObj.setSummary(srcObj.getValue());
        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 权限模型对象列表转换
     */
    public static List<RightModel> from(List<CfgModel> srcObjs) {
        if (srcObjs == null) {
            return null;
        }

        List<RightModel> dstObjs = new ArrayList<RightModel>();

        for (CfgModel srcObj : srcObjs) {
            RightModel dstObj = from(srcObj);
            if (dstObj != null) {
                dstObjs.add(dstObj);
            }
        }

        return dstObjs;
    }

    /**
     * 参数模型对象转换
     */
    public static CfgModel convert(RightModel srcObj) {
        if (srcObj == null) {
            return null;
        }

        CfgModel dstObj = new CfgModel();

        dstObj.setCatg(CATG);
        dstObj.setName(srcObj.getCode());
        dstObj.setTitle(srcObj.getName());
        dstObj.setValue(srcObj.getSummary());
        dstObj.setGmtCreate(srcObj.getGmtCreate());
        dstObj.setGmtModify(srcObj.getGmtModify());

        return dstObj;
    }

    /**
     * 查询单个权限模型
     */
    public static RightModel find(String name) {
        return from(CfgUtils.find(CATG, name));
    }

    /**
     * 查询所有权限模型
     */
    public static List<RightModel> find() {
        return from(CfgUtils.find(CATG));
    }
    
}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.xhelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * XHelper工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: XHelperUtils.java, V1.0.1 2013年11月25日 上午11:29:38 $
 * @deprecated 请使用{@code WebX}接口
 */
public class XHelperUtils {
    /** 工具类容器 */
    private static final ConcurrentMap<String, XHelper> helpers = new ConcurrentHashMap<String, XHelper>();

    /**
     * 初始化
     */
    public void init() {
        helpers.clear();
    }

    /**
     * 注册XHelper
     */
    public static void regist(XHelper helper) {
        regist(helper.findHelperName(), helper);
    }

    /**
     * 注册XHelper
     */
    public static void regist(String name, XHelper helper) {
        helpers.put(name, helper);
    }

    /**
     * 获取所有XHelper
     */
    public static Map<String, XHelper> findXHelpers() {
        return helpers;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.webx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * WebX工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: XHelperUtils.java, V1.0.1 2013年11月25日 上午11:29:38 $
 */
public class WebXUtils {
    /** 工具类容器 */
    private static final ConcurrentMap<String, WebX> xtools = new ConcurrentHashMap<String, WebX>();

    /**
     * 初始化
     */
    public void init() {
        xtools.clear();
    }

    /**
     * 注册WebX
     */
    public static void regist(WebX webx) {
        String name = webx.getClass().getSimpleName();
        if (webx instanceof NamedWebX) {
            name = ((NamedWebX) webx).findWebXName();
        }

        regist(name, webx);
    }

    /**
     * 注册WebX
     */
    public static void regist(String name, WebX webx) {
        xtools.put(name, webx);
    }

    /**
     * 获取所有WebX
     */
    public static Map<String, WebX> findAllWebX() {
        return xtools;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 定时器工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: TickTimerUtils.java, V1.0.1 2013年12月5日 上午11:22:27 $
 */
public final class TickTimerUtils {
    /** 工具类容器 */
    private static final ConcurrentMap<String, TickTimer> timers = new ConcurrentHashMap<String, TickTimer>();

    /**
     * 初始化
     */
    public void init() {
        timers.clear();
    }

    /**
     * 注册定时器
     */
    public static void regist(TickTimer timer) {
        regist(timer.name(), timer);
    }

    /**
     * 注册定时器
     */
    public static void regist(String name, TickTimer timer) {
        timers.put(name, timer);
        LogUtils.warn("[定时器]-注册[" + name + "]-[" + timer.getClass().getName() + "].");
    }

    /**
     * 获取所有定时器
     */
    public static Map<String, TickTimer> findTickTimers() {
        return timers;
    }

}

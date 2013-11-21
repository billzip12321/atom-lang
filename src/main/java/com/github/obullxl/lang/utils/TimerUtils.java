/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.util.Timer;

/**
 * 定时器工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: TimerUtils.java, V1.0.1 2013-2-16 下午7:25:56 $
 */
public final class TimerUtils {
    /** 定时器 */
    private static final Timer timer = initTimer();

    /**
     * 定时器初始化
     */
    private static Timer initTimer() {
        return new Timer();
    }

    /**
     * 获取定时器
     */
    public static Timer findTimer() {
        return timer;
    }

    /**
     * 停止定时器
     */
    public static void stopTimer() {
        timer.cancel();
    }
    
}

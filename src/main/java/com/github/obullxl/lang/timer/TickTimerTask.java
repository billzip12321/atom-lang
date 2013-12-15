/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import java.util.Date;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 定时调度器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TickTimerTask.java, V1.0.1 2013年12月5日 上午11:29:09 $
 */
public class TickTimerTask {

    /**
     * 执行调度
     */
    public void doTask() {
        Date now = new Date();
        for (TickTimer timer : TickTimerUtils.findTickTimers().values()) {
            try {
                timer.tick(now);
            } catch (Exception e) {
                LogUtils.warn("[定时器]-执行异常[" + timer.name() + "].", e);
            }
        }
    }

}

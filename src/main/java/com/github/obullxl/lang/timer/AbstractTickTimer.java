/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import java.util.Date;

import org.slf4j.Logger;

import com.github.obullxl.lang.timer.TickTimer;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 调度处理基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractTickTimer.java, V1.0.1 2014年5月31日 上午11:08:28 $
 */
public abstract class AbstractTickTimer implements TickTimer {
    public static final Logger logger = LogUtils.get();

    /** 执行时间间隔(秒) */
    private long               refresh;

    /** 最近执行时间 */
    private Date               execTime;

    /**
     * 定时器触发
     * 
     * @see com.github.obullxl.lang.timer.TickTimer#tick()
     */
    public final void tick() {
        Date now = new Date();

        // 检查是否需要执行
        if (!this.isMustExecute(now)) {
            return;
        }

        long start = now.getTime();
        String clazz = this.getClass().getSimpleName();
        boolean rtn = false;
        try {
            LogUtils.updateLogID();
            logger.warn("[定时调度]-开始执行[{}]业务操作[{}].", this.refresh, clazz);

            // 业务操作
            rtn = this.doTask(now);
        } finally {
            logger.warn("[定时调度]-业务操作[{}]执行完成[{}], 耗时[{}]ms.", new Object[] { clazz, rtn, (System.currentTimeMillis() - start) });
            LogUtils.removeLogID();
        }
    }

    /**
     * 执行任务
     */
    public abstract boolean doTask(Date now);

    /**
     * 进行业务操作检测
     */
    private boolean isMustExecute(Date now) {
        if (this.execTime == null) {
            this.execTime = DateUtils.toDateDW("1988-08-08");
        }

        if (now.getTime() - this.execTime.getTime() >= this.refresh) {
            this.execTime.setTime(now.getTime());
            return true;
        }

        return false;
    }

    // ~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~ //

    public void setRefresh(long refresh) {
        this.refresh = refresh;
    }

}

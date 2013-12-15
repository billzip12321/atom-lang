/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import java.util.Date;

import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 定时器基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractTickTimer.java, V1.0.1 2013年12月5日 上午11:11:18 $
 */
public abstract class AbstractTickTimer implements TickTimer {
    /** Logger */
    protected static final Logger logger = LogUtils.get();

    /** 时间间隔（毫秒） */
    private long                  interval;

    /** 开始时间 */
    private Date                  startTick;

    /** 触发次数 */
    private int                   tickCount;

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#name()
     */
    public String name() {
        return this.getClass().getName();
    }

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#tick(java.util.Date)
     */
    public void tick(Date start) {
        if (this.interval <= 0) {
            this.tickInternal(start);
            this.tickCount++;
        } else {
            if (this.startTick == null) {
                this.tickInternal(start);
                this.tickCount++;
            } else {
                if (start.getTime() - this.startTick.getTime() >= this.interval) {
                    this.tickInternal(start);
                    this.tickCount++;
                }
            }
        }

        this.startTick = start;
    }

    /**
     * 定时触发业务操作
     */
    public abstract void tickInternal(Date start);

    // ~~~~~~~~~~~~~~ 获取属性 ~~~~~~~~~~~~~~ //

    public Date getStartTick() {
        return startTick;
    }

    public int getTickCount() {
        return tickCount;
    }

    // ~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~ //

    public void setInterval(long interval) {
        this.interval = interval;
    }

}

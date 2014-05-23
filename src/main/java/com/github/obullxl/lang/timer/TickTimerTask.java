/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 定时调度器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TickTimerTask.java, V1.0.1 2013年12月5日 上午11:29:09 $
 */
public class TickTimerTask implements ApplicationContextAware {
    /** Logger */
    private static final Logger   logger = LogUtils.get();

    /** 工具类容器 */
    private final List<TickTimer> timers = new ArrayList<TickTimer>();

    /** 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        String[] names = context.getBeanNamesForType(TickTimer.class);
        if (names == null || names.length < 1) {
            return;
        }

        for (String name : names) {
            this.timers.add(context.getBean(name, TickTimer.class));
        }
    }

    /**
     * 执行调度
     */
    public void doTask() {
        for (TickTimer timer : this.timers) {
            try {
                timer.tick();
            } catch (Exception e) {
                logger.warn("[定时器]-执行异常[{}].", timer.getClass().getName(), e);
            }
        }
    }
}

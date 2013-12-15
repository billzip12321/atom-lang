/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 定时器Spring组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: TickTimerBean.java, V1.0.1 2013年12月5日 上午11:21:07 $
 */
public class TickTimerBean implements ApplicationContextAware {

    /** 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        String[] names = context.getBeanNamesForType(TickTimer.class);
        if (names == null || names.length < 1) {
            return;
        }

        for (String name : names) {
            TickTimer timer = context.getBean(name, TickTimer.class);
            TickTimerUtils.regist(timer);
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import java.util.Date;

/**
 * 定时器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TickTimer.java, V1.0.1 2013年12月5日 上午11:09:20 $
 */
public interface TickTimer {

    /**
     * 定时器名称
     */
    public String name();

    /**
     * 定时器触发
     * 
     * @param start 触发时间
     */
    public void tick(Date start);

}

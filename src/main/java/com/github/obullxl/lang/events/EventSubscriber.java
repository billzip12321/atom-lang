/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.events;

/**
 * 消息订阅者
 * 
 * @author obullxl@gmail.com
 * @version $Id: EventSubscriber.java, V1.0.1 2014年4月5日 上午11:54:57 $
 */
public interface EventSubscriber {

    /**
     * 接收消息
     */
    public boolean accept(String topic, String evtCode);

    /**
     * 消息处理
     */
    public boolean onEvent(UniformEvent event);

}

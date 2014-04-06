/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.events;

import java.util.List;

import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;
import com.google.common.eventbus.EventBus;

/**
 * 消息中心
 * 
 * @author obullxl@gmail.com
 * @version $Id: EventBusExt.java, V1.0.1 2014年4月5日 下午10:19:59 $
 */
public class EventBusExt {
    private static final Logger      logger   = LogUtils.get();

    /** 消息中心 */
    private final EventBus           eventBus = new EventBus("ATOM");

    /** 依赖注入 */
    private List<MessageListenerExt> listeners;

    /**
     * 初始化
     */
    public void init() {
        if (this.listeners != null) {
            for (MessageListenerExt listener : this.listeners) {
                this.eventBus.register(listener);

                logger.warn("[EventBus]-监听器: {}", listener.getClass().getName());
            }
        }
    }

    /**
     * 获取消息总线
     */
    public EventBus findEventBus() {
        return this.eventBus;
    }

    /**
     * 发送消息
     */
    public void post(Object object) {
        this.eventBus.post(object);
    }

    /**
     * 发送消息
     */
    public void postUniformEvent(UniformEvent event) {
        this.eventBus.post(event);
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setListeners(List<MessageListenerExt> listeners) {
        this.listeners = listeners;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 消息发送器
 * 
 * @author obullxl@gmail.com
 * @version $Id: MsgBroke.java, V1.0.1 2014年2月18日 下午12:14:57 $
 */
public class EventBroke implements ApplicationEventPublisherAware {

    /** 消息发送器 */
    private static ApplicationEventPublisher eventPublisher;

    /** 
     * @see org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher(org.springframework.context.ApplicationEventPublisher)
     */
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        eventPublisher = applicationEventPublisher;
    }

    /**
     * 发送消息
     */
    public static void publishEvent(UniformEvent event) {
        eventPublisher.publishEvent(event);
    }

}

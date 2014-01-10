/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.spring;

import org.springframework.context.ApplicationEvent;

/**
 * Servlet容器启动完成事件
 * 
 * @author obullxl@gmail.com
 * @version $Id: ServletReadyEvent.java, V1.0.1 2014年1月7日 下午12:16:36 $
 */
public class ServletReadyEvent extends ApplicationEvent {
    private static final long serialVersionUID = 2169455578477505099L;

    /**
     * @param source
     */
    public ServletReadyEvent(Object source) {
        super(source);
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.events;

/**
 * 消息事件常量
 * 
 * @author obullxl@gmail.com
 * @version $Id: EventConsts.java, V1.0.1 2014年2月18日 下午12:19:14 $
 */
public interface EventConsts {

    /**
     * 配置模型
     */
    public interface CFG {
        /** 主题 */
        public String TOPIC = "TP_CFG";
        
        /** 事件-新增 */
        public String CREATE = "EC_CFG_CREATE";
        
        /** 事件-更新 */
        public String UPDATE = "EC_CFG_UPDATE";
        
        /** 事件-删除 */
        public String REMOVE = "EC_CFG_REMOVE";
    }
    
}

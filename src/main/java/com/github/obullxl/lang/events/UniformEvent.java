/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.events;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.obullxl.lang.ToString;

/**
 * 统一事件对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: UniformEvent.java, V1.0.1 2014年2月18日 下午12:05:17 $
 */
public class UniformEvent extends ToString {
    private static final long   serialVersionUID = 531803174045100863L;

    /** 主题 */
    private String              topic;

    /** 事件码 */
    private String              evtCode;

    /** 消息内容 */
    private Object              payload;

    /** 扩展参数 */
    private Map<String, String> extMap;

    /**
     * CTOR
     */
    public UniformEvent() {
    }

    public UniformEvent(Object source) {
        this.payload = source;
    }

    public UniformEvent(String topic, String evtCode) {
        this(Boolean.TRUE);
        this.topic = topic;
        this.evtCode = evtCode;
    }

    public UniformEvent(Object source, String topic, String evtCode) {
        this(source);
        this.topic = topic;
        this.evtCode = evtCode;
    }

    public UniformEvent(Object source, String topic, String evtCode, Map<String, String> extMap) {
        this(source);
        this.topic = topic;
        this.evtCode = evtCode;
        this.extMap = extMap;
    }

    /**
     * 获取消息数据对象
     */
    public Object findPayload() {
        return this.payload;
    }

    /**
     * 获取并转换消息数据对象
     */
    @SuppressWarnings("unchecked")
    public <T> T castPayLoad() {
        return (T) this.findPayload();
    }

    // ~~~~~~~~~~~~~~~~ Getters and Setters ~~~~~~~~~~~~~~~~~ //

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEvtCode() {
        return evtCode;
    }

    public void setEvtCode(String evtCode) {
        this.evtCode = evtCode;
    }

    public Map<String, String> getExtMap() {
        if (this.extMap == null) {
            this.extMap = new ConcurrentHashMap<String, String>();
        }

        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

}

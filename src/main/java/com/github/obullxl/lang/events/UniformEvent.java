/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.events;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationEvent;

/**
 * 统一事件对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: UniformEvent.java, V1.0.1 2014年2月18日 下午12:05:17 $
 */
public class UniformEvent extends ApplicationEvent {
    private static final long   serialVersionUID = 531803174045100863L;

    /** 主题 */
    private String              topic;

    /** 事件码 */
    private String              evtCode;

    /** 扩展参数 */
    private Map<String, String> extMap;

    /**
     * CTOR
     */
    public UniformEvent(Object source) {
        super(source);
    }

    public UniformEvent(Object source, String topic, String evtCode) {
        super(source);
        this.topic = topic;
        this.evtCode = evtCode;
    }

    public UniformEvent(Object source, String topic, String evtCode, Map<String, String> extMap) {
        super(source);
        this.topic = topic;
        this.evtCode = evtCode;
        this.extMap = extMap;
    }

    /**
     * 获取消息数据对象
     */
    public Object findPayLoad() {
        return super.getSource();
    }

    /**
     * 获取并转换消息数据对象
     */
    @SuppressWarnings("unchecked")
    public <T> T castPayLoad() {
        return (T) this.findPayLoad();
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

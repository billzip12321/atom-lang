/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.biz;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.obullxl.lang.ToString;

/**
 * 请求返回结果
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseResponse.java, V1.0.1 2013-7-1 下午12:58:29 $
 */
public class BizResponse extends ToString implements Serializable {
    private static final long   serialVersionUID = 8526248715699921117L;

    /** 业务数据KEY常量 */
    public static final String  BIZ_ID_KEY       = "biz_id";

    /** 业务流水 */
    private String              bizLog;

    /** 成功标识 */
    private boolean             success;

    /** 返回代码 */
    private String              respCode;

    /** 返回文本 */
    private String              respDesp;

    /** 业务数据 */
    private Map<String, String> bizData          = new ConcurrentHashMap<String, String>();

    /**
     * CTOR
     */
    public BizResponse() {
    }

    public String getBizLog() {
        return bizLog;
    }

    public void setBizLog(String bizLog) {
        this.bizLog = bizLog;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesp() {
        return respDesp;
    }

    public void setRespDesp(String respDesp) {
        this.respDesp = respDesp;
    }

    public Map<String, String> getBizData() {
        if (this.bizData == null) {
            this.bizData = new ConcurrentHashMap<String, String>();
        }

        return bizData;
    }

    public void setBizData(Map<String, String> bizData) {
        this.bizData = bizData;
    }

}

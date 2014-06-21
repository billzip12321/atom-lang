/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.storex.impl;

import org.apache.commons.lang.Validate;

import com.github.obullxl.lang.storex.StoreX;

/**
 * 存储基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractStoreX.java, V1.0.1 2014年6月18日 下午8:28:36 $
 */
public abstract class AbstractStoreX implements StoreX {

    /** 配置-Bucket存储 */
    protected String bucket;

    /** 配置-AccessID */
    protected String accessId;

    /** 配置-AccessKey */
    protected String accessKey;

    /**
     * 初始化
     */
    public void init() {
        Validate.notEmpty(this.bucket, "[OSSStoreX]-目标Bucket注入失败！");
        Validate.notEmpty(this.accessId, "[OSSStoreX]-AccessID注入失败！");
        Validate.notEmpty(this.accessKey, "[OSSStoreX]-AccessKey注入失败！");
    }

    /**
     * 系统销毁
     */
    public void destroy() {
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~ //

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

}

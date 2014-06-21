/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.storex;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * X存储
 * 
 * @author obullxl@gmail.com
 * @version $Id: StoreX.java, V1.0.1 2014年6月13日 下午7:17:02 $
 */
public interface StoreX {
    public static final Logger logger       = LoggerFactory.getLogger("StoreX");

    /** 业务系统实现BEAN */
    public static final String STORE_X_BEAN = "store_x_impl";

    /**
     * 上传/更新文件
     * 
     * @param file 待上传的本地文件
     * @param objectKey 目标对象ID
     * @return 上传结果
     */
    public boolean store(File file, String objectKey);

    /**
     * 获取公开读的下载链接
     */
    public String findUrl(String objectKey);

    /**
     * 从服务器获取文件的下载链接
     * 
     * @param objectKey 目标对象ID
     * @return 文件的下载链接，超时时间为24小时
     */
    public String fetchUrl(String objectKey);

    /**
     * 从服务器获取文件的下载链接
     * 
     * @param objectKey 目标对象ID
     * @param timeout 链接有效时长（秒）
     * @return 文件的下载链接
     */
    public String fetchUrl(String objectKey, int timeout);

    /**
     * 删除文件
     * 
     * @param objectKey 目标对象ID
     * @return 删除结果
     */
    public boolean delete(String objectKey);

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.storex.impl;

import java.io.File;
import java.net.URI;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.utils.LogUtils;
import com.jcloud.jss.Credential;
import com.jcloud.jss.JingdongStorageService;

/**
 * 京东云JSS存储
 * 
 * @author obullxl@gmail.com
 * @version $Id: JSSStoreX.java, V1.0.1 2014年6月13日 下午7:27:21 $
 */
public class JSSStoreX extends AbstractStoreX {

    /** JSS客户端 */
    private JingdongStorageService client;

    /** 
     * @see com.github.obullxl.lang.storex.impl.AbstractStoreX#init()
     */
    public void init() {
        super.init();

        // 初始化
        Credential credential = new Credential(this.accessId, this.accessKey);
        this.client = new JingdongStorageService(credential);
    }

    /** 
     * @see com.github.obullxl.lang.storex.impl.AbstractStoreX#destroy()
     */
    public void destroy() {
        try {
            this.client.destroy();
        } catch (Exception e) {
            // ignore
        }
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#store(java.io.File, java.lang.String)
     */
    public boolean store(File file, String objectKey) {
        if (file == null || !file.exists() || !file.isFile()) {
            logger.warn("[上传文件]-文件[{}]不存在！", file);
            return false;
        }

        if (StringUtils.isBlank(objectKey)) {
            objectKey = file.getName();
            logger.warn("[上传文件]-文件ID不存在，以源文件名[{}]代替！", objectKey);
        }

        if (StringUtils.startsWith(objectKey, "/")) {
            objectKey = StringUtils.substringAfter(objectKey, "/");
        }

        String path = file.getAbsolutePath();
        logger.info("[上传文件]-源文件[{}], 文件ID[{}].", path, objectKey);

        long start = System.currentTimeMillis();
        LogUtils.updateLogID();
        try {
            this.client.bucket(this.bucket).object(objectKey).entity(file).put();
            return true;
        } catch (Exception e) {
            logger.error("[上传文件]-操作异常, 文件[{}]！", path, e);
            return false;
        } finally {
            logger.info("[上传文件]-耗时[{}]ms, 文件[{}].", (System.currentTimeMillis() - start), path);
            LogUtils.removeLogID();
        }
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#findUrl(java.lang.String)
     */
    public String findUrl(String objectKey) {
        return this.fetchUrl(objectKey);
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#fetchUrl(java.lang.String)
     */
    public String fetchUrl(String objectKey) {
        return this.fetchUrl(objectKey, 24 * 60 * 60);
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#fetchUrl(java.lang.String, long)
     */
    public String fetchUrl(String objectKey, int timeout) {
        if (StringUtils.isBlank(objectKey)) {
            logger.warn("[文件链接]-文件ID为空.", objectKey);
            return StringUtils.EMPTY;
        }

        if (timeout <= 0) {
            logger.warn("[文件链接]-文件有效期不合法, 文件ID[].", objectKey);
            return StringUtils.EMPTY;
        }

        logger.info("[文件链接]-文件ID[{}], 失效时间[{}].", objectKey, timeout);

        String linkUrl = StringUtils.EMPTY;
        long start = System.currentTimeMillis();
        LogUtils.updateLogID();
        try {
            URI uri = this.client.bucket(this.bucket).object(objectKey).generatePresignedUrl(timeout);
            linkUrl = uri.toString();
        } catch (Exception e) {
            logger.error("[文件链接]-操作异常, 文件ID[{}].", objectKey, e);
        } finally {
            logger.info("[文件链接]-耗时[{}]ms, 链接[{}].", (System.currentTimeMillis() - start), linkUrl);
            LogUtils.removeLogID();
        }

        // 返回
        return linkUrl;
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#delete(java.lang.String)
     */
    public boolean delete(String objectKey) {
        if (StringUtils.isBlank(objectKey)) {
            logger.warn("[删除文件]-文件ID为空.", objectKey);
            return false;
        }

        logger.info("[删除文件]-文件ID[{}]." + objectKey);

        long start = System.currentTimeMillis();
        LogUtils.updateLogID();
        try {
            this.client.bucket(this.bucket).object(objectKey).delete();
            return true;
        } catch (Exception e) {
            logger.error("[删除文件]-操作异常, 文件ID[{}].", objectKey, e);
            return false;
        } finally {
            logger.info("[删除文件]-耗时[{}]ms.", (System.currentTimeMillis() - start));
            LogUtils.removeLogID();
        }
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~ //

}

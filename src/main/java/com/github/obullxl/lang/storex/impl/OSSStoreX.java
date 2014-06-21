/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.storex.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 阿里云OSS存储
 * 
 * @author obullxl@gmail.com
 * @version $Id: OSSStoreX.java, V1.0.1 2014年6月13日 下午7:27:21 $
 */
public class OSSStoreX extends AbstractStoreX {

    /** 配置-存储主机 */
    private String storeHost = "oss-cn-hangzhou.aliyuncs.com";

    /** 
     * @see com.github.obullxl.lang.storex.impl.AbstractStoreX#init()
     */
    public void init() {
        super.init();
        Validate.notEmpty(this.storeHost, "[OSSStoreX]-存储主机注入失败！");
    }

    /**
     * 新建客户端
     */
    private OSSClient newClient() {
        return new OSSClient(this.accessId, this.accessKey);
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
            // 1. 计算文件的MD5
            InputStream input = null;
            String digest = StringUtils.EMPTY;
            try {
                input = new FileInputStream(file);
                digest = DigestUtils.md5Hex(input);
            } finally {
                IOUtils.closeQuietly(input);
            }

            // 2.1 设置目标文件名，即用户从OSS中下载时看到的文件名
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(file.length());
            meta.setHeader("Content-disposition", "attachment; filename=" + file.getName());

            // 2.2 上传
            input = new FileInputStream(file);
            PutObjectResult result = null;
            try {
                result = this.newClient().putObject(this.bucket, objectKey, input, meta);
            } finally {
                IOUtils.closeQuietly(input);
            }

            // 2.3 摘要比对
            String etag = result.getETag();
            if (!StringUtils.equalsIgnoreCase(etag, digest)) {
                logger.warn("[上传文件]-文件校验失败, 源[{}], 目标[{}].", digest, etag);
                return false;
            }

            // 上传成功
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
        if (StringUtils.startsWith(objectKey, "/")) {
            objectKey = StringUtils.substringAfter(objectKey, "/");
        }

        // http://azdai.oss-cn-hangzhou.aliyuncs.com/avatar/avatar.jpg
        return "http://" + this.bucket + "." + this.storeHost + "/" + objectKey;
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#fetchUrl(java.lang.String)
     */
    public String fetchUrl(String objectKey) {
        return this.fetchUrl(objectKey, 24 * 60 * 60);
    }

    /** 
     * @see com.github.obullxl.lang.storex.StoreX#fetchUrl(java.lang.String, int)
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

        if (StringUtils.startsWith(objectKey, "/")) {
            objectKey = StringUtils.substringAfter(objectKey, "/");
        }

        logger.info("[文件链接]-文件ID[{}], 失效时间[{}].", objectKey, timeout);

        String linkUrl = StringUtils.EMPTY;
        long start = System.currentTimeMillis();
        LogUtils.updateLogID();
        try {
            Date expire = new Date(System.currentTimeMillis() + timeout * 1000);
            URL url = this.newClient().generatePresignedUrl(this.bucket, objectKey, expire);
            linkUrl = url.toString();
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
            this.newClient().deleteObject(this.bucket, objectKey);
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

    public void setStoreHost(String storeHost) {
        this.storeHost = storeHost;
    }

}

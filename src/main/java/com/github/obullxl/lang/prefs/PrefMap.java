/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.prefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * 偏好对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: PrefMap.java, V1.0.1 2013-1-18 上午10:59:34 $
 */
public class PrefMap {
    private final Map<String, String> _map = new ConcurrentHashMap<String, String>();
    private final String              name;

    public PrefMap(String name) {
        this.name = name;
        this.init();
    }

    /**
     * 初始化
     */
    private void init() {
        String path = this.getFilePath();
        File file = new File(path + "/" + this.name);
        if(!file.exists()) {
            return;
        }
        
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            Properties props = new Properties();
            props.loadFromXML(input);
            this.toPrefMap(props);
        } catch (Exception e) {
            throw new RuntimeException("初始化PrefMap[" + this.name + "]异常.", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 存储内容
     */
    private void store() {
        String path = this.getFilePath();
        File file = new File(path + "/" + this.name);
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            Properties props = new Properties();
            this.toProperties(props);
            props.storeToXML(output, "", "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("存储PrefMap[" + this.name + "]异常.", e);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    /**
     * 根据Key取得值
     */
    public String get(String key) {
        return this._map.get(key);
    }

    /**
     * 设置Key/Value对
     */
    public void put(String key, String value) {
        this._map.put(key, value);
        this.store();
    }

    /**
     * 删除一个值
     */
    public String remove(String key) {
        String value = this._map.remove(key);
        this.store();
        
        return value;
    }
    
    /**
     * 删除所有值
     */
    public void removeAll() {
        this._map.clear();
        this.store();
    }

    /**
     * 偏好文件目录
     */
    private String getFilePath() {
        return FilenameUtils.normalizeNoEndSeparator(SystemUtils.getUserHome().getAbsolutePath());
    }

    /**
     * 映射转换
     */
    private void toPrefMap(Properties props) {
        for(Map.Entry<Object, Object> entry : props.entrySet()) {
            this._map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
    }
    
    /**
     * 映射转换
     */
    private void toProperties(Properties props) {
        for(Map.Entry<String, String> entry : this._map.entrySet()) {
            props.put(entry.getKey(), entry.getValue());
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置类
 * 
 * @author obullxl@gmail.com
 * @version $Id: Configration.java, V1.0.1 2013年11月15日 下午4:18:52 $
 */
public class Configration {
    /** 系统唯一标识符 */
    public static final String  SYS_IDENTIFY       = "identify";

    /** 本地主机名称 */
    public static final String  SYS_HOST_NAME      = "sys_host_name";
    /** 本机ip */
    public static final String  SYS_IP             = "sys_ip";
    /** 应用名称 */
    public static final String  SYS_APP_NAME       = "app_name";
    /** 运行模式 */
    public static final String  SYS_RUN_MODE       = "run_mode";

    public static final String  SYS_VERSION        = "sys_version";

    public static final String  LOG_CONFIG         = "log_config";

    /** key 常量 */
    public static final String  LOG4J_PATH         = "log4j_path";
    public static final String  CUSTOM_CONFIG_PATH = "custom-config-path";

    // ~~~~ 内部变量

    private Map<String, String> configs            = new LinkedHashMap<String, String>();

    // ~~~~  构造函数

    protected Configration() {
    }

    public Map<String, String> getConfig() {
        //对map进行clone，保证map的安全性
        Map<String, String> map = new HashMap<String, String>();
        map.putAll(this.configs);
        return map;
    }

    /**
     * 设置配置信息
     */
    public Configration setConfigs(Map<String, String> map) {
        this.configs.putAll(map);
        return this;
    }

    public String getPropertyValue(String key) {
        return this.configs.get(key);
    }

    /**
     * 设置配置属性
     */
    public Configration setProperty(String key, String value) {
        this.configs.put(key, value);
        return this;
    }

    public String getPropertyValue(String key, String defaultValue) {
        return this.configs.get(key) == null ? defaultValue : this.configs.get(key);
    }

    public String getSysAppName() {
        return getPropertyValue(SYS_APP_NAME);
    }

    public String getSysIp() {
        return getPropertyValue(SYS_IP);
    }

    public String getSysRunMode() {
        return getPropertyValue(SYS_RUN_MODE);
    }

    public String getSysHostName() {
        return getPropertyValue(SYS_HOST_NAME);
    }

    public String getSysVersion() {
        return getPropertyValue(SYS_VERSION);
    }

    public String getLogConfig() {
        return getPropertyValue(LOG_CONFIG);
    }
    
    public String getCustomConfigPath() {
        return getPropertyValue(CUSTOM_CONFIG_PATH);
    }

}

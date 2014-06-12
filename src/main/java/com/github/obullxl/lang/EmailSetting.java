/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang;

import java.util.Properties;

/**
 * 电子邮箱发送器设置
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmailSetting.java, V1.0.1 2014年6月4日 下午8:39:41 $
 */
public class EmailSetting extends ToString {
    private static final long serialVersionUID   = -536097548033089596L;

    /** 主机 */
    private String            host;

    /** 用户名 */
    private String            username;

    /** 密码 */
    private String            password;

    /** 默认编码 */
    private String            defaultEncoding    = "UTF-8";

    /** 扩展参数 */
    private Properties        extProperties      = new Properties();

    /** 属性参数 */
    private Properties        javaMailProperties = new Properties();

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getExtProperties() {
        return extProperties;
    }

    public void setExtProperties(Properties extProperties) {
        this.extProperties = extProperties;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public Properties getJavaMailProperties() {
        return javaMailProperties;
    }

    public void setJavaMailProperties(Properties javaMailProperties) {
        this.javaMailProperties = javaMailProperties;
    }

}

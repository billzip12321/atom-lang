/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import com.github.obullxl.lang.config.ConfigFactory;

/**
 * 环境配置值工厂
 * <p/>
 * 若对应的环境变量存在，则返回该变量的值；若不存在，则直接返回该变量！
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnvCfgFactoryBean.java, V1.0.1 2013年12月31日 上午9:55:05 $
 */
public class EnvCfgFactoryBean implements FactoryBean<String> {

    /** 参数KEY */
    private String key;

    /** 
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    public String getObject() throws Exception {
        String arg = this.key;
        String value = arg;
        if (StringUtils.startsWith(arg, "$")) {
            arg = StringUtils.substringAfter(arg, "$");
        } else {
            value = "$" + value;
        }

        return this.parseValue(arg, value);
    }

    /**
     * 循环解析参数值
     */
    private String parseValue(String key, String arg) {
        String temp = arg;
        
        if (StringUtils.equals(key, arg) || !StringUtils.startsWith(arg, "$")) {
            return temp;
        }
        
        if (StringUtils.startsWith(arg, "$")) {
            arg = StringUtils.substringAfter(arg, "$");
        }

        String value = ConfigFactory.get().getConfig().get(arg);
        if (value != null) {
            return this.parseValue(arg, StringUtils.trimToEmpty(value));
        }

        value = System.getenv(arg);
        if (value != null) {
            return this.parseValue(arg, StringUtils.trimToEmpty(value));
        }

        value = System.getProperty(arg);
        if (value != null) {
            return this.parseValue(arg, StringUtils.trimToEmpty(value));
        }

        return temp;
    }

    /**
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    public Class<?> getObjectType() {
        return String.class;
    }

    /** 
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    public boolean isSingleton() {
        return false;
    }

    // ~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setKey(String key) {
        this.key = key;
    }

}

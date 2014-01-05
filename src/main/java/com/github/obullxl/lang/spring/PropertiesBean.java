/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.spring;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 系统参数工具栏
 * 
 * @author obullxl@gmail.com
 * @version $Id: PropertiesBean.java, V1.0.1 2013年12月28日 下午1:46:03 $
 */
public class PropertiesBean implements InitializingBean {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /** 系统参数信息 */
    private Map<String, String> props;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        logger.warn("[配置参数]-参数集合: " + this.props);

        if (this.props == null || this.props.isEmpty()) {
            return;
        }

        for (Map.Entry<String, String> prop : props.entrySet()) {
            String key = StringUtils.trimToEmpty(prop.getKey());
            String value = StringUtils.trimToEmpty(prop.getValue());
            System.setProperty(key, value);
        }
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~ //

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

}

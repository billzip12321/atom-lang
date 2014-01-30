/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.webx;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * WebX组件
 * <p/>
 * <b>功能：</b>获取系统配置的WebX工具类，注册到{@code WebXUtils}中！
 * 
 * @author obullxl@gmail.com
 * @version $Id: XHelperBean.java, V1.0.1 2013年11月25日 上午11:29:25 $
 */
public class WebXBean implements ApplicationContextAware {

    /** 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        String[] names = context.getBeanNamesForType(WebX.class);
        if (names == null || names.length < 1) {
            return;
        }

        for (String name : names) {
            WebXUtils.regist(context.getBean(name, WebX.class));
        }
    }

}

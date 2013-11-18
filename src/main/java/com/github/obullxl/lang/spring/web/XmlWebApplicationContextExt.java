/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import java.io.IOException;

import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.github.obullxl.lang.spring.xml.XmlBeanDefinitionReaderExt;

/**
 * Web上下文处理器
 * 
 * @author obullxl@gmail.com
 * @version $Id: XmlWebApplicationContextExt.java, V1.0.1 2013年11月18日 下午7:35:38 $
 */
public class XmlWebApplicationContextExt extends XmlWebApplicationContext {

    /** 
     * @see org.springframework.web.context.support.XmlWebApplicationContext#loadBeanDefinitions(org.springframework.beans.factory.xml.XmlBeanDefinitionReader)
     */
    protected void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws IOException {
        XmlBeanDefinitionReader ext = new XmlBeanDefinitionReaderExt(reader.getBeanFactory());
        
        // Configure the bean definition reader with this context's
        // resource loading environment.
        ext.setEnvironment(this.getEnvironment());
        ext.setResourceLoader(this);
        ext.setEntityResolver(new ResourceEntityResolver(this));

        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            for (String configLocation : configLocations) {
                ext.loadBeanDefinitions(configLocation);
            }
        }
    }

}

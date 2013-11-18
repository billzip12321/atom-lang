/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.xml;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.xml.sax.InputSource;

import com.github.obullxl.lang.config.VelocityHelper;

/**
 * SpringXML组建加载器
 * 
 * @author obullxl@gmail.com
 * @version $Id: XmlBeanDefinitionReaderExt.java, V1.0.1 2013年11月18日 下午6:04:48 $
 */
public class XmlBeanDefinitionReaderExt extends XmlBeanDefinitionReader {

    /**
     * CTOR
     */
    public XmlBeanDefinitionReaderExt(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /** 
     * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader#doLoadBeanDefinitions(org.xml.sax.InputSource, org.springframework.core.io.Resource)
     */
    protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource) {
        InputStream input = null;
        try {
            input = inputSource.getByteStream();
            String encoding = inputSource.getEncoding();
            inputSource = new InputSource(VelocityHelper.get().evaluate(input));
            inputSource.setEncoding(encoding);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return super.doLoadBeanDefinitions(inputSource, resource);
    }

}

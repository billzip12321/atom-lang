/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.atom.core.lang.xml.XMLNode;
import com.atom.core.lang.xml.XMLUtils;

/**
 * XMLUtils工具类测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: XMLUtilsTest.java, V1.0.1 2013-2-25 上午11:57:08 $
 */
public class XMLUtilsTest {

    /**
     * XMLUtilsTest.xml
     */
    @Test
    public void test_XMLUtils() {
        InputStream input = null;
        try {
            input = XMLUtils.class.getResourceAsStream("/XMLUtilsTest.xml");
            XMLNode root = XMLUtils.toXMLNode(input);
            
            System.out.println(root);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
}

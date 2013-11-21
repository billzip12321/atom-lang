/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

/**
 * TemplateUtils测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: TemplateUtilsTest.java, V1.0.1 2013-3-24 下午2:39:27 $
 */
public class TemplateUtilsTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TemplateUtils.setTplPath(CfgUtils.findConfigPath() + "/cfgs/tpls");

        String t01 = TemplateUtils.render("Template01.html");
        System.out.println(t01);
    }

}

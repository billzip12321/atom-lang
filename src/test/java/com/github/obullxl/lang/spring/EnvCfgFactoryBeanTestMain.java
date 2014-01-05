/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnvCfgFactoryBeanTestMain.java, V1.0.1 2013年12月31日 上午10:17:04 $
 */
public class EnvCfgFactoryBeanTestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:/spring/env-cfg-test.xml");
    }

}

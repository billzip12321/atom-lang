/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: TickTimerTestMain.java, V1.0.1 2014年1月26日 下午4:28:38 $
 */
public class TickTimerTestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext(//
            "classpath:/spring/tick-timer-config.xml",//
            "classpath:/how-to-use/area-spring.xml",//
            "classpath:/how-to-use/catg-spring.xml",//
            "classpath:/how-to-use/cfg-spring.xml");
    }

}

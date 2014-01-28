/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.area;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: AreaServiceTestMain.java, V1.0.1 2014年1月26日 下午3:26:50 $
 */
public class AreaServiceTestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext("classpath:/how-to-use/area-spring.xml");
        AreaService areaService = actxt.getBean(AreaService.class);
        
        List<AreaDTO> areas = AreaUtils.root();
        Collections.sort(areas);
        System.out.println(areas);
    }

}

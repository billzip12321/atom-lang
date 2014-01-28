/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 系统参数服务测试主类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgServiceTestMain.java, V1.0.1 2014年1月26日 下午1:07:13 $
 */
public class CfgServiceTestMain {
    private static final Logger logger = LogUtils.get();

    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext(//
            "classpath:/how-to-use/cfg-spring.xml",//
            "classpath:/spring/tick-timer-config.xml");
        
        CfgService cfgService = actxt.getBean(CfgService.class);

        // 1.清理
        cfgService.remove();

        // 1.创建
        CfgDTO cfg = new CfgDTO();
        cfg.setCatg("system");
        cfg.setName("test01");
        cfg.setTitle("测试参数01");
        cfg.setValue("test-value-01");
        cfg.setValueExt("test-value-ext-01");

        cfgService.create(cfg);
    }

}

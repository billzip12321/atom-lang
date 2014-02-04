/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.List;

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
        
        // 系统参数
        CfgService cfgService = actxt.getBean(CfgService.class);

        cfgService.remove();

        CfgDTO cfg = new CfgDTO();
        cfg.setCatg("system");
        cfg.setName("test01");
        cfg.setTitle("测试参数01");
        cfg.setValue("test-value-01");
        cfg.setValueExt("test-value-ext-01");

        cfgService.create(cfg);
        
        
        // 权限模型
        RightService rgtService = actxt.getBean(RightService.class);
        rgtService.remove();

        RightDTO right = new RightDTO();
        right.setCode("right-code-test");
        right.setName("测试权限名称");

        rgtService.create(right);

        List<RightDTO> cfgs = RightUtils.find();
        logger.warn("RightService#find(): {}", cfgs);
    }

}

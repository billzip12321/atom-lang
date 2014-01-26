/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 系统参数服务测试主类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgServiceTestMain.java, V1.0.1 2014年1月26日 下午1:07:13 $
 */
public class CatgServiceTestMain {
    private static final Logger logger = LogUtils.get();

    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext("classpath:/how-to-use/catg-spring.xml");
        CatgService catgService = actxt.getBean(CatgService.class);

        // 1.清理
        catgService.remove();

        // 1.创建
        CatgDTO catg = new CatgDTO();
        catg.setCatg("system");
        catg.setCode("test01");
        catg.setSort("0001");
        catg.setTitle("测试参数01");
        catg.setExtMap("key1=value1|key2=value2");
        catg.setSummary("test-summary-01");

        catgService.create(catg);
    }

}

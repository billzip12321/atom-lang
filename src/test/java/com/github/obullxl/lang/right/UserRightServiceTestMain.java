/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.right;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 用户权限服务测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightServiceTestMain.java, V1.0.1 2014年1月29日 下午2:20:58 $
 */
public class UserRightServiceTestMain {
    private static final Logger logger = LogUtils.get();

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext(//
            "classpath:/how-to-use/urgt-spring.xml");

        UserRightService service = actxt.getBean(UserRightService.class);
        service.remove();

        List<UserRightDTO> urgts = service.find();
        logger.warn("UserRightService#find(): {}", urgts);
        
        UserRightDTO urgt = new UserRightDTO();
        urgt.setUserNo("2014010000000001");
        urgt.setNickName("老牛啊");
        urgt.setRgtCode("RGT_LOGIN_NORMAL");
        urgt.setRgtName("正常登录");
        urgt.setExtMap("{}");
        service.create(urgt);
        
        urgts = service.find();
        logger.warn("UserRightService#find(): {}", urgts);
    }

}

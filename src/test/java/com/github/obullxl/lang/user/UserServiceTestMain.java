/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.model.user.UserModel;
import com.github.obullxl.model.user.service.UserService;

/**
 * 用户模型测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserServiceTestMain.java, V1.0.1 2014年1月28日 下午1:26:02 $
 */
public class UserServiceTestMain {
    private static final Logger logger = LogUtils.get();

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext(//
            "classpath:/how-to-use/user-spring.xml");

        UserService service = actxt.getBean(UserService.class);

        String unique = "老牛啊";
        UserModel user = service.findByUnique(unique);
        logger.warn("UserService#findUnique('{}'): {}", unique, user);
        
        String no = "0001";
        int count = service.removeByNo(no);
        logger.warn("UserService#delete('{}'): {}", no, count);
    }

}

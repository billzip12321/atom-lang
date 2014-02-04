/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.relate;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 关系模型服务测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightServiceTestMain.java, V1.0.1 2014年1月29日 下午2:20:58 $
 */
public class RelateServiceTestMain {
    private static final Logger logger = LogUtils.get();

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext(//
            "classpath:/how-to-use/relate-spring.xml");

        // 论坛管理员
        ForumUserService forumUserService = actxt.getBean(ForumUserService.class);
        forumUserService.remove();

        List<ForumUserDTO> fusers = forumUserService.find();
        logger.warn("ForumUserService#find(): {}", fusers);

        ForumUserDTO fuser = new ForumUserDTO();
        fuser.setCode("forum-code");
        fuser.setName("测试论坛名称");
        fuser.setUserNo("2014010000000001");
        fuser.setNickName("老牛啊");
        fuser.setExtMap("{}");
        forumUserService.create(fuser);

        fusers = forumUserService.find();
        logger.warn("ForumUserService#find(): {}", fusers);

        // 用户权限
        UserRightService userRightService = actxt.getBean(UserRightService.class);
        userRightService.remove();

        List<UserRightDTO> urgts = userRightService.find();
        logger.warn("UserRightService#find(): {}", urgts);

        UserRightDTO urgt = new UserRightDTO();
        urgt.setUserNo("2014010000000001");
        urgt.setNickName("老牛啊");
        urgt.setRgtCode("RGT_LOGIN_NORMAL");
        urgt.setRgtName("正常登录");
        urgt.setExtMap("{}");
        userRightService.create(urgt);

        urgts = userRightService.find();
        logger.warn("UserRightService#find(): {}", urgts);
    }

}

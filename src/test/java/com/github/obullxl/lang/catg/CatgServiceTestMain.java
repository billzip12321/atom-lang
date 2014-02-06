/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.model.catg.forum.ForumModel;
import com.github.obullxl.model.catg.forum.ForumUtils;
import com.github.obullxl.model.catg.service.ForumService;

/**
 * 系统参数服务测试主类
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgServiceTestMain.java, V1.0.1 2014年1月26日 下午1:07:13 $
 */
public class CatgServiceTestMain {
    private static final Logger logger = LogUtils.get();

    public static void main(String[] args) {
        ApplicationContext actxt = new ClassPathXmlApplicationContext(//
            "classpath:/how-to-use/catg-spring.xml",//
            "classpath:/spring/tick-timer-config.xml");

        // 分类模型
        /*
        CatgService catgService = actxt.getBean(CatgService.class);

        catgService.remove();

        CatgDTO catg = new CatgDTO();
        catg.setCatg("system");
        catg.setCode("test01");
        catg.setSort("0001");
        catg.setTitle("测试参数01");
        catg.setExtMap("key1=value1|key2=value2");
        catg.setSummary("test-summary-01");

        catgService.create(catg);
        */

        // 论坛模型
        ForumService forumService = actxt.getBean(ForumService.class);

        forumService.remove();

        for (int i = 1; i <= 10; i++) {
            ForumModel forum = new ForumModel();
            forum.setCode("test-forum-" + i);
            forum.setSort("000" + i);
            forum.setTitle("安众贷测试论坛标题-" + i);
            // forum.setExtMap("key1=value1|key2=value2");
            forum.setSummary("安众贷测试论坛描述-" + i + "论坛的发展也如同网络雨后春笋般的出现，并迅速的发展壮大。论坛几乎涵盖了人们生活的各个方面，几乎每一个人都可以找到自己感兴趣或者需要了解的专题性论坛，而各类网站，综合性门户网站或者功能性专题网站也都青睐于开设自己的论坛，以促进网友之间的交流，增加互动性和丰富网站的内容。");

            forum.setTotalPost(10);
            forum.setTotalReply(20);
            forum.setTodayPost(5);
            forum.setTodayReply(10);

            forumService.create(forum);
        }

        List<ForumModel> forums = ForumUtils.find();
        logger.warn("ForumService#find(): {}", forums);
    }

}

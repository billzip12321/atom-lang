/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.Assert;

import com.github.obullxl.lang.catg.CatgService;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 论坛模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultForumService.java, V1.0.1 2014年1月30日 下午4:32:51 $
 */
public class DefaultForumService implements ForumService {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /** 分类模型服务 */
    private CatgService         catgService;

    /**
     * 初始化
     */
    public void init() {
        Assert.notNull(this.catgService, "[论坛服务]-分类服务[CatgService]注入失败！");

        try {
            ForumDTO forum = ForumUtils.from(this.catgService.find(CATG));
            if (forum == null) {
                forum = new ForumDTO();
                forum.setCatg(StringUtils.EMPTY);
                forum.setCode(CATG);
                forum.setTitle("论坛模型保留分类");
                forum.setSummary("论坛模型保留分类，请勿修改！");
                this.catgService.create(ForumUtils.to(forum));
            }
        } catch (Exception e) {
            logger.error("[论坛服务]-初始化根论坛分类异常！", e);
        }
    }

    /** 
     * @see com.github.obullxl.lang.forum.ForumService#create(com.github.obullxl.lang.forum.ForumDTO)
     */
    public void create(ForumDTO forum) {
        this.catgService.create(ForumUtils.to(forum));
    }

    /** 
     * @see com.github.obullxl.lang.forum.ForumService#update(com.github.obullxl.lang.forum.ForumDTO)
     */
    public void update(ForumDTO forum) {
        this.catgService.update(ForumUtils.to(forum));
    }

    /** 
     * @see com.github.obullxl.lang.forum.ForumService#remove()
     */
    public int remove() {
        return this.catgService.removeByCatg(CATG);
    }

    /** 
     * @see com.github.obullxl.lang.forum.ForumService#remove(java.lang.String)
     */
    public int remove(String code) {
        return this.catgService.remove(CATG, code);
    }

    // ~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~~~ //

    public void setCatgService(CatgService catgService) {
        this.catgService = catgService;
    }

}

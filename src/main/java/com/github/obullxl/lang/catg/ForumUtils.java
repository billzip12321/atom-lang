/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * 论坛模型工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumUtils.java, V1.0.1 2014年1月30日 下午4:04:32 $
 */
public class ForumUtils {
    /** 论坛分类模型KEY */
    public static final String CATG = "FORUMS";

    /**
     * 论坛模型转换
     */
    public static final ForumDTO from(CatgDTO catg) {
        if (catg == null) {
            return null;
        }

        ForumDTO forum = new ForumDTO();

        BeanUtils.copyProperties(catg, forum);

        catg.setCatg(CATG);
        forum.setExtMap(catg.getExtMap());

        return forum;
    }

    /**
     * 分类模型转换
     */
    public static final CatgDTO to(ForumDTO forum) {
        if (forum == null) {
            return null;
        }

        CatgDTO catg = new CatgDTO();

        BeanUtils.copyProperties(forum, catg);

        if (!StringUtils.equals(forum.getCode(), CATG)) {
            catg.setCatg(CATG);
        }

        catg.setExtMap(forum.getExtMap());

        return catg;
    }

    /**
     * 查询一级论坛模型
     */
    public static final List<ForumDTO> find() {
        List<ForumDTO> forums = new ArrayList<ForumDTO>();

        CatgDTO catg = CatgUtils.find(CATG);
        if (catg != null) {
            for (CatgDTO child : catg.getChildren()) {
                forums.add(from(child));
            }
        }
        
        Collections.sort(forums);
        return forums;
    }

    /**
     * 查询单个论坛模型
     */
    public static final ForumDTO find(String code) {
        CatgDTO catg = CatgUtils.find(CATG, code);
        return from(catg);
    }

}

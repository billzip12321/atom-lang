/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg.forum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.lang.MapExt;
import com.github.obullxl.model.catg.CatgModel;
import com.github.obullxl.model.catg.CatgUtils;

/**
 * 论坛模型工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumUtils.java, V1.0.1 2014年1月30日 下午4:04:32 $
 */
public class ForumUtils {
    /** 论坛分类模型KEY */
    public static final String CATG        = "FORUM";

    /** 当日主题数 */
    public static final String TODAY_POST  = "todayPost";

    /** 当日跟帖数KEY */
    public static final String TODAY_REPLY = "todayReply";

    /** 主题数 */
    public static final String TOTAL_POST  = "totalPost";

    /** 跟帖数 */
    public static final String TOTAL_REPLY = "totalReply";

    /**
     * 论坛模型转换
     */
    public static final ForumModel from(CatgModel catg) {
        if (catg == null) {
            return null;
        }

        ForumModel forum = new ForumModel();

        BeanUtils.copyProperties(catg, forum);

        MapExt mapExt = JSON.parseObject(catg.getExtMap(), MapExt.class);
        if (mapExt != null) {
            forum.setTodayPost(mapExt.getInt(TODAY_POST));
            forum.setTodayReply(mapExt.getInt(TODAY_REPLY));

            forum.setTotalPost(mapExt.getInt(TOTAL_POST));
            forum.setTotalReply(mapExt.getInt(TOTAL_REPLY));
        }

        return forum;
    }

    /**
     * 分类模型转换
     */
    public static final CatgModel from(ForumModel forum) {
        if (forum == null) {
            return null;
        }

        CatgModel catg = new CatgModel();

        BeanUtils.copyProperties(forum, catg);

        // 论坛分类只有一级
        if (!StringUtils.equals(forum.getCode(), CATG)) {
            catg.setCatg(CATG);
        }

        MapExt mapExt = new MapExt();
        mapExt.put(TODAY_POST, Integer.toString(forum.getTodayPost()));
        mapExt.put(TODAY_REPLY, Integer.toString(forum.getTodayReply()));
        mapExt.put(TOTAL_POST, Integer.toString(forum.getTotalPost()));
        mapExt.put(TOTAL_REPLY, Integer.toString(forum.getTotalReply()));

        catg.setExtMap(JSON.toJSONString(mapExt));

        return catg;
    }

    /**
     * 查询一级论坛模型
     */
    public static final List<ForumModel> find() {
        List<ForumModel> forums = new ArrayList<ForumModel>();

        CatgModel catg = CatgUtils.find(CATG);
        if (catg != null) {
            for (CatgModel child : catg.getChildren()) {
                forums.add(from(child));
            }
        }

        Collections.sort(forums);
        return forums;
    }

    /**
     * 查询单个论坛模型
     */
    public static final ForumModel find(String code) {
        CatgModel catg = CatgUtils.find(CATG, code);
        return from(catg);
    }

}

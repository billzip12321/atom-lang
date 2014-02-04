/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.lang.MapExt;
import com.github.obullxl.lang.catg.CatgDTO;

/**
 * 论坛模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumDTO.java, V1.0.1 2014年1月30日 下午4:06:33 $
 */
public class ForumDTO extends CatgDTO {
    private static final long serialVersionUID = 380260647192312964L;

    /** 论坛其他信息 */
    private MapExt            extras           = new MapExt();

    // ~~~~~~~~~~~~~~~~ 论坛分类 ~~~~~~~~~~~~~~~~ //

    public String getExtMap() {
        return JSON.toJSONString(this.extras);
    }

    public void setExtMap(String extMap) {
        super.setExtMap(extMap);
        this.extras = JSON.parseObject(extMap, MapExt.class);
    }

    /** 主题数 */
    public static final String TOTAL_POST = "totalPost";

    public int getTotalPost() {
        return this.extras.getInt(TOTAL_POST);
    }

    public void setTotalPost(int totalPost) {
        this.extras.put(TOTAL_POST, Integer.toString(totalPost));
    }

    /** 跟帖数 */
    public static final String TOTAL_REPLY = "totalReply";

    public int getTotalReply() {
        return this.extras.getInt(TOTAL_REPLY);
    }

    public void setTotalReply(int totalReply) {
        this.extras.put(TOTAL_REPLY, Integer.toString(totalReply));
    }

    /** 当日主题数 */
    public static final String TODAY_POST = "todayPost";

    public int getTodayPost() {
        return this.extras.getInt(TODAY_POST);
    }

    public void setTodayPost(int todayPost) {
        this.extras.put(TODAY_POST, Integer.toString(todayPost));
    }

    /** 当日跟帖数KEY */
    public static final String TODAY_REPLY = "todayReply";

    public int getTodayReply() {
        return this.extras.getInt(TODAY_REPLY);
    }

    public void setTodayReply(int todayReply) {
        this.extras.put(TODAY_REPLY, Integer.toString(todayReply));
    }

}

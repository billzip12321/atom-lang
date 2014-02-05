/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.catg.forum;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 论坛模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumModel.java, V1.0.1 2014年1月30日 下午4:06:33 $
 */
public class ForumModel extends BaseDTO implements Comparable<ForumModel> {
    private static final long serialVersionUID = 380260647192312964L;

    /** 分类代码 */
    private String            code;

    /** 排序值 */
    private String            sort;

    /** 分类说明 */
    private String            title;

    /** 分类描述 */
    private String            summary;

    /** 主题数 */
    private int               totalPost;

    /** 回复数 */
    private int               totalReply;

    /** 当日主题数 */
    private int               todayPost;

    /** 当日回复数 */
    private int               todayReply;

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(ForumModel dst) {
        if (this.getSort() == null) {
            return -1;
        }

        if (dst == null || dst.getSort() == null) {
            return 1;
        }

        return this.getSort().compareTo(dst.getSort());
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(int totalPost) {
        this.totalPost = totalPost;
    }

    public int getTotalReply() {
        return totalReply;
    }

    public void setTotalReply(int totalReply) {
        this.totalReply = totalReply;
    }

    public int getTodayPost() {
        return todayPost;
    }

    public void setTodayPost(int todayPost) {
        this.todayPost = todayPost;
    }

    public int getTodayReply() {
        return todayReply;
    }

    public void setTodayReply(int todayReply) {
        this.todayReply = todayReply;
    }

}

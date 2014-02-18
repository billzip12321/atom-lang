/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.msgbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.lang.biz.BaseDTO;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.model.user.UserKey;

/**
 * 消息信箱模型对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: MsgBoxModel.java, V1.0.1 2014年2月10日 下午3:33:12 $
 */
public class MsgBoxModel extends BaseDTO {
    private static final long serialVersionUID = 2654010092766248940L;

    /** ID */
    private String            id;

    /** 信箱分类 */
    private MsgBoxCatgEnum    catgEnum;

    /** 阅读状态 */
    private ValveBoolEnum     viewStateEnum;

    /** 阅读时间 */
    private Date              gmtView;

    /** 发送方 */
    private UserKey           postUser;

    /** 接收方 */
    private UserKey           takeUser;

    /** 多个接收方列表 */
    private List<UserKey>     takeUsers;

    /** 标题 */
    private String            title;

    /** 内容 */
    private String            content;

    /**
     * 格式化多个接收方为字符串
     */
    public String formatTakeUsers() {
        return JSON.toJSONString(this.getTakeUser());
    }

    /**
     * 解析字符串为多个接收方列表
     */
    public static List<UserKey> parseTakeUsers(String format) {
        return JSON.parseArray(format, UserKey.class);
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MsgBoxCatgEnum getCatgEnum() {
        return catgEnum;
    }

    public void setCatgEnum(MsgBoxCatgEnum catgEnum) {
        this.catgEnum = catgEnum;
    }

    public ValveBoolEnum getViewStateEnum() {
        return viewStateEnum;
    }

    public void setViewStateEnum(ValveBoolEnum viewStateEnum) {
        this.viewStateEnum = viewStateEnum;
    }

    public Date getGmtView() {
        return gmtView;
    }

    public void setGmtView(Date gmtView) {
        this.gmtView = gmtView;
    }

    public UserKey getPostUser() {
        return postUser;
    }

    public void setPostUser(UserKey postUser) {
        this.postUser = postUser;
    }

    public UserKey getTakeUser() {
        return takeUser;
    }

    public void setTakeUser(UserKey takeUser) {
        this.takeUser = takeUser;
    }

    public List<UserKey> getTakeUsers() {
        if (this.takeUsers == null) {
            this.takeUsers = new ArrayList<UserKey>();
        }

        return takeUsers;
    }

    public void setTakeUsers(List<UserKey> takeUsers) {
        this.takeUsers = takeUsers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.forumuser;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 论坛管理员
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumUserModel.java, V1.0.1 2014年1月30日 下午7:29:45 $
 */
public class ForumUserModel extends BaseDTO implements Comparable<ForumUserModel> {
    private static final long serialVersionUID = 3861567141310276632L;

    /** 论坛代码 */
    private String            code;

    /** 论坛名称 */
    private String            name;

    /** 管理员编号 */
    private String            userNo;

    /** 管理员昵称 */
    private String            nickName;

    /** 扩展参数 */
    private String            extMap;

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(ForumUserModel dst) {
        if (dst == null || dst.getUserNo() == null) {
            return 1;
        }

        return this.getUserNo().compareTo(dst.getUserNo());
    }

    // ~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~ //

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

}

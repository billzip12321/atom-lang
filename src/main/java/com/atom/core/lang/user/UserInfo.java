/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.user;

/**
 * 用户基本信息，用户不仅仅指网站注册用户或是系统登录用户，可以指任何一个实体对象！
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserInfo.java, V1.0.1 2013-1-18 上午10:59:34 $
 */
public class UserInfo {

    /** ID */
    private long   userId;
    
    /** 用户名 */
    private String userName;
    
    /** 用户密码 */
    private String userPasswd;

    /** ~~~~~~~ getters and setters */

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

}

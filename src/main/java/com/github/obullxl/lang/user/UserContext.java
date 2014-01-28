/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户基本信息
 * <p/>
 * 用户不仅仅指网站注册用户或是系统登录用户，可以指任何一个实体对象！
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserContext.java, V1.0.1 2013-1-18 上午10:59:34 $
 */
public class UserContext {

    /** NO */
    private String              userNo;

    /** 用户名 */
    private String              userName;

    /** 用户Email */
    private String              userEmail;

    /** 用户昵称 */
    private String              userNick;

    /** 用户登录信息 */
    private UserLogin           userLogin;

    /** 用户权限码 */
    private Set<String>         userRights  = new HashSet<String>();

    /** 用户扩展参数 */
    private Map<String, Object> userExtData = new ConcurrentHashMap<String, Object>();

    /**
     * CTOR
     */
    public UserContext() {
        this.userLogin = UserLogin.newMockLogin();
    }

    /**
     * 模拟用户上下文
     */
    public static final UserContext newMockContext() {
        UserContext mock = new UserContext();

        mock.setUserNo("0000000000");
        mock.setUserName("mock-user");
        mock.setUserEmail("mock@mock.com");
        mock.setUserNick("模拟用户");

        UserContextUtils.setLogin(mock, false);
        UserContextUtils.setAdmin(mock, false);

        return mock;
    }

    // ~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public Set<String> getUserRights() {
        return userRights;
    }

    public void setUserRights(Set<String> userRights) {
        this.userRights = userRights;
    }

    public Map<String, Object> getUserExtData() {
        return userExtData;
    }

    public void setUserExtData(Map<String, Object> userExtData) {
        this.userExtData = userExtData;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.util.Date;

import com.github.obullxl.lang.ToString;

/**
 * 登录信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserLogin.java, V1.0.1 2013年12月12日 上午9:31:12 $
 */
public class UserLogin extends ToString {
    private static final long serialVersionUID = 8214481188004276537L;

    /** 最后登录IP地址 */
    private String            lastIp;
    /** 本次登录IP地址 */
    private String            loginIp;

    /** 最后登录地点 */
    private String            lastLocate;
    /** 本次登录地点 */
    private String            loginLocate;

    /** 用户登录版本，N表示新版本，O表示老版本*/
    private String            loginVersion;

    /** 上一次登录时间 */
    private Date              lastDate         = new Date();
    /** 本次登录时间 */
    private Date              loginDate        = new Date();

    /**
     * 模拟登录信息
     */
    public static UserLogin newMockLogin() {
        UserLogin mock = new UserLogin();

        mock.setLastIp("0.0.0.0");
        mock.setLoginIp("0.0.0.0");

        mock.setLastLocate("-");
        mock.setLoginLocate("-");

        mock.setLoginVersion("0");

        return mock;
    }

    /** ~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ */

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLastLocate() {
        return lastLocate;
    }

    public void setLastLocate(String lastLocate) {
        this.lastLocate = lastLocate;
    }

    public String getLoginLocate() {
        return loginLocate;
    }

    public void setLoginLocate(String loginLocate) {
        this.loginLocate = loginLocate;
    }

    public String getLoginVersion() {
        return loginVersion;
    }

    public void setLoginVersion(String loginVersion) {
        this.loginVersion = loginVersion;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

}

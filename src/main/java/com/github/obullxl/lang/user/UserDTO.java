/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 用户对象模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserDTO.java, V1.0.1 2014年1月28日 上午9:07:12 $
 */
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = -420024505665136429L;

    /** 编号 */
    private String            no;

    /** 位状态 */
    private String            flag;

    /** 昵称/显示名称 */
    private String            nickName;

    /** 登录密码 */
    private String            passwd;

    /** 登录密码错误输入次数 */
    private int               passwdErrCount;

    /** 注册日期（格式：yyyy-MM-dd） */
    private String            registDate;

    /** 激活日期（格式：yyyy-MM-dd） */
    private String            activeDate;

    /** 认证日期（格式：yyyy-MM-dd） */
    private String            authDate;

    /** 手机号码 */
    private String            mobile;

    /** 电子邮箱 */
    private String            email;

    /** 真实姓名 */
    private String            realName;

    /** 出生日期 */
    private String            birthDate;

    /** 头像地址 */
    private String            avatarPath;

    /** 现居地邮政编码 */
    private String            postCode;

    /** 现居地省份代码 */
    private String            provinceCode;

    /** 现居地市代码 */
    private String            cityCode;

    /** 现居地县代码 */
    private String            countyCode;

    /** 现居地街道信息 */
    private String            streetInfo;

    // ~~~~~~~~~~~~~~~~~ Valve ~~~~~~~~~~~~~~~~~~~~~ //

    /** Valve */
    private UserValve         valve;

    /**
     * FetchValve
     */
    public UserValve findValve() {
        if (this.valve == null) {
            this.valve = new UserValve(this);
        }

        return this.valve;
    }

    /**
     * ResetValve
     */
    public UserDTO resetValve() {
        this.valve = null;
        return this;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getPasswdErrCount() {
        return passwdErrCount;
    }

    public void setPasswdErrCount(int passwdErrCount) {
        this.passwdErrCount = passwdErrCount;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getStreetInfo() {
        return streetInfo;
    }

    public void setStreetInfo(String streetInfo) {
        this.streetInfo = streetInfo;
    }

}

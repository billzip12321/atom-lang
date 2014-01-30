/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.right;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 用户授权模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightDTO.java, V1.0.1 2014年1月29日 下午1:28:42 $
 */
public class UserRightDTO extends BaseDTO {
    private static final long serialVersionUID = 8930521443805973799L;

    /** 用户编号 */
    private String            userNo;

    /** 用户昵称 */
    private String            nickName;

    /** 权限代码 */
    private String            rgtCode;

    /** 权限名称 */
    private String            rgtName;

    /** 扩展参数 */
    private String            extMap;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

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

    public String getRgtCode() {
        return rgtCode;
    }

    public void setRgtCode(String rgtCode) {
        this.rgtCode = rgtCode;
    }

    public String getRgtName() {
        return rgtName;
    }

    public void setRgtName(String rgtName) {
        this.rgtName = rgtName;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

}

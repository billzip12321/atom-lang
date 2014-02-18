/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.user;

import com.github.obullxl.lang.ToString;

/**
 * 用户关键信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserKey.java, V1.0.1 2014年2月10日 下午3:40:51 $
 */
public class UserKey extends ToString {
    private static final long serialVersionUID = -5932045170325892732L;

    /** 编号 */
    private String            no;

    /** 昵称 */
    private String            nickName;

    /**
     * 构建用户构建信息
     */
    public static UserKey to(String no, String nickName) {
        UserKey uk = new UserKey();
        uk.setNo(no);
        uk.setNickName(nickName);

        return uk;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}

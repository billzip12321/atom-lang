/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * MD5工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: MD5Utils.java, 2012-1-28 上午11:38:55 Exp $
 */
public final class MD5Utils {

    /**
     * 加密
     */
    public static final String digest(String source) {
        if (source == null) {
            source = StringUtils.EMPTY;
        }

        return DigestUtils.md5Hex(source);
    }

    /**
     * 加密
     */
    public static final String digest(String source, String salt) {
        return digest(salt + source + salt);
    }

    public static final void main(String[] args) {
        // root <-> 63a9f0ea7bb98050796b649e85481845
        // System.out.println(password("root"));

        // admin <-> 21232f297a57a5a743894a0e4a801fc3
        // System.out.println(password("admin"));
    }

}

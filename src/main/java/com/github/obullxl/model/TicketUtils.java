/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.utils.DateUtils;

/**
 * 统一ID值工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: TicketUtils.java, V1.0.1 2014年2月7日 下午5:29:48 $
 */
public class TicketUtils {

    /**
     * 通用模型ID
     * <br/>
     * yyyyMMdd+00+10位ID，共20位
     */
    public static String toModelID(long id) {
        try {
            String no = StringUtils.leftPad(Long.toString(id), 10, "0");
            return DateUtils.toStringDS(new Date()) + "00" + no;
        } catch (Exception e) {
            throw new RuntimeException("[通用模型]-格式化通用模型ID[" + id + "]异常！", e);
        }
    }

    /**
     * 用户模型编号
     * <br/>
     * yyyyMMdd+00+10位ID，共20位
     */
    public static String toUserNo(long id) {
        return toModelID(id);
    }

    /**
     * 主题模型ID
     * <br/>
     * yyyyMMdd+00+10位ID，共20位
     */
    public static String toTopicID(long id) {
        return toModelID(id);
    }

}

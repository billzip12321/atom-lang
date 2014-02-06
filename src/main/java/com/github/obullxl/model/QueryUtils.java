/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 模型查询工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: QueryUtils.java, V1.0.1 2014年2月6日 上午10:51:00 $
 */
public class QueryUtils {

    /**
     * 获取枚举查询代码
     */
    public static String findEnumCode(EnumBase enm) {
        if (enm == null) {
            return null;
        }

        return StringUtils.trimToNull(enm.code());
    }

    /**
     * 获取列表查询值
     */
    public static <T> List<T> findList(List<T> srcObjs) {
        if (CollectionUtils.isEmpty(srcObjs)) {
            return null;
        }

        return srcObjs;
    }

}

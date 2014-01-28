/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 枚举接口工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnumBaseUtils.java, V1.0.1 2013-7-20 下午2:29:36 $
 */
public class EnumBaseUtils {

    /**
     * 枚举转化为Map
     */
    public static Map<String, String> toMap(EnumBase[] enumms) {
        Map<String, String> map = new LinkedHashMap<String, String>();

        if (enumms != null) {
            for (EnumBase enumm : enumms) {
                map.put(enumm.code(), enumm.desp());
            }
        }

        return map;
    }

    /**
     * 枚举转化为Map
     */
    public static Map<String, EnumBase> toEnumMap(EnumBase[] enumms) {
        Map<String, EnumBase> map = new LinkedHashMap<String, EnumBase>();

        if (enumms != null) {
            for (EnumBase enumm : enumms) {
                map.put(enumm.code(), enumm);
            }
        }

        return map;
    }

}

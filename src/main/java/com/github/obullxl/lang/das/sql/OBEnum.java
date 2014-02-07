/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import org.apache.commons.lang.StringUtils;

/**
 * ORDER BY枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: OBEnum.java, V1.0.1 2014年2月7日 下午3:20:33 $
 */
public enum OBEnum {
    //
    ASC("ASC"),
    //
    DESC("DESC"),
    //
    ;

    private final String type;

    private OBEnum(String type) {
        this.type = type;
    }

    public static OBEnum findDefault(String code) {
        code = StringUtils.trimToEmpty(code);
        for (OBEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(code, enm.getType())) {
                return enm;
            }
        }

        return ASC;
    }

    public String getType() {
        return type;
    }
}

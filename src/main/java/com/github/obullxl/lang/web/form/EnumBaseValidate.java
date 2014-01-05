/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.web.form;

import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.enums.EnumBase;

/**
 * 枚举代码验证
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnumBaseValidate.java, V1.0.1 2014年1月1日 下午7:00:51 $
 */
public class EnumBaseValidate extends ToString {
    private static final long serialVersionUID = 8587369895142454562L;

    private String            property;
    private String            enumCode;
    private EnumBase[]        enums;

    private boolean           enumValue        = false;                ;

    public EnumBaseValidate() {
    }

    public EnumBaseValidate(String property, EnumBase[] enums) {
        this.property = property;
        this.enums = enums;
    }

    public EnumBaseValidate(String property, String enumCode, EnumBase[] enums) {
        this(property, enums);
        this.enumCode = enumCode;
        this.enumValue = true;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    public EnumBase[] getEnums() {
        return enums;
    }

    public void setEnums(EnumBase[] enums) {
        this.enums = enums;
    }

    public boolean isEnumValue() {
        return enumValue;
    }

    public void setEnumValue(boolean enumValue) {
        this.enumValue = enumValue;
    }

}

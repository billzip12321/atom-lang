/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.web.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;

import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.enums.EnumBase;

/**
 * 基础表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractForm.java, V1.0.1 2014年1月1日 下午6:19:30 $
 */
public abstract class AbstractForm extends ToString {
    private static final long serialVersionUID = 1940668197735695808L;

    /**
     * 获取需要验证的枚举
     */
    public abstract void enumBases(List<EnumBaseValidate> validates);

    /**
     * 验证枚举代码
     */
    public final void validateEnumBase(BindingResult errors) throws Exception {
        List<EnumBaseValidate> validates = new ArrayList<EnumBaseValidate>();
        this.enumBases(validates);

        if (validates == null || validates.isEmpty()) {
            return;
        }

        for (EnumBaseValidate validate : validates) {
            if (!validate.isEnumValue()) {
                this.validateEnumBase(validate.getProperty(), validate.getEnums(), errors);
            } else {
                this.validateEnumBase(validate.getProperty(), validate.getEnumCode(), validate.getEnums(), errors);
            }
        }
    }

    /**
     * 验证枚举代码
     */
    public final AbstractForm validateEnumBase(String property, EnumBase[] enums, BindingResult errors) throws Exception {
        String value = BeanUtils.getSimpleProperty(this, property);
        return this.validateEnumBase(property, value, enums, errors);
    }

    /**
     * 验证枚举代码
     */
    public final AbstractForm validateEnumBase(String property, String value, EnumBase[] enums, BindingResult errors) {
        if (errors != null) {
            boolean hasError = true;

            for (EnumBase enm : enums) {
                if (StringUtils.equals(value, enm.code())) {
                    hasError = false;
                    break;
                }
            }

            if (hasError) {
                errors.rejectValue(property, this.findErrorCode(property));
            }
        }

        return this;
    }

    private String findErrorCode(String property) {
        return this.getClass().getSimpleName() + "." + property + ".enumBase";
    }

}

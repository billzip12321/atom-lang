/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.spring;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.time.DateUtils;

/**
 * 日期属性编译器
 * 
 * @author obullxl@gmail.com
 * @version $Id: DatePropertyEditor.java, 2012-3-18 下午2:46:56 Exp $
 */
public class DatePropertyEditor extends PropertyEditorSupport {

    private static final String[] FORMATS = new String[] { "yyyyMMdd", "yyyy-MM-dd", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss" };

    /** 
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            this.setValue(DateUtils.parseDate(text, FORMATS));
        } catch (Exception e) {
            throw new IllegalArgumentException("Parse date[" + text + "] error!", e);
        }
    }

}

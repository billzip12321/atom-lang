/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.enums;

import com.atom.core.lang.ToString;

/**
 * 枚举基类默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultEnumBase.java, 2012-8-18 下午8:25:49 Exp $
 */
public class DefaultEnumBase extends ToString implements EnumBase {
    private static final long serialVersionUID = -5882463629304927587L;

    /** ID */
    private final int         id;
    /** Name */
    private final String      name;
    /** Code */
    private final String      code;
    /** Description */
    private final String      desp;

    /**
     * CTOR
     */
    public DefaultEnumBase(String code, String desp) {
        this(code, code, desp);
    }

    public DefaultEnumBase(String name, String code, String desp) {
        this(0, name, code, desp);
    }

    public DefaultEnumBase(int id, String name, String code, String desp) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.desp = desp;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.id;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#name()
     */
    public String name() {
        return this.name;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.code;
    }

    /** 
     * @see com.atom.core.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}

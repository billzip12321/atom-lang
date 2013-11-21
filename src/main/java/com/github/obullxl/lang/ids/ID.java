/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.ids;

/**
 * ID接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: ID.java, 2012-8-18 下午8:25:49 Exp $
 */
public interface ID<T> {

    /**
     * 获取ID
     */
    public T getId();

    /**
     * 设置ID
     */
    public void setId(T id);

}

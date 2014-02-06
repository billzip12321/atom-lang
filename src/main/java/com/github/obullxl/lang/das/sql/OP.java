/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

/**
 * JDBC操作枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: OperateEnum.java, V1.0.1 2014年2月6日 下午12:53:04 $
 */
public enum OP {
    //
    EQ("="),
    //
    NE("<>"),
    //
    LT("<"),
    //
    LE("<="),
    //
    GT(">"),
    //
    GE(">="),
    //
    IN(" IN "),
    //
    NI(" NOT IN "),
    //
    LK(" LIKE "),
    //
    NK(" NOT LIKE "),
    //
    ;

    private final String operate;

    private OP(String operate) {
        this.operate = operate;
    }

    public String getOperate() {
        return operate;
    }

}

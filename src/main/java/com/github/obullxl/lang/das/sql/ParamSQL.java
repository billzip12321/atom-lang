/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL参数
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamSQL.java, V1.0.1 2014年2月6日 下午12:48:52 $
 */
public interface ParamSQL {

    /**
     * Where条件，不带WHERE关键字
     */
    public String whereSQL();

    /**
     * 设置查询条件值
     * 
     * @param idx 从0开始
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException;

}

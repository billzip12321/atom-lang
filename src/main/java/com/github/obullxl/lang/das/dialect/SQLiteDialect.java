/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.dialect;

import com.github.obullxl.lang.das.JdbcDialect;

/**
 * SQLite方言
 * 
 * @author obullxl@gmail.com
 * @version $Id: SQLiteDialect.java, V1.0.1 2014年2月11日 上午10:26:41 $
 */
public class SQLiteDialect extends JdbcDialect {
    
    /**
     * 获取系统当前时间
     * 
     * @see com.github.obullxl.lang.das.JdbcDialect#sysNowSQL()
     */
    public String sysNowSQL() {
        return "DATETIME('NOW', 'LOCALTIME')";
    }
    
    /**
     * 获取分割字符串函数
     * 
     * @see com.github.obullxl.lang.das.JdbcDialect#subStringSQL()
     */
    public String subStringSQL() {
        return "SUBSTR";
    }
}

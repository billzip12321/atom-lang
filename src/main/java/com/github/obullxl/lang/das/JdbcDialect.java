/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.obullxl.lang.das.dialect.MySQLDialect;
import com.github.obullxl.lang.das.dialect.SQLiteDialect;

/**
 * DB断言
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcDialect.java, V1.0.1 2014年2月11日 上午10:19:15 $
 */
public abstract class JdbcDialect {
    /** 断言列表 */
    private static final Map<SQLDialect, JdbcDialect> dialects = new ConcurrentHashMap<SQLDialect, JdbcDialect>();

    static {
        dialects.put(SQLDialect.MYSQL, new MySQLDialect());
        dialects.put(SQLDialect.SQLITE, new SQLiteDialect());
    }

    /**
     * 获取断言
     */
    public static final JdbcDialect findJdbcDialect(SQLDialect dialect) {
        return dialects.get(dialect);
    }

    /**
     * 获取系统当前时间
     */
    public String sysNowSQL() {
        return "NOW()";
    }

    /**
     * 获取分割字符串函数
     */
    public String subStringSQL() {
        return "SUBSTRING";
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.github.obullxl.lang.das.sql.select.SelectSQL;

/**
 * SelectSQL单元测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: SelectSQLTest.java, V1.0.1 2014年2月6日 下午2:07:21 $
 */
public class SelectSQLTest {

    @Test
    public void test_whereSQL() {
        // 1.
        SelectSQL select = SelectSQL.start();
        String where = select.whereSQL();
        Assert.assertEquals("", where);

        // 2.
        select = SelectSQL.start();
        select.eq("field", "123");
        where = select.whereSQL();
        Assert.assertEquals("((field=?))", where);

        // 3.
        select = SelectSQL.start();
        select.eq("f1", "123");
        select.eq("f2", "123");
        where = select.whereSQL();
        Assert.assertEquals("((f1=?) AND (f2=?))", where);

        // 4.
        select = SelectSQL.start();
        select.eq("f1", "123");
        select.neq("f2", "123");
        where = select.whereSQL();
        Assert.assertEquals("((f1=?) AND (f2<>?))", where);

        // 5.
        select = SelectSQL.start();
        select.eq("f01", "123");
        select.neq("f02", "123");
        select.in("f07", Arrays.asList("1", "2", "3"));
        select.notin("f08", Arrays.asList("1", "2", "3", "4"));
        select.like("f09", "123");
        select.notlike("f10", "123");
        where = select.whereSQL();
        Assert.assertEquals("((f01=?) AND (f02<>?) AND (f07 IN (?,?,?)) AND (f08 NOT IN (?,?,?,?)) AND (f09 LIKE ?) AND (f10 NOT LIKE ?))", where);

        // 6.
        select = SelectSQL.start();
        select.eq("f01", "123");
        select.range("f02", "123", "456");
        where = select.whereSQL();
        Assert.assertEquals("((f01=?) AND (f02>=? AND f02<?))", where);

        // 7.
        select = SelectSQL.start();
        select.eq("f01", "123");
        select.range("f02", "123", null);
        where = select.whereSQL();
        Assert.assertEquals("((f01=?) AND (f02>=?))", where);

        // 8.
        select = SelectSQL.start();
        select.eq("f01", "123");
        select.range("f02", null, "456");
        where = select.whereSQL();
        Assert.assertEquals("((f01=?) AND (f02<?))", where);

        // 9.
        select = SelectSQL.start();
        select.select("f01,f02").from("tb");
        select.eq("f01", "123");
        select.range("f02", null, "456");
        select.orderby("f01").limit(10);
        where = select.findSQL();
        Assert.assertEquals("SELECT f01,f02 FROM tb WHERE ((f01=?) AND (f02<?)) ORDER BY f01 ASC LIMIT ?,?", where);

        // 10.
        select = SelectSQL.start();
        select.select("f01,f02").from("tb");
        select.eq("f01", "123");
        select.range("f02", null, "456");
        select.isnull("f03").isntnull("f04");
        select.orderby("f01", OBEnum.DESC);
        select.limit(10, 40);
        where = select.findSQL();
        Assert.assertEquals("SELECT f01,f02 FROM tb WHERE ((f01=?) AND (f02<?) AND (f03 IS NULL) AND (f04 IS NOT NULL)) ORDER BY f01 DESC LIMIT ?,?", where);

        // 10.
        select = SelectSQL.start();
        select.select("f01,f02").from("tb");
        select.eq("f01", "123");
        select.range("f02", null, "456");
        select.isnull("f03").isntnull("f04");
        select.orderby("f01", OBEnum.DESC);
        select.limit(10, 0);
        where = select.findSQL();
        Assert.assertEquals("SELECT f01,f02 FROM tb WHERE ((f01=?) AND (f02<?) AND (f03 IS NULL) AND (f04 IS NOT NULL)) ORDER BY f01 DESC", where);
    }

}

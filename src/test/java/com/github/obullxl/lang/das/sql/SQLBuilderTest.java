/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * SQLBuilder单元测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: SQLBuilderTest.java, V1.0.1 2014年2月6日 下午2:07:21 $
 */
public class SQLBuilderTest {

    @Test
    public void test_whereSQL() {
        // 1.
        SQLBuilder builder = SQLBuilder.newBuilder();
        String where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("", where);

        // 2.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "field", 123);
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((field=?))", where);

        // 3.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "f1", 123);
        builder.where(OP.EQ, "f2", "123");
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((f1=?) AND (f2=?))", where);

        // 4.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "f1", 123);
        builder.where(OP.NE, "f2", "123");
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((f1=?) AND (f2<>?))", where);

        // 5.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "f01", 123);
        builder.where(OP.NE, "f02", 123);
        builder.where(OP.LT, "f03", 123);
        builder.where(OP.LE, "f04", 123);
        builder.where(OP.GT, "f05", 123);
        builder.where(OP.GE, "f06", 123);
        builder.where(OP.IN, "f07", Arrays.asList("1", "2", "3"));
        builder.where(OP.NI, "f08", Arrays.asList("1", "2", "3", "4"));
        builder.where(OP.LK, "f09", 123);
        builder.where(OP.NK, "f10", 123);
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((f01=?) AND (f02<>?) AND (f03<?) AND (f04<=?) AND (f05>?) AND (f06>=?) AND (f07 IN (?,?,?)) AND (f08 NOT IN (?,?,?,?)) AND (f09 LIKE ?) AND (f10 NOT LIKE ?))", where);

        // 6.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "f01", 123);
        builder.where("f02", 123, 456);
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((f01=?) AND (f02>=? AND f02<?))", where);

        // 7.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "f01", 123);
        builder.where("f02", 123, null);
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((f01=?) AND (f02>=?))", where);

        // 8.
        builder = SQLBuilder.newBuilder();
        builder.where(OP.EQ, "f01", 123);
        builder.where("f02", null, 456);
        where = builder.finishBuilder().whereSQL();
        Assert.assertEquals("((f01=?) AND (f02<?))", where);
    }

}

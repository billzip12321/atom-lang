/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.das.sql.OBEnum;
import com.github.obullxl.lang.das.sql.OP;
import com.github.obullxl.lang.das.sql.ParamNULL;
import com.github.obullxl.lang.das.sql.ParamOrder;
import com.github.obullxl.lang.das.sql.ParamSQL;

/**
 * SELECT查询SQL
 * 
 * @author obullxl@gmail.com
 * @version $Id: SelectSQL.java, V1.0.1 2014年2月7日 下午1:26:12 $
 */
public class SelectSQL {

    /** 字段 */
    private String[]         fields;

    /** 数据表 */
    private String[]         tables;

    /** 查询条件 */
    private List<ParamSQL>   wheres = new ArrayList<ParamSQL>();

    /** 排序条件 */
    private List<ParamOrder> orders = new ArrayList<ParamOrder>();

    /** 分页条件 */
    private ParamLimit       limit;

    /**
     * CTOR
     */
    private SelectSQL() {
    }

    /**
     * 启动一个查询SQL
     */
    public static SelectSQL start() {
        return new SelectSQL();
    }

    /**
     * DSL-查询字段
     */
    public SelectSQL select(String... fields) {
        this.fields = fields;
        return this;
    }

    /**
     * DSL-查询数据表
     */
    public SelectSQL from(String... tables) {
        this.tables = tables;
        return this;
    }

    /**
     * DSL-IS NULL查询条件
     */
    public SelectSQL isnull(String field) {
        this.wheres.add(ParamNULL.to(OP.NULL, field));
        return this;
    }

    /**
     * DSL-IS NOT NULL查询条件
     */
    public SelectSQL isntnull(String field) {
        this.wheres.add(ParamNULL.to(OP.NOT_NULL, field));
        return this;
    }

    /**
     * DSL-String单值查询条件
     */
    public SelectSQL eq(String field, String value) {
        if (value != null) {
            this.wheres.add(ParamStringSingle.to(OP.EQ, field, value));
        }

        return this;
    }

    /**
     * DSL-String单值查询条件
     */
    public SelectSQL neq(String field, String value) {
        if (value != null) {
            this.wheres.add(ParamStringSingle.to(OP.NE, field, value));
        }

        return this;
    }

    /**
     * DSL-String查询区间条件
     */
    public SelectSQL range(String field, String begin, String finish) {
        if (begin != null || finish != null) {
            this.wheres.add(ParamStringRange.to(field, begin, finish));
        }

        return this;
    }

    /**
     * DSL-Date查询区间条件
     */
    public SelectSQL range(String field, Date begin, Date finish) {
        if (begin != null || finish != null) {
            this.wheres.add(ParamDateRange.to(field, begin, finish));
        }

        return this;
    }

    /**
     * DSL-String-IN查询条件
     */
    public SelectSQL in(String field, List<String> values) {
        if (CollectionUtils.isNotEmpty(values)) {
            this.wheres.add(ParamStringIN.to(OP.IN, field, values));
        }

        return this;
    }

    /**
     * DSL-String-NOT IN查询条件
     */
    public SelectSQL notin(String field, List<String> values) {
        if (CollectionUtils.isNotEmpty(values)) {
            this.wheres.add(ParamStringIN.to(OP.NI, field, values));
        }

        return this;
    }

    /**
     * DSL-String-LIKE查询条件
     */
    public SelectSQL like(String field, String value) {
        if (value != null) {
            this.wheres.add(ParamStringSingle.to(OP.LK, field, value));
        }

        return this;
    }

    /**
     * DSL-String-NOT LIKE查询条件
     */
    public SelectSQL notlike(String field, String value) {
        if (value != null) {
            this.wheres.add(ParamStringSingle.to(OP.NK, field, value));
        }

        return this;
    }

    /**
     * DSL-ORDER BY排序条件
     */
    public SelectSQL orderby(String field) {
        return this.orderby(field, OBEnum.ASC);
    }

    /**
     * DSL-ORDER BY排序条件
     */
    public SelectSQL orderby(String field, OBEnum value) {
        this.orders.add(ParamOrder.to(field, value));
        return this;
    }

    /**
     * DSL-LIMIT分页查询条件
     */
    public SelectSQL limit(int pageSize) {
        return this.limit(0, pageSize);
    }

    /**
     * DSL-LIMIT分页查询条件
     */
    public SelectSQL limit(int offset, int pageSize) {
        if (pageSize < 1) {
            return this;
        }

        this.limit = new ParamLimit();
        this.limit.setOffset(offset);
        this.limit.setPageSize(pageSize);

        return this;
    }

    /**
     * 获取整个查询SQL
     */
    public String findSQL() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        Iterator<String> iterate = Arrays.asList(this.fields).iterator();
        while (iterate.hasNext()) {
            sql.append(iterate.next());
            if (iterate.hasNext()) {
                sql.append(",");
            }
        }

        sql.append(" FROM ");
        iterate = Arrays.asList(this.tables).iterator();
        while (iterate.hasNext()) {
            sql.append(iterate.next());
            if (iterate.hasNext()) {
                sql.append(",");
            }
        }

        String where = this.whereSQL();
        if (StringUtils.isNotBlank(where)) {
            sql.append(" WHERE ").append(where);
        }

        String orderby = this.orderbySQL();
        if (StringUtils.isNotBlank(orderby)) {
            sql.append(" ORDER BY ").append(orderby);
        }

        if (this.limit != null) {
            sql.append(" LIMIT ").append(this.limit.whereSQL());
        }

        return sql.toString();
    }

    /**
     * 获取Where查询条件SQL
     */
    public String whereSQL() {
        StringBuilder sql = new StringBuilder();

        for (ParamSQL param : this.wheres) {
            if (sql.length() > 0) {
                sql.append(" AND ");
            }

            sql.append(param.whereSQL());
        }

        if (sql.length() > 0) {
            return "(" + sql.toString() + ")";
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取ORDER BY排序条件SQL
     */
    public String orderbySQL() {
        StringBuilder sql = new StringBuilder();

        for (ParamOrder param : this.orders) {
            if (sql.length() > 0) {
                sql.append(",");
            }

            sql.append(param.getField());
            sql.append(" ").append(param.getType().getType());
        }

        return sql.toString();
    }

    /**
     * 设置预编译查询条件值
     */
    public int stmtValue(PreparedStatement stmt) throws SQLException {
        int idx = 0;

        for (ParamSQL param : wheres) {
            idx = param.stmtValue(idx, stmt);
        }

        if (this.limit != null) {
            this.limit.stmtValue(idx, stmt);
        }

        return idx;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql;

/**
 * ORDER BY排序
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamOrder.java, V1.0.1 2014年2月7日 下午3:18:53 $
 */
public class ParamOrder {
    /** 排序字段 */
    private String field;

    /** 排序方式 */
    private OBEnum type = OBEnum.ASC;

    /**
     * CTOR
     */
    private ParamOrder() {
    }

    public static ParamOrder to(String field) {
        ParamOrder order = new ParamOrder();
        order.setField(field);

        return order;
    }

    public static ParamOrder to(String field, OBEnum type) {
        ParamOrder order = to(field);
        order.setType(type);

        return order;
    }

    // ~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~ //

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OBEnum getType() {
        return type;
    }

    public void setType(OBEnum type) {
        this.type = type;
    }

}

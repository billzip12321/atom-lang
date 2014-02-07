/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import com.github.obullxl.lang.das.sql.ParamRange;

/**
 * 默认对象查询区间
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamObjectRange.java, V1.0.1 2014年2月7日 下午2:45:13 $
 */
public class ParamObjectRange extends ParamRange<Object> {

    /**
     * CTOR
     */
    private ParamObjectRange() {
    }

    public static ParamObjectRange to(String field, Object valueBegin, Object valueFinish) {
        ParamObjectRange sql = new ParamObjectRange();

        sql.setField(field);
        sql.setValueBegin(valueBegin);
        sql.setValueFinish(valueFinish);

        return sql;
    }

}

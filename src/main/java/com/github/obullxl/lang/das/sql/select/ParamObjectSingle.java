/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import com.github.obullxl.lang.das.sql.OP;
import com.github.obullxl.lang.das.sql.ParamSingle;

/**
 * 默认单值对象查询
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamObjectSingle.java, V1.0.1 2014年2月7日 下午2:45:49 $
 */
public class ParamObjectSingle extends ParamSingle<Object> {
    
    /**
     * CTOR
     */
    private ParamObjectSingle() {
    }

    public static ParamObjectSingle to(OP operate, String field, Object value) {
        ParamObjectSingle sql = new ParamObjectSingle();
        
        sql.setOperate(operate);
        sql.setField(field);
        sql.setValue(value);

        return sql;
    }
    
}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.das.sql.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.obullxl.lang.das.sql.ParamSQL;

/**
 * LIMIT查询参数
 * 
 * @author obullxl@gmail.com
 * @version $Id: ParamLimit.java, V1.0.1 2014年2月7日 下午3:31:26 $
 */
public class ParamLimit implements ParamSQL {
    
    /** 起始行 */
    private int offset;

    /** 分页大小 */
    private int pageSize;
    
    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#whereSQL()
     */
    public String whereSQL() {
        return "?,?";
    }

    /** 
     * @see com.github.obullxl.lang.das.sql.ParamSQL#stmtValue(int, java.sql.PreparedStatement)
     */
    public int stmtValue(int idx, PreparedStatement stmt) throws SQLException {
        stmt.setInt(++idx, this.offset);
        stmt.setInt(++idx, this.pageSize);
        
        return idx;
    }

    // ~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~~~ //

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    

}

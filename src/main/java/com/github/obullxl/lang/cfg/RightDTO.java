/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 权限模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightDTO.java, V1.0.1 2014年1月31日 下午12:59:28 $
 */
public class RightDTO extends BaseDTO implements Comparable<RightDTO> {
    private static final long serialVersionUID = 9097915733122304370L;

    /** 权限代码 */
    private String            code;

    /** 权限名称 */
    private String            name;

    /** 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compareTo(RightDTO dst) {
        if (dst == null || dst.getCode() == null) {
            return 1;
        }

        return this.getCode().compareTo(dst.getCode());
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

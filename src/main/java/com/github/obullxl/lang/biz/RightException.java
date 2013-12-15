/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.biz;

import java.util.List;

/**
 * 权限异常
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightException.java, V1.0.1 2013年12月15日 下午12:41:19 $
 */
public class RightException extends RuntimeException {
    private static final long  serialVersionUID = 2468373106783815600L;

    /** 权限码 */
    private final List<String> rgtCodes;

    /**
     * CTOR
     */
    public RightException(List<String> rgtCodes) {
        super("权限不足-" + rgtCodes);
        this.rgtCodes = rgtCodes;
    }

    // ~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~ //

    public List<String> getRgtCodes() {
        return rgtCodes;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 业务异常
 * 
 * @author obullxl@gmail.com
 * @version $Id: BizException.java, V1.0.1 2013年11月21日 下午2:23:57 $
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 2789303855879089589L;

    /** 异常枚举 */
    private final EnumBase    errEnum;

    /**
     * CTOR
     */
    public BizException(EnumBase errEnum) {
        super(errEnum.code() + ":" + errEnum.desp());
        this.errEnum = errEnum;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~ //

    public EnumBase getErrEnum() {
        return errEnum;
    }

}

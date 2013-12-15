/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.xhelper;

import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * XHelper基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractXHelper.java, V1.0.1 2013年12月4日 上午9:48:45 $
 */
public abstract class AbstractXHelper implements XHelper {
    /** Logger */
    protected static final Logger logger = LogUtils.get();

    /** 
     * @see com.github.obullxl.lang.xhelper.XHelper#findHelperName()
     */
    public String findHelperName() {
        return this.getClass().getSimpleName();
    }

}

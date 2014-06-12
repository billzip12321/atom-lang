/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.sitex.impl;

import org.apache.commons.io.FilenameUtils;

/**
 * JDC站点配置
 * 
 * @author obullxl@gmail.com
 * @version $Id: JDCSiteX.java, V1.0.1 2014年6月9日 下午8:09:23 $
 */
public class JDCSiteX extends AbstractSiteX {

    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findName()
     */
    public String findName() {
        return "jd-app站点配置";
    }

    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findDataPath()
     */
    public String findDataPath() {
        String real = this.findServletContext().getRealPath("/");
        return FilenameUtils.normalizeNoEndSeparator(real, true);
    }

    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findSQLitePath()
     */
    public String findSQLitePath() {
        return this.findDataPath();
    }

}

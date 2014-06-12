/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.sitex.impl;

import org.apache.commons.io.FilenameUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: LocalSiteX.java, V1.0.1 2014年6月9日 下午8:09:23 $
 */
public class LocalSiteX extends AbstractSiteX {

    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findName()
     */
    public String findName() {
        return "LocalHost站点配置";
    }

    /** 
     * @see com.github.obullxl.lang.sitex.impl.AbstractSiteX#isEnvDevelopment()
     */
    public boolean isEnvDevelopment() {
        return true;
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

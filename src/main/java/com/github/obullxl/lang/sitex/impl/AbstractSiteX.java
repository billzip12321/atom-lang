/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.sitex.impl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import com.github.obullxl.lang.sitex.SiteX;
import com.github.obullxl.lang.web.WebContext;

/**
 * X站点基础配置
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractSiteX.java, V1.0.1 2014年6月9日 下午8:03:10 $
 */
public abstract class AbstractSiteX implements SiteX {

    /** 
     * @see com.azdai.biz.site.SiteX#findName()
     */
    public String findName() {
        return this.getClass().getSimpleName();
    }

    /** 
     * @see com.azdai.biz.site.SiteX#findServletConfig()
     */
    public ServletConfig findServletConfig() {
        return WebContext.getServletConfig();
    }
    
    /**
     * Servlet上下文
     */
    public ServletContext findServletContext() {
        return this.findServletConfig().getServletContext();
    }
    
    /** 
     * @see com.github.obullxl.lang.sitex.SiteX#isEnvDevelopment()
     */
    public boolean isEnvDevelopment() {
        return false;
    }

    /** 
     * @see com.azdai.biz.site.SiteX#findDataPath()
     */
    public abstract String findDataPath();

    /** 
     * @see com.azdai.biz.site.SiteX#findSQLitePath()
     */
    public abstract String findSQLitePath();

    /** 
     * @see com.azdai.biz.site.SiteX#findUploadPath()
     */
    public String findUploadPath() {
        return this.findDataPath() + "/upload";
    }

    /** 
     * @see com.azdai.biz.site.SiteX#findAvatarPath()
     */
    public String findAvatarPath() {
        return this.findDataPath() + "/avatar";
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.sitex.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * OpenShift站点配置
 * 
 * @author obullxl@gmail.com
 * @version $Id: RHCSiteX.java, V1.0.1 2014年6月9日 下午7:51:53 $
 */
public class RHCSiteX extends AbstractSiteX {

    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findName()
     */
    public String findName() {
        return "OpenShift站点配置";
    }
    
    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findDataPath()
     */
    public String findDataPath() {
        String path = System.getenv("OPENSHIFT_DATA_DIR");
        return FilenameUtils.normalizeNoEndSeparator(path, true);
    }

    /** 
     * @see com.azdai.biz.site.impl.AbstractSiteX#findSQLitePath()
     */
    public String findSQLitePath() {
        String env = System.getenv("OPENSHIFT_DATA_DIR");
        String id = StringUtils.substringBetween(env, "openshift/", "/app-root");
        return "/var/lib/openshift/" + id + "/app-root/runtime/dependencies/jbossews";
    }

}

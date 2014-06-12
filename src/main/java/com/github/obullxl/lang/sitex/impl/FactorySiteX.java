/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.sitex.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import com.github.obullxl.lang.sitex.SiteX;

/**
 * X站点代理
 * 
 * @author obullxl@gmail.com
 * @version $Id: ProxySiteX.java, V1.0.1 2014年6月9日 下午8:15:14 $
 */
public class FactorySiteX implements FactoryBean<SiteX> {

    /** 
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    public SiteX getObject() throws Exception {
        // OpenShift站点
        String env = System.getenv("OPENSHIFT_DATA_DIR");
        if (StringUtils.isNotBlank(env)) {
            return new RHCSiteX();
        }

        // jd-app站点
        env = System.getenv("VCAP_APPLICATION");
        if (StringUtils.isNotBlank(env)) {
            return new JDCSiteX();
        }

        // LocalHost站点
        return new LocalSiteX();
    }

    /** 
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    public Class<?> getObjectType() {
        return SiteX.class;
    }

    /** 
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    public boolean isSingleton() {
        return true;
    }

}

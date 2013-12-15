/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

/**
 * Velocity配置
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityConfigurerExt.java, V1.0.1 2013年12月6日 下午3:43:04 $
 */
public class VelocityConfigurerExt extends VelocityConfigurer {

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityConfigurer#postProcessVelocityEngine(org.apache.velocity.app.VelocityEngine)
     */
    protected void postProcessVelocityEngine(VelocityEngine engine) {
        super.postProcessVelocityEngine(engine);
    }

}

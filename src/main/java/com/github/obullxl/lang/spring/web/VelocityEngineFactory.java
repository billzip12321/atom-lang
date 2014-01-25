/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

/**
 * Velocity引擎工厂
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityEngineFactory.java, V1.0.1 2014年1月25日 下午2:51:45 $
 */
public class VelocityEngineFactory {

    /** Velocity配置 */
    private VelocityConfigurer velocityConfigurer;

    /**
     * 获取Velocity引擎
     */
    public VelocityEngine get() {
        return this.velocityConfigurer.getVelocityEngine();
    }

    // ~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~~ //

    public void setVelocityConfigurer(VelocityConfigurer velocityConfigurer) {
        this.velocityConfigurer = velocityConfigurer;
    }

}

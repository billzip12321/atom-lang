/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import org.springframework.util.ClassUtils;
import groovy.lang.GroovyClassLoader;

/**
 * Groovy工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: GroovyUtils.java, V1.0.1 2013年12月10日 下午4:14:26 $
 */
public class GroovyUtils {

    /**
     * 加载Groovy文件对象
     */
    public static Class<?> load(String groovyPath) {
        return load(groovyPath, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 加载Groovy文件对象
     */
    public static Class<?> load(String groovyPath, ClassLoader loader) {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(loader);
        try {
            return groovyClassLoader.parseClass(groovyPath);
        } finally {
            try {
                groovyClassLoader.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

}

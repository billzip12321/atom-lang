/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import groovy.lang.GroovyClassLoader;

import java.io.File;

import org.springframework.util.ClassUtils;

/**
 * Groovy工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: GroovyUtils.java, V1.0.1 2013年12月10日 下午4:14:26 $
 */
public class GroovyUtils {

    /**
     * 加载Groovy脚本内容
     */
    public static Class<?> load(String content) {
        return load(content, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 加载Groovy脚本内容
     */
    public static Class<?> load(String content, ClassLoader loader) {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(loader);
        try {
            return groovyClassLoader.parseClass(content);
        } finally {
            try {
                groovyClassLoader.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    /**
     * 加载Groovy文件内容
     */
    public static Class<?> load(File file) throws Exception {
        return load(file, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 加载Groovy脚本内容
     */
    public static Class<?> load(File file, ClassLoader loader) throws Exception {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(loader);
        try {
            return groovyClassLoader.parseClass(file);
        } finally {
            try {
                groovyClassLoader.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.crawl;

import com.github.obullxl.lang.utils.GroovyUtils;

/**
 * Groovy爬虫工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: GroovyCrawlUtils.java, V1.0.1 2013年12月10日 下午4:40:10 $
 */
public class GroovyCrawlUtils {

    /**
     * 获取Groovy脚本爬虫
     */
    public static WebCrawler findCrawler(String path) {
        try {
            Class<?> clz = GroovyUtils.load(path, WebCrawler.class.getClassLoader());
            return (WebCrawler) clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("获取Web爬虫异常[" + path + "].", e);
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.crawl;

import java.util.HashMap;
import java.util.List;

import com.github.obullxl.lang.web.crawl.support.CnblogsWebCrawler;

/**
 * cnblogs爬虫
 * 
 * @author obullxl@gmail.com
 * @version $Id: CnblogsWebCrawlerMain.java, V1.0.1 2013年12月10日 下午12:32:25 $
 */
public class CnblogsWebCrawlerMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CnblogsWebCrawler crawler = new CnblogsWebCrawler();
        List<CrawlData> data = crawler.crawl("http://www.cnblogs.com/obullxl/p/mybatis-integration-with-spring.html", new HashMap<String, String>());
        System.out.println("------------------------------------");
        System.out.println(data.get(0).getSummary());
        System.out.println("------------------------------------");
        System.out.println(data.get(0).getContent());
    }

}

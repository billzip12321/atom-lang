/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.crawl;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

/**
 * 网络爬虫
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebCrawler.java, V1.0.1 2013年12月10日 上午11:07:57 $
 */
public interface WebCrawler {

    /**
     * 爬虫处理
     */
    public List<CrawlData> crawl(String url, Map<String, String> args);

    /**
     * 获取链接
     */
    public List<String> parseLinks(String url);

    /**
     * 解析标题
     */
    public String parseTitle(Document document);

    /**
     * 解析摘要
     */
    public String parseSummary(Document document);

    /**
     * 解析内容
     */
    public String parseContent(Document document);

}

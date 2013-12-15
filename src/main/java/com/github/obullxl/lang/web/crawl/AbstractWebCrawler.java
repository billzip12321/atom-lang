/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.crawl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 爬虫处理基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractWebCrawler.java, V1.0.1 2013年12月10日 上午11:42:17 $
 */
public abstract class AbstractWebCrawler implements WebCrawler {
    /** Logger */
    protected static final Logger logger = LogUtils.get();

    /** 
     * @see com.github.obullxl.lang.web.crawl.WebCrawler#crawl(java.lang.String, java.util.Map)
     */
    public List<CrawlData> crawl(String url, Map<String, String> args) {
        List<CrawlData> crawls = new ArrayList<CrawlData>();

        List<String> links = this.parseLinks(url);
        for (String link : links) {
            try {
                CrawlData data = new CrawlData();
                data.setUrl(link);
                crawls.add(data);

                // HTML
                Document document = Jsoup.connect(link).data(args).get();

                // 标题
                data.setTitle(this.parseTitle(document));

                // 摘要
                data.setSummary(this.parseSummary(document));

                // 内容
                data.setContent(this.parseContent(document));
            } catch (Exception e) {
                logger.error("[爬虫异常]-URL[], Link[{}], Args[{}].", url, link, args, e);
            }
        }

        return crawls;
    }

}

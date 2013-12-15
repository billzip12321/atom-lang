/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.crawl.support;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.github.obullxl.lang.utils.TextUtils;
import com.github.obullxl.lang.web.crawl.AbstractWebCrawler;

/**
 * cnblogs爬虫处理程序
 * 
 * @author obullxl@gmail.com
 * @version $Id: CnblogsWebCrawler.java, V1.0.1 2013年12月10日 下午12:18:52 $
 */
public class CnblogsWebCrawler extends AbstractWebCrawler {

    /** 
     * @see com.github.obullxl.lang.web.crawl.WebCrawler#parseLinks(java.lang.String)
     */
    public List<String> parseLinks(String url) {
        return Arrays.asList(url);
    }
    
    /** 
     * @see com.github.obullxl.lang.web.crawl.WebCrawler#parseTitle(org.jsoup.nodes.Document)
     */
    public String parseTitle(Document document) {
        // MyBatis3与Spring3无缝集成-从iBatis平滑过渡 - 老牛啊 - 博客园
        String title = StringUtils.trim(document.title());

        // MyBatis3与Spring3无缝集成-从iBatis平滑过渡 - 老牛啊
        title = StringUtils.trim(StringUtils.substringBeforeLast(title, "-"));

        // MyBatis3与Spring3无缝集成-从iBatis平滑过渡
        title = StringUtils.trim(StringUtils.substringBeforeLast(title, "-"));

        return title;
    }

    /** 
     * @see com.github.obullxl.lang.web.crawl.WebCrawler#parseSummary(org.jsoup.nodes.Document)
     */
    public String parseSummary(Document document) {
        Element body = document.body();
        if (body == null) {
            return StringUtils.EMPTY;
        }

        Element blog = body.getElementById("cnblogs_post_body");
        if (blog == null) {
            return StringUtils.EMPTY;
        }

        String summary = StringUtils.trim(StringUtil.normaliseWhitespace(blog.text()));
        return TextUtils.truncate(summary, 255);
    }

    /** 
     * @see com.github.obullxl.lang.web.crawl.WebCrawler#parseContent(org.jsoup.nodes.Document)
     */
    public String parseContent(Document document) {
        Element body = document.body();
        if (body == null) {
            return StringUtils.EMPTY;
        }

        Element blog = body.getElementById("cnblogs_post_body");
        if (blog == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.trim(blog.html());
    }

}

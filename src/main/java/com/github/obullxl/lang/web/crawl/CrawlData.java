/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.web.crawl;

import com.github.obullxl.lang.ToString;

/**
 * 爬虫抓取解析数据
 * 
 * @author obullxl@gmail.com
 * @version $Id: CrawlData.java, V1.0.1 2013年12月10日 上午11:20:46 $
 */
public class CrawlData extends ToString {
    private static final long serialVersionUID = 5417830224718694264L;

    /** URL */
    private String            url;

    /** 标题 */
    private String            title;

    /** 摘要 */
    private String            summary;

    /** 内容 */
    private String            content;

    // ~~~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~~ //

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

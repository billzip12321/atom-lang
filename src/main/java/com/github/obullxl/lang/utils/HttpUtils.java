/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpStatus;

/**
 * HTTP工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: HttpUtils.java, V1.0.1 2014年6月16日 下午7:21:36 $
 */
public class HttpUtils {

    /** HTTP客户端 */
    private static final CloseableHttpClient httpClient = initHttpClient();

    /**
     * 初始化
     */
    private static final CloseableHttpClient initHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        return httpClient;
    }

    /**
     * 获取HTTP客户端
     */
    public static final CloseableHttpClient findHttpClient() {
        return httpClient;
    }

    /**
     * 获取GET内容
     */
    public static final String get(String url) throws Exception {
        String body = StringUtils.EMPTY;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = findHttpClient().execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (HttpStatus.OK.value() == code) {
                body = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            }
        } finally {
            IOUtils.closeQuietly(response);
        }

        return body;
    }

    /**
     * 获取POST内容
     */
    public static final String post(String url) throws Exception {
        String body = StringUtils.EMPTY;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            response = findHttpClient().execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            if (HttpStatus.OK.value() == code) {
                body = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            }
        } finally {
            IOUtils.closeQuietly(response);
        }

        return body;
    }

}

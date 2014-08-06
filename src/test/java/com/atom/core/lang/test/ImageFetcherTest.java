/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.core.lang.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpStatus;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageFetcherTest.java, V1.0.1 2014年7月6日 下午1:08:13 $
 */
public class ImageFetcherTest {

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
        HttpHost localhost = new HttpHost("vvtrader.com", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        return httpClient;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost("http://206.190.141.8:911/image/uploadimage/20140505/63f84e3b-6e82-4148-b6ff-603ed4bc7f4e.jpg");
            // httpPost.setHeader("Origin", "http://www.vvtrader.com/index.aspx");
            // httpPost.setHeader("Referer", "http://www.vvtrader.com/index.aspx");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.52 Safari/536.5");
            response = httpClient.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            if (HttpStatus.OK.value() == code) {
                InputStream input = null;
                OutputStream output = null;
                try {
                    input = response.getEntity().getContent();
                    output = new FileOutputStream("c:/img-test-03.jpg");
                    IOUtils.copy(input, output);
                } finally {
                    IOUtils.closeQuietly(output);
                    IOUtils.closeQuietly(input);
                }
            }
        } finally {
            IOUtils.closeQuietly(response);
            httpClient.close();
        }
    }

}

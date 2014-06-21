/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.obullxl.lang.area.IPArea;

/**
 * IP工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: IPUtils.java, V1.0.1 2014年6月17日 下午8:44:58 $
 */
public class IPUtils {
    private static final Logger logger = LogUtils.get();

    /**
     * 根据IP查询区域
     */
    public static final IPArea findArea(String ip) {
        IPArea location = new IPArea();
        try {
            URI uri = new URIBuilder() //
                .setScheme("http") //
                .setHost("ip.taobao.com") //
                .setPath("/service/getIpInfo.php")//
                .setParameter("ip", ip).build();

            String message = HttpUtils.get(uri.toString());
            JSONObject json = JSON.parseObject(message);
            if (StringUtils.equals(json.getString("code"), "0")) {
                json = JSON.parseObject(json.getString("data"));
                location.setIp(ip);
                location.setArea(json.getString("area"));
                location.setCountry(json.getString("country"));
                location.setRegion(json.getString("region"));
                location.setCity(json.getString("city"));
                location.setIsp(json.getString("isp"));
            }
        } catch (Exception e) {
            logger.error("[IP]-查询IP[{}]所在区域异常！", ip, e);
        }

        return location;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.area;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.ToString;

/**
 * IP区域
 * 
 * @author obullxl@gmail.com
 * @version $Id: IPArea.java, V1.0.1 2014年6月17日 下午8:57:50 $
 */
public class IPArea extends ToString {
    private static final long serialVersionUID = 5904367728037714175L;

    /** IP */
    private String            ip;

    /** 区域-华东、华北等 */
    private String            area;

    /** 国家 */
    private String            country;

    /** 省（自治区或直辖市）*/
    private String            region;

    /** 市（县） */
    private String            city;

    /** 运营商 */
    private String            isp;

    /**
     * CTOR
     */
    public IPArea() {
    }

    public IPArea(String ip) {
        this.ip = ip;
    }

    /**
     * IP数据是否正常
     */
    public boolean isValid() {
        return StringUtils.isNotBlank(this.ip);
    }

    /**
     * 获取地理位置
     */
    public String findLocation() {
        String location = StringUtils.EMPTY;
        if (this.isValid()) {
            location = this.region;
            
            if (!StringUtils.equals(this.region, this.city)) {
                location = location + " " + this.city;
            }
        }

        return StringUtils.trim(location);
    }

    // ~~~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

}

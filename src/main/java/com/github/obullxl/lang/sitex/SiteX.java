/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.sitex;

import javax.servlet.ServletConfig;

/**
 * X站点
 * 
 * @author obullxl@gmail.com
 * @version $Id: SiteX.java, V1.0.1 2014年6月9日 下午7:41:26 $
 */
public interface SiteX {

    /** 业务系统实现BEAN */
    public static final String SITE_X_BEAN = "site_x_impl";

    /**
     * 站点名称
     */
    public String findName();

    /**
     * Servlet配置
     */
    public ServletConfig findServletConfig();

    /**
     * 检测是否为开发环境
     */
    public boolean isEnvDevelopment();

    /**
     * 数据目录
     */
    public String findDataPath();

    /**
     * SQLite目录
     */
    public String findSQLitePath();

    /**
     * 文件上传目录
     */
    public String findUploadPath();

    /**
     * 会员头像目录
     */
    public String findAvatarPath();

}

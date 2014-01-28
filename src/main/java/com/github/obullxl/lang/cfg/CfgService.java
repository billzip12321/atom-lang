/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;


/**
 * 系统参数服务
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgService.java, V1.0.1 2014年1月26日 上午11:40:49 $
 */
public interface CfgService {

    /**
     * 刷新缓存
     */
    public void onRefresh();

    /**
     * 新增系统参数
     */
    public void create(CfgDTO cfg);

    /**
     * 更新系统参数
     */
    public void update(CfgDTO cfg);

    /**
     * 删除系统参数
     */
    public void remove();

    /**
     * 删除系统参数
     */
    public void remove(String catg);

    /**
     * 删除系统参数
     */
    public void remove(String catg, String name);

}

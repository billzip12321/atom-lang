/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg.service;

import com.github.obullxl.model.cfg.CfgModel;

/**
 * 参数模型服务
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
     * 新增参数模型
     */
    public void create(CfgModel cfg);

    /**
     * 更新参数模型
     */
    public int update(CfgModel cfg);

    /**
     * 删除参数模型
     */
    public int remove();

    /**
     * 删除参数模型
     */
    public int remove(String catg);

    /**
     * 删除参数模型
     */
    public int remove(String catg, String name);

}

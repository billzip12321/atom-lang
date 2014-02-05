/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg.service;

import com.github.obullxl.model.cfg.right.RightModel;
import com.github.obullxl.model.cfg.right.RightUtils;

/**
 * 权限模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightService.java, V1.0.1 2014年1月30日 下午3:42:07 $
 */
public interface RightService {
    /** 权限模型分类KEY */
    public static final String CATG = RightUtils.CATG;
    
    /**
     * 新增权限模型
     */
    public void create(RightModel right);

    /**
     * 更新权限模型
     */
    public int update(RightModel right);

    /**
     * 删除权限模型
     */
    public int remove();

    /**
     * 删除权限模型
     */
    public int remove(String name);

}

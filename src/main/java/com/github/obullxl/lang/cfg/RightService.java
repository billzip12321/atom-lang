/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.List;

/**
 * 权限权限模型服务接口
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightService.java, V1.0.1 2014年1月30日 下午3:42:07 $
 */
public interface RightService {
    /** 权限系统参数分类KEY */
    public static final String CATG = RightUtils.CATG;

    /**
     * 新增权限模型
     */
    public void create(RightDTO right);

    /**
     * 更新权限模型
     */
    public int update(RightDTO right);

    /**
     * 删除权限模型
     */
    public int remove();

    /**
     * 删除权限模型
     */
    public int remove(String name);

    /**
     * 查询所有权限模型
     */
    public List<RightDTO> find();

    /**
     * 根据代码查询权限模型
     */
    public RightDTO find(String code);

}

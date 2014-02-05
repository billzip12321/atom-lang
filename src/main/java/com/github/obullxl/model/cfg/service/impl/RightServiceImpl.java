/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg.service.impl;

import org.jsoup.helper.Validate;

import com.github.obullxl.model.cfg.CfgModel;
import com.github.obullxl.model.cfg.right.RightModel;
import com.github.obullxl.model.cfg.right.RightUtils;
import com.github.obullxl.model.cfg.service.CfgService;
import com.github.obullxl.model.cfg.service.RightService;

/**
 * 权限模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightServiceImpl.java, V1.0.1 2014年1月30日 下午3:42:32 $
 */
public class RightServiceImpl implements RightService {

    /** 权限模型DAO */
    private CfgService cfgService;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.cfgService, "[权限模型]-CfgService注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.cfg.RightService#create(com.github.obullxl.lang.cfg.RightModel)
     */
    public void create(RightModel right) {
        CfgModel cfg = RightUtils.convert(right);
        this.cfgService.create(cfg);
    }

    /** 
     * @see com.github.obullxl.lang.cfg.RightService#update(com.github.obullxl.lang.cfg.RightModel)
     */
    public int update(RightModel right) {
        CfgModel cfg = RightUtils.convert(right);
        return this.cfgService.update(cfg);
    }

    /** 
     * @see com.github.obullxl.lang.right.RightService#remove()
     */
    public int remove() {
        return this.cfgService.remove(CATG);
    }

    /** 
     * @see com.github.obullxl.lang.right.RightService#remove(java.lang.String)
     */
    public int remove(String name) {
        return this.cfgService.remove(CATG, name);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCfgService(CfgService cfgService) {
        this.cfgService = cfgService;
    }

}

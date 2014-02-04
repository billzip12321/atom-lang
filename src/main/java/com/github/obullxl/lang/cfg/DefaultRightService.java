/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.List;

import org.jsoup.helper.Validate;

import com.github.obullxl.lang.cfg.CfgDTO;
import com.github.obullxl.lang.cfg.CfgService;

/**
 * 权限权限模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultRightService.java, V1.0.1 2014年1月30日 下午3:42:32 $
 */
public class DefaultRightService implements RightService {

    /** 权限模型DAO */
    private CfgService cfgService;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.cfgService, "[权限模型]-CfgService注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.cfg.RightService#create(com.github.obullxl.lang.cfg.RightDTO)
     */
    public void create(RightDTO right) {
        CfgDTO cfg = RightUtils.convert(right);
        this.cfgService.create(cfg);
    }

    /** 
     * @see com.github.obullxl.lang.cfg.RightService#update(com.github.obullxl.lang.cfg.RightDTO)
     */
    public int update(RightDTO right) {
        CfgDTO cfg = RightUtils.convert(right);
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

    /** 
     * @see com.github.obullxl.lang.cfg.RightService#find()
     */
    public List<RightDTO> find() {
        return RightUtils.convert(CfgUtils.find(CATG));
    }

    /** 
     * @see com.github.obullxl.lang.cfg.RightService#find(java.lang.String)
     */
    public RightDTO find(String code) {
        return RightUtils.convert(CfgUtils.find(CATG, code));
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCfgService(CfgService cfgService) {
        this.cfgService = cfgService;
    }

}

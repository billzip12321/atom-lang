/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.cfg;

import java.util.Date;
import java.util.List;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.timer.TickTimer;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 系统参数默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultCfgService.java, V1.0.1 2014年1月26日 上午9:51:25 $
 */
public class DefaultCfgService implements TickTimer, CfgService {
    /** Logger */
    private static final Logger logger   = LogUtils.get();

    /** 最近执行时间 */
    private static Date         EXEC_TIME;

    /** 执行时间间隔(11分钟) */
    private static final long   INTERVAL = 11 * 60 * 1000;

    /** 系统参数DAO */
    private CfgDAO              cfgDAO;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.cfgDAO, "[系统参数]-CfgDAO注入失败!");

        // 刷新缓存
        this.onRefresh();
    }

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#tick()
     */
    public void tick() {
        if (!this.isMustExecute()) {
            if (logger.isInfoEnabled()) {
                logger.info("[系统参数]-本次无需操作, [" + DateUtils.toStringDL(EXEC_TIME) + "].");
            }

            return;
        }

        // 定时刷新
        this.onRefresh();
    }

    /**
     * 是否进行缓存刷新
     */
    private boolean isMustExecute() {
        if (EXEC_TIME == null) {
            EXEC_TIME = DateUtils.toDateDW("1988-08-08");
        }

        Date now = new Date();
        if (now.getTime() - EXEC_TIME.getTime() >= INTERVAL) {
            EXEC_TIME = now;
            return true;
        }

        return false;
    }

    /**
     * 刷新缓存
     */
    public void onRefresh() {
        logger.warn("[系统参数]-开始刷新系统参数缓存......");

        long start = System.currentTimeMillis();
        try {
            List<CfgDTO> cfgs = this.cfgDAO.find();
            if (cfgs != null) {
                for (CfgDTO cfg : cfgs) {
                    CfgUtils.updateCache(cfg);
                }
            }
        } finally {
            logger.warn("[系统参数]-系统参数缓存刷新完成, 耗时[{}]ms, 参数列表: \n{}", (System.currentTimeMillis() - start), CfgUtils.find());
        }
    }

    /**
     * 新增系统参数
     */
    public void create(CfgDTO cfg) {
        this.cfgDAO.insert(cfg);
        CfgUtils.updateCache(cfg);
    }

    /**
     * 更新系统参数
     */
    public void update(CfgDTO cfg) {
        this.cfgDAO.update(cfg);
        CfgUtils.updateCache(cfg);
    }

    /**
     * 删除系统参数
     */
    public void remove() {
        this.cfgDAO.delete();
        CfgUtils.removeCache();
    }

    /**
     * 删除系统参数
     */
    public void remove(String catg) {
        this.cfgDAO.delete(catg);
        CfgUtils.removeCache(catg);
    }

    /**
     * 删除系统参数
     */
    public void remove(String catg, String name) {
        this.cfgDAO.delete(catg, name);
        CfgUtils.removeCache(catg, name);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCfgDAO(CfgDAO cfgDAO) {
        this.cfgDAO = cfgDAO;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.cfg.service.impl;

import java.util.Date;
import java.util.List;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.events.EventBusExt;
import com.github.obullxl.lang.events.EventConsts;
import com.github.obullxl.lang.events.UniformEvent;
import com.github.obullxl.lang.timer.TickTimer;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.model.cfg.CfgModel;
import com.github.obullxl.model.cfg.CfgUtils;
import com.github.obullxl.model.cfg.dao.impl.AbstractCfgDAO;
import com.github.obullxl.model.cfg.service.CfgService;

/**
 * 参数模型默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgServiceImpl.java, V1.0.1 2014年1月26日 上午9:51:25 $
 */
public class CfgServiceImpl implements TickTimer, CfgService {
    /** Logger */
    private static final Logger logger   = LogUtils.get();

    /** 最近执行时间 */
    private static Date         EXEC_TIME;

    /** 执行时间间隔(11分钟) */
    private static final long   INTERVAL = 11 * 60 * 1000;

    /** 参数模型DAO */
    private AbstractCfgDAO      cfgDAO;

    /** 消息发送器 */
    private EventBusExt         eventBusExt;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.cfgDAO, "[参数模型]-CfgDAO注入失败!");

        // 刷新缓存
        this.onRefresh();
    }

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#tick()
     */
    public void tick() {
        if (!this.isMustExecute()) {
            if (logger.isInfoEnabled()) {
                logger.info("[参数模型]-本次无需操作, [" + DateUtils.toStringDL(EXEC_TIME) + "].");
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
     * @see com.github.obullxl.lang.cfg.CfgService#onRefresh()
     */
    public void onRefresh() {
        logger.warn("[参数模型]-开始刷新参数模型缓存......");

        long start = System.currentTimeMillis();
        try {
            List<CfgModel> cfgs = this.cfgDAO.selectAll();
            if (cfgs != null) {
                for (CfgModel cfg : cfgs) {
                    CfgUtils.updateCache(cfg);
                }
            }

            // 发送事件
            this.sendMessage(new UniformEvent(cfgs, EventConsts.CFG.TOPIC, EventConsts.CFG.REFRESH));
        } finally {
            logger.warn("[参数模型]-参数模型缓存刷新完成, 耗时[{}]ms, 参数列表: \n{}", (System.currentTimeMillis() - start), CfgUtils.find());
        }
    }

    /** 
     * @see com.github.obullxl.lang.cfg.CfgService#create(com.github.obullxl.lang.cfg.CfgModel)
     */
    public void create(CfgModel cfg) {
        this.cfgDAO.insert(cfg);
        CfgUtils.updateCache(cfg);

        // 发送事件
        this.sendMessage(new UniformEvent(cfg, EventConsts.CFG.TOPIC, EventConsts.CFG.CREATE));
    }

    /** 
     * @see com.github.obullxl.lang.cfg.CfgService#update(com.github.obullxl.lang.cfg.CfgModel)
     */
    public int update(CfgModel cfg) {
        int cnt = this.cfgDAO.update(cfg);
        CfgUtils.updateCache(cfg);

        // 发送事件
        this.sendMessage(new UniformEvent(cfg, EventConsts.CFG.TOPIC, EventConsts.CFG.UPDATE));

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.cfg.CfgService#remove()
     */
    public int remove() {
        int cnt = this.cfgDAO.deleteAll();
        CfgUtils.removeCache();

        // 发送事件
        this.sendMessage(new UniformEvent(EventConsts.CFG.TOPIC, EventConsts.CFG.REMOVE));

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.cfg.CfgService#remove(java.lang.String)
     */
    public int remove(String catg) {
        int cnt = this.cfgDAO.delete(catg);
        CfgUtils.removeCache(catg);

        // 发送事件
        this.sendMessage(new UniformEvent(EventConsts.CFG.TOPIC, EventConsts.CFG.REMOVE));

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.cfg.CfgService#remove(java.lang.String, java.lang.String)
     */
    public int remove(String catg, String name) {
        int cnt = this.cfgDAO.delete(catg, name);
        CfgUtils.removeCache(catg, name);

        // 发送事件
        this.sendMessage(new UniformEvent(EventConsts.CFG.TOPIC, EventConsts.CFG.REMOVE));

        return cnt;
    }

    /**
     * 发送消息
     */
    private void sendMessage(UniformEvent event) {
        this.eventBusExt.postUniformEvent(event);
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCfgDAO(AbstractCfgDAO cfgDAO) {
        this.cfgDAO = cfgDAO;
    }

    public void setEventBusExt(EventBusExt eventBusExt) {
        this.eventBusExt = eventBusExt;
    }

}

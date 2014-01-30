/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.Date;
import java.util.List;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.timer.TickTimer;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 模块分类默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultCfgService.java, V1.0.1 2014年1月26日 上午9:51:25 $
 */
public class DefaultCatgService implements TickTimer, CatgService {
    /** Logger */
    private static final Logger logger   = LogUtils.get();

    /** 最近执行时间 */
    private static Date         EXEC_TIME;

    /** 执行时间间隔(13分钟) */
    private static final long   INTERVAL = 13 * 60 * 1000;

    /** 模块分类DAO */
    private CatgDAO             catgDAO;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.catgDAO, "[模块分类]-CatgDAO注入失败!");

        // 刷新缓存
        this.onRefresh();
    }

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#tick()
     */
    public void tick() {
        if (!this.isMustExecute()) {
            if (logger.isInfoEnabled()) {
                logger.info("[模块分类]-本次无需操作, [" + DateUtils.toStringDL(EXEC_TIME) + "].");
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
        logger.warn("[模块分类]-开始刷新模块分类缓存......");

        long start = System.currentTimeMillis();
        try {
            // 查询所有
            List<CatgDTO> dtos = this.catgDAO.selectAll();
            CatgUtils.onRefresh(dtos);
        } finally {
            logger.warn("[模块分类]-模块分类缓存刷新完成, 耗时[{}]ms, 参数列表: \n{}", (System.currentTimeMillis() - start), CatgUtils.find());
        }
    }

    /**
     * 新增模块分类
     */
    public void create(CatgDTO catg) {
        this.catgDAO.insert(catg);
        this.onRefresh();
    }

    /**
     * 更新模块分类
     */
    public void update(CatgDTO catg) {
        this.catgDAO.update(catg);
        this.onRefresh();
    }

    /**
     * 删除模块分类
     */
    public void remove() {
        this.catgDAO.deleteAll();
        CatgUtils.remove();
    }

    /**
     * 删除模块分类
     */
    public void remove(String code) {
        this.catgDAO.delete(code);
        this.onRefresh();
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCatgDAO(CatgDAO catgDAO) {
        this.catgDAO = catgDAO;
    }

}

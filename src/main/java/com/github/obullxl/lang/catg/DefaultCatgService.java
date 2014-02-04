/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
     * @see com.github.obullxl.lang.catg.CatgService#onRefresh()
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
     * @see com.github.obullxl.lang.catg.CatgService#find(java.lang.String)
     */
    public CatgDTO find(String code) {
        return this.catgDAO.find(code);
    }

    /** 
     * @see com.github.obullxl.lang.catg.CatgService#create(com.github.obullxl.lang.catg.CatgDTO)
     */
    public void create(CatgDTO catg) {
        this.catgDAO.insert(catg);
        this.onRefresh();
    }

    /** 
     * @see com.github.obullxl.lang.catg.CatgService#update(com.github.obullxl.lang.catg.CatgDTO)
     */
    public int update(CatgDTO catg) {
        int cnt = this.catgDAO.update(catg);

        CatgDTO exist = CatgUtils.find(catg.getCode());
        if (StringUtils.equals(exist.getCatg(), catg.getCatg())) {
            // 结构未改变，无需重新加载
            CatgUtils.merge(exist, catg);
        } else {
            // 结构发送变化，需重新加载
            this.onRefresh();
        }

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.catg.CatgService#remove()
     */
    public int remove() {
        int cnt = this.catgDAO.deleteAll();
        CatgUtils.remove();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.catg.CatgService#remove(java.lang.String)
     */
    public int remove(String code) {
        int cnt = this.catgDAO.delete(code);
        this.onRefresh();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.catg.CatgService#removeByCatg(java.lang.String)
     */
    public int removeByCatg(String catg) {
        int cnt = this.catgDAO.deleteByCatg(catg);
        this.onRefresh();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.catg.CatgService#remove(java.lang.String, java.lang.String)
     */
    public int remove(String catg, String code) {
        int cnt = this.catgDAO.delete(catg, code);
        this.onRefresh();

        return cnt;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setCatgDAO(CatgDAO catgDAO) {
        this.catgDAO = catgDAO;
    }

}

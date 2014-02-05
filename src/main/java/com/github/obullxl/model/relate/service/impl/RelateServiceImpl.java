/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.relate.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.util.Assert;

import com.github.obullxl.lang.timer.TickTimer;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.model.relate.RelateModel;
import com.github.obullxl.model.relate.RelateUtils;
import com.github.obullxl.model.relate.dao.impl.AbstractRelateDAO;
import com.github.obullxl.model.relate.service.RelateService;

/**
 * 关联模型服务接口默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: RelateServiceImpl.java, V1.0.1 2014年1月31日 上午11:51:14 $
 */
public class RelateServiceImpl implements TickTimer, RelateService {
    /** Logger */
    private static final Logger logger   = LogUtils.get();

    /** 最近执行时间 */
    private static Date         EXEC_TIME;

    /** 执行时间间隔(13分钟) */
    private static final long   INTERVAL = 13 * 60 * 1000;

    /** 关联模型DAO */
    private AbstractRelateDAO   relateDAO;

    /**
     * 初始化
     */
    public void init() {
        Assert.notNull(this.relateDAO, "[关联模型]-关联模型DAO[RelateDAO]注入失败！");

        // 刷新缓存
        this.onRefresh();
    }

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#tick()
     */
    public void tick() {
        if (!this.isMustExecute()) {
            if (logger.isInfoEnabled()) {
                logger.info("[关联模型]-本次无需操作, [" + DateUtils.toStringDL(EXEC_TIME) + "].");
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
     * @see com.github.obullxl.lang.relate.CfgService#onRefresh()
     */
    public void onRefresh() {
        logger.warn("[关联模型]-开始刷新关联模型缓存......");

        long start = System.currentTimeMillis();
        try {
            List<RelateModel> relates = this.relateDAO.selectAll();
            if (relates != null) {
                for (RelateModel relate : relates) {
                    RelateUtils.updateCache(relate);
                }
            }
        } finally {
            logger.warn("[关联模型]-关联模型缓存刷新完成, 耗时[{}]ms, 关联列表: \n{}", (System.currentTimeMillis() - start), RelateUtils.find());
        }
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#create(com.github.obullxl.lang.relate.RelateDTO)
     */
    public void create(RelateModel relate) {
        this.relateDAO.insert(relate);
        RelateUtils.updateCache(relate);
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#update(com.github.obullxl.lang.relate.RelateDTO)
     */
    public int update(RelateModel relate) {
        int cnt = this.relateDAO.update(relate);
        RelateUtils.updateCache(relate);

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByCatg(java.lang.String)
     */
    public int removeByCatg(String catg) {
        int cnt = this.relateDAO.deleteByCatg(catg);
        RelateUtils.removeCache(catg);

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeBySrcCatg(java.lang.String, java.lang.String)
     */
    public int removeBySrcCatg(String catg, String srcNo) {
        int cnt = this.relateDAO.deleteBySrcCatg(catg, srcNo);
        this.onRefresh();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByDstCatg(java.lang.String, java.lang.String)
     */
    public int removeByDstCatg(String catg, String dstNo) {
        int cnt = this.relateDAO.deleteByDstCatg(catg, dstNo);
        this.onRefresh();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeBySrcNo(java.lang.String)
     */
    public int removeBySrcNo(String srcNo) {
        int cnt = this.relateDAO.deleteBySrcNo(srcNo);
        this.onRefresh();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByDstNo(java.lang.String)
     */
    public int removeByDstNo(String dstNo) {
        int cnt = this.relateDAO.deleteByDstNo(dstNo);
        this.onRefresh();

        return cnt;
    }

    /** 
     * @see com.github.obullxl.lang.relate.RelateService#removeByUnique(java.lang.String, java.lang.String, java.lang.String)
     */
    public int removeByUnique(String catg, String srcNo, String dstNo) {
        int cnt = this.relateDAO.deleteByUnique(catg, srcNo, dstNo);
        this.onRefresh();

        return cnt;
    }

    // ~~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~ //

    public void setRelateDAO(AbstractRelateDAO relateDAO) {
        this.relateDAO = relateDAO;
    }

}

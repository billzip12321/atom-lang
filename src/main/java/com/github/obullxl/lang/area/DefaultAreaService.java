/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.area;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;

import com.github.obullxl.lang.Consts;
import com.github.obullxl.lang.timer.TickTimer;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 区域服务实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultAreaService.java, V1.0.1 2014年1月26日 下午2:24:43 $
 */
public class DefaultAreaService implements TickTimer, AreaService {
    /** Logger */
    private static final Logger logger   = LogUtils.get();

    /** 最近执行时间 */
    private static Date         EXEC_TIME;

    /** 执行时间间隔(31分钟) */
    private static final long   INTERVAL = 31 * 60 * 1000;

    /** 文件资源 */
    private Resource            config;

    /**
     * 系统初始化
     */
    public void init() {
        Validate.notNull(this.config, "[区域代码]-配置文件注入失败!");

        // 刷新缓存
        this.onRefresh();
    }

    /** 
     * @see com.github.obullxl.lang.timer.TickTimer#tick()
     */
    public void tick() {
        if (!this.isMustExecute()) {
            if (logger.isInfoEnabled()) {
                logger.info("[区域代码]-本次无需操作, [" + DateUtils.toStringDL(EXEC_TIME) + "].");
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
        logger.warn("[区域代码]-开始刷新区域代码缓存......");

        long start = System.currentTimeMillis();
        try {
            List<AreaDTO> areas = this.parse();
            AreaUtils.onRefresh(areas);
        } finally {
            logger.warn("[区域代码]-区域代码缓存刷新完成, 耗时[{}]ms.", (System.currentTimeMillis() - start));
        }
    }

    /**
     * 解析区域代码
     */
    private List<AreaDTO> parse() {
        List<AreaDTO> areas = new ArrayList<AreaDTO>();

        try {
            List<String> lines = IOUtils.readLines(this.config.getInputStream(), Consts.UTF8);
            for (String line : lines) {
                String[] pairs = StringUtils.split(line, ",");

                if (pairs.length != 3) {
                    logger.warn("[区域代码]-字符串[{}]格式错误!", line);
                    continue;
                }

                AreaDTO area = new AreaDTO();

                area.setNo(pairs[0]);
                area.setSort(pairs[1]);
                area.setName(pairs[2]);

                areas.add(area);
            }

            return areas;
        } catch (Exception e) {
            String text = "[区域代码]-解析区域代码异常!";
            logger.error(text, e);
            throw new RuntimeException(text, e);
        }
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setConfig(Resource config) {
        this.config = config;
    }

}

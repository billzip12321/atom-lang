/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * 基于主题的Velocity模板引擎
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityLayoutViewResolverExt.java, V1.0.1 2013年12月4日 下午7:29:01 $
 */
public class VelocityLayoutViewResolverExt extends VelocityLayoutViewResolver implements InitializingBean {
    /** Logger */
    private static final Logger   logger    = LogUtils.get();

    /** VM文件后缀 */
    public static final String    VM_SUFFIX = ".vm";

    /** 基于主题模板 */
    private String                themeLayoutUrl;

    /** Velocity引擎 */
    private VelocityEngineFactory velocityEngineFactory;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        if (StringUtils.isBlank(super.getSuffix())) {
            super.setSuffix(VM_SUFFIX);
        }
    }

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver#buildView(java.lang.String)
     */
    public AbstractUrlBasedView buildView(String viewName) throws Exception {
        // 增加前缀(admin/topic.vm ==> /admin/topic.vm)
        if (!StringUtils.startsWith(viewName, "/")) {
            viewName = "/" + viewName;
        }

        // 去掉后缀(/admin/topic.vm ==> /admin/topic)
        if (StringUtils.endsWithIgnoreCase(viewName, VM_SUFFIX)) {
            viewName = StringUtils.substringBeforeLast(viewName, VM_SUFFIX);
        }

        // 解析视图
        VelocityLayoutView view = (VelocityLayoutView) super.buildView(viewName);

        // 查找模板(/admin/topic ==> /admin/layout/topic.vm ==> /admin/layout/layout.vm ==> /layout/layout.vm)
        String vname = StringUtils.substringAfterLast(viewName, "/");
        String path = StringUtils.substringBeforeLast(viewName, "/");

        // 1.(/admin/topic ==> /admin/layout/topic.vm)
        String layout = path + "/layout/" + vname + VM_SUFFIX;
        if (this.velocityEngineFactory.get().resourceExists(layout)) {
            view.setLayoutUrl(layout);

            if (logger.isDebugEnabled()) {
                logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
            }

            return view;
        }

        // 2.(/admin/topic ==> /admin/layout/layout.vm)
        layout = path + "/layout/layout" + VM_SUFFIX;
        if (this.velocityEngineFactory.get().resourceExists(layout)) {
            view.setLayoutUrl(layout);

            if (logger.isDebugEnabled()) {
                logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
            }

            return view;
        }

        // 3.(/admin/topic ==> /layout/layout.vm)
        path = StringUtils.substringBeforeLast(path, "/");
        while (StringUtils.isNotBlank(path)) {
            layout = path + "/layout/layout" + VM_SUFFIX;
            if (this.velocityEngineFactory.get().resourceExists(layout)) {
                view.setLayoutUrl(layout);

                if (logger.isDebugEnabled()) {
                    logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
                }

                return view;
            }

            // 上级目录
            path = StringUtils.substringBeforeLast(path, "/");
        }

        // 4.默认模板
        if (StringUtils.isBlank(this.themeLayoutUrl)) {
            if (logger.isDebugEnabled()) {
                logger.debug("[视图]-视图[{}]-模板[{}].", viewName, "默认");
            }

            return view;
        }

        // 5.主题替换
        String theme = WebViewThemeHolder.get();
        layout = StringUtils.replace(this.themeLayoutUrl, "{theme}", theme);
        view.setLayoutUrl(layout);

        if (logger.isDebugEnabled()) {
            logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
        }

        return view;
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setThemeLayoutUrl(String themeLayoutUrl) {
        this.themeLayoutUrl = themeLayoutUrl;
    }

    public void setVelocityEngineFactory(VelocityEngineFactory velocityEngineFactory) {
        this.velocityEngineFactory = velocityEngineFactory;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.slf4j.Logger;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * Velocity模板布局视图扩展
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityLayoutViewExt.java, V1.0.1 2014年5月29日 下午8:53:13 $
 */
public class VelocityLayoutViewExt extends VelocityLayoutView {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /** 视图前缀 */
    private String              viewPrefix;

    /** 视图后缀 */
    private String              viewSuffix;

    /** 视图布局KEY */
    private String              viewLayoutKey;

    /** 视图布局URL */
    private String              viewLayoutUrl;

    /** 基于主题模板 */
    private String              themeLayoutUrl;

    /** 主内容KEY */
    private String              screenContentKey;

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView#doRender(org.apache.velocity.context.Context, javax.servlet.http.HttpServletResponse)
     */
    protected void doRender(Context context, HttpServletResponse response) throws Exception {
        this.renderScreenContent(context);

        // Velocity context now includes any mappings that were defined
        // (via #set) in screen content template.
        // The screen template can overrule the layout by doing
        // #set( $layout = "MyLayout.vm" )
        String layoutUrlToUse = (String) context.get(this.viewLayoutKey);
        if (layoutUrlToUse != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Screen content template has requested layout [" + layoutUrlToUse + "]");
            }
        } else {
            // No explicit layout URL given -> use default layout of this view.
            layoutUrlToUse = this.findLayoutURL();
        }

        // 渲染整个页面
        mergeTemplate(getTemplate(layoutUrlToUse), context, response);
    }

    /**
     * 渲染主体内容
     */
    private void renderScreenContent(Context velocityContext) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Rendering screen content template [" + getUrl() + "]");
        }

        Writer sw = new StringWriter();
        Template screenContentTemplate = getTemplate(getUrl());
        screenContentTemplate.merge(velocityContext, sw);

        // Put rendered content into Velocity context.
        velocityContext.put(this.screenContentKey, sw.toString());
    }

    /**
     * 获取模板布局
     */
    private String findLayoutURL() {
        // getPrefix() + viewName + getSuffix()
        String viewName = this.getUrl();
        viewName = StringUtils.substringAfter(viewName, this.viewPrefix);
        viewName = StringUtils.substringBeforeLast(viewName, this.viewSuffix);

        if (!StringUtils.startsWith(viewName, "/")) {
            viewName = "/" + viewName;
        }

        // 查找模板: /admin/topic ==> /admin/layout/topic.vm
        // ======> /admin/layout/layout.vm ==> /layout/layout.vm
        String vname = StringUtils.substringAfterLast(viewName, "/");
        String path = StringUtils.substringBeforeLast(viewName, "/");

        // 1.(/admin/topic ==> /admin/layout/topic.vm)
        String layout = path + "/layout/" + vname + this.viewSuffix;
        if (this.getVelocityEngine().resourceExists(layout)) {
            if (logger.isDebugEnabled()) {
                logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
            }

            return layout;
        }

        // 2.(/admin/topic ==> /admin/layout/layout.vm)
        layout = path + "/layout/layout" + this.viewSuffix;
        if (this.getVelocityEngine().resourceExists(layout)) {
            if (logger.isDebugEnabled()) {
                logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
            }

            return layout;
        }

        // 3.(/admin/topic ==> /layout/layout.vm)
        path = StringUtils.substringBeforeLast(path, "/");
        while (StringUtils.isNotBlank(path)) {
            layout = path + "/layout/layout" + this.viewSuffix;
            if (this.getVelocityEngine().resourceExists(layout)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
                }

                return layout;
            }

            // 上级目录
            path = StringUtils.substringBeforeLast(path, "/");
        }

        // 4.主题替换
        if (StringUtils.isBlank(this.themeLayoutUrl)) {
            String theme = WebViewThemeHolder.get();
            layout = StringUtils.replace(this.themeLayoutUrl, "{theme}", theme);

            if (logger.isDebugEnabled()) {
                logger.debug("[视图]-视图[{}]-模板[{}].", viewName, layout);
            }

            return layout;
        }

        // 5.默认布局
        if (logger.isDebugEnabled()) {
            logger.debug("[视图]-视图[{}]-模板[{}].", viewName, "默认");
        }

        // 返回默认布局
        return this.viewLayoutUrl;
    }

    // ~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~ //

    public void setViewPrefix(String viewPrefix) {
        this.viewPrefix = viewPrefix;
    }

    public void setViewSuffix(String viewSuffix) {
        this.viewSuffix = viewSuffix;
    }

    public void setViewLayoutKey(String viewLayoutKey) {
        super.setLayoutKey(viewLayoutKey);
        this.viewLayoutKey = viewLayoutKey;
    }

    public void setViewLayoutUrl(String viewLayoutUrl) {
        super.setLayoutUrl(viewLayoutUrl);
        this.viewLayoutUrl = viewLayoutUrl;
    }

    public void setThemeLayoutUrl(String themeLayoutUrl) {
        this.themeLayoutUrl = themeLayoutUrl;
    }

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView#setScreenContentKey(java.lang.String)
     */
    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
        super.setScreenContentKey(screenContentKey);
    }

}

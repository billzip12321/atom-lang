/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 * 基于主题的Velocity模板引擎
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityLayoutViewResolverExt.java, V1.0.1 2013年12月4日 下午7:29:01 $
 */
public class VelocityLayoutViewResolverExt extends VelocityLayoutViewResolver {

    /** 模板布局KEY */
    private String layoutKey;

    /** 模板布局URL */
    private String layoutUrl;

    /** 基于主题模板 */
    private String themeLayoutUrl;

    /** 主内容KEY */
    private String screenContentKey;

    /** 
     * @see org.springframework.web.servlet.view.UrlBasedViewResolver#getViewClass()
     */
    protected Class<?> getViewClass() {
        return VelocityLayoutViewExt.class;
    }

    /** 
     * @see org.springframework.web.servlet.view.UrlBasedViewResolver#setViewClass(java.lang.Class)
     */
    public void setViewClass(Class<?> viewClass) {
        super.setViewClass(VelocityLayoutViewExt.class);
    }

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver#buildView(java.lang.String)
     */
    public AbstractUrlBasedView buildView(String viewName) throws Exception {
        // 解析视图
        VelocityLayoutViewExt view = (VelocityLayoutViewExt) super.buildView(viewName);

        view.setViewPrefix(this.getPrefix());
        view.setViewSuffix(this.getSuffix());
        view.setViewLayoutKey(this.layoutKey);
        view.setViewLayoutUrl(this.layoutUrl);
        view.setThemeLayoutUrl(this.themeLayoutUrl);
        view.setScreenContentKey(this.screenContentKey);

        // 扩展视图
        return view;
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver#setLayoutKey(java.lang.String)
     */
    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
        super.setLayoutKey(layoutKey);
    }

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver#setLayoutUrl(java.lang.String)
     */
    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
        super.setLayoutUrl(layoutUrl);
    }

    public void setThemeLayoutUrl(String themeLayoutUrl) {
        this.themeLayoutUrl = themeLayoutUrl;
    }

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver#setScreenContentKey(java.lang.String)
     */
    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
        super.setScreenContentKey(screenContentKey);
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 * 基于主题的Velocity模板引擎
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityLayoutViewResolverExt.java, V1.0.1 2013年12月4日 下午7:29:01 $
 */
public class VelocityLayoutViewResolverExt extends VelocityLayoutViewResolver {

    /** 基于主题模板 */
    private String themeLayoutUrl;

    /** 
     * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver#buildView(java.lang.String)
     */
    public AbstractUrlBasedView buildView(String viewName) throws Exception {
        VelocityLayoutView view = (VelocityLayoutView) super.buildView(viewName);

        if (StringUtils.isBlank(this.themeLayoutUrl)) {
            return view;
        }

        String theme = WebViewThemeHolder.get();
        view.setLayoutUrl(StringUtils.replace(this.themeLayoutUrl, "{theme}", theme));

        return view;
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setThemeLayoutUrl(String themeLayoutUrl) {
        this.themeLayoutUrl = themeLayoutUrl;
    }

}

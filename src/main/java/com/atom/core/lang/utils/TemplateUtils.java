/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.atom.core.lang.tpls.FreeMarkerTPL;

import freemarker.template.Template;

/**
 * 模板工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: TemplateUtils.java, V1.0.1 2013-3-24 下午2:03:04 $
 */
public class TemplateUtils {

    /**
     * 设置模板目录
     */
    public static final void setTplPath(String tplPath) {
        setTplPath(new File(tplPath));
    }

    /**
     * 设置模板目录
     */
    public static final void setTplPath(File path) {
        try {
            FreeMarkerTPL.findConfig().setDirectoryForTemplateLoading(path);
        } catch (Exception e) {
            throw new RuntimeException("[模板]-设置模板目录[" + path + "]异常!");
        }
    }

    /**
     * 渲染模板，返回字符串
     */
    public static final String render(String tplName) {
        return render(tplName, new HashMap<Object, Object>());
    }

    /**
     * 渲染模板，返回字符
     */
    public static final String render(String tplName, Map<Object, Object> data) {
        StringWriter writer = new StringWriter();

        try {
            Template temp = FreeMarkerTPL.findConfig().getTemplate(tplName);
            temp.process(data, writer);
        } catch (Exception e) {
            LogUtils.warn("[模板]-渲染模板[" + tplName + "]异常!", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }

        return writer.toString();
    }

    /**
     * 渲染模板，返回字符
     */
    public static final String render(String tplPath, String fileName, Map<Object, Object> data) {
        setTplPath(tplPath);

        return render(fileName, data);
    }

}

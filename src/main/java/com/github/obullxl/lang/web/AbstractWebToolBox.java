/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.web;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.utils.MD5Utils;
import com.github.obullxl.lang.utils.TextUtils;
import com.google.common.collect.Maps;

/**
 * Web工具箱
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractWebToolBox.java, V1.0.1 2014年4月5日 上午10:05:18 $
 */
public abstract class AbstractWebToolBox {
    /** Spring配置BEAN */
    public static final String TOOL_BOX_BEAN = "web_tool_box_impl";

    /**
     * 共用工具
     */
    public static final Map<String, Object> findBasicTools() {
        Map<String, Object> tools = Maps.newConcurrentMap();

        tools.put(MD5Utils.class.getSimpleName(), new MD5Utils());
        tools.put(DateUtils.class.getSimpleName(), new DateUtils());
        tools.put(TextUtils.class.getSimpleName(), new TextUtils());

        tools.put(IOUtils.class.getSimpleName(), new IOUtils());
        tools.put(NumberUtils.class.getSimpleName(), new NumberUtils());
        tools.put(StringUtils.class.getSimpleName(), new StringUtils());
        tools.put(FileUtils.class.getSimpleName(), new FileUtils());
        tools.put(FilenameUtils.class.getSimpleName(), new FilenameUtils());
        tools.put(BooleanUtils.class.getSimpleName(), new BooleanUtils());

        tools.put(CollectionUtils.class.getSimpleName(), new CollectionUtils());

        return tools;
    }

    /**
     * 最终工具箱
     */
    public abstract Map<String, Object> findToolBox();

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * JARUtils工具类测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: JARUtilsTest.java, V1.0.1 2013-2-24 下午7:59:22 $
 */
public class JARUtilsTest {

    /**
     * findUrlPath
     */
    @Test
    public void test_findUrlPath() {
        String pack = JARUtils.findUrlPath(String.class);
        Assert.assertEquals("/java/lang", pack);
        
        String path = JARUtils.findUrlPath(String.class, "../com/atom/../back/");
        Assert.assertEquals("/java/com/back", path);
        
        path = JARUtils.findUrlPath(String.class, "../com/atom/../back/test.fxml");
        Assert.assertEquals("/java/com/back/test.fxml", path);
    }
}

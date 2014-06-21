/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import junit.framework.Assert;

import org.junit.Test;

import com.github.obullxl.lang.area.IPArea;
import com.github.obullxl.lang.utils.IPUtils;

/**
 * IPUtils测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: IPUtilsTest.java, V1.0.1 2014年6月17日 下午9:09:17 $
 */
public class IPUtilsTest {

    /**
     * findArea
     */
    @Test
    public void test_findArea() {
        String ip = "192.168.1.1";
        IPArea area = IPUtils.findArea(ip);
        Assert.assertEquals(area.findLocation(), "");
        
        ip = "112.64.60.52";
        area = IPUtils.findArea(ip);
        Assert.assertEquals(area.findLocation(), "上海市");
        
        ip = "61.136.183.140";
        area = IPUtils.findArea(ip);
        Assert.assertEquals(area.findLocation(), "湖北省 咸宁市");
    }
    
}

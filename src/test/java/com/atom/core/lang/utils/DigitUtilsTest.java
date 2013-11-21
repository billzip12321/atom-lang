/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import com.github.obullxl.lang.utils.DigitUtils;

/**
 * ByteUtils测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: DigitUtilsTest.java, V1.0.1 2013-2-23 下午11:14:02 $
 */
public class DigitUtilsTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(DigitUtils.toHex(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 64, 65 }));
    }

}

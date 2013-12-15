/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

/**
 * 字符串工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: TextUtils.java, V1.0.1 2013年12月9日 下午3:52:45 $
 */
public class TextUtils {

    /**
     * 字符串截取
     */
    public static String truncate(String text, int size) {
        return truncate(text, size, "UTF-8");
    }

    /**
     * 字符串截取
     */
    public static String truncate(String text, int size, String charsetName) {
        return truncate(text, 0, size, charsetName, 3);
    }

    /**
     * 字符串截取
     */
    public static String truncate(String text, int start, int size, String charsetName) {
        return truncate(text, start, size, charsetName, 3);
    }

    /**
     * 字符串截取
     */
    public static String truncate(String text, int start, int size, String charsetName, int charBytes) {
        if (text == null) {
            return "";
        }

        int count = 0;
        StringBuilder stxt = new StringBuilder(size);

        char[] chars = text.toCharArray();
        for (int i = start; (i < chars.length && size > count); i++) {
            try {
                String s = String.valueOf(chars[i]);
                count += s.getBytes(charsetName).length;
            } catch (Exception e) {
                count += charBytes;
            }

            if (count <= size) {
                stxt.append(chars[i]);
            }
        }

        return stxt.toString();
    }

}

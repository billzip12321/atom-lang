/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 数字工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: DigitUtils.java, V1.0.1 2013-2-23 下午11:08:03 $
 */
public final class DigitUtils {
    /** The high digits lookup table. */
    private static final byte[] highDigits;

    /** The low digits lookup table. */
    private static final byte[] lowDigits;

    /**
     * Initialize lookup tables.
     */
    static {
        final byte[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        int i;
        byte[] high = new byte[256];
        byte[] low = new byte[256];

        for (i = 0; i < 256; i++) {
            high[i] = digits[i >>> 4];
            low[i] = digits[i & 0x0F];
        }

        highDigits = high;
        lowDigits = low;
    }

    /**
     * Dumps an byte array to a hex formatted string.
     */
    public static String toHex(byte[] data) {
        if (data == null) {
            return "null";
        }

        if (data.length == 0) {
            return "";
        }

        StringBuilder out = new StringBuilder();

        for (byte value : data) {
            int byteValue = value & 0xFF;
            out.append((char) highDigits[byteValue]);
            out.append((char) lowDigits[byteValue]);
            out.append(' ');
        }

        return StringUtils.trim(out.toString());
    }

    /**
     * 整形转换为Hex字符
     */
    public static final String toHex(int data) {
        return StringUtils.upperCase(StringUtils.leftPad(Integer.toHexString(data), 2, "0"));
    }

    /**
     * 整形数组转换为Hex字符数组
     */
    public static final String[] toHexs(int[] data) {
        String[] hexs = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            hexs[i] = toHex(data[i]);
        }

        return hexs;
    }

    /**
     * 整形数组转换为Hex字符数串
     */
    public static final String toHex(int[] data) {
        return toHex(data, data.length);
    }

    /**
     * 整形数组转换为Hex字符数串
     */
    public static final String toHex(int[] data, int size) {
        if (data.length < size) {
            size = data.length;
        }

        StringBuilder txt = new StringBuilder();

        for (int i = 0; i < size; i++) {
            txt.append(toHex(data[i])).append(" ");
        }

        return StringUtils.trim(txt.toString());
    }

    /**
     * 数值类型转换
     */
    public static final int[] toInts(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        int[] data = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            data[i] = bytes[i];
        }

        return data;
    }

    /**
     * 数值类型转换
     */
    public static final byte[] toBytes(int[] ints) {
        if (ints == null) {
            return null;
        }

        byte[] data = new byte[ints.length];
        for (int i = 0; i < ints.length; i++) {
            data[i] = (byte) ints[i];
        }

        return data;
    }

    /**
     * 检测数组的开头
     */
    public static final boolean startsWith(int[] data, int[] starts) {
        if (data == null || starts == null) {
            return false;
        }

        if (data.length < starts.length) {
            return false;
        }

        for (int i = 0; i < starts.length; i++) {
            if (data[i] != starts[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测数组的结尾
     */
    public static final boolean endsWith(int[] data, int[] ends) {
        if (data == null || ends == null) {
            return false;
        }

        if (data.length < ends.length) {
            return false;
        }

        int start = data.length - ends.length;
        for (int i = 0; i < ends.length; i++) {
            if (data[start + i] != ends[i]) {
                return false;
            }
        }

        return true;
    }

}

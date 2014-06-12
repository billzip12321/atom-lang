/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * 对象工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ObjectUtils.java, V1.0.1 2014年6月4日 下午9:02:48 $
 */
public class ObjectUtils {

    /**
     * 对象转换为Base64字符串
     */
    public static String toBase64(Object object) {
        ByteArrayOutputStream byteOutput = null;
        ObjectOutputStream objectOutput = null;
        try {
            byteOutput = new ByteArrayOutputStream();
            objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeObject(object);

            byte[] data = byteOutput.toByteArray();
            return Base64.encodeBase64String(data);
        } catch (Exception e) {
            throw new RuntimeException("对象转换为Base64字符串异常！", e);
        } finally {
            IOUtils.closeQuietly(byteOutput);
            IOUtils.closeQuietly(objectOutput);
        }
    }

    /**
     * Base64字符串转换为对象
     */
    public static Object fromBase64(String text) {
        ByteArrayInputStream byteInput = null;
        ObjectInputStream objectInput = null;
        try {
            byte[] data = Base64.decodeBase64(text);
            byteInput = new ByteArrayInputStream(data);
            objectInput = new ObjectInputStream(byteInput);

            return objectInput.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Base64字符串转换为对象异常！", e);
        } finally {
            IOUtils.closeQuietly(byteInput);
            IOUtils.closeQuietly(objectInput);
        }
    }

}

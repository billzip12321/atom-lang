/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.config;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

/**
 * Velocity工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: VelocityHelper.java, V1.0.1 2013年11月18日 下午4:56:37 $
 */
public class VelocityHelper {
    /** 单态实例 */
    private static final VelocityHelper instance = new VelocityHelper();

    /** 私有构造函数 */
    private VelocityHelper() {
        // 初始化velocity的信息 主要设置一些Velocity的默认属性

        // 初始化
        try {
            Velocity.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 单态实例
     */
    public static VelocityHelper get() {
        return instance;
    }

    /**
     * Velocity执行
     */
    public boolean evaluate(Context context, Writer writer, Reader reader) {
        try {
            return Velocity.evaluate(context, writer, "", reader);
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detial [" + e.getMessage() + "]");
        }
    }

    /**
     * 通过Map过滤一个输入流
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public InputStream evaluate(Map map, Reader reader) {
        try {
            VelocityContext context = convertVelocityContext(map);
            CharArrayWriter writer = new CharArrayWriter();
            // 开始评估
            evaluate(context, writer, reader);
            // 把产生的输出流(字符流)，转换成输入流(字节流)
            byte[] dataBytes = writer.toString().getBytes();
            return new BufferedInputStream(new ByteArrayInputStream(dataBytes));
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detial [" + e.getMessage() + "]");
        }
    }

    /**
     * 通过Map过滤一个输入流
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Writer evaluateToWriter(Map map, Reader reader) {
        try {
            VelocityContext context = convertVelocityContext(map);
            CharArrayWriter writer = new CharArrayWriter();
            //开始评估
            evaluate(context, writer, reader);
            return writer;
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detial [" + e.getMessage() + "]");
        }
    }

    /**
     * 通过系统的Configration过滤一个输入流
     */
    public InputStream evaluate(Reader reader) {
        return evaluate(ConfigFactory.get().getConfig(), reader);
    }

    /**
     * 通过系统的Configration过滤一个输入流
     */
    public Writer evaluateToWriter(Reader reader) {
        return evaluateToWriter(ConfigFactory.get().getConfig(), reader);
    }

    /**
     * 通过系统的Configration过滤一个输入流
     */
    public Writer evaluateToWriter(InputStream input) {
        return evaluateToWriter(ConfigFactory.get().getConfig(), new InputStreamReader(input));
    }

    /**
     * 通过系统的Configration过滤一个输入流
     */
    public InputStream evaluate(InputStream input) {
        return evaluate(ConfigFactory.get().getConfig(), new InputStreamReader(input));
    }

    /**
     * 取得Velocity系统属性
     */
    public Object getProperty(String key) {
        return Velocity.getProperty(key);
    }

    /**
     * 上下文复制
     */
    private VelocityContext convertVelocityContext(Map<String, Object> map) {
        VelocityContext context = new VelocityContext();
        if (map == null) {
            return context;
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }

        return context;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 配置工厂
 * 
 * @author obullxl@gmail.com
 * @version $Id: ConfigFactory.java, V1.0.1 2013年11月15日 下午4:18:00 $
 */
public class ConfigFactory {
    private static Configration       config;

    private static String             DEFAULT_HOST_NAME = "app";

    private static final List<String> paths             = Arrays.asList("config-test.xml", "config/config-test.xml", "config-test.properties", "config/config-test.properties", "config.xml",
                                                            "config/config.xml", "config.properties", "config/config.properties");

    /**
     * 取得Configration对象
     */
    public static Configration get() {
        if (config == null) {
            config = new Configration();

            //从配置文件中加载配置
            loadFromConfig();

            //从系统属性中加载配置
            loadFromSystem();

            // 打印系统参数
            print();
        }

        return config;
    }

    /**
     * 获取配置文件
     */
    private static String findConfigPath() {
        ClassLoader clsloader = Thread.currentThread().getContextClassLoader();
        for (String path : paths) {
            URL url = clsloader.getResource(path);
            if (url != null) {
                return path;
            }
        }

        return null;
    }

    /**
     * 从配置文件中加载配置
     */
    private static void loadFromConfig() {
        String path = findConfigPath();
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("配置文件不存在-" + paths);
        }

        ClassLoader clsloader = Thread.currentThread().getContextClassLoader();
        URL url = clsloader.getResource(path);
        try {
            if (StringUtils.endsWithIgnoreCase(path, ".xml")) {
                loadFromStream("xml", url.openStream());
            } else {
                loadFromStream("props", url.openStream());
            }
        } catch (Exception e) {
            throw new RuntimeException("无法加载配置文件-" + path);
        }

        // 个性化参数
        path = StringUtils.trimToEmpty(config.getCustomConfigPath());
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            try {
                if (StringUtils.endsWithIgnoreCase(path, ".xml")) {
                    loadFromStream("xml", new FileInputStream(file));
                } else {
                    loadFromStream("props", new FileInputStream(file));
                }
            } catch (Exception e) {
                System.err.print("加载个性化配置参数异常, 参数路径: " + path);
                e.printStackTrace();
            }
        }
    }

    /**
     * 从系统属性中加载配置
     * 
     * <p>
     * 从系统属性中读取配置信息，不是所有的配置都是有用的，所以没有必要都做处理
     * </p>
     */
    private static void loadFromSystem() {
        // 设置本地的host名称
        config.setProperty(Configration.SYS_HOST_NAME, getHostName());

        // 设置本机IP
        config.setProperty(Configration.SYS_IP, getNetworkAddress());

        // 校验本机的应用名称
        String sysAppName = config.getPropertyValue(Configration.SYS_APP_NAME);
        if (sysAppName == null || sysAppName.length() == 0) {
            throw new RuntimeException("必须设置app_name属性");
        }
    }

    /**
     * 根据输入流获取参数配置
     */
    private static final void loadFromStream(String xml, InputStream input) {
        Properties properties = new Properties();
        try {
            if (StringUtils.equalsIgnoreCase(xml, "xml")) {
                properties.loadFromXML(input);
            } else {
                properties.load(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = StringUtils.trimToEmpty(entry.getKey().toString());
            String value = StringUtils.trimToEmpty(entry.getValue().toString());

            // 从系统参数中取值，如果系统参数中有值，系统参数的值生效
            String sysValue = System.getProperty(key);
            if (StringUtils.isNotEmpty(sysValue)) {
                System.out.println("在Java -D参数中发现key[" + key + "]，将使用系统参数设置的值[" + sysValue + "] 替换原有的值 [" + value + "]");
                value = sysValue;
            }

            config.setProperty(key, value);
        }
    }

    private static void print() {
        Map<String, String> configs = config.getConfig();
        Set<Map.Entry<String, String>> configSet = configs.entrySet();

        System.out.println("系统启动参数:");
        for (Iterator<Map.Entry<String, String>> iterator = configSet.iterator(); iterator.hasNext();) {
            Map.Entry<String, String> entry = iterator.next();

            System.out.println(" | " + entry.getKey() + "  [" + entry.getValue() + "]");
        }
    }

    /**
     * 在超过一块网卡时有点问题，因为这里每次都只是取了第一块网卡绑定的IP地址
     */
    private static String getNetworkAddress() {
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    } else {
                        continue;
                    }
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 取得主机名称
     */
    private static String getHostName() {
        // 设置本地的host名称
        String hostName = DEFAULT_HOST_NAME;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            //ignore
            hostName = DEFAULT_HOST_NAME;
        }
        return hostName;
    }

    public static void main(String[] args) {
        String hostName = DEFAULT_HOST_NAME;
        String ip = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();

            ip = getNetworkAddress();
        } catch (UnknownHostException e) {
            //ignore
            e.printStackTrace();
        }

        System.out.println("ip :" + ip);
        System.out.println("hostname :" + hostName);

        System.out.println();
        get();
    }

}

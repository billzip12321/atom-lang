/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

/**
 * 黑名单服务
 * 
 * @author obullxl@gmail.com
 * @version $Id: BlackService.java, V1.0.1 2013年12月22日 下午1:20:13 $
 */
public class BlackUtils {
    /** Logger */
    private static final Logger      logger = LogUtils.get();

    /** 黑名单 */
    private static final Set<String> blacks = new HashSet<String>();

    /**
     * 根据黑名单文件初始化黑名单
     */
    public static final void initBlacks(String path) {
        logger.warn("[黑名单]-初始化黑名单-{}", path);

        if (StringUtils.isBlank(path) || !new File(path).exists()) {
            logger.warn("[黑名单]-黑名单文件不存在-{}", path);
            return;
        }

        int count = 0;
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            initBlacks(input);
        } catch (Exception e) {
            throw new RuntimeException("[黑名单]-初始化黑名单异常!");
        } finally {
            IOUtils.closeQuietly(input);
            logger.warn("[黑名单]-初始化完成-{}, 共加载黑名单[{}]个.", path, count);
        }
    }

    /**
     * 根据文件流初始化黑名单
     */
    public static final void initBlacks(InputStream input) {
        int count = 0;
        try {
            List<String> lines = IOUtils.readLines(input);
            for (String line : lines) {
                line = StringUtils.trim(line);
                if (StringUtils.isBlank(line) || StringUtils.startsWith(line, "#")) {
                    continue;
                }

                String[] words = StringUtils.split(line, ",");
                for (String word : words) {
                    blacks.add(word);
                    count++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("[黑名单]-初始化黑名单异常!");
        } finally {
            logger.warn("[黑名单]-初始化完成, 共加载黑名单[{}]个.", count);
        }
    }

    /**
     * 直接加载初始化黑名单
     */
    public static final void addBlacks(List<String> words) {
        blacks.addAll(words);
    }

    /**
     * 检测是否为黑名单
     */
    public static boolean isBlackWord(String word) {
        for (String black : blacks) {
            if (StringUtils.containsIgnoreCase(word, black)) {
                return true;
            }
        }

        return false;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 文件树工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: FileTreeUtils.java, V1.0.1 2014年1月3日 上午9:36:10 $
 */
public class FileTreeUtils {

    /**
     * 构建文件树结构
     */
    public static List<FileTreeNode> parse(String rootPath) {
        File root = new File(rootPath);
        Assert.isTrue(root.isDirectory(), "文件树根必须为文件目录！");
        String path = FilenameUtils.normalizeNoEndSeparator(rootPath);

        List<FileTreeNode> roots = new ArrayList<FileTreeNode>();

        File[] files = root.listFiles();
        for (File file : files) {
            FileTreeNode node = new FileTreeNode();
            roots.add(node);

            node.setName(file.getName());
            node.setPath(findFilePath(path, file.getAbsolutePath()));

            parse(path, file, node);
        }

        // 排序
        sort(roots);

        return roots;
    }

    /**
     * 树节点排序
     */
    public static void sort(List<FileTreeNode> nodes) {
        Collections.sort(nodes);

        for (FileTreeNode node : nodes) {
            if (node.isDirectory()) {
                sort(node.getChildren());
            }
        }
    }

    /**
     * 构建树节点
     */
    private static void parse(String path, File file, FileTreeNode node) {
        if (!file.isDirectory()) {
            return;
        }

        node.setDirectory(true);

        File[] files = file.listFiles();
        for (File ftmp : files) {
            FileTreeNode ntmp = new FileTreeNode();
            node.getChildren().add(ntmp);

            ntmp.setName(ftmp.getName());
            ntmp.setPath(findFilePath(path, ftmp.getAbsolutePath()));

            parse(path, ftmp, ntmp);
        }
    }

    /**
     * 获取文件相对路径
     */
    private static String findFilePath(String path, String fpath) {
        fpath = FilenameUtils.normalizeNoEndSeparator(fpath);
        fpath = StringUtils.substringAfterLast(fpath, path);
        return FilenameUtils.normalize(fpath, true);
    }

}

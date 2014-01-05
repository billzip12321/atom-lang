/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.obullxl.lang.ToString;

/**
 * 文件树节点
 * 
 * @author obullxl@gmail.com
 * @version $Id: FileTreeNode.java, V1.0.1 2014年1月3日 下午3:38:11 $
 */
public class FileTreeNode extends ToString implements Comparable<FileTreeNode> {
    private static final long  serialVersionUID = 6377774421008190809L;

    /** 文件名称 */
    private String             name;

    /** 文本路径 */
    private String             path;

    /** 是否为目录 */
    @JSONField(name = "isParent")
    private boolean            directory;

    /** 子节点 */
    private List<FileTreeNode> children;

    // ~~~~~~~~~~~ 公用方法 ~~~~~~~~~~~ //

    public int compareTo(FileTreeNode dst) {
        if (dst == null) {
            return -1;
        }

        if (this.isDirectory()) {
            if (!dst.isDirectory()) {
                return -1;
            } else {
                return StringUtils.trimToEmpty(this.getName()).compareTo(StringUtils.trimToEmpty(dst.getName()));
            }
        }

        if (!dst.isDirectory()) {
            return StringUtils.trimToEmpty(this.getName()).compareTo(StringUtils.trimToEmpty(dst.getName()));
        }

        return 1;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public void setChildren(List<FileTreeNode> children) {
        this.children = children;
    }

    public List<FileTreeNode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<FileTreeNode>();
        }

        return children;
    }

}

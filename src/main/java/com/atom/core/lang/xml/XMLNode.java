/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.xml;

import java.util.ArrayList;
import java.util.List;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ToString;

/**
 * XML节点模型
 * 
 * @author shizihu
 * @version $Id: XMLNode.java, v 0.1 2012-9-13 上午09:50:04 shizihu Exp $
 */
public class XMLNode extends ToString {
    private static final long serialVersionUID = 2545193236502133681L;

    /** 名称（小写） */
    private String            name;

    /** 文本内容，可能为空 */
    private String            text;

    /** 节点属性（Key为小写） */
    private MapExt            extMap;

    /** 子节点 */
    private List<XMLNode>     children;

    // ~~~~~~~~~~~ 公用方法 ~~~~~~~~~~~ //

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChildren(List<XMLNode> children) {
        this.children = children;
    }

    public List<XMLNode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<XMLNode>();
        }

        return children;
    }

    public MapExt getExtMap() {
        if (this.extMap == null) {
            this.extMap = new MapExt();
        }

        return extMap;
    }

    public void setExtMap(MapExt extMap) {
        this.extMap = extMap;
    }

}

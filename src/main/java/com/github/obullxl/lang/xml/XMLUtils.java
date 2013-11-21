/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.xml;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 采用XPath方式解析XML文件
 * 
 * @author obullxl@gmail.com
 * @version $Id: XMLUtils.java, V1.0.1 2013-2-25 上午10:12:06 $
 */
public final class XMLUtils {

    /**
     * 解析XML文件
     * 
     * @param file XML文件路径
     */
    public static final XMLNode toXMLNode(String file) {
        InputStream input = null;
        try {
            input = new FileInputStream(file);

            // 解析
            return toXMLNode(input);
        } catch (Exception e) {
            throw new RuntimeException("开始解析XML异常！", e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 开始解析XML文件
     * 
     * @param input XML文件输入流，使用完成之后，不进行关闭！
     */
    public static final XMLNode toXMLNode(InputStream input) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(input);

            return parse(doc.getDocumentElement());
        } catch (Exception e) {
            throw new RuntimeException("解析XML异常！", e);
        }
    }

    /**
     * Search the first XMLNode that equal to the xname
     */
    public static final XMLNode findByName(XMLNode xroot, String xname) {
        if (xroot == null || StringUtils.isBlank(xname)) {
            return null;
        }

        if (StringUtils.equalsIgnoreCase(xroot.getName(), xname)) {
            return xroot;
        }

        for (XMLNode xnode : xroot.getChildren()) {
            return findByName(xnode, xname);
        }

        return null;
    }

    /**
     * 解析XML节点及其儿子节点
     */
    private final static XMLNode parse(Node element) {
        // 节点基本信息
        XMLNode node = toXMLNode(element);

        // 解析儿子节点
        parse(node, element);

        // XML节点信息
        return node;
    }

    /**
     * 解析XML完整儿子节点
     */
    private final static void parse(XMLNode parentNode, Node parentElement) {
        // 儿子节点
        NodeList elements = parentElement.getChildNodes();
        for (int i = 0; i < elements.getLength(); i++) {
            Node element = elements.item(i);
            if (element.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // 解析单节点
            XMLNode node = toXMLNode(element);
            parentNode.getChildren().add(node);

            // 循环其子节点
            parse(node, element);
        }
    }

    /**
     * 复制XML节点基本信息，节点名称和节点属性名称均转化为小写！
     */
    private final static XMLNode toXMLNode(Node element) {
        // 节点信息
        XMLNode node = new XMLNode();

        // 节点名称
        node.setName(StringUtils.lowerCase(element.getNodeName()));
        // 节点内容
        if (!isParentNode(element)) {
            node.setText(StringUtils.trimToNull(element.getTextContent()));
        }

        // 节点属性
        NamedNodeMap props = element.getAttributes();
        for (int i = 0; i < props.getLength(); i++) {
            Node prop = props.item(i);
            String pname = StringUtils.lowerCase(prop.getNodeName());

            // 属性
            node.getExtMap().put(pname, prop.getTextContent());
        }

        return node;
    }

    /**
     * 监测XML节点是否还有儿子节点
     */
    private final static boolean isParentNode(Node element) {
        NodeList elements = element.getChildNodes();
        for (int i = 0; i < elements.getLength(); i++) {
            Node child = elements.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }

        return false;
    }

}

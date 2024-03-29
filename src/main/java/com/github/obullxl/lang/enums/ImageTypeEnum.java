/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 图片类型枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageTypeEnum.java, V1.0.1 2014年1月5日 上午9:24:31 $
 */
public enum ImageTypeEnum implements EnumBase {
    //
    IMG_BMP("B", "bmp"),
    //
    IMG_GIF("G", "gif"),
    //
    IMG_JPG("J", "jpg"),
    //
    IMG_PNG("P", "png"),
    //
    ;

    private final String code;
    private final String ext;

    private ImageTypeEnum(String code, String ext) {
        this.code = code;
        this.ext = ext;
    }

    /**
     * 获取默认类型
     */
    public static final ImageTypeEnum findDefault() {
        return IMG_JPG;
    }

    /**
     * 根据代码获取枚举
     */
    public static final ImageTypeEnum findByCode(String code) {
        for (ImageTypeEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return null;
    }

    /**
     * 获取图片后缀
     */
    public String findImageExt() {
        return "." + this.ext;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.ordinal();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.code;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.code();
    }

    public String getExt() {
        return ext;
    }

}

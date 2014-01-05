/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.biz;

import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.enums.ImageTypeEnum;

/**
 * 图片信息
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageMeta.java, V1.0.1 2014年1月5日 上午9:35:21 $
 */
public class ImageMeta extends ToString {
    private static final long serialVersionUID = -5157911547572091581L;

    /** 图片 */
    private boolean           image;

    /** 图片类型 */
    private ImageTypeEnum     imgTypeEnum;

    /** 图片大小(Byte) */
    private long              length;

    /** 宽度 */
    private int               width;

    /** 高度 */
    private int               height;

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public ImageTypeEnum getImgTypeEnum() {
        return imgTypeEnum;
    }

    public void setImgTypeEnum(ImageTypeEnum imgTypeEnum) {
        this.imgTypeEnum = imgTypeEnum;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import com.github.obullxl.lang.biz.ImageMeta;
import com.github.obullxl.lang.enums.ImageTypeEnum;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
import com.sun.imageio.plugins.wbmp.WBMPImageReader;

/**
 * 图片工具栏
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageUtils.java, V1.0.1 2014年1月4日 下午7:33:07 $
 */
public class ImageUtils {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /**
     * 检测是否为图片
     * <p/>
     * 注意：文件输入流在本方法执行之后并没有关闭！
     */
    public static boolean isImage(InputStream input) {
        try {
            if (input == null || input.available() <= 0) {
                return false;
            }

            Image image = ImageIO.read(input);
            if (image == null || image.getWidth(null) <= 0 || image.getHeight(null) <= 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            logger.warn("[图片检测]-输入流检测异常, 输入流不是图片！");
            return false;
        }
    }

    /**
     * 检测是否为图片
     */
    public static boolean isImage(byte[] data) {
        InputStream input = null;
        try {
            if (data == null || data.length <= 0) {
                return false;
            }

            input = new ByteArrayInputStream(data);
            return isImage(input);
        } catch (Exception e) {
            logger.warn("[图片检测]-字节数组检测异常, 字节数组不是图片！");
            return false;
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 检测是否为图片
     */
    public static boolean isImage(String imgPath) {
        InputStream input = null;
        try {
            File imgFile = new File(imgPath);
            if (!imgFile.isFile() || imgFile.length() <= 0) {
                return false;
            }

            input = new FileInputStream(imgFile);
            return isImage(input);
        } catch (Exception e) {
            logger.warn("[图片检测]-图片文件检测异常, 文件[{}]不是图片！", imgPath);
            return false;
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 获取图片类型
     * <p/>
     * 注意：文件输入流在本方法执行之后并没有关闭！
     */
    public static ImageTypeEnum findImageExt(InputStream input) {
        ImageTypeEnum imgExt = null;
        ImageInputStream iinput = null;
        try {
            iinput = new MemoryCacheImageInputStream(input);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
            while (readers.hasNext()) {
                ImageReader reader = readers.next();
                if (reader instanceof GIFImageReader) {
                    imgExt = ImageTypeEnum.IMG_GIF;
                    break;
                } else if (reader instanceof JPEGImageReader) {
                    imgExt = ImageTypeEnum.IMG_JPG;
                    break;
                } else if (reader instanceof PNGImageReader) {
                    imgExt = ImageTypeEnum.IMG_PNG;
                    break;
                } else if (reader instanceof BMPImageReader || reader instanceof WBMPImageReader) {
                    imgExt = ImageTypeEnum.IMG_BMP;
                    break;
                }
            }
        } catch (Exception e) {
            logger.warn("[图片检测]-获取图片后缀异常, 后缀[{}]！", imgExt);
        } finally {
            IOUtils.closeQuietly(iinput);
        }

        return imgExt;
    }

    /**
     * 获取图片类型
     */
    public static ImageTypeEnum findImageExt(String imgPath) {
        ImageTypeEnum imgExt = null;
        InputStream input = null;
        try {
            File imgFile = new File(imgPath);
            if (!imgFile.isFile() || imgFile.length() <= 0) {
                return imgExt;
            }

            input = new FileInputStream(imgFile);
            imgExt = findImageExt(input);
        } catch (Exception e) {
            logger.warn("[图片检测]-获取图片后缀异常, 文件[{}]后缀[{}]！", imgPath, imgExt);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return imgExt;
    }

    /**
     * 获取图片类型
     */
    public static ImageTypeEnum findImageExt(byte[] data) {
        ImageTypeEnum imgExt = null;
        InputStream input = null;
        try {
            if (data == null || data.length <= 0) {
                return imgExt;
            }

            input = new ByteArrayInputStream(data);
            imgExt = findImageExt(input);
        } catch (Exception e) {
            logger.warn("[图片检测]-获取图片后缀异常, 字节数组后缀[{}]！", imgExt);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return imgExt;
    }

    /**
     * 获取图片元信息
     */
    public static ImageMeta findImgMeta(InputStream input) {
        ImageMeta meta = new ImageMeta();
        try {
            if (input == null) {
                return meta;
            }

            // 大小
            meta.setLength(input.available());
            if (meta.getLength() <= 0) {
                return meta;
            }

            // 尺寸
            Image image = ImageIO.read(input);
            if (image == null) {
                return meta;
            }

            meta.setWidth(image.getWidth(null));
            meta.setHeight(image.getHeight(null));
            if (meta.getWidth() <= 0 || meta.getHeight() <= 0) {
                return meta;
            }

            // 类型
            meta.setImgTypeEnum(findImageExt(input));
            if(meta.getImgTypeEnum() == null) {
                meta.setImgTypeEnum(ImageTypeEnum.findDefault());
            }

            // 完成
            meta.setImage(true);
        } catch (Exception e) {
            logger.warn("[图片元数据]-获取图片元数据异常-{}", meta);
        }

        return meta;
    }

    /**
     * 获取图片元信息
     */
    public static ImageMeta findImgMeta(String imgPath) {
        ImageMeta meta = new ImageMeta();
        InputStream input = null;
        try {
            File imgFile = new File(imgPath);
            if (!imgFile.isFile() || imgFile.length() <= 0) {
                return meta;
            }

            input = new FileInputStream(imgFile);
            meta = findImgMeta(input);
        } catch (Exception e) {
            logger.warn("[图片元数据]-获取图片文件[{}]元数据异常-{}！", imgPath, meta);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return meta;
    }

    /**
     * 获取图片元信息
     */
    public static ImageMeta findImgMeta(byte[] data) {
        ImageMeta meta = new ImageMeta();
        InputStream input = null;
        try {
            if (data == null || data.length <= 0) {
                return meta;
            }

            input = new ByteArrayInputStream(data);
            meta = findImgMeta(input);
        } catch (Exception e) {
            logger.warn("[图片元数据]-获取图片元数据异常-{}！", meta);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return meta;
    }

    /**
     * 生成缩略图
     */
    public static void preview(String imgSrc, String imgDst, int dstWidth, int dstHeight) throws Exception {
        File srcFile = new File(imgSrc);
        Image image = ImageIO.read(srcFile);
        int srcWidth = image.getWidth(null);
        int srcHeight = image.getHeight(null);

        int w = dstWidth;
        int h = dstHeight;
        if (srcWidth / srcHeight > dstWidth / dstHeight) {
            h = (int) (srcHeight * dstWidth / srcWidth);
        } else {
            w = (int) (srcWidth * dstHeight / srcHeight);
        }

        BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(image, 0, 0, w, h, null);

        OutputStream output = null;
        try {
            output = new FileOutputStream(imgDst);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
            encoder.encode(buffImg);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.storex;

import java.io.File;

import com.github.obullxl.lang.storex.impl.OSSStoreX;

/**
 * 阿里云测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: OSSStoreXMain.java, V1.0.1 2014年6月13日 下午9:25:03 $
 */
public class OSSStoreXMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        OSSStoreX store = new OSSStoreX();
        store.setBucket("azdai");
        store.setAccessId("8i4nw7nod9ww17qr3k5fkckx");
        store.setAccessKey("f72VfmcApY/x8IaPTu9yEqrXrgQ=");
        store.init();

        // 上传文件
        store.store(new File("c:/azdai.db"), "public/azdai.db");
    }

}

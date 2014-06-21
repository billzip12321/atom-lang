/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.storex;

import java.io.File;

import com.github.obullxl.lang.storex.impl.JSSStoreX;

/**
 * 京东云测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: JSSStoreXMain.java, V1.0.1 2014年6月13日 下午9:25:03 $
 */
public class JSSStoreXMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JSSStoreX store = new JSSStoreX();
        store.setBucket("azdai");
        store.setAccessId("831835cde8d445f6a1157671f8d6893e");
        store.setAccessKey("3cc9c8cbabcf49ab9ee66ccb8b250c57GrqAR2g1");
        store.init();

        // 上传文件
        store.store(new File("c:/azdai.db"), "public/azdai.db");
        
        store.destroy();
    }

}

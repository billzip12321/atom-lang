/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserSplitMain.java, V1.0.1 2013年11月30日 下午5:51:29 $
 */
public class UserSplitMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        InputStream input = null;
        try {
            input = new FileInputStream("c:/Users/shizihu/Desktop/WorkSpace/数据恢复/账务用户列表.log");
            List<String> lines = IOUtils.readLines(input);
            int size = lines.size();
            System.out.println("用户总数: " + size);

            int tot = 0;
            int psize = 5000;
            int files = (size + psize - 1) / psize;
            for (int i = 0; i < files; i++) {
                int start = i * psize;
                int end = start + psize;
                end = end > size ? size : end;

                StringBuilder txt = new StringBuilder();
                for (int j = start; j < end; j++) {
                    txt.append(lines.get(j));
                    if (j != end - 1) {
                        txt.append("\r\n");
                    }
                    
                    tot++;
                }

                String index = Integer.toString((i + 1));
                index = StringUtils.leftPad(index, 2, "0");
                OutputStream output = null;
                try {
                    output = new FileOutputStream("c:/Users/shizihu/Desktop/WorkSpace/数据恢复/账务用户列表-" + index + ".txt");
                    IOUtils.write(txt.toString(), output);
                } finally {
                    IOUtils.closeQuietly(output);
                }
            }

            System.out.println("分文件总数: " + tot);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

}

/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.core.lang;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.lang.EmailSetting;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmailSettingTest.java, V1.0.1 2014年6月4日 下午8:46:39 $
 */
public class EmailSettingTest {

    /**
     * {"defaultEncoding":"UTF-8","extProperties":{},"javaMailProperties":{"KEYA":"VALUE-A","KEYB":"VALUE-B"}}
     */
    public static void main(String[] args) {
        EmailSetting setting = new EmailSetting();
        setting.getJavaMailProperties().setProperty("KEYA", "VALUE-A");
        setting.getJavaMailProperties().setProperty("KEYB", "VALUE-B");
        
        String text = JSON.toJSONString(setting);
        System.out.println(text);
    }

}

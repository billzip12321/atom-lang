/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: DateMonthMain.java, V1.0.1 2013年11月30日 下午5:19:21 $
 */
public class DateMonthMain {

    public static void main(String[] args) throws Exception {
        Date now = new Date();
        now = parse(format(now, "yyyyMM"),"yyyyMM");
        Calendar cnow = Calendar.getInstance();
        cnow.setTimeInMillis(now.getTime());
        
        cnow.add(Calendar.MONTH, 1);
        long next = cnow.getTimeInMillis();
    }
    
    public static String format(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
    
    public static Date parse(String date, String format) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }
    
}

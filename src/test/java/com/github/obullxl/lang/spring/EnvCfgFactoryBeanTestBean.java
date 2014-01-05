/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.lang.spring;

import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnvCfgFactoryBeanTestBean.java, V1.0.1 2013年12月31日 上午10:10:49 $
 */
public class EnvCfgFactoryBeanTestBean implements InitializingBean {

    private String myProp;
    private String myProp1;
    private String myProp2;
    private String myProp3;
    private String myProp4;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        System.out.println("我的参数值: " + myProp);
        System.out.println("我的参数值1: " + myProp1);
        System.out.println("我的参数值2: " + myProp2);
        System.out.println("我的参数值3: " + myProp3);
        System.out.println("我的参数值4: " + myProp4);
    }

    public void setMyProp(String myProp) {
        this.myProp = myProp;
    }

    public void setMyProp1(String myProp1) {
        this.myProp1 = myProp1;
    }

    public void setMyProp2(String myProp2) {
        this.myProp2 = myProp2;
    }

    public void setMyProp3(String myProp3) {
        this.myProp3 = myProp3;
    }

    public void setMyProp4(String myProp4) {
        this.myProp4 = myProp4;
    }

}

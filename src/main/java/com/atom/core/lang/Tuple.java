/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.lang;

/**
 * 双数元组
 * 
 * @author obullxl@gmail.com
 * @version $Id: Tuple.java, V1.0.1 2013-6-15 下午7:39:44 $
 */
public class Tuple<V1, V2> extends ToString {
    private static final long serialVersionUID = -999886351353092606L;

    /** 第一数 */
    private V1                one;

    /** 第二数 */
    private V2                two;

    /**
     * CTOR
     */
    public Tuple() {
    }

    public Tuple(V1 one, V2 two) {
        this.one = one;
        this.two = two;
    }

    // ~~~~~~~~~ getters and setters ~~~~~~~~~ //

    public V1 getOne() {
        return one;
    }

    public void setOne(V1 one) {
        this.one = one;
    }

    public V2 getTwo() {
        return two;
    }

    public void setTwo(V2 two) {
        this.two = two;
    }

}

package com.ljw.interfacePlus;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.Date;

/**
 * @Description: 接口增强了static defult
 * 接口属性默认public static final
 * 新增的静态方法和默认方法是否会影响其成为一个函数接口呢，并不会，原因是：静态方法和默认方法均为非抽象方法！
 * 同理，复写父类的非抽象方法也不影响其成为一个函数接口，如复写equals方法
 * @Author: jianweil
 * @date: 2020/12/8 20:12
 */

@FunctionalInterface
public interface InterfacePlus {

    //public static final
    String defultValue = null;

    void run();

    @Override
    boolean equals(Object o);

    static Date createDate() {
        return new Date();
    }

    default void sayHello() {
        System.out.println("Hello Java8!");
    }
}

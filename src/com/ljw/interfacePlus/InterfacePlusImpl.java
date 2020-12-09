package com.ljw.interfacePlus;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 20:22
 */
public class InterfacePlusImpl implements InterfacePlus {
    @Override
    public void run() {
        System.out.println("子类run-------");
    }

    @Override
    public void sayHello() {
        System.out.println("子类 Hello Java8!-------");
    }
}

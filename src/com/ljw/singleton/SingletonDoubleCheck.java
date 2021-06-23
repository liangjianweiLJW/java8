package com.ljw.instance;

/**
 * @Description: 单例模式 双重校验
 * <p>
 * 通过加锁，可以保证同时只有一个线程走到第二个判空代码中去，这样保证了只创建 一个实例。
 * 这里还用到了volatile关键字来修饰singleton，其最关键的作用是防止指令重排。
 * @Author: jianweil
 * @date: 2021/6/23 14:05
 */
public class SingletonDoubleCheck {

    private static volatile SingletonDoubleCheck instance;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getSingleton() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}

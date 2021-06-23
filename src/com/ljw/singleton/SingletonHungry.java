package com.ljw.singleton;

/**
 * @Description: 单例模式 饿汉式
 * <p>
 * 在类被加载的时候就把Singleton实例给创建出来了。
 * 饿汉式的缺点就是，可能在还不需要此实例的时候就已经把实例创建出来了，没起到lazy loading的效果。优点就是实现简单，而且安全可靠。
 * @Author: jianweil
 * @date: 2021/6/23 14:05
 */
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}

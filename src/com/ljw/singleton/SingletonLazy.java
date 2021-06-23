package com.ljw.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 单例模式 懒汉式
 * <p>
 * 在真正需要的时候再去创建实例。在getInstance方法中，先判断实例是否为空再决定是否去创建实例，
 * 看起来似乎很完美，但是存在线程安全问题。在并发获取实例的时候，可能会存在构建了多个实例的情况。所以，需要对此代码进行下改进。
 * @Author: jianweil
 * @date: 2021/6/23 14:05
 */
public class SingletonLazy implements Serializable {

    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.反射攻击
        SingletonLazy singleton = SingletonLazy.getInstance();
        Constructor<SingletonLazy> constructor = SingletonLazy.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonLazy newSingleton = constructor.newInstance();
        //false 通过结果看，这两个实例不是同一个，这就违背了单例模式的原则了。
        System.out.println(singleton == newSingleton);
        //2.反序列化攻击
        SingletonLazy instance = SingletonLazy.getInstance();
        byte[] serialize = SerializationUtils.serialize(instance);
        SingletonLazy newInstance = SerializationUtils.deserialize(serialize);
        //false 通过结果看，这两个实例不是同一个，这就违背了单例模式的原则了。
        System.out.println(instance == newInstance);
    }
}

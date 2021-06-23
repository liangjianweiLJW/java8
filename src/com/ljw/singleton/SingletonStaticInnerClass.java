package com.ljw.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 单例模式 静态内部类
 * 通过静态内部类的方式实现单例模式是线程安全的，同时静态内部类不会在Singleton类加载时就加载，
 * 而是在调用getInstance()方法时才进行加载，达到了懒加载的效果。
 * <p>
 * 似乎静态内部类看起来已经是最完美的方法了，其实不是，可能还存在反射攻击或者反序列化攻击。
 * @Author: jianweil
 * @date: 2021/6/23 14:05
 */
public class SingletonStaticInnerClass implements Serializable {

    private static class SingletonHolder {
        private static SingletonStaticInnerClass instance = new SingletonStaticInnerClass();
    }

    private SingletonStaticInnerClass() {

    }

    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.反射攻击
        SingletonStaticInnerClass singleton = SingletonStaticInnerClass.getInstance();
        Constructor<SingletonStaticInnerClass> constructor = SingletonStaticInnerClass.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonStaticInnerClass newSingleton = constructor.newInstance();
        //false 通过结果看，这两个实例不是同一个，这就违背了单例模式的原则了。
        System.out.println(singleton == newSingleton);
        //2.反序列化攻击
        SingletonStaticInnerClass instance = SingletonStaticInnerClass.getInstance();
        byte[] serialize = SerializationUtils.serialize(instance);
        SingletonStaticInnerClass newInstance = SerializationUtils.deserialize(serialize);
        //false 通过结果看，这两个实例不是同一个，这就违背了单例模式的原则了。
        System.out.println(instance == newInstance);
    }
}

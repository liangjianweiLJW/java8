package com.ljw.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 枚举单例模式 推荐
 * 在effective java（这本书真的很棒）中说道，最佳的单例实现模式就是枚举模式。
 * 利用枚举的特性，让JVM来帮我们保证线程安全和单一实例的问题。除此之外，写法还特别简单。
 * @Author: jianweil
 * @date: 2021/6/23 12:01
 */
public enum SingletonEnum {
    INSTANCE;

    public int i = 0;

    public void anyNameMetho() {
        i++;
        System.out.println("我现在是单例，这是我第" + i + "次调用！！");
    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        // 调用这样子调用
        for (int z = 0; z < 10; z++) {
            SingletonEnum demo = SingletonEnum.INSTANCE;
            //直接通过Singleton.INSTANCE.doSomething()的方式调用即可。方便、简洁又安全。
            demo.anyNameMetho();
        }

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonEnum demo = SingletonEnum.INSTANCE;
                    //直接通过Singleton.INSTANCE.doSomething()的方式调用即可。方便、简洁又安全。
                    demo.anyNameMetho();
                }
            });
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.start();
        }
        for (Thread thread : list) {
            thread.join();
        }

        int i = SingletonEnum.INSTANCE.i;
        //可能出现小于100000，单例只是单个对象实例，线程安全操作还是不能保证
        System.out.println(i);
        //2.反序列化攻击
        SingletonEnum instance = SingletonEnum.INSTANCE;
        byte[] serialize = SerializationUtils.serialize(instance);
        SingletonEnum newInstance = SerializationUtils.deserialize(serialize);
        //true 还是单例
        System.out.println(instance == newInstance);

        //1.反射攻击
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        //报错  JVM规定枚举不能利用反射
        Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonEnum newSingleton = constructor.newInstance();
        System.out.println(singleton == newSingleton);
    }
}

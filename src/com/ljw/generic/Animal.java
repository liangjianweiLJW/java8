package com.ljw.generic;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 14:57
 */
public abstract class Animal {
    // 成员属性
    private String name;

    // get/set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 构造方法
    public Animal() {
        super();
    }

    public Animal(String name) {
        super();
        this.setName(name);
    }

    // 抽象方法
    public abstract void play();
}

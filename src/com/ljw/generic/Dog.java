package com.ljw.generic;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 14:59
 */
public class Dog extends Animal {
    // 构造方法
    public Dog() {
        super();
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println("小狗" + this.getName() + "在做游戏！");
    }
}

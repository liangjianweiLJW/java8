package com.ljw.generic;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 15:01
 */
public class Cat extends Animal {
    public Cat() {
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println("小猫" + this.getName() + "在做游戏！");
    }
}

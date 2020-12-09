package com.ljw.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 15:02
 */
public class AnimalTest {
    public static void main(String[] args) {
        // 创建小狗列表并添加对象
        List<Dog> dog = new ArrayList<>();
        dog.add(new Dog("巴迪"));
        dog.add(new Dog("豆豆"));
        // 创建小猫列表并添加对象
        List<Cat> cat = new ArrayList<>();
        cat.add(new Cat("花花"));
        cat.add(new Cat("凡凡"));
        // 创建做游戏对象并调用方法
        AnimalPlay ap = new AnimalPlay();
        ap.playGames(dog);
        ap.playGames(cat);
    }
}

package com.ljw.generic;

import java.util.List;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 15:02
 */
public class AnimalPlay {
    public void playGames(List<? extends Animal> animal) {
        for(Animal a : animal) {
            a.play();
        }
    }
}

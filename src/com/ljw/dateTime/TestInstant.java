package com.ljw.dateTime;

import java.time.Instant;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/9 11:38
 */
public class TestInstant {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        //获取秒数
        long currentSecond = instant.getEpochSecond();
        System.out.println(currentSecond);
        //获取毫秒数
        long currentMilli = instant.toEpochMilli();
        System.out.println(currentMilli);
        //获取毫秒数
        System.out.println(System.currentTimeMillis());


    }
}

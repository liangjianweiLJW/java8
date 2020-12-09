package com.ljw.dateTime;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 21:03
 */
public class TestLocalTime {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(18, 51, 10);
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(13, 51, 10,200);
        System.out.println("localTime:"+ localTime);
        System.out.println("localTime1:"+ localTime1);
        System.out.println("localTime2:"+ localTime2);


        //获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        //0-11
        int hour2 = localTime.get(ChronoField.HOUR_OF_AMPM);
        System.out.println("getHour:"+hour);
        System.out.println("HOUR_OF_DAY:"+hour1);
        System.out.println("HOUR_OF_AMPM:"+hour2);
        //获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        Long minute2 = localTime.getLong(ChronoField.MICRO_OF_DAY);
        System.out.println("getMinute:"+minute);
        System.out.println("MINUTE_OF_HOUR:"+minute1);
        System.out.println("MICRO_OF_DAY:"+minute2);
        //获取秒
        int second = localTime.getSecond();
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("getSecond:"+second);
        System.out.println("SECOND_OF_MINUTE:"+second1);

    }
}

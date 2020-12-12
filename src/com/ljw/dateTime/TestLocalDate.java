package com.ljw.dateTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 21:03
 */
public class TestLocalDate {
    public static void main(String[] args) {
        //获取当前年月日
        LocalDate localDate = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 12, 31);

        //构造指定的年月日
        LocalDate localDateOf = LocalDate.of(2019, 9, 10);
        //2020-12-09
        System.out.println("获取当前年月日:" + localDate);
        //2020-12-09
        System.out.println("构造指定的年月日:" + localDateOf);

        //获取年、月、日、星期几
      /*  year: 2020
        year1: 2020
        month: DECEMBER
        month1: 12
        day: 9
        day1: 9
        day2: 344
        dayOfWeek: WEDNESDAY
        dayOfWeek1: 3*/
        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        int year2 = localDate.get(ChronoField.YEAR_OF_ERA);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        int day2 = localDate2.get(ChronoField.DAY_OF_YEAR);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("year: " + year);
        System.out.println("year1: " + year1);
        System.out.println("year2: " + year2);
        System.out.println("month: " + month);
        System.out.println("month1: " + month1);
        System.out.println("day: " + day);
        System.out.println("day1: " + day1);
        System.out.println("day2: " + day2);
        System.out.println("dayOfWeek: " + dayOfWeek);
        System.out.println("dayOfWeek1: " + dayOfWeek1);


    }
}

package com.ljw.dateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * @Description: LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本
 * @Author: jianweil
 * @date: 2020/12/9 11:46
 */
public class TestCalculation {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 10,
                14, 46, 56);
        System.out.println("of:" + localDateTime.toString());

        //增加一年
        localDateTime = localDateTime.plusYears(1);
        System.out.println("plusYears:" + localDateTime);
        localDateTime = localDateTime.plus(1, ChronoUnit.YEARS);
        System.out.println("plus:" + localDateTime);

        //减少一个月
        localDateTime = localDateTime.minusMonths(1);
        System.out.println("minusMonths:" + localDateTime);
        localDateTime = localDateTime.minus(1, ChronoUnit.MONTHS);
        System.out.println("minus:" + localDateTime);

        //修改年为2019
        localDateTime = localDateTime.withYear(2020);
        System.out.println("withYear:" + localDateTime);
        //修改为2022
        localDateTime = localDateTime.with(ChronoField.YEAR, 2022);
        System.out.println("with:" + localDateTime);


        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.with(firstDayOfYear());
        System.out.println("firstDayOfYear:" + localDate1);


        //格式化时间
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("BASIC_ISO_DATE:" + s1);
        System.out.println("ISO_LOCAL_DATE:" + s2);

        //自定义格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate.format(dateTimeFormatter);
        System.out.println("ofPattern:" + s3);

        //解析时间
        //和SimpleDateFormat相比，DateTimeFormatter是线程安全的
        LocalDate localDate11 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate localDate12 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("parse BASIC_ISO_DATE:" + localDate11);
        System.out.println("parse ISO_LOCAL_DATE:" + localDate12);

      /*  //将LocalDateTime字段以指定格式化日期的方式返回给前端
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        protected LocalDateTime gmtModified;

        //对前端传入的日期进行格式化
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        protected LocalDateTime gmtModified;*/
    }
}

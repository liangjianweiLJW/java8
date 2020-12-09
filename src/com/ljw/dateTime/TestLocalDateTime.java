package com.ljw.dateTime;

import java.time.*;

/**
 * @Description:
 *
 * 为什么需要LocalDate、LocalTime、LocalDateTime
 * Date如果不格式化，打印出的日期可读性差 Tue Sep 10 09:34:04 CST 2019
 * 使用SimpleDateFormat对时间进行格式化，但SimpleDateFormat是线程不安全的
 *   当多个线程同时使用相同的SimpleDateFormat对象【如用static修饰的SimpleDateFormat】调用format方法时，
 *   多个线程会同时调用calendar.setTime方法，可能一个线程刚设置好time值另外的一个线程马上把设置的time值给修改了导致返回的格式化时间可能是错误的
 *   在多并发情况下使用SimpleDateFormat需格外注意SimpleDateFormat除了format是线程不安全以外，parse方法也是线程不安全的。
 *
 * 多线程并发如何保证线程安全 - 避免线程之间共享一个SimpleDateFormat对象，每个线程使用时都创建一次SimpleDateFormat对象 => 创建和销毁对象的开销大 - 对使用format和parse方法的地方进行加锁 => 线程阻塞性能差 - 使用ThreadLocal保证每个线程最多只创建一次SimpleDateFormat对象 => 较好的方法
 *
 * Date对时间处理比较麻烦，比如想获取某年、某月、某星期，以及n天以后的时间，如果用Date来处理的话真是太难了，你可能会说Date类不是有getYear、getMonth这些方法吗，获取年月日很Easy，但都被弃用了啊
 * @Author: jianweil
 * @date: 2020/12/8 21:03
 */
public class TestLocalDateTime {
    public static void main(String[] args) {
        //创建LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        System.out.println("now:"+localDateTime);
        System.out.println("of:"+localDateTime1);

        LocalDate localDate =LocalDate.now() ;
        LocalTime localTime =LocalTime.now() ;
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);

        //当前日期拼接localTime
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        //localDate拼接当前时间
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        System.out.println("LocalDateTime of:"+localDateTime2);
        System.out.println("atTime:"+localDateTime3);
        System.out.println("atDate:"+localDateTime4);
        //获取LocalDate
        LocalDate localDate2 = localDateTime.toLocalDate();
        //获取LocalTime
        LocalTime localTime2 = localDateTime.toLocalTime();

        System.out.println("toLocalDate:"+localDate2);
        System.out.println("toLocalTime:"+localTime2);

    }
}

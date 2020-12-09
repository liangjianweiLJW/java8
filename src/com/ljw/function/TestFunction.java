package com.ljw.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description: Predicate应用 Function应用 Consumer应用
 * @Author: jianweil
 * @date: 2020/12/8 15:50
 */
public class TestFunction {
    static List<Emp> emps = Arrays.asList(
            new Emp(1, "yw"),
            new Emp(2, "yt"),
            new Emp(3, "yp"),
            new Emp(4, "yc"));

    private static <T,R> void printEmpNameWhenEmpNoLg1(Iterable<T> source, Predicate<T> predicate, Function<T,R> function,
                                                Consumer<R> consumer)
    {
        for(T t:source)
        {
            if(predicate.test(t))
            {
                R r = function.apply(t);
                consumer.accept(r);
            }
        }
    }

    public static void main(String[] args) {

        //判断
        Predicate<Emp> predicate = emp -> emp.getEmpno()>2;
        //定义函数
        Function<Emp,String> function = emp -> emp.getEname();
        //定义消费者
        Consumer<String> consumer = System.out::println;
        TestFunction.printEmpNameWhenEmpNoLg1(emps,predicate,function,consumer);
    }
}

package com.ljw.function;

import java.util.function.Function;

/**
 * @Description: Function<T t, R r>函数式接口
 *               R apply(T t); 数据类型转换
 * @Author: jianweil
 * @date: 2020/12/8 15:50
 */
public class TestFunction {
    /**
     * Function<T t,R r>函数式接口
     * R apply(T t);    数据类型转换
     *
     * @param s
     * @param function
     */
    public static void change(String s, Function<String, Integer> function) {
        //字符串类型的整数 转换为Integer类型的整数
        int in = function.apply(s);
        System.out.println(in);
    }

    /**
     * andThen(Function<T t,R r>);
     *
     * @param s
     * @param fun1
     * @param fun2
     */
    public static void change02(String s, Function<String, Integer> fun1, Function<Integer, String> fun2) {
        String ss = fun1.andThen(fun2).apply(s);
        System.out.println(ss);
    }

    public static int change03(String s, Function<String, String> fun1, Function<String, Integer> fun2, Function<Integer, Integer> fun3) {
        return fun1.andThen(fun2).andThen(fun3).apply(s);
    }

    public static void main(String[] args) {
        String s = "12345";
        String ss = "小小明,20";
        change(s, str -> Integer.parseInt(s));
        change02(s, str -> Integer.parseInt(s) + 10, i -> i + "");
        int num = change03(ss, str -> str.split(",")[1], str -> Integer.parseInt(str), i -> i + 100);
        System.out.println("num: " + num);
    }
}

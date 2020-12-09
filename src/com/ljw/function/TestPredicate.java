package com.ljw.function;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @Description: 函数式接口 Predicate
 *
 *          * predicate是一个接口，含有一个抽象方法test()，
 *          * 含有4个由default修饰的具体实现方法and()、or()、negate()、isEquals()
 *          *          and()对应java的连接符 &&；
 *          *          or()对应java的连接符 || ；
 *          *          negate()对应java的连接符 ! ；
 *          *          isEquals对应java的连接符 == ；
 * @Author: jianweil
 * @date: 2020/12/9 14:26
 */
public class TestPredicate {

    /**
     * boolean test(T t);  获取布尔类型结果
     *
     * @param s
     * @param pre
     * @return
     */
    public static boolean checkString(String s, Predicate<String> pre) {
        return pre.test(s);
    }

    /**
     * and();   代表&&的意思
     * <p>
     * 传递两个Predicate接口,比如：
     * 一个用于判断字符串的长度是否大于5
     * 一个用于判断字符串中是否包含a
     * 两个条件必须同时满足
     *
     * @param s
     * @param pre
     * @return
     */
    public static boolean checkStringAnd(String s, Predicate<String> pre1, Predicate<String> pre2) {
        return pre1.and(pre2).test(s);
    }

    /**
     * or();   代表||的意思
     *
     * @param s
     * @param pre1
     * @param pre2
     * @return
     */
    public static boolean checkStringOr(String s, Predicate<String> pre1, Predicate<String> pre2) {
        return pre1.or(pre2).test(s);
    }

    /**
     * negate取反
     *
     * @param s
     * @param pre
     * @return
     */
    public static boolean checkStringNegate(String s, Predicate<String> pre) {
        return pre.negate().test(s);
    }

    /**
     * 方法的参数传递一个包含人员信息的数组
     * 传递两个Predicate接口，用于对数组中信息进行过滤
     * 把满足条件的信息存到ArrayList集合中并返回
     *
     * @return
     */
    public static ArrayList<String> filter(String arr[], Predicate<String> pre1, Predicate<String> pre2) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr) {
            boolean b = pre1.and(pre2).test(s);
            if (b) {
                list.add(s);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        String s = "abcdef";
        //长度大于5
        boolean b = checkString(s, str -> str.length() > 5);
        System.out.println(b);

        //长度大于6并且包含"a"
        boolean and = checkStringAnd(s, str -> str.length() > 6, str -> str.contains("a"));
        System.out.println(and);

        //长度大于6 或者 包含"a"
        boolean or = checkStringOr(s, str -> str.length() > 6, str -> str.contains("a"));
        System.out.println(or);

        //取反
        boolean negate = checkStringNegate(s, str -> str.length() > 5);
        System.out.println(negate);

        //过滤操作
        String arr[] = {"张小三,男", "小红,女", "李四,男", "小小明,男"};
        ArrayList<String> list = filter(arr, str -> "男".equals(str.split(",")[1])
                , str -> str.split(",")[0].length() > 2);
        list.forEach(System.out::println);
    }
}

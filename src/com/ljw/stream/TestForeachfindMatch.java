package com.ljw.stream;

import com.ljw.stream.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/7 14:05
 *
 */
 class TestForeachfindMatch {
    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        //并行
        list.stream().parallel().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        //匹配任意
        Optional<Integer> findAny2 = list.stream().filter(x -> x > 6).findAny();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean isMatch = list.stream().anyMatch(x -> x >= 9);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("findAny2匹配任意一个值：" + findAny2.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于等于9的值：" + isMatch);
    }
}
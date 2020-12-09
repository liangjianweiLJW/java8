package com.ljw.stream;

import com.ljw.stream.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。
 * @Author: jianweil
 * @date: 2020/12/7 14:54
 */
public class TestFilter {
    public static void main(String[] args) {
        //筛选出Integer集合中大于7的元素，并打印出来
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(System.out::println);
        //筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集）
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).filter(x -> x.getAge()>=24)
                .map(Person::getName).collect(Collectors.toList());
        System.out.print("高于8000的员工姓名：" + fiterList);
    }
}

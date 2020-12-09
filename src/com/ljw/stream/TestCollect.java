package com.ljw.stream;

import com.ljw.stream.entity.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。
*                toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法
 * @Author: jianweil
 * @date: 2020/12/8 9:31
 */
public class TestCollect {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 25, "female", "Washington"));
        personList.add(new Person("Anni", 7800, 24, "female", "New York"));

        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("list:" + list);
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);


        // 求总数
        Long count = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("最高工资：" + max.get());
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);



        /**
         * 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
         *
         * 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
         **/

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);

        /**
         * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
         */
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> listJoining = Arrays.asList("A", "B", "C");
        String string = listJoining.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);

        /**
         * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持
         */
        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        personList.stream().map(person -> person.getSalary()).forEach(System.out::println);
        Integer sumCollect = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 7000)));
        System.out.println("员工扣税薪资总和：" + sumCollect);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());


        /**
         * sorted，中间操作。有两种排序：
         *
         * sorted()：自然排序，流中元素需实现Comparable接口
         *
         * sorted(Comparator com)：Comparator排序器自定义排序
         */

        // 按工资增序排序
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄自然排序（从小到大）
        List<String> newList3 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Comparator.comparing(p->p.getAge())))
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（从大到小）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资自然排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄自然排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);

        /**
         * 流也可以进行合并、去重、限制、跳过等操作。
         */
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newListDistinct = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collectLimit = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collectLimit2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newListDistinct);
        System.out.println("limit：" + collectLimit);
        System.out.println("skip：" + collectLimit2);


    }
}

package com.ljw.stream;

import com.ljw.stream.entity.Person;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
 *
 * 也许在有些文章里面有人告诉你identity是reduce的初始化值，可以随便指定，如下所示：
 *
 * Integer result2=intList.stream().reduce(100, Integer::sum);
 *         log.info("{}",result2);
 * 上面的例子，我们计算的值是106。
 *
 * 如果我们将stream改成parallelStream：
 *
 * Integer result3=intList.parallelStream().reduce(100, Integer::sum);
 *         log.info("{}",result3);
 * 得出的结果就是306。
 *
 * 为什么是306呢？因为在并行计算的时候，每个线程的初始累加值都是100，最后3个线程加出来的结果就是306。
 *
 * 并行计算和非并行计算的结果居然不一样，这肯定不是JDK的问题，我们再看一下JDK中对identity的说明：
 *
 * identity必须是accumulator函数的一个identity，也就是说必须满足：对于所有的t,都必须满足 accumulator.apply(identity, t) == t
 *
 * 所以这里我们传入100是不对的，因为sum（100+1）！= 1。
 *
 * 这里sum方法的identity只能是0。
 *
 * 如果我们用0作为identity,则stream和parallelStream计算出的结果是一样的。这就是identity的真正意图。
 *
 * @Author: jianweil
 * @date: 2020/12/7 14:54
 */
public class TestReduce {
    public static void main(String[] args) {
        //Supplier提供流
        /**1个参数:
         * 假设Stream中的元素a[0]/a[1]/a[2]…a[n - 1]，它表达的计算含义，使用Java代码来表述如下：
         * 也就是说，a[0]与a[1]进行二合运算，结果与a[2]做二合运算，一直到最后与a[n-1]做二合运算。
         *
         * T result = a[0];
         * for (int i = 1; i < n; i++) {
         * 	result = accumulator.apply(result, a[i]);
         * }
         * return result;
         */
        Supplier<Stream<Integer>> s = ()-> Stream.of(1, 2, 3, 4, 5, 6);
        /**
         * 求和，也可以写成Lambda语法：
         * Integer sum = s.reduce((a, b) -> a + b).get();
         */
        Integer sumBinaryOperator = s.get().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).get();
        Integer lambdaSum = s.get().reduce((a, b) -> a + b).get();

        System.out.println("1个参数sum："+sumBinaryOperator);
        System.out.println("1个参数lambdaSum："+lambdaSum);
        /**
         * 求最大值，也可以写成Lambda语法：
         * Integer max = s.reduce((a, b) -> a >= b ? a : b).get();
         */
        Integer maxBinaryOperator = s.get().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer >= integer2 ? integer : integer2;
            }
        }).get();
        Integer lambdaMax = s.get().reduce((a, b) -> a >= b ? a : b).get();
        System.out.println("1个参数max："+maxBinaryOperator);
        System.out.println("1个参数lambdaSum："+lambdaMax);

        System.out.println("---------------------------------------------------------------------");

        /**2个参数
         * identity 初始值 ，注意并行流每个初始值一样
         *
         * T result = identity;
         * for (int i = 0; i < n; i++) {
         * 	result = accumulator.apply(result, a[i]);
         * }
         * return result;
         */
        Supplier<Stream<String>> str = ()->Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
        /**
         * 以下结果将会是：　[value]testt1t2teeeeeaaaataaa
         * 也可以使用Lambda语法：
         * System.out.println(s.reduce("[value]", (s1, s2) -> s1.concat(s2)));
         */
        String s1 = str.get().reduce("[value]", new BinaryOperator<String>() {
            @Override
            public String apply(String s11, String s22) {
                return s11.concat(s22);
            }
        }).toString();
        System.out.println(s1);
        String s2 = str.get().reduce("[value]", (s11, s22) -> s11.concat(s22)).toString();
        System.out.println(s2);

        List<Integer> intList = Arrays.asList(1,2,3);
        Integer result2=intList.stream().reduce(100, Integer::sum);
        System.out.println(result2);
        Integer result3=intList.parallelStream().reduce(100, Integer::sum);
        System.out.println(result3);


        System.out.println("---------------------------------------------------------------------");
        /**
         *   //3个参数
         *   //第三个参数combiner主要是使用在并行计算的场景下；如果Stream是非并行时，第三个参数实际上是不生效的。
         *
         *   它表示的是，使用4与1、2、3中的所有元素按(s1,s2) -> s1 + s2(accumulator)的方式进行第一次计算，得到结果序列4+1, 4+2, 4+3，即5、6、7；
         *   然后将5、6、7按combiner即(s1, s2) -> s1 * s2的方式进行汇总，也就是5 * 6 * 7 = 210。
         *   使用函数表示就是：(4+1) * (4+2) * (4+3) = 210;
         *
         * identity: 一个初始化的值；这个初始化的值其类型是泛型U，与Reduce方法返回的类型一致；注意此时Stream中元素的类型是T，与U可以不一样也可以一样，
         *          这样的话操作空间就大了；不管Stream中存储的元素是什么类型，U都可以是任何类型，如U可以是一些基本数据类型的包装类型Integer、Long等；或者是String，
         *          又或者是一些集合类型ArrayList等；后面会说到这些用法。
         * accumulator: 其类型是BiFunction，输入是U与T两个类型的数据，而返回的是U类型；也就是说返回的类型与输入的第一个参数类型是一样的，而输入的第二个参数类型与Stream中元素类型是一样的。
         * combiner: 其类型是BinaryOperator，支持的是对U类型的对象进行操作
         *
         *
         * lambda语法：
         * System.out.println(Stream.of(1, 2, 3).parallel().reduce(4, (s1, s2) -> s1 + s2
         , (s1, s2) -> s1 * s2))
         */
        System.out.println("并行3参数");
        System.out.println(Stream.of(1, 2, 3).parallel().reduce(4, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                }
                , new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return integer * integer2;
                    }
                }));
        //reduce的这种写法可以与以下写法结果相等（但过程是不一样的，三个参数时会进行并行处理）
        System.out.println(Stream.of(1, 2, 3).map(n -> n + 4).reduce((s111, s222) -> s111 * s222));


        /**
         * 模拟Filter查找其中含有字母a的所有元素，打印结果将是aa ab ad
         * lambda语法：
         * s1.parallel().reduce(new ArrayList<String>(), (r, t) -> {if (predicate.test(t)) r.add(t);  return r; },
         (r1, r2) -> {System.out.println(r1==r2); return r2; }).stream().forEach(System.out::println);
         */
        System.out.println("s33");
        Stream<String> s33 = Stream.of("aa", "ab", "c", "ad");
        Predicate<String> predicate = t -> t.contains("a");
        s33.parallel().reduce(new ArrayList<String>(), new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, String s) {
                        if (predicate.test(s)) {
                            strings.add(s);
                        }

                        return strings;
                    }
                },
                new BinaryOperator<ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                        System.out.println(strings == strings2);
                        return strings;
                    }
                }).stream().forEach(System.out::println);

        System.out.println("s333");
        Stream<String> s333 = Stream.of("aa", "ab", "c", "ad");
        //模拟Filter查找其中含有字母a的所有元素，由于使用了r1.addAll(r2)，其打印结果将不会是预期的aa ab ad
        s333.parallel().reduce(new ArrayList<String>(), (r, t) -> {if (predicate.test(t)) r.add(t);  return r; },
                (r1, r2) -> {r1.addAll(r2); return r1; }).stream().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------");
        //---------------------------------------------------------------------


        //求Integer集合的元素之和、乘积和最大值。
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4,12);
        //List<Integer> list = Arrays.asList(0,0);
        // 求和方式1
        Optional<Integer> sumXY = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sumInteger = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);
        // 求最大值方式1
        Optional<Integer> maxXY = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer maxInteger = list.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sumXY.get() + "," + sumInteger.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求最大值：" + maxXY.get() + "," + maxInteger);

        //获取Integer集合中的最大值。
        // 自然排序
        Optional<Integer> maxIntegerMax = list.stream().max(Integer::compareTo);
        System.out.println("自然排序的最大值：" + maxIntegerMax.get());




        //求所有员工的工资之和和最高工资
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),(sum1, sum2) -> sum1 + sum2);
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);
        // 求工资之和
        Integer sumSalary4 = personList.stream().collect(Collectors.summingInt(Person::getSalary));


        // 求最高工资方式1：
        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);
        // 求最高工资方式2：
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1, max2) -> max1 > max2 ? max1 : max2);
        // 求最高工资方式3：
        Optional<Integer> maxSalary3 = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3+" "+sumSalary4);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2+ ","+maxSalary3.get());

    }
}

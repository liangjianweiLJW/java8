package com.ljw.function;

import java.util.function.Consumer;

/**
 * @Description: 函数式接口-消费接受
 *                  void accept(T t);
 * @Author: jianweil
 * @date: 2020/12/9 14:26
 */
public class TestConsumer {
    public static void method(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }

    public static void method02(String name, Consumer<String> con1, Consumer<String> con2) {
        //使用andThen方法，先把两个Consumer接口连接在一起，在消费数据
        //con1连接con2,先执行con1消费数据,在执行con2消费数据
        con1.andThen(con2).accept(name);
    }

    public static void printInfo(String[] arr, Consumer<String> con1, Consumer<String> con2) {
        for (String message : arr) {
            con1.andThen(con2).accept(message);
        }
    }

    public static void main(String[] args) {
        method("neoooo", (name) -> {
            //对传递的字符串进行消费
            //消费方式:直接输出字符串
            //System.out.println(name);

            //消费方式:把字符串进行反转输出
            String reverse = new StringBuffer(name).reverse().toString();
            System.out.println(reverse);
        });
        //调用method02方法，传递一个字符串，两个lambda表达式
        method02("nizhenniubi", (name) -> {
            System.out.println(name.toUpperCase());
        }, (name) -> {
            System.out.println(name.toLowerCase());
        });

        String arr[] = {"zhangsan,male", "lisi,male", "wanger,female"};
        printInfo(arr, (message) -> {
            String name = message.split(",")[0];
            System.out.println("姓名：" + name);
        }, (message) -> {
            String sex = message.split(",")[1];
            System.out.println("性别：" + sex + " ");
        });
    }
}

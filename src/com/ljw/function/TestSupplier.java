package com.ljw.function;

import java.util.function.Supplier;

/**
 * @Description: 函数式接口-用来做生产的 T get();
 * @Author: jianweil
 * @date: 2020/12/9 14:26
 */
public class TestSupplier {


    public static void main(String[] args) {

        String s = getString(() -> {
            return "hello world";
        });
        System.out.println(s);

        //优化lambda
        String s2 = getString(() -> "hello java");
        System.out.println(s2);

        int[] arr = {34, 45, 23, 1, 345, -54};
        int maxVal = getMax(() -> {
            int max = arr[0];
            for (int i : arr) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        });
        System.out.println(maxVal);
    }

    /**
     * 会返回一个String
     *
     * @param sup
     * @return
     */
    public static String getString(Supplier<String> sup) {
        return sup.get();
    }

    /**
     * 返回一个最大值
     *
     * @param sup
     * @return
     */
    public static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}

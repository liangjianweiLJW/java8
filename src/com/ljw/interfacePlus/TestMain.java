package com.ljw.interfacePlus;

import com.ljw.lambda.InterfaceNoParamNoResult;

import java.util.Date;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 20:20
 */
public class TestMain {
    public static void main(String[] args) {
        Date date = InterfacePlus.createDate();
        System.out.println(date);

        //自行实现后可直接调用default方法
        //子类实例化
        InterfacePlusImpl interfacePlusImpl = new InterfacePlusImpl();
        interfacePlusImpl.sayHello();
        interfacePlusImpl.run();


        //Lambda实例化
        InterfacePlus interfacePlus = System.out::println;
        interfacePlus.sayHello();
        System.out.println("---------");
        interfacePlus.run();

        System.out.println("---------");
        InterfacePlus param = () -> System.out.println("通过Lambda表达式实现run()") ;
        param.run();
        System.out.println("---------");
    }
}

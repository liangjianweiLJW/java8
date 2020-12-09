package com.ljw.lambda;

/**
 * @Description: 包含属性为函数式接口的类
 *              Lambda表达式和函数式接口结合
 * @Author: jianweil
 * @date: 2020/12/8 15:20
 */
public class TestLambda {
    /**
     * 无参数无返回
     */
    //匿名内部类
    InterfaceNoParamNoResult param1 = new InterfaceNoParamNoResult() {
        @Override
        public void run() {
            System.out.println("通过匿名内部类实现run()");
        }
    };
    //Lambda表达式 空括号表示无参
    InterfaceNoParamNoResult param = () -> System.out.println("通过Lambda表达式实现run()") ;

    /**
     * 有参数无返回
     */
    InterfaceWithParamNoResult interfaceWithParamNoResult = new InterfaceWithParamNoResult() {
        @Override
        public void run(String s) {
            System.out.println("通过" + s + "实现run(String)");
        }
    };
    InterfaceWithParamNoResult interfaceWithParamNoResult2 = (String s) -> System.out.println("通过" + s + "实现run(String)");


    /**
     * 无参数有返回
     */
    InterfaceNoParamWithResult interfaceNoParamWithResult = new InterfaceNoParamWithResult() {
        @Override
        public String run() {
            return "Hello World!";
        }
    };
    InterfaceNoParamWithResult interfaceNoParamWithResult2 = () -> "Hello Lambda!";

    /**
     * 有参数有返回
     */
    InterfaceWithParamWithResult interfaceWithParamWithResult = new InterfaceWithParamWithResult() {
        @Override
        public String run(Integer integer) {
            return String.valueOf(integer);
        }
    };
    InterfaceWithParamWithResult interfaceWithParamWithResult2 = (Integer integer) -> String.valueOf(integer);



    public static void main(String[] args) {
        System.out.println("---------");
        new TestLambda().param.run();
        new TestLambda().param1.run();
        System.out.println("---------");
        new TestLambda().interfaceWithParamNoResult.run("匿名类");
        new TestLambda().interfaceWithParamNoResult2.run("lambda");
        System.out.println("---------");
        System.out.println(new TestLambda().interfaceNoParamWithResult.run());
        System.out.println(new TestLambda().interfaceNoParamWithResult2.run());
        System.out.println("---------");
        System.out.println(new TestLambda().interfaceWithParamWithResult.run(1));
        System.out.println(new TestLambda().interfaceWithParamWithResult2.run(2));
    }
}

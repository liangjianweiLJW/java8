package com.ljw.methodReference;

/**
 * @Description: 方法引用
 * @Author: jianweil
 * @date: 2020/12/8 16:49
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 引用静态方法####
         */
        StaticMethod staticMethod = (a, b) -> Math.min(a, b);
        int min = staticMethod.min(2, 3);
        System.out.println(min);

        /**
         *   StaticMethod函数式接口定义的是声明和规范（入参和返回值): int min(int a, int b);
         *   真正逻辑是 Math::max等具体实现
         *   就是把StaticMethod函数min(88, 3)用 Math::max真正逻辑运行
         */

        StaticMethod staticMethod1 = Math::max;
        int max1 = staticMethod1.min(88, 3);
        System.out.println(max1);

        StaticMethod staticMethod2 = Math::min;
        int min1 = staticMethod2.min(88, 3);
        System.out.println(min1);

        /**
         * 引用某个对象的实例方法
         */
        Emp emp = new Emp(1, "eName");
        //普通Lambda表达式
        InstanceMethodOfParticularObject instanceMethodOfParticularObject = name -> emp.setEname(name);
        instanceMethodOfParticularObject.setEmpName("haha");
        System.out.println(emp.getEname());

        //方法引入--引用某个对象的实例方法
        InstanceMethodOfParticularObject instanceMethodOfParticularObject1 = emp::setEname;
        instanceMethodOfParticularObject1.setEmpName("xixi");
        System.out.println(emp.getEname());

        /**
         * 引用某个类型的任意对象的实例方法
         */
        InstanceMethodOfArbitraryObject instanceMethodOfArbitraryObject = (a, b) -> a.equals(b);
        InstanceMethodOfArbitraryObject instanceMethodOfArbitraryObject1 = String::equals;

        System.out.println(instanceMethodOfArbitraryObject1.equale("a", "b"));
        System.out.println(instanceMethodOfArbitraryObject.equale("a", "a"));

        /**
         * 引用构造方法
         */
        //无参构造
        ConstructorWithNoField constructorWithNoField = ()-> new Emp();
        ConstructorWithNoField constructorWithNoField1 = Emp::new;
        Emp a = constructorWithNoField.getNewEmp();
        System.out.println(a.toString());
        Emp a1 = constructorWithNoField1.getNewEmp();
        System.out.println(a1.toString());

        //全参构造
        ConstructorWithFullFields constructorWithFullFields = ((empno, ename) -> new Emp(empno, ename));
        ConstructorWithFullFields constructorWithFullFields1 = Emp::new;
        Emp newEmp = constructorWithFullFields.getNewEmp(1, "11111");
        System.out.println(newEmp.toString());
        Emp newEmp1 = constructorWithFullFields1.getNewEmp(2, "333");
        System.out.println(newEmp1.toString());

    }
}

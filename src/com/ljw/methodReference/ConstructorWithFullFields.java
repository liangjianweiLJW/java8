package com.ljw.methodReference;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 17:01
 */
@FunctionalInterface
public interface ConstructorWithFullFields {
    Emp getNewEmp(int empno,String ename);
}

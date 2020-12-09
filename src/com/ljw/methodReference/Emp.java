package com.ljw.methodReference;

/**
 * @Description: todo
 * @Author: jianweil
 * @date: 2020/12/8 15:48
 */
public class Emp {
    private int empno;
    private String ename;

    public Emp() {
    }

    public Emp(int empno, String ename) {
        this.empno = empno;
        this.ename = ename;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public static void printEmp(Emp emp){
        System.out.println("empno:"+emp.getEmpno()+"\nename:"+emp.getEname());
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                '}';
    }
}

package com.atguigu.spring6.iocxml.ditest;

import java.util.Arrays;

//员工类
public class Emp {

    //对象类型属性：员工属于某个部门
    private Dept dept;
    //员工名称
    private String ename;
    //员工年龄
    private Integer age;

    //爱好
    private String[] loves;

    public void work() {
        System.out.println(ename + "emp work....." + age);
        dept.info();
        if (loves != null && loves.length > 0) {
            System.out.println(Arrays.toString(loves));
        }
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                ", ename='" + ename + '\'' +
                ", age=" + age +
                ", loves=" + Arrays.toString(loves) +
                '}';
    }

    public String[] getLoves() {
        return loves;
    }

    public void setLoves(String[] loves) {
        this.loves = loves;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

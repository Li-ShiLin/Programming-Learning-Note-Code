package com.atguigu.spring6.iocxml.ditest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringDiTestDemo {

    @Test
    public void testDiOuterBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-ditest.xml");
        //员工对象
        Emp emp = context.getBean("emp", Emp.class);
        emp.work();
        //程序输出：
        // lucyemp work.....50
        // 部门名称：安保部
    }

    // 为对象类型属性赋值：内部bean
    @Test
    public void testDiInnerBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-ditest.xml");
        //员工对象
        Emp emp = context.getBean("emp2", Emp.class);
        emp.work();
        //程序输出：
        // maryemp work.....20
        // 部门名称：财务部
    }

    // 级联属性赋值
    @Test
    public void testCascadeBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-ditest.xml");
        //员工对象
        Emp emp = context.getBean("emp3", Emp.class);
        emp.work();
        //程序输出：
        // tomemp work.....30
        // 部门名称：测试部
    }

    // 为数组类型属性赋值
    @Test
    public void testArrayDi() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diarray.xml");
        //员工对象
        Emp emp = context.getBean("emp", Emp.class);
        emp.work();
        //程序输出：
        // lucyemp work.....20
        // 部门名称：技术部
        // [吃饭, 睡觉, 敲代码]
    }


    // 为List集合类型属性赋值
    @Test
    public void testListDi() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-dilist.xml");
        //员工对象
        Dept dept = context.getBean("dept", Dept.class);
        System.out.println("部门名称：" + dept.getDname());
        List<Emp> empList = dept.getEmpList();
        if (empList != null && empList.size() > 0) {
            for (Emp emp : empList) {
                System.out.println(emp);
            }
        }
        //程序输出：
        // 部门名称：技术部
        // Emp{dept=null, ename='lucy', age=20, loves=null}
        // Emp{dept=null, ename='mary', age=30, loves=null}
    }

}

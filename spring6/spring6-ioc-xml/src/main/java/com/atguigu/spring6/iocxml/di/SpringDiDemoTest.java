package com.atguigu.spring6.iocxml.di;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringDiDemoTest {

    @Test
    public void testDIBySet() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-di.xml");
        Student studentOne = ac.getBean("studentOne", Student.class);
        System.out.println(studentOne);
        //程序输出：Student{id=1001, name='张三', age=23, sex='男'}
    }

    @Test
    public void testDIByConstructor(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-di.xml");
        Student studentOne = ac.getBean("studentTwo", Student.class);
        System.out.println(studentOne);
        // 程序输出：Student{id=1002, name='李四', age=33, sex='女'}
    }
}

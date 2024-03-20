package com.atguigu.spring6.iocxml.factorybean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactoryBeanTest {
    @Test
    public void TestFactoryBeanTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factorybean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
        //程序输出：User{name='张三'}
    }
}

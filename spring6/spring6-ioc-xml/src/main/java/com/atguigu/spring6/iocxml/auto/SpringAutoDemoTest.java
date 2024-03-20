package com.atguigu.spring6.iocxml.auto;

import com.atguigu.spring6.iocxml.auto.controller.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAutoDemoTest {

    // 根据类型自动装配
    @Test
    public void testAutoWireXMLByType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-auto.xml");
        UserController controller = context.getBean("userController", UserController.class);
        controller.addUser();
        //程序输出：
        // controller方法执行了...
        // userService方法执行了...
        // userDao方法执行了...
    }

    // 根据名称自动装配
    @Test
    public void testAutoWireXMLByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-auto.xml");
        UserController controller = context.getBean("userController", UserController.class);
        controller.addUser();
        //程序输出：
        // controller方法执行了...
        // userService方法执行了...
        // userDao方法执行了...
    }
}

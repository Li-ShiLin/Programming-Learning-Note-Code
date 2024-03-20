package com.atguigu.spring6.autowired;

import com.atguigu.spring6.autowired.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestUserController {

    @Test
    public void testAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserController controller = context.getBean(UserController.class);
        controller.add();
    }
}

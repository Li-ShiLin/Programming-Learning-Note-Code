package com.itheima.controller;


//import com.itheima.framework.context.ApplicationContext;
//import com.itheima.framework.context.support.ClassPathXmlApplicationContext;


//import com.itheima.service.UserService;
//import org.springframework.context.ApplicationContext;

import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class UserController {
    public static void main(String[] args) throws Exception {
        //1,创建spring的容器对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        //2,从容器对象中获取userService对象
        UserService userService = applicationContext.getBean("userService", UserService.class);
        //3,调用userService方法进行业务逻辑处理
        userService.add();
        /*
            userService被创建了
            userDao被创建了
            UserService ...
            UserDao ...null==null
         */
    }
}

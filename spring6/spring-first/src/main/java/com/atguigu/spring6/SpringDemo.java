package com.atguigu.spring6;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {
    @Test
    public void testSpring() {
        //加载spring配置文件，对象创建
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //获取创建的对象
        User user = (User) context.getBean("user");
        System.out.println(user);

        //使用对象调用方法进行测试
        user.add();
//程序执行结果：
//        无参数构造执行了..
//        com.atguigu.spring6.User@512d92b
//        add.....
    }
}

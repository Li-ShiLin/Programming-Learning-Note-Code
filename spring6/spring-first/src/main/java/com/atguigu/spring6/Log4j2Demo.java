package com.atguigu.spring6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Log4j2Demo {

    //创建Logger对象
    private Logger logger = LoggerFactory.getLogger(SpringDemo.class);

    @Test
    public void testUserObject() {
        //加载spring配置文件，对象创建
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //获取创建的对象
        User user = (User) context.getBean("user");
        logger.info("user: " + user);
        //使用对象调用方法进行测试
        user.add();

        //手动写日志
        logger.info("### 执行调用成功了..");
//程序输出：
//        2024-03-16 14:04:57 232 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@45cff11c
//        2024-03-16 14:04:57 326 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 1 bean definitions from class path resource [bean.xml]
//        2024-03-16 14:04:57 344 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
//        无参数构造执行了..
//        2024-03-16 14:04:57 365 [main] INFO com.atguigu.spring6.SpringDemo - user: com.atguigu.spring6.User@4b770e40
//        add.....
//        2024-03-16 14:04:57 365 [main] INFO com.atguigu.spring6.SpringDemo - ### 执行调用成功了..
    }
}

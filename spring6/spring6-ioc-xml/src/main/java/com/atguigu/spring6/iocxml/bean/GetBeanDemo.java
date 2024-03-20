package com.atguigu.spring6.iocxml.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class GetBeanDemo {

    public static void main(String[] args) {
        getInterfaceBean();
    }

    // 根据id获取bean
    private static void getBeanById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User userById = (User) context.getBean("user");
        System.out.println("根据id获取bean: " + userById);
        // 程序输出：根据id获取bean: com.atguigu.spring6.iocxml.bean.User@3f07b12c
    }


    // 根据类型获取bean
    private static void getBeanByType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User userByType = context.getBean(User.class);
        System.out.println("根据类型获取bean: " + userByType);
        // 程序输出：根据类型获取bean: com.atguigu.spring6.iocxml.bean.User@758a34ce
    }


    //根据id和类型获取bean
    private static void getBeanByIdAndType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User userByIdAndType = context.getBean("user", User.class);
        System.out.println("根据id和类型获取bean: " + userByIdAndType);
        // 根据id和类型获取bean: com.atguigu.spring6.iocxml.bean.User@13f17eb4
    }


    // 根据类型获取接口对应实现类bean
    private static void getInterfaceBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println(userDao);
        userDao.run();
        // 1.配置一个实现类Bean时，可以正确执行
        // <bean id="userDaoImpl" class="com.atguigu.spring6.iocxml.bean.UserDaoImpl"></bean>
        // 程序输出：
        // com.atguigu.spring6.iocxml.bean.UserDaoImpl@784b990c
        // run.....

        // 2.配置两个实现类Bean时，执行报错：
        // <bean id="userDaoImpl" class="com.atguigu.spring6.iocxml.bean.UserDaoImpl"></bean>
        // <bean id="personDaoImpl" class="com.atguigu.spring6.iocxml.bean.PersonDaoImpl"></bean>
        // Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException:
        // No qualifying bean of type 'com.atguigu.spring6.iocxml.bean.UserDao' available:
        // expected single matching bean but found 2: userDaoImpl,personDaoImpl
    }

}

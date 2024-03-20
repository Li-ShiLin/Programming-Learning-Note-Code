package com.atguigu.spring6.iocxml.life;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringBeanLifeCycleDemo {
    @Test
    public void testSpringBeanLifeCycle() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life.xml");
        User user = context.getBean("user", User.class);
        System.out.println("6 bean对象创建完成了，可以使用了");
        System.out.println(user);
        context.close(); //销毁
        // 程序输出：
        // 2024-03-17 15:05:08 057 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
        // 1. bean对象创建，调用无参数构造
        // 2. 给bean对象设置属性值
        // 3. bean后置处理器，初始化之前执行
        // user::com.atguigu.spring6.iocxml.life.User@50eca7c6
        // 4. bean对象初始化，调用指定的初始化的方法
        // 5. bean后置处理器，初始化之后执行
        // user::com.atguigu.spring6.iocxml.life.User@50eca7c6
        // 6 bean对象创建完成了，可以使用了
        // com.atguigu.spring6.iocxml.life.User@50eca7c6
        // 2024-03-17 15:05:08 095 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@265adfad, started on Sun Mar 17 15:05:07 CST 2024
        // 7. bean对象销毁，调用指定的销毁的方法
    }
}

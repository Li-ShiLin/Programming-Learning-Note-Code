package com.atguigu.spring6.iocxml.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringScopeDemo {

    // 单实例
    // <bean id="orders" class="com.atguigu.spring6.iocxml.scope.Orders" scope="singleton"></bean>
    @Test
    public void testScopeSingleton() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println(orders);
        Orders orders1 = context.getBean("orders", Orders.class);
        System.out.println(orders1);
        //输出：
        // com.atguigu.spring6.iocxml.scope.Orders@73ff4fae
        // com.atguigu.spring6.iocxml.scope.Orders@73ff4fae
    }

    // 多实例
    // <bean id="orders" class="com.atguigu.spring6.iocxml.scope.Orders" scope="prototype"></bean>
    @Test
    public void testScopePrototype() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println(orders);
        Orders orders1 = context.getBean("orders", Orders.class);
        System.out.println(orders1);
        //输出：
        // com.atguigu.spring6.iocxml.scope.Orders@36676c1a
        // com.atguigu.spring6.iocxml.scope.Orders@5b408dc3
    }
}

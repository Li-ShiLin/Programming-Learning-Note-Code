package com.atguigu.spring6.aop.annoaop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringAopTestDemo {
    @Test
    public void beforeMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(2, 3);
        //程序输出：
        // 环绕通知==目标方法之前执行
        // Logger-->前置通知，方法名称：add，参数：[2, 3]
        // 方法内部 result = 5
        // Logger-->返回通知，方法名称：add，返回结果：5
        // Logger-->后置通知，方法名称：add
        // 环绕通知==目标方法返回值之后
        // 环绕通知==目标方法执行完毕执行
    }
}

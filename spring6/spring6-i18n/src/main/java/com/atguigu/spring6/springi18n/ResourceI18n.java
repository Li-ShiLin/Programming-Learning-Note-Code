package com.atguigu.spring6.springi18n;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Date;
import java.util.Locale;
public class ResourceI18n {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");

        Object[] objs = new Object[]{"atguigu",new Date().toString()};
        String value = context.getMessage("www.atguigu.com", objs, Locale.UK);
        System.out.println(value);
        // 输出： welcome atguigu,时间:Wed Mar 20 00:34:15 CST 2024
    }
}

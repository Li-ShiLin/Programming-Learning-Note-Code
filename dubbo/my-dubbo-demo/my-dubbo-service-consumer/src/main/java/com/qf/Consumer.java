package com.qf;

import com.qf.api.SiteService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 下⾯这⼀整个过程。都是在执⾏远程过程调⽤—— rpc remote produce call 服务框架
        // 获取⼀个代理，代理服务提供者内提供的bean
        // 从ioc容器中获得SiteService服务提供者的代理对象
        SiteService siteService = (SiteService) context.getBean("siteService");
        // 获取远程服务代理
        //调⽤代理对象的getName⽅法。通过代理对象调到服务提供者内的bean
        String qf = siteService.getName("qf");
        System.out.println(qf);

    }
}

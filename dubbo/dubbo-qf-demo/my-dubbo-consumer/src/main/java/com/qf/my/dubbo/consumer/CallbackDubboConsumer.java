package com.qf.my.dubbo.consumer;

import com.qf.site.SiteService;
import com.qf.site.SiteServiceListenerImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * 
 */
@EnableAutoConfiguration
public class CallbackDubboConsumer {

    @Reference(version = "callback")
    private SiteService siteService;


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CallbackDubboConsumer.class);
        SiteService siteService = (SiteService) context.getBean(SiteService.class);
        // key 目的是指明实现类在服务提供者和消费者之间保证是同一个
        System.out.println(siteService.siteName("q-face", "c1", new SiteServiceListenerImpl()));
        System.out.println(siteService.siteName("q-face", "c2", new SiteServiceListenerImpl()));
        System.out.println(siteService.siteName("q-face", "c3", new SiteServiceListenerImpl()));

    }
}

package com.qf.my.dubbo.consumer;

import com.qf.site.SiteService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * 
 */
@EnableAutoConfiguration
public class StubDubboConsumer {

    @Reference(version = "timeout", timeout = 1000, stub = "true")
    private SiteService siteService;


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(StubDubboConsumer.class);
        SiteService siteService = (SiteService) context.getBean(SiteService.class);
        String name = siteService.siteName("q-face");
        System.out.println(name);

    }

}

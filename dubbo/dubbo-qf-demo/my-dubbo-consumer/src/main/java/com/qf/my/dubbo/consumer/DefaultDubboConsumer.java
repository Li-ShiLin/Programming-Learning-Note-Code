package com.qf.my.dubbo.consumer;

import com.qf.site.SiteService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
@EnableAutoConfiguration
public class DefaultDubboConsumer {

    //@Reference(id = "siteService",version = "default",url = "dubbo://127.0.0.1:20881/com.qf.site.SiteService:default")
    @Reference(version = "default", loadbalance = "roundrobin")
    private SiteService siteService;


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DefaultDubboConsumer.class);
//        SiteService siteService = (SiteService) context.getBean("siteService");
        SiteService siteService = (SiteService) context.getBean(SiteService.class);
        String name = siteService.siteName("q-face");
        System.out.println(name);

    }
}

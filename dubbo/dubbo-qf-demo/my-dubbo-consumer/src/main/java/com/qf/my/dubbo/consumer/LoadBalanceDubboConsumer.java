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
public class LoadBalanceDubboConsumer {

    @Reference(version = "default", loadbalance = "consistenthash")
    private SiteService siteService;

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DefaultDubboConsumer.class);
        SiteService siteService = (SiteService) context.getBean(SiteService.class);
        for (int i = 0; i < 100; i++) {
            String name = siteService.siteName("q-face"+i%6);
            System.out.println(name);
        }

        /* roundrobin
        for (int i = 0; i < 100; i++) {
            String name = siteService.siteName("q-face");
            System.out.println(name);
        }*/

    }
}

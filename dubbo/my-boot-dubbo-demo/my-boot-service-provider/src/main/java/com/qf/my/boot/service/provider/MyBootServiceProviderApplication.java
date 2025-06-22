package com.qf.my.boot.service.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@SpringBootApplication
public class MyBootServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBootServiceProviderApplication.class, args);
    }

}

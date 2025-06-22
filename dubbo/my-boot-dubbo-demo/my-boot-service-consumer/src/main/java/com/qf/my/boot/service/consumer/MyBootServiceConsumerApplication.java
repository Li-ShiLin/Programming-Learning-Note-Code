package com.qf.my.boot.service.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@SpringBootApplication
public class MyBootServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBootServiceConsumerApplication.class, args);
    }
}

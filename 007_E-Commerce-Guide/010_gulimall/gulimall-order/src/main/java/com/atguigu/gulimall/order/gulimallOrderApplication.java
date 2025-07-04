package com.atguigu.gulimall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimall.order.dao")
@SpringBootApplication
public class gulimallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(gulimallOrderApplication.class, args);
    }

}

package com.action.shardingsphere.ss03databaseverticalsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss03databaseverticalsharding.mapper")
public class Ss03DatabaseVerticalShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss03DatabaseVerticalShardingApplication.class, args);
    }

}

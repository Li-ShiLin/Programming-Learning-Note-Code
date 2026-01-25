package com.action.shardingsphere.ss01tablehorizontalsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss01tablehorizontalsharding.mapper")
public class Ss01TableHorizontalShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss01TableHorizontalShardingApplication.class, args);
    }

}

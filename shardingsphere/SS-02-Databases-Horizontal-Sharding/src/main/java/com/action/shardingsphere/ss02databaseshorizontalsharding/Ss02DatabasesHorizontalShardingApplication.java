package com.action.shardingsphere.ss02databaseshorizontalsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss02databaseshorizontalsharding.mapper")
public class Ss02DatabasesHorizontalShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss02DatabasesHorizontalShardingApplication.class, args);
    }

}

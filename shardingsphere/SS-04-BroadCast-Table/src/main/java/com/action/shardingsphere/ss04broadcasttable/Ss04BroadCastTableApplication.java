package com.action.shardingsphere.ss04broadcasttable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss04broadcasttable.mapper")
public class Ss04BroadCastTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss04BroadCastTableApplication.class, args);
    }

}

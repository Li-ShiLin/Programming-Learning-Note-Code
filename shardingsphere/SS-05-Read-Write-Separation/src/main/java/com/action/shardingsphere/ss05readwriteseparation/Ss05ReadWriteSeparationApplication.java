package com.action.shardingsphere.ss05readwriteseparation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss05readwriteseparation.mapper")
public class Ss05ReadWriteSeparationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss05ReadWriteSeparationApplication.class, args);
    }

}

package com.hmdp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/1/15 16:01
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6379")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }

   /* @Bean
    public RedissonClient redissonClient2(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6381")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }

    @Bean
    public RedissonClient redissonClient3(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6382")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }*/
}

package com.atguigu.gulimall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 1、开启服务注册发现,把网关服务注册到nacos配置中心
 *  (配置nacos的注册中心地址)
 * 2、编写网关配置文件
 */

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})   : 排除掉数据库有关的配置
public class GulimallGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallGatewayApplication.class, args);
	}

}

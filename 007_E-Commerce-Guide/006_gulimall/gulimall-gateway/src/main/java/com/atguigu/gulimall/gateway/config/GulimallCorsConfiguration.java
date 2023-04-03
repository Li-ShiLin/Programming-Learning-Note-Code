package com.atguigu.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/4/1 19:13
 */
@Configuration
public class GulimallCorsConfiguration {


    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1、配置跨域
        corsConfiguration.addAllowedHeader("*");    // 允许所有请求头跨域
        corsConfiguration.addAllowedMethod("*");    // 允许所有请求方式（get\post\delete\...）跨域
        corsConfiguration.addAllowedOrigin("*");    // 允许所有请求来源跨域
        corsConfiguration.setAllowCredentials(true); // 允许携带Cookie跨域

        // 任意路径都使用上述配置进行跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}

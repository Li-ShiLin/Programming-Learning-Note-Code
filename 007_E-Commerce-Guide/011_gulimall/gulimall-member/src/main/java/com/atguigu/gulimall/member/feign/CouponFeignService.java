package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个声明式的远程调用
 * 告诉springcloud 此接口是一个远程客户端，他要调用远程服务
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    // 声明接口的每一个方法都是调用哪个远程服务的那个请求
    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();

}

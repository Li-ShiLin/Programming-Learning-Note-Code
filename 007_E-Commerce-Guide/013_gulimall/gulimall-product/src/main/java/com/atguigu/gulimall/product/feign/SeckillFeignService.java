package com.atguigu.gulimall.product.feign;

import com.atguigu.common.utils.R;
import com.atguigu.gulimall.product.feign.fallback.SeckillFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//
//@FeignClient(value = "gulimall-seckill")
//public interface SeckillFeignService {
//
//    @GetMapping("/sku/seckill/{skuId}")
//    R getSkuSeckillInfo(@PathVariable("skuId") Long skuId);
//}


// 给feign远程调用加上sentinel的熔断保护
// feign远程调用的默认回调，如果远程调用出现问题，就会调用SeckillFeignServiceFallBack的默认回调接口进行返回
@FeignClient(value = "gulimall-seckill", fallback = SeckillFeignServiceFallBack.class)
public interface SeckillFeignService {
    @GetMapping("/sku/seckill/{skuId}")
    R getSkuSeckillInfo(@PathVariable("skuId") Long skuId);
}


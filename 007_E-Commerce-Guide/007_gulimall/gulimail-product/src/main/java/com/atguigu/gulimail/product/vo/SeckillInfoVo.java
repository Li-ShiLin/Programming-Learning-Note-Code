package com.atguigu.gulimail.product.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeckillInfoVo {

    /**
     * 活动id
     */
    private Long promotionId;

    /**
     * 活动场次id
     */
    private Long promotionSessionId;
    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品秒杀随机码
     */
    private String randomCode;
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    /**
     * 秒杀总量
     */
    private Integer seckillCount;
    /**
     * 每人限购数量
     */
    private Integer seckillLimit;
    /**
     * 排序
     */
    private Integer seckillSort;
    //当前商品秒杀的开始时间
    private Long startTime;

    //当前商品秒杀的结束时间
    private Long endTime;

}

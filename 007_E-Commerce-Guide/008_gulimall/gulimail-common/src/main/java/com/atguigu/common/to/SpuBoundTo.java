package com.atguigu.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/5/7 18:41
 */
@Data
public class SpuBoundTo {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}

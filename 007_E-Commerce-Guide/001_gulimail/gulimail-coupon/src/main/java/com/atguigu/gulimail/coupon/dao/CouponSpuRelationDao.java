package com.atguigu.gulimail.coupon.dao;

import com.atguigu.gulimail.coupon.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author Li-ShiLin
 * @email sunlightcs@gmail.com
 * @date 2023-03-05 21:00:25
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}

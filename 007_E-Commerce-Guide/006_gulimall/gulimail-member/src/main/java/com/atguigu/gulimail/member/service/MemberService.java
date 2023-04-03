package com.atguigu.gulimail.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimail.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author Li-ShiLin
 * @email sunlightcs@gmail.com
 * @date 2023-03-06 00:07:29
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


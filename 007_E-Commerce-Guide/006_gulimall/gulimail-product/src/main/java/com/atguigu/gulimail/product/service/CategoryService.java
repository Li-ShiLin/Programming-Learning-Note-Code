package com.atguigu.gulimail.product.service;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Li-ShiLin
 * @email sunlightcs@gmail.com
 * @date 2023-03-05 00:44:09
 */
public interface CategoryService extends IService<CategoryEntity> {

//    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();
}


package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.mapper.ProductMapper;
import com.atguigu.mybatisplus.pojo.Product;
import com.atguigu.mybatisplus.service.ProductService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
@DS("slave_1")   //指定所操作的数据源
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}

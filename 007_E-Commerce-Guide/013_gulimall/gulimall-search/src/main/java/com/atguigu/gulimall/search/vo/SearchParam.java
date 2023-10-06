package com.atguigu.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * 封装页面所有可能传递过来的查询条件
 * catalog3Id=225&keyword=小米&sort=saleCount_asc&hasStock=0/1&brandId=1&brandId=2
 * &attrs=1_5寸:8寸&attrs=2_16G:8G
 */
@Data
public class SearchParam {
    /**
     * 页面传递过来的全文匹配关键字  v
     */
    private String keyword;
    /**
     * 三级分类id   v
     */
    private Long catalog3Id;
    /**
     * 排序条件 v
     * 排序条件的几种可能：
     * sort=saleCount_asc/desc（按销量）、
     * sort=skuPrice_asc/desc（按价格）、
     * sort=hotScore_asc/desc（按热度分）
     */
    private String sort;

    /**
     * 是否只显示有货 0（无库存）1（有库存） hasStock=0/1
     */
    private Integer hasStock;
    /**
     * 价格区间查询  skuPrice=1_500/_500/500_
     */
    private String skuPrice;

    /**
     * 按照品牌进行查询，可以多选  brandId=1
     */
    private List<Long> brandId;
    /**
     * 按照属性进行筛选 可以多选  attrs=2_5存:6寸
     */
    private List<String> attrs;
    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 原生的所有查询条件
     */
    private String _queryString;
}

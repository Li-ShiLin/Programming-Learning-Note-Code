package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.dao.AttrGroupDao;
import com.atguigu.gulimall.product.dao.SkuSaleAttrValueDao;
import com.atguigu.gulimall.product.vo.SkuItemSaleAttrVo;
import com.atguigu.gulimall.product.vo.SpuItemAttrGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AttrGroupDaoTest {

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Test
    public void test() {
        List<SpuItemAttrGroupVo> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(13L, 225L);
        System.out.println(attrGroupWithAttrsBySpuId);
// [SpuItemAttrGroupVo(groupName=主体, attrs=[Attr(attrId=null, attrName=入网型号, attrValue=A2217), Attr(attrId=null, attrName=上市年份, attrValue=2018)]), SpuItemAttrGroupVo(groupName=基本信息, attrs=[Attr(attrId=null, attrName=机身长度（mm）, attrValue=158.3), Attr(attrId=null, attrName=机身材质工艺, attrValue=以官网信息为准)]), SpuItemAttrGroupVo(groupName=主芯片, attrs=[Attr(attrId=null, attrName=CPU品牌, attrValue=以官网信息为准), Attr(attrId=null, attrName=CPU型号, attrValue=A13仿生)])]

    }


    @Test
    public void test02() {
        List<SkuItemSaleAttrVo> saleAttrsBySpuId = skuSaleAttrValueDao.getSaleAttrsBySpuId(13L);
        System.out.println(saleAttrsBySpuId);
// [SkuItemSaleAttrVo(attrId=9, attrName=颜色, attrValues=[AttrValueWithSkuIdVo(attrValue=白色, skuIds=12,13,14), AttrValueWithSkuIdVo(attrValue=紫色, skuIds=24,25,26), AttrValueWithSkuIdVo(attrValue=红色, skuIds=21,22,23), AttrValueWithSkuIdVo(attrValue=绿色, skuIds=15,16,17), AttrValueWithSkuIdVo(attrValue=黄色, skuIds=18,19,20), AttrValueWithSkuIdVo(attrValue=黑色, skuIds=9,10,11)]), SkuItemSaleAttrVo(attrId=12, attrName=版本, attrValues=[AttrValueWithSkuIdVo(attrValue=128GB , skuIds=9,12,15,18,21,24), AttrValueWithSkuIdVo(attrValue=256GB, skuIds=10,13,16,19,22,25), AttrValueWithSkuIdVo(attrValue=64GB, skuIds=11,14,17,20,23,26)])]
    }
}

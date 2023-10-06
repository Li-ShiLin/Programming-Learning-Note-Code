package com.atguigu.mybatispuls;


import com.atguigu.mybatispuls.mapper.ProductMapper;
import com.atguigu.mybatispuls.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusOptimisticTest {

    @Autowired
    private ProductMapper productMapper;

    // 1.模拟修改冲突 (没采用乐观锁之前)
    @Test
    public void testBefore() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        //小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);

        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productLaoban.getPrice());
        // 老板查询的商品价格：70
    }


    // 采用乐观锁
    @Test
    public void testOptimistic() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        //小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        /*控制台sql日志:
            UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
            ==> Parameters: 外星人笔记本(String), 150(Integer), 2(Integer), 1(Long), 1(Integer)
         */


        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);
        /*
        UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        ==> Parameters: 外星人笔记本(String), 70(Integer), 2(Integer), 1(Long), 1(Integer)
         */

        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productLaoban.getPrice());
        // 老板查询的商品价格：150
    }


    // 采用乐观锁模拟小王和小李修改商品价格的场景
    @Test
    public void testMybatisPlusOptimistic() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        //小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        /*控制台sql日志:
            ==>  Preparing: UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
            ==> Parameters: 外星人笔记本(String), 150(Integer), 3(Integer), 1(Long), 2(Integer)
         */


        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        int result = productMapper.updateById(productWang);
        /*控制台sql日志:
            ==>  Preparing: UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
            ==> Parameters: 外星人笔记本(String), 70(Integer), 3(Integer), 1(Long), 2(Integer)
         */


        if (result == 0) {
            //操作失败，重试。重新获取版本号
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
            /*控制台sql日志:
                ==>  Preparing: UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
                ==> Parameters: 外星人笔记本(String), 120(Integer), 4(Integer), 1(Long), 3(Integer)
             */
        }

        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productLaoban.getPrice());
        // 老板查询的商品价格：150
    }


}

package com.atguigu.mybatispuls;


import com.atguigu.mybatispuls.mapper.UserMapper;
import com.atguigu.mybatispuls.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;

    // MyBatisPlus分页插件
    @Test
    public void testPage() {
        //设置分页参数
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPage(page, null);
        /*控制台sql日志打印：
         ==> Preparing: SELECT COUNT(*) AS total FROM t_user WHERE is_deleted = 0
         ==> Preparing: SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?,?
        */
        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
        /*
            当前页：2
            每页显示的条数：3
            总记录数：15
            总页数：5
            是否有上一页：true
            是否有下一页：true
         */
    }


    @Test
    public void testSelectPageVo() {
        //设置分页参数
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVo(page, 20);
        /*控制台sql日志打印：
         ==>  Preparing: SELECT COUNT(*) AS total FROM t_user WHERE age > ?
         ==>  Preparing: select uid,user_name,age,email from t_user where age > ? LIMIT ?,?
         */

        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
        /*
            当前页：2
            每页显示的条数：3
            总记录数：12
            总页数：4
            是否有上一页：true
            是否有下一页：true
         */
    }

}

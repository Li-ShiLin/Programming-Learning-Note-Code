package com.atguigu.mybatispuls;


import com.atguigu.mybatispuls.mapper.UserMapper;
import com.atguigu.mybatispuls.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    // 通过map条件查询用户信息
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE user_name = ? AND age = ? AND is_deleted=0
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("user_name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    // 新增数据
    // INSERT INTO t_user ( user_name, age, email ) VALUES ( ?, ?, ? )
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@atguigu.com");

        int result = userMapper.insert(user);
        System.out.println("受影响行数：" + result);
        System.out.println("id自动获取：" + user.getId());
        /*
                受影响行数：1
                id自动获取：6
         */
    }


    // 通过多个id批量删除（逻辑删除）
    // UPDATE t_user SET is_deleted=1 WHERE uid IN ( ? , ? , ? ) AND is_deleted=0
    @Test
    public void testDeleteBatchIds() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("受影响行数：" + result);
        // 受影响行数：3
    }

}
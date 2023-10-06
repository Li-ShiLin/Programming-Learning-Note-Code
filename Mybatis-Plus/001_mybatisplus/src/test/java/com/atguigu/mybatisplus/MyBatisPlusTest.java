package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
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

    // SELECT id,name,age,email FROM user
    @Test
    public void testSelectList() {
        // selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        // 通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }


    // 新增数据
    // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
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
                id自动获取：1709849120311668738
         */
    }


    // 通过id删除用户信息
    // DELETE FROM user WHERE id=?
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1709849120311668738L);
        System.out.println("受影响行数：" + result);
        // 受影响行数：1
    }


    // 通过多个id批量删除
    // DELETE FROM user WHERE id IN ( ? , ? , ? )
    @Test
    public void testDeleteBatchIds() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("受影响行数：" + result);
        // 受影响行数：3
    }


    // 根据map集合中所设置的条件删除用户信息
    // DELETE FROM user WHERE name = ? AND age = ?
    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:" + result); // result:2
    }


    // 通过id进行修改
    // UPDATE user SET name=?, email=? WHERE id=?
    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setName("李四");
        user.setEmail("zhangsan@qq.com");
        int result = userMapper.updateById(user);
        System.out.println("受影响行数：" + result);
    }

    // 根据id查询用户信息
    // SELECT id,name,age,email FROM user WHERE id=?
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println(user); // User(id=5, name=李四, age=24, email=zhangsan@qq.com)
    }


    //根据多个id查询多个用户信息
    //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
    @Test
    public void testSelectBatchIds() {
        List<Long> idList = Arrays.asList(4L, 5L);
        List<User> list = userMapper.selectBatchIds(idList);
        list.forEach(System.out::println);
    }


    // 通过map条件查询用户信息
    // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    // 查询所有用户信息
    // SELECT id,name,age,email FROM user
    @Test
    public void testSelectListt() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }


   // 自定义sql：根据id查询用户信息为map集合
   // select id,name,age,email from user where id = ?
   @Test
   public void testSelfDefine() {
       Map<String, Object> map = userMapper.selectMapById(1L);
       System.out.println(map);
       // {name=Jone, id=1, age=18, email=test1@baomidou.com}
   }


}
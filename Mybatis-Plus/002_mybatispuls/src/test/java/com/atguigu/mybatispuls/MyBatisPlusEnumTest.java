package com.atguigu.mybatispuls;


import com.atguigu.mybatispuls.enums.SexEnum;
import com.atguigu.mybatispuls.mapper.UserMapper;
import com.atguigu.mybatispuls.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MyBatisPlusEnumTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        // INSERT INTO t_user ( user_name, sex, age ) VALUES ( ?, ?, ? )
        // Parameters: admin(String), 1(Integer), 33(Integer)
        System.out.println("result:" + result);
    }
}

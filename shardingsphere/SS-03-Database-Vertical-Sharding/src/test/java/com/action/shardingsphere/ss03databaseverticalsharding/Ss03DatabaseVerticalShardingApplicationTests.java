package com.action.shardingsphere.ss03databaseverticalsharding;

import com.action.shardingsphere.ss03databaseverticalsharding.entity.User;
import com.action.shardingsphere.ss03databaseverticalsharding.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss03DatabaseVerticalShardingApplicationTests {

    //注入user 的mapper
    @Autowired
    private UserMapper userMapper;

    // 检查 Java 版本（用于验证运行时使用的 JDK 版本）
    static {
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getProperty("java.home");
        System.out.println("========================================");
        System.out.println("当前运行 Java 版本: " + javaVersion);
        System.out.println("当前 Java 路径: " + javaHome);
        System.out.println("========================================");
    }

    //======================测试垂直分库==================
    //添加操作
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("lucy");
        user.setUstatus("a");
        userMapper.insert(user);
        System.out.println("插入成功，生成的user_id: " + user.getUserId());
    }

    //查询操作 - 查询所有用户
    @Test
    public void findAllUsers() {
        java.util.List<User> users = userMapper.selectList(null);
        System.out.println("查询所有用户，数量: " + users.size());
        for (User user : users) {
            System.out.println("  user_id=" + user.getUserId() + ", username=" + user.getUsername() + ", ustatus=" + user.getUstatus());
        }
    }

    //查询操作 - 根据user_id查询
    @Test
    public void findUserById() {
        User user = userMapper.selectById(465191484111454209L);
        System.out.println("根据user_id查询结果: " + user);
    }
}

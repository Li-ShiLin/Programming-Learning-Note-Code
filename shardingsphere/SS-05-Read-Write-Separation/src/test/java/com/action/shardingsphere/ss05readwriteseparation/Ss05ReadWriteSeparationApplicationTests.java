package com.action.shardingsphere.ss05readwriteseparation;

import com.action.shardingsphere.ss05readwriteseparation.entity.User;
import com.action.shardingsphere.ss05readwriteseparation.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss05ReadWriteSeparationApplicationTests {

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

    //======================测试读写分离==================
    //添加操作（会路由到主库）
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("lucymary");
        user.setUstatus("a");
        userMapper.insert(user);
        System.out.println("插入成功，生成的user_id: " + user.getUserId());
        System.out.println("注意：插入操作会路由到主库（m0）");
    }

    //查询操作（会路由到从库）
    @Test
    public void findUserDb() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置userid 值
        wrapper.eq("user_id", 465508031619137537L);
        User user = userMapper.selectOne(wrapper);
        System.out.println("查询结果: " + user);
        System.out.println("注意：查询操作会路由到从库（s0）");
    }

    //查询操作 - 查询所有用户（会路由到从库）
    @Test
    public void findAllUsers() {
        java.util.List<User> users = userMapper.selectList(null);
        System.out.println("查询所有用户，数量: " + users.size());
        for (User user : users) {
            System.out.println("  user_id=" + user.getUserId() + ", username=" + user.getUsername() + ", ustatus=" + user.getUstatus());
        }
        System.out.println("注意：查询操作会路由到从库（s0）");
    }

    //更新操作（会路由到主库）
    @Test
    public void updateUserDb() {
        User user = new User();
        user.setUserId(465508031619137537L);
        user.setUsername("lucymary_updated");
        user.setUstatus("b");
        int result = userMapper.updateById(user);
        System.out.println("更新结果: " + (result > 0 ? "成功" : "失败"));
        System.out.println("注意：更新操作会路由到主库（m0）");
    }

    //删除操作（会路由到主库）
    @Test
    public void deleteUserDb() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 465508031619137537L);
        int result = userMapper.delete(wrapper);
        System.out.println("删除结果: " + (result > 0 ? "成功" : "失败"));
        System.out.println("注意：删除操作会路由到主库（m0）");
    }
}

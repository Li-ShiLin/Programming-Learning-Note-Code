package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();

    /**
     * 根据用户名查询用户信息
     */
    User getUserByUsername(String username);

    /**
     * 验证登录
     */
    User checkLogin(String username, String password);

    /**
     * 验证登录（参数为map）
     */
    User checkLoginByMap(Map<String, Object> map);

    /**
     * 添加用户信息
     */
    int insertUser(User user);

    /**
     * 验证登录（使用@Param）
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);
}

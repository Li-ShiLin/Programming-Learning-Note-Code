package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.SQLMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class SQLMapperTest {

    @Test
    public void testGetUserByLike() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = mapper.getUserByLike("a");
        System.out.println(list);
    }

    @Test
    public void testDeleteMore() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        int result = mapper.deleteMore("1,2,3");
        System.out.println(result);
    }

    @Test
    public void testGetUserByTableName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = mapper.getUserByTableName("t_user");
        System.out.println(list);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null, "王五", "123", 23, "男", "123@163.com");
        mapper.insertUser(user);
        System.out.println(user);
        // User(id=8, username=王五, password=123, age=23, sex=男, email=123@163.com)
    }

    // 使用jdbc插入时获取自动递增的主键
    @Test
    public void testJDBC() throws Exception {
        String username = "admin";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.10:3306/mybatis", "root", "root");
        PreparedStatement ps = connection.prepareStatement("insert into ", Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
    }
}

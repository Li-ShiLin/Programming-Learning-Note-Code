package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


public class SelectMapperTest {

    /**
     * MyBatis的各种查询功能：
     * 1、若查询出的数据只有一条
     * a>可以通过实体类对象接收
     * b>可以通过list集合接收
     * c>可以通过map集合接收
     * 结果：{password=123456, sex=男, id=3, age=23, email=12345@qq.com, username=admin}
     * 2、若查询出的数据有多条
     * a>可以通过实体类类型的list集合接收
     * b>可以通过map类型的list集合接收
     * c>可以在mapper接口的方法上添加@MapKey注解，此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中
     * 注意：一定不能通过实体类对象接收，此时会抛异常TooManyResultsException
     * <p>
     * MyBatis中设置了默认的类型别名
     * java.lang.Integer-->int,integer
     * int-->_int,_integer
     * Map-->map
     * String-->string
     */

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserById(3));
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUser());
    }

    @Test
    public void testGetCount() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getCount());
    }

    @Test
    public void testGetUserByIdToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserByIdToMap(3));
    }

    @Test
    public void testGetAllUserToMap1() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUserToMap1());
        // 返回结果是List
//        [{password=123, sex=?, id=5, age=23, email=12345@qq.com, username=??},
//        {password=123, sex=?, id=6, age=23, email=123@qq.com, username=??},
//        {password=123, sex=男, id=7, age=23, email=123@qq.com, username=李四}]
    }

    @Test
    public void testGetAllUserToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUserToMap());
        // 返回结果是map类型，id作为key
//        {
//            1 = {password = 123, sex = ?, id = 1, age = 23, email = 12345 @qq.com,username =??},
//            2 = {password = 123, sex = ?, id = 2, age = 23, email = 12345 @qq.com,username =??},
//            3 = {password = 123, sex = ?, id = 3, age = 23, email = 12345 @qq.com,username =??},
//            4 = {password = 123, sex = ?, id = 4, age = 23, email = 12345 @qq.com,username =??},
//            5 = {password = 123, sex = ?, id = 5, age = 23, email = 12345 @qq.com,username =??},
//            6 = {password = 123, sex = ?, id = 6, age = 23, email = 123 @qq.com,username =??},
//            7 = {password = 123, sex = 男, id = 7, age = 23, email = 123 @qq.com,username = 李四}
//        }
    }

}

package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class MybatisTest {

    /**
     * 解决字段名和属性名不一致的情况：
     * a>为字段起别名，保持和属性名的一致
     * b>设置全局配置，将_自动映射为驼峰
     * <setting name="mapUnderscoreToCamelCase" value="true"/>
     * c>通过resultMap设置自定义的映射关系
     * <resultMap id="empResultMap" type="Emp">
     * <id property="eid" column="eid"></id>
     * <result property="empName" column="emp_name"></result>
     * <result property="age" column="age"></result>
     * <result property="sex" column="sex"></result>
     * <result property="email" column="email"></result>
     * </resultMap>
     * <p>
     * 处理多对一的映射关系：
     * a>级联属性赋值
     * b>association
     * c>分步查询
     * <p>
     * 处理一对多的映射关系
     * a>collection
     * b>分步查询
     */

    @Test
    public void testGetAllEmpByFirstWay() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmpByFirstWay();
        list.forEach(emp -> System.out.println(emp));
//        Emp(eid=1, empName=null, age=22, sex=男, email=2762373@qq.com)
//        Emp(eid=2, empName=null, age=32, sex=男, email=2929@qq.com)
//        Emp(eid=3, empName=null, age=20, sex=男, email=ywyyw@163.com)
//        Emp(eid=4, empName=null, age=25, sex=男, email=237636@168.com)
    }


    @Test
    public void testGetAllEmpBySecondWay() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmpBySecondWay();
        list.forEach(emp -> System.out.println(emp));
//        Emp(eid=1, empName=张三, age=22, sex=男, email=2762373@qq.com)
//        Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com)
//        Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com)
//        Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com)
    }

    @Test
    public void testGetAllEmpByResultMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmpByResultMap();
        list.forEach(emp -> System.out.println(emp));
//        Emp(eid=1, empName=张三, age=22, sex=男, email=2762373@qq.com)
//        Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com)
//        Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com)
//        Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com)
    }
}

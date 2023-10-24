package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MBGTest {

    //查询所有数据
    @Test
    public void testSelectByExampleOne() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
            List<Emp> list = mapper.selectByExample(null);
            // select eid, emp_name, age, sex, email, did from t_emp
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //根据条件查询(QBC风格)
    @Test
    public void testSelectByExampleTwo() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //根据条件查询(QBC风格)
            EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
            example.or().andDidIsNotNull();
            // select eid, emp_name, age, sex, email, did from t_emp WHERE ( emp_name = ? and age >= ? ) or( did is not null )
            List<Emp> list = mapper.selectByExample(example);
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 普通修改
    @Test
    public void testUpdateByPrimaryKey() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            // 构造方法：public Emp(Integer eid, String empName, Integer age, String sex, String email, Integer did) {}
            Emp emp = new Emp(1, "admin", 22, null, "456@qq.com", 3);
            // 普通修改
            mapper.updateByPrimaryKey(emp);
            // update t_emp set emp_name = ?, age = ?, sex = ?, email = ?, did = ? where eid = ?
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // updateByPrimaryKeySelective 选择性修改
    @Test
    public void testUpdateByPrimaryKeySelective() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            // 构造方法：public Emp(Integer eid, String empName, Integer age, String sex, String email, Integer did) {}
            Emp emp = new Emp(1, "admin", 22, null, "456@qq.com", 3);
            // Selective 选择性修改
            mapper.updateByPrimaryKeySelective(emp);
            // update t_emp SET emp_name = ?, age = ?, email = ?, did = ? where eid = ?
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

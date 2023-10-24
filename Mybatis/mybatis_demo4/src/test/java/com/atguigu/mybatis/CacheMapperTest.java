package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class CacheMapperTest {


    // 同一个sqlSession下的同一个mapper,一级缓存生效
    @Test
    public void testOneCacheSameSqlSessionFirst() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // 第二次查询没有执行任何sql
        Emp emp2 = mapper1.getEmpByEid(1);
        System.out.println(emp2);
    }

    // 同一个sqlSession下的不同mapper,一级缓存也生效
    @Test
    public void testOneCacheSameSqlSessionSecond() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // 第二次查询没有执行任何sql
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }


    //  一级缓存失效情况一：不同的SqlSession对应不同的一级缓存
    @Test
    public void testOneCacheDifferentSqlSession() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // select * from t_emp where eid = ?
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }

    // 一级缓存失效情况二：同一个SqlSession但查询条件不同
    @Test
    public void testOneCacheSameSqlSessionThird() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

        //  select * from t_emp where eid = ?
        Emp emp2 = mapper.getEmpByEid(4);
        System.out.println(emp2);
    }


    // 一级缓存失效情况三：同一个SqlSession两次查询期间执行了任何一次增删改操作
    @Test
    public void testOneCacheInsert() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp1);
        mapper.insertEmp(new Emp(null, "abc", 23, "男", "123@qq.com"));
        // insert into t_emp values(null,?,?,?,?,null)

        Emp emp2 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp2);
    }

    // 一级缓存失效情况四：同一个SqlSession两次查询期间手动清空了缓存
    @Test
    public void testOneCacheClear() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp1);
        sqlSession.clearCache();
        Emp emp2 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp2);
    }


    @Test
    public void testTwoCache() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpByEid(1));
            // select * from t_emp where eid = ?
            sqlSession1.close();
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpByEid(1));
            // 没有执行sql
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

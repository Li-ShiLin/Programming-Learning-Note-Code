package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class PageHelperTest {

    /**
     * 一.sql分页实现：
     * limit index,pageSize
     * index:当前页的起始索引
     * pageSize：每页显示的条数
     * pageNum：当前页的页码
     * index=(pageNum-1)*pageSize
     * <p>
     * 二、使用MyBatis的分页插件实现分页功能：
     * 1、需要在查询功能之前开启分页
     * PageHelper.startPage(int pageNum, int pageSize);
     * 2、在查询功能之后获取分页相关信息
     * PageInfo<Emp> page = new PageInfo<>(list, 5);
     * list表示分页数据
     * 5表示当前导航分页的数量
     */

    @Test
    public void testPageHelperOne() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(1, 4);
            List<Emp> list = mapper.selectByExample(null);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPageHelperTwo() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(3, 2);
            List<Emp> list = mapper.selectByExample(null);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 在查询功能之后获取分页相关信息方式一：PageHelper.startPage的返回值中包含分页数据
    @Test
    public void testPageHelperThree() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            Page<Object> page = PageHelper.startPage(2, 2);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
            List<Emp> list = mapper.selectByExample(null);
            System.out.println(page);
            //  Page{count=true, pageNum=2, pageSize=2, startRow=2, endRow=4, total=9, pages=5, reasonable=false, pageSizeZero=false}
            //  [Emp{eid=3, empName='王五', age=20, sex='男', email='ywyyw@163.com', did=3},
            //  Emp{eid=4, empName='钱多多', age=25, sex='男', email='237636@168.com', did=2}]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    //   在查询功能之后获取分页相关信息方式二：使用 new PageInfo<>(list, 5) 对查询后的list进行处理，再从返回值中获取分页信息
    @Test
    public void testPageHelperFour() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(2, 2);
            List<Emp> list = mapper.selectByExample(null);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
            PageInfo<Emp> page = new PageInfo<>(list, 2);
            //  PageInfo{pageNum=2, pageSize=2, size=2, startRow=3, endRow=4, total=9, pages=5,
            //  list=Page{count=true, pageNum=2, pageSize=2, startRow=2, endRow=4, total=9, pages=5, reasonable=false, pageSizeZero=false}
            //  [Emp{eid=3, empName='王五', age=20, sex='男', email='ywyyw@163.com', did=3},
            //  Emp{eid=4, empName='钱多多', age=25, sex='男', email='237636@168.com', did=2}],
            //  prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true,
            //  hasNextPage=true, navigatePages=2, navigateFirstPage=1, navigateLastPage=2, navigatepageNums=[1, 2]}
            System.out.println(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

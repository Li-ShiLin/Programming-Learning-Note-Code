package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class DynamicSQLMapperTest {

    /**
     * 动态SQL：
     * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
     * 2、where：
     * 当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉
     * 当where标签中没有内容时，此时where标签没有任何效果,也就是不会生成where关键字
     * 注意：where标签不能将其中内容后面多余的and或or去掉
     * 3、trim：
     * 若标签中有内容时：
     * prefix|suffix：将trim标签中内容前面或后面添加指定内容
     * suffixOverrides|prefixOverrides：将trim标签中内容前面或后面去掉指定内容
     * 若标签中没有内容时，trim标签也没有任何效果
     * 4、choose、when、otherwise，相当于if...else if...else
     * when至少要有一个，otherwise最多只能有一个
     * 5、foreach
     * collection:设置需要循环的数组或集合
     * item:表示数组或集合中的每一个数据
     * separator:循环体之间的分割符
     * open:foreach标签所循环的所有内容的开始符
     * close:foreach标签所循环的所有内容的结束符
     * 6、sql标签
     * 设置SQL片段：<sql id="empColumns">eid,emp_name,age,sex,email</sql>
     * 引用SQL片段：<include refid="empColumns"></include>
     */

    @Test
    public void getEmpByConditionIf() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByConditionIf(new Emp(null, "", null, "", null));
        // select * from t_emp where 1=1
        System.out.println(list);
        List<Emp> list2 = mapper.getEmpByConditionIf(new Emp(3, "", 23, "男", "2762373@qq.com"));
        // select * from t_emp where 1=1 and age = ? and sex = ? and email = ?
        System.out.println(list2);
    }

    @Test
    public void testGetEmpByConditionWhere() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByConditionWhere(new Emp(null, "", null, "", null));
        // select * from t_emp
        System.out.println(list);
        List<Emp> list2 = mapper.getEmpByConditionWhere(new Emp(3, "", 23, "男", "2762373@qq.com"));
        // select * from t_emp WHERE age = ? or sex = ? and email = ?
        System.out.println(list2);
    }


    @Test
    public void testGetEmpByConditionTrim() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByConditionTrim(new Emp(null, "", null, "", null));
        // select eid ,emp_name,age,sex,email from t_emp
        System.out.println(list);
        List<Emp> list2 = mapper.getEmpByConditionTrim(new Emp(3, "", 23, "男", "2762373@qq.com"));
        // select eid ,emp_name,age,sex,email from t_emp where age = ? or sex = ? and email = ?
        System.out.println(list2);
    }

    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByChoose(new Emp(null, "", null, "", ""));
        //  select * from t_emp WHERE did = 1
        List<Emp> list2 = mapper.getEmpByChoose(new Emp(null, "", 34, "", ""));
        // select * from t_emp WHERE age = ?
        List<Emp> list3 = mapper.getEmpByChoose(new Emp(null, "张三", 34, "男", "2376236@qq.com"));
        // select * from t_emp WHERE emp_name = ?
    }


    @Test
    public void testDeleteMoreByArrayOne() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArrayOne(new Integer[]{6, 7, 8});
        // delete from t_emp where eid in ( ? , ? , ? )
        System.out.println(result);
    }

    @Test
    public void testDeleteMoreByArrayTwo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArrayTwo(new Integer[]{6, 7, 8});
        // delete from t_emp where eid = ? or eid = ? or eid = ?
        System.out.println(result);
    }


    @Test
    public void testInsertMoreByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "a1", 23, "男", "123@qq.com");
        Emp emp2 = new Emp(null, "a2", 23, "男", "123@qq.com");
        Emp emp3 = new Emp(null, "a3", 23, "男", "123@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        System.out.println(mapper.insertMoreByList(emps));
        // insert into t_emp values (null,?,?,?,?,null) , (null,?,?,?,?,null) , (null,?,?,?,?,null)
    }

}

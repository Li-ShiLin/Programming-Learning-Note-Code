package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


public class ResultMapTest {

    /**
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
    public void testGetEmpAndDeptOne() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptOne(3);
        System.out.println(emp);
        // Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com, dept=Dept(did=3, deptName=C))
    }

    @Test
    public void testGetEmpAndDeptTwo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptTwo(3);
        System.out.println(emp);
        // Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com, dept=Dept(did=3, deptName=C))
    }

    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(3);
        // select * from t_emp where eid = ?
        // select * from t_dept where did = ?
        System.out.println(emp);
        // Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com, dept=Dept(did=3, deptName=C))
    }


    @Test
    public void testGetEmpAndDeptByStepLazy() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(3);
        System.out.println(emp.getEmpName());
        // select * from t_emp where eid = ?
    }


    @Test
    public void testGetDeptAndEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmp(2);
        // select * from t_dept left join t_emp on t_dept.did = t_emp.did where t_dept.did = ?
        System.out.println(dept);
        // Dept(did=2, deptName=B, emps=[Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com, dept=null), Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com, dept=null)])
    }

    @Test
    public void testGetDeptAndEmpByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(2);
        // select * from t_dept where did = ?
        // select * from t_emp where did = ?
        System.out.println(dept);
       // Dept(did=2, deptName=B, emps=[Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com, dept=null), Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com, dept=null)])
    }

}

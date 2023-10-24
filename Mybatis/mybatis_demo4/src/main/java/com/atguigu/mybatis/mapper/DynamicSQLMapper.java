package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DynamicSQLMapper {

    /**
     * 多条件查询 (if标签)
     */
    List<Emp> getEmpByConditionIf(Emp emp);

    /**
     * 多条件查询（where标签）
     */
    List<Emp> getEmpByConditionWhere(Emp emp);

    /**
     * 多条件查询（Trim标签）
     */
    List<Emp> getEmpByConditionTrim(Emp emp);

    /**
     * 测试choose、when、otherwise
     */
    List<Emp> getEmpByChoose(Emp emp);


    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArrayOne(@Param("eids") Integer[] eids);

    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArrayTwo(@Param("eids") Integer[] eids);

    /**
     * 通过list集合实现批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);

}

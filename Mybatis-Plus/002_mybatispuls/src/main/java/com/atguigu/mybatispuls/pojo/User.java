package com.atguigu.mybatispuls.pojo;


import com.atguigu.mybatispuls.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data //lombok注解
@TableName("t_user") // 设置实体类所对应的表名
public class User {

//    //将属性所对应的字段指定为主键
//    //@TableId注解的value属性用于指定主键的字段
//    //@TableId注解的type属性设置主键生成策略
//    @TableId("uid")   // 也可以写成@TableId(value = "uid")
//    private Long id;


    //@TableId注解的type属性设置主键生成策略
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;


    //指定属性所对应的字段名
    @TableField("user_name")
    private String name;

    private SexEnum sex;

    private Integer age;

    private String email;

    @TableLogic
    private Integer isDeleted;
}
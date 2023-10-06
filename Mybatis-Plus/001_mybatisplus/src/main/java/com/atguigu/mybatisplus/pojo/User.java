package com.atguigu.mybatisplus.pojo;


import lombok.Data;

@Data //lombok注解
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
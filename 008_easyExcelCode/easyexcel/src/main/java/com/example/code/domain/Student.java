package com.example.code.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/3/11 7:04
 */
@Data
public class Student {
    /**
     * 学生姓名
     */
    private String name;


    /**
     * 学生性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * id
     */
    private String id;

}

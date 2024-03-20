package com.atguigu.spring6.iocxml.factorybean;
public class User {
    private String name;
    public User() {
    }
    public User(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

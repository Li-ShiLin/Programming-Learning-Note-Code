package com.atguigu.spring6.iocxml.auto.service;
import com.atguigu.spring6.iocxml.auto.dao.UserDao;
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void addUser() {
        System.out.println("userService方法执行了...");
        userDao.addUser();
    }
}

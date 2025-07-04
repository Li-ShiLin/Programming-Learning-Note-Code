package com.atguigu.spring6.iocxml.factorybean;
import org.springframework.beans.factory.FactoryBean;
public class MyFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User("张三");
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}

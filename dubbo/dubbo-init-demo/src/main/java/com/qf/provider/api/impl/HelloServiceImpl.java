package com.qf.provider.api.impl;

import com.qf.provider.api.HelloService;

/**
 * 
 * 
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello:" + name;
    }
}

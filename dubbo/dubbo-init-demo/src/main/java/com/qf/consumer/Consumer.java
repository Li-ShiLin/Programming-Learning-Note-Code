package com.qf.consumer;

import com.qf.provider.api.HelloService;
import com.qf.provider.api.common.ProxyFactory;
/**
 * 
 *
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String name = helloService.hello("qf");
        System.out.println(name);
    }
}

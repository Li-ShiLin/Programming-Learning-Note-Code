package com.qf.provider.api;


import com.qf.provider.api.common.*;
import com.qf.provider.api.impl.HelloServiceImpl;

/**
 * 
 * 
 */
public class Provider {
    public static void main(String[] args) {
        URL url = new URL("localhost",8080);

        //模拟远程注册中心
        RemoteMapRegister.regist(HelloService.class.getName(), url);

        //指明服务的实现类
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);


    }
}

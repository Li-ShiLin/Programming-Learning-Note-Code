package com.qf.provider.api.common.protocol.http;


import com.qf.provider.api.common.Invocation;
import com.qf.provider.api.common.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * 
 * 
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
            Invocation invocation = (Invocation)ois.readObject();
            String interfaceName = invocation.getInterfaceName();//HelloService
            Class implClass = LocalRegister.get(interfaceName);//HelloServiceImpl.hello(String name)
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());//HelloServiceImpl.hello(String name)
            System.out.println("tomcat:" + result);
            IOUtils.write(result.getBytes(), resp.getOutputStream());//写回给服务的消费者
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

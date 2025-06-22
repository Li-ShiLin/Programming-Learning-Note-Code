package com.qf;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

import java.util.ServiceLoader;

/**
 * 
 * 
 */
public class TestSPI {
    public static void main(String[] args) {
        /*ServiceLoader<Cat> cats = ServiceLoader.load(Cat.class);
        for (Cat cat : cats) {
            System.out.println(cat.getName());
        }*/
        //获得扩展点加载器
        /*ExtensionLoader<Cat> extensionLoader = ExtensionLoader.getExtensionLoader(Cat.class);
        Cat cat = extensionLoader.getExtension("white");
        System.out.println(cat.getName());*/
        //拿到的是ProtocolFilterWrapper包装类，实际被包装的是HttpProtocol
        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = extensionLoader.getExtension("http");
        System.out.println(protocol);
    }
}

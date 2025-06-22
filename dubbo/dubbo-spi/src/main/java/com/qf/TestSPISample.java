package com.qf;

import org.apache.dubbo.common.extension.ExtensionLoader;
public class TestSPISample {
    public static void main(String[] args) {
        //获得扩展点加载器
        ExtensionLoader<Cat> extensionLoader = ExtensionLoader.getExtensionLoader(Cat.class);
        Cat cat = extensionLoader.getExtension("black");
        System.out.println(cat.getName());
    }
}

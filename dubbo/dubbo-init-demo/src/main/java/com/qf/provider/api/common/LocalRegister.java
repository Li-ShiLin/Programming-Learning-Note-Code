package com.qf.provider.api.common;
import java.util.HashMap;
import java.util.Map;
/**
 * 指明服务的实现类
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}

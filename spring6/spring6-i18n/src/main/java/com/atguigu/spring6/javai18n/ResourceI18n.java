package com.atguigu.spring6.javai18n;
import java.util.Locale;
import java.util.ResourceBundle;
public class ResourceI18n {

    public static void main(String[] args) {
        ResourceBundle bundle1 = ResourceBundle.getBundle("messages",
                new Locale("zh", "CN"));
        String value1 = bundle1.getString("test");
        System.out.println(value1);
        //输出：
        // China test

        ResourceBundle bundle2 = ResourceBundle.getBundle("messages",
                new Locale("en","GB"));
        String value2 = bundle2.getString("test");
        System.out.println(value2);
        //输出：
        // GB test
    }
}

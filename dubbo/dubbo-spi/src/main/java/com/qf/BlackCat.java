package com.qf;
import org.apache.dubbo.common.URL;
public class BlackCat implements Cat{
    @Override
    public String getName() {
        return "black";
    }
}

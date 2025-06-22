package com.qf;

import org.apache.dubbo.common.URL;

/**
 * 
 * 
 */
public class WhiteCat implements Cat{
    @Override
    public String getName() {
        return "white";
    }
}

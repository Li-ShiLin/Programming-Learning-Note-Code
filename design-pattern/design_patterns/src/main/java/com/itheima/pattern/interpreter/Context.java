package com.itheima.pattern.interpreter;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description: 环境角色类
 */
public class Context {

    //定义一个map集合，用来存储变量及对应的值
    private Map<Variable,Integer> map = new HashMap<Variable, Integer>();

    //添加变量的功能
    public void assign(Variable var, Integer value) {
        map.put(var,value);
    }

    //根据变量获取对应的值
    public int getValue(Variable var) {
        return map.get(var);
    }
}

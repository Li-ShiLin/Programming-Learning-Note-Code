package com.itheima.pattern.interpreter;

/**
 * @Description: 抽象表达式类
 */
public abstract class AbstractExpression {

    public abstract int interpret(Context context);
}

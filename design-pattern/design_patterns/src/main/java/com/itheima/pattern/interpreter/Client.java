package com.itheima.pattern.interpreter;


public class Client {
    public static void main(String[] args) {
        //创建环境对象
        Context context = new Context();

        //创建多个变量对象
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");
        Variable d = new Variable("d");

        //将变量存储到环境对象中
        context.assign(a,1);
        context.assign(b,2);
        context.assign(c,3);
        context.assign(d,4);

        //获取抽象语法树    a + b - c + d
        AbstractExpression expression = new Minus(a,new Minus(new Minus(b,c),d));

        //解释（计算）
        int result = expression.interpret(context);

        System.out.println(expression + " = " + result);
        // (a - ((b - c) - d)) = 6

        //创建环境对象
        Context context1 = new Context();
        //将变量存储到环境对象中
        context1.assign(a,1);
        context1.assign(b,2);
        context1.assign(c,3);
        context1.assign(d,4);
        // 获取抽象语法树
        // a + b - c + d
        AbstractExpression expression1 = new Minus(a,new Plus(new Minus(b,c),d));
        //解释（计算）
        int result1 = expression1.interpret(context);

        System.out.println(expression1 + " = " + result1);
        //(a - ((b - c) + d)) = -2
    }
}

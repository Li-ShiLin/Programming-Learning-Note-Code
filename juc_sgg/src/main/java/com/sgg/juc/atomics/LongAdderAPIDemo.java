package com.sgg.juc.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
// LongAdder只能用来计算加法，且从零开始计算
// LongAccumulator提供了自定义的函数操作
public class LongAdderAPIDemo
{
    public static void main(String[] args)
    {
        // LongAdder只能用来计算加法，且从零开始计算
        // LongAdder的使用：
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum()); // 3

        // LongAccumulator提供了自定义的函数操作
//        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator()
//        {
//            @Override
//            public long applyAsLong(long left, long right)
//            {
//                return left + right;
//            }
//        },0);
        // lambda表达式写法：
        LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left + right,0);
        longAccumulator.accumulate(1);//1
        longAccumulator.accumulate(3);//4
        System.out.println(longAccumulator.get()); // 4
    }
}




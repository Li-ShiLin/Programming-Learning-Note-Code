package com.atguigu.memoryleak;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 模拟内存泄漏
 *
 * 
 * 21:20
 */
public class MemoryLeak {
    static List list = new ArrayList();

    public void oomTests() {
        Object obj = new Object();
        list.add(obj);
    }

}
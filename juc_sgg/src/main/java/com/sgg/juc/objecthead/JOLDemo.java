package com.sgg.juc.objecthead;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
public class JOLDemo {

    public static void main(String[] args) {
        test4();
    }

    private static void test1() {
        // 当前系统java虚拟机的信息
        System.out.println(VM.current().details());
//程序输出：
//        # Running 64-bit HotSpot VM.
//        # Using compressed oop with 3-bit shift.
//        # Using compressed klass with 3-bit shift.
//        # Objects are 8 bytes aligned.
//        # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
//        # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]

        System.out.println("###########################");
        // 当前虚拟机中的对象对齐方式，即对象在内存中的对齐倍数
        System.out.println(VM.current().objectAlignment()); // 输出：8
    }

    private static void test2() {
        System.out.println("###########################");
        Object o = new Object(); //16 bytes
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//程序输出：
//        java.lang.Object object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//        12     4        (loss due to the next object alignment)   => 也就是对其填充
//        Instance size: 16 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


    // 第一种情况，只有对象头，没有其它任何实例数据。 定义： class Customer { }
    private static void test3() {
        // 只有对象头，没有其它任何实例数据的类占16 bytes
        Customer c1 = new Customer();//16 bytes
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
//程序输出：
//        com.sgg.juc.objecthead.Customer object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           43 c0 00 f8 (01000011 11000000 00000000 11111000) (-134168509)
//        12     4        (loss due to the next object alignment)
//        Instance size: 16 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }

   // 第二种情况，int + boolean，默认满足对其填充，24 bytes。16字节（忽略压缩指针的影响）+4字节+1字节=21字节----》对其填充，24字节
   //  对象定义： class Customer { int id;boolean flag = false;}
    private static void test4() {
        Customer c1 = new Customer(); // 24字节
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
//程序输出：
//    com.sgg.juc.objecthead.Customer object internals:
//    OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
//    0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//    4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//    8     4           (object header)                           43 c0 00 f8 (01000011 11000000 00000000 11111000) (-134168509)
//    12     4       int Customer.id                               0
//    16     1   boolean Customer.flag                             false
//    17     7           (loss due to the next object alignment)
//    Instance size: 24 bytes
//    Space losses: 0 bytes internal + 7 bytes external = 7 bytes total
    }

    private static void testLast() {
        Object o = new Object();//16 bytes
        //System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer c1 = new Customer();//16 bytes
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
    }

}


class Customer//只有一个对象头的实例对象，16字节（忽略压缩指针的影响）
{
    // 1 第一种情况，只有对象头，没有其它任何实例数据

    // 2 第二种情况，int + boolean，默认满足对其填充，24 bytes。16字节（忽略压缩指针的影响）+4字节+1字节=21字节----》对其填充，24字节
//    int id;
//    boolean flag = false;
//    boolean flag2 = false;

}

/**
 * 1 默认配置，启动了压缩指针，-XX:+UseCompressedClassPointers，
 * 12 + 4(对齐填充) = 一个对象16字节
 * <p>
 * 2 手动配置，关闭了压缩指-XX:-UseCompressedClassPointers，针，
 * 8 + 8 = 一个对象16字节
 */

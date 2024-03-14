package com.sgg.juc.locks;

/*
public class LockSyncDemo {
    Object object = new Object();

    public void m1() {
        // 同步代码块
        synchronized (object) {
            System.out.println("----hello synchronized code block");
            throw new RuntimeException("-----exp");
        }
    }

//    public synchronized void m2() {
//        System.out.println("----hello synchronized m2");
//    }
//
//    public static synchronized void m3() {
//        System.out.println("----hello static synchronized m3");
//    }

    public static void main(String[] args) {

    }
}
*/


// 静态同步方法
// ACC_STATIC,ACC_SYNCHRONIZED访问标志区分该方法是否静态同步方法
public class LockSyncDemo {
    public static synchronized void m3() {
        System.out.println("----hello static synchronized m3");
    }
    public static void main(String[] args) {
    }
}


//// synchronized普通同步方法
//public class LockSyncDemo {
//    public synchronized void m2() {
//        System.out.println("----hello synchronized m2");
//    }
//
//    public static void main(String[] args) {
//
//    }
//}


// 同步代码块中出现异常时，一个monitorenter，一个monitorexit
/*public class LockSyncDemo {
    Object object = new Object();

    public void m1() {
        // 同步代码块
        synchronized (object) {
            System.out.println("----hello synchronized code block");
            throw new RuntimeException("-----exp");
        }
    }

    public static void main(String[] args) {

    }
}*/




/*
// 同步代码块：一个monitorenter，两个monitorexit
public class LockSyncDemo {
    Object object = new Object();

    public void m1() {
        // 同步代码块
        synchronized (object) {
            System.out.println("----hello synchronized code block");
        }
    }

    public static void main(String[] args) {

    }
}
*/


//// 同步代码块中出现异常时，一个monitorenter，一个monitorexit
//public class LockSyncDemo {
//    Object object = new Object();
//
//    public void m1() {
//        // 同步代码块
//        synchronized (object) {
//            System.out.println("----hello synchronized code block");
//            throw new RuntimeException("-----exp");
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
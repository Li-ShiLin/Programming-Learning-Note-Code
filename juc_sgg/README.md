<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. 线程基础知识](#1-%E7%BA%BF%E7%A8%8B%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86)
  - [1.1 多线程相关概念](#11-%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%9B%B8%E5%85%B3%E6%A6%82%E5%BF%B5)
  - [1.2 `JUC` | `native`关键字 | `JNI` | `openJdk`](#12-juc--native%E5%85%B3%E9%94%AE%E5%AD%97--jni--openjdk)
  - [1.3 Thread类start方法底层源码](#13-thread%E7%B1%BBstart%E6%96%B9%E6%B3%95%E5%BA%95%E5%B1%82%E6%BA%90%E7%A0%81)
- [2. Future接口 | Future接口实现类](#2-future%E6%8E%A5%E5%8F%A3--future%E6%8E%A5%E5%8F%A3%E5%AE%9E%E7%8E%B0%E7%B1%BB)
  - [2.1 Future接口](#21-future%E6%8E%A5%E5%8F%A3)
  - [2.2  `FutureTask`类](#22--futuretask%E7%B1%BB)
      - [2.2.1  `FutureTask`类简介](#221--futuretask%E7%B1%BB%E7%AE%80%E4%BB%8B)
      - [2.2.2 `FutureTask`代码演示](#222-futuretask%E4%BB%A3%E7%A0%81%E6%BC%94%E7%A4%BA)
      - [2.2.3  `FutureTask`类的缺点](#223--futuretask%E7%B1%BB%E7%9A%84%E7%BC%BA%E7%82%B9)
  - [2.3 CompletableFuture](#23-completablefuture)
      - [2.3.1  CompletableFuture类简介](#231--completablefuture%E7%B1%BB%E7%AE%80%E4%BB%8B)
      - [2.3.2  `CompletionStage`接口简介](#232--completionstage%E6%8E%A5%E5%8F%A3%E7%AE%80%E4%BB%8B)
  - [2.4  `CompletableFuture`使用案例](#24--completablefuture%E4%BD%BF%E7%94%A8%E6%A1%88%E4%BE%8B)
      - [2.4.1  获得结果和触发计算](#241--%E8%8E%B7%E5%BE%97%E7%BB%93%E6%9E%9C%E5%92%8C%E8%A7%A6%E5%8F%91%E8%AE%A1%E7%AE%97)
      - [2.4.2 对计算结果进行处理](#242-%E5%AF%B9%E8%AE%A1%E7%AE%97%E7%BB%93%E6%9E%9C%E8%BF%9B%E8%A1%8C%E5%A4%84%E7%90%86)
      - [2.4.3 对计算结果进行消费](#243-%E5%AF%B9%E8%AE%A1%E7%AE%97%E7%BB%93%E6%9E%9C%E8%BF%9B%E8%A1%8C%E6%B6%88%E8%B4%B9)
      - [2.4.4 对计算速度进行选用](#244-%E5%AF%B9%E8%AE%A1%E7%AE%97%E9%80%9F%E5%BA%A6%E8%BF%9B%E8%A1%8C%E9%80%89%E7%94%A8)
      - [2.4.5 对计算结果进行合并](#245-%E5%AF%B9%E8%AE%A1%E7%AE%97%E7%BB%93%E6%9E%9C%E8%BF%9B%E8%A1%8C%E5%90%88%E5%B9%B6)
  - [2.5 `completableFuture`和线程池说明](#25-completablefuture%E5%92%8C%E7%BA%BF%E7%A8%8B%E6%B1%A0%E8%AF%B4%E6%98%8E)
  - [2.6  `CompletableFuture`案例-电商网站的比价需求](#26--completablefuture%E6%A1%88%E4%BE%8B-%E7%94%B5%E5%95%86%E7%BD%91%E7%AB%99%E7%9A%84%E6%AF%94%E4%BB%B7%E9%9C%80%E6%B1%82)
- [3. 锁](#3-%E9%94%81)
  - [3.1  悲观锁和乐观锁](#31--%E6%82%B2%E8%A7%82%E9%94%81%E5%92%8C%E4%B9%90%E8%A7%82%E9%94%81)
      - [3.1.1 悲观锁](#311-%E6%82%B2%E8%A7%82%E9%94%81)
      - [3.1.2  乐观锁](#312--%E4%B9%90%E8%A7%82%E9%94%81)
  - [3.2  `synchronized`关键字](#32--synchronized%E5%85%B3%E9%94%AE%E5%AD%97)
      - [3.2.1 阿里规约：`synchronized`锁的粒度](#321-%E9%98%BF%E9%87%8C%E8%A7%84%E7%BA%A6synchronized%E9%94%81%E7%9A%84%E7%B2%92%E5%BA%A6)
      - [3.2.2  `synchronized`的对象锁](#322--synchronized%E7%9A%84%E5%AF%B9%E8%B1%A1%E9%94%81)
      - [3.2.3  `synchronized`的类锁](#323--synchronized%E7%9A%84%E7%B1%BB%E9%94%81)
      - [3.2.4  `synchronized`类锁和对象锁总结](#324--synchronized%E7%B1%BB%E9%94%81%E5%92%8C%E5%AF%B9%E8%B1%A1%E9%94%81%E6%80%BB%E7%BB%93)
      - [3.2.5 `synchronized`同步代码块字节码分析](#325-synchronized%E5%90%8C%E6%AD%A5%E4%BB%A3%E7%A0%81%E5%9D%97%E5%AD%97%E8%8A%82%E7%A0%81%E5%88%86%E6%9E%90)
      - [3.2.6 `synchronized`同步方法字节码分析](#326-synchronized%E5%90%8C%E6%AD%A5%E6%96%B9%E6%B3%95%E5%AD%97%E8%8A%82%E7%A0%81%E5%88%86%E6%9E%90)
      - [3.2.7 `synchronized`的实现原理](#327-synchronized%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86)
  - [3.3  公平锁和非公平锁](#33--%E5%85%AC%E5%B9%B3%E9%94%81%E5%92%8C%E9%9D%9E%E5%85%AC%E5%B9%B3%E9%94%81)
  - [3.4 可重入锁（递归锁）](#34-%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81%E9%80%92%E5%BD%92%E9%94%81)
      - [3.4.1 可重入锁简介](#341-%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81%E7%AE%80%E4%BB%8B)
      - [3.4.2  隐式锁`synchronized`](#342--%E9%9A%90%E5%BC%8F%E9%94%81synchronized)
      - [3.4.3  显式锁`ReentrantLock`](#343--%E6%98%BE%E5%BC%8F%E9%94%81reentrantlock)
      - [3.3.2  `synchronized`可重入的实现原理](#332--synchronized%E5%8F%AF%E9%87%8D%E5%85%A5%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86)
  - [3.4  死锁](#34--%E6%AD%BB%E9%94%81)
      - [3.4.1 死锁简介 | 死锁演示](#341-%E6%AD%BB%E9%94%81%E7%AE%80%E4%BB%8B--%E6%AD%BB%E9%94%81%E6%BC%94%E7%A4%BA)
      - [3.4.2 死锁的排查](#342-%E6%AD%BB%E9%94%81%E7%9A%84%E6%8E%92%E6%9F%A5)
  - [3.5 写锁(独占锁)/读锁(共享锁)](#35-%E5%86%99%E9%94%81%E7%8B%AC%E5%8D%A0%E9%94%81%E8%AF%BB%E9%94%81%E5%85%B1%E4%BA%AB%E9%94%81)
  - [3.6 自旋锁 spinLock](#36-%E8%87%AA%E6%97%8B%E9%94%81-spinlock)
  - [3.7 无锁->独占锁->读写锁->邮戳锁](#37-%E6%97%A0%E9%94%81-%E7%8B%AC%E5%8D%A0%E9%94%81-%E8%AF%BB%E5%86%99%E9%94%81-%E9%82%AE%E6%88%B3%E9%94%81)
  - [3.8 无锁->偏向锁->轻量锁->重量锁](#38-%E6%97%A0%E9%94%81-%E5%81%8F%E5%90%91%E9%94%81-%E8%BD%BB%E9%87%8F%E9%94%81-%E9%87%8D%E9%87%8F%E9%94%81)
- [4. 线程中断 与`LockSupport`](#4-%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD-%E4%B8%8Elocksupport)
  - [4.1 线程中断 与  **`Thread`类中断`API`方法**](#41-%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD-%E4%B8%8E--thread%E7%B1%BB%E4%B8%AD%E6%96%ADapi%E6%96%B9%E6%B3%95)
  - [4.2 如何中断运行线程](#42-%E5%A6%82%E4%BD%95%E4%B8%AD%E6%96%AD%E8%BF%90%E8%A1%8C%E7%BA%BF%E7%A8%8B)
      - [4.2.1 `volatile`变量实现线程中断](#421-volatile%E5%8F%98%E9%87%8F%E5%AE%9E%E7%8E%B0%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD)
      - [4.2.2 `AtomicBoolean`实现线程中断](#422-atomicboolean%E5%AE%9E%E7%8E%B0%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD)
      - [4.2.3 `Thread`类`api`方法实现线程中断](#423-thread%E7%B1%BBapi%E6%96%B9%E6%B3%95%E5%AE%9E%E7%8E%B0%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD)
  - [4.3 `LockSupport`](#43-locksupport)
      - [4.3.1   `LockSupport`简介 | 线程的等待和唤醒](#431---locksupport%E7%AE%80%E4%BB%8B--%E7%BA%BF%E7%A8%8B%E7%9A%84%E7%AD%89%E5%BE%85%E5%92%8C%E5%94%A4%E9%86%92)
      - [4.3.2 `object`的`wait()`方法（等待） 和  `notify()`方法（唤醒）](#432-object%E7%9A%84wait%E6%96%B9%E6%B3%95%E7%AD%89%E5%BE%85-%E5%92%8C--notify%E6%96%B9%E6%B3%95%E5%94%A4%E9%86%92)
      - [4.3.3  `condition`的`await()`方法（等待）和 `signal()`方法（唤醒）](#433--condition%E7%9A%84await%E6%96%B9%E6%B3%95%E7%AD%89%E5%BE%85%E5%92%8C-signal%E6%96%B9%E6%B3%95%E5%94%A4%E9%86%92)
      - [4.3.4  `LockSupport`类中的`park`等待和`unpark`唤醒](#434--locksupport%E7%B1%BB%E4%B8%AD%E7%9A%84park%E7%AD%89%E5%BE%85%E5%92%8Cunpark%E5%94%A4%E9%86%92)
- [5.  `Java`内存模型`JMM`](#5--java%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8Bjmm)
  - [5.1 `JMM`简介](#51-jmm%E7%AE%80%E4%BB%8B)
  - [5.2 `JMM`与并发编程的三大特性](#52-jmm%E4%B8%8E%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E7%9A%84%E4%B8%89%E5%A4%A7%E7%89%B9%E6%80%A7)
      - [5.2.1  可见性](#521--%E5%8F%AF%E8%A7%81%E6%80%A7)
      - [5.2.2 原子性](#522-%E5%8E%9F%E5%AD%90%E6%80%A7)
      - [5.2.3   有序性](#523---%E6%9C%89%E5%BA%8F%E6%80%A7)
  - [5.3 `JMM`的`happens-before`原则](#53-jmm%E7%9A%84happens-before%E5%8E%9F%E5%88%99)
      - [5.3.1  `happens-before`原则简介](#531--happens-before%E5%8E%9F%E5%88%99%E7%AE%80%E4%BB%8B)
      - [5.3.2  `happens-before`的八条规则](#532--happens-before%E7%9A%84%E5%85%AB%E6%9D%A1%E8%A7%84%E5%88%99)
      - [5.3.3  `happens-before`原则总结](#533--happens-before%E5%8E%9F%E5%88%99%E6%80%BB%E7%BB%93)
- [6. `volatile`关键字 与 `JMM`](#6-volatile%E5%85%B3%E9%94%AE%E5%AD%97-%E4%B8%8E-jmm)
  - [6.1 `volatile`三大特性](#61-volatile%E4%B8%89%E5%A4%A7%E7%89%B9%E6%80%A7)
  - [6.2   `volatile` 与 内存屏障](#62---volatile-%E4%B8%8E-%E5%86%85%E5%AD%98%E5%B1%8F%E9%9A%9C)
  - [6.3  `happens-before`原则 之`volatile`变量原则](#63--happens-before%E5%8E%9F%E5%88%99-%E4%B9%8Bvolatile%E5%8F%98%E9%87%8F%E5%8E%9F%E5%88%99)
  - [6.4  `volatile` 可见性](#64--volatile-%E5%8F%AF%E8%A7%81%E6%80%A7)
  - [6.5 `volatile`有序性（指令禁重排）| 内存屏障](#65-volatile%E6%9C%89%E5%BA%8F%E6%80%A7%E6%8C%87%E4%BB%A4%E7%A6%81%E9%87%8D%E6%8E%92-%E5%86%85%E5%AD%98%E5%B1%8F%E9%9A%9C)
  - [6.6  `volatile`不保证原子性](#66--volatile%E4%B8%8D%E4%BF%9D%E8%AF%81%E5%8E%9F%E5%AD%90%E6%80%A7)
  - [6.7  `volatile` 关键字的正确使用](#67--volatile-%E5%85%B3%E9%94%AE%E5%AD%97%E7%9A%84%E6%AD%A3%E7%A1%AE%E4%BD%BF%E7%94%A8)
  - [6.8  `volatile`总结](#68--volatile%E6%80%BB%E7%BB%93)
- [7. `CAS`](#7-cas)
  - [7.1 `CAS`简介](#71-cas%E7%AE%80%E4%BB%8B)
  - [7.2 `CAS`代码演示](#72-cas%E4%BB%A3%E7%A0%81%E6%BC%94%E7%A4%BA)
  - [7.3 `CAS` 与 `UnSafe`类](#73-cas-%E4%B8%8E-unsafe%E7%B1%BB)
  - [7.4 `CAS` 与 原子引用`AtomicReference`](#74-cas-%E4%B8%8E-%E5%8E%9F%E5%AD%90%E5%BC%95%E7%94%A8atomicreference)
  - [7.5  `CAS` 与 自旋锁](#75--cas-%E4%B8%8E-%E8%87%AA%E6%97%8B%E9%94%81)
  - [7.6 `CAS`缺点：开销大 & `ABA`问题](#76-cas%E7%BC%BA%E7%82%B9%E5%BC%80%E9%94%80%E5%A4%A7--aba%E9%97%AE%E9%A2%98)
- [8. 原子类](#8-%E5%8E%9F%E5%AD%90%E7%B1%BB)
  - [8.1 原子类简介](#81-%E5%8E%9F%E5%AD%90%E7%B1%BB%E7%AE%80%E4%BB%8B)
  - [8.2  基本类型原子类](#82--%E5%9F%BA%E6%9C%AC%E7%B1%BB%E5%9E%8B%E5%8E%9F%E5%AD%90%E7%B1%BB)
  - [8.3  数组类型原子类](#83--%E6%95%B0%E7%BB%84%E7%B1%BB%E5%9E%8B%E5%8E%9F%E5%AD%90%E7%B1%BB)
  - [8.4  引用类型原子类](#84--%E5%BC%95%E7%94%A8%E7%B1%BB%E5%9E%8B%E5%8E%9F%E5%AD%90%E7%B1%BB)
  - [8.5  对象的属性修改原子类](#85--%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%B1%9E%E6%80%A7%E4%BF%AE%E6%94%B9%E5%8E%9F%E5%AD%90%E7%B1%BB)
  - [8.6 原子操作增强类 | `LongAdder`的底层原理](#86-%E5%8E%9F%E5%AD%90%E6%93%8D%E4%BD%9C%E5%A2%9E%E5%BC%BA%E7%B1%BB--longadder%E7%9A%84%E5%BA%95%E5%B1%82%E5%8E%9F%E7%90%86)
      - [8.6.1 原子操作增强类简介](#861-%E5%8E%9F%E5%AD%90%E6%93%8D%E4%BD%9C%E5%A2%9E%E5%BC%BA%E7%B1%BB%E7%AE%80%E4%BB%8B)
      - [8.6.2 `LongAdder`类简介 | 使用方法](#862-longadder%E7%B1%BB%E7%AE%80%E4%BB%8B--%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95)
      - [8.6.3 `LongAdder`的底层原理](#863-longadder%E7%9A%84%E5%BA%95%E5%B1%82%E5%8E%9F%E7%90%86)
      - [8.6.4 `AtomicLong`和`LongAdder`的总结对比](#864-atomiclong%E5%92%8Clongadder%E7%9A%84%E6%80%BB%E7%BB%93%E5%AF%B9%E6%AF%94)
- [9. `ThreadLocal`](#9-threadlocal)
  - [9.1 `ThreadLocal`简介 | 使用方法](#91-threadlocal%E7%AE%80%E4%BB%8B--%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95)
  - [9.2 `ThreadLocal`原理 与 源码分析](#92-threadlocal%E5%8E%9F%E7%90%86-%E4%B8%8E-%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
  - [9.3  `ThreadLocal`内存泄露问题](#93--threadlocal%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2%E9%97%AE%E9%A2%98)
      - [9.3.1 内存泄露简介](#931-%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2%E7%AE%80%E4%BB%8B)
      - [9.3.2  `ThreadLocal`内存泄露](#932--threadlocal%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2)
      - [9.3.3   `ThreadLocalMap`的`key`采用弱引用的原因](#933---threadlocalmap%E7%9A%84key%E9%87%87%E7%94%A8%E5%BC%B1%E5%BC%95%E7%94%A8%E7%9A%84%E5%8E%9F%E5%9B%A0)
      - [9.3.4 `ThreadLocaMap`中`key`为`null`的坑](#934-threadlocamap%E4%B8%ADkey%E4%B8%BAnull%E7%9A%84%E5%9D%91)
  - [9.4  `ThreadLocal`总结 & 最佳实践](#94--threadlocal%E6%80%BB%E7%BB%93--%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5)
  - [9.5 补充：四大引用](#95-%E8%A1%A5%E5%85%85%E5%9B%9B%E5%A4%A7%E5%BC%95%E7%94%A8)
      - [9.5.1 四大引用简介](#951-%E5%9B%9B%E5%A4%A7%E5%BC%95%E7%94%A8%E7%AE%80%E4%BB%8B)
      - [9.5.2 强引用](#952-%E5%BC%BA%E5%BC%95%E7%94%A8)
      - [9.5.3 软引用](#953-%E8%BD%AF%E5%BC%95%E7%94%A8)
      - [9.5.4  弱引用](#954--%E5%BC%B1%E5%BC%95%E7%94%A8)
      - [9.5.5  虚引用](#955--%E8%99%9A%E5%BC%95%E7%94%A8)
      - [9.5.6  `GC Roots`  | 四大引用垃圾回收总结](#956--gc-roots---%E5%9B%9B%E5%A4%A7%E5%BC%95%E7%94%A8%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6%E6%80%BB%E7%BB%93)
- [10. `Synchronized` 与 锁升级](#10-synchronized-%E4%B8%8E-%E9%94%81%E5%8D%87%E7%BA%A7)
  - [10.1 `Java`对象内存布局](#101-java%E5%AF%B9%E8%B1%A1%E5%86%85%E5%AD%98%E5%B8%83%E5%B1%80)
      - [10.1.1 对象内存布局 | 对象标记 `Mark Word` | 类元信息 | 实例数据 | 对齐填充](#1011-%E5%AF%B9%E8%B1%A1%E5%86%85%E5%AD%98%E5%B8%83%E5%B1%80--%E5%AF%B9%E8%B1%A1%E6%A0%87%E8%AE%B0-mark-word--%E7%B1%BB%E5%85%83%E4%BF%A1%E6%81%AF--%E5%AE%9E%E4%BE%8B%E6%95%B0%E6%8D%AE--%E5%AF%B9%E9%BD%90%E5%A1%AB%E5%85%85)
      - [10.1.2 使用`JOL`分析对象内存布局](#1012-%E4%BD%BF%E7%94%A8jol%E5%88%86%E6%9E%90%E5%AF%B9%E8%B1%A1%E5%86%85%E5%AD%98%E5%B8%83%E5%B1%80)
      - [10.1.3 `GC`分代年龄 | 压缩指针](#1013-gc%E5%88%86%E4%BB%A3%E5%B9%B4%E9%BE%84--%E5%8E%8B%E7%BC%A9%E6%8C%87%E9%92%88)
      - [10.1.4 任意对象都可以充当锁](#1014-%E4%BB%BB%E6%84%8F%E5%AF%B9%E8%B1%A1%E9%83%BD%E5%8F%AF%E4%BB%A5%E5%85%85%E5%BD%93%E9%94%81)
  - [10.2 `synchronized`锁升级](#102-synchronized%E9%94%81%E5%8D%87%E7%BA%A7)
      - [10.2.1  `synchronized`锁升级简介](#1021--synchronized%E9%94%81%E5%8D%87%E7%BA%A7%E7%AE%80%E4%BB%8B)
      - [10.2.2 无锁](#1022-%E6%97%A0%E9%94%81)
      - [10.2.3 偏向锁](#1023-%E5%81%8F%E5%90%91%E9%94%81)
      - [10.2.4 轻量锁](#1024-%E8%BD%BB%E9%87%8F%E9%94%81)
      - [10.2.5 重量级锁](#1025-%E9%87%8D%E9%87%8F%E7%BA%A7%E9%94%81)
      - [10.2.6 锁升级 与 `hashcode`](#1026-%E9%94%81%E5%8D%87%E7%BA%A7-%E4%B8%8E-hashcode)
      - [10.2.7 总结](#1027-%E6%80%BB%E7%BB%93)
  - [10.3  `JIT`编译器对锁的优化](#103--jit%E7%BC%96%E8%AF%91%E5%99%A8%E5%AF%B9%E9%94%81%E7%9A%84%E4%BC%98%E5%8C%96)
      - [10.3.1 锁消除](#1031-%E9%94%81%E6%B6%88%E9%99%A4)
      - [10.3.2 锁粗化](#1032-%E9%94%81%E7%B2%97%E5%8C%96)
- [11. `AQS`](#11-aqs)
  - [11.1 `AQS`简介](#111-aqs%E7%AE%80%E4%BB%8B)
  - [11.2 `AQS`源码分析](#112-aqs%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [11.2.1` AQS`继承关系 | `AQS`源码注释说明](#1121-aqs%E7%BB%A7%E6%89%BF%E5%85%B3%E7%B3%BB--aqs%E6%BA%90%E7%A0%81%E6%B3%A8%E9%87%8A%E8%AF%B4%E6%98%8E)
      - [11.2.2  `AQS`的`state`变量 和 `Node`节点](#1122--aqs%E7%9A%84state%E5%8F%98%E9%87%8F-%E5%92%8C-node%E8%8A%82%E7%82%B9)
  - [11.3  `AQS`与`ReentrantLock()`](#113--aqs%E4%B8%8Ereentrantlock)
      - [11.3.1 `AQS` 与  `ReentrantLock()`的实现](#1131-aqs-%E4%B8%8E--reentrantlock%E7%9A%84%E5%AE%9E%E7%8E%B0)
      - [11.3.2  `ReentrantLock()`的公平锁和非公平锁](#1132--reentrantlock%E7%9A%84%E5%85%AC%E5%B9%B3%E9%94%81%E5%92%8C%E9%9D%9E%E5%85%AC%E5%B9%B3%E9%94%81)
      - [11.3.3  `ReentrantLock()`的`lock()`方法源码](#1133--reentrantlock%E7%9A%84lock%E6%96%B9%E6%B3%95%E6%BA%90%E7%A0%81)
      - [11.3.4  `ReentrantLock()`非公平锁`unlock()`方法源码](#1134--reentrantlock%E9%9D%9E%E5%85%AC%E5%B9%B3%E9%94%81unlock%E6%96%B9%E6%B3%95%E6%BA%90%E7%A0%81)
- [12. 读写锁`ReentrantReadWriteLock` | 邮戳锁`StampedLock`](#12-%E8%AF%BB%E5%86%99%E9%94%81reentrantreadwritelock--%E9%82%AE%E6%88%B3%E9%94%81stampedlock)
  - [12.1 读写锁`ReentrantReadWriteLock`](#121-%E8%AF%BB%E5%86%99%E9%94%81reentrantreadwritelock)
      - [12.1.1 读写锁简介](#1211-%E8%AF%BB%E5%86%99%E9%94%81%E7%AE%80%E4%BB%8B)
      - [12.1.2  锁降级](#1212--%E9%94%81%E9%99%8D%E7%BA%A7)
      - [12.1.3 `ReentrantReadWriteLock`官网使用演示](#1213-reentrantreadwritelock%E5%AE%98%E7%BD%91%E4%BD%BF%E7%94%A8%E6%BC%94%E7%A4%BA)
  - [13.5  邮戳锁`StampedLock`](#135--%E9%82%AE%E6%88%B3%E9%94%81stampedlock)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

`JUC`并发编程与源码分析

##  1. 线程基础知识

###  1.1 多线程相关概念

**并发vs并行**：

并发和并行都涉及到同时执行多个任务的能力，但有一些重要的区别。并发是指在同一时间段内，系统能够同时处理多个任务。这些任务可以是同时启动的，但并不一定同时完成。在并发中，多个任务可以交替执行，通过快速切换上下文来实现。并发通常用于提高系统的吞吐量和资源利用率，例如在多线程编程中，可以同时处理多个请求，提高系统的响应速度。并行是指在同一时间点上，系统能够同时执行多个任务。在并行中，多个任务可以同时进行，每个任务都有自己的处理器或计算资源。并行通常用于加速计算和提高系统的性能。例如，在分布式系统中，可以将一个任务分成多个子任务，分别在不同的计算节点上并行执行，以加快处理速度。并发和并行的区别在于任务的执行方式和时间点。并发是指同时处理多个任务，但任务之间可以交替执行；而并行是指同时执行多个任务，每个任务都有自己的计算资源。并发更关注于任务的调度和切换，而并行更关注于任务的同时执行和加速。在实际应用中，可以同时使用并发和并行来提高系统的性能。例如，在一个多核处理器上，可以使用并行来同时执行多个计算密集型任务，而使用并发来处理多个IO密集型任务。这样可以充分利用计算资源，提高系统的整体性能

并发(`concurrent`)：同一实体上的多个事件。一台处理器上“同时”处理多个任务，同一时刻其实是只有一个事件在发生

并行(`parallel`)：是在不同实体上的多个事件，是在多台处理器上同时处理多个任务，同一时刻有多个事件在发生



![image-20240117095512202](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142338431.png)

**进程 VS 线程 VS 管程**：

- **进程（`Process`）**：进程是计算机中正在运行的程序的实例。它是操作系统进行资源分配和调度的基本单位。每个进程都有自己的地址空间、代码、数据、文件等资源。进程之间是相互独立的，它们通过进程间通信（`IPC`）来进行数据换和协作。进程可以创建和销毁其他进程，并且可以并行执行
- **线程（`Thread`）**：线程是进程的一部分，是进程内的一个执行单元。一个进程可以包含多个线程，它们共享进程的资源。线程之间可以并发执行，共享同一进程的地址空间、文件和其他资源。线程的创建和销毁消耗的资源较少，线程的切换开销也较小，因此线程的开销比进程小，线程的并发效率比进程高
- **管程（`Monitor`）**：管程是一种同步机制，用于解决多个进程或线程之间的互斥和同步问题。它提供了一种结构化的编程方式，封装了共享资源的访问和操作。管程中的操作只能由一个进程或线程执行，其他进程或线程必须等待。管程通过条件变量（`Condition Variable`）来实现进程或线程之间的通信和等待。管程的使用可以简化并发编程的复杂性，提高程序的可靠性和可维护性
- **总结**：进程、线程和管程是操作系统中的重要概念，它们都是用于实现并发执行的机制，但在功能和实现方式上有所不同。进程是操作系统进行资源分配和调度的基本单位，线程是进程内的执行单元，管程是一种同步机制。进程之间相互独立，线程共享进程的资源，管程用于解决多进程或多线程之间的互斥和同步问题。进程和线程都可以并发执行，但线程的开销较小，效率较高。管程提供了一种结构化的编程方式，简化了并发编程的复杂性

**管程（`Monitor`）**：

```
管程（Monitor）是一种同步机制，用于解决多个进程或线程之间的互斥和同步问题。它提供了一种结构化的编程方式，封装了共享资源的访问和操作。

管程由以下几个组成部分构成：
1. 共享数据：管程中包含一些共享的数据结构或变量，多个进程或线程可以通过管程来访问和修改这些共享数据。
2. 进程或线程的入口点：管程中定义了一些操作或方法，用于进程或线程访问和操作共享数据。这些操作通常是原子的，即在执行过程中不会被其他进程或线程中断。
3. 条件变量（Condition Variable）：管程中可以定义一些条件变量，用于进程或线程之间的通信和等待。条件变量供了一种机制，使得进程或线程可以等待某个条件满足后再继续执行

管程的特点和优势包括：
1. 互斥性：管程中的操作一次只能由一个进程或线程执行，其他进程或线程必须等待。这样可以确保共享资源互斥访问，避免了竞态条件和数据不一致的问题。
2. 同步性：管程的条件变量可以用于进程或线程之间的通信和等待。进程或线程可以通过条件变量等待某个条件满足后再继续执行，从而实现了进程或线程的步。
3. 结构化编程：管程提供了一种结构化的编程方式，将共享资源的访问和操作封装在一起。这样可以简化并发编程的复杂性，提高程序的可靠性和可维护性。

需要注意的是，管程是一种抽象概念，实际上需要通过编程语言或操作系统提供的库或机制来实现。不同的编程语言或操作系统可能有不同的管程实现方式和特性。
```

Monitor(监视器)，也就是平时所说的锁。Monitor其实是一种同步机制，他的义务是保证（同一时间）只有一个线程可以访问被保护的数据和代码。JVM中同步是基于进入和退出监视器对象(Monitor,管程对象)来实现的，每个对象实例都会有一个Monitor对象，Monitor对象会和Java对象一同创建并销毁，它底层是由C++语言来实现的

```java
Object o = new Object();
new Thread(() -> {
      synchronized (o){
      }
}, "t1" ).start();
```

JVM第3版有关管程的描述：

>同步指令
>
>Java虚拟机可以支持方法级的同步和方法内部一段指令序列的同步，这两种同步结构都是使用管程(Monitor，更常见的是直接将它称为“锁”）来实现的
>
>方法级的同步是隐式的，无须通过字节码指令来控制，它实现在方法调用和返回操作之中。虚拟机可以从方法常量池中的方法表结构中的`ACC_SYNCHRONIZED`访问标志得知一个方法是否被声明为同步方法。当方法调用时，调用指令将会检查方法的`ACC_SYNCHRONIZED`访问标志是否被设置，如果设置了，执行线程就要求先成功持有管程，然后才能执行方法，最后当方法完成(无论是正常完成还是非正常完成）时释放管程。在方法执行期间，执行线程持有了管程，其他任何线程都无法再获取到同一个管程。如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的管程将在异常抛到同步方法边界之外时自动释放
>
>同步一段指令集序列通常是由Java语言中的synchronized语句块来表示的，Java虚拟机的指令集中有monitorenter和monitorexit两条指令来支持synchronized关键字的语义，正确实现synchronized关键字需要Javac编译器与Java虚拟机两者共同协作支持



**`synchronized`关键字**：

`synchronized`用于实现线程之间的同步。它可以用于修饰方法或代码块，以确保在同一时间只有一个线程可以访问被`synchronized`修饰的代码段。当一个线程进入一个被`synchronized`修饰的方法或代码块时，它会自动获得该方法或代码块的锁。其他线程在尝试进入相同的方法或代码块时，会被阻塞，直到当前线程释放锁。这样可以确保在任意时刻只有一个线程执行被`synchronized`修饰的代码段，从而避免了多个线程同时访问共享资源而导致的数据不一致和竞争条件的问题

`synchronized`关键字有两种使用方式：对于方法的修饰，可以将`synchronized`关键字直接加在方法的声明上，表示整个方法是一个同步方法；对于代码块的修饰，可以使用`synchronized`关键字加在代码块的前面，使用一对括号指定需要同步的对象或类。

使用synchronized关键字可以有效地解决线程安全问题，但它也有一些限制。首先，`synchronized`关键字会影响性能，因为它需要进行锁的获取和释放操作，可能会引起线程的阻塞。其次，`synchronized`只能对同一个对象或类进行同步，无法对不同对象或类进行同步。为了解决这个问题，Java提供了更灵活的锁机制，如`ReentrantLock`和`Condition`

总结起来，`synchronized`是`Java`中用于实现线程同步的关键字，通过加锁机制确保在同一时间只有一个线程可以访问被`synchronized`修饰的代码段。它是一种简单且常用的同步机制，但在性能和灵活性方面存在一些限制

**用户线程 VS 守护线程**：

Java线程分为用户线程和守护线程。一般情况下不做特别说明配置,默认都是用户线程。用户线程(`User Thread`)是系统的工作线程，它会完成这个程序需要完成的业务操作。守护线程(`Daemon Thread`)是一种特殊的线程为其它线程服务的，在后台默默地完成一些系统性的服务，比如垃圾回收线程就是最典型的例子。守护线程作为一个服务线程，没有服务对象就没有必要继续运行了，如果用户线程全部结束了，意味着程序需要完成的业务操作已经结束了，系统可以退出了。所以假如当系统只剩下守护线程的时候，`java`虚拟机会自动退出。`Thread`类的`daemon`属性用于标识线程是否是守护线程

`isDaemon`方法：判断线程是否是守护线程

```java
// true表示是守护线程
// false表示是用户线程
    /**
     * Tests if this thread is a daemon thread.
     *
     * @return  <code>true</code> if this thread is a daemon thread;
     *          <code>false</code> otherwise.
     * @see     #setDaemon(boolean)
     */
    public final boolean isDaemon() {
        return daemon;
    }
```

守护线程演示：如果用户线程全部结束意味着程序需要完成的业务操作已经结束了，守护线程会随着`JVM`一同结束工作

```java
import java.util.concurrent.*;
public class DaemonDemo
{
    public static void main(String[] args)//一切方法运行的入口
    {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 开始运行, "+
                    (Thread.currentThread().isDaemon() ? "守护线程":"用户线程"));
            while(true)
            {

            }
        },"t1");
        t1.setDaemon(true);
        t1.start();

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName()+"\t ----end 主线程");
    }
}
// 补充: setDaemon(true)方法必须在start()之前设置，否则报IllegalThreadStateException异常
// 执行结果：
//            t1	 开始运行, 守护线程
//            main	 ----end 主线程
```

###  1.2 `JUC` | `native`关键字 | `JNI` | `openJdk`

本节前置知识汇总：`JUC`概念、`native`关键字、`JNI`

**`JUC`**：`JUC`是 `java.util.concurrent`简称，是java并发编程中使用的工具包

**并发编程的意义**：摩尔定律由英特尔创始人之一`Gordon Moore`(戈登·摩尔)提出来的。其内容为：当价格不变时，集成电路上可容纳的元器件的数目约每隔18-24个月便会增加一倍，性能也将提升一倍。换言之，每一美元所能买到的电脑性能，将每隔18-24个月翻一倍以上。这一定律揭示了信息技术进步的速度。可是从2003年开始`CPU`主频已经不再翻倍，而是采用多核而不是更快的主频，摩尔定律失效了。在主频不再提高且核数在不断增加的情况下，要想让程序更快就要用到并行或并发编程

**`native` 关键字**：主要用于表示一个方法不是用 `Java` 语言实现的，而是由本地代码（通常是由其他语言如 C 或 C++ 编写）实现的。当一个方法被声明为 `native` 时，它将不会有实际的实现体，而是通过本地接口与其他语言编写的方法产生联系。一些常见的用例包括与操作系统交互、使用硬件资源或者在 Java 中调用其他语言（如 C 或 C++）开发的库。通常情况下，在定义一个 `native` 方法后，需要通过` JNI（Java Native Interface）`来编写本地方法的实现。以下是一个简单的示例代码来展示 `native` 关键字的使用：

```java
// nativeMethod是一个native方法，它没有具体的实现，而是通过本地库 "NativeLibrary" 中的方法来实现
// 要使用该方法，需要加载本地库
public class NativeExample {
    public native void nativeMethod();
    static {
        System.loadLibrary("NativeLibrary"); // 加载本地库
    }
}
```

**`JNI`**：`JNI`（`Java Native Interface`）是一种允许Java代码与本地（Native）代码（如C、C++）相互交互的技术

- 使用目的：允许Java应用程序与本地代码进行交互。通过JNI，Java代码可以调用本地方法或本地代码

- 使用场景：当需要调用现有的本地库（如C或C++库）、当需要使用本地性能优化来改善Java应用程序的性能、当需要与特定平台上的本地系统功能进行交互，如调用操作系统级别的功能等

- 工作原理：Java类中的native方法通过JNI被映射到本地代码函数。本地代码会被编写成符合JNI规范的方式，然后通过JNI函数进行加载和调用

- 编写流程：

  - 编写包含native方法声明的Java类

  - 使用Java的javah工具生成C/C++头文件，其中包含了native方法的声明

  - 编写C/C++源代码，实现native方法

  - 使用本地编译器编译C/C++源代码并生成共享库（通常是动态链接库.so或.dll文件）

  - 在Java中加载并调用本地库函数

- 注意事项：

  - JNI开发需要小心处理内存管理和类型转换，包括错误检查和异常处理

  - JNI的性能可能会受到影响，因为它涉及了Java和本地代码的切换

**底层的`OpenJDK`源码**：

```
OpenJDK源码网址：              http://openjdk.java.net
OpenJDK java8源码网址：        https://hg.openjdk.org/jdk8
OpenJDK java8源码gitee仓库：   https://gitee.com/chen0218/openjdk8
源码位置：                     openjdk8\hotspot\src\share\vm\runtime 目录下包含C++代码实现
openjdk写的JNI文件和JUC源码的文件名称一般是一一对应的，比如Thread.java对应的就是Thread.c
```

###  1.3 Thread类start方法底层源码

**start方法**：Thread类的start()方法用于启动线程的执行。调用start()方法将导致新线程执行run()方法中的代码。start()方法的主要作用是启动一个新的线程，并且调用该线程的run()方法。一旦start()方法被调用，线程会被放入就绪队列中，当得到CPU时间片后，就开始执行run()方法中的代码。需要注意的是，不应该直接调用run()方法来启动线程，因为这样不会以新线程的方式执行代码，仍然会在当前线程中执行。因此，应该始终通过调用start()方法来启动一个新的线程。start()方法示例代码：

```java
// 使用Thread类的start()方法来启动一个新线程
public class ThreadBaseDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
        }, "t1");
        t1.start();

    }
}
```

`start()`方法源码：`start()`方法调用了`start0()`方法，`start0()`方法是一个`native`关键字修饰的方法，调用了更加底层的C++源码

```java
   public synchronized void start() {
        /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
        if (threadStatus != 0)
            throw new IllegalThreadStateException();

        /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
        group.add(this);

        boolean started = false;
        try {
            start0();
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
                /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
            }
        }
    }
    // 调用更加底层的C++源码
    private native void start0();
```

`start0()`底层的C++源码`Thread.c`：

```c
// 源码位置：https://hg.openjdk.org/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/native/java/lang/Thread.c
static JNINativeMethod methods[] = {
    {"start0",           "()V",        (void *)&JVM_StartThread},
    {"stop0",            "(" OBJ ")V", (void *)&JVM_StopThread},
    {"isAlive",          "()Z",        (void *)&JVM_IsThreadAlive},
    {"suspend0",         "()V",        (void *)&JVM_SuspendThread},
    {"resume0",          "()V",        (void *)&JVM_ResumeThread},
    {"setPriority0",     "(I)V",       (void *)&JVM_SetThreadPriority},
    {"yield",            "()V",        (void *)&JVM_Yield},
    {"sleep",            "(J)V",       (void *)&JVM_Sleep},
    {"currentThread",    "()" THD,     (void *)&JVM_CurrentThread},
    {"countStackFrames", "()I",        (void *)&JVM_CountStackFrames},
    {"interrupt0",       "()V",        (void *)&JVM_Interrupt},
    {"isInterrupted",    "(Z)Z",       (void *)&JVM_IsInterrupted},
    {"holdsLock",        "(" OBJ ")Z", (void *)&JVM_HoldsLock},
    {"getThreads",        "()[" THD,   (void *)&JVM_GetAllThreads},
    {"dumpThreads",      "([" THD ")[[" STE, (void *)&JVM_DumpThreads},
    {"setNativeName",    "(" STR ")V", (void *)&JVM_SetNativeThreadName},
};

源码解读:
Thread.c中定义了一个包含 JNI（Java Native Interface）方法的数组
Thread.c中定义了一个静态的JNINativeMethod数组，数组名为methods
数组的每个元素都是一个JNINativeMethod结构体，包含了方法名、方法签名和方法指针
JNINativeMethod结构体的定义如下：
typedef struct {
const char* name; // 方法名
const char* signature; // 方法签名
void* fnPtr; // 方法指针
} JNINativeMethod;

methods数组中的每个元素都对应着Java中Thread类的本地方法的实现
每个数组元素的name字段表示方法名，signature字段表示方法签名，fnPtr字段表示方法的实现指针
这些本地方法的实现都在这个源码文件中定义，可以通过方法指针调用具体的本地方法
这些本地方法实现了Thread类的一些基本功能，比如线程的启动、停止、挂起、恢复、设置优先级等
这些方法将在Java代码中被调用，然后通过JNI机制调用对应的本地方法实现
    
- "start0" 对应的是 JVM_StartThread 方法，它接受无参数并返回 void
```

JVM中`JVM_StartThread`的源码：此段源码是OpenJDK中的`JVM_StartThread`函数，负责启动一个新的线程

```c++
// 源码位置：https://hg.openjdk.org/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/prims/jvm.cpp
JVM_ENTRY(void, JVM_StartThread(JNIEnv* env, jobject jthread))
  JVMWrapper("JVM_StartThread");
  JavaThread *native_thread = NULL;

  // We cannot hold the Threads_lock when we throw an exception,
  // due to rank ordering issues. Example:  we might need to grab the
  // Heap_lock while we construct the exception.
  bool throw_illegal_thread_state = false;

  // We must release the Threads_lock before we can post a jvmti event
  // in Thread::start.
  {
    // Ensure that the C++ Thread and OSThread structures aren't freed before
    // we operate.
    MutexLocker mu(Threads_lock);

    // Since JDK 5 the java.lang.Thread threadStatus is used to prevent
    // re-starting an already started thread, so we should usually find
    // that the JavaThread is null. However for a JNI attached thread
    // there is a small window between the Thread object being created
    // (with its JavaThread set) and the update to its threadStatus, so we
    // have to check for this
    if (java_lang_Thread::thread(JNIHandles::resolve_non_null(jthread)) != NULL) {
      throw_illegal_thread_state = true;
    } else {
      // We could also check the stillborn flag to see if this thread was already stopped, but
      // for historical reasons we let the thread detect that itself when it starts running

      jlong size =
             java_lang_Thread::stackSize(JNIHandles::resolve_non_null(jthread));
      // Allocate the C++ Thread structure and create the native thread.  The
      // stack size retrieved from java is signed, but the constructor takes
      // size_t (an unsigned type), so avoid passing negative values which would
      // result in really large stacks.
      size_t sz = size > 0 ? (size_t) size : 0;
      native_thread = new JavaThread(&thread_entry, sz);

      // At this point it may be possible that no osthread was created for the
      // JavaThread due to lack of memory. Check for this situation and throw
      // an exception if necessary. Eventually we may want to change this so
      // that we only grab the lock if the thread was created successfully -
      // then we can also do this check and throw the exception in the
      // JavaThread constructor.
      if (native_thread->osthread() != NULL) {
        // Note: the current thread is not being used within "prepare".
        native_thread->prepare(jthread);
      }
    }
  }

  if (throw_illegal_thread_state) {
    THROW(vmSymbols::java_lang_IllegalThreadStateException());
  }

  assert(native_thread != NULL, "Starting null thread?");

  if (native_thread->osthread() == NULL) {
    // No one should hold a reference to the 'native_thread'.
    delete native_thread;
    if (JvmtiExport::should_post_resource_exhausted()) {
      JvmtiExport::post_resource_exhausted(
        JVMTI_RESOURCE_EXHAUSTED_OOM_ERROR | JVMTI_RESOURCE_EXHAUSTED_THREADS,
        "unable to create new native thread");
    }
    THROW_MSG(vmSymbols::java_lang_OutOfMemoryError(),
              "unable to create new native thread");
  }

  Thread::start(native_thread);

JVM_END
    
源码解读：
此段源码是OpenJDK中的JVM_StartThread函数，负责启动一个新的线程
该函数中，首先检查线程是否已经处于运行状态，如果没有运行则创建一个JavaThread对象并分配原生线程
在创建线程时，还要考虑内存不足的情况，如果不能创建成功则会抛出OutOfMemoryError异常
最后调用Thread::start函数启动新线程。
源码中包含的关键步骤：
1. 检查线程是否已经运行，如果没有运行则创建JavaThread对象。
2. 分配原生线程，并检查内存是否足够。
3. 如果不能创建原生线程则抛出异常。
4. 最终调用Thread::start函数启动线程。
在函数中还有一些多线程操作的考量，例如抛出异常时不持有Threads_lock，以及在确定是否能正常创建原生线程时的考虑，负责确保线程的顺利启动
```

`Thread::start`函数：

```c++
// openjdk8\hotspot\src\share\vm\runtime\thread.cpp
// 源码位置：https://hg.openjdk.org/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/runtime/thread.cpp
void Thread::start(Thread* thread) {
  trace("start", thread);
  // Start is different from resume in that its safety is guaranteed by context or
  // being called from a Java method synchronized on the Thread object.
  if (!DisableStartThread) {
    if (thread->is_Java_thread()) {
      // Initialize the thread state to RUNNABLE before starting this thread.
      // Can not set it after the thread started because we do not know the
      // exact thread state at that time. It could be in MONITOR_WAIT or
      // in SLEEPING or some other state.
      java_lang_Thread::set_thread_status(((JavaThread*)thread)->threadObj(),
                                          java_lang_Thread::RUNNABLE);
    }
    os::start_thread(thread);
  }
}

OpenJDK中的Thread::start函数的实现,函数用于启动一个线程
首先，函数调用trace函数，将"start"和thread作为参数进行跟踪。
然后，函数检查DisableStartThread标志。如果该标志为false，则进入下面的代码块。
在代码块中，函数检查传入的thread参数是否为Java线程。如果是Java线程，则调用java_lang_Thread::set_thread_status函数将线程状态设置为RUNNABLE。
最后，函数调用os::start_thread函数启动线程。
```





##  2. Future接口 | Future接口实现类

###  2.1 Future接口

**`Future`接口**：Future接口是Java中用于表示一个异步计算结果的接口，它提供了一种方式来检查任务是否已经完成、获取任务的结果以及取消任务的执行

**`Future`接口作用**：`Future`接口常用于提交异步任务，并在后续需要获取任务结果的时候使用。通过`Future`接口的`get`方法可以实现阻塞等待任务结果的功能，或者通过`isDone`方法进行轮询判断任务是否完成。同时，也可以通过`cancel`方法取消任务的执行

**`Future`接口的实现类**：包括`FutureTask`、`CompletableFuture`等。这些实现类提供了不同的特性和使用方式来适应不同的需求

**`Future`接口的主要方法**：

1. `boolean isDone()`: 该方法用于判断任务是否已经完成。如果任务已经完成，即任务执行结束或被取消，则返回true，否则返回false

2. `boolean cancel(boolean mayInterruptIfRunning)`: 该方法用于取消任务的执行。参数`mayInterruptIfRunning`表示是否中断正在执行的任务。如果任务正在运行并且设置为中断，则会尝试中断任务。如果任务已经完成或被取消，则该方法无效。取消成功返回true，否则返回false

3. `boolean isCancelled()`: 该方法用于判断任务是否被取消。如果任务在执行过程中被取消，则返回true，否则返回false

4. `V get() throws InterruptedException, ExecutionException`: 该方法用于获取异步计算的结果。如果任务已经完成，即任务执行结束，则立即返回结果。如果任务还没有完成，则会阻塞直到任务完成，并返回结果。如果任务被取消，则会抛出`CancellationException`异常。如果任务执行过程中出现异常，则会抛出`ExecutionException`异常。如果线程在等待过程中被中断，则会抛出`InterruptedException`异常

5. `V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException`: 该方法用于获取异步计算的结果，但是设置了等待的最大时间。参数`timeout`表示最大等待时间，`unit`表示时间单位。如果任务在指定的时间内完成，则立即返回结果。如果超过指定时间仍未完成，则会抛出`TimeoutException`异常

**`Future`接口定义**：

```java
package java.util.concurrent;
public interface Future<V> {

    boolean isCancelled();

    boolean isDone();

    V get() throws InterruptedException, ExecutionException;

    V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}
```

###  2.2  `FutureTask`类

#####  2.2.1  `FutureTask`类简介

 **`FutureTask`类**：`FutureTask`类实现了`Future`接口，用于表示一个可以异步执行的任务，并可以获取任务的结果。`FutureTask`可以在多线程的环境中使用，包括使用`Executors`框架提交任务和使用`Thread`直接启动任务。`FutureTask`的实现主要使用了`ReentrantLock`互斥锁和`Condition`条件变量来实现任务的等待和通知机制，以及`Synchronized`关键字来保证对结果的原子操作。`FutureTask`类目的：异步多线程任务执行且返回有结果。`FutureTask`类三个特点：多线程、有返回、异步任务

**`FutureTask`类有如下特性**：

- 异步执行：`FutureTask`可以用来表示一个可以异步执行的任务。通过调用`FutureTask`的`run`方法，任务将会在后台线程中执行，并返回一个结果。任务的执行是非阻塞的，即可以让调用者继续执行其他操作而不必等待任务的完成
- 获取线程执行结果：通过调用`FutureTask`的`get`方法，可以获取任务的结果。如果任务还没有完成，`get`方法会阻塞直到任务完成并返回结果。如果任务已经完成，`get`方法将立即返回结果。可以通过传递超时参数来设置最大等待时间
- 取消任务：通过调用`FutureTask`的`cancel`方法，可以取消任务的执行。任务正在执行的情况下，`cancel`方法将尝试中断任务。如果任务已经完成或被取消，则`cancel`方法返回false

**FutureTask类架构**：

![image-20240117165052133](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142338846.png)







**FutureTask类的构造方法**：`FutureTask`类的构造方法主要用于创建`FutureTask`对象，用于表示一个异步执行的任务。`FutureTask`类有两个主要的构造方法，一个是用`Callable`对象表示异步任务，另一个是将`Runnable`对象转换为具有返回值的任务：

1. `FutureTask(Callable<V> callable)`。此构造方法接受一个`Callable`对象作为参数，用于表示要执行的任务。`Callable`是一个接口，代表一个可以返回结果或抛出异常的任务。当`FutureTask`被创建后，它会将`Callable`提供的任务作为异步任务进行执行。示例代码：

   ```java
   Callable<Integer> callable = () -> {
       // 执行一些耗时的任务并返回结果
       return 42;
   };
   FutureTask<Integer> futureTask = new FutureTask<>(callable);
   ```

2. `FutureTask(Runnable runnable, V result)`。此构造方法接受一个`Runnable`对象以及一个泛型参数`result`作为参数。`Runnable`接口代表一个没有返回值的任务，通过该构造方法创建的`FutureTask`会将`Runnable`对象包装成一个具有返回值的任务，`result`参数代表`Runnable`执行完成后的返回值，可以通过`get`方法获取。示例代码：

   ```java
   Runnable runnable = () -> {
       // 执行一些任务
   };
   FutureTask<Void> futureTask = new FutureTask<>(runnable, null);
   ```

#####  2.2.2 `FutureTask`代码演示

**`FutureTask`示例代码一**：如果主线程需要执行一个很耗时的计算任务，就可以通过future把这个任务放到异步线程中执主线程继续处理其他任务或者先行结束，再通过Future获取计算结果

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(futureTask.get());
    }
}
class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("-----come in call() ");
        return "hello Callable";
    }
}
/* 程序输出：
            -----come in call() 
            hello Callable
 */
```



**`FutureTask`示例代码二**：创建了一个`Callable`任务，该任务会返回整数42。将这个任务传递给`FutureTask`构造函数，然后使用线程池执行`FutureTask`任务。主线程继续执行其他操作，最后通过`get`方法获取任务的结果并输出

```java
import java.util.concurrent.*;
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建一个Callable任务
        Callable<Integer> callable = () -> {
            // 模拟耗时操作
            Thread.sleep(1000);
            return 42;
        };

        // 创建FutureTask，并将Callable任务作为参数传入
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        // 使用线程池提交FutureTask任务
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(futureTask);

        // 主线程继续执行其他操作
        System.out.println("Do something in main thread...");

        // 获取任务的结果，如果任务还没有完成，get方法会阻塞直到任务完成并返回结果
        int result = futureTask.get();

        // 输出任务的结果
        System.out.println("Result: " + result);

        // 关闭线程池
        executor.shutdown();
    }
}
```



**`FutureTask`示例代码三**：future+线程池异步多线程任务配合，能显著提高程序的执行效率

```java
import java.util.concurrent.*;
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //3个任务，目前开启多个异步任务线程来处理，请问耗时多少？
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        });
        threadPool.submit(futureTask2);
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");
        System.out.println(Thread.currentThread().getName() + "\t -----end");
        threadPool.shutdown();
/* 程序执行输出：
        task1 over
        task2 over
        ----costTime: 849 毫秒
        main	 -----end
 */
    }

    private static void m1() {
        //3个任务，目前只有一个线程main来处理，请问耗时多少？
        long startTime = System.currentTimeMillis();
        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");
        System.out.println(Thread.currentThread().getName() + "\t -----end");
    }
}
```

#####  2.2.3  `FutureTask`类的缺点

**FutureTask的缺点**：`FutureTask` 作为 `Future` 和 `Runnable` 的实现类，用于表示异步计算的结果。虽然 `FutureTask` 在许多情况下都非常有用，但它也有一些缺点。FutureTask的缺点如下：

- 阻塞: 当调用 `get` 方法获取结果时，如果计算尚未完成，`FutureTask` 会阻塞直到计算完成。这可能会导致程序出现性能问题，特别是在处理大量任务时
- 非可取消性: 一旦 `FutureTask` 开始执行，就无法取消。尽管可以通过 `cancel` 方法尝试取消任务，但这种取消只能在任务尚未开始或者已经完成时才会成功。在任务执行过程中取消任务可能会导致问题
- 异常处理复杂: `FutureTask` 在处理任务执行过程中的异常时有些复杂。在执行过程中出现的异常不会立即传播到调用线程，而是会被包装在 `FutureTask` 中，需要显式调用 `get` 方法才能抛出异常
- 只能执行一次: 一旦 `FutureTask` 完成一次计算，就不能重新启动。如果需要执行多次，就需要创建新的 `FutureTask` 实例



**`FutureTask`容易导致程序阻塞**: `FutureTask`的get方法的调用容易导致阻塞，一般建议放在程序后面，一旦调用get方法，不管是否计算完成，非要等到结果才会离开，容易导致程序堵塞。`FutureTask`的`get`方法是用于获取由`FutureTask`执行的任务的结果。其声明如下：

```java
V get() throws InterruptedException, ExecutionException
```

这里，`V` 表示任务执行的结果类型。调用`get`方法会导致当前线程阻塞，直到任务执行完成并且返回结果。如果任务已经完成，`get`方法会立即返回结果；如果任务还未完成，当前线程将等待直到任务完成为止。如果任务执行过程中发生了异常，`get`方法会抛出 `ExecutionException`，以便通知调用者任务执行失败，并携带底层异常信息。同时，如果在等待过程中被其他线程中断，`get`方法也会抛出`InterruptedException`。在实际使用时，通常会结合 `isDone()` 方法来判断任务是否已经完成，或者使用带有超时参数的 `get` 方法来避免长时间的阻塞

示例代码: `FutureTask`的get方法容易导致程序阻塞

```java
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * 1 get容易导致阻塞，一般建议放在程序后面，一旦调用get方法，非要等到结果才会离开，不管是否计算完成，容易程序堵塞
 * 2 使用带有超时参数的get方法来避免长时间的阻塞：futureTask.get(3,TimeUnit.SECONDS)
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");
//        System.out.println(futureTask.get());
        System.out.println(futureTask.get(3, TimeUnit.SECONDS));
    }
/*程序执行输出：
            main	 ----忙其它任务了
            t1	 -----come in
            Exception in thread "main" java.util.concurrent.TimeoutException
                at java.util.concurrent.FutureTask.get(FutureTask.java:205)
                at com.bilibili.juc.cf.FutureAPIDemo.main(FutureAPIDemo.java:27)
 */
}
```

**`FutureTask`轮询容易CPU空转**: 可以使用`isDone()`方法结合`get()`方法的重载版本`get(long timeout, TimeUnit unit)`来避免`get`方法导致阻塞。通过使用`isDone()`方法可以首先检查任务是否已经完成，然后在调用`get(long timeout, TimeUnit unit)`方法时，设置一个超时时间，以防止任务永久阻塞。这样做可以在任务完成之前的指定时间内阻止`get`方法导致的无限期阻塞情况。使用轮询的方式来防止`FutureTask`阻塞的发生，通过isDone方法来判断任务是否执行完成，但是轮询的方式会耗费无谓的CPU资源，而且也不见得能及时地得到计算结果。如果想要异步获取结果,通常都会以轮询的方式去获取结果,尽量不要阻塞。结论：`FutureTask`对于结果的获取不是很友好，只能通过阻塞或轮询的方式得到任务的结果

```java
public class FutureAPIDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");
        // 不断的轮询,使用isDone方法来判断任务是否执行完成.缺点:不断地轮询会导致CPU空转
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                //暂停毫秒
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中，不要再催了，越催越慢 ，再催熄火");
            }
        }
    }
/*程序执行输出：
            main	 ----忙其它任务了
            t1	 -----come in
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            task over
 */
}
```

**`FutureTask`的其他缺点**：

```
1.回调通知机制不完善：
         应对Future的完成时间，完成了可以告诉我，也就是回调通知
         通过轮询的方式去判断任务是否完成这样非常占CPU并且代码也不优雅
2.无法实现计算速度选最快：
         当Future集合中某个任务最快结束时，返回结果，返回第一名处理结果
3.无法实现多个任务前后依赖可以组合处理
       想将多个异步任务的计算结果组合起来，后一个异步任务的计算结果需要前一个异步任务的值
       将两个或多个异步计算合成一个异步计算，这几个异步计算互相独立，同时后面这个又依赖前一个处理的结果
4.创建异步任务：Future+线程池配合
```

`CompletableFuture`以声明式的方式优雅的处理这些需求。`FutureTask`能干的，`CompletableFuture`都能干

###  2.3 CompletableFuture

#####  2.3.1  CompletableFuture类简介

**`CompletableFuture`相对于`FutureTask`的改进**：get()方法在Future计算完成之前会一直处在阻塞状态下，isDone()方法容易耗费CPU资源。对于真正的异步处理我们希望是可以通过传入回调函数，在Future结束时自动调用该回调函数，这样，我们就不用等待结果。阻塞的方式和异步编程的设计理念相违背，而轮询的方式会耗费无谓的CPU资源。因此，JDK8设计出`CompletableFuture`。`CompletableFuture`提供了一种与观察者模式类似的机制，可以让任务执行完成后通知监听的一方

**`CompletableFuture`简介**：从Java8开始引入了CompletableFuture，它是Future的功能增强版，减少阻塞和轮询，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。`CompletableFuture`提供了非常强大的Future的扩展功能，可以简化异步编程的复杂性，并且提供了函数式编程的能力，可以通过回调的方式处理计算结果，也提供了转换和组合`CompletableFuture`的方法。`CompletableFuture`实现了`Future`和`CompletionStaqe`接口，`CompletableFuture`可能代表一个明确完成的Future，也有可能代表一个完成阶段(`CompletionStage`)，它支持在计算完成以后触发一些函数或执行某些动作

**`CompletableFuture`的优点**：异步任务结束时，会自动回调某个对象的方法。主线程设置好回调后，不再关心异步任务的执行，异步任务之间可以顺序执行异步任务出错时，会自动回调某个对象的方法

 **`CompletableFuture`代码演示**：`CompletableFuture`不用阻塞等待也可以获取异步线程的执行结果

```java
import java.util.concurrent.*;
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "----come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-----1秒钟后出结果：" + result);
                if (result > 2) {
                    int i = 10 / 0;
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("-----计算完成，更新系统UpdateValue：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
/* 执行输出：
            pool-1-thread-1----come in
            main线程先去忙其它任务
            -----1秒钟后出结果：0
            -----计算完成，更新系统UpdateValue：0
 */
}
```

 **`CompletableFuture`核心方法**：

```java
// runAsync无返回值
public static CompletableFuture<Void> runAsync(Runnable runnable)
public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
// supplyAsync有返回值
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,Executor executor) 
```

**`Executor executor`参数说明**：没有指定Executor的方法会直接使用默认的`ForkJoinPool.commonPool()`作为它的线程池执行异步代码。如果指定线程池，则使用自定义的或者特别指定的线程池执行异步代码

```java
public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        });
        System.out.println(completableFuture.get());
    }
}
// 示例一
public class CompletableFutureBuildDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPool);
        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }
/* 执行结果：
            pool-1-thread-1
            null
 */
}


// 示例二
public class CompletableFutureBuildDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        });
        System.out.println(completableFuture.get());
    }
        /* 程序输出：
        ForkJoinPool.commonPool-worker-9
        hello supplyAsync
         */
}

// 示例三
public class CompletableFutureBuildDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        }, threadPool);
        System.out.println(completableFuture.get());

        threadPool.shutdown();
    }
        /* 程序输出：
                pool-1-thread-1
                hello supplyAsync
         */
}
```

**`CompletableFuture`类的定义**：

```java
public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
    /*
     * Overview:
     *
     * A CompletableFuture may have dependent completion actions,
     * collected in a linked stack. It atomically completes by CASing
     * a result field, and then pops off and runs those actions. This
     * applies across normal vs exceptional outcomes, sync vs async
     * actions, binary triggers, and various forms of completions.
     *
     * Non-nullness of field result (set via CAS) indicates done.  An
     * AltResult is used to box null as a result, as well as to hold
     * exceptions.  Using a single field makes completion simple to
     * detect and trigger.  Encoding and decoding is straightforward
     * but adds to the sprawl of trapping and associating exceptions
     * with targets.  Minor simplifications rely on (static) NIL (to
     * box null results) being the only AltResult with a null
     * exception field, so we don't usually need explicit comparisons.
     * Even though some of the generics casts are unchecked (see
     * SuppressWarnings annotations), they are placed to be
     * appropriate even if checked.
     *
     * Dependent actions are represented by Completion objects linked
     * as Treiber stacks headed by field "stack". There are Completion
     * classes for each kind of action, grouped into single-input
     * (UniCompletion), two-input (BiCompletion), projected
     * (BiCompletions using either (not both) of two inputs), shared
     * (CoCompletion, used by the second of two sources), zero-input
     * source actions, and Signallers that unblock waiters. Class
     * Completion extends ForkJoinTask to enable async execution
     * (adding no space overhead because we exploit its "tag" methods
     * to maintain claims). It is also declared as Runnable to allow
     * usage with arbitrary executors.
     *
     * Support for each kind of CompletionStage relies on a separate
     * class, along with two CompletableFuture methods:
     *
     * * A Completion class with name X corresponding to function,
     *   prefaced with "Uni", "Bi", or "Or". Each class contains
     *   fields for source(s), actions, and dependent. They are
     *   boringly similar, differing from others only with respect to
     *   underlying functional forms. We do this so that users don't
     *   encounter layers of adaptors in common usages. We also
     *   include "Relay" classes/methods that don't correspond to user
     *   methods; they copy results from one stage to another.
     *
     * * Boolean CompletableFuture method x(...) (for example
     *   uniApply) takes all of the arguments needed to check that an
     *   action is triggerable, and then either runs the action or
     *   arranges its async execution by executing its Completion
     *   argument, if present. The method returns true if known to be
     *   complete.
     *
     * * Completion method tryFire(int mode) invokes the associated x
     *   method with its held arguments, and on success cleans up.
     *   The mode argument allows tryFire to be called twice (SYNC,
     *   then ASYNC); the first to screen and trap exceptions while
     *   arranging to execute, and the second when called from a
     *   task. (A few classes are not used async so take slightly
     *   different forms.)  The claim() callback suppresses function
     *   invocation if already claimed by another thread.
     *
     * * CompletableFuture method xStage(...) is called from a public
     *   stage method of CompletableFuture x. It screens user
     *   arguments and invokes and/or creates the stage object.  If
     *   not async and x is already complete, the action is run
     *   immediately.  Otherwise a Completion c is created, pushed to
     *   x's stack (unless done), and started or triggered via
     *   c.tryFire.  This also covers races possible if x completes
     *   while pushing.  Classes with two inputs (for example BiApply)
     *   deal with races across both while pushing actions.  The
     *   second completion is a CoCompletion pointing to the first,
     *   shared so that at most one performs the action.  The
     *   multiple-arity methods allOf and anyOf do this pairwise to
     *   form trees of completions.
     *
     * Note that the generic type parameters of methods vary according
     * to whether "this" is a source, dependent, or completion.
     *
     * Method postComplete is called upon completion unless the target
     * is guaranteed not to be observable (i.e., not yet returned or
     * linked). Multiple threads can call postComplete, which
     * atomically pops each dependent action, and tries to trigger it
     * via method tryFire, in NESTED mode.  Triggering can propagate
     * recursively, so NESTED mode returns its completed dependent (if
     * one exists) for further processing by its caller (see method
     * postFire).
     *
     * Blocking methods get() and join() rely on Signaller Completions
     * that wake up waiting threads.  The mechanics are similar to
     * Treiber stack wait-nodes used in FutureTask, Phaser, and
     * SynchronousQueue. See their internal documentation for
     * algorithmic details.
     *
     * Without precautions, CompletableFutures would be prone to
     * garbage accumulation as chains of Completions build up, each
     * pointing back to its sources. So we null out fields as soon as
     * possible (see especially method Completion.detach). The
     * screening checks needed anyway harmlessly ignore null arguments
     * that may have been obtained during races with threads nulling
     * out fields.  We also try to unlink fired Completions from
     * stacks that might never be popped (see method postFire).
     * Completion fields need not be declared as final or volatile
     * because they are only visible to other threads upon safe
     * publication.
     */
}
```

#####  2.3.2  `CompletionStage`接口简介

**`CompletionStage`接口**：`CompletionStage`代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段。一个阶段的执行可能是由单个阶段完成触发，也可能是由多个阶段一起触发。`CompletionStage`接口是 Java 8 中引入的接口之一，用于支持异步编程和处理异步操作的结果。它表示一个可能存在值的计算过程，该值可能在未来某个时间点变得可用。`CompletionStage` 提供了一组方法，用于组合多个异步操作，处理操作返回的结果，以及处理操作中可能出现的异常。一个阶段的计算执行可以是一个`Function`,`Consumer`或者`Runnable`。比如：

```java
stage.thenApply(x -> square(X))
     .thenAccept(x -> System.out.print(x))
     .thenRun(()-> System.out.println())
```

`CompletionStage`代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段，有些类似Linux系统的管道分隔符传参数

**`CompletionStage`接口的方法**：`CompletionStage`接口定义了许多方法，通过这些方法，`CompletionStage` 接口使得开发人员能够更轻松地处理异步操作的结果，以及有效地组合多个异步操作，从而简化异步编程。`CompletionStage` 接口方法

- `thenApply(Function<? super T,? extends U> fn)`：对操作的结果应用函数，并返回表示转换结果的新 `CompletionStage`。
- `thenCompose(Function<? super T,? extends CompletionStage<U>> fn)`：使用操作的结果作为参数来触发新的异步操作。
- `whenComplete(BiConsumer<? super T,? super Throwable> action)`：无论操作是正常完成还是异常完成，都会执行指定的操作。
- `handle(BiFunction<? super T, Throwable,? extends U> fn)`：根据操作的结果或异常，执行相应的转换操作。
- `thenAccept(Consumer<? super T> action)`：对操作的结果执行给定的动作，且不返回新的 `CompletionStage`。

**`CompletableFuture`的优点**：异步任务结束时，会自动回调某个对象的方法。主线程设置好回调后，不再关心异步任务的执行，异步任务之间可以顺序执行异步任务出错时，会自动回调某个对象的方法

**`CompletionStage`接口源码**：

```java
/**
 * A stage of a possibly asynchronous computation, that performs an
 * action or computes a value when another CompletionStage completes.
 * A stage completes upon termination of its computation, but this may
 * in turn trigger other dependent stages.  The functionality defined
 * in this interface takes only a few basic forms, which expand out to
 * a larger set of methods to capture a range of usage styles: <ul>
 *
 * <li>The computation performed by a stage may be expressed as a
 * Function, Consumer, or Runnable (using methods with names including
 * <em>apply</em>, <em>accept</em>, or <em>run</em>, respectively)
 * depending on whether it requires arguments and/or produces results.
 * For example, {@code stage.thenApply(x -> square(x)).thenAccept(x ->
 * System.out.print(x)).thenRun(() -> System.out.println())}. An
 * additional form (<em>compose</em>) applies functions of stages
 * themselves, rather than their results. </li>
 *
 * <li> One stage's execution may be triggered by completion of a
 * single stage, or both of two stages, or either of two stages.
 * Dependencies on a single stage are arranged using methods with
 * prefix <em>then</em>. Those triggered by completion of
 * <em>both</em> of two stages may <em>combine</em> their results or
 * effects, using correspondingly named methods. Those triggered by
 * <em>either</em> of two stages make no guarantees about which of the
 * results or effects are used for the dependent stage's
 * computation.</li>
 *
 * <li> Dependencies among stages control the triggering of
 * computations, but do not otherwise guarantee any particular
 * ordering. Additionally, execution of a new stage's computations may
 * be arranged in any of three ways: default execution, default
 * asynchronous execution (using methods with suffix <em>async</em>
 * that employ the stage's default asynchronous execution facility),
 * or custom (via a supplied {@link Executor}).  The execution
 * properties of default and async modes are specified by
 * CompletionStage implementations, not this interface. Methods with
 * explicit Executor arguments may have arbitrary execution
 * properties, and might not even support concurrent execution, but
 * are arranged for processing in a way that accommodates asynchrony.
 *
 * <li> Two method forms support processing whether the triggering
 * stage completed normally or exceptionally: Method {@link
 * #whenComplete whenComplete} allows injection of an action
 * regardless of outcome, otherwise preserving the outcome in its
 * completion. Method {@link #handle handle} additionally allows the
 * stage to compute a replacement result that may enable further
 * processing by other dependent stages.  In all other cases, if a
 * stage's computation terminates abruptly with an (unchecked)
 * exception or error, then all dependent stages requiring its
 * completion complete exceptionally as well, with a {@link
 * CompletionException} holding the exception as its cause.  If a
 * stage is dependent on <em>both</em> of two stages, and both
 * complete exceptionally, then the CompletionException may correspond
 * to either one of these exceptions.  If a stage is dependent on
 * <em>either</em> of two others, and only one of them completes
 * exceptionally, no guarantees are made about whether the dependent
 * stage completes normally or exceptionally. In the case of method
 * {@code whenComplete}, when the supplied action itself encounters an
 * exception, then the stage exceptionally completes with this
 * exception if not already completed exceptionally.</li>
 *
 * </ul>
 *
 * <p>All methods adhere to the above triggering, execution, and
 * exceptional completion specifications (which are not repeated in
 * individual method specifications). Additionally, while arguments
 * used to pass a completion result (that is, for parameters of type
 * {@code T}) for methods accepting them may be null, passing a null
 * value for any other parameter will result in a {@link
 * NullPointerException} being thrown.
 *
 * <p>This interface does not define methods for initially creating,
 * forcibly completing normally or exceptionally, probing completion
 * status or results, or awaiting completion of a stage.
 * Implementations of CompletionStage may provide means of achieving
 * such effects, as appropriate.  Method {@link #toCompletableFuture}
 * enables interoperability among different implementations of this
 * interface by providing a common conversion type.
 */
public interface CompletionStage<T> {
}
```

###  2.4  `CompletableFuture`使用案例

#####  2.4.1  获得结果和触发计算

**`CompletableFuture`方法分类总结**：如下方法主要用于获得结果和触发计算

| 方法                                                 | 描述                                                         |
| ---------------------------------------------------- | ------------------------------------------------------------ |
| **`public T get()` 方法**                            | 用于获取 `CompletableFuture` 的结果值。此方法会阻塞当前线程直到 `CompletableFuture` 的结果可用，并返回结果值。如果 `CompletableFuture` 的计算尚未完成，则 `get()` 方法会一直等待，直到计算完成为止。如果计算过程中出现异常，`get()` 方法会抛出一个 `CompletionException`，其中包含导致计算异常的原因。为了避免程序阻塞，还可以使用带有超时时间的重载方法 `get(long timeout, TimeUnit unit)` |
| **`public T get(long timeout, TimeUnit unit)` 方法** | 用于获取 `CompletableFuture` 的结果值，但不同于`get()`方法，它允许设置一个超时时间。如果 `CompletableFuture` 的计算在指定的超时时间内没有完成，该方法会抛出 `TimeoutException` 异常。这个方法允许程序在获取 `CompletableFuture` 结果时避免无限期地阻塞，并在超时发生时进行适当的处理。传入的参数 `timeout` 表示超时时间的长短，`unit` 表示时间单位。通过在调用 `get()` 方法时设置超时时间，可以更好地控制程序的行为，以便根据需要采取反应 |
| **`public T join()` 方法**                           | 与 `get()` 方法类似，用于获取 `CompletableFuture` 的结果值。不同之处在于，`join()` 方法是 `CompletableFuture` 接口的一部分，而 `get()` 方法是从 `Future` 接口继承而来的。`join()` 方法也会等待 `CompletableFuture` 的计算结果，并返回结果值，但在计算过程中如果出现异常，`join()` 方法会抛出一个未经检查的异常，而不是 `CompletionException`。由于 `join()` 不会抛出checked exception，因此在某些情况下，它可能更方便于使用。总体而言，`join()` 方法主要用于结合 `CompletableFuture` 接口，并提供类似于同步代码的处理方式，使得处理异常更为方便 |
| **`public T getNow(T valueIfAbsent)` 方法**          | 用于获取`CompletableFuture`的结果。如果`CompletableFuture`已经完成，它会返回结果值；如果尚未完成，则返回`valueIfAbsent`的值 |
| **`public boolean complete(T value)` 方法**          | 用于手动完成 `CompletableFuture` 的计算，并将结果设置为指定的值 `value`。如果 `CompletableFuture` 尚未完成，则此方法将返回 `true`，表示成功完成操作；如果 `CompletableFuture` 已经完成（无论是正常完成、异常完成还是被取消），则该方法将返回 `false`。通过调用 `complete()` 方法，可以手动触发 `CompletableFuture` 的完成操作，将指定的值作为计算结果。这可以用于特定情况下的手动处理，但要注意，滥用这个方法可能会破坏 `CompletableFuture` 异步计算的特性 |

**示例代码一**：获得结果和触发计算

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        group1();
    }

    /**
     * 获得结果和触发计算
     */
    private static void group1() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        // get() 方法会阻塞程序，直到对应的completableFuture执行结束或者出现异常
        //System.out.println(completableFuture.get());

        // get(long timeout, TimeUnit unit)方法 会阻塞程序，直到线程结束 或者 出现异常 或者 超过指定时间
        // System.out.println(completableFuture.get(2L,TimeUnit.SECONDS));

        // join() 方法 和 get() 方法几乎一致，区别在于join()方法不会抛出异常
        System.out.println(completableFuture.join());

        // 暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        //  getNow(T valueIfAbsent)方法：如果CompletableFuture已经完成，它会返回结果值；如果尚未完成，则返回valueIfAbsent的值
        //  System.out.println(completableFuture.getNow("xxx"));

        // public boolean complete(T value)  是否打断get方法立即返回括号值
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.get());
    }
}
```

#####  2.4.2 对计算结果进行处理

**`CompletableFuture`方法分类总结**：如下方法主要用于对计算结果进行处理

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| **`public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)`方法** | `CompletableFuture` 类中的一种转换方法。它允许在计算完成后对结果执行一些转换操作，并返回一个新的 `CompletableFuture` 对象，该对象会包含转换后的结果。`thenApply()` 方法接受一个 `Function` 参数，该函数会在当前 `CompletableFuture` 中计算完成后被应用，转换结果类型为 `U`。当前 `CompletableFuture` 的计算完成后，`thenApply()` 将以当前计算的结果作为输入，应用给定的函数进行转换，并最终返回一个新的 `CompletableFuture` 对象，该对象会以转换后的结果值作为计算结果。此方法实现了链式调用的概念，使得可以方便地对计算结果进行连续的处理操作 |
| **`public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)` 方法** | 在 `CompletableFuture` 计算完成后对结果进行处理，并可以处理可能发生的异常情况。它允许在结果成功计算或计算过程中发生异常时，对结果执行操作并返回一个新的 `CompletableFuture` 对象。`handle()` 方法接受一个 `BiFunction` 函数式接口作为参数，该函数接受两个参数：`result` 表示计算成功时的结果值，`exception` 表示计算过程中可能的异常。当前 `CompletableFuture` 对象完成后，`handle()` 方法将被调用，以计算结果值和可能的异常作为输入，对它们执行给定的操作，并返回一个新的 `CompletableFuture` 对象，其中包含返回的结果或处理异常后的结果。`handle()` 允许开发者对可能的异常情况进行处理，同时允许对结果进行转换，使其成为一个非常灵活的方法 |

**`thenApply`方法使用示例一**：

```java
//  thenApply() 将在 future完成后将其结果转换成一个带有特定前缀的字符串，并返回一个新的 CompletableFuture对象 transformed
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 20);
CompletableFuture<String> transformed = future.thenApply(result -> "The result is: " + result);
```
**`thenApply`方法使用示例二**：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPI2Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
        }, threadPool).thenApply(f -> {
//            int i = 10 / 0;
            System.out.println("222");
            return f + 2;
        }).thenApply(f -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果： " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "----主线程先去忙其它任务");
        threadPool.shutdown();
/* 程序输出：
            main----主线程先去忙其它任务
            111
            222
            333
            ----计算结果： 6
 */
    }
}
```

**`thenApply`方法使用示例三**：在 `thenApply` 方法中，如果应用转换的过程中发生异常，该异常会被捕获并传递到新的 `CompletableFuture` 中，作为其计算的异常结果。这意味着`thenApply`方法中的异常会导致返回的新`CompletableFuture`进入异常完成状态，其中包含相应的异常信息。对返回的`CompletableFuture`对象调用`join()`或`get()`方法时，将会抛出一个`CompletionException`，其中包含原始异常的信息。程序员可以在处理这种异常情况时执行适当的错误处理逻辑，如通过 `exceptionally` 方法或者 `handle` 方法来处理异常结果或提供默认值

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10 / 0) // 引发异常
        .thenApply(result -> result * 2); // 这里的转换操作将不会执行，future 的结果将是异常状态

future.exceptionally(ex -> {
    System.out.println("Exception occurred: " + ex); // 这里将会打印异常信息
    return 0; // 返回默认值
});

Integer result = future.join(); // 由于异常处理，返回默认值 0
```

**`handle`方法示例一**：`handle()` 方法对可能的异常情况进行了处理，并返回了一个新的 `CompletableFuture` 对象 `handled`，其中包含了根据计算结果或异常进行的相应处理

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10 / 2); // 正常计算
CompletableFuture<String> handled = future.handle((result, exception) -> {
    if (exception != null) {
        return "Error occurred: " + exception.getMessage();
    } else {
        return "Result is: " + result;
    }
});
```
**`handle`方法示例二**：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
        }, threadPool).handle((f, e) -> {
            int i = 10 / 0;
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果： " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "----主线程先去忙其它任务");
        threadPool.shutdown();
/*程序输出：
        main----主线程先去忙其它任务
        111
        333
        java.lang.NullPointerException
 */
    }
}
```

#####   2.4.3 对计算结果进行消费

**`CompletableFuture`方法分类总结**：如下方法主要用于对计算结果进行消费

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| **`public CompletableFuture<Void> thenRun(Runnable action)` 方法** | `thenRun()` 方法指定了一个在前一个任务完成后需要执行的操作，用于在当前 `CompletableFuture` 异步计算完成后执行一个 `Runnable` 操作，之后返回一个新的 `CompletableFuture` 对象。`thenRun()` 方法接受一个 `Runnable` 参数，该参数代表一个无参数且无返回值的操作，可以是一个需要在当前 `CompletableFuture` 计算完成后执行的任务。当前 `CompletableFuture` 完成后，将会执行传入的 `action`，并返回一个新的 `CompletableFuture<Void>` 对象，表示此操作的异步执行结果。由于 `action` 本身不产生结果，因此返回的`CompletableFuture` 对象不会包含特定的结果值，而是表示异步操作的完成状态 |
| **`public CompletableFuture<Void> thenAccept(Consumer<? super T> action)` 方法** | 用于在当前 `CompletableFuture` 异步计算完成后对计算的结果执行一个消费操作，并返回一个新的 `CompletableFuture` 对象。`thenAccept()` 方法接受一个 `Consumer` 参数，该参数代表一个对结果进行消费的操作。`Consumer` 是一个函数式接口，接受一个参数并且不产生任何结果。当前 `CompletableFuture` 完成后，将会对计算的结果执行传入的 `action`，并返回一个新的 `CompletableFuture<Void>` 对象，表示此操作的异步执行结果。由于 `action` 本身不产生结果，因此返回的 `CompletableFuture` 对象不会包含特定的结果值，而是表示异步操作的完成状态 |
| **`public <U> CompletableFuture<U> thenApply(Function<? super T, ? extends U> fn)` 方法** | 用于对 `CompletableFuture` 的结果进行转换，并返回一个新的 `CompletableFuture` 对象，该对象包含了转换后的结果值。`thenApply()` 方法接受一个 `Function` 参数，该函数会在当前 `CompletableFuture` 中计算完成后被应用，将结果类型从 `T` 转换为 `U`。当前 `CompletableFuture` 的计算完成后，`thenApply()` 将以当前计算的结果作为输入，应用给定的函数进行转换，并最终返回一个新的 `CompletableFuture` 对象，该对象会以转换后的结果值作为计算结果。此方法实现了链式调用的概念，使得可以方便地对计算结果进行连续的处理操作 |

**`CompletableFuture`对计算结果进行消费的相关方法总结**：

```shell
# 使用的是默认的ForkJoinPool.commonPool()
thenRun(Runnable runnable)：    任务A执行完执行B，并且B不需要A的结果
thenAccept(Consumer action)：   任务A执行完执行B，B需要A的结果，但是任务B无返回值
thenApply(Function fn)：        任务A执行完执行B，B需要A的结果，同时任务B有返回值

# 异步执行指定的 action
# 使用的是默认的 ForkJoinPool.commonPool()，它会在新的线程中执行指定的 action
thenRunAsync(Runnable action)
thenAcceptAsync(Consumer action)
thenApplyAsync(Function fn)


# 异步执行指定的 action
# 可以自定义执行action的线程池，而不是依赖默认的ForkJoinPool.commonPool()
thenRunAsync(Runnable action,Executor executor)
thenAcceptAsync(Consumer action,Executor executor)
thenApplyAsync(Function fn, Executor executor)
```

**三组消费方法的区别(以`thenRun`和`thenRunAsync`为例)**：

- `thenRun(Runnable runnable)` 方法将在当前阶段（即前一个阶段）正常完成后执行指定的 `runnable`，使用的是默认的 `ForkJoinPool.commonPool()`

- `thenRunAsync(Runnable action)` 方法表示在当前阶段（即前一个阶段）正常完成后异步执行指定的 `action`，使用的是默认的 `ForkJoinPool.commonPool()`，它会在新的线程中执行指定的 `action`

- `thenRunAsync(Runnable action, Executor executor)` 方法与之前的 `thenRunAsync` 方法类似，但它允许指定一个执行 `action` 的 `executor`，这样可以自定义执行 `action` 的线程池，而不是依赖默认的 `ForkJoinPool.commonPool()`

**`thenAccept()` 方法使用示例**：

```java
//  thenAccept()方法用于指定对计算结果的消费操作，即打印输出
//  在future计算完成后，thenAccept会执行传入的消费操作，打印出结果
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<Void> result = future.thenAccept(s -> System.out.println("Received result: " + s));
```
**`thenApply()`方法使用示例**：

```java
// thenApply()在future完成后将其结果转换成一个带有特定前缀的字符串，并返回一个新的 CompletableFuture对象transformed
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 20);
CompletableFuture<String> transformed = future.thenApply(result -> "The result is: " + result);
```
**`thenApply()`方法使用示例**：

```java
import java.util.concurrent.CompletableFuture;
public class CompletableFutureAPI4Demo {
    public static void main(String[] args) {
        /*CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f ->{
            return f + 2;
        }).thenApply(f ->{
            return f + 3;
        }).thenAccept(System.out::println);*/
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {
        }).join());
        // null
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        //  resultA
        //  null
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());
        // resultAresultB
    }
}
```

#####   2.4.4 对计算速度进行选用

**`CompletableFuture`方法分类总结**：如下方法主要用于对计算速度进行选用

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)` | `CompletableFuture`的`applyToEither`方法是用来指定当两个`CompletableFuture`中的任意一个完成时，对其结果进行转换并返回一个新的`CompletableFuture`。该方法接受一个`CompletionStage`类型的参数和一个函数，该函数将会被应用到首先完成的`CompletableFuture`的结果上，然后返回一个新的`CompletableFuture` |

**`applyToEither`方法使用示例一**：当`future1`和`future2`中的任意一个首先完成时，`applyToEither`方法将使用`String::toUpperCase`函数将其结果转换为大写，并返回一个新的`CompletableFuture`。最后，通过`thenAccept`方法输出最终结果，并通过`join`方法等待结果完成

```java
import java.util.concurrent.CompletableFuture;
public class CompletableFutureExample {
    public static void main(String[] args) {
        // 创建两个CompletableFuture对象
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Result from future1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Result from future2");

        // 当任意一个CompletableFuture完成时，将其结果字符串转换为大写
        CompletableFuture<String> resultFuture = future1.applyToEither(future2, String::toUpperCase);

        // 输出最终结果
        resultFuture.thenAccept(System.out::println);

        // 等待结果完成
        resultFuture.join();
    }
}
```

**`applyToEither`方法使用示例二**：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
public class CompletableFutureFastDemo {
    public static void main(String[] args)
    {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playB";
        });

        CompletableFuture<String> result = playA.applyToEither(playB, f -> {
            return f + " is winer";
        });
        System.out.println(Thread.currentThread().getName()+"\t"+"-----: "+result.join());
/*程序执行结果：
          A come in
          B come in
          main  -----: playB is winer
 */
    }
}
```

#####   2.4.5 对计算结果进行合并

**`CompletableFuture`方法分类总结**：如下方法主要用于对计算结果进行合并

| 方法                                                    | 描述                                                         |
| ------------------------------------------------------- | ------------------------------------------------------------ |
| **`public <U,V> CompletableFuture<V> thenCombine`方法** | `thenCombine`：两个`CompletionStage`任务都完成后，最终能把两个任务的结果一起交给`thenCombine`来处理，先完成的先等着，等待其它分支任务。将两个独立的`CompletableFuture`的结果进行合并处理。该方法需要两个参数：另一个`CompletableFuture`和一个`BiFunction`，该`BiFunction`操作将两个`CompletableFuture`的结果作为输入，并返回合并后的结果。`other`参数代表另一个`CompletableFuture`对象，它的结果将与当前`CompletableFuture`的结果进行合并。`fn`参数是一个`BiFunction`类型的对象，接收两个输入参数，代表两个`CompletableFuture`的结果，返回一个新的合并后的结果。使用`thenCombine`方法，可以等待两个`CompletableFuture`都完成，并在它们都完成之后将它们的结果合并成一个新的`CompletableFuture` |

**`thenCombine`方法使用示例一**：


```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
public class CompletableFutureCombineDemo {
    public static void main(String[] args)
    {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---启动");
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---启动");
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });
        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("-----开始两个结果合并");
            return x + y;
        });
        System.out.println(result.join());
/*程序执行结果：
        ForkJoinPool.commonPool-worker-9	 ---启动
        ForkJoinPool.commonPool-worker-2	 ---启动
        -----开始两个结果合并
        30
 */
    }
}
```

**`thenCombine`方法使用示例二**：

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureThenCombineExample {
    public static void main(String[] args) {
        // 创建第一个CompletableFuture，模拟一个异步任务
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);

        // 创建第二个CompletableFuture，模拟另一个异步任务
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");

        // 使用thenCombine方法将两个CompletableFuture的结果合并
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + " " + result2);

        // 等待并获取合并后的结果
        combinedFuture.thenAccept(result -> System.out.println("Combined Result: " + result));
    }
}
```

### 2.5 `completableFuture`和线程池说明

**`CompletableFuture`对计算结果进行消费的相关方法总结**：

```shell
# 使用的是默认的ForkJoinPool.commonPool()
thenRun(Runnable runnable)：    任务A执行完执行B，并且B不需要A的结果
thenAccept(Consumer action)：   任务A执行完执行B，B需要A的结果，但是任务B无返回值
thenApply(Function fn)：        任务A执行完执行B，B需要A的结果，同时任务B有返回值

# 异步执行指定的 action
# 使用的是默认的 ForkJoinPool.commonPool()，它会在新的线程中执行指定的 action
thenRunAsync(Runnable action)
thenAcceptAsync(Consumer action)
thenApplyAsync(Function fn)


# 异步执行指定的 action
# 可以自定义执行action的线程池，而不是依赖默认的ForkJoinPool.commonPool()
thenRunAsync(Runnable action,Executor executor)
thenAcceptAsync(Consumer action,Executor executor)
thenApplyAsync(Function fn, Executor executor)
```

**`completableFuture`和线程池说明**：

- 如果没有传入自定义线程池，都用默认线程池`ForkJoinPool`
- 如果传入了一个自定义线程池
  - 如果执行第一个任务的时候，传入了一个自定义线程池
  - 调用`thenRun`方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池
  - 调用`thenRunAsync`执行第二个任务时，第一个任务使用的是自定义的线程池，第二个任务使用的是`ForkJoin`线程池
- 备注
  - 有可能处理太快，系统优化切换原则，直接使用main线程处理
  - 其它如: `thenAccept`和`thenAcceptAsync`，`thenApply`和`thenApplyAsync`等，它们之间的区别也是同理

**`completableFuture`和线程池演示一**：如果没有传入自定义线程池，都用默认线程池`ForkJoinPool`

```java
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try
        {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abcd";
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });
            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            threadPool.shutdown();
        }
/*程序输出：
            1号任务    ForkJoinPool.commonPool-worker-9
            2号任务    ForkJoinPool.commonPool-worker-9
            3号任务    ForkJoinPool.commonPool-worker-9
            4号任务    ForkJoinPool.commonPool-worker-9
            null
 */
```

**`completableFuture`和线程池演示二**：如果没有传入自定义线程池，都用默认线程池`ForkJoinPool`

```java
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try
        {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abcd";
            }).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });
            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            threadPool.shutdown();
        }
/*程序输出：
            1号任务    ForkJoinPool.commonPool-worker-9
            2号任务    ForkJoinPool.commonPool-worker-9
            3号任务    ForkJoinPool.commonPool-worker-9
            4号任务    ForkJoinPool.commonPool-worker-9
            null
 */
```

**`completableFuture`和线程池演示三**：如果执行第一个任务的时候，传入了一个自定义线程池。调用`thenRun`方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池

```java
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try
        {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abcd";
            },threadPool).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });
            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
/*程序输出：
            1号任务    pool-1-thread-1
            2号任务    pool-1-thread-1
            3号任务    pool-1-thread-1
            4号任务    pool-1-thread-1
            null
 */
```

**`completableFuture`和线程池演示四**：如果执行第一个任务的时候，传入了一个自定义线程池。调用`thenRunAsync`执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是`ForkJoin`线程池

```java
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try
        {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abcd";
            },threadPool).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });
            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
/*程序输出：
            1号任务    pool-1-thread-1
            2号任务    ForkJoinPool.commonPool-worker-9
            3号任务    ForkJoinPool.commonPool-worker-9
            4号任务    ForkJoinPool.commonPool-worker-9
            null
 */
```

`CompletableFuture`源码：

```java
public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
/**
 * Default executor -- ForkJoinPool.commonPool() unless it cannot
 * support parallelism.
 */
private static final Executor asyncPool = useCommonPool ?
    ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();

public CompletableFuture<Void> thenRun(Runnable action) {
    return uniRunStage(null, action);
}
public CompletableFuture<Void> thenRunAsync(Runnable action) {
    return uniRunStage(asyncPool, action);
}
public CompletableFuture<Void> thenRunAsync(Runnable action,
                                            Executor executor) {
    return uniRunStage(screenExecutor(executor), action);
}
}
```

###  2.6  `CompletableFuture`案例-电商网站的比价需求

`Lambda`表达式+`Stream`流式调用+`Chain`链式调用+`Java8`函数式编程

```java
// 函数式接口
// Runnable 无参数，无返回值
@FunctionalInterface
public interface Runnable {
public abstract void run();
}

// Function<T,R> 接受一个参数，并且有返回值
@FunctionalInterface
public interface Function<T,R>{
    R apply(T t);
}

// Consumer接受一个参数，没有返回值
@FunctionalInterface
public interface Consumer<T>{
    void accept(T t);
}

// BiConsumer<T,U> 接受两个参数（Bi，英文单词词根，代表两个的意思)，没有返回值
@FunctionalInterface
public interface BiConsumer<T, U>{
       void accept(T t,U u);
}

// Supplier没有参数，有一个返回值
@FunctionalInterface
public interface Supplier<T>{
    T get();
}
```

**`join`方法和`get`方法对比**：`join` 方法和 `get` 方法都可以用于等待`CompletableFuture` 完成。`join` 方法适用于不需要获取返回结果的情况，而 `get` 方法则适用于需要获取返回结果，并且可能需要处理异常情况。`join` 方法不会抛出已检查异常，因此在编码时更为便捷

| .          | join 方法                                    | get 方法                                   |
| ---------- | -------------------------------------------- | ------------------------------------------ |
| 返回值类型 | void                                         | T                                          |
| 抛出异常   | 不会抛出已检查异常                           | 会抛出已检查异常                           |
| 使用场景   | 适合于不需要获取返回结果，仅需要等待任务完成 | 适合于需要获取返回结果，并可能处理异常情况 |
| 调用方式   | 直接调用                                     | 需要捕获并处理异常或声明抛出               |

电商网站比价需求分析：  

```shell
1需求说明
1.1同一款产品，同时搜索出同款产品在各大电商平台的售价
1.2同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
输出返回:
出来结果希望是同款产品的在不同地方的价格清单列表，返回一个List<String>
《mysql》in jd price is 88.05
《mysql》in dangdang price is 86.11
《mysql》in taobao price is 90.43
3.解决方案,比对同一个商品在各个平台上的价格，要求获得一个清单列表
1 step by step，按部就班，查完京东查淘宝，查完淘宝查天猫
2 all in，万箭齐发，一口气多线程异步任务同时查询
```

**电商网站的比价需求实现**：

```java
import lombok.Getter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * 案例说明：电商比价需求，模拟如下情况：
 * <p>
 * 1需求：
 * 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价;
 * 1.2 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 * <p>
 * 2输出：出来结果希望是同款产品的在不同地方的价格清单列表，返回一个List<String>
 * 《mysql》 in jd price is 88.05
 * 《mysql》 in dangdang price is 86.11
 * 《mysql》 in taobao price is 90.43
 * <p>
 * 3 技术要求
 * 3.1 函数式编程
 * 3.2 链式编程
 * 3.3 Stream流式计算
 */
public class CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(new NetMall("jd"), new NetMall("dangdang"), new NetMall("taobao"), new NetMall("pdd"), new NetMall("tmall"));

    /**
     * step by step 一家家搜查
     * List<NetMall> ----->map------> List<String>
     */
    public static List<String> getPriceStepByStep(List<NetMall> list, String productName) {
        //《mysql》 in taobao price is 90.43
        return list.stream()
                .map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * List<NetMall> ----->List<CompletableFuture<String>>------> List<String>
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPriceStepByStep(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----step by step costTime: " + (endTime - startTime) + " 毫秒");
        System.out.println("--------------------");
        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("----CompletableFuture costTime: " + (endTime2 - startTime2) + " 毫秒");
/* 程序输出：
                mysql in jd price is 109.33
                mysql in dangdang price is 110.54
                mysql in taobao price is 110.36
                mysql in pdd price is 109.01
                mysql in tmall price is 109.22
                ----step by step costTime: 5107 毫秒
                --------------------
                mysql in jd price is 109.22
                mysql in dangdang price is 109.28
                mysql in taobao price is 109.57
                mysql in pdd price is 109.75
                mysql in tmall price is 110.74
                ----CompletableFuture costTime: 1015 毫秒
 */
    }
}
/**
 * 模拟价格查询
 */
class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
```

##  3. 锁

###  3.1  悲观锁和乐观锁

#####  3.1.1 悲观锁

**悲观锁**：悲观锁是一种并发控制机制，在Java中通常通过使用`synchronized`关键字或者`ReentrantLock`来实现。悲观锁的特点是它假设在并发情况下会发生冲突，因此在访问共享资源时会进行加锁操作，以阻止其他线程同时访问。当一个线程获取到悲观锁后，其他线程需要等待该线程释放锁才能访问相应资源。在Java中，可以使用`synchronized`关键字实现悲观锁。另外`ReentrantLock`也是一种支持悲观锁的机制。它提供了`比synchronized`更多的灵活性，允许显式地获取和释放锁，以及支持公平性和可中断性。总的来说，悲观锁在Java中主要通过`synchronized`关键字和`ReentrantLock`来实现，并通过对共享资源进行加锁来保证同一时间只有一个线程可以访问。悲观锁认为自己在使用数据的时候一定有别的线程来修改数据，因此在获取数据的时候会先加锁，确保数据不会被别的线程修改。synchronized关键字和Lock的实现类都是悲观锁。适合写操作多的场景，先加锁可以保证写操作时数据正确。显式的锁定之后再操作同步资源

```java
public class Example {
    private final Object lock = new Object();

    public void synchronizedMethod() {
        synchronized (lock) {
            // 访问共享资源的代码
        }
    }
}
```

#####  3.1.2  乐观锁

**乐观锁**：乐观锁是一种用于处理并发访问的技术，通常用于数据库操作或者多线程编程。乐观锁假设在数据被读取和操作的过程中，不会有其他需要互斥访问的操作发生。在处理数据库时，乐观锁通常会使用版本号或时间戳来实现。在Java中，乐观锁通常通过以下两种方式实现：**版本号控制**：每次更新数据时，都会检查数据的版本号，如果版本号与期望的值不匹配，说明数据已经被其他线程修改，此时会放弃当前操作或者进行其他处理。**`CAS（Compare And Swap）`算法**：通过`Atomic`类中的`compareAndSet`方法，在多线程环境下实现乐观锁。`CAS`能够在不使用锁的情况下实现多线程并发操作

```java
import java.util.concurrent.atomic.AtomicInteger;
public class OptimisticLockExample {
    private AtomicInteger version = new AtomicInteger(0);
    private String data;

    public void updateData(String newData, int expectedVersion) {
        if (version.get() == expectedVersion) {
            data = newData;
            version.getAndIncrement();
        } else {
            // 处理版本不匹配的情况
        }
    }
}
```

乐观锁认为自己在使用数据时不会有别的线程修改数据或资源，所以不会添加锁。在Java中是通过使用无锁编程来实现，只是在更新数据的时候去判断之前有没有别的线程更新了这个数据。如果这个数据没有被更新，当前线程将自己修改的数据成功写入。如果这个数据已经被其它线程更新，则根据不同的实现方式执行不同的操作，比如放弃修改、重试抢锁等等。乐观锁的优势在于在读取数据时不需要加锁，从而提升了系统的并发性能。然而，乐观锁需要开发者自行处理版本号或 `CAS` 算法，而且需要对数据并发访问的特性有较深的理解，以确保操作的正确性。乐观锁适合读操作多的场景，不加锁的特点能够使其读操作的性能大幅提升。乐观锁直接去操作同步资源，是一种无锁算法。乐观锁一般有两种实现方式：1.采用`Version`版本号机制 2.`CAS` (`Compare-and-Swap`，即比较并替换）算法实现。Java原子类中的递增操作就通过`CAS`自旋实现的

###  3.2  `synchronized`关键字

#####  3.2.1 阿里规约：`synchronized`锁的粒度

阿里巴巴开发手册：

> 【强制】高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁。能锁区块，就不要锁整个方法体。能用对象锁，就不要用类锁。说明：尽可能使加锁的代码工作量尽可能的小，避免在锁代码块中调用RPC方法

#####  3.2.2  `synchronized`的对象锁

 **`synchronized`的对象锁**：一个类里面如果定义了多个`synchronized`方法，某一个时刻内，只要某个线程利用该类的同一个对象去调用其中的一个`synchronized`方法，其它利用该对象去调用类中任意的`synchronized`方法的线程都只能等待。换句话说，某一个时刻内，只能有唯一的一个线程去访问这些`synchronized`方法。`synchronized`锁的是当前对象`this`，被锁定后，其它的线程都不能进入到当前对象的其它的`synchronized`方法

案例一：标准访问有ab两个线程，请问先发送邮件还是短信？程序执行结果：先发邮件再发短信

```java
// 标准访问有ab两个线程，请问先发送邮件还是短信
// 资源类
class Phone {
    public synchronized void sendEmail() {
        System.out.println("-----sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发邮件再发短信
//            -----sendEmail
//            -----sendSMS
}
```

案例二：`sendEmail`方法中加入暂停3秒钟，请问先打印邮件还是短信？程序执行结果：先发邮件再发短信

```java
// 2 sendEmail方法中加入暂停3秒钟，请问先打印邮件还是短信
// 资源类
class Phone {
    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }
    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }

//    public void hello() {
//        System.out.println("-------hello");
//    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发邮件再发短信
//             -----sendEmail
//             -----sendSMS
}
```

案例三：有两部手机，请问先打印邮件还是短信？程序执行结果：先发短信再发邮件。说明换成两个对象后，不是同一把锁了，情况立刻变化。结论：对象锁锁的是当前对象，不同对象的`synchronized`方法不会相互影响

```java
// 4 有两部手机，请问先打印邮件还是短信
class Phone {
    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }

    public void hello() {
        System.out.println("-------hello");
    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发短信再发邮件
//                -----sendSMS
//                -----sendEmail
}
```

案例四：添加一个普通的hello方法，请问先打印邮件还是hello？程序执行结果：先输出hello。结论：加个普通方法后发现和同步锁无关

```java
// 3 添加一个普通的hello方法，请问先打印邮件还是hello
class Phone {
    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }

    public void hello() {
        System.out.println("-------hello");
    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.hello();
        }, "b").start();
    }
// 程序执行结果：
//                -------hello
//                -----sendEmail
}
```

#####  3.2.3  `synchronized`的类锁

`synchronized`的类锁：将`synchronized`同步方法定义成静态的，情况又变化。三种`synchronized`锁的内容有一些差别。对于普通同步方法，锁的是当前实例对象，通常指`this`，具体的一部部手机，所有的普通同步方法用的都是同一把锁(实例对象本身)。对于静态同步方法，锁的是当前类的`Class`对象，如`Phone.class`是唯一的一个模板。对于同步方法块，锁的是`synchronized`括号内的对象

案例五：有两个静态同步方法，有1部手机，请问先打印邮件还是短信？程序执行结果：先发邮件再发短信

```java
// 5 有两个静态同步方法，有1部手机，请问先打印邮件还是短信
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }
    public static synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }
}
public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发邮件再发短信
//            -----sendEmail
//            -----sendSMS
}
```

案例六：有两个静态同步方法，有2部手机，请问先打印邮件还是短信？程序执行结果：先发邮件再发短信

```java
// 6 有两个静态同步方法，有2部手机，请问先打印邮件还是短信
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }

    public static synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发邮件再发短信
//                -----sendEmail
//                -----sendSMS
}
```

案例七：有1个静态同步方法，有1个普通同步方法,有1部手机，请问先打印邮件还是短信？程序执行结果：先发短信再发邮件

```java
// 7 有1个静态同步方法，有1个普通同步方法,有1部手机，请问先打印邮件还是短信
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }
    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发短信再发邮件
//                -----sendSMS
//                -----sendEmail
}
```

案例八：有1个静态同步方法，有1个普通同步方法,有2部手机，请问先打印邮件还是短信？程序执行结果：先发邮件再发短信

```java
// 8 有1个静态同步方法，有1个普通同步方法,有2部手机，请问先打印邮件还是短信
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }
}

public class Lock8Demo {
    // 一切程序的入口
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        //暂停200毫秒,保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.sendSMS();
        }, "b").start();
    }
// 程序执行结果：先发邮件再发短信
//                -----sendSMS
//                -----sendEmail
}
```

#####  3.2.4  `synchronized`类锁和对象锁总结

 **`synchronized`类锁和对象锁总结**：当一个线程试图访问同步代码时它首先必须得到锁，正常退出或抛出异常时必须释放锁。所有的普通同步方法用的都是同一把锁（实例对象本身），就是new出来的具体实例对象本身(本类this)。也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获取锁。所有的静态同步方法用的也是同一把锁（类对象本身），也就是唯一模板Class。具体实例对象this和唯一模板Class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的。但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁

**阿里巴巴开发手册**：`synchronized`锁的粒度

> 【强制】高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁。能锁区块，就不要锁整个方法体。能用对象锁，就不要用类锁。说明：尽可能使加锁的代码工作量尽可能的小，避免在锁代码块中调用RPC方法

**`synchronized`的三种应用方式**：

- 作用于实例方法，对当前实例加锁，进入同步代码前要获得当前实例的锁
- 作用于代码块，对括号里配置的对象加锁
- 作用于静态方法，当前类加锁，进去同步代码前要获得当前类对象的锁

注：`JDK`源码`notify`方法官方描述中印证了这三种应用方式

```
public final void notify()

Wakes up a single thread that is waiting on this object's monitor. If any threads are waiting on this object,one of them is chosen to be awakened.The choice is arbitrary and occurs at the discretion of the implementation.A thread waits on an object's monitor by calling one of the wait methods.

The awakened thread will not be able to proceed until the current thread relinquishes the lock on this object.The awakened thread will compete in the usual manner with any other threads that might be actively competing to synchronize on this object,for example, the awakened thread enjoys no reliable privilege or disadvantage in being the next thread to lock this object.

This method should only be called by a thread that is the owner of this object's monitor.A thread becomes the owner of the object's monitor in one of three ways:
- By executing a synchronized instance method of that object.
- By executing the body of a synchronized statement that synchronizes on the object
- For objects of type class, by executing a synchronized static method of that class.

notify方法应当只被拥有该对象监视器的线程所调用。线程可以通过以下三种方式之一成为对象监视器的拥有者：
- 通过执行该对象的同步实例方法
- 通过执行在该对象上同步的同步语句的主体部分
- 对于类类型的对象，通过执行该类的同步静态方法

Only one thread at a time can own an object's monitor.
```

**`JVM`类加载**：

![image-20240121150838140](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142339656.png)



#####  3.2.5 `synchronized`同步代码块字节码分析

**`javap`命令**：`javap`命令是`Java Development Kit（JDK）`中的一个工具，用于反编译已编译的`.class`文件。`javap`命令可以显示类的成员、方法、字段等信息，以及类的字节码指令。通过`javap`命令可以查看已编译的Java类的详细信息，包括类的结构、方法的签名、字段的类型等。这对于理解和调试Java代码非常有用。需要注意的是，`javap`命令只适用于已编译的`.class`文件，无法直接对源代码进行反编译。`javap`命令语法格式：

```
javap [options] [classes...]
常用选项：
         -c：显示类的字节码指令
         -l：显示行号和本地变量表
         -s：显示类的内部签名
         -verbose：显示详细信息。输出附加信息(包括行号、本地变量表，反汇编等详细信息)
         -classpath path：指定查找类文件的路径
         -bootclasspath path：指定查找引导类文件的路径
```

**使用`javap`命令分析`synchronized`代码块的字节码指令**：

一、运行`LockSyncDemo`类使其生成编译文件`LocksyncDemo.class`

```java
// LockSyncDemo类
// 一个monitorenter，两个monitorexit
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
```

二、在`LocksyncDemo.class`文件的目录下使用命令`javap -c .\LocksyncDemo.class`查看`LockSyncDemo`类的字节码指令。从字节码指令中可以看到编译器在`synchronized`同步代码块的前面生成一个`monitorenter`指令，该指令用于获取对象的监视器锁。该指令会尝试获取对象的监视器锁，如果获取成功，则继续执行同步代码块中的代码；如果获取失败，则当前线程会进入阻塞状态，等待其他线程释放锁。在同步代码块的末尾，编译器会生成一个`monitorexit`指令，该指令用于释放对象的监视器锁。当线程执行完同步代码块中的代码后，会执行该指令来释放锁，以便其他等待的线程可竞争获取锁。通过`monitorenter`和`monitorexit`指令的配合，`synchronized`同步代码块实现了对对象的监视器锁的获取和释放。当某个线程获取到锁时，其他线程必须等待锁的释放才能继续执行。需要注意的是，`synchronized`同步代码块的锁是基于对象的，每个对象都有一个监视器锁。不同线程对于同一个对象的同步代码块会竞争同一个锁，而对于不同对象的同步代码块则不会竞争同一个锁。总结起来，`synchronized`同步代码块的原理是通过生成`monitorenter`和`monitorexit`指令来获取和释放对象的监视器锁，实现线程的同步和互斥访问

```java
// 在LocksyncDemo.class文件的目录下使用命令javap -c .\LocksyncDemo.class查看LockSyncDemo类的字节码指令
PS D:\learn\SGG-JUC\juc_sgg\target\classes\com\sgg\juc\locks> javap -c .\LocksyncDemo.class
Compiled from "LockSyncDemo.java"
public class com.sgg.juc.locks.LockSyncDemo {
  java.lang.Object object;

  public com.sgg.juc.locks.LockSyncDemo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: new           #2                  // class java/lang/Object
       8: dup
       9: invokespecial #1                  // Method java/lang/Object."<init>":()V
      12: putfield      #3                  // Field object:Ljava/lang/Object;
      15: return

  public void m1();
    Code:
       0: aload_0
       1: getfield      #3                  // Field object:Ljava/lang/Object;
       4: dup
       5: astore_1
       6: monitorenter
       7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #5                  // String ----hello synchronized code block
      12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: aload_1
      16: monitorexit
      17: goto          25
      20: astore_2
      21: aload_1
      22: monitorexit
      23: aload_2
      24: athrow
      25: return
    Exception table:
       from    to  target type
           7    17    20   any
          20    23    20   any

  public static void main(java.lang.String[]);
    Code:
       0: return
}
```

三、使用`javap`命令分析`synchronized`代码块的字节码指令的步骤流程图



![image-20240301051421640](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142340983.png)

**为什么有1个`monitorenter`指令和2个`monitorexit`指令**：可以看到字节码指令中有一个`monitorenter`指令和2个`monitorexit`指令，对于`synchronized`同步代码块，实际上只需要一个`monitorenter`指令和一个`monitorexit`指令来完成对对象监视器锁的获取和释放。`monitorenter`指令用于获锁，`monitorexit`指令用于释放锁。然而，在Java字节码中，会生成两个`monitorexit`指令的原因是为了处理异常情况。当同步代码块中发生异常时，如果没有适当的处理，锁可能会一直被持有，导致其他线程无法获取锁。为了避免这种情况，编译器在字节码中生成了两个`monitorexit`指令。第一个`monitorexit`指令用于正常的代码流程，在同步代码块执行完毕后，会执行该指令来释放锁。如果同步代码块中发生了异常，那么异常处理代码会被执行，这时就需要第二个`monitorexit`指令来确保锁的释放，以防止锁一直被持有。通过这种方式，即使在同步代码块中发生了异常，也能保证最终锁会被释放，以允许其他线程继续获取锁并执行同步代码块。需要注意的是，当发生异常时，第二个`monitorexit`指令的执行是在异常处理代码块中，而不是在同步代码块中。这是为了确保无论是否发生异常，都会执行到第二个`monitorexit`指令来释放锁。总之，生成一个`monitorenter`指令和两个`monitorexit`指令是为了保证在同步代码块中发生异常时，能够正确地释放锁，避免锁一直被持有



**一定是1个`monitorenter`指令和2个`monitorexit`指令吗？**  一般情况是1个`monitorenter`指令和2个`monitorexit`指令。但是当同步代码块中出现异常时，只要1个`monitorenter`和1个`monitorexit`

```java
// 同步代码块中出现异常时，一个monitorenter，一个monitorexit
public class LockSyncDemo {
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
}
// 同步代码块中出现异常时，一个monitorenter，一个monitorexit
PS D:\learn\SGG-JUC\juc_sgg\target\classes\com\sgg\juc\locks> javap -c .\LocksyncDemo.class
Compiled from "LockSyncDemo.java"
public class com.sgg.juc.locks.LockSyncDemo {
  java.lang.Object object;

  public com.sgg.juc.locks.LockSyncDemo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: new           #2                  // class java/lang/Object
       8: dup
       9: invokespecial #1                  // Method java/lang/Object."<init>":()V
      12: putfield      #3                  // Field object:Ljava/lang/Object;
      15: return

  public void m1();
    Code:
       0: aload_0
       1: getfield      #3                  // Field object:Ljava/lang/Object;
       4: dup
       5: astore_1
       6: monitorenter
       7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #5                  // String ----hello synchronized code block
      12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: new           #7                  // class java/lang/RuntimeException
      18: dup
      19: ldc           #8                  // String -----exp
      21: invokespecial #9                  // Method java/lang/RuntimeException."<init>":(Ljava/lang/String;)V
      24: athrow
      25: astore_2
      26: aload_1
      27: monitorexit
      28: aload_2
      29: athrow
    Exception table:
       from    to  target type
           7    28    25   any

  public static void main(java.lang.String[]);
    Code:
       0: return
}

```

#####  3.2.6 `synchronized`同步方法字节码分析

**`javap -v`命令**：显示详细信息。输出附加信息(包括行号、本地变量表，反汇编等详细信息)

```
javap [options] [classes...]
常用选项：
         -c：显示类的字节码指令
         -l：显示行号和本地变量表
         -s：显示类的内部签名
         -verbose：显示详细信息。输出附加信息(包括行号、本地变量表，反汇编等详细信息)
         -classpath path：指定查找类文件的路径
         -bootclasspath path：指定查找引导类文件的路径
```

**使用`javap`命令分析`synchronized`普通同步方法的字节码指令**：

一、运行`LockSyncDemo`类使其生成编译文件`LocksyncDemo.class`

```java
// synchronized普通同步方法
public class LockSyncDemo {
    public synchronized void m2() {
        System.out.println("----hello synchronized m2");
    }
    public static void main(String[] args) {

    }
}
```

二、在`LocksyncDemo.class`文件的目录下使用命令`javap -v .\LockSyncDemo.class`查看`LockSyncDemo`类的字节码指令。结论：调用指令将会检查方法的`ACC_SYNCHRONIZED`访问标志是否被设置。如果设置了，执行线程会先持有`monitor`锁，然后再执行方法，最后在方法完成(无论是正常完成还是非正常完成)时释放`monitor`

```java
PS D:\learn\SGG-JUC\juc_sgg\target\classes\com\sgg\juc\locks> javap -v .\LockSyncDemo.class
Classfile /D:/learn/SGG-JUC/juc_sgg/target/classes/com/sgg/juc/locks/LockSyncDemo.class
  Last modified 2024年3月1日; size 681 bytes
  MD5 checksum a1237ef4d0b216f5efe5e541d9ff570f
  Compiled from "LockSyncDemo.java"
public class com.sgg.juc.locks.LockSyncDemo
    // 省略...
{
    // 省略...
  public synchronized void m2();
    descriptor: ()V
    flags: (0x0021) ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String ----hello synchronized m2
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 46: 0
        line 46: 0
        line 47: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcom/sgg/juc/locks/LockSyncDemo;
    // 省略...
}
SourceFile: "LockSyncDemo.java"
```

**使用`javap`命令分析`synchronized`静态同步方法的字节码指令**：

一、运行`LockSyncDemo`类使其生成编译文件`LocksyncDemo.class`

```java
// 静态同步方法
// ACC_STATIC,ACC_SYNCHRONIZED访问标志区分该方法是否静态同步方法
public class LockSyncDemo {
    public static synchronized void m3() {
        System.out.println("----hello static synchronized m3");
    }
    public static void main(String[] args) {
    }
}
```

二、在`LocksyncDemo.class`文件的目录下使用命令`javap -v .\LockSyncDemo.class`查看`LockSyncDemo`类的字节码指令。结论：利用`ACC_STATIC`、`ACC_SYNCHRONIZED`访问标志区分该方法是否静态同步方法

```java
PS D:\learn\SGG-JUC\juc_sgg\target\classes\com\sgg\juc\locks> javap -v .\LockSyncDemo.class
Classfile /D:/learn/SGG-JUC/juc_sgg/target/classes/com/sgg/juc/locks/LockSyncDemo.class
  Last modified 2024年3月1日; size 670 bytes
  MD5 checksum 908bf6107dea7fead87c6451947d7f95
  Compiled from "LockSyncDemo.java"
public class com.sgg.juc.locks.LockSyncDemo
  // 省略...
{
  public com.sgg.juc.locks.LockSyncDemo();
  // 省略...
  public static synchronized void m3();
    descriptor: ()V
    flags: (0x0029) ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
    Code:
      stack=2, locals=0, args_size=0
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String ----hello static synchronized m3
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 34: 0
        line 35: 8
            
        // 省略...
}
SourceFile: "LockSyncDemo.java"
```



#####  3.2.7 `synchronized`的实现原理

**`Monitor`管程**：管程(英语:`Monitor`，也称为监视器)是一种程序结构，结构内的多个子程序（对象或模块）形成的多个工作线程互斥访问共享资源。这些共享资源一般是硬件设备或一群变量。对共享变量能够进行的所有操作集中在一个模块中。(把信号量及其操作原语“封装”在一个对象内部）管程实现了在一个时间点，最多只有一个线程在执行管程的某个子程序。管程提供了一种机制，管程可以看做一个软件模块，它是将共享的变量和对于这些共享变量的操作封装起来，形成一个具有一定接口的功能模块，进程可以调用管程来实现进程级别的并发控制



**`Monitor`机制**：`Java`中的`Monitor`机制是一种用于实现线程同步的机制，它基于对象的内置锁（也称为监视器锁）实现。每个`Java`对象都有一个内置锁，当一个线程访问一个带有`synchronized`关键字的方法或代码块时，它将自动获得该对象的内置锁，并且其他线程将被阻塞，直到持有锁的线程释放锁。`Monitor`机制提供了以下几个关键特性：

- 互斥性：同一时间只能有一个线程持有对象的内置锁。如果一个线程已经持有锁，其他线程将无法获得锁，它们将被阻塞在锁的入口处
- 可见性：当一个线程释放锁时，它所做的修改将对其他线程可见。这是通过`Java`内存模型的保证来实现的
- 等待和通知机制：`Monitor`机制提供了等待和通知机制，允许线程在等待某个条件满足时进入等待状态，并在其他线程满足条件时通知等待线程继续执行。这是通过`wait()`、`notify()`和`notifyAll()`方法来实现的
- 条件队列：每个对象的内置锁都关联着一个条件队列，线程可以通过调用对象的`wait()`方法进入条件队列等待，当其他线程调用对象的`notify()`或`notifyAll()`方法时，等待线程将从条件队列中移动到就绪队列，准备竞争获取锁



**同步指令（`JVM`虚拟机第三版）**：

> Java虚拟机可以支持方法级的同步和方法内部一段指令序列的同步，这两种同步结构都是使用管程(Monitor，更常见的是直接将它称为“锁”）来实现的
>
> 方法级的同步是隐式的，即无须通过字节码指令来控制，它实现在方法调用和返回操作之中。虚拟机可以从方法常量池的方法表结构中的`ACC_SYNCHRONIZED`访问标志得知一个方法是否声明为同步方法。当调用方法时，调用指令将会检查方法的`ACC_SYNCHRONIZED`访问标志是否设置。如果设置了，执行线程就要求先成功持有管程，然后才能执行方法。最后在方法完成(无论是正常完成还是非正常完成)时释放管程。在方法执行期间，执行线程持有了管程，其他任何线程都无法再获得同一个管程。如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的锁将在异常抛到同步方法之外时自动释放
>
> 同步一段指令集序列通常是由`Java`语言中的`synchronized`语句块来表示的，`java`虚拟机的指令集中有有`monitorenter`和`monitorexit`两条指令来支持`synchronized`关键字的语义。正确实现`synchronized`关键字需要`java`编译器和`java`虚拟机两者共同协作支持



**`synchronized`的实现原理**：`synchronized`是`Java`中用来实现线程同步的关键字，它可以保证多个线程在访问共享资源时的互斥性和可见性。`synchronized`的实现原理主要涉及到对象头和`monitor`。每个`Java`对象都有一个对象头，对象头中包含了一些用于存储对象元数据的信息，其中之一就是用来实现`synchronized`的锁标记。当一个线程进入`synchronized`块时，它会尝试获取对象的锁标记，如果锁标记已经被其他线程持有，则该线程会进入阻塞状态，直到锁标记被释放。具体实现上，`synchronized`使用了`monitor`机制来实现线程的互斥和协作。每个`Java`对象都会与一个`monitor`关联，`monitor`中包含了一个计数器和一个等待队列。当一个线程获取到锁标记后，计数器会加1，表示该线程持有了锁。如果另一个线程尝试获取锁标记时发现已经被其他线程持有，它会进入等待队列中等待。当持有锁的线程释放锁标记后，计数器会减1，并且等待队列中的一个线程会被唤醒，继续尝试获取锁标记。需要注意的是，`synchronized`还可以用于修饰静态方法和代码块，以实现对类级别的线程同步。在这种情况下，锁标记是与类对象关联的，而不是实例对象。`synchronized`的实现原理图示：

![image-20240124170015844](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142340577.png)



**为什么任何一个对象都可以成为一个锁？**`synchronized`必须作用于某个对象中，所以`Java`在对象头`MarkWord`存储了锁的相关信息，`MarkWord`中标记着四种锁的信息：无锁、偏向锁、轻量级锁、重量级锁，锁升级功能主要依赖于`MarkWord `中的锁标志位和释放偏向锁标志位。每个对象天生都带着一个对象监视器，每一个被锁住的对象都会和`Monitor`关联起来，任何一个对象都可以成为一个锁。64位`Java`虚拟机对象头`MarkWord`示意图：

![image-20240301213650902](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142341634.png)



**`monitor`底层源码**：通过C++底层源码了解，在`HotSpot`虚拟机中，`monitor`采用`ObjectMonitor`实现。每个对象天生都带着一个对象监视器，每一个被锁住的对象都会和`Monitor`关联起来。`ObjectMonitor.java` -> `ObjectMonitor.cpp` -> `objectMonitor.hpp`

`ObjectMonitor.java`：

```java
// ObjectMonitor.java源码位置：
// https://hg.openjdk.org/jdk8/jdk8/hotspot/file/87ee5ee27509/agent/src/share/classes/sun/jvm/hotspot/runtime/ObjectMonitor.java
public class ObjectMonitor extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    heap = VM.getVM().getObjectHeap();
    Type type  = db.lookupType("ObjectMonitor");
    sun.jvm.hotspot.types.Field f = type.getField("_header");
    headerFieldOffset = f.getOffset();
    f = type.getField("_object");
    objectFieldOffset = f.getOffset();
    f = type.getField("_owner");
    ownerFieldOffset = f.getOffset();
    f = type.getField("FreeNext");
    FreeNextFieldOffset = f.getOffset();
    countField  = type.getCIntegerField("_count");
    waitersField = type.getCIntegerField("_waiters");
    recursionsField = type.getCIntegerField("_recursions");
  }

  public ObjectMonitor(Address addr) {
    super(addr);
  }
}
```

`objectMonitor.cpp`：

```c++
// objectMonitor.cpp源码位置
// https://hg.openjdk.org/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/runtime/objectMonitor.cpp
#include "runtime/objectMonitor.hpp"
bool ObjectMonitor::try_enter(Thread* THREAD) {
  if (THREAD != _owner) {
    if (THREAD->is_lock_owned ((address)_owner)) {
       assert(_recursions == 0, "internal state error");
       _owner = THREAD ;
       _recursions = 1 ;
       OwnerIsThread = 1 ;
       return true;
    }
    if (Atomic::cmpxchg_ptr (THREAD, &_owner, NULL) != NULL) {
      return false;
    }
    return true;
  } else {
    _recursions++;
    return true;
  }
}
void ObjectMonitor::Initialize () {
  static int InitializationCompleted = 0 ;
  assert (InitializationCompleted == 0, "invariant") ;
  InitializationCompleted = 1 ;
  if (UsePerfData) {
      EXCEPTION_MARK ;
      #define NEWPERFCOUNTER(n)   {n = PerfDataManager::create_counter(SUN_RT, #n, PerfData::U_Events,CHECK); }
      #define NEWPERFVARIABLE(n)  {n = PerfDataManager::create_variable(SUN_RT, #n, PerfData::U_Events,CHECK); }
      NEWPERFCOUNTER(_sync_Inflations) ;
      NEWPERFCOUNTER(_sync_Deflations) ;
      NEWPERFCOUNTER(_sync_ContendedLockAttempts) ;
      NEWPERFCOUNTER(_sync_FutileWakeups) ;
      NEWPERFCOUNTER(_sync_Parks) ;
      NEWPERFCOUNTER(_sync_EmptyNotifications) ;
      NEWPERFCOUNTER(_sync_Notifications) ;
      NEWPERFCOUNTER(_sync_SlowEnter) ;
      NEWPERFCOUNTER(_sync_SlowExit) ;
      NEWPERFCOUNTER(_sync_SlowNotify) ;
      NEWPERFCOUNTER(_sync_SlowNotifyAll) ;
      NEWPERFCOUNTER(_sync_FailedSpins) ;
      NEWPERFCOUNTER(_sync_SuccessfulSpins) ;
      NEWPERFCOUNTER(_sync_PrivateA) ;
      NEWPERFCOUNTER(_sync_PrivateB) ;
      NEWPERFCOUNTER(_sync_MonInCirculation) ;
      NEWPERFCOUNTER(_sync_MonScavenged) ;
      NEWPERFVARIABLE(_sync_MonExtant) ;
      #undef NEWPERFCOUNTER
  }
}
```

`ObjectMonitor.hpp`：

```c
// 源码位置：https://hg.openjdk.org/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/runtime/objectMonitor.hpp
  // initialize the monitor, exception the semaphore, all other fields
  // are simple integers or pointers
  ObjectMonitor() {
    _header       = NULL;   
    _count        = 0;
    _waiters      = 0,
    _recursions   = 0;
    _object       = NULL;
    _owner        = NULL;
    _WaitSet      = NULL;
    _WaitSetLock  = 0 ;
    _Responsible  = NULL ;
    _succ         = NULL ;
    _cxq          = NULL ;
    FreeNext      = NULL ;
    _EntryList    = NULL ;
    _SpinFreq     = 0 ;
    _SpinClock    = 0 ;
    OwnerIsThread = 0 ;
    _previous_owner_tid = 0;
  }
```

`ObjectMonitor`中有几个关键属性：

```
_owner:        指向持有ObjectMonitor对象的线程
_WaitSet:      存放处于wait状态的线程队列
_EntryList:    存放处于等待锁block状态的线程队列
_recursions:   锁的重入次数
_count:        竞争锁的线程个数
```



###  3.3  公平锁和非公平锁

**公平锁**：公平锁是指多个线程按照申请锁的顺序来获取锁，类似排队买票，先来的人先买，后来的人在队尾排着

**非公平锁**：非公平锁是指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁。在高并发环境下，有可能造成优先级翻转或者饥饿的状态（某个线程一直得不到锁)

```java
Lock lock = new ReentrantLock(true);   // true表示公平锁,先来先得
Lock lock = new ReentrantLock(false);  // false表示非公平锁，后来的也可能先获得锁
Lock lock = new ReentrantLock();       // 默认非公平锁
```

**非公平锁的优点**：恢复挂起的线程到真正锁的获取还是有时间差的，从开发人员来看这个时间微乎其微，但是从CPU的角度来看，这个时间差存在的还是很明显的。所以非公平锁能更充分的利用CPU的时间片，尽量减少CPU空闲状态时间。使用多线程很重要的考量点是线程切换的开销，当采用非公平锁时，当1个线程请求锁获取同步状态，然后释放同步状态，所以刚释放锁的线程在此刻再次获取同步状态的概率就变得非常大，所以就减少了线程的开销

**公平锁和非公平锁的选择**：如果为了更高的吞吐量，很显然非公平锁是比较合适的，因为节省很多线程切换时间，吞吐量自然就上去了。否则那就用公平锁，所有线程公平使用

**公平锁和非公平锁代码演示**：从`ReentrantLock`卖票`demo`演示公平和非公平现象

```java
import java.util.concurrent.locks.ReentrantLock;
// 公平锁和非公平锁的演示
class Ticket1 //资源类，模拟3个售票员卖完10张票
{
    private int number = 10;
    // 非公平锁
    //    ReentrantLock lock = new ReentrantLock();
    // 公平锁
    ReentrantLock lock = new ReentrantLock(true);
    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下:" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo1 {
    public static void main(String[] args)//一切程序的入口
    {
        Ticket1 ticket = new Ticket1();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "c").start();
    }
}

/*  ReentrantLock lock = new ReentrantLock(); 非公平锁的输出：
a卖出第：	10	 还剩下:9
c卖出第：	9	 还剩下:8
c卖出第：	8	 还剩下:7
c卖出第：	7	 还剩下:6
c卖出第：	6	 还剩下:5
c卖出第：	5	 还剩下:4
c卖出第：	4	 还剩下:3
c卖出第：	3	 还剩下:2
c卖出第：	2	 还剩下:1
c卖出第：	1	 还剩下:0
 */

/* ReentrantLock lock = new ReentrantLock(true); 公平锁输出：
a卖出第：	10	 还剩下:9
a卖出第：	9	 还剩下:8
a卖出第：	8	 还剩下:7
a卖出第：	7	 还剩下:6
b卖出第：	6	 还剩下:5
a卖出第：	5	 还剩下:4
c卖出第：	4	 还剩下:3
b卖出第：	3	 还剩下:2
a卖出第：	2	 还剩下:1
c卖出第：	1	 还剩下:0
 */
```



###  3.4 可重入锁（递归锁）

#####  3.4.1 可重入锁简介

**可重入锁（递归锁）**：可重入锁是指同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁(前提，锁对象得是同一个对象)，不会因为之前已经获取过还没释放而阻塞，”入“的含义是进入同步域（即同步代码块/方法或显式锁锁定的代码)。如果是一个有`synchronized`修饰的递归调用方法，程序第二次进入被自己阻塞了岂不是天大的笑话，出现了作茧自缚。所以`Java`中`synchronized`和`ReentrantLock`都是可重入锁，可重入锁使得一个线程中的多个流程可以获取同一把锁，持有这把同步锁可以再次进入，自己可以获取自己的内部锁。可重入锁的一个优点是能在一定程度上避免死锁

#####  3.4.2  隐式锁`synchronized`

**隐式锁**即`synchronized`关键字使用的锁，`synchronized`默认是可重入锁。在一个`synchronized`修饰的方法或代码块的内部调用本类的其他`synchronized`修饰的方法或代码块时，是永远可以得到锁的

**`synchronized`可重入演示一**：`synchronized`修饰的方法或代码块的内部调用本类的其他`synchronized`修饰的方法或代码块时，是永远可以得到锁的

```java
public class ReEntryLockDemo1 {
    public static void main(String[] args) {
        reEntryM1();
    }

    private static void reEntryM1() {
        final Object object = new Object();
        // 三层调用都是同一把锁
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t ----外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t ----中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t ----内层调用");
                    }
                }
            }
        }, "t1").start();
    }
/* 程序输出：
            t1   ----外层调用
            t1   ----中层调用
            t1   ----内层调用
 */
}
```

**`synchronized`可重入演示二**：`synchronized`修饰的方法或代码块的内部调用本类的其他`synchronized`修饰的方法或代码块时，是永远可以得到锁的

```java
// 同步方法可重入演示
public class ReEntryLockDemo2 {

    public static void main(String[] args) {
        ReEntryLockDemo2 reEntryLockDemo2 = new ReEntryLockDemo2();
        new Thread(() -> {
            reEntryLockDemo2.m1();
        }, "t1").start();
    }

    public synchronized void m1() {
        //指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
        System.out.println(Thread.currentThread().getName() + "\t ----come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ----end m1");
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ----come in");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ----come in");
    }
/* 程序输出：
            t1   ----come in
            t1   ----come in
            t1   ----come in
            t1   ----end m1
 */
}
```

#####  3.4.3  显式锁`ReentrantLock`

**显式锁**（即`Lock`）中的`ReentrantLock`是可重入锁

**`ReentrantLock`可重入演示**：

```java
import java.util.concurrent.locks.ReentrantLock;
// 显式锁（即Lock）ReentrantLock可重入锁演示
// 注意：正常情况，加锁几次就要解锁几次
public class ReEntryLockDemo3 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----come in内层调用");
                } finally {
                    lock.unlock();
                }

            } finally {
                // 如果加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                lock.unlock();// 正常情况，加锁几次就要解锁几次
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in外层调用");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
/*程序输出：
            t1   ----come in外层调用
            t1   ----come in内层调用
            t2   ----come in外层调用
 */
}
```

#####  3.3.2  `synchronized`可重入的实现原理

`synchronized`可重入的实现原理：每个锁对象都拥有一个锁计数器和一个指向持有该锁的线程的指针。当执行`monitorenter`时，如果目标锁对象的计数器为零，那么说明它没有被其他线程所持有，`Java`虚拟机会将该锁对象的持有线程设置为当前线程，并且将其计数器加1。在目标锁对象的计数器不为零的情况下，如果锁对象的持有线程是当前线程，那么`Java`虚拟机可以将其计数器加1，否则需要等待，直至持有线程释放该锁。当执行`monitorexit`时，`Java`虚拟机则需将锁对象的计数器减1，计数器为零代表锁已被释放。

`ObjectMonitor.hpp`：每个对象天生都带着一个对象监视器，每一个被锁住的对象都会和`Monitor`关联起来。通过`C++`底层源码了解，在`HotSpot`虚拟机中，`monitor`采用`ObjectMonitor`实现。`ObjectMonitor`的`_owner`指针指向`monitor`对象（也称为管程或监视器锁）的起始地址，当一个`monitor`被某个线程持有后，它便处于锁定状态。`ObjectMonitor`的主要数据结构如下（位于`HotSpot`虚拟机源码`ObjectMonitor.hpp`文件，`C++`实现的)

```c
// 源码位置：https://hg.openjdk.org/jdk8/jdk8/hotspot/file/87ee5ee27509/src/share/vm/runtime/objectMonitor.hpp
  // initialize the monitor, exception the semaphore, all other fields
  // are simple integers or pointers
  ObjectMonitor() {
    _header       = NULL;   
    _count        = 0;
    _waiters      = 0,
    _recursions   = 0;
    _object       = NULL;
    _owner        = NULL;
    _WaitSet      = NULL;
    _WaitSetLock  = 0 ;
    _Responsible  = NULL ;
    _succ         = NULL ;
    _cxq          = NULL ;
    FreeNext      = NULL ;
    _EntryList    = NULL ;
    _SpinFreq     = 0 ;
    _SpinClock    = 0 ;
    OwnerIsThread = 0 ;
    _previous_owner_tid = 0;
  }
```

`ObjectMonitor`中有几个关键属性：

```
_owner:        指向持有ObjectMonitor对象的线程
_WaitSet:      存放处于wait状态的线程队列
_EntryList:    存放处于等待锁block状态的线程队列
_recursions:   锁的重入次数
_count:        竞争锁的线程个数
```

`synchronized`的实现原理图示：

![image-20240124170015844](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142341788.png)

 ###  3.4  死锁

#####  3.4.1 死锁简介 | 死锁演示

**死锁**：死锁是指两个或两个以上的线程在执行过程中，因争夺资源而造成的一种互相等待的现象。若无外力干涉那它们都将无法推进下去。如果资源充足，进程的资源请求都能够得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁

**产生死锁主要原因**：系统资源不足、进程运行推进的顺序不合适、资源分配不当

![image-20240124150711282](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142341024.png)





**死锁案例演示**：

```java
import java.util.concurrent.TimeUnit;
// 死锁案例演示
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();
        new Thread(() -> {
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有A锁，希望获得B锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功获得B锁");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有B锁，希望获得A锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功获得A锁");
                }
            }
        }, "B").start();
    }
/*程序输出：
            A    自己持有A锁，希望获得B锁
            B    自己持有B锁，希望获得A锁
 */
}
```

#####  3.4.2 死锁的排查

**`jps`命令**：`jps` 是 `Java Virtual Machine Process Status` 工具的缩写，`jps` 命令用于列出正在运行的` Java`进程。`jps` 命令常用选项：

```
jps命令常用选项：
            -l：显示主类全名，如果主类的全名太长，则显示它的部分
            -m：显示传递给主类 main 方法的参数
            -v：显示传递给JVM的参数
```

**`jstack` 命令**：`jstack` 命令用于生成`Java` 线程转储，可以用于诊断 `Java` 进程的线程情况。`jstack` 命令可以帮助定位死锁、死循环或者性能问题

```bash
# 其中pid是Java进程的进程号
jstack命令的基本语法：jstack [ options ] pid

jstack命令常用选项:
                -l：以更详细的方式输出信息，包括锁的附加信息
                -F：当pid对应的Java进程没有响应时，强制生成线程转储信息
                -m：显示native方法的堆栈跟踪
```

**`jps`命令结合`jstack` 命令进行死锁排查**：

一、使用`jps -l`命令查看`java`进程的进程号

```bash
PS D:\learn> jps -l
2496 com.sgg.juc.locks.DeadLockDemo
23192 org.jetbrains.idea.maven.server.RemoteMavenServer36
23212 jdk.jcmd/sun.tools.jps.Jps
24396 org.jetbrains.jps.cmdline.Launcher
26572
```

二、使用 `jstack -l pid`命令将生成带有锁附加信息的线程转储，诊断死锁问题

```bash
PS D:\learn> jstack 2496
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.291-b10 mixed mode):
// 省略...
Found one Java-level deadlock:
=============================
"B":
  waiting to lock monitor 0x00000283e0dbbdc8 (object 0x0000000716cf4e68, a java.lang.Object),
  which is held by "A"
"A":
  waiting to lock monitor 0x00000283e0dbe5a8 (object 0x0000000716cf4e78, a java.lang.Object),
  which is held by "B"

Java stack information for the threads listed above:
===================================================
"B":
        at com.sgg.juc.locks.DeadLockDemo.lambda$main$1(DeadLockDemo.java:33)
        - waiting to lock <0x0000000716cf4e68> (a java.lang.Object)
        - locked <0x0000000716cf4e78> (a java.lang.Object)
        at com.sgg.juc.locks.DeadLockDemo$$Lambda$2/128526626.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:748)
"A":
        at com.sgg.juc.locks.DeadLockDemo.lambda$main$0(DeadLockDemo.java:19)
        - waiting to lock <0x0000000716cf4e78> (a java.lang.Object)
        - locked <0x0000000716cf4e68> (a java.lang.Object)
        at com.sgg.juc.locks.DeadLockDemo$$Lambda$1/2012232625.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.
```

**图形化界面进行死锁排查**：

`jconsole`是一个用于监视和管理`Java`虚拟机（`JVM`）的图形化工具。运行`jconsole`命令可以打开图形化工具，通过图形化工具可以查看程序的运行情况。`jconsole`还可以使用其他参数启动，以连接到特定的`Java`进程。例如，可以使用`jconsole <PID>` 命令连接到本地正在运行的Java进程。在图像界面的线程页面下点击`检测死锁`即可检测到死锁并查看死锁信息

![image-20240124160040329](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142342121.png)



###  3.5 写锁(独占锁)/读锁(共享锁)



###  3.6 自旋锁 spinLock



###  3.7 无锁->独占锁->读写锁->邮戳锁



###  3.8 无锁->偏向锁->轻量锁->重量锁

![image-20240301214319275](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142342646.png)





##  4. 线程中断 与`LockSupport`

###  4.1 线程中断 与  **`Thread`类中断`API`方法**

**线程中断机制**：一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止，自己来决定自己的命运。所以，`Thread.stop`，`Thread.suspend`, `Thread.resume`都已经被废弃了。其次，在`Java`中没有办法立即停止一条线程，然而停止线程却显得尤为重要，如取消一个耗时操作。因此，`Java`提供了一种用于停止线程的协商机制——中断，也即中断标识协商机制。中断只是一种协作协商机制，`Java`没有给中断增加任何语法，中断的过程完全需要程序员自己实现。若要中断一个线程，需要手动调用该线程的`interupt`方法，该方法也仅仅是将线程对象的中断标识设成`true`。接着需要自己写代码不断地检测当前线程的标识位，如果为`true`，表示别的线程请求这条线程中断，此时究竟该做什么需要自己写代码实现。每个线程对象中都有一个中断标识位，用于表示线程是否被中断。该标识位为`true`表示中断，为`false`表示未中断。通过调用线程对象的`interrupt`方法将该线程的标识位设为`true`。可以在别的线程中调用，也可以在自己的线程中调用

**`Thread`中断相关三大`API`方法**：

```java
public class Thread implements Runnable {
        // 实例方法，Just to set the interrupt flag
        // 实例方法interrupt()仅仅是设置线程的中断状态为true，发起一个协商而不会立刻停止线程
        public void interrupt() {
            // 省略...
        }
    
        // 静态方法，Thread.interrupted(); 判断线程是否被中断并清除当前中断状态
        // 这个方法做了两件事:
        //      1.返回当前线程的中断状态，测试当前线程是否已被中断
        //      2.将当前线程的中断状态清零并重新设为false，清除线程的中断状态
        //  此方法有点不好理解，如果连续两次调用此方法，则第二次调用将返回false，因为连续调用两次的结果可能不一样
        public static boolean interrupted() {
            return currentThread().isInterrupted(true);
        }
        // 实例方法，判断当前线程是否被中断（通过检查中断标志位)
        public boolean isInterrupted() {
            return isInterrupted(false);
        }
         // 省略...
}
```

###  4.2 如何中断运行线程

#####  4.2.1 `volatile`变量实现线程中断

**`volatile`关键字实现线程中断程序演示**：

```java
// 中断机制之通过volatile实现线程中断
public class InterruptDemo1 {
    // 静态的volatile布尔变量isStop表示线程的中断状态
    static volatile boolean isStop = false;

    public static void main(String[] args) {
        m1_volatile();
    }

    // 创建了两个线程t1和t2。t2线程在启动后将isStop设置为true
    // t1线程通过一个无限循环来检查isStop的值，如果isStop为true，则跳出循环结束线程
    private static void m1_volatile() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello volatile");
            }
        }, "t1").start();

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
/*程序输出：
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1 -----hello volatile
            t1   isStop被修改为true，程序停止
 */
}
```

#####  4.2.2 `AtomicBoolean`实现线程中断

**`AtomicBoolean`实现线程中断**：

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
// 线程中断机制之通过AtomicBoolean实现线程中断
public class InterruptDemo1 {
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        m2_atomicBoolean();
    }
    private static void m2_atomicBoolean() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello atomicBoolean");
            }
        }, "t1").start();

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }
//程序输出：
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1 -----hello atomicBoolean
//            t1     atomicBoolean被修改为true，程序停止
}
```

#####  4.2.3 `Thread`类`api`方法实现线程中断

**`Thread`类`api`方法实现线程中断**：在需要中断的线程中不断监听中断状态，一旦发生中断，就执行相应的中断处理业务逻辑`stop`线程`API`

```java
// 通过Thread类自带的中断api实例方法实现
public class InterruptDemo1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello interrupt api");
            }
        }, "t1");
        t1.start();

        System.out.println("-----t1的默认中断标志位：" + t1.isInterrupted());

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //t2向t1发出协商，将t1的中断标志位设为true希望t1停下来
        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
        //t1.interrupt();
    }
//程序输出：
//            -----t1的默认中断标志位：false
//            t1 -----hello interrupt api
//            t1 -----hello interrupt api
//            t1 -----hello interrupt api
//            t1 -----hello interrupt api
//            t1 -----hello interrupt api
//            t1 -----hello interrupt api
//            t1 -----hello interrupt api
//            t1     isInterrupted()被修改为true，程序停止
}
```

**`Thread`类`api`方法**：

```java
public class Thread implements Runnable {
        // 实例方法，Just to set the interrupt flag
        // 实例方法interrupt()仅仅是设置线程的中断状态为true，发起一个协商而不会立刻停止线程
        public void interrupt() {
            // 省略...
        }
    
        // 静态方法，Thread.interrupted(); 判断线程是否被中断并清除当前中断状态
        // 这个方法做了两件事:
        //      1.返回当前线程的中断状态，测试当前线程是否已被中断
        //      2.将当前线程的中断状态清零并重新设为false，清除线程的中断状态
        //  此方法有点不好理解，如果连续两次调用此方法，则第二次调用将返回false，因为连续调用两次的结果可能不一样
        public static boolean interrupted() {
            return currentThread().isInterrupted(true);
        }
        // 实例方法，判断当前线程是否被中断（通过检查中断标志位)
        public boolean isInterrupted() {
            return isInterrupted(false);
        }
         // 省略...
}
```

 **`Thread`类`interrupt()`方法源码分析**：

```java
public void interrupt() {
        if (this != Thread.currentThread())
            checkAccess();

        synchronized (blockerLock) {
            Interruptible b = blocker;
            if (b != null) {
                interrupt0();           // Just to set the interrupt flag
                b.interrupt(this);
                return;
            }
        }
        interrupt0();
    }
  
  // 调用更底层的C++代码
  private native void interrupt0();


具体来说，当对一个线程调用interrupt()时

      1.如果线程处于正常活动状态，那么会将该线程的中断标志设置为true，仅此而已。
      被设置中断标志的线程将继续正常运行，不受影响。所以，interrupt()并不能真正的中断线程，需要被调用的线程自己进行配合才行

      2.如果线程处于被阻塞状态（例如处于sleep, wait,join等状态)，在别的线程中调用当前线程对象的interrupt方法，
        那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常
```

**`Thread`类`isInterrupted()`方法**：

```java
    public boolean isInterrupted() {
        return isInterrupted(false);
    }

    // 调用更底层的C++代码
    /**
     * Tests if some Thread has been interrupted.  The interrupted state
     * is reset or not based on the value of ClearInterrupted that is
     * passed.
     */
    private native boolean isInterrupted(boolean ClearInterrupted);
```

**当前线程的中断标识为true，是不是线程就立刻停止?**  如果线程处于正常活动状态，那么会将该线程的中断标志设置为 `true`，仅此而已。被设置中断标志的线程将继续正常运行，不受影响。所以，`interrupt()`并不能真正的中断线程，需要被调用的线程自己进行配合才行

```java
import java.util.concurrent.TimeUnit;
public class InterruptDemo2 {
    public static void main(String[] args) {
        //实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("-----: " + i);
            }
            System.out.println("t1线程调用interrupt()后的的中断标识02：" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标识：" + t1.isInterrupted());//false

        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();//true
        System.out.println("t1线程调用interrupt()后的的中断标识01：" + t1.isInterrupted());//true

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1线程调用interrupt()后的的中断标识03：" + t1.isInterrupted());//????---false中断不活动的线程不会产生任何影响。
    }
//程序输出：
//            -----: 289
//            -----: 290
//            -----: 291
//    t1线程调用interrupt()后的的中断标识01：true
//            -----: 292
//            -----: 293
//            -----: 294
//            -----: 295
//            -----: 296
//            -----: 297
//            -----: 298
//            -----: 299
//            -----: 300
//    t1线程调用interrupt()后的的中断标识02：true
//    t1线程调用interrupt()后的的中断标识03：false
}
```

**`interrupt`方法补充说明**：如果线程处于被阻塞状态（例如处于`sleep`， `wait`，`join`等状态)，在别的线程中调用当前线程对象的`interrupt`方法，那么线程将立即退出被阻塞状态，并抛出一个`InterruptedException`异常

![image-20240125230524119](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142342787.png)

```java
import java.util.concurrent.TimeUnit;
/**
 * 如果线程处于被阻塞状态（例如处于sleep, wait,join等状态)，在别的线程中调用当前线程对象的interrupt方法，
 * 那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常
 */
public class InterruptDemo3 {
    public static void main(String[] args) {
        /**
         * 1 中断标志位，默认false
         * 2 t2 ----> t1发出了中断协商，t2调用t1.interrupt()，中断标志位true
         * 3 中断标志位true，正常情况，程序停止，
         * 4 中断标志位true，异常情况，InterruptedException，将会把中断状态将被清除，并且将收到InterruptedException 。中断标志位false
         * 导致无限循环
         * <p>
         * 5 在catch块中，需要再次给中断标志位设置为true，2次调用停止程序才OK
         */
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t " + "中断标志位：" + Thread.currentThread().isInterrupted() + " 程序停止");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // 如果注释掉下面一行代码 Thread.currentThread().interrupt(); ，会陷入死循环
                    Thread.currentThread().interrupt();//为什么要在异常处，再调用一次？？
                    e.printStackTrace();
                }
                System.out.println("-----hello InterruptDemo3");
            }
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> t1.interrupt(), "t2").start();
    }
}
```

**`Thread`类的静态方法`Thread.interrupted()`**：静态方法`public static boolean interrupted()`用于判断线程是否被中断并清除当前中断状态。此方法有点不好理解，如果连续两次调用此方法，则第二次调用将返回false，因为连续调用两次的结果可能不一样。`interrupted()`方法做了两件事：1.返回当前线程的中断状态，测试当前线程是否已被中断。2.将当前线程的中断状态清零并重新设为false，清除线程的中断状态

```java
public class Thread implements Runnable {
    // 中断标识被清空，如果该方法被连续调用两次，第二次调用将返回false
    // 除非当前线程在第一次和第二次调用该方法之间再次被interrupt
    /**
     * Tests whether the current thread has been interrupted.  The
     * <i>interrupted status</i> of the thread is cleared by this method.  In
     * other words, if this method were to be called twice in succession, the
     * second call would return false (unless the current thread were
     * interrupted again, after the first call had cleared its interrupted
     * status and before the second call had examined it).
     *
     * <p>A thread interruption ignored because a thread was not alive
     * at the time of the interrupt will be reflected by this method
     * returning false.
     *
     * @return  <code>true</code> if the current thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see #isInterrupted()
     * @revised 6.0
     */
    public static boolean interrupted() {
        return currentThread().isInterrupted(true);
    }
}
```

`Thread`类的静态方法`Thread.interrupted()`演示：

```java
import java.util.concurrent.locks.LockSupport;
public class InterruptDemo4 {
    public static void main(String[] args) {
        //测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态，
        // 第二次再调用时中断状态已经被清除，将返回一个false。
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("----1");
        Thread.currentThread().interrupt();// 中断标志位设置为true
        System.out.println("----2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        LockSupport.park();
        Thread.interrupted();//静态方法
        Thread.currentThread().isInterrupted();//实例方法
/*程序输出：
            main	false
            main	false
            ----1
            ----2
            main	true
            main	false
 */
    }
}
```

**`public boolean isInterrupted()` 和`public static boolean interrupted()`  两者对比**：相同点：都会返回中断状态。不同点：方法的注释也清晰的表达了“中断状态将会根据传入的`ClearInterrupted`参数值确定是否重置”。所以，静态方法`interrupted`将会清除中断状态（传入的参数`Clearlnterrupted`为`true)` ，实例方法`isInterrupted`则不会（传入的参数`ClearInterrupted`为`false`）

```java
    /**
     * Tests whether the current thread has been interrupted.  The
     * <i>interrupted status</i> of the thread is cleared by this method.  In
     * other words, if this method were to be called twice in succession, the
     * second call would return false (unless the current thread were
     * interrupted again, after the first call had cleared its interrupted
     * status and before the second call had examined it).
     *
     * <p>A thread interruption ignored because a thread was not alive
     * at the time of the interrupt will be reflected by this method
     * returning false.
     *
     * @return  <code>true</code> if the current thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see #isInterrupted()
     */
    public static boolean interrupted() {
        return currentThread().isInterrupted(true);
    }

    /**
     * Tests whether this thread has been interrupted.  The <i>interrupted
     * status</i> of the thread is unaffected by this method.
     *
     * <p>A thread interruption ignored because a thread was not alive
     * at the time of the interrupt will be reflected by this method
     * returning false.
     *
     * @return  <code>true</code> if this thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see     #interrupted()
     * @revised 6.0
     */
    public boolean isInterrupted() {
        return isInterrupted(false);
    }

    /**
     * Tests if some Thread has been interrupted.  The interrupted state
     * is reset or not based on the value of ClearInterrupted that is
     * passed.
     */
    private native boolean isInterrupted(boolean ClearInterrupted);
```

**`Thread`类中断相关`api`方法总结**：

```
线程中断相关的方法：
        public void interrupt() , interrupt()方法是一个实例方法
        通知目标线程中断，也仅是设置目标线程的中断标志位为true

        public boolean isInterrupted(), isInterrupted()方法是一个实例方法
        判断当前线程是否被中断（通过检查中断标志位）并获取中断标志

        public static boolean interrupted()，Thread类的静态方法interrupted()
        返回当前线程的中断状态真实值(boolean类型)后会将当前线程的中断状态设为false
        调用方法之后会清除当前线程的中断标志位的状态（将中断标志置为false了)，返回当前值并清零置false
```

###  4.3 `LockSupport`

#####  4.3.1   `LockSupport`简介 | 线程的等待和唤醒

**`LockSupport`简介**：`LockSupport`是`Java`中`JUC（Java Util Concurrent）`包提供的一个工具类。`LockSupport`是用于创建锁和其他同步类的基本线程阻塞原语，用于支持线程的阻塞和唤醒操作。它的主要作用是允许线程阻塞和唤醒，比如线程的挂起和恢复操作。与传统的`synchronized`和`wait/notify`机制相比，`LockSupport`提供了更灵活的线程阻塞和唤醒功能。使用`LockSupport`可以在不需要使用传统的`Object`监视器、`synchronized`或`wait/notify`机制的情况下，实现线程挂起和唤醒，从而提供更灵活、精细的线程控制能力。`LockSupport`中的`park()`和`unpark()`的作用分别是阻塞线程和解除阻塞线程

**`LockSupport`的主要方法**：

- `public static void park()`: 阻塞当前线程，使其进入`waiting`状态，直到调用`unpark`方法或被中断
- `public static void park(Object blocker)`: 阻塞当前线程，同时关联一个`blocker`对象，用于标识阻塞的原因
- `public static void unpark(Thread thread)`: 唤醒指定线程，使其从`park`状态恢复到可运行状态
- `public static void parkNanos(long nanos)`: 阻塞当前线程，最多阻塞指定的纳秒数，直到调用`unpark`方法、达到指定时间或被中断
- `public static void parkUntil(long deadline)`: 阻塞当前线程，直到调用`unpark`方法或到达指定的时间戳，或者被中断

 **三种让线程等待和唤醒的方法**：

```
方法一：使用object中的wait()方法让线程等待，使用Object中的notify()方法唤醒线程
方法二：使用JUC包中condition的await()方法让线程等待，使用signal()方法唤醒线程
方法三：LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程。LockSupport类中的park等待和unpark唤醒
```

#####  4.3.2 `object`的`wait()`方法（等待） 和  `notify()`方法（唤醒）

 **`object`的`wait()`方法（等待） 和  `notify()`方法（唤醒）**：在`Java`中，每个对象都有一个等待集合`（wait set）`和一个锁`（lock）`。`wait()`方法和`notify()`方法是`Object`类中用于实现线程间通信的方法。`wait()`方法的作用是使当前线程进入等待状态，直到其他线程调用了相同对象的`notify()`方法或`notifyAll()`方法，或者指定的等待时间到期。当一个线程调用了对象的`wait()`方法后，它就会释放持有的锁，并进入等待集合中，直到被其他线程唤醒。`wait()`方法有几个重载方法，其中最常用的是无参的`wait()`方法。`notify()`方法的作用是唤醒一个正在等待该对象的线程。如果有多个线程在等待该对象，那么只会唤醒其中一个线程，并使其进入就绪状态。`notifyAll()`方法则会唤醒所有等待该对象的线程。当一个线程调用了对象的`notify()`或`notifyAll()`方法后，等待集合中的线程将会竞争锁资源，只有获得锁的线程才能继续执行。`wait()`方法和`notify()`方法的使用通常需要与`synchronized`关键字一起使用，以确保线程安全。`wait()`方法必须在`synchronized`块中调用，否则会抛出`IllegalMonitorStateException`异常。`wait()`方法和`notify()`方法是实现线程间通信的重要工具，可以用于解决多线程并发访问共享资源时的同步问题。通过`wait()`方法和`notify()`方法的配合使用，可以实现线程的等待和唤醒，从而保证多个线程之间的协调和同步

**让线程等待和唤醒的方法一**：使用`object`中的`wait()`方法让线程等待，使用`Object`中的`notify()`方法唤醒线程

```java
public class LockSupportDemo {
    public static void main(String[] args) {
        syncWaitNotify();
    }
    private static void syncWaitNotify() {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
// 程序输出：
//            t1	 ----come in
//            t2	 ----发出通知
//            t1	 ----被唤醒
    }
}
```

**`wait()`方法和`notify()`方法的注意事项一**：`wait()`方法和`notify()`方法的使用通常需要与`synchronized`关键字一起使用，以确保线程安全。`wait()`方法必须在`synchronized`块中调用，否则会抛出`IllegalMonitorStateException`异常


![image-20240126012734692](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142342082.png)

**`wait()`方法和`notify()`方法的注意事项二**：要先执行`wait()`方法再执行`notify()`方法，如果将`wait()`方法和`notify()`方法前面程序无法执行，无法唤醒

```java
public class LockSupportDemo {
    public static void main(String[] args) {
        syncWaitNotify();
    }
    private static void syncWaitNotify() {
        Object objectLock = new Object();
        new Thread(() -> {
            // 让线程1阻塞1秒钟，保证出现先notify后wait的情况
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();
        //暂停几秒钟线程
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
// 程序输出（程序一直卡死，无法退出）：
//            t2	 ----发出通知
//            t1	 ----come in
    }
}
```

**`wait()`方法和`notify()`方法的注意事项**：`wait()`方法在执行`notify()`方法必须要在同步块或者同步方法里面，且成对出现使用。必须先执行`wait()`方法再执行`notify()`方法

#####  4.3.3  `condition`的`await()`方法（等待）和 `signal()`方法（唤醒）

在`Java`中，`Condition`接口是在`Java 5`中引入的，用于在多线程编程中实现线程间的通信和协调。`Condition`接口提供了`await()`和`signal()`方法来实现线程的等待和唤醒操作。`await()`方法用于使当前线程进入等待状态，直到接收到其他线程发出的信号或被中断。当线程调用`await()`方法后，它会释放当前持有的锁，并进入等待状态，直到其他线程调用相同`Condition`对象的`signal()`或`signalAll()`方法。被唤醒的线程会尝试重新获取锁并继续执行。`await()`方法可以通过传入超时时间来设置最长等待时间，避免线程永久等待。`signal()`方法用于唤醒一个等待在`Condition`上的线程，使其继续执行。如果有多个线程在等待，只有一个线程会被唤醒，具体唤醒哪个线程是不确定的，取决于线程调度器的策略。如果没有线程在等待，调用`signal()`方法不会产生任何效果。`Condition`接通常与`Lock`接口一起使用，常见的实现类是`ReentrantLock`和`Condition`的组合。通过`Lock`接口的`newCondition()`方法可以创建一个`Condition`对象。使用`Condition`接口的`await()`和`signal()`方法可以实现更灵活的线程间通信和协调，相比于传统的`synchronized`和`wait()`、`notify()`方法，`Condition`提供了更多的功能和灵活性

**让线程等待和唤醒的方法二**：使用`JUC`包中`condition`的`await()`方法让线程等待，使用`signal()`方法唤醒线程

```java
// 正常情况演示：
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class LockSupportDemo {
    public static void main(String[] args) {
        lockAwaitSignal();
    }
    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
/*程序输出：
            t1	 ----come in
            t2	 ----发出通知
            t1	 ----被唤醒
 */
}
```

**`await()`方法和`signal()`方法使用注意事项一**：`await()`和`signal()`必须在`lock()`和`unlock()`之间，才能正确调用`condition`中线程等待和唤醒的方法。`condition.await();`和 `condition.signal();`都会触发`IllegalMonitorStateException`异常

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class LockSupportDemo {
    public static void main(String[] args) {
        lockAwaitSignal();
    }
    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
//            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                lock.unlock();
            }
        }, "t1").start();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
//            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
//                lock.unlock();
            }
        }, "t2").start();
    }
// 程序执行报错:
//    t1	 ----come in
//    Exception in thread "t1" java.lang.IllegalMonitorStateException
//    at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer.fullyRelease(AbstractQueuedSynchronizer.java:1723)
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2036)
//    at com.bilibili.juc.LockSupport.LockSupportDemo.lambda$lockAwaitSignal$0(LockSupportDemo.java:115)
//    at java.lang.Thread.run(Thread.java:748)
//    Exception in thread "t2" java.lang.IllegalMonitorStateException
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.signal(AbstractQueuedSynchronizer.java:1939)
//    at com.bilibili.juc.LockSupport.LockSupportDemo.lambda$lockAwaitSignal$1(LockSupportDemo.java:132)
}
```

**`await()`方法和`signal()`方法使用注意事项二**：一定要先`await()`方法后`signal()`方法，否则无法唤醒

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
    public static void main(String[] args) {
        lockAwaitSignal();
    }

    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                // 阻塞当前线程,保证signal在await之前执行
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
// 程序输出(程序阻塞):
//        t2	 ----发出通知
//        t1	 ----come in
}
```

**`await()`方法和`signal()`方法使用注意事项总结**：`Condtion`中的线程等待和唤醒方法,需要先获取锁。一定要先`await()`方法后`signal()`方法，否则无法唤醒

#####  4.3.4  `LockSupport`类中的`park`等待和`unpark`唤醒

**让线程等待和唤醒的方法三**：`LockSupport`类通过`park()`和`unpark(thread)`方法来实现阻塞和唤醒线程的操作

**`LockSupport`类**：`LockSupport`类是用来创建锁和其他同步类的基本线程阻塞原语。`LockSupport`类使用了一种名为`Permit(许可）`的概念来做到阻塞和唤醒线程的功能，每个线程都有一个许可`(permit)`,但与`Semaphore`不同的是，许可的累加上限是1。`LockSupport`类与使用它的每个线程关联一个许可证(在`Semaphore`类的意义上)。如果许可证可用，将立即返回`park` ，并在此过程中消费许可证。否则可能会阻止，如果尚未提供许可，则致电`unpark`获得许可。(与`semaphores`不同，许可证不会累积。最多只有一个。可靠的使用需要使用`volatie`(或原子)变量来控制何时停放或取消停放。对于易失性变量访问保持对这些方法的调用的顺序，但不一定是非易失性量访问。`LockSupport`类可以阻塞当前线程以及唤醒指定被阻塞的线程，`LockSupport`类通过`park()`和`unpark(thread)`方法来实现阻塞和唤醒线程的操作

  **`LockSupport`类中的`park`等待和`unpark`唤醒**：`park`方法用于使当前线程进入等待状态，它可以被其他线程通过`unpark`方法唤醒。`unpark`方法用于唤醒一个被`park`方法阻塞的线程，使其恢复执行。`LockSupport`类的`park`和`unpark`方法提供了一种更灵活的线程阻塞和唤醒机制，相比于传统的`wait`和`notify`方法，它不需要依赖于特定的对象锁，而是直接操作线程本身。这样可以更方便地控制线程的阻塞和唤醒，避免了因为等待对象锁而导致的死锁问题。同时，`LockSupport`类还提供了一些其他的方法，用于线程的阻塞和唤醒操作，如`parkUntil`和`parkNanos`等

`park`方法有以下几种形式：

```
1.park()：                     使当前线程进入等待状态，直到被其他线程调用unpark方法唤醒。
2.park(Object blocker)：       使当前线程进入等待状态，并关联一个blocker对象，直到被其他线程调用`unpark`方法唤醒。
3.parkNanos(long nanos)：      使当前线程进入等待状态，最多等待指定的纳秒数，直到被其他线程调用`unpark`方法唤醒或等待超时。
4.parkNanos(Object blocker, long nanos)：
                               使当前线程进入等待状态，并关联一个blocker对象，最多等待指定纳秒数，直到被其他线程调用`unpark`方法唤醒或等待超时。
5.parkUntil(long deadline)：    使当前线程进入等待状态，直到被其他线程调用`unpark`方法唤醒或等待超时
```

`unpark`方法有以下几种形式：

```
1.unpark(Thread thread)：   唤醒指定的线程,唤醒处于阻塞状态的指定线程
2.unpark(Object blocker)：  唤醒与指定blocker对象关联的线程
```

  `LockSupport`类中的`park()`方法和`unpark()`方法：`permit`许可证默认没有不能放行，所以一开始调`park()`方法当前线程就会阻塞，直到别的线程给当前线程的发放`permit`，`park`方法才会被唤醒。调用`unpark(thread)`方法后，就会将`thread`线程的许可证`permit`发放，会自动唤醒`park`线程，即之前阻塞中的`LockSupport.park()`方法会立即返回

```java
public class LockSupport {
    /**
     * Disables the current thread for thread scheduling purposes unless the
     * permit is available.
     *
     * <p>If the permit is available then it is consumed and the call
     * returns immediately; otherwise the current thread becomes disabled
     * for thread scheduling purposes and lies dormant until one of three
     * things happens:
     *
     * <ul>
     *
     * <li>Some other thread invokes {@link #unpark unpark} with the
     * current thread as the target; or
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts}
     * the current thread; or
     *
     * <li>The call spuriously (that is, for no reason) returns.
     * </ul>
     *
     * <p>This method does <em>not</em> report which of these caused the
     * method to return. Callers should re-check the conditions which caused
     * the thread to park in the first place. Callers may also determine,
     * for example, the interrupt status of the thread upon return.
     */
    public static void park() {
        UNSAFE.park(false, 0L);
    }
    
    /**
     * Makes available the permit for the given thread, if it
     * was not already available.  If the thread was blocked on
     * {@code park} then it will unblock.  Otherwise, its next call
     * to {@code park} is guaranteed not to block. This operation
     * is not guaranteed to have any effect at all if the given
     * thread has not been started.
     *
     * @param thread the thread to unpark, or {@code null}, in which case
     *        this operation has no effect
     */
    public static void unpark(Thread thread) {
        if (thread != null)
            UNSAFE.unpark(thread);
    }
}
// Unsafe类
public final class Unsafe {
    public native void park(boolean var1, long var2);
    public native void unpark(Object var1);
}
// permit许可证默认没有不能放行，所以一开始调park()方法当前线程就会阻塞，直到别的线程给当前线程的发放permit，park方法才会被唤醒
// 调用unpark(thread)方法后，就会将thread线程的许可证permit发放，会自动唤醒park线程，即之前阻塞中的LockSupport.park()方法会立即返回
```

**`LockSupport`类中的`park`等待和`unpark`唤醒理解**：`LockSupport`是用来创建锁和其他同步类的基本线程阻塞原语。`LockSupport`是一个线程阻塞工具类，所有的方法都是静态方法，可以让线程在任意位置阻塞，阻塞之后也有对应的唤醒方法。归根结底，`LockSupport`调用的`Unsafe`中的`native`代码，`LockSupport`提供`park()`和`unpark()`方法实现阻塞线程和解除线程阻塞的过程。`LockSupport`和每个使用它的线程都有一个许可`(permit)`关联。每个线程都有一个相关的`permit`, `permit`最多只有一个，重复调用`unpark`也不会积累凭证。形象的理解：线程阻塞要消耗凭证`(permit)`，这个凭证最多只有1个。当调用`park`方法时，如果有凭证，则会直接消耗掉这个凭证然后正常退出。如果无凭证，就必须阻塞等待凭证可用。而`unpark`则相反，它会增加一个凭证，但凭证最多只能有1个，累加无效

**`LockSupport`代码演示一**：没有利用`synchronized`或者`lock`加锁也可以使用`LockSupport`的`park()`方法和`unpark()`方法

```java
// 正常+无锁块要求
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
/*程序执行输出：
        t1	 ----come in1706344026613
        t2	 ----发出通知
        t1	 ----被唤醒1706344027620
 */
}
```

**`LockSupport`代码演示二**：`LockSupport`支持先`unpark`唤醒后`park`等待。`sleep`方法3秒后醒来，执行`park`无效，没有阻塞效果，解释如下先执行了`unpark(t1)`导致上面的`park`方法形同虚设无效，时间一样。类似高速公路的ETC，提前买好了通行证`unpark`，到闸机处直接抬起栏杆放行了，没有`park`拦截了

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
// LockSupport支持先唤醒后等待
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出：
//                t2     ----发出通知
//                t1     ----come in1706344340866
//                t1     ----被唤醒1706344340866
}
```

**`LockSupport`代码演示三**：线程阻塞要消耗凭证`(permit)`，这个凭证最多只有1个。当调用`park`方法时，如果有凭证，则会直接消耗掉这个凭证然后正常退出。如果无凭证，就必须阻塞等待凭证可用。而`unpark`则相反，它会增加一个凭证，但凭证最多只能有1个，累加无效

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出：
//            t2     ----发出通知
//            t1     ----come in1706347362930
//            t1     ----被唤醒1706347362930
}
```

**`LockSupport`代码演示四**：线程阻塞要消耗凭证`(permit)`，这个凭证最多只有1个。当调用`park`方法时，如果有凭证，则会直接消耗掉这个凭证然后正常退出。如果无凭证，就必须阻塞等待凭证可用。而`unpark`则相反，它会增加一个凭证，但凭证最多只能有1个，累加无效

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出（程序出现阻塞）：
//                            t2     ----发出通知
//                            t1     ----come in1706347007391
}
```



##  5.  `Java`内存模型`JMM`

###  5.1 `JMM`简介

**多核处理器和`	CPU`多级缓存**：数据从本地磁盘到主存到`CPU`缓存，也就是从硬盘到内存到CPU。程序的操作一般就是从数据库查数据到内存然后到`CPU`进行计算

![image-20240127175115612](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142343106.png)

**`JMM`产生的原因和作用**：因为`CPU`有这么多级的缓存(`cpu`和物理主内存的速度是不一致)，`CPU`的运行并不是直接操作内存而是先把内存里边的数据读到缓存，进行内存的读和写操作时就会造成不一致的问题。`JVM`试图定义一种`Java`内存模型（`java Memory Model`，简称`JMM`）来屏蔽掉各种硬件和操作系统的内存访问差异，以实现让`Java`程序在各种平台下都能达到一致的内存访问效果。在多线程环境下，多个线程可能同时访问共享的数据，如果没有合适的同步机制，就会导致数据的不一致性和错误的结果。`JMM`通过定义内存模型，规定了多线程编程中如何正确地进行内存访问和同步操作，从而避免线程安全问题。编译器在编译过程中会对代码进行优化，例如重排序、内联等。这些优化可能会改变代码的执行顺序，从而导致多线程程序的行为不可预测。`JMM`通过指定内存屏障和禁止重排序规则，限制了编译器的优化行为，保证了多线程程序的正确性。`Java`是一种平台的编程语言，可以在不同的操作系统和硬件平台上运行。不同的平台可能有不同的内存模型和处理器架构，`JMM`提供了一个统一的内存模型，使得`Java`程序在不同平台上的行为保持一致。`JMM`(`Java`内存模型`Java Memory Model`，简称`JMM`)本身是一种抽象的概念，并不真实存在。`JMM`仅仅描述的是一组约定或规范，通过这组规范定义了程序中(尤其是多线程)各个变量的读写访问方式，并决定一个线程对共享变量的写入何时以及如何变成对另一个线程可见，关键技术点都是围绕多线程的原子性、可见性和有序性展开的

![image-20240127180942584](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142344819.png)



**`JMM`作用总结**：1.通过`JMM`来实现线程和主内存之间的抽象关系。2.屏蔽各个硬件平台和操作系统的内存访问差异以实现让`Java`程序在各种平台下都能达到一致的内存访问效果

###  5.2 `JMM`与并发编程的三大特性

 `JMM`与并发编程的三大特性：`JMM`的关键技术点都是围绕多线程的原子性、可见性和有序性展开的

#####  5.2.1  可见性

 **可见性**：可见性是指当一个线程修改了某一个共享变量的值，其他线程是否能够立即知道该变更。可见性指的是当一个线程修改了某个共享变量的值后，其他线程能够立即看到这个修改的值

![image-20240127203254595](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142344896.png)

 **`JMM`规范下，多线程对变量的读写过程**：`JMM`定义了线程和主内存之间的抽象关系：1.线程之间的共享变量存储在主内存中(从硬件角度来说就是内存条) 2.每个线程都有一个私有的本地工作内存，本地工作内存中存储了该线程用来读/写共享变量的副本(从硬件角度来说就是`CPU`的缓存，比如寄存器、`L1`、`L2`、`L3`缓存等)。由于`JVM`运行程序的实体是线程，而每个线程创建时`JVM`都会为其创建一个工作内存(有些地方称为栈空间)，工作内存是每个线程的私有数据区域，而`Java`内存模型中规定所有变量都存储在主内存，主内存是共享内存区域，所有线程都可以访问，但线程对变量的操作(读取赋值等)必须在工作内存中进行，首先要将变量从主内存拷贝到的线程自己的工作内存空间，然后对变量进行操作，操作完成后再将变量写回主内存，不能直接操作主内存中的变量，各个线程中的工作内存中存储着主内存中的变量副本拷贝，因此不同的线程间无法访问对方的工作内存，线程间的通信(传值)必须通过主内存来完成，其简要访问过程如下图：

![image-20240127220526605](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142344528.png)



 **`JMM`对可见性的保证**：`JMM`规定了所有的变量都存储在主内存中，系统主内存共享变量数据修改后被写入的时机是不确定的，多线程并发下很可能出现"脏读"。每个线程都有自己的工作内存、线程自己的工作内存中保存了该线程使用到的变量的主内存副本拷贝，线程对变量的所有操作（读取，赋值等）都必需在线程自己的工作内存中进行，而不能够直接读写主内存中的变量。不同线程之间也无法直接访问对方工作内存中的变量，线程间变量值的传递均需要通过主内存来完成。

`CPT`补充：可见性指的是当一个线程修改了某个共享变量的值后，其他线程能够立即看到这个修改的值。为了实现可见性，`JMM`使用了一些机制，如"主内存"和"工作内存"。主内存是所有线程共享的内存区域，包含了所有的共享变量。工作内存是每个线程独有的内存区域，用于存储该线程使用到的共享变量的拷贝。当一个线程要访问共享变量时，它首先要将共享变量从主内存中拷贝到自己的工作内存中，然后对该变量进行操作。操作完成后，将结果写回主内存。`JMM`通过控制主内存和工作内存之间的交互，来保证多线程的可见性。具体来说，当一个线程对共享变量进行修改时，它会首先将修改的值写入自己的工作内存，然后再将修改的值刷新到主内存。其他线程在访问该共享变量时，会先将主内存中的值读取到自己的工作内存，然后再进行操作。`JMM`通过以下规则来保证可见性：1.线程对共享变量的修改操作必须写回主内存。2.线程对共享变量的读取操作必须从主内存中读取最新的值

![image-20240127211323871](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142345008.png)

#####  5.2.2 原子性

原子性：指一个操作是不可打断的，即多线程环境下，操作不能被其他线程干扰

**线程脏读场景**：

```
1.主内存中有变量x，初始值为0
2.线程A要将x加1，先将x=0拷贝到自己的私有内存中，然后更新x的值
3.线程A将更新后的x值回刷到主内存的时间是不固定的
4.刚好在线程A没有回刷x到主内存时，线程B同样从主内存中读取x，此时为0，和线程A一样的操作，最后期盼的x=2就会变成x=1
```

#####  5.2.3   有序性

**指令重排序**：对于一个线程的执行代码而言，我们总是习惯性认为代码的执行总是从上到下，有序执行。但为了提升性能，编译器和处理器通常会的指令序列进行重新排序。`Java`规范规定`JVM`线程内部维持顺序化语义，即只要程序的最终结果与它顺序化执行的结果相等，那么指令的执行顺序可以与代码顺序不一致，此过程叫指令的重排序。`JVM`能根据处理器特性(`CPU`多级缓存系统、多核处理器等）适当的对机器指令进行重排序，使机器指令能更符合`CPU`的执行特性，最大限度的发挥机器性能。但是，指令重排可以保证串行语义一致，但没有义务保证多线程间的语义也一致(即可能产生"脏读")，简单说，两行以上不相干的代码在执行的时候有可能先执行的不是第一条，不见得是从上到下顺序执行，执行顺序会被优化。合法的重排序可以保证单线程环境里面确保程序最终执行结果和代码顺序执行的结果一致，处理器在进行重排序时必须要考虑指令之间的数据依赖性。多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测

![image-20240127213555717](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142345122.png)

```java
public void mySort(){
int x = 11;  //语句1
int y = 12;  //语句2
x = x + 5;   //语句3
y = x * x;   //语句4
}
可行的重排序：
        1234
        2134
        1324
问题：请问语句4可以重排后变成第一个条吗?
```

###  5.3 `JMM`的`happens-before`原则

#####  5.3.1  `happens-before`原则简介

**`happens-before`（先行发生）原则**：在`JMM`中，如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须存在`happens-before`(先行发生)原则。如果`Java`内存模型中所有的有序性都仅靠`volatile`和`synchronized`来完成，那么有很多操作都将会变得非常繁琐，但是我们在编写`Java`并发代码的时候并没有察觉到这一点。我们没有时时、处处、次次，添加`volatile`和`synchronized`来完成程序，这是因为`Java`语言中`JMM`原则下有一个“先行发生”(`Happens-Before`)的原则起到了限制和规范的作用。具体来说，如果一个操作A `happens-before`另一个操作B，那么A操作的结果对于B操作是可见的，`happens-before`原则保证了在多线程环境下，程序中操作的有序性和可见性。通过`happens-before`原则，`JMM`保证了程序中的操作能够按照一定的顺序执行，并且对于其他线程是可见的，从而避免了多线程并发执行时可能出现的数据竞争和不一致性问题。`happens-before`原则是判断数据是否存在竞争，线程是否安全的非常有用的手段。依赖这个原则，可以通过几条简单规则一揽子解决并发环境下两个操作之间是否可能存在冲突的所有问题，而不需要陷入`Java`内存模型苦涩难懂的底层编译原理之中

**x、y案例说明**：

```
x = 5     线程A执行
y = x     线程B执行
上述称之为：写后读

问题：y是否等于5呢?
如果线程A的操作（x=5) happens-before(先行发生)线程B的操作（y=x),那么可以确定线程B执行后y=5一定成立;
如果他们不存在happens-before原则，那么y=5不一定成立。
这就是happens-before原则的威力——保证可见性和有序性
```

**`happens-before`总原则**：1.如果一个操作`happens-before`另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前。 2.两个操作之间存在`happens-before`关系，并不意味着一定要按照`happens-before`原则制定的顺序来执行。如果重排序之后的执行结果与按照`happens-before`关系来执行的结果一致，那么这种重排序并不非法

#####  5.3.2  `happens-before`的八条规则

**1.次序规则**：一个线程内，按照代码顺序，写在前面的操作先行发生于写在后面的操作

```
加深说明：前一个操作的结果可以被后续的操作获取。讲白点就是前面一个操作把变量x赋值为1，那后面一个操作肯定能知道x已经变成了1
```

**2.锁定规则**：一个`unLock`操作先行发生于后面(这里的“后面”是指时间上的先后)对同一个锁的`lock`操作

![image-20240128001023579](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142345312.png)

**3.`volatile`变量规则**：对一个`volatile`变量的写操作先行发生于后面对这个变量的读操作，前面的写对后面的读是可见的，这里的“后面”同样是指时间上的先后

**4.传递规则**：如果操作A先行发生于操作B，而操作B又先行发生于操作c，则可以得出操作A先行发生于操作C

**5.线程启动规则(`Thread Start Rule`)**：`Thread`对象的`start()`方法先行发生于此线程中的每一个动作

**6.线程中断规则(`Thread Interruption Rule`)**：对线程`interrupt()`方法的调用先行发生于被中断线程的代码检测到中断事件的发生

```
说明：也可以通过Thread.interrupted()检测到是否发生中断。也就是说要先调用interrupt()方法设置过中断标志位，才能检测到中断发生
```

**7.线程终止规则(`Thread Termination Rule`)**：线程中的所有操作都先行发生于对此线程的终止检测，可以通过`isAlive()`等手段检测线程是否已经终止执行

**8.对象终结规则(`Finalizer Rule`)**：一个对象的初始化完成（构造函数执行结束)先行发生于它的`finalize()`方法的开始

```
说明：也就是对象没有完成初始化之前，是不能调用finalized()方法的
```

#####  5.3.3  `happens-before`原则总结

**`happens-before`原则总结**：在`Java`语言里面，`Happens-Before`的语义本质上是一种可见性。A `Happens-Before` B意味着A发生过的事情对B来说是可见的，无论A事件和B事件是否发生在同一个线程里。`JMM`的设计分为两部分：一部分是面向我们程序员提供的，也就是`happens-before`规则，它通俗易懂的向程序员阐述了一个强内存模型，只要理解`happens-before`规则，就可以编写并发安全的程序了。另一部分是针对`JVM`实现的，为了尽可能少的对编译器和处理器做约束从而提高性能，`JMM`在不影响程序执行结果的前提下对其不做要求，即允许优化重排序。我们只需要关注前者就好了，也就是理解`happens-before`规则即可，其它繁杂的内容有`JMM`规范结合操作系统给我们搞定，我们只写好代码即可

 **利用`happens-before`规则判断线程是否安全**：假设存在线程A和B，线程A先(时间上的先后）调用了`setValue()`，然后线程B调用了同一个对象的`getValue()`，那么线程B收到的返回值是什么?

```java
public class JMMTestDemo {
    private int value = 0;
    public int getValue() {
        return value;
    }
    public int setValue() {
        return ++value;
    }
}
// 假设存在线程A和B，
// 线程A先（时间上的先后）调用了setValue(),
// 然后线程B调用了同一个对象的getValue(),
// 那么线程B收到的返回值是什么?
```

我们就这段代码分析一下`happens-before`的规则(规则5、6、7、8可以忽略，因为他们和这段代码毫无关系)：1.由于两个方法是由不同的线程调用，不在同一个线程中，所以肯定不满足程序次序规则  2.两个方法都没有使用锁，所以不满足锁定规则  3.变量不是用volatile修饰的，所以volatile变量规则不满足  4.传递规则肯定不满足 。所以无法通过`happens-before`原则推导出线程A `happens-before`线程B，虽然可以确认在时间上线程A优先于线程B指定，但就是无法确认线程B获得的结果是什么，所以这段代码不是线程安全的。那么怎么修复这段代码呢？

**修复方法一**：把`getter/setter`方法都定义为`synchronized`方法。加上`synchronized`就解决了上述问题，可以保证这段代码是线程安全的。但是锁的粒度太大，不适合并发量太大的情况

```java
// 修复01：把getter/setter方法都定义为synchronized方法
public class JMMTestDemo {
    private int value = 0;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int setValue() {
        return ++value;
    }
}
```

**修复方法二**：把`value`定义为`volatile`变量，由于`setter`方法对`value`的修改不依赖`value`的原值，满足`volatile`关键字使用场景

```java
//  修复02：把value定义为volatile变量，由于setter方法对value的修改不依赖value的原值，满足volatile关键字使用场景
public class JMMTestDemo {
    /**
     * 使用：把value定义为volatile变量，由于setter方法对value的修改不依赖value的原值，满足volatile关键字使用场景
     * 理由：利用volatile保证读取操作的可见性;利用synchronized保证复合操作的原子性结合使用锁和volatile变量来减少同步的开销
     */
    private volatile int value = 0;

    public int getValue() {
        return value;   // 利用volatile保证读取操作的可见性
    }

    public synchronized int setValue() {
        return ++value;   // 利用synchronized保证复合操作的原子性
    }
}
```

##  6. `volatile`关键字 与 `JMM`

###  6.1 `volatile`三大特性

**`volatile`关键字**：当一个变量被声明为`volatile`时，编译器和`JVM`会确保每次访问该变量时都从内存中读取，而不是使用缓存值。同时，对`volatile`变量的写操作也会立即刷新到内存中，而不是延迟到某个时刻。使用`volatile`关键字修饰的变量具有以下特性：1.**可见性**：对一个`volatile`变量的写操作对所有线程可见，即一个线程对变量的修改对其他线程是可见的。2.**有序性**：编译器和处理器不会重新排序被`volatile`修饰的变量的读写操作，这样可以保证执行顺序遵循代码中的顺序。3.**`volatile`不保证原子性**：`volatile`仅确保可见性和禁止指令重排序，但并不提供原子操作的保证。若需要原子性操作，应该使用`java.util.concurrent`包中的原子类，如`AtomicInteger`、`AtomicLong`等

**`volatile`的内存语义**：当写一个`volatile`变量时，`JMM`会把该线程对应的本地内存中的共享变量值立即刷新回主内存中。当读一个`volatile`变量时，`JMM`会把该线程对应的本地内存设置为无效，重新回到主内存中读取最新共享变量。所以`volatile`的写内存语义是直接刷新到主内存中，读的内存语义是直接从主内存中读取

**`volatile`特性**：

- **可见性**：被`volatile`修饰的变量写完后会立即刷新回主内存并及时发出通知，大家可以去主内存拿最新版，前面的修改对后面所有线程可见
- **有序性(禁重排)重排序**：重排序是指编译器和处理器为了优化程序性能而对指令序列进行重新排序的一种手段，有时候会改变程序语句的先后顺序。不存在数据依赖关系，可以重排序。存在数据依赖关系，禁止重排序。不存在数据依赖关系时，重排后的指令绝对不能改变原有的串行语义!这点在并发设计中必须要重点考虑
- **`volatile`不保证原子性**：`volatile`仅确保可见性和禁止指令重排序，但并不提供原子操作的保证。若需要原子性操作，应该使用`java.util.concurrent`包中的原子类，如`AtomicInteger`、`AtomicLong`等

###  6.2   `volatile` 与 内存屏障

**`volatile`凭什么可以保证可见性和有序性？**内存屏障`Memory Barrier`

**内存屏障**：内存屏障也称内存栅栏，屏障指令等。内存屏障是一类同步屏障指令，是`CPU`或编译器在对内存随机访问的操作中的一个同步点，使得此点之前的所有读写操作都执行后才可以开始执行此点之后的操作，避免代码重排序。内存屏障其实就是一种`JVM`指令，`Java`内存模型的重排规则会要求`Java`编译器在生成`JVM`指令时插入特定的内存屏障指令。通过这些内存屏障指令，`volatle`实现了`Java`内存模型中的可见性和有序性(禁重排)，但`volatile`无法保证原子性。内存屏障之前的所有写操作都要回写到主内存，内存屏障之后的所有读操作都能获得内存屏障之前的所有写操作的最新结果(实现了可见性)。写屏障(`Store Memory Barrier`)：告诉处理器在写屏障之前将所有存储在缓存(`store bufferes`)中的数据同步到主内存。也就是说当看到`Store`屏障指令，就必须把该指令之前所有写入指令执行完毕才能继续往下执行。读屏障(`Load Memory Barrier`)：处理器在读屏障之后的读操作，都在读屏障之后执行。也就是说在`Load`屏障指令之后就能够保证后面的读取数据指令一定能够读取到最新的数据。因此重排序时，不允许把内存屏障之后的指令重排序到内存屏障之前。一句话：对一个`volatile`变量的写,先行发生于任意后续对这个`volatile`变量的读，也叫写后读

![image-20240128135209557](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142345167.png)

**内存屏障分类（粗分2种）**：

- 读屏障(`Load Barrier`)：在读指令之前插入读屏障，让工作内存或`CPU`高速缓存当中的缓存数据失效，重新回到主内存中获取最新数据
- 写屏障(`Store Barrier`)：在写指令之后插入写屏障，强制把写缓冲区的数据刷回到主内存中

**内存屏障分类（细分4种）**：四大屏障

| 屏障类型     | 指令示例                     | 说明                                                         |
| ------------ | ---------------------------- | ------------------------------------------------------------ |
| `LoadLoad`   | `Load1; LoadLoad; Load2`     | 保证`load1`的读取操作在`load2`及后续读取操作之前执行         |
| `StoreStore` | `Store1; StoreStore; Store2` | 在`store2`及其后的写操作执行前，保证`store1`的写操作已刷新到主内存 |
| `LoadStore`  | `Load1; LoadStore; Store2`   | 在`stroe2`及其后的写操作执行前，保证`load1`的读操作已读取结束 |
| `StoreLoad`  | `Store1; StoreLoad; Load2`   | 保证`store1`的写操作已刷新到主内存之后，`load2`及其后的读操作才能执行 |

**`volatile`关键字对有序性(禁重排)的保证**：对于编译器的重排序，`JMM`会根据重排序的规则，禁止特定类型的编译器重排序。对于处理器的重排序，`Java`编译器在生成指令序列的适当位置，插入内存屏障指令，来禁止特定类型的处理器排序

###  6.3  `happens-before`原则 之`volatile`变量原则

`happens-before`原则之`volatile`变量原则：

| 第一个操作 | 第二个操作: 普通读写 | 第二个操作: volatile读 | 第二个操作: volatile写 |
| ---------- | -------------------- | ---------------------- | ---------------------- |
| 普通读写   | 可以重排             | 可以重排               | 不可以重排             |
| volatile读 | 不可以重排           | 不可以重排             | 不可以重排             |
| volatile写 | 可以重排             | 不可以重排             | 不可以重排             |

**`volatile`变量原则说明**：

```
当第一个操作为volatile读时，不论第二个操作是什么，都不能重排序。这个操作保证了volatle读之后的操作不会被重排到volatile读之前
当第二个操作为volatie写时，不论第一个操作是什么，都不能重排序。这个操作保证了volatile写之前的操作不会被重排到volatle写之后
当第一个操作为volatile写时，第二个操作为volatile读时，不能重排
```

### 6.4  `volatile` 可见性

**可见性**：保证不同线程对某个变量完成操作后结果及时可见，即该共享变量一旦改变所有线程立即可见

**`volatile`是如何保证可见性的？**被`volatile`修改的变量有以下特点：1.线程中读取的时候，每次读取都会去主内存中读取共享变量最新的值，然后将其复制到工作内存 2.线程中修改了工作内存中变量的副本，修改之后会立即刷新到主内存

**可见性演示**：不加`volatile`，没有可见性，程序无法停止。加了`volatile`，保证可见性，程序可以停止

```java
// 不加volatile，没有可见性，程序无法停止
// 加了volatile，保证可见性，程序可以停止
public class VolatileSeeDemo {
    //static boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            while (flag) {
                System.out.println("flag还未修改,flag:" + flag);
            }
            System.out.println(Thread.currentThread().getName() + "\t -----flag被设置为false，程序停止");
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成flag: " + flag);
    }
// 程序输出：
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                t1     -----flag被设置为false，程序停止
//                main   修改完成flag: false
}
```

上述代码原理解释：

```
线程t1中为何看不到被主线程main修改为false的flag的值?
问题可能:
1.主线程修改了flag之后没有将其刷新到主内存，所以t1线程看不到。
2.主线程将flag刷新到了主内存，但是t1一直读取的是自己工作内存中flag的值，没有去主内存中更新获取flag最新的值

我们的诉求:
1.线程中修改了自己工作内存中的副本之后，立即将其刷新到主内存
2.工作内存中每次读取共享变量时，都去主内存中重新读取，然后拷贝到工作内存
解决:
使用volatile修饰共享变量，就可以达到上面的效果，被volatile修改的变量有以下特点:
1.线程中读取的时候，每次读取都会去主内存中读取共享变量最新的值，然后将其复制到工作内存
2.线程中修改了工作内存中变量的副本，修改之后会立即刷新到主内存
```

**`volatile`变量的读写过程**：`read`(读取)-> `load`(加载)-> `use`(使用)-> `assign`(赋值)-> `store`(存储)一> `write`(写入)-> `lock`(锁定)-> `unlock`(解锁)

`Java`内存模型中定义的8种**每个线程自己的工作内存**与**主物理内存之间**的原子操作：

- **`read`**：  作用于主内存，将变量的值从主内存传输到工作内存，主内存到工作内存
- **`load`**：作用于工作内存，将read从主内存传输的变量值放入工作内存变量副本中，即数据加载
- **`use`**：作用于工作内存，将工作内存变量副本的值传递给执行引擎，每当`JVM`遇到需要该变量的字节码指令时会执行该操作
- **`assign`**：作用于工作内存，将从执行引擎接收到的值赋值给工作内存变量，每当`JVM`遇到一个给变量赋值字节码指令时会执行该操作
- **`store`**：作用于工作内存，将赋值完毕的工作变量的值写回给主内存
- **`write`**： 作用于主内存，将store传输过来的变量值赋值给主内存中的变量
- 由于上述6条只能保证单条指令的原子性，针对多条指令的组合性原子保证，没有大面积加锁，所以，`JVM`提供了另外两个原子指令:
  - `lock`:    作用于主内存，将一个变量标记为一个线程独占的状态，只是写时候加锁，就只是锁了写变量的过程
  - `unlock`： 作用于主内存，把一个处证锁定状态的变量释放，然后才能被其他线程占用

![image-20240201035148142](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346530.png)

###  6.5 `volatile`有序性（指令禁重排）| 内存屏障

**数据依赖性**：若两个操作访问同一变量，且这两个操作中有一个为写操作，此时两操作间就存在数据依赖性

**重排序**：重排序是指编译器和处理器为了优化程序性能而对指令序列进行重新排序的一种手段，有时候会改变程序语句的先后顺序。不存在数据依赖关系，可以重排序。存在数据依赖关系，禁止重排序。不存在数据依赖关系时，重排后的指令绝对不能改变原有的串行语义。若存在数据依赖关系，禁止重排序（重排序发生，会导致程序运行结果不同）。编译器和处理器在重排序时，会遵守数据依赖性，不会改变存在依赖关系的两个操作的执行。但不同处理器和不同线程之间的数据性不会被编译器和处理器考虑，编译器和处理器只会作用于单处理器和单线程环境。下面三种情况，只要重排序两个操作的执行顺序，程序的执行结果就会被改变

![image-20240201055917196](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346742.png)

**重排序的分类和执行流程**：

- 编译器优化的重排序：编译器在不改变单线程串行语义的前提下，可以重新调整指令的执行顺序
- 指令级并行的重排序：处理器使用指令级并行技术来讲多条指令重叠执行，若不存在数据依赖性，处理器可以改变语句对应机器指令的执行顺序
- 内存系统的重排序：由于处理器使用缓存和读/写缓冲区，这使得加载和存储操作看上去可能是乱序执行

![image-20240201054609622](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346970.png)

**重排序案例**：

```
案例一：不存在数据依赖关系，可以重排序===>重排序OK。
重排前
int a = 1;    //第一句
int b = 20;   //第二句
int c = a+ b; //3

重排后
int b = 20;    //第一句
int a = 1;     //第二句
int c = a+ b;  //3

结论:假设编译器调整了语句的顺序，但是不影响程序的最终结果。重排序OK

案例二：若存在数据依赖关系，禁止重排序===>重排序发生，会导致程序运行结果不同
重排前
int a= 1;      //第一句
int b = 20;    //第二句
int c = a + b; //3

重排后
int b = 20;      //第一句
int a = 1;       //第二句
int c = a + b;   //3

结论:假设编译器调整了语句的顺序，但是不影响程序的最终结果。重排序OK
```

`volatile`有序性（指令禁重排）的底层实现是通过内存屏障，`volatile`有关的禁止指令重排的行为（内存屏障插入策略）： 

| 第一个操作   | 第二个操作: 普通读写 | 第二个操作: volatile读 | 第二个操作: volatile写 |
| ------------ | -------------------- | ---------------------- | ---------------------- |
| 普通读写     | 可以重排             | 可以重排               | 不可以重排             |
| `volatile`读 | 不可以重排           | 不可以重排             | 不可以重排             |
| `volatile`写 | 可以重排             | 不可以重排             | 不可以重排             |

**内存屏障插入策略说明**：

- 当第一个操作为`volatile`读时，不论第二个操作是什么，都不能重排序。这个操作保证了`volatle`读之后的操作不会被重排到`volatile`读之前
- 当第二个操作为`volatie`写时，不论第一个操作是什么，都不能重排序。这个操作保证了`volatile`写之前的操作不会被重排到`volatle`写之后
- 当第一个操作为`volatile`写时，第二个操作为`volatile`读时，不能重排

**四大屏障的插入情况**：

- 在每一个`volatile`写操作前面插入一个`StoreStore`屏障，`StoreStore`屏障可以保证在`volatile`写之前，其前面的所有普通写操作都已经刷新到主内存
- 在每一个`volatile`写操作后面插入一个`StoreLoad`屏障，`StoreLoad`屏障的作用是避免`volatile`写与后面可能有的`volatile`读/写操作重排序
- 在每一个`volatile`读操作后面插入一个`LoadLoad`屏障，`LoadLoad`屏障用来禁止处理器把上面的`volatile`读与下面的普通读重排序
- 在每一个`volatile`读操作后面插入一个`LoadStore`屏障，`LoadStore`屏障用来禁止处理器把上面的`volatile`读与下面的普通写重排序

**`JMM`将内存屏障插入策略分为4种规则**：

- 读屏障规则：
  - 一、在每个`volatile`读操作的后面插入一个`LoadLoad`屏障，禁止处理器把上面的`volatile`读与下面的普通读重排序
  - 二、在每个`volatile`读操作的后面插入一个`LoadStore`屏障，禁止处理器把上面的`volatile`读与下面的普通写重排序

- 写屏障规则：
  - 三、在每个`volatile` 写操作的前面插入一个`StoreStore`屏障,可以保证在`volatile`写之前，其前面的所有普通写操作都已经刷新到主内存中
  - 四、在每个`volatile` 写操作的后面插入一个`StoreLoad`屏障,作用是避免`volatile`写与后面可能有的`volatile`读/写操作重排序

**读屏障**：一、在每个`volatile`读操作的后面插入一个`LoadLoad`屏障，禁止处理器把上面的`volatile`读与下面的普通读重排序。二、在每个`volatile`读操作的后面插入一个`LoadStore`屏障，禁止处理器把上面的`volatile`读与下面的普通写重排序。`volatile`读插入内存屏障后生成的指令序列示意图：



![image-20240128154351983](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346770.png)



**写屏障**：三、在每个`volatile` 写操作的前面插入一个`StoreStore`屏障，可以保证在`volatile`写之前，其前面的所有普通写操作都已经刷新到主内存中。四、在每个`volatile` 写操作的后面插入一个`StoreLoad`屏障，作用是避免`volatile`写与后面可能有的`volatile`读/写操作重排序。`volatile`写插入内存屏障后生成的指令序列示意图



![image-20240128154847813](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346481.png)

**指令禁重排代码演示**：

```java
// 什么是顺序读？什么是顺序写？
public class VolatileTest {
    int i = 0;
    volatile boolean flag = false;
    public void write() {
        i = 2;
        // 在每一个volatile写操作前面插入一个StoreStore屏障
        // 在每一个volatile写操作后面插入一个StoreLoad屏障
        // volatile 写之前的操作，都禁止重排序到volatile之后
        flag = true;
    }
    public void read() {
        // 在每一个volatile读操作后面插入一个LoadLoad屏障
        // 在每一个volatile读操作后面插入一个LoadStore屏障
        // volatile读之后的操作，都禁止重排序到volatile之前
        if (flag) {
            System.out.println("--i = " + i);
        }
    }
}

```

**代码说明**：

![image-20240202063530800](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346592.png)



###  6.6  `volatile`不保证原子性

 **`volatile`不保证原子性**：`volatile`变量的读写过程为`read`(读取)-> `load`(加载)-> `use`(使用)-> `assign`(赋值)-> `store`(存储)一> `write`(写入)-> `lock`(锁定)-> `unlock`(解锁)。当线程1对主内存对象发起`read`操作到`write`操作的时间里，线程2随时都有可能对这个主内存对象发起`read`操作到`write`操作



![image-20240201042252742](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346128.png)

对于`volatile`变量具备可见性，`JVM`只是保证从主内存加载到线程工作内存的值是最新的，也仅是数据加载时是最新的。但是多线程环境下，"数据计算"和""数据赋值"操作可能多次出现，若数据在加载之后，若主内存`volatile`修饰变量发生修改之后，线程工作内存中的操作将会作废去读主内存最新值，操作出现写丢失问题。即各线程私有内存和主内存公共内存中变量不同步，进而导致数据不一致。由此可见`volatile`解决的是变量读时的可见性问题，但无法保证原子性，对于多线程修改主内存共享变量的场景必须使用加锁同步

![image-20240201043034159](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346353.png)



**`i++`运算不具备原子性**：从`i++`的字节码角度说明：原子性指的是一个操作是不可中断的，即使是在多线程环境下，一个操作一旦开始就不会被其他线程影响。从`JVM`的字节码可以看出`i++`分成三步，间隙期不同步，所以`(i++)`是非原子操作

```java
public void add(){
i++;  // 不具备原子性，该操作是先读取值，然后写回一个新值，相当于原来的值加上1，分3步完成
}
```



![image-20240201044844284](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346812.png)

如果第二个线程在第一个线程读取旧值和写回新值期间读取i的域值，那么第二个线程就会与第一个线程一起看到同一个值,并执行相同值的加1操作，这也就造成了线程安全失败，因此对于`add`方法必须使用`synchronized`修饰以便保证线程安全

对于`volatile`变量，`JVM`只是保证从主内存加载到线程工作内存的值是最新的，也只是数据加载时是最新的。如果第二个线程在第一个线程读取旧值和写回新值期间读取i的域值，也就造成了线程安全问题



![image-20240201050925979](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142346722.png)



**`volatile`不具备原子性程序演示**：

```java
import java.util.concurrent.TimeUnit;
// volatile不具备原子性
// volatile变量的复合操作不具有原子性，比如number++
class MyNumber {
    volatile int number;

    public void addPlusPlus() {
        number++;
    }


    // synchronized同步方法保证原子性，如果使用下面的同步方法，就会正确输出
//    public synchronized void addPlusPlus() {
//        number++;
//    }
}

public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myNumber.number);
        // 9883
    }
}
```

**结论**：`volatile`变量不适合参与到依赖当前值的运算

###  6.7  `volatile` 关键字的正确使用

**`volatile`的使用场景**：`volatile`变量不适合参与到依赖当前值的运算，比如`i=i+1; i++;`。依靠`volatile`可见性的特点，通常将`volatile`用做保存某个状态的`boolean`值或者`int`值。单一赋值可以使用`volatile`，但是含复合运算赋值不可以使用`volatile`(`i++`之类)

```java
// 单一赋值可以使用volatile
volatile int a = 10
volatile boolean flag = false
```

**不能使用`volatile`的场景**：由于`volatile`变量只能保证可见性，在不符合以下两条规则的运算场景中，最好要通过加锁(使用`synchronized`、`java.util.concurrent`中的锁或原子类）来保证原子性：一、运算结果并不依赖变量的当前值，或者能够确保只有单一的线程修改变量的值。二、变量不需要与其他的状态变量共同参与不变约束

**`volatile`的使用案例一**：使用`volatile`修饰状态标志`flag`，判断业务是否结束

```java
/**
 * 使用:作为一个布尔状态标志，用于指示发生了一个重要的一次性事件，例如完成初始化或任务结束
 * 理由:状态标志并不依赖于程序内任何其他状态，且通常只有一种状态转换
 * 例子:判断业务是否结束
 */
public class UseVolatileDemo {
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            while (flag) {
                System.out.println("flag还未修改,flag:" + flag);
            }
            System.out.println(Thread.currentThread().getName() + "\t -----flag被设置为false，程序停止");
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成flag: " + flag);
    }
// 程序输出：
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                t1	 -----flag被设置为false，程序停止
//                main	 修改完成flag: false
}
```

**`volatile`的使用案例二**：开销较低的读，写锁策略

```java
// 开销较低的读，写锁策略
public class UseVolatileDemo1 {
    /**
     * 使用：当读远多于写，结合使用内部锁和volatile变量来减少同步的开销
     * 理由：利用volatile保证读取操作的可见性;利用synchronized保证复合操作的原子性
     */
    public class Counter {
        private volatile int value;
        public int getValue() {
            return value;  // 利用volatile保证读取操作的可见性
        }
        public synchronized int increment() {
            return value++;  // 利用synchronized保证复合操作的原子性
        }
    }
}
```

**`volatile`的使用案例三**：使用`volatile`解决`DCL`双端锁的发布问题

```java
public class SafeDoubleCheckSingleton {
    private static SafeDoubleCheckSingleton singleton;
    // 私有化构造方法
    private SafeDoubleCheckSingleton() {
    }
    // 双重锁设计
    public static SafeDoubleCheckSingleton getInstance() {
       // 第一次检查
        if (singleton == null) {
            // 1.多线程并发创建对象时，会通过加锁保证只用一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class) {
                // 第二次检查
                if (singleton == null) {
                    // 隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        // 2.对象创建完毕，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;
    }
}
```

上述代码在单线程环境中保证能获取到已完成初始化的实例，在"问题代码处"，会执行如下操作：

```java
memory = allocate();      // 1.分配对象的内存空间
ctorInstance(memory);     // 2.初始化对象
instance = memory;        // 3.设置instance指向刚分配的内存地址
```

上述代码在多线程环境中由于存在指令重排序，可能会出现错误。隐患：多线程环境下，在"问题代码处"由于重排序导致2,3乱序，后果就是其他线程得到的是`null`而不是完成初始化的对象，会执行如下操作：

```java
memory = allocate();     //  1.分配对象的内存空间
ctorInstance(memory);    //  2.初始化对象
instance = memory;       //  3.设置instance指向刚分配的内存地址
```

问题分析：其中第3步中实例化`Singleton`分多步执行（分配内存空间、初始化对象、将对象指向分配的内存空间)，某些编译器为了性能原因，会将第二步和第三步进行重排序（分配内存空间、将对象指向分配的内存空间、初始化对象〉。这样，某个线程可能会获得一个未完全初始化的实例。这种场景在著名的双重检查锁定(`double-checked-locking`)中会出现

```
memory = allocate( ) ;    //  1.分配对象的内存空间
instance = memory;        //  2.设置instance指向刚分配的内存地址
                          //  注意，此时对象还没有被初始化!
ctorInstance(memory) ;    //  3.初始化对象
```

问题解决：变量`singleton`要加`volatile`修饰

```java
public class SafeDoubleCheckSingleton1 {
    // 通过volatile声明，实现线程安全的延迟初始化
    private volatile static SafeDoubleCheckSingleton1 singleton;

    // 私有化构造方法
    private SafeDoubleCheckSingleton1() {
    }

    // 双重锁设计
    public static SafeDoubleCheckSingleton1 getInstance() {
        // 第一次检查
        if (singleton == null) {
            // 1.多线程并发创建对象时，会通过加锁保证只用一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton1.class) {
                // 第二次检查
                if (singleton == null) {
                    // 隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    // 解决隐患原理：利用volatile，禁止"初始化对象"(2）和"没置singleton指向内存空间"(3）的重排序
                    singleton = new SafeDoubleCheckSingleton1();
                }
            }
        }
        // 2.对象创建完毕，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;
    }
}
```

###  6.8  `volatile`总结

**`volatile`不保证原子性**

**`volatile`关键字保证可见性**：

1.对一个被`volatile`关键字修饰的变量进行写操作的话，这个变量的最新值会立即刷新回到主内存中
2.对一个被`volatile`关键字修饰的变量进行读操作的话，总是能够读取到这个变量的最新值，也就是这个变量最后被修改的值
3.当某个线程收到通知，去读取`volatile`修饰的变量的值的时候，线程私有工作内存的数据失效，需要重新回到主内存中去读取最新的数据

**`volatile`有序性（禁重排）**：通过内存屏障来保证有序性

**内存屏障**：内存屏障是一种屏障指令，它使得`CPU`或编译器对屏障指令的前和后所发出的内存操作执行一个排序的约束。也叫内存栅栏或栅栏指令

**内存屏障作用**：

- 阻止屏障两边的指令重排序
- 写数据时加入屏障，强制将线程私有工作内存的数据刷回主物理内存
- 读数据时加入屏障，线程私有工作内存的数据失效，重新到主物理内存中获取最新数据

**内存屏障四大指令**：

- 在每一个`volatile`写操作前面插入一个`StoreStore`屏障
- 在每一个`volatile`写操作后面插入一个`StoreLoad`屏障
- 在每一个`volatile`读操作后面插入一个`LoadLoad`屏障
- 在每一个`volatile`读操作后面插入一个`LoadStore`屏障

**三句话概括内存屏障**：

- `volatile` 写之前的操作，都禁止重排序到`volatile` 之后
- `volatile` 读之后的操作，都禁止重排序到`volatile `之前
- `volatile` 写之后`volatile`读，禁止重排序



**`volatile`写操作** 要符合 **写屏障规则**：

- 在每个`volatile` 写操作的前面插入一个`StoreStore`屏障,可以保证在`volatile`写之前，其前面的所有普通写操作都已经刷新到主内存中
- 在每个`volatile` 写操作的后面插入一个`StoreLoad`屏障,作用是避免`volatile`写与后面可能有的`volatile`读/写操作重排序



![image-20240202081006882](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142347236.png)

**`volatile`读操作** 要符合 **读屏障规则**：

- 在每个`volatile`读操作的后面插入一个`LoadLoad`屏障，禁止处理器把上面的`volatile`读与下面的普通读重排序
- 在每个`volatile`读操作的后面插入一个`LoadStore`屏障，禁止处理器把上面的`volatile`读与下面的普通写重排序

![image-20240202081338891](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142347036.png)

在声明`volatile`关键字修饰的变量以后，系统底层会在相应位置加入内存屏障。字节码层面。`JVM`在把字节码生成机器码的时候，发现操作是`volatile`变量的话，会按照`JMM`的规范，在相应的位置插入内存屏障

![image-20240202081718943](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142348801.png)



##  7. `CAS`

###  7.1 `CAS`简介

**`CAS`简介**：`CAS`即比较并交换`（Compare and Swap）`，是Java并发编程中的重要概念之一，通常用于实现非阻塞算法。`CAS`操作包含三个参数：内存位置V，预期值A和新值B。`CAS`操作会检查内存位置V的值是否等于预期值A，如果相等，那么将内存位置V的值更新为新值B；如果不相等，则不做任何操作。CAS是一种乐观锁的思想，它允许线程在不阻塞的情况下尝试更新一个变量的值，如果变量的值在尝试更新时发生了变化，那么操作就不会成功，线程需要根据此情况进行相应的重试或其他处理。`GPT`补充：`CAS`是`Java`并发编程中的一种同步机制，用于解决多线程并发访问共享数据时的数据安全性问题。`CAS`操作由三个参数组成：一个内存位置（V）、旧的预期值（A）和新的值（B）。`CAS`操作首先将内存位置的当前值与预期值进行比较，如果相等，则将内存位置的值更新为新的值。如果不相等，则说明其他线程已经修改了内存位置的值，当前线程需要重新读取内存位置的值并进行下一次的`CAS`操作。`CAS`操作是一种乐观锁的实现方式，它不需要使用传统的互斥锁机制来保证数据的安全性因此在高并发场景下，`CAS`操作可以提供更好的性能。`Java`并发编程中的`Atomic`包提供了一系列基于`CAS`操作的原子类，如`AtomicInteger`、`AtomicLong`、`AtomicBoolean`等，这些原子类可以保证对共享数据的原子操作，从而避免了多线程并发访问共享数据时出现的竞态条件和数据不一致的问题。使用`CAS`操作需要注意以下几点：1.`CAS`操作是一种无锁的操作，因此不会引起线程的阻塞和切换，适用于高并发场景。2.`CAS`操作只能保证一个共享变量的原子操作，如果涉及多个共享变量的操作，仍然需要使用传统的互斥锁机制来保证数据的一致性。3.`CAS`操作可能会导致`ABA`问题，即在操作过程中，其他线程修改了内存位置的值，然后又将其恢复为原来的值，这时`CAS`操作可能会误判为成功。为了解决`ABA`问题，可以使用版本号或者标记位来确保数据的一致性

**`CAS`原理**：`CAS`有3个操作数，位置内存值`V`，旧的预期值`A`，要修改的更新值`B`。当且仅当旧的预期值`A`和内存值`V`相同时，将内存值`V`修改为`B`，否则什么都不做或重试。重来重试的这种行为称为自旋。执行`CAS`操作的时候，将内存位置的值与预期原值比较。如果相匹配，那么处理器会自动将该位置值更新为新值。如果不匹配，处理器不做任何操作。多个线程同时执行`CAS`操作只有一个会成功

![image-20240202092232333](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142348654.png)

**`CAS`硬件级别保证**：`CAS`是`JDK`提供的非阻塞原子性操作，它通过硬件保证了比较-更新的原子性。`CAS`是非阻塞的，且自身具有原子性，也就是说`CAS`效率更高且通过硬件保证，说明`CAS`更可靠。`CAS`是一条`CPU`的原子指令(`cmpxchg`指令），不会造成所谓的数据不一致问题，`Unsafe`提供的`CAS`方法(如`compareAndSwapXXX`）底层实现即为`CPU`指令`cmpxchg`，执行`cmpxchg`指令的时候，会判断当前系统是否为多核系统，如果是就给总线加锁，只有一个线程会对总线加锁成功，加锁成功之后会执行`cas`操作，也就是说`CAS`的原子性实际上是`CPU`实现独占的，比起用`synchronized`重量级锁，这里的排他时间要短很多，所以在多线程情况下性能会比较好

**源码分析**：`compareAndSet(int expect,int update)`：

```java
public class AtomicInteger extends Number implements java.io.Serializable {
     /**
     * Atomically sets the value to the given updated value
     * if the current value {@code ==} the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that
     * the actual value was not equal to the expected value.
     */
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
}
```

`compareAndSet()`方法的源代码：

```java
public final class Unsafe {
    public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
    public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
    public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
}

上面三个方法都是类似的，主要对4个参数做一下说明：
        var1：表示要操作的对象
        var2：表示要操作对象中属性地址的偏移量
        var4：表示需要修改数据的期望的值
        var5/var6：表示需要修改为的新值
```

###  7.2 `CAS`代码演示

**没有`CAS`之前**：多线程环境使用锁来保证复合操作`i++`（基本数据类型)的线程安全

```java
// 没有atomics时，通过synchronized保证原子性
public class NoAtomicDemo {
    volatile int number = 0;
    // 读取
    public int getNumber() {
        return number;
    }
    // 写入加锁保证原子性
    public synchronized void setNumber() {
        number++;
    }
}
```

**使用`CAS`之后**：多线程环境使用原子类保证线程安全`i++`（基本数据类型)类似乐观锁

```java
public class UseAtomicDemo {
    volatile int number;
    // 读取
    public int getNumber() {
        return number;
    }
    // 写入加锁保证原子性
    public synchronized void setNumber() {
        number++;
    }
    // ====== 使用AtomicInteger ======
    AtomicInteger atomicInteger = new AtomicInteger();
    public int getAtomicInteger() {
        return atomicInteger.get();
    }
    public void setAtomicInteger() {
        atomicInteger.getAndIncrement();
    }
}
```

**`CAS`代码演示**：`java.util.concurrent.atomic`中的原子类使用

```java
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
//        true	2022
//        false	2022
        atomicInteger.getAndIncrement();
    }
}
```

###  7.3 `CAS` 与 `UnSafe`类

**`Unsafe`类简介**：`Unsafe`类是`CAS`的核心类，由于`Java`方法无法直接访问底层系统，需要通过本地`(native)`方法来访问，`Unsafe`相当于一个后门，基于该类可以直接操作特定内存的数据。`Unsafe`类存在于`sun.misc`包中，其内部方法操作可以像`C`的指针一样直接操作内存，**`Java`中`CAS`操作的执行依赖于`Unsafe`类的方法**。注意`Unsafe`类中的所有方法都是`native`修饰的，也就是说`Unsafe`类中的方法都直接调用操作系统底层资源执行相应任务。**`GPT`补充**：`Unsafe`类是`Java`中的一个非常特殊的类，它提供了一些底层操作，例如直接操作内存、线程同步和原子操作等。`Unsafe`类在`Java`标准库中并没有公开的`API`，而是被定义为`sun.misc.Unsafe`，因此它被认为是`Java`中的“不安全”类，`Unsafe`类向开发者提供了直接操纵内存和执行特权操作的能力，这可能导致不安全的代码和潜在的安全漏洞。`Unsafe`类提供了一些常用的方法，可以绕过`Java`的安全检查机制，直接操作内存。这使得开发人员可以实现一些高级的功能，比如手动管理内存、创建实例、获取对象的属性和数组元素等。但是，由于`Unsafe`类可以绕过`Java`的安全检查机制，所以使用它需要非常小心，不当的使用可能导致内存泄漏、线程安全问题等。除了直接操作内存之外，`Unsafe`类还提供了一些原子操作的方法，例如`compareAndSwapInt`、`compareAndSwapLong`和`compareAndSwapObject`等。这些方法可以实现线程安全的操作，避免了使用锁的开销，提高了性能。另外，`Unsafe`类还提供了一些线程同步的方法，例如`park`和`unpark`。这些方法可以用于实现高级的线程同步机制，比如自旋锁、信号量等。需要注意的是，由于`Unsafe`类是`Java`标准库中的一个内部类，所以它的使用是不被推荐的。它不是公开的`API`，并且在不同的`Java`版本中可能会有不同的实现。因此，在开发中，应该尽量避免使用`Unsafe`类，而是使用`Java`提供的安全、可靠的`API`

**`Unsafe`类是实现`CAS`的核心类**：`Unsafe`类是`CAS`得以实现的核心类，`Unsafe`类中的所有方法都是`native`修饰的，也就是说`Unsafe`类中的方法都直接调用操作系统底层资源执行相应任务。`Unsafe`类的`compareAndSwap`方法允许使用`CAS`来实现非阻塞算法。通过`CAS`，线程可以尝试直接在内存中修改值，但仅在当前值符合预期情况下才会进行操作。这种比较并交换的机制可用于避免使用锁而实现线程安全。实现`CAS`的基本思想是：首先读取当前内存位置的值，然后与预期值进行比较，如果相同，则进行更新操作；否则说明其他线程已经修改了该位置的值，这时需要重新读取并再次尝试操作。`Unsafe`类提供了底层的支持，允许直接进行这些操作。在`Java`中，`Unsafe`类提供了一些方法来实现`CAS`操作。其中最常用的方法是`compareAndSwapInt`、`compareAndSwapLong`和`compareAndSwapObject`，分别用于比较和交换整型、长整型和对象类型的值

 **`Unsafe`类 与 原子类的实现**：原之类`AtomicInteger`首先通过`Unsafe`类的静态方法`getUnsafe`获取`Unsafe`实例，然后使用该实例调用对应的`CAS`方法对字段进行操作。`Atomiclnteger`类主要利用`CAS(compare and swap) + volatile`和 `native`方法来保证原子操作，从而避免 `synchronized`的高开销，执行效率大为提升

```java
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    // 变量valueOffset，表示该变量值在内存中的偏移地址，因为Unsafe就是根据内存偏移地址获取数据的
    // 变量value用volatile修饰，保证了多线程之间的内存可见性
    private static final long valueOffset;
    private volatile int value;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * Atomically sets the value to the given updated value
     * if the current value {@code ==} the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that
     * the actual value was not equal to the expected value.
     */
    // compareAndSwap方法使用unsafe.compareAndSwapInt方法来比较并交换value字段的值
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
}
```

**`CAS`之`Unsafe`类`CAS`方法底层汇编源码分析**：`CAS`并发原语体现在`JAVA`语言中就是`sun.misc.Unsafe`类中的各个方法。调用`UnSafe`类中的`CAS`方法，`JVM`会帮我们实现出`CAS`汇编指令。这是一种完全依赖于硬件的功能，通过它实现了原子操作。再次强调，由于`CAS`是一种系统原语，原语属于操作系统用语范畴，是由若干条指令组成的，用于完成某个功能的一个过程，并且原语的执行必须是连续的，在执行过程中不允许被中断，也就是说`CAS`是一条`CPU`的原子指令，不会造成所谓的数据不一致问题

`AtomicInteger`类：`AtomicInteger()`类的`getAndIncrement()`方法源码分析

```java
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;
    /**
     * Atomically increments by one the current value.
     *
     * @return the previous value
     */
    public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
}
```

`Unsafe`类的`getAndAddInt`方法源码分析：

```java
public final class Unsafe {

   public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
   // native修饰的方法代表是底层方法
   public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);

}

假设线程A和线程B两个线程同时执行getAndAddInt操作（分别跑在不同CPU上) :
1.AtomicInteger里面的value原始值为3，即主内存中Atomiclnteger的value为3，根据JMM模型，线程A和线程B各自持有一份值为3的value的副本分别到各自的工作内存
2.线程A通过getIntVolatile(var1, var2)拿到value值3，这时线程A被挂起
3.线程B也通过getIntVolatile(var1, var2)方法获取到value值3，此时刚好线程B没有被挂起并执行compareAndSwaplnt方法比较内存值也为3，成功修改内存值为4，线程B打完收工，一切OK
4.这时线程A恢复，执行compareAndSwapInt方法比较，发现自己手里的值数字3和主内存的值数字4不一致，说明该值己经被其它线程抢先一步修改过了，那A线程本次修改失败，只能重新读取重新来一遍了
5.线程A重新获取value值，因为变量value被volatile修饰，所以其它线程对它的修改，线程A总是能够看到，线程A继续执行compareAndSwapInt进行比较替换，直到成功
```

**`Unsafe`类`CAS`方法底层汇编**：`Unsafe`类中的`compareAndSwapInt`，是一个`native`本地方法，该方法的实现位于`unsafe.cpp`中

![image-20240203080839019](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142348482.png)

`cmpxchg`方法：`cmpxchg`是一个汇编指令，用于执行原子比较和交换操作。它通常用于多线程编程中，用于实现线程间的同步和互斥。`cmpxchg`指令的作用是将一个内存位置的值与一个寄存器中的值进行比较，如果相等，则将另一个寄存器中的值写入内存位置；如果不相等，则将内存位置的值写入另一个寄存器中。这个操作是原子的，意味着在执行过程中不会被其他线程中断。`cmpxchg`指令通常用于实现锁操作，比如自旋锁或者互斥量。在多线程环境下，多个线程可能同时访问共享资源，使用`cmpxchg`可以确保只有一个线程能够成功地获取到锁，其他线程需要等待

![image-20240203082040227](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142348544.png)

在不同的操作系统下会调用不同的`cmpxchg`重载函数，`win10`系统中的`cmpxchg`函数如下：

![image-20240203082557628](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142348389.png)

**`CAS`总结**：`CAS`是靠硬件实现的从而在硬件层面提升效率，最底层还是交给硬件来保证原子性和可见性。`CAS`实现方式是基于硬件平台的汇编指令，在`intel`的`CPU中`(X86机器上)，使用的是汇编指令`cmpxchg`指令。核心思想就是：比较要更新变量的值`V`和预期值`E(compare)`，相等才会将`V`的值设为新值`N(swap）`如果不相等自旋再来

###  7.4 `CAS` 与 原子引用`AtomicReference`

**原子引用`AtomicReference`简介**：`AtomicReference`是Java中的一个类，位于`java.util.concurrent.atomic`包中。它提供了一种原子化更新对象引用的方式，能够在多线程环境下进行安全操作。通过`AtomicReference`可以在不使用锁的情况下，对对象引用进行原子更新，从而避免了传统锁机制可能引起的性能问题和死锁。为了实现原子性操作，`AtomicReference`基于`CAS（Compare And Swap）`操作，这意味着它能够在不加锁的情况下进行原子性的更改，这对于需要高效执行并发操作的情况来说非常有用

**原子引用`AtomicReference`使用演示**：

```java
import java.util.concurrent.atomic.AtomicReference;
@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User z3 = new User("z3", 22);
        User li4 = new User("li4", 28);
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
/*程序输出：
            true	User(userName=li4, age=28)
            false	User(userName=li4, age=28)
 */
    }
}
```

###  7.5  `CAS` 与 自旋锁

**`CAS`与自旋锁`(spinlock)`**：`CAS`是实现自旋锁的基础，`CAS`利用`CPU`指令保证了操作的原子性，以达到锁的效果。自旋是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，当线程发现锁被占用时，会不断循环判断锁的状态，直到获取。这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗`CPU`。`CAS`是实现自旋锁的基础，自旋翻译成人话就是循环，一般是用一个无限循环实现。在一个无限循环中，执行一个`CAS`操作，当操作成功返回`true` 时，循环结束。当返回 `false` 时，接着执行循环，继续尝试`CAS`操作，直到返回`true`

**`OpenJDK`源码里面查看下`Unsafe.java`源码**：

```java
// 源码位置：https://hg.openjdk.org/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/sun/misc/Unsafe.java
public final class Unsafe {
 /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object <code>o</code>
     * at the given <code>offset</code>.
     * @param o object/array to update the field/element in
     * @param offset field/element offset
     * @param delta the value to add
     * @return the previous value
     */
    public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!compareAndSwapInt(o, offset, v, v + delta));
        return v;
    }
}
```

**利用`CAS`思想实现一个自旋锁**：

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/**
 * 需求：利用CAS思想实现一个自旋锁
 * 自旋锁好处：循环比较获取，没有类似wait的阻塞
 * <p>
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "----task over,unLock...");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
            spinLockDemo.unLock();
        }, "A").start();

        //暂停500毫秒,线程A先于B启动
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unLock();
        }, "B").start();
/*  程序输出：
             A	----come in
             B	----come in
             A	----task over,unLock...
             B	----task over,unLock...
 */
    }
}
```

###  7.6 `CAS`缺点：开销大 & `ABA`问题

缺点一：`CAS`循环时间长，开销很大。如果`CAS`失败，会一直进行尝试。如果`CAS`长时间一直不成功，可能会给`CPU`带来很大的开销

```java
public final class Unsafe {
   public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
}
```

 缺点二：`CAS`会导致`ABA`问题。`CAS`算法实现一个重要前提需要取出内存中某时刻的数据并在当下时刻比较并替换，那么在这个时间差类会导致数据的变化。比如线程1从内存位置V中取出A，这时候另一个线程2也从内存中取出A，并且线程2进行了一些操作将值变成了B，然后线程2又将V位置的数据变成A，这时候线程1进行`CAS`操作发现内存中仍然是A，预期OK，然后线程1操作成功。尽管线程1的`CAS`操作成功，但是不代表这个过程就是没有问题的

`AtomicStampedReference`版本号时间戳原子引用类解决`ABA`问题：`AtomicStampedReference`类提供了对对象引用变量的原子读和写, `AtomicStampedReference`是指多个试图更改同一`AtomicStampedReference`的线程不会使`AtomicStampedReference`最终处于不一致的状态。`AtomicStampedReference`和`AtomicReference`的不同之处在于，`AtomicStampedReference`在内部同时保留对象引用和数据戳，引用和数据戳可以通过`compareAndSet()`方法使用单`CAS`操作进行交换。`AtomicStampedReference`设计是为了解决`AtomicReference`不能解决的`ABA`问题

**`AtomicStampedReference`的使用**：

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;
public class AtomicStampedDemo {
    public static void main(String[] args) {
        // 初始化的Book
        Book javaBook = new Book(1,"javaBook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook,1);
        System.out.println(stampedReference.getReference()+"\t"+stampedReference.getStamp());
        Book mysqlBook = new Book(2,"mysqlBook");
        boolean b;
        b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b+"\t"+stampedReference.getReference()+"\t"+stampedReference.getStamp());
        b = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b+"\t"+stampedReference.getReference()+"\t"+stampedReference.getStamp());
//程序输出：
//            Book(id=1, bookName=javaBook)	1
//            true	Book(id=2, bookName=mysqlBook)	2
//            true	Book(id=1, bookName=javaBook)	3
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Book
{
    private int id;
    private String bookName;
}
```

**使用`AtomicStampedReference`解决`ABA`问题**：比较的时候还应该对版本号进行比较

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t"+"首次版本号："+stamp);

            //暂停500毫秒,保证后面的t4线程初始化拿到的版本号和我一样
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"2次流水号："+stampedReference.getStamp());

            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"3次流水号："+stampedReference.getStamp());

        },"t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t"+"首次版本号："+stamp);

            //暂停1秒钟线程,等待上面的t3线程，发生了ABA问题
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);

            System.out.println(b+"\t"+stampedReference.getReference()+"\t"+stampedReference.getStamp());
        },"t4").start();

//程序输出：
//            t3    首次版本号：1
//            t4    首次版本号：1
//            t3    2次流水号：2
//            t3    3次流水号：3
//            false 100    3
    }
    private static void abaHappen() {
        new Thread(() -> {
            atomicInteger.compareAndSet(100,101);
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicInteger.compareAndSet(101,100);
        },"t1").start();

        new Thread(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicInteger.compareAndSet(100, 2022)+"\t"+atomicInteger.get());
        },"t2").start();
// 程序输出： true   2022
    }
}
```

##  8. 原子类

###  8.1 原子类简介

**原子类**：原子类指的是 `java.util.concurrent.atomic` 包中的一组类，这些类提供了一系列的原子操作，提供了对数值和引用变量进行原子操作的功能，以便在多线程环境下执行操作时保证线程安全。这些类都采用了 `CAS（Compare and Swap）`机制来确保在并发环境中进行原子操作。通过使用原子操作类，可以避免使用 `synchronized` 关键字来对整个方法或代码块进行加锁，从而提高了程序在多线程环境下的性能。当需要进行简单的原子操作时，使用这些类通常是一个不错的选择

**常用的`Java`原子类**：

- `AtomicInteger`: 一个用于原子更新整型的类，提供原子性的递增和递减操作
- `AtomicLong`: 用于原子更新长整型的类，提供原子性的递增和递减操作
- `AtomicBoolean`: 用于原子更新布尔值的类，提供原子性的设置和获取操作
- `AtomicReference`: 用于原子更新引用类型的类，提供原子性的设置和获取操作
- `AtomicStampedReference`: 可以原子性地更新引用对象和一个整数的类
- `AtomicIntegerFieldUpdater`, `AtomicLongFieldUpdater`, `AtomicReferenceFieldUpdater`: 这些类可以用于原子更新对象的字段

**`java.util.concurrent.atomic`包下的原子类**：

```
AtomicBoolean
AtomicInteger
AtomicIntegerArray
AtomicIntegerFieldUpdater
AtomicLong
AtomicLongArray
AtomicLongFieldUpdater
AtomicMarkableReference
AtomicReference
AtomicReferenceArray
AtomicReferenceFieldUpdater
AtomicStampedReference
DoubleAccumulator
DoubleAdder
LongAccumulator
LongAdder
```

**`java.util.concurrent.atomic`包下原子类分类**：

- 基本类型原子类
- 数组类型原子类
- 引用类型原子类
- 对象的属性修改原子类
- 原子操作增强类

###  8.2  基本类型原子类

**基本类型原子类**：`Atomiclnteger`、`AtomicBoolean`、`AtomicLong`

**原子类常用`API`**：

```java
public final int get()                          // 获取当前的值
public final int getAndSet(int newValue)        // 获取当前的值，并设置新的值
public final int getAndIncrement()              // 获取当前的值，并自增
public final int getAndDecrement()              // 获取当前的值，并自减
public final int getAndAdd(int delta)           // 获取当前的值，并加上预期的值
boolean compareAndSet(int expect, int update)   // 如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update)
```

**原子操作类`Atomiclnteger`使用案例**：

```java
class MyNumber
{
    // 默认初始值是0
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus()
    {
        atomicInteger.getAndIncrement();
    }
}
public class AtomicIntegerDemo
{
    // 50个线程
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException
    {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <=SIZE; i++) {
            new Thread(() -> {
                    for (int j = 1; j <=1000; j++) {
                        myNumber.addPlusPlus();
                    }
            },String.valueOf(i)).start();
        }
        //等待上面50个线程全部计算完成后，再去获得最终值
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()+"\t"+"result: "+myNumber.atomicInteger.get());
//程序输出：
//         main result: 50000
    }
}
```

**使用`CountDownLatch`对上述的程序进行优化**：

- **上述程序的不足**：50个线程，每个线程对`AtomicInteger`原子类进行1000次自增操作，如果要获取预期的值，可以让主线程休眠一段时间，保证50个线程都执行完毕以后，再去读取原子类的值。不足之处是这个休眠的时间不容易确定，太短的话可能50个线程并没有全部结束运行，太长的话影响程序的整体响应时间
- **优化**：使用`CountDownLatch`来判断50个线程是否都已经执行完毕。`CountDownLatch`是Java中的一个同步辅助类，可以让一个或多个线程等待其他线程完成操作后再继续执行。`CountDownLatch`通过一个计数器来实现，初始值设定为某个数目，当在其他线程中调用`countDown()`方法时，计数器的值会减一，而调用`await()`方法的线程会阻塞直到计数器的值为0

```java
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
// CountDownLatch的使用：
class MyNumber
{
    // 默认初始值是0
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus()
    {
        atomicInteger.getAndIncrement();
    }
}

public class AtomicIntegerDemo
{
    // 50个线程
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException
    {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for (int i = 1; i <=SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        //等待上面50个线程全部计算完成后，再去获得最终值
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t"+"result: "+myNumber.atomicInteger.get());
 // 程序输出：  main result: 50000
    }
}
```

**`CountDownLatch` 补充**：`CountDownLatch` 是 `Java` 中的一个同步辅助类，用于控制一个或多个线程等待其他线程完成操作。它通过一个计数器来实现，可以在计数器变为零之前阻塞线程，然后在计数器为零时释放所有等待的线程。`CountDownLatch` 在多线程环境下是非常有用的，特别是在一些需要等待其他线程执行完特定操作后再进一步执行的情况下。可以使用 `CountDownLatch` 来协调多个线程，使得它们在某个共同点上同时开始执行或等待着某个条件满足后再执行下一步操作。可以通过 `CountDownLatch` 的 `await()` 方法来阻塞一个或多个线程等待计数器变为零，然后通过 `countDown()` 方法来让计数器减一。`CountDownLatch` 的基本用法示例：

```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);

        Runnable task = () -> {
            // 执行某些操作
            latch.countDown(); // 当某个操作完成时调用 countDown()
        };

        // 启动线程
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        latch.await(); // 等待计数器变为零

        // 当计数器为零时，执行接下来的操作
    }
}
```

###  8.3  数组类型原子类

**数组类型原子类**：`AtomiclntegerArray` 、`AtomicLongArray`、`AtomicReferenceArray`

**`AtomicIntegerArray`使用案例**：

```java
import java.util.concurrent.atomic.AtomicIntegerArray;
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        // 指定容量为5，初始值都是0
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
        // 指定容量为5
        //AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        // 设置了初始值
        //AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});

        for (int i = 0; i <atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }
        System.out.println();
        int tmpInt = 0;
        // 获取下标为0的原子类，再将它的值设置为1122
        tmpInt = atomicIntegerArray.getAndSet(0,1122);
        System.out.println(tmpInt+"\t"+atomicIntegerArray.get(0));
        // 获取下标为0的原子类，再让它的值自增
        tmpInt = atomicIntegerArray.getAndIncrement(0);
        System.out.println(tmpInt+"\t"+atomicIntegerArray.get(0));
//程序输出：
//            0
//            0
//            0
//            0
//            0
//
//            0	1122
//            1122	1123
    }
}
```

###  8.4  引用类型原子类

**引用类型原子类**：`AtomicReference`、`AtomicStampedReference`、`AtomicMarkableReference`。其中，`AtomicStampedReference`（状态戳原子引用）携带版本号的引用类型原子类，可以解决`ABA`问题，可以记录修改过几次。`AtomicMarkableReference`（状态戳(`true/false`)原子引用）可以原子地更新带有标记位的引用类型对象，解决是否修改过，它的定义就是将状态戳简化`true\false`，类似一次性筷子

**`AtomicReference`使用案例**：自旋锁`spinLockDemo`的实现

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/**
 * 需求：利用CAS思想实现一个自旋锁
 * 自旋锁好处：循环比较获取，没有类似wait的阻塞
 * <p>
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "----task over,unLock...");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
            spinLockDemo.unLock();
        }, "A").start();

        //暂停500毫秒,线程A先于B启动
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unLock();
        }, "B").start();
/*  程序输出：
             A  ----come in
             B  ----come in
             A  ----task over,unLock...
             B  ----task over,unLock...
 */
    }
}
```

**`AtomicStampedReference`使用案例**：`AtomicStampedReference`（状态戳原子引用）：携带版本号的引用类型原子类，可以解决`ABA`问题，可以记录修改过几次

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t"+"首次版本号："+stamp);

            //暂停500毫秒,保证后面的t4线程初始化拿到的版本号和我一样
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"2次流水号："+stampedReference.getStamp());

            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"3次流水号："+stampedReference.getStamp());

        },"t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t"+"首次版本号："+stamp);

            //暂停1秒钟线程,等待上面的t3线程，发生了ABA问题
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);

            System.out.println(b+"\t"+stampedReference.getReference()+"\t"+stampedReference.getStamp());
        },"t4").start();

//程序输出：
//            t3	首次版本号：1
//            t4	首次版本号：1
//            t3	2次流水号：2
//            t3	3次流水号：3
//            false	100	3
    }
    private static void abaHappen() {
        new Thread(() -> {
            atomicInteger.compareAndSet(100,101);
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicInteger.compareAndSet(101,100);
        },"t1").start();

        new Thread(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicInteger.compareAndSet(100, 2022)+"\t"+atomicInteger.get());
        },"t2").start();
// 程序输出： true	2022
    }
}
```

**`AtomicMarkableReference`使用案例**：`AtomicMarkableReference`（状态戳(`true/false`)原子引用）可以原子地更新带有标记位的引用类型对象。解决是否修改过，它的定义就是将状态戳简化`true\false`，类似一次性筷子

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
/**
 * 原子更新带有标记位的引用类型对象
 * 解决是否修改过：它的定义就是将状态戳简化true\false，类似一次性筷子
 * 状态戳(true/false)原子引用
 */
public class AtomicMarkableReferenceDemo {
    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100,false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+"默认标识："+marked);
            //暂停1秒钟线程,等待后面的T2线程和我拿到一样的模式flag标识，都是false
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            markableReference.compareAndSet(100,1000,marked,!marked);
        },"t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+"默认标识："+marked);

            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName()+"\t"+"t2线程CASresult： "+b);
            System.out.println(Thread.currentThread().getName()+"\t"+markableReference.isMarked());
            System.out.println(Thread.currentThread().getName()+"\t"+markableReference.getReference());
        },"t2").start();
    }
//程序输出：
//                t1    默认标识：false
//                t2    默认标识：false
//                t2    t2线程CASresult： false
//                t2    true
//                t2    1000
}
```

###  8.5  对象的属性修改原子类

**对象的属性修改原子类**：`AtomiclntegerFieldUpdater`原子更新对象中`int`类型字段的值、`AtomicLongFieldUpdater`原子更新对象中`Long`类型字段的值、`AtomicReferenceFieldUpdater`原子更新引用类型字段的值。使用目的：以一种线程安全的方式操作非线程安全对象内的某些字段。使用要求：更新的对象属性必须使用`public volatile` 修饰符。因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法`newUpdater()`创建一个更新器，并且需要设置想要更新的类和属性

**官方文档介绍**：

```
AtomiclntegerFieldUpdater<T>
基于反射的实用程序，可对指定类的指定volatile int字段进行原子更新

AtomicLongFieldUpdater<T>
基于反射的实用程序，可以对指定类的指定volatile long字段进行原子更新

AtomicReferenceFieldUpdater<T,V>
基于反射的实用程序，可以对指定类的指定volatile引用字段进行原子更新
```

**`AtomicIntegerFieldUpdater`的使用**：更新的对象属性必须使用`public volatile` 修饰符。因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法`newUpdater()`创建一个更新器，并且需要设置想要更新的类和属性

```java
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
class BankAccount//资源类
{
    String bankName = "CCB";

    // 使用要求：更新的对象属性必须使用 public volatile 修饰符
    public volatile int money = 0;//钱数

    public void add()
    {
        money++;
    }

    //因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须
    // 使用静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");

    //不加synchronized，保证高性能原子性，局部微创小手术
    public void transMoney(BankAccount bankAccount)
    {
        fieldUpdater.getAndIncrement(bankAccount);
    }
}

/**
 * 以一种线程安全的方式操作非线程安全对象的某些字段
 * 需求：
 * 10个线程，
 * 每个线程转账1000，
 * 不使用synchronized,尝试使用AtomicIntegerFieldUpdater来实现。
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException
    {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <=10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=1000; j++) {
                        //bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"\t"+"result: "+bankAccount.money);
//程序输出： main    result: 10000
    }
}
```

**`AtomicReferenceFieldUpdater`的使用**：更新的对象属性必须使用`public volatile` 修饰符。因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法`newUpdater()`创建一个更新器，并且需要设置想要更新的类和属性

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
class MyVar //资源类
{
    public volatile Boolean isInit = Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyVar,Boolean> referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInit");
    public void init(MyVar myVar)
    {
        if (referenceFieldUpdater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE))
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"----- start init,need 2 seconds");
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"----- over init");
        }else{
            System.out.println(Thread.currentThread().getName()+"\t"+"----- 已经有线程在进行初始化工作。。。。。");
        }
    }
}
/**
 * 需求：
 * 多线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作，
 * 要求只能被初始化一次，只有一个线程操作成功
 */
public class AtomicReferenceFieldUpdaterDemo
{
    public static void main(String[] args)
    {
        MyVar myVar = new MyVar();

        for (int i = 1; i <=5; i++) {
            new Thread(() -> {
                myVar.init(myVar);
            },String.valueOf(i)).start();
        }
    }
// 程序输出：
//           2  ----- 已经有线程在进行初始化工作。。。。。
//           5  ----- 已经有线程在进行初始化工作。。。。。
//           4  ----- 已经有线程在进行初始化工作。。。。。
//           1  ----- start init,need 2 seconds
//           3  ----- 已经有线程在进行初始化工作。。。。。
//           1  ----- over init
}
```

###  8.6 原子操作增强类 | `LongAdder`的底层原理

#####  8.6.1 原子操作增强类简介

**原子操作增强类**：`DoubleAccumulator`、`DoubleAdder`、`LongAccumulator`、`LongAdder`

- `DoubleAccumulator`：一个或多个变量共同维护使用提供的函数更新的运行`double`值
- `DoubleAdder`：一个或多个变量共同维持最初的零和`double`总和
- `LongAccumulator`：一个或多个变量共同维护使用提供的函数更新的运行`long`值
- `LongAdder`：一个或多个变量共同维持最初为零的总和为`long`

##### 8.6.2 `LongAdder`类简介 | 使用方法

**`LongAdder`类简介**：`LongAdder` 是 Java 中 `java.util.concurrent` 包提供的类之一，用于支持高并发情况下的原子操作。它的主要目的是在高度并发情况下提供比传统的 `AtomicLong` 更好的性能。与 `AtomicLong` 不同，`LongAdder` 将内部计数器分割成多个部分，每个线程访问时都会更新不同的部分，减少了线程竞争。当需要获取总和时，它会将所有部分的值汇总起来，从而实现高效的累加操作

**阿里巴巴开发手册**：

```java
17.【参考】volatile解决多线程内存不可见问题对于。一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题
说明:如果是count++操作，使用如下类实现： AtomicInteger count = new AtomicInteger(); count.addAndGet(1);
如果是JDK8，推荐使用LongAdder对象，比AtomicLong性能更好（减少乐观锁的重试次数)
    
LongAdder应用场景&相关面试题：
1.热点商品点赞计算器，点赞数加加统计，不要求实时精确
2.一个很大的List，里面都是int类型，如何实现加加，说说思路
```

**`LongAdder`类常用`API`**：

```
void add(long x)      将当前的value加x
void increment()      将当前的value加1
void decrement()      将当前的value减1
long sum()            返回当前值。特别注意，在没有并发更新value的情况下，sum会返回一个精确值，在存在并发的情况下，sum不保证返回精确值
void reset()          将value重置为0，可用于替代重新new一个LongAdder，但此方法只可以在没有并发更新的情况下使用
long sumThenReset()   获取当前value，并将value重置为0
```

**`LongAccumulator`类 和 `LongAdder`类使用演示**：实现点赞计效器，看看性能

```java
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
```

 **演示`LongAdder`的高性能**：

```java
import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
class ClickNumber //资源类
{
    int number = 0;

    // 方案一：使用synchronized实现累加
    public synchronized void clickBySynchronized()
    {
        number++;
    }

    // 方案二：使用AtomicLong实现累加
    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong()
    {
        atomicLong.getAndIncrement();
    }

    // 方案三：使用LongAdder实现累加
    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder()
    {
        longAdder.increment();
    }
    // 方案四：使用LongAccumulator实现累加
    LongAccumulator longAccumulator = new LongAccumulator((x,y) -> x + y,0);
    public void clickByLongAccumulator()
    {
        longAccumulator.accumulate(1);
    }
}

/**
 * 需求： 50个线程，每个线程100W次，统计总点赞数
 */
public class AccumulatorCompareDemo
{
    public static final int _1W = 10000;
    public static final int threadNumber = 50;

    public static void main(String[] args) throws InterruptedException
    {
        ClickNumber clickNumber = new ClickNumber();
        long startTime;
        long endTime;

        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickBySynchronized: "+clickNumber.number);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickByAtomicLong: "+clickNumber.atomicLong.get());


        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickByLongAdder: "+clickNumber.longAdder.sum());

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickByLongAccumulator: "+clickNumber.longAccumulator.get());
//程序输出：
//            ----costTime: 4171 毫秒  clickBySynchronized: 50000000
//            ----costTime: 711 毫秒   clickByAtomicLong: 50000000
//            ----costTime: 221 毫秒   clickByLongAdder: 50000000
//            ----costTime: 79 毫秒    clickByLongAccumulator: 50000000
    }
}
```

#####  8.6.3 `LongAdder`的底层原理

**`LongAdder`架构**：`LongAdder`是`Striped64`的子类

![image-20240206041129161](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142350784.png)



`LongAdder`：

```java
public class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;
}
```

`Striped64`：

```java
abstract class Striped64 extends Number {
}
```

**`LongAdder`官网说明**：

> public class LongAdder extends Number implements Serializable
> 一个或多个变量，它们共同维持最初的零long总和。当跨线程争用更新（方法add(long))时，变量集可以动态增长以减少争用。方法sun()（或等效地，longValue()）返回保持总和的变量的当前总和
>
> 当多个线程更新用于收集统计信息但不用于细粒度同步控制的目的的公共和时，LongAdder通常优于AtomicLong。在低更新争用下，这两个类具有相同的特征。但在高争用的情况下，这一类的预期吞吐量明显更高，但代价是空间消耗更高
>
> LongAdders可与ConcurrentHashMap一起使用，以维护可扩展的频率图（直方图或多重集的形式）。例如，要将计数添加到ConcurrentHashMap<String, LongAdder> freqs ，如果尚未初始化，则可以使用freqs.computeIfAbsent(key,k -> new LongAdder()).increment();
>
> LongAdder 类扩展Number，但不定义诸如方法equals , hashCode和compareTo ，因为实例预计将发生突变，所以不如收集钥匙有用

**阿里Java开发手册**：

```
17.【参考】volatile解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题
说明：如果是count++操作，使用如下类实现：AtomicInteger count = new AtomicInteger();count.addAndGet(1);
如果是JDK8，推荐使用LongAdder对象，比AtomicLong性能更好（减少乐观锁的重试次数）
```

**`Striped64`**：`Striped64`有几个比较重要的成员函数

```java
abstract class Striped64 extends Number {
        /** Number of CPUS, to place bound on table size */
        // CPU数量，即ceLls数组的最大长度
        static final int NCPU = Runtime.getRuntime().availableProcessors();

        /**
         * Table of cells. When non-null, size is a power of 2.
         */
        // cells数组，为2的幂，2,4,8,16.....，方便以后位运算
        transient volatile Cell[] cells;

        /**
         * Base value, used mainly when there is no contention, but also as
         * a fallback during table initialization races. Updated via CAS.
         */
        // 基础value值，当并发较低时，只累加该值主要用于没有竞争的情况，通过CAS_更新
        transient volatile long base;

        /**
         * Spinlock (locked via CAS) used when resizing and/or creating Cells.
         */
        // 创建或者扩容Cells数组时使用的自旋锁变量调整单元格大小(扩容），创建单元格时使用的锁
        transient volatile int cellsBusy;
}
```

**`Striped64`主要变量和方法**：

```
base:             类似于AtomicLong中全局的value值。在没有竞争情况下数据直接累加到base上，或者cells扩容时，也需要将数据写入到base上
collide:          表示扩容意向，false一定不会扩容，true可能会扩容
cellsBusy:        初始化cells或者扩容cells需要获取锁， 0:表示无锁状态  1:表示其他线程已经持有了锁
casCellsBusy():   通过CAS操作修改cellsBusy的值，CAS成功代表获取锁返回true

NCPU:             当前计算机CPU数量，Cell数组扩容时会使用到
getProbe():       获取当前线程的hash值
advanceProbe():   重置当前线程的hash值
```

**`Striped64`内部类`Cell`**：`Cell`类是`java.util.concurrent.atomic`下`Striped64`的一个内部类

```java
abstract class Striped64 extends Number {
/**
 * Padded variant of AtomicLong supporting only raw accesses plus CAS.
 *
 * JVM intrinsics note: It would be possible to use a release-only
 * form of CAS here, if it were provided.
 */
@sun.misc.Contended static final class Cell {
    volatile long value;
    Cell(long x) { value = x; }
    final boolean cas(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long valueOffset;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> ak = Cell.class;
            valueOffset = UNSAFE.objectFieldOffset
                (ak.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
}
```

**`LongAdder`的底层原理**：如果是`JDK8`，推荐使用`LongAdder`对象，比`AtomicLong`性能更好（减少乐观锁的重试次数）。`AtomicLong`的底层原理是使用`CAS（Compare And Swap）`自旋锁。`LongAdder`的底层原理是使用分段锁和热点分离技术

![image-20240206105308308](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142350927.png)



**`LongAdder`的底层原理**：`LongAdder`的基本思路就是分散热点，将`value`值分散到一个`Cell`数组中，不同线程会命中到数组的不同槽中，各个线程只对自己槽中的那个值进行`CAS`操作，这样热点就被分散了，冲突的概率就小很多。如果要获取真正的`long`值，只要将各个槽中的变量值累加返回。`sum()`会将所有`Cell`数组中的`value`和`base`累加作为返回值，核心的思想就是将之前`AtomicLong`一个`value`的更新压力分散到多个`value`中去，从而降级更新热点

![image-20240206110501834](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142350246.png)

**`LongAdder`的底层原理**：`LongAdder`内部有一个`base`变量，一个`Cel`l数组。低并发，直接累加到`base`变量上。高并发，累加进各个线程自己的`Cell`数组的`Cell[i]`槽中。`LongAdder`在无竞争的情况，跟`AtomicLong`一样，对同一个`base`进行操作，当出现竞争关系时则是采用化整为零分散热点的做法，用空间换时间，用一个数组`cells`，将一个`value`拆分进这个数组`cells`。多个线程需要同时对`value`进行操作时候，可以对线程`id`进行`hash`得到`hash`值，再根据`hash`值映射到这个数组`cells`的某个下标，再对该下标所对应的值进行自增操作。当所有线程操作完毕，将数组`cells`的所有值和`base`都加起来作为最终结果



![image-20240206112721810](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142351974.png)



**`LongAdder`类`add`方法源码解析**：1.最初无竞争时只更新`base  `  2.如果更新`base`失败后，首次新建一个`Cell`数组   3.当多个线程竞争同一个`cell`比较激烈时，可能就要对`cell`扩容

**`LongAdder`类`add`方法源码解析**：`LongAdder`的`add`方法用于向`LongAdder`中添加给定的值。`add`方法利用`CAS`操作来避免锁竞争，将值添加到`LongAdder`中。如果存在`Cell`数组，则通过无锁的方式更新`Cell`的值，以减少对`base`的竞争，提高并发性能。主要逻辑如下：

- 首先，它尝试直接对`base`值进行`CAS（Compare And Swap）`操作，如果能成功执行`CAS`，则直接将值加到`base`上，完成操作

- 如果`CAS`操作失败，则会检查是否存在`Cell`数组（`cells`）

  - 如果存在`Cell`数组，则使用`getProbe()`生成一个`probe`值，通过位运算找到对应的`Cell`，再尝试无锁更新对应的`Cell`内部值

  - 如果没有`Cell`数组或者定位到的`Cell`为空，则使用`longAccumulate`进行进一步的更新操作

```java
public class LongAdder extends Striped64 implements Serializable {
    //    as表示cells引用
    //    b表示获取的base值
    //    v表示期望值
    //    m表示cells数组的长度
    //    a表示当前线程命中的cell单元格
    public void add(long x) {
        Cell[] as; long b, v; int m; Cell a;
       // 首次首线程(as = cells) != null)一定是false，此时走casBase方法，以CAS的方式更新base值，且只有当cas失败时，才会走到if中
       // 条件1：cells不为空
       // 条件2：cas操作base失败，说明其它线程先一步修改了base 正在出现竞争
        if ((as = cells) != null || !casBase(b = base, b + x)) {
            // true无竞争，false表示竞争激烈，多个线程hash到同一个cell，可能要扩容
            boolean uncontended = true;
            // 条件1: cells为空
            // 条件2：应该不会出现
            // 条件3: 当前线程所在的cell为空，说明当前线程还没有更新过cell，应初始化一个cell
            // 条件4: 更新当前线程所在的cell失败，说明现在竞争很激烈，多个线程hash到了同一个cell，应扩容
            if (as == null || (m = as.length - 1) < 0 ||
                (a = as[getProbe() & m]) == null ||
                !(uncontended = a.cas(v = a.value, v + x)))
                longAccumulate(x, null, uncontended);  // 调用striped64中的方法处理
            //  getProbe()方法返回的是线程中的threadLocalRandomProbe字段
            //  它是通过随机数生成的一个值，对于一个确定的线程这个值是固定的(除非刻意修改它)
            
        }
    }
}
```

**`LongAdder`类`add`方法源码总结**：

1.如果`cells`表为空，尝试用`CAS`更新`base`字段，或功则退出

2.如果`Cells`表为空，`CAS`更新`base`字段失败，出现竞争，`uncontended`为`true`，调用`longAccumulate`

3.如果`Cells`表非空，但当前线程映射的槽为空，`uncontended`为`true`，调用`longAccumulate`

4.如果`Cells`表非空，且前线程映射的槽非空，`CAS`更新`Cell`的值，成功则返回，否则，`uncontended`设为`false`，调用`longAccumulate`

**`longAccumulate`方法源码分析**：

一、`longAccumulate()`方法的入参说明：

```
long x                    需要增加的值，一般默认都是1
LongBinaryoperator fn     默认传递的是null
wasUncontended            竞争标识，如果是false则代表有竞争。只有cells初始化之后，并且当前线程CAS竞争修改失败，才会是false
```

二、`Striped64`核心变量或者方法定义：

```
base:           类似于AtomicLong中全局的value值。在没有竞争情况下数据直接累加到base上，或者cells扩容时，也需要将数据写入到base上
collide:        表示扩容意向，false一定不会扩容，true可能会扩容
cellsBusy:      初始化cells或者扩容cells需要获取锁, 0:表示无锁状态  1:表示其他线程已经持有了锁
casCellsBusy(): 通过CAS操作修改cellsBusy的值，CAS成功代表获取锁，返回true
NCPU:           当前计算机CPU数量，Cell数组扩容时会使用到
getProbe():     获取当前线程的hash值
advanceProbe(): 重置当前线程的hash值
```

三、`longAccumulate`方法源码分析：首先给当前线程分配一个`hash`值，然后进入一个`for(;;)`自旋，这个自旋分为三个分支`CASE`：**`CASE1`**:    `Cell`数组已经初始化。`cell`数组不再为空且可能存在`cell`数组扩容。**`CASE2`**:    `Cell`数组未初始化(首次新建)。`cells`没有加锁且没有初始化，则尝试对它进行加锁，并初始化`cells`数组。**`CASE3`**:    `Cell`数组正在初始化中。`cells`正在进行初始化，则尝试直接在基数`base`上进行累加操作

```java
abstract class Striped64 extends Number {    
     /**
     * Handles cases of updates involving initialization, resizing,
     * creating new Cells, and/or contention. See above for
     * explanation. This method suffers the usual non-modularity
     * problems of optimistic retry code, relying on rechecked sets of
     * reads.
     *
     * @param x the value
     * @param fn the update function, or null for add (this convention
     * avoids the need for an extra field or function in LongAdder).
     * @param wasUncontended false if CAS failed before call
     */
    // longAccumulate()方法的入参说明:
    // long x                    需要增加的值，一般默认都是1
    // LongBinaryoperator fn     默认传递的是null
    // wasUncontended            竞争标识，如果是false则代表有竞争。只有cells初始化之后，并且当前线程CAS竞争修改失败，才会是false
    final void longAccumulate(long x, LongBinaryOperator fn,
                              boolean wasUncontended) {
        // 存储线程的probe值
        int h;
        // 如果getProbe()方法返回0，说明随机数未初始化
        if ((h = getProbe()) == 0) {  //这个if相当于给当前线程生成一个非的hash值
            // 使用ThreadLocalRandom为当前线程重新计算一个hash值,强制初始化
            ThreadLocalRandom.current(); // force initialization
            // 重新获取probe值, hash值被重置就好比一个全新的线程一样，所以设置了wasUncontended竞争状态为true
            h = getProbe();
            // 重新计算了当前线程的hash后认为此次不算是一次竞争，都未初始化，肯定还不存在竞争激烈wasUncontended竞争状态为true
            wasUncontended = true;
        }
        // 如果hash取模映射得到的Cell单元不是null，则collide为true，此值也可以看作是扩容意向
        boolean collide = false;                // True if last slot nonempty
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            // CASE1:cells已经被初始化了
            // cell数组不再为空且可能存在cell数组扩容
            if ((as = cells) != null && (n = as.length) > 0) {
                // 判断当前线程hash后指向的数据位置元素是否为空
                // 如果为空则将Cell数据放入数组中，跳出循环。如果不空则继续循环
                if ((a = as[(n - 1) & h]) == null) { // 当前线程的hash值运算后映射得到的Cell单元为null，说明该Cell没有被使用
                    // Cell[]数组没有正在扩容
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        // 创建一个cell单元
                        Cell r = new Cell(x);   // Optimistically create
                        if (cellsBusy == 0 && casCellsBusy()) { // 尝试加锁，成功后cellsBusy==1

                            boolean created = false;
                            try {               // Recheck under lock
                                // 在有锁的情况下再检测一遍之前的判断
                                // 将cell单元附到Cell[ ]数组上
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null &&
                                    (m = rs.length) > 0 &&
                                    rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = false;
                }
                // wasUncontended表示cells初始化后，当前线程竞争修改失败
                // wasUncontended =false，这里只是重新设置了这个值为true
                // 紧接着执行advanceProbe(h)重置当前线程的hash，重新循环
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                // 说明当前线程对应的数组中有了数据，也重置过hash值
                // 这时通过CAS操作尝试对当前数中的value值进行累加x操作，x默认为1
                // 如果CAS成功则直接跳出循环
                else if (a.cas(v = a.value, ((fn == null) ? v + x :
                                             fn.applyAsLong(v, x))))
                    break;
                // 如果n大于CPU最大数量，不可扩容
                // 并通过下面的h = advanceProbe(h)方法修改线程的probe再重新尝试
                else if (n >= NCPU || cells != as)
                    collide = false;            // At max size or stale
                // 如果扩容意向collide是false则修改它为true，然后重新计算当前线程的hash值继续循环
                // 如果当前数组的长度已经大于了CPU的核数，就会再次设置扩容意向collide=false (见上一个if)
                else if (!collide)
                    collide = true;
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        // 当前的cells数组和最先赋值的as是同一个，代表没有被其他线程扩容过
                        if (cells == as) {      // Expand table unless stale
                            // 按位左移1位来操作，扩容大小为之前容量的两倍
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                // 扩容后再将之前数组的元素拷贝到新数组中
                                rs[i] = as[i];
                            cells = rs;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                h = advanceProbe(h);
            }
            // CASE2：cells没有加锁且没有初始化，则尝试对它进行加锁，并初始化cells数组
            // 首次初始化Cell[]数组(首次新建)。未初始化过Cell数组，尝试占有锁并首次初始化cells数组，如果上面条件都执行成功就会执行数组的初始化及赋值操作
            // Cell[] rs =new Cell[2]表示数组的长度为2，rs[h & 1]= new Cell(x)表示创建一个新的Cell元素，value是x值，默认为1。
            // h&1类似于我们之前HashMap常用到的计算散列桶index的算法，通常都是hash & (table.len-1)。同hashmap一个意思
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    // 此处再次判断 cells == as （进行double check）
                    // 如果不double check，就会再次new—个cell数组，上一个线程对应数组中的值将会被篡改
                    if (cells == as) {
                        // 新建—个大小为2的Cell数组
                        Cell[] rs = new Cell[2];
                        // 找到当前线星hash到数组中的位置并创建其对应的Cell
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finally {
                    // 释放锁设置cellsBusy = 0，设置扩容状态，然后继续循环执行
                    cellsBusy = 0;
                }
                if (init)
                    break;
            }
            // CASE3:cells正在进行初始化，则尝试直接在基数base上进行累加操作
            // 兜底：多个线程尝试CAS修改失败的线程会走到这个分支
            // 此分支实现直接操作base基数，将值累加到base上，也即其它线程正在初始化，多个线程正在更新base的值
            else if (casBase(v = base, ((fn == null) ? v + x :
                                        fn.applyAsLong(v, x))))
                break;                          // Fall back on using base
        }
    }
    
    /**
     * Returns the probe value for the current thread.
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     */
    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }
}
```

**`LongAdder`类的抽象父类`Striped64`的`longAccumulate`方法源码分析图**：

![image-20240206173316280](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142351770.png)





**`LongAdder`类的`sum`方法源码分析**：`sum()`会将所有`Cell`数组中的`value`和`base`累加作为返回值。核心的思想就是将之前`AtomicLong`一个`value`的更新压力分散到多个`value`中去，从而降级更新热点

```java
public class LongAdder extends Striped64 implements Serializable {
    /**
     * Returns the current sum.  The returned value is <em>NOT</em> an
     * atomic snapshot; invocation in the absence of concurrent
     * updates returns an accurate result, but concurrent updates that
     * occur while the sum is being calculated might not be
     * incorporated.
     *
     * @return the sum
     */
    public long sum() {
        Cell[] as = cells; Cell a;
        long sum = base;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    sum += a.value;
            }
        }
        return sum;
    }
}
```

**并发情况下`sum`方法得到的值不精确**：`sum`执行时，并没有限制对`base`和`cells`的更新。所以`LongAdder`不是强一致性的，它是最终一致性的。首先，最终返回的`sum`局部变量，初始被复制为`base`，而最终返回时，很可能`base`已经被更新了，而此时局部变量`sum`不会更新，造成不一致。其次，这里对`cell`的读取也无法保证是最后一次写入的值。所以，`sum`方法在没有并发的情况下，可以获得正确的结果

#####  8.6.4 `AtomicLong`和`LongAdder`的总结对比

 **`AtomicLong`和`LongAdder`的对比**：

| 特性     | `AtomicLong`                                 | `LongAdder`                                        |
| -------- | -------------------------------------------- | -------------------------------------------------- |
| 线程竞争 | 高                                           | 低                                                 |
| 性能     | 在低并发情况下性能较差，高并发情况下性能较好 | 针对高并发情况下性能优化较好，低并发情况下性能稍差 |
| 适用场景 | 低并发场景                                   | 高并发场景                                         |
| 内部实现 | 使用`CAS（Compare And Swap）`自旋锁          | 使用分段锁和热点分离技术                           |
| 内存占用 | 高                                           | 低                                                 |
| 初始值   | 需要手动初始化                               | 自动初始化为零                                     |

 **`AtomicLong`和`LongAdder`的使用场景**：

- `AtomicLong`适用于低并发环境，较为简单。当需要保证线程安全，可允许一些性能损耗，要求高精度时可使用。`AtomicLong`保证精度，存在性能代价。`AtomicLong`是多个线程针对单个热点值value进行原子操作
- `LongAdder`适用于高并发环境，能够有效地减少线程竞争和提高并发性能。当需要在高并发下有较好的性能表现，且对值的精确度要求不高时可以使用。`AtomicLong`保证精度，存在性能代价。`LongAdder`是每个线程拥有自己的槽，各个线程一般只对自己槽中的那个值进行`CAS`操作

 **`AtomicLong`和`LongAdder`的总结**：

`AtomicLong`：

- 原理: `CAS`+自旋  `incrementAndGet`
- 使用场景：低并发下的全局计算。`AtomicLong`能保证并发情况下计数的准确性，其内部通过`CAS`来解决并发安全性的问题
- 缺陷:  高并发后性能急剧下降。`AtomicLong`的自旋会成为瓶颈,`N`个线程`CAS`操作修改线程的值，每次只有一个成功，其它N-1失败，失败的不停的自旋直到成功，这样大量失败自旋的情况，一下子`cpu`就打高了

`LongAdder`：

- 原理: `CAS+Base+Cell`数组分散,空间换时间并分散了热点数据
- 场景: 高并发下的全局计算
- 缺陷: `sum`求和后还有计算线程修改结果的话，实时结果不够准确

## 9. `ThreadLocal`

###  9.1 `ThreadLocal`简介 | 使用方法

**`ThreadLocal`**：`ThreadLocal`提供线程局部变量。这些变量与正常的变量不同，因为每一个线程在访问`ThreadLocal`实例的时候（通过其`get`或`set`方法)都有自己的、独立初始化的变量副本。`ThreadLocal`实例通常是类中的私有静态字段，使用它的目的是希望将状态（例如，用户ID或事务ID)与线程关联起来

**`ThreadLocal`作用**：`ThreadLocal` 用于在多线程编程中存储线程局部变量。每个线程都可以使用 `ThreadLocal` 来创建自己的局部变量，互不干扰。它通常用于将线程相关的对象绑定到线程上，以便在整个线程的生命周期内使用。`ThreadLocal` 提供了一个 `get()` 方法和 `set()` 方法，允许每个线程访问和修改其自己的变量副本，而不会影响其他线程。同时，它也提供了 `initialValue()` 方法，可以为每个线程的局部变量设定初始值。`ThreadLocal` 实现每一个线程都有自己专属的本地变量副本(自己用自己的变量不麻烦别人，不和其他人共享，人人有份，人各一份)。主要解决了让每个线程绑定自己的值，通过使用`get()`和`set()`方法，获取默认值或将其值更改为当前线程所存的副本的值从而避免了线程安全问题。`ThreadLocal`可以在多线程环境下记录线程的上下文信息

**`ThreadLocal`类的方法**：

| 变量和类型                  | 方法                                          | 描述                                       |
| --------------------------- | --------------------------------------------- | ------------------------------------------ |
| T                           | get()                                         | 返回当前线程的此线程局部变量副本中的值     |
| protected T                 | initialValue()                                | 返回此线程局部变量的当前线程的“初始值”     |
| void                        | remove()                                      | 删除此线程局部变量的当前线程值             |
| void                        | set(T value)                                  | 将此线程局部变量的当前线程副本设置为指定值 |
| `static <S> ThreadLocal<S>` | `withInitial(Supplier<? extends S> supplier)` | 创建一个线程局部变量                       |

**`ThreadLocal`相关面试题**：

```
ThreadLocal中ThreadLocalMap的数据结构和关系?
ThreadLocal的key为什么是弱引用
ThreadLocal内存泄露问题你知道吗?
ThreadLocal中最后为什么要加remove方法?
```

**阿里巴巴`java`开发手册**：

```java
6.【强制】必须回收自定义的ThreadLocal变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题,尽量在代理中使用try-finally块进行回收。
正例:
objectThreadLocal.set(userInfo);
    try {
     //...
    } finally {
        objectThreadLocal.remove();
    }
```

**代码案例演示一**：5个销售卖房子，集团高层只关心销售总量的准确统计数，按照总销售额统计，方便集团公司给部门发送奖金。为了数据安全只能加锁。5个销售卖房子，集团高层只关心销售总量的准确统计数

```java
import java.util.Random;
import java.util.concurrent.TimeUnit;
class House1 //资源类
{
    int saleCount = 0;

    // 为了数据安全只能加锁
    public synchronized void saleHouse()
    {
        ++saleCount;
    }
}
/**
 * 需求1： 5个销售卖房子，集团高层只关心销售总量的准确统计数
 */
public class ThreadLocalBeforeDemo {
    public static void main(String[] args) throws InterruptedException {
        House1 house = new House1();

        for (int i = 1; i <=5; i++) {
            new Thread(() -> {
                // size：模拟当前房产销售的销售额
                int size = new Random().nextInt(5)+1;
                System.out.println(size);
                for (int j = 1; j <=size; j++) {
                    house.saleHouse();
                }
            },String.valueOf(i)).start();
        };
        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()+"\t"+"共计卖出多少套： "+house.saleCount);
//程序输出：
//            1
//            4
//            1
//            5
//            3
//            main  共计卖出多少套： 14
    }
}
```

**代码案例演示二**：5个销售卖完随机数房子，各自独立销售额度，自己业绩按提成走，分灶吃饭，各个销售自己动手，丰衣足食。**需要使用`ThreadLocal`来存储线程局部变量**

```java
import java.util.Random;
class House //资源类
{
    int saleCount = 0;
    /*ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue()
        {
            return 0;
        }
    };*/
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);
    public void saleVolumeByThreadLocal()
    {
        saleVolume.set(1+saleVolume.get());
    }
}

/**
 * 需求2： 5个销售卖完随机数房子，各自独立销售额度，自己业绩按提成走，分灶吃饭，各个销售自己动手，丰衣足食
 * 希望各自分牡吃饭, 各凭销售本事提成，按照出单数各自统计，比如某房产中介销售都有自己的销售额指标，自己专属自己的，不和别人掺和
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        for (int i = 1; i <=5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5)+1;
                for (int j = 1; j <=size; j++) {
                    house.saleVolumeByThreadLocal();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"号销售卖出："+house.saleVolume.get());
            },String.valueOf(i)).start();
        };
    }
//程序输出：
//            5 号销售卖出：1
//            4 号销售卖出：1
//            3 号销售卖出：5
//            2 号销售卖出：3
//            1 号销售卖出：3
}
```

**代码案例演示三**：必须回收自定义的 `ThreadLocal`变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的 `ThreadLocal` 变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用`try-finally` 块进行回收

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class MyData {
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);
    public void add()
    {
        threadLocalField.set(1 + threadLocalField.get());
    }
}
/**
 阿里巴巴java开发手册：
【强制】必须回收自定义的 ThreadLocal 变量，尤其在线程池场景下，线程经常会被复用，如果不清理
自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用
try-finally 块进行回收。
 */
public class ThreadLocalDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 5; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myData.threadLocalField.get();
                        myData.add();
                        Integer afterInt = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName()+"\t"+"beforeInt:"+beforeInt+"\t afterInt: "+afterInt);
                    } finally {
                        myData.threadLocalField.remove();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
//不使用remove时的程序输出：
//                        pool-1-thread-2   beforeInt:0     afterInt: 1
//                        pool-1-thread-1   beforeInt:0     afterInt: 1
//                        pool-1-thread-3   beforeInt:0     afterInt: 1
//                        pool-1-thread-2   beforeInt:1     afterInt: 2
//                        pool-1-thread-1   beforeInt:1     afterInt: 2

//使用remove时的程序输出：
//                        pool-1-thread-3   beforeInt:0     afterInt: 1
//                        pool-1-thread-1   beforeInt:0     afterInt: 1
//                        pool-1-thread-2   beforeInt:0     afterInt: 1
//                        pool-1-thread-1   beforeInt:0     afterInt: 1
//                        pool-1-thread-3   beforeInt:0     afterInt: 1
    }
}
```

`ThreadLocal`使用总结：每个`Thread`内有自己的实例副本且该副本只由当前线程自己使用，既然其它`Thread`不可访问，那就不存在多线程间共享的问题，统一设置初始值，但是每个线程对这个值的修改都是各自线程互相独立的

###  9.2 `ThreadLocal`原理 与 源码分析

**`ThreadLocal`实现原理**：

- 每个`Thread`中都存储着一个`ThreadLocalMap`类型的成员变量
- `ThreadLocal`本身不存储数据，`ThreadLoclMap`才是关键的存储结构。`ThreadLocal`像是一个工具类，基于`ThreadLocal`去操作`ThreadLocalMap`
- `ThreadLocalMap`本身就是基于`Entry[]`实现的，因为一个线程可以绑定多个`ThreadLocal`，这样一来，可能需要存储多个数据，所以采用`Entry[]`的形式实现
- 每一个线程都自己独立的`ThreadLocalMap`，再基于`ThreadLocal`对象本身作为`key`，对`value`进行存取
- `ThreadLocalMap`的key是一个弱引用。弱引用的特点是，即便有弱引用，在`GC`时，也必须被回收。这里是为了在`ThreadLocal`对象失去引用后，如果`key`的引用是强引用，会导致`ThreadLocal`对象无法被回收，从而导致内存泄露



![image-20240310213333032](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142351530.png)



**`Thread`、`ThreadLocal`和`ThreadLocalMap`三者关系如下**：`Thread`类是`Java`中用于创建和操作线程的类。每个`Thread`对象代表一个线程，每个线程都有一个与之关联的`ThreadLocalMap`对象。`ThreadLocal`类是一个用于在多线程环境下保存线程局部变量的类。每个`ThreadLocal`对象都可以保存一个线程私有的变量，该变量对其他线程是不可见的。每个线程都有一个`ThreadLocalMap`对象，用于存储该线程的所有`ThreadLocal`变量。`ThreadLocal`类提供了一些方法用于获取和设置线程局部变量的值，比如`get()`、`set()`等。`ThreadLocalMap`类是一个用于存储线程局部变量的哈希表。它是`ThreadLocal`类的内部类。每个线程都有一个`ThreadLocalMap`对象，用于存储该线程的所有`ThreadLocal`变量。`ThreadLocalMap`类提供了一些方法用于获取和设置线程局部变量的值，比如`getEntry()`、`set()`等。三者关系总结：`Thread`类是用于创建和操作线程的类，每个线程都有一个与之关联的`ThreadLocalMap`对象，而`ThreadLocal`类是一个用于在多线程环境下保存线程局部变量的类，每个线程都有一个`ThreadLocalMap`对象，用于存储该线程的所有`ThreadLocal`变量

![image-20240207101702996](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142351105.png)



**`Thread`、`ThreadLocal`和`ThreadLocalMap`三者关系示意图**：

![image-20240207101330285](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142351050.png)

**`Thread`和`ThreadLocal`关系**：每个`Thread`线程都有一个与之关联的`ThreadLocalMap`对象

```java
// Thread和ThreadLocal关系:
public class Thread implements Runnable {
        /* ThreadLocal values pertaining to this thread. This map is maintained
         * by the ThreadLocal class. */
        ThreadLocal.ThreadLocalMap threadLocals = null;
}
```

**`ThreadLocal`和`ThreadLocalMap`的关系**：`ThreadLocalMap`类是一个用于存储线程局部变量的哈希表。`ThreadLocalMap`是`ThreadLocal`类的内部类。`threadLocaMap`实际上就是一个以`threadLocal`实例为key，任意对象为`value`的`Entry`对象。为`ThreadLocal`变量赋值，实际上就是以当前`threadLocal`实例为key，把值为`value`的`Entry`往这个`threadLocalMap`中存放

```java
public class ThreadLocal<T> {
        /**
         * Create the map associated with a ThreadLocal. Overridden in
         * InheritableThreadLocal.
         *
         * @param t the current thread
         * @param firstValue value for the initial entry of the map
         */
        void createMap(Thread t, T firstValue) {
            t.threadLocals = new ThreadLocalMap(this, firstValue);
        }
        /**
         * ThreadLocalMap is a customized hash map suitable only for
         * maintaining thread local values. No operations are exported
         * outside of the ThreadLocal class. The class is package private to
         * allow declaration of fields in class Thread.  To help deal with
         * very large and long-lived usages, the hash table entries use
         * WeakReferences for keys. However, since reference queues are not
         * used, stale entries are guaranteed to be removed only when
         * the table starts running out of space.
         */
        static class ThreadLocalMap {
                    /**
                     * The initial capacity -- MUST be a power of two.
                     */
                    private static final int INITIAL_CAPACITY = 16;

                    /**
                     * The table, resized as necessary.
                     * table.length MUST always be a power of two.
                     */
                    private Entry[] table;
                    /**
                     * The entries in this hash map extend WeakReference, using
                     * its main ref field as the key (which is always a
                     * ThreadLocal object).  Note that null keys (i.e. entry.get()
                     * == null) mean that the key is no longer referenced, so the
                     * entry can be expunged from table.  Such entries are referred to
                     * as "stale entries" in the code that follows.
                     */
                    static class Entry extends WeakReference<ThreadLocal<?>> {
                        /** The value associated with this ThreadLocal. */
                        Object value;

                        Entry(ThreadLocal<?> k, Object v) {
                            super(k);
                            value = v;
                        }
                    }
                /**
                 * Construct a new map initially containing (firstKey, firstValue).
                 * ThreadLocalMaps are constructed lazily, so we only create
                 * one when we have at least one entry to put in it.
                 */
                ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
                    table = new Entry[INITIAL_CAPACITY];
                    int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
                    table[i] = new Entry(firstKey, firstValue);
                    size = 1;
                    setThreshold(INITIAL_CAPACITY);
                }
            // 省略。。。
        }
}
```

**`ThreadLocal`的`set`方法的源码分析**：

```java
public class ThreadLocal<T> {
        /**
         * Sets the current thread's copy of this thread-local variable
         * to the specified value.  Most subclasses will have no need to
         * override this method, relying solely on the {@link #initialValue}
         * method to set the values of thread-locals.
         *
         * @param value the value to be stored in the current thread's copy of
         *        this thread-local.
         */
        public void set(T value) {
            // 首先获取当前线程对象Thread t = Thread.currentThread();
            Thread t = Thread.currentThread();
            // 调用getMap(t)方法获取当前线程的ThreadLocalMap对象map
            ThreadLocalMap map = getMap(t);
            if (map != null)
                // 如果map不为null，则调用map.set(this, value)方法将当前ThreadLocal对象和value值存入map中
                map.set(this, value);
            else
                // 如果map为null，则调用createMap(t, value)方法创建一个新的ThreadLocalMap对象，并将其赋值给当前线程的threadLocals属性
                createMap(t, value);
        }

        /**
         * Create the map associated with a ThreadLocal. Overridden in
         * InheritableThreadLocal.
         * @param t the current thread
         * @param firstValue value for the initial entry of the map
         */
        void createMap(Thread t, T firstValue) {
            t.threadLocals = new ThreadLocalMap(this, firstValue);
        }
}
```

**`ThreadLocal`的`get`方法源码分析**：

```java
public class ThreadLocal<T> {
        /**
         * Returns the value in the current thread's copy of this
         * thread-local variable.  If the variable has no value for the
         * current thread, it is first initialized to the value returned
         * by an invocation of the {@link #initialValue} method.
         *
         * @return the current thread's value of this thread-local
         */
        public T get() {
            // 获取当前线程对象Thread t = Thread.currentThread();
            Thread t = Thread.currentThread();
            // 调用getMap(t)方法获取当前线程的ThreadLocalMap对象map
            ThreadLocalMap map = getMap(t);
            if (map != null) {
                // 如果map不为null，则调用map.getEntry(this)方法获取当前ThreadLocal对象在map中对应的Entry对象e
                ThreadLocalMap.Entry e = map.getEntry(this);
                if (e != null) {
                    // 如果e不为null，则将e.value强制转换为T类型，并返回结果result
                    @SuppressWarnings("unchecked")
                    T result = (T)e.value;
                    return result;
                }
            }
            // 如果map为null或者e为null，则调用setInitialValue()方法返回当前ThreadLocal对象的初始值
            // 返回此线程局部变量的当前线程的“初始值”
            return setInitialValue();
        }

        /**
         * Variant of set() to establish initialValue. Used instead
         * of set() in case user has overridden the set() method.
         * @return the initial value
         */
        private T setInitialValue() {
            // setInitialValue()方法首先调用initialValue()方法获取当前ThreadLocal对象的初始值value
            T value = initialValue();
            // 获取当前线程的ThreadLocalMap对象map
            Thread t = Thread.currentThread();
            ThreadLocalMap map = getMap(t);
            if (map != null)
                // 如果map不为null，则调用map.set(this, value)方法将当前ThreadLocal对象和value值存入map中
                map.set(this, value);
            else
                // 如果map为null，则调用createMap(t, value)方法创建一个新的ThreadLocalMap对象，并将其赋值给当前线程的threadLocals属性
                createMap(t, value);
            // 返回value作为当前线程的ThreadLocal对象的值
            return value;
        }
}
```

**`Thread`、`ThreadLocal`和`ThreadLocalMap`三者关系总结**：`ThreadLocalMap`从字面上就可以看出这是一个保存`ThreadLocal`对象的`map`(其实是以`ThreadLocal`为`Key`)，不过是经过了两层包装的`ThreadLocal`对象。`JVM`内部维护了一个线程版的`Map<ThreadLocal,Value>`(通过`ThreadLocal`对象的`set`方法，结果把`ThreadLocal`对象自己当做`key`，放进了`ThreadLoalMap`中)，每个线程要用到这个T的时候，用当前的线程去Map里面获取，通过这样让每个线程都拥有了自己独立的变量，人手一份，竞争条件被彻底消除，在并发模式下是绝对安全的变量

![image-20240207102407532](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142351150.png)



###  9.3  `ThreadLocal`内存泄露问题

##### 9.3.1 内存泄露简介

**内存泄漏**：不再会被使用的对象或者变量占用的内存不能被回收，就是内存泄露。`Java`内存泄漏指的是在`Java`程序中存在一些不再被使用的对象，但由于某些原因仍然被`Java`虚拟机`（JVM）`认为是活动对象，导致这些对象占用的内存无法被回收，从而导致内存的消耗不断增加，最终可能导致程序运行缓慢甚至崩溃。内存泄漏是指程序中的对象持续占用内存，而这些对象实际上已经不再需要，却没有被及时地释放。内存泄漏通常发生在开发人员不正确使用内存管理的情况下，导致虚拟机无法回收这些不再使用的对象，最终导致内存耗尽或性能下降的问题

**`Java`内存泄漏的常见原因**：

- 对象引用未被及时释放：当一个对象不再被使用时，如果其引用没有被置为null，那么这个对象就无法被垃圾回收器回收，从而导致内存泄漏
- 静态引用导致的内存泄漏：如果一个对象被声明为静态，并且在程序运行期间一直存在，那么即使这个对象不再被使用，也无法被垃圾回收器回收，从而导致内存泄漏
- 集合类使用不当：在使用集合类时，如果没有正确地释放其中的对象引用，就会导致内存泄漏。例如，在使用`HashMap`时，如果没有及时调用`remove()`方法删除不再需要的键值对，就会导致内存泄漏
- 线程使用不当：如果线程没有正确地终止，或者线程持有某些对象的引用而没有释放，就会导致内存泄漏
- 未关闭资源：例如打开文件、数据库连接或网络连接后，没有及时关闭这些资源
- 静态集合类持有对象：如果将对象存储在全局的静态集合中，导致这些对象无法被垃圾回收
- 匿名内部类和内部类：当内部类实例持有外部类实例时，外部类无法被回收，导致内存泄漏
- 监听器和回调：注册了事件监听器或回调函数，但忘记取消注册，导致回调对象无法被释放

**如何避免`Java`内存泄漏**：

- 及时释放对象引用：在一个对象不再被使用时，应该将其引用置为`null`，以便垃圾回收器能够回收这个对象所占用的内存

- 使用`try-with-resources`语句：在使用需要手动关闭的资源（如文件、数据库连接等）时，可以使用`try-with-resources`语句，以确保资源在使用完毕后能够被正确关闭，从而避免内存泄漏
- 避免滥用静态变量和集合类：在使用静态变量和集合类时，应该慎重考虑其生命周期和引用关系，避免出现不必要的内存泄漏
- 使用弱引用（`WeakReference`）或软引用（`SoftReference`）：在一些特殊情况下，可以使用弱引用或软引用来引用一些可能会被垃圾回收器回收的对象，从而避免内存泄漏
- 尽量避免使用静态集合存储对象，或者使用弱引用来管理对象
- 避免在匿名内部类中持有外部类的引用，可以使用弱引用解决这个问题
- 注意管理注册的监听器和回调，确保在不需要时取消注册

#####  9.3.2  `ThreadLocal`内存泄露

**`ThreadLocal`实现原理**：

- 每个`Thread`中都存储着一个`ThreadLocalMap`类型的成员变量
- `ThreadLocal`本身不存储数据，`ThreadLoclMap`才是关键的存储结构。`ThreadLocal`像是一个工具类，基于`ThreadLocal`去操作`ThreadLocalMap`
- `ThreadLocalMap`本身就是基于`Entry[]`实现的，因为一个线程可以绑定多个`ThreadLocal`，这样一来，可能需要存储多个数据，所以采用`Entry[]`的形式实现
- 每一个线程都自己独立的`ThreadLocalMap`，再基于`ThreadLocal`对象本身作为`key`，对`value`进行存取
- `ThreadLocalMap`的`key`是一个弱引用。弱引用的特点是，即便有弱引用，在`GC`时，也必须被回收。这里是为了在`ThreadLocal`对象失去引用后，如果`key`的引用是强引用，会导致`ThreadLocal`对象无法被回收，从而导致内存泄露

**`ThreadLocal`内存泄漏问题**：如果`ThreadLocal`引用丢失，因为`key`是弱引用所以会被`GC`回收掉。如果同时线程还没有被回收，就会导致内存泄漏，内存中的`value`无法被回收，同时也无法被获取到。只需要在使用完毕`ThreadLocal`对象之后，及时的调用`remove`方法，移除`Entry`即可防止内存泄漏

![image-20240310213333032](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142352930.png)



**`ThreadLocal`内存泄露**：`ThreadLocal`内存泄露是指在使用`ThreadLocal`时，由于没有正确地清理`ThreadLocal`变量的引用，导致`ThreadLocal`变量无法被垃圾回收，从而造成内存泄露。这是因为`ThreadLocal`在每个线程中维护了一个`ThreadLocalMap`，该`ThreadLocalMap`以`ThreadLocal`对象作为键，以线程局部变量的值作为值。当线程结束后，`ThreadLocalMap`中的`ThreadLocal`对象不会被垃圾回收，因为它们仍然被`Thread`对象引用着

**如何避免`ThreadLocal`内存泄露**：在使用完`ThreadLocal`变量后，调用`remove()`方法清理变量的引用，即`threadLocal.remove()`。这样可以及时清理`ThreadLocalMap`中无效的`ThreadLocal`对象。将`ThreadLocal`声明为`static`类型，避免被线程持有而无法被回收。尽量避免使用`ThreadLocal`变量的默认初始值，以减少`ThreadLocal`对象的创建。正确地使用和清理`ThreadLocal`变量的引用是避免`ThreadLocal`内存泄露的关键

#####  9.3.3   `ThreadLocalMap`的`key`采用弱引用的原因

`ThreadLocalMap`的`key`采用弱引用：`ThreadLocalMap`的`key`是一个弱引用`ThreadLocal`

```java
public class T1 {
    public static void main(String[] args) {
        // 新建了一个ThreadLocal对象，tl是强引用指向这个对象
        ThreadLocal<String> tl = new ThreadLocal<>();    //line1
        // 调用set()方法后新建一个Entry，通过源码可知Entry对象里的k是弱引用指向这个对象
        tl.set("zzyybs@126.com");                        //line2
        tl.get();                                        //line3
    }
}
// line1新建了一个ThreadLocal对象，t1是强引用指向这个对象
// line2调用set()方法后新建一个Entry，通过源码可知Entry对象里的k是弱引用指向这个对象
```

**`ThreadLocalMap`的内部结构分析**：`ThreadLocalMap`从字面上就可以看出是一个保存`ThreadLocal`对象的`map`(以`ThreadLocal`为`Key`)。`ThreadLocalMap`是经过了两层包装的`ThreadLocal`对象：第一层包装是使用`WeakReference<ThreadLocal<?>>`将`ThreadLocal`对象变成一个弱引用的对象。第二层包装是定义了一个专门的类`Entry`来扩展`WeakReference<ThreadLocal<?>>;`



![image-20240207184306129](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142352429.png)



**`ThreadLoclMap`是关键的存储结构**：`ThreadLocal`是一个壳子，真正的存储结构是`ThreadLocal`里有`ThreadLoclMap`这么个内部类，每个`Thread`对象维护着一个`TheadLacalMap`的引用。`ThreadLocalMap`是`ThreadLocal`的内部类，用`Entry`来进行存储。调用`ThreadLocal`的`set()`方法时，实际上就是往`ThreadLocalMap`设置值，`key`是`ThreadLocal`对象，值`Value`是传递进来的对象。调用`ThreadLocal`的`get()`方法时，实际上就是往`ThreadLocalMap`获取值，`key`是`ThreadLocal`对象。`ThreadLocal`本身并不存储值(`ThreadLocal`是一个壳子)，它只是自己作为一个`key`来让线程从`ThreadLocalMap`获取`value`。正因为这个原理，所以`ThreadLocal`能够实现“数据隔离”，获取当前线程的局部变量值，不受其他线程影响



![image-20240207235312094](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142352507.png)



**`ThreadLocalMap`源码**：

```java
public class ThreadLocal<T> {
        /**
         * ThreadLocalMap is a customized hash map suitable only for
         * maintaining thread local values. No operations are exported
         * outside of the ThreadLocal class. The class is package private to
         * allow declaration of fields in class Thread.  To help deal with
         * very large and long-lived usages, the hash table entries use
         * WeakReferences for keys. However, since reference queues are not
         * used, stale entries are guaranteed to be removed only when
         * the table starts running out of space.
         */
        static class ThreadLocalMap {

            /**
             * The entries in this hash map extend WeakReference, using
             * its main ref field as the key (which is always a
             * ThreadLocal object).  Note that null keys (i.e. entry.get()
             * == null) mean that the key is no longer referenced, so the
             * entry can be expunged from table.  Such entries are referred to
             * as "stale entries" in the code that follows.
             */
            static class Entry extends WeakReference<ThreadLocal<?>> {
                /** The value associated with this ThreadLocal. */
                Object value;

                Entry(ThreadLocal<?> k, Object v) {
                    super(k);
                    value = v;
                }
            }
            /**
             * The initial capacity -- MUST be a power of two.
             */
            private static final int INITIAL_CAPACITY = 16;
            /**
             * The table, resized as necessary.
             * table.length MUST always be a power of two.
             */
            private Entry[] table;

            /**
             * The number of entries in the table.
             */
            private int size = 0;
            
            /**
             * Construct a new map initially containing (firstKey, firstValue).
             * ThreadLocalMaps are constructed lazily, so we only create
             * one when we have at least one entry to put in it.
             */
            ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
                table = new Entry[INITIAL_CAPACITY];
                int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
                table[i] = new Entry(firstKey, firstValue);
                size = 1;
                setThreshold(INITIAL_CAPACITY);
            }
        }
}
```

**`ThreadLocaMap`使用`ThreadLocal`的弱引用作为`key`**：`key`引用是弱引用就大概率会减少内存泄漏的问题。当`function1`方法执行完毕后，栈帧销毁强引用`tl`也就没有了。但是此时线程的`ThreadLocalMap`里某个`entry`的`key`引用还指向这个对象。如果这个`key`引用是强引用，就会导致`key`指向的`ThreadLocal`对象和`v`指向的对象不能被`gc`回收，造成内存泄漏。如果这个`key`引用是弱引用就大概率会减少内存泄漏的问题。使用弱引用，就可以使`ThreadLocal`对象在方法执行完毕后顺利被回收，并且`Entry`的`key`引用指向为`null`。此后如果调用`get`，`set`或`remove`方法时，就会尝试删除`key`为`null`的`entry`，可以释放`value`对象所占用的内存

![image-20240208085759154](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142352834.png)

**`ThreadLocaMap`使用`ThreadLocal`的弱引用作为`key`**：`ThreadLocaMap`使用`ThreadLocal`的弱引用作为`key`，如果一个`ThreadLocal`没有外部强引用引用他，那么系统`gc`的时候，这个`ThreadLocal`势必会被回收，`ThreadLocalMap`中就会出现`key`是`null`的`Entry`，就没有办法访问这些`key`为`null`的`Entry`的`value`，如果当前线程再迟迟不结束的话(比如正好用在线程池)，这些`key`为`null`的`Entry`的`value`就会一直存在一条强引用链。虽然弱引用保证了`key`指向的`ThreadLocal`对象能被及时回收，但是`v`指向的`value`对象是需要`ThreadLocalMap`调用`get`、`set`时发现`key`为`null`时才会去回收整个`entry`、`value`，因此弱引用不能100%保证内存不泄露。我们要在不使用某个`ThreadLocal`对象后，手动调用`remove`方法来删除它。尤其是在线程池中，不仅仅是内存泄露的问题，因为线程池中的线程是重复使用的，意味着这个线程的`ThreadLocalMap`对象也是重复使用的，如果不手动调用`remove`方法，那么后面的线程就有可能获取到上个线程遗留下来的`value`值，造成bug

![image-20240208102259310](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142352631.png)

##### 9.3.4 `ThreadLocaMap`中`key`为`null`的坑

**`ThreadLocaMap`中`key`为`null`的坑**：

1.给`threadLocal`变量赋值，实际上就是把当前的`Entry`（`threadLocal`实例为`key`，值为`value`）往这个`threadLocalMap`中存放。`Entry`中的`key`是弱引用，当`threadLocal`外部强引用被置为`null`(`tl=null`,那么系统`GC`的时候，根据可达性分析，这个`threadLocal`实例就没有任何一条链路能够引用到它，这个`ThreadLocal`势必会被回收。这样一来，`ThreadLocaMap`中就会出现`key`为`null`的`Entry`，就没有办法访问这些`key`是`null`的`Entry`的`value`。如果当前线程再迟迟不结束的话（比如使用线程池来进行线程复用时，复用的线程会一直存在），这些`key`为`null`的`Entry`的`value`就会一直存在一条强引用链：`Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value`。`GC`无法回收`value`，造成内存泄漏

2.如果当前`thread`运行结束，`threadLocal`，`threadLocalMap`，`Entry`没有引用链可达，在垃圾回收的时候都会被系统进行回收

3.在实际使用中有时候会用线程池去维护线程，比如在`Executors.newFixvedThreadPoo()`时创建线程的时候，复用的线程是不会结束的，所以使用`threadLocal`时就要小心内存泄漏的发生



**`ThreadLocaMap`中`key`为`null`的坑（`GPT`补充）**：

- 在使用`ThreadLocal`时，如果将`null`作为`key`进行操作，可能会导致以下问题：
  - 数据结构异常：如果将`null`作为`key`，那么调用`ThreadLocal`的`get()`方法时，会返回`null`对应的值。这可能会导致一些逻辑上的错误，因为`null`可能是一个合法的值，而无法区分是因为`key`为`null`还是对应的值为`null`
  - 内存泄漏：将`null`作为`key`时，可能会导致内存泄漏。`ThreadLocal`内部使用一个`ThreadLocalMap`来存储线程本地变量，它是一个以`ThreadLocal`对象为`key`的`Map`。由于`ThreadLocalMap`使用`ThreadLocal`对象的引用作为`key`，如果将`null`作为`key`，那么在`ThreadLocalMap`中就无法找到对应的值，但是`ThreadLocal`对象本身仍然存在于内存中。这样就会导致`ThreadLocal`对象无法被垃圾回收，从而造成内存泄漏
- 在使用`ThreadLocal`时，确保每次都传入一个非空的 `key`，或者使用`ThreadLocal的initialValue()`方法来设置默认值。这样可以避免可能出现的问题，确保`ThreadLocal`的正常使用



**`ThreadLocal`中内部类`ThreadLocalMap`的`set()`、`get()`、`remove()`方法源码**：调用`set()`、`get()`方法时会去检查所有键为`null`的`Entry`对象，将这些键为`null`的`Entry`对象清除。从`set()`、`get()`、`remove()`的实现可以看出，在`ThreadLocal`的生命周期里，针对`ThreadLocal`存在的内存泄漏的问题，都会通过`expungeStaleEntry()`、`cleanSomeSlots()`、`replaceStaleEntry()`这三个方法清理掉`key`为`null`的脏`entry`

```java
public class ThreadLocal<T> {
    static class ThreadLocalMap {

        /**
         * The entries in this hash map extend WeakReference, using
         * its main ref field as the key (which is always a
         * ThreadLocal object).  Note that null keys (i.e. entry.get()
         * == null) mean that the key is no longer referenced, so the
         * entry can be expunged from table.  Such entries are referred to
         * as "stale entries" in the code that follows.
         */
        static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }

        /**
         * The initial capacity -- MUST be a power of two.
         */
        private static final int INITIAL_CAPACITY = 16;

        /**
         * The table, resized as necessary.
         * table.length MUST always be a power of two.
         */
        private Entry[] table;
        /**
         * Construct a new map initially containing (firstKey, firstValue).
         * ThreadLocalMaps are constructed lazily, so we only create
         * one when we have at least one entry to put in it.
         */
        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            table = new Entry[INITIAL_CAPACITY];
            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            table[i] = new Entry(firstKey, firstValue);
            size = 1;
            setThreshold(INITIAL_CAPACITY);
        }

        /**
         * Construct a new map including all Inheritable ThreadLocals
         * from given parent map. Called only by createInheritedMap.
         *
         * @param parentMap the map associated with parent thread.
         */
        private ThreadLocalMap(ThreadLocalMap parentMap) {
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];

            for (int j = 0; j < len; j++) {
                Entry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key, value);
                        int h = key.threadLocalHashCode & (len - 1);
                        while (table[h] != null)
                            h = nextIndex(h, len);
                        table[h] = c;
                        size++;
                    }
                }
            }
        }
        
        /**
         * Version of getEntry method for use when key is not found in
         * its direct hash slot.
         *
         * @param  key the thread local object
         * @param  i the table index for key's hash code
         * @param  e the entry at table[i]
         * @return the entry associated with key, or null if no such
         */
        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = table;
            int len = tab.length;

            while (e != null) {
                ThreadLocal<?> k = e.get();
                if (k == key)
                    return e;
                if (k == null)
                    expungeStaleEntry(i);
                else
                    i = nextIndex(i, len);
                e = tab[i];
            }
            return null;
        }

        /**
         * Set the value associated with key.
         *
         * @param key the thread local object
         * @param value the value to be set
         */
        private void set(ThreadLocal<?> key, Object value) {

            // We don't use a fast path as with get() because it is at
            // least as common to use set() to create new entries as
            // it is to replace existing ones, in which case, a fast
            // path would fail more often than not.

            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);

            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();

                if (k == key) {
                    e.value = value;
                    return;
                }

                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }

            tab[i] = new Entry(key, value);
            int sz = ++size;
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();
        }

        /**
         * Remove the entry for key.
         */
        private void remove(ThreadLocal<?> key) {
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                if (e.get() == key) {
                    e.clear();
                    expungeStaleEntry(i);
                    return;
                }
            }
        }

        /**
         * Replace a stale entry encountered during a set operation
         * with an entry for the specified key.  The value passed in
         * the value parameter is stored in the entry, whether or not
         * an entry already exists for the specified key.
         *
         * As a side effect, this method expunges all stale entries in the
         * "run" containing the stale entry.  (A run is a sequence of entries
         * between two null slots.)
         *
         * @param  key the key
         * @param  value the value to be associated with key
         * @param  staleSlot index of the first stale entry encountered while
         *         searching for key.
         */
        private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                       int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;

            // Back up to check for prior stale entry in current run.
            // We clean out whole runs at a time to avoid continual
            // incremental rehashing due to garbage collector freeing
            // up refs in bunches (i.e., whenever the collector runs).
            int slotToExpunge = staleSlot;
            for (int i = prevIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = prevIndex(i, len))
                if (e.get() == null)
                    slotToExpunge = i;

            // Find either the key or trailing null slot of run, whichever
            // occurs first
            for (int i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();

                // If we find key, then we need to swap it
                // with the stale entry to maintain hash table order.
                // The newly stale slot, or any other stale slot
                // encountered above it, can then be sent to expungeStaleEntry
                // to remove or rehash all of the other entries in run.
                if (k == key) {
                    e.value = value;

                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;

                    // Start expunge at preceding stale entry if it exists
                    if (slotToExpunge == staleSlot)
                        slotToExpunge = i;
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                    return;
                }

                // If we didn't find stale entry on backward scan, the
                // first stale entry seen while scanning for key is the
                // first still present in the run.
                if (k == null && slotToExpunge == staleSlot)
                    slotToExpunge = i;
            }

            // If key not found, put new entry in stale slot
            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key, value);

            // If there are any other stale entries in run, expunge them
            if (slotToExpunge != staleSlot)
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
        }

        /**
         * Expunge a stale entry by rehashing any possibly colliding entries
         * lying between staleSlot and the next null slot.  This also expunges
         * any other stale entries encountered before the trailing null.  See
         * Knuth, Section 6.4
         *
         * @param staleSlot index of slot known to have null key
         * @return the index of the next null slot after staleSlot
         * (all between staleSlot and this slot will have been checked
         * for expunging).
         */
        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;

                        // Unlike Knuth 6.4 Algorithm R, we must scan until
                        // null because multiple entries could have been stale.
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            return i;
        }

        /**
         * Heuristically scan some cells looking for stale entries.
         * This is invoked when either a new element is added, or
         * another stale one has been expunged. It performs a
         * logarithmic number of scans, as a balance between no
         * scanning (fast but retains garbage) and a number of scans
         * proportional to number of elements, that would find all
         * garbage but would cause some insertions to take O(n) time.
         *
         * @param i a position known NOT to hold a stale entry. The
         * scan starts at the element after i.
         *
         * @param n scan control: {@code log2(n)} cells are scanned,
         * unless a stale entry is found, in which case
         * {@code log2(table.length)-1} additional cells are scanned.
         * When called from insertions, this parameter is the number
         * of elements, but when from replaceStaleEntry, it is the
         * table length. (Note: all this could be changed to be either
         * more or less aggressive by weighting n instead of just
         * using straight log n. But this version is simple, fast, and
         * seems to work well.)
         *
         * @return true if any stale entries have been removed.
         */
        private boolean cleanSomeSlots(int i, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do {
                i = nextIndex(i, len);
                Entry e = tab[i];
                if (e != null && e.get() == null) {
                    n = len;
                    removed = true;
                    i = expungeStaleEntry(i);
                }
            } while ( (n >>>= 1) != 0);
            return removed;
        }

        /**
         * Expunge all stale entries in the table.
         */
        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null)
                    expungeStaleEntry(j);
            }
        }
    }
}
```

###  9.4  `ThreadLocal`总结 & 最佳实践

阿里巴巴`java`开发手册：

```
19.【推荐】ThreadLocal对象使用static修饰，ThreadLocal无法解决共享对象的更新问题
说明︰这个变量是针对一个线程内所有操作共享的，所以设置为静态变量，所有此类实例共享此静态变量，也就是说在类第一次被使用时装载，只分配一块存储空间，所有此类的对象(只要是这个线程内定义的)都可以操控这个变量
补充：ThreadLocal能实现了线程的数据隔离，不在于它自己本身，而在于Thread的ThreadLocalMap。所以，ThreadLoca可以只初始化一次，只分配一块存储空间就足以了，没必要作为成员变量多次被初始化
```

阿里巴巴`java`开发手册：

```java
使用ThreadLocal的时候需要遵循下列规范，使用remove方法回收自定义的ThreadLocal变量
6.【强制】必须回收自定义的ThreadLocal变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题,尽量在代理中使用try-finally块进行回收。
正例:
objectThreadLocal.set(userInfo);
    try {
     //...
    } finally {
        objectThreadLocal.remove();
    }
```

**`ThreadLocal`总结**：`ThreadLocal`并不解决线程间共享数据的问题。`ThreadLocal`适用于变量在线程间隔离且在方法间共享的场景。`ThreadLocal`通过隐式的在不同线程内创建独立实例副本避免了实例线程安全的问题。每个线程持有一个只属于自己的专属`Map`并维护了`ThreadLocal`对象与具体实例的映射，该`Map`由于只被持有它的线程访问，故不存在线程安全以及锁的问题。`ThreadLocalMap`的`Entry`对`ThreadLocal`的引用为弱引用，避免了`ThreadLocal`对象无法被回收的问题。`ThreadLocalMap`的`set()`、`get()`、`remove()`方法调用时会通过`expungeStaleEntry`，`cleanSomeSlots`，`replaceStaleEntry`这三个方法回收键为`null` 的`Entry`对象的值（即为具体实例）以及`Entry`对象本身从而防止内存泄漏，属于安全加固的方法

**`ThreadLocal`最佳实践**：

```
1.对ThreadLocal进行初始化，防止空指针异常。ThreadLocal.withInitial(() ->初始化值);
2.建议把ThreadLocal修饰为static
3.强制：用完记得手动remove
```

###  9.5 补充：四大引用

#####  9.5.1 四大引用简介

**`Java`四大引用**：`Java`中的引用是指用于访问对象的变量。`Java`中有四种不同类型的引用，它们分别是强引用、软引用、弱引用和虚引用。这四种引用类型的主要区别在于垃圾回收器对它们的处理方式。四大引用类型在`Java`中提供了不同程度的对象引用保护和垃圾回收控制。开发人员可以根据实际需求选择适当的引用类型来管理内存和对象的生命周期

**`Java`四大引用介绍**： 

- 强引用`（Strong Reference）`：强引用是最常见的引用类型，它是默认的引用类型。当一个对象被强引用引用时，即使系统内存不足，垃圾回收器也不会回收该对象。只有当该对象没有任何强引用时，垃圾回收器才会将其回收
- 软引用`（Soft Reference）`：软引用是一种相对强引用弱化的引用类型。当一个对象只被软引用引用时，垃圾回收器只有在系统内存不足时才会回收该对象。这使得软引用非常适合用于缓存数据，可以根据内存使用情况动态地回收不常用的缓存数据
- 弱引用`（Weak Reference）`：弱引用是一种比软引用更弱化的引用类型。当一个对象只被弱引用引用时，在下一次垃圾回收时，不论系统内存是否充足，垃圾回收器都会回收该对象。弱引用常用实现一些容器类，如`WeakHashMap`
- 虚引用`（Phantom Reference）`：虚引用是最弱化的引用类型。虚引用主要用于跟踪对象被垃圾回收的状态，无法通过虚引用访问对象的任何属性或方法。虚引用必须与引用队列`ReferenceQueue`一起使用，垃圾回收器在回收对象时，会将虚引用放入引用队列，以供程序员进行相应的操作

**引用的整体架构**：

![image-20240207185827098](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142352929.png)



**`finalize()`方法**：`finalize()`是`Object`类的一个方法，用于在对象被垃圾回收之前执行清理操作。当对象不再被引用或者程序显式调用`System.gc()`方法时，垃圾回收器会在对象被回收之前调用`finalize()`方法。`Java`技术允许使用`finalize()`方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。需要注意的是，`Java 9`中已经将`finalize()`方法标记为过时，不推荐使用。取而代之的是使用`try-with-resources`语句块或显式调用`close()`方法来释放资源

#####  9.5.2 强引用

**强引用(默认支持模式)**：当内存不足，`JVM`开始垃圾回收，对于强引用的对象，就算是出现了`OOM`也不会对该对象进行回收。强引用是最常见的普通对象引用，只要还有强引用指向一个对象，就能表明对象还“活着”，垃圾收集器不会碰这种对象。在`Java`中最常见的就是强引用，把一个对象赋给一个引用变量，这个引用变量就是一个强引用。当一个对象被强引用变量引用时，它处于可达状态，它是不可能被垃圾回收机制回收的，即使该对象以后永远都不会被用到，`JVM`也不会回收。因此强引用是造成`Java`内存泄漏的主要原因之一。对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式地将相应（强）引用赋值为`null`,一般认为就是可以被垃圾回收的了(当然具体回收时机还是要看具体的垃圾收集策略)

**强引用演示**：

```java
import java.util.concurrent.TimeUnit;
// 新建一个带finalize()方法的对象MyObject
class MyObject {
    //finalize()方法一般不用复写，此处只是为了教学做案例演示说明
    @Override
    protected void finalize() throws Throwable
    {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-------invoke finalize method~!!!");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
        strongReference();
    }
    
    // 强引用演示
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before: "+myObject);
        myObject = null;
        System.gc();//人工开启GC，一般不用。调用以后由后台线程进行垃圾回收
        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("gc after: "+myObject);
//程序输出：
//         gc before: com.bilibili.juc.tl.MyObject@16f65612
//         -------invoke finalize method~!!!
//         gc after: null
    }
}
```

#####  9.5.3 软引用

**软引用**：软引用是一种相对强引用弱化了一些的引用，需要用`java.lang.ref.SoftReference`类来实现。软引用可以让对象豁免一些垃圾收集。对于只有软引用的对象来说，当系统内存充足时它不会被回收，当系统内存不足时它会被回收。软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收

**软引用演示**：添加`java`虚拟机参数`-Xms10m -Xmx10m` 来限制程序内存

![image-20240207202602722](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142353346.png)

```java
import java.lang.ref.*;
import java.util.concurrent.TimeUnit;
// 新建一个带finalize()方法的对象MyObject
class MyObject {
    //finalize()方法一般不用复写，此处只是为了教学做案例演示说明
    @Override
    protected void finalize() throws Throwable
    {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-------invoke finalize method~!!!");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
        softReference();
    }

    // 软引用演示
    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        //System.out.println("-----softReference:"+softReference.get());
        System.gc();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----gc after内存够用: "+softReference.get());
        try
        {
            byte[] bytes = new byte[20 * 1024 * 1024];//20MB对象
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("-----gc after内存不够: "+softReference.get());
        }
//不限制内存时程序输出：
//                -----gc after内存够用: com.bilibili.juc.tl.MyObject@2f410acf
//                -----gc after内存不够: com.bilibili.juc.tl.MyObject@2f410acf
//限制内存时程序输出：
//                -----gc after内存够用:com.bilibili.juc.tl.Myobject@30f39991
//                Exception in thread "main" java.lang.outofMemoryError Create breakpoint : Java heap space
//                at com.bilibili.juc.tl.ReferenceDemo.main(ReferenceDemo.java:35)
//                ----gc after内存不够: null
//                -------invoke finalize method~! ! !
    }
}
```

#####  9.5.4  弱引用

**弱引用**：弱引用需要用`java.lang.ref.WeakReference`类来实现，它比软引用的生存期更短，对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管`JVM`的内存空间是否足够，都会回收该对象占用的内存

**弱引用演示**：

```java
import java.lang.ref.*;
import java.util.concurrent.TimeUnit;
// 新建一个带finalize()方法的对象MyObject
class MyObject {
    //finalize()方法一般不用复写，此处只是为了教学做案例演示说明
    @Override
    protected void finalize() throws Throwable
    {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-------invoke finalize method~!!!");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
        weakReference();
    }
    // 弱引用演示
    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("-----gc before 内存够用： "+weakReference.get());
        System.gc();
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----gc after 内存够用： "+weakReference.get());
//程序输出：
//                -----gc before 内存够用： com.bilibili.juc.tl.MyObject@16f65612
//                -------invoke finalize method~!!!
//                -----gc after 内存够用： null
    }
}
```

**软引用和弱引用的使用场景**：假如有一个应用需要读取大量的本地图片。如果每次读取图片都从硬盘读取则会严重影响性能。如果一次性全部加载到内存中又可能造成内存溢出。此时使用软引用可以解决这个问题。设计思路是：用一个`HashMap`来保存图片的路径和相应图片对象关联的软引用之间的映射关系，在内存不足时，`JVM`会自动回收这些缓存图片对象所占用的空间，从而有效地避免了`OOM`的问题。`Map<String, SoftReference<Bitmap>>imageCache = new HashMap<String, SoftReference<Bitmap>>();`

#####  9.5.5  虚引用

**虚引用**：虚引用是一种较为特殊的引用类型，通常用于跟踪对象被垃圾回收器回收的活动。虚引用的主要作用是在对象被垃圾回收时收到通知，它并不能通过虚引用访问对象，也无法通过虚引用来操作对象。在`Java`中，可以使用`java.lang.ref.PhantomReference`类来创建虚引用。虚引用需要与引用队列`ReferenceQueue`结合使用，当虚引用所引用的对象被垃圾回收时，虚引用会被放入引用队列中，应用程序可以通过检查引用队列来获取虚引用的通知。这使得虚引用可以用于执行一些清理工作或者进行资源释放的操作

**虚引用特点**：

- **虚引用必须和引用队列`ReferenceQueue`联合使用**。虚引用需要`java.lang.ref.PhantomReference`类来实现，虚引用顾名思义就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列`ReferenceQueue`联合使用
- **`PhantomReference`的get方法总是返回null**。虚引用的主要作用是跟踪对象被垃圾回收的状态。仅仅是提供了一种确保对象被`finalize`以后，做某些事情的通知机制。`PhantomReference`的`get`方法总是返回`null`，因此无法访问对应的引用对象
- **处理监控通知使用**。设置虚引用关联对象的唯一目的，就是在这个对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理，用来实现比`finalize`机制更灵活的回收操作

**虚引用演示**：

```java
import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
// 新建一个带finalize()方法的对象MyObject
class MyObject {
    //finalize()方法一般不用复写，此处只是为了教学做案例演示说明
    @Override
    protected void finalize() throws Throwable
    {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-------invoke finalize method~!!!");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
        phantomReference();
    }
    // 虚引用演示
    private static void phantomReference(){
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
        // 虚引用的get方法总是返回null
        System.out.println(phantomReference.get());//null
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            // 不停地添加字节数组,导致OOM,接着会进行垃圾回收
            while (true){
                list.add(new byte[10 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(phantomReference.get()+"\t"+"list add ok");
            }
        },"t1").start();

        new Thread(() -> {
            while (true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference != null){
                    System.out.println("-----有虚对象回收加入了队列");
                    break;
                }
            }
        },"t2").start();
//程序输出：
//        null
//        null  list add ok
//        null  list add ok
//        null  list add ok
//                -------invoke finalize method~!!!
//        null  list add ok
//        null  list add ok
//                -----有虚对象回收加入了队列
//        null  list add ok
//        null  list add ok
    }
}
```

#####  9.5.6  `GC Roots`  | 四大引用垃圾回收总结

**`GC Roots`**：`GC Roots`是指一组对象的引用集合，这些引用的对象被认为是活跃的，因此不会被垃圾回收器回收。`GC Roots`对象是垃圾回收算法的起点，通过从`GC Roots`对象开始遍历，垃圾回收器可以确定哪些对象是可达的，哪些对象是不可达的。不可达的对象将被标记为垃圾，最终被回收。`GC Roots`的存在是为了防止垃圾回收器将还在被引用的对象错误地回收。通过标记`GC Roots`对象，垃圾回收器可以确保只回收不可达的对象，从而有效地释放内存空间。需要注意的是，`GC Roots`对象本身并不一定要被引用，它们只是作为垃圾回收的起始点。垃圾回收器会根据`GC Roots`对象的引用关系，逐步遍历并标记可达的对象，最终确定哪些对象是垃圾

**`JVM`中哪些对象可以作为`GC Roots`**：

- 虚拟机栈中的引用的对象——即被栈中的局部变量、方法参数等引用的对象
- 方法区中的类静态属性引用的对象——即被类的静态变量引用的对象
- 方法区中的常量引用的对象——即被常量引用的对象
- 本地方法栈中`JNI（Java Native Interface）`引用的对象——即被本地方法引用的对象
- 被同步锁`synchronized`持有的对象

**`JVM`中判断对象是否存活的两种算法**：

- 引用计数法∶给对象添加一个引用计数器，每当有一个地方引用它，计数器就加1。当引用失效，计数器就减1，计数器为0的对象就是没有被引用的对象。这个方法实现简单，效率高，但是现在主流的虚拟机中并没有使用这个算法来管理内存，最主要的原因是它很难解决对象之间循环引用的问题
- 可达性分析算法。这个算法的基本思路就是通过一系列的成为`GC Roots`的对象作为起点，从这个节点开始向下搜索，节点所走过的路径称之为引用链，当一个对象到`GC Roots`没有任何引用链相连时，则证明此对象是不可用的。但是一个对象到`GC Roots`没有任何引用链相连，并不代表就一定会被回收。对象是否被回收，需要进行2次标记。1.如果对象在进行可达性分析后发现没有与`GC Roots`相连接的引用链，则将会被第一次标记  2.若这个对象被判定为没有必要执行`finalize()`方法，或者是有必要执行，但是该对象没有完成自救，都会进行第2次标记。有过2次标记的对象，都即将会被回收

**四大引用垃圾回收示意图**：

![image-20240207233252000](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142353999.png)

**四大引用总结**：

- 强引用：通过关键字`new`创建的对象引用即为强引用，垃圾回收器不会回收被强引用的对象
- 软引用：使用`java.lang.ref.SoftReference`类实现，当内存不足时才会回收软引用对象，通常用于实现缓存
- 弱引用：使用`java.lang.ref.WeakReference`类实现，垃圾回收器遇到弱引用对象会立即对其进行回收
- 虚引用：使用`java.lang.ref.PhantomReference`类实现，无法通过虚引用获取到对象，用于在对象被回收时收到通知

## 10. `Synchronized` 与 锁升级

###  10.1 `Java`对象内存布局

#####  10.1.1 对象内存布局 | 对象标记 `Mark Word` | 类元信息 | 实例数据 | 对齐填充

**`Java`对象的存放位置**：`Java`中的基本类型数据（如`int`、`double`、`long`等）直接存储在栈内存中，而对象的引用和实际数据则分别存储在栈内存和堆内存中。**堆内存**：对于大多数`Java`对象来说，它们都存在于堆内存中。堆内存是`Java`虚拟机内存的一部分，用于存放创建的对象实例。当使用关键字 `new` 创建对象时，该对象会存储在堆内存中，并由垃圾回收器负责管理。**栈内存**：`Java`中的基本数据类型和对象的引用通常存储在栈内存中。这些引用变量存储在栈内存中，而实际的对象实例则存储在堆内存中。因此，栈内存中的引用指向了堆内存中的对象

**对象内存布局简介**：在`HotSpot`虚拟机里，对象在堆内存中的存储布局可以划分为对象头（`Header`)、实例数据（`Instance Data`）和对齐填充（`Padding`)三个部分（保证8个字节的倍数）。对象头由对象标记`Mark Word`和类元信息(又叫类型指针)两部分构成。对象头分为对象标记(`markOop`)和类元信息(`klassOop`)，类元信息存储的是指向该对象类元数据（`klass `）的首地址

![image-20240312215121943](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142354137.png)





**对象头构成**：对象头由对象标记`Mark Word`和类元信息(又叫类型指针)两部分构成

>官网文档地址：`https://openjdk.org/groups/hotspot/docs/HotSpotGlossary.html`
>
>**mark word**
>
>The first word of every object header. Usually a set of bit fields including synchronization state and identity hash code. May also be a pointer (with characteristic low bit encoding) to synchronization related information. During GC, may contain GC state bits.
>
>**klass pointer**
>
>The second word of every object header. Points to another object (a meta object) which describes the layout and behavior of the original object. For Java objects, the "klass" contains a C++ style "vtable".



**底层源码理论证明**：`https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/89fb452b3688/src/share/vm/oops/oop.hpp`

```c++
// _mark字段是mark word，_metadata是类指针klass pointer
// 对象头（object header)即是由这两个字段组成，这些术语可以参考Hotspot术语表
class PSPromotionManager;
class ParCompactionManager;

class oopDesc {
  friend class VMStructs;
 private:
  volatile markOop  _mark;
  union _metadata {
    Klass*      _klass;
    narrowKlass _compressed_klass;
  } _metadata;

  // Fast access to barrier set.  Must be initialized.
  static BarrierSet* _bs;

 public:
  markOop  mark() const         { return _mark; }
  markOop* mark_addr() const    { return (markOop*) &_mark; }

  void set_mark(volatile markOop m)      { _mark = m;   }

  void    release_set_mark(markOop m);
  markOop cas_set_mark(markOop new_mark, markOop old_mark);
}
```



**对象标记`Mark Word`**：`Mark Word`默认存储对象的`HashCode`、分代年龄和锁标志位等信息。这些信息都是与对象自身定义无关的数据，所以`MarkWord`被设计成一个非固定的数据结构以便在极小的空间内存储尽量多的数据。它会根据对象的状态复用自己的存储空间，也就是说在运行期间`MarkWord`里存储的数据会随着锁标志位的变化而变化。对象标记的内容在不同的 `JVM` 实现中可能略有差异，但都旨在为 `JVM` 提供对象的重要元数据信息以及用于线程同步和垃圾回收的状态标记。在 `HotSpot` 虚拟机中，对象标记通常包含以下内容：

1. 锁状态标志位：用于表示对象的锁状态，包括无锁状态、偏向锁、轻量级锁或重量级锁
2. `GC` 状态标志位：用于表示对象的垃圾回收状态，包括分代年龄等信息
3. 哈希码：用于快速计算对象的哈希码，以支持各种哈希算法
4. 分代年龄：用于支持分代垃圾回收算法，跟踪对象的年龄信息

![image-20240216001018487](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355842.png)

**32位虚拟机对象标记`Mark Word`的存储结构**：



![image-20240216014632870](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355972.png)



**64位虚拟机对象标记`Mark Word`的存储结构**：对象布局、`GC`回收和锁升级就是对象标记`MarkWord`里面标志位的变化

![image-20240216014915150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355426.png)

**对象标记`Mark Word`源码说明`markOop.hpp`**：

```
hash:保存对象的哈希码
age:保存对象的分代年龄
biased_lock:偏向锁标识位
lock:锁状态标识位
JavaThread*:保存持有偏向锁的线程ID
epoch:保存偏向时间戳
```

```c++
// 源码位置：https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/89fb452b3688/src/share/vm/oops/markOop.hpp
//  64 bits:
//  --------
//  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
//  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
//  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
//  size:64 ----------------------------------------------------->| (CMS free block)
//
//  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
//  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
//  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
//  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
//
//  - hash contains the identity hash value: largest value is
//    31 bits, see os::random().  Also, 64-bit vm's require
//    a hash value no bigger than 32 bits because they will not
//    properly generate a mask larger than that: see library_call.cpp
//    and c1_CodePatterns_sparc.cpp.
//
//  - the biased lock pattern is used to bias a lock toward a given
//    thread. When this pattern is set in the low three bits, the lock
//    is either biased toward a given thread or "anonymously" biased,
//    indicating that it is possible for it to be biased. When the
//    lock is biased toward a given thread, locking and unlocking can
//    be performed by that thread without using atomic operations.
//    When a lock's bias is revoked, it reverts back to the normal
//    locking scheme described below.
//
//    Note that we are overloading the meaning of the "unlocked" state
//    of the header. Because we steal a bit from the age we can
//    guarantee that the bias pattern will never be seen for a truly
//    unlocked object.
//
//    Note also that the biased state contains the age bits normally
//    contained in the object header. Large increases in scavenge
//    times were seen when these bits were absent and an arbitrary age
//    assigned to all biased objects, because they tended to consume a
//    significant fraction of the eden semispaces and were not
//    promoted promptly, causing an increase in the amount of copying
//    performed. The runtime system aligns all JavaThread* pointers to
//    a very large value (currently 128 bytes (32bVM) or 256 bytes (64bVM))
//    to make room for the age bits & the epoch bits (used in support of
//    biased locking), and for the CMS "freeness" bit in the 64bVM (+COOPs).
//
//    [JavaThread* | epoch | age | 1 | 01]       lock is biased toward given thread
//    [0           | epoch | age | 1 | 01]       lock is anonymously biased
//
//  - the two lock bits are used to describe three states: locked/unlocked and monitor.
//
//    [ptr             | 00]  locked             ptr points to real header on stack
//    [header      | 0 | 01]  unlocked           regular object header
//    [ptr             | 10]  monitor            inflated lock (header is wapped out)
//    [ptr             | 11]  marked             used by markSweep to mark an object
//                                               not valid at any other time
//
//    We assume that stack/thread pointers have the lowest two bits cleared.
```

**类元信息(又叫类型指针)**：对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。对象头多大？在64位系统中，`Mark Word`占了8个字节，类型指针占了8个字节，一共是16个字节

![image-20240216004302686](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355028.png)

**实例数据**：存放类的属性(Field)数据信息，包括父类的属性信息

**对齐填充**：虚拟机要求对象起始地址必须是8字节的整数倍。填充数据不是必须存在的，仅仅是为了字节对齐这部分内存按8字节补充对齐

```java
class Customer//只有一个对象头的实例对象，16字节（忽略压缩指针的影响）
{
    //1 第一种情况，只有对象头，没有其它任何实例数据

    // 2 第二种情况，int + boolean，默认满足对其填充，24 bytes。16字节（忽略压缩指针的影响）+4字节+1字节=21字节----》对齐填充，24字节
    /*int id;
    boolean flag = false;
    boolean flag2 = false;*/
}
```

#####  10.1.2 使用`JOL`分析对象内存布局

**`JOL`官网**：`https://openjdk.org/projects/code-tools/jol/`

>官网介绍：JOL (Java Object Layout) is the tiny toolbox to analyze object layout schemes in JVMs. These tools are using Unsafe, JVMTI, and Serviceability Agent (SA) heavily to decoder the *actual* object layout, footprint, and references. This makes JOL much more accurate than other tools relying on heap dumps, specification assumptions, etc.

**`JOL`依赖**：

```xml
<!-- 官网: https://openjdk.org/projects/code-tools/jol/
作用:分析对象在JVM的大小和分布-->
<dependency>
    <groupId>org.openjdk.jol</groupId>
    <artifactId>jol-core</artifactId>
    <version>0.9</version>
</dependency>
```

**程序演示一**：使用`VM.current().details()`查看当前系统`java`虚拟机的信息

```java
public class JOLDemo {

    public static void main(String[] args) {
        test1();
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
}
```

**程序演示二**：使用`ClassLayout.parseInstance(o).toPrintable()`查看对象内存布局

```
ClassLayout.parseInstance(o).toPrintable()参数说明：
        OFFSET:         偏移量，也就是到这个字段位置所占用的byte数
        SIZE:           后面类型的字节大小
        TYPE:           是Class中定义的类型
        DESCRIPTION:    DESCRIPTION是类型的描述
        VALUE:          VALUE是TYPE在内存中的值
```

```java
public class JOLDemo {

    public static void main(String[] args) {
        test2();
    }
    
    private static void test2() {
        System.out.println("###########################");
        Object o = new Object(); // Object占16 bytes
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
}
```



![image-20240216032655019](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355348.png)



**程序演示三**：使用`ClassLayout.parseInstance(o).toPrintable()`查看对象内存布局

```java
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
public class JOLDemo {
    public static void main(String[] args) {
        test3();
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
}

class Customer//只有一个对象头的实例对象，16字节（忽略压缩指针的影响）
{
    // 1 第一种情况，只有对象头，没有其它任何实例数据

    // 2 第二种情况，int + boolean，默认满足对其填充，24 bytes。16字节（忽略压缩指针的影响）+4字节+1字节=21字节----》对其填充，24字节
    /*int id;
    boolean flag = false;
    boolean flag2 = false;*/
}
```

**程序演示四**：使用`ClassLayout.parseInstance(o).toPrintable()`查看对象内存布局

```java
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
public class JOLDemo {

    public static void main(String[] args) {
        test4();
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
}

class Customer
{
    // 2 第二种情况，int + boolean，默认满足对其填充，24 bytes。16字节（忽略压缩指针的影响）+4字节+1字节=21字节----》对其填充，24字节
    int id;
    boolean flag = false;
}
```

#####  10.1.3 `GC`分代年龄 | 压缩指针

对象分代年龄：`GC`年龄采用4位bit存储，最大为15，例如`MaxTenuringThreshold`参数默认值就是15

![image-20240217105425804](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355755.png)

验证：配置`JVM`参数`--xx:MaxTenuringThreshold=16`，运行报错

![image-20240217105310243](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142355624.png)

**压缩指针**：压缩指针是默认开启的：运行命令`java -XX:+PrintCommandLineFlags -version`会显示 Java 虚拟机的启动参数和版本信息。参数`-XX:+PrintCommandLineFlags`表示打印出在启动 Java 虚拟机时设置的标记。参数`-version`表示显示 Java 运行时的版本信息。运行命令可以发现默认开启了压缩指针`XX:+UseCompressedClassPointers`

```shell
PS D:\SGG-JUC\juc_sgg\src\main\java\com\sgg\juc\objecthead> java -XX:+PrintCommandLineFlags -version
-XX:G1ConcRefinementThreads=13 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=532634368 -XX:MaxHeapSize=8522149888 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation 
openjdk version "11.0.16.1" 2022-08-12 LTS
OpenJDK Runtime Environment Microsoft-40648 (build 11.0.16.1+1-LTS)
OpenJDK 64-Bit Server VM Microsoft-40648 (build 11.0.16.1+1-LTS, mixed mode)
```

测试：

```java
Object o = new Object(); //16 bytes
System.out.println(ClassLayout.parseInstance(o).toPrintable());
```

上述表示开启了类型指针的压缩，以节约空间。启动压缩指针`-XX:+UseCompressedClassPointers`结果：

![image-20240217112931683](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356086.png)

手动关闭压缩再看看，关闭压缩指针`-XX:-UseCompressedClassPointers`，结果：

![image-20240217114339352](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356344.png)

压缩指针结论：

```
/**
 * 1 默认配置，启动压缩指针，-XX:+UseCompressedClassPointers，
 * 12 + 4(对齐填充) = 一个对象16字节
 * <p>
 * 2 手动配置，关闭压缩指针-XX:-UseCompressedClassPointers，针，
 * 8 + 8 = 一个对象16字节
 */
```

开启压缩指针（`-XX:+UseCompressedClassPointers` ）可以减小内存占用，尤其是在64位 `JVM` 中，因为它可以缩小对象引用的大小。在启用这个选项后，对象引用的大小会从8字节缩小为4字节，这可以显著减少对象引用所占的内存空间。关闭压缩指针（ `-XX:-UseCompressedClassPointers` 选项）将导致对象引用的大小保持为8字节，这将增加内存占用。开启和关闭压缩指针的区别在于内存占用。开启压缩指针可以减小对象引用的大小，从而降低内存占用；而关闭压缩指针会导致对象引用的大小增加，从而增加内存占用

#####  10.1.4 任意对象都可以充当锁

**任意对象都可以充当锁**：`Monitor`可以理解为一种同步工具，也可理解为一种同步机制，常常被描述为一个`Java`对象。`Java`对象是天生的`Monior`，每一个`Java`对象都有成为`Monitor`的潜质，因为在`Java`的设计中，每一个`Java`对象自打娘胎里出来就带了一把看不见的锁，它叫做内部锁或者`Monitor`锁。`Monitor`的本质是依赖于底层操作系统的`MutexLock`实现，操作系统实现线程之间的切换需要从用户态到内核态的转换，成本非常高

![image-20240312232504490](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356945.png)

**任意对象都可以充当锁**：`Java`中的每个对象都派生自`Object`类，而每个`Java Object`在`JVM`内部都有一个`native`的`C++`对象 `oop/oopDesc`进行对应。线程在获取锁的时候，实际上就是获得一个监视器对象(`monitor`) ，`monitor`可以认为是一个同步对象，所有的`Java`对象是天生携带`monitor`。在`hotspot`源码的 `markOop.hpp`文件中，可以看到下面这段代码。 多个线程访问同步代码块时，相当于去争抢对象监视器修改对象中的锁标识，下面的代码中`ObjectMonitor`这个对象和线程争抢锁的逻辑有密切的关系

```c++
// markOop.hpp源码：
// 源码位置：https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/89fb452b3688/src/share/vm/oops/markOop.hpp
  ObjectMonitor* monitor() const {
    assert(has_monitor(), "check");
    // Use xor instead of &~ to provide one extra tag-bit check.
    return (ObjectMonitor*) (value() ^ monitor_value);
  }
```

**`Monitor`(监视器锁)**：`JVM`中的同步就是基于进入和退出管程（`Monitor`）对象实现的。每个对象实例都会有一个`Monitor`，`Monitor`可以和对象一起创建、销毁。`Monitor`是由`ObjectMonitor`实现，而`ObjectMonitor`是由`C++`的`ObjectMonitor.hpp`文件实现，如下所示：

![image-20240217131239348](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356941.png)



**`Mutex Lock`**：`Monitor`是在`jvm`底层实现的，底层代码是`c++`。本质是依赖于底层操作系统的`MutexLock`实现，操作系统实现线程之间的切换需要从用户态到内核态的转换，状态转换需要耗费很多的处理器时间，成本非常高。所以`synchronized`是Java语言中的一个重量级操作。`Mutex Lock`的切换需要从用户态转换到核心态中，因此状态转换需要耗费很多的处理器时间

**`Monitor`与`java`对象以及线程是如何关联**？如果一个`java`对象被某个线程锁住，则该`java`对象的`Mark Word`字段中`LockWord`指向`monitor`的起始地址。`Monitor`的`Owne`r字段会存放拥有相关联对象锁的线程id

**对象头 与 `synchronized`锁的原理示意图**：

![image-20240217132753173](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356053.png)



###  10.2 `synchronized`锁升级

#####  10.2.1  `synchronized`锁升级简介

**阿里`java`开发手册**：锁会带来性能损耗

```
7.【强制】高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁;
能锁区块，就不要锁整个方法体;能用对象锁，就不要用类锁。
说明:尽可能使加锁的代码块工作量尽可能的小，避免在锁代码块中调用RPC方法
```

 **`synchronized`锁升级**：`Java 6`为了减少获得锁和释放锁带来的性能消耗，引入了偏向锁和轻量级锁。在`Java 6`中，锁一共有4种状态，级别从低到高依次是：**无锁状态、偏向锁状态、轻量级锁状态和重量级锁状态**。这几个状态会随着竞争情况逐渐升级，锁可以升级但不能降级，意味着偏向锁升级成轻量级锁后不能降级成偏向锁。这种锁升级却不能降级的策略，目的是为了提高获得锁和释放锁的效率。`java`的线程是映射到操作系统原生线程之上的，如果要阻塞或唤醒一个线程就需要操作系统介入，需要在用户态与核心态之间切换，这种切换会消耗大量的系统资源，因为用户态与内核态都有各自专用的内存空间，专用的寄存器等，用户态切换至内核态需要传递给许多变量、参数给内核，内核也需要保护好用户态在切换时的一些寄存器值、变量等，以便内核态调用结束后切换回用户态继续工作。在`Java`早期版本中，`synchronized`属于重量级锁，效率低下，因为监视器锁(`monitor`)是依赖于底层的操作系统的`Mutex Lock`(系统互斥量)来实现的，挂起线程和恢复线程都需要转入内核态去完成，阻塞或唤醒一个`Java`线程需要操作系统切换`CPU`状态来完成，这种状态切换需要耗费处理器时间，如果同步代码块中内容过于简单，这种切换的时间可能比用户代码执行的时间还长，时间成本相对较高，这也是为什么早期的`synchronized`效率低的原因。`Java 6`之后，为了减少获得锁和释放锁所带来的性能消耗，引入了轻量级锁和偏向锁。`Java5`之前，锁会导致用户态和内核态之间的频繁切换：

![image-20240217125816275](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356291.png)



**`synchronized`锁升级的实现**：锁升级的实现主要依赖`MarkWord`中锁标志位和释放偏向锁标志位。`synchronized`用的锁存在于`Java`对象头里的`Mark Word`中，`synchronized`锁会根据对象头中`Mark Word`的锁标志位的不同而被复用或锁升级。64位虚拟机`Mark Word`的存储结构：

![image-20240216014915150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356519.png)

**三种情况下`MarkWord`的存储内容**：

```
偏向锁: MarkWord存储的是偏向的线程ID
轻量锁: MarkWord存储的是指向线程栈中Lock Record的指针
重量锁: MarkWord存储的是指向堆中的monitor对象的指针
```

**C源码的`MarkWord`标记**：

```
hash:           保存对象的哈希码
age:            保存对象的分代年龄
biased_lock:    偏向锁标识位
lock:           锁状态标识位
JavaThread*:    保存持有偏向锁的线程ID
epoch:          保存偏向时间戳
```

![image-20240217141410071](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356440.png)

#####  10.2.2 无锁

**无锁状态**：一个对象被实例化后如果还没有被任何线程竞争锁，那么它就为无锁状态(001)。无锁状态下程序不会有锁的竞争

**无锁状态`Code`演示**：使用`ClassLayout.parseInstance(o).toPrintable()`查看无锁状态下对象的内存布局，可以发现无锁状态下对象的锁标志位是001。补充：对象的`hashCode`只有在调用`hashCode`方法时才会生成

```java
    private static void noLock01() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           01 00 00 00 (00000001(无锁) 00000000 00000000 00000000) (1)
//    4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }

    // hashCode测试：对象的hashCode只有在调用hashCode方法时才会生成
    private static void noLock02() {
        Object o = new Object();
        System.out.println("10进制："+o.hashCode());
        System.out.println("16进制："+Integer.toHexString(o.hashCode()));
        System.out.println("2进制："+Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//程序输出：
//    10进制：991505714
//    16进制：3b192d32
//    2进制：111011000110010010110100110010
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           01 32 2d 19 (00000001 00110010 00101101 00011001) (422392321)
//    4     4        (object header)                           3b 00 00 00 (00111011 00000000 00000000 00000000) (59)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
```

#####  10.2.3 偏向锁

**偏向锁**：`Hotspot`的作者经过研究发现，大多数情况下，锁不仅不存在多线程竞争，而且锁总是由同一线程多次获得，为了让线程获得锁的代价更低而引入了偏向锁。**偏向锁的出现是为了解决只有在一个线程执行同步时提高性能**。偏向锁会偏向于第一个访问锁的线程，如果在接下来的运行过程中，该锁没有被其他的线程访问，则持有偏向锁的线程将永远不需要触发同步。也即偏向锁在资源没有竞争情况下消除了同步语句，连`CAS`操作都不做，直接提高了程序性能

**偏向锁的持有**：当一个线程访问同步块并获取锁时，会在对象头和栈帧中的锁记录里存储锁偏向的线程`ID`，以后该线程在进入和退出同步块时不需要进行`CAS`操作来加锁和解锁，只需简单地测试一下对象头的`Mark Word`里是否存储着指向当前线程的偏向锁。如果测试成功，表示线程已经获得了锁。如果测试失败，则需要再测试一下`Mark Word`中偏向锁的标识是否设置成1（表示当前是偏向锁）：如果没有设置，则使用`CAS`竞争锁；如果设置了，则尝试使用`CAS`将对象头的偏向锁指向当前线程。在实际应用运行过程中发现"锁总是同一个线程持有，很少发生竞争"，也就是说锁总是被第一个占用他的线程拥有，这个线程就是锁的偏向线程。这种情况只需要在锁第一次被拥有的时候，记录下偏向线程ID，这样偏向线程就一直持有着锁。后续这个线程进入和退出这段加了同步锁的代码块时，不需要再次加锁和释放锁，而是直接会去检查锁的`MarkWord`里面是不是放的自己的线程ID。**如果相等**，表示偏向锁是偏向于当前线程的，就不需要再尝试获得锁了，直到竞争发生才释放锁。以后每次同步，检查锁的偏向线程ID与当前线程ID是否一致，如果一致直接进入同步。无需每次加锁解锁都去`CAS`更新对象头。如果自始至终使用锁的线程只有一个，很明显偏向锁几乎没有额外开销，性能极高。**如果不等**，表示发生了竞争，锁已经不是总是偏向于同一个线程了，这个时候会尝试使用`CAS`来替换`MarkWord`里面的线程ID为新线程的ID，竞争成功，表示之前的线程不存在了，`MarkWord`里面的线程ID为新线程的ID，锁不会升级，仍然为偏向锁。竞争失败，这时候可能需要升级变为轻量级锁，才能保证线程间公平竞争锁。注意，偏向锁只有遇到其他线程尝试竞争偏向锁时，持有偏向锁的线程才会释放锁，线程是不会主动释放偏向锁的。技术实现：一个`synchronized`方法被一个线程抢到了锁时，那这个方法所在的对象就会在其所在的`Mark Word`中将偏向锁修改状态位，同时还会占用前54位来存储线程指针作为标识。若该线程再次访问同一个`synchronized`方法时，该线程只需去对象头的`Mark Word`中去判断一下是否有偏向锁指向本身的ID，无需再进入 `Monitor`去竞争对象了

![image-20240216014915150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356466.png)



**举例说明偏向锁的持有**：偏向锁的操作不用操作系统参与，不涉及用户到内核转换，不必要直接升级为重量级锁。以一个`account`对象的“对象头”为例

![image-20240217174752584](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356575.png)

假如有一个线程执行到`synchronized`代码块的时候，`JVM`使用`CAS`操作把线程指针ID记录到`Mark Word`当中，并修改偏向标识，标识当前线程就获得该锁。锁对象变成偏向锁（通过`CAS`修改对象头里的锁标志位)，字面意思是"偏向于第一个获得它的线程"的锁。执行完同步代码块后，线程并不会主动释放偏向锁

![image-20240217175028374](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356927.png)

这时线程获得了锁，可以执行同步代码块。当该线程第二次到达同步代码块时会判断此时持有锁的线程是否还是自己（持有锁的线程ID也在对象头里)，`JVM`通过`account`对象的`Mark Word`判断：当前线程ID还在，说明还持有着这个对象的锁，就可以继续进入临界区工作。由于之前没有释放锁，这里也就不需要重新加锁。如果自始至终使用锁的线程只有一个，很明显偏向锁几乎没有额外开销，性能极高。结论：`JVM`不用和操作系统协商设置`Mutex`(争取内核)，它只需要记录下线程ID就标示自己获得了当前锁，不用操作系统介入。上述就是偏向锁：在没有其他线程竞争的时候，一直偏向当前线程，当前线程可以一直执行

**使用`JVM`命令查询偏向锁相关参数信息**：

```shell
java -XX:+PrintFlagsInitial | grep BiasedLock*
# 打印出Java虚拟机的初始标志，并使用 grep 命令来过滤出包含 "BiasedLock" 的相关信息
# -XX:+UseBiasedLocking    开启偏向锁(默认)
# -XX:-UseBiasedLocking    关闭偏向锁(关闭后会跳级进入轻量锁)
# -XX:BiasedLockingStartupDeLay=0   关闭延迟(演示偏向锁时需要开启)
```

参数说明：偏向锁在`JDK1.6`以后默认开启，开启后程序启动几秒后才会被激活，可以使用`JVM`参数`-XX:BiasedLockingStartupDelay=0`来关闭延迟。如果确定锁通常处于竞争状态则可通过`JVM`参数`-xx:-UseBiasedLocking`关闭偏向锁，那么默认会进入轻量级锁

![image-20240217190243161](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356862.png)

`code`演示：由于默认情况下偏向锁的开启有4秒延迟，所以会发现锁的标志位显示的是轻量级锁

```java
    // 由于默认情况下偏向锁的开启有4秒延迟，所以会发现锁的标志位显示的是轻量级锁
    private static void thinLock1() {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
//程序输出（一切默认）：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           70 ed 5f a2 (01110000(轻量级锁) 11101101 01011111 10100010) (-1570771600)
//    4     4        (object header)                           c7 00 00 00 (11000111 00000000 00000000 00000000) (199)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
```

演示偏向锁：关闭延迟(演示偏向锁时需要设置`-XX:BiasedLockingStartupDelay=0`)。使用`ClassLayout.parseInstance(o).toPrintable()`查看偏向锁状态下对象的内存布局，可以发现偏向锁状态下对象的锁标志位是101

![image-20240217191105355](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142356092.png)

```java
    // 演示偏向锁：关闭延迟(演示偏向锁时需要设置-XX:BiasedLockingStartupDelay=0)
    private static void thinLock2() {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 b0 4d 03 (00000101(偏向锁) 10110000 01001101 00000011) (55422981)
//    4     4        (object header)                           64 02 00 00 (01100100 00000010 00000000 00000000) (612)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
```

演示偏向锁：不用关闭延时，让程序运行5秒，超过延时时间以后再去检验偏向锁。使用`ClassLayout.parseInstance(o).toPrintable()`查看偏向锁状态下对象的内存布局，可以发现无锁状态下对象的锁标志位是101

```java
    // 演示偏向锁：不用关闭延时，让程序运行5秒，超过延时时间以后再去检验偏向锁
    private static void biasedLock3() {
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 b0 4d 03 (00000101(偏向锁) 10110000 01001101 00000011) (55422981)
//    4     4        (object header)                           64 02 00 00 (01100100 00000010 00000000 00000000) (612)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
```

偏向锁的撤销：当有另外的线程逐步来竞争锁的时候，就不能再使用偏向锁了，要升级为轻量级锁。如果竞争线程尝试`CAS`更新对象头失败，就会等到全局安全点(此时不会执行任何代码）时撤销偏向锁。偏向锁使用一种等到竞争出现才释放锁的机制，只有当其他线程竞争锁时，持有偏向锁的原来线程才会被撤销。**撤销需要等待全局安全点(该时间点上没有字节码正在执行)，同时检查持有偏向锁的线程是否还在执行**。**如果**第一个线程正在执行`synchronized`方法(**处于同步块**)，它还没有执行完，其它线程来抢夺，该偏向锁会被取消掉并出现锁升级。此时轻量级锁由原持有偏向锁的线程持有，继续执行其同步代码，而正在竞争的线程会进入自旋等待获得该轻量级锁。**如果**第一个线程执行完成`synchronized`方法(**退出同步块**)，则将对象头设置成无锁状态并撤销偏向锁，重新偏向

> 偏向锁使用了一种等到竞争出现才释放锁的机制，所以当其他线程尝试竞争偏向锁时，持有偏向锁的线程才会释放锁。偏向锁的撤销，需要等待全局安全点（在这个时间点上没有正在执行的字节码）。它会首先暂停拥有偏向锁的线程，然后检查持有偏向锁的线程是否活着，如果线程不处于活动状态，则将对象头设置成无锁状态；如果线程仍然活着，拥有偏向锁的栈会被执行，遍历偏向对象的锁记录，栈中的锁记录和对象头的Mark Word要么重新偏向于其他线程，要么恢复到无锁或者标记对象不适合作为偏向锁，最后唤醒暂停的线程



![image-20240217202508795](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357075.png)

**整体步骤流程图示**：

![20200602120540100](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357014.jpg)

`Java15`逐步废弃偏向锁：`JDK15`已经在2020年9月15日发布，其中有一项更新是废弃偏向锁，官方的详细说明在`JEP 374:Disable and Deprecate Biased Locking`

![image-20240217204906189](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357057.png)

#####  10.2.4 轻量锁

**轻量级锁**：多线程竞争，但是任意时刻最多只有一个线程竞争，即不存在锁竞争太过激烈的情况，也就没有线程阻塞。有线程来参与锁的竞争，但是获取锁的冲突时间极短，本质就是自旋锁`CAS`

**轻量级锁的获取**：轻量级锁是为了在线程近乎交替执行同步块时提高性能。主要目的：在没有多线程竞争的前提下，通过`CAS`减少重量级锁使用操作系统互斥量产生的性能消耗，说白了先自旋，不行才升级阻塞

**轻量级锁的升级时机**：当关闭偏向锁功能或多线程竞争偏向锁会导致偏向锁升级为轻量级锁。假如线程A已经拿到锁，这时线程B又来抢该对象的锁，由于该对象的锁已经被线程A拿到，当前该锁已是偏向锁了。而线程B在争抢时发现对象头`Mark Word`中的线程ID不是线程B自己的线程ID(而是线程A)，那线程B就会进行`CAS`操作希望能获得锁。此时线程B操作中有两种情况：**如果锁获取成功**，直接替换`Mark Word`中的线程ID为B自己的ID(A→B)。重新偏向于其他线程(即将偏向锁交给其他线程，相当于当前线程"被"释放了锁)，该锁会保持偏向锁状态，A线程结束，B线程上位。**如果锁获取失败**，则偏向锁升级为轻量级锁(设置偏向锁标识为0并设置锁标志位为00)，此时轻量级锁由原持有偏向锁的线程持有，继续执行其同步代码，而正在竞争的线程B会进入自旋等待获得该轻量级锁

![image-20240216014915150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357635.png)

**轻量级锁的加锁**：`JVM`会为每个线程在当前线程的栈帧中创建用于存储锁记录的空间，官方称为`Displaced Mark Word`。若一个线程获得锁时发现是轻量级锁，会把锁的`MarkWord`复制到自己的`Displaced Mark Word`里面。然后线程尝试用`CAS`将锁的`MarkWord`替换为指向锁记录的指针。如果成功，当前线程获得锁，如果失败，表示`Mark Word`已经被替换成了其他线程的锁记录，说明在与其它线程竞争锁，当前线程就尝试使用自旋来获取锁。自旋`CAS`：不断尝试去获取锁，能不升级就不升级，尽量不要阻塞

**轻量级锁的释放**：在释放锁时，当前线程会使用`CAS`操作将`Displaced Mark Word`的内容复制回锁的`Mark Word`里面。如果没有发生竞争，那么这个复制的操作会成功。如果有其他线程因为自旋多次导致轻量级锁升级成了重量级锁，那么`CAS`操作会失败，此时会释放锁并唤醒被阻塞的线程

**理解"自旋达到一定次数和程度"**：

>`java6`之前：
>默认启用，默认情况下自旋的次数是10次或者自旋线程数超过cpu核数一半
>配置自旋的次数：`-XX:PreBlockSpin=10`
>
>`Java6`之后：
>自适应自旋锁的大致原理：线程如果自旋成功了，那下次自旋的最大次数会增加，因为`JVM`认为既然上次成功了，那么这一次也很大概率会成功。反之，如果很少会自旋成功，那么下次会减少自旋的次数甚至不自旋，避免CPU空转。自适应意味着自旋的次数不是固定不变的而是根据:同一个锁上一次自旋的时间 和 拥有锁线程的状态来决定

**轻量锁与偏向锁的区别和不同**：轻量级锁每次退出同步块都需要释放锁，而偏向锁是在竞争发生时才释放锁。轻量级锁争夺锁失败时会自旋尝试抢占锁

#####  10.2.5 重量级锁

**重量级锁**：有大量的线程参与锁的竞争，冲突性很高。重量级锁原理：`Java`中`synchronized`的重量级锁是基于进入和退出`Monitor`对象实现的。在编译时会将同步块的开始位置插入`monitor enter`指令，在结束位置插入`monitor exit`指令。当线程执行到`monitor enter`指令时，会尝试获取对象所对应的`Monitor`所有权，如果获取到了，即获取到了锁，会在`Monitor`的`owner`中存放当前线程的id，这样它将处于锁定状态，除非退出同步块，否则其他线程无法获取到这个`Monitor`

**`Code`演示**：使用`ClassLayout.parseInstance(o).toPrintable()`查看重量级锁状态下对象的内存布局，可以发现重量级锁状态下对象的锁标志位是10

```java
    // 重锁演示：
    private static void heavyWeightLock() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                synchronized (objectLock){
                    // o对象使用synchronized加锁，偏向锁带线程id情况，第一行中后面不再是0了，有了线程id的值。
                    System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
                }
                System.out.println("#################" + finalI + "##############");
            },"thread" + i).start();
        }
//程序输出：
//#################7##############
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           ca b5 75 ad (11001010(重量级锁) 10110101 01110101 10101101) (-1384794678)
//    4     4        (object header)                           8a 01 00 00 (10001010 00000001 00000000 00000000) (394)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
//
//#################5##############
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           ca b5 75 ad (11001010 10110101(重量级锁) 01110101 10101101) (-1384794678)
//    4     4        (object header)                           8a 01 00 00 (10001010 00000001 00000000 00000000) (394)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
    private static Object objectLock = new Object();
```

#####  10.2.6 锁升级 与 `hashcode`

**锁升级 和 `hashcode`**：锁升级为轻量级或重量级锁后，`Mark Word`中保存的分别是线程栈帧里的锁记录指针和重量级锁指针，已经没有位置再保存哈希码、`GC`年龄了，那么这些信息就会被转移到其他地方。在`Java`语言里面一个对象如果计算过哈希码，就应该一直保持该值不变（强烈推荐但不强制，因为用户可以重载`hashCode()`方法按自己的意愿返回哈希码)，否则很多依赖对象哈希码的API都可能存在出错风险。而作为绝大多数对象哈希码来源的`Object::hashCode()`方法，返回的是对象的一致性哈希码（`Identity Hash Code`)，这个值是能强制保证不变的，它通过在对象头中存储计算结果来保证第一次计算之后，再次调用该方法取到的哈希码值永远不会再发生改变。因此，当一个对象已经计算过一致性哈希码后，它就再也无法进入偏向锁状态了。而当一个对象当前正处于偏向锁状态，又收到需要计算其一致性哈希码请求时，它的偏向锁状态会被立即撤销，并且膨胀为重量级锁。在重量级锁的实现中，对象头的指向了重量级锁的位置，代表重量级锁的`ObjectMonitor`类里有字段可以记录非加锁状态(标志位为“01”)下的`Mark Word`，其中自然可以存储原来的哈希码

**锁升级 时 对`hashcode`的处理**：**在无锁状态下**，`Mark Word`中可以存储对象的`identity hash code`值。当对象的`hashCode()`方法第一次被调用时，`JVM`会生成对应的`identity hash code`值并将该值存储到`Mark Word`中。**对于偏向锁**，在线程获取偏向锁时，会用`Thread ID`和`epoch`值覆盖`identity hash code`所在的位置。如果一个对象的`hashCode()`方法已经被调用过一次之后，这个对象不能被设置偏向锁。因为如果可以的话，那`Mark Word`中的`identity hash code`必然会被偏向线程ld给覆盖，这就会造成同一个对象前后两次调用`hashCode()`方法得到的结果不一致。**升级为轻量级锁时**，`JVM`会在当前线程的栈帧中创建一个锁记录(`Lock Record`)空间，用于存储锁对象的`Mark Word`拷贝，该拷贝中可以包含`identity hash code`，所以轻量级锁可以和`identity hash code`共存，哈希码和`GC`年龄自然保存在此，释放锁后会将这些信息写回到对象头。**升级为重量级锁后**，`Mark Word`保存的重量级锁指针，代表重量级锁的`ObjectMonitor`类里有字段记录非加锁状态下的`Mark Word`，锁释放后也会将信息写回到对象头

**代码验证演示**：当一个对象已经计算过`identity hash code`，它就无法进入偏向锁状态，跳过偏向锁直接升级轻量级锁

```java
    private static void lockLashCode01(){
            //先睡眠5秒，保证开启偏向锁
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

            Object o = new Object();
            System.out.println("本应是偏向锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());

            o.hashCode();//没有重写，一致性哈希，重写后无效,当一个对象已经计算过identity hash code，它就无法进入偏向锁状态；

            synchronized (o){
                System.out.println("本应是偏向锁，但是由于计算过一致性哈希，会直接升级为轻量级锁");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
//程序输出：
//    本应是偏向锁
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 00 00 00 (00000101(偏向锁) 00000000 00000000 00000000) (5)
//    4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
//
//    本应是偏向锁，但是由于计算过一致性哈希，会直接升级为轻量级锁
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           38 f1 df 4a (00111000(轻量级锁) 11110001 11011111 01001010) (1256190264)
//    4     4        (object header)                           2b 00 00 00 (00101011 00000000 00000000 00000000) (43)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
```

**代码验证演示**：偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁

```java
    private static void lockLashCode02(){
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        Object o = new Object();
        synchronized (o){
            o.hashCode();//没有重写，一致性哈希，重写后无效
            System.out.println("偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//程序输出：
//    偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           7a cf a4 77 (01111010(重量级锁) 11001111 10100100 01110111) (2007289722)
//    4     4        (object header)                           0f 02 00 00 (00001111 00000010 00000000 00000000) (527)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
```

#####  10.2.7 总结

**锁升级时不同锁的优缺点对比**：

| 锁       | 优点                                                         | 缺点                                          | 适用场景                           |
| -------- | ------------------------------------------------------------ | --------------------------------------------- | ---------------------------------- |
| 偏向锁   | 加锁和解锁不需要额外的消耗,和执行非同步方法相比仅存在纳秒级的差距 | 如果线程间存在锁竞争,会带来额外的锁撤销的消耗 | 适用于只有一个线程访问同步块场景   |
| 轻量级锁 | 竞争的线程不会阻塞,提高了程序的响应速度                      | 如果始终得不到锁竞争的线程,使用自旋会消耗CPU  | 追求响应时间，同步块执行速度非常快 |
| 重量级锁 | 线程竞争不使用自旋,不会消耗CPU                               | 线程阻塞,响应时间缓慢                         | 追求吞吐量，同步块执行速度较长     |

**不同锁的适用场景**：

- 偏向锁：适用于单线程的情况，在不存在锁竞争的时候进入同步方法/代码块则使用偏向锁
- 轻量级锁：适用于竞争较不激烈的情况(这和乐观锁的使用范围类似)，存在竞争时升级为轻量级锁，轻量级锁采用的是自旋锁，如果同步方法/代码块执行时间很短的话，采用轻量级锁虽然会占用`cpu`资源但是相对比使用重量级锁还是更高效
- 重量级锁：适用于竞争激烈的情况，如果同步方法/代码块执行时间很长，那么使用轻量级锁自旋带来的性能消耗就比使用重量级锁更严重，这时候就需要升级为重量级锁

**总结**：`JDK1.6`之前`synchronized`使用的是重量级锁，`JDK1.6`之后进行了优化，拥有了无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁的升级过程，而不是无论什么情况都使用重量级锁。`synchronized`锁升级过程实际上是把之前的悲观锁(重量级锁)变成在一定条件下使用偏向锁以及使用轻量级(自旋锁`CAS`)的形式。`synchronized`在修饰方法和代码块在字节码上实现方式有很大差异，但是内部实现还是基于对象头的`MarkWord`来实现的

###  10.3  `JIT`编译器对锁的优化

#####  10.3.1 锁消除

`JIT`：`Just ln Time Compiler`（即时编译器）

**锁消除**：锁消除是指在运行时，`JIT`编译器会尝试优化代码，以避免不必要的同步操作和锁定。这种优化可以提高代码的执行效率和并发性能。这种优化对于性能关键型的`Java`应用程序特别有用，因为它可以减少不必要的同步开销，从而提高应用程序的并发性能和运行效率。在`Java`中，`JIT`编译器的锁消除优化使得开发者能够更好地利用并发编程，而无需担心性能损失

**锁消除通常发生在以下情况下**：

- 当编译器能够确定某些代码段不存在竞争条件时，它可以消除不必要的锁定
- 编译器可以通过分析上下文环境来确定锁定是否真正需要，如果不需要，就会尝试消除锁定，以提高性能

**锁消除演示**：

```java
/**
 * 锁消除演示：
 * 从JIT角度看相当于无视它，synchronized (o)不存在了,
 * 这个锁对象并没有被共用扩散到其它线程使用，极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
 */
public class LockClearUPDemo
{
    static Object objectLock = new Object();

    public void m1()
    {
        // 正确加锁
        /*synchronized (objectLock){
            System.out.println("-----hello LockClearUPDemo");
        }*/

        // 错误加锁（对线程内部new出来的局部变量加锁）
        //锁消除问题，JIT编译器会无视它 ，synchronized(o),每次new出来的，不存在锁了，非正常的。
        Object o = new Object();
        synchronized (o){
            System.out.println("-----hello LockClearUPDemo"+"\t"+o.hashCode()+"\t"+objectLock.hashCode());
        }
    }

    public static void main(String[] args)
    {
        LockClearUPDemo lockClearUPDemo = new LockClearUPDemo();

        for (int i = 1; i <=10; i++) {
            new Thread(() -> {
                lockClearUPDemo.m1();
            },String.valueOf(i)).start();
        }
    }
}
```

#####  10.3.2 锁粗化

**锁粗化**：锁粗化是一种优化技术，它通过合并多个连续的同步块，从而减少由于同步块的细粒度导致的性能开销。当`JIT`编译器检测到存在连续的同步块，并且这些同步块作用于同一个锁对象时，它会将这些多个同步块合并为一个更大的同步块，从而减少锁的粒度，这就是锁粗化。通过锁粗化优化，`JIT`编译器能够减少由于细粒度同步块带来的开销，从而改善并发程序的性能。这种优化特别适用于包含大量细粒度同步块的代码，通过锁粗化，可以大大降低同步的开销，提高多线程程序的执行效率

**锁粗化的具体优化如下**：

- 当`JIT`编译器检测到一系列的连续操作都针对同一对象加锁和解锁时，它会将这些操作合并为一个更大的同步块，从而减少频繁加锁解锁的开销
- 锁粗化使得同步块的范围更大，可以减少线程争用锁的概率，提高并发性能

**锁粗化演示**：

```java
/**
 * 锁粗化
 * 假如方法中首尾相接，前后相邻的都是同一个锁对象，那JIT编译器就会把这几个synchronized块合并成一个大块，
 * 加粗加大范围，一次申请锁使用即可，避免次次的申请和释放锁，提升了性能
 */
public class LockBigDemo
{
    static Object objectLock = new Object();

    public static void main(String[] args)
    {
        new Thread(() -> {
            synchronized (objectLock){
                System.out.println("111111");
            }
            synchronized (objectLock){
                System.out.println("222222");
            }
            synchronized (objectLock){
                System.out.println("333333");
            }
            synchronized (objectLock){
                System.out.println("444444");
            }

            synchronized (objectLock){
                System.out.println("111111");
                System.out.println("222222");
                System.out.println("333333");
                System.out.println("444444");
            }

        },"t1").start();
    }
}
```



## 11. `AQS`

`AQS`相关知识：公平锁和非公平锁、可重入锁、自旋思想、`LockSupport`、数据结构之双向链表、设计模式之模板设计模式

###  11.1 `AQS`简介

**`AQS`（`AbstractQueuedSynchronizer`）**：抽象的队列同步器。是用来实现锁或者其它同步器组件的公共基础部分的抽象实现，是重量级基础框架及整个`JUC`体系的基石，主要用于解决锁"分配给谁"的问题。`AQS`利用一个抽象的`FIFO`队列来完成资源获取线程的排队工作，并通过一个`int`类变量表示持有锁的状态。`AQS`是通过使用`volatile`关键字和`CAS`机制实现的锁模板。`volatile`关键字确保了`AQS`内部的状态对于所有线程都是可见的，而`CAS`机制则提供了一种无锁的原子操作方式，用于实现线程间的同步和互斥。`AQS`封装了线程阻塞等待挂起，解锁唤醒其他线程的逻辑。`AQS`子类只需根据状态变量判断是否可获取锁，是否释放锁，使用`LockSupport`挂起、唤醒线程

![image-20240218101400593](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357753.png)

**`AQS`的作用**：加锁会导致线程阻塞，有线程阻塞就需要排队，实现排队必然需要队列。抢到资源的线程直接处理业务，抢不到资源的线程必然涉及一种排队等候机制。抢占资源失败的线程继续去等待(类似银行业务办理窗口都满了，暂时没有受理窗口的顾客只能去候客区排队等候)，但等候线程仍然保留获取锁的可能且获取锁流程仍在继续(候客区的顾客也在等着叫号，轮到了再去受理窗口办理业务)。既然说到了排队等候机制，那么就一定会有某种队列形成，这样的队列是什么数据结构呢？如果共享资源被占用，就需要一定的阻塞等待唤醒机制来保证锁分配。`AQS`中抽象的`FIFO`队列主要是用`CLH`队列的变体实现的，将暂时获取不到锁的线程加入到队列中，这个队列就是`AQS`同步队列的抽象表现。它将要请求共享资源的线程及自身的等待状态封装成队列的结点对象(`Node`)，通过`CAS`、自旋以及`LockSupport.park()`的方式，维护`state`变量的状态，使并发达到同步的效果

**`AQS`同步队列的基本结构**：`CLH: Craig、Landin and Hagersten`队列，是个单向链表，`AQS`中的队列是`CLH`变体的虚拟双向队列(FIFO)

![image-20240221010511437](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357371.png)



**`AQS`是`JUC`中最重要的基石**：`ReentrantLock`、`CountDownLatch`、`ReentrantReadWriteLock`、`semaphore`等类都利用了`AbstractQueuedSynchronizer`提供的机制来实现它们各自的功能，包括锁的获取和释放、计数控制、读写管理以及资源访问。 `AQS`为这些类提供了一个强大的基础，使其能够高效地支持多线程并发操作

![image-20240218102323795](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357587.png)

**`AQS`与`ReentrantLock`**： `ReentrantLock`是`AbstractQueuedSynchronizer`的一个重要使用示例。它提供了可重入的互斥锁，这意味着同一线程可以多次获得该锁而不被阻塞。`ReentrantLock`内部使用`AQS`来实现锁的获取和释放，因此它充分利用了`AQS`提供的`FIFO`等待队列以及同步状态的机制

```java
public class ReentrantLock implements Lock, java.io.Serializable {
    /** Synchronizer providing all implementation mechanics */
    private final Sync sync;
    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the lock.
     */
    abstract static class Sync extends AbstractQueuedSynchronizer {
        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        abstract void lock();
    }
}
```

**`AQS` 与 `CountDownLatch`**： `CountDownLatch`也使用了`AbstractQueuedSynchronizer`的机制来实现其计数功能。在`CountDownLatch`内部，通过`AQS`的共享模式来控制计数的减少和等待的过程

```java
public class CountDownLatch {
    private volatile int state;
    /**
     * Synchronization control For CountDownLatch.
     * Uses AQS state to represent count.
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;
    }
    private final Sync sync;
}
```

**`AQS`与`ReentrantReadWriteLock`**： `ReentrantReadWriteLock`是另一个与`AQS`相关的类。在内部，它包含两个`AQS`实例，用于管理读锁和写锁。这个锁的实现充分利用了`AQS`的能力，使得可以高效地支持多线程读取和少量线程写入的场景

```java
public class ReentrantReadWriteLock implements ReadWriteLock, java.io.Serializable {
    /** Performs all synchronization mechanics */
    final Sync sync;
    /**
     * Synchronization implementation for ReentrantReadWriteLock.
     * Subclassed into fair and nonfair versions.
     */
    abstract static class Sync extends AbstractQueuedSynchronizer {
    }
}
```

**`AQS`与`Semaphore`**： `Semaphore`也依赖于`AbstractQueuedSynchronizer`。`Semaphore`内部使用`AQS`来维护许可证的数量，并通过`AQS`的状态以及排队机制来管理对临界区资源的访问

```java
public class Semaphore implements java.io.Serializable {
    /** All mechanics via AbstractQueuedSynchronizer subclass */
    private final Sync sync;
    /**
     * Synchronization implementation for semaphore.  Uses AQS state
     * to represent permits. Subclassed into fair and nonfair
     * versions.
     */
    abstract static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1192457210091910933L;
    }
}
```

**`AQS`和`ReentrantLock`的关系架构图**：

![image-20240221011103302](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357068.png)



**锁和`AQS`同步器的关系**：

- **锁面向锁的使用者**（锁定义了程序员和锁交互的使用层`API`，隐藏了实现细节，程序员调用即可）
- **同步器面向锁的实现者**（`Java`并发大神`DougLee`，提出统一规范并简化了锁的实现，将其抽象出来，屏蔽了同步状态管理、同步队列的管理和维护、阻塞线程排队和通知、唤醒机制等，是一切锁和同步组件实现的公共基础部分）。锁是用来控制对共享资源的访问的机制，而`AQS（AbstractQueuedSynchronizer）`是用于构建锁和同步器的框架。`AQS`提供了一种通用的方式来实现各种类型的同步器，比如`ReentrantLock`和`Semaphore`等。它通过内置的`FIFO`队列（由`Node`组成）来管理等待锁的线程，同时支持自定义的同步器。重要的是，`AQS`使用模板方法模式，使得开发者可以定义自己的同步器，从而实现更灵活的锁定机制。具体来说，锁（比如`ReentrantLock`）通常会使用`AQS`来提供底层的实现。`AQS`提供了可重入性（即同一个线程可以多次获得同一个锁）、条件等待、共享模式等功能，这些特性被锁（如`ReentrantLock`）所使用，从而使得`Java`中的锁得以实现。总之，`AQS`作为一个框架，为锁（以及其他同步器）的实现提供了核心的底层机制，使得开发者能够构建各种灵活的同步工具

###  11.2 `AQS`源码分析

#####  11.2.1` AQS`继承关系 | `AQS`源码注释说明

`AbstractQueuedSynchronizer`(`AQS`)继承关系：`AbstractQueuedSynchronizer`(`AQS`) 、`AbstractQueuedLongSynchronizer`、`AbstractOwnableSynchronizer`都位于`java.util.concurrent.locks`包下。`AbstractQueuedSynchronizer`(`AQS`) 和`AbstractQueuedLongSynchronizer`都继承自`AbstractOwnableSynchronizer`

```java
public abstract class AbstractQueuedLongSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
    }
```

```java
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
}
```

**`AbstractQueuedSynchronizer`源码注释说明**：核心思想是`AQS`为实现阻塞锁和相关的同步器提供一个框架，`AQS`是依赖于先进先出的一个等待，同时依靠单个原子`int`值来表示状态，通过占用和释放方法，改变状态值

```java
/**
 * Provides a framework for implementing blocking locks and related
 * synchronizers (semaphores, events, etc) that rely on
 * first-in-first-out (FIFO) wait queues.  This class is designed to
 * be a useful basis for most kinds of synchronizers that rely on a
 * single atomic {@code int} value to represent state. Subclasses
 * must define the protected methods that change this state, and which
 * define what that state means in terms of this object being acquired
 * or released.  Given these, the other methods in this class carry
 * out all queuing and blocking mechanics. Subclasses can maintain
 * other state fields, but only the atomically updated {@code int}
 * value manipulated using methods {@link #getState}, {@link
 * #setState} and {@link #compareAndSetState} is tracked with respect
 * to synchronization.
 *
 * <p>Subclasses should be defined as non-public internal helper
 * classes that are used to implement the synchronization properties
 * of their enclosing class.  Class
 * {@code AbstractQueuedSynchronizer} does not implement any
 * synchronization interface.  Instead it defines methods such as
 * {@link #acquireInterruptibly} that can be invoked as
 * appropriate by concrete locks and related synchronizers to
 * implement their public methods.
 *
 * <p>This class supports either or both a default <em>exclusive</em>
 * mode and a <em>shared</em> mode. When acquired in exclusive mode,
 * attempted acquires by other threads cannot succeed. Shared mode
 * acquires by multiple threads may (but need not) succeed. This class
 * does not &quot;understand&quot; these differences except in the
 * mechanical sense that when a shared mode acquire succeeds, the next
 * waiting thread (if one exists) must also determine whether it can
 * acquire as well. Threads waiting in the different modes share the
 * same FIFO queue. Usually, implementation subclasses support only
 * one of these modes, but both can come into play for example in a
 * {@link ReadWriteLock}. Subclasses that support only exclusive or
 * only shared modes need not define the methods supporting the unused mode.
 *
 * <p>This class defines a nested {@link ConditionObject} class that
 * can be used as a {@link Condition} implementation by subclasses
 * supporting exclusive mode for which method {@link
 * #isHeldExclusively} reports whether synchronization is exclusively
 * held with respect to the current thread, method {@link #release}
 * invoked with the current {@link #getState} value fully releases
 * this object, and {@link #acquire}, given this saved state value,
 * eventually restores this object to its previous acquired state.  No
 * {@code AbstractQueuedSynchronizer} method otherwise creates such a
 * condition, so if this constraint cannot be met, do not use it.  The
 * behavior of {@link ConditionObject} depends of course on the
 * semantics of its synchronizer implementation.
 *
 * <p>This class provides inspection, instrumentation, and monitoring
 * methods for the internal queue, as well as similar methods for
 * condition objects. These can be exported as desired into classes
 * using an {@code AbstractQueuedSynchronizer} for their
 * synchronization mechanics.
 *
 * <p>Serialization of this class stores only the underlying atomic
 * integer maintaining state, so deserialized objects have empty
 * thread queues. Typical subclasses requiring serializability will
 * define a {@code readObject} method that restores this to a known
 * initial state upon deserialization.
 *
 * <h3>Usage</h3>
 *
 * <p>To use this class as the basis of a synchronizer, redefine the
 * following methods, as applicable, by inspecting and/or modifying
 * the synchronization state using {@link #getState}, {@link
 * #setState} and/or {@link #compareAndSetState}:
 *
 * <ul>
 * <li> {@link #tryAcquire}
 * <li> {@link #tryRelease}
 * <li> {@link #tryAcquireShared}
 * <li> {@link #tryReleaseShared}
 * <li> {@link #isHeldExclusively}
 * </ul>
 *
 * Each of these methods by default throws {@link
 * UnsupportedOperationException}.  Implementations of these methods
 * must be internally thread-safe, and should in general be short and
 * not block. Defining these methods is the <em>only</em> supported
 * means of using this class. All other methods are declared
 * {@code final} because they cannot be independently varied.
 *
 * <p>You may also find the inherited methods from {@link
 * AbstractOwnableSynchronizer} useful to keep track of the thread
 * owning an exclusive synchronizer.  You are encouraged to use them
 * -- this enables monitoring and diagnostic tools to assist users in
 * determining which threads hold locks.
 *
 * <p>Even though this class is based on an internal FIFO queue, it
 * does not automatically enforce FIFO acquisition policies.  The core
 * of exclusive synchronization takes the form:
 *
 * <pre>
 * Acquire:
 *     while (!tryAcquire(arg)) {
 *        <em>enqueue thread if it is not already queued</em>;
 *        <em>possibly block current thread</em>;
 *     }
 *
 * Release:
 *     if (tryRelease(arg))
 *        <em>unblock the first queued thread</em>;
 * </pre>
 *
 * (Shared mode is similar but may involve cascading signals.)
 *
 * <p id="barging">Because checks in acquire are invoked before
 * enqueuing, a newly acquiring thread may <em>barge</em> ahead of
 * others that are blocked and queued.  However, you can, if desired,
 * define {@code tryAcquire} and/or {@code tryAcquireShared} to
 * disable barging by internally invoking one or more of the inspection
 * methods, thereby providing a <em>fair</em> FIFO acquisition order.
 * In particular, most fair synchronizers can define {@code tryAcquire}
 * to return {@code false} if {@link #hasQueuedPredecessors} (a method
 * specifically designed to be used by fair synchronizers) returns
 * {@code true}.  Other variations are possible.
 *
 * <p>Throughput and scalability are generally highest for the
 * default barging (also known as <em>greedy</em>,
 * <em>renouncement</em>, and <em>convoy-avoidance</em>) strategy.
 * While this is not guaranteed to be fair or starvation-free, earlier
 * queued threads are allowed to recontend before later queued
 * threads, and each recontention has an unbiased chance to succeed
 * against incoming threads.  Also, while acquires do not
 * &quot;spin&quot; in the usual sense, they may perform multiple
 * invocations of {@code tryAcquire} interspersed with other
 * computations before blocking.  This gives most of the benefits of
 * spins when exclusive synchronization is only briefly held, without
 * most of the liabilities when it isn't. If so desired, you can
 * augment this by preceding calls to acquire methods with
 * "fast-path" checks, possibly prechecking {@link #hasContended}
 * and/or {@link #hasQueuedThreads} to only do so if the synchronizer
 * is likely not to be contended.
 *
 * <p>This class provides an efficient and scalable basis for
 * synchronization in part by specializing its range of use to
 * synchronizers that can rely on {@code int} state, acquire, and
 * release parameters, and an internal FIFO wait queue. When this does
 * not suffice, you can build synchronizers from a lower level using
 * {@link java.util.concurrent.atomic atomic} classes, your own custom
 * {@link java.util.Queue} classes, and {@link LockSupport} blocking
 * support.
 *
 * <h3>Usage Examples</h3>
 *
 * <p>Here is a non-reentrant mutual exclusion lock class that uses
 * the value zero to represent the unlocked state, and one to
 * represent the locked state. While a non-reentrant lock
 * does not strictly require recording of the current owner
 * thread, this class does so anyway to make usage easier to monitor.
 * It also supports conditions and exposes
 * one of the instrumentation methods:
 *
 *  <pre> {@code
 * class Mutex implements Lock, java.io.Serializable {
 *
 *   // Our internal helper class
 *   private static class Sync extends AbstractQueuedSynchronizer {
 *     // Reports whether in locked state
 *     protected boolean isHeldExclusively() {
 *       return getState() == 1;
 *     }
 *
 *     // Acquires the lock if state is zero
 *     public boolean tryAcquire(int acquires) {
 *       assert acquires == 1; // Otherwise unused
 *       if (compareAndSetState(0, 1)) {
 *         setExclusiveOwnerThread(Thread.currentThread());
 *         return true;
 *       }
 *       return false;
 *     }
 *
 *     // Releases the lock by setting state to zero
 *     protected boolean tryRelease(int releases) {
 *       assert releases == 1; // Otherwise unused
 *       if (getState() == 0) throw new IllegalMonitorStateException();
 *       setExclusiveOwnerThread(null);
 *       setState(0);
 *       return true;
 *     }
 *
 *     // Provides a Condition
 *     Condition newCondition() { return new ConditionObject(); }
 *
 *     // Deserializes properly
 *     private void readObject(ObjectInputStream s)
 *         throws IOException, ClassNotFoundException {
 *       s.defaultReadObject();
 *       setState(0); // reset to unlocked state
 *     }
 *   }
 *
 *   // The sync object does all the hard work. We just forward to it.
 *   private final Sync sync = new Sync();
 *
 *   public void lock()                { sync.acquire(1); }
 *   public boolean tryLock()          { return sync.tryAcquire(1); }
 *   public void unlock()              { sync.release(1); }
 *   public Condition newCondition()   { return sync.newCondition(); }
 *   public boolean isLocked()         { return sync.isHeldExclusively(); }
 *   public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
 *   public void lockInterruptibly() throws InterruptedException {
 *     sync.acquireInterruptibly(1);
 *   }
 *   public boolean tryLock(long timeout, TimeUnit unit)
 *       throws InterruptedException {
 *     return sync.tryAcquireNanos(1, unit.toNanos(timeout));
 *   }
 * }}</pre>
 *
 * <p>Here is a latch class that is like a
 * {@link java.util.concurrent.CountDownLatch CountDownLatch}
 * except that it only requires a single {@code signal} to
 * fire. Because a latch is non-exclusive, it uses the {@code shared}
 * acquire and release methods.
 *
 *  <pre> {@code
 * class BooleanLatch {
 *
 *   private static class Sync extends AbstractQueuedSynchronizer {
 *     boolean isSignalled() { return getState() != 0; }
 *
 *     protected int tryAcquireShared(int ignore) {
 *       return isSignalled() ? 1 : -1;
 *     }
 *
 *     protected boolean tryReleaseShared(int ignore) {
 *       setState(1);
 *       return true;
 *     }
 *   }
 *
 *   private final Sync sync = new Sync();
 *   public boolean isSignalled() { return sync.isSignalled(); }
 *   public void signal()         { sync.releaseShared(1); }
 *   public void await() throws InterruptedException {
 *     sync.acquireSharedInterruptibly(1);
 *   }
 * }}</pre>
 *
 * @since 1.5
 * @author Doug Lea
 */
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
}
```

#####  11.2.2  `AQS`的`state`变量 和 `Node`节点

**`AQS`核心成员变量**：`AQS`使用一个`volatile`的`int`类型的成员变量来表示同步状态，通过内置的FIFO队列来完成资源获取的排队工作，将每个要去抢占资源的线程封装成一个`Node`节点来实现锁的分配，通过`CAS`完成对`State`值的修改。`AQS`中的`state`成员变量和`Node`节点：

```java
// AQS的同步状态State成员变量（类比银行办理业务的受理窗口状态）
// state变量用于判断是否阻塞
private volatile int state;

// CLH队列(双向队列)中的节点（Node可类比银行候客区的等待顾客）
// Node节点在CLH队列(双向队列)中从尾部入队，从头部出队
static final class Node{}

// 总结AQS：state变量+CLH双端队列
```

**`AQS`的同步队列和`Node`节点**：`Node`节点类用于实现`CLH（Craig，Landin和Hagersten）`锁队列。`Node`节点类是用来构建等待队列的节点，它通过`prev`和`next`来构建一个链表结构，用于管理线程的阻塞和唤醒。`Node`节点实现了简化的`CLH`锁队列，`Node`节点不仅用于阻塞同步器，还用于同步器的控制信息，节点中的状态字段用于跟踪线程是否需要阻塞。每个节点实际上代表了一个特定通知样式的监视器，它能够持有一个等待中的线程。节点的状态字段并不控制线程是否被授予锁等，只是用来跟踪线程是否应该阻塞。`CLH`队列进行入队和出队的操作是基于原子操作实现的。如果一个节点被取消，它的后继节点通常会被重新连接到一个未取消的前驱节点。同时，被阻塞的线程仍然有机会被唤醒并找到一个新的前驱节点。构建等待队列时，需要一个虚拟的头节点进行初始化。这些虚拟的头节点在构造过程中并不会立即创建，而是在第一次发现有争用时才创建

**`AQS`的同步队列和`Node`节点**：`AQS`内部维护一个同步队列，元素就是包装了线程的`Node`。同步队列中首节点是获取到锁的节点，它在释放锁的时会唤醒后继节点，后继节点获取到锁的时候会把自己设为首节点。线程会先尝试获取锁，失败则封装成`Node`，`CAS`加入同步队列的尾部。在加入同步队列的尾部时，会判断前驱节点是否是`head`结点，并尝试加锁(可能前驱节点刚好释放锁)，否则线程进入阻塞等待

**`AQS`源码中对`Node`节点说明**：

```java
public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer
    implements java.io.Serializable {
    private static final long serialVersionUID = 7373984972572414691L;
    /**
     * Creates a new {@code AbstractQueuedSynchronizer} instance
     * with initial synchronization state of zero.
     */
    protected AbstractQueuedSynchronizer() { }
    /**
     * Wait queue node class.
     *
     * <p>The wait queue is a variant of a "CLH" (Craig, Landin, and
     * Hagersten) lock queue. CLH locks are normally used for
     * spinlocks.  We instead use them for blocking synchronizers, but
     * use the same basic tactic of holding some of the control
     * information about a thread in the predecessor of its node.  A
     * "status" field in each node keeps track of whether a thread
     * should block.  A node is signalled when its predecessor
     * releases.  Each node of the queue otherwise serves as a
     * specific-notification-style monitor holding a single waiting
     * thread. The status field does NOT control whether threads are
     * granted locks etc though.  A thread may try to acquire if it is
     * first in the queue. But being first does not guarantee success;
     * it only gives the right to contend.  So the currently released
     * contender thread may need to rewait.
     *
     * <p>To enqueue into a CLH lock, you atomically splice it in as new
     * tail. To dequeue, you just set the head field.
     * <pre>
     *      +------+  prev +-----+       +-----+
     * head |      | <---- |     | <---- |     |  tail
     *      +------+       +-----+       +-----+
     * </pre>
     *
     * <p>Insertion into a CLH queue requires only a single atomic
     * operation on "tail", so there is a simple atomic point of
     * demarcation from unqueued to queued. Similarly, dequeuing
     * involves only updating the "head". However, it takes a bit
     * more work for nodes to determine who their successors are,
     * in part to deal with possible cancellation due to timeouts
     * and interrupts.
     *
     * <p>The "prev" links (not used in original CLH locks), are mainly
     * needed to handle cancellation. If a node is cancelled, its
     * successor is (normally) relinked to a non-cancelled
     * predecessor. For explanation of similar mechanics in the case
     * of spin locks, see the papers by Scott and Scherer at
     * http://www.cs.rochester.edu/u/scott/synchronization/
     *
     * <p>We also use "next" links to implement blocking mechanics.
     * The thread id for each node is kept in its own node, so a
     * predecessor signals the next node to wake up by traversing
     * next link to determine which thread it is.  Determination of
     * successor must avoid races with newly queued nodes to set
     * the "next" fields of their predecessors.  This is solved
     * when necessary by checking backwards from the atomically
     * updated "tail" when a node's successor appears to be null.
     * (Or, said differently, the next-links are an optimization
     * so that we don't usually need a backward scan.)
     *
     * <p>Cancellation introduces some conservatism to the basic
     * algorithms.  Since we must poll for cancellation of other
     * nodes, we can miss noticing whether a cancelled node is
     * ahead or behind us. This is dealt with by always unparking
     * successors upon cancellation, allowing them to stabilize on
     * a new predecessor, unless we can identify an uncancelled
     * predecessor who will carry this responsibility.
     *
     * <p>CLH queues need a dummy header node to get started. But
     * we don't create them on construction, because it would be wasted
     * effort if there is never contention. Instead, the node
     * is constructed and head and tail pointers are set upon first
     * contention.
     *
     * <p>Threads waiting on Conditions use the same nodes, but
     * use an additional link. Conditions only need to link nodes
     * in simple (non-concurrent) linked queues because they are
     * only accessed when exclusively held.  Upon await, a node is
     * inserted into a condition queue.  Upon signal, the node is
     * transferred to the main queue.  A special value of status
     * field is used to mark which queue a node is on.
     *
     * <p>Thanks go to Dave Dice, Mark Moir, Victor Luchangco, Bill
     * Scherer and Michael Scott, along with members of JSR-166
     * expert group, for helpful ideas, discussions, and critiques
     * on the design of this class.
     */
    static final class Node {
        /** Marker to indicate a node is waiting in shared mode */
        // 共享
        static final Node SHARED = new Node();
        /** Marker to indicate a node is waiting in exclusive mode */
        // 独占
        static final Node EXCLUSIVE = null;
        /** waitStatus value to indicate thread has cancelled */
        // 线程被取消了
        static final int CANCELLED =  1;
        /** waitStatus value to indicate successor's thread needs unparking */
        // 后继线程需要唤醒
        static final int SIGNAL    = -1;
        /** waitStatus value to indicate thread is waiting on condition */
        // 等待condition唤醒
        static final int CONDITION = -2;
        /**
         * waitStatus value to indicate the next acquireShared should
         * unconditionally propagate
         */
        // 共享式同步状态获取将会无条件地传播下去
        static final int PROPAGATE = -3;

        /**
         * Status field, taking on only the values:
         *   SIGNAL:     The successor of this node is (or will soon be)
         *               blocked (via park), so the current node must
         *               unpark its successor when it releases or
         *               cancels. To avoid races, acquire methods must
         *               first indicate they need a signal,
         *               then retry the atomic acquire, and then,
         *               on failure, block.
         *   CANCELLED:  This node is cancelled due to timeout or interrupt.
         *               Nodes never leave this state. In particular,
         *               a thread with cancelled node never again blocks.
         *   CONDITION:  This node is currently on a condition queue.
         *               It will not be used as a sync queue node
         *               until transferred, at which time the status
         *               will be set to 0. (Use of this value here has
         *               nothing to do with the other uses of the
         *               field, but simplifies mechanics.)
         *   PROPAGATE:  A releaseShared should be propagated to other
         *               nodes. This is set (for head node only) in
         *               doReleaseShared to ensure propagation
         *               continues, even if other operations have
         *               since intervened.
         *   0:          None of the above
         *
         * The values are arranged numerically to simplify use.
         * Non-negative values mean that a node doesn't need to
         * signal. So, most code doesn't need to check for particular
         * values, just for sign.
         *
         * The field is initialized to 0 for normal sync nodes, and
         * CONDITION for condition nodes.  It is modified using CAS
         * (or when possible, unconditional volatile writes).
         */
        // Node的等待状态waitState成员变量
        // 类比等候区中其它线程的等待状态。队列中每个排队的个体就是一个Node
        // 初始为0，状态是上面的几种
        volatile int waitStatus;
        /**
         * Link to predecessor node that current node/thread relies on
         * for checking waitStatus. Assigned during enqueuing, and nulled
         * out (for sake of GC) only upon dequeuing.  Also, upon
         * cancellation of a predecessor, we short-circuit while
         * finding a non-cancelled one, which will always exist
         * because the head node is never cancelled: A node becomes
         * head only as a result of successful acquire. A
         * cancelled thread never succeeds in acquiring, and a thread only
         * cancels itself, not any other node.
         */
        // 前置节点
        volatile Node prev;
        /**
         * Link to the successor node that the current node/thread
         * unparks upon release. Assigned during enqueuing, adjusted
         * when bypassing cancelled predecessors, and nulled out (for
         * sake of GC) when dequeued.  The enq operation does not
         * assign next field of a predecessor until after attachment,
         * so seeing a null next field does not necessarily mean that
         * node is at end of queue. However, if a next field appears
         * to be null, we can scan prev's from the tail to
         * double-check.  The next field of cancelled nodes is set to
         * point to the node itself instead of null, to make life
         * easier for isOnSyncQueue.
         */
        // 后继节点
        volatile Node next;
        /**
         * The thread that enqueued this node.  Initialized on
         * construction and nulled out after use.
         */
        volatile Thread thread;
        /**
         * Link to next node waiting on condition, or the special
         * value SHARED.  Because condition queues are accessed only
         * when holding in exclusive mode, we just need a simple
         * linked queue to hold nodes while they are waiting on
         * conditions. They are then transferred to the queue to
         * re-acquire. And because conditions can only be exclusive,
         * we save a field by using special value to indicate shared
         * mode.
         */
        Node nextWaiter;
    }
    /**
     * Head of the wait queue, lazily initialized.  Except for
     * initialization, it is modified only via method setHead.  Note:
     * If head exists, its waitStatus is guaranteed not to be
     * CANCELLED.
     */
    private transient volatile Node head;
    /**
     * Tail of the wait queue, lazily initialized.  Modified only via
     * method enq to add new wait node.
     */
    private transient volatile Node tail;
    /**
     * The synchronization state.
     */
    // AQS的同步状态State成员变量（类比银行办理业务的受理窗口状态）
    private volatile int state;
}
```

**`AQS`源码中对`Node`节点说明（中文）**：

> 等待队列是"CLH"（Craig、Landin和Hagersten）锁队列的一种变体。CLH锁通常用于自旋锁。我们将它们用于阻塞同步器，但使用相同的基本策略，在其节点的前驱中保存一些线程的控制信息。每个节点中的状态字段用于跟踪线程是否应该阻塞。当其前驱释放时，节点被唤醒。队列中的每个节点还充当特定通知样式的监视器，持有一个等待线程。但是，状态字段并不控制线程是否被授予锁等。线程可能会尝试获取锁，但是排在队列的第一个并不保证成功；它只有争夺的权利。因此，当前释放的竞争者线程可能需要重新等待。
>
> 要将节点入队到CLH锁中，您需要原子地将其拼接为新的尾节点。要出队，只需设置头节点字段。
>
> +------+  prev +-----+       +-----+
>
> head |      | <---- |     | <---- |     |  tail
>
> +------+       +-----+       +-----+
>
> CLH队列的插入仅需要对"tail"执行一次原子操作，因此从未排队到队之间有一个简单的原子分界点。同样，出队仅涉及更新"head"。但是，节点需要更多的工作来确定其后继节点，部分原因是要处理由于超时中断可能导致的取消。
>
> "prev"链接（在原始CLH锁中未使用）主要用于处理取消。如果节点被取消，其后继节点（通常）会重新链接到未取消的前驱节点。有关自旋锁情况下类似机制的解释，请参阅Scott和Scherer的论文，网址为http://www.cs.rochester.edu/u/scott/synchronization/
>
> 我们还使用"next"链接来实现阻塞机制。每个节点的线程ID保存在其自己的节点中，因此前驱节点通过遍历next链接来唤醒下一个节点，以确定它是哪个线程。确定后继节点必须避免与新入队节点竞争，以设置其前驱节点的"next"字段。当需要时，通过从原子更新的"tail"向后检查，可以解决节点的后继节点似乎为null的问题。（或者，换句话说，next链接是一种优化，因此我们通常不需要向后扫描。）
>
> 取消会在基本算法中引入一些保守性。由于我们必须轮询其他节点的取消，我们可能会错过已取消节点相对于我们的位置。这通过在取消时总是唤醒后继节点来处理，使它们能够稳定在新前驱节点上，除非我们能够识别一个未取消的前驱节点，其将承担此责任
>
> CLH队列需要一个虚拟头节点来开始。但是我们并不会在构造函数中创建它们，因为如果没有竞争，那将是一种浪费。相反，该节点将在首次竞争时构造，并设置head和tail指针
>
> 等待条件的线程使用相同的节点，但使用一个附加链接。条件仅需要将节点链接到简单（非并发）链式队列中，因为只有在独占持有时才会访问它们。在等待时，节点被插入到条件队列中。在信号时，节点被转移到主队列。状态字段的特殊值用于标记节点所在的队列



**`Node`节点的属性说明**：

![image-20240221022329777](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142357011.png)

###  11.3  `AQS`与`ReentrantLock()`

##### 11.3.1 `AQS` 与  `ReentrantLock()`的实现

`AQS`与`ReentrantLock()`的实现，下面以`ReentrantLock()`非公平锁的实现，来分析`AQS`的作用

> ReentrantLock 提供了两种类型的锁，即非公平锁和公平锁。非公平锁允许当前正在争夺锁的线程以及任意其他等待线程来获取锁，这意味着它可能会出现饥饿现象，即某些线程获取锁的机会会很小。非公平锁的优势在于它的吞吐量通常比公平锁更高。AQS 是一个用于实现同步器的框架，ReentrantLock 就是基于 AQS 实现的。AQS 提供了一种构建阻塞锁和相关同步器（如 CountDownLatch、Semaphore 等）的框架。它通过内置的 FIFO 队列来实现线程的排队和等待。在 ReentrantLock 中，AQS 负责管理锁的获取和释放，以及维护相关的等待队列管理。总的来说，ReentrantLock 中的非公平锁允许当前正在竞争的线程以及其他等待线程获取锁，而 AQS 则是一种框架，用于实现诸如 ReentrantLock 这样的同步器，帮助管理线程的排队和等待

**`AQS`抢锁模拟程序**：

```java
public class AQSDemo
{
    public static void main(String[] args)
    {
        ReentrantLock reentrantLock = new ReentrantLock();//非公平锁

        // A B C三个顾客，去银行办理业务，A先到，此时窗口空无一人，他优先获得办理窗口的机会，办理业务。
        // A 耗时严重，估计长期占有窗口
        new Thread(() -> {
            reentrantLock.lock();
            try
            {
                System.out.println("----come in A");
                //暂停20分钟线程
                try { TimeUnit.MINUTES.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
            }finally {
                reentrantLock.unlock();
            }
        },"A").start();

        //B是第2个顾客，B一看到受理窗口被A占用，只能去候客区等待，进入AQS队列，等待着A办理完成，尝试去抢占受理窗口。
        new Thread(() -> {
            reentrantLock.lock();
            try
            {
                System.out.println("----come in B");
            }finally {
                reentrantLock.unlock();
            }
        },"B").start();


        //C是第3个顾客，C一看到受理窗口被A占用，只能去候客区等待，进入AQS队列，等待着A办理完成，尝试去抢占受理窗口,前面是B顾客，FIFO
        new Thread(() -> {
            reentrantLock.lock();
            try
            {
                System.out.println("----come in C");
            }finally {
                reentrantLock.unlock();
            }
        },"C").start();

        //后续顾客DEFG。。。。。。。以此类推
        new Thread(() -> {
            reentrantLock.lock();
            try
            {
                //。。。。。。
            }finally {
                reentrantLock.unlock();
            }
        },"D").start();
    }
}
```

#####  11.3.2  `ReentrantLock()`的公平锁和非公平锁

`Lock`接口的实现类，基本都是通过聚合了一个队列同步器的子类完成线程访问控制的。`ReentrantLock()`的的锁分为公平锁和非公平锁，`ReentrantLock`架构图：

![image-20240218144317955](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142358419.png)



**`ReentrantLock`的构造方法**：`ReentrantLock`有公平锁和非公平锁之分

```java
public class ReentrantLock implements Lock, java.io.Serializable {
/*  //创建的是公平锁
    private ReentrantLock lock = new ReentrantLock(true);
    //创建的是非公平锁
    private ReentrantLock lock = new ReentrantLock(false);
    //默认创建非公平锁
    private ReentrantLock lock = new ReentrantLock();
*/
    /**
     * Creates an instance of {@code ReentrantLock}.
     * This is equivalent to using {@code ReentrantLock(false)}.
     */
    public ReentrantLock() {
        sync = new NonfairSync();
    }
    /**
     * Creates an instance of {@code ReentrantLock} with the
     * given fairness policy.
     *
     * @param fair {@code true} if this lock should use a fair ordering policy
     */
    public ReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
    }
}
```

`ReentrantLock`中公平锁和非公平锁的`lock()`方法：

```java
public class ReentrantLock implements Lock, java.io.Serializable {
    /** Synchronizer providing all implementation mechanics */
    private final Sync sync;
    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the lock.
     */
    abstract static class Sync extends AbstractQueuedSynchronizer {
        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        // 省略....
    }
    /**
     * Sync object for non-fair locks
     */
    static final class NonfairSync extends Sync {
        /**
         * Performs lock.  Try immediate barge, backing up to normal
         * acquire on failure.
         */
        final void lock() {
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
        }
        // 省略....
    }
    /**
     * Sync object for fair locks
     */
    static final class FairSync extends Sync {
        private static final long serialVersionUID = -3000897897090466540L;
        final void lock() {
            acquire(1);
        }
    }
    /**
     * Acquires the lock.
     *
     * <p>Acquires the lock if it is not held by another thread and returns
     * immediately, setting the lock hold count to one.
     *
     * <p>If the current thread already holds the lock then the hold
     * count is incremented by one and the method returns immediately.
     *
     * <p>If the lock is held by another thread then the
     * current thread becomes disabled for thread scheduling
     * purposes and lies dormant until the lock has been acquired,
     * at which time the lock hold count is set to one.
     */
    public void lock() {
        sync.lock();
    }
}
```

`AbstractQueuedSynchronizer`的`acquire`方法：

```java
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer
    implements java.io.Serializable {
        /**
         * Acquires in exclusive mode, ignoring interrupts.  Implemented
         * by invoking at least once {@link #tryAcquire},
         * returning on success.  Otherwise the thread is queued, possibly
         * repeatedly blocking and unblocking, invoking {@link
         * #tryAcquire} until success.  This method can be used
         * to implement method {@link Lock#lock}.
         *
         * @param arg the acquire argument.  This value is conveyed to
         *        {@link #tryAcquire} but is otherwise uninterpreted and
         *        can represent anything you like.
         */
        // 尝试获取锁,如果获取不到，就排队、阻塞当前线程
        public final void acquire(int arg) {
            if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
                selfInterrupt();
        }

        // 具体实现在FairSync和NonfairSync中
        protected boolean tryAcquire(int arg) {
           throw new UnsupportedOperationException();
        }
}
```

在创建完公平锁、非公平锁后，调用`lock`方法会进行加锁，最终都会调用到`AbstractQueuedSynchronizer`的`acquire`方法：



![image-20240221040317330](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142358116.png)



`ReentrantLock`中非公平锁和公平锁的`lock()`方法的实现：

```java
public class ReentrantLock implements Lock, java.io.Serializable {
    private static final long serialVersionUID = 7373984872572414699L;
    /** Synchronizer providing all implementation mechanics */
    private final Sync sync;
    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the lock.
     */
    abstract static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -5179523762034025860L;
        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        abstract void lock();
        /**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                // 0表示锁是自由状态
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                // 不等于0表示锁已经被占用
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }

    }

    /**
     * Sync object for non-fair locks
     */
    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = 7316153563782823691L;
        /**
         * Performs lock.  Try immediate barge, backing up to normal
         * acquire on failure.
         */
        final void lock() {
            // CAS获取锁。如果成功获取到锁,设置锁持有线程为当前线程并返回
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                acquire(1);
            // acquire方法在AbstractQueuedSynchronizer中
        }
        // 返回true表示获取到了锁,线程逐级返回,加锁过程结束
        // 返回false表示线程没有获取到锁
        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }

    /**
     * Sync object for fair locks
     */
    static final class FairSync extends Sync {
        private static final long serialVersionUID = -3000897897090466540L;

        final void lock() {
            // acquire方法在AbstractQueuedSynchronizer中
            acquire(1);
        }
        /**
         * Fair version of tryAcquire.  Don't grant access unless
         * recursive call or no waiters or is first.
         */
        // 返回true表示获取到了锁,线程逐级返回,加锁过程结束
        // 返回false表示线程没有获取到锁
        protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                // 0表示锁是自由状态
                // 锁为自由状态(c == 0)，并不能说明可以立刻执行cas获取锁
                // 因为可能在当前线程获取锁之前，已经有其他线程在排队了，必须遵循先来后到原则获取锁。
                // 所以还要调用hasQueuedPredecessors方法，查看自己是否需要排队。
                // hasQueuedPredecessors返回true，表示需要排队
                // hasQueuedPredecessors返回false，表示无需排队
                // 调用setExclusiveOwnerThread，设置当前线程为锁持有线程
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
             // 不等于0表示锁已经被占用
            // 重入锁的实现原理：判断之前是不是当前线程获取到锁的
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                // 设置重入锁计数器
                setState(nextc);
                // 整个tryAcquire返true，表示获取锁成功
                return true;
            }
            // 整个tryAcquire返回false，表示获取锁失败
            return false;
        }
    }
}
```

`AbstractQueuedSynchronizer`：`hasQueuedPredecessors`方法执行流程

```java
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
        /**
         * Queries whether any threads have been waiting to acquire longer
         * than the current thread.
         *
         * <p>An invocation of this method is equivalent to (but may be
         * more efficient than):
         *  <pre> {@code
         * getFirstQueuedThread() != Thread.currentThread() &&
         * hasQueuedThreads()}</pre>
         *
         * <p>Note that because cancellations due to interrupts and
         * timeouts may occur at any time, a {@code true} return does not
         * guarantee that some other thread will acquire before the current
         * thread.  Likewise, it is possible for another thread to win a
         * race to enqueue after this method has returned {@code false},
         * due to the queue being empty.
         *
         * <p>This method is designed to be used by a fair synchronizer to
         * avoid <a href="AbstractQueuedSynchronizer#barging">barging</a>.
         * Such a synchronizer's {@link #tryAcquire} method should return
         * {@code false}, and its {@link #tryAcquireShared} method should
         * return a negative value, if this method returns {@code true}
         * (unless this is a reentrant acquire).  For example, the {@code
         * tryAcquire} method for a fair, reentrant, exclusive mode
         * synchronizer might look like this:
         *
         *  <pre> {@code
         * protected boolean tryAcquire(int arg) {
         *   if (isHeldExclusively()) {
         *     // A reentrant acquire; increment hold count
         *     return true;
         *   } else if (hasQueuedPredecessors()) {
         *     return false;
         *   } else {
         *     // try to acquire normally
         *   }
         * }}</pre>
         *
         * @return {@code true} if there is a queued thread preceding the
         *         current thread, and {@code false} if the current thread
         *         is at the head of the queue or the queue is empty
         * @since 1.7
         */
        public final boolean hasQueuedPredecessors() {
            // The correctness of this depends on head being initialized
            // before tail and on head.next being accurate if the current
            // thread is first in queue.
            Node t = tail; // Read fields in reverse initialization order
            Node h = head;
            Node s;
            // 队列没有初始化，则h != t返回false。因为此时h == t == null。整个hasQueuedPredecessors方法返回false，表示不需要排队
            // s是第二个节点(第一个节点是占位空节点，代表下一个应该获取锁的线程。
            // s.thread != Thread.currentThread0为false,表示二者相等,即下一个可以获得锁的线程,正是当前线程,所以,此时整个hasQueuedPredecessors返回false，表示不需要排队
            // s.thread != Thread.currentThread()为true,表示二者不相等,说明下一个可以获得锁的线程,不是当前线程,所以整个hasQueuedPredecessors返回true，表示需要排队
            return h != t &&
                ((s = h.next) == null || s.thread != Thread.currentThread());
        }
}
```

对比公平锁和非公平锁的`tryAcquire()`方法的实现代码，其实差别就在于非公平锁获取锁时比公平锁中少了一个判断`!hasQueuedPredecessors()`。`hasQueuedPredecessors()`中判断了是否需要排队，导致公平锁和非公平锁的差异如下：**公平锁**讲究先来先到，线程在获取锁时，如果这个锁的等待队列中已经有线程在等待，那么当前线程就会进入等待队列中。**非公平锁**不管是否有等待队列，如果可以获取锁，则立刻占有锁对象。也就是说队列的第一个排队线程苏醒后，不一定就是排头的这个线程获得锁，它还是需要参加竞争锁（存在线程竞争的情况下)，后来的线程可能直接插队抢夺锁

![image-20240218162217687](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142358946.png)



##### 11.3.3  `ReentrantLock()`的`lock()`方法源码

整个`ReentrantLock`的加锁过程，可以分为三个阶段：

```
1.尝试加锁
2.加锁失败，线程入队列
3.线程入队列后，进入阻塞状态
```

`ReentrantLock()`非公平锁源码：

```java
public class ReentrantLock implements Lock, java.io.Serializable {
    /** Synchronizer providing all implementation mechanics */
    private final Sync sync;
    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the lock.
     */
    abstract static class Sync extends AbstractQueuedSynchronizer {
        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        abstract void lock();
        /**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }
    }
    /**
     * Sync object for non-fair locks
     */
    static final class NonfairSync extends Sync {
        /**
         * Performs lock.  Try immediate barge, backing up to normal
         * acquire on failure.
         */
        final void lock() {
            // 第一个线程抢占
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());
            // 第二个线程及后续线程抢占
            else
                acquire(1);
        }
        
        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }
    
}
```

`AQS`相关实现源码：

```java
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer
    implements java.io.Serializable {
    /**
     * Creates a new {@code AbstractQueuedSynchronizer} instance
     * with initial synchronization state of zero.
     */
    protected AbstractQueuedSynchronizer() { }
    /**
     * Head of the wait queue, lazily initialized.  Except for
     * initialization, it is modified only via method setHead.  Note:
     * If head exists, its waitStatus is guaranteed not to be
     * CANCELLED.
     */
    private transient volatile Node head;

    /**
     * Tail of the wait queue, lazily initialized.  Modified only via
     * method enq to add new wait node.
     */
    private transient volatile Node tail;
    /**
     * Atomically sets synchronization state to the given updated
     * value if the current state value equals the expected value.
     * This operation has memory semantics of a {@code volatile} read
     * and write.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that the actual
     *         value was not equal to the expected value.
     */
    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
    
    /**
     * Acquires in exclusive mode, ignoring interrupts.  Implemented
     * by invoking at least once {@link #tryAcquire},
     * returning on success.  Otherwise the thread is queued, possibly
     * repeatedly blocking and unblocking, invoking {@link
     * #tryAcquire} until success.  This method can be used
     * to implement method {@link Lock#lock}.
     *
     * @param arg the acquire argument.  This value is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted and
     *        can represent anything you like.
     */
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
        // acquireQueued(addWaiter(Node.EXCLUSIVE), arg)是尝试获取锁失败的情况下,当前线程（尝试获取锁的)封装成Node对象,加入到aqs队列中的处理逻辑
    }

    /**
     * Attempts to acquire in exclusive mode. This method should query
     * if the state of the object permits it to be acquired in the
     * exclusive mode, and if so to acquire it.
     *
     * <p>This method is always invoked by the thread performing
     * acquire.  If this method reports failure, the acquire method
     * may queue the thread, if it is not already queued, until it is
     * signalled by a release from some other thread. This can be used
     * to implement method {@link Lock#tryLock()}.
     *
     * <p>The default
     * implementation throws {@link UnsupportedOperationException}.
     *
     * @param arg the acquire argument. This value is always the one
     *        passed to an acquire method, or is the value saved on entry
     *        to a condition wait.  The value is otherwise uninterpreted
     *        and can represent anything you like.
     * @return {@code true} if successful. Upon success, this object has
     *         been acquired.
     * @throws IllegalMonitorStateException if acquiring would place this
     *         synchronizer in an illegal state. This exception must be
     *         thrown in a consistent fashion for synchronization to work
     *         correctly.
     * @throws UnsupportedOperationException if exclusive mode is not supported
     */
    // 具体实现看NonfairSync或FairSync
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Acquires in exclusive uninterruptible mode for thread already in
     * queue. Used by condition wait methods as well as acquire.
     *
     * @param node the node
     * @param arg the acquire argument
     * @return {@code true} if interrupted while waiting
     */
    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                // node 加入队列后的处理逻辑，这里会有一次自旋,尝试获取锁，获取不到，才调用park,阻塞自己。
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }

    /**
     * Creates and enqueues node for current thread and given mode.
     *
     * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
     * @return the new node
     */
    // 用于创建并将当前线程以及给定模式（exclusive 或 shared）的节点加入到队列中
    // 将当前线程封装成Node对象，并加入排队队列中。
    // 根据排队队列是否执行过初始化，执行1、2不同处理逻辑
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        Node pred = tail;
        if (pred != null) {
            // 表示排队队列不为空，即之前已经初始化过了，此时只需将新的node 加入排队队列末尾即可
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        // 表示排队队列为空，需执行队列初始化。enq会初始化一个空的Node，作为排队队列的head，然后将需要排队的线程，作为head 的next节点插入
        enq(node);
        return node;
    }

    /**
     * Inserts node into queue, initializing if necessary. See picture above.
     * @param node the node to insert
     * @return node's predecessor
     */
    // 没有其他线程在排队，调enq构造队列，并将node加入到队列
    // 队列尚未初始化，调用这个enq方法。
    // enq方法方法生成一个空的Node对象,插入到aqs队列头部,然后将参数node，作为其后继节点,插入队列
    private Node enq(final Node node) {
        for (;;) {
            Node t = tail;
            if (t == null) { // Must initialize
                // 如果尾指针为空，说明队列为空
                // 创建一个虚拟节点，并将尾指针指向虚拟节点
                // 说明：双向链表中，第一个节点为虚节点(也叫哨兵节点)，其实并不存储任何信息，只是占位
                // 真正的第一个有数据的节点，是从第二个节点开始的
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else {
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

    /**
     * Acquires in exclusive uninterruptible mode for thread already in
     * queue. Used by condition wait methods as well as acquire.
     *
     * @param node the node
     * @param arg the acquire argument
     * @return {@code true} if interrupted while waiting
     */
     // 整个aqs 的核心和难点之一。注意这里使用了 for (;;)
     // ,若获取到，则将链表关系重新维护下(node设置为head，之前的head从链表移出)，然后返回。
     // 首先判断node的前辈节点是不是head，如果是，说明它是下一个可以获得锁的线程，则调用一次tryAcquire，尝试获取锁
     // 如果node的前辈节点不是head_或获取锁失败,再判断其前辈节点的waitstate，是不是SIGNAL,如果是，则当前线程调用park，进入阻塞状态。如不是:
       //  1、==0，则设置为SIGNAL;
       //  2、>0(==1)，则表示前辈节点已经被取消了，将取消的节点，从队列移出,重新维护下排队链表关系。然后再次进入for循环，上面的逻辑重新执行一遍。

    final boolean acquireQueued(final Node node, long arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            // 注意这里使用了 for (;;)
            for (;;) {
                // 获取当前节点node的前置节点p
                final Node p = node.predecessor();
                // p== head为true表示node是第一个排队的，tryAcquire(arg)返回true表示获取到锁
                // 首先判断node的前辈节点是不是head，如果是，说明它是下一个可以获得锁的线程，则调用一次tryAcquire，尝试获取锁
                if (p == head && tryAcquire(arg)) {
                    // 将node设置为头结点。释放之前的头结点这个逻辑执行完成后node节点就不再排队了(获取到锁的线程，不会在排队队列中了)
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                
                // 如果前驱节点的 waitStatus 是SIGNAL状态，则shouldParkAfterFailedAcquire方法会返回true
                // shouldParkAfterFailedAcquire方法会返回true程序会继续向下执行parkAndCheckInterrupt方法，用于将当前线程挂起
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }

    /**
     * Returns previous node, or throws NullPointerException if null.
     * Use when predecessor cannot be null.  The null check could
     * be elided, but is present to help the VM.
     *
     * @return the predecessor of this node
     */
    final Node predecessor() throws NullPointerException {
        Node p = prev;
        if (p == null)
            throw new NullPointerException();
        else
            return p;
    }

    /**
     * Checks and updates status for a node that failed to acquire.
     * Returns true if thread should block. This is the main signal
     * control in all acquire loops.  Requires that pred == node.prev.
     *
     * @param pred node's predecessor holding status
     * @param node the node
     * @return {@code true} if thread should block
     */
    // shouldParkAfterFailedAcquire方法接收两个Node对象参数:参数2是淮备执行park操作的节点node ,参数1是其前辈节点 pred。
     // node 加入队列后的处理逻辑，这里会有一次自旋,尝试获取锁，获取不到，才调用park,阻塞自己。
     // shouldParkAfterFailedAcquire方法：
     // 如果前驱节点的 waitStatus 是SIGNAL状态，即shouldParkAfterFailedAcquire方法会返回true
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        // 获取前置节点的状态
        int ws = pred.waitStatus;
        
        // 如果是SIGNAL状态，即等待被占用的资源释放，直接返回true
        // 准备继续调用parkAndCheckInterrupt方法
        if (ws == Node.SIGNAL)  
            /*
             * This node has already set status asking a release
             * to signal it, so it can safely park.
             */
            return true;
        // ws大于0说明是CANCELLED状态
        if (ws > 0) {
            /*
             * Predecessor was cancelled. Skip over predecessors and
             * indicate retry.
             */
            // 循环判断前驱节点的前驱节点是否也为CANCELLED状态，忽略该状态的节点，重新连接队列
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            /*
             * waitStatus must be 0 or PROPAGATE.  Indicate that we
             * need a signal, but don't park yet.  Caller will need to
             * retry to make sure it cannot acquire before parking.
             */
            // 将当前节点的前驱节点设置为设置为SIGNAL状态，用于后续唤醒操作
            // 程序第一次执行到这返回为false，还会进行外层第二次循环，最终从代码if (ws == Node.SIGNAL) 判断处返回
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }

    /**
     * Convenience method to park and then check if interrupted
     *
     * @return {@code true} if interrupted
     */
    // 两种情况,会导致阻塞结束:
    // 1、持有锁的线程，释放锁后，将这个线程unpark 了。此时该线程，一定排在队列的队头(不包括head节点;
    // 2、线程被interrupt 了:(注意,在外部interrupt这个线程，不是抛出lnterruptException，这一点和sleep. wait阻塞不一样)
    private final boolean parkAndCheckInterrupt() {
        // 线程挂起，程序不会继续向下执行
        LockSupport.park(this);
        // 根据 park方法API描述，程序在下述三种情况会继续向下执行
        // 1.被unpark
        // 2.被中断(interrupt)
        // 3.其他不合逻辑的返回才会继续向下执行
        // 因上述三种情况程序执行至此，返回当前线程的中断状态，并清空中断状态
        // 如果由于被中断，该方法会返回true
        return Thread.interrupted();
    }
}
```

小总结：`acquire()`方法源码和3大流程走向

![642154cfb03a4e9683e5cbc4896df958](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/juc/202403142358550.png)



#####   11.3.4  `ReentrantLock()`非公平锁`unlock()`方法源码

`ReentrantLock`的`unlock()`方法：

```java
public class ReentrantLock implements Lock, java.io.Serializable {
    /** Synchronizer providing all implementation mechanics */
    private final Sync sync;
    /**
     * Attempts to release this lock.
     *
     * <p>If the current thread is the holder of this lock then the hold
     * count is decremented.  If the hold count is now zero then the lock
     * is released.  If the current thread is not the holder of this
     * lock then {@link IllegalMonitorStateException} is thrown.
     *
     * @throws IllegalMonitorStateException if the current thread does not
     *         hold this lock
     */
    public void unlock() {
        sync.release(1);
    }
    
    protected final boolean tryRelease(int releases) {
    int c = getState() - releases;
    // 当前线程不是持有锁的线程就抛出异常   
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
    boolean free = false;
    if (c == 0) {
        free = true;
        setExclusiveOwnerThread(null);
    }
    setState(c);
    return free;
    }
   
    /**
     * The synchronization state.
     */
    private volatile int state;

    /**
     * Returns the current value of synchronization state.
     * This operation has memory semantics of a {@code volatile} read.
     * @return current state value
     */
    protected final int getState() {
        return state;
    }
    
    /**
     * Sets the value of synchronization state.
     * This operation has memory semantics of a {@code volatile} write.
     * @param newState the new state value
     */
    protected final void setState(int newState) {
        state = newState;
    }
}
```

`AbstractQueuedSynchronizer`：

```java
public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer
    implements java.io.Serializable {
        /**
         * Releases in exclusive mode.  Implemented by unblocking one or
         * more threads if {@link #tryRelease} returns true.
         * This method can be used to implement method {@link Lock#unlock}.
         *
         * @param arg the release argument.  This value is conveyed to
         *        {@link #tryRelease} but is otherwise uninterpreted and
         *        can represent anything you like.
         * @return the value returned from {@link #tryRelease}
         */
        public final boolean release(int arg) {
            if (tryRelease(arg)) {
                Node h = head;
                if (h != null && h.waitStatus != 0)
                    unparkSuccessor(h);
                return true;
            }
            return false;
        }
        // aqs父类方法，此处具体实现要看ReentrantLock的tryRelease如何实现
        protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
        }
    
    /**
     * Wakes up node's successor, if one exists.
     *
     * @param node the node
     */
    private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }
    
    /**
     * CAS waitStatus field of a node.
     */
    private static final boolean compareAndSetWaitStatus(Node node,
                                                         int expect,
                                                         int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset,
                                        expect, update);
    }
}
```



##  12. 读写锁`ReentrantReadWriteLock` | 邮戳锁`StampedLock`

###  12.1 读写锁`ReentrantReadWriteLock`

#####  12.1.1 读写锁简介

**读写锁特点**：一个资源能够被多个读线程访问，或者被一个写线程访问，但是不能同时存在读写线程。写的时候不可以读，读的时候不能写，读写互斥，读读共享。一般情况下，读写锁的性能都会比排它锁好，因为大多数场景读是多于写的。在读多于写的情况下，读写锁能够提供比排它锁更好的并发性和吞吐量

**读写锁`ReentrantReadWriteLock`**：`ReentrantReadWriteLock`是`Java`中的一种锁类型，它支持读写分离锁定，允许多个线程同时读取共享资源，但只允许一个线程写入共享资源。它是通过提供读锁和写锁来实现这种功能的。在使用`ReentrantReadWriteLock`时，多个线程可以同时获取读锁，只有写入操作时才需要获取写锁。这种方式可以提高并发性能，因为读取操作通常不会引起数据竞争问题，而写入操作可能引起数据不一致性，因此需要互斥访问。`ReentrantReadWriteLock`允许在读取或者写入时进行锁定和解锁。当一个线程持有写锁时，其他线程无法获取读锁或者写锁，而当一个线程持有读锁时，其他线程仍可以获取读锁，但无法获取写锁。这种特性使得`ReentrantReadWriteLock`在适当的场景下可以提供更好的并发控制。`ReentrantReadWriteLock`只允许读读共存，而读写和写写依然是互斥的，大多实际场景是读读线程间并不存在互斥关系，只有读写线程或写写线程间的操作需要互斥的。因此引入`ReentrantReadWriteLock`。一个`ReentrantReadWriteLock`同时只能存在一个写锁但是可以存在多个读锁，但不能同时存在写锁和读锁。也即一个资源可以被多个读操作访问―或一个写操作访问，但两者不能同时进行，读没有完成的时候其它线程无法获得写锁。只有在读多写少情景之下，读写锁才具有较高的性能体现

**`code`演示**：使用排它锁`ReentrantLock`对资源的读写操作分别进行加锁，此时任何一个写（读）线程在写（读）的时候不会被其他读写线程干扰，也就是只有写完或者是读完之后，其他线程才会进行读或者写，读多写少情景之下效率较低

```java
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class ReentrantLockResource //资源类，模拟一个简单的缓存
{
    Map<String,String> map = new HashMap<>();
    //=====ReentrantLock 等价于 =====synchronized，之前讲解过
    Lock lock = new ReentrantLock();
    //=====ReentrantReadWriteLock 一体两面，读写互斥，读读共享

    // 写锁：对公共资源map进行写更改时，需要加写锁
    public void write(String key ,String value)
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在写入");
            map.put(key,value);
            //暂停毫秒
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成写入");
        }finally {
            lock.unlock();
        }
    }


    // 读锁：对公共资源map进行读取时，需要加读锁
    public void read(String key)
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取");
            String result = map.get(key);
            //暂停200毫秒
//            //try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
//            try { TimeUnit.MILLISECONDS.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成读取"+"\t"+result);
        }finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {

    public static void main(String[] args) {
        testReentrantLock();
    }

    // 演示利用ReentrantLock读读和写进行加锁
    private static void testReentrantLock(){
        ReentrantLockResource reentrantLockResource = new ReentrantLockResource();

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                reentrantLockResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                reentrantLockResource.read(finalI +"");
            },String.valueOf(i)).start();
        }
//程序输出：任何一个写（读）线程在写（读）的时候不会被其他读写线程干扰，也就是只有写完或者是读完之后，其他线程才会进行读或者写
//        1	正在写入
//        1	完成写入
//        2	正在写入
//        2	完成写入
//        3	正在写入
//        3	完成写入
//        9	正在写入
//        9	完成写入
//        5	正在写入
//        5	完成写入
//        1	正在读取
//        1	完成读取	1
//        7	正在写入
//        7	完成写入
//        8	正在写入
//        8	完成写入
//        4	正在写入
//        4	完成写入
//        8	正在读取
//        8	完成读取	8
//        2	正在读取
//        2	完成读取	2
//        6	正在写入
//        6	完成写入
//        3	正在读取
//        3	完成读取	3
//        4	正在读取
//        4	完成读取	4
//        5	正在读取
//        5	完成读取	5
//        6	正在读取
//        6	完成读取	6
//        7	正在读取
//        7	完成读取	7
//        10	正在写入
//        10	完成写入
//        9	正在读取
//        9	完成读取	9
//        10	正在读取
//        10	完成读取	10
    }
}
```

**`ReentrantReadWriteLock`读写锁演示**：1.写线程和其他读写线程都互斥，读线程之间不会互斥    2.读锁没有完成之前，无法获得写锁（防止脏读）3.在读多于写的情况下，读写锁能够提供比排它锁更好的并发性和吞吐量

```java
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
// 演示读写锁
class RWLockResource //资源类，模拟一个简单的缓存
{
    Map<String,String> map = new HashMap<>();
    //=====ReentrantLock 等价于 =====synchronized，之前讲解过
    Lock lock = new ReentrantLock();
    //=====ReentrantReadWriteLock 一体两面，读写互斥，读读共享
    ReadWriteLock rwLock = new ReentrantReadWriteLock();


    // 写锁：对公共资源map进行写更改时，需要加写锁
    public void write(String key ,String value)
    {
        rwLock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在写入");
            map.put(key,value);
            //暂停毫秒
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成写入");
        }finally {
            rwLock.writeLock().unlock();
        }
    }


    // 读锁：对公共资源map进行读取时，需要加读锁
    public void read(String key)
    {
        rwLock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取");
            String result = map.get(key);
            //暂停200毫秒
            //try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

            //暂停2000毫秒,演示读锁没有完成之前，写锁无法获得
            try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成读取"+"\t"+result);
        }finally {
            rwLock.readLock().unlock();
        }
    }
}


public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        testReentrantReadWriteLock();
    }

    private static void testReentrantReadWriteLock(){
        RWLockResource RWLockResource = new RWLockResource();

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                RWLockResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                RWLockResource.read(finalI +"");
            },String.valueOf(i)).start();
        }

        // 暂停几秒钟线程
        // 暂停2000毫秒,演示“读锁没有完成之前，写锁无法获得”
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        for (int i = 1; i <=3; i++) {
            int finalI = i;
            new Thread(() -> {
                RWLockResource.write(finalI +"", finalI +"");
            },"新写锁线程->"+String.valueOf(i)).start();
        }
//程序输出：1.写线程和其他读写线程都互斥，读线程之间不会互斥    2.读锁没有完成之前，写锁无法获得（防止脏读）
//        2 正在写入
//        2 完成写入
//        3 正在写入
//        3 完成写入
//        1 正在写入
//        1 完成写入
//        8 正在写入
//        8 完成写入
//        10    正在写入
//        10    完成写入
//        6 正在写入
//        6 完成写入
//        7 正在写入
//        7 完成写入
//        4 正在写入
//        4 完成写入
//        9 正在写入
//        9 完成写入
//        5 正在写入
//        5 完成写入
//        1 正在读取
//        2 正在读取
//        3 正在读取
//        4 正在读取
//        5 正在读取
//        6 正在读取
//        7 正在读取
//        8 正在读取
//        10    正在读取
//        9 正在读取
//        4 完成读取   4
//        1 完成读取   1
//        2 完成读取   2
//        10    完成读取   10
//        6 完成读取   6
//        5 完成读取   5
//        7 完成读取   7
//        9 完成读取   9
//        3 完成读取   3
//        8 完成读取   8
//        新写锁线程->1  正在写入
//        新写锁线程->1  完成写入
//        新写锁线程->2  正在写入
//        新写锁线程->2  完成写入
//        新写锁线程->3  正在写入
//        新写锁线程->3  完成写入
    }
    
}
```

#####  12.1.2  锁降级

**锁降级**：锁降级指的是写锁降级成为读锁。如果当前线程拥有写锁，然后将其释放，最后再获取读锁，这种分段完成的过程不能称之为锁降级。锁降级是指把持住（当前拥有的）写锁，再获取到读锁，随后释放（先前拥有的）写锁的过程。`ReentrantReadWriteLock`是Java中的一个锁实现，它允许多个线程在没有写入操作的情况下同时访问共享资源，但在进行写入操作时需要独占访问。锁降级是指将持有的写锁降级为读锁，以允许其他线程同时访问资源，而不会释放整个写锁。要实现锁降级，可以按照以下步骤进行：1.首先，获取写锁，确保当前线程是唯一持有锁的线程。2.在持有写锁的情况下，获取读锁。3.释放写锁，但保留读锁。

**`GPT`补充**：**锁降级**是指将一个写锁降级为读锁，这样可以在保持数据一致性的同时提高并发性能。在使用`ReentrantReadWriteLock`时，通常会先获取写锁，然后再获取读锁。这样做的目的是为了在写操作期间阻塞其他线程的读操作，以保持数据的一致性。但是在某些情况下，当写操作完成后，可以将写锁降级为读锁，允许其他线程同时进行读操作。锁降级的过程如下：首先，获取写锁。通过调用`writeLock()`方法获取写锁。然后，执行需要修改数据的操作。接下来，通过调用`readLock()`方法获取读锁。由于`ReentrantReadWriteLock`是可重入锁，所以在持有写锁的情况下，可以再次获取读锁。最后，释放写锁。通过调用`unlock()`方法释放写锁。通过锁降级，可以在保持数据的一致性的同时，提高并发性能。因为在锁降级后，其他线程可以同时进行读操作，而不需要等待写锁的释放

**读写锁`ReentrantReadWriteLock`特点**：支持非公平（默认）和公平的锁获取方式，吞吐量还是非公平优于公平。`ReentrantReadWriteLock`具有可重入性，以读写线程为例，读线程在获取了读锁之后，能够再次获取读锁。而写线程在获取了写锁之后能够再次获取写锁，同时也可以获取读锁。`ReentrantReadWriteLock`存在锁降级的特性，遵循获取写锁、获取读锁再释放写锁的次序，写锁能够降级成为读锁

**锁降级总结**：如果有线程在读，那么写线程是无法获取写锁的，是悲观锁的策略。如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写

**锁降级`code`演示**：

```java
/**
 * 锁降级：遵循获取写锁→再获取读锁→再释放写锁的次序，写锁能够降级成为读锁
 * 总结：如果有线程在读，那么写线程是无法获取写锁的，是悲观锁的策略
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁
 * 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
//        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
//        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
//        //本例，only one 同一个线程
//        readLock.lock();
//        System.out.println("----读取");
//        readLock.unlock();
//
//        writeLock.lock();
//        System.out.println("----写入");
//        writeLock.unlock();
        test3();
    }


    // 正常的读锁、写锁用法
    private static void test1() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //正常 A B两个线程
        // A
        readLock.lock();
        System.out.println("----读取");
        readLock.unlock();
        // B
        writeLock.lock();
        System.out.println("----写入");
        writeLock.unlock();
//程序输出：
//        ----读取
//        ----写入
    }


    // 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
    // 同一个线程写后可以立刻读
    // 锁降级：获取写锁→ 再获取读锁 → 再释放写锁的次序，写锁能够降级成为读锁
    private static void test2() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //本例，only one 同一个线程
        writeLock.lock();
        System.out.println("----写入");

        readLock.lock();
        System.out.println("----读取");

        writeLock.unlock();
        readLock.unlock();
//程序输出：
//        ----写入
//        ----读取
    }

    // 读锁不能变成写锁
    // 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
    private static void test3() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //本例，only one 同一个线程
        readLock.lock();
        System.out.println("----读取");

        writeLock.lock();
        System.out.println("----写入");

        writeLock.unlock();
        readLock.unlock();
//程序输出：
//        ----读取
    }
}
```

#####  12.1.3 `ReentrantReadWriteLock`官网使用演示

**`ReentrantReadWriteLock`官网说明**：`https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html`

**`ReentrantReadWriteLock`官网使用演示**：Sample usages.Here is a code sketch showing how to perform lock downgrading after updating a cache(exception handling is particulrly tricky when handling multiple locks in a non-nested fashion)

```java
class CachedData {
   Object data;
   volatile boolean cacheValid;
   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

   void processCachedData() {
     rwl.readLock().lock();
     if (!cacheValid) {
       // Must release read lock before acquiring write lock
       rwl.readLock().unlock();
       rwl.writeLock().lock();
       try {
         // Recheck state because another thread might have
         // acquired write lock and changed state before we did.
         if (!cacheValid) {
           data = ...
           cacheValid = true;
         }
         // Downgrade by acquiring read lock before releasing write lock
         rwl.readLock().lock();
       } finally {
         rwl.writeLock().unlock(); // Unlock write, still hold read
       }
     }

     try {
       use(data);
     } finally {
       rwl.readLock().unlock();
     }
   }
 }
```

**官网`code`演示解读**：

1.代码中声明了一个`volatile`类型的`cacheValid`变量，保证其可见性

2.首先获取读锁，如果`cache`不可用，则释放读锁。获取写锁，在更改数据之前，再检查一次`cacheValid`的值，然后修改数据，将`cacheValid`置为`true`，然后在释放写锁前立刻抢夺获取读锁。此时，`cache`中数据可用，处理`cache`中数据，最后释放读锁。这个过程就是一个完整的锁降级的过程，目的是保证数据可见性

3.总结：一句话，同一个线程自己持有写锁时再去拿读锁，其本质相当于重入

**锁降级规则**：`ReentrantWriteReadLock`支持锁降级，遵循按照获取写锁，获取读锁再释放写锁的次序，写锁能够降级成为读锁，不支持锁升级

**上述锁降级规则的合理性**：

- 如果违背锁降级的锁降级规则：如果当前的线程C在修改完`cache`中的数据后，没有获取读锁而是直接释放了写锁，那么假设此时另一个线程D获取了写锁并修改了数据，那么C线程无法感知到数据已被修改，则数据出现错误
- 如果遵循锁降级的步骤：线程C在释放写锁之前获取读锁，那么线程D在获取写锁时将被阻塞，直到线程C完成数据处理过程,释放读锁。这样可以保证返回的数据是这次更新的数据，该机制是专门为了缓存设计的

###  13.5  邮戳锁`StampedLock`

**读写锁`ReentrantReadWriteLock`的不足**：写锁和读锁是互斥的(这里的互斥是指线程间的互斥，当前线程可以获取到写锁又获取到读锁，但是获取到了读锁不能继续获取写锁)，这是因为读写锁要保持写操作的可见性。因为，如果允许读锁在被获取的情况下对写锁的获取，那么正在运行的其他读线程无法感知到当前写线程的操作。因此，分析读写锁`ReentrantReadWriteLock`，会发现它有个潜在的问题：读锁结束，写锁有望。写锁独占，读写全堵。如果有线程正在读，写线程需要等待读线程释放锁后才能获取写锁，见前面code演示`LockDownGradingDemo`。即`ReentrantReadWriteLock`读的过程中不允许写，只有等待线程都释放了读锁，当前线程才能获取写锁，也就是写入必须等待，这是一种悲观的读锁

**读写锁`ReentrantReadWriteLock`会导致锁饥饿问题**：`ReentrantReadWriteLock`实现了读写分离，但是当读操作比较多的时候，想要获取写锁就变得比较困难了，假如当前1000个线程，999个读，1个写，有可能999个读取线程长时间抢到了锁，那1个写线程就悲剧了。因为当前有可能会一直存在读锁，而无法获得写锁，根本没机会写。`ReentrantReadWriteLock`读写锁中读没有完成的时候写锁无法获得锁，必须要等着读锁读完后才有机会写，容易导致锁饥饿问题

**邮戳锁`StampedLock`**：邮戳锁`StampedLock`是`JDK1.8`中新增的一个读写锁，是对`JDK1.5`中的读写锁`ReentrantReadWriteLock`的优化。邮戳锁`StampedLock`读的过程中也允许获取写锁介入，这样就可能会导致读的数据不一致。所以，需要额外的方法来判断读的过程中是否有写入，这是一种乐观的读锁。显然乐观锁的并发效率更高，但一旦有小概率的写入导致读取的数据不一致，在实际使用时需要进行额外的校验。`ReentrantReadWriteLock`的读被占用的时候，其他线程尝试获取写锁的时候会被阻塞。但是，`StampedLock`采取乐观获取锁后，其他线程尝试获取写锁时不会被阻塞，这其实是对读锁的优化，所以，在获取乐观读锁后，还需要对结果进行校验。一句话：对短的只读代码段，使用乐观模式通常可以减少争用并提高吞吐量

**`StampedLock`的特点**：`StampedLock`中所有获取锁的方法，都返回一个邮戳（`Stamp`），`Stamp`为0表示获取失败，其余都表示成功。`StampedLock`中所有释放锁的方法，都需要一个邮戳(`Stamp`)，这个`Stamp`必须是和成功获取锁时得到的`Stamp`一致。`StampedLock`是不可重入的，危险(如果一个线程已经持有了写锁，再去获取写锁的话就会造成死锁)

**`StampedLock`的三种访问模式**：

- `Reading`(读模式悲观)：功能和`ReentrantReadWriteLock`的读锁类似
- `Writing`(写模式)：功能和`ReentrantRedWriteLock`的写锁类似
- `Optimistic read`(乐观读模式)：无锁机制，类似于数据库中的乐观锁，支持读写并发，很乐观认为读取时没人修改，假如被修改再实现升级为悲观读模式

**`Optimistic read`(乐观读模式)中两个重要方法**：

```java
    /**
     * Returns a stamp that can later be validated, or zero
     * if exclusively locked.
     *
     * @return a stamp, or zero if exclusively locked
     */
    public long tryOptimisticRead() {
        long s;
        return (((s = state) & WBIT) == 0L) ? (s & SBITS) : 0L;
    }
    /**
     * Returns true if the lock has not been exclusively acquired
     * since issuance of the given stamp. Always returns false if the
     * stamp is zero. Always returns true if the stamp represents a
     * currently held lock. Invoking this method with a value not
     * obtained from {@link #tryOptimisticRead} or a locking method
     * for this lock has no defined effect or result.
     *
     * @param stamp a stamp
     * @return {@code true} if the lock has not been exclusively acquired
     * since issuance of the given stamp; else false
     */
    public boolean validate(long stamp) {
        U.loadFence();
        return (stamp & SBITS) == (state & SBITS);
    }
```

**乐观读模式`code`演示**：读的过程中也允许获取写锁介入

```java
/**
 * StampedLock = ReentrantReadWriteLock + 读的过程中也允许获取写锁介入
 */
public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write()
    {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName()+"\t"+"写线程准备修改");
        try
        {
            number = number + 13;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"写线程结束修改");
    }

    //悲观读，读没有完成时候写锁无法获得锁
    public void read()
    {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName()+"\t"+" come in readlock code block，4 seconds continue...");
        for (int i = 0; i < 4; i++) {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+" 正在读取中......");
        }

        try
        {
            int result = number;
            System.out.println(Thread.currentThread().getName()+"\t"+" 获得成员变量值result："+result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        }finally {
            stampedLock.unlockRead(stamp);
        }
    }

    //乐观读，读的过程中也允许获取写锁介入
    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        //故意间隔4秒钟，很乐观认为读取中没有其它线程修改过number值，具体靠判断
        System.out.println("4秒前stampedLock.validate方法值(true无修改，false有修改)"+"\t"+stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取... "+i+" 秒" +
                    "后stampedLock.validate方法值(true无修改，false有修改)"+"\t"+stampedLock.validate(stamp));
        }
        if(!stampedLock.validate(stamp))
        {
            System.out.println("有人修改过------有写操作");
            stamp = stampedLock.readLock();
            try
            {
                System.out.println("从乐观读 升级为 悲观读");
                result = number;
                System.out.println("重新悲观读后result："+result);
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t"+" finally value: "+result);
    }


    public static void main(String[] args) {
        testOptimisticRead();
    }



    // 1.Reading(读模式悲观）:功能和ReentrantReadWriteLock的读锁类似
    // 2.Writing(写模式）:功能和ReentrantRedWriteLock的写锁类似
    private static void testReadingWritingMode(){
        StampedLockDemo resource = new StampedLockDemo();
        // 传统版
        new Thread(() -> {
            resource.read();
        },"readThread").start();
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
            resource.write();
        },"writeThread").start();
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName()+"\t"+"number:" +number);
//程序输出：
//        readThread     come in readlock code block，4 seconds continue...
//        readThread     正在读取中......
//        writeThread   ----come in
//        readThread     正在读取中......
//        readThread     正在读取中......
//        readThread     正在读取中......
//        readThread     获得成员变量值result：37
//        写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥
//        writeThread   写线程准备修改
//        writeThread   写线程结束修改
//        main  number:50
    }


    // 3.Optimistic readina(乐观读模式):无锁机制，类似于数据库中的乐观锁,
    // 支持读写并发，很乐观认为读取时没人修改，假如被修改再实现升级为悲观读模式
    private static void testOptimisticRead(){
        StampedLockDemo resource = new StampedLockDemo();
        new Thread(() -> {
            resource.tryOptimisticRead();
        },"readThread").start();

        // 暂停2秒钟线程,读过程可以写介入，演示
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

//        //暂停6秒钟线程，读过程不会介入，演示
//        try { TimeUnit.SECONDS.sleep(6); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
            resource.write();
        },"writeThread").start();
        System.out.println(Thread.currentThread().getName()+"\t"+"number:" +number);
//程序输出：
//        4秒前stampedLock.validate方法值(true无修改，false有修改)  true
//        readThread    正在读取... 0 秒后stampedLock.validate方法值(true无修改，false有修改)  true
//        main  number:37
//        writeThread   ----come in
//        writeThread   写线程准备修改
//        writeThread   写线程结束修改
//        readThread    正在读取... 1 秒后stampedLock.validate方法值(true无修改，false有修改)  false
//        readThread    正在读取... 2 秒后stampedLock.validate方法值(true无修改，false有修改)  false
//        readThread    正在读取... 3 秒后stampedLock.validate方法值(true无修改，false有修改)  false
//        有人修改过------有写操作
//        从乐观读 升级为 悲观读
//        重新悲观读后result：50
//        readThread     finally value: 50
    }

}
```

**`StampedLock`锁的缺点和注意事项**：`StampedLock`不支持可重入，没有`Re`开头。`StampedLock`的悲观读锁和写锁都不支持条件变量(`Condition`），这个也需要注意。使用`StampedLock`一定不要调用中断操作，即不要调用`interrupt()`方法








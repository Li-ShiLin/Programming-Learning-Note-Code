<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.`Class`文件结构](#1class%E6%96%87%E4%BB%B6%E7%BB%93%E6%9E%84)
  - [1.1 概述](#11-%E6%A6%82%E8%BF%B0)
      - [1.1.1 字节码文件的跨平台性](#111-%E5%AD%97%E8%8A%82%E7%A0%81%E6%96%87%E4%BB%B6%E7%9A%84%E8%B7%A8%E5%B9%B3%E5%8F%B0%E6%80%A7)
      - [1.1.2 `Java`的前端编译器](#112-java%E7%9A%84%E5%89%8D%E7%AB%AF%E7%BC%96%E8%AF%91%E5%99%A8)
      - [1.1.3 透过字节码指令看代码细节](#113-%E9%80%8F%E8%BF%87%E5%AD%97%E8%8A%82%E7%A0%81%E6%8C%87%E4%BB%A4%E7%9C%8B%E4%BB%A3%E7%A0%81%E7%BB%86%E8%8A%82)
  - [1.2 虚拟机的基石-Class文件](#12-%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%9A%84%E5%9F%BA%E7%9F%B3-class%E6%96%87%E4%BB%B6)
  - [1.3 `Class`文件结构](#13-class%E6%96%87%E4%BB%B6%E7%BB%93%E6%9E%84)
      - [1.3.1 魔数-Class文件的标志](#131-%E9%AD%94%E6%95%B0-class%E6%96%87%E4%BB%B6%E7%9A%84%E6%A0%87%E5%BF%97)
      - [1.3.2 Class文件版本号](#132-class%E6%96%87%E4%BB%B6%E7%89%88%E6%9C%AC%E5%8F%B7)
      - [1.3.3 常量池—存放所有常量](#133-%E5%B8%B8%E9%87%8F%E6%B1%A0%E5%AD%98%E6%94%BE%E6%89%80%E6%9C%89%E5%B8%B8%E9%87%8F)
      - [1.3.4 访问标识](#134-%E8%AE%BF%E9%97%AE%E6%A0%87%E8%AF%86)
      - [1.3.5 类索引、父类索引、接口索引集合](#135-%E7%B1%BB%E7%B4%A2%E5%BC%95%E7%88%B6%E7%B1%BB%E7%B4%A2%E5%BC%95%E6%8E%A5%E5%8F%A3%E7%B4%A2%E5%BC%95%E9%9B%86%E5%90%88)
      - [1.3.6 字段表集合](#136-%E5%AD%97%E6%AE%B5%E8%A1%A8%E9%9B%86%E5%90%88)
      - [1.3.7 方法表集合](#137-%E6%96%B9%E6%B3%95%E8%A1%A8%E9%9B%86%E5%90%88)
      - [1.3.8 属性表集合](#138-%E5%B1%9E%E6%80%A7%E8%A1%A8%E9%9B%86%E5%90%88)
      - [1.3.9 小结](#139-%E5%B0%8F%E7%BB%93)
  - [1.4 使用`javap`指令解析Class文件](#14-%E4%BD%BF%E7%94%A8javap%E6%8C%87%E4%BB%A4%E8%A7%A3%E6%9E%90class%E6%96%87%E4%BB%B6)
      - [1.4.1 解析字节码的作用](#141-%E8%A7%A3%E6%9E%90%E5%AD%97%E8%8A%82%E7%A0%81%E7%9A%84%E4%BD%9C%E7%94%A8)
      - [1.4.2 `javac -g`操作](#142-javac--g%E6%93%8D%E4%BD%9C)
      - [1.4.3 `javap`的用法](#143-javap%E7%9A%84%E7%94%A8%E6%B3%95)
      - [1.4.4 总结](#144-%E6%80%BB%E7%BB%93)
- [2.字节码指令集与解析举例](#2%E5%AD%97%E8%8A%82%E7%A0%81%E6%8C%87%E4%BB%A4%E9%9B%86%E4%B8%8E%E8%A7%A3%E6%9E%90%E4%B8%BE%E4%BE%8B)
  - [2.1 字节码指令集概述](#21-%E5%AD%97%E8%8A%82%E7%A0%81%E6%8C%87%E4%BB%A4%E9%9B%86%E6%A6%82%E8%BF%B0)
      - [2.1.1 字节码指令集](#211-%E5%AD%97%E8%8A%82%E7%A0%81%E6%8C%87%E4%BB%A4%E9%9B%86)
      - [2.1.2 执行模型](#212-%E6%89%A7%E8%A1%8C%E6%A8%A1%E5%9E%8B)
      - [2.1.3 字节码与数据类型](#213-%E5%AD%97%E8%8A%82%E7%A0%81%E4%B8%8E%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B)
      - [2.1.4 指令分类](#214-%E6%8C%87%E4%BB%A4%E5%88%86%E7%B1%BB)
  - [2.2 加载与存储指令](#22-%E5%8A%A0%E8%BD%BD%E4%B8%8E%E5%AD%98%E5%82%A8%E6%8C%87%E4%BB%A4)
      - [2.2.1 加载与存储指令简介](#221-%E5%8A%A0%E8%BD%BD%E4%B8%8E%E5%AD%98%E5%82%A8%E6%8C%87%E4%BB%A4%E7%AE%80%E4%BB%8B)
      - [2.2.2 再谈操作数栈与局部变量表](#222-%E5%86%8D%E8%B0%88%E6%93%8D%E4%BD%9C%E6%95%B0%E6%A0%88%E4%B8%8E%E5%B1%80%E9%83%A8%E5%8F%98%E9%87%8F%E8%A1%A8)
      - [2.2.3 局部变量压栈指令](#223-%E5%B1%80%E9%83%A8%E5%8F%98%E9%87%8F%E5%8E%8B%E6%A0%88%E6%8C%87%E4%BB%A4)
      - [2.2.4 常量入栈指令](#224-%E5%B8%B8%E9%87%8F%E5%85%A5%E6%A0%88%E6%8C%87%E4%BB%A4)
      - [2.2.5 出栈装入局部变量表指令](#225-%E5%87%BA%E6%A0%88%E8%A3%85%E5%85%A5%E5%B1%80%E9%83%A8%E5%8F%98%E9%87%8F%E8%A1%A8%E6%8C%87%E4%BB%A4)
  - [2.3 算术指令](#23-%E7%AE%97%E6%9C%AF%E6%8C%87%E4%BB%A4)
  - [2.4 类型转换指令](#24-%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2%E6%8C%87%E4%BB%A4)
      - [2.4.1 宽化类型转换(Widening Numeric Conversions)](#241-%E5%AE%BD%E5%8C%96%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2widening-numeric-conversions)
      - [2.4.2 窄化类型转换(Narrowing Numeric Conversion)](#242-%E7%AA%84%E5%8C%96%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2narrowing-numeric-conversion)
  - [2.5 对象的创建与访问指令](#25-%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9B%E5%BB%BA%E4%B8%8E%E8%AE%BF%E9%97%AE%E6%8C%87%E4%BB%A4)
      - [2.5.1 创建指令](#251-%E5%88%9B%E5%BB%BA%E6%8C%87%E4%BB%A4)
      - [2.5.2 字段访问指令](#252-%E5%AD%97%E6%AE%B5%E8%AE%BF%E9%97%AE%E6%8C%87%E4%BB%A4)
      - [2.5.3 数组操作指令](#253-%E6%95%B0%E7%BB%84%E6%93%8D%E4%BD%9C%E6%8C%87%E4%BB%A4)
      - [2.5.4 类型检查指令](#254-%E7%B1%BB%E5%9E%8B%E6%A3%80%E6%9F%A5%E6%8C%87%E4%BB%A4)
  - [2.6 方法调用与返回指令](#26-%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E4%B8%8E%E8%BF%94%E5%9B%9E%E6%8C%87%E4%BB%A4)
      - [2.6.1 方法调用指令](#261-%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8%E6%8C%87%E4%BB%A4)
      - [2.6.2 方法返回指令](#262-%E6%96%B9%E6%B3%95%E8%BF%94%E5%9B%9E%E6%8C%87%E4%BB%A4)
  - [2.7 操作数栈管理指令](#27-%E6%93%8D%E4%BD%9C%E6%95%B0%E6%A0%88%E7%AE%A1%E7%90%86%E6%8C%87%E4%BB%A4)
  - [2.8 控制转移指令](#28-%E6%8E%A7%E5%88%B6%E8%BD%AC%E7%A7%BB%E6%8C%87%E4%BB%A4)
      - [2.8.1 比较指令](#281-%E6%AF%94%E8%BE%83%E6%8C%87%E4%BB%A4)
      - [2.8.2 条件跳转指令](#282-%E6%9D%A1%E4%BB%B6%E8%B7%B3%E8%BD%AC%E6%8C%87%E4%BB%A4)
      - [2.8.3 比较条件跳转指令](#283-%E6%AF%94%E8%BE%83%E6%9D%A1%E4%BB%B6%E8%B7%B3%E8%BD%AC%E6%8C%87%E4%BB%A4)
      - [2.8.4 多条件分支跳转指令](#284-%E5%A4%9A%E6%9D%A1%E4%BB%B6%E5%88%86%E6%94%AF%E8%B7%B3%E8%BD%AC%E6%8C%87%E4%BB%A4)
      - [2.8.5 无条件跳转指令](#285-%E6%97%A0%E6%9D%A1%E4%BB%B6%E8%B7%B3%E8%BD%AC%E6%8C%87%E4%BB%A4)
  - [2.9 异常处理指令](#29-%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E6%8C%87%E4%BB%A4)
      - [2.9.1 抛出异常指令](#291-%E6%8A%9B%E5%87%BA%E5%BC%82%E5%B8%B8%E6%8C%87%E4%BB%A4)
      - [2.9.2 异常处理与异常表](#292-%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E4%B8%8E%E5%BC%82%E5%B8%B8%E8%A1%A8)
  - [2.10 同步控制指令](#210-%E5%90%8C%E6%AD%A5%E6%8E%A7%E5%88%B6%E6%8C%87%E4%BB%A4)
      - [2.10.1 方法级的同步](#2101-%E6%96%B9%E6%B3%95%E7%BA%A7%E7%9A%84%E5%90%8C%E6%AD%A5)
      - [2.10.2 方法内指定指令序列的同步](#2102-%E6%96%B9%E6%B3%95%E5%86%85%E6%8C%87%E5%AE%9A%E6%8C%87%E4%BB%A4%E5%BA%8F%E5%88%97%E7%9A%84%E5%90%8C%E6%AD%A5)
- [3.类的加载过程(类的生命周期)详解](#3%E7%B1%BB%E7%9A%84%E5%8A%A0%E8%BD%BD%E8%BF%87%E7%A8%8B%E7%B1%BB%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E8%AF%A6%E8%A7%A3)
  - [3.1 过程一：Loading(加载)阶段](#31-%E8%BF%87%E7%A8%8B%E4%B8%80loading%E5%8A%A0%E8%BD%BD%E9%98%B6%E6%AE%B5)
      - [3.1.1 加载阶段的3个操作](#311-%E5%8A%A0%E8%BD%BD%E9%98%B6%E6%AE%B5%E7%9A%843%E4%B8%AA%E6%93%8D%E4%BD%9C)
      - [3.1.2 二进制流的获取方式](#312-%E4%BA%8C%E8%BF%9B%E5%88%B6%E6%B5%81%E7%9A%84%E8%8E%B7%E5%8F%96%E6%96%B9%E5%BC%8F)
      - [3.1.3 类模型与Class实例的位置](#313-%E7%B1%BB%E6%A8%A1%E5%9E%8B%E4%B8%8Eclass%E5%AE%9E%E4%BE%8B%E7%9A%84%E4%BD%8D%E7%BD%AE)
      - [3.1.4 数组类的加载](#314-%E6%95%B0%E7%BB%84%E7%B1%BB%E7%9A%84%E5%8A%A0%E8%BD%BD)
  - [3.2 过程二：Linking(链接)阶段](#32-%E8%BF%87%E7%A8%8B%E4%BA%8Clinking%E9%93%BE%E6%8E%A5%E9%98%B6%E6%AE%B5)
      - [3.2.1 环节1：链接阶段之Verification(验证)](#321-%E7%8E%AF%E8%8A%821%E9%93%BE%E6%8E%A5%E9%98%B6%E6%AE%B5%E4%B9%8Bverification%E9%AA%8C%E8%AF%81)
      - [3.2.2 环节2：链接阶段之Preparation(准备)](#322-%E7%8E%AF%E8%8A%822%E9%93%BE%E6%8E%A5%E9%98%B6%E6%AE%B5%E4%B9%8Bpreparation%E5%87%86%E5%A4%87)
      - [3.2.3 环节3：链接阶段之Resolution(解析)](#323-%E7%8E%AF%E8%8A%823%E9%93%BE%E6%8E%A5%E9%98%B6%E6%AE%B5%E4%B9%8Bresolution%E8%A7%A3%E6%9E%90)
  - [3.3 过程三：Initialization(初始化)阶段](#33-%E8%BF%87%E7%A8%8B%E4%B8%89initialization%E5%88%9D%E5%A7%8B%E5%8C%96%E9%98%B6%E6%AE%B5)
      - [3.3.1 初始化阶段](#331-%E5%88%9D%E5%A7%8B%E5%8C%96%E9%98%B6%E6%AE%B5)
      - [3.3.2 static与final的搭配问题](#332-static%E4%B8%8Efinal%E7%9A%84%E6%90%AD%E9%85%8D%E9%97%AE%E9%A2%98)
      - [3.3.3 `<clinit>()`的线程安全性](#333-clinit%E7%9A%84%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E6%80%A7)
      - [3.3.4 类的初始化情况：主动使用 vs 被动使用](#334-%E7%B1%BB%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E6%83%85%E5%86%B5%E4%B8%BB%E5%8A%A8%E4%BD%BF%E7%94%A8-vs-%E8%A2%AB%E5%8A%A8%E4%BD%BF%E7%94%A8)
  - [3.4 过程四：类的Using(使用)](#34-%E8%BF%87%E7%A8%8B%E5%9B%9B%E7%B1%BB%E7%9A%84using%E4%BD%BF%E7%94%A8)
  - [3.5 过程五：类的Unloading(卸载)](#35-%E8%BF%87%E7%A8%8B%E4%BA%94%E7%B1%BB%E7%9A%84unloading%E5%8D%B8%E8%BD%BD)
- [4.再谈类的加载器](#4%E5%86%8D%E8%B0%88%E7%B1%BB%E7%9A%84%E5%8A%A0%E8%BD%BD%E5%99%A8)
  - [4.1 概述](#41-%E6%A6%82%E8%BF%B0)
  - [4.2 类的加载器分类](#42-%E7%B1%BB%E7%9A%84%E5%8A%A0%E8%BD%BD%E5%99%A8%E5%88%86%E7%B1%BB)
      - [4.2.1 启动类加载器](#421-%E5%90%AF%E5%8A%A8%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8)
      - [4.2.2 扩展类加载器](#422-%E6%89%A9%E5%B1%95%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8)
      - [4.2.3 系统类加载器](#423-%E7%B3%BB%E7%BB%9F%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8)
      - [4.2.4 用户自定义类加载器](#424-%E7%94%A8%E6%88%B7%E8%87%AA%E5%AE%9A%E4%B9%89%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8)
  - [4.3 测试不同的类加载器](#43-%E6%B5%8B%E8%AF%95%E4%B8%8D%E5%90%8C%E7%9A%84%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8)
  - [4.4 ClassLoader源码解析](#44-classloader%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)
      - [4.4.1 ClassLoader的主要方法](#441-classloader%E7%9A%84%E4%B8%BB%E8%A6%81%E6%96%B9%E6%B3%95)
      - [4.4.2 SecureClassLoader 与 URLClassLoader](#442-secureclassloader-%E4%B8%8E-urlclassloader)
      - [4.4.3 ExtClassLoader 与 AppClassLoader](#443-extclassloader-%E4%B8%8E-appclassloader)
      - [4.4.4 Class.forName() 与 lassLoader.loadClass()](#444-classforname-%E4%B8%8E-lassloaderloadclass)
  - [4.5 双亲委派模型](#45-%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%B4%BE%E6%A8%A1%E5%9E%8B)
      - [4.5.1 定义与本质](#451-%E5%AE%9A%E4%B9%89%E4%B8%8E%E6%9C%AC%E8%B4%A8)
      - [4.5.2 优势与劣势](#452-%E4%BC%98%E5%8A%BF%E4%B8%8E%E5%8A%A3%E5%8A%BF)
      - [4.5.3 破坏双亲委派机制](#453-%E7%A0%B4%E5%9D%8F%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%B4%BE%E6%9C%BA%E5%88%B6)
      - [4.5.4 热替换的实现](#454-%E7%83%AD%E6%9B%BF%E6%8D%A2%E7%9A%84%E5%AE%9E%E7%8E%B0)
  - [4.6 沙箱安全机制](#46-%E6%B2%99%E7%AE%B1%E5%AE%89%E5%85%A8%E6%9C%BA%E5%88%B6)
  - [4.7 自定义类的加载器](#47-%E8%87%AA%E5%AE%9A%E4%B9%89%E7%B1%BB%E7%9A%84%E5%8A%A0%E8%BD%BD%E5%99%A8)
  - [4.8 `Java9`新特性](#48-java9%E6%96%B0%E7%89%B9%E6%80%A7)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

字节码与类的加载篇

## 1.`Class`文件结构

### 1.1 概述

##### 1.1.1 字节码文件的跨平台性

1.Java语言：跨平台的语言(write once ,run anywhere)

- 当Java源代码成功编译成字节码后，如果想在不同的平台上面运行，则无须再次编译
- 这个优势不再那么吸引人了。Python、PHP、Per1、Ruby、Lisp等语言拥有强大的解释器，也可以实现跨平台
- 跨平台似乎已经快成为一门语言必选的特性



2.Java 虚拟机：跨语言的平台

- Java 虚拟机不和包括Java在内的任何语言绑定，它只与“class 文件”这种特定的二进制文件格式所关联
- 无论使用何种语言进行软件开发，只要能将源文件编译为正确的class文件，那么这种语言就可以Java虚拟机上执行
- 可以说，统一而强大的Class文件结构，就是Java虚拟机的基石、桥梁。所有的JVM全部遵守Java虚拟机规范，也就是说所有的JVM环境都是一样的，这样一来字节码文件可以在各种JVM上运行
- Java虚拟机官方规范：https://docs.oracle.com/javase/specs/index.html

![image-20240803190337813](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240803190337813.png)

3.想要让一个Java程序正确地运行在JVM中，Java源码就必须要被编译为符合JVM规范的字节码

- 前端编译器的主要任务就是负责将符合Java语法规范的Java代码转换为符合JVM规范的字节码文件
- javac是一种能够将Java源码编译为字节码的前端编译器
- Javac编译器在将Java源码编译为一个有效的字节码文件过程中经历了4个步骤，分别是词法解析、语法解析、语义解析以及生成字节码

![image-20240803191909988](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240803191909988.png)

Oracle的JDK软件包括两部分内容：

- 一部分是将Java源代码编译成Java虚拟机的指令集的编译器
- 另一部分是用于实现Java虚拟机的运行时环境

##### 1.1.2 `Java`的前端编译器

![image-20240803192337520](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240803192337520.png)

前端编译器 vs 后端编译器

Java源代码的编译结果是字节码，那么肯定需要有一种编译器能够将Java源码编译为字节码，承担这个重要责任的就是配置在path环境变量中的javac编译器。javac是一种能够将]ava源码编译为字节码的前端编译器

HotSpot VM并没有强制要求前端编译器只能使用javac来编译字节码，其实只要编译结果符合JVM规范都可以被JVM所识别即可。在Java的前端编译器领域，除了javac之外，还有一种被大家经常用到的前端编译器，那就是内置在Eclipse中的ECJ(Eclipse Compiler for Java)编译器。和Javac的全量式编译不同，ECJ是一种增量式编译器

- 在Eclipse中，当开发人员编写完代码后，使用“ctrl+S”快捷键时，ECJ编译器所采取的编译方案是把未编译部分的源码逐行进行编译，而非每次都全量编译。因此ECJ的编译效率会比javac更加迅速和高效，当然编译质量和javac相比大致还是一样的
- ECJ不仅是Eclipse的默认内置前端编译器，在Tomcat中同样也是使用ECJ编译器来编译jsp文件。由于ECJ编译器是采用
- ECJ不仅是Eclipse的默认内置前端编译器，在Tomcat中同样也是使用ECJ编译器来编译jsp文件。由于ECJ编译器是采用GPLv2的开源协议进行源代码公开，所以，大家可以登录eclipse官网下载ECJ编译器的源码进行二次开发
- 默认情况下，IntelliJ IDEA 使用 javac 编译器。(还可以自己设置为AspectJ编译器 ajc)

前端编译器并不会直接涉及编译优化等方面的技术，而是将这些具体优化细节移交给Hotspot的JIT编译器负责。复习：AOT(静态提前编译器，Ahead Of Time Compiler)



##### 1.1.3 透过字节码指令看代码细节

案例一：Integer

```java
public class IntegerTest {
    public static void main(String[] args) {
        Integer x = 5;
        int y = 5;
        System.out.println(x == y);//true

        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);//true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);//false
    }
}
```

查看字节码：`javap -c IntegerTest`。代码中调用了Integer.valueOf，Integer.valueOf是导致上述结果的关键，可以对Integer.valueOf的源码进行分析

```java
public class com.atguigu.java.IntegerTest {
  public com.atguigu.java.IntegerTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_5
       1: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       4: astore_1
       5: iconst_5
       6: istore_2
       7: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      10: aload_1
      11: invokevirtual #4                  // Method java/lang/Integer.intValue:()I
      14: iload_2
      15: if_icmpne     22
      18: iconst_1
      19: goto          23
      22: iconst_0
      23: invokevirtual #5                  // Method java/io/PrintStream.println:(Z)V
      26: bipush        10
      28: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      31: astore_3
      32: bipush        10
      34: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      37: astore        4
      39: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      42: aload_3
      43: aload         4
      45: if_acmpne     52
      48: iconst_1
      49: goto          53
      52: iconst_0
      53: invokevirtual #5                  // Method java/io/PrintStream.println:(Z)V
      56: sipush        128
      59: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      62: astore        5
      64: sipush        128
      67: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      70: astore        6
      72: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      75: aload         5
      77: aload         6
      79: if_acmpne     86
      82: iconst_1
      83: goto          87
      86: iconst_0
      87: invokevirtual #5                  // Method java/io/PrintStream.println:(Z)V
      90: return
}
```

案例二：String

```java
package com.atguigu.java;
public class StringTest {
    public static void main(String[] args) {
        String str = new String("hello") + new String("world");
        String str1 = "helloworld";
        System.out.println(str == str1);  //false
        String str2 = new String("helloworld");
        System.out.println(str == str2);  //false
    }
}
```

分析字节码：`javap -c StringTest`

```java
public class com.atguigu.java.StringTest {
  public com.atguigu.java.StringTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class java/lang/StringBuilder
       3: dup
       4: invokespecial #3                  // Method java/lang/StringBuilder."<init>":()V
       7: new           #4                  // class java/lang/String
      10: dup
      11: ldc           #5                  // String hello
      13: invokespecial #6                  // Method java/lang/String."<init>":(Ljava/lang/String;)V      
      16: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      19: new           #4                  // class java/lang/String
      22: dup
      23: ldc           #8                  // String world
      25: invokespecial #6                  // Method java/lang/String."<init>":(Ljava/lang/String;)V      
      28: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      31: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      34: astore_1
      35: ldc           #10                 // String helloworld
      37: astore_2
      38: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
      41: aload_1
      42: aload_2
      63: astore_3
      64: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
      67: aload_1
      68: aload_3
      69: if_acmpne     76
      72: iconst_1
      73: goto          77
      76: iconst_0
      77: invokevirtual #12                 // Method java/io/PrintStream.println:(Z)V
      80: return
}
```

案例三：从字节码的角度验证成员变量赋值的过程

```java
package com.atguigu.java;
/*
成员变量（非静态的）的赋值过程： ① 默认初始化 - ② 显式初始化 /代码块中初始化 - ③ 构造器中初始化 - ④ 有了对象之后，可以“对象.属性”或"对象.方法"
 的方式对成员变量进行赋值。
 */
class Father {
    int x = 10;

    public Father() {
        this.print();
        x = 20;
    }
    public void print() {
        System.out.println("Father.x = " + x);
    }
}

class Son extends Father {
    int x = 30;
//    float x = 30.1F;
    public Son() {
        this.print();
        x = 40;
    }
    public void print() {
        System.out.println("Son.x = " + x);
    }
}

public class SonTest {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);
    }
/*输出:
        Son.x = 0
        Son.x = 30
        20
 */
}
```

分析字节码：`javap -c SonTest `

```java
public class com.atguigu.java.SonTest {
  public com.atguigu.java.SonTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class com/atguigu/java/Son
       3: dup
       4: invokespecial #3                  // Method com/atguigu/java/Son."<init>":()V
       7: astore_1
       8: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
      11: aload_1
      12: getfield      #5                  // Field com/atguigu/java/Father.x:I
      15: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
      18: return
}
```

### 1.2 虚拟机的基石-Class文件

Class文件官方文档：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html

字节码文件：源代码经过编译器编译之后便会生成一个字节码文件，字节码是一种二进制的类文件，它的内容是`JVM`的指令，而不像C、C++经由编译器直接生成机器码

字节码指令(byte code)：Java虚拟机的指令由一个字节长度的、代表着某种特定操作含义的操作码(opcode)以及跟随其后的零至多个代表此操作所需参数的操作数(operand)所构成。虚拟机中许多指令并不包含操作数，只有一个操作码。比如：

如何解读供虚拟机解释执行的二进制字节码：

- 方式1：一个一个二进制的看。这里用到的是Notepad++，需要安装一个HEX-Editor插件，或者使用Binary Viewer
- 方式2：使用javap指令，jdk自带的反解析工具
- 方式3：使用IDEA插件，jclasslib 或 jclasslib bytecode viewer客户端工具(可视化更好)

class 类的本质：任何一个class文件都对应着唯一一个类或接口的定义信息，但反过来说，class文件实际上它并不一定以磁盘文件的形式存在。Class 文件是一组以8位字节为基础单位的二进制流

Class文件格式：class 的结构不像 XML 等描述语言，由于它没有任何分隔符号。所以在其中的数据项，无论是字节顺序还是数量，都是被严格限定的，哪个字节代表什么含义，长度是多少，先后顺序如何，都不允许改变

Class 文件格式采用一种类似于 c语言结构体的方式进行数据存储，这种结构中只有两种数据类型：无符号数和表

- 无符号数属于基本的数据类型，以 u1、u2、u4、u8 来分别代表 1 个字节、2 个字节、4 个字节和 8 个字节的无符号数
- 无符号数可以用来描述数字、索引引用、数量值或者按照 UTF-8 编码构成字符串值
- 表是由多个无符号数或者其他表作为数据项构成的复合数据类型，所有表都习惯性地以“ info”结尾。表用于描述有层次关系的复合结构的数据，整个 class 文件本质上就是一张表。 由于表没有固定长度，所以通常会在其前面加上个数说明
- 换句话说，充分理解了每一个字节码文件的细节，自己也可以反编译出Java源文件来



Class文件的结构并不是一成不变的，随着Java虚拟机的不断发展，总是不可避免地会对class文件结构做出一些调整，但是其基本结构和框架是非常稳定的

Class文件的总体结构如下：

- 魔数
- Class文件版本
- 常量池
- 访问标志
- 类索引，父类索引，接口索引集合
- 字段表集合
- 方法表集合
- 属性表集合

官方说明：A `class` file consists of a single `ClassFile` structure

```java
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

class字节码文件结构：

| 类型           | 名称                | 说明                   | 长度    | 数量                  |
| -------------- | ------------------- | ---------------------- | ------- | --------------------- |
| u4             | magic               | 魔数,识别Class文件格式 | 4个字节 | 1                     |
| u2             | minor_version       | 副版本号(小版本)       | 2个字节 | 1                     |
| u2             | major_version       | 主版本号(大版本)       | 2个字节 | 1                     |
| u2             | constant_pool_count | 常量池计数器           | 2个字节 | 1                     |
| cp_info        | constant_pool       | 常量池表               | n个字节 | constant_pool_count-1 |
| u2             | access_flags        | 访问标识               | 2个字节 | 1                     |
| u2             | this_class          | 类索引                 | 2个字节 | 1                     |
| u2             | super_class         | 父类索引               | 2个字节 | 1                     |
| u2             | interfaces_count    | 接口计数器             | 2个字节 | 1                     |
| u2             | interfaces          | 接口索引集合           | 2个字节 | interfaces_count      |
| u2             | fields_count        | 字段计数器             | 2个字节 | 1                     |
| field_info     | fields              | 字段表                 | n个字节 | fields_count          |
| u2             | methods_count       | 方法计数器             | 2个字节 | 1                     |
| method_info    | methods             | 方法表                 | n个字节 | methods_count         |
| u2             | attributes_count    | 属性计数器             | 2个字节 | 1                     |
| attribute_info | attributes          | 属性表                 | n个字节 | attributes_count      |

### 1.3 `Class`文件结构

##### 1.3.1 魔数-Class文件的标志

Magic Number(魔数)：每个 Class 文件开头的4个字节的无符号整数称为魔数(Magic Number)。它的唯一作用是确定这个文件是否为一个能被虚拟机接受的有效合法的class文件。即：魔数是class文件的标识符。魔数值固定为`0xCAFEBABE`。不会改变

如果一个class文件不以`0xCAFEBABE`开头，虚拟机在进行文件校验的时候就会直接抛出以下错误：

> Error: A JNI error has occurred, please check your installation and try again Exception in thread "main" java.lang.classformatError: Incompatible magic value 1885430635 in class file string Test

使用魔数而不是扩展名来进行识别主要是基于安全方面的考虑，因为文件扩展名可以随意地改动

##### 1.3.2 Class文件版本号

紧接着魔数的 4 个字节存储的是 class 文件的版本号。同样也是4个字节。第5个和第6个字节所代表的含义就是编译的副版本号`minol _version`，而第7个和第8个字节就是编译的主版本号`major_version`

它们共同构成了class文件的格式版本号。譬如某个 class 文件的主版本号为 M，副版本号为 m，那么这个class 文件的格式版本号就确定为 M.m

Class文件版本号和Java编译器的对应关系如下表：

| 主版本（十进制） | 副版本（十进制） | 编译器版本 |
| ---------------- | ---------------- | ---------- |
| 45               | 3                | 1.1        |
| 46               | 0                | 1.2        |
| 47               | 0                | 1.3        |
| 48               | 0                | 1.4        |
| 49               | 0                | 1.5        |
| 50               | 0                | 1.6        |
| 51               | 0                | 1.7        |
| 52               | 0                | 1.8        |
| 53               | 0                | 1.9        |
| 54               | 0                | 1.10       |
| 55               | 0                | 1.11       |

Java 的版本号是从45开始的，JDK1.1之后的每个JDK大版本发布主版本号向上加1

不同版本的Java编译器编译的class文件对应的版本是不一样的，目前，高版本的Java虚拟机可以执行由低版本编译器生成的class文件，但是低版本的Java虚拟机不能执行由高版本编译器生成的class文件。否则JVM会抛出`java.lang.UnsupportedclassVersionError`异常(向下兼容)

在实际应用中，由于开发环境和生产环境的不同，可能会导致该问题的发生。因此，需要我们在开发时，特别注意开发编译的JDK版本和生产环境中的JDK版本是否一致。虚拟机JDK版本为1.k(k>= 2)时，对应的class文件格式版本号的范围为45.0-44+k.0 (含两端)

##### 1.3.3 常量池—存放所有常量



常量池是class文件中内容最为丰富的区域之一。常量池对于class文件中的字段和方法解析也有着至关重要的作用

随着Java虚拟机的不断发展，常量池的内容也日渐丰富。可以说，常量池是整个class文件的基石

> 官方说明：Java Virtual Machine instructions do not rely on the run-time layout of classes, interfaces, class instances, or arrays. Instead, instructions refer to symbolic information in the `constant_pool` table.
>
> All `constant_pool` table entries have the following general format：

```c++
cp_info {
    u1 tag;
    u1 info[];
}
```

在版本号之后，紧跟着的是常量池的数量，以及若干个常量池表项。常量池中常量的数量是不固定的，所以在常量池的入口需要放置一项u2类型的无符号数，代表常量池容量计数值(constant pool count)。与Java中语言习惯不一样的是，这个容量计数是从1而不是6开始的

由上表可见，class文件使用了一个前置的容量计数器(constant_pool_count)加若千个连续的数据项(constant_pool)的形式来描述常量池内容。把这一系列连续常量池数据称为常量池集合。常量池表项中，用于存放编译时期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放

**常量池计数器**：constant_pool_count (常量池计数器)

- 由于常量池的数量不固定，时长时短，所以需要放置两个字节来表示常量池容量计数值。
- 常量池容量计数值(u2类型)：从1开始，表示常量池中有多少项常量。即constant_pool count=1表示常量池中有8个常量项

**`constant_pool []`(常量池)**：

- constant_pool是一种表结构，以1~ constant_pool count - 1为索引。表明了后面有多少个常量项
- 常量池主要存放两大类常量：字面量(Literal)和符号引用(Symbolic References)
- 它包含了class文件结构及其子结构中引用的所有字符串常量、类或接口名、字段名和其他常量。常量池中的每一项都具备相同的特征。第1个字节作为类型标记，用于确定该项的格式，这个字节称为tag byte (标记字节、标签字节)

常量类型和结构：

| 类型                             | 标志(或标识) | 描述                   |
| -------------------------------- | ------------ | ---------------------- |
| CONSTANT_utf8_info               | 1            | UTF-8编码的字符串      |
| CONSTANT_Integer_info            | 3            | 整型字面量             |
| CONSTANT_Float_info              | 4            | 浮点型字面量           |
| CONSTANT_Long_info               | 5            | 长整型字面量           |
| CONSTANT_Double_info             | 6            | 双精度浮点型字面量     |
| CONSTANT_Class_info              | 7            | 类或接口的符号引用     |
| CONSTANT_String_info             | 8            | 字符串类型字面量       |
| CONSTANT_Fieldref_info           | 9            | 字段的符号引用         |
| CONSTANT_Methodref_info          | 10           | 类中方法的符号引用     |
| CONSTANT_InterfaceMethodref_info | 11           | 接口中方法的符号引用   |
| CONSTANT_NameAndType_info        | 12           | 字段或方法的符号引用   |
| CONSTANT_MethodHandle_info       | 15           | 表示方法句柄           |
| CONSTANT_MethodType_info         | 16           | 标志方法类型           |
| CONSTANT_InvokeDynamic_info      | 18           | 表示一个动态方法调用点 |

字面量和符号引用：

- 在对这些常量解读前，需要搞清楚几个概念

- 全限定名：`com/atguigu/test/Demo`这个就是类的全限定名，仅仅是把包名的"."替换成"/"，为了使连续的多个全限定名之间不产生混淆，在使用时最后一般会加入一个“;”表示全限定名结束

- 简单名称：简单名称是指没有类型和参数修饰的方法或者字段名称，上面例子中的类的add()方法和num字段的简单名称分别是add和num

- 常量池主要存放两大类常量：字面量(Literal)和符号引用(Symbolic References)。如下表：

| 常量     | 具体的常量          |
| -------- | ------------------- |
| 字面量   | 文本字符串          |
|          | 声明为final的常量值 |
| 符号引用 | 类和接口的全限定名  |
|          | 字段的名称和描述符  |
|          | 方法的名称和描述符  |

类型描述符：描述符的作用是用来描述字段的数据类型、方法的参数列表(包括数量、类型以及顺序)和返回值。根据描述符规则，基本数据类型(byte、char、double、float、int、long、short、boolean)以及代表无返回值的void类型都用一个大写字符来表示，而对象类型则用字符L加对象的全限定名来表示，详见下表：

| 标志符 | 含义                                                 |
| ------ | ---------------------------------------------------- |
| B      | 基本数据类型byte                                     |
| C      | 基本数据类型char                                     |
| D      | 基本数据类型double                                   |
| F      | 基本数据类型float                                    |
| I      | 基本数据类型int                                      |
| J      | 基本数据类型long                                     |
| S      | 基本数据类型short                                    |
| Z      | 基本数据类型boolean                                  |
| V      | 代表void类型                                         |
| L      | 对象类型，比如：`Ljava/lang/Object;`                 |
| [      | 数组类型，代表一维数组。比如：`double[][][] is [[[D` |

补充说明：虚拟机在加载class文件时才会进行动态链接，也就是说，class文件中不会保存各个方法和字段的最终内存布局信息，因此，这些字段和方法的符号引用不经过转换是无法直接被虚拟机使用的。当虚拟机运行时，需要从常量池中获得对应的符号引用，再在类加载过程中的解析阶段将其替换为直接引用，并翻译到具体的内存地址中

这里说明下符号引用和直接引用的区别与关联：

- 符号引用：符号引用以一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要使用时能无歧义地定位到目标即可。符号引用与虚拟机实现的内存布局无关，引用的目标并不一定已经加载到了内存中
- 直接引用：直接引用可以是直接指向目标的指针、相对偏移量或是一个能间接定位到目标的句柄。直接引用是与虚拟机实现的内存布局相关的，同一个符号引用在不同虚拟机实例上翻译出来的直接引用一般不会相同。如果有了直接引用，那说明引用的目标必定已经存在于内存之中了

![image-20240804130709203](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240804130709203.png)

总结：

- 这14种表(或者常量项结构)的共同点是：表开始的第一位是一个u1类型的标志位(tag)，代表当前这个常量项使用的是哪种表结构，即哪种常量类型
- 在常量池列表中，`CONSTANT_Utf8_info`常量项是一种使用改进过的UTF-8编码格式来存储诸如文字字符串、类或者接口的全限定名、字段或者方法的简单名称以及描述符等常量字符串信息
- 这14种常量项结构还有一个特点是，其中13个常量项占用的字节固定，只有`CONSTANT_Utf8_info`占用字节不固定，其大小由length决定。为什么呢？因为从常量池存放的内容可知，其存放的是字面量和符号引用，最终这些内容都会是一个字符串，这些字符串的大小是在编写程序时才确定，比如定义一个类，类名可以取长取短，所以在没编译前，大小不固定，编译后，通过utf-8编码，就可以知道其长度

常量池总结：

- 常量池：可以理解为class文件之中的资源仓库，它是class文件结构中与其他项目关联最多的数据类型(后面的很多数据类型都会指向此处)，也是占用Class文件空间最大的数据项目之一
- 常量池中为什么要包含这些内容？Java代码在进行javac编译的时候，并不像C和C++那样有“连接”这一步骤，而是在虚拟机加载Class文件的时候进行动态链接。也就是说，在class文件中不会保存各个方法、字段的最终内存布局信息，因此这些字段、方法的符号引用不经过运行期转换的话无法得到真正的内存入口地址，也就无法直接被虚拟机使用。当虚拟机运行时，需要从常量池获得对应的符号引用，再在类创建时或运行时解析、翻译到具体的内存地址之中。关于类的创建和动态链接的内容，在虚拟机类加载过程时再进行详细讲解

##### 1.3.4 访问标识

访问标识（access flag、访问标志、访问标记）：

- 在常量池后，紧跟着访问标记。该标记使用两个字节表示，用于识别一些类或者接口层次的访问信息，包括：这个 class是类还是接口、是否定义为 public 类型、是否定义为 abstract 类型。如果是类的话，是否被声明为 final 等。各种访问标记如下所示：

| 标志名称      | 标志值 | 含义                       |
| ------------- | ------ | -------------------------- |
| ACC_PUBLIC    | 0x0001 | 字段是否为public           |
| ACC_PRIVATE   | 0x0002 | 字段是否为private          |
| ACC_PROTECTED | 0x0004 | 字段是否为protected        |
| ACC_STATIC    | 0x0008 | 字段是否为static           |
| ACC_FINAL     | 0x0010 | 字段是否为final            |
| ACC_VOLATILE  | 0x0040 | 字段是否为volatile         |
| ACC_TRANSTENT | 0x0080 | 字段是否为transient        |
| ACC_SYNCHETIC | 0x1000 | 字段是否为由编译器自动产生 |
| ACC_ENUM      | 0x4000 | 字段是否为enum             |

- 类的访问权限通常为 `ACC` 开头的常量
- 每一种类型的表示都是通过设置访问标记的32位中的特定位来实现的。比如，若是public final的类，则该标记为`ACC_PUBL | CACC FINAL`
- 使用`ACC_SUPER`可以让类更准确地定位到父类的方法`super.method()`，现代编译器都会设置并且使用这个标记
- 补充说明：
  - 带有`ACC_INTERFACE`标志的class文件表示的是接口而不是类，反之则表示的是类而不是接口。如果一个class文件被设置了 `ACC_INTERFACE` 标志，那么同时也得设置`ACC_ABSTRACT` 标志。同时它不能再设置 `ACC_FINAL`、`ACC_SUPER` 或 `ACC_ENUM` 标志。如果没有设置`ACC_INTERFACE`标志，那么这个`class`文件可以具有上表中除 `ACC_ANNOTATION`外的其他所有标志。当然，`ACC_FINAL`和`ACC_ABSTRACT`这类互斥的标志除外。这两个标志不得同时设置
  - `ACC_SUPER`标志用于确定类或接口里面的invokespecial指令使用的是哪一种执行语义。针对Java虚拟机指令集的编译器都应当设置这个标志。对于Java SE 8及后续版本来说，无论class文件中这个标志的实际值是什么，也不管class文件的版本号是多少，Java虚拟机都认为每个class文件均设置了`ACC_SUPER`标志。`ACC_SUPER`标志是为了向后兼容由旧]ava编译器所编译的代码而设计的。目前的`ACC_SUPER`标志在由JDK 1.0.2之前的编译器所生成的access_flags中是没有确定含义的，如果设置了该标志，那么oracle的Java虚拟机实现会将其忽略
  - `ACC_SYNTHETIC`标志意味着该类或接口是由编译器生成的，而不是由源代码生成的
  - 注解类型必须设置`ACC_ANNOTATION`标志。如果设置了 `ACC_ANNOTATION`标志， 那么也必须设置`ACC_INTERFACE`标志
  - `ACC_ENUM`标志表明该类或其父类为枚举类型

##### 1.3.5 类索引、父类索引、接口索引集合

在访问标记后，会指定该类的类别、父类类别以及实现的接口，格式如下：

| 长度 | 含义                         |
| ---- | ---------------------------- |
| u2   | this_class                   |
| u2   | super_class                  |
| u2   | interfaces_count             |
| u2   | interfaces[interfaces_count] |

这三项数据确定这个类的继承关系：

- 类索引用于确定这个类的全限定名
- 父类索引用于确定这个类的父类的全限定名。由于Java语言不允许多重继承，所以父类索引只有一个，除了`java.lang.Object`之外，所有的Java类都有父类，因此除了`java.lang.Object`外，所有Java类的父类索引都不为0
- 接口索引集合就用来描述这个类实现了哪些接口，这些被实现的接口将按 implements 语句(如果这个类本身是一个接口，则应当是 extends 语句)后的接口顺序从左到右排列在接口索引集合中

this class(类索引)：

- 2字节无符号整数，指向常量池的索引。它提供了类的全限定名，`com/atguigu/java1/Demo`。this_class的值必须是对常量池表中某项的一个有效索引值。常量池在这个索引处的成员必须为`CONSTANT_class_info`类型结构体，该结构体表示这个class文件所定义的类或接口

super_class (父类索引)

- 2字节无符号整数，指向常量池的索引。它提供了当前类的父类的全限定名。如果没有继承任何类，其默认继承的是`java/lang/Object`类。同时，由于Java不支持多继承，所以其父类只有一个
- superclass指向的父类不能是final

interfaces

- 指向常量池索引集合，它提供了一个符号引用到所有已实现的接口
- 由于一个类可以实现多个接口，因此需要以数组形式保存多个接口的索引，表示接口的每个索引也是一个指向常量池的`CONSTANT_Class`(当然这里就必须是接口，而不是类)

interfaces_count(接口计数器)

- interfaces_count项的值表示当前类或接口的直接超接口数量

`interfaces[]`(接口索引集合)

- interfaces []中每个成员的值必须是对常量池表中某项的有效索引值，它的长度为 interfaces count
- 每个成员interfaces[i]必须为 `CONSTANT_Class_info`结构，其中 `0<=i<interfaces count`
- 在 interfaces[i]中，各成员所表示的接口顺序和对应的源代码中给定的接口顺序(从左至右)一样，即 interfaces[0]对应的是源代码中最左边的接口

##### 1.3.6 字段表集合

fields：

- 用于描述接口或类中声明的变量。字段(field)包括类级变量以及实例级变量，但是不包括方法内部、代码块内部声明的局部变量(local variables)

- 字段叫什么名字、字段被定义为什么数据类型，这些都是无法固定的，只能引用常量池中的常量来描述

- 它指向常量池索引集合，它描述了每个字段的完整信息。比如字段的标识符、访问修饰符(public、private或protected)、是类变量还是实例变量(static修饰符)、是否是常量(final修饰符)等

- 注意事项：

  - 字段表集合中不会列出从父类或者实现的接口中继承而来的字段，但有可能列出原本Java代码之中不存在的字段。譬如在内部类中为了保持对外部类的访问性，会自动添加指向外部类实例的字段

  - 在Java语言中字段是无法重载的，两个字段的数据类型、修饰符不管是否相同，都必须使用不一样的名称，但是对于字节码来讲，如果两个字段的描述符不一致，那字段重名就是合法的

fields count (字段计数器)：

- fields count的值表示当前class文件fields表的成员个数。使用两个字节来表示
- fields表中每个成员都是一个field info结构，用于表示该类或接口所声明的所有类字段或者实例字段，不包括方法内部声明的变量，也不包括从父类或父接口继承的那些字段



`fields[]`(字段表)：

- fields表中的每个成员都必须是一个fields info结构的数据项，用于表示当前类或接口中某个字段的完整描述
- 一个字段的信息包括如下这些信息。这些信息中，各个修饰符都是布尔值，要么有，要么没有
  - 作用域(public、private、protected修饰符)
  - 是实例变量还是类变量(static修饰符)
  - 可变性(final)
  - 并发可见性(volatile修饰符，是否强制从主内存读写)
  - 可否序列化(transient修饰符)
  - 字段数据类型(基本数据类型、对象、数组)
  - 字段名称
  - 字段表结构

字段表作为一个表，同样有他自己的结构：

| 类型           | 名称             | 含义       | 数量             |
| -------------- | ---------------- | ---------- | ---------------- |
| u2             | access_flags     | 访问标志   | 1                |
| u2             | name_index       | 字段名索引 | 1                |
| u2             | descriptor_index | 描述符索引 | 1                |
| u2             | attributes_count | 属性计数器 | 1                |
| attribute_info | attributes       | 属性集合   | attributes_count |

1.字段表访问标识：

- 我们知道，一个字段可以被各种关键字去修饰，比如：作用域修饰符(public、private、protected)、static修饰符、final修饰符、volatile修饰符等等。因此，其可像类的访问标志那样，使用一些标志来标记字段。字段的访问标志有如下这些：

| 标志名称      | 标志值 | 含义                       |
| ------------- | ------ | -------------------------- |
| ACC_PUBLIC    | 0x0001 | 字段是否为public           |
| ACC_PRIVATE   | 0x0002 | 字段是否为private          |
| ACC_PROTECTED | 0x0004 | 字段是否为protected        |
| ACC_STATIC    | 0x0008 | 字段是否为static           |
| ACC_FINAL     | 0x0010 | 字段是否为final            |
| ACC_VOLATILE  | 0x0040 | 字段是否为volatile         |
| ACC_TRANSTENT | 0x0080 | 字段是否为transient        |
| ACC_SYNCHETIC | 0x1000 | 字段是否为由编译器自动产生 |
| ACC_ENUM      | 0x4000 | 字段是否为enum             |

2.字段名索引：根据字段名索引的值，查询常量池中的指定索引项即可

3.描述符索引

- 描述符的作用是用来描述字段的数据类型、方法的参数列表(包括数量、类型以及顺序)和返回值。根据描述符规则，基本数据类型(byte,char,double,float,int,long,short,boolean)及代表无返回值的void类型都用一个大写字符来表示，而对象则用字符L加对象的全限定名来表示

4.属性表集合：

- 一个字段还可能拥有一些属性，用于存储更多的额外信息。比如初始化值、一些注释信息等。属性个数存放在attribute count中，属性具体内容存放在attributes数组中。以常量属性为例，结构为：

```c
ConstantValue attributef
u2 attribute name index;
u4 attribute length;
u2 constantvalue index;
}
```

- 
  说明：对于常量属性而言，attribute_length值恒为2

##### 1.3.7 方法表集合

methods count (方法计数器)：

- methods_count的值表示当前class文件methods表的成员个数。使用两个字节来表示
- methods 表中每个成员都是一个method info结构

methods：

- 指向常量池索引集合，它完整描述了每个方法的签名

- 在字节码文件中，每一个method info项都对应着一个类或者接口中的方法信息。比如方法的访问修饰符(public、private或protected)，方法的返回值类型以及方法的参数信息等

- 如果这个方法不是抽象的或者不是native的，那么字节码中会体现出来

- 一方面，methods表只描述当前类或接口中声明的方法，不包括从父类或父接口继承的方法。另一方面，methods表有可能会出现由编译器自动添加的方法，最典型的便是编译器产生的方法信息(比如：类(接口)初始化方法`<c1init>()`和实例初始化方法`<init>()`

- 使用注意事项：

  - 在Java语言中，要重载(Overload)一个方法，除了要与原方法具有相同的简单名称之外，还要求必须拥有一个与原方法不同的特征签名，特征签名就是一个方法中各个参数在常量池中的字段符号引用的集合，也就是因为返回值不会包含在特征签名之中

  - 因此Java语言里无法仅仅依靠返回值的不同来对一个已有方法进行重载。但在Class文件格式中，特征签名的范围更大一些。只要描述符不是完全一致的两个方法就可以共存。也就是说，如果两个方法有相同的名称和特征签名，但返回值不同，那么也是可以合法共存于同一个class文件中

  - 也就是说，尽管Java语法规范并不允许在一个类或者接口中声明多个方法签名相同的方法，但是和Java语法规范相反，字节码文件中却恰恰允许存放多个方法签名相同的方法，唯一的条件就是这些方法之间的返回值不能相同

`methods[]`(方法表)：

- methods表中的每个成员都必须是一个method info结构，用于表示当前类或接口中某个方法的完整描述。如果某个method_info结构的access_flags项既没有设置`ACC_NATIVE`标志也没有设置`ACC_ABSTRACT`标志，那么该结构中也应包含实现这个方法所用的Java虚拟机指令
- method info结构可以表示类和接口中定义的所有方法，包括实例方法、类方法、实例初始化方法和类或接口初始化方法
- 方法表的结构实际跟字段表是一样的，方法表结构如下：

| 类型           | 名称             | 含义       | 数量             |
| -------------- | ---------------- | ---------- | ---------------- |
| u2             | access_flags     | 访问标志   | 1                |
| u2             | name_index       | 方法名索引 | 1                |
| u2             | descriptor_index | 描述符索引 | 1                |
| u2             | attributes_count | 属性计数器 | 1                |
| attribute_info | attributes       | 属性集合   | attributes_count |

方法表访问标志：

- 跟字段表一样，方法表也有访问标志，而且他们的标志有部分相同，部分则不同，方法表的具体访问标志如下：

| 标记名        | 值     | 说明                                |
| ------------- | ------ | ----------------------------------- |
| ACC_PUBLIC    | 0x0001 | public，方法可以从包外访问          |
| ACC_PRIVATE   | 0x0002 | private，方法只能本类中访问         |
| ACC_PROTECTED | 0x0004 | protected，方法在自身和子类可以访问 |
| ACC_STATIC    | 0x0008 | static，静态方法                    |

##### 1.3.8 属性表集合

属性表集合(attributes)：

- 方法表集合之后的属性表集合，指的是class文件所携带的辅助信息，比如该 class 文件的源文件的名称。以及任何带有`RetentionPolicy.CLASS`或者`RetentionPolicy.RUNTIME`的注解。这类信息通常被用于Java虚拟机的验证和运行，以及Java程序的调试，一般无须深入了解
- 此外，字段表、方法表都可以有自己的属性表。用于描述某些场景专有的信息
- 属性表集合的限制没有那么严格，不再要求各个属性表具有严格的顺序，并且只要不与己有的属性名重复，任何人实现的编译器都可以向属性表中写入自己定义的属性信息，但Java虚拟机运行时会忽略掉它不认识的属性

attributes count (属性计数器)：

- attributes_count的值表示当前class文件属性表的成员个数。属性表中每一项都是一个attribute info结构

`attributes[]`(属性表)：

- 属性表的每个项的值必须是attribute info结构。属性表的结构比较灵活，各种不同的属性只要满足以下结构即可

属性的通用格式：

| 类型 | 名称                 | 数量             | 含义       |
| ---- | -------------------- | ---------------- | ---------- |
| u2   | attribute_name_index | 1                | 属性名索引 |
| U4   | attribute_length     | 1                | 属性长度   |
| u1   | info                 | attribute_length | 属性表     |

- 即只需说明属性的名称以及占用位数的长度即可，属性表具体的结构可以去自定义



属性类型：

- 属性表实际上可以有很多类型，上面看到的Code属性只是其中一种，Java8里面定义了23种属性



`LocalVariableTable`属性：

- `LocalVariableTable`是可选变长属性，位于 Code属性的属性表中。它被调试器用于确定方法在执行过程中局部变量的信息在 Code 属性的属性表中，`LocalVariableTable`属性可以按照任意顺序出现。 Code 属性中的每个局部变量最多只能有一个`LocalVariableTable`属性
- start pc + length表示这个变量在字节码中的生命周期起始和结束的偏移位置(this生命周期从头0到结尾10)
- index就是这个变量在局部变量表中的位(槽位可复用)
- name就是变量名称
- Descriptor表示局部变量类型描述
- LocalVariableTable 属性表结构：

```java
LocalVariableTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 descriptor_index;
        u2 index;
    } local_variable_table[local_variable_table_length];
}
```

##### 1.3.9 小结
本章主要介绍了Class文件的基本格式

随着Java平台的不断发展，在将来，class文件的内容也一定会做进一步的扩充，但是其基本的格式和结构不会做重大调整

从Java虚拟机的角度看，通过class文件，可以让更多的计算机语言支持Java虚拟机平台。因此，class文件结构不仅仅是Java虚拟机的执行入口，更是Java生态圈的基础和核心

### 1.4 使用`javap`指令解析Class文件

##### 1.4.1 解析字节码的作用

通过反编译生成的字节码文件，可以深入的了解Java代码的工作机制。但是，自己分析类文件结构太麻烦了。除了使用第三方的jclasslib工具之外，oracle官方也提供了工具：javap

javap是jdk自带的反解析工具。它的作用就是根据class字节码文件，反解析出当前类对应的code区(字节码指令)、局部变量表、异常表和代码行偏移量映射表、常量池等信息

通过局部变量表，可以査看局部变量的作用域范围、所在槽位等信息，甚至可以看到槽位复用等信息

##### 1.4.2 `javac -g`操作

解析字节码文件得到的信息中，有些信息(如局部变量表、指令和代码行偏移量映射表、常量池中方法的参数名称等等)需要在使用javac编译成class文件时，指定参数才能输出

比如，直接javac xx.java，就不会在生成对应的局部变量表等信息，如果使用`javac-g xx.java`就可以生成所有相关信息了。如果使用的eclipse或IDEA，则默认情况下，eclipse、IDEA在编译时会生成局部变量表、指令和代码行偏移量映射表等信息的

##### 1.4.3 `javap`的用法

```
C:\Users\hhh>javap -help
用法: javap <options> <classes>
其中, 可能的选项包括:
  -help  --help  -?        输出此用法消息
  -version                 版本信息
  -v  -verbose             输出附加信息
  -l                       输出行号和本地变量表
  -public                  仅显示公共类和成员
  -protected               显示受保护的/公共类和成员
  -package                 显示程序包/受保护的/公共类
                           和成员 (默认)
  -p  -private             显示所有类和成员
  -c                       对代码进行反汇编
  -s                       输出内部类型签名
  -sysinfo                 显示正在处理的类的
                           系统信息 (路径, 大小, 日期, MD5 散列)
  -constants               显示最终常量
  -classpath <path>        指定查找用户类文件的位置
  -cp <path>               指定查找用户类文件的位置
  -bootclasspath <path>    覆盖引导类文件的位置
```

##### 1.4.4 总结

通过javap命令可以査看一个java类反汇编得到的class文件版本号、常量池、访问标识、变量表、指令代码行号表等等信息。不显示类索引、父类索引、接口索引集合、`<clinit>()`、`<init>()`等结构

通过对前面例子代码反汇编文件的简单分析，可以发现，一个方法的执行通常会涉及下面几块内存的操作：

- java栈中：局部变量表、操作数栈
- java堆：通过对象的地址引用去操作
- 常量池
- 其他如帧数据区、方法区的剩余部分等情况，测试中没有显示出来，这里说明一下

平常比较关注的是java类中每个方法的反汇编中的指令操作过程，这些指令都是顺序执行的，可以参考官方文档查看每个指令的含义：

- 官方文档：https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html

## 2.字节码指令集与解析举例

### 2.1 字节码指令集概述

##### 2.1.1 字节码指令集

Java字节码对于虚拟机，就好像汇编语言对于计算机，属于基本执行指令

Java 虚拟机的指令由一个字节长度的、代表着某种特定操作含义的数字(称为操作码，opcode)以及跟随其后的零至多个代表此操作所需参数(称为操作数，operands)而构成。由于Java虚拟机采用面向操作数栈而不是寄存器的结构，所以大多数的指令都不包含操作数，只有一个操作码

由于限制了 Java 虚拟机操作码的长度为一个字节(即 8~255)，这意味着指令集的操作码总数不可能超过256 条

官方文档：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html

熟悉虚拟机的指令对于动态字节码生成、反编译Class文件、class文件修补都有着非常重要的价值。因此，阅读字节码作为了解 Java 虚拟机的基础技能，需要熟练掌握常见指令

##### 2.1.2 执行模型

执行模型：如果不考虑异常处理的话，那么Java虚拟机的解释器可以使用下面这个伪代码当做最基本的执行模型来理解

```java
do{
    自动计算PC寄存器的值加1;
    根据PC寄存器的指示位置，从字节码流中取出操作码;
    if(字节码存在操作数) 从字节码流中取出操作数;
    执行操作码所定义的操作;
}while(字节码长度>0);
```

##### 2.1.3 字节码与数据类型

在Java虚拟机的指令集中，大多数的指令都包含了其操作所对应的数据类型信息。例如，iload指令用于从局部变量表中加载int型的数据到操作数栈中，而float指令加载的则是float类型的数据

对于大部分与数据类型相关的字节码指令，它们的操作码助记符中都有特殊的字符来表明专门为哪种数据类型服务：

- i代表对int类型的数据操作
- l代表long
- s代表short
- b代表byte
- c代表char
- f代表float
- d代表double

也有一些指令的助记符中没有明确地指明操作类型的字母，如arraylength指令，它没有代表数据类型的特殊字符，但操作数永远只能是一个数组类型的对象

还有另外一些指令，如无条件跳转指令goto则是与数据类型无关的

大部分的指令都没有支持整数类型byte、char和short，甚至没有任何指令支持boolean类型。编译器会在编译期或运行期将byte和short类型的数据带符号扩展(sign-Extend)为相应的int类型数据，将boolean和char类型数据零位扩展(Zero-Extend)为相应的int类型数据。与之类似，在处理boolean、byte、short和char类型的数组时，也会转换为使用对应的int类型的字节码指令来处理。因此，大多数对于boolean、byte、short和char类型数据的操作，实际上都是使用相应的int类型作为运算类型

##### 2.1.4 指令分类

由于完全介绍和学习这些指令需要花费大量时间。为了让大家能够更快地熟悉和了解这些基本指令，这里将JVM中的字节码指令集按用途大致分成9类

- 加载与存储指令
- 算术指令
- 类型转换指令
- 对象的创建与访问指令
- 方法调用与返回指令
- 操作数栈管理指令
- 比较控制指令
- 异常处理指令
- 同步控制指令

在做值相关操作时：

- 一个指令，可以从局部变量表、常量池、堆中对象、方法调用、系统调用中等取得数据，这些数据(可能是值，可能是对象的引用)被压入操作数栈
- 一个指令，也可以从操作数栈中取出一到多个值(pop多次)，完成赋值、加减乘除、方法传参、系统调用等等操作

### 2.2 加载与存储指令

##### 2.2.1 加载与存储指令简介

加载和存储指令：加载和存储指令用于将数据从栈帧的局部变量表和操作数栈之间来回传递

常用指令：

- 【局部变量压栈指令】将一个局部变量加载到操作数栈：`xload、xload_<n>`(其中x为i、l、f、d、n为0到3)
- 【常量入栈指令】将一个常量加载到操作数栈：bipush、sipush、ldc、ldc_w、ldc2_w、aconst_null、iconst_ml、`iconst_<i>`、`lconst_<l>`、`fconst_<f>`、`dconst_<d>`
- 【出栈装入局部变量表指令】将一个数值从操作数栈存储到局部变量表：xstore、`xstore_<n>`(其中x为i、1、f、d、a,n为日到3)。xastore(其中x为i、l、f、d、a、b、c、s)
- 扩充局部变量表的访问索引的指令：wide

上面所列举的指令助记符中，有一部分是以尖括号结尾的(例如`iload_<n>`)。这些指令助记符实际上代表了一组指令(例如 `iload_<n>`代表了iload 0、iload 1、iload 2和iload 3这几个指令)。这几组指令都是某个带有一个操作数的通用指令(例如 iload)的特殊形式，对于这若干组特殊指令来说，它们表面上没有操作数，不需要进行取操作数的动作，但操作数都隐含在指令中。比如`iload_0`表示将局部变量表中索引为0位置上的数据压入操作数栈中，`iload_4`表示将局部变量表中索引为4位置上的数据压入操作数栈中

除此之外，它们的语义与原生的通用指令完全一致(例如 iload_0的语义与操作数为0时的 iload 指令语义完全一致)。在尖括号之间的字母指定了指令隐含操作数的数据类型。`<n>`代表非负的整数，`<i>`代表是int类型数据，`<l>`代表long类型，`<f>`代表float类型，`<d>`代表double类型。操作byte、char、short和boolean类型数据时，经常用int类型的指令来表示

##### 2.2.2 再谈操作数栈与局部变量表

![image-20240808210145736](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240808210145736.png)

**操作数栈(Operand stacks)**：

- Java字节码是Java虚拟机所使用的指令集。因此，它与Java虚拟机基于栈的计算模型是密不可分的。在解释执行过程中，每当为Java方法分配栈桢时，Java虚拟机往往需要开辟一块额外的空间作为操作数栈，来存放计算的操作数以及返回结果
- 具体来说便是：执行每一条指令之前，Java 虚拟机要求该指令的操作数已被压入操作数栈中。在执行指令时，Java 虚拟机会将该指令所需的操作数弹出，并且将指令的结果重新压入中
- 以加法指令 iadd 为例。假设在执行该指令前，栈顶的两个元素分别为 int 值1和 int 值 2，那么 iadd 指令将弹出这两个 int，并将求得的和 int 值 3 压入栈中

![image-20240808210940858](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240808210940858.png)

![image-20240808211135521](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240808211135521.png)

**局部变量表(Local Variables)**：

- Java 方法栈桢的另外一个重要组成部分则是局部变量区，字节码程序可以将计算的结果缓存在局部变量区之中。实际上，Java 虚拟机将局部变量区当成一个数组，依次存放 this 指针(仅非静态方法)，所传入的参数，以及字节码中的局部变量。和操作数栈一样，long 类型以及 double类型的值将占据两个单元，其氽类型仅占据一个单元
- 在栈帧中，与性能调优关系最为密切的部分就是局部变量表。局部变量表中的变量也是重要的垃圾回收根节点，只要被局部变量表中直接或间接引用的对象都不会被回收。在方法执行时，虚拟机使用局部变量表完成方法的传递

![image-20240808212428851](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240808212428851.png)

##### 2.2.3 局部变量压栈指令

局部变量压栈指令将给定的局部变量表中的数据压入操作数栈

这类指令大体可以分为：

- `xload_<n>`（x为i、l、f、d、a，n为 0到3)
- xload(x为i、l、f、d、a)

说明：在这里，x的取值表示数据类型，指令`xload_n`表示将第n个局部变量压入操作数栈，比如iload_1、fload_0、aload_8等指令。其中aload_n表示将对象引用压栈

指令xload通过指定参数的形式，把局部变量压入操作数栈，当使用这个命令时，表示局部变量的数量可能超过了4个，比如指令iload、fload等

![image-20240808214201437](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240808214201437.png)

##### 2.2.4 常量入栈指令

常量入栈指令：常量入栈指令的功能是将常数压入操作数栈，根据数据类型和入栈内容的不同，又可以分为const系列、push系列和ldc指令

指令const系列：

- 用于对特定的常量入栈，入栈的常量隐含在指令本身里。指令有：`iconst_<i>`(i从-1到5)、`lconst<l>`(l从0到1)、`fconst_<f>`(f从0到2)、`dconst_<d>`(d从0到1)、`aconst_null`
- 指令const系列举例：
  - iconst_m1将-1压入操作数栈
  - iconst_x(x为0到5)将x压入栈
  - lconst_0、lconst_1分别将长整数和1压入栈
  - fconst_0、fconst_1、fconst_2分别将浮点数0、1、2压入栈
  - dconst_0和dconst_1分别将double型0和1压入栈
  - aconst_null将null压入操作数栈

- 从指令的命名上不难找出规律，指令助记符的第一个字符总是喜欢表示数据类型，i表示整数，1表示长整数，f表示浮点数，d表示双精度浮点，习惯上用a表示对象引用。如果指令隐含操作的参数，会以下划线形式给出

指令push系列：

- 主要包括bipush和sipush。它们的区别在于接收数据类型的不同，bipush接收8位整数作为参数，sipush接收16位整数，它们都将参数压入

指令ldc系列：

- 如果以上指令都不能满足需求，那么可以使用万能的ldc指令，它可以接收一个8位的参数，该参数指向常量池中的int、float或者string的索引，将指定的内容压入堆。类似的还有ldc_w，它接收两个8位参数，能支持的索引范围大于ldc。如果要压入的元素是long或者double类型的,则使用ldc2_w指令，使用方式都是类似的

将常量压入栈的指令：

```
aconst_null 将null对象引用压入栈
iconst_m1 将int类型常量-1压入栈
iconst_0 将int类型常量0压入栈
iconst_1 将int类型常量1压入栈
iconst_2 将int类型常量2压入栈
iconst_3 将int类型常量3压入栈
iconst_4 将int类型常量4压入栈
iconst_5 将int类型常量5压入栈
lconst_0 将long类型常量0压入栈
lconst_1 将long类型常量1压入栈
fconst_0 将float类型常量0压入栈
fconst_1 将float类型常量1压入栈
dconst_0 将double类型常量0压入栈
dconst_1 将double类型常量1压入栈
bipush 将一个8位带符号整数压入栈
sipush 将16位带符号整数压入栈
ldc 把常量池中的项压入栈
ldc_w 把常量池中的项压入栈（使用宽索引）
ldc2_w 把常量池中long类型或者double类型的项压入栈（使用宽索引）
```

代码演示：

```java
    //2.常量入栈指令
    public void pushConstLdc() {
        int i = -1;
        int a = 5;
        int b = 6;
        int c = 127;
        int d = 128;
        int e = 32767;
        int f = 32768;
    }
// 字节码指令
 0 iconst_m1
 1 istore_1
 2 iconst_5
 3 istore_2
 4 bipush 6
 6 istore_3
 7 bipush 127
 9 istore 4
11 sipush 128
14 istore 5
16 sipush 32767
19 istore 6
21 ldc #7 <32768>
23 istore 7
25 return
```

##### 2.2.5 出栈装入局部变量表指令

出栈装入局部变量表指令：用于将操作数栈中栈顶元素弹出后，装入局部变量表的指定位置，用于给局部变量赋值

这类指令主要以store的形式存在，比如xstore (x为i、1、f、d、a)、 xstore_n(x为 i、l、f、d、a，n 为0至3)

- 其中，指令istore_n将从操作数栈中弹出一个整数，并把它赋值给局部变量索引n位置
- 指令xstore由于没有隐含参数信息，故需要提供一个byte类型的参数类指定目标局部变量表的位置

说明：

- 一般说来，类似像store这样的命令需要带一个参数，用来指明将弹出的元素放在局部变量表的第几个位置。但是，为了尽可能压缩指令大小，使用专门的istore_1指令表示将弹出的元素放置在局部变量表第1个位置。类似的还有istore_0、istore 2、istore 3,它们分别表示从操作数栈顶弹出一个元素，存放在局部变量表第0、2、3个位置。由于局部变量表前几个位置总是非常常用，因此这种做法虽然增加了指令数量，但是可以大大压缩生成的字节码的体积。如果局部变量表很大，需要存储的槽位大于3,那么可以使用istore指令，外加一个参数，用来表示需要存放的槽位位置

### 2.3 算术指令

算术指令作用：

- 算术指令用于对两个操作数栈上的值进行某种特定运算，并把结果重新压入操作数

分类：

- 大体上算术指令可以分为两种：对整型数据进行运算的指令与对浮点类型数据进行运算的指令

byte、short、char和boolean类型说明：

- 在每一大类中，都有针对Java虚拟机具体数据类型的专用算术指令。但没有直接支持byte、short、char和boolean类型的算术指令，对于这些数据的运算，都使用int类型的指令来处理。此外，在处理boolean、byte、short和char类型的数组时，也会转换为使用对应的int类型的字节码指令来处理

运算时的溢出：

- 数据运算可能会导致溢出，例如两个很大的正整数相加，结果可能是一个负数。其实]ava虚拟机规范并无明确规定过整型数据溢出的具体结果，仅规定了在处理整型数据时，只有除法指令以及求余指令中当出现除数为0时会导致虚拟机抛出异常ArithmeticException

运算模式：

- 向最接近数舍入模式：JVM要求在进行浮点数计算时，所有的运算结果都必须舍入到适当的精度，非精确结果必须舍入为可被表示的最接近的精确值，如果有两种可表示的形式与该值一样接近，将优先选择最低有效位为零的
- 向零舍入模式：将浮点数转换为整数时，采用该模式，该模式将在目标数值类型中选择一个最接近但是不大于原值的数字作为最精确的舍入结果

NaN值使用：

- 当一个操作产生溢出时，将会使用有符号的无穷大表示，如果某个操作结果没有明确的数学定义的话，将会使用 NaN值来表示。而且所有使用NaN值作为操作数的算术操作，结果都会返回NaN

所有的算术指令包括：

```
加法指令: iadd、ladd、fadd、dadd
减法指令:isub、1sub、fsub、dsub
乘法指令:imul、1mul、 fmu1、dmu1
除法指令:idiv、ldiv、fdiv、ddiv
求余指令:irem、lrem、frem、drem  //remainder:余数
取反指令:ineg、lneg、fneg、dneg  //negation:取反
自增指令:iinc
位运算指令，又可分为:
      位移指令:ishl、ishr、iushr、lshl、lshr、lushr
      按位或指令:ior、lor
      按位与指令:iand、land
      按位异或指令:ixor、1xor
比较指令:dcmpg、dcmpl、fcmpg、fcmpl、lcmp
```

![image-20240808230713321](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240808230713321.png)

比较指令的说明：

- 比较指令的作用是比较栈顶两个元素的大小，并将比较结果入
- 比较指令有：dcmpg，dcmpl、fcmpg、fcmpl、lcmp
- 与前面讲解的指令类似，首字符d表示double类型，f表示float，l表示long
- 对于double和float类型的数字，由于NaN的存在，各有两个版本的比较指令。以float为例，有fcmpg和fcmpl两个指令，它们的区别在于在数字比较时，若遇到NaN值，处理结果不同
- 指令dcmpl和dcmpg也是类似的，根据其命名可以推测其含义，在此不再述
- 指令lcmp针对long型整数，由于long型整数没有NaN值，故无需准备两套指令
- 举例：指令fcmpg和fcmpl都从栈中弹出两个操作数，并将它们做比较，设栈顶的元素为v2,栈顶顺位第2位的元素为v1,若v1=v2,则压入0：若v1>v2则压入1：若v1<v2则压入-1。两个指令的不同之处在于，如果遇到NaN值，fcmpg会压入1,而fcmp1会压入-1

### 2.4 类型转换指令

类型转换指令说明：

- 类型转换指令可以将两种不同的数值类型进行相互转换
- 这些转换操作一般用于实现用户代码中的显式类型转换操作，或者用来处理字节码指令集中数据类型相关指令无法与数据类型一一对应的问题

##### 2.4.1 宽化类型转换(Widening Numeric Conversions)

转换规则：

- Java虚拟机直接支持以下数值的宽化类型转换(widening numeric conversion，小范围类型向大范围类型的安全转换)。也就是说，并不需要指令执行，包括：
  - 从int类型到long、float或者double类型。对应的指令为：i2l、i2f、i2d
  - 从long类型到float、double类型。对应的指令为：12f、12d
  - 从float类型到double类型。对应的指令为：f2d
- 简化为：int --> long --> float --> double



精度损失问题：

- 宽化类型转换是不会因为超过目标类型最大值而丢失信息的，例如，从int转换到 long，或者从int转换到double，都不会丢失任何信息，转换前后的值是精确相等的
- 从int、long类型数值转换到float，或者long类型数值转换到double时，将可能发生精度丢失。可能丢失掉几个最低有效位上的值，转换后的浮点数值是根据IEEE754最接近舍入模式所得到的正确整数值
- 尽管宽化类型转换实际上是可能发生精度丢失的，但是这种转换永远不会导致Java虚拟机抛出运行时异常

补充说明：

- 从byte、char和short类型到int类型的宽化类型转换实际上是不存在的。对于byte类型转为int，虚拟机并没有做实质性的转化处理，只是简单地通过操作数栈交换了两个数据。而将byte转为long时，使用的是i2l，可以看到在内部byte在这里已经等同于int类型处理，类似的还有short类型，这种处理方式有两个特点：
  - 一方面可以减少实际的数据类型，如果为short和byte都准备一套指令，那么指令的数量就会大增，而虚拟机目前的设计上，只愿意使用一个字节表示指令，因此指令总数不能超过256个，为了节省指令资源，将short和byte当做int处理也在情理之中
  - 另一方面，由于局部变量表中的槽位固定为32位，无论是byte或者short存入局部变量表，都会占用32位空间。从这个角度说，也没有必要特意区分这几种数据类型

##### 2.4.2 窄化类型转换(Narrowing Numeric Conversion)

转换规则：

Java虚拟机也直接支持以下窄化类型转换：

- 对应的指令有：i2b、i2c、i2s
- 从int类型至byte、short或者char类型
- 从long类型到int类型。对应的指令有：l2i
- 从float类型到int或者long类型。对应的指令有：f2i、f2l
- 从double类型到int、long或者float类型。对应的指令有：d2i、d2l、d2f

精度损失问题：

- 窄化类型转换可能会导致转换结果具备不同的正负号、不同的数量级，因此，转换过程很可能会导致数值丢失精度
- 尽管数据类型窄化转换可能会发生上限溢出、下限溢出和精度丢失等情况，但是Java虚拟机规范中明确规定数值类型的窄化转换指令永远不可能导致虚拟机抛出运行时异常

补充说明：

- 当将一个浮点值窄化转换为整数类型T(T限于int或long类型之一)的时候，将遵循以下转换规则：

  - 如果浮点值是NaN，那转换结果就是int或long类型的0

  - 如果浮点值不是无穷大的话，浮点值使用IEEE 754的向零舍入模式取整，获得整数值v，如果v在目标类型T(int或long)的表示范围之内，那转换结果就是v。否则，将根据v的符号，转换为T所能表示的最大或者最小正数

- 将一个 double 类型窄化转换为 float 类型时，将遵循以下转换规则：
  - 通过向最接近数舍入模式舍入一个可以使用float类型表示的数字。最后结果根据下面这3条规则判断：
    - 如果转换结果的绝对值太小而无法使用 float来表示，将返回 float类型的正负零
    - 如果转换结果的绝对值太大而无法使用 float来表示，将返回 float类型的正负无穷大
    - 对于double 类型的 NaN值将按规定转换为 float类型的 NaN值

### 2.5 对象的创建与访问指令

Java是面向对象的程序设计语言，虚拟机平台从字节码层面就对面向对象做了深层次的支持。有一系列指令专门用于对象操作，可进一步细分为创建指令、字段访问指令、数组操作指令、类型检查指令

##### 2.5.1 创建指令

虽然类实例和数组都是对象，但Java虚拟机对类实例和数组的创建与操作使用了不同的字节码指令

1.创建类实例的指令：

- 创建类实例的指令：new
  - 它接收一个操作数，为指向常量池的索引，表示要创建的类型，执行完成后，将对象的引用压入栈

2.创建数组的指令：

- 创建数组的指令：newarray、anewarray、multianewarray
  - newarray：创建基本类型数组
  - anewarray：创建引用类型数组
  - multianewarray：创建多维数组

上述创建指令可以用于创建对象或者数组，由于对象和数组在Java中的广泛使用，这些指令的使用频率也非常高

##### 2.5.2 字段访问指令

对象创建后，就可以通过对象访问指令获取对象实例或数组实例中的字段或者数组元素

- 访问类字段（static字段，或者称为类变量）的指令：getstatic、putstatic
- 访问类实例字段(非static字段，或者称为实例变量)的指令：getfield、putfield

举例：

- 以getstatic指令为例，它含有一个操作数，为指向常量池的Fieldref索引，它的作用就是获取Fieldref指定的对象或者值，并将其压入操作数栈

##### 2.5.3 数组操作指令

数组操作指令主要有：xastore和xaload指令。具体为：

- 把一个数组元素加载到操作数栈的指令：baload、caload、saload、iaload、laload、faload、daload、aaload
- 将一个操作数栈的值存储到数组元素中的指令：bastore、 castore、 sastore、iastore、 lastore、fastore、dastore、aastore
- 取数组长度的指令：arraylength
  - 获取数组的长度，将长度压入栈，该指令弹出栈顶的数组元素


说明：

- 指令xaload表示将数组的元素压栈，比如saload、caload分别表示压入short数组和char数组。指令xaload在执行时，要求操作数中栈顶元素为数组索引i，栈顶顺位第2个元素为数组引用a，该指令会弹出栈顶这两个元素，并将a[i]重新压入堆
- xastore则专门针对数组操作，以iastore为例，它用于给一个int数组的给定索引赋值。在iastore执行前，操作数栈顶需要以此准备3个元素：值、索引、数组引用，iastore会弹出这3个值，并将值赋给数组中指定索引的位置

##### 2.5.4 类型检查指令

检查类实例或数组类型的指令：instanceof、checkcast

- 指令checkcast用于检查类型强制转换是否可以进行。如果可以进行，那么checkcast指令不会改变操作数栈，否则它会抛出ClassCastException异常
- 指令instanceof用来判断给定对象是否是某一个类的实例，它会将判断结果压入操作数栈

### 2.6 方法调用与返回指令

##### 2.6.1 方法调用指令

方法调用指令：invokevirtual、invokeinterface、invokespecial、invokestatic 、invokedynamic

以下5条指令用于方法调用：

- invokevirtual指令：用于调用对象的实例方法，根据对象的实际类型进行分派(虚方法分派)，支持多态。这也是Java语言中最常见的方法分派方式
- invokeinterface指令：用于调用接口方法，它会在运行时搜索由特定对象所实现的这个接口方法，并找出适合的方法进行调用
- invokespecial指令：用于调用一些需要特殊处理的实例方法，包括实例初始化方法(构造器)、私有方法和父类方法。这些方法都是静态类型绑定的，不会在调用时进行动态派发
- invokestatic指令：用于调用命名类中的类方法(static方法)。这是静态绑定的
- invokedynamic：调用动态绑定的方法，这个是JDK 1.7后新加入的指令。用于在运行时动态解析出调用点限定符所引用的方法，并执行该方法。前面4条调用指令的分派逻辑都固化在 java 虚拟机内部，而invokedynamic指令的分派逻辑是由用户所设定的引导方法决定的

```java
import java.util.Date;
/**
 * 指令5：方法调用与返回指令
 */
public class MethodInvokeReturnTest {

    //方法调用指令:invokespecial:静态分派
    public void invoke1(){
        //情况1：类实例构造器方法：<init>()
        Date date = new Date();

        Thread t1 = new Thread();
        //情况2：父类的方法
        super.toString();
        //情况3：私有方法
        methodPrivate();
    }

    private void methodPrivate(){

    }
    //方法调用指令:invokestatic:静态分派
    public void invoke2(){
        methodStatic();
    }
    public static void methodStatic(){

    }

    //方法调用指令:invokeinterface
    public void invoke3(){
        Thread t1 = new Thread();
        ((Runnable)t1).run();

        Comparable<Integer> com = null;
        com.compareTo(123);
    }

    //方法调用指令:invokeVirtual:动态分派
    public void invoke4(){
        System.out.println("hello");

        Thread t1 = null;
        t1.run();
    }
    //方法的返回指令
    public int returnInt(){
        int i = 500;
        return i;
    }

    public double returnDouble(){
        return 0.0;
    }

    public String returnString(){
        return "hello,world";
    }

    public int[] returnArr(){
        return null;
    }
    public float returnFloat(){
        int i = 10;
        return i;
    }

    public byte returnByte(){
        return 0;
    }

    public void methodReturn(){
        int i = returnByte();
    }

}
```

##### 2.6.2 方法返回指令

方法返回指令：方法调用结束前，需要进行返回。方法返回指令是根据返回值的类型区分的

- 包括ireturn(当返回值是 boolean、byte、char、short和int 类型时使用)、lreturn、freturn、dreturn和areturn
- 另外还有一条return 指令供声明为 void的方法、实例初始化方法以及类和接口的类初始化方法使用

举例：

- 通过ireturn指令，将当前函数操作数栈的顶层元素弹出，并将这个元素压入调用者函数的操作数栈中(因为调用者非常关心函数的返回值)，所有在当前函数操作数中的其他元素都会被丢弃
- 如果当前返回的是synchronized方法，那么还会执行一个隐含的monitorexit指令，退出临界区
- 最后，会丢弃当前方法的整个帧，恢复调用者的帧，并将控制权转交给调用者

返回值的类型 和 返回指令：

| 返回类型                         | 返回指令 |
| -------------------------------- | -------- |
| void                             | return   |
| int (boolean, byte, char, short) | ireturn  |
| long                             | lreturn  |
| float                            | freturn  |
| double                           | dreturn  |
| reference                        | areturn  |

### 2.7 操作数栈管理指令

如同操作一个普通数据结构中的堆栈那样，JVM提供的操作数栈管理指令，可以用于直接操作操作数栈的指令，这些指令属于通用型，对栈的压入或者弹出无需指明数据类型

这类指令包括如下内容：

- pop，pop2：将一个或两个元素从栈顶弹出，并且直接废弃
- dup，dup2，dup_x1，dup2 x1,dup x2,dup2 x2：复制栈顶一个或两个数值并将复制值或双份的复制值重新压入栈顶
- swap：将栈最顶端的两个slot数值位置交换。Java虚拟机没有提供交换两个64位数据类型(long、double)数值的指令
- 指令nop：是一个非常特殊的指令，它的字节码为0x80。和汇编语言中的nop一样，它表示什么都不做。这条指令一般可用于调试、占位等

说明：

- 不带x的指令是复制栈顶数据并压入栈顶。包括两个指令，dup和dup2。dup的系数代表要复制的slot个数
  - dup开头的指令用于复制1个slot的数据。例如1个int或1个reference类型数据
  - dup2开头的指令用于复制2个slot的数据。例如1个long，或2个int，或1个int+1个float类型数据
- 带x的指令是复制栈顶数据并插入栈顶以下的某个位置。共有4个指令：dup x1，dup2 x1，dup_x2，dup2 x2。对于带x的复制插入指令，只要将指令的dup和x的系数相加，结果即为需要插入的位置。因此
  - dup_x1插入位置：1+1=2，即栈顶2个Slot下面
  - dup_x2插入位置：1+2=3，即顶3个slot下面
  - dup2 x1插入位置：2+1=3，即栈顶3个slot下面
  - dup2 x2插入位置：2+2=4，即栈顶4个slot下面

- pop：将栈顶的1个slot数值出栈。例如1个short类型数值
- pop2：将栈顶的2个slot数值出栈。例如1个double类型数值，或者2个int类型数值

### 2.8 控制转移指令

程序流程离不开条件控制，为了支持条件跳转，虚拟机提供了大量字节码指令，大体上可以分为：比较指令、条件跳转指令、比较条件跳转指令、多条件分支跳转指令、无条件跳转指令等

##### 2.8.1 比较指令

比较指令的说明：

- 比较指令的作用是比较栈顶两个元素的大小，并将比较结果入
- 比较指令有：dcmpg，dcmpl、fcmpg、fcmpl、lcmp
- 与前面讲解的指令类似，首字符d表示double类型，f表示float，l表示long
- 对于double和float类型的数字，由于NaN的存在，各有两个版本的比较指令。以float为例，有fcmpg和fcmpl两个指令，它们的区别在于在数字比较时，若遇到NaN值，处理结果不同
- 指令dcmpl和dcmpg也是类似的，根据其命名可以推测其含义，在此不再述
- 指令lcmp针对long型整数，由于long型整数没有NaN值，故无需准备两套指令
- 举例：指令fcmpg和fcmpl都从栈中弹出两个操作数，并将它们做比较，设栈顶的元素为v2,栈顶顺位第2位的元素为v1,若v1=v2,则压入0：若v1>v2则压入1：若v1<v2则压入-1。两个指令的不同之处在于，如果遇到NaN值，fcmpg会压入1,而fcmp1会压入-1

数值类型的数据，才可以谈大小。数值类型包括(byte\short\char\int、long、float、double)。boolean、引用数据类型不能比较大小

##### 2.8.2 条件跳转指令

条件跳转指令通常和比较指令结合使用。在条件跳转指令执行前，一般可以先用比较指令进行栈顶元素的准备，然后进行条件跳转

条件跳转指令有: ifeg，iflt，ifle，ifne，ifgt，ifge，ifnull，ifnonnull。这些指令都接收两个字节的操作数用于计算跳转的位置(16位符号整数作为当前位置的offset)

它们的统一含义为：弹出栈顶元素，测试它是否满足某一条件，如果满足条件，则跳转到给定位置

具体说明：

```
ifeq 如果等于0，则跳转
ifne 如果不等于0，则跳转
iflt 如果小于0，则跳转
ifge 如果大于等于0，则跳转
ifgt 如果大于0，则跳转
ifle 如果小于等于0，则跳转
if_icmpcq 如果两个int值相等，则跳转
if_icmpne 如果两个int类型值不相等，则跳转
if_icmplt 如果一个int类型值小于另外一个int类型值，则跳转
if_icmpge 如果一个int类型值大于或者等于另外一个int类型值，则跳转
if_icmpgt 如果一个int类型值大于另外一个int类型值，则跳转
if_icmple 如果一个int类型值小于或者等于另外一个int类型值，则跳转
ifnull 如果等于null，则跳转
ifnonnull 如果不等于null，则跳转
if_acmpeq 如果两个对象引用相等，则跳转
if_acmpnc 如果两个对象引用不相等，则跳转
```

注意：

1.与前面运算规则一致：

- 对于boolean、byte、char、short类型的条件分支比较操作，都是使用int类型的比较指令完成
- 对于long、float、double类型的条件分支比较操作，则会先执行相应类型的比较运算指令，运算指令会返回一个整型值到操作数栈中，随后再执行int类型的条件分支比较操作来完成整个分支跳转

2.由于各类型的比较最终都会转为 int 类型的比较操作，所以Java虚拟机提供的int类型的条件分支指令是最为丰富和强大的

##### 2.8.3 比较条件跳转指令

比较条件跳转指令：类似于比较指令和条件跳转指令的结合体，它将比较和跳转两个步骤合二为一

这类指令有：if icmpeg、if icmpne、if icmplt、if icmpgt、if icmple、if icmpge、if acmpeq和if acmpne

其中指令助记符加上“if ”后，以字符i开头的指令针对int型整数操作(也包括short和byte类型)，以字符a开头的指令表示对象引用的比较

| if_icmpeq | 比较栈顶两int类型教值大小，当前者等于后者时跳转     |
| --------- | --------------------------------------------------- |
| if_icmpne | 比较栈顶两int类型数值大小，当前者不等于后者时跳转   |
| if_icmplt | 比较栈顶两int类型数值大小，当前者小于后者时跳转     |
| if_icmple | 比较栈顶两int类型数值大小，当前者小于等于后者时跳转 |
| if_icmpgt | 比较栈顶两int类型数值大小，当前者大于后者时跳转     |
| if_icmpge | 比较栈顶两int类型数值大小，当前者大于等于后者时跳转 |
| if_acmpeq | 比较栈顶两引用类型数值，当结果相等时跳转            |
| if_acmpne | 比较栈顶两引用类型教值，当结果不相等时跳转          |

这些指令都接收两个字节的操作数作为参数，用于计算跳转的位置。同时在执行指令时，栈顶需要准备两个元素进行比较。指令执行完成后，栈顶的这两个元素被清空，且没有任何数据入栈。如果预设条件成立，则执行跳转，否则，继续执行下条语句

##### 2.8.4 多条件分支跳转指令

多条件分支跳转指令是专为switch-case语句设计的，主要有tableswitch和lookupswitch

| 指令名称     | 描述                             |
| ------------ | -------------------------------- |
| tableswitch  | 用于switch条件跳转，case值连续   |
| lookupswitch | 用于switch条件跳转，case值不连续 |

从助记符上看，两者都是switch语句的实现，它们的区别：

- tableswitch要求多个条件分支值是连续的，它内部只存放起始值和终止值，以及若干个跳转偏移量，通过给定的操作数index，可以立即定位到跳转偏移量位置，因此效率比较高
- 指令lookupswitch内部存放着各个离散的case-offset对，每次执行都要搜索全部的case-offset对，找到匹配的case值，并根据对应的offset计算跳转地址，因此效率较低

指令tableswitch的示意图如下图所示。由于tableswitch的case值是连续的，因此只需要记录最低值和最高值，以及每项对应的offset偏移量，根据给定的index值通过简单的计算即可直接定位到offset



![image-20240810152102263](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810152102263.png)

指令lookupswitch处理的是离散的case值，但是出于效率考虑，将case-offset对按照case值大小排序，给定index时需要査找与index相等的case，获得其offset，如果找不到则跳转到default。指令lookupswitch 如下图所示：

![image-20240810152536887](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810152536887.png)

##### 2.8.5 无条件跳转指令

目前主要的无条件跳转指令为goto。指令goto接收两个字节的操作数，共同组成一个带符号的整数，用于指定指令的偏移量指令执行的目的就是跳转到偏移量给定的位置处

如果指令偏移量太大，超过双字节的带符号整数的范围，则可以使用指令goto_w，它和goto有相同的作用，但是它接收4个字节的操作，可以表示更大的地址范围

指令jsr、jsr_w、ret虽然也是无条件跳转的，但主要用于 try-finally语句，且已经被虚拟机逐渐废弃，故不在这里介绍这两个指令

| 指令名称 | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| goto     | 无条件跳转                                                   |
| goto_w   | 无条件跳转(宽索引)                                           |
| jsr      | 跳转至指定16位offset位置，并将jsr下一条指令地址压入栈顶      |
| jsr_w    | 跳转至指定32位offser位置，并将srw下一条指令地址压入栈顶      |
| ret      | 返回至由指定的局部变量所给出的指令位置(一般与jsr、jsw联合使用) |



```java
/**
 * 指令7：控制转移指令
 */
public class IfSwitchGotoTest {
    //1.条件跳转指令
    public void compare1(){
        int a = 0;
        if(a != 0){
            a = 10;
        }else{
            a = 20;
        }
    }
    public boolean compareNull(String str){
        if(str == null){
            return true;
        }else{
            return false;
        }
    }
    //结合比较指令
    public void compare2() {
        float f1 = 9;
        float f2 = 10;
        System.out.println(f1 < f2);//true
    }
    public void compare3() {
        int i1 = 10;
        long l1 = 20;
        System.out.println(i1 > l1);
    }

    public int compare4(double d) {
        if (d > 50.0) {
            return 1;
        } else {
            return -1;
        }
    }

    //2.比较条件跳转指令
    public void ifCompare1(){
        int i = 10;
        int j = 20;
        System.out.println(i > j);
    }
    public void ifCompare2() {
        short s1 = 9;
        byte b1 = 10;
        System.out.println(s1 > b1);
    }

    public void ifCompare3() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println(obj1 == obj2);//false
        System.out.println(obj1 != obj2);//true
    }

    //3.多条件分支跳转
    public void swtich1(int select){
        int num;
        switch(select){
            case 1:
                num = 10;
                break;
            case 2:
                num = 20;
                //break;
            case 3:
                num = 30;
                break;
            default:
                num = 40;
        }

    }
    public void swtich2(int select){
        int num;
        switch(select){
            case 100:
                num = 10;
                break;
            case 500:
                num = 20;
                break;
            case 200:
                num = 30;
                break;
            default:
                num = 40;
        }
    }
    //jdk7新特性：引入String类型
    public void swtich3(String season){
        switch(season){
            case "SPRING":break;
            case "SUMMER":break;
            case "AUTUMN":break;
            case "WINTER":break;
        }
    }

    //4.无条件跳转指令
    public void whileInt() {
        int i = 0;
        while (i < 100) {
            String s = "atguigu.com";
            i++;
        }
    }

    public void whileDouble() {
        double d = 0.0;
        while(d < 100.1) {
            String s = "atguigu.com";
            d++;
        }
    }

    public void printFor() {
        short i;
        for (i = 0; i < 100; i++) {
            String s = "atguigu.com";
        }

    }

    //思考：如下两个方法的操作有何不同？
    public void whileTest(){
        int i = 1;
        while(i <= 100){

            i++;
        }
        //可以继续使用i
    }
    public void forTest(){
        for(int i = 1;i <= 100;i++){

        }
        //不可以继续使用i
    }
    //更进一步
    public void doWhileTest(){
        int i = 1;
        do{
            i++;
        }while(i <= 100);
    }
}
```

### 2.9 异常处理指令

##### 2.9.1 抛出异常指令

athrow指令：

- 在Java程序中显示抛出异常的操作(throw语句)都是由athrow指令来实现
- 除了使用throw语句显示抛出异常情况之外，JVM规范还规定了许多运行时异常会在其他Java虚拟机指令检测到异常状况时自动抛出。例如，在之前介绍的整数运算时，当除数为零时，虚拟机会在 idiv或 ldiv指令中抛出ArithmeticException异常

注意：

- 正常情况下，操作数栈的压入弹出都是一条条指令完成的。唯一的例外情况是在抛异常时，Java 虚拟机会清除操作数栈上的所有内容，而后将异常实例压入调用者操作数栈上

##### 2.9.2 异常处理与异常表

1.处理异常

- 在Java虚拟机中，处理异常(catch语句)不是由字节码指令来实现的(早期使用jsr、ret指令)，而是采用异常表来完成的

2.异常表

- 如果一个方法定义了一个try-catch 或者try-finally的异常处理，就会创建一个异常表。它包含了每个异常处理或者finally块的信息
- 异常表保存了每个异常处理信息。比如：
  - 起始位置
  - 结束位置
  - 程序计数器记录的代码处理的偏移地址
  - 被捕获的异常类在常量池中的索引

当一个异常被抛出时，JVM会在当前的方法里寻找一个匹配的处理，如果没有找到，这个方法会强制结束并弹出当前栈帧，并且异常会重新抛给上层调用的方法(在调用方法栈帧)。如果在所有栈帧弹出前仍然没有找到合适的异常处理，这个线程将终止。如果这个异常在最后一个非守护线程里抛出，将会导致JVM自己终止，比如这个线程是个main线程

不管什么时候抛出异常，如果异常处理最终匹配了所有异常类型，代码就会继续执行。在这种情况下，如果方法结束后没有抛出异常，仍然执行finally块，在return前，它直接跳到finally块来完成目标

### 2.10 同步控制指令

java虚拟机支持两种同步结构：方法级的同步 和 方法内部一段指令序列的同步，这两种同步都是使用monitor来支持的

##### 2.10.1 方法级的同步

方法级的同步：

- 方法级的同步是隐式的， 即无须通过字节码指令来控制，它实现在方法调用和返回操作之中。虚拟机可以从方法常量池的方法表结构中的 `ACC_SYNCHRONIZED` 访问标志得知一个方法是否声明为同步方法
- 当调用方法时，调用指令将会检查方法的`ACC_SYNCHRONIZED`访问标志是否设置
  - 如果设置了，执行线程将先持有同步锁，然后执行方法。最后在方法完成(无论是正常完成还是非正常完成)时释放同步锁
  - 在方法执行期间，执行线程持有了同步锁，其他任何线程都无法再获得同一个锁
  - 如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的锁将在异常抛到同步方法之外时自动释放



```java
举例:
            private int i=0;
            public synchronized void add(){
                    i++;
            }
对应的字节码:
        0 aload 0
        1 dup
        2 getfield #2<com/atguigu/java1/SynchronizedTest.i>
        5 iconst_1
        6 iadd
        7 putfield #2<com/atguigu/java1/SynchronizedTest.i>
结论：方法级的同步是隐式的，字节码中没有显式的同步指令
```

说明：这段代码和普通的无同步操作的代码没有什么不同，没有使用monitorenter和monitorexit进行同步区控制。这是因为对于同步方法而言，当虚拟机通过方法的访问标示符判断是一个同步方法时，会自动在方法调用前进行加锁，当同步方法执行完毕后，不管方法是正常结束还是有异常抛出，均会由虚拟机释放这个锁。因此，对于同步方法而言，monitorenter 和monitorexit指令是隐式存在的，并未直接出现在字节码中

#####  2.10.2 方法内指定指令序列的同步

同步一段指令集序列：通常是由java中的synchronized语句块来表示的。jvm的指令集有 monitorenter 和 monitorexit 两条指令来支持 synchronized关键字的语义

当一个线程进入同步代码块时，它使用monitorenter指令请求进入。如果当前对象的监视器计数器为0，则它会被准许进入。若为1，则判断持有当前监视器的线程是否为自己，如果是，则进入，否则进行等待，直到对象的监视器计数器为0，才会被允许进入同步块

当线程退出同步块时，需要使用monitorexit声明退出。在Java虚拟机中，任何对象都有一个监视器与之相关联，用来判断对象是否被锁定，当监视器被持有后，对象处于锁定状态

指令monitorenter和monitorexit在执行时，都需要在操作数栈顶压入对象,之后monitorenter和monitorexit的锁定和释放都是针对这个对象的监视器进行的

下图展示了监视器如何保护临界区代码不同时被多个线程访问，只有当线程4离开临界区后，线程1、2、3才有可能进入

![image-20240810163547838](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810163547838.png)



## 3.类的加载过程(类的生命周期)详解

在Java中数据类型分为基本数据类型和引用数据类型。基本数据类型由虚拟机预先定义，引用数据类型则需要进行类的加载

按照Java虚拟机规范，从class文件到加载到内存中的类，到类卸载出内存为止，它的整个生命周期包括如下7个阶段：

![image-20240810164826658](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810164826658.png)

其中，验证、准备、解析 3个部分统称为链接(Linking)

从程序中类的使用过程看：

![image-20240810165232821](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810165232821.png)

### 3.1 过程一：Loading(加载)阶段

加载的理解：所谓加载，简而言之就是将Java类的字节码文件加载到机器内存中，并在内存中构建出Java类的原型，也就是类模板对象。所谓类模板对象，其实就是Java类在JVM内存中的一个快照，JVM将从字节码文件中解析出的常量池、类字段、类方法等信息存储到类模板中，这样JVM在运行期便能通过类模板而获取Java类中的任意信息，能够对Java类的成员变量进行遍历，也能进行Java方法的调用。反射的机制即基于这一基础。如果JVM没有将Java类的声明信息存储起来，则JVM在运行期也无法反射

##### 3.1.1 加载阶段的3个操作

加载阶段，简言之，查找并加载类的二进制数据，生成class的实例。在加载类时，Java虚拟机必须完成3件事情。加载阶段的三个主要操作：

- 通过类的全名，获取类的二进制数据流
- 解析类的二进制数据流为方法区内的数据结构(Java类模型)
- 创建java.lang.Class类的实例，表示该类型。作为方法区这个类的各种数据的访问入口

##### 3.1.2 二进制流的获取方式

对于类的二进制数据流，虚拟机可以通过多种途径产生或获得。(只要所读取的字节码符合JVM规范即可)

- 虚拟机可能通过文件系统读入一个class后缀的文件(最常见)
- 读入jar、zip等归档数据包，提取类文件
- 事先存放在数据库中的类的二进制数据
- 使用类似于HTTP之类的协议通过网络进行加载
- 在运行时生成一段Class的二进制信息等

在获取到类的二进制信息后，Java虚拟机就会处理这些数据，并最终转为一个java.lang.Class的实例。如果输入数据不是ClassFile的结构，则会抛出ClassFormatError

##### 3.1.3 类模型与Class实例的位置

1.类模型的位置

- 加载的类在JVM中创建相应的类结构，类结构会存储在方法区（JDK1.8之前：永久代、JDK1.8及之后：元空间）

2.Class实例的位置

- 类将.class文件加载至元空间后，会在堆中创建一个Java.lang.Class对象，用来封装类位于方法区内的数据结构，该Class对象是在加载类的过程中创建的，每个类都对应有一个Class类型的对象

3.图示

![image-20240810173403443](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810173403443.png)

外部可以通过访问代表Order类的Class对象来获取Order的类数据结构

4.再说明

- Class类的构造方法是私有的，只有JVM能够创建
- java.lang.Class实例是访问类型元数据的接口，也是实现反射的关键数据、入口。通过class类提供的接口，可以获得目标类所关联的.class文件中具体的数据结构：方法、字段等信息

```java
/**
 * 过程一：加载阶段
 * 通过Class类，获得了java.lang.String类的所有方法信息，并打印方法访问标识符、描述符
 */
public class LoadingTest {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("java.lang.String");
            //获取当前运行时类声明的所有方法
            Method[] ms = clazz.getDeclaredMethods();
            for (Method m : ms) {
                //获取方法的修饰符
                String mod = Modifier.toString(m.getModifiers());
                System.out.print(mod + " ");
                //获取方法的返回值类型
                String returnType = m.getReturnType().getSimpleName();
                System.out.print(returnType + " ");
                //获取方法名
                System.out.print(m.getName() + "(");
                //获取方法的参数列表
                Class<?>[] ps = m.getParameterTypes();
                if (ps.length == 0) System.out.print(')');
                for (int i = 0; i < ps.length; i++) {
                    char end = (i == ps.length - 1) ? ')' : ',';
                    //获取参数的类型
                    System.out.print(ps[i].getSimpleName() + end);
                }
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

##### 3.1.4 数组类的加载

创建数组类的情况稍微有些特殊，因为数组类本身并不是由类加载器负责创建，而是由JVM在运行时根据需要而直接创建的，但数组的元素类型仍然需要依靠类加载器去创建。创建数组类(下述简称A)的过程：

1. 如果数组的元素类型是引用类型，那么就遵循定义的加载过程递归加载和创建数组A的元素类型
2. JVM使用指定的元素类型和数组维度来创建新的数组类

如果数组的元素类型是引用类型，数组类的可访问性就由元素类型的可访问性决定。否则数组类的可访问性将被缺省定义为public

### 3.2 过程二：Linking(链接)阶段

##### 3.2.1 环节1：链接阶段之Verification(验证)

验证阶段(Verification)

- 当类加载到系统后，就开始链接操作，验证是链接操作的第一步。它的目的是保证加载的字节码是合法、合理并符合规范的。验证的步骤比较复杂，实际要验证的项目也很繁多，大体上Java虚拟机需要做以下检查，如图所示：

![image-20240810175932756](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810175932756.png)

整体说明：

- 验证的内容则涵盖了类数据信息的格式验证、语义检查、字节码验证，以及符号引用验证等
- 其中格式验证会和加载阶段一起执行。验证通过之后，类加载器才会成功将类的二进制数据信息加载到方法区中
- 格式验证之外的验证操作将会在方法区中进行
- 链接阶段的验证虽然拖慢了加载速度，但是它避免了在字节码运行时还需要进行各种检查。(磨刀不误砍柴工)



具体说明：

1. 格式验证：是否以魔数 0xCAFEBABE开头，主版本和副版本号是否在当前Java虚拟机的支持范围内，数据中每一个项是否都拥有正确的长度等

2. Java虚拟机会进行字节码的语义检查，但凡在语义上不符合规范的，虚拟机也不会给予验证通过。比如：

   - 是否所有的类都有父类的存在(在Java里，除了Object外，其他类都应该有父类)

   - 是否一些被定义为final的方法或者类被重写或继承了
   - 非抽象类是否实现了所有抽象方法或者接口方法

3. Java虚拟机还会进行字节码验证，字节码验证也是验证过程中最为复杂的一个过程。它试图通过对字节码流的分析，判断字节码是否可以被正确地执行。比如：

   - 在字节码的执行过程中，是否会跳转到一条不存在的指令
   - 函数的调用是否传递了正确类型的参数
   - 变量的赋值是不是给了正确的数据类型等

栈映射帧(stackMapTable)就是在这个阶段，用于检测在特定的字节码处，其局部变量表和操作数栈是否有着正确的数据类型。但遗憾的是，100%准确地判断一段字节码是否可以被安全执行是无法实现的，因此，该过程只是尽可能地检査出可以预知的明显的问题。如果在这个阶段无法通过检查，虚拟机也不会正确装载这个类。但是，如果通过了这个阶段的检查，也不能说明这个类是完全没有问题的。在前面3次检查中，已经排除了文件格式错误、语义错误以及字节码的不正确性。但是依然不能确保类是没有问题的

##### 3.2.2 环节2：链接阶段之Preparation(准备)

准备阶段(Preparation)，简言之，为类的静态变量分配内存，并将其初始化为默认值

当一个类验证通过时，虚拟机就会进入准备阶段。在这个阶段，虚拟机就会为这个类分配相应的内存空间，并设置默认初始值

Java虚拟机为各类型变量默认的初始值如表所示：

| 类型      | 默认初始值 |
| --------- | ---------- |
| byte      | (byte)0    |
| short     | (short)0   |
| int       | 0          |
| long      | 0L         |
| float     | 0.0f       |
| double    | 0.0        |
| char      | 1u0000     |
| boolean   | false      |
| reference | null       |

注意：

- Java并不支持boolean类型，对于boolean类型，内部实现是int，由于int的默认值是0，故对应的，boolean的默认值就是false
- 这里的初始化不包含基本数据类型的字段用static final修饰的情况，因为final在编译的时候就会分配了，准备阶段会显式赋值
- 注意这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中
- 在这个阶段并不会像初始化阶段中那样会有初始化或者代码被执行

##### 3.2.3 环节3：链接阶段之Resolution(解析)

在准备阶段完成后，就进入了解析阶段

解析阶段(Resolution)，简言之，将类、接口、字段和方法的符号引用转为直接引用

具体描述：

- 符号引用就是一些字面量的引用，和虚拟机的内部数据结构和内存布局无关。比较容易理解的就是在class类文件中，通过常量池进行了大量的符号引用。但是在程序实际运行时，只有符号引用是不够的，比如当如下println()方法被调用时，系统需要明确知道该方法的位置
- 举例：输出操作`System.out.println()`对应的字节码：`invokevirtual #24<java/io/PrintStream.println>`

![image-20240810183851225](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810183851225.png)



以方法为例，Java虚拟机为每个类都准备了一张方法表，将其所有的方法都列在表中，当需要调用一个类的方法的时候只要知道这个方法在方法表中的偏移量就可以直接调用该方法。通过解析操作，符号引用就可以转变为目标方法在类中方法表中的位置，从而使得方法被成功调用

小结：

- 所谓解析就是将符号引用转为直接引用，也就是得到类、字段、方法在内存中的指针或者偏移量。因此，可以说，如果直接引用存在，那么可以肯定系统中存在该类、方法或者字段。但只存在符号引用，不能确定系统中一定存在该结构
- 不过Java虚拟机规范并没有明确要求解析阶段一定要按照顺序执行。在Hotspot VM中，加载、验证、准备和初始化会按照顺序有条不紊地执行，但链接阶段中的解析操作往往会伴随着JVM在执行完初始化之后再执行

字符串的复习：

- 最后，再来看一下CONSTANT string的解析。由于字符串在程序开发中有着重要的作用，因此，读者有必要了解一下String在Java虚拟机中的处理。当在Java代码中直接使用字符串常量时，就会在类中出现CONSTANT string，它表示字符串常量，并且会引用一个CONSTANT UTF8的常量项。在Java虚拟机内部运行中的常量池中，会维护一张字符串拘留表(intern)，它会保存所有出现过的字符串常量，并且没有重复项。只要以CONSTANT String形式出现的字符串也都会在这张表中。使用string.intern()方法可以得到一个字符串在拘留表中的引用，因为该表中没有重复项，所以任何字面相同的字符串的string.intern()方法返回总是相等的

### 3.3 过程三：Initialization(初始化)阶段

##### 3.3.1 初始化阶段

初始化阶段，简言之，为类的静态变量赋予正确的初始值。初始化阶段就是对类变量（静态变量）进行初始化的阶段

具体描述

- 类的初始化是类装载的最后一个阶段。如果前面的步骤都没有问题，那么表示类可以顺利装载到系统中。此时，类才会开始执行Java字节码。(即：到了初始化阶段，才真正开始执行类中定义的Java 程序代码)
- 初始化阶段的重要工作是执行类的初始化方法：`<clinit>()`方法
  - `<clinit>()`方法仅能由Java编译器生成并由JVM调用，程序开发者无法自定义一个同名的方法，更无法直接在Java程序中调用该方法，虽然该方法也是由字节码指令所组成
  - `<clinit>()`方法是由类静态成员的赋值语句以及static语句块合并产生的

说明：

- 在加载一个类之前，虚拟机总是会试图加载该类的父类，因此父类的`<clinit>`总是在子类`<c1init>`之前被调用。也就是说，父类的static块优先级高于子类。口诀：由父及子，静态先行
- Java编译器并不会为所有的类都产生`<clinit>()`初始化方法。哪些类在编译为字节码后，字节码文件中将不会包含`<clinit>()`方法？
  - 一个类中并没有声明任何的类变量，也没有静态代码块时
  - 一个类中声明类变量，但是没有明确使用类变量的初始化语句以及静态代码块来执行初始化操作时
  - 一个类中包含static final修饰的基本数据类型的字段，这些类字段初始化语句采用编译时常量表达式

 哪些场景下，java编译器就不会生成`<clinit>()`方法

```java
/**
 * 哪些场景下，java编译器就不会生成<clinit>()方法
 */
public class InitializationTest1 {
    //场景1：对应非静态的字段，不管是否进行了显式赋值，都不会生成<clinit>()方法
    public int num = 1;
    //场景2：静态的字段，没有显式的赋值，不会生成<clinit>()方法
    public static int num1;
    //场景3：比如对于声明为static final的基本数据类型的字段，不管是否进行了显式赋值，都不会生成<clinit>()方法
    public static final int num2 = 1;
}
```

##### 3.3.2 static与final的搭配问题

说明：使用static + final修饰的字段的显式赋值的操作，到底是在哪个阶段进行的赋值？

```java
import java.util.Random;
/**
 * 说明：使用static + final修饰的字段的显式赋值的操作，到底是在哪个阶段进行的赋值？
 * 情况1：在链接阶段的准备环节赋值
 * 情况2：在初始化阶段<clinit>()中赋值
 *
 * 结论：
 * 在链接阶段的准备环节赋值的情况：
 * 1. 对于基本数据类型的字段来说，如果使用static final修饰，则显式赋值(直接赋值常量，而非调用方法）通常是在链接阶段的准备环节进行
 * 2. 对于String来说，如果使用字面量的方式赋值，使用static final修饰的话，则显式赋值通常是在链接阶段的准备环节进行
 *
 * 在初始化阶段<clinit>()中赋值的情况：
 * 排除上述的在准备环节赋值的情况之外的情况。
 *
 * 最终结论：使用static + final修饰，且显示赋值中不涉及到方法或构造器调用的基本数据类型或String类型的显式赋值，是在链接阶段的准备环节进行。
 */
public class InitializationTest2 {
    public static int a = 1;//在初始化阶段<clinit>()中赋值
    public static final int INT_CONSTANT = 10;//在链接阶段的准备环节赋值

    public static final Integer INTEGER_CONSTANT1 = Integer.valueOf(100);//在初始化阶段<clinit>()中赋值
    public static Integer INTEGER_CONSTANT2 = Integer.valueOf(1000);//在初始化阶段<clinit>()中赋值

    public static final String s0 = "helloworld0";//在链接阶段的准备环节赋值
    public static final String s1 = new String("helloworld1");//在初始化阶段<clinit>()中赋值

    public static String s2 = "helloworld2";

    public static final int NUM1 = new Random().nextInt(10);//在初始化阶段<clinit>()中赋值
}
```

##### 3.3.3 `<clinit>()`的线程安全性

`<clinit>()`的线程安全性：

- 对于`<clinit>()`方法的调用，也就是类的初始化，虚拟机会在内部确保其多线程环境中的安全性
- 虚拟机会保证一个类的`<clinit>()`方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的`<clinit>()`方法，其他线程都需要阻塞等待，直到活动线程执行`<clinit>()`方法完毕
- 正是因为函数`<clinit>()`带锁线程安全的，因此，如果在一个类的`<clinit>()`方法中有耗时很长的操作，就可能造成2个线程阻塞，引发死锁，并且这种死锁是很难发现的，因为看起来它们并没有可用的锁信息
- 如果之前的线程成功加载了类，则等在队列中的线程就没有机会再执行`<clinit>()`方法了。那么，当需要使用这个类时虚拟机会直接返回给它已经准备好的信息

类加载死锁演示：

```java
class StaticA {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            Class.forName("com.atguigu.java1.StaticB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("StaticA init OK");
    }
}
class StaticB {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            Class.forName("com.atguigu.java1.StaticA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("StaticB init OK");
    }
}

public class StaticDeadLockMain extends Thread {
    private char flag;

    public StaticDeadLockMain(char flag) {
        this.flag = flag;
        this.setName("Thread" + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("com.atguigu.java1.Static" + flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " over");
    }

    public static void main(String[] args) throws InterruptedException {
        StaticDeadLockMain loadA = new StaticDeadLockMain('A');
        loadA.start();
        StaticDeadLockMain loadB = new StaticDeadLockMain('B');
        loadB.start();
    }
}
```

##### 3.3.4 类的初始化情况：主动使用 vs 被动使用

Java程序对类的使用分为两种：主动使用和被动使用

**一、主动使用**：

Class只有在必须要首次使用的时候才会被装载，Java虚拟机不会无条件地装载class类型。Java虚拟机规定，一个类或接口在初次使用前，必须要进行初始化。这里指的“使用”，是指主动使用，主动使用只有下列几种情况：(即：如果出现如下的情况，则会对类进行初始化操作。而初始化操作之前的加载、验证、准备已经完成)

1. 当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化
2. 当调用类的静态方法时，即当使用了字节码invokestatic指令
3. 当使用类、接口的静态字段时(final修饰特殊考虑)，比如，使用getstatic或者putstatic指令。(对应访问变量、赋值变量操作)
4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：Class.forName("com.atguigu.java.Test")
5. 当初始化子类时，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化
6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化
7. 当虚拟机启动时，用户需要指定一个要执行的主类(包含main()方法的那个类)，虚拟机会先初始化这个主类
8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。(涉及解析REF getstatic、REF putStatic、REF invokestatic方法句柄对应的类)

针对5，补充说明：

- 当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口
  - 在初始化一个类时，并不会先初始化它所实现的接口
  - 在初始化一个接口时，并不会先初始化它的父接口
- 因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态字段时，才会导致该接口的初始化

针对7，说明：

- JVM启动的时候通过引导类加载器加载一个初始类。这个类在调用public static void main(string[])方法之前被链接和初始化。这个方法的执行将依次导致所需的类的加载，链接和初始化



```java
/**
 * 测试类的主动使用：意味着会调用类的<clinit>()，即执行了类的初始化阶段
 *
 * 1. 当创建一个类的实例时，比如使用new关键字，或者通过反射、克隆、反序列化。
 * 2. 当调用类的静态方法时，即当使用了字节码invokestatic指令。
 */
public class ActiveUse1 {
    public static void main(String[] args) {
        Order order = new Order();
    }

    //序列化的过程：
    @Test
    public void test1() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("order.dat"));

            oos.writeObject(new Order());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //反序列化的过程：（验证）
    @Test
    public void test2() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("order.dat"));

            Order order = (Order) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test3(){
        Order.method();
    }

}

class Order implements Serializable{
    static {
        System.out.println("Order类的初始化过程");
    }

    public static void method(){
        System.out.println("Order method()....");
    }
}
```



```java
/**
 * 3. 当使用类、接口的静态字段时(final修饰特殊考虑)，比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）
 *
 */
public class ActiveUse2 {
    @Test
    public void test1(){
//        System.out.println(User.num);
//        System.out.println(User.num1);
        System.out.println(User.num2);
    }

    @Test
    public void test2(){
//        System.out.println(CompareA.NUM1);
        System.out.println(CompareA.NUM2);
    }
}

class User{
    static{
        System.out.println("User类的初始化过程");
    }

    public static int num = 1;
    public static final int num1 = 1;
    public static final int num2 = new Random().nextInt(10);

}

interface CompareA{
    public static final Thread t = new Thread(){
        {
            System.out.println("CompareA的初始化");
        }
    };

    public static final int NUM1 = 1;
    public static final int NUM2 = new Random().nextInt(10);

}
```



```java
/**
 * 4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：Class.forName("com.atguigu.java.Test")
 * 5. 当初始化子类时，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
 * 6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化。
 * 7. 当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。
 * 8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。
 * （涉及解析REF_getStatic、REF_putStatic、REF_invokeStatic方法句柄对应的类）
 * 针对5，补充说明：
 * 当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口。
 * >在初始化一个类时，并不会先初始化它所实现的接口
 * >在初始化一个接口时，并不会先初始化它的父接口
 * 因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态字段时，
 * 才会导致该接口的初始化。
 */
public class ActiveUse3 {
    static{
        System.out.println("ActiveUse3的初始化过程");
    }
    @Test
    public void test1() {
        try {
            Class clazz = Class.forName("com.atguigu.java1.Order");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        System.out.println(Son.num);
    }

    @Test
    public void test3(){
        System.out.println(CompareC.NUM1);
    }

    @Test
    public void test4() {
        System.out.println(Son.num);
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}


class Father {
    static {
        System.out.println("Father类的初始化过程");
    }
}

class Son extends Father implements CompareB{
    static {
        System.out.println("Son类的初始化过程");
    }

    public static int num = 1;
}

interface CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareB的初始化");
        }
    };
    public default void method1(){
        System.out.println("你好！");
    }

}

interface CompareC extends CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareC的初始化");
        }
    };

    public static final int NUM1 = new Random().nextInt();
}
```



**二、被动使用**

除了以上的情况属于主动使用，其他的情况均属于被动使用。被动使用不会引起类的初始化

也就是说：并不是在代码中出现的类，就一定会被加载或者初始化。如果不符合主动使用的条件，类就不会初始化

1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化
   - 当通过子类引用父类的静态变量，不会导致子类初始化
2. 通过数组定义类引用，不会触发此类的初始化
3. 引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了
4. 调用classLoader类的loadclass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化

```java
/**
 * 关于类的被动使用，即不会进行类的初始化操作，即不会调用<clinit>()
 *
 * 1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化。
 *     > 当通过子类引用父类的静态变量，不会导致子类初始化
 * 2. 通过数组定义类引用，不会触发此类的初始化
 *
 * 说明：没有初始化的类，不意味着没有加载！
 */
public class PassiveUse1 {
    @Test
    public void test1(){
        System.out.println(Child.num);
    }

    @Test
    public void test2(){
        Parent[] parents = new Parent[10];
        System.out.println(parents.getClass());
        System.out.println(parents.getClass().getSuperclass());

        parents[0] = new Parent();
        parents[1] = new Parent();
    }
}

class Parent{
    static{
        System.out.println("Parent的初始化过程");
    }

    public static int num = 1;
}

class Child extends Parent{
    static{
        System.out.println("Child的初始化过程");
    }
}
```



```java
import org.junit.Test;

import java.util.Random;

/**
 *  * 3. 引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了。
 *  * 4. 调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 */
public class PassiveUse2 {
    @Test
    public void test1(){
//        System.out.println(Person.NUM);
        System.out.println(Person.NUM1);
    }

    @Test
    public void test2(){
//        System.out.println(SerialA.ID);
        System.out.println(SerialA.ID1);
    }

    @Test
    public void test3(){
        try {
            Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.atguigu.java1.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Person{
    static{
        System.out.println("Person类的初始化");
    }
    public static final int NUM = 1;//在链接过程的准备环节就被赋值为1了。
    public static final int NUM1 = new Random().nextInt(10);//此时的赋值操作需要在<clinit>()中执行
}

interface SerialA{
    public static final Thread t = new Thread() {
        {
            System.out.println("SerialA的初始化");
        }
    };

    int ID = 1;
    int ID1 = new Random().nextInt(10);//此时的赋值操作需要在<clinit>()中执行
}
```

### 3.4 过程四：类的Using(使用)

任何一个类型在使用之前都必须经历过完整的加载、链接和初始化3个类加载步骤。一旦一个类型成功经历过这3个步骤之后，便“万事俱备，只欠东风”，就等着开发者使用了

开发人员可以在程序中访问和调用它的静态类成员信息(比如：静态字段、静态方法)，或者使用new关键字为其创建对象实例

### 3.5 过程五：类的Unloading(卸载)

**一、类、类的加载器、类的实例之间的引用关系**

在类加载器的内部实现中，用一个Java集合来存放所加载类的引用。另一方面，一个class对象总是会引用它的类加载器，调用class对象的getclassLoader()方法，就能获得它的类加载器。由此可见，代表某个类的class实例与其类的加载器之间为双向关联关系

一个类的实例总是引用代表这个类的class对象。在object类中定义了getclass()方法，这个方法返回代表对象所属类的Class对象的引用。此外，所有的Java类都有一个静态属性class，它引用代表这个类的class对象

**二、类的生命周期**

当Sample类被加载、链接和初始化后，它的生命周期就开始了。当代表Sample类的class对象不再被引用，即不可触及时，Class对象就会结束生命周期，Sample类在方法区内的数据也会被卸载，从而结束sample类的生命周期。一个类何时结束生命周期，取决于代表它的Class对象何时结束生命周期

**三、具体例子**

![image-20240810202327196](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810202327196.png)

loader1变量和obj变量间接应用代表Sample类的class对象，而objclass变量则直接引用它

如果程序运行过程中，将上图左侧三个引用变量都置为null，此时Sample对象结束生命周期，MyClassLoader对象结束生命周期，代表Sample类的Class对象也结束生命周期，Sample类在方法区内的二进制数据被卸载

当再次有需要时，会检查sample类的class对象是否存在，如果存在会直接使用，不再重新加载。如果不存在sample类会被重新加载，在Java虚拟机的堆区会生成一个新的代表sample类的class实例(可以通过哈希码査看是否是同一个实例

**四、复习方法区的垃圾回收**

方法区的垃圾收集主要回收两部分内容：常量池中废弃的常量和不再使用的类型

HotSpot虚拟机对常量池的回收策略是很明确的，只要常量池中的常量没有被任何地方引用，就可以被回收

判定一个常量是否“废弃”还是相对简单，而要判定一个类型是否属于“不再被使用的类”的条件就比较苛刻了。需要同时满足下面三个条件：

- 该类所有的实例都已经被回收。也就是Java堆中不存在该类及其任何派生子类的实例
- 加载该类的类加载器已经被回收。这个条件除非是经过精心设计的可替换类加载器的场景，如OSGi、JSP的重加载等，否则通常是很难达成的
- 该类对应的java.lang.class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法

Java虚拟机被允许对满足上述三个条件的无用类进行回收，这里说的仅仅是“被允许”，而并不是和对象一样，没有引用了就必然会回收

**五、类的卸载**

1.启动类加载器加载的类型在整个运行期间是不可能被卸载的(jvm和j1s规范)

2.被系统类加载器和扩展类加载器加载的类型在运行期间不太可能被卸载，因为系统类加载器实例或者扩展类的实例基本上在整个运行期间总能直接或者间接的访问的到，其达到unreachable的可能性极小

3.被开发者自定义的类加载器实例加载的类型只有在很简单的上下文环境中才能被卸载，而且一般还要借助于强制调用虚拟机的垃圾收集功能才可以做到。可以预想，稍微复杂点的应用场景中(比如：很多时候用户在开发自定义类加载器实例的时候采用缓存的策略以提高系统性能)，被加载的类型在运行期间也是几乎不太可能被卸载的(至少卸载的时间是不确定的)

综合以上三点，一个已经加载的类型被卸载的几率很小至少被卸载的时间是不确定的。同时可以看的出来，开发者在开发代码时候，不应该对虚拟机的类型卸载做任何假设的前提下，来实现系统中的特定功能

## 4.再谈类的加载器

### 4.1 概述

类加载器是 JVM 执行类加载机制的前提

ClassLoader的作用：

- classLoader是Java的核心组件，所有的class都是由ClassLoader进行加载的，ClassLoader负责通过各种方式将Class信息的二进制数据流读入JVM内部，转换为一个与目标类对应的java.lang.Class对象实例。然后交给Java虚拟机进行链接、初始化等操作。因此ClassLoader在整个装载阶段，只能影响到类的加载，而无法通过classLoader去改变类的链接和初始化行为。至于它是否可以运行，则由Execution Engine决定

![image-20240810204504800](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240810204504800.png)

类加载器最早出现在Java1.8版本中，那个时候只是单纯地为了满足Java Applet应用而被研发出来。但如今类加载器却在OSGi、字节码加解密领域大放异彩。这主要归功于Java虚拟机的设计者们当初在设计类加载器的时候，并没有考虑将它绑定在JVM内部，这样做的好处就是能够更加灵活和动态地执行类加载操作



类的加载分类：显式加载 vs 隐式加载

- class文件的显式加载与隐式加载的方式是指JVM加载class文件到内存的方式
- 显式加载指的是在代码中通过调用classLoader加载class对象，如直接使用Class.forName(name)或this.getClass().getClassLoader().loadClass()加载class对象
- 隐式加载则是不直接在代码中调用classLoader的方法加载class对象，而是通过虚拟机自动加载到内存中，如在加载某个类的class文件时，该类的class文件中引用了另外一个类的对象，此时额外引用的类将通过JVM自动加载到内存中
- 在日常开发以上两种方式一般会混合使用

```java
public class UserTest {
    public static void main(String[] args) {
        User user = new User(); //隐式加载
        try {
            Class clazz = Class.forName("com.atguigu.java.User"); //显式加载
            ClassLoader.getSystemClassLoader().loadClass("com.atguigu.java.User");//显式加载
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
```

类加载器的必要性：

- 一般情况下，Java开发人员并不需要在程序中显式地使用类加载器，但是了解类加载器的加载机制却显得至关重要。从以下几个方面说：
  - 避免在开发中遇到 java.lang.ClassNotFoundException异常或java.lang.NoClassDefFoundError异常时，手足无措
  - 只有了解类加载器的加载机制才能够在出现异常的时候快速地根据错误异常日志定位问题和解决问题
  - 需要支持类的动态加载或需要对编译后的字节码文件进行加解密操作时，就需要与类加载器打交道了
  - 开发人员可以在程序中编写自定义类加载器来重新定义类的加载规则，以便实现一些自定义的处理逻辑



类的命名空间：

- 何为类的唯一性？
  - 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确认其在Java虚拟机中的唯一性。每一个类加载器，都拥有一个独立的类名称空间：比较两个类是否相等，只有在这两个类是由同一个类加载器加载的前提下才有意义。否则，即使这两个类源自同一个class文件，被同一个虚拟机加载，只要加载他们的类加载器不同，那这两个类就必定不相等

- 命名空间
  - 每个类加载器都有自己的命名空间，命名空间由该加载器及所有的父加载器所加载的类组成
  - 在同一命名空间中，不会出现类的完整名字(包括类的包名)相同的两个类
  - 在不同的命名空间中，有可能会出现类的完整名字(包括类的包名)相同的两个类
  - 在大型应用中，我们往往借助这一特性，来运行同一个类的不同版本

```java
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class UserClassLoader extends ClassLoader {
    private String rootDir;

    public UserClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * 编写findClass方法的逻辑
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取类的class文件字节数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            //直接生成class对象
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 编写获取class文件并转换为字节码流的逻辑 * @param className * @return
     */
    private byte[] getClassData(String className) {
        // 读取类文件的字节
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            // 读取类文件的字节码
            while ((len = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 类文件的完全路径
     */
    private String classNameToPath(String className) {
        return rootDir + "\\" + className.replace('.', '\\') + ".class";
    }

    public static void main(String[] args) {
        String rootDir = "D:\\code\\workspace_idea5\\JVMDemo1\\chapter04\\src\\";

        try {
            //创建自定义的类的加载器1
            UserClassLoader loader1 = new UserClassLoader(rootDir);
            Class clazz1 = loader1.findClass("com.atguigu.java.User");

            //创建自定义的类的加载器2
            UserClassLoader loader2 = new UserClassLoader(rootDir);
            Class clazz2 = loader2.findClass("com.atguigu.java.User");

            System.out.println(clazz1 == clazz2); //clazz1与clazz2对应了不同的类模板结构。
            System.out.println(clazz1.getClassLoader());
            System.out.println(clazz2.getClassLoader());

            //######################
            Class clazz3 = ClassLoader.getSystemClassLoader().loadClass("com.atguigu.java.User");
            System.out.println(clazz3.getClassLoader());


            System.out.println(clazz1.getClassLoader().getParent());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```



类加载机制的基本特征——类加载机制有三个基本特征：

- 双亲委派模型。但不是所有类加载都遵守这个模型，有的时候，启动类加载器所加载的类型，是可能要加载用户代码的，比如JDK内部的ServiceProvider/ServiceLoader机制，用户可以在标准API框架上，提供自己的实现，JDK也需要提供些默认的参考实现。例如，Java 中JNDI、JDBC、文件系统、Cipher等很多方面，都是利用的这种机制，这种情况就不会用双亲委派模型去加载，而是利用所谓的上下文加载器
- 可见性，子类加载器可以访问父加载器加载的类型，但是反过来是不允许的。不然，因为缺少必要的隔离，就没有办法利用类加载器去实现容器的逻辑
- 单一性，由于父加载器的类型对于子加载器是可见的，所以父加载器中加载过的类型，就不会在子加载器中重复加载。但是注意，类加载器“邻居”间，同一类型仍然可以被加载多次，因为互相并不可见

### 4.2 类的加载器分类

JVM支持两种类型的类加载器，分别为引导类加载器(Bootstrap ClassLoader)和自定义类加载器(User-Defined ClassLoader)

从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是Java虚拟机规范却没有这么定义，而是将所有派生于抽象类classLoader的类加载器都划分为自定义类加载器。无论类加载器的类型如何划分，在程序中最常见的类加载器结构主要是如下情况：

![image-20240811100613728](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811100613728.png)

除了顶层的启动类加载器外，其余的类加载器都应当有自己的“父类”加载器

不同类加载器看似是继承(Inheritance)关系，实际上是包含关系。在下层加载器中，包含着上层加载器的引用

##### 4.2.1 启动类加载器

启动类加载器(引导类加载器，BootstrapClassLoader)

- 启动类加载器使用C/C++语言实现的，嵌套在JVM内部。启动类加载器使用C++编写的。C/C++:指针函数&函数指针、C++支持多继承、更加高效。Java：由C++演变而来，(C++)--版，单继承
- 启动类加载器用来加载Java的核心库(JAVA HOME/jre/lib/rt.jar或sun.boot.class.path路径下的内容)。用于提供JVM自身需要的类
- 并不继承自java.lang.ClassLoader，没有父加载器
- 出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类
- 加载扩展类和应用程序类加载器，并指定为他们的父类加载器

使用`-XX:+TraceClassLoading`参数可以查看类加载的详细情况

##### 4.2.2 扩展类加载器

扩展类加载器(Extension ClassLoader)

- Java语言编写，由sun.misc.Launcher$ExtClassLoader实现
- 继承于ClassLoader类
- 父类加载器为启动类加载器
- 从java.ext.dirs系统属性所指定的目录中加载类库，或从JDK的安装目录的jre/lib/ext子目录下加载类库。如果用户创建的JAR放在此目录下，也会自动由扩展类加载器加载

![image-20240811102941012](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811102941012.png)

```java
import java.net.URL;
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println("**********启动类加载器**************");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL element : urLs) {
            System.out.println(element.toExternalForm());
        }
        //从上面的路径中随意选择一个类,来看看他的类加载器是什么:引导类加载器
        ClassLoader classLoader = java.security.Provider.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println("***********扩展类加载器*************");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }
//
//        //从上面的路径中随意选择一个类,来看看他的类加载器是什么:扩展类加载器
        ClassLoader classLoader1 = sun.security.ec.CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@1540e19d
    }
}
/*
**********启动类加载器**************
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/resources.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/rt.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/sunrsasign.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/jsse.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/jce.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/charsets.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/lib/jfr.jar
file:/D:/develop_tools/jdk/jdk-8u261-windows-x64/jre/classes
file:/C:/Users/22418/AppData/Local/JetBrains/IntelliJIdea2024.1/captureAgent/debugger-agent.jar
null
***********扩展类加载器*************
D:\develop_tools\jdk\jdk-8u261-windows-x64\jre\lib\ext
C:\WINDOWS\Sun\Java\lib\ext
sun.misc.Launcher$ExtClassLoader@67b64c45
 */
```

##### 4.2.3 系统类加载器

应用程序类加载器(系统类加载器，AppClassLoader)

- java语言编写，由sun.misc.Launcher$AppClassLoader实现
- 继承于ClassLoader类
- 父类加载器为扩展类加载器
- 它负责加载环境变量classpath或系统属性 java.class.path 指定路径下的类库
- 应用程序中的类加载器默认是系统类加载器
- 它是用户自定义类加载器的默认父加载器
- 通过ClassLoader的getSystemClassLoader()方法可以获取到该类加载器

##### 4.2.4 用户自定义类加载器

在Java的日常应用程序开发中，类的加载几乎是由上述3种类加载器相互配合执行的。在必要时，还可以自定义类加载器，来定制类的加载方式

体现Java语言强大生命力和巨大魅力的关键因素之一便是，Java开发者可以自定义类加载器来实现类库的动态加载加载源可以是本地的JAR包，也可以是网络上的远程资源

通过类加载器可以实现非常绝妙的插件机制，这方面的实际应用案例举不胜举。例如，著名的OSGI组件框架，再如Eclipse的插件机制。类加载器为应用程序提供了一种动态增加新功能的机制，这种机制无须重新打包发布应用程序就能实现

同时，自定义加载器能够实现应用隔离，例如 Tomcat，Spring等中间件和组件框架都在内部实现了自定义的加载器，并通过自定义加载器隔离不同的组件模块。这种机制比C/C++程序要好太多，想不修改C/C++程序就能为其新增功能，几乎是不可能的，仅仅一个兼容性便能阻挡住所有美好的设想

自定义类加载器通常需要继承于ClassLoader

### 4.3 测试不同的类加载器

每个Class对象都会包含一个定义它的ClassLoader的一个引用

获取ClassLoader的途径

- 获得当前类的ClassLoader：clazz.getClassLoader()
- 获得当前线程上下文的ClassLoader：Thread.currentThread().getContextClassLoader()
- 获得系统的ClassLoader：ClassLoader.getSystemClassLoader()

说明：

站在程序的角度看，引导类加载器与另外两种类加载器(系统类加载器和扩展类加载器)并不是同一个层次意义上的加载器，引导类加载器是使用C++语言编写而成的，而另外两种类加载器则是使用Java语言编写而成的。由于引导类加载器压根儿就不是一个Java类，因此在Java程序中只能打印出空值

数组类的class对象，不是由类加载器去创建的，而是在Java运行期JVM根据需要自动创建的。对于数组类的类加载器来说，是通过class.getClassLoader()返回的，与数组当中元素类型的类加载器是一样的。如果数组当中的元素类型是基本数据类型，数组类是没有类加载器的

```java
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        //获取系统该类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@1540e19d
        //试图获取引导类加载器：失败
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        //###########################
        try {
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);
            //自定义的类默认使用系统类加载器
            ClassLoader classLoader1 = Class.forName("com.atguigu.java.ClassLoaderTest1").getClassLoader();
            System.out.println(classLoader1);

            //关于数组类型的加载:使用的类的加载器与数组元素的类的加载器相同
            String[] arrStr = new String[10];
            System.out.println(arrStr.getClass().getClassLoader());//null:表示使用的是引导类加载器

            ClassLoaderTest1[] arr1 = new ClassLoaderTest1[10];
            System.out.println(arr1.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

            int[] arr2 = new int[10];
            System.out.println(arr2.getClass().getClassLoader());//null:不需要类的加载器


            System.out.println(Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

### 4.4 ClassLoader源码解析

ClassLoader与现有类加载器的关系：

![image-20240811110022550](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811110022550.png)

除了以上虚拟机自带的加载器外，用户还可以定制自己的类加载器。Java提供了抽象类java.lang.ClassLoader，所有用户自定义的类加载器都应该继承ClassLoader类

```java
/**
 * A class loader is an object that is responsible for loading classes. The
 * class <tt>ClassLoader</tt> is an abstract class.  Given the <a
 * href="#name">binary name</a> of a class, a class loader should attempt to
 * locate or generate data that constitutes a definition for the class.  A
 * typical strategy is to transform the name into a file name and then read a
 * "class file" of that name from a file system.
 *
 * <p> Every {@link Class <tt>Class</tt>} object contains a {@link
 * Class#getClassLoader() reference} to the <tt>ClassLoader</tt> that defined
 * it.
 *
 * <p> <tt>Class</tt> objects for array classes are not created by class
 * loaders, but are created automatically as required by the Java runtime.
 * The class loader for an array class, as returned by {@link
 * Class#getClassLoader()} is the same as the class loader for its element
 * type; if the element type is a primitive type, then the array class has no
 * class loader.
 *
 * <p> Applications implement subclasses of <tt>ClassLoader</tt> in order to
 * extend the manner in which the Java virtual machine dynamically loads
 * classes.
 *
 * <p> Class loaders may typically be used by security managers to indicate
 * security domains.
 *
 * <p> The <tt>ClassLoader</tt> class uses a delegation model to search for
 * classes and resources.  Each instance of <tt>ClassLoader</tt> has an
 * associated parent class loader.  When requested to find a class or
 * resource, a <tt>ClassLoader</tt> instance will delegate the search for the
 * class or resource to its parent class loader before attempting to find the
 * class or resource itself.  The virtual machine's built-in class loader,
 * called the "bootstrap class loader", does not itself have a parent but may
 * serve as the parent of a <tt>ClassLoader</tt> instance.
 *
 * <p> Class loaders that support concurrent loading of classes are known as
 * <em>parallel capable</em> class loaders and are required to register
 * themselves at their class initialization time by invoking the
 * {@link
 * #registerAsParallelCapable <tt>ClassLoader.registerAsParallelCapable</tt>}
 * method. Note that the <tt>ClassLoader</tt> class is registered as parallel
 * capable by default. However, its subclasses still need to register themselves
 * if they are parallel capable. <br>
 * In environments in which the delegation model is not strictly
 * hierarchical, class loaders need to be parallel capable, otherwise class
 * loading can lead to deadlocks because the loader lock is held for the
 * duration of the class loading process (see {@link #loadClass
 * <tt>loadClass</tt>} methods).
 *
 * <p> Normally, the Java virtual machine loads classes from the local file
 * system in a platform-dependent manner.  For example, on UNIX systems, the
 * virtual machine loads classes from the directory defined by the
 * <tt>CLASSPATH</tt> environment variable.
 *
 * <p> However, some classes may not originate from a file; they may originate
 * from other sources, such as the network, or they could be constructed by an
 * application.  The method {@link #defineClass(String, byte[], int, int)
 * <tt>defineClass</tt>} converts an array of bytes into an instance of class
 * <tt>Class</tt>. Instances of this newly defined class can be created using
 * {@link Class#newInstance <tt>Class.newInstance</tt>}.
 *
 * <p> The methods and constructors of objects created by a class loader may
 * reference other classes.  To determine the class(es) referred to, the Java
 * virtual machine invokes the {@link #loadClass <tt>loadClass</tt>} method of
 * the class loader that originally created the class.
 *
 * <p> For example, an application could create a network class loader to
 * download class files from a server.  Sample code might look like:
 *
 * <blockquote><pre>
 *   ClassLoader loader&nbsp;= new NetworkClassLoader(host,&nbsp;port);
 *   Object main&nbsp;= loader.loadClass("Main", true).newInstance();
 *       &nbsp;.&nbsp;.&nbsp;.
 * </pre></blockquote>
 *
 * <p> The network class loader subclass must define the methods {@link
 * #findClass <tt>findClass</tt>} and <tt>loadClassData</tt> to load a class
 * from the network.  Once it has downloaded the bytes that make up the class,
 * it should use the method {@link #defineClass <tt>defineClass</tt>} to
 * create a class instance.  A sample implementation is:
 *
 * <blockquote><pre>
 *     class NetworkClassLoader extends ClassLoader {
 *         String host;
 *         int port;
 *
 *         public Class findClass(String name) {
 *             byte[] b = loadClassData(name);
 *             return defineClass(name, b, 0, b.length);
 *         }
 *
 *         private byte[] loadClassData(String name) {
 *             // load the class data from the connection
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote>
 *
 * <h3> <a name="name">Binary names</a> </h3>
 *
 * <p> Any class name provided as a {@link String} parameter to methods in
 * <tt>ClassLoader</tt> must be a binary name as defined by
 * <cite>The Java&trade; Language Specification</cite>.
 *
 * <p> Examples of valid class names include:
 * <blockquote><pre>
 *   "java.lang.String"
 *   "javax.swing.JSpinner$DefaultEditor"
 *   "java.security.KeyStore$Builder$FileBuilder$1"
 *   "java.net.URLClassLoader$3$1"
 * </pre></blockquote>
 *
 * @see      #resolveClass(Class)
 * @since 1.0
 */
public abstract class ClassLoader {
}
```

##### 4.4.1 ClassLoader的主要方法

抽象类ClassLoader的主要方法(内部没有抽象方法)：

public final classLoader getParent()：返回该类加载器的超类加载器

```java
    // The parent class loader for delegation
    // Note: VM hardcoded the offset of this field, thus all new fields
    // must be added *after* it.
    private final ClassLoader parent;

/**
 * Returns the parent class loader for delegation. Some implementations may
 * use <tt>null</tt> to represent the bootstrap class loader. This method
 * will return <tt>null</tt> in such implementations if this class loader's
 * parent is the bootstrap class loader.
 *
 * <p> If a security manager is present, and the invoker's class loader is
 * not <tt>null</tt> and is not an ancestor of this class loader, then this
 * method invokes the security manager's {@link
 * SecurityManager#checkPermission(java.security.Permission)
 * <tt>checkPermission</tt>} method with a {@link
 * RuntimePermission#RuntimePermission(String)
 * <tt>RuntimePermission("getClassLoader")</tt>} permission to verify
 * access to the parent class loader is permitted.  If not, a
 * <tt>SecurityException</tt> will be thrown.  </p>
 *
 * @return  The parent <tt>ClassLoader</tt>
 *
 * @throws  SecurityException
 *          If a security manager exists and its <tt>checkPermission</tt>
 *          method doesn't allow access to this class loader's parent class
 *          loader.
 *
 * @since  1.2
 */
@CallerSensitive
public final ClassLoader getParent() {
    if (parent == null)
        return null;
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        // Check access to the parent class loader
        // If the caller's class loader is same as this class loader,
        // permission check is performed.
        checkClassLoaderPermission(parent, Reflection.getCallerClass());
    }
    return parent;
}
```

public Class<?>loadclass(string name)throws ClassNotFoundException：加载名称为name的类，返回结果为java.lang.Class类的实例。如果找不到类，则返回 ClassNotfoundException异常。该方法中的逻辑就是双亲委派模式的实现

```java
/**
 * Loads the class with the specified <a href="#name">binary name</a>.
 * This method searches for classes in the same manner as the {@link
 * #loadClass(String, boolean)} method.  It is invoked by the Java virtual
 * machine to resolve class references.  Invoking this method is equivalent
 * to invoking {@link #loadClass(String, boolean) <tt>loadClass(name,
 * false)</tt>}.
 *
 * @param  name
 *         The <a href="#name">binary name</a> of the class
 *
 * @return  The resulting <tt>Class</tt> object
 *
 * @throws  ClassNotFoundException
 *          If the class was not found
 */
public Class<?> loadClass(String name) throws ClassNotFoundException {
    return loadClass(name, false);
}

/**
 * Loads the class with the specified <a href="#name">binary name</a>.  The
 * default implementation of this method searches for classes in the
 * following order:
 *
 * <ol>
 *
 *   <li><p> Invoke {@link #findLoadedClass(String)} to check if the class
 *   has already been loaded.  </p></li>
 *
 *   <li><p> Invoke the {@link #loadClass(String) <tt>loadClass</tt>} method
 *   on the parent class loader.  If the parent is <tt>null</tt> the class
 *   loader built-in to the virtual machine is used, instead.  </p></li>
 *
 *   <li><p> Invoke the {@link #findClass(String)} method to find the
 *   class.  </p></li>
 *
 * </ol>
 *
 * <p> If the class was found using the above steps, and the
 * <tt>resolve</tt> flag is true, this method will then invoke the {@link
 * #resolveClass(Class)} method on the resulting <tt>Class</tt> object.
 *
 * <p> Subclasses of <tt>ClassLoader</tt> are encouraged to override {@link
 * #findClass(String)}, rather than this method.  </p>
 *
 * <p> Unless overridden, this method synchronizes on the result of
 * {@link #getClassLoadingLock <tt>getClassLoadingLock</tt>} method
 * during the entire class loading process.
 *
 * @param  name
 *         The <a href="#name">binary name</a> of the class
 *
 * @param  resolve
 *         If <tt>true</tt> then resolve the class
 *
 * @return  The resulting <tt>Class</tt> object
 *
 * @throws  ClassNotFoundException
 *          If the class could not be found
 */
protected Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // First, check if the class has already been loaded
        // 首先，在缓存中判断是否已经加裁同名的类
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            long t0 = System.nanoTime();
            try {
                // 获取当前类加载器的父类加载器
                if (parent != null) {
                    // 如果存在父类加载器，则调用父类加载器进行类的加载 (双亲委派机制)
                    c = parent.loadClass(name, false);
                } else {
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
            }
            // 当前类的加载器的父类加载器未加载此类 or 当前类的加载器未加载此类
            if (c == null) { 
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                // 调用当前classLoader的findclass()
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
```

protected class<?>findclass(String name)throws ClassNotFoundException：
- 查找二进制名称为name的类，返回结果为java.lang.class类的实例。这是一个受保护的方法，JVM鼓励我们重写此方法，需要自定义加载器遵循双亲委托机制，该方法会在检査完父类加载器之后被loadclass()方法调用
- 在JDK1.2之前，在自定义类加载时，总会去继承classLoader类并重写loadclass方法，从而实现自定义的类加载类。但是在JDK1.2之后已不再建议用户去覆盖loadclass()方法，而是建议把自定义的类加载逻辑写在findclass()方法中，从前面的分析可知，findclass()方法是在loadclass()方法中被调用的，当loadclass()方法中父加载器加载失败后，则会调用自己的findclass()方法来完成类加载，这样就可以保证自定义的类加载器也符合双亲委托模式
- 需要注意的是classLoader类中并没有实现findclass()方法的具体代码逻辑，取而代之的是抛出ClassNotFoundException异常，同时应该知道的是findclass方法通常是和defineClass方法一起使用的。一般情况下，在自定义类加载器时，会直接覆盖classLoader的findclass()方法并编写加载规则，取得要加载类的字节码后转换成流，然后调用defineClass()方法生成类的class对象

```java
public abstract class ClassLoader {
/**
 * Finds the class with the specified <a href="#name">binary name</a>.
 * This method should be overridden by class loader implementations that
 * follow the delegation model for loading classes, and will be invoked by
 * the {@link #loadClass <tt>loadClass</tt>} method after checking the
 * parent class loader for the requested class.  The default implementation
 * throws a <tt>ClassNotFoundException</tt>.
 *
 * @param  name
 *         The <a href="#name">binary name</a> of the class
 *
 * @return  The resulting <tt>Class</tt> object
 *
 * @throws  ClassNotFoundException
 *          If the class could not be found
 *
 * @since  1.2
 */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
}
```

`URLClassLoader`对findClass的实现

```java
public class URLClassLoader extends SecureClassLoader implements Closeable {
    /**
     * Finds and loads the class with the specified name from the URL search
     * path. Any URLs referring to JAR files are loaded and opened as needed
     * until the class is found.
     *
     * @param name the name of the class
     * @return the resulting class
     * @exception ClassNotFoundException if the class could not be found,
     *            or if the loader is closed.
     * @exception NullPointerException if {@code name} is {@code null}.
     */
    protected Class<?> findClass(final String name)
        throws ClassNotFoundException
    {
        final Class<?> result;
        try {
            result = AccessController.doPrivileged(
                new PrivilegedExceptionAction<Class<?>>() {
                    public Class<?> run() throws ClassNotFoundException {
                        String path = name.replace('.', '/').concat(".class");
                        Resource res = ucp.getResource(path, false);
                        if (res != null) {
                            try {
                                return defineClass(name, res);
                            } catch (IOException e) {
                                throw new ClassNotFoundException(name, e);
                            }
                        } else {
                            return null;
                        }
                    }
                }, acc);
        } catch (java.security.PrivilegedActionException pae) {
            throw (ClassNotFoundException) pae.getException();
        }
        if (result == null) {
            throw new ClassNotFoundException(name);
        }
        return result;
    }
}
```

protected final class<?> defineClass(string name, byte[] b, int off, int len)：

- 根据给定的字节数组b转换为Class的实例，off和len参数表示实际Class信息在byte数组中的位置和长度，其中byte数组b是ClassLoader从外部获取的。这是受保护的方法，只有在自定义classLoader子类中可以使用
- defineClass()方法是用来将byte字节流解析成JVM能够识别的class对象(ClassLoader中已实现该方法逻辑)，通过这个方法不仅能够通过class文件实例化class对象，也可以通过其他方式实例化class对象，如通过网络接收个类的字节码，然后转换头byte字节流创建对应的class对象
- defineclass()方法通常与findclass()方法一起使用，一般情况下，在自定义类加载器时，会直接覆盖ClassLoader的findclass()方法并编写加载规则，取得要加载类的字节码后转换成流，然后调用defineclass()方法生成类的Class对象

```java
/**
 * Converts an array of bytes into an instance of class <tt>Class</tt>,
 * with an optional <tt>ProtectionDomain</tt>.  If the domain is
 * <tt>null</tt>, then a default domain will be assigned to the class as
 * specified in the documentation for {@link #defineClass(String, byte[],
 * int, int)}.  Before the class can be used it must be resolved.
 *
 * <p> The first class defined in a package determines the exact set of
 * certificates that all subsequent classes defined in that package must
 * contain.  The set of certificates for a class is obtained from the
 * {@link java.security.CodeSource <tt>CodeSource</tt>} within the
 * <tt>ProtectionDomain</tt> of the class.  Any classes added to that
 * package must contain the same set of certificates or a
 * <tt>SecurityException</tt> will be thrown.  Note that if
 * <tt>name</tt> is <tt>null</tt>, this check is not performed.
 * You should always pass in the <a href="#name">binary name</a> of the
 * class you are defining as well as the bytes.  This ensures that the
 * class you are defining is indeed the class you think it is.
 *
 * <p> The specified <tt>name</tt> cannot begin with "<tt>java.</tt>", since
 * all classes in the "<tt>java.*</tt> packages can only be defined by the
 * bootstrap class loader.  If <tt>name</tt> is not <tt>null</tt>, it
 * must be equal to the <a href="#name">binary name</a> of the class
 * specified by the byte array "<tt>b</tt>", otherwise a {@link
 * NoClassDefFoundError <tt>NoClassDefFoundError</tt>} will be thrown. </p>
 *
 * @param  name
 *         The expected <a href="#name">binary name</a> of the class, or
 *         <tt>null</tt> if not known
 *
 * @param  b
 *         The bytes that make up the class data. The bytes in positions
 *         <tt>off</tt> through <tt>off+len-1</tt> should have the format
 *         of a valid class file as defined by
 *         <cite>The Java&trade; Virtual Machine Specification</cite>.
 *
 * @param  off
 *         The start offset in <tt>b</tt> of the class data
 *
 * @param  len
 *         The length of the class data
 *
 * @param  protectionDomain
 *         The ProtectionDomain of the class
 *
 * @return  The <tt>Class</tt> object created from the data,
 *          and optional <tt>ProtectionDomain</tt>.
 *
 * @throws  ClassFormatError
 *          If the data did not contain a valid class
 *
 * @throws  NoClassDefFoundError
 *          If <tt>name</tt> is not equal to the <a href="#name">binary
 *          name</a> of the class specified by <tt>b</tt>
 *
 * @throws  IndexOutOfBoundsException
 *          If either <tt>off</tt> or <tt>len</tt> is negative, or if
 *          <tt>off+len</tt> is greater than <tt>b.length</tt>.
 *
 * @throws  SecurityException
 *          If an attempt is made to add this class to a package that
 *          contains classes that were signed by a different set of
 *          certificates than this class, or if <tt>name</tt> begins with
 *          "<tt>java.</tt>".
 */
protected final Class<?> defineClass(String name, byte[] b, int off, int len,
                                     ProtectionDomain protectionDomain)
    throws ClassFormatError
{
    protectionDomain = preDefineClass(name, protectionDomain);
    String source = defineClassSourceLocation(protectionDomain);
    Class<?> c = defineClass1(name, b, off, len, protectionDomain, source);
    postDefineClass(c, protectionDomain);
    return c;
}
```

protected final void resolveclass(class<?>c)

- 链接指定的一个Java类。使用该方法可以使用类的Class对象创建完成的同时也被解析。前面说链接阶段主要是对字节码进行验证，为类变量分配内存并设置初始值同时将字节码文件中的符号引用转换为直接引用

protected final class<?>findLoadedclass(string name)

- 查找名称为name的已经被加载过的类，返回结果为java.lang.Class类的实例。这个方法是final方法，无法被修改

private final ClassLoader parent

- 它也是一个classLoader的实例，这个字段所表示的classLoader也称为这个classLoader的双亲。在类加载的过程中ClassLoader可能会将某些请求交予自己的双亲处理

##### 4.4.2 SecureClassLoader 与 URLClassLoader

接着secureclassLoader扩展了 classLoader，新增了几个与使用相关的代码源(对代码源的位置及其证书的验证)和权限定义类验证(主要指对class源码的访问权限)的方法，一般不会直接跟这个类打交道，更多是与它的子类URLClassLoader有所关联

前面说过，ClassLoader是一个抽象类，很多方法是空的没有实现，比如 findclass()、findResource()等。而URLClassLoader这个实现类为这些方法提供了具体的实现。并新增了URLClassPath类协助取得Class字节码流等功能

在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承URLClassLoader类，这样就可以避免自己去编写findclass()方法及其获取字节码流的方式，使自定义类加载器编写更加简洁

##### 4.4.3 ExtClassLoader 与 AppClassLoader

了解完URLClassLoader后接着看看剩余的两个类加载器，即拓展类加载器ExtClassLoader和系统类加载器AppClassLoader，这两个类都继承自URLClassLoader，是sun.misc.Launcher的静态内部类

sun.misc.Launcher主要被系统用于启动主应用程序，ExtClassLoader和AppClassLoader都是由sun.misc.Launcher创建的

我们发现ExtclassLoader并没有重写loadclass()方法，这足矣说明其遵循双亲委派模式，而AppClassLoader重载了loadclass()方法，但最终调用的还是父类loadClass()方法，因此依然遵守双亲委派模式

##### 4.4.4 Class.forName() 与 lassLoader.loadClass()

Class.forName()：

- 是一个静态方法，最常用的是class.forName(string className)。根据传入的类的全限定名返回一个 Class 对象。该方法在将 Class 文件加载到内存的同时，会执行类的初始化。如：Class.forName("com.atguigu.java.Helloworld");

ClassLoader.loadclass()：

- 这是一个实例方法，需要一个 classLoader 对象来调用该方法。该方法将 class 文件加载到内存时，并不会执行类的初始化，直到这个类第一次使用时才进行初始化。该方法因为需要得到一个 ClassLoader 对象，所以可以根据需要指定使用哪个类加载器。如：ClassLoader cl=.......;    c1.loadClass("com.atguigu.java.HelloWorld");

### 4.5 双亲委派模型

##### 4.5.1 定义与本质

类加载器用来把类加载到Java虚拟机中。从JDK1.2版本开始，类的加载过程采用双亲委派机制，这种机制能更好地保证Java平台的安全

1.定义

- 如果一个类加载器在接到加载类的请求时，它首先不会自己尝试去加载这个类，而是把这个请求任务委托给父类加载器去完成，依次递归，如果父类加载器可以完成类加载任务，就成功返回。只有父类加载器无法完成此加载任务时，才自己去加载

2.本质

- 规定了类加载的顺序是：引导类加载器先加载，若加载不到，由扩展类加载器加载，若还加载不到，才会由系统类加载器或自定义的类加载器进行加载

![image-20240811150055724](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811150055724.png)



![image-20240811150123640](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811150123640.png)

##### 4.5.2 优势与劣势

1.双亲委派机制优势

- 避免类的重复加载，确保一个类的全局唯一性
- Java类随着它的类加载器一起具备了一种带有优先级的层次关系，通过这种层级关可以避免类的重复加载，当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次
- 保护程序安全，防止核心API被随意篡改

2.代码支持

- 双亲委派机制在java.lang.ClassLoader.loadclass(string,boolean)接口中体现。该接口的逻辑如下
  1. 先在当前加载器的缓存中查找有无目标类，如果有，直接返回
  2. 判断当前加载器的父加载器是否为空，如果不为空，则调用parent.loadclass(name，false)接口进行加载
  3. 反之，如果当前加载器的父类加载器为空，则调用findBootstrapclassOrNull(name)接口，让引导类加载器进行加载
  4. 如果通过以上3条路径都没能成功加载，则调用findclass(name)接口进行加载。该接口最终会调用java.lang.ClassLoader接口的defineClass系列的native接口加载目标Java类

- 双亲委派的模型就隐藏在这第2和第3步中

3.举例

- 假设当前加载的是iava.lang.Obiect这个类，很显然，该类属于JDK中核心得不能再核心的一个类，因此一定只能由引导类加载器进行加载。当JVM准备加载java.lang.Object时，JVM默认会使用系统类加载器去加载，按照上面4步加载的逻辑，在第1步从系统类的缓存中肯定査找不到该类，于是进入第2步。由于从系统类加载器的父加载器是扩展类加载器，于是扩展类加载器继续从第1步开始重复。由于扩展类加载器的缓存中也一定査找不到该类，因此进入第2步。扩展类的父加载器是null，因此系统调用findclass(string)，最终通过引导类加载器进行加载

4.思考

- 如果在自定义的类加载器中重写java.lang.ClassLoader.loadclass(string)或java.lang.classLoader.loadclass(string，boolean)方法，抹去其中的双亲委派机制，仅保留上面这4步中的第1步与第4步，那么是不是就能够加载核心类库了呢？
- 这也不行! 因为JDK还为核心类库提供了一层保护机制。不管是自定义的类加载器，还是系统类加载器抑或扩展类加载器，最终都必须调用 java.lang.ClassLoader.defineClass(String,byte[]，int，int,ProtectionDomain)方法，而该方法会执行preDefineclass()接口，该接口中提供了对JDK核心类库的保护

5.双亲委托模式的弊端

- 检查类是否加载的委托过程是单向的，这个方式虽然从结构上说比较清晰，使各个classLoader的职责非常明确，但是同时会带来一个问题，即顶层的ClassLoader无法访问底层的ClassLoader所加载的类
- 通常情况下，启动类加载器中的类为系统核心类，包括一些重要的系统接口，而在应用类加载器中，为应用类。按照这种模式，应用类访问系统类自然是没有问题，但是系统类访问应用类就会出现问题。比如在系统类中提供了一个接口，该接口需要在应用类中得以实现，该接口还绑定一个工厂方法，用于创建该接口的实例，而接口和工厂方法都在启动类加载器中。这时，就会出现该工厂方法无法创建由应用类加载器加载的应用实例的问题

6.结论：

- 由于Java虚拟机规范并没有明确要求类加载器的加载机制一定要使用双亲委派模型，只是建议采用这种方式而已。比如在Tomcat中，类加载器所采用的加载机制就和传统的双亲委派模型有一定区别，当缺省的类加载器接收到一个类的加载任务时，首先会由它自行加载，当它加载失败时，才会将类的加载任务委派给它的超类加载器去执行，这同时也是Servlet规范推荐的一种做法

##### 4.5.3 破坏双亲委派机制

双亲委派模型并不是一个具有强制性约束的模型，而是Java设计者推荐给开发者们的类加载器实现方式

在Java的世界中大部分的类加载器都遵循这个模型，但也有例外的情况，直到Java模块化出现为止，双亲委派模型主要出现过3次较大规模“被破坏”的情况

**第一次破坏双亲委派机制**：

- 双亲委派模型的第一次“被破坏”其实发生在双亲委派模型出现之前--即JDK 1.2面世以前的“远古”时代

- 由于双亲委派模型在JDK 1.2之后才被引入，但是类加载器的概念和抽象类java.lang.ClassLoader则在Java的第一个版本中就已经存在，面对已经存在的用户自定义类加载器的代码，Java设计者们引入双亲委派模型时不得不做出一些妥协，为了兼容这些已有代码，无法再以技术手段避免loadclass()被子类覆盖的可能性，只能在JDK1.2之后的java.lang.ClassLoader中添加一个新的protected方法findclass()，并引导用户编写的类加载逻辑时尽可能去重写这个方法，而不是在loadclass()中编写代码。上节我们已经分析过loadclass()方法，双亲委派的具体逻辑就实现在这里面，按照loadclass()方法的逻辑，如果父类加载失败，会自动调用自己的findclass()方法来完成加载，这样既不影响用户按照自己的意愿去加载类，又可以保证新写出来的类加载器是符合双亲委派规则的



**第二次破坏双亲委派机制：线程上下文类加载器**

- 双亲委派模型的第二次“被破坏”是由这个模型自身的缺陷导致的，双亲委派很好地解决了各个类加载器协作时基础类型的一致性问题(越基础的类由越上层的加载器进行加载)，基础类型之所以被称为“基础”，是因为它们总是作为被用户代码继承、调用的API存在，但程序设计往往没有绝对不变的完美规则，如果有基础类型又要调用回用户的代码，那该怎么办呢?
- 这并非是不可能出现的事情，一个典型的例子便是JNDI服务，INDI现在已经是Java的标准服务，它的代码由启动类加载器来完成加载(在JDK 1.3时加入到rt.iar的)，肯定属于Java中很基础的类型了。但JNDI存在的目的就是对资源进行查找和集中管理，它需要调用由其他厂商实现并部署在应用程序的ClassPath下的JNDI服务提供者接口(Service Provider Interface，SPI)的代码，现在问题来了，启动类加载器是绝不可能认识、加载这些代码的，那该怎么？(SPI：在Java平台中，通常把核心类rt,iar中提供外部服务、可由应用层自行实现的接口称为SPI)
- 为了解决这个困境，Java的设计团队只好引入了一个不太优雅的设计：线程上下文类加载器(Thread contextClassLoader)。这个类加载器可以通过java.lang.Thread类的setContextClassLoader()方法进行设置，如果创建线程时还未设置，它将会从父线程中继承一个，如果在应用程序的全局范围内都没有设置过的话，那这个类加载器默认就是应用程序类加载器
- 有了线程上下文类加载器，程序就可以做一些“舞弊”的事情了。JNDI服务使用这个线程上下文类加载器去加载所需的SPI服务代码，这是一种父类加载器去请求子类加载器完成类加载的行为，这种行为实际上是打通了双亲委派模型的层次结构来逆向使用类加载器，已经违背了双亲委派模型的一般性原则，但也是无可奈何的事情。Java中涉及SPI的加载基本上都采用这种方式来完成，例如JNDI、JDBC、ICE、JAXB和JBI等。不过，当SPI的服务提供者多于一个的时候，代码就只能根据具体提供者的类型来硬编码判断，为了消除这种极不优雅的实现方式，在JDK6时，JDK提供了java.util.ServiceLoader类，以META-INF/services中的配置信息，辅以责任链模式，这才算是给SPI的加载提供了一种相对合理的解决方案

![image-20240811154008884](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811154008884.png)

默认上下文加载器就是应用类加载器，这样以上下文加载器为中介，使得启动类加载器中的代码也可以访问应用类加载器中的类



**第三次破坏双亲委派机制**

- 双亲委派模型的第三次“被破坏”是由于用户对程序动态性的追求而导致的。如：代码热替换(Hot swap)、模块热部署(Hot Deployment)等
- IBM公司主导的JSR-291(即OSGi R4.2)实现模块化热部署的关键是它自定义的类加载器机制的实现，每一个程序模块(OSGi中称为Bundle)都有一个自己的类加载器，当需要更换一个Bundle时，就把Bundle连同类加载器一起换掉以实现代码的热替换。在OSGi环境下，类加载器不再双亲委派模型推荐的树状结构，而是进一步发展为更加复杂的网状结构
- 当收到类加载请求时，OSGi将按照下面的顺序进行类搜索：
  1. 将以java.*开头的类，委派给父类加载器加载
  2. 否则，将委派列表名单内的类，委派给父类加载器加载
  3. 否则，将Import列表中的类，委派给Export这个类的Bundle的类加载器加载
  4. 否则，查找当前Bundle的ClassPath，使用自己的类加载器加载
  5. 否则，查找类是否在自己的Fragment Bundle中，如果在，则委派给Fragment Bundle的类加载器加载
  6. 否则，查找Dynamic Import列表的Bundle，委派给对应Bundle的类加载器加载
  7. 否则，类查找失败

说明：只有开头两点仍然符合双亲委派模型的原则，其余的类查找都是在平级的类加载器中进行的

小结：这里，我们使用了“被破坏”这个词来形容上述不符合双亲委派模型原则的行为，但这里“被破坏”并不一定是带有贬义的。只要有明确的目的和充分的理由，突破旧有原则无疑是一种创新

正如：OSGi中的类加载器的设计不符合传统的双亲委派的类加载器架构，且业界对其为了实现热部署而带来

##### 4.5.4 热替换的实现

热替换是指在程序的运行过程中，不停止服务，只通过替换程序文件来修改程序的行为。热替换的关键需求在于服务不能中断，修改必须立即表现正在运行的系统之中。基本上大部分脚本语言都是天生支持热替换的，比如：PHP，只要替换了PHP源文件，这种改动就会立即生效，而无需重启Web服务器

但对Java来说，热替换并非天生就支持，如果一个类已经加载到系统中，通过修改类文件，并无法让系统再来加载并重定义这个类。因此，在Java中实现这一功能的一个可行的方法就是灵活运用ClassLoader

注意：由不同classLoader加载的同名类属于不同的类型，不能相互转换和兼容。即两个不同的classLoader加载同一个类，在虚拟机内部，会认为这2个类是完全不同的。根据这个特点，可以用来模拟热替换的实现，基本思路如下图所示：

![image-20240811155536370](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811155536370.png)

**热替换的实现演示**：

1.自定义类的加载器：

```java
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
/**
 * 自定义类的加载器
 */
public class MyClassLoader extends ClassLoader {
    private String rootDir;

    public MyClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class clazz = this.findLoadedClass(className);
        FileChannel fileChannel = null;
        WritableByteChannel outChannel = null;
        if (null == clazz) {
            try {
                String classFile = getClassFile(className);
                FileInputStream fis = new FileInputStream(classFile);
                fileChannel = fis.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                outChannel = Channels.newChannel(baos);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while (true) {
                    int i = fileChannel.read(buffer);
                    if (i == 0 || i == -1) {
                        break;
                    }
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }

                byte[] bytes = baos.toByteArray();
                clazz = defineClass(className, bytes, 0, bytes.length);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileChannel != null)
                        fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (outChannel != null)
                        outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return clazz;
    }

    /**
     * 类文件的完全路径
     */
    private String getClassFile(String className) {
        return rootDir + "\\" + className.replace('.', '\\') + ".class";
    }
}
```

2.Demo1类：

```java
public class Demo1 {
    public void hot() {
        System.out.println("OldDemo1---> NewDemo1");
    }

}
```

3.LoopRun类：利用自定义加载器加载Demo1类并调用Demo1类的hot方法。运行LoopRun的时候，可以不停地去修改Demo1类的hot方法，修改之后重新编译Demo1类，会发现LoopRun类中总是会执行Demo1类的hot方法的最新实现，也就是实现了热替换

```java
import java.lang.reflect.Method;

public class LoopRun {
    public static void main(String args[]) {
        while (true) {
            try {
                //1. 创建自定义类加载器的实例
                MyClassLoader loader = new MyClassLoader("D:\\code\\workspace_idea5\\JVMDemo1\\chapter04\\src\\");
                //2. 加载指定的类
                Class clazz = loader.findClass("com.atguigu.java1.Demo1");
                //3. 创建运行时类的实例
                Object demo = clazz.newInstance();
                //4. 获取运行时类中指定的方法
                Method m = clazz.getMethod("hot");
                //5. 调用指定的方法
                m.invoke(demo);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("not find");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

}
```

### 4.6 沙箱安全机制

沙箱安全机制

- 保证程序安全
- 保护Java原生的JDK代码
- Java安全模型的核心就是Java沙箱(sandbox)
- 所有的Java程序运行都可以指定沙箱，可以定制安全策略

什么是沙箱机制？

- 沙箱是一个限制程序运行的环境
- 沙箱机制就是将Java代码限定在虚拟机(JVM)特定的运行范围中，并且严格限制代码对本地系统资源访问
- 通过这样的措施来保证对代码的有限隔离，防止对本地系统造成破坏

沙箱主要限制系统资源访问，那系统资源包括什么？

- CPU、内存、文件系统、网络。不同级别的沙箱对这些资源访问的限制也可以不一样



JDK1.0时期沙箱安全机制：

- 在Java中将执行程序分成本地代码和远程代码两种，本地代码默认视为可信任的，而远程代码则被看作是不受信的。对于授信的本地代码，可以访问一切本地资源。而对于非授信的远程代码，在早期的Java实现中，安全依赖于沙箱(Sandbox)机制。如下图所示JDK1.8安全模型

![image-20240811161550489](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811161550489.png)

JDK1.1时期沙箱安全机制：

- JDK1.0中如此严格的安全机制也给程序的功能扩展带来障碍，比如当用户希望远程代码访问本地系统的文件时候，就无法实现
- 因此在后续的Java1.1版本中，针对安全机制做了改进，增加了安全策略。允许用户指定代码对本地资源的访问权限
- 如下图所示JDK1.1安全模型

![image-20240811162029933](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811162029933.png)

JDK1.2时期沙箱安全机制：

- 在Java1.2版本中，再次改进了安全机制，增加了代码签名。不论本地代码或是远程代码，都会按照用户的安全策略设定，由类加载器加载到虚拟机中权限不同的运行空间，来实现差异化的代码执行权限控制
- 如下图所示JDK1.2安全模型

![image-20240811162457645](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811162457645.png)

JDK1.6时期沙箱安全机制：

- 当前最新的安全机制实现，则引入了域(Domain)的概念
- 虚拟机会把所有代码加载到不同的系统域和应用域。系统域部分专门负责与关键资源进行交互，而各个应用域部分则通过系统域的部分代理来对各种需要的资源进行访问
- 虚拟机中不同的受保护域(Protected Domain)，对应不一样的权限(Permission)。存在于不同域中的类文件就具有了当前域的全部权限
- 如下图所示，最新的安全模型(jdk1.6)

![image-20240811162906906](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811162906906.png)

### 4.7 自定义类的加载器

为什么要自定义类加载器？

- 用户通过定制自己的类加载器，这样可以重新定义类的加载规则，以便实现一些自定义的处理逻辑

- 隔离加载类：在某些框架内进行中间件与应用的模块隔离，把类加载到不同的环境。比如：阿里内某容器框架通过自定义类加载器确保应用中依赖的jar包不会影响到中间件运行时使用的jar包。再比如：Tomcat这类web应用服务器，内部自定义了好几种类加载器，用于隔离同一个Web应用服务器上的不同应用程序
- 修改类加载的方式：类的加载模型并非强制，除Bootstrap外，其他的加载并非一定要引入，或者根据实际情况在某个时间点进行按需进行动态加载
- 扩展加载源：比如从数据库、网络、甚至是电视机机顶盒进行加载
- 防止源码泄漏：Java代码容易被编译和篡改，可以进行编译加密。那么类加载也需要自定义，还原加密的字节码

常见的场景

- 实现类似进程内隔离，类加载器实际上用作不同的命名空间，以提供类似容器、模块化的效果。例如，两个模块依赖于某个类库的不同版本，如果分别被不同的容器加载，就可以互不干扰。这个方面的集大成者是Java EE和OSGI、JPMS等框架
- 应用需要从不同的数据源获取类定义信息，例如网络数据源，而不是本地文件系统。或者是需要自己操纵字节码，动态修改或者生成类型

注意：

- 在一般情况下，使用不同的类加载器去加载不同的功能模块，会提高应用程序的安全性。但是，如果涉及Java类型转换则加载器反而容易产生不美好的事情。在做Java类型转换时，只有两个类型都是由同一个加载器所加载，才能进行类型转换，否则转换时会发生异常



**实现方式**：

Java提供了抽象类java.lang.ClassLoader，所有用户自定义的类加载器都应该继承classLoader类，在自定义 ClassLoader 的子类时候，常见的会有两种做法：

- 方式一：重写loadclass()方法
- 方式二：重写findclass()方法（推荐）

对比：

- 这两种方法本质上差不多，毕竟loadclass()也会调用findclass()，但是从逻辑上讲最好不要直接修改loadclass()的内部逻辑。建议的做法是只在findclass()里重写自定义类的加载方法，根据参数指定类的名字，返回对应的Class对象的引用
- loadclass()这个方法是实现双亲委派模型逻辑的地方，擅自修改这个方法会导致模型被破坏，容易造成问题。因此最好是在双亲委派模型框架内进行小范围的改动，不破坏原有的稳定结构。同时，也避免了自己重写loadclass()方法的过程中必须写双亲委托的重复代码，从代码的复用性来看，不直接修改这个方法始终是比较好的选择
- 当编写好自定义类加载器后，便可以在程序中调用loadclass()方法来实现类加载操作



说明：

- 其父类加载器是系统类加载器
- JVM中的所有类加载都会使用java.lang.ClassLoader.loadclass(string)接口(自定义类加载器并重写java.lang.ClassLoader.loadclass(string)接口的除外)，连JDK的核心类库也不能例外

自定义ClassLoader演示：

```java
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * 自定义ClassLoader
 */
public class MyClassLoader extends ClassLoader {
    private String byteCodePath;

    public MyClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public MyClassLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            //获取字节码文件的完整路径
            String fileName = byteCodePath + className + ".class";
            //获取一个输入流
            bis = new BufferedInputStream(new FileInputStream(fileName));
            //获取一个输出流
            baos = new ByteArrayOutputStream();
            //具体读入数据并写出的过程
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            //获取内存中的完整的字节数组的数据
            byte[] byteCodes = baos.toByteArray();
            //调用defineClass()，将字节数组的数据转换为Class的实例。
            Class clazz = defineClass(null, byteCodes, 0, byteCodes.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;


    }
}
```

使用自定义ClassLoader：

```java
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader("d:/");

        try {
            Class clazz = loader.loadClass("Demo1");
            System.out.println("加载此类的类的加载器为：" + clazz.getClassLoader().getClass().getName());

            System.out.println("加载当前Demo1类的类的加载器的父类加载器为：" + clazz.getClassLoader().getParent().getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

### 4.8 `Java9`新特性

为了保证兼容性，JDK 9没有从根本上改变三层类加载器架构和双亲委派模型，但为了模块化系统的顺利运行，仍然发生了一些值得被注意的变动

1.扩展机制被移除

- 扩展类加载器由于向后兼容性的原因被保留，不过被重命名为平台类加载器(platform classloader)。可以通过ClassLoader的新方法getPlatformClassLoader()来获取
- JDK 9 时基于模块化进行构建(原来的 rt.jar 和 tools.jar 被拆分成数十个 JMOD 文件)。其中的 Java 类库就已天然地满足了可扩展的需求，那自然无须再保留`<JAVA HOME>\1ib\ext` 目录，此前使用这个目录或者 java.ext.dirs 系统变量来扩展 JDK 功能的机制已经没有继续存在的价值了

2.平台类加载器和应用程序类加载器都不再继承自 java.net.URLclassLoader

- 现在启动类加载器、平台类加载器、应用程序类加载器全都继承于 jdk.internal.loader.BuiltinclassLoader
- 如果有程序直接依赖了这种继承关系，或者依赖了 URLClassLoader 类的特定方法，那代码很可能会在 JDK 9 及更新版本的 JDK 中崩溃

![image-20240811170937551](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811170937551.png)

3.在]ava 9中，类加载器有了名称。该名称在构造方法中指定，可以通过getName()方法来获取。平台类加载器的名称是platform，应用类加载器的名称是app。类加载器的名称在调试与类加载器相关的问题时会非常有用

4.启动类加载器现在是在jvm内部和java类库共同协作实现的类加载器(以前是 C++实现)，但为了与之前代码兼容在获取启动类加载器的场景中仍然会返回null，而不会得到BootClassLoader实例

5.类加载的委派关系也发生了变动

- 当平台及应用程序类加载器收到类加载请求，在委派给父加载器加载前，要先判断该类是否能够归属到某一个系统模块中，如果可以找到这样的归属关系，就要优先委派给负责那个模块的加载器完成加载
- 在 Java 模块化系统明确规定了三个类加载器负责各自加载的模块
  - 启动类加载器负责加载的模块
  - 平台类加载器负责加载的模块
  - 应用程序类加载器负责加载的模块
- 双亲委派模式示意图：

![image-20240811171647793](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240811171647793.png)

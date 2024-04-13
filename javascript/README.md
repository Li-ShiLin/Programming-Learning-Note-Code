<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. JavaScript简介](#1-javascript%E7%AE%80%E4%BB%8B)
  - [1.1 JavaScript简介](#11-javascript%E7%AE%80%E4%BB%8B)
- [2. JS语法](#2-js%E8%AF%AD%E6%B3%95)
  - [1.1  js编写位置](#11--js%E7%BC%96%E5%86%99%E4%BD%8D%E7%BD%AE)
  - [1.2 注释](#12-%E6%B3%A8%E9%87%8A)
  - [1.3 变量](#13-%E5%8F%98%E9%87%8F)
  - [1.4 标识符](#14-%E6%A0%87%E8%AF%86%E7%AC%A6)
  - [1.5  数据类型](#15--%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B)
      - [1.5.1 String (字符串型)](#151-string-%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%9E%8B)
      - [1.5.2 Number(数值型)](#152-number%E6%95%B0%E5%80%BC%E5%9E%8B)
      - [1.5.3 Boolean(布尔型)](#153-boolean%E5%B8%83%E5%B0%94%E5%9E%8B)
      - [1.5.4 Null & Undefined](#154-null--undefined)
  - [1.6 数据类型转换](#16-%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2)
      - [1.6.1 字符串转换](#161-%E5%AD%97%E7%AC%A6%E4%B8%B2%E8%BD%AC%E6%8D%A2)
      - [1.6.2 数值转换 & 进制转换](#162-%E6%95%B0%E5%80%BC%E8%BD%AC%E6%8D%A2--%E8%BF%9B%E5%88%B6%E8%BD%AC%E6%8D%A2)
      - [1.6.3 布尔型转换](#163-%E5%B8%83%E5%B0%94%E5%9E%8B%E8%BD%AC%E6%8D%A2)
- [3 运算符](#3-%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.1 算术运算符](#31-%E7%AE%97%E6%9C%AF%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.2 符号运算符](#32-%E7%AC%A6%E5%8F%B7%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.3 自增和自减](#33-%E8%87%AA%E5%A2%9E%E5%92%8C%E8%87%AA%E5%87%8F)
  - [3.4 逻辑运算符](#34-%E9%80%BB%E8%BE%91%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.5 赋值运算符](#35-%E8%B5%8B%E5%80%BC%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.6 关系运算符](#36-%E5%85%B3%E7%B3%BB%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.7 相等运算符 | 全等运算符](#37-%E7%9B%B8%E7%AD%89%E8%BF%90%E7%AE%97%E7%AC%A6--%E5%85%A8%E7%AD%89%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.8 三元运算符](#38-%E4%B8%89%E5%85%83%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [3.9 运算符的优先级](#39-%E8%BF%90%E7%AE%97%E7%AC%A6%E7%9A%84%E4%BC%98%E5%85%88%E7%BA%A7)
- [4. 语句](#4-%E8%AF%AD%E5%8F%A5)
  - [4.1 语句 & 代码块](#41-%E8%AF%AD%E5%8F%A5--%E4%BB%A3%E7%A0%81%E5%9D%97)
  - [1.2 if语句](#12-if%E8%AF%AD%E5%8F%A5)
  - [4.3 switch语句](#43-switch%E8%AF%AD%E5%8F%A5)
  - [4.4 循环语句](#44-%E5%BE%AA%E7%8E%AF%E8%AF%AD%E5%8F%A5)
  - [4.5 for循环](#45-for%E5%BE%AA%E7%8E%AF)
  - [4.6 break和continue](#46-break%E5%92%8Ccontinue)
- [4. 对象](#4-%E5%AF%B9%E8%B1%A1)
  - [4.1 对象和对象的属性](#41-%E5%AF%B9%E8%B1%A1%E5%92%8C%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%B1%9E%E6%80%A7)
  - [4.2 基本数据类型 和 引用数据类型](#42-%E5%9F%BA%E6%9C%AC%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B-%E5%92%8C-%E5%BC%95%E7%94%A8%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B)
  - [4.3 对象字面量](#43-%E5%AF%B9%E8%B1%A1%E5%AD%97%E9%9D%A2%E9%87%8F)
  - [4.4 枚举对象中的属性](#44-%E6%9E%9A%E4%B8%BE%E5%AF%B9%E8%B1%A1%E4%B8%AD%E7%9A%84%E5%B1%9E%E6%80%A7)
  - [4.4 函数](#44-%E5%87%BD%E6%95%B0)
      - [4.4.1 函数的声明和调用](#441-%E5%87%BD%E6%95%B0%E7%9A%84%E5%A3%B0%E6%98%8E%E5%92%8C%E8%B0%83%E7%94%A8)
      - [4.4.2 函数的参数](#442-%E5%87%BD%E6%95%B0%E7%9A%84%E5%8F%82%E6%95%B0)
      - [4.4.3 函数的返回值](#443-%E5%87%BD%E6%95%B0%E7%9A%84%E8%BF%94%E5%9B%9E%E5%80%BC)
      - [4.4.4 立即执行函数](#444-%E7%AB%8B%E5%8D%B3%E6%89%A7%E8%A1%8C%E5%87%BD%E6%95%B0)
      - [4.4.5 对象的方法](#445-%E5%AF%B9%E8%B1%A1%E7%9A%84%E6%96%B9%E6%B3%95)
  - [4.5 作用域](#45-%E4%BD%9C%E7%94%A8%E5%9F%9F)
      - [4.5.1 全局作用域、局部作用域](#451-%E5%85%A8%E5%B1%80%E4%BD%9C%E7%94%A8%E5%9F%9F%E5%B1%80%E9%83%A8%E4%BD%9C%E7%94%A8%E5%9F%9F)
      - [4.5.2 函数作用域](#452-%E5%87%BD%E6%95%B0%E4%BD%9C%E7%94%A8%E5%9F%9F)
      - [4.5.3 this关键字](#453-this%E5%85%B3%E9%94%AE%E5%AD%97)
  - [4.6 使用工厂方法创建对象](#46-%E4%BD%BF%E7%94%A8%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95%E5%88%9B%E5%BB%BA%E5%AF%B9%E8%B1%A1)
  - [4.7 使用构造函数创建对象](#47-%E4%BD%BF%E7%94%A8%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0%E5%88%9B%E5%BB%BA%E5%AF%B9%E8%B1%A1)
  - [4.8 原型](#48-%E5%8E%9F%E5%9E%8B)
      - [4.8.1 原型 & 原型链](#481-%E5%8E%9F%E5%9E%8B--%E5%8E%9F%E5%9E%8B%E9%93%BE)
      - [4.8.2 `toString`方法](#482-tostring%E6%96%B9%E6%B3%95)
  - [4.9 垃圾回收](#49-%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6)
  - [4.10 数组](#410-%E6%95%B0%E7%BB%84)
      - [4.10.1 数组](#4101-%E6%95%B0%E7%BB%84)
      - [4.10.1 数组的方法](#4101-%E6%95%B0%E7%BB%84%E7%9A%84%E6%96%B9%E6%B3%95)
      - [4.10.2 数组的遍历](#4102-%E6%95%B0%E7%BB%84%E7%9A%84%E9%81%8D%E5%8E%86)
      - [4.10.3 forEach方法](#4103-foreach%E6%96%B9%E6%B3%95)
      - [4.10.4 slice方法 和 splice方法](#4104-slice%E6%96%B9%E6%B3%95-%E5%92%8C-splice%E6%96%B9%E6%B3%95)
      - [4.10.5 数组中的其他方法](#4105-%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E5%85%B6%E4%BB%96%E6%96%B9%E6%B3%95)
  - [4.11 函数的call()和apply()方法](#411-%E5%87%BD%E6%95%B0%E7%9A%84call%E5%92%8Capply%E6%96%B9%E6%B3%95)
  - [4.12 arguments](#412-arguments)
  - [4.13 Date对象](#413-date%E5%AF%B9%E8%B1%A1)
  - [4.14 Math工具类](#414-math%E5%B7%A5%E5%85%B7%E7%B1%BB)
  - [4.15 包装类](#415-%E5%8C%85%E8%A3%85%E7%B1%BB)
  - [4.16 字符串相关方法](#416-%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9B%B8%E5%85%B3%E6%96%B9%E6%B3%95)
- [5 正则表达式](#5-%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F)
  - [5.1 正则表达式](#51-%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F)
  - [5.2 正则表达式和字符串相关方法](#52-%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%E5%92%8C%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9B%B8%E5%85%B3%E6%96%B9%E6%B3%95)
  - [5.3 正则表达式语法](#53-%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%AF%AD%E6%B3%95)
      - [5.4 正则表达式语法二](#54-%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%AF%AD%E6%B3%95%E4%BA%8C)
- [6. DOM](#6-dom)
  - [6.1 DOM简介](#61-dom%E7%AE%80%E4%BB%8B)
  - [6.2 事件](#62-%E4%BA%8B%E4%BB%B6)
  - [6.3 文档的加载](#63-%E6%96%87%E6%A1%A3%E7%9A%84%E5%8A%A0%E8%BD%BD)
  - [6.4 DOM查询](#64-dom%E6%9F%A5%E8%AF%A2)
      - [6.4.1 通过document对象查询元素节点](#641-%E9%80%9A%E8%BF%87document%E5%AF%B9%E8%B1%A1%E6%9F%A5%E8%AF%A2%E5%85%83%E7%B4%A0%E8%8A%82%E7%82%B9)
      - [6.4.2 图片切换练习](#642-%E5%9B%BE%E7%89%87%E5%88%87%E6%8D%A2%E7%BB%83%E4%B9%A0)
      - [6.4.3 获取元素节点的子节点](#643-%E8%8E%B7%E5%8F%96%E5%85%83%E7%B4%A0%E8%8A%82%E7%82%B9%E7%9A%84%E5%AD%90%E8%8A%82%E7%82%B9)
      - [6.4.4 获取父节点和兄弟节点](#644-%E8%8E%B7%E5%8F%96%E7%88%B6%E8%8A%82%E7%82%B9%E5%92%8C%E5%85%84%E5%BC%9F%E8%8A%82%E7%82%B9)
      - [6.4.5 元素节点的属性](#645-%E5%85%83%E7%B4%A0%E8%8A%82%E7%82%B9%E7%9A%84%E5%B1%9E%E6%80%A7)
      - [6.4.5 DOM查询练习](#645-dom%E6%9F%A5%E8%AF%A2%E7%BB%83%E4%B9%A0)
      - [6.4.6 DOM查询补充](#646-dom%E6%9F%A5%E8%AF%A2%E8%A1%A5%E5%85%85)
  - [6.5 DOM增删改](#65-dom%E5%A2%9E%E5%88%A0%E6%94%B9)
      - [6.5.1 节点的修改](#651-%E8%8A%82%E7%82%B9%E7%9A%84%E4%BF%AE%E6%94%B9)
      - [6.5.2 增删改练习一](#652-%E5%A2%9E%E5%88%A0%E6%94%B9%E7%BB%83%E4%B9%A0%E4%B8%80)
      - [6.5.3 增删改练习二](#653-%E5%A2%9E%E5%88%A0%E6%94%B9%E7%BB%83%E4%B9%A0%E4%BA%8C)
      - [6.5.4 超链接a的索引问题](#654-%E8%B6%85%E9%93%BE%E6%8E%A5a%E7%9A%84%E7%B4%A2%E5%BC%95%E9%97%AE%E9%A2%98)
      - [6.5.5  操作`CSS`内联样式](#655--%E6%93%8D%E4%BD%9Ccss%E5%86%85%E8%81%94%E6%A0%B7%E5%BC%8F)
      - [6.5.6 获取元素样式](#656-%E8%8E%B7%E5%8F%96%E5%85%83%E7%B4%A0%E6%A0%B7%E5%BC%8F)
      - [6.5.7 操作其他样式的属性](#657-%E6%93%8D%E4%BD%9C%E5%85%B6%E4%BB%96%E6%A0%B7%E5%BC%8F%E7%9A%84%E5%B1%9E%E6%80%A7)
- [7. 事件](#7-%E4%BA%8B%E4%BB%B6)
  - [7.1 `JS`事件简介](#71-js%E4%BA%8B%E4%BB%B6%E7%AE%80%E4%BB%8B)
  - [7.2 div跟随鼠标移动](#72-div%E8%B7%9F%E9%9A%8F%E9%BC%A0%E6%A0%87%E7%A7%BB%E5%8A%A8)
  - [7.3 事件冒泡](#73-%E4%BA%8B%E4%BB%B6%E5%86%92%E6%B3%A1)
  - [7.4 事件的委派](#74-%E4%BA%8B%E4%BB%B6%E7%9A%84%E5%A7%94%E6%B4%BE)
  - [7.5 事件的绑定](#75-%E4%BA%8B%E4%BB%B6%E7%9A%84%E7%BB%91%E5%AE%9A)
  - [7.6 事件的传播](#76-%E4%BA%8B%E4%BB%B6%E7%9A%84%E4%BC%A0%E6%92%AD)
  - [7.7 拖拽一](#77-%E6%8B%96%E6%8B%BD%E4%B8%80)
  - [7.8 拖拽二](#78-%E6%8B%96%E6%8B%BD%E4%BA%8C)
  - [7.9 拖拽三](#79-%E6%8B%96%E6%8B%BD%E4%B8%89)
  - [7.10 滚轮的事件](#710-%E6%BB%9A%E8%BD%AE%E7%9A%84%E4%BA%8B%E4%BB%B6)
  - [7.11 键盘事件](#711-%E9%94%AE%E7%9B%98%E4%BA%8B%E4%BB%B6)
  - [7.12 键盘控制div移动](#712-%E9%94%AE%E7%9B%98%E6%8E%A7%E5%88%B6div%E7%A7%BB%E5%8A%A8)
- [8. `BOM`](#8-bom)
  - [8.1 `BOM`简介](#81-bom%E7%AE%80%E4%BB%8B)
  - [8.2 navigator](#82-navigator)
  - [8.3 History](#83-history)
  - [8.4 Location](#84-location)
  - [8.5 定时调用](#85-%E5%AE%9A%E6%97%B6%E8%B0%83%E7%94%A8)
  - [8.6 定时切换图片](#86-%E5%AE%9A%E6%97%B6%E5%88%87%E6%8D%A2%E5%9B%BE%E7%89%87)
  - [8.7 定时器控制div移动](#87-%E5%AE%9A%E6%97%B6%E5%99%A8%E6%8E%A7%E5%88%B6div%E7%A7%BB%E5%8A%A8)
  - [8.8 延时调用](#88-%E5%BB%B6%E6%97%B6%E8%B0%83%E7%94%A8)
  - [8.9 定时调用练习一](#89-%E5%AE%9A%E6%97%B6%E8%B0%83%E7%94%A8%E7%BB%83%E4%B9%A0%E4%B8%80)
  - [8.10 定时器调用练习二](#810-%E5%AE%9A%E6%97%B6%E5%99%A8%E8%B0%83%E7%94%A8%E7%BB%83%E4%B9%A0%E4%BA%8C)
  - [8.11定时器练习三](#811%E5%AE%9A%E6%97%B6%E5%99%A8%E7%BB%83%E4%B9%A0%E4%B8%89)
  - [8.12 轮播图](#812-%E8%BD%AE%E6%92%AD%E5%9B%BE)
  - [8.13 类的操作](#813-%E7%B1%BB%E7%9A%84%E6%93%8D%E4%BD%9C)
  - [8.14 二级菜单](#814-%E4%BA%8C%E7%BA%A7%E8%8F%9C%E5%8D%95)
  - [8.15 JSON](#815-json)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

##  1. JavaScript简介

### 1.1 JavaScript简介

**计算机编程语言**：计算机就是一个由人来控制的机器，人让它干嘛，它就得干嘛。计算机编程语言就是人和计算机交流的工具，人类通过语言来控制、操作计算机。编程语言和中文、英文本质上没有区别，只是语法比较特殊

**语言的发展**∶纸带机（机器语言）、汇编语言（符号语言）、现代语言（高级语言）

**JavaScript简史**：

- JavaScript诞生于1995年，它的出现主要是用于处理网页中的前端验证。所谓的前端验证，就是指检查用户输入的内容是否符合一定的规则。比如︰用户名的长度，密码的长度，邮箱的格式等
- JavaScript是由网景公司发明，起初命名为LiveScript，后来由于SUN公司的介入更名为了JavaScript。1996年微软公司在其最新的IE3浏览器中引入了自己对JavaScript的实现JScript。于是在市面上存在两个版本的JavaScript，一个网景公司的JavaScript和微软的JScript。为了确保不同的浏览器上运行的JavaScript标准一致，所以几个公司共同定制了JS的标准名命名为ECMAScript
- JavaScript发展事件

```
年份            事件
1995年          网景公司开发了JavaScript
1996年          微软发布了和JavaScript兼容的JScript
1997年          ECMAscript第1版（ECMA-262 )
1998年          ECMAScript 第2版
1998年          DOM Level1的制定
1998年          新型语言DHTML登场
1999年          ECMAScript第3版
2000年          DOM Level2的制定
2002年          ISO/ IEC16262;2002的确立
2004年          DOM Level3的制定
2005年          新型语言AJAX登场
2009年          ECMAScript第5版
2009年          新型语言HTML5登场
```

**JavaScript实现**：ECMAScript是JavaScript标准，所以一般情况下这两个词为是一个意思，但是实际上JavaScript的含义却要更大一些。一个完整的JavaScript实现包含了三个部分：ECMAScript、DOM和BOM。不同的浏览器厂商对该标准会有不同的实现，ECMAScript这个标准需要由各个厂商去实现

```
浏览器                   JavaScript实现方式
FireFox                 SpiderMonkey
lnternet Explorer       JScript/Chakra
Safari                  JavaScriptCore
Chrome                  v8
Carakan                 Carakan
```

**JavaScript特点**：解释型语言、动态语言、基于原型的面向对象、类似于C和Java的语法结构

 **解释型语言**：JavaScript是一门解释型语言，所谓解释型语言不需要被编译为机器码再执行，而是直接执行。由于少了编译这一步骤，所以解释型语言开发起来尤为轻松，但是解释型语言运行较慢也是它的劣势。不过解释型语言中使用了JIT技术，使得运行速度得以改善。

**类似于C和Java 的语法结构**： JavaScript的语法结构与C和Java很像，向for、if、while等语句和Java的基本上是一模一样的

**动态语言**：JavaScript是一门动态语言，所谓的动态语言可以暂时理解为在语言中的一切内容都是不确定的。比如一个变量，这一时刻是个整型，下一时刻可能会变成字符串了。当然这个问题以后再谈。不过在补充一句动态语言相比静态语言性能上要差一些，不过由于JavaScript中应用的JIT技术，所以JS可能是运行速度最快的动态语言了

JavaScript `HelloWorld`代码演示：`06.HelloWorld.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<!--JS代码需要编写到script标签中-->
		<script type="text/javascript">
			/*
			 * 控制浏览器弹出一个警告框
			 * alert("哥，你真帅啊！！");
			 */
			/*
			 * 让计算机在页面中输出一个内容
			 * document.write()可以向body中输出一个内容
			 * document.write("看我出不出来~~~");
			 */
			/*
			 * 向控制台输出一个内容
			 * console.log()的作用是向控制台输出一个内容
			 * console.log("你猜我在哪出来呢？");
			 */
			alert("哥，你真帅啊！！");
			document.write("看我出不出来~~~");
			console.log("你猜我在哪出来呢？");
		</script>
	</head>
	<body>
	</body>
</html>
```



## 2. JS语法

### 1.1  js编写位置

 **JS编写位置**：目前学习的JS全都是客户端的JS，也就是说全都是需要在浏览器中运行的，所以JS代码全都需要在网页中编写。JS代码需要编写到`<script>`标签中，一般将script标签写到head中(和style标签有点像)

**`<script>`标签属性**：

- type：  默认值text/javascript可以不写，不写也是这个值
- src：   当需要引入一个外部的js文件时，使用该属性指向文件的地址

**严格区分大小写**：JavaScript是严格区分大小写的，也就是abc和Abc会被解析器认为是两个不同的东西

**code演示**：`07.JS编写位置.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<!--
			可以将js代码编写到外部js文件中，然后通过script标签引入
			写到外部文件中可以在不同的页面中同时引用，也可以利用到浏览器的缓存机制
			推荐使用的方式
		-->
		<!--
			script标签一旦用于引入外部文件了，就不能在编写代码了，即使编写了浏览器也会忽略
			如果需要则可以在创建一个新的script标签用于编写内部代码
		-->
		<script type="text/javascript" src="js/script.js"></script>
		<script type="text/javascript">
			alert("我是内部的JS代码");
		</script>
		
		<!--
			可以将js代码编写到script标签	
		<script type="text/javascript">
			
			alert("我是script标签中的代码！！");
			
		</script>
		-->
	</head>
	<body>
		<!--
			可以将js代码编写到标签的onclick属性中
			当我们点击按钮时，js代码才会执行
			
			虽然可以写在标签的属性中，但是他们属于结构与行为耦合，不方便维护，不推荐使用
		-->
		<button onclick="alert('讨厌，你点我干嘛~~');">点我一下</button>
		
		<!--
			可以将js代码写在超链接的href属性中，这样当点击超链接时，会执行js代码
		-->
		<a href="javascript:alert('让你点你就点！！');">你也点我一下</a>
		<a href="javascript:;">你也点我一下</a>
		
	</body>
</html>
```

### 1.2 注释

**注释**：注释中的内容不会被解析器解析执行，但是会在源码中显示，一般会使用注释对程序中的内容进行解释，JS中的注释和Java的的一致

**两种JS注释**：

- 单行注释∶`//注释内容`
- 多行注释∶`/*注释内容*/`

**code演示**：`08.基本语法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 	多行注释
			 	JS注释
			 	多行注释，注释中的内容不会被执行，但是可以在源代码中查看
			 		要养成良好的编写注释的习惯，也可以通过注释来对代码进行一些简单的调试
			 */
			
			//单行注释
			//alert("hello");
			//document.write("hello");
			console.log("hello"); //该语句用来在控制台输出一个日志
			
			/*
			 * 1.JS中严格区分大小写
			 * 2.JS中每一条语句以分号(;)结尾
			 * 		- 如果不写分号，浏览器会自动添加，但是会消耗一些系统资源，
			 * 			而且有些时候，浏览器会加错分号，所以在开发中分号必须写
			 * 3.JS中会忽略多个空格和换行，所以可以利用空格和换行对代码进行格式化
			 * 
			 */
			alert("hello");
		</script>
	</head>
	<body>
		
	</body>
</html>
```

###  1.3 变量

**变量**：变量的作用是给某一个值或对象标注名称。比如程序中有一个值123，这个值是需要反复使用的，这个时候最好将123这个值赋值给一个变量，然后通过变量去使用123这个值

**变量的声明和赋值**：

```
变量的声明：使用var关键字声明一个变量。var a;
变量的赋值：使用=为变量赋值。a = 123;   
声明和赋值同时进行：var a = 123;
```

**code演示**：`09.字面量和变量.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 字面量，都是一些不可改变的值
			 * 		比如 ：1 2 3 4 5 
			 * 		字面量都是可以直接使用，但是我们一般都不会直接使用字面量
			 * 
			 * 变量    变量可以用来保存字面量，而且变量的值是可以任意改变的
			 * 		变量更加方便我们使用，所以在开发中都是通过变量去保存一个字面量，
			 * 		而很少直接使用字面量
			 * 		可以通过变量对字面量进行描述
			 */
			
			//声明变量
			//在js中使用var关键字来声明一个变量
			var a;
			
			//为变量赋值
			a = 123;
			a = 456;
			a = 123124223423424;
			//声明和赋值同时进行
			var b = 789;
			var c = 0;
			var age = 80;
			console.log(age);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  1.4 标识符

**标识符**：所谓标识符，就是指变量、函数、属性的名字，或函数的参数

**标识符规则**：标识符可以是按照下列格式规则组合起来的一或多个字符

- JavaScript中的标识符不能是关键字和保留字符
- 第一个字符必须是一个字母、下划线(_)或一个美元符号( $ )
- 其他字符可以是字母、下划线、美元符号或数字
- ECMAScript标识符采用驼峰命名法

**关键字**：break、do、instanceof、typeof、case、else、new、var、catch、finally、return、void、continue、for、switch、while、default、if、throw、delete、in、try、function、this、with、debugger、false、true、null

**保留字符**：class、enum、extends、super、const、export、import、implements、let、private、public、yield、interface、package、protected、static

**其他不建议使用的标识符**：abstract、double、goto、native、static、boolean、enum、implements、package、super、byte、export、import、private、synchronize、char、extends、int、protected、throws、class、final、interface、public、transient、const、float、long、short、volatile、arguments、encodeURI、lnfinity、Number、RegExp、undefined、isFinite、Object、string、Boolean、Error、RangeError、parseFloat、syntaxError、Date、eval、JSON、ReferenceError、TypeError、decodeURl、EvalError、Math、URIError、decodeURIComponent、Function、NaN、isNaN、parselnt、Array、encodeURICOmponent

**code演示**：`10.标识符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 标识符
			 * 	- 在JS中所有的可以自主命名的都可以称为是标识符
			 * 	- 例如：变量名、函数名、属性名都属于标识符
			 * 	- 命名一个标识符时需要遵守如下的规则：
			 * 		1.标识符中可以含有字母、数字、_、$
			 * 		2.标识符不能以数字开头
			 * 		3.标识符不能是ES中的关键字或保留字
			 * 		4.标识符一般都采用驼峰命名法
			 * 			- 首字母小写，每个单词的开头字母大写，其余字母小写
			 * 			helloWorld xxxYyyZzz
			 * 
			 * 	- JS底层保存标识符时实际上是采用的Unicode编码，
			 * 		所以理论上讲，所有的utf-8中含有的内容都可以作为标识符
			 */
			/*var if = 123;
			
			console.log(if);*/

			//千万不要这么用
			var 锄禾日当午 = 789;
			console.log(锄禾日当午);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  1.5  数据类型

**数据类型**：数据类型决定了一个数据的特征，比如∶123和"123”，直观上看这两个数据都是123，但实际上前者是一个数字，而后者是一个字符串。不同的数据类型在进行操作时会有很大的不同

**JavaScript 数据类型**：

- 5种基本数据类型：字符串型 (String)、数值型(Number)、布尔型(Boolean)、null型(Null)、undefined型 (Undefined)
- 5种基本数据类型的类型都称为Object，所以JavaScript中共有六种数据类型

**typeof运算符**：

- 使用typeof操作符可以用来检查一个变量的数据类型
- 使用方式：typeof数据，例如typeof 123

**typeof运算符运算结果**：

```
typeof表达式             结果
typeof 数值             number
typeof 字符串           string
typeof 布尔型           boolean
typeof undefined       undefined
typeof null            object
```

##### 1.5.1 String (字符串型)

**字符串**：String用于表示一个字符序列，即字符串。字符串需要使用'或"括起来。将其他数据类型转换为字符串有三种方式: toString、String()、拼串

**转义字符**：

```
转义字符     含义
\n          换行
\\          斜杠
\t          制表
\'          单引号
\b          空格
\"          双引号
\r          回车
```

**code演示**：`01.数据类型.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
			/*
			 * 数据类型指的就是字面量的类型
			 *  在JS中一共有六种数据类型
			 * 		String 字符串
			 * 		Number 数值
			 * 		Boolean 布尔值
			 * 		Null 空值
			 * 		Undefined 未定义
			 * 		Object 对象
			 * 
			 * 其中String Number Boolean Null Undefined属于基本数据类型
			 * 	而Object属于引用数据类型
			 */
			
			/*
			 * String字符串
			 * 	- 在JS中字符串需要使用引号引起来
			 * 	- 使用双引号或单引号都可以，但是不要混着用
			 * 	- 引号不能嵌套，双引号内部不能放双引号，单引号内部不能放单引号
			 */
			var str = 'hello';
			
			str = '我说:"今天天气真不错！"';
			
			/*
			 	在字符串中我们可以使用\作为转义字符，
			 		当表示一些特殊符号时可以使用\进行转义
			 		
			 		\" 表示 "
			 		\' 表示 '
			 		\n 表示换行
			 		\t 制表符
			 		\\ 表示\
			 * */
			str = "我说:\"今天\t天气真不错！\"";
			
			str = "\\\\\\";
			
			//输出字面量 字符串str
			//alert("str");
			//输出变量str
			//alert(str);
			var str2 = "hello";
			str2 = "你好";
			str2 = 3;
			//alert("hello，你好");
			
			//console.log("我就是不出来~~~");
		</script>
	</head>
	<body>
	</body>
</html>
```

##### 1.5.2 Number(数值型)

**数值型(Number)**：Number类型用来表示整数和浮点数，最常用的功能就是用来表示10进制的整数和浮点数。Number表示的数字大小是有限的，范围是︰±1.7976931348623157e+308。如果超过了这个范围，则会返回±Infinity

**NaN**：NaN表示非数值( Not a Number)是一个特殊的数值，JS中当对数值进行计算时没有结果返回，则返回NaN

**数值的转换**：Number()、parseInt()和parseFloat()这三个函数可以把非数值转换为数值。Number()可以用来转换任意类型的数据，而后两者只能用于转换字符串。parseInt()只会将字符串转换为整数，而parseFloat()可以转换为浮点数

**code演示**：`02.Number.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 在JS中所有的数值都是Number类型，
			 * 	包括整数和浮点数（小数）
			 * 
			 * JS中可以表示的数字的最大值
			 * 	Number.MAX_VALUE
			 * 		1.7976931348623157e+308
			 * 
			 * 	Number.MIN_VALUE 大于0的最小值
			 * 		5e-324
			 * 
			 *  如果使用Number表示的数字超过了最大值，则会返回一个
			 * 		Infinity 表示正无穷
			 * 		-Infinity 表示负无穷
			 * 		使用typeof检查Infinity也会返回number
			 *  NaN 是一个特殊的数字，表示Not A Number
			 * 		使用typeof检查一个NaN也会返回number
			 */
			//数字123
			var a = 123;
			//字符串123
			var b = "123";
			/*
			 	可以使用一个运算符 typeof
			 		来检查一个变量的类型
			 	语法：typeof 变量	
			 	检查字符串时，会返回string
			 	检查数值时，会返回number
			 * */
			//console.log(typeof b);
			a = -Number.MAX_VALUE * Number.MAX_VALUE;		
			a = "abc" * "bcd";
			a = NaN;
			//console.log(typeof a);
			a = Number.MIN_VALUE;
			//console.log(a);
			/*
			 * 在JS中整数的运算基本可以保证精确
			 */
			var c = 1865789 + 7654321;
			/*
			 * 如果使用JS进行浮点运算，可能得到一个不精确的结果
			 * 	所以千万不要使用JS进行对精确度要求比较高的运算	
			 */
			var c = 0.1 + 0.2;
			console.log(c);
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  1.5.3 Boolean(布尔型)

**布尔型**：布尔型也被称为逻辑值类型或者真假值类型。布尔型只能够取真( true ）和假( false )两种数值，除此以外其他的值都不被支持。其他的数据类型也可以通过Boolean()函数转换为布尔类型

**code演示**：`03.Boolean.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * Boolean 布尔值
			 * 	布尔值只有两个，主要用来做逻辑判断
			 * 	true
			 * 		- 表示真
			 * 	false
			 * 		- 表示假
			 * 
			 * 使用typeof检查一个布尔值时，会返回boolean
			 */
			var bool = false;
			console.log(typeof bool);
			console.log(bool);
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  1.5.4 Null & Undefined

**Nul l类型**：Null类型只有一个特殊值，这个特殊的值是null。从语义上看null表示的是一个空的对象。所以使用typeof检查null会返回一个Object。undefined值实际上是由null值衍生出来的，所以如果比较undefined和null是否相等，会返回true

**Undefined类型**：Undefined类型只有一个值，即特殊的undefined。在使用var声明变量但未对其加以初始化时，这个变量的值就是undefined。需要注意的是typeof对没有初始化和没有声明的变量都会返回undefined

```javascript
// 在使用var声明变量但未对其加以初始化时，这个变量的值就是undefined。例如︰
var message;
// message的值就是undefined
```

**code演示**：`04.Null和Undefined.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * Null（空值）类型的值只有一个，就是null
			 * 	null这个值专门用来表示一个为空的对象
			 * 	使用typeof检查一个null值时，会返回object
			 * 
			 * Undefined（未定义）类型的值只有一个，就undefind
			 * 	当声明一个变量，但是并不给变量赋值时，它的值就是undefined
			 * 	使用typeof检查一个undefined时也会返回undefined
			 */
			var a = null;
			var b = undefined;
			console.log(typeof b);
		</script>
	</head>
	<body>
	</body>
</html>
```

### 1.6 数据类型转换

**类型转换**：指将一个数据类型强制转换为其他的数据类型。JS中的类型转换主要指，将其他的数据类型转换为String、Number、Boolean

#####  1.6.1 字符串转换

**字符串转换**：将其他数据类型转换为字符串有三种方式: toString、String()、拼串

**code演示**：`05.强制类型转换.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 强制类型转换
			 * 	- 指将一个数据类型强制转换为其他的数据类型
			 * 	- 类型转换主要指，将其他的数据类型，转换为
			 * 		String Number Boolean
			 * 		
			 */
			
			/*
			 * 将其他的数据类型转换为String
			 * 	方式一：
			 * 		- 调用被转换数据类型的toString()方法
			 * 		- toString()方法不会影响到原变量，它会将转换的结果返回
			 * 		- 但是注意：null和undefined这两个值没有toString()方法，
			 * 			如果调用他们的方法，会报错
			 * 
			 *  方式二：
			 * 		- 调用String()函数，并将被转换的数据作为参数传递给函数
			 * 		- 使用String()函数做强制类型转换时，
			 * 			对于Number和Boolean实际上就是调用的toString()方法
			 * 			但是对于null和undefined，就不会调用toString()方法
			 * 				它会将 null 直接转换为 "null"
			 * 				将 undefined 直接转换为 "undefined"
			 * 
			 */
			
			var a = 123;
			
			//调用a的toString()方法
			//调用xxx的yyy()方法，就是xxx.yyy()
			a = a.toString();
			
			a = true;
			a = a.toString();
			
			a = null;
			//a = a.toString(); //报错
			
			a = undefined;
			//a = a.toString(); //报错
			
			a = 123;
			
			//调用String()函数，来将a转换为字符串
			a = String(a);
			
			a = null;
			a = String(a);
			
			a = undefined;
			a = String(a);
			
			console.log(typeof a);
			console.log(a);
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  1.6.2 数值转换 & 进制转换

**数值的转换**：Number()、parseInt()和parseFloat()这三个函数可以把非数值转换为数值。Number()可以用来转换任意类型的数据，而后两者只能用于转换字符串。parseInt()只会将字符串转换为整数，而parseFloat()可以转换为浮点数

**code演示**：`06.转换为Number.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
		
			/*
			 * 将其他的数据类型转换为Number
			 * 	 转换方式一：
			 * 		使用Number()函数
			 * 			- 字符串 --> 数字
			 * 				1.如果是纯数字的字符串，则直接将其转换为数字
			 * 				2.如果字符串中有非数字的内容，则转换为NaN
			 * 				3.如果字符串是一个空串或者是一个全是空格的字符串，则转换为0
			 * 			- 布尔 --> 数字
			 * 				true 转成 1
			 * 				false 转成 0
			 * 
			 * 			- null --> 数字     0
			 * 
			 * 			- undefined --> 数字 NaN
			 * 
			 * 转换方式二：
			 * 		- 这种方式专门用来对付字符串
			 * 		- parseInt() 把一个字符串转换为一个整数
			 * 		- parseFloat() 把一个字符串转换为一个浮点数
			 */
			
			var a = "123";
			
			//调用Number()函数来将a转换为Number类型
			a = Number(a);
			
			a = false;
			a = Number(a);
			
			a = null;
			a = Number(a);
			
			a = undefined;
			a = Number(a);
			
			a = "123567a567px";
			//调用parseInt()函数将a转换为Number
			/*
			 * parseInt()可以将一个字符串中的有效的整数内容去出来，
			 * 	然后转换为Number
			 */
			a = parseInt(a);//123567
			
			/*
			 * parseFloat()作用和parseInt()类似，不同的是它可以获得有效的小数
			 */
			a = "123.456.789px";
			a = parseFloat(a);//123
			
			/*
			 * 如果对非String使用parseInt()或parseFloat()
			 * 	它会先将其转换为String然后在操作
			 */
			a = true;
			a = parseInt(a);
			
			a = 198.23;
			a = parseInt(a);
			console.log(typeof a);
			console.log(a);
		</script>
	</head>
	<body>
	</body>
</html>
```

**数值的进制及转换**：`07.其他的进制的数字.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			var a = 123;
			
			/*
			 * 在js中，如果需要表示16进制的数字，则需要以0x开头
			 * 			如果需要表示8进制的数字，则需要以0开头
			 * 			如果要要表示2进制的数字，则需要以0b开头
			 * 				但是不是所有的浏览器都支持
			 * 	
			 */
			
			//十六进制
			a = 0x10;
			a = 0xff;
			a = 0xCafe;
			
			//八进制数字
			a = 070;
			
			//二进制数字
			//a = 0b10;
			
			//向"070"这种字符串，有些浏览器会当成8进制解析，有些会当成10进制解析
			a = "070";
			
			//可以在parseInt()中传递一个第二个参数，来指定数字的进制
			a = parseInt(a,10);
			
			console.log(typeof a);
			console.log(a);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  1.6.3 布尔型转换

**布尔型转换规则**：其他的数据类型也可以通过Boolean()函数转换为布尔类型

| 数据类型  | 转换为true     | 转换为false  |
| --------- | -------------- | ------------ |
| Boolean   | true           | false        |
| String    | 任何非空字符串 | ""(空字符串) |
| Number    | 任何非0数字    | 0和NaN       |
| Object    | 任何对象       | null         |
| Undefined | n/a            | undefined    |

**code演示**：`08.转换为Boolean.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 将其他的数据类型转换为Boolean
			 * 	- 使用Boolean()函数
			 * 		- 数字 ---> 布尔
			 * 			- 除了0和NaN，其余的都是true
			 * 
			 * 		- 字符串 ---> 布尔
			 * 			- 除了空串，其余的都是true
			 * 
			 * 		- null和undefined都会转换为false
			 * 
			 * 		- 对象也会转换为true
			 * 		
			 */
			
			var a = 123; //true
			a = -123; //true
			a = 0; //false
			a = Infinity; //true
			a = NaN; //false
			
			//调用Boolean()函数来将a转换为布尔值
			a = Boolean(a);
			
			a = " ";
			
			a = Boolean(a);
			
			a = null; //false
			a = Boolean(a);
			
			a = undefined; //false
			a = Boolean(a);
			
			console.log(typeof a);
			console.log(a);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

## 3 运算符

**运算符**：运算符可以对数据进行运算，运算符包括算数运算符、位运算符、关系运算符等

### 3.1 算术运算符

算数运算符：算数运算符顾名思义就是进行算数操作的运算符

JS中的算数运算符：

```
运算符         说明
+             加法
-             减法
*             乘法
/             除法
%             取模
++(前置)       自增
++（后置)      自增
--(前置)       自减
--（后置)      自减
+            符号不变
-            符号反转
```

**code演示**：`09.运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 运算符也叫操作符
			 * 	通过运算符可以对一个或多个值进行运算,并获取运算结果
			 * 	比如：typeof就是运算符，可以来获得一个值的类型
			 * 		它会将该值的类型以字符串的形式返回
			 * 		number string boolean undefined object
			 * 
			 * 	算数运算符
			 * 		当对非Number类型的值进行运算时，会将这些值转换为Number然后在运算
			 * 			任何值和NaN做运算都得NaN
			 * 
			 * 		+
			 * 			+可以对两个值进行加法运算，并将结果返回
			 * 			 如果对两个字符串进行加法运算，则会做拼串
			 * 				会将两个字符串拼接为一个字符串，并返回
			 * 			任何的值和字符串做加法运算，都会先转换为字符串，然后再和字符串做拼串的操作
			 * 		-
			 * 			- 可以对两个值进行减法运算，并将结果返回
			 * 
			 * 		*
			 * 			* 可以对两个值进行乘法运算
			 * 		/
			 * 			/ 可以对两个值进行除法运算
			 * 		%
			 * 			% 取模运算（取余数）
			 */
			var a = 123;
			
			var result = typeof a;
			
			//console.log(typeof result);
			
			result = a + 1;
			
			result = 456 + 789;
			
			result = true + 1;
			
			result = true + false;
			
			result = 2 + null; // 2
			
			result = 2 + NaN; // NaN
			
			result = "你好" + "大帅哥"; // 拼串
			
			var str = "锄禾日当午，" +
					  "汗滴禾下土，" +
					  "谁知盘中餐，" +
					  "粒粒皆辛苦";
					  
					  
			result = 123 + "1"; // 拼串
			
			result = true + "hello"; // 拼串
			
			//任何值和字符串相加都会转换为字符串，并做拼串操作
			/*
			 * 我们可以利用这一特点，来将一个任意的数据类型转换为String
			 * 	我们只需要为任意的数据类型 + 一个 "" 即可将其转换为String
			 * 	这是一种隐式的类型转换，由浏览器自动完成，实际上它也是调用String()函数
			 */
			var c = 123;
			
			c = c + ""; // 拼串
			
			//c = null;
			
			//c = c + "";
			
			
			//console.log(result);
			//console.log(typeof c);
			//console.log("c = "+c);
			// 拼串
			result = 1 + 2 + "3"; //33
			// 拼串
			result = "1" + 2 + 3; //123
			
			result = 100 - 5; // 95
			
			result = 100 - true; // 99
			
			result = 100 - "1";  // 99
			
			result = 2 * 2;      // 4
			
			result = 2 * "8";   // 16
			
			result = 2 * undefined; // NaN
			
			result = 2 * null;   // 0
			
			result = 4 / 2;   // 2
			
			result = 3 / 2;   // 1.5 
			
			/*
			 * 任何值做- * /运算时都会自动转换为Number
			 * 	我们可以利用这一特点做隐式的类型转换
			 * 		可以通过为一个值 -0 *1 /1来将其转换为Number
			 * 		原理和Number()函数一样，使用起来更加简单
			 */
			var d = "123";
			//console.log("result = "+result);
			d = d - 0;
			/*console.log(typeof d);
			console.log(d);*/
			result = 9 % 3;
			result = 9 % 4;
			result = 9 % 5;
			console.log("result = "+result);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.2 符号运算符

符号运算符：

```
+      符号不变
-      符号反转
```

**code演示**：`10.一元运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 一元运算符，只需要一个操作数
			 * 	+ 正号
			 * 		- 正号不会对数字产生任何影响
			 * 	- 负号
			 * 		- 负号可以对数字进行负号的取反
			 * 
			 * 	- 对于非Number类型的值，
			 * 		它会将先转换为Number，然后在运算
			 * 		可以对一个其他的数据类型使用+,来将其转换为number
			 * 		它的原理和Number()函数一样
			 */
			var a = 123;
			a = -a;
			a = true;
			a = "18";
			a = +a;
			/*console.log("a = "+a);
			console.log(typeof a);*/
			
			var result = 1 + +"2" + 3;  // 6
			
			console.log("result = "+result);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.3 自增和自减

自增和自减：自增和自减分为前置运算和后置元素。所谓的前置元素就是将元素符放到变量的前边，而后置将元素符放到变量的后边。前置自增: ++a  后置自减:a--。运算符在前置时，表达式值等于变量原值。运算符在后置时，表达式值等于变量变更以后的值

**code演示**：`11.自增和自减.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 自增 ++
			 * 	 - 通过自增可以使变量在自身的基础上增加1
			 * 	 - 对于一个变量自增以后，原变量的值会立即自增1
			 * 	 - 自增分成两种：后++(a++) 和 前++(++a)	
			 * 		无论是a++ 还是 ++a，都会立即使原变量的值自增1
			 * 			不同的是a++ 和 ++a的值不同
			 * 		a++的值等于原变量的值（自增前的值）
			 * 		++a的值等于新值 （自增后的值）
			 * 
			 * 自减 --
			 * 	- 通过自减可以使变量在自身的基础上减1
			 * 	- 自减分成两种：后--(a--) 和 前--(--a)
			 * 		无论是a-- 还是 --a 都会立即使原变量的值自减1
			 * 			不同的是a-- 和 --a的值不同
			 * 				a-- 是变量的原值 （自减前的值）
			 * 				--a 是变量的新值 （自减以后的值）
			 * 			
			 * 	
			 */
			var num = 10;
			//num--;
			//--num;
			//console.log(num--);
			console.log(--num);
			console.log("num = "+num);
			var a = 1;
			//使a自增1
			//a++;
			//++a;
			//console.log(a++);
			//console.log("++a = " + ++a);
			/*console.log("a++ = " + a++);
			
			console.log("a = "+a);*/
			var c = 10;
			//第一次c++，是在10的基础上自增
			//console.log(c++);
			
			//第二次c++，是在11的基础上自增
			c++;
			
			var d = 20;
			//console.log(++d);//21
			//console.log(++d);//22
			//20 + 22 + 22
			//var result = d++ + ++d + d ;
			//d = 20
			d = d++;
			
			/*
			 * var e = d++;
			 * d = e;
			 */
			//console.log("d = "+d);
		</script>
	</head>
	<body>
	</body>
</html>
```

**code演示**：自增自减练习   `02.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			var n1=10;
			var n2=20;
			var n = n1++; //n1 = 11  n1++ = 10
			
			console.log('n='+n);  // 10
			console.log('n1='+n1); //11
			
			n = ++n1 //n1 = 12  ++n1 =12
			console.log('n='+n); //12
			console.log('n1='+n1); //12
			
			n = n2--;// n2=19 n2--=20
			console.log('n='+n); //20
			console.log('n2='+n2); //19
			
			n = --n2; //n2=18 --n2 = 18
			console.log('n='+n); //18
			console.log('n2='+n2); //18
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.4 逻辑运算符

**逻辑运算符**：逻辑运算符主要是非、与、或。一般情况下使用逻辑运算符会返回一个布尔值。在进行逻辑操作时如果操作数不是布尔类型则会将其转换布尔类型在进行计算。非使用符号!表示，与使用&&表示，或使||表示

**非运算**：

- 非运算符使用!表示
- 非运算符可以应用于任意值，无论值是什么类型，这个运算符都会返回一个布尔值
- 非运算符会对原值取反，比如原值是true使用非运算符会返回false，原值为false使用非运算符会返回true

**与运算**：

- 与运算符使用&&表示
- 与运算符可以应用于任何数据类型，且不一定返回布尔值
- 对于非布尔值运算，会先将非布尔值转换为布尔值。·对布尔值做运算时，如果两个值都为true则返回true ,否则返回false
- 非布尔值时∶如果两个都为true，则返回第二个值，如果两个值中有false则返回靠前的false的值

**或运算**：

- 或运算符使用Ⅱ表示
- 或运算符可以应用于任何数据类型，且不一定返回布尔值。·对于非布尔值运算，会先将非布尔值转换为布尔值
- 对布尔值进行运算时，如果两个值都为false则返回false ,否则返回true
- 非布尔值时∶如果两个都为false ，则返回第二个值，否则返回靠前true的值

**逻辑运算符**：

| 运算符 | 说明          | 短路规则                 |
| ------ | ------------- | ------------------------ |
| !      | 逻辑非（NOT） | 无                       |
| &&     | 逻辑与(AND)   | 若左值为假，则不运算右值 |
| \|\|   | 逻辑或(OR）   | 若左值为真，则不运算右值 |

**code演示**：`03.逻辑运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * JS中为我们提供了三种逻辑运算符
			 * ! 非
			 * 	- !可以用来对一个值进行非运算
			 * 	- 所谓非运算就是值对一个布尔值进行取反操作，
			 * 		true变false，false变true
			 * 	- 如果对一个值进行两次取反，它不会变化
			 * 	- 如果对非布尔值进行元素，则会将其转换为布尔值，然后再取反
			 * 		所以我们可以利用该特点，来将一个其他的数据类型转换为布尔值
			 * 		可以为一个任意数据类型取两次反，来将其转换为布尔值，
			 * 		原理和Boolean()函数一样
			 * 
			 * && 与
			 * 	- &&可以对符号两侧的值进行与运算并返回结果
			 * 	- 运算规则
			 * 		- 两个值中只要有一个值为false就返回false，
			 * 			只有两个值都为true时，才会返回true
			 * 		- JS中的“与”属于短路的与，
			 * 			如果第一个值为false，则不会看第二个值
			 * 
			 * || 或
			 * 	- ||可以对符号两侧的值进行或运算并返回结果
			 * 	- 运算规则：
			 * 		- 两个值中只要有一个true，就返回true
			 * 			如果两个值都为false，才返回false
			 *		- JS中的“或”属于短路的或
			 * 			如果第一个值为true，则不会检查第二个值
			 */
			
			//如果两个值都是true则返回true
			var result = true && true;
			
			//只要有一个false，就返回false
			result = true && false;
			result = false && true;
			result = false && false;
			
			//console.log("result = "+result);
			
			//第一个值为true，会检查第二个值
			//true && alert("看我出不出来！！");
			
			//第一个值为false，不会检查第二个值
			//false && alert("看我出不出来！！");
			
			//两个都是false，则返回false
			result = false || false;
			
			//只有有一个true，就返回true
			result = true || false;
			result = false || true ;
			result = true || true ;
			
			//console.log("result = "+result);
			
			//第一个值为false，则会检查第二个值
			//false || alert("123");
			
			//第一个值为true，则不再检查第二个值
			//true || alert("123");
			var a = false;
			
			//对a进行非运算
			a = !a;
			
			//console.log("a = "+a);
			
			var b = 10;
			b = !!b;
			
			//console.log("b = "+b);
			//console.log(typeof b);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

**非布尔值的与运算和非运算**：对于非布尔值进行与或运算时会先将其转换为布尔值，然后再运算，并且返回原值。具体返回哪个值可以根据短路运算进行推导，运算总是返回发生短路的值

**code演示**： `04.逻辑运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * && || 非布尔值的情况
			 * 	- 对于非布尔值进行与或运算时，
			 * 		会先将其转换为布尔值，然后再运算，并且返回原值
			 * 	- 与运算：
			 * 		- 如果第一个值为true，则必然返回第二个值
			 * 		- 如果第一个值为false，则直接返回第一个值
			 * 
			 * 	- 或运算
			 * 		- 如果第一个值为true，则直接返回第一个值
			 * 		- 如果第一个值为false，则返回第二个值
			 * 
			 */
			//true && true
			//与运算：如果两个值都为true，则返回后边的
			var result = 5 && 6;  // 6
			
			//与运算：如果两个值中有false，则返回靠前的false
			//false && true
			result = 0 && 2;  // 0
			result = 2 && 0;  // 0
			//false &&　false
			result = NaN && 0; // NaN
			result = 0 && NaN; // 0
			
			
			//true || true
			//如果第一个值为true，则直接返回第一个值
			result = 2 || 1;
			result = 2 || NaN;
			result = 2 || 0;
			
			//如果第一个值为false，则直接返回第二个值
			result = NaN || 1;
			result = NaN || 0;
			
			result = "" || "hello"; // hello
			
			result = -1 || "你好";   // -1
			console.log("result = "+result);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.5 赋值运算符

**赋值运算符**：简单的赋值操作符由等于号( = )表示，其作用就是把右侧的值赋给左侧的变量。如果在等于号左边添加加减乘除等运算符，就可以完成复合赋值操作。复合赋值操作：`+=、*=、-=、/=、%=`。比如: a+=10和a=a+10是一样的

**code演示**： `05.赋值运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * =
			 * 	可以将符号右侧的值赋值给符号左侧的变量
			 * += 
			 * 	a += 5 等价于 a = a + 5
			 * -=
			 * 	a -= 5 等价于 a = a - 5
			 * *=
			 * 	a *= 5 等价于 a = a * 5
			 * /=
			 * 	a /= 5 等价于 a = a / 5
			 * %=
			 * 	a %= 5 等价于 a = a % 5
			 * 	
			 */
			var a = 10;
			//a = a + 5;
			//a += 5;
			
			//a -= 5;
			
			//a *= 5;
			
			// a = a%3;
			a %= 3;
			console.log("a = "+a);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.6 关系运算符

**关系运算符**：小于(<)、大于(> )、小于等于(<=)和大于等于(>= )这几个关系运算符用于对两个值进行比较。关系运算符都返回一个布尔值，用来表示两个值之间的关系是否成立

**code演示**：`06.关系运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 通过关系运算符可以比较两个值之间的大小关系，
			 * 	如果关系成立它会返回true，如果关系不成立则返回false
			 * 
			 * > 大于号
			 * 	- 判断符号左侧的值是否大于右侧的值
			 * 	- 如果关系成立，返回true，如果关系不成立则返回false
			 * 
			 * >= 大于等于
			 * 	- 判断符号左侧的值是否大于或等于右侧的值
			 * 
			 * < 小于号
			 * <= 小于等于
			 * 
			 * 非数值的情况
			 * 	- 对于非数值进行比较时，会将其转换为数字然后在比较
			 * 	- 如果符号两侧的值都是字符串时，不会将其转换为数字进行比较
			 * 		而会分别比较字符串中字符的Unicode编码
			 */
			var result = 5 > 10;//false
			result = 5 > 4; //true
			result = 5 > 5; //false
			result = 5 >= 5; //true
			result = 5 >= 4; //true
			result = 5 < 4; //false
			result = 4 <= 4; //true
			//console.log("result = "+result);
			//console.log(1 > true); //false
			//console.log(1 >= true); //true
			//console.log(1 > "0"); //true
			//console.log(10 > null); //true
			//任何值和NaN做任何比较都是false
			//console.log(10 <= "hello"); //false
			//console.log(true > false); //true
			
			//console.log("1" < "5"); //true
			//console.log("11" < "5"); //true
			
			//比较两个字符串时，比较的是字符串的字符编码
			//console.log("a" < "b");//true
			//比较字符编码时是一位一位进行比较
			//如果两位一样，则比较下一位，所以借用它来对英文进行排序
			//console.log("abc" < "bcd");//true
			//比较中文时没有意义
			//console.log("戒" > "我"); //true
			
			//如果比较的两个字符串型的数字，可能会得到不可预期的结果
			//注意：在比较两个字符串型的数字时，一定一定一定要转型
			console.log("11123123123123123123" < +"5"); //true
		</script>
	</head>
	<body>
	</body>
</html>
```

如果符号两侧的值都是字符串时，不会将其转换为数字进行比较。而会分别比较字符串中字符的Unicode编码

**code演示**：Unicode编码演示  `07.编码.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 在字符串中使用转义字符输入Unicode编码
			 * 	\u四位编码
			 */
			console.log("\u2620");
			
		</script>
	</head>
	<body>
		<!--在网页中使用Unicode编码
			&#编码; 这里的编码需要的是10进制
		-->
		<h1 style="font-size: 200px;">&#9760;</h1>
		<h1 style="font-size: 200px;">&#9856;</h1>
		
	</body>
</html>
```

### 3.7 相等运算符 | 全等运算符

**相等运算符**：JS中使用==来判断两个值是否相等，如果相等则返回true。使用!=来表示两个值是否不相等，如果不等则返回true。注意: null和undefined使用==判断时是相等的

**相等运算符举例**：

```
表达式                            值
null == undefined               true
true == 1                       true
"NaN"== NaN                     false
true == 2                       false
5== NaN                         false
undefined == 0                  false
NaN == NaN                      false
null == 0                       false
NaN != NaN                      true
“5”== 5                         true
false == 0                      true
```

**全等运算符**：===表示全等，他和==基本一致，不过==在判断两个值时会进行自动的类型转换，而===不会。也就是说”55"==55会返回true，而"55"===55会返回false。同样还有!==表示不全等，同样比较时不会自动转型。也就是说"55"!=55会返回false，而”55"!==55会返回true

**code演示**：`08.相等运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 相等运算符用来比较两个值是否相等，
			 * 	如果相等会返回true，否则返回false
			 * 
			 * 使用 == 来做相等运算
			 * 	- 当使用==来比较两个值时，如果值的类型不同，
			 * 		则会自动进行类型转换，将其转换为相同的类型
			 * 		然后在比较
			 * 不相等
			 * 	 不相等用来判断两个值是否不相等，如果不相等返回true，否则返回false
			 * 	- 使用 != 来做不相等运算
			 * 	- 不相等也会对变量进行自动的类型转换，如果转换后相等它也会返回false
			 * 
			 * 		
			 *  ===
			 * 		全等
			 * 		- 用来判断两个值是否全等，它和相等类似，不同的是它不会做自动的类型转换
			 * 			如果两个值的类型不同，直接返回false
			 * 	!==
			 * 		不全等
			 * 		- 用来判断两个值是否不全等，和不等类似，不同的是它不会做自动的类型转换
			 * 			如果两个值的类型不同，直接返回true
			 */
			
			//console.log(1 == 1); //true
			var a = 10;
			//console.log(a == 4); //false
			
			//console.log("1" == 1); //true
			
			//console.log(true == "1"); //true
			
			//console.log(null == 0); //false
			
			/*
			 * undefined 衍生自 null
			 * 	所以这两个值做相等判断时，会返回true
			 */
			//console.log(undefined == null);
			
			/*
			 * NaN不和任何值相等，包括他本身
			 */
			//console.log(NaN == NaN); //false
			var b = NaN;
			//判断b的值是否是NaN
			//console.log(b == NaN); // false
			/*
			 * 可以通过isNaN()函数来判断一个值是否是NaN
			 * 	如果该值是NaN则返回true，否则返回false
			 */
			//console.log(isNaN(b));
			
			//console.log(10 != 5); //true
			//console.log(10 != 10); //false
			//console.log("abcd" != "abcd"); //false
			//console.log("1" != 1);//false
			//console.log("123" === 123);//false
			//console.log(null === undefined);//false
			console.log(1 !== "1"); //true
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.8 三元运算符

**三元运算符**：三元运算符也称为条件运算符，通常运算符写为`? :`。这个运算符需要三个操作数，第一个操作数在?之前，第二个操作数在?和:之间，第三个操作数在:之后。例如：`x > 0 ? x :-x`   表示求x的绝对值。这个的例子，首先会执行x>0，如果返回true则执行冒号左边的代码，并将结果返回，这里就是返回x本身，如果返回false则执行冒号右边的代码，并将结果返回

**code演示**：`09.条件运算符.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 条件运算符也叫三元运算符
			 * 	语法：
			 * 		条件表达式?语句1:语句2;
			 * 	- 执行的流程：
			 * 		条件运算符在执行时，首先对条件表达式进行求值，
			 * 			如果该值为true，则执行语句1，并返回执行结果
			 * 			如果该值为false，则执行语句2，并返回执行结果
			 * 		如果条件的表达式的求值结果是一个非布尔值，
			 * 			会将其转换为布尔值然后在运算
			 */
			//false?alert("语句1"):alert("语句2");
			var a = 300;
			var b = 143;
			var c = 50;
			//a > b ? alert("a大"):alert("b大");
			//获取a和b中的最大值
			//var max = a > b ? a : b;
			//获取a b c 中的大值
			//max = max > c ? max : c;
			//这种写法不推荐使用，不方便阅读
			var max = a > b ? (a > c ? a :c) : (b > c ? b : c);
			//console.log("max = "+max);
			//"hello"?alert("语句1"):alert("语句2");
		</script>
	</head>
	<body>
	</body>
</html>
```

###  3.9 运算符的优先级

**逗号**：使用逗号可以在一条语句中执行多次操作。`比如: var num1=1, num2=2, num3=3;`使用逗号运算符分隔的语句会从左到右顺序依次执行

**运算符的优先级**：在表中越靠上优先级越高，优先级越高越优先计算，如果优先级一样，则从左往右计算。但是这个表并不需要记忆，如果遇到优先级不清楚可以使用()来改变优先级

```
.、[]、new()
++、--
!、~、+(单目)、-(单目)、typeof、void、delete
%、*、/
+(双目)、-(双目)
<<、>>、>>>
<、<=、>、>=
==、!==、===
&
^
|
&&
||
? :
=、+=、-=、*=、/=、%=、<<=、>>=、>>>=、&=、N=、|=
,
```

**code演示**：`10.运算符的优先级.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * , 运算符
			 * 	使用,可以分割多个语句，一般可以在声明多个变量时使用,
			 */
			//使用,运算符同时声明多个变量
			//var a , b , c;
			
			//可以同时声明多个变量并赋值
			//var a=1 , b=2 , c=3;
			//alert(b);
			
			/*
			 * 就和数学中一样，在JS中运算符也有优先级，
			 * 	比如：先乘除 后加减
			 * 在JS中有一个运算符优先级的表，
			 * 	在表中越靠上优先级越高，优先级越高越优先计算，
			 * 	如果优先级一样，则从左往右计算。
			 * 但是这个表我们并不需要记忆，如果遇到优先级不清楚
			 * 	可以使用()来改变优先级
			 */
			//var result = 1 + 2 * 3;
			/*
			 * 如果||的优先级高，或者两个一样高，则应该返回3
			 * 如果与的优先级高，则应该返回1
			 * 		
			 */
			var result = 1 || 2 && 3; // 1
			console.log("result = "+result);	
		</script>
	</head>
	<body>
	</body>
</html>
```

##  4. 语句

### 4.1 语句 & 代码块

**语句**：前边的表达式和运算符等内容可以理解成是JS语言中的单词，短语。而语句( statement )就是JS语言中一句一句完整的话。语句是一个程序的基本单位，JS的程序就是由一条一条语句构成的，每一条语句使用;结尾。JS中的语句默认是由上至下顺序执行的，但是也可以通过一些流程控制语句来控制语句的执行顺序

**三种特殊语句**：1.条件判断语句 2.条件分支语句 3.循环语句

**代码块**：代码块是在大括号中所写的语句，以此将多条语句的集合视为一条语句来使用。一般使用代码块将需要一起执行的语句进行分组，需要注意的是，代码块结尾不需要加分号

```javascript
// 代码块
{
    var a = 123;
    a++;
    alert(a) ;
}
```

**code演示**：`11.代码块.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 我们的程序是由一条一条语句构成的
			 * 	语句是按照自上向下的顺序一条一条执行的
			 * 	在JS中可以使用{}来为语句进行分组,
			 * 		同一个{}中的语句我们称为是一组语句，
			 * 		它们要么都执行，要么都不执行，
			 * 		一个{}中的语句我们也称为叫一个代码块
			 * 		在代码块的后边就不用再编写;了
			 * 
			 * 	JS中的代码块，只具有分组的的作用，没有其他的用途
			 * 		代码块内容的内容，在外部是完全可见的
			 */
			{
				var a = 10;	
				alert("hello");
				console.log("你好");
				document.write("语句");
			}
			console.log("a = "+a);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  1.2 if语句

**条件语句**：条件语句是通过判断指定表达式的值来决定执行还是跳过某些语句。最基本的条件语句：`if...else`、`switch...case`

**if...else语句**：if...else语句是一种最基本的控制语句，它让JavaScript可以有条件的执行语句。除了if和else还可以使用else if 来创建多个条件分支

```
if...else语句：
第一种形式: 
          if(expression)
              statement
第二种形式: 
          if(expression)
             statement
          else
               statement
```

**code演示**：`12.流程控制语句.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 流程控制语句
			 * 	- JS中的程序是从上到下一行一行执行的
			 * 	- 通过流程控制语句可以控制程序执行流程，
			 * 		使程序可以根据一定的条件来选择执行
			 *  - 语句的分类：
			 * 		1.条件判断语句
			 * 		2.条件分支语句
			 * 		3.循环语句
			 * 
			 * 
			 * 条件判断语句：
			 * 	- 使用条件判断语句可以在执行某个语句之前进行判断，
			 * 		如果条件成立才会执行语句，条件不成立则语句不执行。
			 *  - if语句
			 * 	- 语法一：
			 * 		if(条件表达式){
			 * 			语句...
			 * 		}
			 * 			
			 * 		if语句在执行时，会先对条件表达式进行求值判断，
			 * 		如果条件表达式的值为true，则执行if后的语句，
			 * 		如果条件表达式的值为false，则不会执行if后的语句。
			 * 			if语句只能控制紧随其后的那个语句,
			 * 				如果希望if语句可以控制多条语句，
			 * 				可以将这些语句统一放到代码块中
			 * 			if语句后的代码块不是必须的，但是在开发中尽量写上代码块，即使if后只有一条语句
			 * 			
			 */
			var a = 25;
			if(a > 10 && a <= 20){
				alert("a大于10，并且 a小于等于20");
			}	
		</script>
	</head>
	<body>
	</body>
</html>
```

**code演示**：`13.if语句.html`    除了if和else还可以使用else if 来创建多个条件分支

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * if语句
			 * 	语法二:
			 * 		if(条件表达式){
			 * 			语句...
			 * 		}else{
			 * 			语句...
			 * 		}
			 * 
			 * 	if...else...语句
			 * 		当该语句执行时，会先对if后的条件表达式进行求值判断，
			 * 			如果该值为true，则执行if后的语句
			 * 			如果该值为false，则执行else后的语句
			 * 
			 * 	语法三：
			 * 		if(条件表达式){
			 * 			语句...
			 * 		}else if(条件表达式){
			 * 			语句...
			 * 		}else if(条件表达式){
			 * 			语句...
			 * 		}else{
			 * 			语句...
			 * 		}
			 * 
			 * 		if...else if...else
			 * 			当该语句执行时，会从上到下依次对条件表达式进行求值判断
			 * 			如果值为true，则执行当前语句。
			 * 			如果值为false，则继续向下判断。
			 * 			如果所有的条件都不满足，则执行最后一个else后的语句
			 * 			该语句中，只会有一个代码块被执行，一旦代码块执行了，则直接结束语句
			 */
			var age = 50;
			/*if(age >= 60){
				alert("你已经退休了~~");
			}else{
				alert("你还没退休~~~");
			}*/
			age = 200;
			/*if(age > 100){
				alert("活着挺没意思的~~");
			}else if(age > 80){
				alert("你也老大不小的了~~");	
			}else if(age > 60){
				alert("你也退休了~~");
			}else if(age > 30){
				alert("你已经中年了~~");
			}else if(age > 17){
				alert("你已经成年了");
			}else{
				alert("你还是个小孩子~~");
			}*/
			age = 90;
			if(age > 17 && age <= 30){
				alert("你已经成年了");
			}else if(age > 30 && age <= 60){
				alert("你已经中年了");
			}else if(age > 60 && age <= 80){
				alert("你已经退休了");
			}else{
				alert("你岁数挺大的了~~");
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

**code演示**：`if-exer1.html`  if语句使练习

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>if练习1</title>
		<script type="text/javascript">
			/*
			 * 	从键盘输入小明的期末成绩:
			 *	当成绩为100时，'奖励一辆BMW'
			 *	当成绩为[80-99]时，'奖励一台iphone15s'
			 *	当成绩为[60-80]时，'奖励一本参考书'
			 *	其他时，什么奖励也没有
			 */
			
			/*
			 * prompt()可以弹出一个提示框，该提示框中会带有一个文本框，
			 * 	用户可以在文本框中输入一段内容，该函数需要一个字符串作为参数，
			 * 	该字符串将会作为提示框的提示文字
			 * 
			 * 用户输入的内容将会作为函数的返回值返回，可以定义一个变量来接收该内容
			 */
			//score就是小明的期末成绩
			var score = prompt("请输入小明的期末成绩(0-100):");
			//判断值是否合法
			if(score > 100 || score < 0 || isNaN(score)){
				alert("拉出去毙了~~~");
			}else{
				//根据score的值来决定给小明什么奖励
				if(score == 100){
					//奖励一台宝马
					alert("宝马，拿去~~~");
				}else if(score >= 80){
					//奖励一个手机
					alert("手机，拿去玩~~~");
				}else if(score >= 60){
					//奖励一本参考书
					alert("参考书，拿去看~~~");
				}else{
					alert("棍子一根~~");
				}
			}
		</script>
	</head>
	<body>
		
	</body>
</html>
```

**code演示**：`if-exer2.html`   if语句使练习

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>if练习2</title>
		<script type="text/javascript">
			/*
			 * 	大家都知道，男大当婚，女大当嫁。那么女方家长要嫁女儿，当然要提出一定的条件： 
			 *	高：180cm以上; 富:1000万以上; 帅:500以上;
			 *	如果这三个条件同时满足，则:'我一定要嫁给他'
			 *	如果三个条件有为真的情况，则:'嫁吧，比上不足，比下有余。' 
			 *	如果三个条件都不满足，则:'不嫁！' 
			 */
			var height = prompt("请输入你的身高(CM):");
			var money = prompt("请输入你的财富(万):");
			var face = prompt("请输入你的颜值(PX):");
			
			//如果这三个条件同时满足，则:'我一定要嫁给他'
			if(height > 180 && money > 1000 && face > 500){
				alert("我一定要嫁给他~~");
			}else if(height > 180 || money > 1000 || face > 500){
				//如果三个条件有为真的情况，则:'嫁吧，比上不足，比下有余。' 
				alert("嫁吧，比上不足，比下有余。");
			}else{
				//如果三个条件都不满足，则:'不嫁！' 
				alert("不嫁。");
			}
		</script>
	</head>
	<body>
		
	</body>
</html>
```

**code演示**：`if-exer3.html`   if语句使练习

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>if练习3</title>
		<script type="text/javascript">
			/*
			 * 	编写程序，由键盘输入三个整数分别存入变量num1、num2、num3，
			 * 	对他们进行排序，并且从小到大输出。
			 */
			
			//获取用户输入的三个数
			/*
			 * prompt()函数的返回值是String类型的
			 */
			var num1 = +prompt("请输入第一个数:");
			var num2 = +prompt("请输入第二个数:");
			var num3 = +prompt("请输入第三个数:");
			
			
			//找到三个数中最小的数
			if(num1 < num2 && num1 < num3){
				//num1最小，比较num2和num3
				if(num2 < num3){
					//num1 num2 num3
					alert(num1 +","+num2 + ","+num3);
				}else{
					//num1 num3 num2
					alert(num1 +","+num3 + ","+num2);
				}
				
			}else if(num2 < num1 && num2 < num3){
				//num2最小，比较num1和num3
				if(num1 < num3){
					//num2 num1 num3
					alert(num2 +","+num1 + ","+num3);
				}else{
					//num2 num3 num1
					alert(num2 +","+num3 + ","+num1);
				}
				
			}else{
				//num3最小,比较num1和num2
				if(num1 < num2){
					// num3 num1 num2
					alert(num3 +","+num1 + ","+num2);
				}else{
					//num3 num2 num1
					alert(num3 +","+num2 + ","+num1);
				}
				
			}	
		</script>
	</head>
	<body>
		
	</body>
</html>
```

### 4.3 switch语句

switch. . .case语句：switch...case是另一种流程控制语句。switch语句更适用于多条分支使用同一条语句的情况。需要注意的是case语句只是标识的程序运行的起点，并不是终点，所以一旦符合case的条件程序会一直运行到结束。所以一般会在case中添加break作为语句的结束

语法：

```javascript
switch(语句){
    case表达式1:
        语句...
    case表达式2:
        语句...
    default:
        语句...
)
```

**code演示**：`02.条件分支语句.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 条件分支语句也叫switch语句
			 * 	语法：
			 * 		switch(条件表达式){
			 * 			case 表达式:
			 * 				语句...
			 * 				break;
			 *  		case 表达式:
			 * 				语句...
			 * 				break;
			 * 			default:
			 * 				语句...
			 * 				break;
			 * 		}
			 * 
			 * 	执行流程：
			 * 		switch...case..语句
			 * 		在执行时会依次将case后的表达式的值和switch后的条件表达式的值进行全等比较，
			 * 			如果比较结果为true，则从当前case处开始执行代码。
			 * 				当前case后的所有的代码都会执行，我们可以在case的后边跟着一个break关键字，
			 * 				这样可以确保只会执行当前case后的语句，而不会执行其他的case
			 * 			如果比较结果为false，则继续向下比较
			 * 			如果所有的比较结果都为false，则只执行default后的语句
			 * 
			 * 	switch语句和if语句的功能实际上有重复的，使用switch可以实现if的功能，
			 * 		同样使用if也可以实现switch的功能，所以我们使用时，可以根据自己的习惯选择。
			 */
			//根据num的值，输出对应的中文
			var num = 3;
			/*if(num == 1){
				console.log("壹");
			}else if(num == 2){
				console.log("贰");
			}else if(num == 3){
				console.log("叁");
			}*/
			num = "hello";
			switch(num){
				case 1:
					console.log("壹");
					//使用break可以来退出switch语句
					break;
				case 2:
					console.log("贰");
					break;
				case 3:
					console.log("叁");
					break;
				default:
					console.log("非法数字~~");
					break;
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

**code演示**：`switch-exer1.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>switch练习1</title>
		<script type="text/javascript">
			/*
			 * 对于成绩大于60分的，输出'合格'。低于60分的，输出'不合格'
			 * 
			 * 6x  /  10 = 6
			 * 7x  /  10 = 7
			 * 8x  /  10 = 8
			 * 9x  /  10 = 9
			 * 100 /  10 = 10
			 * 
			 */
			var score = 75;
			
			/*switch(parseInt(score/10)){
				case 10:
				case 9:
				case 8:
				case 7:
				case 6:
					console.log("合格");
					break;
				default:
					console.log("不合格");
					break;
			}*/
			switch(true){
				case score >= 60:
					console.log("合格");
					break;
				default:
					console.log("不合格");
					break;
			}
		</script>
	<body>
	</body>
</html>
```

###  4.4 循环语句

**while语句**：while语句是一个最基本的循环语句，while语句也被称为while循环。和if—样while中的条件表达式将会被转换为布尔类型，只要该值为真，则代码块将会一直重复执行。代码块每执行一次，条件表达式将会重新计算

```javascript
// 语法 
while(条件表达式) {
    语句...
)
```

**do. . .while循环**：do...while和while非常类似，只不过它会在循环的尾部而不是顶部检查表达式的值。do...while循环会至少执行一次

```javascript
// 语法
do{
    语句...
}while(条件表达式);
```

**code演示**：`03.循环语句.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 向页面中输出连续的数字
			 */
			/*var n = 1;
			document.write(n++ +"<br />");*/
			
			/*
			 * 循环语句：
			 * 	通过循环语句可以反复的执行一段代码多次
			 * 
			 * while循环
			 * 	- 语法：
			 * 		while(条件表达式){
			 * 			语句...
			 * 		}
			 * 
			 * 	- while语句在执行时，
			 * 		先对条件表达式进行求值判断，
			 * 			如果值为true，则执行循环体，
			 * 				循环体执行完毕以后，继续对表达式进行判断
			 * 				如果为true，则继续执行循环体，以此类推
			 * 			如果值为false，则终止循环
			 * 
			 * do...while循环
			 * 	- 语法：
			 * 		do{
			 * 			语句...
			 * 		}while(条件表达式)
			 * 
			 * 	- 执行流程：
			 * 		do...while语句在执行时，会先执行循环体，
			 * 			循环体执行完毕以后，在对while后的条件表达式进行判断，
			 * 			如果结果为true，则继续执行循环体，执行完毕继续判断以此类推
			 * 			如果结果为false，则终止循环
			 * 
			 * 		实际上这两个语句功能类似，不同的是while是先判断后执行，
			 * 			而do...while会先执行后判断，
			 * 		do...while可以保证循环体至少执行一次，
			 * 			而while不能
			 */
			var n = 1;
			
			//向这种将条件表达式写死为true的循环，叫做死循环
			//该循环不会停止，除非浏览器关闭，死循环在开发中慎用
			//可以使用break，来终止循环
			/*while(true){
				alert(n++);
				
				//判断n是否是10
				if(n == 10){
					//退出循环
					break;
				}
				
			}*/
			
			//创建一个循环，往往需要三个步骤
			
			//1.创初始化一个变量
			var i = 11;
			
			//2.在循环中设置一个条件表达式
			/*while(i <= 10){
				//3.定义一个更新表达式，每次更新初始化变量
				document.write(i++ +"<br />")
				
			}*/
			
			/*do{
				document.write(i++ +"<br />");
			}while(i <= 10);*/
			
			/*while(true){
				alert(1);
			}*/
		</script>
	</head>
	<body>
	</body>
</html>
```

while循环练习：`04.while练习.html`

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 假如投资的年利率为5%，试求从1000块增长到5000块，需要花费多少年
			 * 
			 * 1000 1000*1.05
			 * 1050 1050*1.05
			 */
			//定义一个变量，表示当前的钱数
			var money = 1000;
			//定义一个计数器
			var count = 0;
			//定义一个while循环来计算每年的钱数
			while(money < 5000){
				money *= 1.05;
				
				//使count自增
				count++;
			}
			//console.log(money);
			console.log("一共需要"+count+"年");
		</script>
	</head>
	<body>
	</body>
</html>
```

while循环练习：`05.while练习.html`  循环获取输入，直到用户输入合法的值

```javascript
        /*
         * prompt()可以弹出一个提示框，该提示框中会带有一个文本框，
         * 	用户可以在文本框中输入一段内容，该函数需要一个字符串作为参数，
         * 	该字符串将会作为提示框的提示文字
         * 
         * 用户输入的内容将会作为函数的返回值返回，可以定义一个变量来接收该内容
         */
        //将prompt放入到一个循环中
        while(true){
            //score就是小明的期末成绩
            var score = prompt("请输入小明的期末成绩(0-100):");
            //判断用户输入的值是否合法
            if(score >= 0 && score <= 100){
                //满足该条件则证明用户的输入合法，退出循环
                break;
            }

            alert("请输入有效的分数！！！");
        }
```

### 4.5 for循环

**for循环**：for语句也是循环控制语句，我们也称它为for循环。大部分循环都会有一个计数器用以控制循环执行的次数，计数器的三个关键操作是初始化、检测和更新。for语句就将这三步操作明确为了语法的一部分

```javascript
// 语法
for(初始化表达式;条件表达式;更新表达式){
    语句...
)
```

**code演示**：`06.for循环.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * for语句，也是一个循环语句，也称为for循环
			 * 	在for循环中，为我们提供了专门的位置用来放三个表达式：
			 * 		1.初始化表达式
			 * 		2.条件表达式
			 * 		3.更新表达式
			 * 
			 *  for循环的语法：
			 * 		for(①初始化表达式;②条件表达式;④更新表达式){
			 * 			③语句...
			 * 		}
			 * 
			 * 		for循环的执行流程：
			 * 			①执行初始化表达式，初始化变量（初始化表达式只会执行一次）
			 * 			②执行条件表达式，判断是否执行循环。
			 * 				如果为true，则执行循环③
			 * 				如果为false，终止循环
			 * 			④执行更新表达式，更新表达式执行完毕继续重复②
			 */
			
			//创建一个执行10次的while循环
			//初始化表达式
			/*var i = 0;
			
			//创建一个循环，定义条件表达式
			while(i < 10){
				//设置更新表达式
				alert(i++);
			}*/
			
			for(var i = 0 ; i < 10 ; i++ ){
				alert(i);
			}
			
			/*
			 * for循环中的三个部分都可以省略，也可以写在外部
			 * 	如果在for循环中不写任何的表达式，只写两个;
			 * 	此时循环是一个死循环会一直执行下去，慎用
			 * 	for(;;){
					alert("hello");
				}
			 */
		</script>
	</head>
	<body>
	</body>
</html>
```

`07.for循环的练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 打印1-100之间所有奇数之和
			 */
			//创建一个变量，用来保存奇数之和
			//var sum = 0;
			//打印1-100之间的数
			for(var i=1 , sum=0 ; i<=100 ; i++){
				
				//判断i是否是奇数
				//不能被2整除的数就是奇数
				if(i%2 != 0){
					//如果i除以2有余数则证明i是奇数
					//console.log(i);
					sum = sum+i;
				}
			}
			console.log("奇数之和为 : "+sum);
		</script>
	</head>
	<body>
	</body>
</html>
```

`08.for练习.html`

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 打印1-100之间所有7的倍数的个数及总和
			 */
			//定义一个变量，来保存总和
			var sum = 0;
			//定义一个计数器，来记录数量
			var count = 0;
			
			//打印1-100之间所有的数
			for(var i=1 ; i<=100 ; i++){
				
				//判断i是否是7的倍数
				if(i % 7 == 0){
					//console.log(i);
					sum += i;
					//使计数器自增1
					count++;
				}
			}
			//输出总和
			console.log("总和为:"+sum);
			//输出总数
			console.log("总数量为:"+count);
		</script>
	</head>
	<body>
	</body>
</html>
```

`09.for练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 水仙花数是指一个3位数，它的每个位上的数字的3 次幂之和等于它本身。
				（例如：1^3 + 5^3 + 3^3 = 153）,请打印所有的水仙花数。
			 */
			
			//打印所有的三位数
			for(var i=100 ; i<1000 ; i++){
				
				//获取i的百位 十位 个位的数字
				//获取百位数字
				var bai = parseInt(i/100);
				
				//获取十位的数字
				var shi = parseInt((i-bai*100)/10);
				
				//获取个位数字
				var ge = i % 10;
				
				//判断i是否是水仙花数
				if(bai*bai*bai + shi*shi*shi + ge*ge*ge == i){
					console.log(i);
				}
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

`10.for练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 在页面中接收一个用户输入的数字，并判断该数是否是质数。
				质数：只能被1和它自身整除的数，1不是质数也不是合数，质数必须是大于1的自然数。	
			 */
			var num = prompt("请输入一个大于1的整数:");
			//判断这个值是否合法
			if(num <= 1){
				alert("该值不合法！");
			}else{
				//创建一个变量来保存当前的数的状态
				//默认当前num是质数
				var flag = true;
				//判断num是否是质数
				//获取2-num之间的数
				for(var i=2 ; i<num ; i++){
					//console.log(i);
					//判断num是否能被i整除
					if(num % i == 0){
						//如果num能被i整除，则说明num一定不是质数
						//设置flag为false
						flag = false;
					}
				}
				//如果num是质数则输出
				if(flag){
					alert(num + "是质数！！！");
				}else{
					alert("这个不是质数")
				}
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

`01.嵌套的for循环.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
			/*
			 
			 	通过程序，在页面中输出如下的图形：
			 	
			 	*      1   <1   i=0
			 	**     2   <2   i=1
			 	***    3   <3   i=2
			 	****   4   <4   i=3
			 	*****  5   <5   i=4
			 	
			 	*****
			 	*****
			 	*****
			 	*****
			 	*****
			 	
			 	***** 1   j<5(5-0)  i=0
			 	****  2	  j<4(5-1)  i=1
			 	***   3   j<3(5-2)  i=2
			 	**    4   j<2(5-3)  i=3
			 	*     5   j<1(5-4)  i=4
			 */
			//向body中输出一个内容
			//document.write("*****<br />");
			//通过一个for循环来输出图形
			//这个for循环执行几次，图形的高度就是多少
			//它可以用来控制图形的高度
			for(var i=0 ; i<5 ; i++){
				
				/*
				 * 在循环的内部再创建一个循环，用来控制图形的宽度
				 * 目前我们的外部的for循环执行1次，内部的就会执行5次
				 * 内层循环可以来决定图形的宽度，执行几次图形的宽度就是多少
				 */
				/*for(var j=0 ; j<i+1 ; j++){
					document.write("*&nbsp;&nbsp;&nbsp;");
				}*/
				for(var j=0 ; j<5-i ; j++){
					document.write("*&nbsp;&nbsp;&nbsp;");
				}
				//输出一个换行
				document.write("<br />");
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

`02.99乘法表.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 1.打印99乘法表
			 * 	 1*1=1
			 * 	 1*2=2 2*2=4
			 * 	 1*3=3 2*3=6 3*3=9
			 * 	 1*4=4 2*4=8 3*4=12 4*4=16	
			 * 						....9*9=81
			 * 
			 * 2.打印出1-100之间所有的质数
			 */
			//创建外层循环，用来控制乘法表的高度
			for(var i=1 ; i<=9 ; i++ ){
				//创建一个内层循环来控制图形的宽度
				for(var j=1 ; j<=i ; j++){
					document.write("<span>"+j+"*"+i+"="+i*j+"</span>");
				}
				
				//输出一个换行
				document.write("<br />");
				
			}
		</script>
		<style type="text/css">
		
			body{
				width: 2000px;
			}
			span{
				display: inline-block;
				width: 80px;
			}
		</style>
	</head>
	<body>
	</body>
</html>
```

`03.质数练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 打印出1-100之间所有的质数
			 */
			//打印2-100之间所有的数
			for(var i=2 ; i<=100 ; i++){
				//创建一个布尔值，用来保存结果，默认i是质数
				var flag = true;
				//判断i是否是质数
				//获取到2-i之间的所有的数
				for(var j=2 ; j<i ; j++){
					//判断i是否能被j整除
					if(i%j == 0){
						//如果进入判断则证明i不是质数,修改flag值为false
						flag = false;
					}
				}
				//如果是质数，则打印i的值
				if(flag){
					console.log(i);
				}
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.6 break和continue

**break和continue**：break和continue语句用于在循环中精确地控制代码的执行。使用break语句会使程序立刻退出最近的循环，强制执行循环后边的语句。break和continue语句只在循环和switch语句中使用。使用continue语句会使程序跳过当次循环，继续执行下一次循环，并不会结束整个循环。continue只能在循环中使用，不能出现在其他的结构中

label：使用label语句可以在代码中添加标签，以便将来使用。

```javascript
// 语法：
// label: statement
start: for (var i=0 ; i <count; i++){
     alert(i);
)
// 这个例子中定义的start标签可以在将来由break或 continue语句引用
// 加标签的语句一般都要与for语句等循环语句配合使用
```

`04.break和continue.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * break关键字可以用来退出switch或循环语句
			 * 	不能在if语句中使用break和continue
			 * 	break关键字，会立即终止离他最近的那个循环语句
			 */
			
			/*for(var i=0 ; i<5 ; i++){
				console.log(i);
				
				if(i == 2){
					break;
				}
				
			}*/
			/*for(var i=0 ; i<5 ; i++){
				console.log("@外层循环"+i)
				for(var j=0 ; j<5; j++){
					break;
					console.log("内层循环:"+j);
				}
			}*/
			/*
			 * 可以为循环语句创建一个label，来标识当前的循环
			 * label:循环语句
			 * 使用break语句时，可以在break后跟着一个label，
			 * 这样break将会结束指定的循环，而不是最近的
			 */
			
			/*outer:
			for(var i=0 ; i<5 ; i++){
				console.log("@外层循环"+i)
				for(var j=0 ; j<5; j++){
					break outer;
					console.log("内层循环:"+j);
				}
			}*/
			/*
			 * continue关键字可以用来跳过当次循环
			 * 	同样continue也是默认只会对离他最近的循环循环起作用
			 */
			/*for(var i=0 ; i<5 ; i++){
				
				if(i==2){
					continue;
				}
				
				console.log(i);
			}*/
			outer:
			for(var i=0 ; i<5 ; i++){
				for(var j=0 ; j<5 ; j++){
					continue;
					console.log("-->"+j);
				}
				console.log("@--->"+i);
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

`05.质数练习补充.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//测试如下的程序的性能
			//在程序执行前，开启计时器
			//console.time("计时器的名字")可以用来开启一个计时器
			//它需要一个字符串作为参数，这个字符串将会作为计时器的标识
			console.time("test");
			
			//打印2-100之间所有的数
			for(var i=2 ; i<=100000 ; i++){
				var flag = true;
				for(var j=2 ; j<=Math.sqrt(i) ; j++){
					if(i%j == 0){
						//如果进入判断则证明i不是质数,修改flag值为false
						flag = false;
						//一旦进入判断，则证明i不可能是质数了，此时循环再执行已经没有任何意义了
						//使用break来结束循环
						break;
						//不加break 215ms
						//加break 25ms
						//修改j<=后 2.6
					}
				}
				//如果是质数，则打印i的值
				if(flag){
					//console.log(i);
				}
			}
			
			//终止计时器
			//console.timeEnd()用来停止一个计时器，需要一个计时器的名字作为参数
			console.timeEnd("test");
			/*
			 * 36
			 * 1 36
			 * 2 18
			 * 3 12
			 * 4 9
			 * 6 6
			 */
			//可以通过Math.sqrt()对一个数进行开方
			//var result = Math.sqrt(97);
			//console.log("result = "+result)
		</script>
	</head>
	<body>
	</body>
</html>
```

## 4. 对象

###  4.1 对象和对象的属性

**0bject对象**：Object类型也称为一个对象，是JavaScript中的引用数据类型。它是一种复合值，它将很多值聚合到一起，可以通过名字访问这些值。对象也可以看做是属性的无序集合，每个属性都是一个名/值对。对象除了可以创建自有属性，还可以通过从一个名为原型的对象那里继承属性。除了字符串、数字、true、false、null和undefined之外，JS中的值都是对象

**对象的分类**：

- 内建对象：由ES标准中定义的对象，在任何的ES的实现中都可以使用。比如：Math String Number Boolean Function Object....
- 宿主对象：由JS的运行环境提供的对象，目前来讲主要指由浏览器提供的对象。比如 BOM DOM
- 自定义对象：由开发人员自己创建的对象

**创建对象的两种方式**：

```javascript
// 创建对象的两种方式
// 第一种
    var person =new object();
    person.name = "孙悟空";
    person.age = 18;

// 第二种
var person = {
        name : "孙悟空",
        age : 18
};

```

`06.对象.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * JS中数据类型
			 * 	- String 字符串
			 *  - Number 数值
			 * 	- Boolean 布尔值
			 * 	- Null 空值
			 * 	- Undefined 未定义
			 * 		- 以上这五种类型属于基本数据类型，以后我们看到的值
			 * 			只要不是上边的5种，全都是对象
			 * 	- Object 对象
			 * 
			 * 
			 * 基本数据类型都是单一的值"hello" 123 true,
			 * 	值和值之间没有任何的联系。
			 * 
			 * 在JS中来表示一个人的信息（name gender age）：
			 * 	var name = "孙悟空";
			 * 	var gender = "男";
			 * 	var age = 18;
			 * 如果使用基本数据类型的数据，我们所创建的变量都是独立，不能成为一个整体。
			 * 
			 * 对象属于一种复合的数据类型，在对象中可以保存多个不同数据类型的属性。
			 * 
			 * 对象的分类：
			 * 	1.内建对象
			 * 		- 由ES标准中定义的对象，在任何的ES的实现中都可以使用
			 * 		- 比如：Math String Number Boolean Function Object....
			 * 
			 * 	2.宿主对象
			 * 		- 由JS的运行环境提供的对象，目前来讲主要指由浏览器提供的对象
			 * 		- 比如 BOM DOM
			 * 
			 * 	3.自定义对象
			 * 		- 由开发人员自己创建的对象
			 * 
			 */
			//创建对象
			/*
			 * 使用new关键字调用的函数，是构造函数constructor
			 * 	构造函数是专门用来创建对象的函数
			 * 使用typeof检查一个对象时，会返回object
			 */
			var obj = new Object();
			/*
			 * 在对象中保存的值称为属性
			 * 向对象添加属性
			 * 	语法：对象.属性名 = 属性值;
			 */
			//向obj中添加一个name属性
			obj.name = "孙悟空";
			//向obj中添加一个gender属性
			obj.gender = "男";
			//向obj中添加一个age属性
			obj.age = 18;
			/*
			 * 读取对象中的属性
			 * 	语法：对象.属性名
			 * 
			 * 如果读取对象中没有的属性，不会报错而是会返回undefined
			 */
			//console.log(obj.gender);
			//console.log(obj.hello);
			/*
			 * 修改对象的属性值
			 * 	语法：对象.属性名 = 新值
			 */
			obj.name = "tom";
			/*
			 * 删除对象的属性
			 * 	语法：delete 对象.属性名
			 */
			delete obj.name;
			console.log(obj.age);
		</script>
	</head>
	<body>
	</body>
</html>
```

**访问对象属性的两种方式**︰对象.属性名、对象['属性名']

`07.属性名和属性值.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			var obj = new Object();
			/*
			 * 向对象中添加属性
			 * 属性名：
			 * 	- 对象的属性名不强制要求遵守标识符的规范
			 * 		什么乱七八糟的名字都可以使用
			 * 	- 但是我们使用是还是尽量按照标识符的规范去做
			 * 
			 */
			obj.name = "孙悟空";
			
			//obj.var = "hello";
			
			/*
			 * 如果要使用特殊的属性名，不能采用.的方式来操作
			 * 	需要使用另一种方式：
			 * 		语法：对象["属性名"] = 属性值
			 * 	读取时也需要采用这种方式
			 * 
			 * 使用[]这种形式去操作属性，更加的灵活，
			 * 	在[]中可以直接传递一个变量，这样变量值是多少就会读取那个属性
			 * 
			 */
			obj["123"] = 789;
			obj["nihao"] = "你好";
			var n = "nihao";
			//console.log(obj["123"]);
			/*
			 * 属性值
			 * 	JS对象的属性值，可以是任意的数据类型
			 * 		甚至也可以是一个对象
			 */
			obj.test = true;
			obj.test = null;
			obj.test = undefined;
			
			//创建一个对象
			var obj2 = new Object();
			obj2.name = "猪八戒";
			//将obj2设置为obj的属性
			obj.test = obj2;
			//console.log(obj.test.name);
			/*
			 * in 运算符
			 * 	- 通过该运算符可以检查一个对象中是否含有指定的属性
			 * 		如果有则返回true，没有则返回false
			 *  - 语法：
			 * 		"属性名" in 对象
			 */
			//console.log(obj.test2);
			
			//检查obj中是否含有test2属性
			//console.log("test2" in obj);
			//console.log("test" in obj);
			console.log("name" in obj);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.2 基本数据类型 和 引用数据类型

JS中的变量可能包含两种不同数据类型的值：基本数据类型和引用数据类型

**基本数据类型**：JS中一共有5种基本数据类型:String、Number、Boolean、Undefined、Null。基本数据类型的值是无法修改的，是不可变的。基本数据类型的比较是值的比较，也就是只要两个变量的值相等，就认为这两个变量相等

**引用数据类型**：引用类型的值是保存在内存中的对象。当一个变量是一个对象时，实际上变量中保存的并不是对象本身，而是对象的引用。当从一个变量向另一个变量复制引用类型的值时，会将对象的引用复制到变量中，并不是创建一个新的对象。这时，两个变量指向的是同一个对象。因此，改变其中一个变量会影响另一个

**栈内存和堆内存**：JavaScript在运行时数据是保存到栈内存和堆内存当中的。简单来说栈内存用来保存变量和基本类型。堆内存用来保存对象。在声明一个变量时实际上就是在栈内存中创建了一个空间用来保存变量。如果是基本类型则在栈内存中直接保存，如果是引用类型则会在堆内存中保存，变量中保存的实际上对象在堆内存中的地址

![image-20240324141735238](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140323548.png)

**对象的引用**：JS中的变量都是保存到栈内存中的。基本数据类型的值直接在栈内存中存储，基本数据类型的值与值之间是独立存在，修改一个变量不会影响其他的变量。对象是保存到堆内存中的，每创建一个新的对象，就会在堆内存中开辟出一个新的空间，而变量保存的是对象的内存地址（对象的引用），如果两个变量保存的是同一个对象引用，当一个通过一个变量修改属性时，另一个也会受到影响。如果将变量设为null，那么这个变量就不再引用对象了，设为null的操作不会对该对象造成影响

![image-20240324143627449](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140324217.png)

**基本数据类和引用数据类型的比较**：当比较两个基本数据类型的值时，就是比较值。而比较两个引用数据类型时，它是比较的对象的内存地址，如果两个对象的属性都是是一模一样的，但是地址不同，它也会返回false

![image-20240324145350685](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140324267.png)

`08.基本和引用数据类型.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 基本数据类型
			 * 	String Number Boolean Null Undefined
			 * 
			 * 引用数据类型
			 * 	Object
			 * 
			 * JS中的变量都是保存到栈内存中的，
			 * 		基本数据类型的值直接在栈内存中存储，
			 * 		值与值之间是独立存在，修改一个变量不会影响其他的变量
			 * 
			 * 		对象是保存到堆内存中的，每创建一个新的对象，就会在堆内存中开辟出一个新的空间，
			 * 		而变量保存的是对象的内存地址（对象的引用），如果两个变量保存的是同一个对象引用，
			 * 		当一个通过一个变量修改属性时，另一个也会受到影响
			 */
			var a = 123;
			var b = a;
			a++;
			/*console.log("a = "+a);
			console.log("b = "+b);*/
			
			var obj = new Object();
			obj.name = "孙悟空";
			
			var obj2 = obj;
			
			//修改obj的name属性
			obj.name = "猪八戒";
			/*console.log(obj.name);
			console.log(obj2.name);*/
			//设置obj2为null
			obj2 = null;
			/*console.log(obj);
			console.log(obj2);*/
			
			var c = 10;
			var d = 10;
			//console.log(c == d);
			var obj3 = new Object();
			var obj4 = new Object();
			obj3.name = "沙和尚";
			obj4.name = "沙和尚";
			/*console.log(obj3);
			console.log(obj4);*/
			/*
			 * 当比较两个基本数据类型的值时，就是比较值。
			 * 而比较两个引用数据类型时，它是比较的对象的内存地址，
			 * 		如果两个对象是一摸一样的，但是地址不同，它也会返回false
			 */
			console.log(obj3 == obj4);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.3 对象字面量

**对象字面量**：JavaScript 对象字面量是一种创建对象的方式，它允许定义和初始化对象而不必使用构造函数（不使用new Object()）。对象字面量使用花括号 `{}` 来表示，其中包含零个或多个属性和方法的键值对。对象字面量使用示例：

```javascript
// 创建一个表示汽车的对象
// 这些属性和方法由键值对组成，并用逗号分隔。属性名作为键，而属性值则作为相应的值
const car = {
  brand: "Toyota",
  model: "Corolla",
  year: 2020,
  start: function() {
    console.log("Engine started");
  }
};
```

`09.对象字面量.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//创建一个对象
			//var obj = new Object();
			/*
			 * 使用对象字面量来创建一个对象
			 */
			var obj = {};
			//console.log(typeof obj);
			obj.name = "孙悟空";
			//console.log(obj.name);
			/*
			 * 使用对象字面量，可以在创建对象时，直接指定对象中的属性
			 * 语法：{属性名:属性值,属性名:属性值....}
			 * 	对象字面量的属性名可以加引号也可以不加，建议不加,
			 * 	如果要使用一些特殊的名字，则必须加引号
			 * 
			 * 属性名和属性值是一组一组的名值对结构，
			 * 	名和值之间使用:连接，多个名值对之间使用,隔开
			 * 	如果一个属性之后没有其他的属性了，就不要写,
			 */
			var obj2 = {
				name:"猪八戒",
				age:13,
				gender:"男",
				test:{name:"沙僧"}
			};
			console.log(obj2.test);
		</script>
	</head>
	<body>
	</body>
</html>
```

### 4.4 枚举对象中的属性

**枚举对象中的属性**：JavaScript中可以使用如下几种方式来枚举对象中的属性

```
for...in  循环
Object.keys()方法
Object.getOwnPropertyNames()方法
```

**`for...in` 循环**：使用 `for...in` 循环可以遍历对象的可枚举属性，包括从原型链继承的属性

```javascript
for (let key in obj) {
  console.log(key, obj[key]);
}
```

**`Object.keys()`**：`Object.keys()` 方法返回一个包含对象自身的可枚举属性名称的数组

```javascript
const keys = Object.keys(obj);
keys.forEach(key => {
  console.log(key, obj[key]);
});
```

**`Object.getOwnPropertyNames()`**：`Object.getOwnPropertyNames()` 方法返回一个数组，包含对象自身的所有属性，包括不可枚举属性名称

```javascript
const propertyNames = Object.getOwnPropertyNames(obj);
propertyNames.forEach(name => {
  console.log(name, obj[name]);
});
```

**`Object.entries()`**：`Object.entries()` 方法返回一个给定对象自身可枚举属性的键值对数组

```javascript
const entries = Object.entries(obj);
entries.forEach(([key, value]) => {
  console.log(key, value);
});
```

`06.枚举对象中的属性.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			var obj = {
						name:"孙悟空",
						age:18,
						gender:"男",
						address:"花果山"
					 };
					 
			//枚举对象中的属性
			//使用for ... in 语句
			/*
			 * 语法：
			 * 	for(var 变量 in 对象){
			 * 	
			 *  }
			 * 
			 * for...in语句 对象中有几个属性，循环体就会执行几次
			 * 	每次执行时，会将对象中的一个属性的名字赋值给变量
			 */
			for(var n in obj){
				console.log("属性名:"+n);
				
				console.log("属性值:"+obj[n]);
			}
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.4 函数

#####  4.4.1 函数的声明和调用

**函数**：函数是由一连串的子程序(语句的集合)所组成的，可以被外部程序调用。向函数传递参数之后，函数可以返回一定的值。通常情况下，JavaScript代码是自上而下执行的，不过函数体内部的代码则不是这样。如果只是对函数进行了声明，其中的代码并不会执行。只有在调用函数时才会执行函数体内部的代码。这里要注意的是JavaScript中的函数也是一个对象

**函数的声明**：首先明确一点函数也是一个对象，所以函数也是在堆内存中保存的。函数声明比较特殊，需要使用function关键字声明。函数声明的两种方式：

- `var sum = function (a,b) {return a+b};`。创建一个函数对象，并将函数对象赋值给了sum这个变量。其中()中的内容表示执行函数时需要的参数，{}中的内容表示函数的主体
- 可以通过函数声明语句来定义一个函数。函数声明语句以关键字function开始，其后跟有函数名、参数列表和函数体。其语法如下所示

```javascript
// 声明函数的语法
function 函数名(参数,参数,参数...) {
        函数体
)
// 声明函数
// 定义一个函数名为sum，两个参数a和b
// 函数声明时设置的参数称为形参（形式参数），这个函数对两个参数做了加法运算并将结果返回
function sum (a,b){
    return a+b ;
)
```

**函数的调用**：调用函数时，传递给函数的参数称为实参(实际参数)。如果想调用上边定义的sum函数，可以这样写`var result = sum (123,456);`。这样表示调用sum这个函数，并将123和456作为实参传递给函数，函数中会将两个参数求和并赋值给result

**参数传递**：JS中的所有的参数传递都是按值传递的。也就是说把函数外部的值赋值给函数内部的参数，就和把值从一个变量赋值给另一个变量是一样的

`10.函数.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 函数 function
			 * 	- 函数也是一个对象
			 * 	- 函数中可以封装一些功能（代码），在需要时可以执行这些功能（代码）
			 * 	- 函数中可以保存一些代码在需要的时候调用
			 * 	- 使用typeof检查一个函数对象时，会返回function
			 */
			
			//我们在实际开发中很少使用构造函数来创建一个函数对象
			//创建一个函数对象
			//可以将要封装的代码以字符串的形式传递给构造函数
			//var fun = new Function("console.log('Hello 这是我的第一个函数');");
			
			//封装到函数中的代码不会立即执行
			//函数中的代码会在函数调用的时候执行
			//调用函数 语法：函数对象()
			//当调用函数时，函数中封装的代码会按照顺序执行
			//fun();
			
			/*
			 * 使用 函数声明 来创建一个函数
			 * 	语法：
			 * 		function 函数名([形参1,形参2...形参N]){
			 * 			语句...
			 * 		}
			 */
			
			function fun2(){
				console.log("这是我的第二个函数~~~");
				alert("哈哈哈哈哈");
				document.write("~~~~(>_<)~~~~");
			}
			//console.log(fun2);
			//调用fun2
			//fun2();
			/*
			 * 使用 函数表达式 来创建一个函数
			 * var 函数名  = function([形参1,形参2...形参N]){
			 * 	 语句....
			 *  }
			 */
			var fun3 = function(){
				console.log("我是匿名函数中封装的代码");
			};
			fun3();
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.4.2 函数的参数

**传递参数**：JS中的所有的参数传递都是按值传递的。也就是说把函数外部的值赋值给函数内部的参数，就和把值从一个变量赋值给另一个变量是一样的

`11.函数的参数.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 定义一个用来求两个数和的函数
			 * 	可以在函数的()中来指定一个或多个形参（形式参数）
			 * 	多个形参之间使用,隔开，声明形参就相当于在函数内部声明了对应的变量
			 * 	但是并不赋值
			 */
			function sum(a,b){
				console.log("a = "+a);
				console.log("b = "+b);
				console.log(a+b);
			}
			
			/*
			 * 在调用函数时，可以在()中指定实参（实际参数）
			 * 	实参将会赋值给函数中对应的形参
			 */
			/*sum(1,2);
			sum(123,456);*/
			
			/*
			 * 调用函数时解析器不会检查实参的类型,
			 * 	所以要注意，是否有可能会接收到非法的参数，如果有可能则需要对参数进行类型的检查
			 * 函数的实参可以是任意的数据类型
			 */
			//sum(123,"hello");
			//sum(true , false);
			
			/*
			 * 调用函数时，解析器也不会检查实参的数量
			 * 	多余实参不会被赋值
			 * 如果实参的数量少于形参的数量，则没有对应实参的形参将是undefined
			 * 
			 */
			//sum(123,456,"hello",true,null);
			sum(123);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.4.3 函数的返回值

**函数的返回值**：使用return来设置函数的返回值。return后的值将会会作为函数的执行结果返回，可以定义一个变量来接收该结果。在函数中return后的语句都不会执行，如果return语句后不跟任何值就相当于返回一个undefined，如果函数中不写return，则也会返回undefined。	return后可以跟任意类型的值

`01.返回值.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个函数，用来计算三个数的和
			 * 
			 * 可以使用 return 来设置函数的返回值
			 * 	语法：
			 * 		return 值
			 * 
			 * 	return后的值将会会作为函数的执行结果返回，
			 * 		可以定义一个变量，来接收该结果
			 * 
			 *  在函数中return后的语句都不会执行
			 * 
			 * 	如果return语句后不跟任何值就相当于返回一个undefined，
			 * 	如果函数中不写return，则也会返回undefined
			 * 
			 * 	return后可以跟任意类型的值
			 * 
			 */
			function sum(a , b , c){
				//alert(a + b +c);
				
				var d = a + b + c;
				
				return d;
				
				//return undefined;
				
			}
			//调用函数
			//变量result的值就是函数的执行结果
			//函数返回什么result的值就是什么
			var result = sum(4,7,8);
			//var result = alert("hello");
			console.log("result = "+result);
		</script>
	</head>
	<body>
	</body>
</html>
```

`02.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 定义一个函数，判断一个数字是否是偶数，如果是返回true，否则返回false
			 */
			function isOu(num){
				return num % 2 == 0;
			}
			var result = isOu(15);
			//console.log("result = "+result);
			/*
			 * 定义一个函数，可以根据半径计算一个圆的面积，并返回计算结果
			 * 3.14*r*r
			 */
			function mianji(r){
				return 3.14*r*r;
			}
			result = mianji(5);
			//console.log("result = "+result);
			/*
			 * 创建一个函数，可以在控制台中输出一个人的信息
			 * 	可以输出人的 name age gender address
			 * 
			 * 实参可以是任意的数据类型，也可以是一个对象
			 * 	当我们的参数过多时，可以将参数封装到一个对象中，然后通过对象传递
			 */
			function sayHello(o){
				
				//console.log("o = "+o);
				console.log("我是"+o.name+",今年我"+o.age+"岁了,"+"我是一个"+o.gender+"人"+",我住在"+o.address);
			}
			//sayHello("猪八戒",28,"高老庄","男");
			//创建一个对象
			var obj = {
				name:"孙悟空",
				age:18,
				address:"花果山",
				gender:"男"
				
			};
			//sayHello(obj);
			/*
			 * 实参可以是一个对象，也可以是一个函数
			 */
			function fun(a){
				console.log("a = "+a);
				//a(obj);
			}
			//fun(sayHello);
			//fun(function(){alert("hello")});
			fun(mianji(10));
			/*
			 * mianji()
			 * 	- 调用函数
			 * 	- 相当于使用的函数的返回值
			 * 
			 * mianji
			 * 	- 函数对象
			 * 	- 相当于直接使用函数对象
			 */
			
		</script>
	</head>
	<body>
	</body>
</html>
```

`03.return.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			function fun(){
				alert("函数要执行了~~~~");
				
				for(var i=0 ; i<5 ; i++){
					
					
					if(i == 2){
						//使用break可以退出当前的循环
						//break;
						
						//continue用于跳过当次循环
						//continue;
						
						//使用return可以结束整个函数
						//return;
					}
					console.log(i);
				}
				alert("函数执行完了~~~~");
			}
			//fun();
			
			/*
			 * 返回值可以是任意的数据类型
			 * 	也可以是一个对象，也可以是一个函数
			 */
			function fun2(){
				
				//返回一个对象
				return {name:"沙和尚"};
			}
			var a = fun2();
			//console.log("a = "+a);
			function fun3(){
				//在函数内部再声明一个函数
				function fun4(){
					alert("我是fun4");
				}
				//将fun4函数对象作为返回值返回
				return fun4;
			}
			a = fun3();
			//console.log(a);
			//a();
			fun3()();
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.4.4 立即执行函数

**立即执行函数**：立即执行函数（Immediately Invoked Function Expression，IIFE）是一种特殊的函数定义方式，它可以立即执行而不需要被显式调用。这种函数通常用于创建私有作用域、模块化代码和避免全局命名冲突。通常的形式如下：

```javascript
(function() {
  // 代码逻辑
})();
```

立即执行函数的基本结构包括三部分：
1. 将函数用括号包裹起来，使其成为一个表达式
2. 在函数末尾紧跟一个对函数的调用操作符（括号）
3. 在定义的整体外部的括号中，这是为了确保JavaScript将其作为一个函数表达式而不是函数声明来处理

通过立即执行函数，可以创建一个独立的作用域，将一些函数、变量封装在其中，从而避免污染全局命名空间。这种实现方式使得在同一作用域内可以有相同名称的变量而不会发生冲突

`04.立即执行函数.html`

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//函数对象()
			/*
			 * 立即执行函数
			 * 	函数定义完，立即被调用，这种函数叫做立即执行函数
			 * 	立即执行函数往往只会执行一次
			 */
			/*(function(){
				alert("我是一个匿名函数~~~");
			})();*/
			(function(a,b){
				console.log("a = "+a);
				console.log("b = "+b);
			})(123,456);
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.4.5 对象的方法

**对象的方法**：函数也可以称为对象的属性，如果一个函数作为一个对象的属性保存，那么称这个函数是这个对象的方法，调用这个函数就说调用对象的方法（method），但是它只是名称上的区别，没有其他的区别

`05.对象.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个对象
			 */
			var obj = new Object();
			//向对象中添加属性
			obj.name = "孙悟空";
			obj.age = 18;
			//对象的属性值可以是任何的数据类型，也可以是个函数
			obj.sayName = function(){
				console.log(obj.name);
			};
			function fun(){
				console.log(obj.name);
			};
			//console.log(obj.sayName);
			//调方法
			obj.sayName();
			//调函数
			//fun();
			/*
			 * 函数也可以称为对象的属性，
			 * 	如果一个函数作为一个对象的属性保存，
			 * 	那么我们称这个函数时这个对象的方法
			 * 	调用这个函数就说调用对象的方法（method）
			 * 但是它只是名称上的区别没有其他的区别
			 */
			var obj2 = {
				name:"猪八戒",
				age:18,
				sayName:function(){
					console.log(obj2.name);
				}
			};
			obj2.sayName();
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.5 作用域

#####  4.5.1 全局作用域、局部作用域

**作用域**：JavaScript中的作用域指的是变量和函数的可访问性及可见性。作用域规定了在代码中的哪些部分可以访问或使用某个变量。JavaScript中有两种主要的作用域：全局作用域和局部作用域。JavaScript中的作用域也受到变量提升（hoisting）的影响，即在代码执行之前，变量和函数的声明会被提升到其所在作用域的顶部。这可能导致一些意外的行为，因此在编写JavaScript代码时需要小心处理作用域和变量提升的影响。另外，ES6（ECMAScript 6）引入了新的关键字`let`和`const`，它们可以在块级作用域内声明变量，进一步改变了JavaScript中的作用域规则

**全局作用域**：全局作用域中声明的变量和函数可以被代码中的任何部分访问，它们在整个代码中都是可见的

**局部作用域**：局部作用域是指在代码块或函数内声明的变量，这些变量只能在其声明的范围内被访问。在函数内部声明的变量只能在该函数内部访问

`07.作用域（Scope）.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 作用域
			 * 	- 作用域指一个变量的作用的范围
			 * 	- 在JS中一共有两种作用域：
			 * 		1.全局作用域
			 * 			- 直接编写在script标签中的JS代码，都在全局作用域
			 * 			- 全局作用域在页面打开时创建，在页面关闭时销毁
			 * 			- 在全局作用域中有一个全局对象window，
			 * 				它代表的是一个浏览器的窗口，它由浏览器创建，我们可以直接使用
			 * 			- 在全局作用域中：
			 * 				创建的变量都会作为window对象的属性保存
			 * 				创建的函数都会作为window对象的方法保存
			 * 			- 全局作用域中的变量都是全局变量，
			 * 				在页面的任意的部分都可以访问的到
			 * 
			 * 		2.函数作用域
			 * 
			 */
			var a = 10;
			var b = 20;
			//var c = "hello";
			
			//console.log(window.c);
			
			function fun(){
				console.log("我是fun函数");
			}
			//window.fun();
			//window.alert("hello");
		</script>
	</head>
	<body>
	</body>
</html>
```

`08.变量的声明提前.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 变量的声明提前
			 * 	- 使用var关键字声明的变量，会在所有的代码执行之前被声明（但是不会赋值），
			 * 		但是如果声明变量时不使用var关键字，则变量不会被声明提前
			 * 
			 * 函数的声明提前
			 * 	- 使用函数声明形式创建的函数 function 函数(){}
			 * 		它会在所有的代码执行之前就被创建，所以我们可以在函数声明前来调用函数
			 * 	   使用函数表达式创建的函数，不会被声明提前，所以不能在声明前调用	
			 */
			/*console.log("a = "+a);
			
			var a = 123;*/
			
			//fun();
			//函数声明，会被提前创建
			function fun(){
				console.log("我是一个fun函数");
			}
			//函数表达式，不会被提前创建
			var fun2 = function(){
				console.log("我是fun2函数");
			};
			fun2();
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.5.2 函数作用域

**执行环境**：执行环境定义了变量或函数有权访问的其他数据，决定了它们各自的行为。每个执行环境都有一个与之关联的变量对象，环境中定义的所有变量和函数都保存在这个对象中。全局执行环境是最外围的一个执行环境。在 Web浏览器中，全局执行环境被认为是window对象，因此所有全局变量和函数都是作为window对象的属性和方法创建的。某个执行环境中的所有代码执行完毕后，该环境被销毁，保存在其中的所有变量和函数定义也随之销毁。在内部环境可以读取外部环境的变量，反之则不行

`09.函数作用域.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 函数作用域	
			 * 	- 调用函数时创建函数作用域，函数执行完毕以后，函数作用域销毁
			 * 	- 每调用一次函数就会创建一个新的函数作用域，他们之间是互相独立的
			 * 	- 在函数作用域中可以访问到全局作用域的变量
			 * 		在全局作用域中无法访问到函数作用域的变量
			 * 	- 当在函数作用域操作一个变量时，它会先在自身作用域中寻找，如果有就直接使用
			 * 		如果没有则向上一级作用域中寻找，直到找到全局作用域，
			 * 		如果全局作用域中依然没有找到，则会报错ReferenceError
			 * 	- 在函数中要访问全局变量可以使用window对象
			 */
			//创建一个变量
			var a = 10;
			function fun(){
				var a = "我是fun函数中的变量a";
				var b = 20;
				//console.log("a = "+a);
				function fun2(){
					console.log("a = "+window.a);
				}
				fun2();
			}
			//fun();
			//console.log("b = "+b);
			/*
			 * 在函数作用域也有声明提前的特性，
			 * 	使用var关键字声明的变量，会在函数中所有的代码执行之前被声明
			 * 	函数声明也会在函数中所有的代码执行之前执行
			 */
			function fun3(){
				fun4();
				//console.log(a);
				var a = 35;
				function fun4(){
					alert("I'm fun4");
				}
			}
			//fun3();
			var c = 33;
			/*
			 * 在函数中，不适用var声明的变量都会成为全局变量
			 */
			function fun5(){
				//console.log("c = "+c);
				//c = 10;
				//d没有使用var关键字，则会设置为全局变量
				d = 100;
			}
			fun5();
			//在全局输出c
			//console.log("d = "+d);
			var e = 23;
			/*
			 * 定义形参就相当于在函数作用域中声明了变量
			 */
			function fun6(e){
				alert(e);
			}
			fun6();
		</script>
	</head>
	<body>
	</body>
</html>
```

函数作用域练习：

```javascript
判断以下代码的执行结果
=========================================
var a = 123;
function fun(){
	alert(a);
}
fun(); // 123

=========================================

var a = 123;
function fun(){
	alert(a); // undefined
	var a = 456;
}
fun();  // undefined
alert(a);  // 123

=========================================

var a = 123;
function fun(){
	alert(a);   // 123
	a = 456;
}
fun();  // 123 
alert(a);  // 456

=========================================
var a = 123;
function fun(a){
	alert(a);  
	a = 456;
}
fun();  // undefined
alert(a);  // 123
=========================================
var a = 123;
function fun(a){
	alert(a);
	a = 456;
}
fun(123);  // 123
alert(a);  // 123
```

调试技巧：可以在浏览器的脚本下设置断点，查看程序的运行情况、查看变量的作用域和变化情况

![image-20240324201000874](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140325281.png)

`10.debug.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			alert(d);
			
			var a = 10;
			
			var b = "hello";
			
			c = true;
			
			function fun(){
				alert("hello");
			}
			var d = 35;
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.5.3 this关键字

**this关键字**：关键字 `this` 引用当前执行代码的对象，其值取决于函数的调用方式。`this` 的值在运行时绑定，并且其上下文取决于函数的调用方式。this引用的是一个对象。对于最外层代码与函数内部的情况，其引用目标是不同的。此外，即使在函数内部，根据函数调用方式的不同，引用对象也会有所不同。需要注意的是，this 引用会根据代码的上下文语境自动改变其引用对象。`this` 是 JavaScript 中非常重要的概念之一，其具体行为取决于函数的调用方式和上下文

 **`this` 的常见用法**：

- 在全局作用域中，`this` 指向全局对象（在浏览器中为 `window` 对象）
- 在函数中，`this` 的值取决于函数的调用方式。如果函数作为对象的方法被调用，`this` 将指向调用该方法的对象
- 在事件处理程序中，`this` 通常引用触发事件的元素
- 在构造函数中，`this` 引用即将创建的新实例

 `this` 的使用示例：

```javascript
// 全局作用域中的 this
console.log(this); // 在浏览器中通常指向 window 对象

// 对象方法中的 this
const myObject = {
  property: "value",
  myMethod: function() {
    console.log(this.property); // 指向 myObject
  }
};

// 构造函数中的 this
function MyClass(value) {
  this.property = value;
}

// 在事件处理程序中的 this
document.getElementById("myButton").addEventListener("click", function() {
  console.log(this); // 在这里 this 引用触发事件的元素，即按钮元素
});
```

`11.this.html`

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 解析器在调用函数每次都会向函数内部传递进一个隐含的参数,
			 * 	这个隐含的参数就是this，this指向的是一个对象，
			 * 	这个对象我们称为函数执行的 上下文对象，
			 * 	根据函数的调用方式的不同，this会指向不同的对象
			 * 		1.以函数的形式调用时，this永远都是window
			 * 		2.以方法的形式调用时，this就是调用方法的那个对象
			 */
			function fun(){
				//console.log("a = "+a+", b = "+b);
				console.log(this.name); 
			}
			//fun(); // window
			//创建一个对象
			var obj = {
				name:"孙悟空",
				sayName:fun
			};
			var obj2 = {
				name:"沙和尚",
				sayName:fun
			};
			//console.log(obj.sayName == fun);
			var name = "全局的name属性";
			//obj.sayName();
			//以函数形式调用，this是window
			//fun();
			//以方法的形式调用，this是调用方法的对象
			//obj.sayName(); // 孙悟空
			obj2.sayName();  // 沙和尚
		</script>
	</head>
	<body>
	</body>
</html>
```

`12.this.html`

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//创建一个name变量
			var name = "全局";
			//创建一个fun()函数
			function fun(){
				console.log(this.name);
			}
			//创建两个对象
			var obj = {
					name:"孙悟空",
					sayName:fun
			};
			var obj2 = {
					name:"沙和尚",
					sayName:fun
			};
			//我们希望调用obj.sayName()时可以输出obj的名字
			//obj.sayName();
			obj.sayName();
		</script>
	</head>
	<body>
	</body>
</html>
```

### 4.6 使用工厂方法创建对象

`13.对象.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个对象
			 */
			var obj = {
					name:"孙悟空",
					age:18,
					gender:"男",
					sayName:function(){
						alert(this.name);
					}
			};
			/*
			 * 使用工厂方法创建对象
			 * 	通过该方法可以大批量的创建对象
			 */
			function createPerson(name , age ,gender){
				//创建一个新的对象 
				var obj = new Object();
				//向对象中添加属性
				obj.name = name;
				obj.age = age;
				obj.gender = gender;
				obj.sayName = function(){
					alert(this.name);
				};
				//将新的对象返回
				return obj;
			}
			/*
			 * 用来创建狗的对象
			 */
			function createDog(name , age){
				var obj = new Object();
				obj.name = name;
				obj.age = age;
				obj.sayHello = function(){
					alert("汪汪~~");
				};
				return obj;
			}
			var obj2 = createPerson("猪八戒",28,"男");
			var obj3 = createPerson("白骨精",16,"女");
			var obj4 = createPerson("蜘蛛精",18,"女");
			/*
			 * 使用工厂方法创建的对象，使用的构造函数都是Object
			 * 	所以创建的对象都是Object这个类型，
			 * 	就导致我们无法区分出多种不同类型的对象
			 */
			//创建一个狗的对象
			var dog = createDog("旺财",3);
			console.log(dog);
			console.log(obj4);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.7 使用构造函数创建对象

**构造函数**：构造函数是用于生成对象的函数，像之前调用的Object()就是一个构造函数。构造函数本身和普通的函数声明形式相同。构造函数的调用不同于普通函数的调用，构造函数需要通过new关键字来调用，new关键字会新创建一个对象并返回。通过new关键字调用的构造函数内的 this 引用引用了（被新生成的）对象

```javascript
// 创建一个构造函数
function Myclass(x,y){
        this. x=x ;
        this.y =y ;
)
```

**构造函数的调用**：构造函数总是由new关键字调用。构造函数和普通函数的区别就在于调用方式的不同。任何函数都可以通过new来调用，所以函数都可以是构造函数。在开发中，通常会区分用于执行的函数和构造函数。构造函数的首字母要大写

**使用new关键字执行一个构造函数的流程**：首先会创建一个空的对象。然后会执行相应的构造函数，构造函数中的this将会引用这个新对象。最后，将对象作为执行结果返回

**instanceof**：学习基本数据类型时学习了typeof用来检查一个变量的类型。但是typeof对于对象来说却不是那么好用，因为任何对象使用typeof都会返回Object。而想要获取的是对象的具体类型就需要使用instanceof运算符，instanceof主要用来检查一个对象的具体类型

`14.构造函数.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个构造函数，专门用来创建Person对象的
			 * 	构造函数就是一个普通的函数，创建方式和普通函数没有区别,
			 * 	不同的是构造函数习惯上首字母大写
			 * 
			 * 构造函数和普通函数的区别就是调用方式的不同
			 * 	普通函数是直接调用，而构造函数需要使用new关键字来调用
			 * 
			 * 构造函数的执行流程：
			 * 	1.立刻创建一个新的对象
			 * 	2.将新建的对象设置为函数中this,在构造函数中可以使用this来引用新建的对象
			 * 	3.逐行执行函数中的代码
			 * 	4.将新建的对象作为返回值返回
			 * 
			 * 使用同一个构造函数创建的对象，我们称为一类对象，也将一个构造函数称为一个类。
			 * 	我们将通过一个构造函数创建的对象，称为是该类的实例
			 * 
			 * this的情况：
			 * 	1.当以函数的形式调用时，this是window
			 * 	2.当以方法的形式调用时，谁调用方法this就是谁
			 * 	3.当以构造函数的形式调用时，this就是新创建的那个对象
			 * 
			 */
			function Person(name , age , gender){
				this.name = name;
				this.age = age;
				this.gender = gender;
				this.sayName = function(){
					alert(this.name);
				};
			}
			function Dog(){
			}
			var per = new Person("孙悟空",18,"男");
			var per2 = new Person("玉兔精",16,"女");
			var per3 = new Person("奔波霸",38,"男");
			var dog = new Dog();
			/*console.log(per);
			console.log(dog);*/
			/*
			 * 使用instanceof可以检查一个对象是否是一个类的实例
			 * 	语法：
			 * 		对象 instanceof 构造函数
			 * 如果是，则返回true，否则返回false
			 */
			//console.log(per instanceof Person);
			//console.log(dog instanceof Person);
			/*
			 * 所有的对象都是Object的后代，
			 * 	所以任何对象和Object做instanceof检查时都会返回true
			 */
			//console.log(dog instanceof Object);
		</script>
	</head>
	<body>
	</body>
</html>
```

`02.构造函数.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个Person构造函数
			 * 	- 在Person构造函数中，为每一个对象都添加了一个sayName方法，
			 * 		目前我们的方法是在构造函数内部创建的，
			 * 			也就是构造函数每执行一次就会创建一个新的sayName方法
			 * 		也是所有实例的sayName都是唯一的。
			 * 		这样就导致了构造函数执行一次就会创建一个新的方法，
			 * 			执行10000次就会创建10000个新的方法，而10000个方法都是一摸一样的
			 * 			这是完全没有必要，完全可以使所有的对象共享同一个方法
			 */
			function Person(name , age , gender){
				this.name = name;
				this.age = age;
				this.gender = gender;
				//向对象中添加一个方法
				//this.sayName = fun;
			}
			//将sayName方法在全局作用域中定义
			/*
			 * 将函数定义在全局作用域，污染了全局作用域的命名空间
			 * 	而且定义在全局作用域中也很不安全
			 */
			/*function fun(){
				alert("Hello大家好，我是:"+this.name);
			};*/
			//向原型中添加sayName方法
			Person.prototype.sayName = function(){
				alert("Hello大家好，我是:"+this.name);
			};
			//创建一个Person的实例
			var per = new Person("孙悟空",18,"男");
			var per2 = new Person("猪八戒",28,"男");
			per.sayName();
			per2.sayName();
			//console.log(per.sayName == per2.sayName);
		</script>
	</head>
	<body>
	</body>
</html>
```

### 4.8 原型

#####  4.8.1 原型 & 原型链

**原型继承**：JS是一门面向对象的语言，而且它还是一个基于原型的面向对象的语言。所谓的原型实际上指的是，在构造函数中存在着一个名为原型的(prototype)对象，这个对象中保存着一些属性，凡是通过该构造函数创建的对象都可以访问存在于原型中的属性。最典型的原型中的属性就是toString()函数，实际上对象中并没有定义这个函数，但是却可以调用，那是因为这个函数存在于Object对应的原型中

**设置原型**：原型就是一个对象，和其他对象没有任何区别，可以通过构造函数来获取原型对象。和其他对象一样可以添加修改删除原型中的属性，也可以修改原型对象的引用。需要注意的是prototype属性只存在于函数对象中，其他对象是没有prototype属性的。每一个对象都有原型，包括原型对象也有原型。特殊的是Object的原型对象没有原型

**获取原型对象的方法**：除了可以通过构造函数获取原型对象以外，还可以通过具体的对象来获取原型对象。需要注意的是，可以获取到Object的原型对象，也可以对它的属性进行操作，但是不能修改Object原型对象的引用

```javascript
// 获取原型对象的方法
Object.getPrototypeOf(对象)
对象._proto_
对象.constructor.prototype
```

**原型链**：基于上边所说的，每个对象都有原型对象，原型对象也有原型对象。由此，对象和对象的原型，以及原型的原型，就构成了一个原型链。当从一个对象中获取属性时，会首先从当前对象中查找，如果没有则顺着向上查找原型对象，直到找到Object对象的原型位置，找到则返回，找不到则返回undefined

```javascript
// 比如这么一个对象
var mc = new MyClass(123,456);
// 这个对象本身，原型MyClass.proprototype原型对象的原型对象是Object 
// Object对象还有其原型。这组对象就构成了一个原型链。
// 这个链的次序是：mc对象、mc对象原型、原型的原型(Object) 、Object的原型
```

![image-20240325002515674](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140325246.png)

`03.原型.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 原型 prototype
			 * 
			 * 	我们所创建的每一个函数，解析器都会向函数中添加一个属性prototype
			 * 		这个属性对应着一个对象，这个对象就是我们所谓的原型对象
			 * 	如果函数作为普通函数调用prototype没有任何作用
			 * 	当函数以构造函数的形式调用时，它所创建的对象中都会有一个隐含的属性，
			 * 		指向该构造函数的原型对象，我们可以通过__proto__来访问该属性
			 * 
			 * 	原型对象就相当于一个公共的区域，所有同一个类的实例都可以访问到这个原型对象，
			 * 		我们可以将对象中共有的内容，统一设置到原型对象中。
			 * 
			 * 当我们访问对象的一个属性或方法时，它会先在对象自身中寻找，如果有则直接使用，
			 * 	如果没有则会去原型对象中寻找，如果找到则直接使用
			 * 
			 * 以后我们创建构造函数时，可以将这些对象共有的属性和方法，统一添加到构造函数的原型对象中，
			 * 	这样不用分别为每一个对象添加，也不会影响到全局作用域，就可以使每个对象都具有这些属性和方法了
			 */
			function MyClass(){
			}
			//向MyClass的原型中添加属性a
			MyClass.prototype.a = 123;
			//向MyClass的原型中添加一个方法
			MyClass.prototype.sayHello = function(){
				alert("hello");
			};
			var mc = new MyClass();
			var mc2 = new MyClass();
			//console.log(MyClass.prototype);
			//console.log(mc2.__proto__ == MyClass.prototype);  // true
			//向mc中添加a属性
			mc.a = "我是mc中的a";
			//console.log(mc2.a);
			mc.sayHello();
		</script>
	</head>
	<body>
	</body>
</html>
```

`04.原型.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个构造函数
			 */
			function MyClass(){
			}
			//向MyClass的原型中添加一个name属性
			MyClass.prototype.name = "我是原型中的名字";
			var mc = new MyClass();
			mc.age = 18;
			//console.log(mc.name);
			//使用in检查对象中是否含有某个属性时，如果对象中没有但是原型中有，也会返回true
			//console.log("name" in mc);
			//可以使用对象的hasOwnProperty()来检查对象自身中是否含有该属性
			//使用该方法只有当对象自身中含有属性时，才会返回true
			//console.log(mc.hasOwnProperty("age"));
			//console.log(mc.hasOwnProperty("hasOwnProperty"));
			/*
			 * 原型对象也是对象，所以它也有原型，
			 * 	当我们使用一个对象的属性或方法时，会现在自身中寻找，
			 * 		自身中如果有，则直接使用，
			 * 		如果没有则去原型对象中寻找，如果原型对象中有，则使用，
			 * 		如果没有则去原型的原型中寻找,直到找到Object对象的原型，
			 * 		Object对象的原型没有原型，如果在Object原型中依然没有找到，则返回undefined
			 */
			
			//console.log(mc.__proto__.hasOwnProperty("hasOwnProperty")); // false
			
			//console.log(mc.__proto__.__proto__.hasOwnProperty("hasOwnProperty")); // true
			
			//console.log(mc.__proto__.__proto__.__proto__); // null
			
			//console.log(mc.hello);
			
			//console.log(mc.__proto__.__proto__.__proto__)
			
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.8.2 `toString`方法

`05.toString.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			function Person(name , age , gender){
				this.name = name;
				this.age = age;
				this.gender = gender;
			}
			//修改Person原型的toString
			Person.prototype.toString = function(){
				return "Person[name="+this.name+",age="+this.age+",gender="+this.gender+"]";
			};
			//创建一个Person实例
			var per = new Person("孙悟空",18,"男");
			var per2 = new Person("猪八戒",28,"男");
			
			//当我们直接在页面中打印一个对象时，事件上是输出的对象的toString()方法的返回值
			//如果我们希望在输出对象时不输出[object Object]，可以为对象添加一个toString()方法
			//Person[name=孙悟空,age=18,gender=男]
			/*per.toString = function(){
				return "Person[name="+this.name+",age="+this.age+",gender="+this.gender+"]";
			};*/
			var result = per.toString();
			//console.log("result = " + result);
			//console.log(per.__proto__.__proto__.hasOwnProperty("toString"));
			console.log(per2);
			console.log(per);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.9 垃圾回收

**垃圾回收**：不再使用的对象的内存将会自动回收，这种功能称作垃圾回收。所谓不再使用的对象，指的是没有被任何一个属性（变量)引用的对象。垃圾回收的目的是，使开发者不必为对象的生命周期管理花费太多精力

![image-20240325005101725](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140325527.png)

`06.垃圾回收.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 垃圾回收（GC）
			 * 	- 就像人生活的时间长了会产生垃圾一样，程序运行过程中也会产生垃圾
			 * 		这些垃圾积攒过多以后，会导致程序运行的速度过慢，
			 * 		所以我们需要一个垃圾回收的机制，来处理程序运行过程中产生垃圾
			 *  - 当一个对象没有任何的变量或属性对它进行引用，此时我们将永远无法操作该对象，
			 * 		此时这种对象就是一个垃圾，这种对象过多会占用大量的内存空间，导致程序运行变慢，
			 * 		所以这种垃圾必须进行清理。
			 * 	- 在JS中拥有自动的垃圾回收机制，会自动将这些垃圾对象从内存中销毁，
			 * 		我们不需要也不能进行垃圾回收的操作
			 * 	- 我们需要做的只是要将不再使用的对象设置null即可
			 * 
			 */
			var obj = new Object();
			//对对象进行各种操作。。。。
			obj = null;
		</script>
	</head>
	<body>
	</body>
</html>
```

### 4.10 数组

#####  4.10.1 数组

**引用类型**：上边说到JS中除了5种基本数据类型以外其余的全都是对象，也就是引用数据类型。但是虽然全都是对象，但是对象的种类却是非常繁多的。比如Array(数组），Function(函数）这些都是不同的类型对象，实际上在JavaScript中还提供了多种不同类型的对象

```
0bject
·目前为止，我们看到的最多的类型就是Object，它也是我们在JS中使用的最多的对象。·虽然Object对象中并没有为我们提供太多的功能，但是我们会经常会用途来存储和传
输数据。
·创建Object对象有两种方式:
- var obj = new Object();- var obj= 0
.上边的两种方式都可以返回一个Object对象。
·但是第一种我们使用了一个new关键字和一个Object()函数。
·这个函数就是专门用来创建一个Object对象并返回的，像这种函数我们称为构造函数

```

**Array**：Array用于表示一个有序的数组，JS的数组中可以保存任意类型的数据

```javascript
// 创建一个数组的两种方式︰
// 1.使用构造器∶
var arr = new Array(数组的长度);
var arr = new Array(123,'hello',true);
// 2.使用[]
var arr = [];
var arr = [123,'hello',false];
// 读取数组中的值使用数组[索引]的方式，注意索引是从0开始的
```

`07.数组.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 三种类型的对象：
			 *       内建对象
			 *       宿主对象
			 *       自定义对象
			 * 
			 * 数组（Array）
			 * 	- 数组也是一个对象
			 * 	- 它和我们普通对象功能类似，也是用来存储一些值的
			 * 	- 不同的是普通对象是使用字符串作为属性名的，
			 * 		而数组时使用数字来作为索引操作元素
			 * 	- 索引：
			 * 		从0开始的整数就是索引
			 * 	- 数组的存储性能比普通对象要好，在开发中我们经常使用数组来存储一些数据
			 */
			//创建数组对象
			var arr = new Array();
			//使用typeof检查一个数组时，会返回object
			//console.log(typeof arr);
			/*
			 * 向数组中添加元素
			 * 语法：数组[索引] = 值
			 */
			arr[0] = 10;
			arr[1] = 33;
			arr[2] = 22;
			arr[3] = 44;
			/*arr[10] = 31;
			arr[100] = 90;*/
			/*
			 * 读取数组中的元素
			 * 语法：数组[索引]
			 * 	如果读取不存在的索引，他不会报错而是返回undefined
			 */
			
			//console.log(arr[3]);
			
			/*
			 * 获取数组的长度
			 * 可以使用length属性来获取数组的长度(元素的个数)
			 * 	语法：数组.length
			 * 
			 * 对于连续的数组，使用length可以获取到数组的长度（元素的个数）
			 * 对于非连续的数组，使用length会获取到数组的最大的索引+1
			 * 		尽量不要创建非连续的数组
			 */
			/*console.log(arr.length);
			console.log(arr);*/
			
			/*
			 * 修改length
			 * 	如果修改的length大于原长度，则多出部分会空出来
			 *  如果修改的length小于原长度，则多出的元素会被删除
			 */
			//arr.length = 10;
			
			/*arr.length = 2;
			
			console.log(arr.length);
			console.log(arr);*/
			arr[4] = 50;
			arr[5] = 60;
			//向数组的最后一个位置添加元素
			//语法：数组[数组.length] = 值;
			arr[arr.length] = 70;
			arr[arr.length] = 80;
			arr[arr.length] = 90;
			console.log(arr);
		</script>
	</head>
	<body>
	</body>
</html>
```

`08.数组.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			//创建一个数组
			//var arr = new Array();
			
			//使用字面量来创建数组
			//语法:[]
			//var arr = [];
			
			//console.log(typeof arr);
			
			//使用字面量创建数组时，可以在创建时就指定数组中的元素
			var arr = [1,2,3,4,5,10];
			
			//console.log(arr[3]);
			
			//使用构造函数创建数组时，也可以同时添加元素，将要添加的元素作为构造函数的参数传递
			//元素之间使用,隔开
			var arr2 = new Array(10,20,30);
			//console.log(arr2);
			
			//创建一个数组数组中只有一个元素10
			arr = [10];
			
			//创建一个长度为10的数组
			arr2 = new Array(10);
			
			//console.log(arr2.length);
			//数组中的元素可以是任意的数据类型
			arr = ["hello",1,true,null,undefined];
			
			//也可以是对象
			var obj = {name:"孙悟空"};
			arr[arr.length] = obj;
			arr = [{name:"孙悟空"},{name:"沙和尚"},{name:"猪八戒"}];
			
			//也可以是一个函数
			arr = [function(){alert(1)},function(){alert(2)}];
			
			//console.log(arr);
			//arr[0]();
			
			//数组中也可以放数组，如下这种数组我们称为二维数组
			arr = [[1,2,3],[3,4,5],[5,6,7]];
			console.log(arr[1]);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.10.1 数组的方法

`09.数组的方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			//创建一个数组
			var arr = ["孙悟空","猪八戒","沙和尚"];
			
			/*
			 * push()
			 * 	- 该方法可以向数组的末尾添加一个或多个元素，并返回数组的新的长度
			 * 	- 可以将要添加的元素作为方法的参数传递，
			 * 		这样这些元素将会自动添加到数组的末尾
			 * 	- 该方法会将数组新的长度作为返回值返回
			 */
			
			var result = arr.push("唐僧","蜘蛛精","白骨精","玉兔精");
			
			
			//console.log(arr);
			//console.log("result = "+result);
			
			/*
			 * pop()
			 * 	- 该方法可以删除数组的最后一个元素,并将被删除的元素作为返回值返回
			 */
			result = arr.pop();
			/*console.log(arr);
			console.log("result = "+result);*/
			
			/*
			 * unshift()
			 * 	- 向数组开头添加一个或多个元素，并返回新的数组长度
			 * 	- 向前边插入元素以后，其他的元素索引会依次调整
			 */
			//console.log(arr);
			
			arr.unshift("牛魔王","二郎神");
			
			console.log(arr);
			
			/*
			 * shift()
			 * 	- 可以删除数组的第一个元素，并将被删除的元素作为返回值返回
			 */
			result = arr.shift();
			result = arr.shift();
			
			console.log(arr);
			console.log("result = "+result);
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.10.2 数组的遍历

`10.数组的遍历.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			//创建一个数组
			var arr = ["孙悟空","猪八戒","沙和尚","唐僧","白骨精"];
			
			//所谓的遍历数组，就是将数组中所有的元素都取出来
			/*console.log(arr[0]);
			console.log(arr[1]);
			console.log(arr[2]);
			console.log(arr[3]);*/
			
			for(var i=0 ; i<arr.length ; i++){
				console.log(arr[i]);
			}
			
		</script>
	</head>
	<body>
	</body>
</html>
```

`11.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			function Person(name , age , gender){
				this.name = name;
				this.age = age;
			}
			
			//修改Person原型的toString
			Person.prototype.toString = function(){
				return "Person[name="+this.name+",age="+this.age+"]";
			};
			
			//创建一个Person对象
			var per = new Person("孙悟空",18);
			var per2 = new Person("猪八戒",28);
			var per3 = new Person("红孩儿",8);
			var per4 = new Person("蜘蛛精",16);
			var per5 = new Person("二郎神",38);
			
			/*
			 * 将这些person对象放入到一个数组中
			 */
			var perArr = [per,per2,per3,per4,per5];
			
			/*
			 * 创建一个函数，可以将perArr中的满18岁的Person提取出来，
			 * 	然后封装到一个新的数组中并返回
			 * arr
			 * 	形参，要提取信息的数组
			 */
			function getAdult(arr){
				//创建一个新的数组
				var newArr = [];
				
				//遍历arr，获取arr中Person对象
				for(var i=0 ; i<arr.length ; i++){
					var p = arr[i];
					//判断Person对象的age是否大于等于18
					if(p.age >= 18){
						//如果大于等于18，则将这个对象添加到newArr中
						//将对象放入到新数组中
						newArr.push(p);
					}
				}
				//将新的数组返回
				return newArr;
				
			}
			
			var result = getAdult(perArr);
			
			console.log(result);
			
			
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.10.3 forEach方法

`12.forEach.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 一般我们都是使用for循环去遍历数组，
			 * 	JS中还为我们提供了一个方法，用来遍历数组
			 * forEach()
			 * 		- 这个方法只支持IE8以上的浏览器
			 * 			IE8及以下的浏览器均不支持该方法，所以如果需要兼容IE8，则不要使用forEach
			 * 			还是使用for循环来遍历
			 */
			
			//创建一个数组
			var arr = ["孙悟空","猪八戒","沙和尚","唐僧","白骨精"];
			
			/*
			 * forEach()方法需要一个函数作为参数
			 * 	- 像这种函数，由我们创建但是不由我们调用的，我们称为回调函数
			 * 	- 数组中有几个元素函数就会执行几次，每次执行时，浏览器会将遍历到的元素
			 * 		以实参的形式传递进来，我们可以来定义形参，来读取这些内容
			 * 	- 浏览器会在回调函数中传递三个参数：
			 * 		第一个参数，就是当前正在遍历的元素
			 * 		第二个参数，就是当前正在遍历的元素的索引
			 * 		第三个参数，就是正在遍历的数组
			 * 		
			 */
			arr.forEach(function(value , index , obj){
				console.log(value);
			});
			
			
		</script>
	</head>
	<body>
	</body>
</html>
```

#####  4.10.4 slice方法 和 splice方法

`13.数组的方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			var arr = ["孙悟空","猪八戒","沙和尚","唐僧","白骨精"];
			
			/*
			 * slice()
			 * 	- 可以用来从数组提取指定元素
			 * 	- 该方法不会改变元素数组，而是将截取到的元素封装到一个新数组中返回
			 * 	- 参数：
			 * 		1.截取开始的位置的索引,包含开始索引
			 * 		2.截取结束的位置的索引,不包含结束索引
			 * 			- 第二个参数可以省略不写,此时会截取从开始索引往后的所有元素
			 * 		- 索引可以传递一个负值，如果传递一个负值，则从后往前计算
			 * 			-1 倒数第一个
			 * 			-2 倒数第二个
			 */
			
			var result = arr.slice(1,4);
			
			result = arr.slice(3);
			
			result = arr.slice(1,-2);
			
			//console.log(result);
			
			/*
			 * splice()
			 * 	- 可以用于删除数组中的指定元素
			 * 	- 使用splice()会影响到原数组，会将指定元素从原数组中删除
			 * 		并将被删除的元素作为返回值返回
			 * 	- 参数：
			 * 		第一个，表示开始位置的索引
			 * 		第二个，表示删除的数量
			 * 		第三个及以后。。
			 * 			可以传递一些新的元素，这些元素将会自动插入到开始位置索引前边
			 * 	
			 */
			
			arr = ["孙悟空","猪八戒","沙和尚","唐僧","白骨精"];
			var result = arr.splice(3,0,"牛魔王","铁扇公主","红孩儿");
			
			console.log(arr);
			//console.log(result);
			
			
		</script>
	</head>
	<body>
	</body>
</html>
```

`14.练习.html`  去除数组中重复的元素

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			//创建一个数组
			var arr = [1,2,3,2,2,1,3,4,2,5];
			
			//去除数组中重复的数字
			//获取数组中的每一个元素
			for(var i=0 ; i<arr.length ; i++){
				//console.log(arr[i]);
				/*获取当前元素后的所有元素*/
				for(var j=i+1 ; j<arr.length ; j++){
					//console.log("---->"+arr[j]);
					//判断两个元素的值是否相等
					if(arr[i] == arr[j]){
						//如果相等则证明出现了重复的元素，则删除j对应的元素
						arr.splice(j,1);
						//当删除了当前j所在的元素以后，后边的元素会自动补位
						//此时将不会在比较这个元素吧，我需要在比较一次j所在位置的元素
						//使j自减
						j--;
					}
				}
			}
			console.log(arr);
		</script>
	</head>
	<body>
	</body>
</html>
```

##### 4.10.5 数组中的其他方法

`01.数组的剩余方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
			
			var arr = ["孙悟空","猪八戒","沙和尚"];
			var arr2 = ["白骨精","玉兔精","蜘蛛精"];
			var arr3 = ["二郎神","太上老君","玉皇大帝"];
			
			/*
			 * concat()可以连接两个或多个数组，并将新的数组返回
			 * 	- 该方法不会对原数组产生影响
			 */
			var result = arr.concat(arr2,arr3,"牛魔王","铁扇公主");
			
			/*
			 * join()
			 * 	- 该方法可以将数组转换为一个字符串
			 * 	- 该方法不会对原数组产生影响，而是将转换后的字符串作为结果返回
			 * 	- 在join()中可以指定一个字符串作为参数，这个字符串将会成为数组中元素的连接符
			 * 		如果不指定连接符，则默认使用,作为连接符
			 */
			arr = ["孙悟空","猪八戒","沙和尚","唐僧"];
			
			result = arr.join("@-@");
			/*
			 * reverse()
			 * 	- 该方法用来反转数组（前边的去后边，后边的去前边）
			 * 	- 该方法会直接修改原数组
			 */
			
			arr.reverse();
			
			//console.log(arr);
			
			arr = ["b","d","e","a","c"];
			
			/*
			 * sort()
			 * 	- 可以用来对数组中的元素进行排序
			 * 	- 也会影响原数组，默认会按照Unicode编码进行排序
			 */
			arr.sort();
			//arr.reverse();
			
			/*
			 * 即使对于纯数字的数组，使用sort()排序时，也会按照Unicode编码来排序，
			 * 	所以对数字进排序时，可能会得到错误的结果。
			 * 
			 * 我们可以自己来指定排序的规则
			 * 	我们可以在sort()添加一个回调函数，来指定排序规则，
			 * 		回调函数中需要定义两个形参,
			 * 		浏览器将会分别使用数组中的元素作为实参去调用回调函数
			 * 		使用哪个元素调用不确定，但是肯定的是在数组中a一定在b前边
			 * 	- 浏览器会根据回调函数的返回值来决定元素的顺序，
			 * 		如果返回一个大于0的值，则元素会交换位置
			 * 		如果返回一个小于0的值，则元素位置不变
			 * 		如果返回一个0，则认为两个元素相等，也不交换位置
			 * 
			 * 	- 如果需要升序排列，则返回 a-b
			 * 		如果需要降序排列，则返回b-a
			 */
			arr = [5,4,2,1,3,6,8,7];
			
			arr.sort(function(a,b){
				
				//前边的大
				/*if(a > b){
					return -1;
				}else if(a < b){
					return 1;
				}else{
					return 0;
				}*/
				
				//升序排列
				//return a - b;
				
				//降序排列
				return b - a;
				
			});
			
			console.log(arr);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.11 函数的call()和apply()方法

`02.函数的方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			function fun(a,b) {
				console.log("a = "+a);
				console.log("b = "+b);
				//alert(this);
			}	
			var obj = {
				name: "obj",
				sayName:function(){
					alert(this.name);
				}
			};

			/*
			 * call()和apply()
			 * 	- 这两个方法都是函数对象的方法，需要通过函数对象来调用
			 * 	- 当对函数调用call()和apply()都会调用函数执行
			 * 	- 在调用call()和apply()可以将一个对象指定为第一个参数
			 * 		此时这个对象将会成为函数执行时的this
			 * 	- call()方法可以将实参在对象之后依次传递
			 * 	- apply()方法需要将实参封装到一个数组中统一传递
			 * 
			 * 	- this的情况：
			 * 		1.以函数形式调用时，this永远都是window
			 * 		2.以方法的形式调用时，this是调用方法的对象
			 * 		3.以构造函数的形式调用时，this是新创建的那个对象
			 * 		4.使用call和apply调用时，this是指定的那个对象
			 */
			// call()方法可以将实参在对象之后依次传递
			//fun.call(obj,2,3);
            // apply()方法需要将实参封装到一个数组中统一传递
			fun.apply(obj,[2,3]);

			

			var obj2 = {
				name: "obj2"
			};

			/*fun.apply();
			fun.call();
			fun();*/

			//fun.call(obj);
			//fun.apply(obj);

			//fun();
			
			//obj.sayName.apply(obj2);
		</script>
	</head>

	<body>
	</body>
</html>
```

###  4.12 arguments

`03.arguments.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 在调用函数时，浏览器每次都会传递进两个隐含的参数：
			 * 	1.函数的上下文对象 this
			 * 	2.封装实参的对象 arguments
			 * 		- arguments是一个类数组对象,它也可以通过索引来操作数据，也可以获取长度
			 * 		- 在调用函数时，我们所传递的实参都会在arguments中保存
			 * 		- arguments.length可以用来获取实参的长度
			 * 		- 我们即使不定义形参，也可以通过arguments来使用实参，
			 * 			只不过比较麻烦
			 * 			arguments[0] 表示第一个实参
			 * 			arguments[1] 表示第二个实参 。。。
			 *		- arguments里边有一个属性叫做callee，
			 * 			这个属性对应一个函数对象，就是当前正在指向的函数的对象
			 * 		
			 */
			function fun(a,b){
				//console.log(arguments instanceof Array);
				//console.log(Array.isArray(arguments));
				//console.log(arguments[1]);
				//console.log(arguments.length);
				console.log(arguments.callee == fun);
			}
			fun("hello",true);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.13 Date对象

`04.Date.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * Date对象
			 * 	- 在JS中使用Date对象来表示一个时间
			 */
			
			//创建一个Date对象
			//如果直接使用构造函数创建一个Date对象，则会封装为当前代码执行的时间
			var d = new Date();
			
			//创建一个指定的时间对象
			//需要在构造函数中传递一个表示时间的字符串作为参数
			//日期的格式  月份/日/年 时:分:秒
			var d2 = new Date("2/18/2011 11:10:30");
			
			/*
			 * getDate()
			 * 	- 获取当前日期对象是几日
			 */
			var date = d2.getDate();
			/*
			 * getDay()
			 * 	- 获取当前日期对象时周几
			 * 	- 会返回一个0-6的值
			 * 		0 表示周日
			 * 		1表示周一
			 * 		。。。
			 */
			var day = d2.getDay();
			
			/*
			 * getMonth()
			 * d2 = new Date("12/18/2011 11:10:30");
			 * - 获取当前时间对象的月份
			 * 	- 会返回一个0-11的值
			 * 		0 表示1月
			 * 		1 表示2月
			 * 		11 表示12月
			 */
			var month = d2.getMonth();
			
			/*
			 * getFullYear()
			 * 	- 获取当前日期对象的年份
			 */
			var year = d2.getFullYear();
			
			//console.log(d2);
			//console.log("date = "+date);
			//console.log("day = "+day);
			//console.log("month = "+month);
			//console.log(year);
			
			/*
			 * getTime()
			 * 	- 获取当前日期对象的时间戳
			 * 	- 时间戳，指的是从格林威治标准时间的1970年1月1日，0时0分0秒
			 * 		到当前日期所花费的毫秒数（1秒 = 1000毫秒）
			 * 	- 计算机底层在保存时间时使用都是时间戳
			 */
			
			var time = d2.getTime();
			
			//console.log(time/1000/60/60/24/365);
			
			/*var d3 = new Date("1/1/1970 0:0:0");
			time = d3.getTime();
			console.log(time);*/
			
			//利用时间戳来测试代码的执行的性能
			//获取当前的时间戳
			var start = Date.now();
			
			for(var i=0 ; i<100 ; i++){
				console.log(i);
			}
			var end = Date.now();
			console.log("执行了："+(end - start)+"毫秒");
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.14 Math工具类

`05.Math.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * Math
			 * 	- Math和其他的对象不同，它不是一个构造函数，
			 * 		它属于一个工具类不用创建对象，它里边封装了数学运算相关的属性和方法
			 * 	- 比如
			 * 		Math.PI 表示的圆周率
			 */
			
			//console.log(Math.PI);
			
			/*
			 * abs()可以用来计算一个数的绝对值
			 */
			//console.log(Math.abs(-1));
			
			/*
			 * Math.ceil()
			 * 	- 可以对一个数进行向上取整，小数位只有有值就自动进1
			 * Math.floor()
			 * 	- 可以对一个数进行向下取整，小数部分会被舍掉
			 * Math.round()
			 * 	- 可以对一个数进行四舍五入取整
			 */
			//console.log(Math.ceil(1.1));
			//console.log(Math.floor(1.99));
			//console.log(Math.round(1.4));
			
			/*
			 * Math.random()
			 * 	- 可以用来生成一个0-1之间的随机数
			 *  - 生成一个0-10的随机数
			 * 	- 生成一个0-x之间的随机数
			 * 		Math.round(Math.random()*x)
			 * 
			 * 	- 生成一个1-10
			 * 	- 生成一个x-y之间的随机数
			 * 		Math.round(Math.random()*(y-x)+x)
			 */
			/*for(var i=0 ; i<100 ; i++){
				//console.log(Math.round(Math.random()*10));
				//console.log(Math.round(Math.random()*20));
				
				//console.log(Math.round(Math.random()*9)+1);
				//console.log(Math.round(Math.random()*8)+2);
				
				//生成1-6之间的随机数
				console.log(Math.round(Math.random()*5+1));
			}*/
			
			/*
			 * max() 可以获取多个数中的最大值
			 * min() 可以获取多个数中的最小值
			 */
			
			var max = Math.max(10,45,30,100);
			var min = Math.min(10,45,30,100);
			//console.log(min);
			
			/*
			 * Math.pow(x,y)
			 * 	返回x的y次幂
			 */
			
			//console.log(Math.pow(12,3));
			
			/*
			 * Math.sqrt()
			 *  用于对一个数进行开方运算
			 */
			console.log(Math.sqrt(2));
			
		</script>
	</head>
	<body>
	</body>
</html>
```

### 4.15 包装类

`06.包装类.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 基本数据类型
			 * 	String Number Boolean Null Undefined
			 * 引用数据类型
			 * 	Object
			 * 
			 * 在JS中为我们提供了三个包装类，通过这三个包装类可以将基本数据类型的数据转换为对象
			 * 	String()
			 * 		- 可以将基本数据类型字符串转换为String对象
			 * 	Number()
			 * 		- 可以将基本数据类型的数字转换为Number对象
			 *  Boolean()
			 * 		- 可以将基本数据类型的布尔值转换为Boolean对象
			 * 	但是注意：我们在实际应用中不会使用基本数据类型的对象，
			 * 		如果使用基本数据类型的对象，在做一些比较时可能会带来一些不可预期的结果
			 */
			
			//创建一个Number类型的对象
			//num = 3;
			var num = new Number(3);
			var num2 = new Number(3);
			var str = new String("hello");
			var str2 = new String("hello");
			var bool = new Boolean(true);
			var bool2 = true;
			
			//向num中添加一个属性
			num.hello = "abcdefg";
			
			//console.log(str === str2);
			
			var b = new Boolean(false);
			
			/*if(b){
				alert("我运行了~~~");
			}*/
			
			/*
			 * 方法和属性之能添加给对象，不能添加给基本数据类型
			 * 	当我们对一些基本数据类型的值去调用属性和方法时，
			 * 		浏览器会临时使用包装类将其转换为对象，然后在调用对象的属性和方法
			 * 		调用完以后，在将其转换为基本数据类型
			 */
			var s = 123;
			
			s = s.toString();
			
			s.hello = "你好";
			
			console.log(s.hello);
			//console.log(typeof s);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  4.16 字符串相关方法

`07.字符串的相关方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			//创建一个字符串
			var str = "Hello Atguigu";
			
			/*
			 * 在底层字符串是以字符数组的形式保存的
			 * ["H","e","l"]
			 */
			
			/*
			 * length属性
			 * 	- 可以用来获取字符串的长度
			 */
			//console.log(str.length);
			//console.log(str[5]);
			
			/*
			 * charAt()
			 * 	- 可以返回字符串中指定位置的字符
			 * 	- 根据索引获取指定的字符	
			 */
			str = "中Hello Atguigu";
			
			var result = str.charAt(6);
			
			/*
			 * charCodeAt()
			 * 	- 获取指定位置字符的字符编码（Unicode编码）
			 */
			
			result = str.charCodeAt(0);
			
			/*
			 * String.formCharCode()
			 * 	- 可以根据字符编码去获取字符
			 */
			result = String.fromCharCode(0x2692);
			
			/*
			 * concat()
			 * 	- 可以用来连接两个或多个字符串
			 * 	- 作用和+一样
			 */
			result = str.concat("你好","再见");
			
			/*
			 * indexof()
			 * 	- 该方法可以检索一个字符串中是否含有指定内容
			 * 	- 如果字符串中含有该内容，则会返回其第一次出现的索引
			 * 		如果没有找到指定的内容，则返回-1
			 * 	- 可以指定一个第二个参数，指定开始查找的位置
			 * 
			 * lastIndexOf();
			 * 	- 该方法的用法和indexOf()一样，
			 * 		不同的是indexOf是从前往后找，
			 * 		而lastIndexOf是从后往前找
			 * 	- 也可以指定开始查找的位置
			 */
			
			str = "hello hatguigu";
			
			result = str.indexOf("h",1);
			
			result = str.lastIndexOf("h",5);
			
			/*
			 * slice()
			 * 	- 可以从字符串中截取指定的内容
			 * 	- 不会影响原字符串，而是将截取到内容返回
			 * 	- 参数：
			 * 		第一个，开始位置的索引（包括开始位置）
			 * 		第二个，结束位置的索引（不包括结束位置）
			 * 			- 如果省略第二个参数，则会截取到后边所有的
			 * 		- 也可以传递一个负数作为参数，负数的话将会从后边计算
			 */
			str = "abcdefghijk";
			
			result = str.slice(1,4);
			result = str.slice(1,-1);
			
			/*
			 * substring()
			 * 	- 可以用来截取一个字符串，可以slice()类似
			 * 	- 参数：
			 * 		- 第一个：开始截取位置的索引（包括开始位置）
			 * 		- 第二个：结束位置的索引（不包括结束位置）
			 * 		- 不同的是这个方法不能接受负值作为参数，
			 * 			如果传递了一个负值，则默认使用0
			 * 		- 而且他还自动调整参数的位置，如果第二个参数小于第一个，则自动交换
			 */
			
			result = str.substring(0,1);
			
			/*
			 * substr()
			 * 	- 用来截取字符串
			 * 	- 参数：
			 * 		1.截取开始位置的索引
			 * 		2.截取的长度
			 */
			
			str = "abcdefg";
			
			result = str.substr(3,2);
			
			/*
			 * split()
			 * 	- 可以将一个字符串拆分为一个数组
			 * 	- 参数：
			 * 		-需要一个字符串作为参数，将会根据该字符串去拆分数组
			 */
			str = "abcbcdefghij";
			
			result = str.split("d");
			
			/*
			 * 如果传递一个空串作为参数，则会将每个字符都拆分为数组中的一个元素
			 */
			result = str.split("");
			
			//console.log(Array.isArray(result));
			//console.log(result[0]);
			console.log(result);
			str = "abcdefg";
			/*
			 * toUpperCase()
			 * 	- 将一个字符串转换为大写并返回
			 */
			result = str.toUpperCase();
			str = "ABCDEFG";
			/*
			 * toLowerCase()
			 * 	-将一个字符串转换为小写并返回
			 */
			result = str.toLowerCase();
			//console.log(result);
		</script>
	</head>
	<body>
	</body>
</html>
```

##  5 正则表达式

###  5.1 正则表达式

`08.正则表达式.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 正则表达式
			 * 	- admin@atguigu.com
			 *  - admin@.com   adminatguigu.com
			 *  - 邮件的规则：
			 * 		1.前边可以是xxxx乱七八糟
			 * 		2.跟着一个@
			 * 		3.后边可以是xxxx乱七八糟
			 * 		4..com获取其他的乱七八糟
			 * 
			 * 	- 正则表达式用于定义一些字符串的规则，
			 * 		计算机可以根据正则表达式，来检查一个字符串是否符合规则，
			 * 		获取将字符串中符合规则的内容提取出来
			 */
			
			//创建正则表达式的对象
			/*
			 * 语法：
			 * 	var 变量 = new RegExp("正则表达式","匹配模式");
			 *  使用typeof检查正则对象，会返回object
			 * 	var reg = new RegExp("a"); 这个正则表达式可以来检查一个字符串中是否含有a
			 * 在构造函数中可以传递一个匹配模式作为第二个参数，
			 * 		可以是 
			 * 			i 忽略大小写 
			 * 			g 全局匹配模式
			 */
			var reg = new RegExp("ab","i");
			var str = "a";
			/*
			 * 正则表达式的方法：
			 * 	test()
			 * 	 - 使用这个方法可以用来检查一个字符串是否符合正则表达式的规则，
			 * 		如果符合则返回true，否则返回false
			 */
			var result = reg.test(str);
			//console.log(result);
			console.log(reg.test("Ac"));
		</script>
	</head>
	<body>
	</body>
</html>
```

`09.正则表达式.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 使用字面量来创建正则表达式
			 * 	语法：var 变量 = /正则表达式/匹配模式
			 * 使用字面量的方式创建更加简单
			 * 	使用构造函数创建更加灵活
			 * 
			 */
			//var reg = new RegExp("a","i");
			
			var reg = /a/i;
			
			//console.log(typeof reg);
			//console.log(reg.test("abc"));
			
			//创建一个正则表达式，检查一个字符串中是否有a或b
			/*
			 * 使用 | 表示或者的意思
			 */
			reg = /a|b|c/;
			/*
			 * 创建一个正则表达式检查一个字符串中是否有字母
			 */
			//reg = /a|b|c|d|e|f|g/;
			/*
			 * []里的内容也是或的关系
			 * [ab] == a|b
			 * [a-z] 任意小写字母
			 * [A-Z] 任意大写字母
			 * [A-z] 任意字母
			 * [0-9] 任意数字
			 */
			reg = /[A-z]/;
			//检查一个字符串中是否含有 abc 或 adc 或 aec
			reg = /a[bde]c/;
			/*
			 * [^ ] 除了
			 */
			reg = /[^ab]/;
			reg = /[^0-9]/;
			console.log(reg.test("12a3456"));
		</script>
	</head>
	<body>
	</body>
</html>
```

### 5.2 正则表达式和字符串相关方法

`10.字符串和正则相关的方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			var str = "1a2b3c4d5e6f7";
			/*
			 * split()
			 * 	- 可以将一个字符串拆分为一个数组
			 * 	- 方法中可以传递一个正则表达式作为参数，这样方法将会根据正则表达式去拆分字符串
			 * 	- 这个方法即使不指定全局匹配，也会全都插分
			 */
			
			/*
			 * 根据任意字母来将字符串拆分
			 */
			var result = str.split(/[A-z]/);
			
			
			//console.log(result);
			
			/*
			 * search()
			 * 	- 可以搜索字符串中是否含有指定内容
			 * 	- 如果搜索到指定内容，则会返回第一次出现的索引，如果没有搜索到返回-1
			 * 	- 它可以接受一个正则表达式作为参数，然后会根据正则表达式去检索字符串
			 * 	- serach()只会查找第一个，即使设置全局匹配也没用
			 */
			str = "hello abc hello aec afc";
			/*
			 * 搜索字符串中是否含有abc 或 aec 或 afc
			 */
			result = str.search(/a[bef]c/);
			
			//console.log(result);
			
			
			/*
			 * match()
			 * 	- 可以根据正则表达式，从一个字符串中将符合条件的内容提取出来
			 * 	- 默认情况下我们的match只会找到第一个符合要求的内容，找到以后就停止检索
			 * 		我们可以设置正则表达式为全局匹配模式，这样就会匹配到所有的内容
			 * 		可以为一个正则表达式设置多个匹配模式，且顺序无所谓,如ig表示忽略大小写的全局匹配
			 * 	- match()会将匹配到的内容封装到一个数组中返回，即使只查询到一个结果
			 * 
			 * 	
			 */
			str = "1a2a3a4a5e6f7A8B9C";
			
			result = str.match(/[a-z]/ig);
			
			//console.log(result[2]);
			
			/*
			 * replace()
			 * 	- 可以将字符串中指定内容替换为新的内容
			 *  - 参数：
			 * 		1.被替换的内容，可以接受一个正则表达式作为参数
			 * 		2.新的内容
			 *  - 默认只会替换第一个
			 */
			//result = str.replace(/[a-z]/gi , "@_@");
			result = str.replace(/[a-z]/gi , "");
			
			//console.log(result);
			
		</script>
	</head>
	<body>
	</body>
</html>
```

### 5.3 正则表达式语法

`10.字符串和正则相关的方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 创建一个正则表达式检查一个字符串中是否含有aaa
			 */
			
			/*
			 * 量词
			 * 	- 通过量词可以设置一个内容出现的次数
			 * 	- 量词只对它前边的一个内容起作用
			 * 	- {n} 正好出现n次
			 * 	- {m,n} 出现m-n次
			 * 	- {m,} m次以上
			 * 	- + 至少一个，相当于{1,}
			 * 	- * 0个或多个，相当于{0,}
			 * 	- ? 0个或1个，相当于{0,1}
			 */
			var reg = /a{3}/;
			//ababab
			reg = /(ab){3}/;
			
			reg = /b{3}/;
			
			reg = /ab{1,3}c/;
			
			reg = /ab{3,}c/;
			
			reg = /ab+c/;
			
			reg = /ab*c/;
			
			reg = /ab?c/;
			
			//console.log(reg.test("abbc"));
			
			/*
			 * 检查一个字符串中是否以a开头
			 * 	^ 表示开头
			 * 	$ 表示结尾
			 */
			reg = /^a/; //匹配开头的a
			
			reg = /a$/; //匹配结尾的a
			
			//console.log(reg.test("abcabca"));
			
			/*
			 * 如果在正则表达式中同时使用^ $则要求字符串必须完全符合正则表达式
			 */
			reg = /^a$/;
			
			//console.log(reg.test("bbca"));
			/*
			 * 创建一个正则表达式，用来检查一个字符串是否是一个合法手机号
			 * 
			 * 手机号的规则：
			 * 	1 3 567890123 （11位）
			 * 	
			 * 	1. 以1开头
			 *  2. 第二位3-9任意数字
			 * 	3. 三位以后任意数字9个
			 * 
			 *  ^1   [3-9]  [0-9]{9}$  
			 * 
			 */
			var phoneStr = "13067890123";
			
			var phoneReg = /^1[3-9][0-9]{9}$/;
			
			console.log(phoneReg.test(phoneStr));
		</script>
	</head>
	<body>
	</body>
</html>
```

##### 5.4 正则表达式语法二

`03.正则表达式.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 检查一个字符串中是否含有 .
			 * . 表示任意字符
			 * 在正则表达式中使用\作为转义字符
			 * \. 来表示.
			 * \\  表示\
			 * 
			 * 注意：使用构造函数时，由于它的参数是一个字符串，而\是字符串中转义字符，
			 * 	如果要使用\则需要使用\\来代替
			 */
			var reg = /\./;
			
			reg = /\\/;
			
			reg = new RegExp("\\.");
			reg = new RegExp("\\\\");
			
			/*
			 * \w
			 * 	- 任意字母、数字、_  [A-z0-9_]
			 * \W
			 * 	- 除了字母、数字、_  [^A-z0-9_]
			 * \d
			 * 	- 任意的数字 [0-9]
			 * \D
			 * 	- 除了数字 [^0-9]
			 * \s
			 * 	- 空格
			 * \S
			 * 	- 除了空格
			 * \b
			 * 	- 单词边界
			 * \B
			 * 	- 除了单词边界
			 */
			
			reg = /\w/;
			reg = /\W/;
			
			reg = /\d/;
			reg = /\D/;
			
			reg = /\s/;
			reg = /\S/;
			
			/*
			 * 创建一个正则表达式检查一个字符串中是否含有单词child
			 */
			
			reg = /\bchild\b/;
			
			//console.log(reg.test("hello child "));
			
			//接收一个用户的输入
			//var str = prompt("请输入你的用户名:");
			
			var str = "              he      llo                ";
			
			//去除掉字符串中的前后的空格
			//去除空格就是使用""来替换空格
			console.log(str);
			
			//str = str.replace(/\s/g , "");  // 去除所有空格
			//去除开头的空格
			//str = str.replace(/^\s*/, "");
			//去除结尾的空格
			//str = str.replace(/\s*$/, "");
			// /^\s*|\s*$/g 匹配开头和结尾的空格
			str = str.replace(/^\s*|\s*$/g,"");
			console.log(str);
		</script>
	</head>
	<body>
	</body>
</html>
```

`04.邮件的正则.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			/*
			 * 电子邮件
			 * 	hello  .nihao          @     abc  .com.cn
			 * 
			 * 任意字母数字下划线    .任意字母数字下划线  @   任意字母数字     .任意字母（2-5位）   .任意字母（2-5位）
			 * 
			 * \w{3,}  (\.\w+)*  @  [A-z0-9]+  (\.[A-z]{2,5}){1,2}
			 */
			
			var emailReg = /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;
			
			var email = "abc.hello@163.com";
			console.log(emailReg.test(email));
		</script>
	</head>
	<body>
	</body>
</html>
```

## 6. DOM

###  6.1 DOM简介

**DOM**：DOM全称Document Object Model文档对象模型。JS中通过DOM来对HTML文档进行操作。只要理解了DOM就可以随心所欲的操作WEB页面。文档Document 表示的就是整个的HTML网页文档，对象Object表示将网页中的每一个部分都转换为了一个对象，模型Model表示对象之间的关系，这样方便获取对象

**模型**：

![image-20240326223259952](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140326884.png)

**节点Node**：节点Node是构成网页的最基本的组成部分，网页中的每一个部分都可以称为是一个节点。比如: html标签、属性、文本、注释、整个文档等都是一个节点。虽然都是节点，但是实际上他们的具体类型是不同的。比如∶标签称为元素节点、属性称为属性节点、文本称为文本节点、文档称为文档节点。节点的类型不同，属性和方法也都不尽相同。节点Node是构成HTML文档最基本的单元

**常用节点分为四类**：

- 文档节点：  整个HTML文档
- 元素节点：  HTML文档中的HTML标签
- 属性节点∶  元素的属性
- 文本节点:   HTML标签中的文本内容

![image-20240326014004974](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140326401.png)

**节点的属性**：

| .        | nodeName  | nodeType | nodeValue |
| -------- | --------- | -------- | --------- |
| 文档节点 | #document | 9        | null      |
| 元素节点 | 标签名    | 1        | null      |
| 属性节点 | 属性名    | 2        | 属性值    |
| 文本节点 | #text     | 3        | 文本内容  |

**文档节点（document)**：文档节点document代表的是整个HTML文档，网页中的所有节点都是它的子节点。document对象作为window对象的属性存在的，我们不用获取可以直接使用，通过该对象可以在整个文档访问内查找节点对象，并可以通过该对象创建各种节点对象

**元素节点(Element）**：HTML中的各种标签都是元素节点，这也是最常用的一个节点。浏览器会将页面中所有的标签都转换为一个元素节点，我们可以通过document的方法来获取元素节点，比如document.getElementById() 表示根据id属性值获取一个元素节点对象

**文本节点（Text)**：文本节点表示的是HTML标签以外的文本内容，任意非HTML的文本都是文本节点。它包括可以字面解释的纯文本内容。文本节点一般是作为元素节点的子节点存在的。获取文本节点时，一般先要获取元素节点。在通过元素节点获取文本节点，例如`元素节点.firstChild;`表示获取元素节点的第一个子节点，一般为文本节点

**属性节点（Attr)**：属性节点表示的是标签中的一个一个的属性，这里要注意的是属性节点并非是元素节点的子节点，而是元素节点的一部分。可以通过元素节点来获取指定的属性节点，例如`元素节点.getAttributeNode("属性名");`。注意：一般不使用属性节点

`05.DOM.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<button id="btn">我是一个按钮</button>
		<script type="text/javascript">
			/*
			 * 浏览器已经为我们提供 文档节点 对象这个对象是window属性
			 * 	可以在页面中直接使用，文档节点代表的是整个网页
			 */
			//console.log(document);
			
			//获取到button对象
			var btn = document.getElementById("btn");
			
			//修改按钮的文字
			btn.innerHTML = "I'm Button";
		</script>
	</body>
</html>
```

###  6.2 事件

**事件**：事件就是文档或浏览器窗口中发生的一些特定的交互瞬间，JavaScript与HTML之间的交互是通过事件实现的。对于Web应用来说，有下面这些代表性的事件︰点击某个元素、将鼠标移动至某个元素上方、按下键盘上某个键等等

`06.事件.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<!--
			我们可以在事件对应的属性中设置一些js代码，
				这样当事件被触发时，这些代码将会执行
				
			这种写法我们称为结构和行为耦合，不方便维护，不推荐使用	
		-->
		<!--<button id="btn" onmousemove="alert('讨厌，你点我干嘛！');">我是一个按钮</button>-->
		<button id="btn">我是一个按钮</button>
		<script type="text/javascript">
			/*
			 * 事件，就是用户和浏览器之间的交互行为，
			 * 	比如：点击按钮，鼠标移动、关闭窗口。。。
			 */
			//获取按钮对象
			var btn = document.getElementById("btn");
			
			/*
			 * 可以为按钮的对应事件绑定处理函数的形式来响应事件
			 * 	这样当事件被触发时，其对应的函数将会被调用
			 */
			
			//绑定一个单击事件
			//像这种为单击事件绑定的函数，我们称为单击响应函数
			btn.onclick = function(){
				alert("你还点~~~");
			};
		</script>
	</body>
</html>
```

###  6.3 文档的加载

`07.文档的加载.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * 浏览器在加载一个页面时，是按照自上向下的顺序加载的，
			 * 	读取到一行就运行一行,如果将script标签写到页面的上边，
			 * 	在代码执行时，页面还没有加载，页面没有加载DOM对象也没有加载
			 * 	会导致无法获取到DOM对象
			 */
			
			/*
			 * onload事件会在整个页面加载完成之后才触发
			 * 为window绑定一个onload事件
			 * 		该事件对应的响应函数将会在页面加载完成之后执行，
			 * 		这样可以确保我们的代码执行时所有的DOM对象已经加载完毕了
			 * 	
			 */
			window.onload = function(){
				//获取id为btn的按钮
				var btn = document.getElementById("btn");
				//为按钮绑定一个单击响应函数
				btn.onclick = function(){
					alert("hello");
				};
			};	
		</script>
	</head>
	<body>
		<button id="btn">点我一下</button>
		<!--<script type="text/javascript">
			/*
			 * 将js代码编写到页面的下部就是为了，可以在页面加载完毕以后再执行js代码
			 */
			//获取id为btn的按钮
			var btn = document.getElementById("btn");
			//为按钮绑定一个单击响应函数
			btn.onclick = function(){
				alert("hello");
			};
		</script>-->
	</body>
</html>
```

### 6.4 DOM查询

##### 6.4.1 通过document对象查询元素节点

```
通过document对象查询元素节点
        1.getElementById()
                通过id属性获取一个元素节点对象
        2.getElementsByTagName()
                通过标签名获取一组元素节点对象
        3.getElementsByName()
                通过name属性获取一组元素节点对象
```

![image-20240326230035682](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140326304.png)

`08.dom查询.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Untitled Document</title>
		<link rel="stylesheet" type="text/css" href="style/css.css" />
		<script type="text/javascript">
			window.onload = function(){
				//1.练习一：查找#bj节点
                // 为id为btn01的按钮绑定一个单击响应函数
				var btn01 = document.getElementById("btn01");
				btn01.onclick = function(){
					//查找#bj节点
					var bj = document.getElementById("bj");
					//打印bj
					//innerHTML 通过这个属性可以获取到元素内部的html代码
					alert(bj.innerHTML);
				};
				
				// 2.练习二：查找所有li节点
				//为id为btn02的按钮绑定一个单击响应函数
				var btn02 = document.getElementById("btn02");
				btn02.onclick = function(){
					//查找所有li节点
					//getElementsByTagName()可以根据标签名来获取一组元素节点对象
					//这个方法会给我们返回一个类数组对象，所有查询到的元素都会封装到对象中
					//即使查询到的元素只有一个，也会封装到数组中返回
					var lis = document.getElementsByTagName("li");
					
					//打印lis
					//alert(lis.length);
					
					//变量lis
					for(var i=0 ; i<lis.length ; i++){
						alert(lis[i].innerHTML);
					}
				};
                
                // 3.练习三：查找name=gender的所有节点
				//为id为btn03的按钮绑定一个单击响应函数
				var btn03 = document.getElementById("btn03");
				btn03.onclick = function(){
					//查找name=gender的所有节点
					var inputs = document.getElementsByName("gender");
					
					//alert(inputs.length);
					
					for(var i=0 ; i<inputs.length ; i++){
						/*
						 * innerHTML用于获取元素内部的HTML代码的
						 * 	对于自结束标签，这个属性没有意义
						 */
						//alert(inputs[i].innerHTML);
						/*
						 * 如果需要读取元素节点属性，
						 * 	直接使用 元素.属性名
						 * 		例子：元素.id、 元素.name、 元素.value
						 * 		注意：class属性不能采用这种方式（class是保留字）
						 * 			读取class属性时需要使用 元素.className
						 */
						alert(inputs[i].className);
					}
				};
				//查找#city下所有li节点
				//返回#city的所有子节点
				//返回#phone的第一个子节点
				//返回#bj的父节点
				//返回#android的前一个兄弟节点
				//读取#username的value属性值
				//设置#username的value属性值
				//返回#bj的文本值
			};
		</script>
	</head>
	<body>
		<div id="total">
			<div class="inner">
				<p>
					你喜欢哪个城市?
				</p>
				<ul id="city">
					<li id="bj">北京</li>
					<li>上海</li>
					<li>东京</li>
					<li>首尔</li>
				</ul>
				<br>
				<br>
				<p>
					你喜欢哪款单机游戏?
				</p>
				<ul id="game">
					<li id="rl">红警</li>
					<li>实况</li>
					<li>极品飞车</li>
					<li>魔兽</li>
				</ul>
				<br />
				<br />
				<p>
					你手机的操作系统是?
				</p>
				<ul id="phone"><li>IOS</li><li id="android">Android</li><li>Windows Phone</li></ul>
			</div>
			<div class="inner">
				gender:
				<input class="hello" type="radio" name="gender" value="male"/>
				Male
				<input class="hello" type="radio" name="gender" value="female"/>
				Female
				<br>
				<br>
				name:
				<input type="text" name="name" id="username" value="abcde"/>
			</div>
		</div>
		<div id="btnList">
			<div><button id="btn01">查找#bj节点</button></div>
			<div><button id="btn02">查找所有li节点</button></div>
			<div><button id="btn03">查找name=gender的所有节点</button></div>
			<div><button id="btn04">查找#city下所有li节点</button></div>
			<div><button id="btn05">返回#city的所有子节点</button></div>
			<div><button id="btn06">返回#phone的第一个子节点</button></div>
			<div><button id="btn07">返回#bj的父节点</button></div>
			<div><button id="btn08">返回#android的前一个兄弟节点</button></div>
			<div><button id="btn09">返回#username的value属性值</button></div>
			<div><button id="btn10">设置#username的value属性值</button></div>
			<div><button id="btn11">返回#bj的文本值</button></div>
		</div>
	</body>
</html>
```

#####  6.4.2 图片切换练习

`09.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			*{
				margin: 0;
				padding: 0;
			}
			
			#outer{
				width: 500px;
				margin: 50px auto;
				padding: 10px;
				background-color: greenyellow;
				/*设置文本居中*/
				text-align: center;
			}
		</style>
		
		<script type="text/javascript">
			
			window.onload = function(){
				
				/*
				 * 点击按钮切换图片
				 */
				
				//获取两个按钮
				var prev = document.getElementById("prev");
				var next = document.getElementById("next");
				
				/*
				 * 要切换图片就是要修改img标签的src属性
				 */
				
				//获取img标签
				var img = document.getElementsByTagName("img")[0];
				
				//创建一个数组，用来保存图片的路径
				var imgArr = ["img/1.jpg" , "img/2.jpg" , "img/3.jpg" , "img/4.jpg" ,"img/5.jpg"];
				
				//创建一个变量，来保存当前正在显示的图片的索引
				var index = 0;
				
				//获取id为info的p元素
				var info = document.getElementById("info");
				//设置提示文字
				info.innerHTML = "一共 "+imgArr.length+" 张图片，当前第 "+(index+1)+" 张";
				
				
				//分别为两个按钮绑定单击响应函数
				prev.onclick = function(){
					
					/*
					 * 切换到上一张，索引自减
					 */
					index--;
					
					//判断index是否小于0
					if(index < 0){
						index = imgArr.length - 1;
					}
					
					img.src = imgArr[index];
					
					//当点击按钮以后，重新设置信息
					info.innerHTML = "一共 "+imgArr.length+" 张图片，当前第 "+(index+1)+" 张";
				};
				
				next.onclick = function(){
					
					/*
					 * 切换到下一张是index自增
					 */
					index++;
					
					if(index > imgArr.length - 1){
						index = 0;
					}
					
					//切换图片就是修改img的src属性
					//要修改一个元素的属性 元素.属性 = 属性值
					img.src = imgArr[index];
					
					//当点击按钮以后，重新设置信息
					info.innerHTML = "一共 "+imgArr.length+" 张图片，当前第 "+(index+1)+" 张";
				};
			};
		</script>
	</head>
	<body>
		<div id="outer">
			<p id="info"></p>
			<img src="img/1.jpg" alt="冰棍" />
			<button id="prev">上一张</button>
			<button id="next">下一张</button>
		</div>
	</body>
</html>
```

#####  6.4.3 获取元素节点的子节点

```
1.getElementsByTagName()方法
      返回当前节点的指定标签名后代节点
2.childNodes
      属性，表示当前节点的所有子节点
3. firstChild
      属性，表示当前节点的第一个子节点
4.lastChild
      属性，表示当前节点的最后一个子节点
```

![image-20240326230035682](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/javascript/202404140326276.png)

`01.dom查询.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Untitled Document</title>
		<link rel="stylesheet" type="text/css" href="style/css.css" />
		<script type="text/javascript">
			/*
			 * 定义一个函数，专门用来为指定元素绑定单击响应函数
			 * 	参数：
			 * 		idStr 要绑定单击响应函数的对象的id属性值
			 * 		fun 事件的回调函数，当单击元素时，该函数将会被触发
			 */
			function myClick(idStr , fun){
				var btn = document.getElementById(idStr);
				btn.onclick = fun;
			}
		    
			window.onload = function(){
				// 1.练习一：查找#bj节点
				//为id为btn01的按钮绑定一个单击响应函数
				var btn01 = document.getElementById("btn01");
				btn01.onclick = function(){
					//查找#bj节点
					var bj = document.getElementById("bj");
					//打印bj
					//innerHTML 通过这个属性可以获取到元素内部的html代码
					alert(bj.innerHTML);
				};
				
				// 2.练习二：查找所有li节点
				//为id为btn02的按钮绑定一个单击响应函数
				var btn02 = document.getElementById("btn02");
				btn02.onclick = function(){
					//查找所有li节点
					//getElementsByTagName()可以根据标签名来获取一组元素节点对象
					//这个方法会给我们返回一个类数组对象，所有查询到的元素都会封装到对象中
					//即使查询到的元素只有一个，也会封装到数组中返回
					var lis = document.getElementsByTagName("li");
					
					//打印lis
					//alert(lis.length);
					
					//变量lis
					for(var i=0 ; i<lis.length ; i++){
						alert(lis[i].innerHTML);
					}
				};
				
				// 3.练习三：查找name=gender的所有节点
				//为id为btn03的按钮绑定一个单击响应函数
				var btn03 = document.getElementById("btn03");
				btn03.onclick = function(){
					//查找name=gender的所有节点
					var inputs = document.getElementsByName("gender");
					
					//alert(inputs.length);
					
					for(var i=0 ; i<inputs.length ; i++){
						/*
						 * innerHTML用于获取元素内部的HTML代码的
						 * 	对于自结束标签，这个属性没有意义
						 */
						//alert(inputs[i].innerHTML);
						/*
						 * 如果需要读取元素节点属性，
						 * 	直接使用 元素.属性名
						 * 		例子：元素.id 元素.name 元素.value
						 * 		注意：class属性不能采用这种方式，
						 * 			读取class属性时需要使用 元素.className
						 */
						alert(inputs[i].className);
					}
				};
				
                // 4.练习四：获取id为city的元素
				//为id为btn04的按钮绑定一个单击响应函数
				var btn04 = document.getElementById("btn04");
				btn04.onclick = function(){
					
					//获取id为city的元素
					var city = document.getElementById("city");
					
					//查找#city下所有li节点
					var lis = city.getElementsByTagName("li");
					
					for(var i=0 ; i<lis.length ; i++){
						alert(lis[i].innerHTML);
					}
					
				};
				
                // 5.练习五：返回#city的所有子节点
				//为id为btn05的按钮绑定一个单击响应函数
				var btn05 = document.getElementById("btn05");
				btn05.onclick = function(){
					//获取id为city的节点
					var city = document.getElementById("city");
					//返回#city的所有子节点
					/*
					 * childNodes属性会获取包括文本节点在内的所有节点
					 * 根据DOM标签标签间空白也会当成文本节点
					 * 注意：在IE8及以下的浏览器中，不会将空白文本当成子节点，
					 * 	所以该属性在IE8中会返回4个子元素而其他浏览器是9个
					 */
					var cns = city.childNodes;
					
					//alert(cns.length);
					
					/*for(var i=0 ; i<cns.length ; i++){
						alert(cns[i]);
					}*/
					
					/*
					 * children属性可以获取当前元素的所有子元素（不会将空白文本当成子元素）
					 */
					var cns2 = city.children;
					alert(cns2.length);
				};
				
                // 5.练习五：获取id为phone的元素
				//为id为btn06的按钮绑定一个单击响应函数
				var btn06 = document.getElementById("btn06");
				btn06.onclick = function(){
					//获取id为phone的元素
					var phone = document.getElementById("phone");
					//返回#phone的第一个子节点
					//phone.childNodes[0];
					//firstChild可以获取到当前元素的第一个子节点（包括空白文本节点）
					var fir = phone.firstChild;
					
					//firstElementChild获取当前元素的第一个子元素（不包括空白文本节点）
					/*
					 * firstElementChild不支持IE8及以下的浏览器，
					 * 	如果需要兼容他们尽量不要使用
					 */
					//fir = phone.firstElementChild;
					
					alert(fir);
				};
				
				//为id为btn07的按钮绑定一个单击响应函数
				myClick("btn07",function(){
					
					//获取id为bj的节点
					var bj = document.getElementById("bj");
					
					//返回#bj的父节点
					var pn = bj.parentNode;
					
					alert(pn.innerHTML);
					
					/*
					 * innerText
					 * 	- 该属性可以获取到元素内部的文本内容
					 * 	- 它和innerHTML类似，不同的是它会自动将html去除
					 */
					//alert(pn.innerText);
					
					
				});
				
				
				//为id为btn08的按钮绑定一个单击响应函数
				myClick("btn08",function(){
					
					//获取id为android的元素
					var and = document.getElementById("android");
					
					//返回#android的前一个兄弟节点（也可能获取到空白的文本）
					var ps = and.previousSibling;
					
					//previousElementSibling获取前一个兄弟元素，IE8及以下不支持
					//var pe = and.previousElementSibling;
					
					alert(ps);
					
				});
				
				//读取#username的value属性值
				myClick("btn09",function(){
					//获取id为username的元素
					var um = document.getElementById("username");
					//读取um的value属性值
					//文本框的value属性值，就是文本框中填写的内容
					alert(um.value);
				});
				
				
				//设置#username的value属性值
				myClick("btn10",function(){
					//获取id为username的元素
					var um = document.getElementById("username");
					
					um.value = "今天天气真不错~~~";
				});
				
				
				//返回#bj的文本值
				myClick("btn11",function(){
					
					//获取id为bj的元素
					var bj = document.getElementById("bj");
					
					//alert(bj.innerHTML);
					//alert(bj.innerText);
					
					//获取bj中的文本节点
					/*var fc = bj.firstChild;
					alert(fc.nodeValue);*/
					
					alert(bj.firstChild.nodeValue);
				});
			};
		</script>
	</head>
	<body>
		<div id="total">
			<div class="inner">
				<p>
					你喜欢哪个城市?
				</p>

				<ul id="city">
					<li id="bj">北京</li>
					<li>上海</li>
					<li>东京</li>
					<li>首尔</li>
				</ul>

				<br>
				<br>

				<p>
					你喜欢哪款单机游戏?
				</p>

				<ul id="game">
					<li id="rl">红警</li>
					<li>实况</li>
					<li>极品飞车</li>
					<li>魔兽</li>
				</ul>

				<br />
				<br />

				<p>
					你手机的操作系统是?
				</p>

				<ul id="phone"><li>IOS</li> <li id="android">Android</li><li>Windows Phone</li></ul>
			</div>

			<div class="inner">
				gender:
				<input class="hello" type="radio" name="gender" value="male"/>
				Male
				<input class="hello" type="radio" name="gender" value="female"/>
				Female
				<br>
				<br>
				name:
				<input type="text" name="name" id="username" value="abcde"/>
			</div>
		</div>
		<div id="btnList">
			<div><button id="btn01">查找#bj节点</button></div>
			<div><button id="btn02">查找所有li节点</button></div>
			<div><button id="btn03">查找name=gender的所有节点</button></div>
			<div><button id="btn04">查找#city下所有li节点</button></div>
			<div><button id="btn05">返回#city的所有子节点</button></div>
			<div><button id="btn06">返回#phone的第一个子节点</button></div>
			<div><button id="btn07">返回#bj的父节点</button></div>
			<div><button id="btn08">返回#android的前一个兄弟节点</button></div>
			<div><button id="btn09">返回#username的value属性值</button></div>
			<div><button id="btn10">设置#username的value属性值</button></div>
			<div><button id="btn11">返回#bj的文本值</button></div>
		</div>
	</body>
</html>
```

#####  6.4.4 获取父节点和兄弟节点

通过具体的节点获取父节点和兄弟节点

```
1.parentNode
             属性，表示当前节点的父节点
2.previousSibling
             属性，表示当前节点的前一个兄弟节点
3.nextSibling
             属性，表示当前节点的后一个兄弟节点
```

`02.dom查询.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Untitled Document</title>
		<link rel="stylesheet" type="text/css" href="style/css.css" />
		<script type="text/javascript">
		
			/*
			 * 定义一个函数，专门用来为指定元素绑定单击响应函数
			 * 	参数：
			 * 		idStr 要绑定单击响应函数的对象的id属性值
			 * 		fun 事件的回调函数，当单击元素时，该函数将会被触发
			 */
			function myClick(idStr , fun){
				var btn = document.getElementById(idStr);
				btn.onclick = fun;
			}
		
			window.onload = function(){
				//为id为btn07的按钮绑定一个单击响应函数
				myClick("btn07",function(){
					//获取id为bj的节点
					var bj = document.getElementById("bj");
					//返回#bj的父节点
					var pn = bj.parentNode;
					alert(pn.innerHTML);
					
					/*
					 * innerText
					 * 	- 该属性可以获取到元素内部的文本内容
					 * 	- 它和innerHTML类似，不同的是它会自动将html去除
					 */
					//alert(pn.innerText);
				});
				
				//为id为btn08的按钮绑定一个单击响应函数
				myClick("btn08",function(){
					//获取id为android的元素
					var and = document.getElementById("android");
					//返回#android的前一个兄弟节点（也可能获取到空白的文本）
					var ps = and.previousSibling;
					//previousElementSibling获取前一个兄弟元素，IE8及以下不支持
					//var pe = and.previousElementSibling;
					alert(ps);
				});
				//读取#username的value属性值
				myClick("btn09",function(){
					//获取id为username的元素
					var um = document.getElementById("username");
					//读取um的value属性值
					//文本框的value属性值，就是文本框中填写的内容
					alert(um.value);
				});
				
				
				//设置#username的value属性值
				myClick("btn10",function(){
					//获取id为username的元素
					var um = document.getElementById("username");
					
					um.value = "今天天气真不错~~~";
				});
				//返回#bj的文本值
				myClick("btn11",function(){
					//获取id为bj的元素
					var bj = document.getElementById("bj");
					//alert(bj.innerHTML);
					//alert(bj.innerText);
					//获取bj中的文本节点
					/*var fc = bj.firstChild;
					alert(fc.nodeValue);*/
					alert(bj.firstChild.nodeValue);
				});
				
			};
		</script>
	</head>
	<body>
		<div id="total">
			<div class="inner">
				<p>
					你喜欢哪个城市?
				</p>

				<ul id="city">
					<li id="bj">北京</li>
					<li>上海</li>
					<li>东京</li>
					<li>首尔</li>
				</ul>

				<br>
				<br>

				<p>
					你喜欢哪款单机游戏?
				</p>

				<ul id="game">
					<li id="rl">红警</li>
					<li>实况</li>
					<li>极品飞车</li>
					<li>魔兽</li>
				</ul>

				<br />
				<br />

				<p>
					你手机的操作系统是?
				</p>

				<ul id="phone"><li>IOS</li> <li id="android">Android</li><li>Windows Phone</li></ul>
			</div>

			<div class="inner">
				gender:
				<input class="hello" type="radio" name="gender" value="male"/>
				Male
				<input class="hello" type="radio" name="gender" value="female"/>
				Female
				<br>
				<br>
				name:
				<input type="text" name="name" id="username" value="abcde"/>
			</div>
		</div>
		<div id="btnList">
			<div><button id="btn01">查找#bj节点</button></div>
			<div><button id="btn02">查找所有li节点</button></div>
			<div><button id="btn03">查找name=gender的所有节点</button></div>
			<div><button id="btn04">查找#city下所有li节点</button></div>
			<div><button id="btn05">返回#city的所有子节点</button></div>
			<div><button id="btn06">返回#phone的第一个子节点</button></div>
			<div><button id="btn07">返回#bj的父节点</button></div>
			<div><button id="btn08">返回#android的前一个兄弟节点</button></div>
			<div><button id="btn09">返回#username的value属性值</button></div>
			<div><button id="btn10">设置#username的value属性值</button></div>
			<div><button id="btn11">返回#bj的文本值</button></div>
		</div>
	</body>
</html>
```

#####  6.4.5 元素节点的属性

```
获取元素节点的属性：元素对象.属性名
                例: element.value 、element.id、element.className
设置元素节点的属性：元素对象.属性名 = 新的值
                例: element.value = "hello"
                    element.id = "idO1”
                    element.className = "newClass"
```

```javascript
//读取#username的value属性值
				myClick("btn09",function(){
					//获取id为username的元素
					var um = document.getElementById("username");
					//读取um的value属性值
					//文本框的value属性值，就是文本框中填写的内容
					alert(um.value);
				});
				
				
				//设置#username的value属性值
				myClick("btn10",function(){
					//获取id为username的元素
					var um = document.getElementById("username");
					
					um.value = "今天天气真不错~~~";
				});
				//返回#bj的文本值
				myClick("btn11",function(){
					//获取id为bj的元素
					var bj = document.getElementById("bj");
					//alert(bj.innerHTML);
					//alert(bj.innerText);
					//获取bj中的文本节点
					/*var fc = bj.firstChild;
					alert(fc.nodeValue);*/
					alert(bj.firstChild.nodeValue);
				});
```

#####  6.4.5 DOM查询练习

`02.全选练习.html`

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全选练习</title>
<script type="text/javascript">

	window.onload = function(){
		
		
		//获取四个多选框items
		var items = document.getElementsByName("items");
		//获取全选/全不选的多选框
		var checkedAllBox = document.getElementById("checkedAllBox");
		
		/*
		 * 全选按钮
		 * 	- 点击按钮以后，四个多选框全都被选中
		 */
		
		//1.#checkedAllBtn
		//为id为checkedAllBtn的按钮绑定一个单击响应函数
		var checkedAllBtn = document.getElementById("checkedAllBtn");
		checkedAllBtn.onclick = function(){
			
			
			//遍历items
			for(var i=0 ; i<items.length ; i++){
				
				//通过多选框的checked属性可以来获取或设置多选框的选中状态
				//alert(items[i].checked);
				
				//设置四个多选框变成选中状态
				items[i].checked = true;
			}
			
			//将全选/全不选设置为选中
			checkedAllBox.checked = true;
			
			
		};
		
		/*
		 * 全不选按钮
		 * 	- 点击按钮以后，四个多选框都变成没选中的状态
		 */
		//2.#checkedNoBtn
		//为id为checkedNoBtn的按钮绑定一个单击响应函数
		var checkedNoBtn = document.getElementById("checkedNoBtn");
		checkedNoBtn.onclick = function(){
			
			for(var i=0; i<items.length ; i++){
				//将四个多选框设置为没选中的状态
				items[i].checked = false;
			}
			
			//将全选/全不选设置为不选中
			checkedAllBox.checked = false;
			
		};
		
		/*
		 * 反选按钮
		 * 	- 点击按钮以后，选中的变成没选中，没选中的变成选中
		 */
		//3.#checkedRevBtn
		var checkedRevBtn = document.getElementById("checkedRevBtn");
		checkedRevBtn.onclick = function(){
			
			//将checkedAllBox设置为选中状态
			checkedAllBox.checked = true;
			
			for(var i=0; i<items.length ; i++){
				
				//判断多选框状态
				/*if(items[i].checked){
					//证明多选框已选中，则设置为没选中状态
					items[i].checked = false;
				}else{
					//证明多选框没选中，则设置为选中状态
					items[i].checked = true;
				}*/
				
				items[i].checked = !items[i].checked;
				
				//判断四个多选框是否全选
				//只要有一个没选中则就不是全选
				if(!items[i].checked){
					//一旦进入判断，则证明不是全选状态
					//将checkedAllBox设置为没选中状态
					checkedAllBox.checked = false;
				}
			}
			
			//在反选时也需要判断四个多选框是否全都选中
			
		};
		/*
		 * 提交按钮：
		 * 	- 点击按钮以后，将所有选中的多选框的value属性值弹出
		 */
		//4.#sendBtn
		//为sendBtn绑定单击响应函数
		var sendBtn = document.getElementById("sendBtn");
		sendBtn.onclick = function(){
			//遍历items
			for(var i=0 ; i<items.length ; i++){
				//判断多选框是否选中
				if(items[i].checked){
					alert(items[i].value);
				}
			}
		};
		//5.#checkedAllBox
		/*
		 * 全选/全不选 多选框
		 * 	- 当它选中时，其余的也选中，当它取消时其余的也取消
		 * 
		 * 在事件的响应函数中，响应函数是给谁绑定的this就是谁
		 */
		//为checkedAllBox绑定单击响应函数
		checkedAllBox.onclick = function(){
			
			//alert(this === checkedAllBox);
			
			//设置多选框的选中状态
			for(var i=0; i <items.length ; i++){
				items[i].checked = this.checked;
			}
			
		};
		//6.items
		/*
		 * 如果四个多选框全都选中，则checkedAllBox也应该选中
		 * 如果四个多选框没都选中，则checkedAllBox也不应该选中
		 */
		//为四个多选框分别绑定点击响应函数
		for(var i=0 ; i<items.length ; i++){
			items[i].onclick = function(){
				
				//将checkedAllBox设置为选中状态
				checkedAllBox.checked = true;
				
				for(var j=0 ; j<items.length ; j++){
					//判断四个多选框是否全选
					//只要有一个没选中则就不是全选
					if(!items[j].checked){
						//一旦进入判断，则证明不是全选状态
						//将checkedAllBox设置为没选中状态
						checkedAllBox.checked = false;
						//一旦进入判断，则已经得出结果，不用再继续执行循环
						break;
					}
				}
			};
		}
	};
</script>
</head>
<body>

	<form method="post" action="">
		你爱好的运动是？<input type="checkbox" id="checkedAllBox" />全选/全不选 
		
		<br />
		<input type="checkbox" name="items" value="足球" />足球
		<input type="checkbox" name="items" value="篮球" />篮球
		<input type="checkbox" name="items" value="羽毛球" />羽毛球
		<input type="checkbox" name="items" value="乒乓球" />乒乓球
		<br />
		<input type="button" id="checkedAllBtn" value="全　选" />
		<input type="button" id="checkedNoBtn" value="全不选" />
		<input type="button" id="checkedRevBtn" value="反　选" />
		<input type="button" id="sendBtn" value="提　交" />
	</form>
</body>
</html>
```

#####  6.4.6 DOM查询补充

**使用CSS选择器进行查询**：querySelector()和querySelectorAll()这两个方法都是用document对象来调用，两个方法使用相同，都是传递一个选择器字符串作为参数，方法会自动根据选择器字符串去网页中查找元素。不同的地方是querySelector()只会返回找到的第一个元素，而querySelectorAll()会返回所有符合条件的元素

`03.dom查询的其他的方法.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			window.onload = function(){
				
				//获取body标签
				//var body = document.getElementsByTagName("body")[0];
				
				/*
				 * 在document中有一个属性body，它保存的是body的引用
				 */
				var body = document.body;
				
				/*
				 * document.documentElement保存的是html根标签
				 */
				var html = document.documentElement;
				
				//console.log(html);
				
				/*
				 * document.all代表页面中所有的元素
				 */
				var all = document.all;
				
				//console.log(all.length);
				
				/*for(var i=0 ; i<all.length ; i++){
					console.log(all[i]);
				}*/
				
				//all = document.getElementsByTagName("*");
				//console.log(all.length);
				
				
				/*
				 * 根据元素的class属性值查询一组元素节点对象
				 * getElementsByClassName()可以根据class属性值获取一组元素节点对象，
				 * 	但是该方法不支持IE8及以下的浏览器
				 */
				//var box1 = document.getElementsByClassName("box1");
				//console.log(box1.length);
				
				//获取页面中的所有的div
				//var divs = document.getElementsByTagName("div");
				
				//获取class为box1中的所有的div
				//.box1 div
				/*
				 * document.querySelector()
				 * 	- 需要一个选择器的字符串作为参数，可以根据一个CSS选择器来查询一个元素节点对象
				 * 	- 虽然IE8中没有getElementsByClassName()但是可以使用querySelector()代替
				 * 	- 使用该方法总会返回唯一的一个元素，如果满足条件的元素有多个，那么它只会返回第一个
				 */
				var div = document.querySelector(".box1 div");
				var box1 = document.querySelector(".box1")
				//console.log(div.innerHTML);
				//console.log(box1.innerHTML);
				/*
				 * document.querySelectorAll()
				 * 	- 该方法和querySelector()用法类似，不同的是它会将符合条件的元素封装到一个数组中返回
				 * 	- 即使符合条件的元素只有一个，它也会返回数组
				 */
				box1 = document.querySelectorAll(".box1");
				box1 = document.querySelectorAll("#box2");
				console.log(box1);
			};
		</script>
	</head>
	<body>
		<div id="box2"></div>	
		<div class="box1">
			我是第一个box1	 
			<div>我是box1中的div</div>
		</div>
		<div class="box1">
			<div>我是box1中的div</div>
		</div>
		<div class="box1">
			<div>我是box1中的div</div>
		</div>
		<div class="box1">
			<div>我是box1中的div</div>
		</div>
		<div></div>
	</body>
</html>
```

###  6.5 DOM增删改

#####  6.5.1 节点的修改

节点的修改，这里的修改主要指对元素节点的操作

```
创建节点：      document.createElement(标签名)
删除节点：      父节点.removeChild(子节点)
替换节点：      父节点.replaceChild(新节点，旧节点)
插入节点：      父节点.appendChild(子节点)、父节点.insertBefore(新节点，旧节点)
```

`04.dom增删改.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Untitled Document</title>
		<link rel="stylesheet" type="text/css" href="style/css.css" />
		<script type="text/javascript">
		
			window.onload = function() {
				
				//创建一个"广州"节点,添加到#city下
				myClick("btn01",function(){
					//创建广州节点 <li>广州</li>
					//创建li元素节点
					/*
					 * document.createElement()
					 * 	可以用于创建一个元素节点对象，
					 * 	它需要一个标签名作为参数，将会根据该标签名创建元素节点对象，
					 * 	并将创建好的对象作为返回值返回
					 */
					var li = document.createElement("li");
					
					//创建广州文本节点
					/*
					 * document.createTextNode()
					 * 	可以用来创建一个文本节点对象
					 *  需要一个文本内容作为参数，将会根据该内容创建文本节点，并将新的节点返回
					 */
					var gzText = document.createTextNode("广州");
					
					//将gzText设置li的子节点
					/*
					 * appendChild()
					 * 	 - 向一个父节点中添加一个新的子节点
					 * 	 - 用法：父节点.appendChild(子节点);
					 */
					li.appendChild(gzText);
					
					//获取id为city的节点
					var city = document.getElementById("city");
					
					//将广州添加到city下
					city.appendChild(li);
					
					
				});
				
				//将"广州"节点插入到#bj前面
				myClick("btn02",function(){
					//创建一个广州
					var li = document.createElement("li");
					var gzText = document.createTextNode("广州");
					li.appendChild(gzText);
					
					//获取id为bj的节点
					var bj = document.getElementById("bj");
					
					//获取city
					var city = document.getElementById("city");
					
					/*
					 * insertBefore()
					 * 	- 可以在指定的子节点前插入新的子节点
					 *  - 语法：
					 * 		父节点.insertBefore(新节点,旧节点);
					 */
					city.insertBefore(li , bj);
					
					
				});
				
				
				//使用"广州"节点替换#bj节点
				myClick("btn03",function(){
					//创建一个广州
					var li = document.createElement("li");
					var gzText = document.createTextNode("广州");
					li.appendChild(gzText);
					
					//获取id为bj的节点
					var bj = document.getElementById("bj");
					
					//获取city
					var city = document.getElementById("city");
					
					/*
					 * replaceChild()
					 * 	- 可以使用指定的子节点替换已有的子节点
					 * 	- 语法：父节点.replaceChild(新节点,旧节点);
					 */
					city.replaceChild(li , bj);
					
					
				});
				
				//删除#bj节点
				myClick("btn04",function(){
					//获取id为bj的节点
					var bj = document.getElementById("bj");
					//获取city
					var city = document.getElementById("city");
					
					/*
					 * removeChild()
					 * 	- 可以删除一个子节点
					 * 	- 语法：父节点.removeChild(子节点);
					 * 		
					 * 		子节点.parentNode.removeChild(子节点);
					 */
					//city.removeChild(bj);
					
					bj.parentNode.removeChild(bj);
				});
				
				
				//读取#city内的HTML代码
				myClick("btn05",function(){
					//获取city
					var city = document.getElementById("city");
					
					alert(city.innerHTML);
				});
				
				//设置#bj内的HTML代码
				myClick("btn06" , function(){
					//获取bj
					var bj = document.getElementById("bj");
					bj.innerHTML = "昌平";
				});
				
				myClick("btn07",function(){
					
					//向city中添加广州
					var city = document.getElementById("city");
					
					/*
					 * 使用innerHTML也可以完成DOM的增删改的相关操作
					 * 一般我们会两种方式结合使用
					 */
					//city.innerHTML += "<li>广州</li>";
					
					//创建一个li
					var li = document.createElement("li");
					//向li中设置文本
					li.innerHTML = "广州";
					//将li添加到city中
					city.appendChild(li);
					
				});
				
				
			};
			
			function myClick(idStr, fun) {
				var btn = document.getElementById(idStr);
				btn.onclick = fun;
			}
			
		
		</script>
		
	</head>
	<body>
		<div id="total">
			<div class="inner">
				<p>
					你喜欢哪个城市?
				</p>

				<ul id="city">
					<li id="bj">北京</li>
					<li>上海</li>
					<li>东京</li>
					<li>首尔</li>
				</ul>
				
			</div>
		</div>
		<div id="btnList">
			<div><button id="btn01">创建一个"广州"节点,添加到#city下</button></div>
			<div><button id="btn02">将"广州"节点插入到#bj前面</button></div>
			<div><button id="btn03">使用"广州"节点替换#bj节点</button></div>
			<div><button id="btn04">删除#bj节点</button></div>
			<div><button id="btn05">读取#city内的HTML代码</button></div>
			<div><button id="btn06">设置#bj内的HTML代码</button></div>
			<div><button id="btn07">创建一个"广州"节点,添加到#city下</button></div>
		</div>
	</body>
</html>
```

#####  6.5.2 增删改练习一

删除员工

`05.练习.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加删除记录练习</title>
<link rel="stylesheet" type="text/css" href="ex_2_style/css.css" />
<script type="text/javascript">
	window.onload = function(){
		/*
		 * 点击超链接以后，删除一个员工的信息
		 */
		//获取所有额超链接
		var allA = document.getElementsByTagName("a");
		
		//为每个超链接都绑定一个单击响应函数
		for(var i=0 ; i < allA.length ; i++){
			allA[i].onclick = function(){
				
				//点击超链接以后需要删除超链接所在的那行
				//这里我们点击那个超链接this就是谁
				//获取当前tr
				var tr = this.parentNode.parentNode;
				
				//获取要删除的员工的名字
				//var name = tr.getElementsByTagName("td")[0].innerHTML;
				var name = tr.children[0].innerHTML;
				
				//删除之前弹出一个提示框
				/*
				 * confirm()用于弹出一个带有确认和取消按钮的提示框
				 * 	需要一个字符串作为参数，该字符串将会作为提示文字显示出来
				 * 如果用户点击确认则会返回true，如果点击取消则返回false
				 */
				var flag = confirm("确认删除"+name+"吗?");
				
				//如果用户点击确认
				if(flag){
					//删除tr
					tr.parentNode.removeChild(tr);
				}
				/*
				 * 点击超链接以后，超链接会跳转页面，这个是超链接的默认行为，
				 * 	但是此时我们不希望出现默认行为，可以通过在响应函数的最后return false来取消默认行为
				 */
				return false;
			};
		}
	};
</script>
</head>
<body>

	<table id="employeeTable">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Salary</th>
			<th>&nbsp;</th>
		</tr>
		<tr>
			<td>Tom</td>
			<td>tom@tom.com</td>
			<td>5000</td>
			<td><a href="javascript:;">Delete</a></td>
		</tr>
		<tr>
			<td>Jerry</td>
			<td>jerry@sohu.com</td>
			<td>8000</td>
			<td><a href="deleteEmp?id=002">Delete</a></td>
		</tr>
		<tr>
			<td>Bob</td>
			<td>bob@tom.com</td>
			<td>10000</td>
			<td><a href="deleteEmp?id=003">Delete</a></td>
		</tr>
	</table>

	<div id="formDiv">
	
		<h4>添加新员工</h4>

		<table>
			<tr>
				<td class="word">name: </td>
				<td class="inp">
					<input type="text" name="empName" id="empName" />
				</td>
			</tr>
			<tr>
				<td class="word">email: </td>
				<td class="inp">
					<input type="text" name="email" id="email" />
				</td>
			</tr>
			<tr>
				<td class="word">salary: </td>
				<td class="inp">
					<input type="text" name="salary" id="salary" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="addEmpButton" value="abc">
						Submit
					</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
```

#####  6.5.3 增删改练习二

添加员工

`02.练习.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加删除记录练习</title>
		<link rel="stylesheet" type="text/css" href="ex_2_style/css.css" />
		<script type="text/javascript">
				/*
				 * 添加员工的功能
				 * 	- 点击按钮以后，将员工的信息添加到表格中
				 */

				//为提交按钮绑定一个单击响应函数
				var addEmpButton = document.getElementById("addEmpButton");
				addEmpButton.onclick = function() {

					//获取用户添加的员工信息
					//获取员工的名字
					var name = document.getElementById("empName").value;
					//获取员工的email和salary
					var email = document.getElementById("email").value;
					var salary = document.getElementById("salary").value;

					//alert(name+","+email+","+salary);
					/*
					 *  <tr>
							<td>Tom</td>
							<td>tom@tom.com</td>
							<td>5000</td>
							<td><a href="javascript:;">Delete</a></td>
						</tr>
						需要将获取到的信息保存到tr中
					 */

					//创建一个tr
					var tr = document.createElement("tr");

					//设置tr中的内容
					tr.innerHTML = "<td>"+name+"</td>"+
									"<td>"+email+"</td>"+
									"<td>"+salary+"</td>"+
									"<td><a href='javascript:;'>Delete</a></td>";
									
					//获取刚刚添加的a元素，并为其绑定单击响应函数				
					var a = tr.getElementsByTagName("a")[0];
					a.onclick = delA;
					
					//获取table
					var employeeTable = document.getElementById("employeeTable");
					//获取employeeTable中的tbody
					var tbody = employeeTable.getElementsByTagName("tbody")[0];
					//将tr添加到tbodye中
					tbody.appendChild(tr);
					/*tbody.innerHTML += "<tr>"+
					
					"<td>"+name+"</td>"+
									"<td>"+email+"</td>"+
									"<td>"+salary+"</td>"+
									"<td><a href='javascript:;'>Delete</a></td>"
					
					+"</tr>";*/

				};

			};
		</script>
	</head>
	<body>
		<table id="employeeTable">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Salary</th>
				<th>&nbsp;</th>
			</tr>
			<tr>
				<td>Tom</td>
				<td>tom@tom.com</td>
				<td>5000</td>
				<td>
					<a href="javascript:;">Delete</a>
				</td>
			</tr>
			<tr>
				<td>Jerry</td>
				<td>jerry@sohu.com</td>
				<td>8000</td>
				<td>
					<a href="deleteEmp?id=002">Delete</a>
				</td>
			</tr>
			<tr>
				<td>Bob</td>
				<td>bob@tom.com</td>
				<td>10000</td>
				<td>
					<a href="deleteEmp?id=003">Delete</a>
				</td>
			</tr>
		</table>
		<div id="formDiv">
			<h4>添加新员工</h4>
			<table>
				<tr>
					<td class="word">name: </td>
					<td class="inp">
						<input type="text" name="empName" id="empName" />
					</td>
				</tr>
				<tr>
					<td class="word">email: </td>
					<td class="inp">
						<input type="text" name="email" id="email" />
					</td>
				</tr>
				<tr>
					<td class="word">salary: </td>
					<td class="inp">
						<input type="text" name="salary" id="salary" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="addEmpButton">
						Submit
					</button>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
```

#####  6.5.4 超链接a的索引问题

`03.练习.html`

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加删除记录练习</title>
<link rel="stylesheet" type="text/css" href="ex_2_style/css.css" />
<script type="text/javascript">
	window.onload = function(){
		/*
		 * 点击超链接以后，删除一个员工的信息
		 */
		//获取所有额超链接
		var allA = document.getElementsByTagName("a");
		
		//为每个超链接都绑定一个单击响应函数
		for(var i=0 ; i < allA.length ; i++){
			/*
			 * for循环会在页面加载完成之后立即执行，
			 * 	而响应函数会在超链接被点击时才执行
			 * 当响应函数执行时，for循环早已执行完毕
			 */
			
			alert("for循环正在执行"+i);
			
			allA[i].onclick = function(){
				
				alert("响应函数正在执行"+i);
				
				//alert(allA[i]);
				return false;
			};
		}
	};
</script>
</head>
<body>
	<table id="employeeTable">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Salary</th>
			<th>&nbsp;</th>
		</tr>
		<tr>
			<td>Tom</td>
			<td>tom@tom.com</td>
			<td>5000</td>
			<td><a href="javascript:;">Delete</a></td>
		</tr>
		<tr>
			<td>Jerry</td>
			<td>jerry@sohu.com</td>
			<td>8000</td>
			<td><a href="deleteEmp?id=002">Delete</a></td>
		</tr>
		<tr>
			<td>Bob</td>
			<td>bob@tom.com</td>
			<td>10000</td>
			<td><a href="deleteEmp?id=003">Delete</a></td>
		</tr>
	</table>
	<div id="formDiv">
		<h4>添加新员工</h4>
		<table>
			<tr>
				<td class="word">name: </td>
				<td class="inp">
					<input type="text" name="empName" id="empName" />
				</td>
			</tr>
			<tr>
				<td class="word">email: </td>
				<td class="inp">
					<input type="text" name="email" id="email" />
				</td>
			</tr>
			<tr>
				<td class="word">salary: </td>
				<td class="inp">
					<input type="text" name="salary" id="salary" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="addEmpButton" value="abc">
						Submit
					</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
```

#####  6.5.5  操作`CSS`内联样式

`04.使用DOM操作CSS.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 点击按钮以后，修改box1的大小
				 */
				//获取box1
				var box1 = document.getElementById("box1");
				//为按钮绑定单击响应函数
				var btn01 = document.getElementById("btn01");
				btn01.onclick = function(){
					//修改box1的宽度
					/*
					 * 通过JS修改元素的样式：
					 * 	语法：元素.style.样式名 = 样式值
					 * 
					 * 注意：如果CSS的样式名中含有-，
					 * 		这种名称在JS中是不合法的比如background-color
					 * 		需要将这种样式名修改为驼峰命名法，
					 * 		去掉-，然后将-后的字母大写
					 * 
					 * 我们通过style属性设置的样式都是内联样式，
					 * 	而内联样式有较高的优先级，所以通过JS修改的样式往往会立即显示
					 * 
					 * 但是如果在样式中写了!important，则此时样式会有最高的优先级，
					 * 	即使通过JS也不能覆盖该样式，此时将会导致JS修改样式失效
					 * 	所以尽量不要为样式添加!important
					 * 
					 * 
					 * 
					 */
					box1.style.width = "300px";
					box1.style.height = "300px";
					box1.style.backgroundColor = "yellow";
				};
				//点击按钮2以后，读取元素的样式
				var btn02 = document.getElementById("btn02");
				btn02.onclick = function(){
					//读取box1的样式
					/*
					 * 	语法：元素.style.样式名
					 * 
					 * 通过style属性设置和读取的都是内联样式
					 * 	无法读取样式表中的样式
					 */
					//alert(box1.style.height);
					alert(box1.style.width);
				};
			};
		</script>
	</head>
	<body>
		<button id="btn01">点我一下</button>
		<button id="btn02">点我一下2</button>
		<br /><br />
		<div id="box1"></div>
	</body>
</html>
```

#####  6.5.6 获取元素样式

`05.读取元素的样式.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: yellow;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				//点击按钮以后读取box1的样式
				var box1 = document.getElementById("box1");
				var btn01 = document.getElementById("btn01");
				btn01.onclick = function(){
					//读取box1的宽度
					//alert(box1.style.width);
					/*
					 * 获取元素的当前显示的样式
					 * 	语法：元素.currentStyle.样式名
					 * 它可以用来读取当前元素正在显示的样式
					 * 	如果当前元素没有设置该样式，则获取它的默认值
					 * 
					 * currentStyle只有IE浏览器支持，其他的浏览器都不支持
					 */
					
					//alert(box1.currentStyle.width);
					//box1.currentStyle.width = "200px";
					//alert(box1.currentStyle.backgroundColor);
					
					/*
					 * 在其他浏览器中可以使用
					 * 		getComputedStyle()这个方法来获取元素当前的样式
					 * 		这个方法是window的方法，可以直接使用
					 * 需要两个参数
					 * 		第一个：要获取样式的元素
					 * 		第二个：可以传递一个伪元素，一般都传null
					 * 
					 * 该方法会返回一个对象，对象中封装了当前元素对应的样式
					 * 	可以通过对象.样式名来读取样式
					 * 	如果获取的样式没有设置，则会获取到真实的值，而不是默认值
					 * 	比如：没有设置width，它不会获取到auto，而是一个长度
					 * 
					 * 但是该方法不支持IE8及以下的浏览器
					 * 
					 * 通过currentStyle和getComputedStyle()读取到的样式都是只读的，
					 * 	不能修改，如果要修改必须通过style属性
					 */
					//var obj = getComputedStyle(box1,null);
					
					/*alert(getComputedStyle(box1,null).width);*/
					//正常浏览器的方式
					//alert(getComputedStyle(box1,null).backgroundColor);
					
					//IE8的方式
					//alert(box1.currentStyle.backgroundColor);
					
					//alert(getStyle(box1,"width"));
					
					var w = getStyle(box1,"width");
					alert(w);
					
					
				};
				
			};
			
			/*
			 * 定义一个函数，用来获取指定元素的当前的样式
			 * 参数：
			 * 		obj 要获取样式的元素
			 * 		name 要获取的样式名
			 */
			function getStyle(obj , name){
				
				if(window.getComputedStyle){
					//正常浏览器的方式，具有getComputedStyle()方法
					return getComputedStyle(obj , null)[name];
				}else{
					//IE8的方式，没有getComputedStyle()方法
					return obj.currentStyle[name];
				}
				//return window.getComputedStyle?getComputedStyle(obj , null)[name]:obj.currentStyle[name];
			}
		</script>
	</head>
	<body>
		<button id="btn01">点我一下</button>
		<br /><br />
		<div id="box1" ></div>
	</body>
</html>
```

#####  6.5.7 操作其他样式的属性

`06.其他样式操作的属性.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				padding: 10px;
				border: 10px solid yellow;
			}
			
			
			#box2{
				padding: 100px;
				background-color: #bfa;
			}
			
			#box4{
				width: 200px;
				height: 300px;
				background-color: #bfa;
				overflow: auto;
			}
			
			#box5{
				width: 450px;
				height: 600px;
				background-color: yellow;
			}
			
		</style>
		<script type="text/javascript">
			
			window.onload = function(){
				var box1 = document.getElementById("box1");
				var btn01 = document.getElementById("btn01");
				var box4 = document.getElementById("box4");
				
				btn01.onclick = function(){
					/*
					 * clientWidth
					 * clientHeight
					 * 	- 这两个属性可以获取元素的可见宽度和高度
					 * 	- 这些属性都是不带px的，返回都是一个数字，可以直接进行计算
					 * 	- 会获取元素宽度和高度，包括内容区和内边距
					 *  - 这些属性都是只读的，不能修改
					 */
					//alert(box1.clientWidth);
					//alert(box1.clientHeight);
					//box1.clientHeight = 300;
					
					/*
					 * offsetWidth
					 * offsetHeight
					 * 	- 获取元素的整个的宽度和高度，包括内容区、内边距和边框
					 */
					//alert(box1.offsetWidth);
					
					/*
					 * offsetParent
					 * 	- 可以用来获取当前元素的定位父元素
					 *  - 会获取到离当前元素最近的开启了定位的祖先元素
					 * 		如果所有的祖先元素都没有开启定位，则返回body
					 */
					var op = box1.offsetParent;
					
					//alert(op.id);
					
					/*
					 * offsetLeft
					 * 	- 当前元素相对于其定位父元素的水平偏移量
					 * offsetTop
					 * 	- 当前元素相对于其定位父元素的垂直偏移量
					 */
					
					//alert(box1.offsetLeft);
					
					/*
					 * scrollWidth
					 * scrollHeight
					 * 	- 可以获取元素整个滚动区域的宽度和高度
					 */
					//alert(box4.clientHeight);
					//alert(box4.scrollWidth);
					
					/*
					 * scrollLeft
					 * 	- 可以获取水平滚动条滚动的距离
					 * scrollTop
					 * 	- 可以获取垂直滚动条滚动的距离
					 */
					//alert(box4.scrollLeft);
					//alert(box4.scrollTop);
					
					//alert(box4.clientHeight); // 283
					
					//当满足scrollHeight - scrollTop == clientHeight
					//说明垂直滚动条滚动到底了
					
					//当满足scrollWidth - scrollLeft == clientWidth
					//说明水平滚动条滚动到底
					//alert(box4.scrollHeight - box4.scrollTop); // 600
				};
			};
		</script>
	</head>
	<body id="body">
		<button id="btn01">点我一下</button>
		<br /><br />
		
		 <div id="box4">
		 	<div id="box5"></div>
		 </div>
		<br /><br />
		<div id="box3">
			<div id="box2" style="position: relative;">
				<div id="box1"></div>
			</div>
		</div>
	</body>
</html>
```

`07.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#info{
				width: 300px;
				height: 500px;
				background-color: #bfa;
				overflow: auto;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				
				/*
				 * 当垂直滚动条滚动到底时使表单项可用
				 * onscroll
				 * 	- 该事件会在元素的滚动条滚动时触发
				 */
				
				//获取id为info的p元素
				var info = document.getElementById("info");
				//获取两个表单项
				var inputs = document.getElementsByTagName("input");
				//为info绑定一个滚动条滚动的事件
				info.onscroll = function(){
					
					//检查垂直滚动条是否滚动到底
					if(info.scrollHeight - info.scrollTop == info.clientHeight){
						//滚动条滚动到底，使表单项可用
						/*
						 * disabled属性可以设置一个元素是否禁用，
						 * 	如果设置为true，则元素禁用
						 * 	如果设置为false，则元素可用
						 */
						inputs[0].disabled = false;
						inputs[1].disabled = false;
					}
				};
			};
		</script>
	</head>
	<body>
		<h3>欢迎亲爱的用户注册</h3>
		<p id="info">
			亲爱的用户，请仔细阅读以下协议
		</p>
		<!-- 如果为表单项添加disabled="disabled" 则表单项将变成不可用的状态 -->
		<input type="checkbox" disabled="disabled" />我已仔细阅读协议，一定遵守
		<input type="submit" value="注册" disabled="disabled" />
	</body>
</html>
```

##  7. 事件

###  7.1 `JS`事件简介

**事件**：事件指的就是用户与浏览器交互的一瞬间，通过为指定事件绑定回调函数的形式来处理事件，当指定事件触发以后我们的回调函数就会被调用，这样页面就可以完成和用户的交互

**为一个元素绑定事件处理程序的三种方式**：通过HTML元素指定事件属性来绑定事件、通过DOM对象指定的属性来绑定事件（推荐使用）、使用`对象.addEventListener()`设置事件监听器元素



```
通过HTML标签的属性设置
通过HTML属性来绑定事件处理程序是最简单的方式
<button onclick="alert( 'hello' ) ;alert ( 'world')">按钮</button>
·这种方式当我们点击按钮以后，onclick属性中对应的JS代码将会执行，也就是点击按钮以后，页面中会弹出两个提示框。·这种方式我们直接将代码编写到了onclick属性中，可以编写多行js代码，当然也可以事先在外部定义好函数。
·这种方式的优点在于，设定步骤非常简单，并且能够确保事件处理程序会在载入时被设定。
·如果在函数的最后return false则会取消元素的默认行为。
```

`08.事件对象.html`

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#areaDiv {
		border: 1px solid black;
		width: 300px;
		height: 50px;
		margin-bottom: 10px;
	}
	#showMsg {
		border: 1px solid black;
		width: 300px;
		height: 20px;
	}
</style>
<script type="text/javascript">

	window.onload = function(){
		/*
		 * 当鼠标在areaDiv中移动时，在showMsg中来显示鼠标的坐标
		 */
		//获取两个div
		var areaDiv = document.getElementById("areaDiv");
		var showMsg = document.getElementById("showMsg");
		
		/*
		 * onmousemove
		 * 	- 该事件将会在鼠标在元素中移动时被触发
		 * 
		 * 事件对象
		 * 	- 当事件的响应函数被触发时，浏览器每次都会将一个事件对象作为实参传递进响应函数,
		 * 		在事件对象中封装了当前事件相关的一切信息，比如：鼠标的坐标  键盘哪个按键被按下  鼠标滚轮滚动的方向。。。
		 */
		areaDiv.onmousemove = function(event){
			
			/*
			 * 在IE8中，响应函数被处罚时，浏览器不会传递事件对象，
			 * 	在IE8及以下的浏览器中，是将事件对象作为window对象的属性保存的
			 */
			/*if(!event){
				event = window.event;
			}*/
			
			//解决事件对象的兼容性问题
			event = event || window.event;
			
			/*
			 * clientX可以获取鼠标指针的水平坐标
			 * cilentY可以获取鼠标指针的垂直坐标
			 */
			var x = event.clientX;
			var y = event.clientY;
			//alert("x = "+x + " , y = "+y);
			//在showMsg中显示鼠标的坐标
			showMsg.innerHTML = "x = "+x + " , y = "+y;
		};
	};
</script>
</head>
<body>
	<div id="areaDiv"></div>
	<div id="showMsg"></div>
</body>
</html>
```

###  7.2 div跟随鼠标移动

练习：div跟随鼠标移动

`09.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				/*
				 * 开启box1的绝对定位
				 */
				position: absolute;
			}
		</style>
		
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 使div可以跟随鼠标移动
				 */
				//获取box1
				var box1 = document.getElementById("box1");
				//绑定鼠标移动事件
				document.onmousemove = function(event){
					
					//解决兼容问题
					event = event || window.event;
					
					//获取滚动条滚动的距离
					/*
					 * chrome认为浏览器的滚动条是body的，可以通过body.scrollTop来获取
					 * 火狐等浏览器认为浏览器的滚动条是html的，
					 */
					var st = document.body.scrollTop || document.documentElement.scrollTop;
					var sl = document.body.scrollLeft || document.documentElement.scrollLeft;
					//var st = document.documentElement.scrollTop;
					
					
					//获取到鼠标的坐标
					/*
					 * clientX和clientY
					 * 	用于获取鼠标在当前的可见窗口的坐标
					 * div的偏移量，是相对于整个页面的
					 * 
					 * pageX和pageY可以获取鼠标相对于当前页面的坐标
					 * 	但是这个两个属性在IE8中不支持，所以如果需要兼容IE8，则不要使用
					 */
					var left = event.clientX;
					var　top = event.clientY;
					
					//设置div的偏移量
					box1.style.left = left + sl + "px";
					box1.style.top = top + st + "px";
				};
			};
		</script>
	</head>
	<body style="height: 1000px;width: 2000px;">
		<div id="box1"></div>
	</body>
</html>
```

###  7.3 事件冒泡



`10.冒泡.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			#box1{
				width: 200px;
				height: 200px;
				background-color: yellowgreen;
			}
			#s1{
				background-color: yellow;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 事件的冒泡（Bubble）
				 * 	- 所谓的冒泡指的就是事件的向上传导，当后代元素上的事件被触发时，其祖先元素的相同事件也会被触发
				 * 	- 在开发中大部分情况冒泡都是有用的,如果不希望发生事件冒泡可以通过事件对象来取消冒泡
				 * 
				 */
				
				//为s1绑定一个单击响应函数
				var s1 = document.getElementById("s1");
				s1.onclick = function(event){
					event = event || window.event;
					alert("我是span的单击响应函数");
					
					//取消冒泡
					//可以将事件对象的cancelBubble设置为true，即可取消冒泡
					event.cancelBubble = true;
				};
				
				//为box1绑定一个单击响应函数
				var box1 = document.getElementById("box1");
				box1.onclick = function(event){
					event = event || window.event;
					alert("我是div的单击响应函数");
					
					event.cancelBubble = true;
				};
				//为body绑定一个单击响应函数
				document.body.onclick = function(){
					alert("我是body的单击响应函数");
				};
			};
		</script>
	</head>
	<body>
		<div id="box1">
			我是box1
			<span id="s1">我是span</span>
		</div>
	</body>
</html>
```

###  7.4 事件的委派

`01.事件的委派.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
			
			window.onload = function(){
				
				var u1 = document.getElementById("u1");
				
				//点击按钮以后添加超链接
				var btn01 = document.getElementById("btn01");
				btn01.onclick = function(){
					//创建一个li
					var li = document.createElement("li");
					li.innerHTML = "<a href='javascript:;' class='link'>新建的超链接</a>";
					
					//将li添加到ul中
					u1.appendChild(li);
				};
				
				
				/*
				 * 为每一个超链接都绑定一个单击响应函数
				 * 这里我们为每一个超链接都绑定了一个单击响应函数，这种操作比较麻烦，
				 * 	而且这些操作只能为已有的超链接设置事件，而新添加的超链接必须重新绑定
				 */
				//获取所有的a
				var allA = document.getElementsByTagName("a");
				//遍历
				/*for(var i=0 ; i<allA.length ; i++){
					allA[i].onclick = function(){
						alert("我是a的单击响应函数！！！");
					};
				}*/
				
				/*
				 * 我们希望，只绑定一次事件，即可应用到多个的元素上，即使元素是后添加的
				 * 我们可以尝试将其绑定给元素的共同的祖先元素
				 * 
				 * 事件的委派
				 * 	- 指将事件统一绑定给元素的共同的祖先元素，这样当后代元素上的事件触发时，会一直冒泡到祖先元素
				 * 		从而通过祖先元素的响应函数来处理事件。
				 *  - 事件委派是利用了冒泡，通过委派可以减少事件绑定的次数，提高程序的性能
				 */
				
				//为ul绑定一个单击响应函数
				u1.onclick = function(event){
					event = event || window.event;
					
					/*
					 * target
					 * 	- event中的target表示的触发事件的对象
					 */
					//alert(event.target);
					
					
					//如果触发事件的对象是我们期望的元素，则执行否则不执行
					if(event.target.className == "link"){
						alert("我是ul的单击响应函数");
					}
					
				};
				
			};
			
		</script>
	</head>
	<body>
		<button id="btn01">添加超链接</button>
		
		<ul id="u1" style="background-color: #bfa;">
			<li>
				<p>我是p元素</p>
			</li>
			<li><a href="javascript:;" class="link">超链接一</a></li>
			<li><a href="javascript:;" class="link">超链接二</a></li>
			<li><a href="javascript:;" class="link">超链接三</a></li>
		</ul>
	</body>
</html>
```

###  7.5 事件的绑定

`02.事件的绑定.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			window.onload = function(){
				
				/*
				 * 点击按钮以后弹出一个内容
				 */
				//获取按钮对象
				var btn01 = document.getElementById("btn01");
				
				/*
				 * 使用 对象.事件 = 函数 的形式绑定响应函数，
				 * 	它只能同时为一个元素的一个事件绑定一个响应函数，
				 * 	不能绑定多个，如果绑定了多个，则后边会覆盖掉前边的
				 */
				
				//为btn01绑定一个单击响应函数
				/*btn01.onclick = function(){
					alert(1);
				};*/
				
				//为btn01绑定第二个响应函数
				/*btn01.onclick = function(){
					alert(2);
				};*/
				
				/*
				 * addEventListener()
				 * 	- 通过这个方法也可以为元素绑定响应函数
				 *  - 参数：
				 * 		1.事件的字符串，不要on
				 * 		2.回调函数，当事件触发时该函数会被调用
				 * 		3.是否在捕获阶段触发事件，需要一个布尔值，一般都传false
				 * 
				 * 使用addEventListener()可以同时为一个元素的相同事件同时绑定多个响应函数，
				 * 	这样当事件被触发时，响应函数将会按照函数的绑定顺序执行
				 * 
				 * addEventListener()这个方法不支持IE8及以下的浏览器
				 */
				/*btn01.addEventListener("click",function(){
					alert(1);
				},false);
				
				btn01.addEventListener("click",function(){
					alert(2);
				},false);
				
				btn01.addEventListener("click",function(){
					alert(3);
				},false);*/
				
				/*
				 * attachEvent()
				 * 	- 在IE8中可以使用attachEvent()来绑定事件
				 *  - 参数：
				 * 		1.事件的字符串，要on
				 * 		2.回调函数
				 * 
				 *  - 这个方法也可以同时为一个事件绑定多个处理函数，
				 * 		不同的是它是后绑定先执行，执行顺序和addEventListener()相反
				 */
				/*btn01.attachEvent("onclick",function(){
					alert(1);
				});
				
				btn01.attachEvent("onclick",function(){
					alert(2);
				});
				
				btn01.attachEvent("onclick",function(){
					alert(3);
				});*/
				
				/*btn01.addEventListener("click",function(){
					alert(this);
				},false);*/
				
				/*btn01.attachEvent("onclick",function(){
					alert(this);
				});*/
				
				bind(btn01 , "click" , function(){
					alert(this);
				});
			
				
			};
			
			//定义一个函数，用来为指定元素绑定响应函数
			/*
			 * addEventListener()中的this，是绑定事件的对象
			 * attachEvent()中的this，是window
			 *  需要统一两个方法this
			 */
			/*
			 * 参数：
			 * 	obj 要绑定事件的对象
			 * 	eventStr 事件的字符串(不要on)
			 *  callback 回调函数
			 */
			function bind(obj , eventStr , callback){
				if(obj.addEventListener){
					//大部分浏览器兼容的方式
					obj.addEventListener(eventStr , callback , false);
				}else{
					/*
					 * this是谁由调用方式决定
					 * callback.call(obj)
					 */
					//IE8及以下
					obj.attachEvent("on"+eventStr , function(){
						//在匿名函数中调用回调函数
						callback.call(obj);
					});
				}
			}
		</script>
	</head>
	<body>
		<button id="btn01">点我一下</button>
	</body>
</html>
```

###  7.6 事件的传播

`03.事件的传播.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#box1{
				width: 300px;
				height: 300px;
				background-color: yellowgreen;
			}
			
			#box2{
				width: 200px;
				height: 200px;
				background-color: yellow;
			}
			
			#box3{
				width: 150px;
				height: 150px;
				background-color: skyblue;
			}
			
		</style>
		
		<script type="text/javascript">
			
			window.onload = function(){
				
				/*
				 * 分别为三个div绑定单击响应函数
				 */
				var box1 = document.getElementById("box1");
				var box2 = document.getElementById("box2");
				var box3 = document.getElementById("box3");
				
				/*
				 * 事件的传播
				 * 	- 关于事件的传播网景公司和微软公司有不同的理解和设计
				 * 	- 微软公司认为事件应该是由内向外传播，也就是当事件触发时，应该先触发当前元素上的事件，
				 * 		然后再向当前元素的祖先元素上传播，也就说事件应该在冒泡阶段执行。
				 *  - 网景公司认为事件应该是由外向内传播的，也就是当前事件触发时，应该先触发当前元素的最外层的祖先元素的事件，
				 * 		然后在向内传播给后代元素
				 * 	- W3C综合了两个公司的方案，将事件传播分成了三个阶段
				 * 		1.捕获阶段
				 * 			- 在捕获阶段时从最外层的祖先元素，向目标元素进行事件的捕获，但是默认此时不会触发事件
				 * 		2.目标阶段
				 * 			- 事件捕获到目标元素，捕获结束开始在目标元素上触发事件
				 * 		3.冒泡阶段
				 * 			- 事件从目标元素向他的祖先元素传递，依次触发祖先元素上的事件
				 * 
				 * 		- 如果希望在捕获阶段就触发事件，可以将addEventListener()的第三个参数设置为true
				 * 			一般情况下我们不会希望在捕获阶段触发事件，所以这个参数一般都是false
				 * 
				 * 	- IE8及以下的浏览器中没有捕获阶段
				 */
				
				bind(box1,"click",function(){
					alert("我是box1的响应函数")
				});
				
				bind(box2,"click",function(){
					alert("我是box2的响应函数")
				});
				
				bind(box3,"click",function(){
					alert("我是box3的响应函数")
				});
			};
			function bind(obj , eventStr , callback){
				if(obj.addEventListener){
					//大部分浏览器兼容的方式
					obj.addEventListener(eventStr , callback , true);
				}else{
					/*
					 * this是谁由调用方式决定
					 * callback.call(obj)
					 */
					//IE8及以下
					obj.attachEvent("on"+eventStr , function(){
						//在匿名函数中调用回调函数
						callback.call(obj);
					});
				}
			}
		</script>
	</head>
	<body>
		<div id="box1">
			<div id="box2">
				<div id="box3"></div>
			</div>
		</div>
		
	</body>
</html>
```

###  7.7 拖拽一

`04.拖拽.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
			}
			
			#box2{
				width: 100px;
				height: 100px;
				background-color: yellow;
				position: absolute;
				
				left: 200px;
				top: 200px;
			}
			
		</style>
		
		<script type="text/javascript">
			
			window.onload = function(){
				/*
				 * 拖拽box1元素
				 *  - 拖拽的流程
				 * 		1.当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
				 * 		2.当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
				 * 		3.当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
				 */
				
				//获取box1
				var box1 = document.getElementById("box1");
				//为box1绑定一个鼠标按下事件
				//当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
				box1.onmousedown = function(event){
					event = event || window.event;
					//div的偏移量 鼠标.clentX - 元素.offsetLeft
					//div的偏移量 鼠标.clentY - 元素.offsetTop
					var ol = event.clientX - box1.offsetLeft;
					var ot = event.clientY - box1.offsetTop;
					
					
					//为document绑定一个onmousemove事件
					document.onmousemove = function(event){
						event = event || window.event;
						//当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
						//获取鼠标的坐标
						var left = event.clientX - ol;
						var top = event.clientY - ot;
						
						//修改box1的位置
						box1.style.left = left+"px";
						box1.style.top = top+"px";
						
					};
					
					//为document绑定一个鼠标松开事件
					document.onmouseup = function(){
						//当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
						//取消document的onmousemove事件
						document.onmousemove = null;
						//取消document的onmouseup事件
						document.onmouseup = null;
					};
				};
			};
		</script>
	</head>
	<body>
		<div id="box1"></div>
		
		<div id="box2"></div>
	</body>
</html>
```

###  7.8 拖拽二

`05.拖拽2.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
			}
			
			#box2{
				width: 100px;
				height: 100px;
				background-color: yellow;
				position: absolute;
				
				left: 200px;
				top: 200px;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 拖拽box1元素
				 *  - 拖拽的流程
				 * 		1.当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
				 * 		2.当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
				 * 		3.当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
				 */
				
				//获取box1
				var box1 = document.getElementById("box1");
				var box2 = document.getElementById("box2");
				//为box1绑定一个鼠标按下事件
				//当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
				box1.onmousedown = function(event){
					
					//设置box1捕获所有鼠标按下的事件
					/*
					 * setCapture()
					 * 	- 只有IE支持，但是在火狐中调用时不会报错，
					 * 		而如果使用chrome调用，会报错
					 */
					/*if(box1.setCapture){
						box1.setCapture();
					}*/
					box1.setCapture && box1.setCapture();
					
					
					event = event || window.event;
					//div的偏移量 鼠标.clentX - 元素.offsetLeft
					//div的偏移量 鼠标.clentY - 元素.offsetTop
					var ol = event.clientX - box1.offsetLeft;
					var ot = event.clientY - box1.offsetTop;
					
					
					//为document绑定一个onmousemove事件
					document.onmousemove = function(event){
						event = event || window.event;
						//当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
						//获取鼠标的坐标
						var left = event.clientX - ol;
						var top = event.clientY - ot;
						
						//修改box1的位置
						box1.style.left = left+"px";
						box1.style.top = top+"px";
						
					};
					
					//为document绑定一个鼠标松开事件
					document.onmouseup = function(){
						//当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
						//取消document的onmousemove事件
						document.onmousemove = null;
						//取消document的onmouseup事件
						document.onmouseup = null;
						//当鼠标松开时，取消对事件的捕获
						box1.releaseCapture && box1.releaseCapture();
					};
					
					/*
					 * 当我们拖拽一个网页中的内容时，浏览器会默认去搜索引擎中搜索内容，
					 * 	此时会导致拖拽功能的异常，这个是浏览器提供的默认行为，
					 * 	如果不希望发生这个行为，则可以通过return false来取消默认行为
					 * 
					 * 但是这招对IE8不起作用
					 */
					return false;
				};
			};
		</script>
	</head>
	<body>
		我是一段文字
		<div id="box1"></div>
		<div id="box2"></div>
	</body>
</html>
```

`06.测试IE8.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			
			window.onload = function(){
				//分别为两个按钮绑定单击响应函数
				var btn01 = document.getElementById("btn01");
				var btn02 = document.getElementById("btn02");
				
				btn01.onclick = function(){
					alert(1);
				};
				
				btn02.onclick = function(){
					alert(2);
				};
				
				//设置btn01对鼠标按下相关的事件进行捕获
				//当调用一个元素的setCapture()方法以后，这个元素将会把下一次所有的鼠标按下相关的事件捕获到自身上
				btn01.setCapture();
			};
			
		</script>
	</head>
	<body>
		<button id="btn01">按钮01</button>
		<button id="btn02">按钮02</button>
	</body>
</html>
```

###  7.9 拖拽三

`07.拖拽3.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
			}
			
			#box2{
				width: 100px;
				height: 100px;
				background-color: yellow;
				position: absolute;
				
				left: 200px;
				top: 200px;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 拖拽box1元素
				 *  - 拖拽的流程
				 * 		1.当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
				 * 		2.当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
				 * 		3.当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
				 */
				//获取box1
				var box1 = document.getElementById("box1");
				var box2 = document.getElementById("box2");
				var img1 = document.getElementById("img1");
				
				//开启box1的拖拽
				drag(box1);
				//开启box2的
				drag(box2);
				drag(img1);
			};
			
			/*
			 * 提取一个专门用来设置拖拽的函数
			 * 参数：开启拖拽的元素
			 */
			function drag(obj){
				//当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
				obj.onmousedown = function(event){
					
					//设置box1捕获所有鼠标按下的事件
					/*
					 * setCapture()
					 * 	- 只有IE支持，但是在火狐中调用时不会报错，
					 * 		而如果使用chrome调用，会报错
					 */
					/*if(box1.setCapture){
						box1.setCapture();
					}*/
					obj.setCapture && obj.setCapture();
					
					
					event = event || window.event;
					//div的偏移量 鼠标.clentX - 元素.offsetLeft
					//div的偏移量 鼠标.clentY - 元素.offsetTop
					var ol = event.clientX - obj.offsetLeft;
					var ot = event.clientY - obj.offsetTop;
					
					
					//为document绑定一个onmousemove事件
					document.onmousemove = function(event){
						event = event || window.event;
						//当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
						//获取鼠标的坐标
						var left = event.clientX - ol;
						var top = event.clientY - ot;
						
						//修改box1的位置
						obj.style.left = left+"px";
						obj.style.top = top+"px";
						
					};
					
					//为document绑定一个鼠标松开事件
					document.onmouseup = function(){
						//当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
						//取消document的onmousemove事件
						document.onmousemove = null;
						//取消document的onmouseup事件
						document.onmouseup = null;
						//当鼠标松开时，取消对事件的捕获
						obj.releaseCapture && obj.releaseCapture();
					};
					/*
					 * 当我们拖拽一个网页中的内容时，浏览器会默认去搜索引擎中搜索内容，
					 * 	此时会导致拖拽功能的异常，这个是浏览器提供的默认行为，
					 * 	如果不希望发生这个行为，则可以通过return false来取消默认行为
					 * 
					 * 但是这招对IE8不起作用
					 */
					return false;
				};
			}
		</script>
	</head>
	<body>
		我是一段文字
		<div id="box1"></div>
		<div id="box2"></div>
		<img src="img/an.jpg" id="img1" style="position: absolute;"/>
	</body>
</html>
```

###  7.10 滚轮的事件

`08.滚轮事件.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
			}
		</style>
		<script type="text/javascript">
			
			window.onload = function(){
				
				
				//获取id为box1的div
				var box1 = document.getElementById("box1");
				
				//为box1绑定一个鼠标滚轮滚动的事件
				/*
				 * onmousewheel鼠标滚轮滚动的事件，会在滚轮滚动时触发，
				 * 	但是火狐不支持该属性
				 * 
				 * 在火狐中需要使用 DOMMouseScroll 来绑定滚动事件
				 * 	注意该事件需要通过addEventListener()函数来绑定
				 */
				
				
				box1.onmousewheel = function(event){
					
					event = event || window.event;
					
					
					//event.wheelDelta 可以获取鼠标滚轮滚动的方向
					//向上滚 120   向下滚 -120
					//wheelDelta这个值我们不看大小，只看正负
					
					//alert(event.wheelDelta);
					
					//wheelDelta这个属性火狐中不支持
					//在火狐中使用event.detail来获取滚动的方向
					//向上滚 -3  向下滚 3
					//alert(event.detail);
					
					
					/*
					 * 当鼠标滚轮向下滚动时，box1变长
					 * 	当滚轮向上滚动时，box1变短
					 */
					//判断鼠标滚轮滚动的方向
					if(event.wheelDelta > 0 || event.detail < 0){
						//向上滚，box1变短
						box1.style.height = box1.clientHeight - 10 + "px";
						
					}else{
						//向下滚，box1变长
						box1.style.height = box1.clientHeight + 10 + "px";
					}
					
					/*
					 * 使用addEventListener()方法绑定响应函数，取消默认行为时不能使用return false
					 * 需要使用event来取消默认行为event.preventDefault();
					 * 但是IE8不支持event.preventDefault();这个玩意，如果直接调用会报错
					 */
					event.preventDefault && event.preventDefault();
					
					
					/*
					 * 当滚轮滚动时，如果浏览器有滚动条，滚动条会随之滚动，
					 * 这是浏览器的默认行为，如果不希望发生，则可以取消默认行为
					 */
					return false;
				};
				//为火狐绑定滚轮事件
				bind(box1,"DOMMouseScroll",box1.onmousewheel);
				
				
			};
			
			function bind(obj , eventStr , callback){
				if(obj.addEventListener){
					//大部分浏览器兼容的方式
					obj.addEventListener(eventStr , callback , false);
				}else{
					/*
					 * this是谁由调用方式决定
					 * callback.call(obj)
					 */
					//IE8及以下
					obj.attachEvent("on"+eventStr , function(){
						//在匿名函数中调用回调函数
						callback.call(obj);
					});
				}
			}
		</script>
	</head>
	<body style="height: 2000px;">
		<div id="box1"></div>
	</body>
</html>
```

###  7.11 键盘事件

`09.键盘事件.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 键盘事件：
				 * 	onkeydown
				 * 		- 按键被按下
				 * 		- 对于onkeydown来说如果一直按着某个按键不松手，则事件会一直触发
				 * 		- 当onkeydown连续触发时，第一次和第二次之间会间隔稍微长一点，其他的会非常的快
				 * 			这种设计是为了防止误操作的发生。
				 * 	onkeyup
				 * 		- 按键被松开
				 * 
				 *  键盘事件一般都会绑定给一些可以获取到焦点的对象或者是document
				 */
				document.onkeydown = function(event){
					event = event || window.event;
					/*
					 * 可以通过keyCode来获取按键的编码
					 * 	通过它可以判断哪个按键被按下
					 * 除了keyCode，事件对象中还提供了几个属性
					 * 	altKey
					 * 	ctrlKey
					 * 	shiftKey
					 * 		- 这个三个用来判断alt ctrl 和 shift是否被按下
					 * 			如果按下则返回true，否则返回false
					 */
					//console.log(event.keyCode);
					//判断一个y是否被按下
					//判断y和ctrl是否同时被按下
					if(event.keyCode === 89 && event.ctrlKey){
						console.log("ctrl和y都被按下了");
					}
				};
				/*document.onkeyup = function(){
					console.log("按键松开了");
				};*/
				//获取input
				var input = document.getElementsByTagName("input")[0];
				
				input.onkeydown = function(event){
					event = event || window.event;
					//console.log(event.keyCode);
					//数字 48 - 57
					//使文本框中不能输入数字
					if(event.keyCode >= 48 && event.keyCode <= 57){
						//在文本框中输入内容，属于onkeydown的默认行为
						//如果在onkeydown中取消了默认行为，则输入的内容，不会出现在文本框中
						return false;
					}
				};
			};
		</script>
	</head>
	<body>
		<input type="text" />
	</body>
</html>
```

### 7.12 键盘控制div移动

`10.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
			}
		</style>
		<script type="text/javascript">
			//使div可以根据不同的方向键向不同的方向移动
			/*
			 * 按左键，div向左移
			 * 按右键，div向右移
			 * 。。。
			 */
			window.onload = function(){
				//为document绑定一个按键按下的事件
				document.onkeydown = function(event){
					event = event || window.event;
					
					//定义一个变量，来表示移动的速度
					var speed = 10;
					
					//当用户按了ctrl以后，速度加快
					if(event.ctrlKey){
						speed = 500;
					}
					
					/*
					 * 37 左
					 * 38 上
					 * 39 右
					 * 40 下
					 */
					switch(event.keyCode){
						case 37:
							//alert("向左"); left值减小
							box1.style.left = box1.offsetLeft - speed + "px";
							break;
						case 39:
							//alert("向右");
							box1.style.left = box1.offsetLeft + speed + "px";
							break;
						case 38:
							//alert("向上");
							box1.style.top = box1.offsetTop - speed + "px";
							break;
						case 40:
							//alert("向下");
							box1.style.top = box1.offsetTop + speed + "px";
							break;
					}
				};
			};
		</script>
	</head>
	<body>
		<div id="box1"></div>
	</body>
</html>
```

## 8. `BOM`

###  8.1 `BOM`简介

**`BOM`**：`ECMAScript`无疑是`JavaScript`的核心，但是要想在浏览器中使用`JavaScript`，那么`BOM`(浏览器对象模型)才是真正的核心。`BOM`提供了很多对象，用于访问浏览器的功能，这些功能与任何网页内容无关。`BOM`将浏览器中的各个部分转换成了一个一个的对象，通过修改这些对象的属性，调用他们的方法，从而控制浏览器的各种行为

**window对象**：window对象是BOM的核心，它表示一个浏览器的实例。在浏览器中我们可以通过window对象来访问操作浏览器，同时window也是作为全局对象存在的。window对象是浏览器中的全局对象，因此所有在全局作用域中声明的变量、对象、函数都会变成window对象的属性和方法

**窗口大小**：浏览器中提供了四个属性用来确定窗口的大小

```
网页窗口的大小：
                innerWidth
                innerHeight
浏览器本身的尺寸：
                outerWidth
                outerHeight
```

**打开窗口**：使用`window.open()`方法既可以导航到一个特定的URL，也可以打开一个新的浏览器窗口。这个方法需要四个参数：需要加载的url地址、窗口的目标、一个特性的字符串、是否创建新的历史记录

**超时调用**：超时调用会在超过一定时间以后执行指定函数，超时调用都是在全局作用域中执行的。超时调用setTimeout()需要两个参数∶要执行的内容、超过的时间。取消超时调用clearTimeout()

**间歇调用setInterval()**：每隔一段时间执行指定代码。需要两个参数: 要执行的代码、间隔的时间。取消间隔调用：clearInterval()

**系统对话框**：浏览器通过alert() .confirm()和prompt()方法可以调用系统对话框向用户显示消息。它们的外观由操作系统及(或）浏览器设置决定，而不是由CSS决定。显示系统对话框时会导致程序终止，当关闭对话框程序会恢复执行

**alert()方法**：alert()接收一个字符串并显示给用户。调用alert()方法会向用户显示一个包含一个确认按钮的对话框

**confirm()方法**：confirm和alert类似，只不过confirm弹出的对话框有一个确认和取消按钮。用户可以通过按钮来确认是否执行操作。这个函数的执行会返回一个布尔值，如果选择确定则返回true，如果点击取消则返回false

**prompt方法**：prompt会弹出一个带输入框的提示框，并可以将用户输入的内容返回。它需要两个值作为参数︰显示的提示文字、文本框中的默认值

**location对象**：location对象提供了与当前窗口中加载的文档有关的信息，还提供了一些导航功能。location对象的属性和方法

```
location对象的属性和方法：

href属性︰          href属性可以获取或修改当前页面的完整的URL地址，使浏览器跳转到指定页面
assign()方法：      使用和href一样，使浏览器跳转页面，新地址错误参数传递到assign()方法中
replace()方法：     功能一样，只不过使用replace方法跳转地址不会体现到历史记录中
reload()方法：      用于强制刷新当前页面
```

**navigator对象**：navigator对象包含了浏览器的版本、浏览器所支持的插件、浏览器所使用的语言等各种与浏览器相关的信息。我们有时会使用navigator的userAgent属性来检查用户浏览器的版本

**screen对象**：screen 对象基本上只用来表明客户端的能力，其中包括浏览器窗口外部的显示器的信息，如像素宽度和高度等。该对象作用不大，一般不太使用

**history对象**：history对象保存着用户上网的历史记录，从窗口被打开的那一刻算起。**go()方法**：使用go()方法可以在用户的历史记录中任意跳转，可以向后也可以向前。back()方法向后跳转，forward()向前跳转

**document对象**：document对象也是window的一个属性，这个对象代表的是整个网页的文档对象。对网页的大部分操作都需要以document对象作为起点。关于document对象的内容，后边还要具体讲解

###  8.2 navigator

`01.BOM.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript">
			/*
			 * BOM
			 * 	- 浏览器对象模型
			 * 	- BOM可以使我们通过JS来操作浏览器
			 * 	- 在BOM中为我们提供了一组对象，用来完成对浏览器的操作
			 * 	- BOM对象
			 * 		Window
			 * 			- 代表的是整个浏览器的窗口，同时window也是网页中的全局对象
			 * 		Navigator
			 * 			- 代表的当前浏览器的信息，通过该对象可以来识别不同的浏览器
			 * 		Location
			 * 			- 代表当前浏览器的地址栏信息，通过Location可以获取地址栏信息，或者操作浏览器跳转页面
			 * 		History
			 * 			- 代表浏览器的历史记录，可以通过该对象来操作浏览器的历史记录
			 * 				由于隐私原因，该对象不能获取到具体的历史记录，只能操作浏览器向前或向后翻页
			 * 				而且该操作只在当次访问时有效
			 * 		Screen
			 * 			- 代表用户的屏幕的信息，通过该对象可以获取到用户的显示器的相关的信息
			 * 
			 * 
			 * 		这些BOM对象在浏览器中都是作为window对象的属性保存的，
			 * 			可以通过window对象来使用，也可以直接使用
			 * 
			 * 		
			 */
			
			//console.log(navigator);
			//console.log(location);
			//console.log(history);
			
			/*
			 * Navigator
			 * 	- 代表的当前浏览器的信息，通过该对象可以来识别不同的浏览器
			 * 	- 由于历史原因，Navigator对象中的大部分属性都已经不能帮助我们识别浏览器了
			 * 	- 一般我们只会使用userAgent来判断浏览器的信息，
			 * 		userAgent是一个字符串，这个字符串中包含有用来描述浏览器信息的内容，
			 * 		不同的浏览器会有不同的userAgent
			 * 
			 * 火狐的userAgent
			 * 	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0
			 * 
			 * Chrome的userAgent
			 *  Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36
			 * 
			 * IE8
			 * 	Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)
			 * 
			 * IE9
			 * 	Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)
			 * 
			 * IE10
			 * 	Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)
			 * 
			 * IE11
			 * 	Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; rv:11.0) like Gecko
			 * 	- 在IE11中已经将微软和IE相关的标识都已经去除了，所以我们基本已经不能通过UserAgent来识别一个浏览器是否是IE了
			 */
			
			//alert(navigator.appName);
			
			var ua = navigator.userAgent;
			
			console.log(ua);
			
			if(/firefox/i.test(ua)){
				alert("你是火狐！！！");
			}else if(/chrome/i.test(ua)){
				alert("你是Chrome");
			}else if(/msie/i.test(ua)){
				alert("你是IE浏览器~~~");
			}else if("ActiveXObject" in window){
				alert("你是IE11，枪毙了你~~~");
			}
			
			/*
			 * 如果通过UserAgent不能判断，还可以通过一些浏览器中特有的对象，来判断浏览器的信息
			 * 比如：ActiveXObject
			 */
			/*if("ActiveXObject" in window){
				alert("你是IE，我已经抓住你了~~~");
			}else{
				alert("你不是IE~~~");
			}*/
			
			/*alert("ActiveXObject" in window);*/
		</script>
	</head>
	<body>
	</body>
</html>
```

###  8.3 History

`02.History.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * History
			 * 	- 对象可以用来操作浏览器向前或向后翻页
			 */
			window.onload = function(){
				
				//获取按钮对象
				var btn = document.getElementById("btn");
				
				btn.onclick = function(){
					/*
					 * length
					 * 	- 属性，可以获取到当成访问的链接数量
					 */
					//alert(history.length);
					
					/*
					 * back()
					 * 	- 可以用来回退到上一个页面，作用和浏览器的回退按钮一样
					 */
					//history.back();
					
					/*
					 * forward()
					 * 	- 可以跳转下一个页面，作用和浏览器的前进按钮一样
					 */
					//history.forward();
					
					/*
					 * go()
					 * 	- 可以用来跳转到指定的页面
					 * 	- 它需要一个整数作为参数
					 * 		1:表示向前跳转一个页面 相当于forward()
					 * 		2:表示向前跳转两个页面
					 * 		-1:表示向后跳转一个页面
					 * 		-2:表示向后跳转两个页面
					 */
					history.go(-2);
				};
				
			};
			
		</script>
	</head>
	<body>
		<button id="btn">点我一下</button>
		<h1>History</h1>
		<a href="01.BOM.html">去BOM</a>
	</body>
</html>
```

###  8.4 Location

`03.Location.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			/*
			 * Location
			 * 	- 该对象中封装了浏览器的地址栏的信息
			 */
			window.onload = function(){
				
				//获取按钮对象
				var btn = document.getElementById("btn");
				
				btn.onclick = function(){
					
					//如果直接打印location，则可以获取到地址栏的信息（当前页面的完整路径）
					//alert(location);
					
					/*
					 * 如果直接将location属性修改为一个完整的路径，或相对路径
					 * 	则我们页面会自动跳转到该路径，并且会生成相应的历史记录
					 */
					//location = "http://www.baidu.com";
					//location = "01.BOM.html";
					
					/*
					 * assign()
					 * 	- 用来跳转到其他的页面，作用和直接修改location一样
					 */
					//location.assign("http://www.baidu.com");
					
					/*
					 * reload()
					 * 	- 用于重新加载当前页面，作用和刷新按钮一样
					 * 	- 如果在方法中传递一个true，作为参数，则会强制清空缓存刷新页面
					 */
					//location.reload(true);
					
					/*
					 * replace()
					 * 	- 可以使用一个新的页面替换当前页面，调用完毕也会跳转页面
					 * 		不会生成历史记录，不能使用回退按钮回退
					 */
					location.replace("01.BOM.html");
				};
			};
		</script>
	</head>
	<body>
		<button id="btn">点我一下</button>
		<h1>Location</h1>
		<input type="text" />
		<a href="01.BOM.html">去BOM</a>
	</body>
</html>
```

###  8.5 定时调用

`04.定时调用.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			window.onload = function(){
				//获取count
				var count = document.getElementById("count");
				//使count中的内容，自动切换
				/*
				 * JS的程序的执行速度是非常非常快的
				 * 	如果希望一段程序，可以每间隔一段时间执行一次，可以使用定时调用
				 */
				/*for(var i=0 ; i<10000 ; i++){
					count.innerHTML = i;
					
					alert("hello");
				}*/
				/*
				 * setInterval()
				 * 	- 定时调用
				 * 	- 可以将一个函数，每隔一段时间执行一次
				 * 	- 参数：
				 * 		1.回调函数，该函数会每隔一段时间被调用一次
				 * 		2.每次调用间隔的时间，单位是毫秒
				 * 
				 * 	- 返回值：
				 * 		返回一个Number类型的数据
				 * 		这个数字用来作为定时器的唯一标识
				 */
				var num = 1;
				var timer = setInterval(function(){
					count.innerHTML = num++;
					if(num == 11){
						//关闭定时器
						clearInterval(timer);
					}
				},1000);
				//console.log(timer);
				//clearInterval()可以用来关闭一个定时器
				//方法中需要一个定时器的标识作为参数，这样将关闭标识对应的定时器
				//clearInterval(timer);
			};
		</script>
	</head>
	<body>
		<h1 id="count"></h1>
	</body>
</html>
```

### 8.6 定时切换图片

`05.练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			window.onload = function(){
				/*
				 * 使图片可以自动切换
				 */
				
				//获取img标签
				var img1 = document.getElementById("img1");
				
				//创建一个数组来保存图片的路径
				var imgArr = ["img/1.jpg","img/2.jpg","img/3.jpg","img/4.jpg","img/5.jpg"];
				
				//创建一个变量，用来保存当前图片的索引
				var index = 0;
				
				//定义一个变量，用来保存定时器的标识
				var timer;
				
				//为btn01绑定一个单击响应函数
				var btn01 = document.getElementById("btn01");
				btn01.onclick = function(){
					
					/*
					 * 目前，我们每点击一次按钮，就会开启一个定时器，
					 * 	点击多次就会开启多个定时器，这就导致图片的切换速度过快，
					 * 	并且我们只能关闭最后一次开启的定时器
					 */
					
					//在开启定时器之前，需要将当前元素上的其他定时器关闭
					clearInterval(timer);
					
					/*
					 * 开启一个定时器，来自动切换图片
					 */
					timer = setInterval(function(){
						//使索引自增
						index++;
						//判断索引是否超过最大索引
						/*if(index >= imgArr.length){
							//则将index设置为0
							index = 0;
						}*/
						index %= imgArr.length;
						//修改img1的src属性
						img1.src = imgArr[index];
						
					},1000);
				};
				
				//为btn02绑定一个单击响应函数
				var btn02 = document.getElementById("btn02");
				btn02.onclick = function(){
					//点击按钮以后，停止图片的自动切换，关闭定时器
					/*
					 * clearInterval()可以接收任意参数，
					 * 	如果参数是一个有效的定时器的标识，则停止对应的定时器
					 * 	如果参数不是一个有效的标识，则什么也不做
					 */
					clearInterval(timer);
				};
			};
		</script>
	</head>
	<body>
		<img id="img1" src="img/1.jpg"/>
		<br /><br />
		<button id="btn01">开始</button>
		<button id="btn02">停止</button>
	</body>
</html>
```

### 8.7 定时器控制div移动

`06.移动div练习.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
			}
		</style>
		<script type="text/javascript">
			//使div可以根据不同的方向键向不同的方向移动
			/*
			 * 按左键，div向左移
			 * 按右键，div向右移
			 * 。。。
			 */
			window.onload = function(){
				//定义一个变量，来表示移动的速度
				var speed = 10;
				
				//创建一个变量表示方向
				//通过修改dir来影响移动的方向
				var dir = 0;
				
				//开启一个定时器，来控制div的移动
				setInterval(function(){
					/*
					 * 37 左
					 * 38 上
					 * 39 右
					 * 40 下
					 */
					switch(dir){
						case 37:
							//alert("向左"); left值减小
							box1.style.left = box1.offsetLeft - speed + "px";
							break;
						case 39:
							//alert("向右");
							box1.style.left = box1.offsetLeft + speed + "px";
							break;
						case 38:
							//alert("向上");
							box1.style.top = box1.offsetTop - speed + "px";
							break;
						case 40:
							//alert("向下");
							box1.style.top = box1.offsetTop + speed + "px";
							break;
					}
				},30);
				//为document绑定一个按键按下的事件
				document.onkeydown = function(event){
					event = event || window.event;
					
					//当用户按了ctrl以后，速度加快
					if(event.ctrlKey){
						speed = 500;
					}else{
						speed = 10;
					}
					
					//使dir等于按键的值
					dir = event.keyCode;
				};
				
				//当按键松开时，div不再移动
				document.onkeyup = function(){
					//设置方向为0
					dir = 0;
				};
			};
		</script>
	</head>
	<body>
		<div id="box1"></div>
	</body>
</html>
```

### 8.8 延时调用

`07.延时调用.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
			var num = 1;
			//开启一个定时器
			/*setInterval(function(){
				console.log(num++);
			},3000);*/
			/*
			 * 延时调用，
			 * 	延时调用一个函数不马上执行，而是隔一段时间以后在执行，而且只会执行一次
			 * 
			 * 延时调用和定时调用的区别，定时调用会执行多次，而延时调用只会执行一次
			 * 
			 * 延时调用和定时调用实际上是可以互相代替的，在开发中可以根据自己需要去选择
			 */
			var timer = setTimeout(function(){
				console.log(num++);
			},3000);
			//使用clearTimeout()来关闭一个延时调用
			clearTimeout(timer);
		</script>
	</head>
	<body>
	</body>
</html>
```

###  8.9 定时调用练习一

`08.定时器.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			*{
				margin: 0;
				padding: 0;
			}
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
				left: 0;
			}
		</style>
		<script type="text/javascript">
			
			window.onload = function(){
				
				//获取box1
				var box1 = document.getElementById("box1");
				//获取btn01
				var btn01 = document.getElementById("btn01");
				
				//定义一个变量，用来保存定时器的标识
				var timer;
				
				//点击按钮以后，使box1向右移动（left值增大）
				btn01.onclick = function(){
					
					//关闭上一个定时器
					clearInterval(timer);
					
					//开启一个定时器，用来执行动画效果
					timer = setInterval(function(){
						
						//获取box1的原来的left值
						var oldValue = parseInt(getStyle(box1,"left"));
						
						//在旧值的基础上增加
						var newValue = oldValue + 1;
						
						//判断newValue是否大于800
						if(newValue > 800){
							newValue = 800;
						}
						
						//将新值设置给box1
						box1.style.left = newValue + "px";
						
						//当元素移动到800px时，使其停止执行动画
						if(newValue == 800){
							//达到目标，关闭定时器
							clearInterval(timer);
						}
					},30);
				};
			};
			/*
			 * 定义一个函数，用来获取指定元素的当前的样式
			 * 参数：
			 * 		obj 要获取样式的元素
			 * 		name 要获取的样式名
			 */
			function getStyle(obj , name){
				
				if(window.getComputedStyle){
					//正常浏览器的方式，具有getComputedStyle()方法
					return getComputedStyle(obj , null)[name];
				}else{
					//IE8的方式，没有getComputedStyle()方法
					return obj.currentStyle[name];
				}
			}
		</script>
	</head>
	<body>
		<button id="btn01">点击按钮以后box1向右移动</button>
		<br /><br />
		<div id="box1"></div>
		<div style="width: 0; height: 1000px; border-left:1px black solid; position: absolute; left: 800px;top:0;"></div>
	</body>
</html>
```

###  8.10 定时器调用练习二

`09.定时器2.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			*{
				margin: 0;
				padding: 0;
			}
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
				left: 0;
			}
			
		</style>
		
		<script type="text/javascript">
			
			window.onload = function(){
				
				//获取box1
				var box1 = document.getElementById("box1");
				//获取btn01
				var btn01 = document.getElementById("btn01");
				
				//获取btn02
				var btn02 = document.getElementById("btn02");
				
				
				//点击按钮以后，使box1向右移动（left值增大）
				btn01.onclick = function(){
					move(box1 , 800 , 10);
				};
				
				
				//点击按钮以后，使box1向左移动（left值减小）
				btn02.onclick = function(){
					move(box1 , 0 , 10);
				};
				
			};
			
			//定义一个变量，用来保存定时器的标识
			var timer;
			
			//尝试创建一个可以执行简单动画的函数
			/*
			 * 参数：
			 * 	obj:要执行动画的对象
			 * 	target:执行动画的目标位置
			 * 	speed:移动的速度(正数向右移动，负数向左移动)
			 */
			function move(obj , target ,speed){
				//关闭上一个定时器
				clearInterval(timer);
				
				//获取元素目前的位置
				var current = parseInt(getStyle(obj,"left"));
				
				//判断速度的正负值
				//如果从0 向 800移动，则speed为正
				//如果从800向0移动，则speed为负
				if(current > target){
					//此时速度应为负值
					speed = -speed;
				}
				
				//开启一个定时器，用来执行动画效果
				timer = setInterval(function(){
					
					//获取box1的原来的left值
					var oldValue = parseInt(getStyle(obj,"left"));
					
					//在旧值的基础上增加
					var newValue = oldValue + speed;
					
					//判断newValue是否大于800
					//从800 向 0移动
					//向左移动时，需要判断newValue是否小于target
					//向右移动时，需要判断newValue是否大于target
					if((speed < 0 && newValue < target) || (speed > 0 && newValue > target)){
						newValue = target;
					}
					
					//将新值设置给box1
					obj.style.left = newValue + "px";
					
					//当元素移动到0px时，使其停止执行动画
					if(newValue == target){
						//达到目标，关闭定时器
						clearInterval(timer);
					}
					
					
				},30);
			}
			
			
			/*
			 * 定义一个函数，用来获取指定元素的当前的样式
			 * 参数：
			 * 		obj 要获取样式的元素
			 * 		name 要获取的样式名
			 */
			function getStyle(obj , name){
				
				if(window.getComputedStyle){
					//正常浏览器的方式，具有getComputedStyle()方法
					return getComputedStyle(obj , null)[name];
				}else{
					//IE8的方式，没有getComputedStyle()方法
					return obj.currentStyle[name];
				}
				
			}
			
			
		</script>
	</head>
	<body>
		
		<button id="btn01">点击按钮以后box1向右移动</button>
		<button id="btn02">点击按钮以后box1向左移动</button>
		
		<br /><br />
		
		<div id="box1"></div>
		
		<div style="width: 0; height: 1000px; border-left:1px black solid; position: absolute; left: 800px;top:0;"></div>
		
	</body>
</html>
```

###  8.11定时器练习三

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			*{
				margin: 0;
				padding: 0;
			}
			
			#box1{
				width: 100px;
				height: 100px;
				background-color: red;
				position: absolute;
				left: 0;
			}
			
			#box2{
				width: 100px;
				height: 100px;
				background-color: yellow;
				position: absolute;
				left: 0;
				top: 200px;
			}
			
		</style>
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript">
			
			window.onload = function(){
				//获取box1
				var box1 = document.getElementById("box1");
				//获取btn01
				var btn01 = document.getElementById("btn01");
				//获取btn02
				var btn02 = document.getElementById("btn02");
				//点击按钮以后，使box1向右移动（left值增大）
				btn01.onclick = function(){
					move(box1 ,"left", 800 , 20);
				};
				//点击按钮以后，使box1向左移动（left值减小）
				btn02.onclick = function(){
					move(box1 ,"left", 0 , 10);
				};
				//获取btn03
				var btn03 = document.getElementById("btn03");
				btn03.onclick = function(){
					move(box2 , "left",800 , 10);
				};
				
				//测试按钮
				var btn04 = document.getElementById("btn04");
				btn04.onclick = function(){
					//move(box2 ,"width", 800 , 10);
					//move(box2 ,"top", 800 , 10);
					//move(box2 ,"height", 800 , 10);
					move(box2 , "width" , 800 , 10 , function(){
						move(box2 , "height" , 400 , 10 , function(){
							move(box2 , "top" , 0 , 10 , function(){
								move(box2 , "width" , 100 , 10 , function(){
							
								});
							});
						});
					});
				};
			};
			
			//定义一个变量，用来保存定时器的标识
			/*
			 * 目前我们的定时器的标识由全局变量timer保存，
			 * 	所有的执行正在执行的定时器都在这个变量中保存
			 */
			//var timer;
		</script>
	</head>
	<body>
		
		<button id="btn01">点击按钮以后box1向右移动</button>
		<button id="btn02">点击按钮以后box1向左移动</button>
		<button id="btn03">点击按钮以后box2向右移动</button>
		<button id="btn04">测试按钮</button>
		<br /><br />
		<div id="box1"></div>
		<div id="box2"></div>
		<div style="width: 0; height: 1000px; border-left:1px black solid; position: absolute; left: 800px;top:0;"></div>
	</body>
</html>
```

### 8.12 轮播图

`11.轮播图.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			
			/*
			 * 设置outer的样式
			 */
			#outer{
				/*设置宽和高*/
				width: 520px;
				height: 333px;
				/*居中*/
				margin: 50px auto;
				/*设置背景颜色*/
				background-color: greenyellow;
				/*设置padding*/
				padding: 10px 0;
				/*开启相对定位*/
				position: relative;
				/*裁剪溢出的内容*/
				overflow: hidden;
			}
			
			/*设置imgList*/
			#imgList{
				/*去除项目符号*/
				list-style: none;
				/*设置ul的宽度*/
				/*width: 2600px;*/
				/*开启绝对定位*/
				position: absolute;
				/*设置偏移量*/
				/*
				 * 每向左移动520px，就会显示到下一张图片
				 */
				left: 0px;
			}
			
			/*设置图片中的li*/
			#imgList li{
				/*设置浮动*/
				float: left;
				/*设置左右外边距*/
				margin: 0 10px;
			}
			
			/*设置导航按钮*/
			#navDiv{
				/*开启绝对定位*/
				position: absolute;
				/*设置位置*/
				bottom: 15px;
				/*设置left值
				 	outer宽度  520
				 	navDiv宽度 25*5 = 125
				 		520 - 125 = 395/2 = 197.5
				 * */
				/*left: 197px;*/
			}
			
			#navDiv a{
				/*设置超链接浮动*/
				float: left;
				/*设置超链接的宽和高*/
				width: 15px;
				height: 15px;
				/*设置背景颜色*/
				background-color: red;
				/*设置左右外边距*/
				margin: 0 5px;
				/*设置透明*/
				opacity: 0.5;
				/*兼容IE8透明*/
				filter: alpha(opacity=50);
			}
			
			/*设置鼠标移入的效果*/
			#navDiv a:hover{
				background-color: black;
			}
		</style>
		
		<!--引用工具-->
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript">
			window.onload = function(){
				//获取imgList
				var imgList = document.getElementById("imgList");
				//获取页面中所有的img标签
				var imgArr = document.getElementsByTagName("img");
				//设置imgList的宽度
				imgList.style.width = 520*imgArr.length+"px";
				
				/*设置导航按钮居中*/
				//获取navDiv
				var navDiv = document.getElementById("navDiv");
				//获取outer
				var outer = document.getElementById("outer");
				//设置navDiv的left值
				navDiv.style.left = (outer.offsetWidth - navDiv.offsetWidth)/2 + "px";
				
				//默认显示图片的索引
				var index = 0;
				//获取所有的a
				var allA = document.getElementsByTagName("a");
				//设置默认选中的效果
				allA[index].style.backgroundColor = "black";
				
				/*
				 	点击超链接切换到指定的图片
				 		点击第一个超链接，显示第一个图片
				 		点击第二个超链接，显示第二个图片
				 * */
				
				//为所有的超链接都绑定单击响应函数
				for(var i=0; i<allA.length ; i++){
					
					//为每一个超链接都添加一个num属性
					allA[i].num = i;
					
					//为超链接绑定单击响应函数
					allA[i].onclick = function(){
						
						//获取点击超链接的索引,并将其设置为index
						index = this.num;
						
						//切换图片
						/*
						 * 第一张  0 0
						 * 第二张  1 -520
						 * 第三张  2 -1040
						 */
						//imgList.style.left = -520*index + "px";
						//设置选中的a
						setA();
						
						//使用move函数来切换图片
						move(imgList , "left" , -520*index , 20 , function(){
							
						});
						
					};
				}
				//创建一个方法用来设置选中的a
				function setA(){
					
					//遍历所有a，并将它们的背景颜色设置为红色
					for(var i=0 ; i<allA.length ; i++){
						allA[i].style.backgroundColor = "";
					}
					//将选中的a设置为黑色
					allA[index].style.backgroundColor = "black";
				};
			};
		</script>
	</head>
	<body>
		<!-- 创建一个外部的div，来作为大的容器 -->
		<div id="outer">
			<!-- 创建一个ul，用于放置图片 -->
			<ul id="imgList">
				<li><img src="img/1.jpg"/></li>
				<li><img src="img/2.jpg"/></li>
				<li><img src="img/3.jpg"/></li>
				<li><img src="img/4.jpg"/></li>
				<li><img src="img/5.jpg"/></li>
			</ul>
			<!--创建导航按钮-->
			<div id="navDiv">
				<a href="javascript:;"></a>
				<a href="javascript:;"></a>
				<a href="javascript:;"></a>
				<a href="javascript:;"></a>
				<a href="javascript:;"></a>
			</div>
		</div>
	</body>
</html>
```

### 8.13 类的操作

`02.类的操作.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			
			.b1{
				width: 100px;
				height: 100px;
				background-color: red;
			}
			
			.b2{
				height: 300px;
				background-color: yellow;
			}
			
		</style>
		
		<script type="text/javascript">
			
			window.onload = function(){
				//获取box
				var box = document.getElementById("box");
				//获取btn01
				var btn01 = document.getElementById("btn01");
				
				//为btn01绑定单击响应函数
				btn01.onclick = function(){
					//修改box的样式
					/*
					 * 通过style属性来修改元素的样式，每修改一个样式，浏览器就需要重新渲染一次页面
					 * 	这样的执行的性能是比较差的，而且这种形式当我们要修改多个样式时，也不太方便
					 */
					/*box.style.width = "200px";
					box.style.height = "200px";
					box.style.backgroundColor = "yellow";*/
					
					/*
					 * 我希望一行代码，可以同时修改多个样式
					 */
					
					//修改box的class属性
					/*
					 * 我们可以通过修改元素的class属性来间接的修改样式
					 * 这样一来，我们只需要修改一次，即可同时修改多个样式，
					 * 	浏览器只需要重新渲染页面一次，性能比较好，
					 * 	并且这种方式，可以使表现和行为进一步的分离
					 */
					//box.className += " b2";
					//addClass(box,"b2");
					
					//alert(hasClass(box,"hello"));
					
					//removeClass(box,"b2");
					
					toggleClass(box,"b2");
				};
				
			};
			
			//定义一个函数，用来向一个元素中添加指定的class属性值
			/*
			 * 参数:
			 * 	obj 要添加class属性的元素
			 *  cn 要添加的class值
			 * 	
			 */
			function addClass(obj , cn){
				
				//检查obj中是否含有cn
				if(!hasClass(obj , cn)){
					obj.className += " "+cn;
				}
				
			}
			
			/*
			 * 判断一个元素中是否含有指定的class属性值
			 * 	如果有该class，则返回true，没有则返回false
			 * 	
			 */
			function hasClass(obj , cn){
				
				//判断obj中有没有cn class
				//创建一个正则表达式
				//var reg = /\bb2\b/;
				var reg = new RegExp("\\b"+cn+"\\b");
				
				return reg.test(obj.className);
				
			}
			
			/*
			 * 删除一个元素中的指定的class属性
			 */
			function removeClass(obj , cn){
				//创建一个正则表达式
				var reg = new RegExp("\\b"+cn+"\\b");
				
				//删除class
				obj.className = obj.className.replace(reg , "");
				
			}
			
			/*
			 * toggleClass可以用来切换一个类
			 * 	如果元素中具有该类，则删除
			 * 	如果元素中没有该类，则添加
			 */
			function toggleClass(obj , cn){
				
				//判断obj中是否含有cn
				if(hasClass(obj , cn)){
					//有，则删除
					removeClass(obj , cn);
				}else{
					//没有，则添加
					addClass(obj , cn);
				}
				
			}
			
		</script>
	</head>
	<body>
		
		<button id="btn01">点击按钮以后修改box的样式</button>
		
		<br /><br />
		
		<div id="box" class="b1 b2"></div>
	</body>
</html>
```

### 8.14 二级菜单

`03.二级菜单.html`

```html
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>二级菜单</title>
		<style type="text/css">
			* {
				margin: 0;
				padding: 0;
				list-style-type: none;
			}
			
			a,img {
				border: 0;
				text-decoration: none;
			}
			
			body {
				font: 12px/180% Arial, Helvetica, sans-serif, "新宋体";
			}
		</style>

		<link rel="stylesheet" type="text/css" href="css/sdmenu.css" />
		
		<script type="text/javascript" src="js/tools.js"></script>
		<script type="text/javascript">
			window.onload = function(){
				
				/*
				 * 我们的每一个菜单都是一个div
				 * 	当div具有collapsed这个类时，div就是折叠的状态
				 * 	当div没有这个类是，div就是展开的状态
				 */
				
				/*
				 * 点击菜单，切换菜单的显示状态
				 */
				//获取所有的class为menuSpan的元素
				var menuSpan = document.querySelectorAll(".menuSpan");
				
				//定义一个变量，来保存当前打开的菜单
				var openDiv = menuSpan[0].parentNode;
				
				//为span绑定单击响应函数
				for(var i=0 ; i<menuSpan.length ; i++){
					menuSpan[i].onclick = function(){
						
						//this代表我当前点击的span
						//获取当前span的父元素
						var parentDiv = this.parentNode;
						
						//切换菜单的显示状态
						toggleMenu(parentDiv);
						
						
						//判断openDiv和parentDiv是否相同
						if(openDiv != parentDiv  && !hasClass(openDiv , "collapsed")){
							//打开菜单以后，应该关闭之前打开的菜单
							//为了可以统一处理动画过渡效果，我们希望在这将addClass改为toggleClass
							//addClass(openDiv , "collapsed");
							//此处toggleClass()不需要有移除的功能
							//toggleClass(openDiv , "collapsed");
							//切换菜单的显示状态
							toggleMenu(openDiv);
						}
						
						//修改openDiv为当前打开的菜单
						openDiv = parentDiv;
						
					};
				}
				
				/*
				 * 用来切换菜单折叠和显示状态
				 */
				function toggleMenu(obj){
					//在切换类之前，获取元素的高度
					var begin = obj.offsetHeight;
					
					//切换parentDiv的显示
					toggleClass(obj , "collapsed");
					
					//在切换类之后获取一个高度
					var end = obj.offsetHeight;
					
					//console.log("begin = "+begin +" , end = "+end);
					//动画效果就是将高度从begin向end过渡
					//将元素的高度重置为begin
					obj.style.height = begin + "px";
					
					//执行动画，从bengin向end过渡
					move(obj,"height",end,10,function(){
						//动画执行完毕，内联样式已经没有存在的意义了，删除之
						obj.style.height = "";
					});
						
				}
				
				
			};
			
			
		</script>
		
	</head>

	<body>

		<div id="my_menu" class="sdmenu">
			<div>
				<span class="menuSpan">在线工具</span>
				<a href="#">图像优化</a>
				<a href="#">收藏夹图标生成器</a>
				<a href="#">邮件</a>
				<a href="#">htaccess密码</a>
				<a href="#">梯度图像</a>
				<a href="#">按钮生成器</a>
			</div>
			<div class="collapsed">
				<span class="menuSpan">支持我们</span>
				<a href="#">推荐我们</a>
				<a href="#">链接我们</a>
				<a href="#">网络资源</a>
			</div>
			<div class="collapsed">
				<span class="menuSpan">合作伙伴</span>
				<a href="#">JavaScript工具包</a>
				<a href="#">CSS驱动</a>
				<a href="#">CodingForums</a>
				<a href="#">CSS例子</a>
			</div>
			<div class="collapsed">
				<span class="menuSpan">测试电流</span>
				<a href="#">Current or not</a>
				<a href="#">Current or not</a>
				<a href="#">Current or not</a>
				<a href="#">Current or not</a>
			</div>
		</div>
	</body>
</html>
```

###  8.15 JSON

`04.JSON.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<!--
			如果需要兼容IE7及以下的JSON操作，则可以通过引入一个外部的js文件来处理
		-->
		<script type="text/javascript" src="js/json2.js"></script>
		<script type="text/javascript">
			
			/*
			 * JSON
			 * 	- JS中的对象只有JS自己认识，其他的语言都不认识
			 * 	- JSON就是一个特殊格式的字符串，这个字符串可以被任意的语言所识别，
			 * 		并且可以转换为任意语言中的对象，JSON在开发中主要用来数据的交互
			 * 	- JSON
			 * 		- JavaScript Object Notation JS对象表示法
			 * 		- JSON和JS对象的格式一样，只不过JSON字符串中的属性名必须加双引号
			 * 			其他的和JS语法一致
			 * 		JSON分类：
			 * 			1.对象 {}
			 * 			2.数组 []
			 * 
			 * 		JSON中允许的值：
			 * 			1.字符串
			 * 			2.数值
			 * 			3.布尔值
			 * 			4.null
			 * 			5.对象
			 * 			6.数组
			 */
			
			//创建一个对象
			
			
			var arr = '[1,2,3,"hello",true]';
			
			var obj2 = '{"arr":[1,2,3]}';
			
			var arr2 ='[{"name":"孙悟空","age":18,"gender":"男"},{"name":"孙悟空","age":18,"gender":"男"}]';
			
			/*
			 * 将JSON字符串转换为JS中的对象
			 * 	在JS中，为我们提供了一个工具类，就叫JSON
			 * 	这个对象可以帮助我们将一个JSON转换为JS对象，也可以将一个JS对象转换为JSON
			 */
			
			var json = '{"name":"孙悟空","age":18,"gender":"男"}';
			
			/*
			 * json --> js对象
			 * 	 JSON.parse()
			 * 		- 可以将以JSON字符串转换为js对象
			 * 		- 它需要一个JSON字符串作为参数，会将该字符串转换为JS对象并返回
			 */
			
			var o = JSON.parse(json);
			var o2 = JSON.parse(arr);
			
			//console.log(o.gender);
			//console.log(o2[1]);
			
			var obj3 = {name:"猪八戒" , age:28 , gender:"男"};
			
			
			/*
			 * JS对象 ---> JSON
			 * 	JSON.stringify()
			 * 		- 可以将一个JS对象转换为JSON字符串
			 * 		- 需要一个js对象作为参数，会返回一个JSON字符串
			 */
			
			var str = JSON.stringify(obj3);
			//console.log(str);
			
			/*
			 * JSON这个对象在IE7及以下的浏览器中不支持，所以在这些浏览器中调用时会报错
			 */
			var str3 = '{"name":"孙悟空","age":18,"gender":"男"}';
			JSON.parse(str3);
		</script>
	</head>
	<body>
	</body>
</html>
```

`05.JSON.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript">
		
			var str = '{"name":"孙悟空","age":18,"gender":"男"}';
			
			/*
			 * eval()
			 * 	- 这个函数可以用来执行一段字符串形式的JS代码，并将执行结果返回
			 * 	- 如果使用eval()执行的字符串中含有{},它会将{}当成是代码块
			 * 		如果不希望将其当成代码块解析，则需要在字符串前后各加一个()
			 * 
			 * 	- eval()这个函数的功能很强大，可以直接执行一个字符串中的js代码，
			 * 		但是在开发中尽量不要使用，首先它的执行性能比较差，然后它还具有安全隐患
			 */
			var str2 = "alert('hello');";
			var obj = eval("("+str+")");
			//console.log(obj);
		</script>
	</head>
	<body>
	</body>
</html>
```






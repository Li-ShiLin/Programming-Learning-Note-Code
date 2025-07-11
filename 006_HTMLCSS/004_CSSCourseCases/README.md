<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.选择器进阶](#1%E9%80%89%E6%8B%A9%E5%99%A8%E8%BF%9B%E9%98%B6)
    - [1.1 后代选择器](#11-%E5%90%8E%E4%BB%A3%E9%80%89%E6%8B%A9%E5%99%A8)
    - [1.2 子代选择�?:>](#12-%E5%AD%90%E4%BB%A3%E9%80%89%E6%8B%A9%E5%99%A8)
    - [1.3 并集选择�?: ，](#13-%E5%B9%B6%E9%9B%86%E9%80%89%E6%8B%A9%E5%99%A8-)
    - [1.4 交集选择器](#14-%E4%BA%A4%E9%9B%86%E9%80%89%E6%8B%A9%E5%99%A8)
    - [1.5 hover伪类选择器](#15-hover%E4%BC%AA%E7%B1%BB%E9%80%89%E6%8B%A9%E5%99%A8)
    - [1.6 emmet语法](#16-emmet%E8%AF%AD%E6%B3%95)
- [2.背景相关属�?�](#2%E8%83%8C%E6%99%AF%E7%9B%B8%E5%85%B3%E5%B1%9E%E6%80%A7)
    - [2.1 背景颜色](#21-%E8%83%8C%E6%99%AF%E9%A2%9C%E8%89%B2)
    - [2.2 背景图片](#22-%E8%83%8C%E6%99%AF%E5%9B%BE%E7%89%87)
    - [2.3 背景平铺](#23-%E8%83%8C%E6%99%AF%E5%B9%B3%E9%93%BA)
    - [2.4 背景位置](#24-%E8%83%8C%E6%99%AF%E4%BD%8D%E7%BD%AE)
    - [2.5 背景相关属�?�连写](#25-%E8%83%8C%E6%99%AF%E7%9B%B8%E5%85%B3%E5%B1%9E%E6%80%A7%E8%BF%9E%E5%86%99)
    - [2.6 (拓展)img标签和背景图片的区别](#26-%E6%8B%93%E5%B1%95img%E6%A0%87%E7%AD%BE%E5%92%8C%E8%83%8C%E6%99%AF%E5%9B%BE%E7%89%87%E7%9A%84%E5%8C%BA%E5%88%AB)
- [3.元素显示模式](#3%E5%85%83%E7%B4%A0%E6%98%BE%E7%A4%BA%E6%A8%A1%E5%BC%8F)
    - [3.1块级元素](#31%E5%9D%97%E7%BA%A7%E5%85%83%E7%B4%A0)
    - [3.2 行内元素](#32-%E8%A1%8C%E5%86%85%E5%85%83%E7%B4%A0)
    - [3.3 行内块元素](#33-%E8%A1%8C%E5%86%85%E5%9D%97%E5%85%83%E7%B4%A0)
    - [3.4 元素显示模式转换](#34-%E5%85%83%E7%B4%A0%E6%98%BE%E7%A4%BA%E6%A8%A1%E5%BC%8F%E8%BD%AC%E6%8D%A2)
    - [3.5 HTML嵌套规范注意点](#35-html%E5%B5%8C%E5%A5%97%E8%A7%84%E8%8C%83%E6%B3%A8%E6%84%8F%E7%82%B9)
- [4.CSS特�?�](#4css%E7%89%B9%E6%80%A7)
    - [4.1 继承性](#41-%E7%BB%A7%E6%89%BF%E6%80%A7)
    - [4.2 层叠性](#42-%E5%B1%82%E5%8F%A0%E6%80%A7)
- [5.综合案例](#5%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.选择器进�?

#### 1.1 后代选择�?

- 作用:根据HTML标签的嵌套关系，选择父元素后代中满足条件的元�?
- 选择器语�?:**选择�?1 选择�?2{ css }**
- 结果:
  - 在�?�择�?1�?找到标签的后�?(儿子、孙子�?�重孙子...）中，找到满足�?�择�?2的标签，设置样式
- 注意�?:
  - 1.后代包括:儿子、孙子�?�重孙子......
  - 2.后代选择器中，�?�择器与选择器之前�?�过空格隔开

#### 1.2 子代选择�?:>

- 作用:根据HTML标签的嵌套关系，选择父元素子代中满足条件的元�?
- 选择器语�?:选择�?1>选择�?2 { css }
- 结果:
  - 在�?�择�?1�?找到标签的子�?(儿子)中，找到满足选择�?2的标签，设置样式
- 注意�?:
  - 1.子代只包�?:儿子
  - 2.子代选择器中，�?�择器与选择器之前�?�过 > 隔开

#### 1.3 并集选择�?: �?

- 作用:同时选择多组标签，设置相同的样式
- 选择器语�?:选择�?1 , 选择�?2{ css }
- 结果:
  - 找到选择�?1和�?�择�?2选中的标签，设置样式
- 注意�?:
  - 1.并集选择器中的每组�?�择器之间�?�过，分�?
  - 2.并集选择器中的每组�?�择器可以是基础选择器或者复合�?�择�?
  - 3.并集选择器中的每组�?�择器�?�常�?行写�?个，提高代码的可读�??

#### 1.4 交集选择�?

- 作用:选中页面中同时满足多个�?�择器的标签
- 选择器语法∶选择�?1选择�?2 { css }
- 结果:
  - (既又原则）找到页面中既能被�?�择�?1选中，又能被选择�?2选中的标签，设置样式
- 注意�?:
  - 1.交集选择器中的�?�择器之间是紧挨�?的，没有东西分隔
  - 2．交集�?�择器中如果有标签�?�择器，标签选择器必须写在最前面

```html
    <!-- 找到第一个p,带box类的, 设置文字颜色是红�? -->
    <p class="box box1" id="dilireba">这是p标签:box</p>
    <p class="box">这是p标签:box</p>
    <p>pppppp</p>
    <div class="box">这是div标签:box</div>
```

#### 1.5 hover伪类选择�?

- 作用: 选中鼠标悬停在元素上的状态，设置样式
- 选择器语�?: **选择�?:hover { css }**
- 注意�?:
  - 1.伪类选择器�?�中的元素的某种状�??



#### 1.6 emmet语法

- 作用:通过�?写语法，快�?�生成代�?
- 语法:
  - 类似于刚刚学习的选择器的写法

| 记忆       | 示例                | 效果                                   |
| ---------- | ------------------- | -------------------------------------- |
| 标签�?     | div                 | `<div></div>`                          |
| 类�?�择�?   | .red                | `<div class="red"></div>`              |
| id选择�?   | #one                | `<div id="one"></div>`                 |
| 交集选择�? | p.red#one           | `<p class="red" id="one"></p>`         |
| 子代选择�? | `<ul>li`            | `<ul><li></li></u1>`                   |
| 内部文本   | ul>li{我是li的内容} | `<ul><li>我是1i的内�?</1i></u1>`       |
| 创建多个   | ul>li*3             | `<ul><li></li><li></li><li></li></u1>` |

## 2.背景相关属�??

#### 2.1 背景颜色

- 属�?�名: background-color (bgc)
- 属�?��??:
  - 颜色取�??:关键字�?�rgb表示法�?�rgba表示法�?�十六进�?......
- 注意�?:
  - 背景颜色默认值是透明: rgba(0,0,0,0)�? transparent
  - 背景颜色不会影响盒子大小，并且还能看清盒子的大小和位置，�?般在布局中会习惯先给盒子设置背景颜色

#### 2.2 背景图片

- 属�?�名: background-image (bgi)
- 属�?��??: background-image: url('图片的路�?')
- 注意�?:
  - 背景图片中url中可以省略引�?
  - 背景图片默认是在水平和垂直方向平铺的
  - 背景图片仅仅是指给盒子起到装饰效果，类似于背景颜色，是不能撑�?盒子�?

#### 2.3 背景平铺

- 属�?�名: background-repeat (bgr)
- 属�?��??:

| 取�??      | 效果                         |
| --------- | ---------------------------- |
| repeat    | (默认�?)水平和垂直方向都平铺 |
| no-repeat | 不平�?                       |
| repeat-x  | 沿着水平方向(x轴）平铺       |
| repeat-y  | 沿着垂直方向(y�?)平铺        |

#### 2.4 背景位置

- 属�?�名: background-position (bgp)
- 属�?��??:background-position: 水平方向位置    垂直方向位置;

![image-20230224203657250](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302242037587.png)

- 注意�?:
  - 方位名词取�?�和坐标取�?�可以混使用，第�?个取值表示水平，第二个取值表示垂�?

#### 2.5 背景相关属�?�连�?

- 属�?�名 background (bg)
- 属�?��??:
  - 单个属�?��?�的合写，取值之间以空格隔开
- 书写顺序:
  - 推荐: background: color image repeat position
- 省略问题:
  - 可以按照�?求省�?
  - 特殊情况:在pc端，如果盒子大小和背景图片大小一样，此时可以直接写background: url()
- 注意�?
  - 如果�?要设置单独的样式和连�?
  - 要么把单独的样式写在连写的下�?
  - 要么把单独的样式写在连写的里�?

#### 2.6 (拓展)img标签和背景图片的区别

- �?�?:�?要在网页中展示一张图片的效果?
- 方法�?:直接写上img标签即可
  - img标签是一个标签，不设置宽高默认会以原尺寸显示
- 方法�?: div标签＋背景图�?
  - �?要设置div的宽高，因为背景图片只是装饰的CSS样式，不能撑�?div标签

## 3.元素显示模式

#### 3.1块级元素

- 显示特点:
  - 1.**独占�?行（�?行只能显示一�?)**
  - 2.宽度默认是父元素的宽度，高度默认由内容撑�?
  - 3.可以设置宽高
- 代表标签:
  - div、p�? h系列、ul、li、dl、dt、dd、form、header、nav、footer....

#### 3.2 行内元素

- 显示特点:
  - **�?行可以显示多�?**
  - **宽度和高度默认由内容撑开**
  - **不可以设置宽�?**
- 代表标签:
  - a、span . b、u、i�? s、strong、ins、em、del.....

#### 3.3 行内块元�?

- 显示特点:
  - 1.�?行可以显示多�?
  - 2.可以设置宽高
- 代表标签:
  - input、textarea、button、select......
  - 特殊情况:img标签有行内块元素特点，但是Chrome调试工具中显示结果是inline

#### 3.4 元素显示模式转换

- 目的:改变元素默认的显示特点，让元素符合布�?要求
- 语法:

| 属�??                  | 效果             | 使用频率 |
| --------------------- | ---------------- | -------- |
| display: block        | 转换成块级元�?   | 较多     |
| display :inline-block | 转换成行内块元素 | 较多     |
| display :inline       | 转换成行内元�?   | 极少     |

#### 3.5 HTML嵌套规范注意�?

- 块级元素�?般作为大容器，可以嵌�?:文本、块级元素�?�行内元素�?�行内块元素等等...
  - 但是:p标签中不要嵌套div、p、h等块级元�?
- a标签内部可以嵌套任意壳素
  - 但是: a标签不能嵌套a标签s

## 4.CSS特�??

####  4.1 继承�?

- 特�??:子元素有默认继承父元素样式的特点（子承父�?)
- 可以继承的常见属�?(文字控制属�?�都可以继承)
  - color
  - font-style、font-weight、font-size、font-family
  - text-indent、text-align
  - line-height
- 注意�?:
  - 可以通过调试工具判断样式是否可以继承

**(拓展）继承失效的特殊情况**

- 如果元素有浏览器默认样式，此时继承�?�依然存在，但是优先显示浏览器的默认样式
- a标签的color会继承失�?
  - 其实color属�?�继承下来了，但是被浏览器默认设置的样式给覆盖掉�?
- h系列标签的font-size会继承失�?
  - 其实font-size属�?�继承下来了，但是被浏览器默认设置的样式给覆盖掉�?

#### 4.2 层叠�?

- 特�??:
  - 给同�?个标签设置不同的样式→此时样式会层叠叠加→会共同作用在标签上
  - 给同�?个标签设置相同的样式→此时样式会层叠覆盖→最终写在最后的样式会生�?

- 注意�?:
  - 当样式冲突时，只有当选择器优先级相同时，才能通过层叠性判断结�?



## 5.综合案例
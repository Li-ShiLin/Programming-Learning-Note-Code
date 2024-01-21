<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.CSS特�?�](#1css%E7%89%B9%E6%80%A7)
    - [1.1 优先级](#11-%E4%BC%98%E5%85%88%E7%BA%A7)
    - [1.2**权重叠加计算**](#12%E6%9D%83%E9%87%8D%E5%8F%A0%E5%8A%A0%E8%AE%A1%E7%AE%97)
- [2.PxCook的基本使用](#2pxcook%E7%9A%84%E5%9F%BA%E6%9C%AC%E4%BD%BF%E7%94%A8)
- [3.盒子模型](#3%E7%9B%92%E5%AD%90%E6%A8%A1%E5%9E%8B)
    - [3.1 盒子模型概念](#31-%E7%9B%92%E5%AD%90%E6%A8%A1%E5%9E%8B%E6%A6%82%E5%BF%B5)
    - [3.2 内容的宽度和高度](#32-%E5%86%85%E5%AE%B9%E7%9A%84%E5%AE%BD%E5%BA%A6%E5%92%8C%E9%AB%98%E5%BA%A6)
    - [3.3 边框(border)](#33-%E8%BE%B9%E6%A1%86border)
      - [1.边框(border)-连写形式](#1%E8%BE%B9%E6%A1%86border-%E8%BF%9E%E5%86%99%E5%BD%A2%E5%BC%8F)
      - [2.边框(border)-单方向设置](#2%E8%BE%B9%E6%A1%86border-%E5%8D%95%E6%96%B9%E5%90%91%E8%AE%BE%E7%BD%AE)
      - [3.边框(border)-单个属�??(了解)](#3%E8%BE%B9%E6%A1%86border-%E5%8D%95%E4%B8%AA%E5%B1%9E%E6%80%A7%E4%BA%86%E8%A7%A3)
      - [4.案例�?](#4%E6%A1%88%E4%BE%8B%E4%B8%80)
      - [5.案例二](#5%E6%A1%88%E4%BE%8B%E4%BA%8C)
      - [6.案例三](#6%E6%A1%88%E4%BE%8B%E4%B8%89)
    - [3.4 内边�?( padding )](#34-%E5%86%85%E8%BE%B9%E8%B7%9D-padding-)
      - [3.4.1 内边�?(padding) -取�?�](#341-%E5%86%85%E8%BE%B9%E8%B7%9Dpadding--%E5%8F%96%E5%80%BC)
      - [3.4.2内边�?(padding) –单方向设置](#342%E5%86%85%E8%BE%B9%E8%B7%9Dpadding-%E5%8D%95%E6%96%B9%E5%90%91%E8%AE%BE%E7%BD%AE)
      - [3.4.3 (拓展）不会撑大盒子的特殊情况](#343-%E6%8B%93%E5%B1%95%E4%B8%8D%E4%BC%9A%E6%92%91%E5%A4%A7%E7%9B%92%E5%AD%90%E7%9A%84%E7%89%B9%E6%AE%8A%E6%83%85%E5%86%B5)
      - [3.4.4 CSS3盒模�?(自动内减)](#344-css3%E7%9B%92%E6%A8%A1%E5%9E%8B%E8%87%AA%E5%8A%A8%E5%86%85%E5%87%8F)
    - [3.5 外边�?(margin)](#35-%E5%A4%96%E8%BE%B9%E8%B7%9Dmargin)
      - [3.5.1外边�?(margin)–取值](#351%E5%A4%96%E8%BE%B9%E8%B7%9Dmargin%E5%8F%96%E5%80%BC)
      - [3.5.2外边�?(margin) -单方向设置](#352%E5%A4%96%E8%BE%B9%E8%B7%9Dmargin--%E5%8D%95%E6%96%B9%E5%90%91%E8%AE%BE%E7%BD%AE)
      - [3.5.3 margin单方向设置的应用](#353-margin%E5%8D%95%E6%96%B9%E5%90%91%E8%AE%BE%E7%BD%AE%E7%9A%84%E5%BA%94%E7%94%A8)
      - [3.5.4 清除默认内外边距](#354-%E6%B8%85%E9%99%A4%E9%BB%98%E8%AE%A4%E5%86%85%E5%A4%96%E8%BE%B9%E8%B7%9D)
      - [3.5.5 外边距折叠现象�?�①合并现象](#355-%E5%A4%96%E8%BE%B9%E8%B7%9D%E6%8A%98%E5%8F%A0%E7%8E%B0%E8%B1%A1%E2%91%A0%E5%90%88%E5%B9%B6%E7%8E%B0%E8%B1%A1)
      - [3.5.6 外边距折叠现象一塌陷现象](#356-%E5%A4%96%E8%BE%B9%E8%B7%9D%E6%8A%98%E5%8F%A0%E7%8E%B0%E8%B1%A1%E4%B8%80%E5%A1%8C%E9%99%B7%E7%8E%B0%E8%B1%A1)
      - [3.5.7 行内元素的margin和padding无效情况](#357-%E8%A1%8C%E5%86%85%E5%85%83%E7%B4%A0%E7%9A%84margin%E5%92%8Cpadding%E6%97%A0%E6%95%88%E6%83%85%E5%86%B5)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.CSS特�??

**CSS三大特�??**

- 1.继承�?
- 2.层叠�?
- 3.优先�?

#### 1.1 优先�?

- 特�??:不同选择器具有不同的优先级，优先级高的�?�择器样式会覆盖优先级低选择器样�?
- 优先级公�?:
  - 继承<通配符�?�择�?<标签选择�?<类�?�择�?<id选择�?<行内样式< !important
- 注意�?:
  - !important写在属�?��?�的后面，分号的前面!
  - !important不能提升继承的优先级，只要是继承优先级最�?
  - 实际�?发中不建议使�?!important 

#### 1.2**权重叠加计算**

- 场景:如果是复合�?�择器，此时�?要�?�过权重叠加计算方法，判断最终哪个�?�择器优先级�?高会生效
- 权重叠加计算公式:(每一级之间不存在进位)

![image-20230224235731484](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302242357684.png)

- 比较规则:
  - 1.先比较第�?级数字，如果比较出来了，之后的统统不�?
  - 2.如果第一级数字相同，此时再去比较第二级数字，如果比较出来了，之后的统统不�?
  - 3.如果�?终所有数字都相同，表示优先级相同，则比较层叠�?(谁写在下面，谁说了算!
- 注意�?: !important如果不是继承，则权重�?高，天下第一!

## 2.PxCook的基本使�?



## 3.盒子模型

#### 3.1 盒子模型概念

- 页面中的每一个标签，都可看做是一个�?�盒子�?�，通过盒子的视角更方便的进行布�?
- 浏览器在渲染(显示）网页时，会将网页中的元素看做是�?个个的矩形区域，我们也形象的称之为盒�?
- 盒子模型
  - CSS 中规定每个盒子分别由:内容区域(content)、内边距区域(padding)、边框区�?(border)、外边距区域（margin)构成，这就是盒子模型
  - 记忆:可以联想现实中的包装�?

![image-20230225100338434](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251003453.png)

#### 3.2 内容的宽度和高度

- 作用:利用width和height属�?�默认设置是盒子内容区域的大�?
- 属�??: width / height
- 常见取�??:数字+px

![image-20230225101029160](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251010631.png)

#### 3.3 边框(border)

##### 1.边框(border)-连写形式

- 属�?�名: border
- 属�?��??:单个取�?�的连写，取值之间以空格隔开
  - �?: border : 10px solid red;
- 快捷�?: bd + tab

##### 2.边框(border)-单方向设�?

- 场景:只给盒子的某个方向单独设置边�?
- 属�?�名: border-方位名词
- 属�?��??:连写的取�?

```html
<style>
        div {
            width: 200px;
            height: 200px;
            background-color: pink;
            /* border: 粗细  线条样式   颜色 -- 不分先后顺序 */
            /* solid : 实线 */
            /* border: 1px solid #000; */

            /* dashed: 虚线 */
            /* border: 5px dashed #000; */

            /* dotted : 点线 */
            /* border: 5px dotted #000; */

            border-left: 5px dotted #000;
            border-right: 5px dotted #000;
            border-top: 1px solid red;
            border-bottom: 1px solid red;
        }
    </style>
```

##### 3.边框(border)-单个属�??(了解)

- 作用: 设置边框粗细、边框样式�?�边框颜色效�?
- 单个属�??:

| 作用     | 属�?�名       | 属�?��??                             |
| -------- | ------------ | ---------------------------------- |
| 边框粗细 | border-width | 数字+px                            |
| 边框样式 | border-style | 实线solid、虚线dashed 、点线dotted |
| 边框颜色 | border-color | 颜色取�??                           |

##### 4.案例�?

![image-20230225102346680](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251023349.png)

##### 5.案例�?

![image-20230225102735067](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251027412.png)



##### 6.案例�?

![image-20230225103132761](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251031425.png)

#### 3.4 内边�?( padding )

##### 3.4.1 内边�?(padding) -取�??
- 作用:设置边框与内容区域之间的距离
- 属�?�名: padding
- 常见取�??:

| 取�??   | 示例                          | 含义                                                   |
| ------ | ----------------------------- | ------------------------------------------------------ |
| �?个�?? | padding: 10px;                | 上右下左都设置为10px                                   |
| 两个�? | padding: 10px 20px;           | 上下设置�?10px、左右设置为20px                         |
| 三个�? | padding: 10px 20px 30px;      | 上设置为10px、左右设置为20px、下设置�?30px             |
| 四个�? | padding: 10px 20px 30px 40px; | 上设置为10px、右设置�?20px、下设置�?30px、左设置�?40px |

- 
  记忆规则:从上�?始赋值，然后顺时针赋值，如果设置赋�?�的，看对面�?!!



![image-20230225110456478](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251117760.png)



![image-20230225110618819](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251118043.png)



##### 3.4.2内边�?(padding) –单方向设置

- 场景:只给盒子的某个方向单独设置内边距
- 属�?�名: padding -方位名词
- 属�?��??:数字＋px

##### 3.4.3 (拓展）不会撑大盒子的特殊情况

- 不会撑大盒子的特殊情况（块级元素)
  - 1．如果子盒子没有设置宽度，此时宽度默认是父盒子的宽度
  - 2.此时给子盒子设置左右的padding或�?�左右的border，此时不会撑大子盒子

##### 3.4.4 CSS3盒模�?(自动内减)

- �?�?:盒子尺寸300*300，背景粉色，边框10px实线黑色，上下左�?20px的内边距，如何完�??
  - 给盒子设置border或padding时，盒子会被撑大，如果不想盒子被撑大?
- 解决方法①∶手动内减
  - 操作:自己计算多余大小，手动在内容中减�?
  - 缺点:项目中计算量太大，很麻烦
- 解决方法②∶自动内减
  - **操作:给盒子设置属性box-sizing : border-box ;即可**
  - 优点:浏览器会自动计算多余大小，自动在内容中减�?

#### 3.5 外边�?(margin)

##### 3.5.1外边�?(margin)–取�?

- 作用:设置边框以外，盒子与盒子之间的距�?
- 属�?�名: margin
- 常见取�??:

| 取�??   | 示例                         | 含义                                                   |
| ------ | ---------------------------- | ------------------------------------------------------ |
| �?个�?? | margin: 10px;                | 上右下左都设置为10px                                   |
| 两个�? | margin: 10px 20px;           | 上下设置�?10px、左右设置为20px                         |
| 三个�? | margin: 10px 20px 30px;      | 上设置为10px、左右设置为20px、下设置�?30px             |
| 四个�? | margin: 10px 20px 30px 40px; | 上设置为10px、右设置�?20px、下设置�?30px、左设置�?40px |

- 
  记忆规则:从上�?始赋值，然后顺时针赋值，如果设置赋�?�的，看对面�?!!

##### 3.5.2外边�?(margin) -单方向设�?

- 场景:只给盒子的某个方向单独设置外边距
- 属�?�名: margin -方位名词
- 属�?��??:数字+px

##### 3.5.3 margin单方向设置的应用

应用:

| 方向     | 属�??          | 效果                 |
| -------- | ------------- | -------------------- |
| 水平方向 | margin-left   | 让当前盒子往右移�?   |
| 水平方向 | margin-right  | 让右边的盒子�?右移�? |
| 垂直方向 | margin-top    | �?当前盒子�?下移�?   |
| 垂直方向 | margin-bottom | 让下面的盒子�?下移�? |

##### 3.5.4 清除默认内外边距

- 场景:浏览器会默认给部分标签设置默认的margin和padding，但�?般在项目�?始前�?要先清除这些标签默认的margin和padding，后续自己设�?
  - 比如: body标签默认有margin: 8px
  - 比如: p标签默认有上下的margin
  - 比如: ul标签默认由上下的margin和padding-left
- 解决方法:

![image-20230225113620931](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251136288.png)



##### 3.5.5 外边距折叠现象�?�①合并现象

- 场景:**垂直布局的块级元�?**，上下的margin会合�?
- 结果: **�?终两者距离为margin的最大�??**
- 解决方法:避免就好
  - 只给其中�?个盒子设置margin即可

##### 3.5.6 外边距折叠现象一塌陷现象

- 场景互相嵌套的块级元素，子元素的 margin-top 会作用在父元素上
- 结果:导致父元素一起往下移�?
- 解决方法:
  - 1.给父元素设置border-top或�?�padding-top(分隔父子元素的margin-top)
  - 2.给父元素设置overflow: hidden
  - 3.转换成行内块元素
  - 4.设置浮动

##### 3.5.7 行内元素的margin和padding无效情况

- 场景:给行内元素设置margin和padding�?
  - 水平方向的margin和padding布局中有�?!
  - 垂直方向的margin和padding布局中无�?!
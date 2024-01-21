<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

    - [1.1 CSS的介绍](#11-css%E7%9A%84%E4%BB%8B%E7%BB%8D)
    - [1.2 CSS引入方式](#12-css%E5%BC%95%E5%85%A5%E6%96%B9%E5%BC%8F)
- [2.基础选择器](#2%E5%9F%BA%E7%A1%80%E9%80%89%E6%8B%A9%E5%99%A8)
    - [2.1 标签选择器](#21-%E6%A0%87%E7%AD%BE%E9%80%89%E6%8B%A9%E5%99%A8)
    - [2.2 类选择器](#22-%E7%B1%BB%E9%80%89%E6%8B%A9%E5%99%A8)
    - [2.3 id选择器](#23-id%E9%80%89%E6%8B%A9%E5%99%A8)
    - [2.4 通配符选择器](#24-%E9%80%9A%E9%85%8D%E7%AC%A6%E9%80%89%E6%8B%A9%E5%99%A8)
- [3.字体和文本样式](#3%E5%AD%97%E4%BD%93%E5%92%8C%E6%96%87%E6%9C%AC%E6%A0%B7%E5%BC%8F)
    - [3.1 字体样式](#31-%E5%AD%97%E4%BD%93%E6%A0%B7%E5%BC%8F)
      - [1. 字体大小: font-size](#1-%E5%AD%97%E4%BD%93%E5%A4%A7%E5%B0%8F-font-size)
      - [2.字体粗细:font-weight](#2%E5%AD%97%E4%BD%93%E7%B2%97%E7%BB%86font-weight)
      - [3.字体样式: font-style](#3%E5%AD%97%E4%BD%93%E6%A0%B7%E5%BC%8F-font-style)
      - [4.字体类型: font-family](#4%E5%AD%97%E4%BD%93%E7%B1%BB%E5%9E%8B-font-family)
      - [5.常见字体系列（了解)](#5%E5%B8%B8%E8%A7%81%E5%AD%97%E4%BD%93%E7%B3%BB%E5%88%97%E4%BA%86%E8%A7%A3)
      - [6.样式的层叠问题](#6%E6%A0%B7%E5%BC%8F%E7%9A%84%E5%B1%82%E5%8F%A0%E9%97%AE%E9%A2%98)
      - [7.字体font相关属性的连写](#7%E5%AD%97%E4%BD%93font%E7%9B%B8%E5%85%B3%E5%B1%9E%E6%80%A7%E7%9A%84%E8%BF%9E%E5%86%99)
    - [3.2 文本样式](#32-%E6%96%87%E6%9C%AC%E6%A0%B7%E5%BC%8F)
      - [1.文本缩进: text-indent](#1%E6%96%87%E6%9C%AC%E7%BC%A9%E8%BF%9B-text-indent)
      - [2.文本水平对齐方式: text-align](#2%E6%96%87%E6%9C%AC%E6%B0%B4%E5%B9%B3%E5%AF%B9%E9%BD%90%E6%96%B9%E5%BC%8F-text-align)
      - [3.文本修饰: text-decoration](#3%E6%96%87%E6%9C%AC%E4%BF%AE%E9%A5%B0-text-decoration)
    - [3.3 行高](#33-%E8%A1%8C%E9%AB%98)
    - [3.4 拓展颜色常见取值](#34-%E6%8B%93%E5%B1%95%E9%A2%9C%E8%89%B2%E5%B8%B8%E8%A7%81%E5%8F%96%E5%80%BC)
    - [3.5 标签水平居中](#35-%E6%A0%87%E7%AD%BE%E6%B0%B4%E5%B9%B3%E5%B1%85%E4%B8%AD)
- [4. Chrome调试工具](#4-chrome%E8%B0%83%E8%AF%95%E5%B7%A5%E5%85%B7)
- [5.综合案例](#5%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

1.CSS的介绍 

#### 1.1 CSS的介绍 

- css:层叠样式表(Cascading style sheets)
- CSS作用是什么:表现:css(决定了样式美观)

- CSS语法规则
  - css写在style标签中,style标签一般写在head标签里面，title标签下面

![image-20230223222411520](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302232224712.png)

####  1.2 CSS引入方式

- 内嵌式:CSS 写在style标签中
  - 提示: style标签虽然可以写在页面任意位置，但是通常约定写在head标签中
- 外联式: CSS 写在一个单独的.css文件中
  - 提示: 需要通过link标签在网页中引入
- 行内式: CSS写在标签的style属性中
  - `<div style="color: green; font-size: 30px;">这是div标签</div>`
  - 提示: 基础班不推荐使用，之后会配合js使用



## 2.基础选择器
#### 2.1 标签选择器

- 结构:**标签名**{ css属性名:属性值;}
- 作用:通过标签名，找到页面中所有这类标签，设置样式

- 注意点:
  - 标签选择器选择的是一类标签，而不是单独某一个
  - 标签选择器无论嵌套关系有多深，都能找到对应的标签

#### 2.2 类选择器

- 结构:  **.类名**{ css属性名:属性值;}
- 作用:通过类名，找到页面中所有带有这个类名的标签，设置样式
- 注意点:
  - 所有标签上都有class属性，class属性的属性值称为类名(类似于名字)
  - 类名可以由数字、字母、下划线、中划线组成，但不能以数字或者中划线开头
  - 一个标签可以同时有多个类名，类名之间以空格隔开
  - 类名可以重复，一个类选择器可以同时选中多个标签

![image-20230223225900769](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302232259149.png)



#### 2.3 id选择器

- 结构: **#id属性值**{ css属性名:属性值;}
- 作用:通过id属性值，找到页面中带有这个id属性值的标签，设置样式
- 注意点:
  - 所有标签上都有id属性
  - id属性值类似于身份证号码，在一个页面中是唯一的，不可重复的!
  - 一个标签上只能有一个id属性值
  - 一个id选择器只能选中一个标签

![image-20230223230541328](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302232305148.png)

#### 2.4 通配符选择器

- 结构:  ***{ css属性名∶属性值;}**
- 作用:找到页面中所有的标签，设置样式
- 注意点:
  - 开发中使用极少，只会在极特殊情况下才会用到
  - 在基础班小页面中可能会用于去除标签默认的margin和padding(后续讲解)

![image-20230223231153404](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302232311316.png)

## 3.字体和文本样式

#### 3.1 字体样式

##### 1. 字体大小: font-size

- 属性名: font-size
- 取值:数字＋px
- 注意点:
  - 谷歌浏览器默认文字大小是16px单位需要设置，否则无效

##### 2.字体粗细:font-weight

- 属性名:font-weight
- 取值:
  - 关键字:

| 正常 | normal |
| ---- | ------ |
| 加粗 | bold   |

- 纯数字:100~900的整百数:

| 正常 | 400  |
| ---- | ---- |
| 加粗 | 700  |

- 注意点:

  - 不是所有字体都提供了九种粗细，因此部分取值页面中无变化

  - 实际开发中以: 正常、加粗两种取值使用最多

##### 3.字体样式: font-style

- 属性名:font-style
- 取值:
  - 正常（默认值): normal
  - 倾斜:italic

##### 4.字体类型: font-family

- 常见取值:具体字体1,具体字体2,具体字体3,具体字体4,字体系列
  - 具体字体:"Microsoft YaHei"、微软雅黑、黑体、宋体、楷体等.......
  - 字体系列: sans-serif、serif、monospace等.......
- 渲染规则:
  - 从左往右按照顺序查找，如果电脑中未安装该字体，则显示下一个字体
  - 如果都不支持，此时会根据操作系统，显示最后字体系列的默认字体
- 注意点:
  - 如果字体名称中存在多个单词，推荐使用引号包裹
  - 最后一项字体系列不需要引号包裹
  - 网页开发时，尽量使用系统常见自带字体，保证不同用户浏览网页都可以正确显示

| 系统    | 默认字体 |
| ------- | -------- |
| Windows | 微软雅黑 |
| macos   | 苹方     |

##### 5.常见字体系列（了解)

- 无衬线字体(sans-serif)
  - 1.特点:文字笔画粗细均匀，并且首尾无装饰
  - 2.场景:网页中大多采用无衬线字体
  - 3.常见该系列字体:黑体、Arial
- 衬线字体(serif)
  - 1．特点:文字笔画粗细不均，并且首尾有笔锋装饰
  - 2.场景:报刊书籍中应用广泛
  - 3．常见该系列字体:宋体、Times New Roman
- 等宽字体(monospace)
  - 1.特点:每个字母或文字的宽度相等
  - 2.场景:一般用于程序代码编写，有利于代码的阅读和编写
  - 3.常见该系列字体:Consolas、fira code

##### 6.样式的层叠问题

- 问题:
  - 给同一个标签设置了相同的样式，此时浏览器会如何渲染呢?
- 结果:
  - 如果给同一个标签设置了相同的属性，此时样式会层叠（覆盖)，写在最下面的会s生效
- TIP:
  - css (Cascading style sheets)层叠样式表
  - 所谓的层叠即叠加的意思，表示样式可以一层一层的层叠覆盖

##### 7.字体font相关属性的连写

- 属性名:font(复合属性)
- 取值:
  - font : style weight size family;
- 省略要求:
  - 只能省略前两个，如果省略了相当于设置了默认值
- 注意点:如果需要同时设置单独和连写形式
  - 要么把单独的样式写在连写的下面
  - 要么把单独的样式写在连写的里面

####  3.2 文本样式

##### 1.文本缩进: text-indent

- 属性名: text-indent
- 取值:
  - 数字+px
  - 数字+em(推荐: 1em =当前标签的font-size的大小)

##### 2.文本水平对齐方式: text-align

- 属性名: text-align
- 取值:

| 属性值 | 效果     |
| ------ | -------- |
| left   | 左对齐   |
| center | 居中对齐 |
| right  | 右对齐   |

- 注意点:
  - 如果需要让文本水平居中，text-align属性给文本所在标签（文本的父元素）设置

**水平居中方法总结text-align : center**

- text-align : center 能让哪些元素水平居中?
  - 文本
  - span标签、a标签
  - input标签、img标签
- 注意点:
  - 如果需要让以上元素水平居中， text-align : center需要给以上元素的父元素设置
  - 例：给图片居中，则要为body设置text-align

![image-20230224002705246](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302240037558.png)

##### 3.文本修饰: text-decoration

- 属性名: text-decoration
- 取值:

| 属性值       | 效果             |
| ------------ | ---------------- |
| underline    | 下划线（常用)    |
| line-through | 删除线（不常用)  |
| overline     | 上划线(几乎不用) |
| none         | 无装饰线（常用)  |

- 注意点:
  - 开发中会使用text-decoration:none;  清除a标签默认的下划线

#### 3.3 行高

- 作用:控制一行的上下行间距
- 属性名: line-height
- 取值:
  - 数字+px
  - 倍数(当前标签font-size的倍数)
- 应用:
  - 让单行文本垂直居中可以设置line-height :文字父元素高度
  - 网页精准布局时， 会设置line-height: 1可以取消上下间距
- 行高与font连写的注意点:
  - 如果同时设置了行高和font连写，注意覆盖问题
  - font: style weight size/line-height family ;

![image-20230224003710613](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302240037457.png)

#### 3.4 拓展颜色常见取值

- 属性名:
  - 如:文字颜色: color
  - 如:背景颜色: background-color
- 属性值:

| 颜色表示方式   | 表示含义                                | 属性值                                          |
| -------------- | --------------------------------------- | ----------------------------------------------- |
| 关键词         | 预定义的颜色名                          | red、green、blue、yellow.                       |
| rgb表示法      | 红绿蓝三原色。每项取值范围:0~255        | rgb(0,0,0)、rgb(255,255,255)、rgb(255,0,0...... |
| rgba表示法     | 红绿蓝三原色+a表示透明度，取值范围是0~1 | rgba(255,255,255,0.5)、rgba(255,0,0,0.3).       |
| 十六进制表示法 | #开头，将数字转换成十六进制表示         | #000000、#ffo000、#e92322，简写:#000、#f00      |

#### 3.5 标签水平居中

**标签水平居中方法总结margin : 0 auto**

- 如果需要让div、p、h (大盒子)水平居中?
  - 可以通过margin : 0 auto;实现
- 注意点:
  - 如果需要让div、p、 h (大盒子)水平居中，直接给当前元素本身设置即可
  - margin: 0 auto一般针对于固定宽度的盒子，如果大盒子没有设置宽度，此时会默认占满父元素的宽度

## 4. Chrome调试工具



## 5.综合案例

综合案例一：

![image-20230224012643371](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302240126871.png)

综合案例二：

![image-20230224013742413](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302240137829.png)
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [项目实战](#%E9%A1%B9%E7%9B%AE%E5%AE%9E%E6%88%98)
- [1.样式补充](#1%E6%A0%B7%E5%BC%8F%E8%A1%A5%E5%85%85)
    - [1.1 精灵图](#11-%E7%B2%BE%E7%81%B5%E5%9B%BE)
      - [1.1.1 介绍](#111-%E4%BB%8B%E7%BB%8D)
      - [1.1.2 精灵图的使用步骤](#112-%E7%B2%BE%E7%81%B5%E5%9B%BE%E7%9A%84%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4)
    - [1.2 背景图片大小](#12-%E8%83%8C%E6%99%AF%E5%9B%BE%E7%89%87%E5%A4%A7%E5%B0%8F)
    - [1.3文字阴影](#13%E6%96%87%E5%AD%97%E9%98%B4%E5%BD%B1)
    - [1.4 盒子阴影](#14-%E7%9B%92%E5%AD%90%E9%98%B4%E5%BD%B1)
    - [1.5 过渡](#15-%E8%BF%87%E6%B8%A1)
- [2.项目前置知识补充](#2%E9%A1%B9%E7%9B%AE%E5%89%8D%E7%BD%AE%E7%9F%A5%E8%AF%86%E8%A1%A5%E5%85%85)
    - [2.1 网页与网站的关系](#21-%E7%BD%91%E9%A1%B5%E4%B8%8E%E7%BD%91%E7%AB%99%E7%9A%84%E5%85%B3%E7%B3%BB)
    - [2.2 骨架结构标签](#22-%E9%AA%A8%E6%9E%B6%E7%BB%93%E6%9E%84%E6%A0%87%E7%AD%BE)
      - [2.2.1 DOCTYPE文档说明](#221-doctype%E6%96%87%E6%A1%A3%E8%AF%B4%E6%98%8E)
      - [2.2.2 网页语言](#222-%E7%BD%91%E9%A1%B5%E8%AF%AD%E8%A8%80)
      - [2.2.3 字符编码（了解)](#223-%E5%AD%97%E7%AC%A6%E7%BC%96%E7%A0%81%E4%BA%86%E8%A7%A3)
    - [2.3 SEO三大标签](#23-seo%E4%B8%89%E5%A4%A7%E6%A0%87%E7%AD%BE)
      - [2.3.1 SEO简介](#231-seo%E7%AE%80%E4%BB%8B)
      - [2.3.2 SEO三大标签](#232-seo%E4%B8%89%E5%A4%A7%E6%A0%87%E7%AD%BE)
    - [2.4 有语义的布局标签](#24-%E6%9C%89%E8%AF%AD%E4%B9%89%E7%9A%84%E5%B8%83%E5%B1%80%E6%A0%87%E7%AD%BE)
    - [2.5 ico图标设置](#25-ico%E5%9B%BE%E6%A0%87%E8%AE%BE%E7%BD%AE)
    - [2.6 版心](#26-%E7%89%88%E5%BF%83)
- [3.CSS书写顺序](#3css%E4%B9%A6%E5%86%99%E9%A1%BA%E5%BA%8F)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 项目实战

## 1.样式补充

#### 1.1 精灵图

##### 1.1.1 介绍

- 场景:项目中将多张小图片，合并成一张大图片，这张大图片称之为精灵图
- 优点:减少服务器发送次数，减轻服务器的压力，提高页面加载速度

- 例如:需要在网页中展示8张小图片

##### 1.1.2 精灵图的使用步骤

- 1.创建一个盒子,设置盒子的尺寸和小图尺寸相同
- 2.将精灵图设置为盒子的背景图片
- 3.修改背景图位置
  - 通过PxCook测量小图片左上角坐标，分别取负值设置给盒子的background-position: x y

#### 1.2 背景图片大小

- 作用:设置背景图片的大小，
- 语法: background-size:宽度高度;
- 取值:

| 取值    | 场景                                                     |
| ------- | -------------------------------------------------------- |
| 数字+px | 简单方便，常用                                           |
| 百分比  | 相对于当前盒子自身的宽高百分比                           |
| contain | 包含，将背景图片等比例缩放，直到不会超出盒子的最大       |
| cover   | 覆盖，将背景图片等比例缩放，直到刚好填满整个盒子没有空白 |

- background连写拓展
- 完整连写

![image-20230226213730180](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262138584.png)

- 注意点:
  - background-size和background连写同时设置时，需要注意覆盖问题
- 解决:
  - 1.要么单独的样式写连写的下面
  - 2.要么单独的样式写在连写的里面

#### 1.3文字阴影

- 作用:给文字添加阴影效果，吸引用户注意
- 属性名: text-shadow
- 取值:

| 参数     | 作用                       |
| -------- | -------------------------- |
| h-shadow | 必须，水平偏移量。允许负值 |
| v-shadow | 必须，垂直偏移量。允许负值 |
| blur     | 可选，模糊度               |
| color    | 可选，阴影颜色             |

- 拓展:
- 阴影可以叠加设置，每组阴影取值之间以逗号隔开

#### 1.4 盒子阴影

- 作用:给盒子添加阴影效果，吸引用户注意，体现页面的制作细节
- 属性名: box-shadow
- 取值:

| 参数     | 作用                       |
| -------- | -------------------------- |
| h-shadow | 必须，水平偏移量。允许负值 |
| v-shadow | 必须，垂直偏移量。允许负值 |
| blur     | 可选，模糊度               |
| spread   | 可选，阴影扩大             |
| color    | 可选，阴影颜色             |
| inset    | 可选，将阴影改为内部阴影   |

#### 1.5 过渡

- 作用:让元素的样式慢慢的变化，常配合hover使用，增强网页交互体验
- 属性名:transition
- 常见取值:

| 参数       | 取值                                                         |
| ---------- | ------------------------------------------------------------ |
| 过渡的属性 | all:所有能过渡的属性都过渡、(具体属性名如: width:只有width有过渡 |
| 过渡的时长 | 数字+s(秒)                                                   |

- 注意点:
- 1.过渡需要∶默认状态和hover状态样式不同，才能有过渡效果
- 2.transition属性给需要过渡的元素本身加
- 3.transition属性设置在不同状态中，效果不同的
  - 给默认状态设置，鼠标移入移出都有过渡效果
  - 给hover状态设置，鼠标移入有过渡效果，移出没有过渡效果

## 2.项目前置知识补充

#### 2.1 网页与网站的关系

- 网页:相当于是每页纸
- 网站:相当于一本书籍
  - 用户翻阅的时候，看的是每页纸上的内容
  - 由多页纸整合在一起，就是完整的一本书籍了

#### 2.2 骨架结构标签

##### 2.2.1 DOCTYPE文档说明

- `<!DOCTYPE html>`文档类型声明，告诉浏览器该网页的HTML版本
- ``注意点:DOCTYPE需要写在页面的第一行，不属于HTML标签`

![image-20230226222558375](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262225953.png)

##### 2.2.2 网页语言

- `<html lang="en">`标识网页使用的语言
- 作用:搜索引擎归类＋浏览器翻译
- 常见语言:zh-CN简体中文 / en英文

##### 2.2.3 字符编码（了解)

- `<meta charset="UTF-8">`识网页使用的字符编码
- 作用︰保存和打开的字符编码需要统一设置，否则可能会出现乱码
- 常见字符编码:
  - 1.UTF-8:万国码，国际化的字符编码，收录了全球语言的文字
  - 2.GB2312: 6000+汉字
  - 3.GBK: 20000+汉字
- 注意点∶开发中统一使用UTF-8字符编码即可

#### 2.3 SEO三大标签

##### 2.3.1 SEO简介
- SEO ( Search Engine Optimization) :搜索引擎优化
- 作用:让网站在搜索引擎上的排名靠前
- 提升SEO的常见方法:
  - 竞价排名
  - 将网页制作成html后缀
  - 标签语义化（在合适的地方使用合适的标签)

##### 2.3.2 SEO三大标签

- title:网页标题标签
- description:网页描述标签
- keywords: 网页关键词标签

![image-20230226223235809](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262232063.png)





#### 2.4 有语义的布局标签

- 场景:在HTML5新版本中，推出了一些有语义的布局标签，可以增强网页的语义化
- 标签:

| 标签名  | 语义       |
| ------- | ---------- |
| header  | 网页头部   |
| nav     | 网页导航   |
| footer  | 网页底部   |
| aside   | 网页侧边栏 |
| section | 网页区块   |
| article | 网页文章   |

- 注意点:
- 以上标签显示特点和div一致，但是比div多了不同的语义

#### 2.5 ico图标设置

- 场景:显示在标签页标题左侧的小图标，习惯使用.ico格式的图标

![image-20230226223653586](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262236786.png)

![image-20230226223711532](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262237715.png)

#### 2.6 版心

- 场景:把页面的主体内容约束在网页中间
- 作用:让不同大小的屏幕都能看到页面的主体内容
- 代码:

![image-20230226223807128](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262238061.png)

- 注意点:
- 版心类名常用:container、wrapper、w等

##  3.CSS书写顺序

- 衡量程序员的能力，除了要看实现业务需求的能力，还要看平时书写代码的规范（专业性)
- 不同的CSS书写顺序会影响浏览器的渲染性能，推荐前端工程师使用专业的书写
- 顺序习惯

| 顺序 | 类别          | 属性                                                         |
| ---- | ------------- | ------------------------------------------------------------ |
| 1    | 布局属性      | display 、position 、float 、 clear 、visibility 、 overflow |
| 2    | 盒子模型+背景 | width、 height 、 margin 、padding 、 border 、 background   |
| 3    | 文本内容属性  | color、font 、text-decoration 、text-align 、line-height     |
| 4    | 点缀属性      | cursor、border-radius 、text-shadow 、 box-shadow            |

- 注意点:
- 开发中推荐多用类＋后代，但不是层级越多越好，一个选择器中的类选择器的个数推荐不要超过3个
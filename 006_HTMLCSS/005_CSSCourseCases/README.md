## 1.CSS特性

**CSS三大特性**

- 1.继承性
- 2.层叠性
- 3.优先级

#### 1.1 优先级

- 特性:不同选择器具有不同的优先级，优先级高的选择器样式会覆盖优先级低选择器样式
- 优先级公式:
  - 继承<通配符选择器<标签选择器<类选择器<id选择器<行内样式< !important
- 注意点:
  - !important写在属性值的后面，分号的前面!
  - !important不能提升继承的优先级，只要是继承优先级最低
  - 实际开发中不建议使用!important 

#### 1.2**权重叠加计算**

- 场景:如果是复合选择器，此时需要通过权重叠加计算方法，判断最终哪个选择器优先级最高会生效
- 权重叠加计算公式:(每一级之间不存在进位)

![image-20230224235731484](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302242357684.png)

- 比较规则:
  - 1.先比较第一级数字，如果比较出来了，之后的统统不看
  - 2.如果第一级数字相同，此时再去比较第二级数字，如果比较出来了，之后的统统不看
  - 3.如果最终所有数字都相同，表示优先级相同，则比较层叠性(谁写在下面，谁说了算!
- 注意点: !important如果不是继承，则权重最高，天下第一!

## 2.PxCook的基本使用



## 3.盒子模型

#### 3.1 盒子模型概念

- 页面中的每一个标签，都可看做是一个“盒子”，通过盒子的视角更方便的进行布局
- 浏览器在渲染(显示）网页时，会将网页中的元素看做是一个个的矩形区域，我们也形象的称之为盒子
- 盒子模型
  - CSS 中规定每个盒子分别由:内容区域(content)、内边距区域(padding)、边框区域(border)、外边距区域（margin)构成，这就是盒子模型
  - 记忆:可以联想现实中的包装盒

![image-20230225100338434](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251003453.png)

#### 3.2 内容的宽度和高度

- 作用:利用width和height属性默认设置是盒子内容区域的大小
- 属性: width / height
- 常见取值:数字+px

![image-20230225101029160](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251010631.png)

#### 3.3 边框(border)

##### 1.边框(border)-连写形式

- 属性名: border
- 属性值:单个取值的连写，取值之间以空格隔开
  - 如: border : 10px solid red;
- 快捷键: bd + tab

##### 2.边框(border)-单方向设置

- 场景:只给盒子的某个方向单独设置边框
- 属性名: border-方位名词
- 属性值:连写的取值

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

##### 3.边框(border)-单个属性(了解)

- 作用: 设置边框粗细、边框样式、边框颜色效果
- 单个属性:

| 作用     | 属性名       | 属性值                             |
| -------- | ------------ | ---------------------------------- |
| 边框粗细 | border-width | 数字+px                            |
| 边框样式 | border-style | 实线solid、虚线dashed 、点线dotted |
| 边框颜色 | border-color | 颜色取值                           |

##### 4.案例一

![image-20230225102346680](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251023349.png)

##### 5.案例二

![image-20230225102735067](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251027412.png)



##### 6.案例三

![image-20230225103132761](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251031425.png)

#### 3.4 内边距( padding )

##### 3.4.1 内边距(padding) -取值
- 作用:设置边框与内容区域之间的距离
- 属性名: padding
- 常见取值:

| 取值   | 示例                          | 含义                                                   |
| ------ | ----------------------------- | ------------------------------------------------------ |
| 一个值 | padding: 10px;                | 上右下左都设置为10px                                   |
| 两个值 | padding: 10px 20px;           | 上下设置为10px、左右设置为20px                         |
| 三个值 | padding: 10px 20px 30px;      | 上设置为10px、左右设置为20px、下设置为30px             |
| 四个值 | padding: 10px 20px 30px 40px; | 上设置为10px、右设置为20px、下设置为30px、左设置为40px |

- 
  记忆规则:从上开始赋值，然后顺时针赋值，如果设置赋值的，看对面的!!



![image-20230225110456478](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251117760.png)



![image-20230225110618819](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251118043.png)



##### 3.4.2内边距(padding) –单方向设置

- 场景:只给盒子的某个方向单独设置内边距
- 属性名: padding -方位名词
- 属性值:数字＋px

##### 3.4.3 (拓展）不会撑大盒子的特殊情况

- 不会撑大盒子的特殊情况（块级元素)
  - 1．如果子盒子没有设置宽度，此时宽度默认是父盒子的宽度
  - 2.此时给子盒子设置左右的padding或者左右的border，此时不会撑大子盒子

##### 3.4.4 CSS3盒模型(自动内减)

- 需求:盒子尺寸300*300，背景粉色，边框10px实线黑色，上下左右20px的内边距，如何完成?
  - 给盒子设置border或padding时，盒子会被撑大，如果不想盒子被撑大?
- 解决方法①∶手动内减
  - 操作:自己计算多余大小，手动在内容中减去
  - 缺点:项目中计算量太大，很麻烦
- 解决方法②∶自动内减
  - **操作:给盒子设置属性box-sizing : border-box ;即可**
  - 优点:浏览器会自动计算多余大小，自动在内容中减去

#### 3.5 外边距(margin)

##### 3.5.1外边距(margin)–取值

- 作用:设置边框以外，盒子与盒子之间的距离
- 属性名: margin
- 常见取值:

| 取值   | 示例                         | 含义                                                   |
| ------ | ---------------------------- | ------------------------------------------------------ |
| 一个值 | margin: 10px;                | 上右下左都设置为10px                                   |
| 两个值 | margin: 10px 20px;           | 上下设置为10px、左右设置为20px                         |
| 三个值 | margin: 10px 20px 30px;      | 上设置为10px、左右设置为20px、下设置为30px             |
| 四个值 | margin: 10px 20px 30px 40px; | 上设置为10px、右设置为20px、下设置为30px、左设置为40px |

- 
  记忆规则:从上开始赋值，然后顺时针赋值，如果设置赋值的，看对面的!!

##### 3.5.2外边距(margin) -单方向设置

- 场景:只给盒子的某个方向单独设置外边距
- 属性名: margin -方位名词
- 属性值:数字+px

##### 3.5.3 margin单方向设置的应用

应用:

| 方向     | 属性          | 效果                 |
| -------- | ------------- | -------------------- |
| 水平方向 | margin-left   | 让当前盒子往右移动   |
| 水平方向 | margin-right  | 让右边的盒子往右移动 |
| 垂直方向 | margin-top    | 往当前盒子往下移动   |
| 垂直方向 | margin-bottom | 让下面的盒子往下移动 |

##### 3.5.4 清除默认内外边距

- 场景:浏览器会默认给部分标签设置默认的margin和padding，但一般在项目开始前需要先清除这些标签默认的margin和padding，后续自己设置
  - 比如: body标签默认有margin: 8px
  - 比如: p标签默认有上下的margin
  - 比如: ul标签默认由上下的margin和padding-left
- 解决方法:

![image-20230225113620931](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302251136288.png)



##### 3.5.5 外边距折叠现象–①合并现象

- 场景:**垂直布局的块级元素**，上下的margin会合并
- 结果: **最终两者距离为margin的最大值**
- 解决方法:避免就好
  - 只给其中一个盒子设置margin即可

##### 3.5.6 外边距折叠现象一塌陷现象

- 场景互相嵌套的块级元素，子元素的 margin-top 会作用在父元素上
- 结果:导致父元素一起往下移动
- 解决方法:
  - 1.给父元素设置border-top或者padding-top(分隔父子元素的margin-top)
  - 2.给父元素设置overflow: hidden
  - 3.转换成行内块元素
  - 4.设置浮动

##### 3.5.7 行内元素的margin和padding无效情况

- 场景:给行内元素设置margin和padding时
  - 水平方向的margin和padding布局中有效!
  - 垂直方向的margin和padding布局中无效!
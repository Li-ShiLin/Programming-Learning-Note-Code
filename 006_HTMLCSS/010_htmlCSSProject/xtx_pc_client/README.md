## 项目实战

## 1.项目结构搭建

#### 1.1 文件和目录准备

- 1.新建项目文件夹xtx-pc-client，在VScode中打开
  - 在实际开发中，项目文件夹不建议使用中文
  - 所有项目相关文件都保存在xtx-pc-client目录中
- 2.复制favicon.ico 到xtx-pc-client目录
  - 一般习惯将ico图标放在项目根目录
- 3.复制images和uploads目录到xtx-pc-client目录中
  - images∶存放网站固定使用的图片素材，如: logo、样式修饰图片...等
  - uploads:存放网站非固定使用的图片素材，如:商品图片、宣传图片...等
- 4.新建index.html在根目录
- 5.新建css文件夹保存网站的样式，并新建以下CSS文件:
  - base.css:基础公共样式
  - common.css:该网站中多个网页相同模块的重复样式，如:头部、底部
  - index.css:首页样式

![image-20230226233226885](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302262332006.png)



#### 1.2 基础公共样式

- 场景:一般项目开始前，首先会去除掉浏览器默认样式，设置为当前项目需要的初始化样式
- 作用:防止不同浏览器中标签默认样式不同的影响，统一不同浏览器的默认显示效果，方便后续项目开发
- 要求:
  - 已经准备好base.css代码，同学们需要认识，项目中可以直接引入使用

#### 1.3 index页面骨架

![image-20230228010155675](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202302280101056.png)
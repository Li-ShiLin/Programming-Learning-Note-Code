## 2.vue

### 2.1 MVVM思想

- M:即Model，模型，包括数据和一些基本操作
- v:即 view，视图，页面渲染结果
- VM:即 View-Model，模型与视图间的双向操作（无需开发人员干涉)



在MWM之前，开发人员从后端获取需要的数据模型，然后要通过DOM操作Model渲染到view中。而后当用户操作视图，我们还需要通过DOM获取View中的数据，然后同步到Model中

而MVVM中的VM要做的事情就是把DOM操作完全封装起来，开发人员不用再关心Model和View之间是如何互相影响的:

- 只要我们Model发生了改变，View 上自然就会表现出来
- 当用户修改了View，Model中的数据也会跟着改变。

把开发人员从繁琐的DOM操作中解放出来，把关注点放在如何操作 Model上

![image-20230320010040606](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303200100882.png)















五、Babel
Babel是一个 JavaScript 编译器，我们可以使用es 的最新语法编程，而不用担心浏览器兼容间题。他会自动转化为浏览器兼容的代码
l











四、Vue
1、MVM思想
M:即 Model，模型，包括数据和一些基本操作V:即 View，视图，页面渲染结果
VM:即 View-Model，模型与视图间的双向操作（无需开发人员干涉)
在MVM之前，开发人员从后端获取需要的数据模型，然后要通过DOM操作Model渲染到View中。而后当用户操作视图，我们还需要通过DOM获取View中的数据，然后同步到Model中。
而MVM中的VM要做的事情就是把DOM操作完全封装起来，开发人员不用再关心Model和View之间是如何互相影响的:







2.ES6





3.Node.js





4.Vue

5.Babel

6.Webpack













前端开发，少不了node.js; Node.js是一个基于Chrome v8引擎的 JavaScript运行环境。

http://nodejs.cn/api/

我们关注与node.js 的npm 功能就行;
NPM是随同NodeS一起安装的包管理工具，JavaScript-NPM，Java-Maven;

1)、官网下载安装node.js，并使用node-v检查版本
2）、配置npm，使用淘宝镜像

npm config set registry http://registry.mpm.taobao.org/







四、MMes
1、MVVM思想
M:即 Model，模型，包括数据和一些基本操作V:即 view，视图，页面渲染结果
VM:即 View-Model．模型与视图间的双向操作（无熏开发人员干洗)





五、Babel
Babel是一个 JavaScript 编译器，我们可以使用es 的最新语法编程，而不用担心浏览器兼容间题。他会自动转化为浏览器兼容的代码





六、Weppack
自动化项目构建工具。gulp也是同类产品


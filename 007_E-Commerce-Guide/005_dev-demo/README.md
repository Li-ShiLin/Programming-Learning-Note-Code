**目录导航：**

<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [1.vue模块化开发](#1vue)
    + [1.1 项目搭建](#11-)
    + [1.2 vue组件](#12-vue)
- [2. vue整合ElementUI](#2-vueelementui)

<!-- TOC end -->

<!-- TOC --><a name="1vue"></a>

# 1.vue模块化开发

<!-- TOC --><a name="11-"></a>

### 1.1 项目搭建

**1、全局安装webpack(在cmd窗口执行)：**

- npm install webpack -g
- Weppack :  自动化项目构建工具

**2、全局安装vue脚手架(在cmd窗口执行)：**

- npm install -g @vue/cli-init
- 最好用这个命令(上面这个可能不起作用)： npm install -g @vue/cli

**3、初始化vue项目：**

- vue脚手架使用 webpack模板初始化一个appname 项目
  - vue init webpack appname 

![image-20230326184825795](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303261852473.png)

练习：

![image-20230326201749491](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303262022344.png)

**4、启动vue项目**

- 项目的package.json中script，代表我们能运行的命令

- 启动项目:   npm start = npm run dev 



![image-20230326185034844](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303261852446.png)

项目启动过程中运行`npm run dev `出现报错`npm ERR! code ELIFECYCLE`：

![image-20230326190319549](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303261921065.png)

解决`npm ERR! code ELIFECYCLE`报错：

- 删除package-lock.json文件
- 然后，执行 npm install 指令重新安装下载依赖库
- 最后重新执行npm start就可以启动项目了

package-lock.json文件

![image-20230326191703575](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303262023696.png)



启动后访问`http://localhost:8080`

<!-- TOC --><a name="12-vue"></a>
### 1.2 vue组件

- 组件三要素：`template` 、`script`  、 `style`
- 编写一个自己的组件：创建文件`src\components\Hello.vue` :

```vue
<template>
    <div>
        <h1>你好，Hello,{{name}}</h1>
    </div>
</template>

<script>
export default{
    data(){
        return{
            name:"张三"
        }
    }
}
</script>

<style>
    
</style>
```

编写路由来访问刚才的组件：在`src\router\index.js`中添加路由:

```js
import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
// 导入hello组件
import hello from '@/components/hello'
// import { component } from 'vue/types/umd'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    // 添加路由来使用组件 Hello
    {
      path: '/hello',
      name: 'Hello',
      component: hello
    }
  ]
})

```



访问`http://localhost:8080/#/hello`，返回：

![image-20230326225118708](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303262343949.png)



<!-- TOC --><a name="2-vueelementui"></a>
# 2. vue整合ElementUI

- ElementUI官方文档：  `https://element.eleme.cn/#/zh-CN/component/installation`

- 安装ElementUI：

```
npm i element-ui
```

![image-20230326204952523](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303262050030.png)

**ElementUI使用：**

在 main.js 中写入以下内容：

```js
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
```

ElementUI中有很多组件，下面演示使用ElementUI的单选框组件

![image-20230326210856297](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303262343369.png)

**使用ElementUI的单选框组件：**

- 在`Hello.vue`中添加` ElementUI单选框组件`

```js
<template>
  <div>
    <h1>你好，Hello，{{ name }}</h1>

    <!-- ElementUI单选框组件 -->
    <el-radio v-model="radio" label="1">备选项1</el-radio>
    <el-radio v-model="radio" label="2">备选项2</el-radio>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "张三",
      radio: "2",
    };
  },
};
</script>

<style >
</style>
```



**定义table组件,展示页面**

`src\components\MyTable.vue`:

```vue
<template>
  <div>
    <el-table :data="tableData">
      <el-table-column prop="date" label="日期" width="140"></el-table-column>
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
    </el-table>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data() {
    //这里存放数据
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄"
    };
    return {
      tableData: Array(20).fill(item)
    };
  },
  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {},
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {} //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>

</style>
```

在`src\router\index.js`中引用MyTable组件：

```js
import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Hello from '@/components/Hello'
import MyTable from '@/components/MyTable'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/hello',
      name: "Hello",
      component: Hello
    },
    {
      path: '/table',
      name: 'MyTable',
      component: MyTable
    }
  ]
})
```

修改`src\App.vue`文件：

```vue
<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']" router="true">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>导航一
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="/table">用户列表</el-menu-item>
            <el-menu-item index="/hello">Hello</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>导航二
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>导航三
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="3-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>

      <el-main>
        <router-view></router-view>
        <!--  -->
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄"
    };
    return {
      tableData: Array(20).fill(item)
    };
  }
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

```

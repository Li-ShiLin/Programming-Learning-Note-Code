**目录导航：**

<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [2.商品服务-三级分类前端编写](#2-)
  * [2.1 树形展示三级分类数据](#21-)
  * [2.2 三级分类删除](#22-)
  * [2.3 三级分类新增](#23-)
  * [2.4  三级分类修改](#24-)
  * [2.5 三级分类拖拽功能](#25-)
    + [2.5.1 拖拽效果实现](#251-)
    + [2.5.2 拖拽数据收集](#252-)
    + [2.5.3 发送http post请求完成修改](#253-http-post)
    + [2.5.4 拖拽优化](#254-)
  * [2.6 节点批量删除](#26-)
- [3.商品服务-品牌管理](#3-)
  * [3.1 添加商品模块并优化效果](#31-)
  * [3.2 文件上传功能](#32-)
    + [3.2.1 SpringCloud Alibaba-OSS使用](#321-springcloud-alibaba-oss)
    + [3.2.2 建立第三方服务,实现服务端签名后直传](#322-)
    + [3.2.3 OSS前后端联调测试上传](#323-oss)
  * [3.3 前端表单校验](#33-)
    + [3.3.1 logo图片显示](#331-logo)
    + [3.3.2 前端表单校验实现](#332-)
  * [3.4 后端校验实现](#34-)
  * [3.5 统一的异常处理](#35-)
  * [3.6 分组校验](#36-)
  * [3.7 自定义注解](#37-)

<!-- TOC end -->

1.renren-fast-vue介绍

- renren-fast-vue基于vue、element-ui构建开发，实现[renren-fast](https://gitee.com/renrenio/renren-fast)后台管理前端功能，提供一套更优的前端解决方案
- 前后端分离，通过token进行数据交互，可独立部署
- 主题定制，通过scss变量统一一站式定制
- 动态菜单，通过菜单管理统一管理访问路由
- 数据切换，通过mock配置对接口数据／mock模拟数据进行切换
- 发布时，可动态配置CDN静态资源／切换新旧版本
- 演示地址：[http://demo.open.renren.io/renren-fast](http://demo.open.renren.io/renren-fast) (账号密码：admin/admin)

![输入图片说明](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040219010.png "01.png")
![输入图片说明](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040220943.png "02.png")

**说明文档**

项目开发、部署等说明都在[wiki](https://github.com/renrenio/renren-fast-vue/wiki)中。

**更新日志**

每个版本的详细更改都记录在[release notes](https://github.com/renrenio/renren-fast-vue/releases)中

<!-- TOC --><a name="2-"></a>
## 2.商品服务-三级分类前端编写

<!-- TOC --><a name="21-"></a>
### 2.1 树形展示三级分类数据

1. 启动renren-fast和renren-fast-vue: 启动`renren-fast-vue`前端项目以及对应的后端项目`renren-fast`

2. 登录进去，新增一级菜单`商品系统`

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040159816.png" > <b>登录前端项目，新增菜单</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040200475.png" > <b>新增菜单</b></td>
    </tr>
    </table>


3.添加完菜单后可以看到多出菜单目录模块，且数据库表`sys_menu`也出现新数据：

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040202486.png" > <b>成功添加商品系统模块</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040203893.png" > <b>数据库表sys_menu出现新数据</b></td>
    </tr>
    </table>


4.为商品系统添加菜单，同样可以看到数据库成功添加`分类维护`菜单

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040205075.png" > <b>添加菜单</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040207053.png" > <b>数据库表sys_menu出现新数据</b></td>
    </tr>
    </table>




![image-20230402030111960](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040211765.png)

在左侧点击【商品系统-分类维护】，希望在此展示3级分类。可以看到 url是`http://localhost:8001/#/product-category` ，填写的菜单路由是`product/category`。根据此系统的规范，此模块对应的视图是`src/view/modules/product/category.vue` ，接着创建`src/view/mudules/product/category.vue`文件，在category.vue中编写相关组件



![img](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040211765.png)



**自定义vue组件模板**

为方便编写vue组件，可以定义vue组件模板，步骤：File——>Preferences——>User Snippets(用户代码段)——>New Global Snippets file(新建全局代码片段)

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040214165.png" > <b>自定义模板</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040215508.png" > <b>创建模板文件</b></td>
    </tr>
    </table>


将下面的模板拷贝到全局模板文件中：

```vue
{
	"生成vue模板": {
		"prefix": "vue",
		"body": [
			"<template>",
			"<div></div>",
			"</template>",
			"",
			"<script>",
			"//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）",
			"//例如：import 《组件名称》 from '《组件路径》'",
			"",
			"export default {",
			"//import引入的组件需要注入到对象中才能使用",
			"components: {},",
			"props: {},",
			"data() {",
			"//这里存放数据",
			"return {};",
			"},",
			"//计算属性 类似于data概念",
			"computed: {},",
			"//监控data中的数据变化",
			"watch: {},",
			"//方法集合",
			"methods: {},",
			"//声明周期 - 创建完成（可以访问当前this实例）",
			"created() {},",
			"//声明周期 - 挂载完成（可以访问DOM元素）",
			"mounted() {},",
			"beforeCreate() {}, //生命周期 - 创建之前",
			"beforeMount() {}, //生命周期 - 挂载之前",
			"beforeUpdate() {}, //生命周期 - 更新之前",
			"updated() {}, //生命周期 - 更新之后",
			"beforeDestroy() {}, //生命周期 - 销毁之前",
			"destroyed() {}, //生命周期 - 销毁完成",
			"activated() {} //如果页面有keep-alive缓存功能，这个函数会触发",
			"};",
			"</script>",
			"<style scoped>",
			"</style>",
		]
	}
}
```



![image-20230404021745408](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040217030.png)

4.在创建组件时直接输入`vue`点击回车就可生成模板

![image-20230402121729581](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040218203.png)

重新运行项目时报错：

```sh
You may use special comments to disable some warnings.
Use // eslint-disable-next-line to ignore the next line.
Use /* eslint-disable */ to ignore all warnings in a file.
```

解决：到根目录下 config文件夹下的index.js文件，将useEslint属性设置为false



5.三级分类树形目录编写：

在Element-ui中找到`Tree 树形控件`    位置 ` https://element.eleme.cn/#/zh-CN/component/tree`。将
`<el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>`
拷贝到`<template>`标签中，将data(){} 和 methods:{}也拷贝到category.vue 。得到category.vue：

```vue
<template>
     <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      data: [
        {
          label: "一级 1",
          children: [
            {
              label: "二级 1-1",
              children: [
                {
                  label: "三级 1-1-1",
                },
              ],
            },
          ],
        },
        {
          label: "一级 2",
          children: [
            {
              label: "二级 2-1",
              children: [
                {
                  label: "三级 2-1-1",
                },
              ],
            },
            {
              label: "二级 2-2",
              children: [
                {
                  label: "三级 2-2-1",
                },
              ],
            },
          ],
        },
        {
          label: "一级 3",
          children: [
            {
              label: "二级 3-1",
              children: [
                {
                  label: "三级 3-1-1",
                },
              ],
            },
            {
              label: "二级 3-2",
              children: [
                {
                  label: "三级 3-2-1",
                },
              ],
            },
          ],
        },
      ],
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {},
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

访问`http://localhost:8001/#/product-category`,可以看到如下效果（此时的分类数据展示是用“常量”写死的）：



![image-20230402155800689](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040221117.png)





f12访问控制台，可以看到前端给`http://localhost:8080/renren-fast/product/category/list/tree`发送请求，但是我们希望前端先给网关服务`gulimall-gateway`发生请求，再由网关路由到`reneren-fast`后台系统。

![image-20230402163459281](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040224832.png)



全局搜索`http://localhost:8080/renren-fast`，找到定义请求路径的文件`static\config\index.js`:

![image-20230402163745067](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040225916.png)

更改`static\config\index.js`文件中的请求地址配置，让后端向网关发起请求：将原来的`http://localhost:8080/renren-fast`改为`http://localhost:88/api`

```js
/**
 * 开发环境
 */
;(function () {
  window.SITE_CONFIG = {};

  // api接口请求地址 
  //  
  // 让后端向网关发起请求：将原来的http://localhost:8080/renren-fast改为http://localhost:88/api
  window.SITE_CONFIG['baseUrl'] = 'http://localhost:88/api';

  // cdn地址 = 域名 + 版本号
  window.SITE_CONFIG['domain']  = './'; // 域名
  window.SITE_CONFIG['version'] = '';   // 版本号(年月日时分)
  window.SITE_CONFIG['cdnUrl']  = window.SITE_CONFIG.domain + window.SITE_CONFIG.version;
})();

```

下面配置后端网关服务、商品服务、`renren-fast`的相关请求路径，进行网关路由及路径重写，具体配置见后端项目文档：



处理了网关及跨域问题后，分别启动`renren-fast`、`gulimall-product`、网关服务`gulimall-gateway`后继续三级分类的前端编写：



查看树形控件的props属性说明：

![image-20230402200037765](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040229933.png)

在`category.vue`中向`/product/category/list/tree`发起请求，获取CategoryEntity的children属性、name属性（分类名称）并显示：

```vue
<template>
  <el-tree
    :data="menus"
    :props="defaultProps"
    @node-click="handleNodeClick"
  ></el-tree>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      menus: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get"
      }).then(({ data }) => {
          console.log("成功获取到菜单数据...",data.data)
          this.menus = data.data;
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
      // 一创建就像后端发送请求获取数据
      this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

效果：

![image-20230402200944954](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040230403.png)

<!-- TOC --><a name="22-"></a>
### 2.2 三级分类删除

找到elementUI中Tree树形控件的`自定义节点内容`

![image-20230405072220938](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060316132.png)



将`自定义节点内容`中的span标签内容拷贝到`category.vue`中的`<el-tree> </el-tree>` 标签中，同样将append 、 remove 方法拷贝到`category.vue`中方法中

```vue
 <!-- span标签内容 -->
 <span>{{ node.label }}</span>
        <span>
          <el-button
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
</span>
   
   // append 、 remove 方法
    append(data) {
      console.log("append", data);
    },

    remove(node, data) {
      console.log("remove", node,data);
    },        
```

完成拷贝后`category.vue`内容如下：

```vue
<template>
  <el-tree :data="menus" :props="defaultProps" @node-click="handleNodeClick">
    <!-- span标签内容 -->
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
      <span>
        <el-button type="text" size="mini" @click="() => append(data)">
          Append
        </el-button>
        <el-button type="text" size="mini" @click="() => remove(node, data)">
          Delete
        </el-button>
      </span>
    </span>
  </el-tree>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      menus: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },
    // append 、 remove 方法
    append(data) {
      console.log("append", data);
    },

    remove(node, data) {
      console.log("remove", node,data);
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

保存改动后运行`renren-fast-vue`前端项目，分类维护模块的效果如下：

![image-20230405073420158](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060316041.png)

继续在`<el-tree> </el-tree>` 标签中添加`:expand-on-click-node="false"`属性，使得点击箭头时目录层级才会展开(默认点击元素就会展开)。  继续添加`show-checkbox`属性和`node-key="catId"`属性：

![image-20230405124408394](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060316642.png)

为了满足三级分类的业务场景，展示的目录还需要满足：只有叶子节点(没有子节点)才能显示delete按钮,非叶子节点才能显示append按钮。实现：在按钮标签上 利用 v-if 来决定显示哪些节点。此时`category.vue`内容如下：

```vue
<template>
  <el-tree
    :data="menus"
    :props="defaultProps"
    :expand-on-click-node="false"
    show-checkbox
    node-key="catId"
  >
    <!-- span标签内容 -->
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
      <span>
        <!-- 只有叶子节点(没有子节点)才能显示delete -->
        <el-button
          v-if="node.level <= 2"
          type="text"
          size="mini"
          @click="() => append(data)"
        >
          Append
        </el-button>
        <!-- 非叶子节点才能显示append按钮 -->
        <el-button
          v-if="node.childNodes.length == 0"
          type="text"
          size="mini"
          @click="() => remove(node, data)"
        >
          Delete
        </el-button>
      </span>
    </span>
  </el-tree>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      menus: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },
    // append 、 remove 方法
    append(data) {
      console.log("append", data);
    },

    remove(node, data) {
      console.log("remove", node, data);
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

效果：

![image-20230405124602567](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060316605.png)





新建两个代码块模板，发送get请求和post请求：File——>Preferences——>User Snippets(用户代码段)——>New Global Snippets file(新建全局代码片段)

![image-20230405151518682](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060316335.png)

在模板文件中加入以下内容：

```vue
{
	"http-get请求":{
		"prefix": "httpget",
		"body":[
			"this.\\$http({",
			"url: this.\\$http.adornUrl(''),",
			"method:'get',",
			"params:this.\\$http.adornParams({})",
			"}).then(({data})=>{",
			"})"
		],
		"description":"httpGET请求"
	},
	"http-post请求":{
		"prefix":"httppost",
		"body":[
			"this.\\$http({",
			"url:this.\\$http.adornUrl(''),",
			"method:'post',",
			"data: this.\\$http.adornData(data, false)",
			"}).then(({data})=>{ })"
		],
		"description":"httpPOST请求"
	}	
}
```

模板使用：直接在代码中键入模板中的"prefix"并回车，即可生成对应的代码片段

![image-20230405151949271](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060317557.png)

继续编写`category.vue`， 在remove方法中调用后端删除接口：

```vue
<template>
  <el-tree
    :data="menus"
    :props="defaultProps"
    :expand-on-click-node="false"
    show-checkbox
    node-key="catId"
  >
    <!-- span标签内容 -->
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
      <span>
        <!-- 只有叶子节点(没有子节点)才能显示delete -->
        <el-button
          v-if="node.level <= 2"
          type="text"
          size="mini"
          @click="() => append(data)"
        >
          Append
        </el-button>
        <!-- 非叶子节点才能显示append按钮 -->
        <el-button
          v-if="node.childNodes.length == 0"
          type="text"
          size="mini"
          @click="() => remove(node, data)"
        >
          Delete
        </el-button>
      </span>
    </span>
  </el-tree>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      menus: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },
    // append 、 remove 方法
    append(data) {
      console.log("append", data);
    },
    remove(node, data) {
      console.log("remove", node, data);
      var ids = [data.catId];
      this.$http({
        url: this.$http.adornUrl("/product/category/delete"),
        method: "post",
        data: this.$http.adornData(ids, false),
      }).then(({ data }) => {
        console.log("删除成功");
        // 删除成功后要重新请求获取menus菜单，否则点击删除后不能立即看到菜单元素被删除
        this.getMenus();
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```



![image-20230405164658847](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060317852.png)

在页面上点击删除，后端商品服务`gulimall-product`打印如下日志，到此基本删除功能实现。

```sql
==>  Preparing: UPDATE pms_category SET show_status=0 WHERE cat_id IN ( ? ) AND show_status=1 
 ==> Parameters: 166(Long)
<==    Updates: 1
==>  Preparing: SELECT cat_id,parent_cid,name,icon,show_status,sort,product_unit,product_count,cat_level FROM pms_category WHERE show_status=1 
==> Parameters: 
<==      Total: 1424
==>  Preparing: UPDATE pms_category SET show_status=0 WHERE cat_id IN ( ? ) AND show_status=1 
==> Parameters: 165(Long)
<==    Updates: 1
==>  Preparing: SELECT cat_id,parent_cid,name,icon,show_status,sort,product_unit,product_count,cat_level FROM pms_category WHERE show_status=1 
==> Parameters: 
<==      Total: 1423
```

细化删除功能效果：

1.细化一。在删除时利用elementUI的`message-box`在页面上显示消息提示，官方文档找到message-box ：`https://element.eleme.cn/#/zh-CN/component/message-box`。找到如下消息提示代码，拷贝到category.vue中进行使用

```vue
 this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
```

2.细化二。在删除之后可能成功或失败，同样要对不同状态给出消息提示,将不同状态的提示拷贝到category.vue中进行使用

![image-20230405170947921](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060317599.png)

```vue
 methods: {
      open1() {
        this.$message('这是一条消息提示');
      },
      open2() {
        this.$message({
          message: '恭喜你，这是一条成功消息',
          type: 'success'
        });
      },

      open3() {
        this.$message({
          message: '警告哦，这是一条警告消息',
          type: 'warning'
        });
      },

      open4() {
        this.$message.error('错了哦，这是一条错误消息');
      }
```

3.细化三。删除成功三级目录应该维持展开状态。解决：在`  <el-tree>`标签中添加`default-expanded-keys="expandedKey"`属性

![image-20230405173506617](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060317427.png)

经过效果细化后`category.vue`的代码如下：

```vue
<template>
  <el-tree
    :data="menus"
    :props="defaultProps"
    :expand-on-click-node="false"
    show-checkbox
    node-key="catId"
    :default-expanded-keys="expandedKey"
  >
    <!-- span标签内容 -->
    <span class="custom-tree-node" slot-scope="{ node, data }">
      <span>{{ node.label }}</span>
      <span>
        <!-- 只有叶子节点(没有子节点)才能显示delete -->
        <el-button
          v-if="node.level <= 2"
          type="text"
          size="mini"
          @click="() => append(data)"
        >
          Append
        </el-button>
        <!-- 非叶子节点才能显示append按钮 -->
        <el-button
          v-if="node.childNodes.length == 0"
          type="text"
          size="mini"
          @click="() => remove(node, data)"
        >
          Delete
        </el-button>
      </span>
    </span>
  </el-tree>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      menus: [],
      expandedKey: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },
    // append 、 remove 方法
    append(data) {
      console.log("append", data);
    },
    remove(node, data) {
      console.log("remove", node, data);
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        })
          .then(({ data }) => {
            console.log("删除成功");
            // 删除成功后要重新请求获取menus菜单，否则点击删除后不能立即看到菜单元素被删除
            this.$message({
              message: "菜单删除成功",
              type: "success",
            });
            this.getMenus();
            // 设置需要默认展开的菜单，删除当前节点后，让其父节点依然保持展开
            this.expandedKey = [node.parent.data.catId];
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

完成代码编写后，执行下列SQL恢复被删除的数据

```sql
UPDATE pms_category SET `show_status` = 1; 
```

<!-- TOC --><a name="23-"></a>
### 2.3 三级分类新增

在Element-ui中找到`Dialog 对话框`组件，`https://element.eleme.cn/#/zh-CN/component/dialog`

![image-20230405180507048](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060318897.png)

在`category.vue`中添加对话框组件：

```vue
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
```

当点击`append`按钮时显示对话框

```
    // append方法
    append(data) {
      console.log("append", data);
      // 将对话框设置为显示
      this.dialogVisible = true;
    },
```

效果：点击`append`按钮时显示对话框

![image-20230405182050860](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060318694.png)



还可以在`Dialog 对话框`中添加表单，供用户填写信息。将`<el-form :model="form">`标签复制到对话框组件中

![image-20230405182215250](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060318991.png)

![image-20230405182706028](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010130820.png)



将`<el-form :model="form">`标签复制到对话框组件中，并修改如下。代码逻辑：点击append，弹出对话框，输入分类名称。点击确定，执行addCategory函数把数据添加到数据库

```vue
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <!-- 点击对话框后执行addCategory()函数新增三级分类元素 -->
        <el-button type="primary" @click="addCategory">确 定</el-button>
      </span>
    </el-dialog>
```

append方法和 addCategory()：addCategory方法发送post请求到后端； 因为要把数据添加到数据库，所以在前端数据中按照数据库的格式声明一个category。点击append时，计算category属性，点击确定时发送post请求

```js
 methods: {
   // append方法，调用addCategory添加三级分类
    append(data) {
      console.log("append", data);
      // 将对话框设置为显示
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
    },
    // 添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.category);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    }
}
```

此时完整的`category.vue`代码如下：

```vue
<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
    >
      <!-- span标签内容 -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <!-- 只有叶子节点(没有子节点)才能显示delete -->
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            Append
          </el-button>
          <!-- 非叶子节点才能显示append按钮 -->
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <!-- 点击对话框后执行addCategory()函数新增三级分类元素 -->
        <el-button type="primary" @click="addCategory">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      // 和表单绑定的三级分类实体类category
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        productUnit: "",
        icon: "",
        catId: null,
      },
      // 对话框属性：对话框是否可见
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },
    // append方法，调用addCategory添加三级分类
    append(data) {
      console.log("append", data);
      // 将对话框设置为显示
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
    },
    // 添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.category);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },
    // remove 方法
    remove(node, data) {
      console.log("remove", node, data);
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        })
          .then(({ data }) => {
            console.log("删除成功");
            // 删除成功后要重新请求获取menus菜单，否则点击删除后不能立即看到菜单元素被删除
            this.$message({
              message: "菜单删除成功",
              type: "success",
            });
            this.getMenus();
            // 设置需要默认展开的菜单，删除当前节点后，让其父节点依然保持展开
            this.expandedKey = [node.parent.data.catId];
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

测试效果：

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060320088.png" > <b>添加元素</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060321734.png" > <b>添加元素</b></td>
    </tr>
    </table>




<!-- TOC --><a name="24-"></a>
### 2.4  三级分类修改

在`  <el-tree>`标签中添加修改按钮,点击append时执行append(data)方法，点击edit时执行edit(data)方法，

```js
          <!-- 非叶子节点才能显示append按钮 -->
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            Append
          </el-button>

          <!-- 所有节点都要显示edit按钮 -->
          <el-button type="text" size="mini" @click="edit(data)">
            edit
          </el-button>
```

为了让添加和修改复用`dialog对话框`，将title进行绑定—— `:title="title"`  。点击对话框之后执行submitData函数，在submit中判断执行添加还是修改操作。

```js
  <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false"
    >
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input
            v-model="category.productUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <!-- 点击对话框后执行submitData函数 -->
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
```

在data{}中添加属性`title: ""`  和`dialogType`和属性 。点击修改时设dialogType为edit,点击新增时设dialogType为add

```js
 data() {
    return {
      // 对话框被新增和修改复用，用title动态绑定对话框的标题
      title: "",
      // 点击修改时设dialogType为edit,点击新增时设dialogType为add,
      dialogType: "",
    };
  },
```

点击append/edit时执行append(data)方法/edit(data)方法修改`dialogType` 和`title`属性：

```js
 // append方法，调用addCategory添加三级分类
    append(data) {
      console.log("append", data);
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true;
    },

    // edit方法,参数data为当前节点的数据
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;
    },
```

点击append/edit按钮之后弹出对话框，点击对话框的确认键之后执行submitData函数，在submit中判断执行添加还是修改操作:

```js
    submitData() {
      if ((this.dialogType == "add")) {
        this.addCategory();
      }
      if ((this.dialogType == "edit")) {
        this.editCategory();
      }
    },
```

在点击修改时要回显节点的数据：

![image-20230406024325597](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304060322111.png)

点击修改时要回显节点的数据，实现方式：发送请求获取当前节点最新的数据,回显最新数据

```js
// edit方法,参数data为当前节点的数据
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;

      // 回显数据（此种回显方法拿到的可能不是最新数据）
      //   this.category.name = data.name;
      //   this.category.catId = data.catId;

      // 回显数据: 发送请求获取当前节点最新的数据,回显最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get",
      }).then(({ data }) => {
        // 请求成功
        console.log("要回显的数据", data);
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
        this.category.catLevel = data.data.catLevel;
        this.category.sort = data.data.sort;
        this.category.showStatus = data.data.showStatus;
      });
    },
```

修改三级分类数据: 发送post请求，将要修改的字段解构出来发送给后端，不修改的字段不发

```js
    // 修改三级分类数据
    editCategory() {
      // 将要修改的字段解构出来发送给后端，不修改的字段不发
      var { catId, name, icon, productUnit } = this.category;
      //   var data = {
      //     catId: catId,
      //     name: name,
      //     icon: icon,
      //     productUnit: productUnit,
      //   };
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },
```

完整`category.vue`代码如下：

```vue
<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
    >
      <!-- span标签内容 -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <!-- 非叶子节点才能显示append按钮 -->
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            Append
          </el-button>

          <!-- 所有节点都要显示edit按钮 -->
          <el-button type="text" size="mini" @click="edit(data)">
            edit
          </el-button>

          <!-- 只有叶子节点(没有子节点)才能显示delete -->
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false"
    >
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input
            v-model="category.productUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <!-- 点击对话框后执行addCategory()函数新增三级分类元素 -->
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      // 对话框被新增和修改复用，用title动态绑定对话框的标题
      title: "",
      // 点击修改时设dialogType为edit,点击新增时设dialogType为add,
      dialogType: "",
      // 和表单绑定的三级分类实体类category
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        productUnit: "",
        icon: "",
        catId: null,
      },
      // 对话框属性：对话框是否可见
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },

    // append方法，调用addCategory添加三级分类
    append(data) {
      console.log("append", data);
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.catId = null;
      this.category.name = "";
      this.category.icon = "";
      this.category.productUnit = "";
      this.category.sort = 0;
      this.category.showStatus = 1;
    },

    // 添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.category);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },

    // 修改三级分类数据
    editCategory() {
      // 将要修改的字段解构出来发送给后端，不修改的字段不发
      var { catId, name, icon, productUnit } = this.category;
      //   var data = {
      //     catId: catId,
      //     name: name,
      //     icon: icon,
      //     productUnit: productUnit,
      //   };
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },

    // edit方法,参数data为当前节点的数据
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;

      // 回显数据（此种回显方法拿到的可能不是最新数据）
      //   this.category.name = data.name;
      //   this.category.catId = data.catId;

      // 回显数据: 发送请求获取当前节点最新的数据,回显最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get",
      }).then(({ data }) => {
        // 请求成功
        console.log("要回显的数据", data);
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
        this.category.catLevel = data.data.catLevel;
        this.category.sort = data.data.sort;
        this.category.showStatus = data.data.showStatus;
      });
    },

    submitData() {
      if ((this.dialogType == "add")) {
        this.addCategory();
      }
      if ((this.dialogType == "edit")) {
        this.editCategory();
      }
    },

    // remove 方法
    remove(node, data) {
      console.log("remove", node, data);
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        })
          .then(({ data }) => {
            console.log("删除成功");
            // 删除成功后要重新请求获取menus菜单，否则点击删除后不能立即看到菜单元素被删除
            this.$message({
              message: "菜单删除成功",
              type: "success",
            });
            this.getMenus();
            // 设置需要默认展开的菜单，删除当前节点后，让其父节点依然保持展开
            this.expandedKey = [node.parent.data.catId];
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

<!-- TOC --><a name="25-"></a>
### 2.5 三级分类拖拽功能

<!-- TOC --><a name="251-"></a>
#### 2.5.1 拖拽效果实现

拖拽功能：通过拖拽改变节点顺序、节点的父子关系

![image-20230407214031182](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010132029.png)



通过 draggable 属性可让节点变为可拖拽：在Tree树形控件下添加` draggable`属性就可以让节点可拖拽

```vue
<el-tree
  draggable
</el-tree>
```

由于分类的层级不能超过三层，所以拖拽时要满足一定的规则。可以用allow-drop属性来限制在什么情况下能拖拽。参数draggingNode：被拖动节点，dropNode：最终关联节点，type：拖拽节点和dropNode的位置关系

![image-20230408081741978](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010132972.png)

继续添加`allow-drop`属性、allowDrop函数（allowDrop函数通过返回true\false来判断能否拖拽）

```vue
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
      draggable
      :allow-drop="allowDrop"
    >
    </el-tree>
export default {
 methods: {
      allowDrop(draggingNode, dropNode, type) {
        if (dropNode.data.label === '二级 3-1') {
          return type !== 'inner';
        } else {
          return true;
        }
      },
    }
```

拖拽功能的前端实现：lementui树型控件->可拖拽节点

- 在`<el-tree>`中加入属性draggable表示节点可拖拽
- 在`<el-tree>`中加入属性:allow-drop="allowDrop"，拖拽时判定目标节点能否被放置

- allowDrop有三个参数draggingNode表示拖拽的节点，dropNode表示拖拽到哪个节点，type表示拖拽的类型’prev’、‘inner’ 和 ‘next’，表示拖拽到目标节点之前、里面、之后

- allowDrop函数实现判断，拖拽后必须保持数型的三层结构
- 三级分类能否拖动的判断：
  - 节点的深度 = 最深深度 - 当前深度 + 1
  - 当拖拽节点拖拽到目标节点的内部，要满足： 拖拽节点的深度 + 目标节点的深度 <= 3
  - 当拖拽节点拖拽的目标节点的两侧，要满足： 拖拽节点的深度 + 目标节点的父节点的深度 <= 3

```js
// data中新增属性，用来记录当前节点的最大深度,节点的最大层级，默认为1
maxLevel: 0, 

  allowDrop(draggingNode, dropNode, type) {
      console.log("allowDrop", draggingNode, dropNode, type);

      // 被拖动的当前节点的所有子节点的最大的层级 + 目标父节点层数 <= 3
      // 求出被拖动的当前节点的最大深度：用countNodeLevel方法更新最大深度
      this.countNodeLevel(draggingNode.data);
      // 此处的deep理解为被拖拽节点距其叶子节点的最大距离
      let deep = this.maxLevel - draggingNode.data.catLevel + 1;
      console.log("深度：", deep);
      if (type == "inner") {
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },

    countNodeLevel(node) {
      // 找到所有子节点，求出最大深度
      if (node.children != null && node.children.length > 0) {
        for (let i = 0; i < node.children.length; i++) {
          if (node.children[i].catLevel > this.maxLevel) {
            this.maxLevel = node.children[i].catLevel;
          }
          this.countNodeLevel(node.children[i]);
        }
      }
    },
```

<!-- TOC --><a name="252-"></a>
#### 2.5.2 拖拽数据收集

前端拖拽成功之后要将节点的最新信息发送给后端进行保存，否则节点的层级信息还是维持不变。请求后端前需要收集拖拽节点的信息：新的层级信息、新的排序信息、新的父节点



找到Elememnt-ui —`Tree 树形控件`下的Event，使用node-drop事件来监听拖拽成功事件。

![image-20230408100656992](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010132872.png)

为`el-tree`绑定拖拽成功事件，并在`methods`中添加处理事件的handleDrop函数

```vue
<el-tree
  @node-drop="handleDrop"
</el-tree> 

methods: {
      handleDrop(draggingNode, dropNode, dropType) {
        console.log('tree drop: ', dropNode.label, dropType);
      }
    }
```

拖拽数据收集:

- 在`<el-tree>`中加入属性@node-drop="handleDrop",表示拖拽事件结束后触发事件handleDrop,handleDrop共四个参数，draggingNode：被拖拽节点对应的 Node; dropNode:结束拖拽时最后进入的节点; dropType:被拖拽节点的放置位置（before、after、inner）;ev:event
- 拖拽可能影响的节点的数据：parentCid、catLevel、sort
- data中新增updateNodes，把所有要修改的节点都传进来。
- 要修改的数据：拖拽节点的parentCid、catLevel、sort
- 要修改的数据：新的兄弟节点的sort （把新的节点收集起来，然后重新排序）
- 要修改的数据：子节点的catLevel

代码 + 注释：

```js
//el-tree中新增属性，绑定handleDrop，表示拖拽完触发
@node-drop="handleDrop"

//data 中新增数据，用来记录需要更新的节点（拖拽的节点（parentCid、catLevel、sort），拖拽后的兄弟节点（sort），拖拽节点的子节点（catLevel））
updateNodes: [],

   // 拖拽成功后执行此函数：
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("handleDrop: ", draggingNode, dropNode, dropType);
      //1、当前节点最新父节点的id
      let pCid = 0;
      //拖拽后的兄弟节点，分两种情况，一种是拖拽到两侧，一种是拖拽到内部
      let sibings = null;
      if (dropType == "before" || dropType == "after") {
        pCid =
          dropNode.parent.data.catId == undefined
            ? 0
            : dropNode.parent.data.catId;
        sibings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        sibings = dropNode.childNodes;
      }

      //2、当前拖拽节点的最新顺序
      //遍历所有的兄弟节点，如果是拖拽节点，传入（catId，sort，parentCid，catLevel），如果是兄弟节点传入（catId，sort）
      for (let i = 0; i < sibings.length; i++) {
        if (sibings[i].data.catId == draggingNode.data.catId) {
          //如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level;
          if (sibings[i].level != draggingNode.level) {
            //当前节点的层级发生变化
            catLevel = sibings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(sibings[i]);
          }
          this.updateNodes.push({
            catId: sibings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel,
          });
        } else {
          this.updateNodes.push({ catId: sibings[i].data.catId, sort: i });
        }
      }
    
       //每次拖拽后把数据清空，否则要修改的节点将会越拖越多
        (this.updateNodes = []), 
        (this.maxLevel = 0);
    },

    // 修改拖拽节点的子节点的层级
    updateChildNodeLevel(node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          //遍历子节点，传入（catId，catLevel）
          var cNode = node.childNodes[i].data;
          this.updateNodes.push({
            catId: cNode.catId,
            catLevel: node.childNodes[i].level,
          });
          //处理子节点的子节点
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },
```

<!-- TOC --><a name="253-http-post"></a>
#### 2.5.3 发送http post请求完成修改

```js
     // 拖拽成功后执行此函数：
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("handleDrop: ", draggingNode, dropNode, dropType);
      //1、当前节点最新父节点的id
      let pCid = 0;
      //拖拽后的兄弟节点，分两种情况，一种是拖拽到两侧，一种是拖拽到内部
      let sibings = null;
      if (dropType == "before" || dropType == "after") {
        pCid =
          dropNode.parent.data.catId == undefined
            ? 0
            : dropNode.parent.data.catId;
        sibings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        sibings = dropNode.childNodes;
      }

      //2、当前拖拽节点的最新顺序
      //遍历所有的兄弟节点，如果是拖拽节点，传入（catId，sort，parentCid，catLevel），如果是兄弟节点传入（catId，sort）
      for (let i = 0; i < sibings.length; i++) {
        if (sibings[i].data.catId == draggingNode.data.catId) {
          //如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level;
          if (sibings[i].level != draggingNode.level) {
            //当前节点的层级发生变化
            catLevel = sibings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(sibings[i]);
          }
          this.updateNodes.push({
            catId: sibings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel,
          });
        } else {
          this.updateNodes.push({ catId: sibings[i].data.catId, sort: i });
        }
      }

      // 当前拖拽节点的最新层级信息
      console.log("updateNodes", this.updateNodes);

      // 发送请求修改拖拽后的层级信息
      this.$http({
        url: this.$http.adornUrl("/product/category/update/sort"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单顺序修改成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单：拖拽到的目的地位置的父节点要默认展开
        this.expandedKey = [pCid];
        //每次拖拽后把数据清空，否则要修改的节点将会越拖越多
        (this.updateNodes = []), 
        (this.maxLevel = 0);
      });
    },
```

<!-- TOC --><a name="254-"></a>
#### 2.5.4 拖拽优化

优化一：防止误操作导致拖拽，增加按钮来开启、关闭拖拽功能。利用Element-ui中的Switch开关来实现

![image-20230408141402730](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010135632.png)

增加`el-switch`开关组件，默认的value1用来绑定一个属性，当value1为true的时候开关开启，为false时开关关闭。此处Switch开关主要用来决定能否拖拽，所以可以让树形控件`<el-tree>`中的draggable属性和`<el-switch>`共同绑定一个值

```vue
<template>
<div>
    <el-switch v-model="value1" active-text="按月付费" inactive-text="按年付费"> 
</div>
</template>
```

将树形控件`<el-tree>`中的draggable属性和`<el-switch>`绑定，来决定能否开启拖拽：

```vue
<el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽">
</el-switch>
<el-tree
         :draggable="draggable"
         >
</el-tree>
data() {
    return {
      // 是否开启拖拽
      draggable:false,
      }
}


```

![image-20230408143407769](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010135952.png)

优化二：每一次拖拽，前端都会向后端发送请求，会导致请求过于频繁。可以将所有拖拽完成后再向后端发送请求修改节点信息

添加按钮绑定`批量保存`按钮，该按钮只有开启拖拽时才可见，所以要和`draggable`绑定

```vue
<el-button v-if="draggable" @click="batchSave">批量保存拖拽</el-button>
```

点击按钮时，才发送http请求完成保存。所以将之前发送请求的代码移到`batchSave()`函数中：

```js
    // 批量保存拖拽后的信息
    batchSave() {
      // 发送请求修改拖拽后的层级信息
      this.$http({
        url: this.$http.adornUrl("/product/category/update/sort"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单顺序修改成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单：拖拽到的目的地位置的父节点要默认展开
        this.expandedKey = [pCid];
        //每次拖拽后把数据清空，否则要修改的节点将会越拖越多
        (this.updateNodes = []), (this.maxLevel = 0);
      });
    },
```

修改拖拽的逻辑：计算深度时，用当前数据，而不是数据库中的数据。因为可能还没来得及保存到数据库

```js
    allowDrop(draggingNode, dropNode, type) {
      console.log("allowDrop", draggingNode, dropNode, type);

      // 被拖动的当前节点的所有子节点的最大的层级 + 目标父节点层数 <= 3
      // 求出被拖动的当前节点的最大深度：用countNodeLevel方法更新最大深度
      this.countNodeLevel(draggingNode);
      // 此处的deep理解为被拖拽节点距其叶子节点的最大距离
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
      console.log("深度：", deep);
      if (type == "inner") {
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },

    //计算深度时，用当前数据，而不是数据库中的数据。因为可能还没来得及保存到数据库
    countNodeLevel(node) {
      // 找到所有子节点，求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },
```

此时`category.vue`如下：

```vue
<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽"
    >
    </el-switch>
    <el-button v-if="draggable" @click="batchSave">批量保存拖拽</el-button>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
      :draggable="draggable"
      :allow-drop="allowDrop"
      @node-drop="handleDrop"
    >
      <!-- span标签内容 -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <!-- 非叶子节点才能显示append按钮 -->
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)"
          >
            Append
          </el-button>

          <!-- 所有节点都要显示edit按钮 -->
          <el-button type="text" size="mini" @click="edit(data)">
            edit
          </el-button>

          <!-- 只有叶子节点(没有子节点)才能显示delete -->
          <el-button
            v-if="node.childNodes.length == 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false"
    >
      <!-- 将表单与三级分类实体类category绑定 -->
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input
            v-model="category.productUnit"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <!-- 点击对话框后执行addCategory()函数新增三级分类元素 -->
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》'

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  data() {
    return {
      // 批量保存过后要展开的菜单id
      pCid: [],
      // 是否开启拖拽
      draggable: false,
      // 拖拽时所有要要修改的节点信息
      updateNodes: [],
      // 节点的最大层级，默认为0
      maxLevel: 0,
      // 对话框被新增和修改复用，用title动态绑定对话框的标题
      title: "",
      // 点击修改时设dialogType为edit,点击新增时设dialogType为add,
      dialogType: "",
      // 和表单绑定的三级分类实体类category
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        productUnit: "",
        icon: "",
        catId: null,
      },
      // 对话框属性：对话框是否可见
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        // children对应子节点的属性值，此处是CategoryEntity的children属性
        children: "children",
        // label对应要显示的字段名，此处为CategoryEntity的name属性（分类名称）
        label: "name",
      },
    };
  },
  methods: {
    allowDrop(draggingNode, dropNode, type) {
      console.log("allowDrop", draggingNode, dropNode, type);

      // 被拖动的当前节点的所有子节点的最大的层级 + 目标父节点层数 <= 3
      // 求出被拖动的当前节点的最大深度：用countNodeLevel方法更新最大深度
      this.countNodeLevel(draggingNode);
      // 此处的deep理解为被拖拽节点距其叶子节点的最大距离
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
      console.log("深度：", deep);
      if (type == "inner") {
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },

    //计算深度时，用当前数据，而不是数据库中的数据。因为可能还没来得及保存到数据库
    countNodeLevel(node) {
      // 找到所有子节点，求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },

    // 拖拽成功后执行此函数：
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("handleDrop: ", draggingNode, dropNode, dropType);
      //1、当前节点最新父节点的id
      let pCid = 0;
      //拖拽后的兄弟节点，分两种情况，一种是拖拽到两侧，一种是拖拽到内部
      let sibings = null;
      if (dropType == "before" || dropType == "after") {
        pCid =
          dropNode.parent.data.catId == undefined
            ? 0
            : dropNode.parent.data.catId;
        sibings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        sibings = dropNode.childNodes;
      }
      this.pCid.push(pCid);
      //2、当前拖拽节点的最新顺序
      //遍历所有的兄弟节点，如果是拖拽节点，传入（catId，sort，parentCid，catLevel），如果是兄弟节点传入（catId，sort）
      for (let i = 0; i < sibings.length; i++) {
        if (sibings[i].data.catId == draggingNode.data.catId) {
          //如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level;
          if (sibings[i].level != draggingNode.level) {
            //当前节点的层级发生变化
            catLevel = sibings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(sibings[i]);
          }
          this.updateNodes.push({
            catId: sibings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel,
          });
        } else {
          this.updateNodes.push({ catId: sibings[i].data.catId, sort: i });
        }
      }

      // 当前拖拽节点的最新层级信息
      console.log("updateNodes", this.updateNodes);

      // 发送请求修改拖拽后的层级信息
    },

    // 修改拖拽节点的子节点的层级
    updateChildNodeLevel(node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          //遍历子节点，传入（catId，catLevel）
          var cNode = node.childNodes[i].data;
          this.updateNodes.push({
            catId: cNode.catId,
            catLevel: node.childNodes[i].level,
          });
          // 处理子节点的子节点
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },

    // 批量保存拖拽后的信息
    batchSave() {
      // 发送请求修改拖拽后的层级信息
      this.$http({
        url: this.$http.adornUrl("/product/category/update/sort"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单顺序修改成功",
          type: "success",
        });
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单：拖拽到的目的地位置的父节点要默认展开
        this.expandedKey = this.pCid;
        //每次拖拽后把数据清空，否则要修改的节点将会越拖越多
        this.updateNodes = [];
        this.maxLevel = 0;
        // this.pCid = 0;
      });
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取到菜单数据...", data.data);
        this.menus = data.data;
      });
    },

    // append方法，调用addCategory添加三级分类
    append(data) {
      console.log("append", data);
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.catId = null;
      this.category.name = "";
      this.category.icon = "";
      this.category.productUnit = "";
      this.category.sort = 0;
      this.category.showStatus = 1;
    },

    // 添加三级分类
    addCategory() {
      console.log("提交的三级分类数据", this.category);
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },

    // 修改三级分类数据
    editCategory() {
      // 将要修改的字段解构出来发送给后端，不修改的字段不发
      var { catId, name, icon, productUnit } = this.category;
      //   var data = {
      //     catId: catId,
      //     name: name,
      //     icon: icon,
      //     productUnit: productUnit,
      //   };
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({ catId, name, icon, productUnit }, false),
      }).then(({ data }) => {
        // 显示成功类型的提示信息
        this.$message({
          message: "菜单保存成功",
          type: "success",
        });
        // 关闭对话框
        this.dialogVisible = false;
        // 刷新需要默认展开的菜单
        this.getMenus();
        // 设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      });
    },

    // edit方法,参数data为当前节点的数据
    edit(data) {
      console.log("要修改的数据", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;

      // 回显数据（此种回显方法拿到的可能不是最新数据）
      //   this.category.name = data.name;
      //   this.category.catId = data.catId;

      // 回显数据: 发送请求获取当前节点最新的数据,回显最新数据
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get",
      }).then(({ data }) => {
        // 请求成功
        console.log("要回显的数据", data);
        this.category.name = data.data.name;
        this.category.catId = data.data.catId;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
        this.category.parentCid = data.data.parentCid;
        this.category.catLevel = data.data.catLevel;
        this.category.sort = data.data.sort;
        this.category.showStatus = data.data.showStatus;
      });
    },

    submitData() {
      if (this.dialogType == "add") {
        this.addCategory();
      }
      if (this.dialogType == "edit") {
        this.editCategory();
      }
    },

    // remove 方法
    remove(node, data) {
      console.log("remove", node, data);
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl("/product/category/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        })
          .then(({ data }) => {
            console.log("删除成功");
            // 删除成功后要重新请求获取menus菜单，否则点击删除后不能立即看到菜单元素被删除
            this.$message({
              message: "菜单删除成功",
              type: "success",
            });
            this.getMenus();
            // 设置需要默认展开的菜单，删除当前节点后，让其父节点依然保持展开
            this.expandedKey = [node.parent.data.catId];
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      });
    },
  },
  //声明周期 - 创建完成（可以访问当前this实例）
  created() {
    // 一创建就像后端发送请求获取数据
    this.getMenus();
  },
  //声明周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
```

<!-- TOC --><a name="26-"></a>
### 2.6 节点批量删除

添加批量删除按钮：

```vue
<el-button type="danger" size="small" @click="batchDelete" round>批量删除</el-button>


```

`eltree`组件中的`getCheckedNodes`方法可以返回所有被选中的节点构成数组

![image-20230408162719462](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010135440.png)

如何调用组件`eltree`提供的方法？

- 给组件起一个唯一标识
- 通过` this.$refs`获取当前vue实例中的所有组件，通过`this.$refs.组件唯一标示`来调用特定组件的方法
- 示例：调用`eltree`提供的`getCheckedNodes()`方法

```vue
<el-tree
   <!--eltree中新增属性，用作组件的唯一标示-->
   ref="menuTree"
></el-tree>

batchDelete() {
   let checkedNodes = this.$refs.menuTree.getCheckedNodes();
}
```

完整的删除功能实现：

```vue
    batchDelete() {
      let catIds = [];
      // 获取所有被选中的元素
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      console.log("被选中的元素", checkedNodes);
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId);
      }
      this.$confirm(`是否批量删除【${catIds}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })1
        .then(() => {
          // 点击确认以后发送删除请求
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(catIds, false),
          }).then(({ data }) => {
            this.$message({
              message: "菜单批量删除成功",
              type: "success",
            });
            this.getMenus();
          });
        })
        .catch(() => {
          // 点了取消后执行
        });
    },
```

<!-- TOC --><a name="3-"></a>
## 3.商品服务-品牌管理

<!-- TOC --><a name="31-"></a>
### 3.1 添加商品模块并优化效果

**使用逆向生成的前后端代码：**

在菜单管理中给商品系统添加品牌管理：

![image-20230409010804531](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010135978.png)

找到之前逆向生成的`renren`，在`renren\main\resources\src\views\modules\product`下找到前端代码

![image-20230409022124143](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010136783.png)



将`brand.vue`和`brand-add-or-update.vue`拷贝到`renren-fast-vue`的`product`模块：

![image-20230409024137151](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010136416.png)

启动前端项目后查看品牌管理模块，发现品牌管理模块没有`新增`、`删除`等模块。原因：`brand.vue`中给`新增`、`删除`设置了权限

![image-20230409025616984](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010136203.png)



![image-20230409025820620](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010136583.png)

解决权限问题：

- 方法一：删除`v-if`及其绑定的权限
- 方法二：找到`isAuth`定义的权限判断，让其始终返回true

此处采用方法二，全局查找`isAuth`，发现`isAuth`是在`index.js`中定义的

![image-20230409030447052](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010136448.png)

修改`index.js`中的` isAuth`:

```js
export function isAuth (key) {
  // return JSON.parse(sessionStorage.getItem('permissions') || '[]').indexOf(key) !== -1 || false
  return true
}
```

保存`index.js`的修改，品牌管理的出现`新增`、`删除`按钮：

![image-20230409031238053](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137872.png)

**效果优化与快速显示开关：**

优化一：逆向生成后列的显示值是数据库的备注，需要进行适当修改

![image-20230409122917584](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137080.png)

在`brand.vue`中修改label标签的显示值

```vue
      <el-table-column
        prop="showStatus"
        header-align="center"
        align="center"
        label="显示状态">
```

优化二：在`显示状态`列添加一个开关，如果该行`显示\不显示`，则开关`开启\关闭`。也可以通过调节开关来决定品牌显示与否。在`element-ui`找到`table表格`中的`自定义列模板`

![image-20230409125046091](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137833.png)



![image-20230409130146571](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137968.png)





![image-20230409130501459](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137407.png)



`table`表格代码：通过`slot-scope`获取到 row, column, $index 和store (table 内部的状态管理)的数据

```vue
      <template slot-scope="scope">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{ scope.row.date }}</span>
      </template>
```

`switch`开关：

```vue
<el-switch
  v-model="value"
  active-color="#13ce66"
  inactive-color="#ff4949">
</el-switch>
```

在`显示状态`列结合`table表格`和`switch开关`添加开关：

```vue
      <el-table-column
        prop="showStatus"
        header-align="center"
        align="center"
        label="显示状态"
      >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.showStatus"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
        </template>
      </el-table-column>
```

效果：

![image-20230409131927718](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137136.png)



监听`switch开关`中

![image-20230409135510195](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010137049.png)

数据库中showStatus是0\1，开关默认值是true/false。 所以在开关中设置`:active-value="1" 、:inactive-value="0"`属性，与数据库同步。同时监听开关触发`updateBrandStatus(scope.row)`函数来向后台发送修改请求，更新数据库。`scope.row`可以获取当前行的数据信息

```vue
   <!--brand.vue中显示状态那一列-->   
   <el-table-column prop="showStatus" header-align="center" align="center" label="显示状态">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.showStatus" :active-value="1" :inactive-value="0" active-color="#13ce66" inactive-color="#ff4949" @change="updateBrandStatus(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
```

`updateBrandStatus`函数

```js
    // 修改品牌的显示状态
    updateBrandStatus(data) {
      console.log("最新信息", data);
      let { brandId, showStatus } = data;
      // 发送请求修改最新信息
      this.$http({
        url: this.$http.adornUrl("/product/brand/update"),
        method: "post",
        data: this.$http.adornData(
          { brandId, showStatus: showStatus ? 1 : 0 },
          false
        ),
      }).then(({ data }) => {
        this.$message({
          type: "success",
          message: "状态更新成功",
        });
      });
    },
```

同样在`新增`的弹框里也要调整：在`显示状态`处添加按钮

```vue
      <el-form-item label="显示状态" prop="showStatus">
        <el-switch
          v-model="dataForm.showStatus"
          active-color="#13ce66"
          inactive-color="#ff4949"
        ></el-switch>
      </el-form-item>
```

![image-20230409134056713](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010138015.png)

<!-- TOC --><a name="32-"></a>
### 3.2 文件上传功能

<!-- TOC --><a name="321-springcloud-alibaba-oss"></a>
#### 3.2.1 SpringCloud Alibaba-OSS使用

在单体应用中文件通常上传到系统中保存。分布式架构下，如果将文件上传到每个服务显然是不太好的，这是可以将文件存储的功能独立出来。可以自建服务器来存储文件，也可以采用云存储来存储文件

![image-20230409152251167](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010138728.png)



前端如何将文件上传到OSS对象存储服务中？

- 方案一：让浏览器直接上传给对象存储OSS，只需将对象存储相关的密码写在JS代码中，JS代码提供表单提交直接将文件上传给OSS。此种方式会出现密码泄露的风险，所以不采用。

- 方案二：前端将文件上传到应用服务器，应用服务器再上传到OSS。在本项目中就是前端将文件上传到`gulimall-gateway`网关，网关再将文件传到`gulimall-product`商品服务，商品服务最后将文件上传OSS
- 方案三：服务端签名后直传。将密码等信息存储在应用服务器中，即存到`gulimall-product`商品服务中。前端要上传文件时先到`gulimall-product`商品服务获取一个防伪的签名，再携带这个签名和文件上传到阿里云



 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010138888.png" > <b>普通上传方式</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010148130.png" > <b>服务端签名后直传</b></td>
    </tr>
    </table>




开通阿里云OSS服务：

![image-20230409154334078](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010149232.png)

对象存储OSS官方文档：`https://help.aliyun.com/product/31815.html?spm=5176.8465980.guide.1.4e701450FwQ0ks`

创建bucket

![image-20230409155943830](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010149167.png)



开通子账户：

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010151495.png" > <b>1</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010151351.png" > <b>2</b></td>
    </tr>
    </table>




给新账户添加权限：

![image-20230409174654279](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010152969.png)



**上传文件的实现方式有两种：**

- 方法一：使用官方提供的原生的SDK进行上传
- 方法二：使用SpringCloud Alibaba提供的组件`SpringCloud Alibaba-OSS`来上传文件

**方法一实践：**

在官方文档的`SDK示例`中找到Java示例代码，文档地址：`https://help.aliyun.com/document_detail/32007.html?spm=a2c4g.609604.0.0.4b2e639bpLTDET`

在`gulimall-product`中导入SDK依赖：

```xml
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>3.5.0</version>
        </dependency>
```

上传测试：

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseCrudTest {
    
    @Test
    public void OSSUpload() throws FileNotFoundException {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "accessKeyId";
        String accessKeySecret = "accessKeySecret";

        String bucketName = "gulimall-bucket";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "mypic.jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath = "D:\\gitee\\mypic.jpg";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //上传文件流。
        InputStream inputStream = new FileInputStream(filePath);
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功.");
    }
}
```

上传成功：

![image-20230409192744387](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010139551.png)



**方法二：**使用`SpringCloud Alibaba-OSS`实现上传功能

- 对象存储服务(Object Storage Service, OSS)是一种海量、安全、低成本、高可靠的云存储服务，适合存放任意类型的文件。容量和处理能力弹性扩展，多种存储类型供选择，全面优化存储成本
- `spring-cloud-alibaba`官方地址：https://github.com/alibaba/spring-cloud-alibaba 。在官方文档中找到`SpringCloud Alibaba-OSS`的使用示例`https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample`
- 导入依赖：

```xml
        <!--  引入SpringCloud Alibaba-OSS-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alicloud-oss</artifactId>
        </dependency>
```

- 在`gulimall-product`商品服务的配置文件`application.yml`中添加配置：

```yaml
spring:
  cloud:
    alicloud:
      access-key: access-key
      secret-key: secret-key
      oss:
        endpoint: oss-cn-shenzhen.aliyuncs.com
```

- 注入 OSSClient即可直接上传文件：

```java
package com.atguigu.gulimail.product;
import com.aliyun.oss.OSSClient;
import com.atguigu.gulimail.product.entity.BrandEntity;
import com.atguigu.gulimail.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseCrudTest {
    // 使用`SpringCloud Alibaba-OSS`实现上传功能
    @Autowired
    private OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {

        String bucketName = "gulimall-bucket";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "pic.jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath = "D:\\gitee\\pic.jpg";
        // 创建OSSClient实例。

        //上传文件流。
        InputStream inputStream = new FileInputStream(filePath);
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功.");
    }
}
```

上传成功：

![image-20230409200350478](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010139855.png)

<!-- TOC --><a name="322-"></a>
#### 3.2.2 建立第三方服务,实现服务端签名后直传

上面演示了用`SpringCloud Alibaba-OSS`上传文件，但是我们不会采用这种在服务端上传文件的方式。而是采用服务端签名后直传的方式，故在此处建立第三方工程来整合`服务端签名后直传`功能，后续还可以在这个第三方服务中添加`查物流`、`发短信`等功能

**1.新建gulimall-third-party模块**

创建`gulimall-third-party`模块，引入如下依赖：

```xml
    <properties>
        <java.version>1.8</java.version>
        <!--        <spring-cloud.version>2021.0.5</spring-cloud.version>-->
        <spring-cloud.version>Greenwich.SR3</spring-cloud.version>
    </properties>


    <dependencies>
        <dependency>
            <groupId>com.atguigu.gulimail</groupId>
            <artifactId>gulimail-common</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <exclusions>
                <exclusion>
                    <groupId>com.baomidou</groupId>
                    <artifactId>mybatis-plus-boot-starter</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>


        <!--  引入SpringCloud Alibaba-OSS-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alicloud-oss</artifactId>
        </dependency>

    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>


    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

在nacos新建命名空间,并新建配置`oss.yml`配置



 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010152634.png" > <b>新建命名空间</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010153184.png" > <b>新建配置`oss.yml`:</b></td>
    </tr>
    </table>




nacos配置中心的`oss.yml`配置内容（为了方便可以直接写在本地application.yml配置文件中）：

```yml
spring:
  cloud:
   alicloud:
     access-key: xxx
     secret-key: xxx
     oss:
       endpoint: oss-cn-shenzhen.aliyuncs.com
```

bootstrap.properties配置：

```properties
spring.application.name=gulimall-third-party
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
spring.cloud.nacos.config.namespace=02f3de47-b3c2-42da-ba76-4fba6205c534

spring.cloud.nacos.config.ext-config[0].data-id=oss.yml
spring.cloud.nacos.config.ext-config[0].group=DEFAULT_GROUP
spring.cloud.nacos.config.ext-config[0].refresh=true
```

application.yml配置：

```yml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  application:
    name: gulimall-third-party
server:
  port: 30000
```

启动类上添加`@EnableDiscoveryClient`注解：

```java
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallThirdPartyApplication.class, args);
    }

}
```

将之前的上传测试类拷贝到`gulimall-third-party`服务，验证当前程序是否可用：

```java
package com.atguigu.gulimall;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallThirdPartyApplicationTests {

//    // 使用原生的OSS SDK上传文件 ,需要引入如下依赖：
//    /**
//     *         <dependency>
//     *             <groupId>com.aliyun.oss</groupId>
//     *             <artifactId>aliyun-sdk-oss</artifactId>
//     *             <version>3.5.0</version>
//     *         </dependency>
//     */
//
//    @Test
//    public void OSSUpload() throws FileNotFoundException {
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
//        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "xxx";
//        String accessKeySecret = "xxx";
//
//        String bucketName = "gulimall-bucket";
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "mypic.jpg";
//        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        String filePath = "D:\\gitee\\mypic.jpg";
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        //上传文件流。
//        InputStream inputStream = new FileInputStream(filePath);
//        ossClient.putObject(bucketName, objectName, inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        System.out.println("上传成功.");
//    }

    // 使用`SpringCloud Alibaba-OSS`实现上传功能
    @Autowired
    private OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {

        String bucketName = "gulimall-bucket";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "pic.jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath = "D:\\gitee\\pic.jpg";
        // 创建OSSClient实例。

        //上传文件流。
        InputStream inputStream = new FileInputStream(filePath);
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功.");
    }

}
```

**2.服务端签名后直传实现**

`服务端签名后直传`实现：参考官方文档：`https://help.aliyun.com/document_detail/31926.html?spm=a2c4g.173881.0.0.504c6c3asdKS61`

![image-20230410005201164](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010157863.png)

java代码示例：官方文档`https://help.aliyun.com/document_detail/91868.htm?spm=a2c4g.31926.0.0.6326774flzHzV7#concept-ahk-rfz-2fb`

![image-20230410011154240](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010158200.png)



在`gulimall-third-party`中新增`OssController`，参照官方文档中的`服务端签名后直传`示例代码，编写接口供前端获取签名，实现将一些常用的配置集中抽取到`application.yml`配置文件中：

```yml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
    alicloud:
      access-key: xxx
      secret-key: xxx
      oss:
        endpoint: oss-cn-shenzhen.aliyuncs.com
        bucket: gulimall-bucket

  application:
    name: gulimall-third-party
server:
  port: 30000
```

 OssController实现：

```java
package com.atguigu.gulimall.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.atguigu.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/4/10 1:18
 */

@RestController
public class OssController {

    @Resource
    private OSS ossClient;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;


    @RequestMapping("/oss/policy")
    public R policy() {

        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://" + bucket + "." + endpoint;
//        // 设置上传回调URL，即回调服务器地址，用于处理应用服务器与OSS之间的通信。OSS会在文件上传完成后，把文件上传信息通过此回调URL发送给应用服务器。
//        String callbackUrl = "https://192.168.0.0:8888";

        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        String dir = format + "/";

        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        return R.ok().put("data", respMap);
    }

}
```

测试：用postman访问`http://localhost:30000/oss/policy`接口，返回如下信息：

```json
{
    "msg": "success",
    "code": 0,
    "data": {
        "accessId": "xxx",
        "policy": "eyJleHBpcmF0aW9uIjoiMjAyMy0wNC0xMlQyMjoyMTo0My45MDVaIiwiY29uZGl0aW9ucyI6W1siY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjAwMF0sWyJzdGFydHMtd2l0aCIsIiRrZXkiLCIyMDIzLTA0LTEzLyJdXX0=",
        "signature": "YXcJNCYN0nK+cYUDbJtI0CiRfGs=",
        "dir": "2023-04-13/",
        "host": "https://gulimall-bucket.oss-cn-shenzhen.aliyuncs.com",
        "expire": "1681338103"
    }
}
```

**3.网关配置**

最终的微服务都需要通过网关访问，此处进行网关配置，在`gulimall-gateway`中添加配置：

```yml
spring:
  cloud:
    gateway:
      routes:
## 转发到第三方服务
        - id: third_party_route
          uri: lb://gulimall-third-party
          predicates:
            - Path=/api/thirdparty/**
          filters:
            - RewritePath=/api/thirdparty/(?<segment>.*),/$\{segment}
```

测试:重启网关，访问`http://localhost:88/api/thirdparty/oss/policy`，正确返回响应

<!-- TOC --><a name="323-oss"></a>
#### 3.2.3 OSS前后端联调测试上传

前端上传实现：使用`Element-ui`中的 `upload上传`  组件，此处直接从资料中复制`upload`文件夹到项目中即可

![image-20230412021452734](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010159518.png)



直接从资料中复制`upload`文件夹到项目中的`src\components`文件夹下：

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010159132.png" > <b>1</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010202007.png" > <b>2</b></td>
    </tr>
    </table>




将action中的地址替换成bucket域名

![image-20230412031702316](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010206220.png)

点击上传后出现跨域问题：

![image-20230413063151039](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010206092.png)

解决跨域问题：在OSS控制台配置跨域规则

![image-20230413064229804](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010206283.png)



<!-- TOC --><a name="33-"></a>
### 3.3 前端表单校验

<!-- TOC --><a name="331-logo"></a>
#### 3.3.1 logo图片显示

**自定义`品牌logo`显示**：

目前在品牌logo的位置显示的是文本信息，我们想要在此处显示图片。修改：利用`Element-UI`中的`table 表格`的`自定义模板`更改显示

![image-20230429171120044](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010207978.png)

利用`Element-UI`中的`table 表格`的`自定义模板`更改显示，找到`table 表格`的`自定义模板`，复制下列内容：

![image-20230429165006094](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010207916.png)



```vue
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
```

要在`自定义模板`中显示图片，继续找到`Image 图片`组件，复制下列内容：

![image-20230429165444715](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010208581.png)



```vue
    <el-image
      style="width: 100px; height: 100px"
      :src="url"
      :fit="fit"></el-image>
```

在`brand.vue`中利用上述 `table 表格`组件  和  `Image 图片`组件，将`品牌logo地址`显示代码修改如下：

```vue
      <el-table-column prop="logo" header-align="center" align="center" label="品牌logo地址">
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 80px" :src="scope.row.logo" fit="contain"></el-image>
        </template>
      </el-table-column>
```

**错误处理：**

更改后图片没有正常显示，控制台`console`报错如下，报错提示我们没有导入`image组件`。查看查看`src\element-ui\index.js`，发现原有的脚手架确实没有导入该组件。解决方法：重新导入缺少的`image`组件。为了开发方便，可以将`快速上手`部分的导入示例复制到项目中，从而直接导入最新版的全部组件

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010208982.png" > <b>1</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010209154.png" > <b>2</b></td>
    </tr>
    </table>


导入最新版的全部组件：

```js
import {
  Pagination,
  Dialog,
  Autocomplete,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Menu,
  Submenu,
  MenuItem,
  MenuItemGroup,
  Input,
  InputNumber,
  Radio,
  RadioGroup,
  RadioButton,
  Checkbox,
  CheckboxButton,
  CheckboxGroup,
  Switch,
  Select,
  Option,
  OptionGroup,
  Button,
  ButtonGroup,
  Table,
  TableColumn,
  DatePicker,
  TimeSelect,
  TimePicker,
  Popover,
  Tooltip,
  Breadcrumb,
  BreadcrumbItem,
  Form,
  FormItem,
  Tabs,
  TabPane,
  Tag,
  Tree,
  Alert,
  Slider,
  Icon,
  Row,
  Col,
  Upload,
  Progress,
  Spinner,
  Badge,
  Card,
  Rate,
  Steps,
  Step,
  Carousel,
  CarouselItem,
  Collapse,
  CollapseItem,
  Cascader,
  ColorPicker,
  Transfer,
  Container,
  Header,
  Aside,
  Main,
  Footer,
  Timeline,
  TimelineItem,
  Link,
  Divider,
  Image,
  Calendar,
  Backtop,
  PageHeader,
  CascaderPanel,
  Loading,
  MessageBox,
  Message,
  Notification
} from 'element-ui';

Vue.use(Pagination);
Vue.use(Dialog);
Vue.use(Autocomplete);
Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Input);
Vue.use(InputNumber);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(RadioButton);
Vue.use(Checkbox);
Vue.use(CheckboxButton);
Vue.use(CheckboxGroup);
Vue.use(Switch);
Vue.use(Select);
Vue.use(Option);
Vue.use(OptionGroup);
Vue.use(Button);
Vue.use(ButtonGroup);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(DatePicker);
Vue.use(TimeSelect);
Vue.use(TimePicker);
Vue.use(Popover);
Vue.use(Tooltip);
Vue.use(Breadcrumb);
Vue.use(BreadcrumbItem);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Tabs);
Vue.use(TabPane);
Vue.use(Tag);
Vue.use(Tree);
Vue.use(Alert);
Vue.use(Slider);
Vue.use(Icon);
Vue.use(Row);
Vue.use(Col);
Vue.use(Upload);
Vue.use(Progress);
Vue.use(Spinner);
Vue.use(Badge);
Vue.use(Card);
Vue.use(Rate);
Vue.use(Steps);
Vue.use(Step);
Vue.use(Carousel);
Vue.use(CarouselItem);
Vue.use(Collapse);
Vue.use(CollapseItem);
Vue.use(Cascader);
Vue.use(ColorPicker);
Vue.use(Transfer);
Vue.use(Container);
Vue.use(Header);
Vue.use(Aside);
Vue.use(Main);
Vue.use(Footer);
Vue.use(Timeline);
Vue.use(TimelineItem);
Vue.use(Link);
Vue.use(Divider);
Vue.use(Image);
Vue.use(Calendar);
Vue.use(Backtop);
Vue.use(PageHeader);
Vue.use(CascaderPanel);
```

更新后报错：原因，当前版本没有最新版的如下组件。解决：只需将多余导入的组件删去即可。

![image-20230429173533637](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010210533.png)

发现图片加载出现问题，将上述图片组件`image`替换为原生的`img`标签，修改`brand.vue`如下：

```vue
      <el-table-column prop="logo" header-align="center" align="center" label="品牌logo地址">
        <template slot-scope="scope">
          <!-- <el-image style="width: 100px; height: 80px" :src="scope.row.logo" fit="fill"></el-image> -->
          <img :src="scope.row.logo" style="width: 100px; height: 80px">
        </template>
      </el-table-column>
```

此时图片正确显示：

![image-20230429174841084](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010210858.png)

<!-- TOC --><a name="332-"></a>
#### 3.3.2 前端表单校验实现

**前端表单校验实现：**

利用`Form 表单`中的`自定义校验规则`来实现校验：

![image-20230429184252742](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010211890.png)



`src\views\modules\product\brand-add-or-update.vue` 校验的完整实现：

```vue
<template>
  <el-dialog :title="!dataForm.brandId ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="品牌名" prop="name">
        <el-input v-model="dataForm.name" placeholder="品牌名"></el-input>
      </el-form-item>
      <el-form-item label="品牌logo地址" prop="logo">
        <!-- <el-input v-model="dataForm.logo" placeholder="品牌logo地址"></el-input> -->
        <single-upload v-model="dataForm.logo"></single-upload>
      </el-form-item>
      <el-form-item label="介绍" prop="descript">
        <el-input v-model="dataForm.descript" placeholder="介绍"></el-input>
      </el-form-item>
      <el-form-item label="显示状态" prop="showStatus">
        <el-switch v-model="dataForm.showStatus" active-color="#13ce66" inactive-color="#ff4949" :active-value="1" :inactive-value="0"></el-switch>
      </el-form-item>
      <el-form-item label="检索首字母" prop="firstLetter">
        <el-input v-model="dataForm.firstLetter" placeholder="检索首字母"></el-input>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input v-model.number="dataForm.sort" placeholder="排序"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import singleUpload from "@/components/upload/singleUpload.vue";
export default {
  components: { singleUpload: singleUpload },
  data() {
    return {
      visible: false,
      dataForm: {
        brandId: 0,
        name: "",
        logo: "",
        descript: "",
        showStatus: 1,
        firstLetter: "",
        sort: 0,
      },
      dataRule: {
        name: [{ required: true, message: "品牌名不能为空", trigger: "blur" }],
        logo: [
          { required: true, message: "品牌logo地址不能为空", trigger: "blur" },
        ],
        descript: [
          { required: true, message: "介绍不能为空", trigger: "blur" },
        ],
        showStatus: [{ required: true, message: "显示状态", trigger: "blur" }],
        firstLetter: [
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("首字母必须填写"));
              } else if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error("首字母必须a-z或者A-Z之间"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
        sort: [
          {
            validator: (rule, value, callback) => {
              if (value == "") {
                callback(new Error("排序字段必须填写"));
              } else if (!Number.isInteger(value) || value < 0) {
                callback(new Error("排序字段必须是一个大于等于0的整数"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    init(id) {
      this.dataForm.brandId = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.brandId) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/info/${this.dataForm.brandId}`
            ),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.name = data.brand.name;
              this.dataForm.logo = data.brand.logo;
              this.dataForm.descript = data.brand.descript;
              this.dataForm.showStatus = data.brand.showStatus;
              this.dataForm.firstLetter = data.brand.firstLetter;
              this.dataForm.sort = data.brand.sort;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/product/brand/${!this.dataForm.brandId ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              brandId: this.dataForm.brandId || undefined,
              name: this.dataForm.name,
              logo: this.dataForm.logo,
              descript: this.dataForm.descript,
              showStatus: this.dataForm.showStatus,
              firstLetter: this.dataForm.firstLetter,
              sort: this.dataForm.sort,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                },
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
};
</script>
```

<!-- TOC --><a name="34-"></a>
### 3.4 后端校验实现

**后端校验实现：**

1.给Bean添加校验注解,并定义自己的message提示 。 `@NotBlank  至少包含一个非空字符（ 不为空且不是"" ）`

```java
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空")
	private String name;
}
```

2.添加@Valid注解开启校验功能。在`gulimail-product`服务的`BrandController.java`中修改save接口：

```java
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brand){
		brandService.save(brand);
        return R.ok();
    }
```

3.测试： 效果：校验错误以后会有默认的响应。postman访问`http://localhost:88/api/product/brand/save`,body提交一个空的name: `{"name":""}` ,返回如下信息：

```json
{
    "timestamp": "2023-04-29T12:15:11.652+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotBlank.brandEntity.name",
                "NotBlank.name",
                "NotBlank.java.lang.String",
                "NotBlank"
            ],
            "arguments": [
                {
                    "codes": [
                        "brandEntity.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                }
            ],
            "defaultMessage": "品牌名不能为空",
            "objectName": "brandEntity",
            "field": "name",
            "rejectedValue": "",
            "bindingFailure": false,
            "code": "NotBlank"
        }
    ],
    "message": "Validation failed for object='brandEntity'. Error count: 1",
    "path": "/product/brand/save"
}
```

4.给校验的bean后紧跟一个BindingResult，就可以获取到校验的结果。在`gulimail-product`服务的`BrandController.java`中修改save接口：

```java
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brand, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            // 获取校验的错误结果 item类型为FieldError
            result.getFieldErrors().forEach((item) -> {
                // 获取到错误提示
                String message = item.getDefaultMessage();
                // 获取错误的属性名字
                String field = item.getField();
                map.put(field, message);
            });
            return R.error(400, "提交的数据不合法").put("data", map);
        } else {
            brandService.save(brand);
        }
        return R.ok();
    }
```

5.继续3中的测试，返回如下信息：

```json
{
    "msg": "提交的数据不合法",
    "code": 400,
    "data": {
        "name": "品牌名不能为空"
    }
}
```

6.继续添加其他校验：

```java
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名不能为空")
    private String name;
    /**
     * 品牌logo地址
     */
    @NotEmpty
    @URL(message = "logo必须是一个合法的url地址")
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotEmpty
    @Pattern(regexp = "/^[a-zA-Z]$/", message = "检索首字母必须是一个字母")
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sort;

}
```

5.测试：仍然发送`{"name":""}` ,返回如下信息：

```json
{
    "msg": "提交的数据不合法",
    "code": 400,
    "data": {
        "name": "品牌名不能为空",
        "logo": "不能为空",
        "sort": "不能为null",
        "firstLetter": "不能为空"
    }
}
```

**后端校验总结：**

```sh
3、JSR303
    1）、给Bean添加校验注解: 参考javax.validation.constraints包下的注解，并定义自己的message提示
    2)、开启校验功能@Valid
          效果：校验错误以后会有默认的响应；
    3）、给校验的bean后紧跟一个BindingResult，就可以获取到校验的结果
    4）、分组校验（多场景的复杂校验）
          1)、	@NotBlank(message = "品牌名必须提交",groups = {AddGroup.class,UpdateGroup.class})
              给校验注解标注什么情况需要进行校验
          2）、@Validated({AddGroup.class})
          3)、默认没有指定分组的校验注解@NotBlank，在分组校验情况@Validated({AddGroup.class})下不生效，只会在@Validated生效；

   5）、自定义校验
        1）、编写一个自定义的校验注解
        2）、编写一个自定义的校验器 ConstraintValidator
        3）、关联自定义的校验器和自定义的校验注解
               @Documented
               @Constraint(validatedBy = { ListValueConstraintValidator.class【可以指定多个不同的校验器，适配不同类型的校验】 })
               @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
               @Retention(RUNTIME)
               public @interface ListValue {
```

<!-- TOC --><a name="35-"></a>
### 3.5 统一的异常处理

上面利用BindingResult，获取到校验的结果返回给前端。如果每个类都这样写就太麻烦了，通常都会统一进行异常处理，达到简化、复用代码、优化结构的目的。

1.在`gulimail-common`中新增`exception`包，添加枚举类`BizCodeEnum`，用于统一异常响应码：

```java
/***
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *      002：短信验证码频率太高
 *  11: 商品
 *  12: 订单
 *  13: 购物车
 *  14: 物流
 *  15: 用户
 *  21：库存
 *
 *
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    TOO_MANY_REQUEST(10002,"请求流量过大"),
    SMS_CODE_EXCEPTION(10002,"验证码获取频率太高，稍后再试"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常"),
    USER_EXIST_EXCEPTION(15001,"用户存在"),
    PHONE_EXIST_EXCEPTION(15002,"手机号存在"),
    NO_STOCK_EXCEPTION(21000,"商品库存不足"),
    LOGINACCT_PASSWORD_INVAILD_EXCEPTION(15003,"账号密码错误");

    private int code;
    private String msg;
    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
```

2.注释掉之前的save接口，还原为最简单的形式。（如果在接口声明时带上参数BindingResult result，则异常会被捕获，并在 if (result.hasErrors())这个逻辑里进行处理。不带这个参数则会被抛出，有全局异常处理类处理）

```java
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@Valid @RequestBody BrandEntity brand, BindingResult result) {
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            // 获取校验的错误结果 item类型为FieldError
//            result.getFieldErrors().forEach((item) -> {
//                // 获取到错误提示
//                String message = item.getDefaultMessage();
//                // 获取错误的属性名字
//                String field = item.getField();
//                map.put(field, message);
//            });
//
//            return R.error(400, "提交的数据不合法").put("data", map);
//        } else {
//            brandService.save(brand);
//        }
//        return R.ok();
//    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return R.ok();
    }
```

3.在`gulimall-product`商品服务中添加`gulimail-common`依赖，同时添加`exception`包，添加统一异常处理类` ExceptionControllerAdvice`：

```java
package com.atguigu.gulimail.product.exception;


import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22418
 * @version 1.0
 * @description: 集中处理异常
 * @date 2023/4/29 23:16
 */
@Slf4j
//@ResponseBody  // 所有异常信息都要json格式信息响应出去
//@ControllerAdvice(basePackages = "com.atguigu.gulimail.product.controller")

// @RestControllerAdvice等效于 @ResponseBody + @ControllerAdvice
@RestControllerAdvice(basePackages = "com.atguigu.gulimail.product.controller")
public class ExceptionControllerAdvice {
    /**
     * 处理数据校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException ex){
        log.error("数据校验出现问题：{},异常类型：{}",ex.getMessage(),ex.getClass());
        BindingResult bindingResult = ex.getBindingResult();
        Map<String,String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",map);
    }

    /**
     * 处理任意类型的异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
```

<!-- TOC --><a name="36-"></a>
### 3.6 分组校验

在`新增`和`修改`brandEntity时，校验的规则可能不一样。此时就需要使用JSR303提供的分组校验功能。

1.在`gulimall-common`中添加`valid`包，并添加`AddGroup`类和`UpdateGroup`类

```java
public interface AddGroup {
}
```

```java
public interface UpdateGroup {
}
```

2.在实体类上添加分组校验，

```java
public class BrandEntity implements Serializable {
    /**
     * 品牌id
     */
    // 修改的时候必须不为空
    @NotNull(message = "修改必须指定品牌id",groups = {UpdateGroup.class})
    // 新增的时候必须为空
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    @TableId
    private Long brandId;
    private static final long serialVersionUID = 1L;

    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotEmpty(groups = {AddGroup.class})
    @URL(message = "logo必须是一个合法的url地址",groups = {AddGroup.class,UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotEmpty(groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母")
    private String firstLetter;
    /**
     * 排序
     */
    // 新增的时候一定不为空且长度大于0，修改的时候要么为空、不为空的话长度一定大于0
    @NotEmpty(groups = {AddGroup.class})
    @Min(value = 0, message = "排序必须大于等于0",groups = {AddGroup.class,UpdateGroup.class})
    private Integer sort;

}
```

3.在`BrandController`中添加`@Validated({AddGroup.class})`注解指定校验分组，此时`BrandEntity`没有标注分组的校验不起作用

```java
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }
```

分组校验总结：

```java
分组校验（多场景的复杂校验）
         1)、@NotBlank(message = "品牌名必须提交",groups = {AddGroup.class,UpdateGroup.class})
          给校验注解标注什么情况需要进行校验
         2）、@Validated({AddGroup.class})
         3)、默认没有指定分组的校验注解@NotBlank，在分组校验情况@Validated({AddGroup.class})下不生效，只会在@Validated生效；
```

<!-- TOC --><a name="37-"></a>
### 3.7 自定义注解

**接口调整**

在界面上可以通过`显示状态`开关按钮来更改显示状态，这个接口只需提供`showStatus`参数，所以需要再添加一个分组`UpdateStatusGroup`

![image-20230430145418381](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305010211580.png)

```java
public interface UpdateStatusGroup {
}
```

新增一个接口

```java
    /**
     * 修改显示状态
     */
    @RequestMapping("/updateShowStatus")
    public R updateShowStatus(@Validated({UpdateStatusGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);
        return R.ok();
    }
```

前端项目的`brand.vue`中调用`/product/brand/updateShowStatus`来更改状态

```js
    // 修改品牌的显示状态
    updateBrandStatus(data) {
      console.log("最新信息", data);
      let { brandId, showStatus } = data;
      // 发送请求修改最新信息
      this.$http({
        url: this.$http.adornUrl("/product/brand/updateShowStatus"),
        method: "post",
        data: this.$http.adornData(
          { brandId, showStatus: showStatus ? 1 : 0 },
          false
        ),
      }).then(({ data }) => {
        this.$message({
          type: "success",
          message: "状态更新成功",
        });
      });
    },
```

**自定义注解**

显示状态只能填0和1，我们尝试自定义一个规则来进行校验。首先参考`@NotEmpty`等校验注解在`gulimall-common`中声明一个校验注解`@ListValue` ,其中`message()`,` groups()`,` payload()`是校验注不能少的，由于`显示状态`是的合法值由0、1组成，所以定义一个int 数组`int[] values() `来接收合法的取值列表。同时声明一个`ListValueConstraintValidator`来实现校验规则。仿照其他校验注解，这里将提示信息`message`的信息提取到`ValidationMessages.properties`中统一管理

```java
@Documented
@Constraint(
        validatedBy = {ListValueConstraintValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {

    String message() default "{com.atguigu.common.valid.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] values() default {};
}
```

将默认提示信息`message`的信息提取到`ValidationMessages.properties`中统一管理`src/main/resources/ValidationMessages.properties`配置如下：

```properties
com.atguigu.common.valid.ListValue.message=必须提交指定的值
```

自定义校验规则` ListValueConstraintValidator`实现：首先继承`ConstraintValidator<ListValue, Integer>`,并通过泛型关联`@ListValue`注解、指明要校验的类型为`Integer`。校验逻辑：获取注解中的`value`列表值并添加到集合中，如果提交的`private Integer showStatus`不在此集合中，则校验不通过

```java
package com.atguigu.common.valid;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set = new HashSet<>();

    // 初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            set.add(value);
        }
    }
    // 判断是否校验成功
    /**
     * @param value                      需要校验的值
     * @param constraintValidatorContext 校验的上下文信息
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}
```

使用自定义注解` @ListValue`：通过`values`指定合法的取值列表，通过`groups`指定校验分组，`message`采用默认值

```java
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ListValue(values = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class})
    private Integer showStatus;
```

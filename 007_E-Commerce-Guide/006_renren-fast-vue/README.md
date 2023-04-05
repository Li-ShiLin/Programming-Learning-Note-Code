## 1.renren-fast-vue介绍
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

## 2.三级分类前端编写

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

![image-20230405182706028](C:\Users\22418\AppData\Roaming\Typora\typora-user-images\image-20230405182706028.png)



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


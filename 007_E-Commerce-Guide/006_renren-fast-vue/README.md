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

**商品服务三级分类前端项目编写：**

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

在Element-ui上找到`Tree 树形控件`    位置 ` https://element.eleme.cn/#/zh-CN/component/tree`。将
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

访问`http://localhost:8001/#/product-category`,可以看到如下效果：



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

菜单删除功能编写：


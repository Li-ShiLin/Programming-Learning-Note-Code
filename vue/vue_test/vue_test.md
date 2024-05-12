目录结构：将目录`数字编号_src_模块名称`改成`src`，然后执行命令`npm run serve`，就可以查看对应知识点的代码执行效果

## 1.vue-cli（Vue脚手架）

###  1.1 vue-cli初步使用

#####  1.1.1 vue-cli安装

**vue-cli简介**：

- vue-cli文档: `https://cli.vuejs.org/zh/`
- Vue CLI（Vue脚手架）是Vue官方提供的标准化开发工具(开发平台)。Vue CLI 是一个基于 Vue.js 开发的标准工具，用于快速生成和配置新的项目。使用 Vue CLI 创建的项目具有一定的标准目录结构，这有助于开发者快速了解和上手项目

**脚手架下载安装**：

第一步(仅第一次执行)：全局安装@vue/cli

```sh
npm install -g @vue/cli
# OR
yarn global add @vue/cli
```

第二步：切换到要创建项目的目录，然后使用命令创建项目

```sh
vue create my-vue-app
# OR
vue ui
```

第三步：启动项目

```sh
# cd到my-vue-app目录后执行npm run serve
npm run serve
```

注意：

```
1.如出现下载缓慢请配置npm淘宝镜像:  npm config set registry https://registry.npm.taobao.org
2.Vue脚手架隐藏了所有webpack相关的配置，若想查看具体的webpakc配置，请执行:vue inspect > output.js
```

#####  1.1.2 模板项目的目录结构

使用 Vue CLI 生成的项目的典型目录结构解释：

```
my-vue-app/
├── node_modules/
├── public/
│   ├── favicon.ico      页签图标
│   └── index.html       主页面
├── src/
│   ├── assets/          存放静态资源
│   ├── components/      存放组件
|   |   └──HelloWorld.vue
│   ├── router/
│   ├── store/
│   ├── views/
│   ├── App.vue           汇总所有组件
│   └── main.js           入口文件
├── .gitignore            git版本管制忽略的配置 
├── babel.config.js       label的配置文件 
├── package-lock.json     包版本控制文件 
├── package.json          应用包配置 
└── README.md             应用描述文件 
# 注意：
# 这个结构不是固定不变的，可以根据项目的需要进行调整。但是，遵循一定的结构可以帮助提高项目的可维护性
```

各个目录和文件的作用如下：

- `node_modules/`: 存放项目依赖的所有第三方模块
- `public/`: 存放静态资源，如 `index.html` 入口文件和图标等。在构建时，这里的内容会被复制到输出目录
  - `favicon.ico`: 网站的图标
  - `index.html`: 应用的入口 HTML 文件
- `src/`: 存放项目的源代码
  - `assets/`: 存放静态资源文件，如图片、样式表等
  - `components/`: 存放 Vue 组件
  - `router/`: 如果使用了 vue-router，此目录用于定义路由
  - `store/`: 如果使用了 Vuex，此目录用于定义状态管理
  - `views/`: 存放视图组件，通常与路由一一对应
  - `App.vue`: 是应用的根组件
  - `main.js`: 是应用的入口文件，用于创建 Vue 实例并挂载到 DOM
- `.gitignore`: 指定 Git 忽略跟踪的文件和目录
- `babel.config.js`: Babel 的配置文件，用于设置代码转译规则
- `package-lock.json` 和 `package.json`: 这两个文件用于管理项目依赖
- `README.md`: 项目的说明文件，通常包含项目信息、构建步骤等

#####  1.1.3 入门项目实现

基于脚手架生成的项目进行改造和扩展

`School.vue`：自定义组件

```vue
<template>
	<div class="demo">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
		<button @click="showName">点我提示学校名</button>	
	</div>
</template>

<script>
	 export default {
		name:'School',
		data(){
			return {
				name:'尚硅谷',
				address:'北京昌平'
			}
		},
		methods: {
			showName(){
				alert(this.name)
			}
		},
	}
</script>

<style>
	.demo{
		background-color: orange;
	}
</style>
```

`Student.vue`：自定义组件

```vue
<template>
	<div>
		<h2>学生姓名：{{name}}</h2>
		<h2>学生年龄：{{age}}</h2>
	</div>
</template>

<script>
	 export default {
		name:'Student',
		data(){
			return {
				name:'张三',
				age:18
			}
		}
	}
</script>
```

`App.vue`：作为应用的根组件，`App.vue`通常负责布局的顶层结构，例如导航栏、侧边栏或页脚等

```vue
<template>
	<div>
		<img src="./assets/logo.png" alt="logo">
		<School></School>
		<Student></Student>
	</div>
</template>

<script>
	//引入组件
	import School from './components/School'
	import Student from './components/Student'

	export default {
		name:'App',
		components:{
			School,
			Student
		}
	}
</script>
```

`main.js`：是整个项目的入口文件，负责创建 Vue 应用的实例、挂载根组件、引入所需的插件等

```js
/* 
	main.js文件是整个项目的入口文件
*/
//引入Vue
import Vue from 'vue'
//引入App组件，它是所有组件的父组件
import App from './App.vue'
//关闭vue的生产提示
Vue.config.productionTip = false

/* 
	关于不同版本的Vue：
	
		1.vue.js与vue.runtime.xxx.js的区别：
				(1).vue.js是完整版的Vue，包含：核心功能+模板解析器。
				(2).vue.runtime.xxx.js是运行版的Vue，只包含：核心功能；没有模板解析器。

		2.因为vue.runtime.xxx.js没有模板解析器，所以不能使用template配置项，需要使用
			render函数接收到的createElement函数去指定具体内容。
*/

//创建Vue实例对象---vm
new Vue({
	el:'#app',
	//render函数完成了这个功能：将App组件放入容器中
  render: h => h(App),
	// render:q=> q('h1','你好啊')

	// template:`<h1>你好啊</h1>`,
	// components:{App},
})
```

`index.html`：Vue 应用的入口页面，通常情况下，Vue 会通过这个页面来挂载和启动整个应用

```html
<!DOCTYPE html>
<html lang="">
  <head>
    <meta charset="utf-8">
		<!-- 针对IE浏览器的一个特殊配置，含义是让IE浏览器以最高的渲染级别渲染页面 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 开启移动端的理想视口 -->
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
		<!-- 配置页签图标 -->
    <link rel="icon" href="<%= BASE_URL %>favicon.ico">
		<!-- 引入第三方样式 -->
		<link rel="stylesheet" href="<%= BASE_URL %>css/bootstrap.css">
		<!-- 配置网页标题 -->
    <title>硅谷系统</title>
  </head>
  <body>
		<!-- 当浏览器不支持js时noscript中的元素就会被渲染 -->
    <noscript>
      <strong>We're sorry but <%= htmlWebpackPlugin.options.title %> doesn't work properly without JavaScript enabled. Please enable it to continue.</strong>
    </noscript>
		<!-- 容器 -->
    <div id="app"></div>
    <!-- built files will be auto injected -->
  </body>
</html>
```

#####  1.1.4 render函数

`main.js` ：

```js
/* 
	main.js文件是整个项目的入口文件
*/
//引入Vue
import Vue from 'vue'
//引入App组件，它是所有组件的父组件
import App from './App.vue'
//关闭vue的生产提示
Vue.config.productionTip = false

/* 
	关于不同版本的Vue：
	
		1.vue.js与vue.runtime.xxx.js的区别：
				(1).vue.js是完整版的Vue，包含：核心功能+模板解析器。
				(2).vue.runtime.xxx.js是运行版的Vue，只包含：核心功能；没有模板解析器。

		2.因为vue.runtime.xxx.js没有模板解析器，所以不能使用template配置项，需要使用
			render函数接收到的createElement函数去指定具体内容。
*/

//创建Vue实例对象---vm
new Vue({
	el:'#app',
	//render函数完成了这个功能：将App组件放入容器中
  render: h => h(App),
	// render:q=> q('h1','你好啊')

	// template:`<h1>你好啊</h1>`,
	// components:{App},
})
```

**`render` 函数**：在 `main.js` 中，`render` 函数通常被用来生成并渲染根组件。Vue 使用一个名为 `render` 的选项，来定义根实例的渲染输出。这个函数接收一个参数 `createElement`，常被简写为 `h`，并且返回 `createElement` 的调用结果。这个调用结果通常是根组件 `App.vue` 的渲染结果

```javascript
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
```

示例中，`render` 函数的作用是渲染 `App.vue` 组件，并将其挂载到 HTML 页面中 id 为 `app` 的元素上。具体来说：

- `import Vue from 'vue'`: 引入 Vue 库
- `import App from './App.vue'`: 引入根组件 `App.vue`
- `import router from './router'` 和 `import store from './store'`: 分别引入 vue-router 和 Vuex 的配置，如果项目中使用了这些库的话
- `Vue.config.productionTip = false`: 阻止 vue 在启动时生成生产提示
- `new Vue({...}).$mount('#app')`: 创建 Vue 实例并挂载到 DOM 元素上。这里的 `$mount('#app')` 等价于在选项中指定 `el: '#app'`

`render: h => h(App)`: 这是 `render` 函数的简写形式，其完整形式是：

```javascript
// 此函数创建并返回一个 `App` 组件的 Vue 实例，相当于模板中的 <App/>
// 通过这种方式，Vue 能够知道它需要渲染什么组件，并将该组件作为整个 Vue 应用的根组件
render: function(createElement) {
  return createElement(App);
}
```

#####  1.1.5 修改vue-cli默认配置

1.`Vue CLI`相关配置文档：`https://cli.vuejs.org/zh/config/`

2.Vue脚手架隐藏了所有webpack相关的配置，若想查看具体的webpakc配置，就在项目路径下执行`vue inspect > output.js`命令。使用`vue inspect > output.js`可以查看到Vue脚手架的默认配置

3.vue.config.js配置文件：使用vue.config.js可以对脚手架进行个性化定制

`vue.config.js`：新建`vue.config.js`配置文件，将自定义的配置写在`vue.config.js`文件中，vue会将`vue.config.js`中的配置和默认配置进行整合

```js
module.exports = {
  pages: {
    index: {
      //入口
      entry: 'src/main.js',
    },
  },
	lintOnSave:false, //关闭语法检查
	//开启代理服务器（方式一）
	/* devServer: {
    proxy: 'http://localhost:5000'
  }, */
	//开启代理服务器（方式二）
	devServer: {
    proxy: {
      '/atguigu': {
        target: 'http://localhost:5000',
				pathRewrite:{'^/atguigu':''},
        // ws: true, //用于支持websocket
        // changeOrigin: true //用于控制请求头中的host值
      },
      '/demo': {
        target: 'http://localhost:5001',
				pathRewrite:{'^/demo':''},
        // ws: true, //用于支持websocket
        // changeOrigin: true //用于控制请求头中的host值
      }
    }
  }
}
```

###  1.2 ref属性

```html
1.ref属性被用来给元素或子组件注册引用信息（id的替代者）
2.应用在html标签上获取的是真实DOM元素，应用在组件标签上是组件实例对象（vc）
3.使用方式：
        打标识： <h1 ref="xxx">.....</h1> 或 <School ref="xxx"></School>
        获取：   this.$refs.xxx
```

`School.vue`

```vue
<template>
	<div class="school">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
	export default {
		name:'School',
		data() {
			return {
				name:'尚硅谷',
				address:'北京·昌平'
			}
		},
	}
</script>

<style>
	.school{
		background-color: gray;
	}
</style>
```

`App.vue`

```vue
<template>
	<div>
		<h1 v-text="msg" ref="title"></h1>
		<button ref="btn" @click="showDOM">点我输出上方的DOM元素</button>
		<School ref="sch"/>
	</div>
</template>

<script>
	//引入School组件
	import School from './components/School'

	export default {
		name:'App',
		components:{School},
		data() {
			return {
				msg:'欢迎学习Vue！'
			}
		},
		methods: {
			showDOM(){
				console.log(this.$refs.title) //真实DOM元素
				console.log(this.$refs.btn) //真实DOM元素
				console.log(this.$refs.sch) //School组件的实例对象（vc）
			}
		},
	}
</script>
```



**`ref`属性总结**：在Vue中，`ref`属性是一个非常实用的特性，它可以直接访问到DOM元素或组件的实例。这在某些情况下非常有用，比如当需要直接操作DOM或访问子组件的方法时。`ref`在Vue 2和Vue 3中都可用，但它们的用法略有不同

1.**Vue 2中的`ref`**：在Vue 2中，可以在模板中的元素或组件上使用`ref`属性给元素或组件添加一个引用名。然后，可以通过`this.$refs`对象访问到这些元素或组件

```html
<template>
  <div>
    <input ref="myInput">
    <MyComponent ref="myComponent"></MyComponent>
  </div>
</template>

<script>
export default {
  mounted() {
    // 访问DOM元素
    this.$refs.myInput.focus();
    // 访问子组件实例
    console.log(this.$refs.myComponent);
  }
}
</script>
```

2.**Vue 3中的`ref`**：在Vue 3中，`ref`属性的基本用途与Vue 2相同，但Vue 3引入了Composition API，这意味着可以在`setup()`函数中使用`ref`来创建响应式数据

2.1 访问DOM元素和组件实例：在模板中的用法与Vue 2相似，可以在模板的元素或组件上使用`ref`属性，并通过`this.$refs`（选项API）或直接通过返回的对象（Composition API）访问它们

```html
<template>
  <div>
    <input ref="inputRef">
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const inputRef = ref(null);

onMounted(() => {
  inputRef.value.focus();
});
</script>
```

2.2 创建响应式数据：在Vue 3的Composition API中，`ref`还可以用来创建响应式的数据。这里的`ref`不是模板中的属性，而是从Vue的`reactivity`系统导入的一个函数

```javascript
import { ref } from 'vue';
const count = ref(0);
// 修改值
count.value = 1;
```

###  1.3  props配置项

```vue
1.功能：让组件接收外部传过来的数据
2.传递数据： <Demo name="xxx"/>
3.接收数据：
        3.1第一种方式（只接收）： props:['name']
        3.2第二种方式（限制类型）：props:{name:String}
        3.3第三种方式（限制类型、限制必要性、指定默认值）：
            props:{
                name:{
                type:String, //类型
                required:true, //必要性
                default:'老王' //默认值
                }
            }

备注：props是只读的，Vue底层会监测你对props的修改，如果进行了修改，就会发出警告，若业务需求确实需要修改，那么请复制props的内容到data中一份，然后去修改data中的数据
```

`Student.vue`

```vue
<template>
	<div>
		<h1>{{msg}}</h1>
		<h2>学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
		<h2>学生年龄：{{myAge+1}}</h2>
		<button @click="updateAge">尝试修改收到的年龄</button>
	</div>
</template>

<script>
	export default {
		name:'Student',
		data() {
			console.log(this)
			return {
				msg:'我是一个尚硅谷的学生',
				myAge:this.age
			}
		},
		methods: {
			updateAge(){
				this.myAge++
			}
		},
		//简单声明接收
		// props:['name','age','sex'] 

		//接收的同时对数据进行类型限制
		/* props:{
			name:String,
			age:Number,
			sex:String
		} */

		//接收的同时对数据：进行类型限制+默认值的指定+必要性的限制
		props:{
			name:{
				type:String, //name的类型是字符串
				required:true, //name是必要的
			},
			age:{
				type:Number,
				default:99 //默认值
			},
			sex:{
				type:String,
				required:true
			}
		}
	}
</script>
```

`App.vue`

```vue
<template>
	<div>
		<Student name="李四" sex="女" :age="18"/>
	</div>
</template>

<script>
	import Student from './components/Student'

	export default {
		name:'App',
		components:{Student}
	}
</script>
```

**props补充**：

1.**props 简介**：props 是 Vue.js 组件中的一个非常核心的概念，它用于父子组件之间的数据传递。Props 是 properties 的缩写，代表属性。在 Vue 中，props 是一种组件选项，允许外部环境传递数据给组件。基本上，你可以将 props 理解为组件的配置参数。Props 特性可以将数据从一个组件传递到另一个组件中，通常是从父组件传递到子组件

2.**Props 的基本用法**：

2.1 **定义 Props**： 在子组件中，需要定义props。可以通过组件的 `props` 选项完成，`props` 选项是数组或对象的形式

```javascript
// 1.使用数组形式
Vue.component('my-component', {
  props: ['title', 'message']
})
// 2.使用对象形式，可以指定类型、默认值和验证
Vue.component('my-component', {
  props: {
    title: String,
    message: {
      type: String,
      required: true,
      default: 'Hello World'
    }
  }
})
```

2.2 **传递 Props：** 在父组件的模板中，将数据通过自定义属性的方式传递给子组件

```html
<my-component title="Welcome" message="Hello Vue!"></my-component>
```

2.3 **使用 Props：** 在子组件内部，可以像使用本地数据一样使用 props

```html
<div>
  <h1>{{ title }}</h1>
  <p>{{ message }}</p>
</div>
```

3.**Props 的注意事项**

- **单向数据流：** Props 的主要目的是提供一种单向下行的数据流，从父组件流到子组件。这意味着如果在子组件内部修改 prop，Vue 会发出警告。如果需要修改，应该是基于 prop 的值来定义一个本地的 data 属性或计算属性
- **Prop 验证：** 在使用对象形式定义 props 时，可以为每个 prop 指定额外的选项，如类型检查、默认值和必需性验证。这有助于开发者捕获错误和不一致
- **动态 Props：** 如果想基于父组件的数据动态传递 prop，可以使用 `v-bind` 或简写为 `:`

```html
<my-component :message="parentMsg"></my-component>
```

### 1.4  mixin混入(混合)

```js
mixin混入(混合):
    1.功能:       可以把多个组件共用的配置提取成一个混入对象
    2.使用方式:
            2.1 第一步定义混合：
                    {
                        data(){....},
                        methods:{....}
                        ....
                    }
            2.2 第二步使用混入：
                    全局混入：Vue.mixin(xxx)
                    局部混入：mixins:['xxx']
```

`mixin.js`

```js
export const hunhe = {
	methods: {
		showName(){
			alert(this.name)
		}
	},
	mounted() {
		console.log('你好啊！')
	},
}
export const hunhe2 = {
	data() {
		return {
			x:100,
			y:200
		}
	},
}
```

`Student.vue`

```vue
<template>
	<div>
		<h2 @click="showName">学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
	</div>
</template>

<script>
	// import {hunhe,hunhe2} from '../mixin'

	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男'
			}
		},
		// mixins:[hunhe,hunhe2]
	}
</script>
```

`School.vue`

```vue
<template>
	<div>
		<h2 @click="showName">学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
    // 引入一个混合
	// 引入一个hunhe

	// 1.局部混合
	// import {hunhe,hunhe2} from '../mixin'

	export default {
		name:'School',
		data() {
			return {
				name:'尚硅谷',
				address:'北京',
				x:666
			}
		},
		// mixins:[hunhe,hunhe2],
	}
</script>
```

`App.vue`

```vue
<template>
	<div>
		<School/>
		<hr>
		<Student/>
	</div>
</template>

<script>
	import School from './components/School'
	import Student from './components/Student'

	export default {
		name:'App',
		components:{School,Student}
	}
</script>
```

`main.js`

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
import {hunhe,hunhe2} from './mixin'
//关闭Vue的生产提示
Vue.config.productionTip = false
// 全局混入
Vue.mixin(hunhe)
Vue.mixin(hunhe2)


//创建vm
new Vue({
	el:'#app',
	render: h => h(App)
})
```

**`mixin` 补充**：

1.**`mixin` 简介**：Vue 中的 `mixin` 是一种非常强大和灵活的特性，使用`mixin` 可以创建可重用的功能，这些功能可以被注入到 Vue 组件中。`mixin` 能够包含组件中可用的各种选项，包括数据、计算属性、方法、生命周期钩子等。使用 `mixin`，可以极大地减少不同组件之间的代码重复，让代码更加干净、组织更加合理

2.**基本使用**：要创建一个 `mixin`，只需定义一个对象，这个对象可以包含任何要在多个组件之间共享的组件选项

```javascript
// 定义一个 mixin
const myMixin = {
  created() {
    console.log('组件创建!');
  },
  methods: {
    helloMixin() {
      console.log('hello from mixin!');
    }
  }
};
```

然后在组件中使用 `mixins: []` 选项将其加入到组件中：

```javascript
Vue.component('my-component', {
  mixins: [myMixin],
  created() {
    console.log('组件自身的 created 钩子');
  },
  methods: {
    sayHello() {
      this.helloMixin(); // 这是从mixin来的
      console.log('Hello from component');
    }
  }
});
```

当组件和 `mixin` 包含有冲突的选项时，组件本身的选项会优先使用。例如，如果组件和 `mixin` 都定义了 `created` 钩子，那么首先执行 `mixin` 的 `created` 钩子，然后执行组件本身的 `created` 钩子

3.**全局 Mixin**：Vue 也可以注册全局 `mixin`，这意味着这个 `mixin` 会影响到每一个之后创建的 Vue 组件实例。全局 `mixin` 应该谨慎使用，因为它会影响到每个组件实例，包括第三方组件。全局 `mixin` 可以通过 `Vue.mixin` 方法注册：

```javascript
Vue.mixin({
  created() {
    console.log('全局 mixin 的 created 钩子');
  }
});
```

4.**Mixin使用场景**：`mixin` 在处理跨组件的关注点分离时非常有用。例如

- 数据获取
- 表单验证
- 可复用的事件处理器
- 提供组件之间共享的功能，如工具函数

5.**注意事项**：虽然 `mixin` 提供了一种强大的方法来复用组件逻辑，但过度使用或不当使用可能会让组件间产生紧密耦合，或是不必要的复杂性增加。特别是要确保全局 `mixin` 不会不经意间影响到组件。合理利用 `mixin`，将能在保持代码整洁的同时提升开发效率

### 1.5  插件

```
1.功能：用于增强Vue
2.本质：包含install方法的一个对象，install的第一个参数是Vue，第二个以后的参数是插件使用者传递的数据
3.定义插件：
            对象.install = function (Vue, options) {
                // 1. 添加全局过滤器
                Vue.filter(....)

                // 2. 添加全局指令
                Vue.directive(....)

                // 3. 配置全局混入(合)
                Vue.mixin(....)

                // 4. 添加实例方法
                Vue.prototype.$myMethod = function () {...}
                Vue.prototype.$myProperty = xxxx
            }
4.使用插件：Vue.use()
```

`plugins.js`：定义插件

```js
export default {
	install(Vue,x,y,z){
		console.log(x,y,z)
		//全局过滤器
		Vue.filter('mySlice',function(value){
			return value.slice(0,4)
		})

		//定义全局指令
		Vue.directive('fbind',{
			//指令与元素成功绑定时（一上来）
			bind(element,binding){
				element.value = binding.value
			},
			//指令所在元素被插入页面时
			inserted(element,binding){
				element.focus()
			},
			//指令所在的模板被重新解析时
			update(element,binding){
				element.value = binding.value
			}
		})

		//定义混入
		Vue.mixin({
			data() {
				return {
					x:100,
					y:200
				}
			},
		})

		//给Vue原型上添加一个方法（vm和vc就都能用了）
		Vue.prototype.hello = ()=>{alert('你好啊')}
	}
}
```

`main.js`

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入插件
import plugins from './plugins'
//关闭Vue的生产提示
Vue.config.productionTip = false

//应用（使用）插件
Vue.use(plugins,1,2,3)
//创建vm
new Vue({
	el:'#app',
	render: h => h(App)
})
```

**插件补充**：

1.**插件简介**：插件通常用于向 Vue 添加全局级别的功能。可以添加全局方法、自定义指令、过滤器、过渡、甚至是向根实例添加全局混入（mixin）。插件是 Vue 生态系统中极为重要的部分，插件使得 Vue 应用程序的开发更加灵活和强大。使用插件可以大大提高开发效率，同时还能享受到 Vue 社区的支持和丰富的资源

2.**创建插件**：创建Vue 插件相对简单。基本上，一个插件是一个拥有 `install` 方法的对象。这个方法的第一个参数是 Vue 构造器，第二个之后的参数是一个可选的选项对象

```javascript
MyPlugin = {
  install(Vue, options) {
    // 1. 添加全局方法或属性
    Vue.myGlobalMethod = function () {
      // 逻辑...
    }

    // 2. 添加全局资源
    Vue.directive('my-directive', {
      bind (el, binding, vnode, oldVnode) {
        // 逻辑...
      }
      // 其他钩子函数...
    })

    // 3. 注入组件选项
    Vue.mixin({
      created: function () {
        // 逻辑...
      }
    })

    // 4. 添加实例方法
    Vue.prototype.$myMethod = function (methodOptions) {
      // 逻辑...
    }
  }
}
```

3.**使用插件**：创建插件之后可以通过 `Vue.use()` 全局方法来使用该插件。需要注意的是，在调用 `new Vue()` 启动应用之前，必须先注册插件

```javascript
// 调用 `Vue.use()` 并传入插件
Vue.use(MyPlugin)

// 可以传递选项
Vue.use(MyPlugin, { someOption: true })
```

4.**常见的 Vue 插件**：Vue 生态系统有许多优秀的插件，下面是一些常见和流行的插件：

```
Vue Router：                 用于构建单页面应用的官方路由管理器
Vuex：                       Vue.js 的状态管理模式和库
Vue CLI：                    Vue.js 的标准工具，提供一整套工具来快速开发复杂的单页应用
Vuetify、Quasar、Element UI： 提供了大量的 UI 组件来构建响应式页面和应用程序
VuePress：                    以 Vue 驱动的静态网站生成器
Nuxt.js：                     一个基于 Vue.js 的更高级的框架，用于构建服务端渲染的应用
```

###  1.6 scoped样式

```
scoped样式:
            1.作用：  让样式在局部生效，防止冲突
            2.写法：  <style scoped>
```

`School.vue`

```vue
<template>
	<div class="demo">
		<h2 class="title">学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
	export default {
		name:'School',
		data() {
			return {
				name:'尚硅谷atguigu',
				address:'北京',
			}
		}
	}
</script>

<style scoped>
	.demo{
		background-color: skyblue;
	}
</style>
```

`Student.vue`

```vue
<template>
	<div class="demo">
		<h2 class="title">学生姓名：{{name}}</h2>
		<h2 class="atguigu">学生性别：{{sex}}</h2>
	</div>
</template>

<script>
	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男'
			}
		}
	}
</script>

<style lang="less" scoped>
	.demo{
		background-color: pink;
		.atguigu{
			font-size: 40px;
		}
	}
</style>
```

`App.vue`

```vue
<template>
	<div>
		<h1 class="title">你好啊</h1>
		<School/>
		<Student/>
	</div>
</template>

<script>
	import Student from './components/Student'
	import School from './components/School'

	export default {
		name:'App',
		components:{School,Student}
	}
</script>

<style scoped>
	.title{
		color: red;
	}
</style>
```

**`scoped`样式补充**：

1.**`scoped`样式简介**：在Vue中，`scoped`样式是一种特殊的方式，它使得CSS样式仅应用于当前组件中的元素，而不影响其他组件中的元素。这样做可以避免样式冲突，使得组件更加独立，易于维护和复用。在一个Vue组件的`<style>`标签中使用`scoped`属性时，Vue会为该组件中的所有元素添加一个独特的属性（如：data-v-f3f3eg9），并且对应的CSS样式也会被修改，以确保这些样式只会匹配带有这个独特属性的元素。这意味着，这些样式不会泄露到组件外部，也不会受到外部样式的影响

2.**`scoped`样式使用**：假设有一个Vue组件`MyComponent.vue`，里面有一些带有`scoped`属性的样式

```vue
<template>
  <div class="example">这是一个示例</div>
</template>

<style scoped>
.example {
  color: blue;
}
</style>
```

在这个例子中，`.example`类的样式只会应用于`MyComponent`组件内的元素。即使页面上的其他元素也使用了`example`这个类名，它们也不会被这个样式影响，除非它们也属于`MyComponent`组件，并且在相应的作用域内

3.**注意事项**：

- **全局样式**：如果需要在多个组件中使用相同的样式，可以在一个没有`scoped`属性的`<style>`标签中定义这些样式，或者在一个单独的CSS文件中定义后通过`import`引入
- **样式穿透**：有时可能需要修改`scoped`样式中的子组件样式。为了做到这一点，可以使用`>>>`、`/deep/` 或 `::v-deep`操作符来穿透`scoped`边界
- **性能考量**：虽然`scoped`样式提供了很好的封装性，但是对于大型项目或大量使用时，可能会略微增加HTML和CSS的大小，因为每个组件的元素都会被添加一个独特的属性

###  1.7 Todo-list案例

![image-20240410123523649](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130004090.png)

`MyHeader.vue`

```vue
<template>
	<div class="todo-header">
		<input type="text" placeholder="请输入你的任务名称，按回车键确认" v-model="title" @keyup.enter="add"/>
	</div>
</template>

<script>
	import {nanoid} from 'nanoid'
	export default {
		name:'MyHeader',
		//接收从App传递过来的addTodo
		props:['addTodo'],
		data() {
			return {
				//收集用户输入的title
				title:''
			}
		},
		methods: {
			add(){
				//校验数据
				if(!this.title.trim()) return alert('输入不能为空')
				//将用户的输入包装成一个todo对象
				const todoObj = {id:nanoid(),title:this.title,done:false}
				//通知App组件去添加一个todo对象
				this.addTodo(todoObj)
				//清空输入
				this.title = ''
			}
		},
	}
</script>

<style scoped>
	/*header*/
	.todo-header input {
		width: 560px;
		height: 28px;
		font-size: 14px;
		border: 1px solid #ccc;
		border-radius: 4px;
		padding: 4px 7px;
	}

	.todo-header input:focus {
		outline: none;
		border-color: rgba(82, 168, 236, 0.8);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
	}
</style>
```

`MyList.vue`

```vue
<template>
	<ul class="todo-main">
		<MyItem 
			v-for="todoObj in todos"
			:key="todoObj.id" 
			:todo="todoObj" 
			:checkTodo="checkTodo"
			:deleteTodo="deleteTodo"
		/>
	</ul>
</template>

<script>
	import MyItem from './MyItem'

	export default {
		name:'MyList',
		components:{MyItem},
		//声明接收App传递过来的数据，其中todos是自己用的，checkTodo和deleteTodo是给子组件MyItem用的
		props:['todos','checkTodo','deleteTodo']
	}
</script>

<style scoped>
	/*main*/
	.todo-main {
		margin-left: 0px;
		border: 1px solid #ddd;
		border-radius: 2px;
		padding: 0px;
	}

	.todo-empty {
		height: 40px;
		line-height: 40px;
		border: 1px solid #ddd;
		border-radius: 2px;
		padding-left: 5px;
		margin-top: 10px;
	}
</style>
```

`MyItem.vue`

```vue
<template>
	<li>
		<label>
			<input type="checkbox" :checked="todo.done" @change="handleCheck(todo.id)"/>
			<!-- 如下代码也能实现功能，但是不太推荐，因为有点违反原则，因为修改了props -->
			<!-- <input type="checkbox" v-model="todo.done"/> -->
			<span>{{todo.title}}</span>
		</label>
		<button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
	</li>
</template>

<script>
	export default {
		name:'MyItem',
		//声明接收todo、checkTodo、deleteTodo
		props:['todo','checkTodo','deleteTodo'],
		methods: {
			//勾选or取消勾选
			handleCheck(id){
				//通知App组件将对应的todo对象的done值取反
				this.checkTodo(id)
			},
			//删除
			handleDelete(id){
				if(confirm('确定删除吗？')){
					//通知App组件将对应的todo对象删除
					this.deleteTodo(id)
				}
			}
		},
	}
</script>

<style scoped>
	/*item*/
	li {
		list-style: none;
		height: 36px;
		line-height: 36px;
		padding: 0 5px;
		border-bottom: 1px solid #ddd;
	}

	li label {
		float: left;
		cursor: pointer;
	}

	li label li input {
		vertical-align: middle;
		margin-right: 6px;
		position: relative;
		top: -1px;
	}

	li button {
		float: right;
		display: none;
		margin-top: 3px;
	}

	li:before {
		content: initial;
	}

	li:last-child {
		border-bottom: none;
	}

	li:hover{
		background-color: #ddd;
	}
	
	li:hover button{
		display: block;
	}
</style>
```

`MyFooter.vue`

```vue
<template>
	<div class="todo-footer" v-show="total">
		<label>
			<!-- <input type="checkbox" :checked="isAll" @change="checkAll"/> -->
			<input type="checkbox" v-model="isAll"/>
		</label>
		<span>
			<span>已完成{{doneTotal}}</span> / 全部{{total}}
		</span>
		<button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
	</div>
</template>

<script>
	export default {
		name:'MyFooter',
		props:['todos','checkAllTodo','clearAllTodo'],
		computed: {
			//总数
			total(){
				return this.todos.length
			},
			//已完成数
			doneTotal(){
				//此处使用reduce方法做条件统计
				/* const x = this.todos.reduce((pre,current)=>{
					console.log('@',pre,current)
					return pre + (current.done ? 1 : 0)
				},0) */
				//简写
				return this.todos.reduce((pre,todo)=> pre + (todo.done ? 1 : 0) ,0)
			},
			//控制全选框
			isAll:{
				//全选框是否勾选
				get(){
					return this.doneTotal === this.total && this.total > 0
				},
				//isAll被修改时set被调用
				set(value){
					this.checkAllTodo(value)
				}
			}
		},
		methods: {
			/* checkAll(e){
				this.checkAllTodo(e.target.checked)
			} */
			//清空所有已完成
			clearAll(){
				this.clearAllTodo()
			}
		},
	}
</script>

<style scoped>
	/*footer*/
	.todo-footer {
		height: 40px;
		line-height: 40px;
		padding-left: 6px;
		margin-top: 5px;
	}

	.todo-footer label {
		display: inline-block;
		margin-right: 20px;
		cursor: pointer;
	}

	.todo-footer label input {
		position: relative;
		top: -1px;
		vertical-align: middle;
		margin-right: 5px;
	}

	.todo-footer button {
		float: right;
		margin-top: 5px;
	}
</style>
```

`App.vue`

```vue
<template>
	<div id="root">
		<div class="todo-container">
			<div class="todo-wrap">
				<MyHeader :addTodo="addTodo"/>
				<MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
				<MyFooter :todos="todos" :checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"/>
			</div>
		</div>
	</div>
</template>

<script>
	import MyHeader from './components/MyHeader'
	import MyList from './components/MyList'
	import MyFooter from './components/MyFooter.vue'

	export default {
		name:'App',
		components:{MyHeader,MyList,MyFooter},
		data() {
			return {
				//由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
				todos:[
					{id:'001',title:'抽烟',done:true},
					{id:'002',title:'喝酒',done:false},
					{id:'003',title:'开车',done:true}
				]
			}
		},
		methods: {
			//添加一个todo
			addTodo(todoObj){
				this.todos.unshift(todoObj)
			},
			//勾选or取消勾选一个todo
			checkTodo(id){
				this.todos.forEach((todo)=>{
					if(todo.id === id) todo.done = !todo.done
				})
			},
			//删除一个todo
			deleteTodo(id){
				this.todos = this.todos.filter( todo => todo.id !== id )
			},
			//全选or取消全选
			checkAllTodo(done){
				this.todos.forEach((todo)=>{
					todo.done = done
				})
			},
			//清除所有已经完成的todo
			clearAllTodo(){
				this.todos = this.todos.filter((todo)=>{
					return !todo.done
				})
			}
		}
	}
</script>

<style>
	/*base*/
	body {
		background: #fff;
	}
	.btn {
		display: inline-block;
		padding: 4px 12px;
		margin-bottom: 0;
		font-size: 14px;
		line-height: 20px;
		text-align: center;
		vertical-align: middle;
		cursor: pointer;
		box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		border-radius: 4px;
	}
	.btn-danger {
		color: #fff;
		background-color: #da4f49;
		border: 1px solid #bd362f;
	}
	.btn-danger:hover {
		color: #fff;
		background-color: #bd362f;
	}
	.btn:focus {
		outline: none;
	}
	.todo-container {
		width: 600px;
		margin: 0 auto;
	}
	.todo-container .todo-wrap {
		padding: 10px;
		border: 1px solid #ddd;
		border-radius: 5px;
	}
</style>
```

**总结TodoList案例**：

1.**组件化编码流程**：

```
(1).拆分静态组件：组件要按照功能点拆分，命名不要与html元素冲突
(2).实现动态组件：考虑好数据的存放位置，数据是一个组件在用，还是一些组件在用：
		1).一个组件在用：放在组件自身即可
		2). 一些组件在用：放在他们共同的父组件上（<span style="color:red">状态提升</span>）
(3).实现交互：从绑定事件开始
```

2.**props适用于如下场景**：

```
(1).父组件 ==> 子组件 通信
(2).子组件 ==> 父组件 通信（要求父先给子一个函数）
```

3.**使用v-model时要切记**：v-model绑定的值不能是props传过来的值，因为props是不可以修改

4.props传过来的若是对象类型的值，修改对象中的属性时Vue不会报错，但不推荐这样做

###  1.8 浏览器本地缓存webStorage

#####  1.8.1 浏览器缓存webStorage

```
webStorage:
1.存储内容大小一般支持5MB左右（不同浏览器可能还不一样）
2.浏览器端通过 Window.sessionStorage 和 Window.localStorage 属性来实现本地存储机制
3. 相关API：
          1.xxxxxStorage.setItem('key', 'value');
        		 该方法接受一个键和值作为参数，会把键值对添加到存储中，如果键名存在，则更新其对应的值
          2.xxxxxStorage.getItem('person');
                 该方法接受一个键名作为参数，返回键名对应的值
          3.xxxxxStorage.removeItem('key');
                 该方法接受一个键名作为参数，并把该键名从存储中删除
          4.xxxxxStorage.clear()
                 该方法会清空存储中的所有数据
4.注意事项：
            1.SessionStorage存储的内容会随着浏览器窗口关闭而消失
            2.LocalStorage存储的内容，需要手动清除才会消失
            3.xxxxxStorage.getItem(xxx) 如果xxx对应的value获取不到，那么getItem的返回值是null
            4.JSON.parse(null)的结果依然是null
```



![image-20240410213943306](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130004281.png)

`localStorage.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>localStorage</title>
	</head>
	<body>
		<h2>localStorage</h2>
		<button onclick="saveData()">点我保存一个数据</button>
		<button onclick="readData()">点我读取一个数据</button>
		<button onclick="deleteData()">点我删除一个数据</button>
		<button onclick="deleteAllData()">点我清空一个数据</button>

		<script type="text/javascript" >
			let p = {name:'张三',age:18}

			function saveData(){
				localStorage.setItem('msg','hello!!!')
				localStorage.setItem('msg2',666)
				localStorage.setItem('person',JSON.stringify(p))
			}
			function readData(){
				console.log(localStorage.getItem('msg'))
				console.log(localStorage.getItem('msg2'))

				const result = localStorage.getItem('person')
				console.log(JSON.parse(result))

				// console.log(localStorage.getItem('msg3'))
			}
			function deleteData(){
				localStorage.removeItem('msg2')
			}
			function deleteAllData(){
				localStorage.clear()
			}
		</script>
	</body>
</html>
```

`sessionStorage.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>sessionStorage</title>
	</head>
	<body>
		<h2>sessionStorage</h2>
		<button onclick="saveData()">点我保存一个数据</button>
		<button onclick="readData()">点我读取一个数据</button>
		<button onclick="deleteData()">点我删除一个数据</button>
		<button onclick="deleteAllData()">点我清空一个数据</button>

		<script type="text/javascript" >
			let p = {name:'张三',age:18}

			function saveData(){
				sessionStorage.setItem('msg','hello!!!')
				sessionStorage.setItem('msg2',666)
				sessionStorage.setItem('person',JSON.stringify(p))
			}
			function readData(){
				console.log(sessionStorage.getItem('msg'))
				console.log(sessionStorage.getItem('msg2'))

				const result = sessionStorage.getItem('person')
				console.log(JSON.parse(result))

				// console.log(sessionStorage.getItem('msg3'))
			}
			function deleteData(){
				sessionStorage.removeItem('msg2')
			}
			function deleteAllData(){
				sessionStorage.clear()
			}
		</script>
	</body>
</html>
```

**本地缓存补充**：浏览器中本地缓存机制可以帮助提升网页性能、保存用户偏好设置或者在用户离线时仍能访问某些数据。下面是几种常见的浏览器本地缓存：

1.**Cookies**:

- 用途：保存用户数据，如登录状态、用户偏好
- 特点：每次HTTP请求时自动发送给服务器，大小限制在大约4KB
- 缺点：由于每次请求都会携带，可能会影响性能

2.**LocalStorage**:

- 用途：存储不需要经常更改的数据，如用户界面的布局偏好
- 特点：数据保存在客户端，不会随着HTTP请求发送，大小限制约为5MB
- 缺点：它只能存储字符串类型的数据

3.**SessionStorage**:

- 用途：存储在页面会话期间需要的数据
- 特点：数据仅在当前浏览器窗口或标签页中有效，关闭窗口或标签页后数据将被清除，大小限制约为5MB
- 缺点：与LocalStorage相似，但更适用于临时数据

4.**IndexedDB**:

- 用途：更复杂的客户端存储，可以存储大量数据，并支持索引

  特点：一个基于JavaScript的对象存储数据库系统，支持事务，无大小限制，但受浏览器的整体存储限制

- 缺点：API较复杂，适用于需要高性能和大容量存储的应用

5.**Cache API** (一般用于Service Workers):

- 用途：用于存储网络请求及其响应对象，特别适合创建离线应用
- 特点：提供了更细致的控制，可以缓存不同请求的响应
- 缺点：相对较新，可能不被所有浏览器完全支持

#####  1.8.2 改造Todo-list案例

`App.vue`：将填写的todo-list进行存储，刷新浏览器后用户之前的输入不会丢失

```vue
<template>
	<div id="root">
		<div class="todo-container">
			<div class="todo-wrap">
				<MyHeader :addTodo="addTodo"/>
				<MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
				<MyFooter :todos="todos" :checkAllTodo="checkAllTodo" :clearAllTodo="clearAllTodo"/>
			</div>
		</div>
	</div>
</template>

<script>
	import MyHeader from './components/MyHeader'
	import MyList from './components/MyList'
	import MyFooter from './components/MyFooter.vue'

	export default {
		name:'App',
		components:{MyHeader,MyList,MyFooter},
		data() {
			return {
				//由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
				todos:JSON.parse(localStorage.getItem('todos')) || []
			}
		},
		methods: {
			//添加一个todo
			addTodo(todoObj){
				this.todos.unshift(todoObj)
			},
			//勾选or取消勾选一个todo
			checkTodo(id){
				this.todos.forEach((todo)=>{
					if(todo.id === id) todo.done = !todo.done
				})
			},
			//删除一个todo
			deleteTodo(id){
				this.todos = this.todos.filter( todo => todo.id !== id )
			},
			//全选or取消全选
			checkAllTodo(done){
				this.todos.forEach((todo)=>{
					todo.done = done
				})
			},
			//清除所有已经完成的todo
			clearAllTodo(){
				this.todos = this.todos.filter((todo)=>{
					return !todo.done
				})
			}
		},
		watch: {
			todos:{
				deep:true,
				handler(value){
					localStorage.setItem('todos',JSON.stringify(value))
				}
			}
		},
	}
</script>
// 省略。。。
```

###  1.9  组件自定义事件

#####  1.9.1  组件的自定义事件

```
组件的自定义事件:
1.一种组件间通信的方式，适用于：子组件 ===> 父组件
2.使用场景：A是父组件，B是子组件，B想给A传数据，那么就要在A中给B绑定自定义事件（事件的回调在A中）
3.绑定自定义事件：
           3.1. 第一种方式，在父组件中：<Demo @atguigu="test"/>  或 <Demo v-on:atguigu="test"/>
           3.2. 第二种方式，在父组件中：
                      <Demo ref="demo"/>
                      ......
                      mounted(){
                         this.$refs.xxx.$on('atguigu',this.test)
                      }
           3.3. 若想让自定义事件只能触发一次，可以使用once修饰符，或$once方法

4.触发自定义事件:  this.$emit('atguigu',数据)	
5.解绑自定义事件:  this.$off('atguigu')
6.组件上也可以绑定原生DOM事件，需要使用native修饰符
7.注意：通过this.$refs.xxx.$on('atguigu',回调)绑定自定义事件时，回调要么配置在methods中，要么用箭头函数，否则this指向会出问题！
```

`Student.vue`

```vue
<template>
	<div class="student">
		<h2>学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
		<h2>当前求和为：{{number}}</h2>
		<button @click="add">点我number++</button>
		<button @click="sendStudentlName">把学生名给App</button>
		<button @click="unbind">解绑atguigu事件</button>
		<button @click="death">销毁当前Student组件的实例(vc)</button>
	</div>
</template>

<script>
	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男',
				number:0
			}
		},
		methods: {
			add(){
				console.log('add回调被调用了')
				this.number++
			},
			sendStudentlName(){
				//触发Student组件实例身上的atguigu事件
				this.$emit('atguigu',this.name,666,888,900)
				// this.$emit('demo')
				// this.$emit('click')
			},
			unbind(){
				this.$off('atguigu') //解绑一个自定义事件
				// this.$off(['atguigu','demo']) //解绑多个自定义事件
				// this.$off() //解绑所有的自定义事件
			},
			death(){
				this.$destroy() //销毁了当前Student组件的实例，销毁后所有Student实例的自定义事件全都不奏效。
			}
		},
	}
</script>

<style lang="less" scoped>
	.student{
		background-color: pink;
		padding: 5px;
		margin-top: 30px;
	}
</style>
```

`App.vue`

```vue
<template>
	<div class="app">
		<h1>{{msg}}，学生姓名是:{{studentName}}</h1>

		<!-- 通过父组件给子组件传递函数类型的props实现：子给父传递数据 -->
		<School :getSchoolName="getSchoolName"/>

		<!-- 通过父组件给子组件绑定一个自定义事件实现：子给父传递数据（第一种写法，使用@或v-on） -->
		<!-- <Student @atguigu="getStudentName" @demo="m1"/> -->

		<!-- 通过父组件给子组件绑定一个自定义事件实现：子给父传递数据（第二种写法，使用ref） -->
		<Student ref="student" @click.native="show"/>
	</div>
</template>

<script>
	import Student from './components/Student'
	import School from './components/School'

	export default {
		name:'App',
		components:{School,Student},
		data() {
			return {
				msg:'你好啊！',
				studentName:''
			}
		},
		methods: {
			getSchoolName(name){
				console.log('App收到了学校名：',name)
			},
			getStudentName(name,...params){
				console.log('App收到了学生名：',name,params)
				this.studentName = name
			},
			m1(){
				console.log('demo事件被触发了！')
			},
			show(){
				alert(123)
			}
		},
		mounted() {
			this.$refs.student.$on('atguigu',this.getStudentName) //绑定自定义事件
			// this.$refs.student.$once('atguigu',this.getStudentName) //绑定自定义事件（一次性）
		},
	}
</script>

<style scoped>
	.app{
		background-color: gray;
		padding: 5px;
	}
</style>
```

**组件的自定义事件补充**：

1.**组件的自定义事件**：在Vue.js中，组件间通信是一个非常重要的概念，其中自定义事件是实现父子组件通信的常用方法之一。自定义事件允许子组件向父组件发送消息，这对于保持组件的独立性和可重用性非常有用

2.**定义事件**：在Vue组件中可以使用`this.$emit`方法来触发一个事件，该方法接受至少一个参数（事件名），其余的参数将被传递给处理函数

```javascript
// 子组件
<template>
  <button @click="handleClick">Click me</button>
</template>

<script>
export default {
  methods: {
    handleClick() {
      // 触发一个名为'custom-event'的事件，并传递数据
      this.$emit('custom-event', 'Hello from child!');
    }
  }
}
</script>
```

3.**监听事件**：父组件监听子组件触发的事件，可以在模板中直接使用`v-on`或简写`@`来绑定自定义事件

```javascript
// 父组件
<template>
  <div>
    <child-component @custom-event="handleCustomEvent"></child-component>
  </div>
</template>

<script>
import ChildComponent from './ChildComponent.vue';

export default {
  components: {
    ChildComponent
  },
  methods: {
    handleCustomEvent(data) {
      console.log('Received:', data);
    }
  }
}
</script>
```

4.**在组件上使用`.native`修饰符**：在Vue 2.x中，如果想在一个子组件的根元素上监听一个原生事件（如点击事件），可以使用`.native`修饰符。Vue 3.x中已移除`.native`修饰符，因为Vue 3推荐使用更明确的事件处理方式

5.**使用`v-model`进行双向绑定**：可以通过自定义事件来模拟`v-model`的行为。`v-model`在背后其实是一个语法糖，它结合了`value`属性和`input`事件

```javascript
// 子组件，自定义 v-model
<template>
  <input :value="value" @input="onInput">
</template>

<script>
export default {
  props: ['value'],
  methods: {
    onInput(event) {
      this.$emit('input', event.target.value);
    }
  }
}
</script>
```

```javascript
// 父组件
<template>
  <child-component v-model="msg"></child-component>
</template>

<script>
import ChildComponent from './ChildComponent.vue';

export default {
  components: {
    ChildComponent
  },
  data() {
    return {
      msg: ''
    };
  }
}
</script>
```

这样，父组件和子组件之间就可以通过`v-model`实现双向绑定，子组件通过`input`事件更新父组件的数据

##### 1.9.2 改造TodoList案例

将TodoList案例中涉及到子组件给父组件传递数据的地方都利用自定义事件进行实现

`App.vue`

```vue
<template>
	<div id="root">
		<div class="todo-container">
			<div class="todo-wrap">
				<!-- 将TodoList案例中涉及到子组件给父组件传递数据的地方都利用自定义事件进行实现 -->
				<MyHeader @addTodo="addTodo"/>
				<MyList :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
				<!-- 将TodoList案例中涉及到子组件给父组件传递数据的地方都利用自定义事件进行实现 -->
				<MyFooter :todos="todos" @checkAllTodo="checkAllTodo" @clearAllTodo="clearAllTodo"/>
			</div>
		</div>
	</div>
</template>

<script>
	import MyHeader from './components/MyHeader'
	import MyList from './components/MyList'
	import MyFooter from './components/MyFooter.vue'

	export default {
		name:'App',
		components:{MyHeader,MyList,MyFooter},
		data() {
			return {
				//由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
				todos:JSON.parse(localStorage.getItem('todos')) || []
			}
		},
		methods: {
			//添加一个todo
			addTodo(todoObj){
				this.todos.unshift(todoObj)
			},
			//勾选or取消勾选一个todo
			checkTodo(id){
				this.todos.forEach((todo)=>{
					if(todo.id === id) todo.done = !todo.done
				})
			},
			//删除一个todo
			deleteTodo(id){
				this.todos = this.todos.filter( todo => todo.id !== id )
			},
			//全选or取消全选
			checkAllTodo(done){
				this.todos.forEach((todo)=>{
					todo.done = done
				})
			},
			//清除所有已经完成的todo
			clearAllTodo(){
				this.todos = this.todos.filter((todo)=>{
					return !todo.done
				})
			}
		},
		watch: {
			todos:{
				deep:true,
				handler(value){
					localStorage.setItem('todos',JSON.stringify(value))
				}
			}
		},
	}
</script>
// 省略。。。
```

`MyHeader.vue`

```vue
<template>
	<div class="todo-header">
		<input type="text" placeholder="请输入你的任务名称，按回车键确认" v-model="title" @keyup.enter="add"/>
	</div>
</template>

<script>
	import {nanoid} from 'nanoid'
	export default {
		name:'MyHeader',
		data() {
			return {
				//收集用户输入的title
				title:''
			}
		},
		methods: {
			add(){
				//校验数据
				if(!this.title.trim()) return alert('输入不能为空')
				//将用户的输入包装成一个todo对象
				const todoObj = {id:nanoid(),title:this.title,done:false}
				//通知App组件去添加一个todo对象
				// 将TodoList案例中涉及到子组件给父组件传递数据的地方都利用自定义事件进行实现
				this.$emit('addTodo',todoObj,1,2,3)
				//清空输入
				this.title = ''
			}
		},
	}
</script>
// 省略。。。
```

`MyFooter.vue`

```vue
<template>
	<div class="todo-footer" v-show="total">
		<label>
			<!-- <input type="checkbox" :checked="isAll" @change="checkAll"/> -->
			<input type="checkbox" v-model="isAll"/>
		</label>
		<span>
			<span>已完成{{doneTotal}}</span> / 全部{{total}}
		</span>
		<button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
	</div>
</template>

<script>
	export default {
		name:'MyFooter',
		props:['todos'],
		computed: {
			//总数
			total(){
				return this.todos.length
			},
			//已完成数
			doneTotal(){
				//此处使用reduce方法做条件统计
				/* const x = this.todos.reduce((pre,current)=>{
					console.log('@',pre,current)
					return pre + (current.done ? 1 : 0)
				},0) */
				//简写
				return this.todos.reduce((pre,todo)=> pre + (todo.done ? 1 : 0) ,0)
			},
			//控制全选框
			isAll:{
				//全选框是否勾选
				get(){
					return this.doneTotal === this.total && this.total > 0
				},
				//isAll被修改时set被调用
				set(value){
					// this.checkAllTodo(value)
					// 将TodoList案例中涉及到子组件给父组件传递数据的地方都利用自定义事件进行实现
					this.$emit('checkAllTodo',value)
				}
			}
		},
		methods: {
			/* checkAll(e){
				this.checkAllTodo(e.target.checked)
			} */
			//清空所有已完成
			clearAll(){
				// this.clearAllTodo()
				// 将TodoList案例中涉及到子组件给父组件传递数据的地方都利用自定义事件进行实现
				this.$emit('clearAllTodo')
			}
		},
	}
</script>
// 省略。。。
```

###  1.10 全局事件总线

#####  1.10.1 全局事件总线（GlobalEventBus）

1. 一种组件间通信的方式，适用于<span style="color:red">任意组件间通信</span>

2. 安装全局事件总线：

   ```js
   new Vue({
   	......
   	beforeCreate() {
   		Vue.prototype.$bus = this //安装全局事件总线，$bus就是当前应用的vm
   	},
       ......
   }) 
   ```

3. 使用事件总线：

   1. 接收数据：A组件想接收数据，则在A组件中给$bus绑定自定义事件，事件的<span style="color:red">回调留在A组件自身。</span>

      ```js
      methods(){
        demo(data){......}
      }
      ......
      mounted() {
        this.$bus.$on('xxxx',this.demo)
      }
      ```

   2. 提供数据：```this.$bus.$emit('xxxx',数据)```

4. 最好在beforeDestroy钩子中，用$off去解绑<span style="color:red">当前组件所用到的</span>事件

![image-20240411224320719](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130004600.png)

`School.vue`

```vue
<template>
	<div class="school">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
	export default {
		name:'School',
		data() {
			return {
				name:'尚硅谷',
				address:'北京',
			}
		},
		mounted() {
			// console.log('School',this)
			this.$bus.$on('hello',(data)=>{
				console.log('我是School组件，收到了数据',data)
			})
		},
		beforeDestroy() {
			this.$bus.$off('hello')
		},
	}
</script>

<style scoped>
	.school{
		background-color: skyblue;
		padding: 5px;
	}
</style>
```

`Student.vue`

```vue
<template>
	<div class="student">
		<h2>学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
		<button @click="sendStudentName">把学生名给School组件</button>
	</div>
</template>

<script>
	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男',
			}
		},
		mounted() {
			// console.log('Student',this.x)
		},
		methods: {
			sendStudentName(){
				this.$bus.$emit('hello',this.name)
			}
		},
	}
</script>

<style lang="less" scoped>
	.student{
		background-color: pink;
		padding: 5px;
		margin-top: 30px;
	}
</style>
```

`App.vue`

```vue
<template>
	<div class="app">
		<h1>{{msg}}</h1>
		<School/>
		<Student/>
	</div>
</template>

<script>
	import Student from './components/Student'
	import School from './components/School'

	export default {
		name:'App',
		components:{School,Student},
		data() {
			return {
				msg:'你好啊！',
			}
		}
	}
</script>

<style scoped>
	.app{
		background-color: gray;
		padding: 5px;
	}
</style>
```

`main.js`

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//关闭Vue的生产提示
Vue.config.productionTip = false

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	beforeCreate() {
		Vue.prototype.$bus = this //安装全局事件总线
	},
})
```

**全局事件总线补充**：

1.**全局事件总线**：全局事件总线（Global Event Bus）是 Vue.js 中一种用于跨组件通信的技术。在 Vue.js 的某些版本中，尤其是 Vue 2.x，全局事件总线被广泛使用，尽管在 Vue 3.x 中，它已被其他模式（如提供/注入）所取代。不过，了解全局事件总线仍然有其价值，特别是在处理旧的 Vue 项目时

2.**全局事件总线的使用**：

1. **创建事件总线：** 通过创建一个新的 Vue 实例来作为事件总线：

   ```javascript
   const EventBus = new Vue();
   ```

2. **事件触发：** 任何组件可以使用这个事件总线来触发一个事件：

   ```javascript
   EventBus.$emit('eventName', eventData);
   ```

3. **监听事件：** 其他组件可以监听这个事件总线上的事件：

   ```javascript
   EventBus.$on('eventName', (eventData) => {
     // 处理事件
   });
   ```

4. **移除事件监听器：** 如果不再需要监听某个事件，应当移除相应的事件监听器，以避免内存泄漏：

   ```javascript
   EventBus.$off('eventName', listenerFunction);
   ```

**说明**：在 Vue 3.x 中，全局事件总线的概念不再是官方推荐的做法。Vue 3.x 引入了 Composition API，它提供了更为强大和灵活的方法来处理跨组件通信，例如 `provide` 和 `inject` 函数。这些方法允许开发者更加明确和控制地共享状态和行为，而不是依赖于一个全局的事件中心

#####  1.10.2 改造TodoList案例

`MyFooter.vue`

```vue
<template>
	<div class="todo-footer" v-show="total">
		<label>
			<!-- <input type="checkbox" :checked="isAll" @change="checkAll"/> -->
			<input type="checkbox" v-model="isAll"/>
		</label>
		<span>
			<span>已完成{{doneTotal}}</span> / 全部{{total}}
		</span>
		<button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
	</div>
</template>

<script>
	export default {
		name:'MyFooter',
		props:['todos'],
		computed: {
			//总数
			total(){
				return this.todos.length
			},
			//已完成数
			doneTotal(){
				//此处使用reduce方法做条件统计
				/* const x = this.todos.reduce((pre,current)=>{
					console.log('@',pre,current)
					return pre + (current.done ? 1 : 0)
				},0) */
				//简写
				return this.todos.reduce((pre,todo)=> pre + (todo.done ? 1 : 0) ,0)
			},
			//控制全选框
			isAll:{
				//全选框是否勾选
				get(){
					return this.doneTotal === this.total && this.total > 0
				},
				//isAll被修改时set被调用
				set(value){
					// this.checkAllTodo(value)
					this.$emit('checkAllTodo',value)
				}
			}
		},
		methods: {
			/* checkAll(e){
				this.checkAllTodo(e.target.checked)
			} */
			//清空所有已完成
			clearAll(){
				// this.clearAllTodo()
				this.$emit('clearAllTodo')
			}
		},
	}
</script>
// 省略
```

`MyHeader.vue`

```vue
<template>
	<div class="todo-header">
		<input type="text" placeholder="请输入你的任务名称，按回车键确认" v-model="title" @keyup.enter="add"/>
	</div>
</template>

<script>
	import {nanoid} from 'nanoid'
	export default {
		name:'MyHeader',
		data() {
			return {
				//收集用户输入的title
				title:''
			}
		},
		methods: {
			add(){
				//校验数据
				if(!this.title.trim()) return alert('输入不能为空')
				//将用户的输入包装成一个todo对象
				const todoObj = {id:nanoid(),title:this.title,done:false}
				//通知App组件去添加一个todo对象
				this.$emit('addTodo',todoObj,1,2,3)
				//清空输入
				this.title = ''
			}
		},
	}
</script>
// 省略。。。
```

`MyList.vue`

```vue
<template>
	<ul class="todo-main">
		<MyItem 
			v-for="todoObj in todos"
			:key="todoObj.id" 
			:todo="todoObj" 
		/>
	</ul>
</template>

<script>
	import MyItem from './MyItem'

	export default {
		name:'MyList',
		components:{MyItem},
		//声明接收App传递过来的数据
		props:['todos']
	}
</script>
// 省略。。。
```

`MyItem.vue`

```vue
<template>
	<li>
		<label>
			<input type="checkbox" :checked="todo.done" @change="handleCheck(todo.id)"/>
			<!-- 如下代码也能实现功能，但是不太推荐，因为有点违反原则，因为修改了props -->
			<!-- <input type="checkbox" v-model="todo.done"/> -->
			<span>{{todo.title}}</span>
		</label>
		<button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
	</li>
</template>

<script>
	export default {
		name:'MyItem',
		//声明接收todo
		props:['todo'],
		methods: {
			//勾选or取消勾选
			handleCheck(id){
				//通知App组件将对应的todo对象的done值取反
				// this.checkTodo(id)
				this.$bus.$emit('checkTodo',id)
			},
			//删除
			handleDelete(id){
				if(confirm('确定删除吗？')){
					//通知App组件将对应的todo对象删除
					// this.deleteTodo(id)
					this.$bus.$emit('deleteTodo',id)
				}
			}
		},
	}
</script>
```

`App.vue`

```vue
<template>
	<div id="root">
		<div class="todo-container">
			<div class="todo-wrap">
				<MyHeader @addTodo="addTodo"/>
				<MyList :todos="todos"/>
				<MyFooter :todos="todos" @checkAllTodo="checkAllTodo" @clearAllTodo="clearAllTodo"/>
			</div>
		</div>
	</div>
</template>

<script>
	import MyHeader from './components/MyHeader'
	import MyList from './components/MyList'
	import MyFooter from './components/MyFooter.vue'

	export default {
		name:'App',
		components:{MyHeader,MyList,MyFooter},
		data() {
			return {
				//由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
				todos:JSON.parse(localStorage.getItem('todos')) || []
			}
		},
		methods: {
			//添加一个todo
			addTodo(todoObj){
				this.todos.unshift(todoObj)
			},
			//勾选or取消勾选一个todo
			checkTodo(id){
				this.todos.forEach((todo)=>{
					if(todo.id === id) todo.done = !todo.done
				})
			},
			//删除一个todo
			deleteTodo(id){
				this.todos = this.todos.filter( todo => todo.id !== id )
			},
			//全选or取消全选
			checkAllTodo(done){
				this.todos.forEach((todo)=>{
					todo.done = done
				})
			},
			//清除所有已经完成的todo
			clearAllTodo(){
				this.todos = this.todos.filter((todo)=>{
					return !todo.done
				})
			}
		},
		watch: {
			todos:{
				deep:true,
				handler(value){
					localStorage.setItem('todos',JSON.stringify(value))
				}
			}
		},
		mounted() {
			this.$bus.$on('checkTodo',this.checkTodo)
			this.$bus.$on('deleteTodo',this.deleteTodo)
		},
		beforeDestroy() {
			this.$bus.$off('checkTodo')
			this.$bus.$off('deleteTodo')
		},
	}
</script>
```

`main.js`

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//关闭Vue的生产提示
Vue.config.productionTip = false

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	beforeCreate() {
		Vue.prototype.$bus = this
	},
})
```

###  1.11  消息订阅与发布

#####  1.11.1 消息订阅与发布（pubsub）

消息订阅与发布（pubsub）

1.   一种组件间通信的方式，适用于<span style="color:red">任意组件间通信</span>

2. 使用步骤：

   1. 安装pubsub：```npm i pubsub-js```

   2. 引入: ```import pubsub from 'pubsub-js'```

   3. 接收数据：A组件想接收数据，则在A组件中订阅消息，订阅的<span style="color:red">回调留在A组件自身</span>

      ```js
      methods(){
        demo(data){......}
      }
      ......
      mounted() {
        this.pid = pubsub.subscribe('xxx',this.demo) //订阅消息
      }
      ```

   4. 提供数据：```pubsub.publish('xxx',数据)```

   5. 最好在beforeDestroy钩子中，用```PubSub.unsubscribe(pid)```去<span style="color:red">取消订阅</span>

`School.vue`

```vue
<template>
	<div class="school">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
	</div>
</template>

<script>
	import pubsub from 'pubsub-js'
	export default {
		name:'School',
		data() {
			return {
				name:'尚硅谷',
				address:'北京',
			}
		},
		mounted() {
			// console.log('School',this)
			/* this.$bus.$on('hello',(data)=>{
				console.log('我是School组件，收到了数据',data)
			}) */
			this.pubId = pubsub.subscribe('hello',(msgName,data)=>{
				console.log(this)
				// console.log('有人发布了hello消息，hello消息的回调执行了',msgName,data)
			})
		},
		beforeDestroy() {
			// this.$bus.$off('hello')
			pubsub.unsubscribe(this.pubId)
		},
	}
</script>
// 省略。。。
```

`Student.vue`

```vue
<template>
	<div class="student">
		<h2>学生姓名：{{name}}</h2>
		<h2>学生性别：{{sex}}</h2>
		<button @click="sendStudentName">把学生名给School组件</button>
	</div>
</template>

<script>
	import pubsub from 'pubsub-js'
	export default {
		name:'Student',
		data() {
			return {
				name:'张三',
				sex:'男',
			}
		},
		mounted() {
			// console.log('Student',this.x)
		},
		methods: {
			sendStudentName(){
				// this.$bus.$emit('hello',this.name)
				pubsub.publish('hello',666)
			}
		},
	}
</script>
```

**消息订阅与发布补充**：

1.**消息订阅与发布**：Vue.js 中的消息订阅与发布（Pub-Sub）模式是一种允许组件之间进行通信的技术，特别是在那些不直接关联（如父子、兄弟组件）的组件之间。这种模式可以帮助我们在组件间传递消息，而不需要将数据通过每一层组件显式传递，从而简化了跨组件通信的复杂性。Vue.js 没有内置的 Pub-Sub 系统，但是我们可以使用 Vue 的实例作为事件中心（event bus），或者使用专门的状态管理库如 Vuex，或是借助第三方的 Pub-Sub 库来实现

2.**使用 Vue 实例作为事件中心**

1. **创建事件中心**：首先需要创建一个可以作为事件中心的 Vue 实例。这个实例将用于绑定和触发事件，而不是用于UI渲染

   ```javascript
   // eventBus.js
   import Vue from 'vue';
   export const EventBus = new Vue();
   ```

2. **事件发布**：组件可以调用事件中心的 `$emit` 方法来发布（触发）事件。发布事件时，可以附加消息数据作为参数

   ```javascript
   // ComponentA.vue
   import { EventBus } from './eventBus.js';
   
   export default {
     methods: {
       publishEvent() {
         EventBus.$emit('eventName', 'Hello from Component A!');
       }
     }
   }
   ```

3. **消息订阅**：其他组件可以通过事件中心的 `$on` 方法订阅这些事件，并定义一个回调函数来响应这些事件

   ```javascript
   // ComponentB.vue
   import { EventBus } from './eventBus.js';
   
   export default {
     mounted() {
       EventBus.$on('eventName', (message) => {
         console.log(message); // 输出: "Hello from Component A!"
       });
     }
   }
   ```

4. **取消订阅**：如果需要取消订阅，可以使用 `$off` 方法。这对于防止内存泄露和确保在组件销毁时取消订阅是很有用的

   ```javascript
   EventBus.$off('eventName');
   ```

3.**使用 Vuex**：对于大型应用，使用 Vuex 管理状态和事件可能更合适。Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式和库。它提供了一个中央存储，所有组件都可以访问，并且还可以通过 actions 和 mutations 来管理状态的变化，这种方式间接实现了组件间的通信

4.**使用第三方 Pub-Sub 库**：除了以上 Vue 特有的方法外，还可以使用第三方的 Pub-Sub 库（如 Mitt, EventEmitter3 等）来实现跨组件或跨系统的消息订阅与发布

#####  1.11.2 改造TodoList案例

`MyList.vue`

```vue
<template>
	<ul class="todo-main">
		<MyItem 
			v-for="todoObj in todos"
			:key="todoObj.id" 
			:todo="todoObj" 
		/>
	</ul>
</template>

<script>
	import MyItem from './MyItem'

	export default {
		name:'MyList',
		components:{MyItem},
		//声明接收App传递过来的数据
		props:['todos']
	}
</script>
```

`MyItem.vue`

```vue
<template>
	<li>
		<label>
			<input type="checkbox" :checked="todo.done" @change="handleCheck(todo.id)"/>
			<!-- 如下代码也能实现功能，但是不太推荐，因为有点违反原则，因为修改了props -->
			<!-- <input type="checkbox" v-model="todo.done"/> -->
			<span>{{todo.title}}</span>
		</label>
		<button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
	</li>
</template>

<script>
	import pubsub from 'pubsub-js'
	export default {
		name:'MyItem',
		//声明接收todo
		props:['todo'],
		methods: {
			//勾选or取消勾选
			handleCheck(id){
				//通知App组件将对应的todo对象的done值取反
				// this.checkTodo(id)
				this.$bus.$emit('checkTodo',id)
			},
			//删除
			handleDelete(id){
				if(confirm('确定删除吗？')){
					//通知App组件将对应的todo对象删除
					// this.deleteTodo(id)
					// this.$bus.$emit('deleteTodo',id)
					pubsub.publish('deleteTodo',id)
				}
			}
		},
	}
</script>
```

`App.vue`

```vue
<template>
	<div id="root">
		<div class="todo-container">
			<div class="todo-wrap">
				<MyHeader @addTodo="addTodo"/>
				<MyList :todos="todos"/>
				<MyFooter :todos="todos" @checkAllTodo="checkAllTodo" @clearAllTodo="clearAllTodo"/>
			</div>
		</div>
	</div>
</template>

<script>
	import pubsub from 'pubsub-js'
	import MyHeader from './components/MyHeader'
	import MyList from './components/MyList'
	import MyFooter from './components/MyFooter'

	export default {
		name:'App',
		components:{MyHeader,MyList,MyFooter},
		data() {
			return {
				//由于todos是MyHeader组件和MyFooter组件都在使用，所以放在App中（状态提升）
				todos:JSON.parse(localStorage.getItem('todos')) || []
			}
		},
		methods: {
			//添加一个todo
			addTodo(todoObj){
				this.todos.unshift(todoObj)
			},
			//勾选or取消勾选一个todo
			checkTodo(id){
				this.todos.forEach((todo)=>{
					if(todo.id === id) todo.done = !todo.done
				})
			},
			//删除一个todo
			deleteTodo(_,id){
				this.todos = this.todos.filter( todo => todo.id !== id )
			},
			//全选or取消全选
			checkAllTodo(done){
				this.todos.forEach((todo)=>{
					todo.done = done
				})
			},
			//清除所有已经完成的todo
			clearAllTodo(){
				this.todos = this.todos.filter((todo)=>{
					return !todo.done
				})
			}
		},
		watch: {
			todos:{
				deep:true,
				handler(value){
					localStorage.setItem('todos',JSON.stringify(value))
				}
			}
		},
		mounted() {
			this.$bus.$on('checkTodo',this.checkTodo)
			this.pubId = pubsub.subscribe('deleteTodo',this.deleteTodo)
		},
		beforeDestroy() {
			this.$bus.$off('checkTodo')
			pubsub.unsubscribe(this.pubId)
		},
	}
</script>
```

###  1.12  TodoList案例(编辑|nextTick)

nextTick

1. 语法：```this.$nextTick(回调函数)```
2. 作用：在下一次 DOM 更新结束后执行其指定的回调。
3. 什么时候用：当改变数据后，要基于更新后的新DOM进行某些操作时，要在nextTick所指定的回调函数中执行

`MyFooter.vue`

```vue
<template>
	<div class="todo-footer" v-show="total">
		<label>
			<!-- <input type="checkbox" :checked="isAll" @change="checkAll"/> -->
			<input type="checkbox" v-model="isAll"/>
		</label>
		<span>
			<span>已完成{{doneTotal}}</span> / 全部{{total}}
		</span>
		<button class="btn btn-danger" @click="clearAll">清除已完成任务</button>
	</div>
</template>

<script>
	export default {
		name:'MyFooter',
		props:['todos'],
		computed: {
			//总数
			total(){
				return this.todos.length
			},
			//已完成数
			doneTotal(){
				//此处使用reduce方法做条件统计
				/* const x = this.todos.reduce((pre,current)=>{
					console.log('@',pre,current)
					return pre + (current.done ? 1 : 0)
				},0) */
				//简写
				return this.todos.reduce((pre,todo)=> pre + (todo.done ? 1 : 0) ,0)
			},
			//控制全选框
			isAll:{
				//全选框是否勾选
				get(){
					return this.doneTotal === this.total && this.total > 0
				},
				//isAll被修改时set被调用
				set(value){
					// this.checkAllTodo(value)
					this.$emit('checkAllTodo',value)
				}
			}
		},
		methods: {
			/* checkAll(e){
				this.checkAllTodo(e.target.checked)
			} */
			//清空所有已完成
			clearAll(){
				// this.clearAllTodo()
				this.$emit('clearAllTodo')
			}
		},
	}
</script>
```

`MyHeader.vue`

```vue
<template>
	<div class="todo-header">
		<input type="text" placeholder="请输入你的任务名称，按回车键确认" v-model="title" @keyup.enter="add"/>
	</div>
</template>

<script>
	import {nanoid} from 'nanoid'
	export default {
		name:'MyHeader',
		data() {
			return {
				//收集用户输入的title
				title:''
			}
		},
		methods: {
			add(){
				//校验数据
				if(!this.title.trim()) return alert('输入不能为空')
				//将用户的输入包装成一个todo对象
				const todoObj = {id:nanoid(),title:this.title,done:false}
				//通知App组件去添加一个todo对象
				this.$emit('addTodo',todoObj,1,2,3)
				//清空输入
				this.title = ''
			}
		},
	}
</script>
```

`MyList.vue`

```vue
<template>
	<ul class="todo-main">
		<MyItem 
			v-for="todoObj in todos"
			:key="todoObj.id" 
			:todo="todoObj" 
		/>
	</ul>
</template>

<script>
	import MyItem from './MyItem'

	export default {
		name:'MyList',
		components:{MyItem},
		//声明接收App传递过来的数据
		props:['todos']
	}
</script>
```

`MyItem.vue`

```vue
<template>
	<li>
		<label>
			<input type="checkbox" :checked="todo.done" @change="handleCheck(todo.id)"/>
			<!-- 如下代码也能实现功能，但是不太推荐，因为有点违反原则，因为修改了props -->
			<!-- <input type="checkbox" v-model="todo.done"/> -->
			<span v-show="!todo.isEdit">{{todo.title}}</span>
			<input 
				type="text" 
				v-show="todo.isEdit" 
				:value="todo.title" 
				@blur="handleBlur(todo,$event)"
				ref="inputTitle"
			>
		</label>
		<button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
		<button v-show="!todo.isEdit" class="btn btn-edit" @click="handleEdit(todo)">编辑</button>
	</li>
</template>

<script>
	import pubsub from 'pubsub-js'
	export default {
		name:'MyItem',
		//声明接收todo
		props:['todo'],
		methods: {
			//勾选or取消勾选
			handleCheck(id){
				//通知App组件将对应的todo对象的done值取反
				// this.checkTodo(id)
				this.$bus.$emit('checkTodo',id)
			},
			//删除
			handleDelete(id){
				if(confirm('确定删除吗？')){
					//通知App组件将对应的todo对象删除
					// this.deleteTodo(id)
					// this.$bus.$emit('deleteTodo',id)
					pubsub.publish('deleteTodo',id)
				}
			},
			//编辑
			handleEdit(todo){
				if(todo.hasOwnProperty('isEdit')){
					todo.isEdit = true
				}else{
					// console.log('@')
					this.$set(todo,'isEdit',true)
				}
				this.$nextTick(function(){
					this.$refs.inputTitle.focus()
				})
			},
			//失去焦点回调（真正执行修改逻辑）
			handleBlur(todo,e){
				todo.isEdit = false
				if(!e.target.value.trim()) return alert('输入不能为空！')
				this.$bus.$emit('updateTodo',todo.id,e.target.value)
			}
		},
	}
</script>
```

### 1.13 过渡与动画

#####  1.13.1 Vue封装的过渡与动画

1.作用：在插入、更新或移除 DOM元素时，在合适的时候给元素添加样式类名

2.图示：

![image-20240419213219842](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130005646.png)

3.写法：

1. 准备好样式：

   - 元素进入的样式：
     1. v-enter：进入的起点
     2. v-enter-active：进入过程中
     3. v-enter-to：进入的终点
   - 元素离开的样式：
     1. v-leave：离开的起点
     2. v-leave-active：离开过程中
     3. v-leave-to：离开的终点

2. 使用```<transition>```包裹要过度的元素，并配置name属性：

   ```vue
   <transition name="hello">
   	<h1 v-show="isShow">你好啊！</h1>
   </transition>
   ```

3. 过渡的相关类名：
   ```
   1.xxx-enter-active：指定显示的transition
   2.xxx-leave-active：指定隐藏的transition
   3.xxx-enter/xxx-leave-to：指定隐藏时的样式
   ```

4. 备注：若有多个元素需要过渡，则需要使用：```<transition-group>```，且每个元素都要指定```key```值

`Test.vue`

```vue
<template>
	<div>
		<button @click="isShow = !isShow">显示/隐藏</button>
		<transition name="hello" appear>
			<h1 v-show="isShow">你好啊！</h1>
		</transition>
	</div>
</template>

<script>
	export default {
		name:'Test',
		data() {
			return {
				isShow:true
			}
		},
	}
</script>

<style scoped>
	h1{
		background-color: orange;
	}

	.hello-enter-active{
		animation: atguigu 0.5s linear;
	}

	.hello-leave-active{
		animation: atguigu 0.5s linear reverse;
	}

	@keyframes atguigu {
		from{
			transform: translateX(-100%);
		}
		to{
			transform: translateX(0px);
		}
	}
</style>
```

`Test2.vue`

```vue
<template>
	<div>
		<button @click="isShow = !isShow">显示/隐藏</button>
		<transition-group name="hello" appear>
			<h1 v-show="!isShow" key="1">你好啊！</h1>
			<h1 v-show="isShow" key="2">尚硅谷！</h1>
		</transition-group>
	</div>
</template>

<script>
	export default {
		name:'Test',
		data() {
			return {
				isShow:true
			}
		},
	}
</script>

<style scoped>
	h1{
		background-color: orange;
	}
	/* 进入的起点、离开的终点 */
	.hello-enter,.hello-leave-to{
		transform: translateX(-100%);
	}
	.hello-enter-active,.hello-leave-active{
		transition: 0.5s linear;
	}
	/* 进入的终点、离开的起点 */
	.hello-enter-to,.hello-leave{
		transform: translateX(0);
	}

</style>
```

#####  1.13.2 集成第三方动画

1.第三方动画库Animate.css：`https://animate.style`

2.安装` npm install animate.css --save`

`Test3.vue`

```vue
<template>
	<div>
		<button @click="isShow = !isShow">显示/隐藏</button>
		<transition-group 
			appear
			name="animate__animated animate__bounce" 
			enter-active-class="animate__swing"
			leave-active-class="animate__backOutUp"
		>
			<h1 v-show="!isShow" key="1">你好啊！</h1>
			<h1 v-show="isShow" key="2">尚硅谷！</h1>
		</transition-group>
	</div>
</template>

<script>
	import 'animate.css'
	export default {
		name:'Test',
		data() {
			return {
				isShow:true
			}
		},
	}
</script>

<style scoped>
	h1{
		background-color: orange;
	}
</style>
```

#####  1.13.3 todoList动画优化

`MyItem.vue`

```vue
<template>
	<li>
		<label>
			<input type="checkbox" :checked="todo.done" @change="handleCheck(todo.id)"/>
			<!-- 如下代码也能实现功能，但是不太推荐，因为有点违反原则，因为修改了props -->
			<!-- <input type="checkbox" v-model="todo.done"/> -->
			<span v-show="!todo.isEdit">{{todo.title}}</span>
			<input 
				type="text" 
				v-show="todo.isEdit" 
				:value="todo.title" 
				@blur="handleBlur(todo,$event)"
				ref="inputTitle"
			>
		</label>
		<button class="btn btn-danger" @click="handleDelete(todo.id)">删除</button>
		<button v-show="!todo.isEdit" class="btn btn-edit" @click="handleEdit(todo)">编辑</button>
	</li>
</template>

<script>
	import pubsub from 'pubsub-js'
	export default {
		name:'MyItem',
		//声明接收todo
		props:['todo'],
		methods: {
			//勾选or取消勾选
			handleCheck(id){
				//通知App组件将对应的todo对象的done值取反
				// this.checkTodo(id)
				this.$bus.$emit('checkTodo',id)
			},
			//删除
			handleDelete(id){
				if(confirm('确定删除吗？')){
					//通知App组件将对应的todo对象删除
					// this.deleteTodo(id)
					// this.$bus.$emit('deleteTodo',id)
					pubsub.publish('deleteTodo',id)
				}
			},
			//编辑
			handleEdit(todo){
				if(todo.hasOwnProperty('isEdit')){
					todo.isEdit = true
				}else{
					// console.log('@')
					this.$set(todo,'isEdit',true)
				}
				this.$nextTick(function(){
					this.$refs.inputTitle.focus()
				})
			},
			//失去焦点回调（真正执行修改逻辑）
			handleBlur(todo,e){
				todo.isEdit = false
				if(!e.target.value.trim()) return alert('输入不能为空！')
				this.$bus.$emit('updateTodo',todo.id,e.target.value)
			}
		},
	}
</script>

<style scoped>
	/*item*/
	li {
		list-style: none;
		height: 36px;
		line-height: 36px;
		padding: 0 5px;
		border-bottom: 1px solid #ddd;
	}

	li label {
		float: left;
		cursor: pointer;
	}

	li label li input {
		vertical-align: middle;
		margin-right: 6px;
		position: relative;
		top: -1px;
	}

	li button {
		float: right;
		display: none;
		margin-top: 3px;
	}

	li:before {
		content: initial;
	}

	li:last-child {
		border-bottom: none;
	}

	li:hover{
		background-color: #ddd;
	}
	
	li:hover button{
		display: block;
	}
</style>
```

## 2. Vue中的ajax

### 2.1 发送请求的方式

在前端应用程序中发送请求的常见方式包括以下几种：

1.**XMLHttpRequest (XHR)**：这是一种可以在浏览器中发送 HTTP 请求和接收 HTTP 响应的老式技术。虽然现在已经不是首选方案（主要是因为有了更现代化、更简洁的 `fetch` API），但仍然在一些老代码中使用。示例：

```javascript
var xhr = new XMLHttpRequest();
xhr.open("GET", "http://example.com/api/data", true);
xhr.onreadystatechange = function () {
  if (xhr.readyState === 4 && xhr.status === 200) {
    var response = JSON.parse(xhr.responseText);
    console.log(response);
  }
};
xhr.send();
```

2.**fetch API**：这是一个现代化的 JavaScript API，用于在网络中发送请求。`fetch` 提供了一个更加简洁和灵活的方式来发送请求，并且返回的是一个 Promise，这使得它可以很好地与现代JavaScript的异步特性结合

```javascript
fetch('http://example.com/api/data')
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok.');
    }
    return response.json();
  })
  .then(data => console.log(data))
  .catch(error => console.error('Fetch error:', error));
```

3.**Axios**：Axios 是一个基于 Promise 的 HTTP 客户端，适用于浏览器和 node.js。它是一个第三方库，需要先通过 npm 或 yarn 安装。Axios 提供了一些优点，如自动转换 JSON 数据和防御 XSRF 攻击的功能

```javascript
axios.get('http://example.com/api/data')
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error('Axios error:', error);
  });
```

4.**jQuery.ajax**：如果项目已经依赖 jQuery，可以使用jQuery提供的`.ajax()`方法。但随着现代JavaScript的发展和原生API的改进，jQuery不再是前端开发中的首选

```javascript
$.ajax({
  url: 'http://example.com/api/data',
  type: 'GET',
  success: function(data) {
    console.log(data);
  },
  error: function(error) {
    console.error('Ajax error:', error);
  }
});
```

5.**其他 HTTP 客户端库**：除了上述提到的方法外，还有许多其他的HTTP客户端库可以用于前端请求，例如 `superagent`、`got`、`request` 等

### 2.2 跨域问题

**跨域问题**：跨域问题（Cross-Origin Resource Sharing, CORS）是指一个源（origin）的网页试图请求访问另一个源的资源时遇到的安全限制问题。在这个上下文中，“源”指的是由协议、域名和端口三者组合定位的一个独特的地址。当尝试从与提供网页的服务器不同的服务器上获取资源时，就会出现跨域问题。这种安全限制是由浏览器的同源策略（Same-Origin Policy）引起的。同源策略出于安全考虑，默认阻止网页的脚本与不同源的服务器交互资源。这样可以帮助防止恶意文档，例如在一个源中运行的JavaScript代码，无法访问另一个源上的敏感文档内容

**什么是同源策略**：同源策略要求协议（如http或https）、域名和端口号均相同的情况下，才可以直接进行脚本交互。比如：

- `http://example.com/app`和`http://example.com/blog`是同源的，因为它们有相同的协议和域名
- `https://example.com/`和`http://example.com/`则不是同源的，尽管域名相同，但是协议不同
- `http://example.com/`和`http://sub.example.com/`也不是同源的，因为子域名不同
- `http://example.com:80/`和`http://example.com:8080/`也不是同源的，因为它们的端口号不同

**如何处理跨域问题**：跨域资源共享（CORS）是解决这个问题的一种机制。它通过让服务器在响应头中加入特殊的CORS headers来告知浏览器，从而允许特定的外部源或者任意源访问它的资源。例如，当一个跨域请求发起时，服务器可以通过响应头来指示浏览器允许这种请求：

```
Access-Control-Allow-Origin: *
```

或者允许特定域的跨域请求：

```
Access-Control-Allow-Origin: https://allowed-origin.com
```

以及其他 CORS 相关的响应头：

- `Access-Control-Allow-Methods`：指定允许的HTTP方法（如GET, POST等）
- `Access-Control-Allow-Headers`：指定允许的HTTP请求头
- `Access-Control-Allow-Credentials`：指明当请求中包含凭据（如Cookies和HTTP认证信息）时，响应是否被共享

**其他跨域解决方式**：除了CORS之外，还有其他几种方法可以绕过跨域限制：

- JSONP（JSON with Padding）：是一种老方法，通过script标签的src属性加载外部资源没有同源策略限制，可以拿到跨域的数据。不过这种方法只能用于GET请求
- 代理服务器（Server Side Proxy）：可以在服务端设置一个代理，把前端的跨域请求先发送到相同源的后端服务器，然后由后端服务器转发到目标服务器上，再将响应返回给前端
- 使用iframe和location.hash、window.name 或者 postMessage：通过在不同域上的页面间传递信息的各种技术
- Web Sockets：它不受同源策略限制，因此也可以用来实现跨源通信
- CORS Everywhere / Allow CORS 浏览器插件：这些插件可以帮助开发者在进行跨域请求的时候绕过限制，但仅适用于开发测试，不适合生产环境

###  2.2 vue脚手架配置代理

##### 2.2.1 脚手架配置代理

**解决开发环境Ajax跨域问题：使用代理服务器解决跨域问题**

```
# 使用axios进行请求发送
# 下载axios
npm i axios
```

node.js实现两台服务器（具体代码见`test_proxy_server`下的代码）

`server1.js`：node.js实现的服务器一

```js
const express = require('express')
const app = express()

app.use((request,response,next)=>{
	console.log('有人请求服务器1了');
	// console.log('请求来自于',request.get('Host'));
	// console.log('请求的地址',request.url);
	next()
})

app.get('/students',(request,response)=>{
	const students = [
		{id:'001',name:'tom',age:18},
		{id:'002',name:'jerry',age:19},
		{id:'003',name:'tony',age:120},
	]
	response.send(students)
})

app.listen(5000,(err)=>{
	if(!err) console.log('服务器1启动成功了,请求学生信息地址为：http://localhost:5000/students');
})
```

`server2.js`：node.js实现的服务器二

```js
const express = require('express')
const app = express()

app.use((request,response,next)=>{
	console.log('有人请求服务器2了');
	next()
})

app.get('/cars',(request,response)=>{
	const cars = [
		{id:'001',name:'奔驰',price:199},
		{id:'002',name:'马自达',price:109},
		{id:'003',name:'捷达',price:120},
	]
	response.send(cars)
})

app.listen(5001,(err)=>{
	if(!err) console.log('服务器2启动成功了,请求汽车信息地址为：http://localhost:5001/cars');
})
```

启动两台node.js服务器

![image-20240420010456676](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130005782.png)

`vue.config.js`：

```js
module.exports = {
  pages: {
    index: {
      //入口
      entry: 'src/main.js',
    },
  },
	lintOnSave:false, //关闭语法检查
	//开启代理服务器（方式一）
	/* devServer: {
    proxy: 'http://localhost:5000'
  }, */
	//开启代理服务器（方式二）
	devServer: {
    proxy: {
      '/atguigu': {
        target: 'http://localhost:5000',
				pathRewrite:{'^/atguigu':''},
        // ws: true, //用于支持websocket
        // changeOrigin: true //用于控制请求头中的host值
      },
      '/demo': {
        target: 'http://localhost:5001',
				pathRewrite:{'^/demo':''},
        // ws: true, //用于支持websocket
        // changeOrigin: true //用于控制请求头中的host值
      }
    }
  }
}
```

`App.vue`：

```vue
<template>
	<div>
		<button @click="getStudents">获取学生信息</button>
		<button @click="getCars">获取汽车信息</button>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		name:'App',
		methods: {
			getStudents(){
				axios.get('http://localhost:8080/atguigu/students').then(
					response => {
						console.log('请求成功了',response.data)
					},
					error => {
						console.log('请求失败了',error.message)
					}
				)
			},
			getCars(){
				axios.get('http://localhost:8080/demo/cars').then(
					response => {
						console.log('请求成功了',response.data)
					},
					error => {
						console.log('请求失败了',error.message)
					}
				)
			}
		},
	}
</script>
```

##### 2.2.2 总结：配置代理解决跨域问题

**方法一**：

	在vue.config.js中添加如下配置：

```js
devServer:{
  proxy:"http://localhost:5000"
}
```

说明：

1. 优点：配置简单，请求资源时直接发给前端（8080）即可
2. 缺点：不能配置多个代理，不能灵活的控制请求是否走代理
3. 工作方式：若按照上述配置代理，当请求了前端不存在的资源时，那么该请求会转发给服务器 （优先匹配前端资源）

**方法二**：

	编写vue.config.js配置具体代理规则：

```js
module.exports = {
	devServer: {
      proxy: {
      '/api1': {// 匹配所有以 '/api1'开头的请求路径
        target: 'http://localhost:5000',// 代理目标的基础路径
        changeOrigin: true,
        pathRewrite: {'^/api1': ''}
      },
      '/api2': {// 匹配所有以 '/api2'开头的请求路径
        target: 'http://localhost:5001',// 代理目标的基础路径
        changeOrigin: true,
        pathRewrite: {'^/api2': ''}
      }
    }
  }
}
/*
   changeOrigin设置为true时，服务器收到的请求头中的host为：localhost:5000
   changeOrigin设置为false时，服务器收到的请求头中的host为：localhost:8080
   changeOrigin默认值为true
*/
```

说明：

1. 优点：可以配置多个代理，且可以灵活的控制请求是否走代理
2. 缺点：配置略微繁琐，请求资源时必须加前缀

### 2.3 github用户搜索案例

##### 2.3.1 axios实现用户搜索

案例说明：github官方提供了一个用户搜索的接口，能够根据搜索关键词返回一些用户相关的信息。本案例结合这个接口实现用户搜索并展示的功能

github官方用户搜索接口：`https://api.github.com/search/users?q=xxx`

效果图：

![image-20240424215037463](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130005530.png)

axios：通用的Ajax请求库,官方推荐，使用广泛

`List.vue`：

```vue
<template>
	<div class="row">
		<!-- 展示用户列表 -->
		<div v-show="info.users.length" class="card" v-for="user in info.users" :key="user.login">
			<a :href="user.html_url" target="_blank">
				<img :src="user.avatar_url" style='width: 100px'/>
			</a>
			<p class="card-text">{{user.login}}</p>
		</div>
		<!-- 展示欢迎词 -->
		<h1 v-show="info.isFirst">欢迎使用！</h1>
		<!-- 展示加载中 -->
		<h1 v-show="info.isLoading">加载中....</h1>
		<!-- 展示错误信息 -->
		<h1 v-show="info.errMsg">{{info.errMsg}}</h1>
	</div>
</template>

<script>
	export default {
		name:'List',
		data() {
			return {
				info:{
					isFirst:true,
					isLoading:false,
					errMsg:'',
					users:[]
				}
			}
		},
		mounted() {
			this.$bus.$on('updateListData',(dataObj)=>{
				this.info = {...this.info,...dataObj}
			})
		},
	}
</script>
// 省略。。。
```

`Search.vue`：

```vue
<template>
	<section class="jumbotron">
		<h3 class="jumbotron-heading">Search Github Users</h3>
		<div>
			<input type="text" placeholder="enter the name you search" v-model="keyWord"/>&nbsp;
			<button @click="searchUsers">Search</button>
		</div>
	</section>
</template>

<script>
	import axios from 'axios'
	export default {
		name:'Search',
		data() {
			return {
				keyWord:''
			}
		},
		methods: {
			searchUsers(){
				//请求前更新List的数据
				this.$bus.$emit('updateListData',{isLoading:true,errMsg:'',users:[],isFirst:false})
				axios.get(`https://api.github.com/search/users?q=${this.keyWord}`).then(
					response => {
						console.log('请求成功了')
						//请求成功后更新List的数据
						this.$bus.$emit('updateListData',{isLoading:false,errMsg:'',users:response.data.items})
					},
					error => {
						//请求后更新List的数据
						this.$bus.$emit('updateListData',{isLoading:false,errMsg:error.message,users:[]})
					}
				)
			}
		},
	}
</script>
```

`App.vue`：

```vue
<template>
	<div class="container">
		<Search/>
		<List/>
	</div>
</template>

<script>
	import Search from './components/Search'
	import List from './components/List'
	export default {
		name:'App',
		components:{Search,List}
	}
</script>
```

`main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//关闭Vue的生产提示
Vue.config.productionTip = false

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	beforeCreate() {
		Vue.prototype.$bus = this
	},
})
```

##### 2.3.2 vue-resource实现搜索案例

vue插件库, vue1.x使用广泛，官方已不维护

`Search.vue`：

```vue
<template>
	<section class="jumbotron">
		<h3 class="jumbotron-heading">Search Github Users</h3>
		<div>
			<input type="text" placeholder="enter the name you search" v-model="keyWord"/>&nbsp;
			<button @click="searchUsers">Search</button>
		</div>
	</section>
</template>

<script>
	export default {
		name:'Search',
		data() {
			return {
				keyWord:''
			}
		},
		methods: {
			searchUsers(){
				//请求前更新List的数据
				this.$bus.$emit('updateListData',{isLoading:true,errMsg:'',users:[],isFirst:false})
				this.$http.get(`https://api.github.com/search/users?q=${this.keyWord}`).then(
					response => {
						console.log('请求成功了')
						//请求成功后更新List的数据
						this.$bus.$emit('updateListData',{isLoading:false,errMsg:'',users:response.data.items})
					},
					error => {
						//请求后更新List的数据
						this.$bus.$emit('updateListData',{isLoading:false,errMsg:error.message,users:[]})
					}
				)
			}
		},
	}
</script>
```

##  3. slot插槽

### 3.1 效果一(不使用插槽)

![image-20240502123031070](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006343.png)

`Category.vue`

```vue
<template>
  <div class="category">
    <h3>{{ title }}分类</h3>
    <ul>
      <li v-for="(item, index) in listData" :key="index">{{ item }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Category",
  props: ["listData", "title"],
};
</script>

<style scoped>
.category {
  background-color: skyblue;
  width: 200px;
  height: 300px;
}
h3 {
  text-align: center;
  background-color: orange;
}
</style>
```

`App.vue`

```vue
<template>
  <div class="container">
    <Category title="美食" :listData="foods"> </Category>
    <Category title="游戏" :listData="games"> </Category>
    <Category title="电影" :listData="films"> </Category>
  </div>
</template>

<script>
import Category from "./components/Category";
export default {
  name: "App",
  components: { Category },
  data() {
    return {
      foods: ["火锅", "烧烤", "小龙虾", "牛排"],
      games: ["红色警戒", "穿越火线", "劲舞团", "超级玛丽"],
      films: ["《教父》", "《拆弹专家》", "《你好，李焕英》", "《尚硅谷》"],
    };
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: space-around;
}
</style>
```

### 3.2 效果二（默认插槽)

![image-20240502124844697](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006716.png)

`Category.vue`：

```vue
<template>
	<div class="category">
		<h3>{{title}}分类</h3>
		<!-- 定义一个插槽（挖个坑，等着组件的使用者进行填充） -->
		<slot>我是一些默认值，当使用者没有传递具体结构时，我会出现</slot>
	</div>
</template>

<script>
	export default {
		name:'Category',
		props:['title']
	}
</script>

<style scoped>
	.category{
		background-color: skyblue;
		width: 200px;
		height: 300px;
	}
	h3{
		text-align: center;
		background-color: orange;
	}
	video{
		width: 100%;
	}
	img{
		width: 100%;
	}
</style>
```

`App.vue`：

```vue
<template>
	<div class="container">
		<Category title="美食" >
			<img src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="">
		</Category>

		<Category title="游戏" >
			<ul>
				<li v-for="(g,index) in games" :key="index">{{g}}</li>
			</ul>
		</Category>

		<Category title="电影">
			<video controls src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
		</Category>
	</div>
</template>

<script>
	import Category from './components/Category'
	export default {
		name:'App',
		components:{Category},
		data() {
			return {
				foods:['火锅','烧烤','小龙虾','牛排'],
				games:['红色警戒','穿越火线','劲舞团','超级玛丽'],
				films:['《教父》','《拆弹专家》','《你好，李焕英》','《尚硅谷》']
			}
		},
	}
</script>

<style scoped>
	.container{
		display: flex;
		justify-content: space-around;
	}
</style>
```

###  3.3 效果三(具名插槽)

![image-20240502132015229](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006089.png)

`Category.vue`：

```vue
<template>
	<div class="category">
		<h3>{{title}}分类</h3>
		<!-- 定义一个插槽（挖个坑，等着组件的使用者进行填充） -->
		<slot name="center">我是一些默认值，当使用者没有传递具体结构时，我会出现1</slot>
		<slot name="footer">我是一些默认值，当使用者没有传递具体结构时，我会出现2</slot>
	</div>
</template>

<script>
	export default {
		name:'Category',
		props:['title']
	}
</script>

<style scoped>
	.category{
		background-color: skyblue;
		width: 200px;
		height: 300px;
	}
	h3{
		text-align: center;
		background-color: orange;
	}
	video{
		width: 100%;
	}
	img{
		width: 100%;
	}
</style>
```

`App.vue`：

```vue
<template>
	<div class="container">
		<Category title="美食" >
			<img slot="center" src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="">
			<a slot="footer" href="http://www.atguigu.com">更多美食</a>
		</Category>

		<Category title="游戏" >
			<ul slot="center">
				<li v-for="(g,index) in games" :key="index">{{g}}</li>
			</ul>
			<div class="foot" slot="footer">
				<a href="http://www.atguigu.com">单机游戏</a>
				<a href="http://www.atguigu.com">网络游戏</a>
			</div>
		</Category>

		<Category title="电影">
			<video slot="center" controls src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
			<template v-slot:footer>
				<div class="foot">
					<a href="http://www.atguigu.com">经典</a>
					<a href="http://www.atguigu.com">热门</a>
					<a href="http://www.atguigu.com">推荐</a>
				</div>
				<h4>欢迎前来观影</h4>
			</template>
		</Category>
	</div>
</template>

<script>
	import Category from './components/Category'
	export default {
		name:'App',
		components:{Category},
		data() {
			return {
				foods:['火锅','烧烤','小龙虾','牛排'],
				games:['红色警戒','穿越火线','劲舞团','超级玛丽'],
				films:['《教父》','《拆弹专家》','《你好，李焕英》','《尚硅谷》']
			}
		},
	}
</script>

<style scoped>
	.container,.foot{
		display: flex;
		justify-content: space-around;
	}
	h4{
		text-align: center;
	}
</style>
```

如以上代码所示，在组件内部定义slot插槽时，可以使用name属性来为其设置具体的名称，需要注意的是，在使用此组件时，要使用template标签来包装插槽内容，对于template标签，通过v-slot来指定与其对应的插槽位置

###  3.4 效果四(作用域插槽)

![image-20240502135318061](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006711.png)

`Category.vue`：

```vue
<template>
	<div class="category">
		<h3>{{title}}分类</h3>
		<slot :games="games" msg="hello">我是默认的一些内容</slot>
	</div>
</template>

<script>
	export default {
		name:'Category',
		props:['title'],
		data() {
			return {
				games:['红色警戒','穿越火线','劲舞团','超级玛丽'],
			}
		},
	}
</script>

<style scoped>
	.category{
		background-color: skyblue;
		width: 200px;
		height: 300px;
	}
	h3{
		text-align: center;
		background-color: orange;
	}
	video{
		width: 100%;
	}
	img{
		width: 100%;
	}
</style>
```

`App.vue`：

```vue
<template>
	<div class="container">

		<Category title="游戏">
			<template scope="atguigu">
				<ul>
					<li v-for="(g,index) in atguigu.games" :key="index">{{g}}</li>
				</ul>
			</template>
		</Category>

		<Category title="游戏">
			<template scope="{games}">
				<ol>
					<li style="color:red" v-for="(g,index) in games" :key="index">{{g}}</li>
				</ol>
			</template>
		</Category>

		<Category title="游戏">
			<template slot-scope="{games}">
				<h4 v-for="(g,index) in games" :key="index">{{g}}</h4>
			</template>
		</Category>

	</div>
</template>

<script>
	import Category from './components/Category'
	export default {
		name:'App',
		components:{Category},
	}
</script>

<style scoped>
	.container,.foot{
		display: flex;
		justify-content: space-around;
	}
	h4{
		text-align: center;
	}
</style>
```

###  3.5 总结

1. 插槽理解：父组件向子组件传递带数据的标签，当一个组件有不确定的结构时,就需要使用slot技术，注意:插槽内容是在父组件中编译后,再传递给子组件的

2. 插槽作用：让父组件可以向子组件指定位置插入html结构，也是一种组件间通信的方式，适用于 <strong style="color:red">父组件 ===> 子组件</strong> 

3. 插槽分类：默认插槽、具名插槽、作用域插槽

4. 插槽使用方式：

   1. 默认插槽：

      ```vue
      父组件中：
              <Category>
                 <div>html结构1</div>
              </Category>
      子组件中：
              <template>
                  <div>
                     <!-- 定义插槽 -->
                     <slot>插槽默认内容...</slot>
                  </div>
              </template>
      ```

   2. 具名插槽：

      ```vue
      父组件中：
              <Category>
                  <template slot="center">
                    <div>html结构1</div>
                  </template>
      
                  <template v-slot:footer>
                     <div>html结构2</div>
                  </template>
              </Category>
      子组件中：
              <template>
                  <div>
                     <!-- 定义插槽 -->
                     <slot name="center">插槽默认内容...</slot>
                     <slot name="footer">插槽默认内容...</slot>
                  </div>
              </template>
      ```

   3. 作用域插槽：

      1. 理解：<span style="color:red">数据在组件的自身，但根据数据生成的结构需要组件的使用者来决定。</span>（games数据在Category组件中，但使用数据所遍历出来的结构由App组件决定）

      2. 具体编码：

         ```vue
         父组件中：
         		<Category>
         			<template scope="scopeData">
         				<!-- 生成的是ul列表 -->
         				<ul>
         					<li v-for="g in scopeData.games" :key="g">{{g}}</li>
         				</ul>
         			</template>
         		</Category>
         
         		<Category>
         			<template slot-scope="scopeData">
         				<!-- 生成的是h4标题 -->
         				<h4 v-for="g in scopeData.games" :key="g">{{g}}</h4>
         			</template>
         		</Category>
         子组件中：
                 <template>
                     <div>
                         <slot :games="games"></slot>
                     </div>
                 </template>
         		
                 <script>
                     export default {
                         name:'Category',
                         props:['title'],
                         //数据在子组件自身
                         data() {
                             return {
                                 games:['红色警戒','穿越火线','劲舞团','超级玛丽']
                             }
                         },
                     }
                 </script>
         ```



## 4.vuex

### 4.1 vuex简介

1.Github地址: `https://github.com/vuejs/vuex`。官方文档：`https://vuex.vuejs.org/zh/guide/`

2.vuex概念：专门在 Vue 中实现集中式状态（数据）管理的一个Vue插件，对vue应用中多个组件的共享状态进行集中式的管理(读/写)，也是一种组件间通信的方式，且适用于任意组件间通信

3.使用Vuex的场景

- 多个组件依赖于同一状态
- 来自不同组件的行为需要变更同一状态

4.多组件共享数据—全局事件总线实现

![image-20240502155741566](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006960.png)

5.多组件共享数据——vuex实现

![image-20240502160300047](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006860.png)

### 4.2 Vuex核心概念和api

**Vuex工作原理**：Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。它可以管理应用程序中的所有组件的状态。在一个大型的 Vue 应用程序中，组件之间的状态管理可能会变得复杂，而 Vuex 可以更好地组织和管理这些状态。Vuex 的核心概念包括状态（State）、Getter、Mutation、Action 和 Module。状态是应用程序中需要共享的数据，Getter 可以在不同组件之间获取这些状态，Mutation 用于修改状态，而 Action 则用于处理异步操作。Module 则可以将 Vuex 分割成多个模块，每个模块都拥有自己的状态、Getter、Mutation 和 Action



![image-20240502194027237](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130006439.png)

1.**state**：

```
1.vuex管理的状态对象
2.它应该是唯一的
3.示例代码:
            const state = {
            xxx:initvalue
            }
```

2.**actions**：

```
1.值为一个对象，包含多个响应用户动作的回调函数
2.通过commit()来触发mutation中函数的调用,间接更新state
3.如何触发actions中的回调?  在组件中使用: $store.dispatch('对应的action回调名')触发
4.可以包含异步代码（定时器, ajax等等)
5.示例代码:
const actions = {
    zzz ({commit，state}, data1){
      commit('yyy', {data1})
    }
}
```

3.**mutations**

```
1.值是一个对象，包含多个直接更新state的方法
2.谁能调用mutations中的方法? 如何调用? 
        在action中使用:commit('对应的mutations方法名')触发
3.mutations中方法的特点:不能写异步代码、只能单纯的操作state
4.示例代码:
            const mutations = {
                yyy (state,{data1}) {
                // 更新state的某个属性
                }
            }
```

4.**getters**

```
1.值为一个对象，包含多个用于返回数据的函数
⒉如何使用?—— $store.getters.XXX
3.示例代码:
            const getters = {
                  mmm(state){
                  return state.msg + '!'
                  }
            }
```

5.**modules**

```
1.包含多个module
2.一个module是一个store的配置对象
3.与一个组件（包含有共享数据)对应
```

### 4.3 求和案例

案例效果：

![image-20240502193355687](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130007760.png)

##### 4.3.1 纯vue实现

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{sum}}</h1>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment">+</button>
		<button @click="decrement">-</button>
		<button @click="incrementOdd">当前求和为奇数再加</button>
		<button @click="incrementWait">等一等再加</button>
	</div>
</template>

<script>
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
				sum:0 //当前的和
			}
		},
		methods: {
			increment(){
				this.sum += this.n
			},
			decrement(){
				this.sum -= this.n
			},
			incrementOdd(){
				if(this.sum % 2){
					this.sum += this.n
				}
			},
			incrementWait(){
				setTimeout(()=>{
					this.sum += this.n
				},500)
			},
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
	</div>
</template>

<script>
	import Count from './components/Count'
	export default {
		name:'App',
		components:{Count},
	}
</script>
```

##### 4.3.2 vuex实现

1.**下载Vuex**

```
# vue2中，要用vuex的3版本。vue3中，要用vuex的4版本
# 安装vuex的3版本
npm i vuex@3
```

2.**搭建vuex环境**

1. 创建文件：```src/store/index.js```

   ```js
   //引入Vue核心库
   import Vue from 'vue'
   //引入Vuex
   import Vuex from 'vuex'
   //应用Vuex插件
   Vue.use(Vuex)
   
   //准备actions对象——响应组件中用户的动作
   const actions = {}
   //准备mutations对象——修改state中的数据
   const mutations = {}
   //准备state对象——保存具体的数据
   const state = {}
   
   //创建并暴露store
   export default new Vuex.Store({
   	actions,
   	mutations,
   	state
   })
   ```

2. 在```main.js```中创建vm时传入```store```配置项

   ```js
   ......
   //引入store
   import store from './store'
   ......
   
   //创建vm
   new Vue({
   	el:'#app',
   	render: h => h(App),
   	store
   })
   ```

3.**求和案例**

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{$store.state.sum}}</h1>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment">+</button>
		<button @click="decrement">-</button>
		<button @click="incrementOdd">当前求和为奇数再加</button>
		<button @click="incrementWait">等一等再加</button>
	</div>
</template>

<script>
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
			}
		},
		methods: {
			increment(){
				this.$store.commit('JIA',this.n)
			},
			decrement(){
				this.$store.commit('JIAN',this.n)
			},
			incrementOdd(){
				this.$store.dispatch('jiaOdd',this.n)
			},
			incrementWait(){
				this.$store.dispatch('jiaWait',this.n)
			},
		},
		mounted() {
			console.log('Count',this)
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`index.js`：

```js
//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)

//准备actions——用于响应组件中的动作
const actions = {
	/* jia(context,value){
		console.log('actions中的jia被调用了')
		context.commit('JIA',value)
	},
	jian(context,value){
		console.log('actions中的jian被调用了')
		context.commit('JIAN',value)
	}, */
	jiaOdd(context,value){
		console.log('actions中的jiaOdd被调用了')
		if(context.state.sum % 2){
			context.commit('JIA',value)
		}
	},
	jiaWait(context,value){
		console.log('actions中的jiaWait被调用了')
		setTimeout(()=>{
			context.commit('JIA',value)
		},500)
	}
}
//准备mutations——用于操作数据（state）
const mutations = {
	JIA(state,value){
		console.log('mutations中的JIA被调用了')
		state.sum += value
	},
	JIAN(state,value){
		console.log('mutations中的JIAN被调用了')
		state.sum -= value
	}
}
//准备state——用于存储数据
const state = {
	sum:0 //当前的和
}

//创建并暴露store
export default new Vuex.Store({
	actions,
	mutations,
	state,
})
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
	</div>
</template>

<script>
	import Count from './components/Count'
	export default {
		name:'App',
		components:{Count},
		mounted() {
			// console.log('App',this)
		},
	}
</script>
```

`main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入插件
import vueResource from 'vue-resource'
//引入store
import store from './store'

//关闭Vue的生产提示
Vue.config.productionTip = false
//使用插件
Vue.use(vueResource)

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	store,
	beforeCreate() {
		Vue.prototype.$bus = this
	}
})
```

##### 4.3.3 总结

1.**搭建vuex环境**

1. 创建文件：```src/store/index.js```

   ```js
   //引入Vue核心库
   import Vue from 'vue'
   //引入Vuex
   import Vuex from 'vuex'
   //应用Vuex插件
   Vue.use(Vuex)
   
   //准备actions对象——响应组件中用户的动作
   const actions = {}
   //准备mutations对象——修改state中的数据
   const mutations = {}
   //准备state对象——保存具体的数据
   const state = {}
   
   //创建并暴露store
   export default new Vuex.Store({
   	actions,
   	mutations,
   	state
   })
   ```

2. 在```main.js```中创建vm时传入```store```配置项

   ```js
   ......
   //引入store
   import store from './store'
   ......
   
   //创建vm
   new Vue({
   	el:'#app',
   	render: h => h(App),
   	store
   })
   ```

2.**基本使用**

1. 初始化数据、配置```actions```、配置```mutations```，操作文件```store.js```

   ```js
   //引入Vue核心库
   import Vue from 'vue'
   //引入Vuex
   import Vuex from 'vuex'
   //引用Vuex
   Vue.use(Vuex)
   
   const actions = {
       //响应组件中加的动作
   	jia(context,value){
   		// console.log('actions中的jia被调用了',miniStore,value)
   		context.commit('JIA',value)
   	},
   }
   
   const mutations = {
       //执行加
   	JIA(state,value){
   		// console.log('mutations中的JIA被调用了',state,value)
   		state.sum += value
   	}
   }
   
   //初始化数据
   const state = {
      sum:0
   }
   
   //创建并暴露store
   export default new Vuex.Store({
   	actions,
   	mutations,
   	state,
   })
   ```

2. 组件中读取vuex中的数据：```$store.state.sum```

3. 组件中修改vuex中的数据：```$store.dispatch('action中的方法名',数据)``` 或 ```$store.commit('mutations中的方法名',数据)```

   >  备注：若没有网络请求或其他业务逻辑，组件中也可以越过actions，即不写```dispatch```，直接编写```commit```

### 4.4 getters配置项

##### 4.4.1 getters配置项使用

`index.js`：

```js
//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)

//准备actions——用于响应组件中的动作
const actions = {
	/* jia(context,value){
		console.log('actions中的jia被调用了')
		context.commit('JIA',value)
	},
	jian(context,value){
		console.log('actions中的jian被调用了')
		context.commit('JIAN',value)
	}, */
	jiaOdd(context,value){
		console.log('actions中的jiaOdd被调用了')
		if(context.state.sum % 2){
			context.commit('JIA',value)
		}
	},
	jiaWait(context,value){
		console.log('actions中的jiaWait被调用了')
		setTimeout(()=>{
			context.commit('JIA',value)
		},500)
	}
}
//准备mutations——用于操作数据（state）
const mutations = {
	JIA(state,value){
		console.log('mutations中的JIA被调用了')
		state.sum += value
	},
	JIAN(state,value){
		console.log('mutations中的JIAN被调用了')
		state.sum -= value
	}
}
//准备state——用于存储数据
const state = {
	sum:0 //当前的和
}
//准备getters——用于将state中的数据进行加工
const getters = {
	bigSum(state){
		return state.sum*10
	}
}

//创建并暴露store
export default new Vuex.Store({
	actions,
	mutations,
	state,
	getters
})
```

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{$store.state.sum}}</h1>
		<h3>当前求和放大10倍为：{{$store.getters.bigSum}}</h3>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment">+</button>
		<button @click="decrement">-</button>
		<button @click="incrementOdd">当前求和为奇数再加</button>
		<button @click="incrementWait">等一等再加</button>
	</div>
</template>

<script>
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
			}
		},
		methods: {
			increment(){
				this.$store.commit('JIA',this.n)
			},
			decrement(){
				this.$store.commit('JIAN',this.n)
			},
			incrementOdd(){
				this.$store.dispatch('jiaOdd',this.n)
			},
			incrementWait(){
				this.$store.dispatch('jiaWait',this.n)
			},
		},
		mounted() {
			console.log('Count',this.$store)
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
	</div>
</template>

<script>
	import Count from './components/Count'
	export default {
		name:'App',
		components:{Count},
		mounted() {
			// console.log('App',this)
		},
	}
</script>
```

`main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入插件
import vueResource from 'vue-resource'
//引入store
import store from './store'

//关闭Vue的生产提示
Vue.config.productionTip = false
//使用插件
Vue.use(vueResource)

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	store,
	beforeCreate() {
		Vue.prototype.$bus = this
	}
})
```

##### 4.4.2 总结

1.getters概念：当state中的数据需要经过加工后再使用时，可以使用getters加工

2.在```store.js```中追加```getters```配置

```js
......

const getters = {
	bigSum(state){
		return state.sum * 10
	}
}

//创建并暴露store
export default new Vuex.Store({
	......
	getters
})
```

3.组件中读取数据：```$store.getters.bigSum```

### 4.5 mapState 和 mapGetters

##### 4.5.1 mapState和mapGetters使用

1.<strong>mapState方法：</strong>用于映射```state```中的数据为计算属性

```js
computed: {
   //借助mapState生成计算属性：sum、school、subject（对象写法）
	...mapState({sum:'sum',school:'school',subject:'subject'}),
		
   //借助mapState生成计算属性：sum、school、subject（数组写法）
   ...mapState(['sum','school','subject']),
},
```

2.<strong>mapGetters方法：</strong>用于映射```getters```中的数据为计算属性

```js
computed: {
   //借助mapGetters生成计算属性：bigSum（对象写法）
   ...mapGetters({bigSum:'bigSum'}),

   //借助mapGetters生成计算属性：bigSum（数组写法）
   ...mapGetters(['bigSum'])
},
```

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{sum}}</h1>
		<h3>当前求和放大10倍为：{{bigSum}}</h3>
		<h3>我在{{school}}，学习{{subject}}</h3>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment">+</button>
		<button @click="decrement">-</button>
		<button @click="incrementOdd">当前求和为奇数再加</button>
		<button @click="incrementWait">等一等再加</button>
	</div>
</template>

<script>
	import {mapState,mapGetters} from 'vuex'
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
			}
		},
		computed:{
			//靠程序员自己亲自去写计算属性
			/* sum(){
				return this.$store.state.sum
			},
			school(){
				return this.$store.state.school
			},
			subject(){
				return this.$store.state.subject
			}, */

			//借助mapState生成计算属性，从state中读取数据。（对象写法）
			// ...mapState({he:'sum',xuexiao:'school',xueke:'subject'}),

			//借助mapState生成计算属性，从state中读取数据。（数组写法）
			...mapState(['sum','school','subject']),

			/* ******************************************************************** */

			/* bigSum(){
				return this.$store.getters.bigSum
			}, */

			//借助mapGetters生成计算属性，从getters中读取数据。（对象写法）
			// ...mapGetters({bigSum:'bigSum'})
			
			//借助mapGetters生成计算属性，从getters中读取数据。（数组写法）
			...mapGetters(['bigSum'])

		},
		methods: {
			increment(){
				this.$store.commit('JIA',this.n)
			},
			decrement(){
				this.$store.commit('JIAN',this.n)
			},
			incrementOdd(){
				this.$store.dispatch('jiaOdd',this.n)
			},
			incrementWait(){
				this.$store.dispatch('jiaWait',this.n)
			},
		},
		mounted() {
			const x = mapState({he:'sum',xuexiao:'school',xueke:'subject'})
			console.log(x)
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`index.js`：

```js
//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)

//准备actions——用于响应组件中的动作
const actions = {
	/* jia(context,value){
		console.log('actions中的jia被调用了')
		context.commit('JIA',value)
	},
	jian(context,value){
		console.log('actions中的jian被调用了')
		context.commit('JIAN',value)
	}, */
	jiaOdd(context,value){
		console.log('actions中的jiaOdd被调用了')
		if(context.state.sum % 2){
			context.commit('JIA',value)
		}
	},
	jiaWait(context,value){
		console.log('actions中的jiaWait被调用了')
		setTimeout(()=>{
			context.commit('JIA',value)
		},500)
	}
}
//准备mutations——用于操作数据（state）
const mutations = {
	JIA(state,value){
		console.log('mutations中的JIA被调用了')
		state.sum += value
	},
	JIAN(state,value){
		console.log('mutations中的JIAN被调用了')
		state.sum -= value
	}
}
//准备state——用于存储数据
const state = {
	sum:0, //当前的和
	school:'尚硅谷',
	subject:'前端'
}
//准备getters——用于将state中的数据进行加工
const getters = {
	bigSum(state){
		return state.sum*10
	}
}

//创建并暴露store
export default new Vuex.Store({
	actions,
	mutations,
	state,
	getters
})
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
	</div>
</template>

<script>
	import Count from './components/Count'
	export default {
		name:'App',
		components:{Count},
		mounted() {
			// console.log('App',this)
		},
	}
</script>
```

`main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入插件
import vueResource from 'vue-resource'
//引入store
import store from './store'

//关闭Vue的生产提示
Vue.config.productionTip = false
//使用插件
Vue.use(vueResource)

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	store,
	beforeCreate() {
		Vue.prototype.$bus = this
	}
})
```

##### 4.5.2 总结

`mapState`和`mapGetters`是两个便利的辅助函数，可以在组件中使用 Vuex 存储的状态和派生状态。通过使用这两个辅助函数，可以在组件中轻松地使用 Vuex 中的状态和 getter，而无需显式地访问 `$store` 对象。这样使得组件更加简洁和可维护

**mapState**：`mapState` 辅助函数用于将组件的计算属性和 Vuex 中的状态进行映射。它接收一个数组或对象作为参数，数组的元素是 Vuex 中的状态名，对象的键值对形式是 `{localStateName: 'vuexStateName'}`，其中 `localStateName` 是组件中计算属性的名称，`vuexStateName` 是 Vuex 中状态的名称

```javascript
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState([
      'count' // 将 this.count 映射为 this.$store.state.count
    ])
  }
}
```

**mapGetters**：`mapGetters` 辅助函数用于将组件的计算属性映射到 Vuex 中的 getter 上。它接收一个数组作为参数，数组的元素是 Vuex 中的 getter 名称

```javascript
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters([
      'doneTodosCount' // 将 this.doneTodosCount 映射为 this.$store.getters.doneTodosCount
    ])
  }
}
```

### 4.6 mapActions和mapMutations

1.<strong>mapActions方法：</strong>用于生成与```actions```对话的方法，即：包含```$store.dispatch(xxx)```的函数

```js
methods:{
   //靠mapActions生成：incrementOdd、incrementWait（对象形式）
   ...mapActions({incrementOdd:'jiaOdd',incrementWait:'jiaWait'})

   //靠mapActions生成：incrementOdd、incrementWait（数组形式）
   ...mapActions(['jiaOdd','jiaWait'])
}
```

2.<strong>mapMutations方法：</strong>用于生成与```mutations```对话的方法，即：包含```$store.commit(xxx)```的函数

```js
methods:{
   //靠mapActions生成：increment、decrement（对象形式）
   ...mapMutations({increment:'JIA',decrement:'JIAN'}),
   
   //靠mapMutations生成：JIA、JIAN（对象形式）
   ...mapMutations(['JIA','JIAN']),
}
```

> 备注：mapActions与mapMutations使用时，若需要传递参数需要：在模板中绑定事件时传递好参数，否则参数是事件对象

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{sum}}</h1>
		<h3>当前求和放大10倍为：{{bigSum}}</h3>
		<h3>我在{{school}}，学习{{subject}}</h3>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment(n)">+</button>
		<button @click="decrement(n)">-</button>
		<button @click="incrementOdd(n)">当前求和为奇数再加</button>
		<button @click="incrementWait(n)">等一等再加</button>
	</div>
</template>

<script>
	import {mapState,mapGetters,mapMutations,mapActions} from 'vuex'
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
			}
		},
		computed:{
			//借助mapState生成计算属性，从state中读取数据。（对象写法）
			// ...mapState({he:'sum',xuexiao:'school',xueke:'subject'}),

			//借助mapState生成计算属性，从state中读取数据。（数组写法）
			...mapState(['sum','school','subject']),

			/* ******************************************************************** */

			//借助mapGetters生成计算属性，从getters中读取数据。（对象写法）
			// ...mapGetters({bigSum:'bigSum'})
			
			//借助mapGetters生成计算属性，从getters中读取数据。（数组写法）
			...mapGetters(['bigSum'])

		},
		methods: {
			//程序员亲自写方法
			/* increment(){
				this.$store.commit('JIA',this.n)
			},
			decrement(){
				this.$store.commit('JIAN',this.n)
			}, */

			//借助mapMutations生成对应的方法，方法中会调用commit去联系mutations(对象写法)
			...mapMutations({increment:'JIA',decrement:'JIAN'}),

			//借助mapMutations生成对应的方法，方法中会调用commit去联系mutations(数组写法)
			// ...mapMutations(['JIA','JIAN']),

			/* ************************************************* */

			//程序员亲自写方法
			/* incrementOdd(){
				this.$store.dispatch('jiaOdd',this.n)
			},
			incrementWait(){
				this.$store.dispatch('jiaWait',this.n)
			}, */

			//借助mapActions生成对应的方法，方法中会调用dispatch去联系actions(对象写法)
			...mapActions({incrementOdd:'jiaOdd',incrementWait:'jiaWait'})

			//借助mapActions生成对应的方法，方法中会调用dispatch去联系actions(数组写法)
			// ...mapActions(['jiaOdd','jiaWait'])
		},
		mounted() {
			const x = mapState({he:'sum',xuexiao:'school',xueke:'subject'})
			console.log(x)
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`index.js`：

```js
//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)

//准备actions——用于响应组件中的动作
const actions = {
	/* jia(context,value){
		console.log('actions中的jia被调用了')
		context.commit('JIA',value)
	},
	jian(context,value){
		console.log('actions中的jian被调用了')
		context.commit('JIAN',value)
	}, */
	jiaOdd(context,value){
		console.log('actions中的jiaOdd被调用了')
		if(context.state.sum % 2){
			context.commit('JIA',value)
		}
	},
	jiaWait(context,value){
		console.log('actions中的jiaWait被调用了')
		setTimeout(()=>{
			context.commit('JIA',value)
		},500)
	}
}
//准备mutations——用于操作数据（state）
const mutations = {
	JIA(state,value){
		console.log('mutations中的JIA被调用了')
		state.sum += value
	},
	JIAN(state,value){
		console.log('mutations中的JIAN被调用了')
		state.sum -= value
	}
}
//准备state——用于存储数据
const state = {
	sum:0, //当前的和
	school:'尚硅谷',
	subject:'前端'
}
//准备getters——用于将state中的数据进行加工
const getters = {
	bigSum(state){
		return state.sum*10
	}
}

//创建并暴露store
export default new Vuex.Store({
	actions,
	mutations,
	state,
	getters
})
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
	</div>
</template>

<script>
	import Count from './components/Count'
	export default {
		name:'App',
		components:{Count},
		mounted() {
			// console.log('App',this)
		},
	}
</script>
```

`main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入插件
import vueResource from 'vue-resource'
//引入store
import store from './store'

//关闭Vue的生产提示
Vue.config.productionTip = false
//使用插件
Vue.use(vueResource)

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	store,
	beforeCreate() {
		Vue.prototype.$bus = this
	}
})
```

### 4.7 多组件共享数据

![image-20240503125455015](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130008283.png)

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{sum}}</h1>
		<h3>当前求和放大10倍为：{{bigSum}}</h3>
		<h3>我在{{school}}，学习{{subject}}</h3>
		<h3 style="color:red">Person组件的总人数是：{{personList.length}}</h3>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment(n)">+</button>
		<button @click="decrement(n)">-</button>
		<button @click="incrementOdd(n)">当前求和为奇数再加</button>
		<button @click="incrementWait(n)">等一等再加</button>
	</div>
</template>

<script>
	import {mapState,mapGetters,mapMutations,mapActions} from 'vuex'
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
			}
		},
		computed:{
			//借助mapState生成计算属性，从state中读取数据。（数组写法）
			...mapState(['sum','school','subject','personList']),
			//借助mapGetters生成计算属性，从getters中读取数据。（数组写法）
			...mapGetters(['bigSum'])
		},
		methods: {
			//借助mapMutations生成对应的方法，方法中会调用commit去联系mutations(对象写法)
			...mapMutations({increment:'JIA',decrement:'JIAN'}),
			//借助mapActions生成对应的方法，方法中会调用dispatch去联系actions(对象写法)
			...mapActions({incrementOdd:'jiaOdd',incrementWait:'jiaWait'})
		},
		mounted() {
			// const x = mapState({he:'sum',xuexiao:'school',xueke:'subject'})
			// console.log(x)
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`Person.vue`：

```vue
<template>
	<div>
		<h1>人员列表</h1>
		<h3 style="color:red">Count组件求和为：{{sum}}</h3>
		<input type="text" placeholder="请输入名字" v-model="name">
		<button @click="add">添加</button>
		<ul>
			<li v-for="p in personList" :key="p.id">{{p.name}}</li>
		</ul>
	</div>
</template>

<script>
	import {nanoid} from 'nanoid'
	export default {
		name:'Person',
		data() {
			return {
				name:''
			}
		},
		computed:{
			personList(){
				return this.$store.state.personList
			},
			sum(){
				return this.$store.state.sum
			}
		},
		methods: {
			add(){
				const personObj = {id:nanoid(),name:this.name}
				this.$store.commit('ADD_PERSON',personObj)
				this.name = ''
			}
		},
	}
</script>
```

`index.js`：

```js
//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//应用Vuex插件
Vue.use(Vuex)

//准备actions——用于响应组件中的动作
const actions = {
	/* jia(context,value){
		console.log('actions中的jia被调用了')
		context.commit('JIA',value)
	},
	jian(context,value){
		console.log('actions中的jian被调用了')
		context.commit('JIAN',value)
	}, */
	jiaOdd(context,value){
		console.log('actions中的jiaOdd被调用了')
		if(context.state.sum % 2){
			context.commit('JIA',value)
		}
	},
	jiaWait(context,value){
		console.log('actions中的jiaWait被调用了')
		setTimeout(()=>{
			context.commit('JIA',value)
		},500)
	}
}
//准备mutations——用于操作数据（state）
const mutations = {
	JIA(state,value){
		console.log('mutations中的JIA被调用了')
		state.sum += value
	},
	JIAN(state,value){
		console.log('mutations中的JIAN被调用了')
		state.sum -= value
	},
	ADD_PERSON(state,value){
		console.log('mutations中的ADD_PERSON被调用了')
		state.personList.unshift(value)
	}
}
//准备state——用于存储数据
const state = {
	sum:0, //当前的和
	school:'尚硅谷',
	subject:'前端',
	personList:[
		{id:'001',name:'张三'}
	]
}
//准备getters——用于将state中的数据进行加工
const getters = {
	bigSum(state){
		return state.sum*10
	}
}

//创建并暴露store
export default new Vuex.Store({
	actions,
	mutations,
	state,
	getters
})
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
		<hr>
		<Person/>
	</div>
</template>

<script>
	import Count from './components/Count'
	import Person from './components/Person'
	export default {
		name:'App',
		components:{Count,Person},
		mounted() {
			// console.log('App',this)
		},
	}
</script>
```

### 4.8 vuex模块化+命名空间

1.模块化+命名空间目的：让代码更好维护，让多种数据分类更加明确

2.修改```store.js```

```javascript
const countAbout = {
 namespaced:true,//开启命名空间
 state:{x:1},
 mutations: { ... },
 actions: { ... },
 getters: {
   bigSum(state){
	  return state.sum * 10
   }
 }
}

const personAbout = {
 namespaced:true,//开启命名空间
 state:{ ... },
 mutations: { ... },
 actions: { ... }
}

const store = new Vuex.Store({
 modules: {
   countAbout,
   personAbout
 }
})
```

3.开启命名空间后，组件中读取state数据：

```js
//方式一：自己直接读取
this.$store.state.personAbout.list
//方式二：借助mapState读取：
...mapState('countAbout',['sum','school','subject']),
```

4.开启命名空间后，组件中读取getters数据：

```js
//方式一：自己直接读取
this.$store.getters['personAbout/firstPersonName']
//方式二：借助mapGetters读取：
...mapGetters('countAbout',['bigSum'])
```

5.开启命名空间后，组件中调用dispatch

```js
//方式一：自己直接dispatch
this.$store.dispatch('personAbout/addPersonWang',person)
//方式二：借助mapActions：
...mapActions('countAbout',{incrementOdd:'jiaOdd',incrementWait:'jiaWait'})
```

6.开启命名空间后，组件中调用commit

```js
//方式一：自己直接commit
this.$store.commit('personAbout/ADD_PERSON',person)
//方式二：借助mapMutations：
...mapMutations('countAbout',{increment:'JIA',decrement:'JIAN'}),
```

`Count.vue`：

```vue
<template>
	<div>
		<h1>当前求和为：{{sum}}</h1>
		<h3>当前求和放大10倍为：{{bigSum}}</h3>
		<h3>我在{{school}}，学习{{subject}}</h3>
		<h3 style="color:red">Person组件的总人数是：{{personList.length}}</h3>
		<select v-model.number="n">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<button @click="increment(n)">+</button>
		<button @click="decrement(n)">-</button>
		<button @click="incrementOdd(n)">当前求和为奇数再加</button>
		<button @click="incrementWait(n)">等一等再加</button>
	</div>
</template>

<script>
	import {mapState,mapGetters,mapMutations,mapActions} from 'vuex'
	export default {
		name:'Count',
		data() {
			return {
				n:1, //用户选择的数字
			}
		},
		computed:{
			//借助mapState生成计算属性，从state中读取数据。（数组写法）
			...mapState('countAbout',['sum','school','subject']),
			...mapState('personAbout',['personList']),
			//借助mapGetters生成计算属性，从getters中读取数据。（数组写法）
			...mapGetters('countAbout',['bigSum'])
		},
		methods: {
			//借助mapMutations生成对应的方法，方法中会调用commit去联系mutations(对象写法)
			...mapMutations('countAbout',{increment:'JIA',decrement:'JIAN'}),
			//借助mapActions生成对应的方法，方法中会调用dispatch去联系actions(对象写法)
			...mapActions('countAbout',{incrementOdd:'jiaOdd',incrementWait:'jiaWait'})
		},
		mounted() {
			console.log(this.$store)
		},
	}
</script>

<style lang="css">
	button{
		margin-left: 5px;
	}
</style>
```

`Person.vue`：

```vue
<template>
	<div>
		<h1>人员列表</h1>
		<h3 style="color:red">Count组件求和为：{{sum}}</h3>
		<h3>列表中第一个人的名字是：{{firstPersonName}}</h3>
		<input type="text" placeholder="请输入名字" v-model="name">
		<button @click="add">添加</button>
		<button @click="addWang">添加一个姓王的人</button>
		<button @click="addPersonServer">添加一个人，名字随机</button>
		<ul>
			<li v-for="p in personList" :key="p.id">{{p.name}}</li>
		</ul>
	</div>
</template>

<script>
	import {nanoid} from 'nanoid'
	export default {
		name:'Person',
		data() {
			return {
				name:''
			}
		},
		computed:{
			personList(){
				return this.$store.state.personAbout.personList
			},
			sum(){
				return this.$store.state.countAbout.sum
			},
			firstPersonName(){
				return this.$store.getters['personAbout/firstPersonName']
			}
		},
		methods: {
			add(){
				const personObj = {id:nanoid(),name:this.name}
				this.$store.commit('personAbout/ADD_PERSON',personObj)
				this.name = ''
			},
			addWang(){
				const personObj = {id:nanoid(),name:this.name}
				this.$store.dispatch('personAbout/addPersonWang',personObj)
				this.name = ''
			},
			addPersonServer(){
				this.$store.dispatch('personAbout/addPersonServer')
			}
		},
	}
</script>
```

`count.js`：

```js
//求和相关的配置
export default {
	namespaced:true,
	actions:{
		jiaOdd(context,value){
			console.log('actions中的jiaOdd被调用了')
			if(context.state.sum % 2){
				context.commit('JIA',value)
			}
		},
		jiaWait(context,value){
			console.log('actions中的jiaWait被调用了')
			setTimeout(()=>{
				context.commit('JIA',value)
			},500)
		}
	},
	mutations:{
		JIA(state,value){
			console.log('mutations中的JIA被调用了')
			state.sum += value
		},
		JIAN(state,value){
			console.log('mutations中的JIAN被调用了')
			state.sum -= value
		},
	},
	state:{
		sum:0, //当前的和
		school:'尚硅谷',
		subject:'前端',
	},
	getters:{
		bigSum(state){
			return state.sum*10
		}
	},
}
```

`person.js`：

```js
//人员管理相关的配置
import axios from 'axios'
import { nanoid } from 'nanoid'
export default {
	namespaced:true,
	actions:{
		addPersonWang(context,value){
			if(value.name.indexOf('王') === 0){
				context.commit('ADD_PERSON',value)
			}else{
				alert('添加的人必须姓王！')
			}
		},
		addPersonServer(context){
			axios.get('https://api.uixsj.cn/hitokoto/get?type=social').then(
				response => {
					context.commit('ADD_PERSON',{id:nanoid(),name:response.data})
				},
				error => {
					alert(error.message)
				}
			)
		}
	},
	mutations:{
		ADD_PERSON(state,value){
			console.log('mutations中的ADD_PERSON被调用了')
			state.personList.unshift(value)
		}
	},
	state:{
		personList:[
			{id:'001',name:'张三'}
		]
	},
	getters:{
		firstPersonName(state){
			return state.personList[0].name
		}
	},
}
```

`index.js`：

```js
//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
import countOptions from './count'
import personOptions from './person'
//应用Vuex插件
Vue.use(Vuex)

//创建并暴露store
export default new Vuex.Store({
	modules:{
		countAbout:countOptions,
		personAbout:personOptions
	}
})
```

`App.vue`：

```vue
<template>
	<div>
		<Count/>
		<hr>
		<Person/>
	</div>
</template>

<script>
	import Count from './components/Count'
	import Person from './components/Person'

	export default {
		name:'App',
		components:{Count,Person},
		mounted() {
			// console.log('App',this)
		},
	}
</script>
```

## 5.路由(vue-router)

### 5.1 路由(vue-router)简介

```
1.对vue-router的理解
        1.vue-router是vue的一个插件库，专门用来实现SPA应用
        2.使用前下载vue-router:  npm i vue-router
2.对SPA应用的理解
        1.单页Web应用(single page web application,SPA)
        2.整个应用只有一个完整的页面
        3.点击页面中的导航链接不会刷新页面，只会做页面的局部更新
        4.数据需要通过ajax请求获取
        
3.什么是路由?
        1.一个路由就是一组映射关系(key-value)
        2.key为路径, value可能是function或component
        
4.路由分类
    1.后端路由:
            1)理解:value是function,用于处理客户端提交的请求
            2)工作过程:服务器接收到一个请求时,根据请求路径找到匹配的函数来处理请求,返回响应数据
    2.前端路由:
            1)理解:value是component，用于展示页面内容
            2)工作过程:当浏览器的路径改变时,对应的组件就会显示
```

现实中的路由：

![image-20240503164133446](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130008946.png)

vue-router路由作用：实现SPA应用中导航区和展示区的匹配和展示。导航区的路径是key，展示区的组件是value

![image-20240503184535662](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130009416.png)

### 5.2 基本使用

1.vue-router安装

```
# 使用前下载安装vue-router
# 2022年2月7日以后，vue-router的默认版本，为4版本
# vue-router4，只能用在vue3中使用。vue-router3才能用在vue2中
# 安装vue-router，命令：npm i vue-router
npm i vue-router@3
```

2.应用插件：```Vue.use(VueRouter)```

3.编写router配置项:

```js
//引入VueRouter
import VueRouter from 'vue-router'
//引入Luyou 组件
import About from '../components/About'
import Home from '../components/Home'

//创建router实例对象，去管理一组一组的路由规则
const router = new VueRouter({
routes:[
	{
		path:'/about',
		component:About
	},
	{
		path:'/home',
		component:Home
	}
]
})

//暴露router
export default router
```

4.实现切换（active-class可配置高亮样式）

```vue
<router-link active-class="active" to="/about">About</router-link>
```

5.指定展示位置

```vue
<router-view></router-view>
```



案例效果：

![image-20240503222210176](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130009196.png)

`About.vue`：

```vue
<template>
	<h2>我是About的内容</h2>
</template>

<script>
	export default {
		name:'About'
	}
</script>
```

`Home.vue`：

```vue
<template>
	<h2>我是Home的内容</h2>
</template>

<script>
	export default {
		name:'Home'
	}
</script>
```

`index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../components/About'
import Home from '../components/Home'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			path:'/about',
			component:About
		},
		{
			path:'/home',
			component:Home
		}
	]
})
```

`App.vue`：

```vue
<template>
  <div>
    <div class="row">
      <div class="col-xs-offset-2 col-xs-8">
        <div class="page-header"><h2>Vue Router Demo</h2></div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
					<!-- 原始html中我们使用a标签实现页面的跳转 -->
          <!-- <a class="list-group-item active" href="./about.html">About</a> -->
          <!-- <a class="list-group-item" href="./home.html">Home</a> -->

					<!-- Vue中借助router-link标签实现路由的切换 -->
					<router-link class="list-group-item" active-class="active" to="/about">About</router-link>
          <router-link class="list-group-item" active-class="active" to="/home">Home</router-link>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="panel">
          <div class="panel-body">
						<!-- 指定组件的呈现位置 -->
            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	export default {
		name:'App',
	}
</script>
```

`main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'
//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import router from './router'

//关闭Vue的生产提示
Vue.config.productionTip = false
//应用插件
Vue.use(VueRouter)

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
	router:router
})
```

### 5.3 几个注意事项

1.路由组件通常存放在```pages```文件夹，一般组件通常存放在```components```文件夹

2.通过切换，“隐藏”了的路由组件，默认是被销毁掉的，需要的时候再去挂载

3.每个组件都有自己的```$route```属性，里面存储着自己的路由信息

4.整个应用只有一个router，可以通过组件的```$router```属性获取到

### 5.4 多级路由(嵌套路由)

1.配置路由规则，使用children配置项：

```js
routes:[
{
	path:'/about',
	component:About,
},
{
	path:'/home',
	component:Home,
	children:[ //通过children配置子级路由
		{
			path:'news', //此处一定不要写：/news
			component:News
		},
		{
			path:'message',//此处一定不要写：/message
			component:Message
		}
	]
}
]
```

2.跳转（要写完整路径）：

```vue
<router-link to="/home/news">News</router-link>
```

3.案例效果：



![image-20240504093318243](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130009108.png)



`src\pages\About.vue`

```vue
<template>
	<h2>我是About的内容</h2>
</template>

<script>
	export default {
		name:'About',
		/* beforeDestroy() {
			console.log('About组件即将被销毁了')
		},*/
		/* mounted() {
			console.log('About组件挂载完毕了',this)
			window.aboutRoute = this.$route
			window.aboutRouter = this.$router
		},  */
	}
</script>
```

`src\pages\Home.vue`

```vue
<template>
	<div>
		<h2>Home组件内容</h2>
		<div>
			<ul class="nav nav-tabs">
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/news">News</router-link>
				</li>
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/message">Message</router-link>
				</li>
			</ul>
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	export default {
		name:'Home',
		/* beforeDestroy() {
			console.log('Home组件即将被销毁了')
		}, */
		/* mounted() {
			console.log('Home组件挂载完毕了',this)
			window.homeRoute = this.$route
			window.homeRouter = this.$router
		},  */
	}
</script>
```

`src\pages\News.vue`

```vue
<template>
	<ul>
		<li>news001</li>
		<li>news002</li>
		<li>news003</li>
	</ul>
</template>

<script>
	export default {
		name:'News'
	}
</script>
```

`src\pages\Message.vue`

```vue
<template>
	<div>
		<ul>
			<li>
				<a href="/message1">message001</a>&nbsp;&nbsp;
			</li>
			<li>
				<a href="/message2">message002</a>&nbsp;&nbsp;
			</li>
			<li>
				<a href="/message/3">message003</a>&nbsp;&nbsp;
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name:'Message'
	}
</script>
```

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			path:'/about',
			component:About
		},
		{
			path:'/home',
			component:Home,
			children:[
				{
					path:'news',
					component:News,
				},
				{
					path:'message',
					component:Message,
				}
			]
		}
	]
})
```

`src\App.vue`：

```vue
<template>
  <div>
    <div class="row">
      <Banner/>
    </div>
    <div class="row">
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
					<!-- 原始html中我们使用a标签实现页面的跳转 -->
          <!-- <a class="list-group-item active" href="./about.html">About</a> -->
          <!-- <a class="list-group-item" href="./home.html">Home</a> -->

					<!-- Vue中借助router-link标签实现路由的切换 -->
					<router-link class="list-group-item" active-class="active" to="/about">About</router-link>
          <router-link class="list-group-item" active-class="active" to="/home">Home</router-link>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="panel">
          <div class="panel-body">
						<!-- 指定组件的呈现位置 -->
            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	import Banner from './components/Banner'
	export default {
		name:'App',
		components:{Banner}
	}
</script>
```

### 5.5 路由传参

路由的query参数

1.传递参数

```vue
<!-- 跳转路由并携带query参数，to的字符串写法 -->
<!-- <router-link :to="`/home/message/detail?id=${m.id}&title=${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp; -->

<!-- 跳转路由并携带query参数，to的对象写法 -->
<router-link :to="{
    path:'/home/message/detail',
    query:{
        id:m.id,
        title:m.title
    }
}">
    {{m.title}}
</router-link>
```

2.接收参数：

```js
		<li>消息编号：{{$route.query.id}}</li>
		<li>消息标题：{{$route.query.title}}</li>
```

案例效果：

![image-20240504110128935](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130009184.png)



`src\pages\Detail.vue`：

```vue
<template>
	<ul>
		<li>消息编号：{{$route.query.id}}</li>
		<li>消息标题：{{$route.query.title}}</li>
	</ul>
</template>

<script>
	export default {
		name:'Detail',
		mounted() {
			console.log(this.$route)
		},
	}
</script>
```

`src\pages\Message.vue`：

```vue
<template>
	<div>
		<ul>
			<li v-for="m in messageList" :key="m.id">
				<!-- 跳转路由并携带query参数，to的字符串写法 -->
				<!-- <router-link :to="`/home/message/detail?id=${m.id}&title=${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp; -->

				<!-- 跳转路由并携带query参数，to的对象写法 -->
				<router-link :to="{
					path:'/home/message/detail',
					query:{
						id:m.id,
						title:m.title
					}
				}">
					{{m.title}}
				</router-link>
			
			</li>
		</ul>
		<hr>
		<router-view></router-view>
	</div>
</template>

<script>
	export default {
		name:'Message',
		data() {
			return {
				messageList:[
					{id:'001',title:'消息001'},
					{id:'002',title:'消息002'},
					{id:'003',title:'消息003'}
				]
			}
		},
	}
</script>
```

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			path:'/about',
			component:About
		},
		{
			path:'/home',
			component:Home,
			children:[
				{
					path:'news',
					component:News,
				},
				{
					path:'message',
					component:Message,
					children:[
						{
							path:'detail',
							component:Detail,
						}
					]
				}
			]
		}
	]
})
```

### 5.6 路由命名

1.作用：可以简化路由的跳转

2.如何使用

2.1 给路由命名：

  ```js
  {
	path:'/demo',
	component:Demo,
	children:[
		{
			path:'test',
			component:Test,
			children:[
				{
					name:'hello' //给路由命名
					path:'welcome',
					component:Hello,
				}
			]
		}
	]
  }
  ```

2.2 简化跳转：

  ```vue
  <!--简化前，需要写完整的路径 -->
  <router-link to="/demo/test/welcome">跳转</router-link>
  
  <!--简化后，直接通过名字跳转 -->
  <router-link :to="{name:'hello'}">跳转</router-link>
  
  <!--简化写法配合传递参数 -->
  <router-link 
	:to="{
		name:'hello',
		query:{
		   id:666,
			  title:'你好'
		}
	}"
  >跳转</router-link>
  ```

`src\pages\Message.vue`：

```vue
<template>
	<div>
		<ul>
			<li v-for="m in messageList" :key="m.id">
				<!-- 跳转路由并携带query参数，to的字符串写法 -->
				<!-- <router-link :to="`/home/message/detail?id=${m.id}&title=${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp; -->

				<!-- 跳转路由并携带query参数，to的对象写法 -->
				<router-link :to="{
					name:'xiangqing',
					query:{
						id:m.id,
						title:m.title
					}
				}">
					{{m.title}}
				</router-link>
			
			</li>
		</ul>
		<hr>
		<router-view></router-view>
	</div>
</template>

<script>
	export default {
		name:'Message',
		data() {
			return {
				messageList:[
					{id:'001',title:'消息001'},
					{id:'002',title:'消息002'},
					{id:'003',title:'消息003'}
				]
			}
		},
	}
</script>
```

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			name:'guanyu',
			path:'/about',
			component:About
		},
		{
			path:'/home',
			component:Home,
			children:[
				{
					path:'news',
					component:News,
				},
				{
					path:'message',
					component:Message,
					children:[
						{
							name:'xiangqing',
							path:'detail',
							component:Detail,
						}
					]
				}
			]
		}
	]
})
```

### 5.7 路由的params参数

路由的params参数：

1.配置路由，声明接收params参数

```js
{
path:'/home',
component:Home,
children:[
	{
		path:'news',
		component:News
	},
	{
		component:Message,
		children:[
			{
				name:'xiangqing',
				path:'detail/:id/:title', //使用占位符声明接收params参数
				component:Detail
			}
		]
	}
]
}
```

2.传递参数

```vue
<!-- 跳转并携带params参数，to的字符串写法 -->
<router-link :to="/home/message/detail/666/你好">跳转</router-link>
			
<!-- 跳转并携带params参数，to的对象写法 -->
<router-link 
:to="{
	name:'xiangqing',
	params:{
	   id:666,
       title:'你好'
	}
}"
>跳转</router-link>
```

> 特别注意：路由携带params参数时，若使用to的对象写法，则不能使用path配置项，必须使用name配置！

3.接收参数：

```js
$route.params.id
$route.params.title
```

### 5.8 路由的props传参

路由的props配置作用：让路由组件更方便的收到参数

```js
{
	name:'xiangqing',
	path:'detail/:id',
	component:Detail,

	//第一种写法：props值为对象，该对象中所有的key-value的组合最终都会通过props传给Detail组件
	// props:{a:900}

	//第二种写法：props值为布尔值，布尔值为true，则把路由收到的所有params参数通过props传给Detail组件
	// props:true
	
	//第三种写法：props值为函数，该函数返回的对象中每一组key-value都会通过props传给Detail组件
	props(route){
		return {
			id:route.query.id,
			title:route.query.title
		}
	}
}
```

`src\pages\Detail.vue`：

```vue
<template>
	<ul>
		<li>消息编号：{{id}}</li>
		<li>消息标题：{{title}}</li>
	</ul>
</template>

<script>
	export default {
		name:'Detail',
		props:['id','title'],
		computed: {
			// id(){
			// 	return this.$route.query.id
			// },
			// title(){
			// 	return this.$route.query.title
			// },
		},
		mounted() {
			// console.log(this.$route)
		},
	}
</script>
```

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			name:'guanyu',
			path:'/about',
			component:About
		},
		{
			path:'/home',
			component:Home,
			children:[
				{
					path:'news',
					component:News,
				},
				{
					path:'message',
					component:Message,
					children:[
						{
							name:'xiangqing',
							path:'detail',
							component:Detail,

							//props的第一种写法，值为对象，该对象中的所有key-value都会以props的形式传给Detail组件。
							// props:{a:1,b:'hello'}

							//props的第二种写法，值为布尔值，若布尔值为真，就会把该路由组件收到的所有params参数，以props的形式传给Detail组件。
							// props:true

							//props的第三种写法，值为函数
							props($route){
								return {
									id:$route.query.id,
									title:$route.query.title,
									a:1,
									b:'hello'
								}
							}

						}
					]
				}
			]
		}
	]
})
```

### 5.9`<router-link>`的replace属性

1.replace属性作用：控制路由跳转时操作浏览器历史记录的模式

2.浏览器的历史记录有两种写入方式：分别为```push```和```replace```，```push```是追加历史记录，```replace```是替换当前记录。路由跳转时候默认为```push```

3.如何开启```replace```模式：```<router-link replace .......>News</router-link>```

`src\pages\Home.vue`

```vue
<template>
	<div>
		<h2>Home组件内容</h2>
		<div>
			<ul class="nav nav-tabs">
				<li>
					<router-link replace class="list-group-item" active-class="active" to="/home/news">News</router-link>
				</li>
				<li>
					<router-link replace class="list-group-item" active-class="active" to="/home/message">Message</router-link>
				</li>
			</ul>
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	export default {
		name:'Home',
		/* beforeDestroy() {
			console.log('Home组件即将被销毁了')
		}, */
		/* mounted() {
			console.log('Home组件挂载完毕了',this)
			window.homeRoute = this.$route
			window.homeRouter = this.$router
		},  */
	}
</script>
```

### 5.10 编程式路由导航

1.作用：不借助```<router-link> ```实现路由跳转，让路由跳转更加灵活

2.具体编码：

```js
//$router的两个API
this.$router.push({
name:'xiangqing',
	params:{
		id:xxx,
		title:xxx
	}
})

this.$router.replace({
name:'xiangqing',
	params:{
		id:xxx,
		title:xxx
	}
})
this.$router.forward() //前进
this.$router.back() //后退
this.$router.go() //可前进也可后退
```

案例效果：

![image-20240504185252137](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130010373.png)

`src\components\Banner.vue`：

```vue
<template>
	<div class="col-xs-offset-2 col-xs-8">
		<div class="page-header">
			<h2>Vue Router Demo</h2>
			<button @click="back">后退</button>
			<button @click="forward">前进</button>
			<button @click="test">测试一下go</button>
		</div>
	</div>
</template>

<script>
	export default {
		name:'Banner',
		methods: {
			back(){
				this.$router.back()
				// console.log(this.$router)
			},
			forward(){
				this.$router.forward()
			},
			test(){
				this.$router.go(3)
			}
		},
	}
</script>
```

`src\pages\About.vue`：

```vue
<template>
	<h2>我是About的内容</h2>
</template>

<script>
	export default {
		name:'About',
		/* beforeDestroy() {
			console.log('About组件即将被销毁了')
		},*/
		/* mounted() {
			console.log('About组件挂载完毕了',this)
			window.aboutRoute = this.$route
			window.aboutRouter = this.$router
		},  */
	}
</script>
```

`src\pages\Home.vue`：

```vue
<template>
	<div>
		<h2>Home组件内容</h2>
		<div>
			<ul class="nav nav-tabs">
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/news">News</router-link>
				</li>
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/message">Message</router-link>
				</li>
			</ul>
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	export default {
		name:'Home',
		/* beforeDestroy() {
			console.log('Home组件即将被销毁了')
		}, */
		/* mounted() {
			console.log('Home组件挂载完毕了',this)
			window.homeRoute = this.$route
			window.homeRouter = this.$router
		},  */
	}
</script>
```

`src\pages\Message.vue`：

```vue
<template>
	<div>
		<ul>
			<li v-for="m in messageList" :key="m.id">
				<!-- 跳转路由并携带params参数，to的字符串写法 -->
				<!-- <router-link :to="`/home/message/detail/${m.id}/${m.title}`">{{m.title}}</router-link>&nbsp;&nbsp; -->

				<!-- 跳转路由并携带params参数，to的对象写法 -->
				<router-link :to="{
					name:'xiangqing',
					query:{
						id:m.id,
						title:m.title
					}
				}">
					{{m.title}}
				</router-link>
				<button @click="pushShow(m)">push查看</button>
				<button @click="replaceShow(m)">replace查看</button>
			</li>
		</ul>
		<hr>
		<router-view></router-view>
	</div>
</template>

<script>
	export default {
		name:'Message',
		data() {
			return {
				messageList:[
					{id:'001',title:'消息001'},
					{id:'002',title:'消息002'},
					{id:'003',title:'消息003'}
				]
			}
		},
		methods: {
			pushShow(m){
				this.$router.push({
					name:'xiangqing',
					query:{
						id:m.id,
						title:m.title
					}
				})
			},
			replaceShow(m){
				this.$router.replace({
					name:'xiangqing',
					query:{
						id:m.id,
						title:m.title
					}
				})
			}
		},
	}
</script>
```

`src\pages\Detail.vue`：

```vue
<template>
	<ul>
		<li>消息编号：{{id}}</li>
		<li>消息标题：{{title}}</li>
	</ul>
</template>

<script>
	export default {
		name:'Detail',
		props:['id','title'],
		computed: {
			// id(){
			// 	return this.$route.query.id
			// },
			// title(){
			// 	return this.$route.query.title
			// },
		},
		mounted() {
			// console.log(this.$route)
		},
	}
</script>
```

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			name:'guanyu',
			path:'/about',
			component:About
		},
		{
			path:'/home',
			component:Home,
			children:[
				{
					path:'news',
					component:News,
				},
				{
					path:'message',
					component:Message,
					children:[
						{
							name:'xiangqing',
							path:'detail',
							component:Detail,

							//props的第一种写法，值为对象，该对象中的所有key-value都会以props的形式传给Detail组件。
							// props:{a:1,b:'hello'}

							//props的第二种写法，值为布尔值，若布尔值为真，就会把该路由组件收到的所有params参数，以props的形式传给Detail组件。
							// props:true

							//props的第三种写法，值为函数
							props($route){
								return {
									id:$route.query.id,
									title:$route.query.title,
									a:1,
									b:'hello'
								}
							}

						}
					]
				}
			]
		}
	]
})
```

`src\App.vue`：

```vue
<template>
  <div>
    <div class="row">
      <Banner/>
    </div>
    <div class="row">
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
					<!-- 原始html中我们使用a标签实现页面的跳转 -->
          <!-- <a class="list-group-item active" href="./about.html">About</a> -->
          <!-- <a class="list-group-item" href="./home.html">Home</a> -->

					<!-- Vue中借助router-link标签实现路由的切换 -->
					<router-link class="list-group-item" active-class="active" to="/about">About</router-link>
          <router-link class="list-group-item" active-class="active" to="/home">Home</router-link>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="panel">
          <div class="panel-body">
						<!-- 指定组件的呈现位置 -->
            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	import Banner from './components/Banner'
	export default {
		name:'App',
		components:{Banner}
	}
</script>
```

### 5.11 缓存路由组件

1.缓存路由组件作用：让不展示的路由组件保持挂载，不被销毁。

2.具体编码：

```vue
<!-- 缓存多个路由组件 -->
<!-- <keep-alive :include="['News','Message']"> -->

<!-- 缓存一个路由组件 -->
<keep-alive include="News">
    <router-view></router-view>
</keep-alive>
```

一、存在的现象：点击news，在news导航下的输入框中输入信息。点击Message跳转到Message后再点击News跳转回来时，News组件已经被销毁，News下输入的内容也都会消失

![image-20240504190428398](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130010926.png)

二、如何保证点击Message跳转后，News组件下的内容不被销毁：因为News组件和Message组件都在Home组件下可以通过在Home组件中使用`keep-alive`对路由组件进行缓存

`src\pages\Home.vue`：

```vue
<template>
	<div>
		<h2>Home组件内容</h2>
		<div>
			<ul class="nav nav-tabs">
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/news">News</router-link>
				</li>
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/message">Message</router-link>
				</li>
			</ul>
			<!-- 缓存多个路由组件 -->
			<!-- <keep-alive :include="['News','Message']"> -->
				
			<!-- 缓存一个路由组件 -->
			<keep-alive include="News">
				<router-view></router-view>
			</keep-alive>
		</div>
	</div>
</template>

<script>
	export default {
		name:'Home',
		/* beforeDestroy() {
			console.log('Home组件即将被销毁了')
		}, */
		/* mounted() {
			console.log('Home组件挂载完毕了',this)
			window.homeRoute = this.$route
			window.homeRouter = this.$router
		},  */
	}
</script>
```

`src\pages\News.vue`：

```vue
<template>
	<ul>
		<li>news001 <input type="text"></li>
		<li>news002 <input type="text"></li>
		<li>news003 <input type="text"></li>
	</ul>
</template>

<script>
	export default {
		name:'News',
		beforeDestroy() {
			console.log('News组件即将被销毁了')
		},
	}
</script>
```

**补充**：在Vue Router中可能希望缓存某些路由组件，以提高应用程序的性能或者避免重新渲染。Vue Router提供了两种主要的方式来缓存路由组件：

1.**`<keep-alive>`组件**： `keep-alive` 是Vue.js内置的一个组件，它可以将其包裹的组件缓存起来，而不是每次切换路由时销毁和重新创建。可以将需要缓存的路由组件放置在 `keep-alive` 组件内部。例如：

```vue
<keep-alive>
  <router-view></router-view>
</keep-alive>
```

这样，被 `<router-view>` 渲染出来的组件在离开时不会被销毁，而是被缓存起来，当再次进入该路由时，会直接从缓存中取出

2.**路由元信息（Route Meta Fields）**： 也可以使用路由元信息来控制路由组件的缓存行为。通过在路由配置中设置 `meta` 字段，可以定义一些额外的信息，其中就包括缓存的设置。例如：

```javascript
// User组件被标记为需要缓存，当离开该路由时，该组件不会被销毁
const router = new VueRouter({
  routes: [
    {
      path: '/user',
      component: User,
      meta: { keepAlive: true }
    }
  ]
})
```

### 5.12 两个新的生命周期钩子

1.作用：路由组件所独有的两个钩子，用于捕获路由组件的激活状态

2.具体名字：

- `activated`路由组件被激活时触发
- `deactivated`路由组件失活时触发

案例效果：点击News组件后，news组件下会显示"欢迎学习vue"，字体的透明度会周期性地发生变化

![image-20240504200744768](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130010335.png)

`src\pages\News.vue`：

```vue
<template>
	<ul>
		<li :style="{opacity}">欢迎学习Vue</li>
		<li>news001 <input type="text"></li>
		<li>news002 <input type="text"></li>
		<li>news003 <input type="text"></li>
	</ul>
</template>

<script>
	export default {
		name:'News',
		data() {
			return {
				opacity:1
			}
		},
		/* beforeDestroy() {
			console.log('News组件即将被销毁了')
			clearInterval(this.timer)
		}, */
		/* mounted(){
			this.timer = setInterval(() => {
				console.log('@')
				this.opacity -= 0.01
				if(this.opacity <= 0) this.opacity = 1
			},16)
		}, */
		activated() {
			console.log('News组件被激活了')
			this.timer = setInterval(() => {
				console.log('@')
				this.opacity -= 0.01
				if(this.opacity <= 0) this.opacity = 1
			},16)
		},
		deactivated() {
			console.log('News组件失活了')
			clearInterval(this.timer)
		},
	}
</script>
```

`src\pages\Home.vue`：

```vue
<template>
	<div>
		<h2>Home组件内容</h2>
		<div>
			<ul class="nav nav-tabs">
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/news">News</router-link>
				</li>
				<li>
					<router-link class="list-group-item" active-class="active" to="/home/message">Message</router-link>
				</li>
			</ul>
			<keep-alive include="News">
				<router-view></router-view>
			</keep-alive>
		</div>
	</div>
</template>

<script>
	export default {
		name:'Home',
		/* beforeDestroy() {
			console.log('Home组件即将被销毁了')
		}, */
		/* mounted() {
			console.log('Home组件挂载完毕了',this)
			window.homeRoute = this.$route
			window.homeRouter = this.$router
		},  */
	}
</script>
```

**补充**：`activated`和`deactivated`是Vue.js组件生命周期钩子函数

1.`activated`钩子函数在组件实例被激活时调用。它只在使用`keep-alive`组件时才有意义。当一个组件被包裹在`keep-alive`组件中，组件被激活后`activated`钩子函数会被调用，这时组件在缓存中，下次再次访问这个组件时，不会再次进行created和mounted操作，而是直接从缓存中获取组件对象，`activated`则表示该组件已被重新激活

2.`deactivated`钩子函数与`activated`相反，当组件被失活时调用，同样，只有在`keep-alive`组件中使用时才有意义。当一个被缓存的组件被离开时，`deactivated`钩子函数就会被调用，并且在下一次激活该组件前，它的生命周期将被暂停，这时可以使用`keep-alive`中的其中一个特定属性判断是否激活。这个钩子函数可以在离开页面前保存一些需要保存的数据或者清除一些临时状态，以提高用户体验

### 5.13 路由守卫

1.路由守卫作用：对路由进行权限控制

2.分类：全局守卫、独享守卫、组件内守卫

3.全局守卫:

```js
//全局前置守卫：初始化时执行、每次路由切换前执行
router.beforeEach((to,from,next)=>{
console.log('beforeEach',to,from)
if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
	if(localStorage.getItem('school') === 'atguigu'){ //权限控制的具体规则
		next() //放行
	}else{
		alert('暂无权限查看')
		// next({name:'guanyu'})
	}
}else{
	next() //放行
}
})

//全局后置守卫：初始化时执行、每次路由切换后执行
router.afterEach((to,from)=>{
console.log('afterEach',to,from)
if(to.meta.title){ 
	document.title = to.meta.title //修改网页的title
}else{
	document.title = 'vue_test'
}
})
```

4.独享守卫:

```js
beforeEnter(to,from,next){
console.log('beforeEnter',to,from)
if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
	if(localStorage.getItem('school') === 'atguigu'){
		next()
	}else{
		alert('暂无权限查看')
		// next({name:'guanyu'})
	}
}else{
	next()
}
}
```

5.组件内守卫：

```js
//进入守卫：通过路由规则，进入该组件时被调用
beforeRouteEnter (to, from, next) {
},
//离开守卫：通过路由规则，离开该组件时被调用
beforeRouteLeave (to, from, next) {
}
```

##### 5.13.1 全局路由守卫

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建并暴露一个路由器
const router =  new VueRouter({
	routes:[
		{
			name:'guanyu',
			path:'/about',
			component:About,
			meta:{title:'关于'}
		},
		{
			name:'zhuye',
			path:'/home',
			component:Home,
			meta:{title:'主页'},
			children:[
				{
					name:'xinwen',
					path:'news',
					component:News,
					meta:{isAuth:true,title:'新闻'}
				},
				{
					name:'xiaoxi',
					path:'message',
					component:Message,
					meta:{isAuth:true,title:'消息'},
					children:[
						{
							name:'xiangqing',
							path:'detail',
							component:Detail,
							meta:{isAuth:true,title:'详情'},

							//props的第一种写法，值为对象，该对象中的所有key-value都会以props的形式传给Detail组件。
							// props:{a:1,b:'hello'}

							//props的第二种写法，值为布尔值，若布尔值为真，就会把该路由组件收到的所有params参数，以props的形式传给Detail组件。
							// props:true

							//props的第三种写法，值为函数
							props($route){
								return {
									id:$route.query.id,
									title:$route.query.title,
									a:1,
									b:'hello'
								}
							}

						}
					]
				}
			]
		}
	]
})

//全局前置路由守卫————初始化的时候被调用、每次路由切换之前被调用
router.beforeEach((to,from,next)=>{
	console.log('前置路由守卫',to,from)
	if(to.meta.isAuth){ //判断是否需要鉴权
		if(localStorage.getItem('school')==='atguigu'){
			next()
		}else{
			alert('学校名不对，无权限查看！')
		}
	}else{
		next()
	}
})

//全局后置路由守卫————初始化的时候被调用、每次路由切换之后被调用
router.afterEach((to,from)=>{
	console.log('后置路由守卫',to,from)
	document.title = to.meta.title || '硅谷系统'
})

export default router
```

##### 5.13.2 独享守卫

`src\router\index.js`：

```js
// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建并暴露一个路由器
const router =  new VueRouter({
	routes:[
		{
			name:'guanyu',
			path:'/about',
			component:About,
			meta:{title:'关于'}
		},
		{
			name:'zhuye',
			path:'/home',
			component:Home,
			meta:{title:'主页'},
			children:[
				{
					name:'xinwen',
					path:'news',
					component:News,
					meta:{isAuth:true,title:'新闻'},
					beforeEnter: (to, from, next) => {
						console.log('独享路由守卫',to,from)
						if(to.meta.isAuth){ //判断是否需要鉴权
							if(localStorage.getItem('school')==='atguigu'){
								next()
							}else{
								alert('学校名不对，无权限查看！')
							}
						}else{
							next()
						}
					}
				},
				{
					name:'xiaoxi',
					path:'message',
					component:Message,
					meta:{isAuth:true,title:'消息'},
					children:[
						{
							name:'xiangqing',
							path:'detail',
							component:Detail,
							meta:{isAuth:true,title:'详情'},

							//props的第一种写法，值为对象，该对象中的所有key-value都会以props的形式传给Detail组件。
							// props:{a:1,b:'hello'}

							//props的第二种写法，值为布尔值，若布尔值为真，就会把该路由组件收到的所有params参数，以props的形式传给Detail组件。
							// props:true

							//props的第三种写法，值为函数
							props($route){
								return {
									id:$route.query.id,
									title:$route.query.title,
									a:1,
									b:'hello'
								}
							}

						}
					]
				}
			]
		}
	]
})

//全局前置路由守卫————初始化的时候被调用、每次路由切换之前被调用
/* router.beforeEach((to,from,next)=>{
	console.log('前置路由守卫',to,from)
	if(to.meta.isAuth){ //判断是否需要鉴权
		if(localStorage.getItem('school')==='atguigu'){
			next()
		}else{
			alert('学校名不对，无权限查看！')
		}
	}else{
		next()
	}
}) */

//全局后置路由守卫————初始化的时候被调用、每次路由切换之后被调用
router.afterEach((to,from)=>{
	console.log('后置路由守卫',to,from)
	document.title = to.meta.title || '硅谷系统'
})

export default router
```

##### 5.13.3 组件内路由守卫

`src\pages\About.vue`：

```vue
<template>
	<h2>我是About的内容</h2>
</template>

<script>
	export default {
		name:'About',
		/* beforeDestroy() {
			console.log('About组件即将被销毁了')
		},*/
		/* mounted() {
			console.log('About组件挂载完毕了',this)
			window.aboutRoute = this.$route
			window.aboutRouter = this.$router
		},  */
		mounted() {
			// console.log('%%%',this.$route)
		},

		//通过路由规则，进入该组件时被调用
		beforeRouteEnter (to, from, next) {
			console.log('About--beforeRouteEnter',to,from)
			if(to.meta.isAuth){ //判断是否需要鉴权
				if(localStorage.getItem('school')==='atguigu'){
					next()
				}else{
					alert('学校名不对，无权限查看！')
				}
			}else{
				next()
			}
		},

		//通过路由规则，离开该组件时被调用
		beforeRouteLeave (to, from, next) {
			console.log('About--beforeRouteLeave',to,from)
			next()
		}
	}
</script>
```

##### 5.13.4 总结

Vue Router 中的路由守卫是一组功能强大的功能，路由守卫可以在路由导航过程中对导航进行控制。路由守卫包括全局前置守卫、全局后置守卫、路由独享的守卫以及组件内的守卫。路由守卫提供了灵活的方式来控制路由导航，可以实现很多功能，例如身份验证、权限控制、日志记录等

**全局前置守卫（Global Before Guards）**：通过 `router.beforeEach` 注册全局前置守卫，这些守卫在路由导航之前被调用，可以用于执行诸如身份验证、权限验证等操作。如果守卫中调用了 `next(false)`，则导航会被取消

```javascript
router.beforeEach((to, from, next) => {
  // Check authentication, permission, etc.
  if (!authenticated) {
    next('/login'); // Redirect to login page
  } else {
    next(); // Proceed with the navigation
  }
}); 
```

**全局后置守卫（Global After Guards）**：通过 `router.afterEach` 注册全局后置守卫，这些守卫在路由导航之后被调用，可以用于执行一些在路由导航完成后需要进行的操作

```javascript
router.afterEach((to, from) => {
  // Log navigation, analytics, etc.
});
```

**路由独享的守卫（Per-Route Guards）**：在定义路由时，可以通过 `beforeEnter` 字段添加路由独享的守卫。这些守卫与全局前置守卫类似，但仅应用于特定的路由

```javascript
const router = new VueRouter({
  routes: [
    {
      path: '/admin',
      component: Admin,
      beforeEnter: (to, from, next) => {
        // Check if user is admin
        if (!isAdmin) {
          next('/'); // Redirect to home page
        } else {
          next(); // Proceed with the navigation
        }
      }
    }
  ]
});
```

**组件内的守卫（In-Component Guards）**：Vue 组件可以包含 `beforeRouteEnter`、`beforeRouteUpdate` 和 `beforeRouteLeave` 这三个导航守卫方法，用于控制组件的路由导航

```javascript
export default {
  beforeRouteEnter(to, from, next) {
    // Access component instance via `vm`
    next(vm => {
      // Access to component instance via `vm`
    });
  },
  beforeRouteUpdate(to, from, next) {
    // React to route changes in the same component instance
  },
  beforeRouteLeave(to, from, next) {
    // React to leaving route from this component instance
  }
};
```

### 5.14 hash模式 | history模式

```
1.路由器的两种工作模式: hash模式 和 history模式
2.对于一个url来说，什么是hash值？—— #及其后面的内容就是hash值
3.hash值不会包含在 HTTP 请求中，即：hash值不会带给服务器
4.hash模式：
       1.地址中永远带着#号，不美观 
       2.若以后将地址通过第三方手机app分享，若app校验严格，则地址会被标记为不合法
       3.兼容性较好
5.history模式：
       1.地址干净，美观 
       2.兼容性和hash模式相比略差
       3.应用部署上线时需要后端人员支持，解决刷新页面服务端404的问题
```

1.在 Vue Router 中，有两种常见的路由模式：hash 模式和 history 模式

2.Hash 模式：在 hash 模式中，URL 中的路由路径会以 # 符号开头，例如 `http://example.com/#/home`。这种模式的优点在于它的兼容性较强，因为 hash 路由不会导致浏览器向服务器发送请求，而只是修改 URL 的片段，因此不会刷新页面。在旧版浏览器或不支持 HTML5 History API 的环境中，hash 模式是一种常见的选择

3.History 模式：在 history 模式中，URL 中的路由路径看起来更加自然，没有 # 符号，例如 `http://example.com/home`。这种模式依赖于 HTML5 History API，可以使用 history.pushState() 和 history.replaceState() 方法来操作浏览器历史记录栈，并且不会向服务器发送请求。相较于 hash 模式，history 模式的 URL 更美观，更有利于 SEO，但需要服务器端的支持。在使用 history 模式时，服务器需要配置一个覆盖所有情况的候选资源，以便在 URL 匹配失败时返回同一个 HTML 页面

4.在 Vue Router 中通过配置来选择使用哪种模式，例如：

```javascript
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history', // 可以选择 'hash' 或 'history'
  routes: [
    // 路由配置
  ]
})
```

## 6.Vue UI组件库

```
1.移动端常用UI组件库
        1.Vant:       https://youzan.github.io/vant
        2.Cube UI:    https://didi.github.io/cube-ui
        3.Mint UI:    http://mint-ui.github.io
2.PC端常用UI组件库
        1.Element UI: https://element.eleme.cn
        2.IView UI:   https://www.iviewui.com
```

### 6.1 基本使用

```
# 安装：
npm i element-ui
```

`src\main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'

//完整引入
//引入ElementUI组件库
// import ElementUI from 'element-ui';
//引入ElementUI全部样式
// import 'element-ui/lib/theme-chalk/index.css';

//按需引入
import { Button,Row,DatePicker } from 'element-ui';

//关闭Vue的生产提示
Vue.config.productionTip = false

//应用ElementUI
// Vue.use(ElementUI);
Vue.component('atguigu-button', Button);
Vue.component('atguigu-row', Row);
Vue.component('atguigu-date-picker', DatePicker);

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
})
```

`src\App.vue`：

```vue
<template>
  <div>
		<button>原生的按钮</button>
		<input type="text">
		<atguigu-row>
			<el-button>默认按钮</el-button>
			<el-button type="primary">主要按钮</el-button>
			<el-button type="success">成功按钮</el-button>
			<el-button type="info">信息按钮</el-button>
			<el-button type="warning">警告按钮</el-button>
			<el-button type="danger">危险按钮</el-button>
		</atguigu-row>
		<atguigu-date-picker
      type="date"
      placeholder="选择日期">
    </atguigu-date-picker>
		<atguigu-row>
			<el-button icon="el-icon-search" circle></el-button>
			<el-button type="primary" icon="el-icon-s-check" circle></el-button>
			<el-button type="success" icon="el-icon-check" circle></el-button>
			<el-button type="info" icon="el-icon-message" circle></el-button>
			<el-button type="warning" icon="el-icon-star-off" circle></el-button>
			<el-button type="danger" icon="el-icon-delete" circle></el-button>
		</atguigu-row>
  </div>
</template>

<script>
	export default {
		name:'App',
	}
</script>
```

### 6.2 按需引入

1.安装 `babel-plugin-component`：

```markdown
# 借助babel-plugin-component可以只引入需要的组件，以达到减小项目体积的目的
# 安装 babel-plugin-component：
npm install babel-plugin-component -D
```

2.修改`babel.config.js`：

```js
module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset',
		["@babel/preset-env", { "modules": false }],
  ],
	plugins:[
    [
      "component",
      {
        "libraryName": "element-ui",
        "styleLibraryName": "theme-chalk"
      }
    ]
  ]
}
```

`src\main.js`：

```js
//引入Vue
import Vue from 'vue'
//引入App
import App from './App.vue'

//完整引入
//引入ElementUI组件库
// import ElementUI from 'element-ui';
//引入ElementUI全部样式
// import 'element-ui/lib/theme-chalk/index.css';

//按需引入
import { Button,Row,DatePicker } from 'element-ui';

//关闭Vue的生产提示
Vue.config.productionTip = false

//应用ElementUI
// Vue.use(ElementUI);
Vue.component('atguigu-button', Button);
Vue.component('atguigu-row', Row);
Vue.component('atguigu-date-picker', DatePicker);

//创建vm
new Vue({
	el:'#app',
	render: h => h(App),
})
```

`src\App.vue`：

```vue
<template>
  <div>
		<button>原生的按钮</button>
		<input type="text">
		<atguigu-row>
			<el-button>默认按钮</el-button>
			<el-button type="primary">主要按钮</el-button>
			<el-button type="success">成功按钮</el-button>
			<el-button type="info">信息按钮</el-button>
			<el-button type="warning">警告按钮</el-button>
			<el-button type="danger">危险按钮</el-button>
		</atguigu-row>
		<atguigu-date-picker
      type="date"
      placeholder="选择日期">
    </atguigu-date-picker>
		<atguigu-row>
			<el-button icon="el-icon-search" circle></el-button>
			<el-button type="primary" icon="el-icon-s-check" circle></el-button>
			<el-button type="success" icon="el-icon-check" circle></el-button>
			<el-button type="info" icon="el-icon-message" circle></el-button>
			<el-button type="warning" icon="el-icon-star-off" circle></el-button>
			<el-button type="danger" icon="el-icon-delete" circle></el-button>
		</atguigu-row>
  </div>
</template>

<script>
	export default {
		name:'App',
	}
</script>
```

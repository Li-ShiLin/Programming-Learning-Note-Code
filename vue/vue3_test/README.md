<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.Vue3简介](#1vue3%E7%AE%80%E4%BB%8B)
- [2.创建Vue3.0工程](#2%E5%88%9B%E5%BB%BAvue30%E5%B7%A5%E7%A8%8B)
  - [2.1 使用 vue-cli 创建](#21-%E4%BD%BF%E7%94%A8-vue-cli-%E5%88%9B%E5%BB%BA)
  - [2.2 使用 vite 创建](#22-%E4%BD%BF%E7%94%A8-vite-%E5%88%9B%E5%BB%BA)
  - [2.3 分析工程结构(vue-cli)](#23-%E5%88%86%E6%9E%90%E5%B7%A5%E7%A8%8B%E7%BB%93%E6%9E%84vue-cli)
  - [2.4 安装vue3开发者工具](#24-%E5%AE%89%E8%A3%85vue3%E5%BC%80%E5%8F%91%E8%80%85%E5%B7%A5%E5%85%B7)
- [3.常用Composition API(组合式api)](#3%E5%B8%B8%E7%94%A8composition-api%E7%BB%84%E5%90%88%E5%BC%8Fapi)
  - [3.1 setup函数](#31-setup%E5%87%BD%E6%95%B0)
  - [3.2 ref函数](#32-ref%E5%87%BD%E6%95%B0)
  - [3.3 reactive函数](#33-reactive%E5%87%BD%E6%95%B0)
  - [3.4 Vue3.0中的响应式原理](#34-vue30%E4%B8%AD%E7%9A%84%E5%93%8D%E5%BA%94%E5%BC%8F%E5%8E%9F%E7%90%86)
      - [3.4.1 vue2.x的响应式](#341-vue2x%E7%9A%84%E5%93%8D%E5%BA%94%E5%BC%8F)
      - [3.4.2 Vue3.0的响应式](#342-vue30%E7%9A%84%E5%93%8D%E5%BA%94%E5%BC%8F)
  - [3.5 reactive与ref对比](#35-reactive%E4%B8%8Eref%E5%AF%B9%E6%AF%94)
  - [3.6 setup的两个注意点](#36-setup%E7%9A%84%E4%B8%A4%E4%B8%AA%E6%B3%A8%E6%84%8F%E7%82%B9)
  - [3.7 计算属性与监视](#37-%E8%AE%A1%E7%AE%97%E5%B1%9E%E6%80%A7%E4%B8%8E%E7%9B%91%E8%A7%86)
      - [3.7.1 computed函数](#371-computed%E5%87%BD%E6%95%B0)
      - [3.7.2  watch函数](#372--watch%E5%87%BD%E6%95%B0)
      - [3.7.3  watch监视ref数据](#373--watch%E7%9B%91%E8%A7%86ref%E6%95%B0%E6%8D%AE)
      - [3.7.4 watchEffect函数](#374-watcheffect%E5%87%BD%E6%95%B0)
  - [3.8 生命周期](#38-%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)
  - [3.9 自定义hook函数](#39-%E8%87%AA%E5%AE%9A%E4%B9%89hook%E5%87%BD%E6%95%B0)
  - [3.10 toRef](#310-toref)
- [4.其它 Composition API](#4%E5%85%B6%E5%AE%83-composition-api)
  - [4.1 shallowReactive 与 shallowRef](#41-shallowreactive-%E4%B8%8E-shallowref)
  - [4.2 readonly 与 shallowReadonly](#42-readonly-%E4%B8%8E-shallowreadonly)
  - [4.3 toRaw 与 markRaw](#43-toraw-%E4%B8%8E-markraw)
  - [4.4 customRef](#44-customref)
  - [4.5 provide 与 inject](#45-provide-%E4%B8%8E-inject)
  - [4.6 响应式数据的判断](#46-%E5%93%8D%E5%BA%94%E5%BC%8F%E6%95%B0%E6%8D%AE%E7%9A%84%E5%88%A4%E6%96%AD)
- [5.Composition API 的优势](#5composition-api-%E7%9A%84%E4%BC%98%E5%8A%BF)
  - [5.1  Options API 存在的问题](#51--options-api-%E5%AD%98%E5%9C%A8%E7%9A%84%E9%97%AE%E9%A2%98)
  - [5.2 Composition API 的优势](#52-composition-api-%E7%9A%84%E4%BC%98%E5%8A%BF)
  - [5.3 Options API 和 Composition API对比](#53-options-api-%E5%92%8C-composition-api%E5%AF%B9%E6%AF%94)
- [6.新的组件](#6%E6%96%B0%E7%9A%84%E7%BB%84%E4%BB%B6)
  - [6.1 Fragment](#61-fragment)
  - [6.2 Teleport](#62-teleport)
  - [6.3  Suspense](#63--suspense)
- [7. 其他](#7-%E5%85%B6%E4%BB%96)
  - [7.1 全局API的转移](#71-%E5%85%A8%E5%B1%80api%E7%9A%84%E8%BD%AC%E7%A7%BB)
  - [7.2 其他改变](#72-%E5%85%B6%E4%BB%96%E6%94%B9%E5%8F%98)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.Vue3简介

1.2020年9月18日，Vue.js发布3.0版本，代号：One Piece（海贼王）

2.耗时2年多、[2600+次提交](https://github.com/vuejs/vue-next/graphs/commit-activity)、[30+个RFC](https://github.com/vuejs/rfcs/tree/master/active-rfcs)、[600+次PR](https://github.com/vuejs/vue-next/pulls?q=is%3Apr+is%3Amerged+-author%3Aapp%2Fdependabot-preview+)、[99位贡献者](https://github.com/vuejs/vue-next/graphs/contributors) 

3.github上的tags地址：https://github.com/vuejs/vue-next/releases/tag/v3.0.0

4.性能的提升：打包大小减少41%、初次渲染快55%, 更新渲染快133%、内存减少54%

5.源码的升级：使用Proxy代替defineProperty实现响应式、重写虚拟DOM的实现和Tree-Shaking

6.拥抱TypeScript：Vue3可以更好的支持TypeScript

7.新的特性：

```
1.Composition API（组合API）: setup配置、ref与reactive、watch与watchEffect、provide与inject
2.新的内置组件: Fragment、Teleport、Suspense
3.其他改变: 新的生命周期钩子、data 选项应始终被声明为一个函数、移除keyCode支持作为 v-on 的修饰符
```

## 2.创建Vue3.0工程

### 2.1 使用 vue-cli 创建

官方文档：https://cli.vuejs.org/zh/guide/creating-a-project.html#vue-create

```bash
## 查看@vue/cli版本，确保@vue/cli版本在4.5.0以上
## 命令vue --version
C:\Users\username> vue --version
@vue/cli 5.0.8

## 安装或者升级@vue/cli
npm install -g @vue/cli
## 创建
vue create vue_test
## 启动
cd vue_test
npm run serve
```

### 2.2 使用 vite 创建

1.vite：vite是新一代前端构建工具

2.Vite最显著的特点之一是它基于 ES Module（ESM）原生模块系统的开发模式。这种模式允许开发者在开发过程中直接使用现代浏览器的原生支持，无需经过传统的打包步骤，这一特性使得开发过程更加迅速。另外，Vite 采用了即时编译（Instant Compilation）的技术，它能够在开发时实时编译代码，当文件发生变化时，只重新编译需要更新的部分，而不是整个项目，这大大提高了开发的速度。Vite 还支持热模块替换（Hot Module Replacement），这意味着当你修改代码时，不需要手动刷新浏览器页面，而是可以实时地在浏览器中看到修改的效果，这极大地提高了开发效率

3.官方文档：`https://v3.cn.vuejs.org/guide/installation.html#vite`

4.vite官网：`https://vitejs.cn`

5.vite优势如下：

- 开发环境中，无需打包操作，可快速的冷启动
- 轻量快速的热重载（HMR）
- 真正的按需编译，不再等待整个应用编译完成

6.传统构建 与 vite构建对比图

![image-20240505222742035](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130015646.png)

7.vite的构建步骤

```bash
## 创建工程
npm init vite-app <project-name>
## 进入工程目录
cd <project-name>
## 安装依赖
npm install
## 运行
npm run dev
```

### 2.3 分析工程结构(vue-cli)

分析基于vue-cli构建的vue3项目工程结构

`src\main.js`：

```js
//引入的不再是Vue构造函数了，引入的是一个名为createApp的工厂函数
import { createApp } from 'vue'
import App from './App.vue'

//创建应用实例对象——app(类似于之前Vue2中的vm，但app比vm更“轻”)
const app = createApp(App)

//挂载
app.mount('#app')
```

`src\App.vue`：

```vue
<template>
	<!-- Vue3组件中的模板结构可以没有根标签 -->
	<img alt="Vue logo" src="./assets/logo.png">
	<HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
	import HelloWorld from './components/HelloWorld.vue'

	export default {
		name: 'App',
		components: {
			HelloWorld
		}
	}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
```

### 2.4 安装vue3开发者工具

安装vue3版本的开发者工具，并添加到浏览器扩展程序

![image-20240505230353951](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130015232.png)



## 3.常用Composition API(组合式api)

官方文档: `https://v3.cn.vuejs.org/guide/composition-api-introduction.html`

### 3.1 setup函数

```
1.理解：setup是Vue3.0中一个新的配置项，值为一个函数。

2.setup是所有Composition API（组合API）“表演的舞台”

3.组件中所用到的：数据、方法等等，均要配置在setup中

4.setup函数的两种返回值：
       1.若返回一个对象，则对象中的属性、方法, 在模板中均可以直接使用。（重点关注！）
       2.若返回一个渲染函数：则可以自定义渲染内容。（了解）

5.注意点：
      1.尽量不要与Vue2.x配置混用
              Vue2.x配置（data、methos、computed...）中可以访问到setup中的属性、方法
              但在setup中不能访问到Vue2.x配置（data、methos、computed...）
              如果有重名, setup优先
       2.setup不能是一个async函数，因为返回值不再是return的对象, 而是promise, 模板看不到return对象中的属性
       （后期也可以返回一个Promise实例，但需要Suspense和异步组件的配合）
```

`src\main.js`：

```js
//引入的不再是Vue构造函数了，引入的是一个名为createApp的工厂函数
import { createApp } from 'vue'
import App from './App.vue'

//创建应用实例对象——app(类似于之前Vue2中的vm，但app比vm更“轻”)
const app = createApp(App)

//挂载
app.mount('#app')
```

`src\App.vue`：

```vue
<template>
	<h1>一个人的信息</h1>
	<!-- 若返回一个对象，则对象中的属性、方法, 在模板中均可以直接使用。（重点关注！） -->
	<h2>姓名：{{ name }}</h2>
	<h2>年龄：{{ age }}</h2>
	<h2>性别：{{ sex }}</h2>
	<h2>a的值是：{{ a }}</h2>
	<button @click="sayHello">说话(Vue3所配置的——sayHello)</button>
	<br>
	<br>
	<button @click="sayWelcome">说话(Vue2所配置的——sayWelcome)</button>
	<br>
	<br>
	<button @click="test1">测试一下在Vue2的配置中去读取Vue3中的数据、方法</button>
	<br>
	<br>
	<button @click="test2">测试一下在Vue3的setup配置中去读取Vue2中的数据、方法</button>

</template>

<script>
// import {h} from 'vue'
export default {
	name: 'App',
	// Vue2.x配置（data、methos、computed...）中可以访问到setup中的属性、方法
	// 但在setup中不能访问到Vue2.x配置（data、methos、computed...）
	// 如果有重名, setup优先
	// 尽量不要与Vue2.x配置混用
	data() {
		return {
			sex: '男',
			a: 100
		}
	},
	methods: {
		sayWelcome() {
			alert('欢迎来到尚硅谷学习')
		},
		test1() {
			console.log(this.sex)
			console.log(this.name)
			console.log(this.age)
			console.log(this.sayHello)
		}
	},
	// 组件中所用到的：数据、方法等等，均要配置在setup中
	//此处只是测试一下setup，暂时不考虑响应式的问题。
	async setup() {
		//数据
		let name = '张三'
		let age = 18
		let a = 200

		//方法
		function sayHello() {
			alert(`我叫${name}，我${age}岁了，你好啊！`)
		}
		function test2() {
			console.log(name)
			console.log(age)
			console.log(sayHello)
			console.log(this.sex)
			console.log(this.sayWelcome)
		}

		//返回一个对象（常用）
		return {
			name,
			age,
			sayHello,
			test2,
			a
		}
		// 若返回一个渲染函数：则可以自定义渲染内容。（了解）
		//返回一个函数（渲染函数）
		// return ()=> h('h1','尚硅谷')
	}
}
</script>
```

**补充**：在Vue 3中，`setup()` 函数是一个新的选项，用于在组件内设置响应式状态、引入和初始化组件逻辑，以及处理生命周期钩子。`setup()` 函数的引入使得组件的逻辑更加清晰和组织化，同时也增加了对组件内部行为的可预测性和可测试性。在 `setup()` 函数中可以执行以下操作：

- **引入响应式状态（reactive state）和响应式引用（ref）**：你可以使用`ref()`和`reactive()`函数创建响应式状态和引用。`ref()`用于创建单个可变的值的引用，而`reactive()`用于创建一个包含多个属性的响应式对象
- **引入生命周期钩子**：虽然生命周期钩子不再是`setup()`函数的一部分，但你仍然可以在`setup()`函数中引入它们，并在需要时调用相应的钩子函数，例如`onMounted()`用于在组件挂载后执行逻辑
- **引入计算属性和监听器**：你可以在`setup()`函数中使用`computed()`和`watch()`函数来创建计算属性和监听器，以便在数据变化时执行相应的逻辑
- **处理props**：在`setup()`函数中，你可以直接访问组件的props，也可以通过参数的方式接收props
- **返回数据和方法**：`setup()`函数应该返回一个包含响应式状态和方法的对象，这些数据和方法将会暴露给组件的模板
- `setup()`函数使用演示：setup()函数接收组件的props作为参数，并返回一个包含了响应式状态、计算属性和方法的对象。这些数据和方法可以在组件的模板中直接使用

```javascript
import { ref, reactive, onMounted } from 'vue';

export default {
  props: ['initialCount'],
  setup(props) {
    // 创建响应式状态和引用
    const count = ref(props.initialCount);
    const user = reactive({ name: 'John', age: 30 });

    // 创建计算属性
    const doubledCount = computed(() => count.value * 2);

    // 生命周期钩子
    onMounted(() => {
      console.log('Component mounted');
    });

    // 返回数据和方法
    return {
      count,
      user,
      doubledCount,
      increment() {
        count.value++;
      }
    };
  }
};
```

### 3.2 ref函数

```
ref函数作用: 定义一个响应式的数据
语法:   const xxx = ref(initValue)
       创建一个包含响应式数据的引用对象（reference对象，简称ref对象）
       JS中操作数据： xxx.value
       模板中读取数据: 不需要.value，直接：<div>{{xxx}}</div>
备注：
       接收的数据可以是：  基本类型、也可以是对象类型
       基本类型的数据：   响应式依然是靠Object.defineProperty()的get与set完成的
       对象类型的数据：   内部“ 求助 ”了Vue3.0中的一个新函数reactive函数
```

`src\App.vue`：

```vue
<template>
	<h1>一个人的信息</h1>
	<h2>姓名：{{name}}</h2>
	<h2>年龄：{{age}}</h2>
	<h3>工作种类：{{job.type}}</h3>
	<h3>工作薪水：{{job.salary}}</h3>
	<button @click="changeInfo">修改人的信息</button>
</template>

<script>
	import {ref} from 'vue'
	export default {
		name: 'App',
		setup(){
			//数据
			let name = ref('张三')
			let age = ref(18)
			let job = ref({
				type:'前端工程师',
				salary:'30K'
			})

			//方法
			function changeInfo(){
				// name.value = '李四'
				// age.value = 48
				console.log(job.value)
				// job.value.type = 'UI设计师'
				// job.value.salary = '60K'
				// console.log(name,age)
			}

			//返回一个对象（常用）
			return {
				name,
				age,
				job,
				changeInfo
			}
		}
	}
</script>
```

`src\main.js`：

```js
//引入的不再是Vue构造函数了，引入的是一个名为createApp的工厂函数
import { createApp } from 'vue'
import App from './App.vue'

//创建应用实例对象——app(类似于之前Vue2中的vm，但app比vm更“轻”)
const app = createApp(App)

//挂载
app.mount('#app')
```

### 3.3 reactive函数

- 作用: 定义一个<strong style="color:#DD5145">对象类型</strong>的响应式数据（基本类型不要用它，要用```ref```函数）
- 语法：```const 代理对象= reactive(源对象)```接收一个对象（或数组），返回一个<strong style="color:#DD5145">代理对象（Proxy的实例对象，简称proxy对象）</strong>
- reactive定义的响应式数据是“深层次的”
- 内部基于 ES6 的 Proxy 实现，通过代理对象操作源对象内部数据进行操作

`src\App.vue`：

```vue
<template>
	<h1>一个人的信息</h1>
	<h2>姓名：{{person.name}}</h2>
	<h2>年龄：{{person.age}}</h2>
	<h3>工作种类：{{person.job.type}}</h3>
	<h3>工作薪水：{{person.job.salary}}</h3>
	<h3>爱好：{{person.hobby}}</h3>
	<h3>测试的数据c：{{person.job.a.b.c}}</h3>
	<button @click="changeInfo">修改人的信息</button>
</template>

<script>
	import {reactive} from 'vue'
	export default {
		name: 'App',
		setup(){
			//数据
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					type:'前端工程师',
					salary:'30K',
					// reactive定义的响应式数据是“深层次的”
					a:{
						b:{
							c:666
						}
					}
				},
				hobby:['抽烟','喝酒','烫头']
			})

			//方法
			function changeInfo(){
				person.name = '李四'
				person.age = 48
				person.job.type = 'UI设计师'
				person.job.salary = '60K'
				person.job.a.b.c = 999
				person.hobby[0] = '学习'
			}

			//返回一个对象（常用）
			return {
				person,
				changeInfo
			}
		}
	}
</script>
```

`src\main.js`：

```json
//引入的不再是Vue构造函数了，引入的是一个名为createApp的工厂函数
import { createApp } from 'vue'
import App from './App.vue'

//创建应用实例对象——app(类似于之前Vue2中的vm，但app比vm更“轻”)
const app = createApp(App)

//挂载
app.mount('#app')
```

在 Vue 3 中，`reactive` 是一个非常核心的函数，用于创建响应式对象。它是 Vue 3 的 Composition API 的一部分，这个新的 API 可以更灵活地组合和重用逻辑

**基本使用**：`reactive` 接受一个普通对象并返回该普通对象的响应式代理。这个响应式代理可以被用来在 Vue 应用中驱动视图的自动更新

```javascript
import { reactive } from 'vue';

const state = reactive({
  count: 0
});

// 当 state.count 改变时，依赖于 state.count 的任何视图都会自动更新。
state.count++;
```

**底层实现**：Vue 3 使用了 ES6 的 Proxy 特性来实现响应式系统。这比 Vue 2 中使用的 Object.defineProperty 方法有了很大的改进，因为 Proxy 可以拦截对象的所有操作，包括属性的添加、修改、删除等，而不仅仅是已存在的属性

**`reactive` 与 `ref` 的比较**：Vue 3 中还有一个创建响应式数据的函数是 `ref`。它们之间的主要区别在于：

- `ref` 用于包装一个基本类型值或对象，使其变为响应式。它返回一个包含 `value` 属性的对象。
- `reactive` 直接接受一个对象并返回该对象的响应式代理。不能用于基本类型（如直接用于字符串或数字）

```javascript
import { ref, reactive } from 'vue';

// 使用 ref
const count = ref(0);

// 使用 reactive
const state = reactive({
  count: 0
});

// ref 必须通过 .value 属性访问或修改：
count.value++;

// reactive 对象可以直接访问或修改其属性：
state.count++;
```

**嵌套对象**：当使用 `reactive` 对一个对象进行响应式转换时，其内部的所有嵌套对象也会自动变成响应式的。但是使用 `ref` 创建的响应式对象，如果改变了其内部对象的属性，可能需要手动保证这些内部对象也是响应式的，或者使用 `reactive` 来包装整个对象

**`reactive` 使用场景**：尽管 `ref` 和 `reactive` 都可以创建响应式数据，`reactive` 更适合用来处理对象形式的状态，而 `ref` 更适用于基本数据类型和单一值。在实际开发中，根据具体需求选择使用 `ref` 或 `reactive`。总结来说，`reactive` 是 Vue 3 中处理对象响应式的强大工具，它让状态管理变得更加灵活和高效。在复杂的应用结构中，正确利用 `reactive` 可以极大地提升应用性能和开发体验

### 3.4 Vue3.0中的响应式原理

#####  3.4.1 vue2.x的响应式

- 实现原理：
  - 对象类型：通过```Object.defineProperty()```对属性的读取、修改进行拦截（数据劫持）。
  
  - 数组类型：通过重写更新数组的一系列方法来实现拦截。（对数组的变更方法进行了包裹）。
  
    ```js
    Object.defineProperty(data, 'count', {
        get () {}, 
        set () {}
    })
    ```

- vue2.x的响应式存在问题：
  - 新增属性、删除属性, 界面不会更新
  - 直接通过下标修改数组, 界面不会自动更新

##### 3.4.2 Vue3.0的响应式

- 实现原理: 
  - 通过Proxy（代理）:  拦截对象中任意属性的变化, 包括：属性值的读写、属性的添加、属性的删除等
  - 通过Reflect（反射）:  对源对象的属性进行操作
  - MDN文档中描述的Proxy与Reflect：
    - Proxy：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Proxy
    - Reflect：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Reflect

```js
new Proxy(data, {
	// 拦截读取属性值
    get (target, prop) {
    	return Reflect.get(target, prop)
    },
    // 拦截设置属性值或添加新属性
    set (target, prop, value) {
    	return Reflect.set(target, prop, value)
    },
    // 拦截删除属性
    deleteProperty (target, prop) {
    	return Reflect.deleteProperty(target, prop)
    }
})

proxy.name = 'tom'   
```

`05_Vue3的响应式原理\Vue3的响应式原理.html`：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Document</title>
	</head>
	<body>
		<script type="text/javascript" >
			//源数据
			let person = {
				name:'张三',
				age:18
			}

			//模拟Vue2中实现响应式
			//#region 
			/* let p = {}
			Object.defineProperty(p,'name',{
				configurable:true,
				get(){ //有人读取name时调用
					return person.name
				},
				set(value){ //有人修改name时调用
					console.log('有人修改了name属性，我发现了，我要去更新界面！')
					person.name = value
				}
			})
			Object.defineProperty(p,'age',{
				get(){ //有人读取age时调用
					return person.age
				},
				set(value){ //有人修改age时调用
					console.log('有人修改了age属性，我发现了，我要去更新界面！')
					person.age = value
				}
			}) */
			//#endregion
			
			//模拟Vue3中实现响应式
			//#region 
			const p = new Proxy(person,{
				//有人读取p的某个属性时调用
				get(target,propName){
					console.log(`有人读取了p身上的${propName}属性`)
					return Reflect.get(target,propName)
				},
				//有人修改p的某个属性、或给p追加某个属性时调用
				set(target,propName,value){
					console.log(`有人修改了p身上的${propName}属性，我要去更新界面了！`)
					Reflect.set(target,propName,value)
				},
				//有人删除p的某个属性时调用
				deleteProperty(target,propName){
					console.log(`有人删除了p身上的${propName}属性，我要去更新界面了！`)
					return Reflect.deleteProperty(target,propName)
				}
			})
			//#endregion

			let obj = {a:1,b:2}
			//通过Object.defineProperty去操作
			//#region 
			/* try {
				Object.defineProperty(obj,'c',{
					get(){
						return 3
					}
				})
				Object.defineProperty(obj,'c',{
					get(){
						return 4
					}
				})
			} catch (error) {
				console.log(error)
			} */
			//#endregion

			//通过Reflect.defineProperty去操作
			//#region 
			/* const x1 = Reflect.defineProperty(obj,'c',{
				get(){
					return 3
				}
			})
			console.log(x1)
			
			const x2 = Reflect.defineProperty(obj,'c',{
				get(){
					return 4
				}
			}) 
			if(x2){
				console.log('某某某操作成功了！')
			}else{
				console.log('某某某操作失败了！')
			} */
			//#endregion

			// console.log('@@@')

		</script>
	</body>
</html>
```

### 3.5 reactive与ref对比

-  从定义数据角度对比：
   -  ref用来定义：<strong style="color:#DD5145">基本类型数据</strong>
   -  reactive用来定义：<strong style="color:#DD5145">对象（或数组）类型数据</strong>
   -  备注：ref也可以用来定义<strong style="color:#DD5145">对象（或数组）类型数据</strong>, 它内部会自动通过```reactive```转为<strong style="color:#DD5145">代理对象</strong>
-  从原理角度对比：
   -  ref通过``Object.defineProperty()``的```get```与```set```来实现响应式（数据劫持）
   -  reactive通过使用<strong style="color:#DD5145">Proxy</strong>来实现响应式（数据劫持）, 并通过<strong style="color:#DD5145">Reflect</strong>操作<strong style="color:orange">源对象</strong>内部的数据
-  从使用角度对比：
   -  ref定义的数据：操作数据<strong style="color:#DD5145">需要</strong>```.value```，读取数据时模板中直接读取<strong style="color:#DD5145">不需要</strong>```.value```
   -  reactive定义的数据：操作数据与读取数据：<strong style="color:#DD5145">均不需要</strong>```.value```

### 3.6 setup的两个注意点

- setup执行的时机
  - 在beforeCreate之前执行一次，this是undefined
  
- setup的参数
  - props：值为对象，包含：组件外部传递过来，且组件内部声明接收了的属性
  - context：上下文对象
    - attrs: 值为对象，包含：组件外部传递过来，但没有在props配置中声明的属性, 相当于 ```this.$attrs```
    - slots: 收到的插槽内容, 相当于 ```this.$slots```
    - emit: 分发自定义事件的函数, 相当于 ```this.$emit```

`src\components\Demo.vue`：

```vue
<template>
	<h1>一个人的信息</h1>
	<h2>姓名：{{person.name}}</h2>
	<h2>年龄：{{person.age}}</h2>
	<button @click="test">测试触发一下Demo组件的Hello事件</button>
</template>

<script>
	import {reactive} from 'vue'
	export default {
		name: 'Demo',
		props:['msg','school'],
		emits:['hello'],
		setup(props,context){
			// console.log('---setup---',props)
			// console.log('---setup---',context)
			// console.log('---setup---',context.attrs) //相当与Vue2中的$attrs
			// console.log('---setup---',context.emit) //触发自定义事件的。
			console.log('---setup---',context.slots) //插槽
			//数据
			let person = reactive({
				name:'张三',
				age:18
			})

			//方法
			function test(){
				context.emit('hello',666)
			}

			//返回一个对象（常用）
			return {
				person,
				test
			}
		}
	}
</script>
```

`src\App.vue`：

```vue
<template>
	<Demo @hello="showHelloMsg" msg="你好啊" school="尚硅谷">
		<template v-slot:qwe>
			<span>尚硅谷</span>
		</template>
		<template v-slot:asd>
			<span>尚硅谷</span>
		</template>
	</Demo>
</template>

<script>
	import Demo from './components/Demo'
	export default {
		name: 'App',
		components:{Demo},
		setup(){
			function showHelloMsg(value){
				alert(`你好啊，你触发了hello事件，我收到的参数是:${value}！`)
			}
			return {
				showHelloMsg
			}
		}
	}
</script>
```

### 3.7 计算属性与监视

##### 3.7.1 computed函数

- 与Vue2.x中computed配置功能一致
- 写法

```js
import {computed} from 'vue'

setup(){
    ...
	//计算属性——简写
    let fullName = computed(()=>{
        return person.firstName + '-' + person.lastName
    })
    //计算属性——完整
    let fullName = computed({
        get(){
            return person.firstName + '-' + person.lastName
        },
        set(value){
            const nameArr = value.split('-')
            person.firstName = nameArr[0]
            person.lastName = nameArr[1]
        }
    })
}
```

`src\components\Demo.vue`：

```vue
<template>
	<h1>一个人的信息</h1>
	姓：<input type="text" v-model="person.firstName">
	<br>
	名：<input type="text" v-model="person.lastName">
	<br>
	<span>全名：{{person.fullName}}</span>
	<br>
	全名：<input type="text" v-model="person.fullName">
</template>

<script>
	import {reactive,computed} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let person = reactive({
				firstName:'张',
				lastName:'三'
			})
			//计算属性——简写（没有考虑计算属性被修改的情况）
			/* person.fullName = computed(()=>{
				return person.firstName + '-' + person.lastName
			}) */

			//计算属性——完整写法（考虑读和写）
			person.fullName = computed({
				get(){
					return person.firstName + '-' + person.lastName
				},
				set(value){
					const nameArr = value.split('-')
					person.firstName = nameArr[0]
					person.lastName = nameArr[1]
				}
			})

			//返回一个对象（常用）
			return {
				person
			}
		}
	}
</script>
```

`src\App.vue`：

```vue
<template>
	<Demo/>
</template>

<script>
	import Demo from './components/Demo'
	export default {
		name: 'App',
		components:{Demo},
	}
</script>
```

##### 3.7.2  watch函数

- 与Vue2.x中watch配置功能一致

- 两个小“坑”：

  - 监视reactive定义的响应式数据时：oldValue无法正确获取、强制开启了深度监视（deep配置失效）
  - 监视reactive定义的响应式数据中某个属性时：deep配置有效
  
```js
//情况一：监视ref定义的响应式数据
watch(sum,(newValue,oldValue)=>{
console.log('sum变化了',newValue,oldValue)
},{immediate:true})

//情况二：监视多个ref定义的响应式数据
watch([sum,msg],(newValue,oldValue)=>{
console.log('sum或msg变化了',newValue,oldValue)
}) 

/* 情况三：监视reactive定义的响应式数据
		若watch监视的是reactive定义的响应式数据，则无法正确获得oldValue！！
		若watch监视的是reactive定义的响应式数据，则强制开启了深度监视 
*/
watch(person,(newValue,oldValue)=>{
console.log('person变化了',newValue,oldValue)
},{immediate:true,deep:false}) //此处的deep配置不再奏效

//情况四：监视reactive定义的响应式数据中的某个属性
watch(()=>person.job,(newValue,oldValue)=>{
console.log('person的job变化了',newValue,oldValue)
},{immediate:true,deep:true}) 

//情况五：监视reactive定义的响应式数据中的某些属性
watch([()=>person.job,()=>person.name],(newValue,oldValue)=>{
console.log('person的job变化了',newValue,oldValue)
},{immediate:true,deep:true})

//特殊情况
watch(()=>person.job,(newValue,oldValue)=>{
  console.log('person的job变化了',newValue,oldValue)
},{deep:true}) //此处由于监视的是reactive素定义的对象中的某个属性，所以deep配置有效
```

`src\components\Demo.vue`：

```vue
<template>
	<h2>当前求和为：{{sum}}</h2>
	<button @click="sum++">点我+1</button>
	<hr>
	<h2>当前的信息为：{{msg}}</h2>
	<button @click="msg+='！'">修改信息</button>
	<hr>
	<h2>姓名：{{person.name}}</h2>
	<h2>年龄：{{person.age}}</h2>
	<h2>薪资：{{person.job.j1.salary}}K</h2>
	<button @click="person.name+='~'">修改姓名</button>
	<button @click="person.age++">增长年龄</button>
	<button @click="person.job.j1.salary++">涨薪</button>
</template>

<script>
	import {ref,reactive,watch} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let sum = ref(0)
			let msg = ref('你好啊')
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})

			//情况一：监视ref所定义的一个响应式数据
			/* watch(sum,(newValue,oldValue)=>{
				console.log('sum变了',newValue,oldValue)
			},{immediate:true}) */

			//情况二：监视ref所定义的多个响应式数据
			/* watch([sum,msg],(newValue,oldValue)=>{
				console.log('sum或msg变了',newValue,oldValue)
			},{immediate:true}) */

			/* 
				情况三：监视reactive所定义的一个响应式数据的全部属性
						1.注意：此处无法正确的获取oldValue
						2.注意：强制开启了深度监视（deep配置无效）
			*/
			/* watch(person,(newValue,oldValue)=>{
				console.log('person变化了',newValue,oldValue)
			},{deep:false}) //此处的deep配置无效 */

			//情况四：监视reactive所定义的一个响应式数据中的某个属性
			/* watch(()=>person.name,(newValue,oldValue)=>{
				console.log('person的name变化了',newValue,oldValue)
			})  */

			//情况五：监视reactive所定义的一个响应式数据中的某些属性
			/* watch([()=>person.name,()=>person.age],(newValue,oldValue)=>{
				console.log('person的name或age变化了',newValue,oldValue)
			})  */

			//特殊情况
			/* watch(()=>person.job,(newValue,oldValue)=>{
				console.log('person的job变化了',newValue,oldValue)
			},{deep:true}) //此处由于监视的是reactive素定义的对象中的某个属性，所以deep配置有效 */


			//返回一个对象（常用）
			return {
				sum,
				msg,
				person
			}
		}
	}
</script>
```

`src\App.vue`：

```vue
<template>
	<Demo/>
</template>

<script>
	import Demo from './components/Demo'
	export default {
		name: 'App',
		components:{Demo},
	}
</script>
```

**补充**：在 Vue 3 中，`watch` API 的功能类似于 Vue 2 中的 `watch`，但是在 Vue 3 中，它的用法有所改变，主要是因为 Vue 3 的 Composition API 带来了一些新的特性。`watch` 用于监视响应式数据的变化，并在数据变化时执行特定的逻辑。在 Vue 3 中可以使用 `watch` 函数来监视 `ref`、`reactive` 和 `computed` 数据。下面演示如何使用 `watch` 来监视 `ref` 数据：

```javascript
// `watch` 监视了 `count` 这个 `ref` 的变化，并在值发生变化时执行回调函数。回调函数接收两个参数：新值和旧值
javascriptimport { ref, watch } from 'vue';

const count = ref(0);

watch(count, (newValue, oldValue) => {
  console.log(`count 的值从 ${oldValue} 变为 ${newValue}`);
});

// 修改 count 的值
count.value = 1; // 输出：count 的值从 0 变为 1
count.value = 2; // 输出：count 的值从 1 变为 2
```

如果要监视多个数据，可以将它们作为一个对象传递给 `watch`：

```javascript
// `watch` 同时监视了 `count1` 和 `count2` 这两个数据，并在它们的值发生变化时执行回调函数。
javascriptwatch(
  { 
    count1: count1,
    count2: count2 
  },
  (newValues, oldValues) => {
    console.log(`count1 的值从 ${oldValues.count1} 变为 ${newValues.count1}`);
    console.log(`count2 的值从 ${oldValues.count2} 变为 ${newValues.count2}`);
  }
);
```

另外还可以使用 `watch` 监视响应式对象的特定属性：

```javascript
// 演示如何在 Vue 3 中使用 `watch` 来监视 `ref`、`reactive` 和 `computed` 数据的变化，并执行相应的逻辑
javascriptconst obj = reactive({ foo: 'bar' });

watch(() => obj.foo, (newValue, oldValue) => {
  console.log(`obj.foo 的值从 ${oldValue} 变为 ${newValue}`);
});

// 修改 obj.foo 的值
obj.foo = 'baz'; // 输出：obj.foo 的值从 bar 变为 baz
```

##### 3.7.3  watch监视ref数据

`src\components\Demo.vue`：

```vue
<template>
	<h2>当前求和为：{{sum}}</h2>
	<button @click="sum++">点我+1</button>
	<hr>
	<h2>当前的信息为：{{msg}}</h2>
	<button @click="msg+='！'">修改信息</button>
	<hr>
	<h2>姓名：{{person.name}}</h2>
	<h2>年龄：{{person.age}}</h2>
	<h2>薪资：{{person.job.j1.salary}}K</h2>
	<button @click="person.name+='~'">修改姓名</button>
	<button @click="person.age++">增长年龄</button>
	<button @click="person.job.j1.salary++">涨薪</button>
</template>

<script>
	import {ref,reactive,watch} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let sum = ref(0)
			let msg = ref('你好啊')
			let person = ref({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})

			console.log(person)

			watch(sum,(newValue,oldValue)=>{
				console.log('sum的值变化了',newValue,oldValue)
			})

			watch(person,(newValue,oldValue)=>{
				console.log('person的值变化了',newValue,oldValue)
			},{deep:true})


			//返回一个对象（常用）
			return {
				sum,
				msg,
				person
			}
		}
	}
</script>
```

##### 3.7.4 watchEffect函数

- watch的套路是：既要指明监视的属性，也要指明监视的回调

- watchEffect的套路是：不用指明监视哪个属性，监视的回调中用到哪个属性，那就监视哪个属性

- watchEffect有点像computed：

  - 但computed注重的计算出来的值（回调函数的返回值），所以必须要写返回值
  - 而watchEffect更注重的是过程（回调函数的函数体），所以不用写返回值

```js
//watchEffect所指定的回调中用到的数据只要发生变化，则直接重新执行回调。
watchEffect(()=>{
  const x1 = sum.value
  const x2 = person.age
  console.log('watchEffect配置的回调执行了')
})
```

`src\components\Demo.vue`：

```vue
<template>
	<h2>当前求和为：{{sum}}</h2>
	<button @click="sum++">点我+1</button>
	<hr>
	<h2>当前的信息为：{{msg}}</h2>
	<button @click="msg+='！'">修改信息</button>
	<hr>
	<h2>姓名：{{person.name}}</h2>
	<h2>年龄：{{person.age}}</h2>
	<h2>薪资：{{person.job.j1.salary}}K</h2>
	<button @click="person.name+='~'">修改姓名</button>
	<button @click="person.age++">增长年龄</button>
	<button @click="person.job.j1.salary++">涨薪</button>
</template>

<script>
	import {ref,reactive,watch,watchEffect} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let sum = ref(0)
			let msg = ref('你好啊')
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})

			//监视
			/* watch(sum,(newValue,oldValue)=>{
				console.log('sum的值变化了',newValue,oldValue)
			},{immediate:true}) */

			watchEffect(()=>{
                // 不用指明监视哪个属性，监视的回调中用到哪个属性，那就监视哪个属性
				const x1 = sum.value
				const x2 = person.job.j1.salary
				console.log('watchEffect所指定的回调执行了')
			})

			//返回一个对象（常用）
			return {
				sum,
				msg,
				person
			}
		}
	}
</script>
```

**补充**：`watchEffect` 是 Vue 3 中的一个组合式 API 函数，用于响应式地追踪依赖并执行副作用。它与 `watch` 类似，但 `watchEffect` 更加自动化和简洁，不需要明确指定依赖项

**`watchEffect` 基本用法**：`watchEffect` 接受一个回调函数，该回调函数会在其依赖的响应式数据发生变化时重新运行。Vue 会自动追踪回调函数中使用的所有响应式数据

```javascript
import { ref, watchEffect } from 'vue';

const count = ref(0);

watchEffect(() => {
  console.log(`Count has changed to: ${count.value}`);
});

count.value++;  // 触发 watchEffect 回调
```

**`watchEffect` 特点**：

1. **自动追踪依赖**：`watchEffect` 不需要明确声明依赖项，它会自动追踪回调函数中使用的所有响应式数据
2. **立即执行**：默认情况下，`watchEffect` 会在注册时立即执行一次回调函数
3. **清理副作用**：可以在回调函数中返回一个清理函数，用于清理上一次副作用

**清理副作用**：如果需要在每次副作用重新执行之前进行一些清理操作，可以在回调函数中返回一个清理函数

```javascript
import { ref, watchEffect } from 'vue';

const count = ref(0);

watchEffect((onCleanup) => {
  const id = setInterval(() => {
    console.log(`Count is: ${count.value}`);
  }, 1000);

  // 注册清理函数
  onCleanup(() => {
    clearInterval(id);
  });
});

count.value++;  // 触发 watchEffect 回调
```

**调用时机**：可以通过配置选项来控制 `watchEffect` 的调用时机，例如在 DOM 更新后执行

```javascript
import { ref, watchEffect } from 'vue';

const count = ref(0);

watchEffect(() => {
  console.log(`Count has changed to: ${count.value}`);
}, {
  flush: 'post'  // 在 DOM 更新后调用回调
});

count.value++;  // 触发 watchEffect 回调
```

`flush` 选项有三个值：

- `'pre'` (默认值)：在组件更新之前调用
- `'post'`：在组件更新之后调用
- `'sync'`：同步调用

**总结**：`watchEffect` 是一个强大的工具，适用于需要自动追踪响应式数据并执行副作用的场景。它简化了代码，不需要手动声明依赖项，且提供了灵活的清理机制和调用时机控制

### 3.8 生命周期

vue2生命周期：

![image-20240514215029773](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405170039287.png)

![image-20240514215048745](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405170040439.png)



vue3生命周期

![image-20240514214600779](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/image-20240514214600779.png)



Vue3.0中可以继续使用Vue2.x中的生命周期钩子，但有有两个被更名：
  - ```beforeDestroy```改名为 ```beforeUnmount```
  - ```destroyed```改名为 ```unmounted```

Vue3.0也提供了 Composition API 形式的生命周期钩子，与Vue2.x中钩子对应关系如下：

  - `beforeCreate`===>`setup()`
  - `created`=======>`setup()`
  - `beforeMount` ===>`onBeforeMount`
  - `mounted`=======>`onMounted`
  - `beforeUpdate`===>`onBeforeUpdate`
  - `updated` =======>`onUpdated`
  - `beforeUnmount` ==>`onBeforeUnmount`
  - `unmounted` =====>`onUnmounted`

`src\components\Demo.vue`：

```vue
<template>
	<h2>当前求和为：{{sum}}</h2>
	<button @click="sum++">点我+1</button>
</template>

<script>
	import {ref,onBeforeMount,onMounted,onBeforeUpdate,onUpdated,onBeforeUnmount,onUnmounted} from 'vue'
	export default {
		name: 'Demo',
		
		setup(){
			console.log('---setup---')
			//数据
			let sum = ref(0)

			//通过组合式API的形式去使用生命周期钩子
			onBeforeMount(()=>{
				console.log('---onBeforeMount---')
			})
			onMounted(()=>{
				console.log('---onMounted---')
			})
			onBeforeUpdate(()=>{
				console.log('---onBeforeUpdate---')
			})
			onUpdated(()=>{
				console.log('---onUpdated---')
			})
			onBeforeUnmount(()=>{
				console.log('---onBeforeUnmount---')
			})
			onUnmounted(()=>{
				console.log('---onUnmounted---')
			})

			//返回一个对象（常用）
			return {sum}
		},
		//通过配置项的形式使用生命周期钩子
		//#region 
		beforeCreate() {
			console.log('---beforeCreate---')
		},
		created() {
			console.log('---created---')
		},
		beforeMount() {
			console.log('---beforeMount---')
		},
		mounted() {
			console.log('---mounted---')
		},
		beforeUpdate(){
			console.log('---beforeUpdate---')
		},
		updated() {
			console.log('---updated---')
		},
		beforeUnmount() {
			console.log('---beforeUnmount---')
		},
		unmounted() {
			console.log('---unmounted---')
		},
		//#endregion
	}
</script>
```

**补充**：在 Vue 3 中，组件的生命周期指的是从组件实例创建、挂载、更新到销毁的全过程。Vue 3 提供了一系列的生命周期钩子，让开发者可以在不同的阶段执行自定义逻辑

**生命周期钩子**：以下是 Vue 3 中常用的生命周期钩子：

1. **`beforeCreate`**：组件实例刚被创建时调用。在这一步，组件的响应式状态还未初始化
2. **`created`**：组件实例创建完成并进行响应式处理之后调用。这时，组件的属性、方法、计算属性和观察者都已设置完毕，但 DOM 还未生成
3. **`beforeMount`**：在挂载开始之前被调用：相关的 render 函数首次被调用
4. **`mounted`**：实例被挂载后调用，这时 `el` 被新创建的 `vm.$el` 替换。此钩子在服务器端渲染期间不被调用
5. **`beforeUpdate`**：数据更新时调用，发生在虚拟 DOM 打补丁之前。可以在这个钩子中进一步更改状态，这不会触发附加的重渲染过程
6. **`updated`**：由于数据更改导致的虚拟 DOM 重新渲染和打补丁之后调用。此时组件 DOM 已经更新，所以你可以执行依赖于 DOM 的操作。避免在这个钩子中更改状态，因为这可能会导致更新无限循环
7. **`beforeUnmount`**：实例销毁之前调用。这一步，实例仍然完全可用
8. **`unmounted`**：实例销毁后调用。调用后，所有的事件监听器会被移除，所有的子实例也会被销毁

**组合式 API 中的生命周期钩子**：在 Vue 3 中，除了 Options API，还可以使用 Composition API 提供的生命周期钩子，这些钩子函数在 `setup` 函数中使用：

- `onBeforeMount`
- `onMounted`
- `onBeforeUpdate`
- `onUpdated`
- `onBeforeUnmount`
- `onUnmounted`

**`Options API`示例**：

```javascript
export default {
  data() {
    return {
      message: "Hello Vue 3!"
    };
  },
  beforeCreate() {
    console.log('beforeCreate');
  },
  created() {
    console.log('created');
  },
  beforeMount() {
    console.log('beforeMount');
  },
  mounted() {
    console.log('mounted');
  },
  beforeUpdate() {
    console.log('beforeUpdate');
  },
  updated() {
    console.log('updated');
  },
  beforeUnmount() {
    console.log('beforeUnmount');
  },
  unmounted() {
    console.log('unmounted');
  }
};
```

**`Composition API`示例**：

```javascript
import { ref, onMounted, onUnmounted, onBeforeUpdate, onUpdated } from 'vue';

export default {
  setup() {
    const message = ref("Hello Vue 3!");

    onMounted(() => {
      console.log('mounted');
    });

    onUnmounted(() => {
      console.log('unmounted');
    });

    onBeforeUpdate(() => {
      console.log('beforeUpdate');
    });

    onUpdated(() => {
      console.log('updated');
    });

    return {
      message
    };
  }
};
```

**总结**：Vue 3 提供了丰富的生命周期钩子，允许开发者在组件的不同阶段执行特定的逻辑。同时，Vue 3 引入了 Composition API，提供了更灵活的方式来使用这些钩子函数。无论是通过 Options API 还是 Composition API，都可以方便地管理组件的生命周期。

### 3.9 自定义hook函数

- 什么是hook？—— 本质是一个函数，把setup函数中使用的Composition API进行了封装

- 类似于vue2.x中的mixin

- 自定义hook的优势: 复用代码, 让setup中的逻辑更清楚易懂

`src\components\Demo.vue`：

```vue
<template>
	<h2>当前求和为：{{sum}}</h2>
	<button @click="sum++">点我+1</button>
	<hr>
	<h2>当前点击时鼠标的坐标为：x：{{point.x}}，y：{{point.y}}</h2>
</template>

<script>
	import {ref} from 'vue'
	import usePoint from '../hooks/usePoint'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let sum = ref(0)
			let point = usePoint()
			

			//返回一个对象（常用）
			return {sum,point}
		}
	}
</script>
```

`src\components\Test.vue`：

```vue
<template>
	<h2>我是Test组件</h2>
	<h2>当前点击时鼠标的坐标为：x：{{point.x}}，y：{{point.y}}</h2>
</template>

<script>
	import usePoint from '../hooks/usePoint'
	export default {
		name:'Test',
		setup(){
			const point = usePoint()
			return {point}
		}
	}
</script>
```

`src\hooks\usePoint.js`：

```js
import {reactive,onMounted,onBeforeUnmount} from 'vue'
export default function (){
	//实现鼠标“打点”相关的数据
	let point = reactive({
		x:0,
		y:0
	})

	//实现鼠标“打点”相关的方法
	function savePoint(event){
		point.x = event.pageX
		point.y = event.pageY
		console.log(event.pageX,event.pageY)
	}

	//实现鼠标“打点”相关的生命周期钩子
	onMounted(()=>{
		window.addEventListener('click',savePoint)
	})

	onBeforeUnmount(()=>{
		window.removeEventListener('click',savePoint)
	})

	return point
}
```

`src\App.vue`：

```vue
<template>
	<button @click="isShowDemo = !isShowDemo">切换隐藏/显示</button>
	<Demo v-if="isShowDemo"/>
	<hr>
	<Test/>
</template>

<script>
	import {ref} from 'vue'
	import Demo from './components/Demo'
	import Test from './components/Test'
	export default {
		name: 'App',
		components:{Demo,Test},
		setup() {
			let isShowDemo = ref(true)
			return {isShowDemo}
		}
	}
</script>
```



**补充**：在 Vue 3 中，自定义 Hook 函数通常被称为“组合函数”（Composition Functions）。它们利用 Vue 3 的组合式 API（Composition API）来提取和重用逻辑。与 Vue 2 中的混入（mixins）相比，组合函数更加灵活和可组合

**创建自定义组合函数**：自定义组合函数通常以 `use` 开头，这是一种社区约定。以下是创建和使用自定义组合函数的基本步骤：

1. **创建组合函数**：定义一个函数来封装可重用的逻辑，并返回所需的响应式状态和方法
2. **使用组合函数**：在组件的 `setup` 函数中调用该组合函数，并将返回的状态和方法暴露给模板

**示例**：计数器组合函数，以下是一个简单的计数器组合函数示例：

一、定义组合函数。首先定义一个名为 `useCounter` 的组合函数：

```javascript
// useCounter.js
import { ref } from 'vue';

export function useCounter() {
  const count = ref(0);

  function increment() {
    count.value++;
  }

  function decrement() {
    count.value--;
  }

  return {
    count,
    increment,
    decrement,
  };
}
```

二、使用组合函数。然后在一个 Vue 组件中使用这个组合函数：

```javascript
// CounterComponent.vue
<template>
  <div>
    <p>Count: {{ count }}</p>
    <button @click="increment">Increment</button>
    <button @click="decrement">Decrement</button>
  </div>
</template>

<script>
import { useCounter } from './useCounter';

export default {
  setup() {
    const { count, increment, decrement } = useCounter();
    return {
      count,
      increment,
      decrement,
    };
  },
};
</script>
```

**好处**：使用组合函数有以下几个好处

- **逻辑复用**：可以将可重用的逻辑提取到独立的函数中，方便在多个组件中使用。
- **代码组织**：使组件代码更加模块化，易于维护和测试。
- **类型安全**：与 TypeScript 结合使用时，可以更好地利用类型检查和智能提示。

**组合函数中的生命周期**：在组合函数中也可以使用 Vue 的生命周期钩子，例如 `onMounted`、`onUnmounted` 等。它们需要从 `vue` 包中导入并在函数内部调用

```javascript
import { ref, onMounted, onUnmounted } from 'vue';

export function useCounter() {
  const count = ref(0);

  function increment() {
    count.value++;
  }

  function decrement() {
    count.value--;
  }

  onMounted(() => {
    console.log('Component mounted');
  });

  onUnmounted(() => {
    console.log('Component unmounted');
  });

  return {
    count,
    increment,
    decrement,
  };
}
```

**结论**：Vue 3 的组合函数提供了一种强大且灵活的方法来管理组件逻辑，促进代码复用和更好的组织。通过将逻辑提取到独立的函数中，可以显著提升代码的清晰度和可维护性

### 3.10 toRef

- 作用：创建一个 ref 对象，其value值指向另一个对象中的某个属性
- 语法：```const name = toRef(person,'name')```
- 应用:   要将响应式对象中的某个属性单独提供给外部使用时


- 扩展：```toRefs``` 与```toRef```功能一致，但可以批量创建多个 ref 对象，语法：```toRefs(person)```

`src\components\Demo.vue`：

```vue
<template>
	<h4>{{person}}</h4>
	<h2>姓名：{{name}}</h2>
	<h2>年龄：{{age}}</h2>
	<h2>薪资：{{job.j1.salary}}K</h2>
	<button @click="name+='~'">修改姓名</button>
	<button @click="age++">增长年龄</button>
	<button @click="job.j1.salary++">涨薪</button>
</template>

<script>
	import {ref,reactive,toRef,toRefs} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})

			// const name1 = person.name
			// console.log('%%%',name1)

			// const name2 = toRef(person,'name')
			// console.log('####',name2)

			const x = toRefs(person)
			console.log('******',x)

			//返回一个对象（常用）
			return {
				person,
				// name:toRef(person,'name'),
				// age:toRef(person,'age'),
				// salary:toRef(person.job.j1,'salary'),
				...toRefs(person)
			}
		}
	}
</script>
```

**补充**：`toRef` 是 Vue 3 中的一个实用工具函数，用于将对象的某个属性转换为响应式引用（`ref`）。这个功能在处理组合式 API（Composition API）时特别有用，因为它允许你将对象的某个属性单独抽取出来，并保持其响应性

**`toRef` 使用场景**：在 Vue 3 组合式 API 中，有时需要将一个响应式对象的某个属性单独传递或使用，而不是整个对象。此时，`toRef` 就显得非常方便。例如当希望将一个大对象中的某个属性传递给子组件时，`toRef` 可以保持该属性的响应性

**语法**：

```javascript
toRef(object, key)
```

- `object`: 一个响应式对象
- `key`: 你希望转换为引用的属性名

**示例**：以下是使用 `toRef` 的简单示例。在这个示例中，创建了一个响应式对象 `state`，其中包含 `count` 和 `message` 属性。通过 `toRef`将 `count` 属性转换为一个单独的响应式引用 `countRef`。这样就可以在模板中单独使用 `countRef`，并保持其响应性

```javascript
import { reactive, toRef } from 'vue';

export default {
  setup() {
    const state = reactive({
      count: 0,
      message: 'Hello, Vue 3!'
    });

    const countRef = toRef(state, 'count');

    function increment() {
      countRef.value++;
    }

    return {
      countRef,
      increment
    };
  }
};
```

**组合 `toRefs`**：如果需要将对象的多个属性都转换为引用，可以使用 `toRefs`。`toRefs` 会将对象的所有属性都转换为响应式引用

```javascript
// 使用 `toRefs` 将 `state` 对象中的所有属性都转换为响应式引用，然后可以单独使用 `count` 和 `message`，并且它们仍然保持响应性
import { reactive, toRefs } from 'vue';
export default {
  setup() {
    const state = reactive({
      count: 0,
      message: 'Hello, Vue 3!'
    });

    const { count, message } = toRefs(state);

    function increment() {
      count.value++;
    }

    return {
      count,
      message,
      increment
    };
  }
};
```

**总结**：`toRef` 和 `toRefs` 是 Vue 3 组合式 API 中非常有用的工具，可以更灵活地处理响应式数据。`toRef` 适用于需要单独处理对象某个属性的情况，而 `toRefs` 则适用于需要处理对象中所有属性的情况。通过这些工具可以更高效地管理和传递响应式状态

## 4.其它 Composition API

### 4.1 shallowReactive 与 shallowRef

- shallowReactive：只处理对象最外层属性的响应式（浅响应式）
- shallowRef：只处理基本数据类型的响应式, 不进行对象的响应式处理

- 使用场景：
  -  shallowReactive：如果有一个对象数据，结构比较深, 但变化时只是外层属性变化
  -  shallowRef：如果有一个对象数据，后续功能不会修改该对象中的属性，而是生新的对象来替换

`src\components\Demo.vue`：

```vue
<template>
	<h4>当前的x.y值是：{{x.y}}</h4>
	<button @click="x={y:888}">点我替换x</button>
	<button @click="x.y++">点我x.y++</button>
	<hr>
	<h4>{{person}}</h4>
	<h2>姓名：{{name}}</h2>
	<h2>年龄：{{age}}</h2>
	<h2>薪资：{{job.j1.salary}}K</h2>
	<button @click="name+='~'">修改姓名</button>
	<button @click="age++">增长年龄</button>
	<button @click="job.j1.salary++">涨薪</button>
</template>

<script>
	import {ref,reactive,toRef,toRefs,shallowReactive,shallowRef} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			// let person = shallowReactive({ //只考虑第一层数据的响应式
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})
			let x = shallowRef({
				y:0
			})
			console.log('******',x)

			//返回一个对象（常用）
			return {
				x,
				person,
				...toRefs(person)
			}
		}
	}
</script>
```

**补充**：在 Vue 3 中，`shallowReactive` 和 `shallowRef` 是用于创建响应式状态的两种不同方式，它们的“shallow”特性意味着仅对对象的顶层属性进行响应式处理，而不对嵌套对象进行深度响应式处理。这可以在某些情况下提高性能和减少不必要的响应式开销。`shallowReactive` 和 `shallowRef` 提供了一种更细粒度的响应式状态管理方式，适用于特定的性能优化需求

**`shallowReactive`**：`shallowReactive` 创建一个响应式对象，但只对该对象的顶层属性进行响应式处理。嵌套对象将保持非响应式状态。`shallowReactive`语法：

```javascript
import { shallowReactive } from 'vue';

const state = shallowReactive({
  foo: 1,
  bar: {
    baz: 2
  }
});
```

**`shallowReactive`示例**：

```javascript
import { shallowReactive } from 'vue';

const state = shallowReactive({
  user: {
    name: 'Alice',
    age: 30
  },
  isLoggedIn: true
});

// 修改顶层属性是响应式的
state.isLoggedIn = false; // 触发响应

// 修改嵌套对象属性不会触发响应
state.user.name = 'Bob'; // 不触发响应
```

`shallowRef`：`shallowRef` 创建一个响应式引用，但仅对引用本身进行响应式处理。引用的值如果是对象，则不会进行深度响应式处理。`shallowRef`语法：

```javascript
import { shallowRef } from 'vue';

const state = shallowRef({
  foo: 1,
  bar: {
    baz: 2
  }
});
```

`shallowRef`示例：

```javascript
import { shallowRef } from 'vue';

const state = shallowRef({
  user: {
    name: 'Alice',
    age: 30
  }
});

// 修改引用本身是响应式的
state.value = { user: { name: 'Carol', age: 25 } }; // 触发响应

// 修改引用内的对象属性不会触发响应
state.value.user.name = 'Bob'; // 不触发响应
```

使用场景：

- **`shallowReactive`**：适用于需要对对象的顶层属性进行响应式处理，但不希望对嵌套对象进行深度响应式处理的场景。例如，当嵌套对象是静态的或不频繁更改时，`shallowReactive` 可以减少性能开销
- **`shallowRef`**：适用于需要响应引用本身的变化，但不需要对引用的对象进行深度响应式处理的场景。比如，当你只关心引用的重新赋值，而不关心引用内对象的变化时

### 4.2 readonly 与 shallowReadonly

- readonly: 让一个响应式数据变为只读的（深只读）
- shallowReadonly：让一个响应式数据变为只读的（浅只读）
- 应用场景: 不希望数据被修改时

`src\components\Demo.vue`：

```vue
<template>
	<h4>当前求和为：{{sum}}</h4>
	<button @click="sum++">点我++</button>
	<hr>
	<h2>姓名：{{name}}</h2>
	<h2>年龄：{{age}}</h2>
	<h2>薪资：{{job.j1.salary}}K</h2>
	<button @click="name+='~'">修改姓名</button>
	<button @click="age++">增长年龄</button>
	<button @click="job.j1.salary++">涨薪</button>
</template>

<script>
	import {ref,reactive,toRefs,readonly,shallowReadonly} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let sum = ref(0)
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})

			person = readonly(person)
			// person = shallowReadonly(person)
			// sum = readonly(sum)
			// sum = shallowReadonly(sum)

			//返回一个对象（常用）
			return {
				sum,
				...toRefs(person)
			}
		}
	}
</script>
```

**补充**：在 Vue 3 中，`readonly` 和 `shallowReadonly` 是用于创建只读反应性状态的工具。它们可以帮助开发者保护状态，防止意外的修改

**`readonly`**：`readonly` 创建一个深度只读的响应式对象。所谓“深度只读”，意味着对象内部的所有嵌套属性也都会变成只读状态。任何试图修改这些属性的操作都会在开发环境下触发警告

**使用示例**：

```javascript
import { reactive, readonly } from 'vue';

const state = reactive({ 
  user: {
    name: 'Alice',
    age: 30
  }
});

const readonlyState = readonly(state);

// 尝试修改readonlyState会触发警告
readonlyState.user.name = 'Bob';  // 警告：修改只读属性
```

`shallowReadonly`：`shallowReadonly` 创建一个浅只读的响应式对象。所谓“浅只读”，意味着只有对象的顶层属性是只读的，嵌套的属性仍然是可变的

**使用示例**：

```javascript
import { reactive, shallowReadonly } from 'vue';

const state = reactive({ 
  user: {
    name: 'Alice',
    age: 30
  }
});

const shallowReadonlyState = shallowReadonly(state);

// 顶层属性是只读的
shallowReadonlyState.user = { name: 'Bob', age: 25 };  // 警告：修改只读属性

// 嵌套属性是可变的
shallowReadonlyState.user.name = 'Bob';  // 可以修改，不会触发警告
```

比较：

- `readonly`：深度只读，整个对象及其所有嵌套属性都不可变
- `shallowReadonly`：浅只读，只有对象的顶层属性不可变，嵌套属性仍可修改

适用场景：

- 使用 `readonly` 时，适合需要完全保护整个对象及其嵌套属性不被修改的情况
- 使用 `shallowReadonly` 时，适合只需要保护对象的顶层属性，允许嵌套属性被修改的情况

### 4.3 toRaw 与 markRaw

**toRaw**：

- 作用：将一个由```reactive```生成的<strong style="color:orange">响应式对象</strong>转为<strong style="color:orange">普通对象</strong>
- 使用场景：用于读取响应式对象对应的普通对象，对这个普通对象的所有操作，不会引起页面更新

**markRaw**：

- 作用：标记一个对象，使其永远不会再成为响应式对象
- 应用场景：
  1. 有些值不应被设置为响应式的，例如复杂的第三方类库等
  2. 当渲染具有不可变数据源的大列表时，跳过响应式转换可以提高性能

`src\components\Demo.vue`：

```vue
<template>
	<h4>当前求和为：{{sum}}</h4>
	<button @click="sum++">点我++</button>
	<hr>
	<h2>姓名：{{name}}</h2>
	<h2>年龄：{{age}}</h2>
	<h2>薪资：{{job.j1.salary}}K</h2>
	<h3 v-show="person.car">座驾信息：{{person.car}}</h3>
	<button @click="name+='~'">修改姓名</button>
	<button @click="age++">增长年龄</button>
	<button @click="job.j1.salary++">涨薪</button>
	<button @click="showRawPerson">输出最原始的person</button>
	<button @click="addCar">给人添加一台车</button>
	<button @click="person.car.name+='!'">换车名</button>
	<button @click="changePrice">换价格</button>
</template>

<script>
	import {ref,reactive,toRefs,toRaw,markRaw} from 'vue'
	export default {
		name: 'Demo',
		setup(){
			//数据
			let sum = ref(0)
			let person = reactive({
				name:'张三',
				age:18,
				job:{
					j1:{
						salary:20
					}
				}
			})

			function showRawPerson(){
				const p = toRaw(person)
				p.age++
				console.log(p)
			}

			function addCar(){
				let car = {name:'奔驰',price:40}
				person.car = markRaw(car)
			}

			function changePrice(){
				person.car.price++
				console.log(person.car.price)
			}

			//返回一个对象（常用）
			return {
				sum,
				person,
				...toRefs(person),
				showRawPerson,
				addCar,
				changePrice
			}
		}
	}
</script>
```

**补充**：在 Vue 3 中，`toRaw` 和 `markRaw` 是两个用于处理响应式数据的方法。它们在某些场景下非常有用，尤其是在需要直接操作原始数据或避免数据成为响应式对象时

**`toRaw`**：`toRaw` 方法用于获取 Vue 响应式对象的原始数据。这个方法不会创建对象的副本，而是返回响应式对象的原始版本。它通常用于调试和临时读取数据，而不会影响响应式系统。用法：

```javascript
import { reactive, toRaw } from 'vue';

const state = reactive({
  count: 0
});

const rawState = toRaw(state);
console.log(rawState.count);  // 输出: 0
```

**`toRaw`主要用途**：

1. **调试**：在调试过程中，有时你需要查看响应式对象的原始数据
2. **性能优化**：在某些情况下原始数据的操作比响应式数据更高效

**`markRaw`**：`markRaw` 方法用于标记一个对象，使其永远不会成为响应式对象。这在你需要存储一些不会被 Vue 响应系统追踪的数据时非常有用，如第三方库的实例或复杂的类实例。用法：

```javascript
import { markRaw, reactive } from 'vue';

const nonReactiveObject = markRaw({
  someProperty: 'someValue'
});

const state = reactive({
  nested: nonReactiveObject
});

console.log(state.nested.someProperty);  // 输出: 'someValue'
```

**`markRaw`主要用途**：

1. **第三方库实例**：当使用某些第三方库的实例并且不希望这些实例被 Vue 的响应式系统影响时，可以使用 `markRaw`
2. **复杂的数据结构**：避免 Vue 的响应式转换可能对性能产生的负面影响

**总结**：

- **`toRaw`**：用于获取响应式对象的原始数据，不会影响原响应式对象，常用于调试和性能优化
- **`markRaw`**：用于标记对象使其不会成为响应式对象，适用于第三方库实例或复杂数据结构

###  4.4 customRef

- 作用：创建一个自定义的 ref，并对其依赖项跟踪和更新触发进行显式控制。

- 实现防抖效果：

```vue
<template>
<input type="text" v-model="keyword">
<h3>{{keyword}}</h3>
</template>

<script>
import {ref,customRef} from 'vue'
export default {
	name:'Demo',
	setup(){
		// let keyword = ref('hello') //使用Vue准备好的内置ref
		//自定义一个myRef
		function myRef(value,delay){
			let timer
			//通过customRef去实现自定义
			return customRef((track,trigger)=>{
				return{
					get(){
						track() //告诉Vue这个value值是需要被“追踪”的
						return value
					},
					set(newValue){
						clearTimeout(timer)
						timer = setTimeout(()=>{
							value = newValue
							trigger() //告诉Vue去更新界面
						},delay)
					}
				}
			})
		}
		let keyword = myRef('hello',500) //使用程序员自定义的ref
		return {
			keyword
		}
	}
}
</script>
```

**补充**：在 Vue 3 中，`customRef` 是一个新的 API，它允许开发者创建一个自定义的 reactive 引用对象（即响应式数据源）。这个 API 是 Composition API 的一部分，为开发者提供了更大的灵活性来控制响应式数据的行为，比如自定义如何追踪依赖和触发更新

**创建 customRef**：`customRef` 接受一个工厂函数作为参数，这个函数需要返回一个包含 `get` 和 `set` 方法的对象。这两个方法决定了如何读取和更新 ref 的值，并且控制依赖的追踪和触发

```javascript
import { customRef } from 'vue';

function useDebouncedRef(value, delay = 300) {
  let timeout;
  return customRef((track, trigger) => {
    return {
      get() {
        track();  // 用于追踪依赖
        return value;
      },
      set(newValue) {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
          value = newValue;
          trigger();  // 用于触发更新
        }, delay);
      }
    };
  });
}
```

**使用 customRef**：上面的示例中，`useDebouncedRef` 函数创建了一个带有防抖功能的 custom ref。这个 ref 在设定的时间延迟后才更新它的值，可以用于例如输入框等场景，避免频繁地触发更新

```javascript
import { ref } from 'vue';

export default {
  setup() {
    const name = useDebouncedRef('Vue');
    
    function updateName(newName) {
      name.value = newName;
    }

    return { name, updateName };
  }
}
```

在这个组件中，`name` 是一个延迟更新的 ref。如果在输入框中持续输入文本，`name.value` 只有在停止输入一段时间后才会更新，这有助于减少因为数据更新而触发的计算或渲染

**`customRef`优点**：使用 `customRef` 可以让开发者针对特定需求定制化 ref 的行为，比如实现防抖、节流、延迟更新等。这增加了 ref 的灵活性，让状态管理更加适应复杂或不标准的场景

### 4.5 provide 与 inject

![image-20240515224804378](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405170040366.png)





作用：实现<strong style="color:#DD5145">祖与后代组件间</strong>通信

套路：父组件有一个 `provide` 选项来提供数据，后代组件有一个 `inject` 选项来开始使用这些数据

具体写法：

1.祖组件中：

```js
setup(){
......
 let car = reactive({name:'奔驰',price:'40万'})
 provide('car',car)
 ......
}
```

2.后代组件中：

```js
setup(props,context){
......
 const car = inject('car')
 return {car}
......
}
```

`src\App.vue`：

```vue
<template>
	<div class="app">
		<h3>我是App组件（祖），{{name}}--{{price}}</h3>
		<Child/>
	</div>
</template>

<script>
	import { reactive,toRefs,provide } from 'vue'
	import Child from './components/Child.vue'
	export default {
		name:'App',
		components:{Child},
		setup(){
			let car = reactive({name:'奔驰',price:'40W'})
			provide('car',car) //给自己的后代组件传递数据
			return {...toRefs(car)}
		}
	}
</script>

<style>
	.app{
		background-color: gray;
		padding: 10px;
	}
</style>
```

`src\components\Child.vue`：

```vue
<template>
	<div class="child">
		<h3>我是Child组件（子）</h3>
		<Son/>
	</div>
</template>

<script>
	import {inject} from 'vue'
	import Son from './Son.vue'
	export default {
		name:'Child',
		components:{Son},
		/* setup(){
			let x = inject('car')
			console.log(x,'Child-----')
		} */
	}
</script>

<style>
	.child{
		background-color: skyblue;
		padding: 10px;
	}
</style>
```

`src\components\Son.vue`：

```vue
<template>
	<div class="son">
		<h3>我是Son组件（孙），{{car.name}}--{{car.price}}</h3>
	</div>
</template>

<script>
	import {inject} from 'vue'
	export default {
		name:'Son',
		setup(){
			let car = inject('car')
			return {car}
		}
	}
</script>

<style>
	.son{
		background-color: orange;
		padding: 10px;
	}
</style>
```

**补充**：在 Vue 3 中，`provide` 和 `inject` 是一对用于组件间依赖注入的 API，能够让祖先组件向后代组件提供数据，而无需通过 props 一层层地传递。这对于跨级传递数据非常有用，特别是在组件树较深时

**`provide` 和 `inject` 的基本用法**：

1.**`provide`**：`provide` 用于在祖先组件中声明提供的数据。它通常在 `setup` 函数中使用，并返回一个包含提供数据的对象

```javascript
// 案例：祖先组件通过 provide提供了一个名为 count的数据
import { provide } from 'vue';

export default {
  setup() {
    const count = ref(0);
    provide('count', count);

    return {
      count
    };
  },
  template: `
    <div>
      <p>Count: {{ count }}</p>
      <button @click="count++">Increment</button>
      <ChildComponent />
    </div>
  `
};
```

2.**`inject`**：`inject` 用于在后代组件中接收数据。它同样在 `setup` 函数中使用，并根据提供的数据键名进行接收

```javascript
// 后代组件通过 inject接收了祖先组件提供的 count 数据
import { inject } from 'vue';

export default {
  setup() {
    const count = inject('count');

    return {
      count
    };
  },
  template: `
    <div>
      <p>Injected count: {{ count }}</p>
    </div>
  `
};
```

**注意事项**：

1. **响应性**：`provide` 和 `inject` 传递的值是响应的，如果传递的是 `ref` 或 `reactive` 对象，后代组件会自动响应数据变化
2. **层级关系**：只有在组件层级关系内，后代组件才能通过 `inject` 接收到祖先组件的 `provide` 数据
3. **默认值**：`inject` 可以接受一个默认值，如果指定的 `provide` 数据不存在，则使用默认值

```javascript
const count = inject('count', 0); // 如果没有提供 'count'，则使用默认值 0
```

**示例**：一个完整的例子如下，在这个例子中，`ParentComponent` 通过 `provide` 提供了 `count` 数据，而 `ChildComponent` 通过 `inject` 接收并使用了这个数据

```javascript
// ParentComponent.vue
<template>
  <div>
    <p>Count: {{ count }}</p>
    <button @click="count++">Increment</button>
    <ChildComponent />
  </div>
</template>

<script>
import { ref, provide } from 'vue';
import ChildComponent from './ChildComponent.vue';

export default {
  components: { ChildComponent },
  setup() {
    const count = ref(0);
    provide('count', count);

    return {
      count
    };
  }
};
</script>

// ChildComponent.vue
<template>
  <div>
    <p>Injected count: {{ count }}</p>
  </div>
</template>

<script>
import { inject } from 'vue';

export default {
  setup() {
    const count = inject('count');
    return {
      count
    };
  }
};
</script>
```

### 4.6 响应式数据的判断

- isRef: 检查一个值是否为一个 ref 对象
- isReactive: 检查一个对象是否是由 `reactive` 创建的响应式代理
- isReadonly: 检查一个对象是否是由 `readonly` 创建的只读代理
- isProxy: 检查一个对象是否是由 `reactive` 或者 `readonly` 方法创建的代理

`src\App.vue`：

```vue
<template>
	<h3>我是App组件</h3>
</template>

<script>
	import {ref, reactive,toRefs,readonly,isRef,isReactive,isReadonly,isProxy } from 'vue'
	export default {
		name:'App',
		setup(){
			let car = reactive({name:'奔驰',price:'40W'})
			let sum = ref(0)
			let car2 = readonly(car)

			console.log(isRef(sum))
			console.log(isReactive(car))
			console.log(isReadonly(car2))
			console.log(isProxy(car))
			console.log(isProxy(sum))
			return {...toRefs(car)}
		}
	}
</script>

<style>
	.app{
		background-color: gray;
		padding: 10px;
	}
</style>
```

## 5.Composition API 的优势

### 5.1  Options API 存在的问题

使用传统OptionsAPI中，新增或者修改一个需求，就需要分别在data，methods，computed里修改 

<div style="width:600px;height:370px;overflow:hidden;float:left">
    <img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f84e4e2c02424d9a99862ade0a2e4114~tplv-k3u1fbpfcp-watermark.image" style="width:600px;float:left" />
</div>
<div style="width:300px;height:370px;overflow:hidden;float:left">
    <img src="https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e5ac7e20d1784887a826f6360768a368~tplv-k3u1fbpfcp-watermark.image" style="zoom:50%;width:560px;left" /> 
</div>






















### 5.2 Composition API 的优势

可以更加优雅的组织我们的代码，函数。让相关功能的代码更加有序的组织在一起

<div style="width:500px;height:340px;overflow:hidden;float:left">
    <img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bc0be8211fc54b6c941c036791ba4efe~tplv-k3u1fbpfcp-watermark.image"style="height:360px"/>
</div>
<div style="width:430px;height:340px;overflow:hidden;float:left">
    <img src="https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6cc55165c0e34069a75fe36f8712eb80~tplv-k3u1fbpfcp-watermark.image"style="height:360px"/>
</div>























### 5.3 Options API 和 Composition API对比

**一、Options API**：Options API 是 Vue 的传统代码组织方式，使用对象语法来定义组件的选项（如 `data`、`methods`、`computed` 等）

优点：

1. **易于上手**：对初学者友好，因为其结构清晰，逻辑分明
2. **良好的文档支持**：大部分 Vue 的文档和教程都是基于 Options API 的
3. **稳定性**：作为 Vue 的传统开发方式，经过大量项目的验证和使用，较为成熟稳定

缺点：

1. **代码分散**：当组件变得复杂时，相关的逻辑会散布在不同的选项中，增加了理解和维护的难度。
2. **复用性差**：逻辑复用主要依赖于 mixins，但 mixins 存在命名冲突和难以追踪的问题。

**二、Composition API**：Composition API 是 Vue 3 引入的新特性，使用函数式编程风格来组织组件逻辑，使得组件逻辑更加集中和复用。

优点：

1. **逻辑集中**：相关逻辑可以放在一起，代码更易于理解和维护。
2. **复用性强**：可以通过组合函数（composables）来实现逻辑复用，避免了 mixins 的各种问题。
3. **类型支持**：更好地支持 TypeScript，增强了类型推断和开发体验。

缺点：

1. **学习曲线**：对初学者来说，相比 Options API，有一定的学习难度。
2. **语法复杂**：对简单组件来说，可能会显得冗长和复杂。

**示例对比**：

1.Options API 示例

```javascript
export default {
  data() {
    return {
      count: 0
    };
  },
  methods: {
    increment() {
      this.count++;
    }
  }
};
```

2.Composition API 示例

```javascript
import { ref } from 'vue';

export default {
  setup() {
    const count = ref(0);

    function increment() {
      count.value++;
    }

    return {
      count,
      increment
    };
  }
};
```

## 6.新的组件

### 6.1 Fragment

- 在Vue2中: 组件必须有一个根标签
- 在Vue3中: 组件可以没有根标签, 内部会将多个标签包含在一个Fragment虚拟元素中
- 好处: 减少标签层级, 减小内存占用

**补充**：在 Vue 3 中引入了 Fragment 特性，这是一个允许组件返回多个根结点的重要新特性。在之前的 Vue 版本中，每个组件必须有一个单独的根节点，这会导致额外的标记和有时不必要的布局约束

**Fragment 的概念**：Fragment 是一个不产生额外 DOM 元素的包装器，可以返回多个元素而不需要一个共同的父元素。这对于减少 DOM 层级、提高渲染效率等方面是非常有益的

**如何使用 Fragment**：在 Vue 3 中可以直接在组件的 `template` 里返回多个根节点，Vue 会自动将它们视为 Fragment 对待。这在 Vue 2 中是不允许的

**举个例子：**

在 Vue 2 中必须这样写：

```vue
<template>
  <div>
    <h1>Title</h1>
    <p>Description</p>
  </div>
</template>
```

在 Vue 3 中可以省略外层的 `<div>`：

```vue
<template>
  <h1>Title</h1>
  <p>Description</p>
</template>
```

这样，`<h1>` 和 `<p>` 将直接作为兄弟节点出现在 DOM 中，而不是被包裹在一个无用的 `<div>` 中

**为什么 Fragment 很重要？**

1. **更简洁的 DOM**：没有多余的包装元素，可以减少页面的 DOM 深度。
2. **灵活的组件设计**：开发人员可以构建返回多个根节点的组件而不受限于单个根节点的要求，有利于组件的逻辑拆分和重用。
3. **性能优化**：减少了不必要的 DOM 元素，可以提升一些性能、尤其是在 DOM 树比较复杂的应用中。

**注意事项**：虽然 Fragment 为开发带来了许多便利，但在使用过程中仍然需要注意合理的组件结构设计。避免过度使用 Fragment 导致组件结构混乱和难以维护

### 6.2 Teleport

- 什么是Teleport？—— `Teleport` 是一种能够将我们的<strong style="color:#DD5145">组件html结构</strong>移动到指定位置的技术

```vue
<teleport to="移动位置">
<div v-if="isShow" class="mask">
	<div class="dialog">
		<h3>我是一个弹窗</h3>
		<button @click="isShow = false">关闭弹窗</button>
	</div>
</div>
</teleport>
```

案例效果：

![image-20240516003020521](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405170041153.png)

`src\App.vue`：

```vue
<template>
	<div class="app">
		<h3>我是App组件</h3>
		<Child/>
	</div>
</template>

<script>
	import Child from './components/Child'
	export default {
		name:'App',
		components:{Child},
	}
</script>

<style>
	.app{
		background-color: gray;
		padding: 10px;
	}
</style>
```

`src\components\Child.vue`：

```vue
<template>
	<div class="child">
		<h3>我是Child组件</h3>
		<Son/>
	</div>
</template>

<script>
	import Son from './Son'
	export default {
		name:'Child',
		components:{Son},
	}
</script>

<style>
	.child{
		background-color: skyblue;
		padding: 10px;
	}
</style>
```

`src\components\Son.vue`：

```vue
<template>
	<div class="son">
		<h3>我是Son组件</h3>
		<Dialog/>
	</div>
</template>

<script>
	import Dialog from './Dialog.vue'
	export default {
		name:'Son',
		components:{Dialog}
	}
</script>

<style>
	.son{
		background-color: orange;
		padding: 10px;
	}
</style>
```

`src\components\Dialog.vue`：

```vue
<template>
	<div>
		<button @click="isShow = true">点我弹个窗</button>
		<teleport to="body">
			<div v-if="isShow" class="mask">
				<div class="dialog">
					<h3>我是一个弹窗</h3>
					<h4>一些内容</h4>
					<h4>一些内容</h4>
					<h4>一些内容</h4>
					<button @click="isShow = false">关闭弹窗</button>
				</div>
			</div>
		</teleport>
	</div>
</template>

<script>
	import {ref} from 'vue'
	export default {
		name:'Dialog',
		setup(){
			let isShow = ref(false)
			return {isShow}
		}
	}
</script>

<style>
	.mask{
		position: absolute;
		top: 0;bottom: 0;left: 0;right: 0;
		background-color: rgba(0, 0, 0, 0.5);
	}
	.dialog{
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
		text-align: center;
		width: 300px;
		height: 300px;
		background-color: green;
	}
</style>
```



**补充**：在 Vue 3 中，`Teleport` 是一个非常强大的特性，它允许将组件的模板部分渲染到 DOM 中的其他位置，而不是默认的父组件上下文。这对于需要将模态框、工具提示、弹出菜单等 UI 元素插入到特定的 DOM 节点中非常有用，特别是当这些节点在组件树之外时

**基本使用**：`Teleport` 组件通过 `vue` 提供的 `<teleport>` 标签使用。你需要指定一个 `to` 属性，指明要将内容渲染到的 DOM 选择器

```vue
<template>
  <div>
    <h1>这是一个普通的组件</h1>
    <teleport to="#teleport-target">
      <p>这个内容将被传送到 #teleport-target 元素中。</p>
    </teleport>
  </div>
</template>
```

在上述例子中，`<p>这个内容将被传送到 #teleport-target 元素中。</p>` 将被渲染到 `id` 为 `teleport-target` 的 DOM 节点中，而不是在它的父组件上下文中

**完整示例**：以下是一个更完整的示例，包括如何在 HTML 中设置目标元素：

```html
<!-- index.html -->
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Vue Teleport Example</title>
</head>
<body>
  <div id="app"></div>
  <div id="teleport-target"></div>
  <script src="https://unpkg.com/vue@next"></script>
  <script src="path/to/your/component.js"></script>
</body>
</html>
```

`component.js`：

```javascript
// component.js
const { createApp } = Vue;

const App = {
  template: `
    <div>
      <h1>这是一个普通的组件</h1>
      <teleport to="#teleport-target">
        <p>这个内容将被传送到 #teleport-target 元素中。</p>
      </teleport>
    </div>
  `
};

createApp(App).mount('#app');
```

**动态目标**：`Teleport` 组件的 `to` 属性也支持动态绑定，这意味着你可以根据条件或状态将内容传送到不同的目标

```vue
<template>
  <div>
    <button @click="toggle">切换传送目标</button>
    <teleport :to="target">
      <p>这个内容将被传送到 {{ target }} 元素中。</p>
    </teleport>
  </div>
</template>

<script>
export default {
  data() {
    return {
      target: '#teleport-target-1'
    }
  },
  methods: {
    toggle() {
      this.target = this.target === '#teleport-target-1' ? '#teleport-target-2' : '#teleport-target-1';
    }
  }
}
</script>
```

### 6.3  Suspense

- 等待异步组件时渲染一些额外内容，让应用有更好的用户体验

- 使用步骤：

  - 异步引入组件

```js
import {defineAsyncComponent} from 'vue'
const Child = defineAsyncComponent(()=>import('./components/Child.vue'))
```

  - 使用```Suspense```包裹组件，并配置好```default``` 与 ```fallback```

```vue
<template>
	<div class="app">
		<h3>我是App组件</h3>
		<Suspense>
			<template v-slot:default>
				<Child/>
			</template>
			<template v-slot:fallback>
				<h3>加载中.....</h3>
			</template>
		</Suspense>
	</div>
</template>
```

`src\components\Child.vue`：

```vue
<template>
	<div class="child">
		<h3>我是Child组件</h3>
		{{sum}}
	</div>
</template>

<script>
	import {ref} from 'vue'
	export default {
		name:'Child',
		async setup(){
			let sum = ref(0)
			let p = new Promise((resolve,reject)=>{
				setTimeout(()=>{
					resolve({sum})
				},3000)
			})
			return await p
		}
	}
</script>

<style>
	.child{
		background-color: skyblue;
		padding: 10px;
	}
</style>
```

`src\App.vue`：

```vue
<template>
	<div class="app">
		<h3>我是App组件</h3>
		<Suspense>
			<template v-slot:default>
				<Child/>
			</template>
			<template v-slot:fallback>
				<h3>稍等，加载中...</h3>
			</template>
		</Suspense>
	</div>
</template>

<script>
	// import Child from './components/Child'//静态引入
	import {defineAsyncComponent} from 'vue' 
	const Child = defineAsyncComponent(()=>import('./components/Child')) //异步引入
	export default {
		name:'App',
		components:{Child},
	}
</script>

<style>
	.app{
		background-color: gray;
		padding: 10px;
	}
</style>
```

**补充**：在 Vue 3 中，`<Suspense>` 是一个强大的组件，用于处理组件的异步加载状态。`<Suspense>`允许在等待异步任务完成之前，显示一个备用内容（如加载指示器）。这在处理需要从服务器获取数据或者其他耗时操作的组件时特别有用

**基本用法**：`<Suspense>` 组件使用起来非常简单。可以将任何异步组件（使用 `defineAsyncComponent` 定义）放在 `<Suspense>` 组件中，并提供一个备用内容作为 `fallback`

```vue
<template>
  <Suspense>
    <template #default>
      <AsyncComponent />
    </template>
    <template #fallback>
      <div>Loading...</div>
    </template>
  </Suspense>
</template>

<script>
import { defineAsyncComponent } from 'vue';

const AsyncComponent = defineAsyncComponent(() =>
  import('./AsyncComponent.vue')
);

export default {
  components: {
    AsyncComponent
  }
};
</script>
```

**`<Suspense>`工作原理**：

1. **默认插槽 (`#default`)**：用于放置需要异步加载的组件
2. **备用插槽 (`#fallback`)**：用于在异步加载过程中显示的内容

当 `<AsyncComponent />` 正在加载时，`<Suspense>` 会显示 `#fallback` 插槽中的内容。一旦加载完成，显示 `#default` 插槽中的内容

**`<Suspense>`高级用法**：可以结合 `setup` 函数和 `Suspense` 来处理更复杂的异步逻辑。例如，使用 `suspense` API 来控制待处理的 Promise

```vue
<template>
  <Suspense>
    <template #default>
      <div v-if="data">{{ data }}</div>
    </template>
    <template #fallback>
      <div>Loading...</div>
    </template>
  </Suspense>
</template>

<script>
import { ref, defineComponent, h } from 'vue';

export default defineComponent({
  setup() {
    const data = ref(null);

    const fetchData = async () => {
      await new Promise(resolve => setTimeout(resolve, 2000)); // 模拟异步请求
      data.value = 'Fetched Data';
    };

    fetchData();

    return { data };
  }
});
</script>
```

**注意事项**：

- `<Suspense>` 只能在 Vue 3 中使用
- 备用内容 (`#fallback`) 只会在异步组件正在加载时显示，一旦加载完成，备用内容会被卸载
- `<Suspense>` 组件在服务端渲染 (SSR) 中也可以使用，并且提供了额外的控制选项

**总结**：`<Suspense>` 是 Vue 3 中处理异步组件加载的一个非常有用的工具。它简化了异步操作的处理，使应用程序在加载数据时能够提供更好的用户体验。通过合理使用 `<Suspense>`可以确保在数据加载期间为用户提供有意义的反馈

## 7. 其他

### 7.1 全局API的转移

- Vue 2.x 有许多全局 API 和配置
  - 例如：注册全局组件、注册全局指令等

```js
//注册全局组件
Vue.component('MyButton', {
  data: () => ({
	count: 0
  }),
  template: '<button @click="count++">Clicked {{ count }} times.</button>'
})

//注册全局指令
Vue.directive('focus', {
  inserted: el => el.focus()
}
```

- Vue3.0中对这些API做出了调整：

  - 将全局的API，即：```Vue.xxx```调整到应用实例（```app```）上

| 2.x 全局 API（```Vue```） | 3.x 实例 API (`app`)                        |
| ------------------------- | ------------------------------------------- |
| Vue.config.xxxx           | app.config.xxxx                             |
| Vue.config.productionTip  | <strong style="color:#DD5145">移除</strong> |
| Vue.component             | app.component                               |
| Vue.directive             | app.directive                               |
| Vue.mixin                 | app.mixin                                   |
| Vue.use                   | app.use                                     |
| Vue.prototype             | app.config.globalProperties                 |

###  7.2 其他改变

- data选项应始终被声明为一个函数

- 过度类名的更改：

  - Vue2.x写法

```css
.v-enter,
.v-leave-to {
  opacity: 0;
}
.v-leave,
.v-enter-to {
  opacity: 1;
}
```

  - Vue3.x写法

```css
.v-enter-from,
.v-leave-to {
  opacity: 0;
}

.v-leave-from,
.v-enter-to {
  opacity: 1;
}
```

- <strong style="color:#DD5145">移除</strong>keyCode作为 v-on 的修饰符，同时也不再支持```config.keyCodes```

- <strong style="color:#DD5145">移除</strong>```v-on.native```修饰符
- 父组件中绑定事件

```vue
<my-component
  v-on:close="handleComponentEvent"
  v-on:click="handleNativeClickEvent"
/>
```

  - 子组件中声明自定义事件

```vue
<script>
  export default {
	emits: ['close']
  }
</script>
```

- <strong style="color:#DD5145">移除</strong>过滤器（filter）

> 过滤器虽然这看起来很方便，但它需要一个自定义语法，打破大括号内表达式是 “只是 JavaScript” 的假设，这不仅有学习成本，而且有实现成本！建议用方法调用或计算属性去替换过滤器


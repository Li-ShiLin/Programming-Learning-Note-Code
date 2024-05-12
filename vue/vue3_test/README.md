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
- [8.生命周期](#8%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)

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

## 8.生命周期


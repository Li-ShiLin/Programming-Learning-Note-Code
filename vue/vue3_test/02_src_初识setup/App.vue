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

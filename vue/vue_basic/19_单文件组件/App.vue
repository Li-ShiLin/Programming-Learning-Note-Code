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

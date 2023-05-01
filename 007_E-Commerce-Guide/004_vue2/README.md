**目录导航：**

<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [2.vue](#2vue)
  * [2.1 MVVM思想](#21-mvvm)
  * [2.2 Vue简介](#22-vue)
  * [2.3 vue基本语法](#23-vue)
    + [1.项目创建](#1)
    + [2.vue声明式渲染](#2vue-1)
    + [3.vue双向绑定](#3vue)
    + [4.事件处理](#4)
    + [5.声明方法](#5)
  * [2.4 vue指令](#24-vue)
    + [1.插值表达式](#1-1)
    + [2.v-bind指令](#2v-bind)
    + [3.v-model指令](#3v-model)
    + [4.v-on指令](#4v-on)
    + [5.v-for指令](#5v-for)
    + [6.v-if指令和v-show指令](#6v-ifv-show)
    + [7.v-else指令和v-else-if指令](#7v-elsev-else-if)
    + [8.计算属性和侦听器](#8)
    + [9.过滤器](#9)
  * [2.5 组件化](#25-)
  * [2.6 生命周期钩子函数](#26-)

<!-- TOC end -->

<!-- TOC --><a name="2vue"></a>

## 2.vue

<!-- TOC --><a name="21-mvvm"></a>

### 2.1 MVVM思想

- M:即Model，模型，包括数据和一些基本操作
- v:即 view，视图，页面渲染结果
- VM:即 View-Model，模型与视图间的双向操作（无需开发人员干涉)



在MWM之前，开发人员从后端获取需要的数据模型，然后要通过DOM操作Model渲染到view中。而后当用户操作视图，我们还需要通过DOM获取View中的数据，然后同步到Model中

而MVVM中的VM要做的事情就是把DOM操作完全封装起来，开发人员不用再关心Model和View之间是如何互相影响的:

- 只要我们Model发生了改变，View 上自然就会表现出来
- 当用户修改了View，Model中的数据也会跟着改变。

把开发人员从繁琐的DOM操作中解放出来，把关注点放在如何操作 Model上

![image-20230320010040606](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303200100882.png)

<!-- TOC --><a name="22-vue"></a>
### 2.2 Vue简介

Vue (读音/vju:/,类似于view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue也完全能够为复杂的单页应用提供驱动

官网: https://cn.vuejs.org/

参考: https://cn.vuejs.org/v2/guide/

Git地址: https://github.com/vuejs

<!-- TOC --><a name="23-vue"></a>
### 2.3 vue基本语法

<!-- TOC --><a name="1"></a>
####  1.项目创建

创建`004_vue`,在该目录下执行`npm init -y`初始化项目，执行`npm install vue`下载vue（默认下载最新版，此教程中vue的版本为2.6.10）

![image-20230320233530856](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220136044.png)

<!-- TOC --><a name="2vue-1"></a>
#### 2.vue声明式渲染

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <h1>{{name}},非常帅</h1>
    </div>

    <!-- 引入vue.js -->
    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        // 1.声明式渲染
        // 声明Vue对象vm来管控div
        let vm = new Vue({
            // 用id选择器管控div
            el: "#app",

            data: {
                name: "张三"
            }
        });
    </script>
</body>
</html>
```

<!-- TOC --><a name="3vue"></a>
#### 3.vue双向绑定

- 双向绑定：模型变化会引起视图变化，视图变化会引起模型变化

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div id="app">
        <!-- 将输入框与vue中的数据模型num绑定 -->
        <!-- v-model指令实现双向绑定 -->
        <input type="text" v-model="num">  
        <h1>{{name}},非常帅。有{{num}}个人为他点赞</h1>
    </div>

    <!-- 引入vue.js -->
    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        // 1.双向绑定：模型变化会引起视图变化，视图变化会引起模型变化
        let vm = new Vue({
            el:"#app",
            data:{
                name:"张三",
                num:1
            }
        });
    </script>
</body>
</html>
```

浏览器效果：

![image-20230321203538170](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220136220.png)

<!-- TOC --><a name="4"></a>
#### 4.事件处理

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div id="app">
        <!-- 将输入框与vue中的数据模型num绑定 -->
        <input type="text" v-model="num">

        <!-- 事件绑定  用v-on:click指令绑定单击事件 -->
        <button v-on:click="num++">点赞</button>
        <h1>{{name}},非常帅。有{{num}}个人为他点赞</h1>
    </div>

    <!-- 引入vue.js -->
    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        // 1.双向绑定：模型变化会引起视图变化，视图变化会引起模型变化
        let vm = new Vue({
            el:"#app",
            data:{
                name:"张三",
                num:1
            }
        });
    </script>
</body>
</html>
```

<!-- TOC --><a name="5"></a>
####  5.声明方法

声明方法来做更复杂的操作

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <!-- 将输入框与vue中的数据模型num绑定 -->
        <input type="text" v-model="num">

        <!-- 事件绑定  用v-on:click指令绑定单击事件 -->
        <button v-on:click="num++">点赞</button>

        <button v-on:click="cancel">取消点赞</button>
        <h1>{{name}},非常帅。有{{num}}个人为他点赞</h1>
    </div>

    <!-- 引入vue.js -->
    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        // 1.双向绑定：模型变化会引起视图变化，视图变化会引起模型变化
        let vm = new Vue({
            el: "#app", //绑定元素
            data: {   //封装数据
                name: "张三",
                num: 1
            },
            methods: { //封装方法
                cancel() {
                    this.num--;
                }
            }
        });

    // 总结：
    // 2、双向绑定,模型变化，视图变化。反之亦然。
    // 3、事件处理
    // v-xx:指令
    // 1、创建vue实例，关联页面的模板，将自己的数据（data）渲染到关联的模板，响应式的
    // 2、指令来简化对dom的一些操作
    // 3、声明方法来做更复杂的操作.methods里面可以封装方法
    //   el:   绑定元素
    //   data: 封装数据
    //   methods: 封装方法
    </script>
</body>

</html>
```



![image-20230321210448433](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220136173.png)



<!-- TOC --><a name="24-vue"></a>
### 2.4 vue指令

<!-- TOC --><a name="1-1"></a>
#### 1.插值表达式

**1）花括号**

- 格式:{{表达式}}
- 说明:
  - 该表达式支持JS语法，可以调用js内置函数（必须有返回值)
  - 表达式必须有返回结果。例如1+1，没有结果的表达式不允许使用，如: let a =1+ 1
  - 可以直接获取Vue实例中定义的数据或函数
  - 插值表达式必须写在标签体里

**2）插值闪烁**

使用`{{}}}`方式在网速较慢时会出现问题。在数据未加载完成时，页面会显示出原始的``{{}}}``，加载完毕后才显示正确数据，我们称为插值闪烁

**3）代码：**

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <!-- 显示  <h1>hello</h1>  -->
        {{msg}} <br />

        <!-- hello -->
        <span v-html="msg"></span><br />

        <!-- 显示  <h1>hello</h1>  -->
        <span v-text="msg"></span><br />

        {{hello()}}<br />
    </div>

    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        new Vue({
            el: "#app",
            data: {
                msg: "<h1>hello</h1>"
            },
            methods: {
                hello() {
                    return "World";
                }
            }
        });
    </script>
</body>
</html>
```

<!-- TOC --><a name="2v-bind"></a>
#### 2.v-bind指令

v-bind指令的作用：动态地绑定一个或多个特性。： 后的为传递的参数

表示把model绑定到view。可以设置src、title、class等

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <!-- 给html标签的属性绑定 -->
    <div id="app">
        <a v-bind:href="link">gogo</a>

        <!-- v-bind 对class和style做增强   {class名：加上？} -->
        <span v-bind:class="{active:isActive,'text-danger':hasError}" :style="{color: color1,fontSize: size}">你好</span>

    </div>

    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        let vm = new Vue({
            el: "#app",
            data: {
                link: "http://www.baidu.com",
                isActive: true,
                hasError: true,
                color1: 'red',
                size: '36px'
            }
        })
    </script>
</body>
</html>
```

<!-- TOC --><a name="3v-model"></a>
#### 3.v-model指令

双向绑定v-model：v-bind只能从model到view。v-model能从view到model

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <!-- 双向绑定 -->
    <!-- 表单项，自定义组件 -->
    <div id="app">

        精通的语言：
        <!-- 多选框checkbox 中选择的内容会被加到 language: [] 数组中 -->
        <input type="checkbox" v-model="language" value="Java"> java<br />
        <input type="checkbox" v-model="language" value="PHP"> PHP<br />
        <input type="checkbox" v-model="language" value="Python"> Python<br />
        <!-- 实时显示选中的数据，展示language数组： -->
        选中了 {{language.join(",")}}
    </div>

    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        let vm = new Vue({
            el: "#app",
            data: {
                language: []
            }
        })
    </script>
</body>
</html>
```

<!-- TOC --><a name="4v-on"></a>
#### 4.v-on指令

**1.事件修饰符**

在事件处理程序中调用`event.preventDefault()`或`event.stopPropagation()`是非常常见的需求。尽管我们可以在方法中轻松实现这点，但更好的方式是:方法只有纯粹的数据逻辑，而不是去处理DOM事件细节

为了解决这个间题，Vue.js 为`v-on`提供了事件修饰符。修饰符是由点开头的指令后缀来表示的

- `.stop`: 阻止事件冒泡到父元素
- `.prevent`: 阻止默认事件发生
- `.capture`: 使用事件捕获模式
- `.self`:只有元素自身触发事件才执行。（冒泡或捕获的都不执行)
- `.once`:只执行一次



**2.按键修饰符**
在监听键盘事件时，我们经常需要检查常见的键值。Vue 允许为`v-on`在监听键盘事件时添加按键修饰符:
<!--只有在`keyCode`是13时调用`vm.submit()`-->

![image-20230322003211634](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220137430.png)

记住所有的`keyCode`比较困难，所以 Vue为最常用的按键提供了别名:

![image-20230322003300846](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220137578.png)

全部的按键别名:

- `.enter` 
- `.tab.delete` (捕获“删除”和“退格"键)
- `.esc`
- ``.space`
- `.up.down`
- ``.left`
- `.right`

**3.组合按钮**

可以用如下修饰符来实现仅在按下相应按键时才触发鼠标或键盘事件的监听器

- `.ctrl`
- `.alt`
- `.shift`

![image-20230322003925381](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220137529.png)

![image-20230322003943545](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220137152.png)

**v-on指令代码：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div id="app">
                
        <!--事件中直接写js片段-->
        <button v-on:click="num++">点赞</button>
        <!--事件指定一个回调函数，必须是Vue实例中定义的函数-->
        <button @click="cancle">取消</button>
        <!--  -->
        <h1>有{{num}}个赞</h1>


        <!-- 事件修饰符 -->
        <div style="border: 1px solid red;padding: 20px;" v-on:click.once="hello">
            大div
            <div style="border: 1px solid blue;padding: 20px;" @click.stop="hello">
                小div <br />
                <a href="http://www.baidu.com" @click.prevent.stop="hello">去百度</a>
            </div>
        </div>

        <!-- 按键修饰符： -->
        <input type="text" v-model="num" v-on:keyup.up="num+=2" @keyup.down="num-=2" @click.ctrl="num=10"><br />

        提示：

    </div>
    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>
        new Vue({
            el: "#app",
            data: {
                num: 1
            },
            methods: {
                cancle() {
                    this.num--;
                },
                hello() {
                    alert("点击了")
                }
            }
        })
    </script>
</body>
</html>
```

<!-- TOC --><a name="5v-for"></a>
#### 5.v-for指令

v-for指令用来遍历数组或对象：

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <ul>
            <li v-for="(user,index) in users" :key="user.name" v-if="user.gender == '女'">
                <!-- 1、显示user信息：v-for="item in items" -->
                当前索引：{{index}} ==> {{user.name}} ==> {{user.gender}} ==>{{user.age}} <br>
                <!-- 2、获取数组下标：v-for="(item,index) in items" -->
                <!-- 3、遍历对象：
                        v-for="value in object"
                        v-for="(value,key) in object"
                        v-for="(value,key,index) in object" 
                -->
                对象信息：
                <span v-for="(v,k,i) in user">{{k}}=={{v}}=={{i}}；</span>
                <!-- 4、遍历的时候都加上:key来区分不同数据，提高vue渲染效率 -->
            </li>
        </ul>
        <ul>
            <li v-for="(num,index) in nums" :key="index"></li>
        </ul>
    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>
    <script>         
        let app = new Vue({
            el: "#app",
            data: {
                users: [{ name: '柳岩', gender: '女', age: 21 },
                { name: '张三', gender: '男', age: 18 },
                { name: '范冰冰', gender: '女', age: 24 },
                { name: '刘亦菲', gender: '女', age: 18 },
                { name: '古力娜扎', gender: '女', age: 25 }],
                nums: [1, 2, 3, 4, 4]
            },
        })
    </script>
</body>

</html>
```

<!-- TOC --><a name="6v-ifv-show"></a>
#### 6.v-if指令和v-show指令

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <!-- 
        v-if，顾名思义，条件判断。当得到结果为true时，所在的元素才会被渲染。
        v-show，当得到结果为true时，所在的元素才会被显示。 
    -->
    <div id="app">
        <button v-on:click="show = !show">点我呀</button>
        <!-- 1、使用v-if显示 -->
        <h1 v-if="show">if=看到我....</h1>
        <!-- 2、使用v-show显示 -->
        <h1 v-show="show">show=看到我</h1>
    </div>

    <script src="./node_modules/vue/dist/vue.js"></script>
        
    <script>
        let app = new Vue({
            el: "#app",
            data: {
                show: true
            }
        })
    </script>
</body>
</html>
```

<!-- TOC --><a name="7v-elsev-else-if"></a>
#### 7.v-else指令和v-else-if指令

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <button v-on:click="random=Math.random()">点我呀</button>
        <span>{{random}}</span>

        <h1 v-if="random>=0.75">
            看到我啦？！ &gt;= 0.75
        </h1>

        <h1 v-else-if="random>=0.5">
            看到我啦？！ &gt;= 0.5
        </h1>

        <h1 v-else-if="random>=0.2">
            看到我啦？！ &gt;= 0.2
        </h1>

        <h1 v-else>
            看到我啦？！ &lt; 0.2
        </h1>
    </div>
    <script src="./node_modules/vue/dist/vue.js"></script>
        
    <script>         
        let app = new Vue({
            el: "#app",
            data: { random: 0.70 }
        })     
    </script>
</body>
</html>
```

<!-- TOC --><a name="8"></a>
####  8.计算属性和侦听器

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <div id="app">
        <!-- 某些结果是基于之前数据实时计算出来的，我们可以利用计算属性。来完成 -->
        <ul>
            <li>西游记； 价格：{{xyjPrice}}，数量：<input type="number" v-model="xyjNum"> </li>
            <li>水浒传； 价格：{{shzPrice}}，数量：<input type="number" v-model="shzNum"> </li>
            <li>总价：{{totalPrice}}</li>
            {{msg}}
        </ul>
    </div>
    <script src="../node_modules/vue/dist/vue.js"></script>

    <script>
        //watch可以让我们监控一个值的变化。从而做出相应的反应。
        new Vue({
            el: "#app",
            data: {
                xyjPrice: 99.98,
                shzPrice: 98.00,
                xyjNum: 1,
                shzNum: 1,
                msg: ""
            },
            // 计算属性：
            computed: {
                totalPrice(){
                    return this.xyjPrice*this.xyjNum + this.shzPrice*this.shzNum
                }
            },
            // 侦听器：
            watch: {
                xyjNum(newVal,oldVal){
                    if(newVal>=3){
                        this.msg = "库存超出限制";
                        this.xyjNum = 3
                    }else{
                        this.msg = "";
                    }
                }
            },
        })
    </script>
</body>
</html>
```

<!-- TOC --><a name="9"></a>
#### 9.过滤器

定义filter组件后，管道符后面跟具体过滤器`{{user.gender | gFilter}}`

```html
<!DOCTYPE html>
<html lang="en"
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <!-- 过滤器常用来处理文本格式化的操作。过滤器可以用在两个地方：双花括号插值和 v-bind 表达式 -->
    <div id="app">
        <ul>
            <li v-for="user in userList">
                {{user.id}} ==> {{user.name}} ==> {{user.gender == 1?"男":"女"}} ==>
                {{user.gender | genderFilter}} ==> {{user.gender | gFilter}}
            </li>
        </ul>
    </div>
    <script src="./node_modules/vue/dist/vue.js"></script>

    <script>

        Vue.filter("gFilter", function (val) {
            if (val == 1) {
                return "男~~~";
            } else {
                return "女~~~";
            }
        })

        let vm = new Vue({
            el: "#app",
            data: {
                userList: [
                    { id: 1, name: 'jacky', gender: 1 },
                    { id: 2, name: 'peter', gender: 0 }
                ]
            },
            filters: {
                //// filters 定义局部过滤器，只可以在当前vue实例中使用
                genderFilter(val) {
                    if (val == 1) {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            }
        })
    </script>
</body>
</html>
```

<!-- TOC --><a name="25-"></a>
### 2.5 组件化

在大型应用开发的时候，页面可以划分成很多部分。往往不同的页面，也会有相同的部分。例如可能会有相同的头部导航

但是如果每个页面都独自开发，这无疑增加了我们开发的成本。所以我们会把页面的不同部分拆分成独立的组件，然后在不同页面就**可以共享这些组件，避免重复开发**。组件化：抽取、复用

在vue里，所有的vue,实例都是组件



![image-20230322012022930](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303220137910.png)



- 组件其实也是一个vue实例，因此它在定义时也会接收：data、methods、生命周期函数等
- 不同的是组件不会与页面的元素绑定（所以不写el），否则就无法复用了，因此没有el属性
- 但是组件渲染需要html模板，所以增加了template属性，值就是HTML模板
- data必须是一个函数，不再是一个对象
- 全局组件定义完毕，任何vue实例都可以直接在HTML中通过组件名称来使用组件了



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>

    <div id="app">
        <button v-on:click="count++">我被点击了 {{count}} 次</button>

        <counter></counter>
        <counter></counter>
        <counter></counter>
        <counter></counter>
        <counter></counter>

        <button-counter></button-counter>
    </div>
    <script src="./node_modules/vue/dist/vue.js"></script>
    <script>
        //1、全局声明注册一个组件
        Vue.component("counter", {
            template: `<button v-on:click="count++">我被点击了 {{count}} 次</button>`,
            data() {
                return {
                    count: 1
                }
            }
        });

        //2、局部声明一个组件
        const buttonCounter = {
            template: `<button v-on:click="count++">我被点击了 {{count}} 次~~~</button>`,
            data() {
                return {
                    count: 1
                }
            }
        };

        new Vue({
            el: "#app",
            data: {
                count: 1
            },
            components: {
                'button-counter': buttonCounter
            }
        })
    </script>
</body>

</html>
```

<!-- TOC --><a name="26-"></a>
### 2.6 生命周期钩子函数

**1.生命周期**

每个 vue实例在被创建时都要经过一系列的初始化过程∶创建实例，装载模板，渲染模板等等。Vue为生命周期中的每个状态都设置了钩子函数（监听函数）。每当vue,实例处于不同的生命周期时，对应的函数就会被触发调用

生命周期:你不需要立马弄明白所有的东西

![生命周期](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303261833520.png)



**生命周期钩子函数**

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div id="app">
        <span id="num">{{num}}</span>
        <button @click="num++">赞！</button>
        <h2>{{name}}，有{{num}}个人点赞</h2>
    </div>

    <script src="./node_modules/vue/dist/vue.js"></script>
    
    <script>
        let app = new Vue({
            el: "#app",
            data: {
                name: "张三",
                num: 100
            },
            methods: {
                show() {
                    return this.name;
                },
                add() {
                    this.num++;
                }
            },
            beforeCreate() {
                console.log("=========beforeCreate=============");
                console.log("数据模型未加载：" + this.name, this.num);
                console.log("方法未加载：" + this.show());
                console.log("html模板未加载：" + document.getElementById("num"));
            },
            created: function () {
                console.log("=========created=============");
                console.log("数据模型已加载：" + this.name, this.num);
                console.log("方法已加载：" + this.show());
                console.log("html模板已加载：" + document.getElementById("num"));
                console.log("html模板未渲染：" + document.getElementById("num").innerText);
            },
            beforeMount() {
                console.log("=========beforeMount=============");
                console.log("html模板未渲染：" + document.getElementById("num").innerText);
            },
            mounted() {
                console.log("=========mounted=============");
                console.log("html模板已渲染：" + document.getElementById("num").innerText);
            },
            beforeUpdate() {
                console.log("=========beforeUpdate=============");
                console.log("数据模型已更新：" + this.num);
                console.log("html模板未更新：" + document.getElementById("num").innerText);
            },
            updated() {
                console.log("=========updated=============");
                console.log("数据模型已更新：" + this.num);
                console.log("html模板已更新：" + document.getElementById("num").innerText);
            }
        });
    </script>
</body>
</html>
```

## 前端开发基础知识&快速入门

前后端技术栈对比：

![image-20230318212308059](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303200101214.png)



## 1.ES6

### 1.1 ES6简介

ECMAScript 6.0（以下简称ES6，ECMAScript是一种由Ecma国际（前身为欧洲计算机制造商协会,英文名称是European Computer Manufacturers Association) 通过ECMA-262标准化的脚本程序设计语言，是JavaScript语言的下一代标准，已经在2015年6月正式发布了，并且从 ECMAScript 6开始，开始采用年号来做版本。即 ECMAScript 2015，就是ECMAScript6。它的目标,是使得 JavaScript语言可以用来编写复杂的大型应用程序,成为企业级开发语言。每年一个新版本：

![image-20230318212603828](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303200101095.png)

### 1.2 ECMAScript简介

来看下**前端的发展历程:**

**web1.0时代:**

- 最初的网页以HTML为主，是纯静态的网页。网页是只读的，信息流只能从服务的到客户端单向流通。开发人员也只关心页面的样式和内容即可

**web2.0时代:**

- 1995年，网景工程师 Brendan Eich花了10天时间设计了JavaScript 语言。- 1996年，微软发布了JScript，其实是JavaScript 的逆向工程实现。
- 1996年11月，JavaScript 的创造者Netscape公司，决定将JavaScript 提交给标准化组织ECMA，希望这种语言能够成为国际标准。
- 1997年，ECMA发布 262号标准文件(ECMA-262）的第一版，规定了浏览器脚本语言的标准，并将这种语言称为ECMAScript，这个版本就是1.0 版。JavaScript和 IScript,都是`ECMAScript`的标准实现者，随后各大浏览器厂商纷纷实现了`ECMAScript`标准

所以，**ECMAScript是浏览器脚本语言的规范，而各种我们熟知的js,语言，如JavaScript则是规范的具体实现**



### 1.3 ES6新特性

#### 1.3.1 let声明变量

- let 声明的变量有严格局部作用域   （ 对比： var 声明的变量往往会越域）
- let 不存在变量提升   （对比： var 可以声明多次）
- let 只能声明一次    （对比：var 会变量提升）

打开VSCode—打开文件夹—新建es6文件夹—新建文件1、let.html—`shift+!+Enter`生成html模板。填入下面内容后，右键open with live server 。 在Windows中，**vscode**格式化代码**快捷键**是“Shift+Alt+F”（用于调整代码）

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
    <script>
        // var 声明的变量往往会越域
        // let 声明的变量有严格局部作用域
          {
              var a = 1;
              let b = 2;
          }
          console.log(a);  // 1
          console.log(b);  // ReferenceError: b is not defined
 
          // var 可以声明多次
          // let 只能声明一次
          var m = 1
          var m = 2
          let n = 3
 //         let n = 4
          console.log(m)  // 2
          console.log(n)  // Identifier 'n' has already been declared
 
         // var 会变量提升
         // let 不存在变量提升
          console.log(x);  // undefined
          var x = 10;
          console.log(y);   //ReferenceError: y is not defined
          let y = 20;
 

         // 1. const声明之后不允许改变
            // 2. 一但声明必须初始化，否则会报错
         const a = 1;
         a = 3; //Uncaught TypeError: Assignment to constant variable.
     
     </script>
</body>
</html>
```

#### 1.3.2 const声明常量

- const声明之后不允许改变，一但声明必须初始化，否则会报错

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
    <script>
         // 1. const声明之后不允许改变
            // 2. 一但声明必须初始化，否则会报错
         const a = 1;
         a = 3; //Uncaught TypeError: Assignment to constant variable.
     
     </script>
</body>
</html>
```

#### 1.3.3 解构表达式

- 数组解构  \   对象解构

- 支持`let arr = [1,2,3]; let [a,b,c] = arr;`这种语法
- 支持对象解析：`const { name: abc, age, language } = person;` 冒号代表改名，`旧:新`
- 字符串函数
- 支持一个字符串为多行``
- 占位符功能 ${}

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
    <script>
        // 通过角标获取
        let arr = [1, 2, 3];
        let a = arr[0];
        let b = arr[1];
        let c = arr[2];
        console.log(a, b, c);

        // 1.数组解构
        // 以前我们想要获取其中的值，只能通过角标，ES6可以这样：
        const [x, y, z] = arr // x , y ,z将与arr中的每个位置对应来取值
        // 打印：
        console.log(x, y, z);  // 1 2 3


        // 2.对象解构
        const person = {
            name: "jack",
            age: 21,
            language: ['java', 'js', 'css']
        }
        // 解构表达式获取值，将person里面的每一个属性和左边对应赋值
        const { name, age, language } = person;
        // 等价于下面：
        // const name = person.name;
        // const age = person.age;
        // const language = person.language;
        // 可以分别打印：
        console.log(name);
        console.log(age);
        console.log(language);

        // 拓展：如果想要将name的值赋值给其他变量，可以如下,nn是新的变量名
        // const { name: nn, age, language } = person;
        // console.log(nn);
        // console.log(age);
        // console.log(language);
    </script>
</body>
</html>
```

#### 1.3.4 字符串扩展

**1.新的api**

ES6为字符串扩展了几个新的API:

`includes()`:  返回布尔值,表示是否找到了参数字符串

`startsWith()`:  返回布尔值，表示参数字符串是否在原字符串的头部

`endsWith()`: 返回布尔值,表示参数字符串是否在原字符串的尾部

```html
        let str = "hello.vue";
        // 新的api
        console.log(str.startsWith("hello")); // true
        console.log(str.endsWith(".vue")); // true
        console.log(str.includes("e")); // true
        console.log(str.includes("hello")); // true
```

**2.字符串模板**

- 模板字符串相当于加强版的字符串,用反引号`将字符串包围起来

- 除了作为普通字符串，还可以用来定义多行字符串，还可以在字符串中加入变量和表达式

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
    <script>
        // 字符串扩展：
        let str = "hello.vue";
        // 1、新的api
        console.log(str.startsWith("hello")); // true
        console.log(str.endsWith(".vue")); // true
        console.log(str.includes("e")); // true
        console.log(str.includes("hello")); // true

        // 2、字符串模板
        // 2.1 多行字符串
        let ss = `
              <div>
                   <span>hello world</span>
              </div>  
              `
        console.log(ss);
        
        // 2.2 字符串插入变量和表达式
        // 变量名写在${}中，${}中可以放入Javascript表达式
        let name = "张三";
        let age = 18;
        let info = `我是${name}.今年${age}了`;
        console.log(name);
        console.log(age);
        console.log(info);

        function fun(){
            return "这是一个函数";
        }

        let info_02 = `我是${name}.今年${age}了.我想说：${fun()}`;
        console.log(info_02);

    </script>
</body>
</html>
```

####  1.3.5 函数优化

**1.函数参数默认值**

```html
 <script>
        //在ES6以前，我们无法给一个函数参数设置默认值，只能采用变通写法：
        function add(a, b) {
            // 判断b是否为空，为空就给默认值1
            b = b || 1;
            return a + b;
        }
        // 传一个参数
        console.log(add(10));


        //现在可以这么写：直接给参数写上默认值，没传就会自动使用默认值
        function add2(a, b = 1) {
            return a + b;
        }
        console.log(add2(20));
    </script>
```

**2.不定参数**

```html
    <script>
        //2）、不定参数
        function fun(...values) {
            console.log(values.length)
        }
        fun(1, 2)      //2
        fun(1, 2, 3, 4)  //4
    </script>
```

**3.箭头函数**

```html
    <script>
        //3）、箭头函数。lambda
        //以前声明一个方法
        // var print = function (obj) {
        //     console.log(obj);
        // }
        // 现在：
        var print = obj => console.log(obj);
        print("hello");  // hell0

        var sum = function (a, b) {
            c = a + b;
            return a + c;
        }

        var sum2 = (a, b) => a + b;
        console.log(sum2(11, 12)); // 23

        var sum3 = (a, b) => {
            c = a + b;
            return a + c;
        }
        console.log(sum3(10, 20)); // 40


        const person = {
            name: "jack",
            age: 21,
            language: ['java', 'js', 'css']
        }

        function hello(person) {
            console.log("hello," + person.name)
        }

        var hello2 = (param) => console.log("hello," + param.name);// hello,jack
        hello2(person);

        //箭头函数+解构
        var hello3 = ({name}) => console.log("hello," + name);// hello,jack
        hello3(person);
    </script>
```

#### 1.3.6 对象优化

**1.新增api:**

ES6给object 拓展了许多新的方法，如:

- `keys(obj):`获取对象的所有key形成的数组

- `values(obj):`获取对象的所有value形成的数组

- `entries(obj):`获取对象的所有key和 value形成的二维数组。格式:`[[k1,v1],[k2,v2]..]`

- `assign(dest,..src):`将多个src,对象的值拷贝到 dest中。(第一层为深拷贝，第二层为浅拷贝)

```html
    <script>
        const person = {
            name: "jack",
            age: 21,
            language: ['java', 'js', 'css']
        }
        console.log(Object.keys(person)); // ["name","age","language"]
        console.log(Object.values(person)); // ["jack",21,Array(3)]
        console.log(Object.entries(person));// [Array(2),Array(2),Array(2)]


        const target = { a: 1 };
        const source1 = { b: 2 };
        const source2 = { c: 3 };
        // Object.assign 方法的第一个参数是目标对象，后面的参数都是源对象
        Object.assign(target, source1, source2);
        console.log(target); // {a: 1, b: 2, c: 3}
    </script>
```

**2.声明对象简写**

```html
    <script>
        // 1.声明对象简写
        const age = 23;
        const name = "张三";
        // 传统
        const person = { age: age, name: name }
        console.log(person); // {age:23 ,name: "张三"}


        // ES6: 属性名和属性值变量名一样，可以省略
        const person2 = { age, name }
        console.log(person2); // {age:23 ,name: "张三"}

        // 2.对象的函数属性简写
        let person3 = {
            name: "jack",
            // 以前：
            eat: function (food) {
                console.log(this.name + "在吃" + food);
            },
            // 箭头函数不能使用this，必须使用 对象.属性
            eat2: food => console.log(this.name + "在吃" + food),
            // 箭头函数必须使用 对象.属性
            eat3: food => console.log(person3.name + "在吃" + food),
            eat4(food) {
                console.log(this.name + "在吃" + food)
            }
        }
        person3.eat("香蕉"); // jack在吃香蕉

        person3.eat2("苹果"); // 在吃苹果

        person3.eat3("草莓"); // jack在吃草莓

        person3.eat4("桃子"); // jack在吃桃子
    </script>
```

**3.对象拓展运算符**

拓展运算符(...) ： 用于取出参数对象所有可遍历属性然后拷贝到当前对象

```html
    <script>
        // 1、拷贝对象（深拷贝）
        let p1 = { name: "Amy", age: 15 }
        let someone = { ...p1 }
        console.log(someone)  //{name: "Amy", age: 15}

        // 2、合并对象
        let age1 = { age: 18 }
        let name1 = { name: "lisi" }
        let p2 = { name: "zhangsan" }
        p2 = { ...age1, ...name1 } // 如果两个对象的字段名重复，后面对象字段值会覆盖前面对象的字段值
        console.log(p2) //{age: 18, name: "harvey"}
    </script>
```

####  1.3.7 map和reduce

**map:**

- `map()`：接收一个函数，将原数组中的所有元素用这个函数处理后放入新数组返回

```html
    <!-- 数组中新增了map和reduce方法 -->
    <script>
        // map():接收一个函数，将原数组中的所有元素用这个函数处理后放入到新数组返回
        let arr = ['1', '20', '5', '3'];
        // arr = arr.map((item) => {
        //     return item * 2;
        // });
        // 简写:
        arr = arr.map((item) => item * 2); // 2 ,40 ,10 ,6

        console.log(arr);
    </script>
```

**reduce:**

- 语法:
  - arr.reduce(callback, [initialValue])
- reduce为数组中的每一个元素依次执行回调函数，不包括数组中被删除或从未被赋值的元素，接受四个参数
- 初始值（或者上一次回调函数的返回值)，当前元素值，当前索引，调用reduce的数组
- callback(执行数组中每个值的函数，包含四个参数)
  - 1、previousValue 上一次调用回调返回的值，或者是提供的初始值（initialMalue）
  - 2、currentValue（数组中当前被处理的元素)
  - 3、index（当前元素在数组中的索引)
  - 4 、array (调用reduce的数组)

```html
    <!-- 数组中新增了map和reduce方法 -->
    <script>
        // map():接收一个函数，将原数组中的所有元素用这个函数处理后放入到新数组返回
        let arr = ['1', '20', '5', '3'];
        // arr = arr.map((item) => {
        //     return item * 2;
        // });
        // 简写:
        arr = arr.map((item) => item * 2); // 2 ,40 ,10 ,6

        console.log(arr);

        //reduce()为数组中的每一个元素依次执行回调函数，不包括数组中被删除或从未被赋值的元素，
       //[2, 40, -10, 6]
        /**
            1、previousValue （上一次调用回调返回的值，或者是提供的初始值（initialValue））
            2、currentValue （数组中当前被处理的元素）
            3、index （当前元素在数组中的索引）
            4、array （调用 reduce 的数组）*/
        let result = arr.reduce((a, b) => {
            console.log("上一次处理后：" + a);
            console.log("当前正在处理：" + b);
            return a + b;
        });
        console.log(result)
    </script>
```



![image-20230319185304377](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303191856683.png)

#### 1.3.8 Promise异步编排

在JavaScript的世界中，所有代码都是单线程执行的。由于这个"缺陷”，导致JavaScript的所有网络操作，浏览器事件，都必须是异步执行。异步执行可以用回调函数实现。一旦有一连串的 ajax请求a,b,c,d... 后面的请求依赖前面的请求结果，就需要层层嵌套。这种缩进和层层嵌套的方式,非常容易造成上下文代码混乱，我们不得不非常小心翼翼处理内层函数与外层函数的数据，一旦内层函数使用了上层函数的变量，这种混乱程度就会加剧。总之，这种**层叠上下文**的层层嵌套方式，着实增加了神经的紧张程度

案例:用户登录，并展示该用户的各科成绩。在页面发送两次请求:

- 1.查询用户，查询成功说明可以登录
- 2.查询用户成功，查询科目
- 3．根据科目的查询结果,获取去成绩

分析:此时后台应该提供三个接口，一个提供用户查询接口，一个提供科目的接口，一个提供各科成绩的接口，为了渲染方便，最好响应json数据。在这里就不编写后台接口了，而是提供三个json文件,直接提供json数据，模拟后台接口:

**corse_score_10.json 得分**

```json
{
    "id": 100,
    "score": 90
}
```

**user.json 用户**

```json
{
    "id": 1,
    "name": "zhangsan",
    "password": "123456"
}
```

**user_corse_1.json 课程**

```json
{
    "id": 10,
    "name": "chinese"
}
```

**Promise改造以前嵌套方式:**

```html
    <script>
        //1、查出当前用户信息
        //2、按照当前用户的id查出他的课程
        //3、按照当前课程id查出分数
        $.ajax({
            url: "mock/user.json",
            success(data) {
                console.log("查询用户：", data);
                $.ajax({
                    url: `mock/user_corse_${data.id}.json`,
                    success(data) {
                        console.log("查询到课程：", data);
                        $.ajax({
                            url: `mock/corse_score_${data.id}.json`,
                            success(data) {
                                console.log("查询到分数：", data);
                            },
                            error(error) {
                                console.log("出现异常了：" + error);
                            }
                        });
                    },
                    error(error) {
                        console.log("出现异常了：" + error);
                    }
                });
            },
            error(error) {
                console.log("出现异常了：" + error);
            }
        });
    </script>
```

**Promise优化处理**

```html
    <script>
        //1、查出当前用户信息
        //2、按照当前用户的id查出他的课程
        //3、按照当前课程id查出分数
        //1、Promise可以封装异步操作
        // let p = new Promise((resolve, reject) => {
        //     //1、异步操作
        //     $.ajax({
        //         url: "mock/user.json",
        //         success: function (data) {
        //             console.log("查询用户成功:", data)
        //             resolve(data);
        //         },
        //         error: function (err) {
        //             reject(err);
        //         }
        //     });
        // });

        // p.then((obj) => {
        //     return new Promise((resolve, reject) => {
        //         $.ajax({
        //             url: `mock/user_corse_${obj.id}.json`,
        //             success: function (data) {
        //                 console.log("查询用户课程成功:", data)
        //                 resolve(data);
        //             },
        //             error: function (err) {
        //                 reject(err)
        //             }
        //         });
        //     })
        // }).then((data) => {
        //     console.log("上一步的结果", data)
        //     $.ajax({
        //         url: `mock/corse_score_${data.id}.json`,
        //         success: function (data) {
        //             console.log("查询课程得分成功:", data)
        //         },
        //         error: function (err) {
        //         }
        //     });
        // })

        function get(url, data) {
            return new Promise((resolve, reject) => {
                $.ajax({
                    url: url,
                    data: data,
                    success: function (data) {
                        resolve(data);
                    },
                    error: function (err) {
                        reject(err)
                    }
                })
            });
        }

        get("mock/user.json")
            .then((data) => {
                console.log("用户查询成功~~~:", data)
                return get(`mock/user_corse_${data.id}.json`);
            })
            .then((data) => {
                console.log("课程查询成功~~~:", data)
                return get(`mock/corse_score_${data.id}.json`);
            })
            .then((data) => {
                console.log("课程成绩查询成功~~~:", data)
            })
            .catch((err) => {
                console.log("出现异常", err)
            });
    </script>
```

#### 1.3.9 模块化

**1.什么是模块化**

模块化就是把代码进行拆分，方便重复利用。类似java中的导包:要使用一个包，必须先导包。而JS中没有包的概念,换来的是模块

模块功能主要由两个命令构成:`export`和"import'

- `export`命令用于规定模块的对外接口
- `import`命令用于导入其他模块提供的功能

**2.export**

 比如定义一个JS文件` hello.js`  , 里面有一个对象:

```js
        const util = {
            sum(a, b) {
                return a + b;
            }
        }
```

可以使用export将这个对象导出 ，`{}`代表批量导入

```js
        const util = {
            sum(a, b) {
                return a + b;
            }
        }
        export {util}
```

`export`不仅可以导出对象，一切JS变量都可以导出。比如：基本类型变量、函数、数组、对象

当要导出多个值时，还可以简写。比如有一个文件: user.js

```js
var name = 'jack'
var age = 21
export {name,age}
```

**3.import**

定义`main.js`文件，在里面可以用`import`导入需要的内容：

```js
import util from "./hello.js"
import {name,add} from "./user.js"
util.sum(1,2);
console.log(name);
```

**省略名称**

- 上面的导出代码中,都明确指定了导出的变量名，这样其它人在导入使用时就必须准确写出变量名，否则就会出错
- 因此js提供了`default`关键字，可以对导出的变量名进行省略
- 例如：

```js
// 无需声明对象的名字
export default {
    sum(a, b) {
        return a + b;
    }
}
// 这样，当使用者导入时，可以任意起名字
```

 这样，当使用者导入时，可以任意起名字

```js
// 当使用者导入时，可以任意起名字，如abc
import abc from "./hello.js"
abc.sum(1,2);
```


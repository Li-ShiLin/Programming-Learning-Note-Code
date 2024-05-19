##  TypeScript

## 1.TypeScript简介 & 环境搭建

### 1.1 TypeScript简介

**`TypeScript`简介**：`TypeScript` 是由微软开发和维护的一种开源编程语言。它是 JavaScript 的超集，增加了可选的静态类型和基于类的面向对象编程特性。TypeScript 的设计目标是开发大型应用程序，并在编译时检测到潜在的错误。`TypeScript`是以`JavaScript`为基础构建的语言，`TypeScript`是`JavaScript`的超集。`TypeScript`可以在任何支持JavaScript的平台中执行，TypeScript扩展了JavaScript，并添加了类型。TS不能被JS解析器直接执行。虽然 TypeScript 不能被 JavaScript 解析器直接执行，但通过编译过程，可以将 TypeScript 转换为兼容的 JavaScript 代码，以便在任何支持 JavaScript 的环境中运行

**`TypeScript`主要特性**：

1.**静态类型**: `TypeScript`允许在编译时定义变量、函数参数和返回值的类型。这有助于捕获潜在的错误，并提供更好的代码可读性和维护性

```typescript
let isDone: boolean = false;
let decimal: number = 6;
let color: string = "blue";
```

2.**类型推断**: 即使没有显式地声明变量类型，TypeScript 也会尝试推断出变量的类型

```typescript
let someValue = "this is a string"; // TypeScript 推断为 string 类型
```

3.**接口**: 接口用于定义对象的结构，确保对象具有特定的形状

```typescript
interface Person {
    name: string;
    age: number;
}

function greet(person: Person) {
    console.log(`Hello, ${person.name}`);
}
```

4.**类和继承**: `TypeScript`支持类和基于类的继承，这对面向对象编程非常有用

```typescript
class Animal {
    name: string;
    constructor(name: string) {
        this.name = name;
    }

    move(distance: number = 0) {
        console.log(`${this.name} moved ${distance} meters.`);
    }
}

class Dog extends Animal {
    bark() {
        console.log('Woof! Woof!');
    }
}

const dog = new Dog('Rex');
dog.bark(); // Woof! Woof!
dog.move(10); // Rex moved 10 meters.
```

5.**模块**: `TypeScript` 使用模块来组织代码，支持 ES6 模块标准

```typescript
export class Calculator {
    static add(a: number, b: number): number {
        return a + b;
    }
}

import { Calculator } from './Calculator';
console.log(Calculator.add(5, 3)); // 8
```

6.**开发工具集成**: `TypeScript` 与现代编辑器和 IDE（如 Visual Studio Code）紧密集成，提供了代码补全、类型检查和重构支持

### 1.2 TS开发环境搭建

1.下载安装Node.js

```sh
64位：https://nodejs.org/dist/v14.15.1/node-v14.15.1-x64.msi
32位：https://nodejs.org/dist/v14.15.1/node-v14.15.1-x86.msi
```

2.使用npm全局安装`typescript`

```sh
# 进入命令行输入如下命令
npm i -g typescript
```

3.创建一个ts文件，使用tsc命令对ts文件进行编译

```sh
# 命令行在ts文件所在目录执行命令
tsc xxx.ts

# 运行tsc 01-HelloTs.ts 命令，生成01-HelloTs.js文件
tsc 01-HelloTs.ts
```

## 2.TS类型

### 2.1 类型声明

**TS的类型声明**：类型声明是TS非常重要的一个特点。通过类型声明可以指定TS中变量（参数、形参）的类型。指定类型后，当为变量赋值时，TS编译器会自动检查值是否符合类型声明，符合则赋值，否则报错。简而言之，类型声明给变量设置了类型，使得变量只能存储某种类型的值

**类型声明语法**：

```typescript
let 变量: 类型;

let 变量: 类型 = 值;

function fn(参数: 类型, 参数: 类型): 类型{
    ...
}
```

**自动类型判断**：TS拥有自动的类型判断机制。当对变量的声明和赋值是同时进行的，TS编译器会自动判断变量的类型。所以如果变量的声明和赋值时同时进行的，可以省略掉类型声明

`02-Type-in-TS\01-Basic-Type-Declarations.ts`：

```typescript
// 声明一个变量a，同时指定它的类型为number
let a: number;

// a 的类型设置为了number，在以后的使用过程中a的值只能是数字
a = 10;
a = 33;
// a = 'hello'; // 此行代码会报错，因为变量a的类型是number，不能赋值字符串
let b: string;
b = 'hello';
// b = 123;


// 声明完变量直接进行赋值
// let c: boolean = false;


// 如果变量的声明和赋值是同时进行的，TS可以自动对变量进行类型检测
// TS拥有自动的类型判断机制
// 当对变量的声明和赋值是同时进行的，TS编译器会自动判断变量的类型
// 所以如果变量的声明和赋值时同时进行的，可以省略掉类型声明
let c = false;
c = true;


// JS中的函数是不考虑参数的类型和个数的
// function sum(a, b){
//     return a + b;
// }

// console.log(sum(123, 456)); // 579
// console.log(sum(123, "456")); // "123456"

function sum(a: number, b: number): number{
    return a + b;
}

let result = sum(123, 456);
```

### 2.2 常用类型

|  类型   |              描述              |       取值        |
| :-----: | :----------------------------: | :---------------: |
| number  |            任意数字            |    1, -33, 2.5    |
| string  |           任意字符串           | 'hi', "hi", `hi`  |
| boolean |       布尔值true或false        |    true、false    |
| 字面量  |  限制变量的值就是该字面量的值  |      其本身       |
|   any   |            任意类型            |         *         |
| unknown |         类型安全的any          |         *         |
|  void   |     没有值（或undefined）      | 空值（undefined） |
|  never  |          不能是任何值          |      没有值       |
| object  |          任意的JS对象          |  {name:'孙悟空'}  |
|  array  |           任意JS数组           |      [1,2,3]      |
|  tuple  | 元素，TS新增类型，固定长度数组 |       [4,5]       |
|  enum   |       枚举，TS中新增类型       |    enum{A, B}     |

`02-Type-in-TS\02_Types-in-TS.ts`：

```typescript
// 也可以直接使用字面量进行类型声明
let a: 10;
a = 10;

// 可以使用 | 来连接多个类型（联合类型）
let b: "male" | "female";
b = "male";
b = "female";

let c: boolean | string;
c = true;
c = 'hello';

// any 表示的是任意类型，一个变量设置类型为any后相当于对该变量关闭了TS的类型检测
// 使用TS时，不建议使用any类型
// let d: any;

// 声明变量如果不指定类型，则TS解析器会自动判断变量的类型为any （隐式的any）
let d;
d = 10;
d = 'hello';
d = true;

// unknown 表示未知类型的值
let e: unknown;
e = 10;
e = "hello";
e = true;

let s:string;

// d的类型是any，它可以赋值给任意变量
// s = d;

e = 'hello';

// unknown 实际上就是一个类型安全的any
// unknown类型的变量，不能直接赋值给其他变量
if(typeof e === "string"){
    s = e;
}

// 类型断言，可以用来告诉解析器变量的实际类型
/*
* 语法：
*   变量 as 类型
*   <类型>变量
*
* */
s = e as string;
s = <string>e;

// void 用来表示空，以函数为例，就表示没有返回值的函数
function fn(): void{
}

// never 表示永远不会返回结果
function fn2(): never{
    throw new Error('报错了！');
}
```

`02-Type-in-TS\03_Types-in-TS.ts`：

```typescript
// object表示一个js对象
let a: object;
a = {};
a = function () {
};

// {} 用来指定对象中可以包含哪些属性
// 语法：{属性名:属性值,属性名:属性值}
// 在属性名后边加上?，表示属性是可选的,可选属性有没有都不会报错
let b: {name: string, age?: number};
b = {name: '孙悟空', age: 18};


// [propName: string]: any 表示任意类型的属性
// [propName: string]: any：对象 c可以有任意数量的其他属性
// 这些属性的名字必须是字符串（string 类型），而它们的值可以是任意类型（any）
let c: {name: string, [propName: string]: any};
c = {name: '猪八戒', age: 18, gender: '男'};




/*
*   设置函数结构的类型声明：
*       语法：(形参:类型, 形参:类型 ...) => 返回值
* */
let d: (a: number ,b: number)=>number;
// d = function (n1: number, n2: number): number{
//     return 10;
// }


/*
*   数组的类型声明：
*       类型[]
*       Array<类型>
* */
// string[] 表示字符串数组
let e: string[];
e = ['a', 'b', 'c'];

// number[] 表示数值数值
let f: number[];

let g: Array<number>;
g = [1, 2, 3];

/*
*   元组，元组就是固定长度的数组
*       语法：[类型, 类型, 类型]
* */
let h: [string, number];
h = ['hello', 123];

/*
* enum 枚举
*
* */
enum Gender{
    Male,
    Female
}

let i: {name: string, gender: Gender};
i = {
    name: '孙悟空',
    gender: Gender.Male // 'male'
}

// console.log(i.gender === Gender.Male);
// &表示同时
let j: { name: string } & { age: number };
// j = {name: '孙悟空', age: 18};


// 类型的别名
type myType = 1 | 2 | 3 | 4 | 5;
let k: myType;
let l: myType;
let m: myType;

k = 2;
```

### 2.3 ts类型使用

number：

```typescript
let decimal: number = 6;
let hex: number = 0xf00d;
let binary: number = 0b1010;
let octal: number = 0o744;
let big: bigint = 100n;
```

boolean：

```typescript
let isDone: boolean = false;
```

string：

```typescript
let color: string = "blue";
color = 'red';

let fullName: string = `Bob Bobbington`;
let age: number = 37;
let sentence: string = `Hello, my name is ${fullName}.

I'll be ${age + 1} years old next month.`;
```

字面量：也可以使用字面量去指定变量的类型，通过字面量可以确定变量的取值范围

```
let color: 'red' | 'blue' | 'black';
let num: 1 | 2 | 3 | 4 | 5;
```

any：任意类型

```typescript
let d: any = 4;
d = 'hello';
d = true;
```

unknown：类型安全的any

```typescript
let notSure: unknown = 4;
notSure = 'hello';
```

void：

```typescript
let unusable: void = undefined;
```

never：

```typescript
function error(message: string): never {
  throw new Error(message);
}
```

object（没啥用）:

```
let obj: object = {};
```

array：

```typescript
let list: number[] = [1, 2, 3];
let list: Array<number> = [1, 2, 3];
```

tuple：

```typescript
let x: [string, number];
x = ["hello", 10]; 
```

enum：

```typescript
enum Color {
  Red,
  Green,
  Blue,
}
let c: Color = Color.Green;

enum Color {
  Red = 1,
  Green,
  Blue,
}
let c: Color = Color.Green;

enum Color {
  Red = 1,
  Green = 2,
  Blue = 4,
}
let c: Color = Color.Green;
```

**类型断言**：有些情况下，变量的类型对于我们来说是很明确，但是TS编译器却并不清楚，此时，可以通过类型断言来告诉编译器变量的类型，断言有两种形式

```typescript
// 第一种
let someValue: unknown = "this is a string";
let strLength: number = (someValue as string).length;

// 第二种
let someValue: unknown = "this is a string";
let strLength: number = (<string>someValue).length;
```

**自定义类型**：

1.**`interface`**

```typescript
interface Person {
    firstName: string;
    lastName: string;
}

function greeter(person: Person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}

let user = { firstName: "Jane", lastName: "Doe" };
console.log(greeter(user));
```

2.**`type` 别名**

```typescript
type Name = string;
type NameResolver = () => string;
type NameOrResolver = Name | NameResolver;

function getName(n: NameOrResolver): Name {
    if (typeof n === "string") {
        return n;
    } else {
        return n();
    }
}
```

### 2.4 `any`和`unknown`对比

**`unknown` 简介**：`unknown` 类型是一个较为特殊且强大的类型，提供了一种与 `any` 类型类似的灵活性，但是更加安全。`unknown` 类型可以表示任何值，但在对其进行操作之前，必须首先进行类型检查或类型断言

**`unknown` 类型的特性**：

1.任何类型的值都可以赋值给 `unknown`：

```typescript
let value: unknown;

value = 42;          // OK
value = "hello";     // OK
value = true;        // OK
value = [];          // OK
```

2.不能对 `unknown` 类型的值直接进行操作： 由于 `unknown` 类型的值可能是任何类型，所以在对其进行操作之前，必须进行类型检查或使用类型断言

```typescript
let value: unknown;

value = "hello";

// 编译错误：对象类型为 "unknown"
// console.log(value.length);

if (typeof value === "string") {
    console.log(value.length); // OK
}
```

3.`unknown` 类型的值必须经过类型检查或类型断言才能赋值给其他类型：

```typescript
let value: unknown;
let str: string;

value = "hello";

// 编译错误：类型 "unknown" 不能赋值给类型 "string"
// str = value;

if (typeof value === "string") {
    str = value; // OK
}

// 或者使用类型断言
str = value as string; // OK
```

**`any` 与`unknown`的对比**：

```
any类型:
        赋值给 any 类型的值可以是任何类型
        any类型的值可以进行任意操作，编译器不会进行类型检查
        使用 any类型会绕过 TypeScript 的类型检查机制，可能导致运行时错误
unknown类型:
        赋值给unknown类型的值可以是任何类型
        在对unknown类型的值进行操作之前，必须进行类型检查或类型断言
        使用unknown类型可以确保在使用值之前进行类型检查，从而提供更好的类型安全
```

## 3.编译选项

**TS编译选项简介**：在 TypeScript（TS）中，编译选项可以通过 `tsconfig.json` 文件进行配置。可以自定义编译器的行为和项目设置，从而更好地控制 TypeScript 代码的编译过程

**自动编译文件**：编译文件时，使用 -w 指令后（命令`tsc xxx.ts -w`），TS编译器会自动监视文件的变化，并在文件发生变化时对文件进行重新编译

**自动编译整个项目**：如果直接使用tsc指令，则可以自动将当前项目下的所有ts文件编译为js文件。但是能直接使用tsc命令的前提是要先在项目根目录下创建一个ts的配置文件 tsconfig.json。tsconfig.json是一个JSON文件，添加配置文件后只需 tsc 命令即可完成对整个项目的编译

### 3.1 基本选项

**include**：定义被编译文件所在的目录。默认值：["\*\*/\*"]

```json
"include":["src/**/*", "tests/**/*"]
// 所有src目录和tests目录下的文件都会被编译
```

**exclude**：定义需要排除在外的目录。默认值：`["node_modules", "bower_components", "jspm_packages"]`

```json
"exclude": ["./src/hello/**/*"]
// src下hello目录下的文件都不会被编译
```

**extends**：定义被继承的配置文件

```json
"extends": "./configs/base"
// 当前配置文件中会自动包含config目录下base.json中的所有配置信息
```

**files**：指定被编译文件的列表，只有需要编译的文件少时才会用到

```json
"files": [
    "core.ts",
    "sys.ts",
    "types.ts",
    "scanner.ts",
    "parser.ts",
    "utilities.ts",
    "binder.ts",
    "checker.ts",
    "tsc.ts"
  ]
// 列表中的文件都会被TS编译器所编译
```



### 3.2 编译器选项

**`compilerOptions`(编译器选项)**：编译选项是配置文件中非常重要也比较复杂的配置选项。在compilerOptions中包含多个子选项，用来完成对编译的配置

**target**：设置ts代码编译的目标版本。可选值：ES3（默认）、ES5、ES6/ES2015、ES7/ES2016、ES2017、ES2018、ES2019、ES2020、ESNext

```json
"compilerOptions": {
    "target": "ES6"
}
// 如上设置，编写的ts代码将会被编译为ES6版本的js代码
```

**lib**：指定代码运行时所包含的库（宿主环境）。可选值：ES5、ES6/ES2015、ES7/ES2016、ES2017、ES2018、ES2019、ES2020、ESNext、DOM、WebWorker、ScriptHost

```json
"compilerOptions": {
    "target": "ES6",
    "lib": ["ES6", "DOM"],
    "outDir": "dist",
    "outFile": "dist/aa.js"
}
```

**module**：设置编译后代码使用的模块化系统。可选值：CommonJS、UMD、AMD、System、ES2020、ESNext、None

```json
"compilerOptions": {
    "module": "CommonJS"
}
```

**outDir**：编译后文件的所在目录。默认情况下，编译后的js文件会和ts文件位于相同的目录，设置outDir后可以改变编译后文件的位置

```json
"compilerOptions": {
    "outDir": "dist"
}
// 设置后编译后的js文件将会生成到dist目录
```

**outFile**：将所有的文件编译为一个js文件。默认会将所有的编写在全局作用域中的代码合并为一个js文件，如果module制定了None、System或AMD则会将模块一起合并到文件之中

```json
"compilerOptions": {
    "outFile": "dist/app.js"
}
```

**rootDir**：指定代码的根目录，默认情况下编译后文件的目录结构会以最长的公共目录为根目录，通过rootDir可以手动指定根目录

```json
"compilerOptions": {
    "rootDir": "./src"
}
```

**allowJs**：是否对js文件编译

**checkJs**：是否对js文件进行检查

```json
"compilerOptions": {
    "allowJs": true,
    "checkJs": true
}
```

**removeComments**：是否删除注释。默认值：false

**noEmit**：不对代码进行编译。默认值：false

**sourceMap**：是否生成sourceMap。默认值：false

### 3.3 严格性选项

严格性选项主要用于语法检查

**`strict`**: 启用所有的严格类型检查选项。如果设置为 `true`，等同于开启所有严格选项

**`noImplicitAny`**: 禁止隐式的any类型。当表达式和声明的类型为 `any` 时，生成一个错误

**`strictNullChecks`**: 启用严格的 null 检查

**alwaysStrict**：总是以严格模式对代码进行编译

**noImplicitAny**：禁止隐式的any类型

**noImplicitThis**：禁止类型不明确的this

**strictBindCallApply**：严格检查bind、call和apply的参数列表

**strictFunctionTypes**：严格检查函数的类型

**strictPropertyInitialization**：严格检查属性是否初始化

### 3.4 其他选项

**模块解析选项**：

- **`baseUrl`**: 解析非相对模块名称的基本目录
- **`paths`**: 模块名到基于 `baseUrl` 的路径映射
- **`rootDirs`**: 将多个目录内容合并为一个虚拟目录结构
- **`typeRoots`**: 包含类型声明的目录列表
- **`types`**: 需要包含的类型声明文件列表

**输出选项**：

- **`outDir`**: 指定输出目录
- **`outFile`**: 将输出文件合并为一个文件（仅适用于 `module` 设置为 `amd` 或 `system`）
- **`declaration`**: 生成相应的 `.d.ts` 文件

**Source Map 选项**：

- **`sourceMap`**: 生成相应的 `.map` 文件，用于调试
- **`inlineSourceMap`**: 将 Source Map 作为数据 URI 嵌入到输出文件中
- **`inlineSources`**: 将源文件内容嵌入到 Source Map 中

**额外检查**：

- noFallthroughCasesInSwitch：检查switch语句包含正确的break
- noImplicitReturns：检查函数没有隐式的返回值
- noUnusedLocals：检查未使用的局部变量
- noUnusedParameters：检查未使用的参数

**其他选项**：

- **`removeComments`**: 删除输出文件中的所有注释
- **`noEmit`**: 不生成输出文件
- **`skipLibCheck`**: 跳过所有声明文件的类型检查
- **allowUnreachableCode**：检查不可达代码。可选值：true，忽略不可达代码。false，不可达代码将引起错误
- **noEmitOnError**：有错误的情况下不进行编译。默认值：false

**示例 `tsconfig.json`**：

```json
{
  "compilerOptions": {
    "target": "ES6",
    "module": "commonjs",
    "strict": true,
    "baseUrl": "./",
    "paths": {
      "@app/*": ["src/app/*"]
    },
    "outDir": "./dist",
    "sourceMap": true,
    "removeComments": true
  },
  "include": ["src/**/*"],
  "exclude": ["node_modules", "**/*.spec.ts"]
}
```

### 3.5 配置示例

`03-Compile-Settings\tsconfig.json`

```json
{
/*
  tsconfig.json是ts编译器的配置文件，ts编译器可以根据它的信息来对代码进行编译
    "include" 用来指定哪些ts文件需要被编译
      路径：** 表示任意目录
            * 表示任意文件
    "exclude" 不需要被编译的文件目录
        默认值：["node_modules", "bower_components", "jspm_packages"]

*/
  "include": [
    "./src/**/*"
  ],
//  "exclude": [
//    "./src/hello/**/*"
//  ]

  /*
    compilerOptions 编译器的选项
  */
  "compilerOptions": {

    // target 用来指定ts被编译为的ES的版本
    // 'es3', 'es5', 'es6', 'es2015', 'es2016', 'es2017', 'es2018', 'es2019', 'es2020', 'esnext'
    "target": "es2015",
    // module 指定要使用的模块化的规范
    // 'none', 'commonjs', 'amd', 'system', 'umd', 'es6', 'es2015', 'es2020', 'esnext'
    "module": "es2015",
    // lib用来指定项目中要使用的库
    //'es5', 'es6', 'es2015', 'es7', 'es2016', 'es2017', 'es
    //2018', 'es2019', 'es2020', 'esnext', 'dom', 'dom.iterable', 'webworker', 'webworker.importscripts', 'webworker.iterable', 'scri
    //pthost', 'es2015.core', 'es2015.collection', 'es2015.generator', 'es2015.iterable', 'es2015.promise', 'es2015.proxy', 'es2015.r
    //eflect', 'es2015.symbol', 'es2015.symbol.wellknown', 'es2016.array.include', 'es2017.object', 'es2017.sharedmemory', 'es2017.st
    //ring', 'es2017.intl', 'es2017.typedarrays', 'es2018.asyncgenerator', 'es2018.asynciterable', 'es2018.intl', 'es2018.promise', '
    //es2018.regexp', 'es2019.array', 'es2019.object', 'es2019.string', 'es2019.symbol', 'es2020.bigint', 'es2020.promise', 'es2020.s
    //haredmemory', 'es2020.string', 'es2020.symbol.wellknown', 'es2020.intl', 'esnext.array', 'esnext.symbol', 'esnext.asynciterable
    //', 'esnext.intl', 'esnext.bigint', 'esnext.string', 'esnext.promise', 'esnext.weakref'
//    "lib": ["es6", "dom"]


    // outDir 用来指定编译后文件所在的目录
    "outDir": "./dist",

    // 将代码合并为一个文件
    // 设置outFile后，所有的全局作用域中的代码会合并到同一个文件中
    //"outFile": "./dist/app.js"

    // 是否对js文件进行编译，默认是false
//    "allowJs": true,
    // 是否检查js代码是否符合语法规范，默认是false
//    "checkJs": true,
    // 是否移除注释
    "removeComments": true,
    // 不生成编译后的文件
    "noEmit": false,


    // 当有错误时不生成编译后的文件
    "noEmitOnError": true,

    // 所有严格检查的总开关
    "strict": true,

    // 用来设置编译后的文件是否使用严格模式，默认false
    "alwaysStrict": true,

    // 不允许隐式的any类型
    "noImplicitAny": true,

    // 不允许不明确类型的this
    "noImplicitThis": true,

    // 严格的检查空值
    "strictNullChecks": true
  }
}
```

## 4.webpack

### 4.1 webpack简介

**webpack官方文档**：`https://webpack.js.org/`

**webpack**：实际开发中很少使用`tsc` 命令对代码进行编译，通常需要使用构建工具对代码进行打包打包，TS同样也可以结合构建工具一起使用。下面以webpack为例介绍一下如何结合构建工具打包TS代码

**webpack简介**：Webpack 是一个现代 JavaScript 应用程序的静态模块打包工具。开发应用程序时Webpack 会根据依赖关系把模块组合成一个或多个 bundle

**webpack主要功能**：

- 模块打包：Webpack 可以处理 JavaScript、CSS、图片等各种类型的文件，并将它们打包成一个或多个 bundle
- 代码拆分：通过代码拆分，Webpack 可以将应用程序分成多个 bundle，从而实现按需加载和优化性能
- 加载器：Webpack 加载器可以将非 JavaScript 的静态资源（如 CSS、图片等）转换为模块
- 插件系统：Webpack 拥有丰富的插件系统，可以扩展其功能，进行复杂的任务，如压缩文件、注入环境变量等

**webpack核心概念**：

- Entry（入口）：Webpack 构建依赖图的起点。一般是一个或多个指定的文件（如 `index.js`）
- Output（输出）：Webpack 打包后生成的文件及其存放路径的配置
- Loaders（加载器）：用于转换应用中的某些类型的模块，例如，使用 `babel-loader` 转换 ES6 代码为 ES5
- Plugins（插件）：用于执行范围更广的任务，如打包优化、资源管理、环境变量注入等

### 4.2 webpack基本使用

1.初始化项目

```sh
# 进入项目根目录执行命令npm init -y
npm init -y
# 主要作用：创建package.json文件
```

2.下载构建工具

```sh
# 下载构建工具
npm i -D webpack webpack-cli webpack-dev-server typescript ts-loader clean-webpack-plugin
# 共安装了如下7个包
# webpack:               构建工具webpack
# webpack-cli:           webpack的命令行工具
# webpack-dev-server:    webpack的开发服务器
# typescript:            ts编译器
# ts-loader:             ts加载器，用于在webpack中编译ts文件
# html-webpack-plugin:   webpack中html插件，用来自动创建html文件
# clean-webpack-plugin:  webpack中的清除插件，每次构建都会先清除目录
```

3.根目录下创建webpack的配置文件`webpack.config.js`

```json
// 引入一个包
const path = require('path');
// 引入html插件
const HTMLWebpackPlugin = require('html-webpack-plugin');
// 引入clean插件
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

// webpack中的所有的配置信息都应该写在module.exports中
module.exports = {

    // 指定入口文件
    entry: "./src/index.ts",

    // 指定打包文件所在目录
    output: {
        // 指定打包文件的目录
        path: path.resolve(__dirname, 'dist'),
        // 打包后文件的文件
        filename: "bundle.js",

        // 告诉webpack不使用箭头
        environment:{
            arrowFunction: false
        }
    },

    // 指定webpack打包时要使用模块
    module: {
        // 指定要加载的规则
        rules: [
            {
                // test指定的是规则生效的文件
                test: /\.ts$/,
                // 要使用的loader
                use: [
                     // 配置babel
                     {
                         // 指定加载器
                         loader:"babel-loader",
                         // 设置babel
                         options: {
                             // 设置预定义的环境
                             presets:[
                                 [
                                     // 指定环境的插件
                                     "@babel/preset-env",
                                     // 配置信息
                                     {
                                         // 要兼容的目标浏览器
                                         targets:{
                                             "chrome":"58",
                                             "ie":"11"
                                         },
                                         // 指定corejs的版本
                                         "corejs":"3",
                                         // 使用corejs的方式 "usage" 表示按需加载
                                         "useBuiltIns":"usage"
                                     }
                                 ]
                             ]
                         }
                     },
                    'ts-loader'
                ],
                // 要排除的文件
                exclude: /node-modules/
            }
        ]
    },

    // 配置Webpack插件
    plugins: [
        new CleanWebpackPlugin(),
        new HTMLWebpackPlugin({
            // title: "这是一个自定义的title"
            template: "./src/index.html"
        }),
    ],

    // 用来设置引用模块
    resolve: {
        extensions: ['.ts', '.js']
    }

};
```

4.根目录下创建`tsconfig.json`，根据自己需要进行配置

```json
{
    "compilerOptions": {
        "target": "ES2015",
        "module": "ES2015",
        "strict": true
    }
}
```

5.修改package.json添加如下配置

```json
{
  ...略...
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "build": "webpack",
    "start": "webpack serve --open chrome.exe"
  },
  ...略...
}
```

6.在src下创建ts文件，并在并命令行执行```npm run build```对代码进行编译，或者执行```npm start```来启动开发服务器

### 4.3 Webpack配合Babel使用

**Webpack引入**：经过一系列的配置，使得TS和webpack已经结合到了一起，除了webpack，开发中还经常需要结合babel来对代码进行转换以使其可以兼容到更多的浏览器

**Babel简介**：`https://babeljs.io/`。Babel用于编译 JavaScript 的工具，与 Webpack 配合使用。Babel是一个开源的JavaScript编译器，用于将最新版本的JavaScript代码转换为向后兼容的旧版本，以便在不同的浏览器和环境中运行。它提供了一种简单而强大的方式来编写使用最新JavaScript语言特性的代码，而无需担心兼容性问题

**Babel配合Webpack使用**：在上述步骤的基础上，通过以下步骤再将babel引入到项目中

1.安装依赖包：

```sh
npm i -D @babel/core @babel/preset-env babel-loader core-js
# 共安装了4个包，分别是:
# @babel/core:          babel的核心工具
# @babel/preset-env:    babel的预定义环境
# @babel-loader:        babel在webpack中的加载器
# core-js:              core-js用来使老版本的浏览器支持新版ES语法
```

2.修改webpack.config.js配置文件：

```javascript
// 如此一来，使用ts编译后的文件将会再次被babel处理
// 使得代码可以在大部分浏览器中直接使用，可以在配置选项的targets中指定要兼容的浏览器版本
...略...
    // 指定webpack打包时要使用模块
    module: {
        // 指定要加载的规则
        rules: [
            {
                // test指定的是规则生效的文件
                test: /\.ts$/,
                // 要使用的loader
                use: [
                     // 配置babel
                     {
                         // 指定加载器
                         loader:"babel-loader",
                         // 设置babel
                         options: {
                             // 设置预定义的环境
                             presets:[
                                 [
                                     // 指定环境的插件
                                     "@babel/preset-env",
                                     // 配置信息
                                     {
                                         // 要兼容的目标浏览器
                                         targets:{
                                             "chrome":"58",
                                             "ie":"11"
                                         },
                                         // 指定corejs的版本
                                         "corejs":"3",
                                         // 使用corejs的方式 "usage" 表示按需加载
                                         "useBuiltIns":"usage"
                                     }
                                 ]
                             ]
                         }
                     },
                    'ts-loader'
                ],
                // 要排除的文件
                exclude: /node-modules/
            }
        ]
    },
...略...
```

## 5.面向对象

### 5.1 面向对象简介

**面向对象思想**：面向对象是程序中一个非常重要的思想，面向对象简而言之就是程序之中所有的操作都需要通过对象来完成举例来说：操作浏览器要使用window对象、操作网页要使用document对象、操作控制台要使用console对象。

**对象**：一切操作都要通过对象，也就是所谓的面向对象，那么对象到底是什么呢？这就要先说到程序是什么，计算机程序的本质就是对现实事物的抽象，抽象的反义词是具体，比如：照片是对一个具体的人的抽象，汽车模型是对具体汽车的抽象等等。程序也是对事物的抽象，在程序中我们可以表示一个人、一条狗、一把枪、一颗子弹等等所有的事物。一个事物到了程序中就变成了一个对象。在程序中所有的对象都被分成了两个部分数据和功能，以人为例，人的姓名、性别、年龄、身高、体重等属于数据，人可以说话、走路、吃饭、睡觉这些属于人的功能。数据在对象中被成为属性，而功能就被称为方法。所以简而言之，在程序中一切皆是对象

### 5.2 类

##### 5.2.1 类的简介

**类的作用**：要想面向对象，操作对象，首先便要拥有对象，那么下一个问题就是如何创建对象。要创建对象，必须要先定义类，所谓的类可以理解为对象的模型，程序中可以根据类创建指定类型的对象，举例来说：可以通过Person类来创建人的对象，通过Dog类创建狗的对象，通过Car类来创建汽车的对象，不同的类可以用来创建不同的对象

**定义类语法**：

```typescript
class 类名 {
	属性名: 类型;
	
	constructor(参数: 类型){
		this.属性名 = 参数;
	}
	
	方法名(){
		....
	}

}
```

示例：

```typescript
class Person{
    name: string;
    age: number;

    constructor(name: string, age: number){
        this.name = name;
        this.age = age;
    }

    sayHello(){
        console.log(`大家好，我是${this.name}`);
    }
}
```

使用类：

```typescript
const p = new Person('孙悟空', 18);
p.sayHello();
```

#####  5.2.2 类的创建与使用

1.创建配置文件`tsconfig.json`：`tsconfig.json`

```json
{
    "compilerOptions": {
        "module": "es2015",
        "target": "es2015",
        "strict": true,
        "outDir": "./dist",
        "noEmitOnError": true
    },
    "include": [
        "./src/**/*"
    ]
}
```

2.创建`Person`类：`src\01-Class-Introduction.ts`

```typescript
// 使用class关键字来定义一个类
/*
*   对象中主要包含了两个部分：
*       属性
*       方法
* */
class Person {

    // 定义属性
    name: string;
    age: number;
    // 在属性前使用static关键字可以定义类属性（静态属性）
    // static age: number;
    // 静态属性通过类名访问：Person.age

    /*
    *   直接定义的属性是实例属性，需要通过对象的实例去访问：
    *       const per = new Person();
    *       per.name
    *
    *   使用static开头的属性是静态属性（类属性），可以直接通过类去访问
    *       Person.age
    * */

    // readonly name: string = '孙悟空';
    //  readonly开头的属性表示一个只读的属性无法修改
    constructor(name: string, age: number) {
        this.name = name;
        this.age = age;
    }

    // 定义方法
    // 如果方法以static开头则方法就是类方法，可以直接通过类去调用
    sayHello() {
        console.log(`大家好，我是${this.name}`);
    }
}

const person = new Person("孙悟空", 1000);
person.sayHello();
```

3.使用`tsc -w`命令编译ts代码

4.在`index.html`中使用类：浏览器打开`index.html`，控制台输出 `大家好，我是孙悟空`

```html
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>类</title>
</head>

<body>
    <script src="dist\01-Class-Introduction.js"></script>
</body>

</html>
```

##### 5.2.3 构造函数和this

**this**：在类中，使用this表示当前对象

`src\02-Constructor.ts`：创建Dog()类

```typescript
"use strict";
class Dog {
    // 定义属性
    name: string;
    age: number;
    // constructor 被称为构造函数
    //  构造函数会在对象创建时调用
    constructor(name: string, age: number) {
        // 在实例方法中，this就表示当前当前的实例
        // 在构造函数中当前对象就是当前新建的那个对象
        // 可以通过this向新建的对象中添加属性
        this.name = name;
        this.age = age;
    }
    bark() {
        // alert('汪汪汪！');
        // 在方法中可以通过this来表示当前调用方法的对象
        console.log(this.name);
    }
}
const dog = new Dog('小黑', 4);
const dog2 = new Dog('小白', 2);
// console.log(dog);
// console.log(dog2);
dog2.bark();
```

`index.html`：使用Dog()类

```html
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>类</title>
</head>

<body>
    <script src="dist\01-Class-Introduction.js"></script>
    <script src="dist\02-constructor.js"></script>
</body>

</html>
```

### 5.3 面向对象特性

##### 5.3.1 继承

1.继承可以将其他类中的属性和方法引入到当前类中，通过继承可以在不修改父类的情况下完成对父类的扩展

```typescript
class Animal{
    name: string;
    age: number;

    constructor(name: string, age: number){
        this.name = name;
        this.age = age;
    }
}

class Dog extends Animal{

    bark(){
        console.log(`${this.name}在汪汪叫！`);
    }
}

const dog = new Dog('旺财', 4);
dog.bark();
```

2.重写：发生继承时，如果子类中的方法会替换掉父类中的同名方法，这就称为方法的重写

```typescript
class Animal{
    name: string;
    age: number;

    constructor(name: string, age: number){
        this.name = name;
        this.age = age;
    }

    run(){
        console.log(`父类中的run方法！`);
    }
}

class Dog extends Animal{

    bark(){
        console.log(`${this.name}在汪汪叫！`);
    }

    run(){
        console.log(`子类中的run方法，会重写父类中的run方法！`);
    }
}

const dog = new Dog('旺财', 4);
dog.bark();
```

3.在子类中可以使用super来完成对父类的引用

`src\04-Super-Operator.ts`：super关键字的使用

```typescript
(function () {
    class Animal {
        name: string;

        constructor(name: string) {
            this.name = name;
        }

        sayHello() {
            console.log('动物在叫~');
        }
    }

    class Dog extends Animal{

        age: number;

        constructor(name: string, age: number) {
            // 如果在子类中写了构造函数，在子类构造函数中必须对父类的构造函数进行调用
            super(name); // 调用父类的构造函数
            this.age = age;
        }

        sayHello() {
            // 在类的方法中 super就表示当前类的父类
            //super.sayHello();

            console.log('汪汪汪汪！');
        }

    }

    const dog = new Dog('旺财', 3);
    dog.sayHello();
})();
```

`src\03-Class-Inheritance.ts`：面向对象的继承特性

```typescript
(function (){

    // 定义一个Animal类
    class Animal{
        name: string;
        age: number;

        constructor(name: string, age: number) {
            this.name = name;
            this.age = age;
        }

        sayHello(){
            console.log('动物在叫~');
        }
    }

    /*
    * Dog extends Animal
    *   - 此时，Animal被称为父类，Dog被称为子类
    *   - 使用继承后，子类将会拥有父类所有的方法和属性
    *   - 通过继承可以将多个类中共有的代码写在一个父类中，
    *       这样只需要写一次即可让所有的子类都同时拥有父类中的属性和方法
    *       如果希望在子类中添加一些父类中没有的属性或方法直接加就行
    *   - 如果在子类中添加了和父类相同的方法，则子类方法会覆盖掉父类的方法
    *       这种子类覆盖掉父类方法的形式，我们称为方法重写
    *
    * */
    // 定义一个表示狗的类
    // 使Dog类继承Animal类
    class Dog extends Animal{

        run(){
            console.log(`${this.name}在跑~~~`);
        }

        sayHello() {
            console.log('汪汪汪汪！');
        }

    }

    // 定义一个表示猫的类
    // 使Cat类继承Animal类
    class Cat extends Animal{
        sayHello() {
            console.log('喵喵喵喵！');
        }
    }

    const dog = new Dog('旺财', 5);
    const cat = new Cat('咪咪', 3);
    console.log(dog);
    dog.sayHello();
    dog.run();
    console.log(cat);
    cat.sayHello();


})();
```

##### 5.3.2 封装

**封装**：对象实质上就是属性和方法的容器，它的主要作用就是存储属性和方法，这就是所谓的封装。默认情况下，对象的属性是可以任意的修改的，为了确保数据的安全性，在TS中可以对属性的权限进行设置

**只读属性（readonly）**：如果在声明属性时添加一个readonly，则属性便成了只读属性无法修改

**TS中属性具有三种修饰符**：

- public（默认值）：可以在类、子类和对象中修改
- protected ：可以在类、子类中修改
- private ：可以在类中修改

public（默认值）：可以在类、子类和对象中修改

```typescript
class Person{
    public name: string; // 写或什么都不写都是public
    public age: number;

    constructor(name: string, age: number){
        this.name = name; // 可以在类中修改
        this.age = age;
    }

    sayHello(){
        console.log(`大家好，我是${this.name}`);
    }
}

class Employee extends Person{
    constructor(name: string, age: number){
        super(name, age);
        this.name = name; //子类中可以修改
    }
}

const p = new Person('孙悟空', 18);
p.name = '猪八戒';// 可以通过对象修改
```

protected ：可以在类、子类中修改

```typescript
class Person{
    protected name: string;
    protected age: number;

    constructor(name: string, age: number){
        this.name = name; // 可以修改
        this.age = age;
    }

    sayHello(){
        console.log(`大家好，我是${this.name}`);
    }
}

class Employee extends Person{

    constructor(name: string, age: number){
        super(name, age);
        this.name = name; //子类中可以修改
    }
}

const p = new Person('孙悟空', 18);
p.name = '猪八戒';// 不能修改
```

private ：可以在类中修改

```typescript
class Person{
    private name: string;
    private age: number;

    constructor(name: string, age: number){
        this.name = name; // 可以修改
        this.age = age;
    }

    sayHello(){
        console.log(`大家好，我是${this.name}`);
    }
}

class Employee extends Person{

    constructor(name: string, age: number){
        super(name, age);
        this.name = name; //子类中不能修改
    }
}

const p = new Person('孙悟空', 18);
p.name = '猪八戒';// 不能修改
```

**属性存取器**：对于一些不希望被任意修改的属性，可以将其设置为private。直接将其设置为private将导致无法再通过对象修改其中的属性。可以在类中定义一组读取、设置属性的方法，这种对属性读取或设置的属性被称为属性的存取器。读取属性的方法叫做setter方法，设置属性的方法叫做getter方法

```typescript
class Person{
    private _name: string;

    constructor(name: string){
        this._name = name;
    }

    get name(){
        return this._name;
    }

    set name(name: string){
        this._name = name;
    }

}

const p1 = new Person('孙悟空');
console.log(p1.name); // 通过getter读取name属性
p1.name = '猪八戒'; // 通过setter修改name属性
```

**静态属性**：静态属性（方法），也称为类属性。使用静态属性无需创建实例，通过类即可直接使用。静态属性（方法）使用static开头

```typescript
class Tools{
    static PI = 3.1415926;
    
    static sum(num1: number, num2: number){
        return num1 + num2
    }
}

console.log(Tools.PI);
console.log(Tools.sum(123, 456));
```

`src\07-Property-Encapsulation.ts`：

```typescript
// 属性封装
(function (){
    // 定义一个表示人的类
    class Person{
        // TS可以在属性前添加属性的修饰符
        /*
        *   public 修饰的属性可以在任意位置访问（修改） 默认值
        *   private 私有属性，私有属性只能在类内部进行访问（修改）
        *       - 通过在类中添加方法使得私有属性可以被外部访问
        *   protected 受包含的属性，只能在当前类和当前类的子类中访问（修改）
        *
        * */
        private _name: string;
        private _age: number;

        constructor(name: string, age: number) {
            this._name = name;
            this._age = age;
        }

        /*
        *   getter方法用来读取属性
        *   setter方法用来设置属性
        *       - 它们被称为属性的存取器
        * */

        // 定义方法，用来获取name属性
        // getName(){
        //     return this._name;
        // }
        //
        // // 定义方法，用来设置name属性
        // setName(value: string){
        //     this._name = value;
        // }
        //
        // getAge(){
        //     return this._age;
        // }
        //
        // setAge(value: number){
        //     // 判断年龄是否合法
        //     if(value >= 0){
        //         this._age = value;
        //     }
        // }

        // TS中设置getter方法的方式
        get name(){
            // console.log('get name()执行了！！');
            return this._name;
        }

        set name(value){
            this._name = value;
        }

        get age(){
            return this._age;
        }

        set age(value){
            if(value >= 0){
                this._age = value
            }
        }
    }

    const per = new Person('孙悟空', 18);

    /*
    * 现在属性是在对象中设置的，属性可以任意的被修改,
    *   属性可以任意被修改将会导致对象中的数据变得非常不安全
    * */

    // per.setName('猪八戒');
    // per.setAge(-33);

    per.name = '猪八戒';
    per.age = -33;

    // console.log(per);


    class A{
        protected num: number;

        constructor(num: number) {
            this.num = num;
        }
    }

    class B extends A{

        test(){
            console.log(this.num);
        }

    }

    const b = new B(123);
    // b.num = 33;


   /* class C{

        name: string;
        age: number

        // 可以直接将属性定义在构造函数中
        constructor(name: string, age: number) {
            this.name = name;
             this.age = age;
        }

    }*/

    class C{

        // 可以直接将属性定义在构造函数中
        constructor(public name: string, public age: number) {
        }

    }

    const c = new C('xxx', 111);

    console.log(c);

})();
```



### 5.4 抽象类

**抽象类（abstract class）**：抽象类是专门用来被其他类所继承的类，它只能被其他类所继承不能用来创建实例。使用abstract开头的方法叫做抽象方法，抽象方法没有方法体只能定义在抽象类中，继承抽象类时抽象方法必须要实现

```typescript
abstract class Animal{
    abstract run(): void;
    bark(){
        console.log('动物在叫~');
    }
}

class Dog extends Animals{
    run(){
        console.log('狗在跑~');
    }
}
```

`src\05-Abstract-Class.ts`：

```typescript
(function () {

    /*
    *   以abstract开头的类是抽象类，
    *       抽象类和其他类区别不大，只是不能用来创建对象
    *       抽象类就是专门用来被继承的类
    *
    *       抽象类中可以添加抽象方法
    * */
    abstract class Animal {
        name: string;

        constructor(name: string) {
            this.name = name;
        }

        // 定义一个抽象方法
        // 抽象方法使用 abstract开头，没有方法体
        // 抽象方法只能定义在抽象类中，子类必须对抽象方法进行重写
        abstract sayHello():void;
    }

    class Dog extends Animal{

        sayHello() {
            console.log('汪汪汪汪！');
        }

    }

    class Cat extends Animal{
        sayHello() {
            console.log('喵喵喵喵！');
        }

    }

    const dog = new Dog('旺财');
    dog.sayHello();

})();
```

**补充**：在 TypeScript 中，抽象类（abstract class）是一种定义了公共行为但不能被直接实例化的类。抽象类通常用于作为其他类的基类，它可以包含抽象方法（没有实现的方法）和具体方法（有实现的方法）。抽象类的主要目的是为子类提供一个通用的接口和一部分通用的实现

**抽象类的定义**：要定义一个抽象类，可以使用 `abstract` 关键字：

```typescript
// Animal是一个抽象类，包含一个抽象方法 makeSound和一个具体方法 move
// 任何继承 Animal 的类都必须实现 makeSound 方法
abstract class Animal {
    // 抽象方法（子类必须实现）
    abstract makeSound(): void;
    
    // 具体方法
    move(): void {
        console.log("Moving...");
    }
}
```

**继承抽象类**：要继承一个抽象类并实现其抽象方法，可以这样做：

```typescript
// 在这个例子中，Dog和 Cat类都继承了 Animal类，并且它们都实现了 makeSound方法
class Dog extends Animal {
    makeSound(): void {
        console.log("Woof! Woof!");
    }
}

class Cat extends Animal {
    makeSound(): void {
        console.log("Meow! Meow!");
    }
}
```

**实例化**：不能直接实例化抽象类

```typescript
// 错误：不能创建抽象类的实例
// const animal = new Animal();
```

**抽象类的优势**：

1. **代码重用**：抽象类可以包含具体方法，实现代码重用
2. **强制约定**：抽象方法要求子类必须实现特定方法，确保子类遵循特定的接口
3. **类型检查**：在编译时检查类的实现是否符合抽象类的定义，有助于捕获错误

**抽象类使用示例**：使用抽象类来设计一个简单的几何形状系统

```typescript
// Shape 是一个抽象类，Circle和Rectangle继承自 Shape并实现了其抽象方法
// describe方法可以被所有形状共享，而每种形状都有自己特定的 area和 perimeter计算方式
abstract class Shape {
    constructor(public color: string) {}

    // 抽象方法
    abstract area(): number;

    abstract perimeter(): number;

    describe(): void {
        console.log(`A ${this.color} shape with area ${this.area()} and perimeter ${this.perimeter()}.`);
    }
}

class Circle extends Shape {
    constructor(color: string, public radius: number) {
        super(color);
    }

    area(): number {
        return Math.PI * this.radius ** 2;
    }

    perimeter(): number {
        return 2 * Math.PI * this.radius;
    }
}

class Rectangle extends Shape {
    constructor(color: string, public width: number, public height: number) {
        super(color);
    }

    area(): number {
        return this.width * this.height;
    }

    perimeter(): number {
        return 2 * (this.width + this.height);
    }
}

const circle = new Circle("red", 5);
circle.describe();

const rectangle = new Rectangle("blue", 4, 6);
rectangle.describe();
```

### 5.5 接口

**接口简介**：接口的作用类似于抽象类，不同点在于接口中的所有方法和属性都是没有实值的，换句话说接口中的所有方法都是抽象方法。接口主要负责定义一个类的结构，接口可以去限制一个对象的接口，对象只有包含接口中定义的所有属性和方法时才能匹配接口。同时，可以让一个类去实现接口，实现接口时类中要保护接口中的所有属性

**利用接口检查对象类型**：

```typescript
interface Person{
    name: string;
    sayHello():void;
}

function fn(per: Person){
    per.sayHello();
}

fn({name:'孙悟空', sayHello() {console.log(`Hello, 我是 ${this.name}`)}});
```

**实现接口**：

```typescript
interface Person{
    name: string;
    sayHello():void;
}

class Student implements Person{
    constructor(public name: string) {
    }

    sayHello() {
        console.log('大家好，我是'+this.name);
    }
}
```

`src\06-Interface.ts`：

```typescript
(function () {

    // 描述一个对象的类型
    type myType = {
        name: string,
        age: number
    };

    /*
    *   接口用来定义一个类结构，用来定义一个类中应该包含哪些属性和方法
    *       同时接口也可以当成类型声明去使用
    * */
    interface myInterface {
        name: string;
        age: number;
    }

    interface myInterface {
        gender: string;
    }

    // const obj: myInterface = {
    //     name: 'sss',
    //     age: 111,
    //     gender: '男'
    // };

    /*
    * 接口可以在定义类的时候去限制类的结构，
    *   接口中的所有的属性都不能有实际的值
    *   接口只定义对象的结构，而不考虑实际值
    *       在接口中所有的方法都是抽象方法
    *
    * */
    interface myInter{
        name: string;

        sayHello():void;
    }

    /*
    * 定义类时，可以使类去实现一个接口,
    *   实现接口就是使类满足接口的要求
    * */
    class MyClass implements myInter{
        name: string;

        constructor(name: string) {
            this.name = name;
        }

        sayHello(){
            console.log('大家好~~');
        }

    }

})();
```





### 5.6 泛型

**泛型**：定义一个函数或类时，有些情况下无法确定其中要使用的具体类型（返回值、参数、属性的类型不能确定），此时泛型便能够发挥作用

**any类型的弊端**：下列中，test函数有一个参数类型不确定，但是能确定的是其返回值的类型和参数的类型是相同的，由于类型不确定所以参数和返回值均使用了any，但是很明显这样做是不合适的，首先使用any会关闭TS的类型检查，其次这样设置也不能体现出参数和返回值是相同的类型

```typescript
function test(arg: any): any{
	return arg;
}
```

**泛型的使用**：

1.`<T>`就是泛型，T是给这个类型起的名字（不一定非叫T），设置泛型后即可在函数中使用T来表示该类型。所以泛型其实很好理解，就表示某个类型

```typescript
// 泛型函数的定义
function test<T>(arg: T): T{
	return arg;
}

// 泛型函数的使用
// 方式一: 直接使用
test(10)
// 方式二: 指定类型
test<number>(10)
```

2.可以同时指定多个泛型，泛型间使用逗号隔开：

```typescript
function test<T, K>(a: T, b: K): K{
    return b;
}
// 使用泛型时，完全可以将泛型当成是一个普通的类去使用
test<number, string>(10, "hello");
```

3.类中同样可以使用泛型

```typescript
class MyClass<T>{
    prop: T;

    constructor(prop: T){
        this.prop = prop;
    }
}
```

4.可以对泛型的范围进行约束

```typescript
// 使用T extends MyInter表示泛型T必须是MyInter的子类
// 不一定非要使用接口,类和抽象类同样适用
interface MyInter{
    length: number;
}

function test<T extends MyInter>(arg: T): number{
    return arg.length;
}
```





`src\08-Generic.ts`：

```typescript
/*
function fn(a: any): any{
    return a;
}*/

/*
*   在定义函数或是类时，如果遇到类型不明确就可以使用泛型
*
* */

function fn<T>(a: T): T {
    return a;
}

// 可以直接调用具有泛型的函数
let result = fn(10); // 不指定泛型，TS可以自动对类型进行推断
let result2 = fn<string>('hello'); // 指定泛型

// 泛型可以同时指定多个
function fn2<T, K>(a: T, b: K): T {
    console.log(b);
    return a;
}
fn2<number, string>(123, 'hello');

interface Inter {
    length: number;
}

// T extends Inter 表示泛型T必须是Inter实现类（子类）
function fn3<T extends Inter>(a: T): number {
    return a.length;
}

class MyClass<T> {
    name: T;
    constructor(name: T) {
        this.name = name;
    }
}

const mc = new MyClass<string>('孙悟空');
```

## 6.贪吃蛇游戏

### 6.1 项目搭建

一、**创建项目根目录**：`06-Greedy-Snake-Game`

二、**添加配置文件**：`tsconfig.json`、`package.json`、`webpack.config.js`

1.`tsconfig.json`：

```json
{
  "compilerOptions": {
    "module": "ES2015",
    "target": "ES2015",
    "strict": true,
    "noEmitOnError": true
  }
}
```

2.`package.json`：

```json
{
  "name": "06greedysnakegame",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "build": "webpack",
    "start": "webpack serve --open chrome.exe"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "@babel/core": "^7.12.9",
    "@babel/preset-env": "^7.12.7",
    "babel-loader": "^8.2.2",
    "clean-webpack-plugin": "^3.0.0",
    "core-js": "^3.8.0",
    "html-webpack-plugin": "^4.5.0",
    "ts-loader": "^8.0.11",
    "typescript": "^4.1.2",
    "webpack": "^5.6.0",
    "webpack-cli": "^4.2.0",
    "webpack-dev-server": "^3.11.0"
  }
}
```

3.`webpack.config.js`：

```typescript
// 引入一个包
const path = require('path');
// 引入html插件
const HTMLWebpackPlugin = require('html-webpack-plugin');
// 引入clean插件
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

// webpack中的所有的配置信息都应该写在module.exports中
module.exports = {

    // 指定入口文件
    entry: "./src/index.ts",

    // 指定打包文件所在目录
    output: {
        // 指定打包文件的目录
        path: path.resolve(__dirname, 'dist'),
        // 打包后文件的文件
        filename: "bundle.js",

        // 告诉webpack不使用箭头
        environment:{
            arrowFunction: false
        }
    },

    // 指定webpack打包时要使用模块
    module: {
        // 指定要加载的规则
        rules: [
            {
                // test指定的是规则生效的文件
                test: /\.ts$/,
                // 要使用的loader
                use: [
                     // 配置babel
                     {
                         // 指定加载器
                         loader:"babel-loader",
                         // 设置babel
                         options: {
                             // 设置预定义的环境
                             presets:[
                                 [
                                     // 指定环境的插件
                                     "@babel/preset-env",
                                     // 配置信息
                                     {
                                         // 要兼容的目标浏览器
                                         targets:{
                                             "chrome":"58",
                                             "ie":"11"
                                         },
                                         // 指定corejs的版本
                                         "corejs":"3",
                                         // 使用corejs的方式 "usage" 表示按需加载
                                         "useBuiltIns":"usage"
                                     }
                                 ]
                             ]
                         }
                     },
                    'ts-loader'
                ],
                // 要排除的文件
                exclude: /node-modules/
            }
        ]
    },

    // 配置Webpack插件
    plugins: [
        new CleanWebpackPlugin(),
        new HTMLWebpackPlugin({
            // title: "这是一个自定义的title"
            template: "./src/index.html"
        }),
    ],

    // 用来设置引用模块
    resolve: {
        extensions: ['.ts', '.js']
    }

};
```

三、**下载依赖**：在`06-Greedy-Snake-Game`目录下运行命令`npm install`

四、在根目录`06-Greedy-Snake-Game`下创建`src`目录，在`src`下创建网页模板`index.html`

五、测试：在`src`下创建`index.ts`，编写简单的`console.log`输出语句。运行编译构建语句`npm run build`，如果在生成`dist`目录且没有报错则环境搭建成功

六、在webpack中安装CSS的插件，使得webpack可以对CSS代码进行处理

1.运行命令如下命令

```sh
npm i -D less less-loader css-loader style-loader
# -D:           --save-dev 的简写，表示将包安装为开发依赖（devDependencies）
# less:    
#               LESS 是一种 CSS 预处理器，添加了变量、嵌套、混合、函数等高级功能，编写更可维护和可复用的 CSS 代码。
#               LESS用途: 编写和编译 LESS 文件（.less）
# less-loader：  解析 CSS 文件中的 @import 和 url() 语句，并将它们转换为 require 语句。用途: 让 Webpack 能够解析 CSS 文件的依赖关系
# style-loader:  将 CSS 注入到 DOM 中，通常在开发环境中使用。使得在浏览器中可以动态加载和更新 CSS
```

2.在`webpack.config.js`文件的`rules`配置中添加如下规则：

```json
    // 省略。。。
    // 指定webpack打包时要使用模块
    module: {
        // 指定要加载的规则
        rules: [
            // 省略。。。
            // 设置less 文件的处理
            {
                test: /\.less$/, // 匹配所有 .less 文件
                use: [
                    'style-loader', // 将 CSS 注入到 DOM 中
                    'css-loader',   // 解析 CSS 文件中的 @import 和 url() 语句
                    'less-loader'   // 将 LESS 文件编译为 CSS
                ]
            }
            // 省略。。。
        ]
    },
    // 省略。。。
```

3.测试CSS样式是否可用：

3.1 创建`src\style`目录，创建`src\style\index.less`文件，定义背景样式：

```less
body {
    background-color: #bfa;
}
```

3.2 在`src\index.ts`中引入样式：

```typescript
// 引入样式
import './style/index.less';
console.log("06-Greedy-Snake-Game");
```

3.3 运行` npm run build`命令编译代码，用浏览器打开`dist`目录下的`index.html`文件，如果出现对应的背景色则代表样式引入成功



七、在项目中安装开发依赖项 `postcss`、`postcss-loader` 和 `postcss-preset-env`。这些工具和插件可以在开发过程中处理和转换 CSS 文件，使其兼容各种浏览器和环境

1.安装依赖

```sh
npm i -D postcss postcss-loader postcss-preset-env
# postcss 是一个用于对 CSS 进行转换的工具。它通过插件来实现各种功能，比如自动添加浏览器前缀、转换现代 CSS 特性等
# postcss-loader 是一个用于 Webpack 的 loader，它允许你在 Webpack 构建过程中使用 PostCSS 及其插件来处理 CSS 文件
# postcss-preset-env 是一个 PostCSS 插件集合，旨在将现s代 CSS 特性转换为大多数浏览器可以理解的形式。它包括了一系列插件来处理不同的 CSS 特点
```

2.在`webpack.config.js`中添加配置：

```json
    // 指定webpack打包时要使用模块
    module: {
        // 指定要加载的规则
        rules: [
            {
              use: [
             // 设置less 文件的处理
            {
                test: /\.less$/, // 匹配所有 .less 文件
                use: [
                    "style-loader", // 将 CSS 注入到 DOM 中
                    "css-loader",   // 解析 CSS 文件中的 @import 和 url() 语句
                    // 引入postcss
                    {
                        loader: "postcss-loader", // 使用 PostCSS 处理 CSS
                        options: {
                            postcssOptions: {
                                plugins: [
                                    [
                                        "postcss-preset-env",
                                        {
                                            browsers: 'last 3 versions'
                                        }
                                    ]
                                ]
                            }
                        }
                    },
                    'less-loader'   // 将 LESS 文件编译为 CSS
                ]
            }
        ]
    },
```

### 6.2 项目界面

![image-20240519134103497](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/typescript/image-20240519134103497.png)

`src\index.html`：游戏界面框架

```html
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>06-Greedy-Snake-Game</title>
</head>
<body>
    <!--创建游戏的主容器-->
<div id="main">
    <!--设置游戏的舞台-->
    <div id="stage">
        <!--设置蛇-->
        <div id="snake">
            <!--snake内部的div 表示蛇的各部分-->
            <div></div>
        </div>

        <!--设置食物-->
        <div id="food">
            <!--添加四个小div 来设置食物的样式-->
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>

    </div>

    <!--设置游戏的积分牌-->
    <div id="score-panel">
        <div>
            SCORE:<span id="score">0</span>
        </div>
        <div>
            level:<span id="level">1</span>
        </div>
    </div>
</body>
</html>
```

`src\style\index.less`：设置样式

```less
// 设置变量
// 在LESS(CSS预处理器)中，所有变量都以@符号开头。这个变量名是bg-color，代表背景颜色
@bg-color: #b7d4a8;

//清除默认样式
* {
    // 清除所有元素的默认外边距和内边距，确保页面布局的一致性
    margin: 0;
    padding: 0;
    // 改变盒子模型的计算方式，使得元素的宽高计算包括内边距和边框，简化布局和尺寸控制
    box-sizing: border-box;
}


body {
    font: bold 20px "Courier";
}


//设置主窗口的样式
#main {
    width: 360px;
    height: 420px;
    // 设置背景颜色
    background-color: @bg-color;
    // 设置居中
    margin: 100px auto;
    border: 10px solid black;
    // 设置圆角
    border-radius: 40px;

    // 开启弹性盒模型
    display: flex;
    // 设置主轴的方向
    flex-flow: column;
    // 设置侧轴的对齐方式
    align-items: center;
    // 设置主轴的对齐方式
    justify-content: space-around;

    // 游戏舞台
    #stage {
        width: 304px;
        height: 304px;
        border: 2px solid black;
        // 开启相对定位
        position: relative;

        // 设置蛇的样式
        #snake {
            &>div {
                width: 10px;
                height: 10px;
                background-color: #000;
                border: 1px solid @bg-color;
                // 开启绝对定位
                position: absolute;
            }
        }

        // 设置食物
        #food {
            width: 10px;
            height: 10px;
            position: absolute;
            left: 40px;
            top: 100px;

            // 开启弹性盒
            display: flex;
            // 设置横轴为主轴，wrap表示会自动换行
            flex-flow: row wrap;

            // 设置主轴和侧轴的空白空间分配到元素之间
            justify-content: space-between;
            align-content: space-between;

            &>div {
                width: 4px;
                height: 4px;
                background-color: black;

                // 使四个div旋转45度
                transform: rotate(45deg);
            }
        }
    }

    // 记分牌
    #score-panel {
        width: 300px;
        display: flex;
        // 设置主轴的对齐方式
        justify-content: space-between;
    }
}
```

### 6.3 ScorePanel类

`src\modules\ScorePanel.ts`：

```typescript
// 定义表示记分牌的类
class ScorePanel {
    // score和level用来记录分数和等级
    score = 0;
    level = 1;

    // 分数和等级所在的元素，在构造函数中进行初始化
    scoreEle: HTMLElement;
    levelEle: HTMLElement;

    // 设置一个变量限制等级
    maxLevel: number;
    // 设置一个变量表示多少分时升级
    upScore: number;

    constructor(maxLevel: number = 10, upScore: number = 10) {
        this.scoreEle = document.getElementById('score')!;
        this.levelEle = document.getElementById('level')!;
        this.maxLevel = maxLevel;
        this.upScore = upScore;
    }

    //设置一个加分的方法
    addScore() {
        // 使分数自增
        this.scoreEle.innerHTML = ++this.score + '';
        // 判断分数是多少
        if (this.score % this.upScore === 0) {
            this.levelUp();
        }
    }

    // 提升等级的方法
    levelUp() {
        if (this.level < this.maxLevel) {
            this.levelEle.innerHTML = ++this.level + '';
        }
    }
}

export default ScorePanel;


// 测试代码
// const scorePanel = new ScorePanel(100, 2);
// for(let i=0; i<200; i++){
//     scorePanel.addScore();
// }
```

### 6.4 Food类

`src\modules\Food.ts`：

```typescript
// 定义食物类Food
class Food {
    // 定义一个属性表示食物所对应的元素
    element: HTMLElement;

    constructor() {
        // 获取页面中的food元素并将其赋值给element
        this.element = document.getElementById('food')!;
    }

    // 定义一个获取食物X轴坐标的方法
    get X() {
        return this.element.offsetLeft;
    }

    // 定义一个获取食物Y轴坐标的方法
    get Y() {
        return this.element.offsetTop;
    }

    // 修改食物的位置
    change() {
        // 生成一个随机的位置
        // 食物的位置最小是0 最大是290
        // 蛇移动一次就是一格，一格的大小就是10，所以就要求食物的坐标必须是整10

        let top = Math.round(Math.random() * 29) * 10;
        let left = Math.round(Math.random() * 29) * 10;

        this.element.style.left = left + 'px';
        this.element.style.top = left + 'px';
    }
}

// 测试代码
// const food =  new Food();
// console.log(food.X, food.Y);
// food.change();
// console.log(food.X, food.Y);

export default Food;
```

### 6.5 Snake类

`src\modules\Snake.ts`：

```typescript
class Snake {
    // 表示蛇头的元素
    head: HTMLElement;
    // 蛇的身体（包括蛇头）
    bodies: HTMLCollection;
    // 获取蛇的容器
    element: HTMLElement;

    constructor() {
        this.element = document.getElementById('snake')!
        this.head = document.querySelector('#snake > div') as HTMLElement;
        this.bodies = this.element.getElementsByTagName('div');
    }

    // 获取蛇的坐标（蛇头坐标）
    get X() {
        return this.head.offsetLeft;
    }

    // 获取蛇的Y轴坐标
    get Y() {
        return this.head.offsetTop;
    }

    // 设置蛇头的坐标
    set X(value) {

        // 如果新值和旧值相同，则直接返回不再修改
        if (this.X === value) {
            return;
        }

        // X的值的合法范围0-290之间
        if (value < 0 || value > 290) {
            // 进入判断说明蛇撞墙了
            throw new Error('蛇撞墙了！');
        }

        // 修改x时，是在修改水平坐标，蛇在左右移动，蛇在向左移动时，不能向右掉头，反之亦然
        if (this.bodies[1] && (this.bodies[1] as HTMLElement).offsetLeft === value) {
            // console.log('水平方向发生了掉头');
            // 如果发生了掉头，让蛇向反方向继续移动
            if (value > this.X) {
                // 如果新值value大于旧值X，则说明蛇在向右走，此时发生掉头，应该使蛇继续向左走
                value = this.X - 10;
            } else {
                // 向左走
                value = this.X + 10;
            }
        }

        // 移动身体
        this.moveBody();

        this.head.style.left = value + 'px';
        // 检查有没有撞到自己
        this.checkHeadBody();
    }

    set Y(value) {
        // 如果新值和旧值相同，则直接返回不再修改
        if (this.Y === value) {
            return;
        }

        // Y的值的合法范围0-290之间
        if (value < 0 || value > 290) {
            // 进入判断说明蛇撞墙了，抛出一个异常
            throw new Error('蛇撞墙了！');
        }

        // 修改y时，是在修改垂直坐标，蛇在上下移动，蛇在向上移动时，不能向下掉头，反之亦然
        if (this.bodies[1] && (this.bodies[1] as HTMLElement).offsetTop === value) {
            if (value > this.Y) {
                value = this.Y - 10;
            } else {
                value = this.Y + 10;
            }
        }

        // 移动身体
        this.moveBody();
        this.head.style.top = value + 'px';
        // 检查有没有撞到自己
        this.checkHeadBody();
    }

    // 蛇增加身体的方法
    addBody() {
        // 向element中添加一个div
        this.element.insertAdjacentHTML("beforeend", "<div></div>");

    }

    // 添加一个蛇身体移动的方法
    moveBody() {
        /*
        *   将后边的身体设置为前边身体的位置
        *       举例子：
        *           第4节 = 第3节的位置
        *           第3节 = 第2节的位置
        *           第2节 = 蛇头的位置
        * */
        // 遍历获取所有的身体
        for (let i = this.bodies.length - 1; i > 0; i--) {
            // 获取前边身体的位置
            let X = (this.bodies[i - 1] as HTMLElement).offsetLeft;
            let Y = (this.bodies[i - 1] as HTMLElement).offsetTop;

            // 将值设置到当前身体上
            (this.bodies[i] as HTMLElement).style.left = X + 'px';
            (this.bodies[i] as HTMLElement).style.top = Y + 'px';

        }

    }

    // 检查蛇头是否撞到身体的方法
    checkHeadBody() {
        // 获取所有的身体，检查其是否和蛇头的坐标发生重叠
        for (let i = 1; i < this.bodies.length; i++) {
            let bd = this.bodies[i] as HTMLElement;
            if (this.X === bd.offsetLeft && this.Y === bd.offsetTop) {
                // 进入判断说明蛇头撞到了身体，游戏结束
                throw new Error('撞到自己了！');
            }
        }
    }
}

export default Snake;
```

### 6.6 GameControl类

`src\modules\GameControl.ts`：

```typescript
// 引入其他的类
import Snake from "./Snake";
import Food from "./Food";
import ScorePanel from "./ScorePanel";

// 游戏控制器，控制其他的所有类
class GameControl {
    //定义三个属性
    // 蛇
    snake: Snake;
    // 食物
    food: Food;
    // 记分牌
    scorePanel: ScorePanel;
    // 创建一个属性来存储蛇的移动方向（也就是按键的方向）
    direction: string = '';
    // 创建一个属性用来记录游戏是否结束
    isLive = true;

    constructor() {
        this.snake = new Snake();
        this.food = new Food();
        this.scorePanel = new ScorePanel(10, 2);

        this.init();
    }

    // 游戏的初始化方法，调用后游戏即开始
    init() {
        // 绑定键盘按键按下的事件
        document.addEventListener('keydown', this.keydownHandler.bind(this));
        // 调用run方法，使蛇移动
        this.run();
    }

    /* chrome浏览器       IE浏览器
    *    ArrowUp           Up
         ArrowDown         Down
         ArrowLeft         Left
         ArrowRight        Right
    * */

    // 创建一个键盘按下的响应函数
    keydownHandler(event: KeyboardEvent) {
        // 需要检查event.key的值是否合法（用户是否按了正确的按键）
        // 修改direction属性
        this.direction = event.key;
    }

    // 创建一个控制蛇移动的方法
    run() {
        /*
        *   根据方向（this.direction）来使蛇的位置改变
        *       向上 top 减少
        *       向下 top 增加
        *       向左  left 减少
        *       向右  left 增加
        * */
        // 获取蛇现在坐标
        let X = this.snake.X;
        let Y = this.snake.Y;


        // 根据按键方向来修改X值和Y值
        switch (this.direction) {
            case "ArrowUp":
            case "Up":
                // 向上移动 top 减少
                Y -= 10;
                break;
            case "ArrowDown":
            case "Down":
                // 向下移动 top 增加
                Y += 10;
                break;
            case "ArrowLeft":
            case "Left":
                // 向左移动 left 减少
                X -= 10;
                break;
            case "ArrowRight":
            case "Right":
                // 向右移动 left 增加
                X += 10;
                break;
        }

        // 检查蛇是否吃到了食物
        this.checkEat(X, Y);

        //修改蛇的X和Y值
        try {
            this.snake.X = X;
            this.snake.Y = Y;
        } catch (e) {
            // 进入到catch，说明出现了异常，游戏结束，弹出一个提示信息 
            alert((e as Error).message + ' GAME OVER!');
            // 将isLive设置为false  
            this.isLive = false;
        }


        // 开启一个定时调用
        this.isLive && setTimeout(this.run.bind(this), 300 - (this.scorePanel.level - 1) * 30);

    }

    // 定义一个方法，用来检查蛇是否吃到食物
    checkEat(X: number, Y: number) {
        if (X === this.food.X && Y === this.food.Y) {
            // 食物的位置要进行重置
            this.food.change();
            // 分数增加
            this.scorePanel.addScore();
            // 蛇要增加一节
            this.snake.addBody();
        }
    }


}

export default GameControl;
```

### 6.7 游戏初始化

`src/index.ts`：结合样式和主要的游戏控制逻辑

```typescript
// 引入样式
import './style/index.less';
// import Food from './moduls/Food';
// const food =  new Food();
// console.log(food.X, food.Y);
// food.change();
// console.log(food.X, food.Y);
import GameControl from "./modules/GameControl";
const gameControl = new GameControl();

// setInterval(()=>{
//     console.log(gameControl.direction);
// }, 1000);
```



## 7.包管理工具

### 7.1 包管理工具简介

**包**：包(package)代表了一组特定功能的源码集合

**包管理工具**：管理包(package)的应用软件，可以对包(package)进行下载安装，更新， 删除，上传 等操作。借助包管理工具，可以快速开发项目，提升开发效率。包管理工具是一个通用的概念，很多编程语言都有包管理工具

**常用的包管理工具**：npm、cnpm、yarn

### 7.2 npm

**npm简介**：npm全称`Node Package Manager`，中文意思是"Node 的包管理工具"。npm是node.js官方内置的包管理工具

**npm的安装**：nodejs 在安装时会自动安装npm，所以如果已经安装了node.js，可以直接使用 npm。可以通过 `npm -v`查看版本号测试，如果显示版本号说明安装成功，反之安装失败

**nodejs安装**：所有nodejs历史版本镜像下载地址 `https://registry.npmmirror.com/binary.html?path=node`。下载到后缀为`msi`的文件后一直点击下一步即可安装成功

**npm 初始化包**：

```
1.创建一个空目录，然后以此目录作为工作目录启动命令行工具，执行npm init
2.npm init 命令的作用是将文件夹初始化为一个包】，交互式创建package.json文件
3.package.json是包的配置文件，每个包都必须要有package.json
4.初始化的过程中还有一些注意事项
     1.package name(包名)不能使用中文、大写，默认值是文件夹的名称，所以文件夹名称也不能使用中文和大写
     2.version(版本号 )要求 x.x.x的形式定义，x必须是数字，默认值是 1.0.0
     3.package.json 可以手动创建与修改
     4.使用 npm init -y或者npm init --yes 极速创建package.json
```

**npm搜索包**：搜索包的方式有两种

```
1.命令行： [npm s/search 关键字]
2.网站搜索： 网址是 https://www.npmjs.com/
```

**`npm install`命令**：

在项目协作中有一个常用的命令就是`npm i`，通过该命令可以依据`package.json`和`package-lock.json`的依赖声明安装项目依赖

```sh
npm i
npm install
# node_modules 文件夹大多数情况都不会存入版本库
```

`npm install`命令的作用：

- 安装依赖包：`npm install` 命令会读取项目根目录下的 `package.json` 文件，安装其中列出的所有依赖包
- 创建 node_modules目录：安装的依赖包会被放置在项目的 node_modules目录中
- 生成或更新 package-lock.json：此文件记录了当前安装的具体版本的依赖包，有助于确保在不同环境中安装相同的包版本

**下载安装包**：

```
1.通过npm install命令 和 npm i命令 安装包
2.语法:
        npm install <包名>
        npm i <包名>
2.示例:
        npm install uniq
        npm i uniq
3.运行之后文件夹下会增加两个资源
        node_modules 文件夹存放下载的包
        package-lock.json包的锁文件，用来锁定包的版本
4.说明:
        安装uniq之后,uniq就是当前这个包的一个依赖包，有时会简称为依赖
        比如创建一个包名字为A，A中安装了包名字是B，就说B是A的一个依赖包，也会说A依赖B

5.依赖包的导入和使用(在index.js中编写如下代码，通过node .\index.js命令运行):      
            //1.导入 uniq 包
            const uniq = require('uniq');
            //2.使用函数
            let arr =[1,2,3,4,5,2,1];
            const result = uniq(arr);
            console.log(result);
```

**require导入npm 包的基本流程**：

```
1.在当前文件夹下node_modules中寻找同名的文件夹
2.在上级目录中下的node_modules中寻找同名的文件夹，直至找到磁盘根目录
```

**开发依赖与生产依赖**：

1.生产环境与开发环境

- 开发环境是程序员专门用来写代码的环境，一般是指程序员的电脑，开发环境的项目一般只能程序员自己访问
- 生产环境是项目代码正式运行的环境，一般是指正式的服务器电脑，生产环境的项目一般每个客户都可以访问

2.生产依赖与开发依赖：可以在安装时设置选项来区分依赖的类型，目前分为两类:

| 类型     | 命令                               | 说明                                                         |
| -------- | ---------------------------------- | ------------------------------------------------------------ |
| 生产依赖 | npm i -S uniq或npm i -save uniq    | S等效于--save，-S 是默认选项。包信息保存在package.json中的dependencies 属性 |
| 开发依赖 | npm i -D less或npm i-save-dev less | D等效于 -save-dev。包信息保存在 package.json中 devDependencies 属性 |

 3.举个例子方便理解

> 比如说做蛋炒饭需要 大米 ， 油 ，葱，鸡蛋 ， 锅 ， 煤气，铲子等
>
> 其中 锅 ， 煤气 ， 铲子 属于开发依赖，只在制作阶段使用
>
> 而 大米，油，葱，鸡蛋 属于生产依赖，在制作与最终食用都会用到
>
> 所以 开发依赖 是只在开发阶段使用的依赖包，而 生产依赖 是开发阶段和最终上线运行阶段都用到的依赖包

**npm 全局安装**：

可以执行安装选项-g进行全局安装

```shell
# 全局安装完成之后就可以在命令行的任何位置运行nodemon命令
npm i -g nodemon
# npm i -g nodemon命令的作用是自动重启node应用程序
```

> 说明：
>
> 全局安装的命令不受工作目录位置影响。可以通过 npm root -g 可以查看全局安装包的位置。不是所有的包都适合全局安装，只有全局类的工具才适合，可以通过査看包的官方文档来确定安装方式

补充：`npm i -g nodemon` 是一个用于全局安装 `nodemon` 的命令。`nodemon` 是一个用于开发 Node.js 应用程序的实用工具，它会在检测到文件变更时自动重启应用程序，从而提高开发效率。全局安装 `nodemon` 后，可以在命令行中使用 `nodemon` 命令来启动 Node.js 应用程序。例如：

```bash
nodemon app.js
```

这会启动 `app.js` 文件，并在检测到文件更改时自动重启应用程序。这样就不需要手动停止和重新启动服务器，使用示例：

```bash
# 安装 nodemon
npm i -g nodemon
# 使用nodemon启动应用
nodemon app.js
```

`nodemon`额外配置：还可以创建一个 `nodemon.json` 配置文件来自定义 `nodemon` 的行为。例如：

```json
// 配置说明：nodemon只监视 src目录中的 .js 和 .json文件，并忽略 node_modules目录
{
  "watch": ["src"],
  "ext": "js,json",
  "ignore": ["node_modules"]
}
```

**npm安装指定版本的包**：项目中可能会遇到版本不匹配的情况，有时就需要安装指定版本的包，可以使用下面的命令的

```sh
## 语法
npm i<包名@版本号>
## 示例
npm i jquery@1.11.2
```

**npm删除依赖**：项目中可能需要删除某些不需要的包，可以使用下面的命令

```sh
## 局部删除
npm remove uniq
npm r uniq
## 全局删除
npm remove -g nodemon
```

**npm 配置命令别名**：通过配置命令别名可以更简单的执行命令。配置 `package.json` 中的 `scripts` 属性

```json
{
        "scripts": {
        "server": "node server.js",
        "start": "node index.js",
        }
}
```

配置完成之后，可以使用别名执行命令

```sh
npm run server
npm run start
# 不过 start 别名比较特别，使用时可以省略 run
npm start
```

> 补充说明： npm start 是项目中常用的一个命令，一般用来启动项目 npm run 有自动向上级目录查找的特性，跟 require 函数也一样。对于陌生的项目可以通过查看 scripts 属性来参考项目的一些操作




















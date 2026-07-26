<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. NVM 是什么 —— 解决 Node 版本管理难题](#1-nvm-%E6%98%AF%E4%BB%80%E4%B9%88-%E2%80%94%E2%80%94-%E8%A7%A3%E5%86%B3-node-%E7%89%88%E6%9C%AC%E7%AE%A1%E7%90%86%E9%9A%BE%E9%A2%98)
- [2. 安装与使用 NVM](#2-%E5%AE%89%E8%A3%85%E4%B8%8E%E4%BD%BF%E7%94%A8-nvm)
    - [2.1 安装前准备：彻底清理旧 Node 环境](#21-%E5%AE%89%E8%A3%85%E5%89%8D%E5%87%86%E5%A4%87%E5%BD%BB%E5%BA%95%E6%B8%85%E7%90%86%E6%97%A7-node-%E7%8E%AF%E5%A2%83)
    - [2.2 Windows / Mac 双平台安装步骤](#22-windows-mac-%E5%8F%8C%E5%B9%B3%E5%8F%B0%E5%AE%89%E8%A3%85%E6%AD%A5%E9%AA%A4)
    - [2.3 验证安装](#23-%E9%AA%8C%E8%AF%81%E5%AE%89%E8%A3%85)
    - [2.4 NVM 核心命令速查表（按场景分类）](#24-nvm-%E6%A0%B8%E5%BF%83%E5%91%BD%E4%BB%A4%E9%80%9F%E6%9F%A5%E8%A1%A8%E6%8C%89%E5%9C%BA%E6%99%AF%E5%88%86%E7%B1%BB)
- [3. 理清生态 —— Node.js、npm、pnpm、yarn、NVM 的职责边界](#3-%E7%90%86%E6%B8%85%E7%94%9F%E6%80%81-%E2%80%94%E2%80%94-nodejsnpmpnpmyarnnvm-%E7%9A%84%E8%81%8C%E8%B4%A3%E8%BE%B9%E7%95%8C)
    - [3.1 JavaScript 与 Node.js（语言与运行环境）](#31-javascript-%E4%B8%8E-nodejs%E8%AF%AD%E8%A8%80%E4%B8%8E%E8%BF%90%E8%A1%8C%E7%8E%AF%E5%A2%83)
    - [3.2 四者基础定义（先理清定位）](#32-%E5%9B%9B%E8%80%85%E5%9F%BA%E7%A1%80%E5%AE%9A%E4%B9%89%E5%85%88%E7%90%86%E6%B8%85%E5%AE%9A%E4%BD%8D)
    - [3.3 层级依赖](#33-%E5%B1%82%E7%BA%A7%E4%BE%9D%E8%B5%96)
    - [3.4 包管理器（npm / Yarn /pnpm）通用联系](#34-%E5%8C%85%E7%AE%A1%E7%90%86%E5%99%A8npm-yarn-pnpm%E9%80%9A%E7%94%A8%E8%81%94%E7%B3%BB)
- [4. 包管理器怎么选 —— npm / Yarn / pnpm 深度对比](#4-%E5%8C%85%E7%AE%A1%E7%90%86%E5%99%A8%E6%80%8E%E4%B9%88%E9%80%89-%E2%80%94%E2%80%94-npm-yarn-pnpm-%E6%B7%B1%E5%BA%A6%E5%AF%B9%E6%AF%94)
- [5. npm 使用指南（在 NVM 环境下）](#5-npm-%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97%E5%9C%A8-nvm-%E7%8E%AF%E5%A2%83%E4%B8%8B)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. NVM 是什么 —— 解决 Node 版本管理难题

全称与定位

> NVM = Node Version Manager，Node.js 多版本管理工具，用于在一台电脑安装、切换、管理多个 Node.js 环境，解决不同项目 Node
> 版本不兼容问题。

核心优势

> 1. 多版本共存：电脑同时安装 Node14/16/18/20/22，互不干扰
> 2. 环境完全隔离：每个 Node 版本全局 npm 包独立，A 项目装的包不会污染 B 项目
> 3. 一键切换版本：一行命令切换 Node，不用手动改环境变量、重装
> 4. 无系统权限依赖：安装在用户目录，Linux/macOS 无需 sudo
> 5. 项目锁定版本：支持 `.nvmrc` 文件，进入项目自动切换对应 Node 版本
> 6. 支持 LTS 长期稳定版，适配生产环境开发

工作原理

> 所有 Node 版本统一存放在 NVM 根目录；切换版本时自动修改系统 PATH，优先读取当前选中 Node 的 `node/npm`；Windows
> 通过软链接映射激活版本。

区分两个分支

> nvm-sh/nvm：官方原版，仅支持 macOS / Linux / WSL
>
> nvm-windows：第三方移植版，Windows 专用，命令语法略有差异。`nvm-windows` 部分命令参数和原版不同

## 2. 安装与使用 NVM

### 2.1 安装前准备：彻底清理旧 Node 环境

```bash
# 全程建议：关闭所有 VS Code、CMD、PowerShell、Git Bash、终端窗口，否则文件占用删不掉。

# 1.控制面板 → 卸载程序，彻底卸载所有已安装 Node.js
#    1.1 Node.js（多个版本全部卸载）
#    2.1 nvm for Windows /nvm-windows（旧版本 nvm 必须清）
#    3.2 Yarn / Yarn Classic
#    4.3 pnpm（单独安装版）
#    5.4 nvm-desktop、nvm 管理类工具
# 额外检查：有没有单独的 npm、yarn、nvm 旧版本，一并卸载（旧环境全部清干净）


# 2.删除残留目录、删除 npm 全局包 & 缓存（Roaming 漫游目录）
# 快速打开用户目录：Win + R 输入 %AppData% 直接跳转漫游文件夹
    C:\Program Files\nodejs
    C:\Program Files (x86)\nodejs
    C:\Users\你的用户名\AppData\Roaming\npm
    C:\Users\你的用户名\AppData\Roaming\npm-cache

# 3.删除 Local 本地缓存（90% 人会漏掉）
# Win+R 输入 %LOCALAPPDATA% 跳转，删除：
#    npm-cache
#    Yarn（yarn 缓存、全局目录）
#    pnpm / .pnpm-store（pnpm 存储目录）


# 4.删除用户根目录下配置文件
# Win + R 输入 %userprofile% 回车进入用户文件夹（一般是 C:\Users\张三）
# .npmrc、.yarnrc  .pnpmrc .pnpm-store 等文件
# .npmrc npm 镜像、代理配置
# .npm npm 旧缓存目录
# .yarnrc yarn 配置
# .yarn yarn 数据目录
# .pnpmrc pnpm 配置
# .npminstall_tarball 国内 cnpm 残留缓存


# 5.清理系统环境变量里所有 node/npm 路径
# Win + R 输入 sysdm.cpl 回车
# 弹出系统属性 → 顶部切换到 高级 标签
# 右下角点击 环境变量
# 窗口分两块：
#	1. 上方：用户变量（只对当前账号生效）
#	2. 下方：系统变量（全电脑生效）
#	两处都要检查清理！
# 删除所有带 nodejs、npm、pnpm、nvm、yarn 的路径条目



# 6.额外兜底清理（可选，彻底根除）
# 6.1 打开 %localappdata%（Win+R 输入）删除：npm、npm-cache、pnpm、pnpm-cache、pnpm-state、yarn 缓存文件夹
# 6.2 回收站全部清空，重启电脑一次
# 至此 Windows 所有原生 Node、npm 环境完全清除，不会和后续安装的 nvm-windows 冲突。


# 7.验证是否清理干净（关键校验步骤）
# 重新打开一个新 CMD 窗口
# 依次执行三条命令，全部提示不是内部或外部命令才算清理成功
#        node -v
#        npm -v
#        nvm version
```

### 2.2 Windows / Mac 双平台安装步骤

```bash
# ========== Windows：安装 nvm-windows ==========
# 1.下载安装包
# 官方发布页：https://github.com/coreybutler/nvm-windows/releases
# 下载 nvm-setup.exe（安装版，推荐）

# 2.运行安装程序（管理员右键打开）
#	许可协议：勾选 I accept → Next
#	NVM 安装目录：推荐 D:\nvm，禁止中文、空格路径
#	Node 软链接目录：推荐 D:\nvm\nodejs（系统 PATH 读取此处）
#	（若自定义此路径，安装完成后请务必检查系统环境变量 PATH 中是否已包含该目录，否则 node 命令无效）
#	通知订阅：全部取消勾选，Next
#	点击 Install → Finish 完成安装

# ========== Mac / Linux：安装 nvm-sh 原版 ==========
# 官方仓库：https://github.com/nvm-sh/nvm
# 使用官方安装脚本（以最新文档为准）：
# curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash
# 安装完成后重新打开终端，或 source ~/.bashrc / ~/.zshrc
```

### 2.3 验证安装

```bash
# 1.关闭所有 CMD/PowerShell，重新打开终端执行
nvm version
# 输出版本号（如 v1.2.2）即成功
# Mac/Linux 原版也可使用：nvm --version
```

### 2.4 NVM 核心命令速查表（按场景分类）

1.基础信息查看命令

```bash
# 1.查看 NVM 自身版本
# Windows / Mac Linux 通用
nvm version
# 原版Mac/Linux等价写法
nvm --version


# 2.查看当前正在使用的 Node 版本
nvm current
# 也可以直接
node -v


# 3.查看本机已安装所有 Node 版本
# Windows
nvm list
nvm ls

# Mac/Linux 原版
nvm ls
# 输出示例：

    24.5.0
    22.17.0
-> 20.18.0
# 箭头 -> 代表当前激活使用的版本
```

2.安装 Node.js 版本（核心）

```bash
# 1.查看可下载的远程 Node 版本
# Windows
nvm list available

# Mac/Linux 查看全部远程
nvm ls-remote
# 只查看LTS长期支持稳定版（推荐）
nvm ls-remote --lts


# 2. 安装指定版本 Node
# 精确版本号安装（全平台通用）
nvm install 22.17.0
# 安装最新LTS稳定版
# Windows 不支持 lts 别名，必须指定具体版本，如：
nvm install 22.17.0
# Mac/Linux
nvm install --lts
# 安装最新非稳定版（不推荐业务使用）
nvm install latest



# 3. 安装加速前置（必须配置，否则下载超时）
# 3.1 Windows 永久配置镜像。打开 NVM 安装目录下 settings.txt，添加：
node_mirror: https://npmmirror.com/mirrors/node/
npm_mirror: https://registry.npmmirror.com
# 3.2 Mac/Linux 镜像配置
echo 'export NVM_NODEJS_ORG_MIRROR=https://npmmirror.com/mirrors/node' >> ~/.zshrc
source ~/.zshrc
```

3.切换 Node 版本

```bash
# 1. 当前终端临时切换（关闭终端失效）
# 全平台通用
nvm use 22.17.0
# 简写，写大版本号自动匹配已安装
nvm use 22


# 2.设置系统永久默认版本（开机新开终端自动生效）
# Windows 专用永久设置
nvm use 24 permanent
# Mac/Linux 原版别名设置默认
nvm alias default 24
# 设置默认最新lts
nvm alias default lts/*


# 3.项目自动切换版本 .nvmrc（高频实用）
# 3.1 进入项目根目录，新建文件 .nvmrc
# 3.2 文件内只写版本号，例如：20 / 22.17.0
# 3.3 下次进入项目文件夹，直接执行命令自动匹配版本
# 注意：仅 macOS/Linux 支持 nvm use 自动读取 .nvmrc
nvm use
# Windows 必须手动指定版本：
nvm use 22.17.0
# 如果本机没有对应版本，会提示执行 nvm install 对应版本
```

4.卸载不需要的 Node 版本

```bash
# 4.卸载不需要的 Node 版本
# 全平台统一语法
nvm uninstall 18.20.0
nvm uninstall 18
# 卸载后该版本下所有全局 npm/pnpm 包会一并删除，互不影响其他 Node 环境
```

5.npm 镜像、全局包管理

```bash
# 单独设置当前 Node 的 npm 淘宝源
# 切换到对应 Node 版本后执行：
npm config set registry https://registry.npmmirror.com
# 查看源是否生效
npm config get registry
```

## 3. 理清生态 —— Node.js、npm、pnpm、yarn、NVM 的职责边界

#### 3.1 JavaScript 与 Node.js（语言与运行环境）

> JavaScript 是编程语言；Node.js 是运行 JavaScript 的后端运行环境。
>
> JS 本身只是一套语法标准，不能独立运行，必须依靠引擎 / 环境；浏览器、Node.js 是两套不同的 JS 运行载体。

> JavaScript（语言）：
>
> 1. 一套标准化编程语言（ECMAScript 规范），规定变量、函数、循环、类、Promise、async/await 等基础语法
> 2. 本身没有读写文件、操作网络、操作系统、创建进程的能力，只包含纯粹语言逻辑
> 3. 分两部分：
     >
- ECMAScript：核心语法（所有环境通用）
>    - 宿主扩展 API：由运行环境额外提供（浏览器一套、Node 一套，互不通用）

> Node.js（运行环境）：
>
> 1. 基于 Chrome 的 V8 JS 引擎开发，专门脱离浏览器、在服务器 / 本地电脑运行 JS
> 2. 组成结构三层：
     > 1）V8 引擎：解析、执行 JS 代码（和 Chrome 浏览器内核同一个引擎）
     > 2）libuv：跨平台异步 I/O 库，处理文件、网络、线程
     > 3）内置 API：Node 独有的全局模块（fs、path、http、stream、process 等）
> 3. 作用：给 JS 赋予操作系统操作能力，让 JS 可以写后端接口、脚本、桌面工具、爬虫

> 二者的联系：
>
> 1. 语法完全互通：变量、if/for、函数、箭头函数、Promise、ES6+ 语法，浏览器 JS 和 Node.js JS 写法一模一样
> 2. 共用 V8 引擎：Chrome 浏览器、Node.js 都使用 V8 解析执行 JS，执行逻辑、性能表现基本一致
> 3. 同属一套 ECMAScript 标准更新：ES2022、ES2024 等新语法，Node 和现代浏览器会同步支持
> 4. 前端全栈统一语言：页面用 JS，后端服务也能用 JS（Node），一套语言前后端通吃

```bash
# 这段代码浏览器、Node 都能跑
const a = 10
const fn = () => a + 20
console.log(fn())
```

> 核心区别（最容易混淆的点）：
>
> 1. 运行环境不同
     >
- 浏览器 JS：运行在浏览器窗口，面向页面交互
>    - Node.js JS：运行电脑 / 服务器终端，面向服务、脚本、工具
>
> 2. 全局对象完全不同
     >
- 浏览器：全局对象 `window`，有 DOM、BOM API（document、querySelector、location、alert 等）
>    - Node.js：全局对象 `global` / `globalThis`，没有 DOM/BOM；独有 API：fs、http、path、process
>
> 3. 模块系统差异
     >
- Node 默认：CommonJS 模块 `require()` / `module.exports`
>    - 浏览器原生：ES Module `import / export`
>    - 现代 Node 已全面支持 ES Module，但历史项目大量使用 CommonJS
>
> 4. 用途划分
     >
- JavaScript（浏览器端）：操作网页 DOM、表单、动画、页面交互；前端框架 Vue/React 页面渲染
>    - Node.js（后端 / 工具端）：搭建后端接口（http/express/NestJS）；本地脚本、爬虫、文件批量处理；前端工程化工具
       Vite、Webpack、npm、pnpm（全部基于 Node 运行）；小程序 / APP 服务端、中间层

```bash
# 浏览器才能用，Node 直接报错
document.getElementById('app')
alert('提示')

# Node 才能用，浏览器报错
const fs = require('fs')
fs.readFileSync('./test.txt')
```

#### 3.2 四者基础定义（先理清定位）

```bash
# 1. Node.js
# 定位：JavaScript 运行时环境
# 作用：让 JS 脱离浏览器在电脑 / 服务器运行；自带内置模块、事件、文件读写能力。
# 自带工具：安装 Node 时 默认内置 npm
# 地位：底层基础，npm /pnpm 都必须依赖 Node 才能运行



# 2. npm
# 定位：Node.js 官方自带包管理器
# 全称：Node Package Manager
# 作用：下载、安装、管理前端 / Node 项目依赖包，处理 package.json
# 归属：Node 官方出品，随 Node 一起安装。



# 3. pnpm
# 定位: 第三方高性能包管理器（替代 npm/yarn）
# 作用：和 npm 功能完全一致：安装依赖、运行脚本、管理版本。
# 定位：npm 的 优化替代品，语法和 npm 几乎通用。
# 解决 npm / Yarn 磁盘占用大、安装慢的痛点，目前企业主流选型。语法高度兼容 npm，速度、磁盘利用率、安全性全面领先。




# 4. Yarn（Facebook 推出，npm 初代替代品）
# 早期为解决 npm v5 前速度慢、锁文件不可靠诞生，分两个版本：
# Yarn Classic（v1）：老版本，目前逐步淘汰
# Yarn Berry（v2/v3/v4，现代 Yarn）：Zero Install、PnP 模式，无 node_modules



# 5. NVM
# 定位: Node.js 多版本管理工具
# 全称：Node Version Manager
# 作用：一台电脑安装多个不同版本 Node.js，一键切换；隔离多套 Node 环境。
# 不管理包，只管理 Node 本体；npm/pnpm 依附于当前激活的 Node
```

#### 3.3 层级依赖

> 一句话概括关系：NVM 管 Node；Node 自带 npm；pnpm、yarn 是 npm 的第三方替代工具，三者都跑在当前 NVM 激活的 Node 环境中

```bash
前端项目代码
    ↓ 调用
npm / pnpm / yarn （三者平级，都是包管理器，三选一使用）
    ↓ 依赖运行
Node.js（JS运行环境，每套Node自带独立npm）
    ↓ 被统一管理
NVM（多Node版本切换工具）
```

#### 3.4 包管理器（npm / Yarn /pnpm）通用联系

> 1. 核心目标完全一致 读取 `package.json`、安装 / 卸载依赖、运行 `scripts`、管理依赖版本、连接 npm 官方仓库。
> 2. 命令语法高度互通
> 3. 共用同一套软件仓库（[npmjs.org](https://npmjs.org)，国内常用 npmmirror 镜像）
> 4. 都需要安装在 Node 环境下，切换 Node 后全局工具会隔离失效

```
npm install     ≈ yarn install   ≈ pnpm install
npm run dev     ≈ yarn dev       ≈ pnpm dev
npm i -g xxx    ≈ yarn global add xxx ≈ pnpm add -g xxx
```

## 4. 包管理器怎么选 —— npm / Yarn / pnpm 深度对比

| 对比维度          | npm（官方）           | Yarn Classic(v1) | Yarn Berry(v4)        | pnpm                 |
|---------------|-------------------|------------------|-----------------------|----------------------|
| 存储机制          | 项目完整复制依赖，重复占用磁盘   | 同 npm，复制文件，有缓存优化 | PnP 不生成 node_modules  | 硬链接 + 全局单份存储，磁盘占用最低  |
| 安装速度          | 慢，大量文件拷贝          | 中等，比旧 npm 快      | 很快                    | 三者最快                 |
| node_modules  | 平铺结构，存在幽灵依赖       | 平铺结构，幽灵依赖        | 无 node_modules，PnP 映射 | 严格层级，杜绝幽灵依赖，更安全      |
| 锁文件           | package-lock.json | yarn.lock        | yarn.lock             | pnpm-lock.yaml       |
| Monorepo 多包管理 | 需要 lerna 第三方      | 内置 workspace     | 完善 workspace          | 原生完善 workspace，企业最常用 |
| 磁盘占用          | 最高                | 高                | 极低                    | 极低（比 npm 省 50%+ 空间）  |
| 全局包隔离         | 随 Node 版本隔离       | 随 Node 隔离        | 随 Node 隔离             | 缓存全局共享，全局命令随 Node 隔离 |
| 目前流行度         | 基础标配，新项目少单独使用     | 基本淘汰             | 少量新项目使用               | 行业主流、新项目首选           |
| 额外特性          | 无特殊增强             | 并行安装             | Zero Install、PnP      | 内容寻址缓存、补丁自动应用、严格依赖校验 |

## 5. npm 使用指南（在 NVM 环境下）

1.基础认知

> npm = Node Package Manager，Node.js 官方内置包管理器。
>
> - 随 Node.js 一同安装，不需要单独下载
> - 核心作用：安装、卸载、更新项目依赖、执行脚本、管理第三方库
> - 配套文件：
    >
- `package.json`：项目配置，记录依赖、脚本、项目信息
>   - `package-lock.json`：锁定依赖精确版本，保证所有人安装版本一致

> 环境关系（结合 NVM）：NVM 切换不同 Node 版本，每个 Node 自带独立 npm，全局包互相隔离。切换 Node 后，之前全局安装的工具需要重新
`npm i -g xxx`。

```bash
# 校验是否可用（新开终端执行）
# 查看 node 版本
node -v
# 查看 npm 版本
npm -v
# 输出版本号即正常
```

2.核心配置：镜像源（国内必配，解决下载慢）

```bash
# 1.查看当前源
npm config get registry
# 默认国外源：https://registry.npmjs.org/

# 2.切换淘宝镜像（npmmirror）
# 永久设置国内镜像
npm config set registry https://registry.npmmirror.com
# 切回官方源（出国使用）
npm config set registry https://registry.npmjs.org

# 3.查看所有 npm 配置
npm config list

# 4.配置文件位置
# 用户根目录 .npmrc，所有镜像、代理配置保存在这里
```

3.项目初始化 package.json

```bash
# 1.交互式初始化（一步步填写信息）
npm init
# 依次填写：包名、版本、描述、入口文件、命令、作者、开源协议，一路回车使用默认值

# 2.快速初始化（全部默认，推荐）
npm init -y
# 简写
npm init --yes
# 执行后自动生成 package.json 文件
```

4.依赖安装（最常用命令）

> `npm install`命令作用：读取当前目录下 `package.json`、锁文件（`package-lock.json`），自动下载、安装、解压项目所有依赖包，生成
`node_modules` 文件夹存放代码，同时管理版本锁定。简写：`npm i` 等价 `npm install`。

> 分类说明：
>
> 1. dependencies 生产依赖：项目运行必须的库（vue、react、axios）
> 2. devDependencies 开发依赖：仅打包 / 开发使用（vite、webpack、eslint）
> 3. 全局依赖 -g：电脑全局命令工具（@vue/cli、typescript）

> `npm install`命令分场景细分作用：
>
> 场景 1：不带任何参数 `npm install`（最常用）
>
> 1. 读取 `package.json` 里 `dependencies`（生产依赖）、`devDependencies`（开发依赖）所有包
> 2. 读取 `package-lock.json` 锁定的精确版本，严格按照该版本下载，保证团队版本统一
> 3. 下载所有依赖、递归下载子依赖，解压到 `node_modules`
> 4. 自动生成 / 更新 `package-lock.json`，记录每一层依赖完整版本
> 5. 软链接可执行命令到 `node_modules/.bin`，供 `npm run`、npx 调用
>
> 适用场景：拉取新项目、换电脑、删除 node_modules 后重装依赖。
>
> 场景 2：带包名 `npm install axios`
>
> 1. 下载指定包最新兼容版本
> 2. 自动写入 `package.json` 的 `dependencies`
> 3. 写入版本号到 `package-lock.json`
> 4. 安装到本地 `node_modules`
>
> 场景 3：带 `-D / --save-dev` `npm install vite -D`
>
> 1. 包仅用于开发、打包、编译（vite、webpack、eslint）
> 2. 存入 `devDependencies`，线上生产部署时可选择不安装
> 3. 其余下载、解压、锁版本逻辑同上
>
> 场景 4：带 `-g / --global` `npm install -g typescript`
>
> 1. 不安装到当前项目 node_modules，安装到当前 Node 全局目录
> 2. 注册系统可执行命令，终端任意目录直接调用（如 `tsc`）
> 3. 受 NVM 环境隔离：切换 Node 版本，全局包互不通用
>
> 场景 5：`npm install --production`
>
> 1. 只安装 dependencies，跳过所有 devDependencies
> 2. 用于服务器线上部署，减少依赖体积、提升安全
>
> 场景 6：指定版本安装 `npm install axios@1.7.0`
>
> 强制下载指定固定版本，写入 package.json 并锁定版本。

```bash
# 1.安装生产依赖
# 完整写法
npm install axios
# 简写 i
npm i axios
# 指定版本安装
npm i axios@1.6.0
# 同时安装多个
npm i axios vue
# 自动写入 package.json 的 dependencies

# 2.安装开发依赖
npm i vite -D
# 等价
npm install vite --save-dev
# 写入 devDependencies

# 3.全局安装工具
npm i -g @vue/cli typescript
# 重点：NVM 切换 Node 后，全局包失效，需要重新全局安装

# 4.根据 package.json 批量安装所有依赖
# 克隆别人项目、拉取代码后，无 node_modules 时执行
npm i
# 等价 npm install
# 读取 package.json + package-lock.json 安装完全匹配的版本

# 5.安装所有依赖并跳过开发依赖（线上部署）
npm i --production
# 只安装 dependencies，不装 devDependencies，减小服务器体积
```

5.卸载依赖

```bash
# 1.卸载项目本地依赖
# 卸载生产依赖
npm uninstall axios
# 简写 un
npm un axios
# 卸载开发依赖
npm un vite -D

# 2.卸载全局工具
npm un -g @vue/cli
```

6.更新依赖

```bash
# 1.更新单个包
# 更新到最新兼容版本
npm update axios
# 强制升级到最新大版本
npm install axios@latest

# 2.查看可更新的包
npm outdated
# 会列出：当前版本、锁定版本、最新版本
```

7.运行 package.json 脚本 scripts

> `npm run` 核心作用：是 npm scripts 的执行命令，读取项目根目录 `package.json` 中 `scripts` 字段定义的脚本命令，调用系统
> Shell 执行。
>
> - 简写：`npm run xxx`
> - 特殊简写：`start` / `test` / `stop` / `restart` 可省略 run，直接 `npm start`、`npm test`
> - 自动扩容环境变量：执行时会把 `node_modules/.bin` 加入临时 PATH，无需全局安装依赖，本地装的包命令可直接调用

> 四大核心用途：
>
> 1. 封装长、复杂命令，简化输入
> 2. 统一团队构建 / 启动 / 打包 / 格式化脚本，规范项目操作
> 3. 区分开发、测试、生产环境执行不同逻辑
> 4. 串联多命令（并行、串行执行）

> 示例 `package.json` scripts 基础结构：
>
> ```json
> {
>   "name": "demo-project",
>   "scripts": {
>     "dev": "vite",
>     "build": "tsc && vite build",
>     "lint": "eslint src/**/*.ts",
>     "fix": "eslint src --fix",
>     "serve": "vite preview"
>   }
> }
> ```

```bash
# 执行脚本
npm run dev
npm run build
npm run lint
npm run preview

# 特殊简写
# npm start 等价 npm run start
# npm test 等价 npm run test
```

> 关键特性深度解读：
>
> 特性 1：自动识别本地 .bin 命令（最重要）
>
> 项目安装 `vite`、`eslint` 等依赖后，二进制文件存在 `node_modules/.bin/`，系统默认找不到。`npm run`
> 执行时临时追加该目录到环境变量，不用全局安装包。

```bash
# 不使用 npm run，必须写完整路径
./node_modules/.bin/vite

# 使用 npm run，直接简写
npm run dev
```

> 特性 2：命令传参
>
> 在脚本后加 `--` 分隔，后面参数会传递给脚本内命令。例如 scripts 中 `"lint": "eslint src"`，传递 `--fix` 参数：

```bash
npm run lint -- --fix
# 等价于执行：eslint src --fix
```

> 特性 3：环境变量注入
>
> npm 内置大量环境变量可在脚本中读取：
>
> - `npm_package_xxx`：读取 package.json 字段
> - `NODE_ENV`：最常用，区分环境

```bash
# 打印项目名称（scripts 中："echo-name": "echo $npm_package_name"）
npm run echo-name

# 环境变量示例 scripts：
# "dev": "NODE_ENV=development vite"
# "build": "NODE_ENV=production vite build"
```

> 特性 4：命令串联（串行 && 并行 &）
>
> 1. 串行执行 `&&`：前一个命令成功才执行下一个，失败终止。例如 `"build": "tsc && vite build"`，先 TS 类型编译无报错 →
     再打包；tsc 报错直接终止打包。
> 2. 并行执行 `&`（Windows 不兼容）：同时启动多个服务，例如 `"dev-all": "vite & json-server db.json --port 3000"`。Windows
     并行推荐工具：`concurrently`

> 特性 5：跨平台兼容问题
>
> - Mac/Linux Shell：`&&`、`&`、`export NODE_ENV=xxx`
> - Windows cmd/powershell：语法不一致
> - 解决方案：使用 `cross-env` 统一环境变量、`concurrently` 统一并行命令

```bash
# 安装跨平台工具
npm i cross-env concurrently -D

# scripts 改造（全平台通用）示意：
# "dev": "cross-env NODE_ENV=development vite"
# "dev-all": "concurrently \"vite\" \"json-server db.json -p 3000\""
```

> 分场景完整实操案例：
>
> 案例 1：Vue/Vite 前端项目标准 scripts
>
> ```json
> {
>   "scripts": {
>     "dev": "cross-env NODE_ENV=development vite --open",
>     "build": "cross-env NODE_ENV=production tsc && vite build",
>     "preview": "vite preview --port 4173",
>     "lint": "eslint src/**/*.{vue,ts}",
>     "lint:fix": "eslint src --fix",
>     "format": "prettier --write src/**/*.{vue,ts,js,css}"
>   }
> }
> ```
>
> 逐条解读：
>
> 1. `npm run dev`：cross-env 统一设置开发环境变量，打开浏览器启动热更新开发服务
> 2. `npm run build`：先执行 tsc 做 TS 类型检查，无报错后执行 vite 生产打包，输出 dist
> 3. `npm run lint:fix`：调用本地 eslint，自动修复可修复的代码规范错误
>
> 案例 2：Node.js 后端项目脚本
>
> ```json
> {
>   "scripts": {
>     "dev": "cross-env NODE_ENV=development nodemon src/index.ts",
>     "start": "cross-env NODE_ENV=production node dist/index.js",
>     "build": "tsc",
>     "db:migrate": "prisma migrate dev",
>     "db:reset": "prisma db push && prisma generate"
>   }
> }
> ```
>
> 解读：
>
> - `npm run dev`：nodemon 监听文件自动重启，开发服务
> - `npm start`：生产环境运行编译后的 js 文件（start 可省略 run）
> - `npm run db:migrate`：执行数据库迁移脚本，封装 prisma 长命令
>
> 案例 3：多命令串行 + 传参实战
>
> 需求：打包前先校验代码，打包完成输出打包路径。scripts：
`"build-all": "npm run lint && cross-env NODE_ENV=production vite build && echo 打包完成，输出目录dist"`

```bash
npm run build-all
# 执行流程：
# 1.运行 lint 校验代码
# 2.校验通过，设置生产环境打包
# 3.打包成功后控制台打印提示
```

> 案例 4：并行启动前端 + mock 服务（concurrently）

```bash
# 安装依赖
npm i concurrently json-server -D

# scripts 示意：
# "serve:front": "vite --port 5173"
# "serve:mock": "json-server mock/db.json --port 3000"
# "dev:all": "concurrently \"npm run serve:front\" \"npm run serve:mock\""

# 同时启动前端开发服务和 mock 接口服务，跨平台兼容
npm run dev:all
```

8.npx 临时调用工具（无需全局安装）

> 场景：只想临时使用工具，不想全局安装污染环境。原理：npx 会自动去本地依赖下载临时包并执行。

```bash
# 临时调用 create-vue 创建项目，用完不留在本地
npx create-vue@latest
# 执行本地 node_modules 里的命令
npx vite
```

9.依赖查看相关命令

```bash
# 1.列出本地已安装所有依赖
npm ls
# 只看一级依赖（简化输出）
npm ls --depth=0

# 2.查看某个包详细信息
npm info axios
# 查看最新版本
npm view axios version
```

10.缓存管理

> npm 下载的包会存在本地缓存目录，重复安装不用重新下载。

```bash
# 1.查看缓存目录
npm config get cache

# 2.清空缓存（下载异常、包损坏时使用）
npm cache clean --force
```

11.版本语义化规则（semver）

> `package.json` 中版本号格式 `主版本.次版本.补丁`，例如 `^1.2.3`
>
> - `^1.2.3`：锁定主版本 1，自动升级次版本 / 补丁（1.x.x）
> - `~1.2.3`：锁定主 + 次版本，仅升级补丁（1.2.x）
> - `1.2.3`：固定死版本，绝不升级

12.lock 文件作用

> `package-lock.json`：
>
> 1. 精确记录每一个依赖、子依赖的安装版本
> 2. 团队所有人、服务器安装依赖版本完全一致，避免「本地能跑线上报错」
> 3. 必须提交到 git，不要删除

13.npm 全局路径管理（Windows NVM 用户重点）

```bash
# 1.查看全局包安装路径
npm config get prefix

# 2.NVM 自动控制全局路径，切换 Node 自动切换全局目录，互不干扰

# 3.问题：全局命令找不到
# 关闭终端重新打开
# 确认当前 Node 环境下重新执行 npm i -g xxx
```

14.常见实操完整流程

```bash
# 流程 1：新建前端项目
# 1.创建文件夹并进入
mkdir demo && cd demo
# 2.初始化
npm init -y
# 3.安装生产依赖
npm i vue axios
# 4.安装开发依赖
npm i vite -D
# 5.配置 scripts 后启动
npm run dev

# 流程 2：拉取已有项目运行
# 1.克隆代码后进入项目
cd xxx-project
# 2.安装全部依赖
npm i
# 3.启动开发服务
npm run dev
# 4.打包部署
npm run build

# 流程 3：全局安装工具
npm i -g typescript
tsc -v
```

15.高频踩坑解决方案

> 1. npm install 下载超时卡住：执行 `npm config set registry https://registry.npmmirror.com` 切换国内镜像
> 2. 切换 NVM Node 后全局命令不存在：当前 Node 环境重新全局安装 `npm i -g xxx`
> 3. node_modules 损坏、报错：删除 `node_modules` + `package-lock.json`，执行 `npm cache clean --force`，再 `npm i`
> 4. 幽灵依赖（没声明却能导入包）：npm 平铺 `node_modules` 特性导致，推荐改用 pnpm 规避
> 5. 权限报错 Windows：右键终端「以管理员身份运行」；mac/linux 避免乱用 sudo

16.npm、pnpm 命令对照表（快速迁移）

| 功能    | npm           | pnpm              |
|-------|---------------|-------------------|
| 安装依赖  | npm i         | pnpm i            |
| 安装生产包 | npm i axios   | pnpm add axios    |
| 安装开发包 | npm i vite -D | pnpm add vite -D  |
| 全局安装  | npm i -g xxx  | pnpm add -g xxx   |
| 卸载    | npm un axios  | pnpm remove axios |
| 运行脚本  | npm run dev   | pnpm dev          |
| 批量安装  | npm i         | pnpm i            |


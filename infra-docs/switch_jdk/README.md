在 windows系统同时装 JDK8/17/21 并快速切换，核心是：**每个版本单独设一个环境变量 + 一个总 JAVA_HOME 来切换指向 + Path 只引用 JAVA_HOME**

### 1.准备工作：安装并清理冲突

1.下载并解压 / 安装三个 JDK

```bash
# 1.下载网址链接
https://www.oracle.com/java/technologies/downloads/#java17-windows
https://www.oracle.com/java/technologies/downloads/#jdk21-windows


# 2. 选择 Windows x64 ZIP
# 建议用 ZIP 解压版，不写注册表
# 找到 Windows → x64 Compressed Archive（zip）
# 文件名：jdk-17.0.11-windows-x64.zip，直接下载
```

2.解压到安装路径

```bash
# 示例路径（不要有中文、空格）
JDK8：	D:\jdk\jdk8
JDK17：	D:\jdk\jdk17
JDK21：	D:\jdk\jdk21
```

3.必做：删除 Oracle 自动生成的 javapath（否则切换不生效）

```bash
# 删除这两个目录（有的话）
C:\Program Files (x86)\Common Files\Oracle\Java\javapath
C:\Program Files\Common Files\Oracle\Java\javapath
```

### 2.配置环境变量（系统变量）

1.打开环境变量：右键「此电脑」→ 属性 → 高级系统设置 → 环境变量（全部在**系统变量**里操作）

2.为每个版本建单独变量：

|     变量名     | 变量值（按实际路径） |
| :------------: | :------------------: |
| `JAVA_8_HOME`  |    `D:\jdk\jdk8`     |
| `JAVA_17_HOME` |    `D:\jdk\jdk17`    |
| `JAVA_21_HOME` |    `D:\jdk\jdk21`    |

3.总变量 JAVA_HOME

```bash
# 新建或编辑
# 总变量 JAVA_HOME（用来切换）
变量名：JAVA_HOME
变量值：%JAVA_8_HOME%（默认先用 JDK8）
```

### 3.配置 Path

编辑系统变量 `Path`：

```bash
# 新增一条并上移到最顶
%JAVA_HOME%\bin
```

删除所有其他直接写死 java 路径的项（如 C:\Program Files\Java\...）

```bash
# 删除这两个目录（有的话）
C:\Program Files (x86)\Common Files\Oracle\Java\javapath
C:\Program Files\Common Files\Oracle\Java\javapath
```

### 4.配置 CLASSPATH

```bash
# CLASSPATH（可选，建议加上）
变量名：CLASSPATH
变量值： .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar
```

### 5.切换 JDK 的三种方法

方法 1：手动切换

```bash
# 1.打开环境变量 → 编辑 JAVA_HOME
        切 JDK8：%JAVA_8_HOME%
        切 JDK17：%JAVA_17_HOME%
        切 JDK21：%JAVA_21_HOME%
# 2.关闭所有旧 CMD/PowerShell，新开窗口

# 3.验证：
    java -version
    javac -version
```

### 6.常用命令

```bash
# 1. 查看当前 JDK 版本
java -version

# 2. 查看 JAVA_HOME 指向的路径
# 如果已正确配置 JAVA_HOME 环境变量，可以直接显示其路径：
echo %JAVA_HOME%

# 3. 查看当前 java 命令实际对应的可执行文件路径
# 有时即使设置了 JAVA_HOME，Path 中可能有多个 Java 条目，导致实际运行的版本与预期不同。使用 where 命令可以按优先级列出所有在 Path 中能找到的 java.exe 位置
# 如果 JAVA_HOME 未设置。如果 echo %JAVA_HOME% 显示为空，说明系统未定义该变量。此时 JDK 路径可以通过 where java 的第一条结果反推
where java

# 输出示例（第一行就是当前优先使用的版本）
C:\User\win>where java
D:\tools\jdk\jdk-8u261-windows-x64\bin\java.exe
C:\Program Files (x86)\Common Files\Oracle\Java\javapath\java.exe


# 4.常见坑与检查清单
✅ Path 里 %JAVA_HOME%\bin 必须在最前面
✅ 删干净 javapath 目录
✅ 切换后必须新开终端
✅ 用 where java 检查：第一行必须是当前 JDK 的 bin 路径
```










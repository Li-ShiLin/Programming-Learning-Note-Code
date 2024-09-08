## 性能监控与调优

## 1.性能监控与调优概述

面试题

```
JVM性能调优都做了什么
有做过JVM内存优化吗
从SQL、JVM、架构、数据库四个方面讲讲优化思路
JVM的编译优化
jvm性能调优都做了什么
JVM诊断调优工具用过哪些
Jvm怎样调优，堆内存、栈空间设置多少合适
JVM相关的分析工具使用过的有哪些? 具体的性能调优步如何
如何进行JVM调优?有哪些方法?
如何理解内存泄漏问题?有哪些情况会导致内存泄漏?如何解决?
JVM如何调优、参数怎么调?
JVM诊断调优工具用过哪些
每秒几十万并发的秒杀系统为什么会频繁发生GC
日均百万级交易系统如何优化JVM
线上生产系统OOM如何监控及定位与解决
高并发系统如何基于G1垃圾回收器优化性能
```

生产环境中的问题

```
- 生产环境发生了内存溢出该如何处理?
- 生产环境应该给服务器分配多少内存合适?
- 如何对垃圾回收器的性能进行调优?
- 生产环境CPU负载飙高该如何处理?
- 生产环境应该给应用分配多少线程合适?
- 不加log，如何确定请求是否执行了某一行代码?
- 不加log，如何实时查看某个方法的入参与返回值?
```

为什么要调优

```
- 防止出现OOM
- 解决OOM
- 减少Full GC出现的频率
```

不同阶段的考虑

```
- 上线前
- 项目运行阶段
- 线上出现OOM
```

调优概述

```
- 监控的依据
      - 运行日志
      - 异常堆栈
      - GC日志
      - 线程快照
      - 堆转储快照

- 调优的大方向
      - 合理地编写代码
      - 充分并合理的使用硬件资源
      - 合理地进行JVM调优
```

性能优化的步骤：

```
第1步(发现问题): 性能监控
一种以非强行或者入侵方式收集或查看应用运营性能数据的活动。
监控通常是指一种在生产、质量评估或者开发环境下实施的带有预防或主动性的活动
当应用相关干系人提出性能问题却没有提供足够多的线索时，首先我们需要进行性能监控，随后是性能分析

                GC 频繁
                cpu load过高
                OOM
                内存泄漏
                死锁
                程序响应时间较长


第2步(排查问题):性能分析
一种以侵入方式收集运行性能数据的活动，它会影响应用的吞吐量或响应性
性能分析是针对性能问题的答复结果，关注的范围通常比性能监控更加集中
性能分析很少在生产环境下进行，通常是在质量评估、系统测试或者开发环境下进行，是性能监控之后的步骤

                打印GC日志，通过GCviewer或者http://gceasy.io来分析日志信息
                灵活运用命令行工具，jstack，jmap，jinfo等
                dump出堆文件，便用内存分析工具分析文件
                便用阿里Arthas，或jconsole，JVisualVM来实时查看JVM状态
                jstack查看堆栈信息


第3步(解决问题):性能调优
一种为改善应用响应性或香吐量而更改参数、源代码、属性配置的活动，性能调优是在性能监控、性能分析之后的活动

                适当增加内存，根据业务背景选择垃圾回收器
                优化代码，控制内存使用
                增加机器，分散节点压力
                合理设置线程池线程数量
                使用中间件提高程序效率，比如缓存，消息队列等
                其他
```

性能评价/测试指标

1. 停顿时间(或响应时间)：提交请求和返回该请求的响应之间使用的时间，一般比较关注平均响应时间。在垃圾回收环节中的暂停时间:  执行垃圾收集时，程序的工作线程被暂停的时间。`-XX:MaxGCPauseMillis`
2. 吞吐量：对单位时间内完成的工作量(请求)的量度。在GC中运行用户代码的时间占总运行时间的比例(总运行时间：程序的运行时间+内存回收的时间)。吞吐量为1-1/(1*n)。-xxGCTimeRatio=n
3. 并发数：同一时刻，对服务器有实际交互的请求数
4. 内存占用：Java 堆区所占的内存大小
5. 相互间的关系：以高速公路通行状况为例
   - 吞吐量：每天通过高速公路收费站的车辆的数据(也可以理解为收费站收取的高速费)
   - 并发数：高速公路上正在行驶的车辆的数目
   - 响应时间：车速

## 2.JVM监控及诊断工具

性能诊断是软件工程师在日常工作中需要经常面对和解决的问题，在用户体验至上的今天，解决好应用的性能问题能带来非常大的收益

Java 作为最流行的编程语言之一，其应用性能诊断一直受到业界广泛关注。可能造成Java 应用出现性能问题的因素非常多，例如线程控制、磁盘读写、数据库访问、网络I/O、垃圾收集等。想要定位这些问题，一款优秀的性能诊断工具必不可少

体会1：使用数据说明问题，使用知识分析问题，使用工具处理问题

体会2：无监控、不调优

### 2.1 命令行工具

简单命令行工具：

- 刚接触java学习的时候，最先了解的两个命令就是javac，java，除此之外，还有没有其他的命令可以供我们使用呢?
- 进入到安装jdk的bin目录，发现还有一系列辅助工具。这些辅助工具用来获取目标JVM不同方面、不同层次的信息，帮助开发人员很好地解决Java应用程序的一些疑难杂症

##### 2.1.1 jps查看正在运行的Java进程

jps（Java Process Status）

- 显示指定系统内所有的HotSpot虚拟机进程(查看虚拟机进程信息)，可用于查询正在运行的虚拟机进程

- 说明：对于本地虚拟机进程来说，进程的本地虚拟机ID与操作系统的进程ID是一致的，是唯一的

基本使用语法：

- jps [options] [hostid]
- 可以通过追加参数，来打印额外的信息

options参数：

- -q：仅仅显示LVMID(1ocal virtual machine id)，即本地虚拟机唯一id。不显示主类的名称等

- -l：输出应用程序主类的全类名 或 如果进程执行的是jar包，则输出jar完整路径

- -m：输出虚拟机进程启动时传递给主类main()的参装

- -v：列出虚拟机进程启动时的JVM参数。比如：-Xms20m-Xmx50m是启动程序指定的jvm参数
- 说明：以上参数可以综合使用
- 补充：如果某 Java 进程关闭了默认开启的UsePerfData参数(即使用参数-XX:-UsePerfData)，那么jps命令(以及下面介绍的jstat)将无法探知该Java 进程

hostid参数：

- RMI注册表中注册的主机名
- 如果想要远程监控主机上的 java 程序，需要安装 jstatd
- 对于具有更严格的安全实践的网络场所而言，可能使用一个自定义的策略文件来显示对特定的可信主机或网络的访问，尽管这种技术容易受到IP地址欺诈攻击
- 如果安全问题无法使用一个定制的策略文件来处理，那么最安全的操作是不运行jstatd服务器，而是在本地使用jstat和jps工具

##### 2.1.2 jstat查看JVM统计信息

jstat(JVM statistics Monitoring Tool)：

- 用于监视虚拟机各种运行状态信息的命令行工具。它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据
- 在没有GUI图形界面，只提供了纯文本控制台环境的服务器上，它将是运行期定位虚拟机性能问题的首选工具。常用于检测垃圾回收问题以及内存泄漏问题
- 官方文档：[jstat (oracle.com)](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html)

基本使用语法：

- `jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]`

查看命令相关参数：

- jstat -h 或 jstat -help

**选项option**：

- 类装载相关的：
  - -class：显示ClassLoader的相关信息：类的装载、卸载数量、总空间、类装载所消耗的时间等

- 垃圾回收相关的：
  - -gc：显示与GC相关的堆信息。包括Eden区、两个Survivor区、老年代、永久代等的容量、已用空间、GC时间合计等信息
  - -gccapacity：显示内容与-gc基本相同，但输出主要关注Java堆各个区域使用到的最大、最小空间
  - -gcutil：显示内容与-gc基本相同，但输出主要关注已使用空间占总空间的百分比
  - -gccause：与-gcutil功能一样，但是会额外输出导致最后一次或当前正在发生的GC产生的原因
  - -gcnew：显示新生代GC状况
  - -gcnewcapacity：显示内容与-gcnew基本相同，输出主要关注使用到的最大、最小空间
  - -geold：显示老年代GC状况

- JIT相关的：
  - -compiler：显示JIT编译器编译过的方法、耗时等信息
  - -printcompilation：输出已经被JIT编译的方法

interval参数：

- 用于指定输出统计数据的周期，单位为毫秒，即：查询间隔

count参数：

- 用于指定查询的总次数

-t参数：

- 可以在输出信息前加上一个Timestamp列，显示程序的运行时间。单位：秒
- 经验：可以比较 Java 进程的启动时间以及总 GC 时间(GCT 列)，或者两次测量的间隔时间以及总 GC 时间的增量，来得出 GC 时间占运行时间的比例。如果该比例超过 20%，则说明目前堆的压力较大。如果该比例超过90%，则说明堆里几乎没有可用空间，随时都可能抛出 OOM 异常

-h参数：

- 可以在周期性数据输出时，输出多少行数据后输出一个表头信息

jstat还可以用来判断是否出现内存泄漏：

- 第1步：在长时间运行的 Java 程序中，可以运行jstat命令连续获取多行性能数据，并取这几行数据中 OU 列(即己占用的老年代内存)的最小值

- 第2步：然后，每隔一段较长的时间重复一次上述操作，来获得多组OU 最小值。如果这些值呈上涨趋势，则说明该 Java 程序的老年代内存已使用量在不断上涨，这意味着无法回收的对象在不断增加，因此很有可能存在内存泄漏

`jstat -gc`命令演示：

```shell
C:\Users\2>jps
21128
22952 Jps

# -gc：显示与GC相关的堆信息。包括Eden区、两个Survivor区、老年代、永久代等的容量、已用空间、GC时间合计等信息
C:\Users\2>jstat -gc 21128
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 0.0   2048.0  0.0   1343.3 503808.0 128000.0  442368.0   355392.0  498176.0 492455.9 66816.0 64377.9    278    1.443   0      0.000    1.443
 
jstat -gc 参数解读:
新生代相关
        S0C是第一个幸存者区的大小(字节)
        S1C是第二个幸存者区的大小(字节)
        S0U是第一个幸存者区已使用的大小(字节)
        S1U是第二个幸存者区已便用的大小(字节)
        EC是Eden空间的大小(字节)
        EU是Eden空间已使用大小(字节)

老年代相关
        OC是老年代的大小(字节)
        OU是老年代已使用的大小(字节)

方法区(元空间)相关
        MC是方法区的大小
        MU是方法区已使用的大小
        CCSC是压编类空间的大小
        CCSU是压缩类空间已使用的大小
        
其他
        YGC是指从应用程序启动到采样时young gc次数
        VGCT是指从应用程序启动到采样时young
        gc消耗的时间(秒)
        FGC是指从应用程序启动到采样时full gc次数
        FGCT是指从应用程序启动到采样时full
        gc消耗的时间(秒)
        GCT是指从应用程序启动到采样时qc的总时间
```

##### 2.1.3 jinfo实时查看和修改JVM配置参数

jinfo(Configuration Info for Java)：

- 查看虚拟机配置参数信息，也可用于调整虚拟机的配置参数
- 在很多情况下，Java应用程序不会指定所有的ava虚拟机参数。而此时，开发人员可能不知道某一个具体的Java虚拟机参数的默认值。在这种情况下，可能需要通过查找文档获取某个参数的默认值。这个查找过程可能是非常艰难的。但有了jinfo工具，开发人员可以很方便地找到Java虚拟机参数的当前值
- 官方帮助文档：[jinfo (oracle.com)](https://docs.oracle.com/en/java/javase/11/tools/jinfo.html)

基本使用语法：

- jinfo [ options ] pid
- 说明：java 进程ID 必须要加上

| 选项             | 选项说明                                                     |
| ---------------- | ------------------------------------------------------------ |
| no option        | 输出全部的参数和系统属性                                     |
| -flag name       | 输出对应名称的参数                                           |
| -flag [+-]name   | 开启或者关闭对应名称的参数 只有被标记为manageable的参数才可以被动态修改 |
| -flag name=value | 设定对应名称的参数                                           |
| -flag            | 输出全部的参数                                               |
| sysprops         | 输出系统属性                                                 |

修改：

- jinfo不仅可以查看运行时某一个Java虚拟机参数的实际取值，甚至可以在运行时修改部分参数，并使之立即生效
- 但是，并非所有参数都支持动态修改。参数只有被标记为manageable的flag可以被实时修改。其实，这个修改能力是极其有限的
- 可以查看被标记为manageable的参数：`java -XX:+PrintFlagsFinal -version | grep manageable`

拓展：

- java -XX:+PrintFlagsinitial    查看所有JVM参数启动的初始值
- java -XX:+PrintFlagsFinal     查看所有JVM参数的最终值
- java -XX:+PrintCommandLineFlags  查看那些已经被用户或者JVM设置过的详细的XX参数的名称和值

##### 2.1.4 jmap导出内存映像文件&内存使用情况

jmap(JVM Memory Map)：

- 可以获取dump文件(堆转储快照文件，二进制文件)，它还可以获取目标Java进程的内存相关信息，包括Java堆各区域的使用情况、堆中对象的统计信息、类加载信息等
- 开发人员可以在控制台中输入命令“jmap -help”查阅jmap工具的具体使用方式和一些标准选项配置
- 官方帮助文档：https://docs.oracle.com/en/java/javase/11/tools/jmap.html

基本使用语法：

- `jmap [option] <pid>`
- ``jmap [option] <executable <core>`
- `jmap [option][server id@]<remote server IP or hostname>`

| 选项           | 作用                                                         |
| -------------- | ------------------------------------------------------------ |
| -dump          | 生成dump文件                                                 |
| -finalizerinfo | 以ClassLoader为统计口径输出永久代的内存状态信息              |
| -heap          | 输出整个堆空间的详细信息，包括GC的使用、堆配置信息，以及内存的使用信息等 |
| -histo         | 输出堆空间中对象的统计信息，包括类、实例数量和合计容量       |
| -permstat      | 以ClassLoader为统计口径输出永久代的内存状态信息              |
| -F             | 当虚拟机进程对dump选项没有任何响应时，强制执行生成dump文件   |

说明：这些参数和linux下输入显示的命令多少会有不同，包括也受jdk版本的影响

```
-dump
        生成Java堆转储快照:  dump文件
        特别的-dump:dive只保存堆中的存活对象
-heap
		输出整个堆空间的详细信息，包括GC的使用、堆配置信息，以及内存的使用信息等

-histo
        输出堆中对象的统计信息，包括类、实例数量和合计容量
        特别的:-histo:dive只统计堆中的存活对象

-pemmstat
        以ClassLoader为统计口径输出永久代的内存状态信息
        仅linux/solaris平台有效

-finalizerinfo
        显示在F-Queue中等待Finalizer线程执行finalize方法的对象
        仅linux/solaris平台有效

-f
        当虚拟机进程对-dump选项没有任何响应时，可使用此选项强制执行生成dump文件
        仅linux/solaris平台有效

-h | -help   
        jmap工具使用的帮助命令
        
-j <flag>
        传递参数给jmap启动的jvm
```



**jmap导出内存映像文件**：

- 一般来说，使用jmap指令生成dump文件的操作算得上是最常用的jmap命令之一，将堆中所有存活对象导出至一个文件之中

- Heap Dump又叫做堆存储文件，指一个Java进程在某个时间点的内存快照。Heap Dump在触发内存快照的时候会保存此刻的信息如下

  - **All Objects**：Class,fields,primitive values and references

  - **All Classes**：Classloader,name,super class,static fields

  - **Garbage Collection Roots**：Objects defined to be reachable by the JVM

  - **Thread Stacks and Local Variables**：The call-stacks of threads at the moment of the snapshot,and per-frame information about local objects

- 说明：
  - 通常在写Heap Dump文件前会触发一次Full GC，所以heap dump文件里保存的都是Full GC后留下的对象信息
  - 由于生成dump文件比较耗时，因此大家需要耐心等待，尤其是大内存镜像生成dump文件则需要耗费更长的时间来完成

- 手动的方式导出：

  - `jmap dump:fomat=b,file=<filename.hprof> <pid>`

  - `jmap -dump:live,format=b,file=<filename.hprof> <pid>`

- 自动的方式：

  - 当程序发生OOM退出系统时，一些瞬时信息都随着程序的终止而消失，而重现OOM问题往往比较困难或者耗时。此时若能在OOM时，自动导出dump文件就显得非常迫切

  - 比较常用的取得堆快照文件的方法，即使用：

    - `XX:+HeapDumpOnOutOfMemoryError`：在程序发生OOM时，导出应用程序的当前堆快照

    - `-XX:HeapDumpPath=<filename.hprof>`：-XX:HeapDumpPath可以指定堆快照的保存位置。比如：`-Xmx100m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\m.hprof`

**jmap显示堆内存相关信息**：

- `-jmap -heap pid`
- ``-jmap -histo pid`

**jmap其它作用**：

`-jmap -permstat pid`：查看系统的ClassLoader信息

`jmap -finalizerinfo`：査看堆积在finalizer队列中的对象

**总结**：

- 由于jmap将访问堆中的所有对象，为了保证在此过程中不被应用线程干扰，jmap需要借助安全点机制，让所有线程停留在不改变堆中数据的状态。也就是说，由jmap导出的堆快照必定是安全点位置的。这可能导致基于该堆快照的分析结果存在偏差
- 举个例子，假设在编译生成的机器码中，某些对象的生命周期在两个安全点之间，那么:live选项将无法探知到这些对象
- 另外，如果某个线程长时间无法跑到安全点，jmap将一直等下去。与前面讲的jstat则不同，垃圾回收器会主动将jstat所需要的摘要数据保存至固定位置之中，而jstat只需直接读取即可

##### 2.1.5 jhat  JDK自带堆分析工具

jhat(JVM Heap Analysis Tool)：

- Sun JDK提供的jhat命令与jmap命令搭配使用，用于分析jmap生成的heap dump文件(堆转储快照)。jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后， 用户可以在浏览器中查看分析结果(分析虚拟机转储快照信息)
- 使用了jhat命令，就启动了一个http服务，端口是7000，即http://localhost:7000/，就可以在浏览器里分析
- 说明：jhat命令在JDK9、JDK18中已经被删除，官方建议用VisualVM代替

基本使用语法：

- `jhat [option] [dumpfile]`

option参数：

- `-stack false|true`：关闭|打开对象分配调用栈跟踪
- `-refs false|true`：关闭|打开对象引用跟踪
- `-port port-number`：设置jhat HTTP Server的端口号，默认7000
- `-excude exclude-file`：执行对象查询时需要排除的数据成员
- `baseline exclude-file`：指定一个基准堆转储
- `debug int`：设置debug级别
- `-version`：启动后显示版本信息就退出
- `-J<flag>`：传入启动参数，比如-J-Xmx512m

命令使用案例：jhat分析herof文件

```shell
# jhat分析d:\目录下的3.herof文件
>jhat d:\3.herof
# 访问http://localhost:7000/ 即可在浏览器中进行查看

# 访问http://localhost:7000/oql/ 可以查询满足条件的对象
# 查询字符串长度大于100的对象:
# select s fron java.lang.String s where s.value. length > 100
```

![image-20240812204317347](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240812204317347.png)



##### 2.1.6 jstack打印JVM中线程快照

jstack(JvM tack Trace)：

- 用于生成虚拟机指定进程当前时刻的线程快照(虚拟机堆栈跟踪)。 线程快照就是当前虚拟机内指定进程的每一条线程正在执行的方法堆栈的集合
- 生成线程快照的作用：可用于定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等问题。这些都是导致线程长时间停顿的常见原因。当线程出现停顿时，就可以用jstack显示各个线程调用的堆情况
- 官方帮助文档：[jstack (oracle.com)](https://docs.oracle.com/en/java/javase/11/tools/jstack.html)
- 在thread dump中，要留意下面几种状态
  - 死锁，Deadlock(重点关注)
  - 等待资源，Waiting on condition(重点关注)
  - 等待获取监视器，Waiting on monitor entry(重点关注)
  - 阻塞，Blocked(重点关注)
  - 执行中，Runnable
  - 暂停，Suspended
  - 对象等待中，Object.wait()或TIMED WAITING
  - 停止，Parked

基本使用语法：

- `jstack option pid`

`jstack`管理远程进程的话，需要在远程程序的启动参数中增加：

- -Djava.rmi.server.hostname=….
- -Dcom.sun.management.jmxremote
- -Dcom.sun.management.jmxremote.port=8888
- -Dcom.sun.management.jmxremote.ssl=false

option参数：

- -F：当正常输出的请求不被响应时，强制输出线程堆栈
- -l：除堆栈外，显示关于锁的附加信息
- -m：如果调用到本地方法的话，可以显示C/C++的堆栈
- -h：帮助操作

##### 2.1.7 jcmd多功能命令行

 jcmd：

- 在JDK 1.7以后，新增了一个命令行工具jcmd
- jcmd是一个多功能的工具，可以用来实现前面除了jstat之外所有命令的功能。比如：用它来导出堆、内存使用、查看Java进程、导出线程信息、执行GC、JVM运行时间等
- jcmd拥有jmap的大部分功能，并且在Oracle的官方网站上也推荐使用jcmd命令代jmap命令
- 官方帮助文档：https://docs.oracle.com/en/java/javase/11/tools/jcmd.html

 jcmd命令：

- `jcmd -l`：列出所有的JVM进程
- `jcmd pid help`：针对指定的进程，列出支持的所有命令
- `jcmd pid 具体命令`：显示指定进程的指今命令的数据
- `jcmd <PID> Thread.print`：生成线程转储

- 可以探索`jcmd`中的下述功能，看看有没有适合的监控项：

```
Compiler.CodeHeap Analytics
Compiler.codecache
Compiler.codelist
Compiler.directives add
Compiler.directives clear
Compiler.directives_print
Compiler.directives remove
Compiler.queue
GC.class histogram
GC.class stats
GC.finalizer info
GC.heap_dump
GC.heap_info
GC.run
GC.run finalization
VM.class hierarchy
VM.classloader stats
VM.classloaders
VM.command line
VM.dynlibs
VM.flags
VM.info
VM.log
VM.metaspace
VM.native_memory
VM.print touched methods
VM.set_flag
VM.stringtable
VM.symboltable
VM.system_properties
VM.systemdictionary
VM.unlock commercial features
VM.uptime
VM.version
```

##### 2.1.8 jstatd远程主机信息收集

之前的指令只涉及到监控本机的Java应用程序，而在这些工具中，一些监控工具也支持对远程计算机的监控(如jps、jstat)

为了启用远程监控，则需要配合使用jstatd 工具

命令jstatd是一个RMI服务端程序，它的作用相当于代理服务器，建立本地计算机与远程监控工具的通信。 jstatd服务器将本机的Java应用程序信息传递到远程计算机

![image-20240812213724496](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240812213724496.png)

### 2.2 GUI工具

使用上一章命令行工具或组合能够获取目标Java应用性能相关的基础信息，但它们存在下列局限：

- 无法获取方法级别的分析数据，如方法间的调用关系、各方法的调用次数和调用时间等(这对定位应用性能瓶颈至关重要)
- 要求用户登录到目标 Java 应用所在的宿主机上，使用起来不是很方便
- 分析数据通过终端输出，结果展示不够直观
- 为此，JDK提供了一些内存泄漏的分析工具，如jconsole，jvisualvm等，用于辅助开发人员定位问题，但是这些工具很多时候并不足以满足快速定位的需求。所以这里介绍的工具相对多一些、丰富一些

**图形化综合诊断工具**：

- JDK自带的工具：
  - **jconsole**：JDK自带的可视化监控工具。查看Java应用程序的运行概况、监控堆信息、永久区(或元空间)使用情况、类加载情况等。位置：jdk\bin\jconsole.exe
  - **Visual VM**：Visual VM是一个工具，它提供了一个可视界面，用于查看Java虚拟机上运行的基于Java技术的应用程序的详细信息。位置：jdk\bin\jvisualvm.exe
  - **JMC**：Java Mission Control，内置Java Flight Recorder。能够以极低的性能开销收集Java虚拟机的性能数据

- 第三方工具
  - **MAT**：MAT(Memory Analyzer Tool)是基于Eclipse的内存分析工具，是一个快速、功能丰富的Java heap分析工具，它可以帮助我们查找内存泄漏和减少内存消耗。Eclipse的插件形式
  - **JProfiler**：商业软件，需要付费。功能强大。与 VisualVM类似
  - **Arthas**：Alibaba开源的Java诊断工具。深受开发者喜爱
  - **Btrace**：Java运行时追踪工具。可以在不停机的情况下，跟踪指定的方法调用、构造函数调用和系统内存等信息

##### 2.2.1 jconsole

jconsole概述：

- 从Java5开始，在JDK中自带的java监控和管理控制台
- 用于对JVM中内存、线程和类等的监控，是一个基于JMX(java management extensions)的GUI性能监控工具
- 官方教程：https://docs.oracle.com/javase/7/docs/technotes/guides/management/jconsole.html

jconsole启动：

- 控制台输入：jconsole

三种连接方式：

- Local：便用JConsole连接一个正在本地系统运行的JVM，并且执行程序的和运行JConsole的需要是同一个用户。JConsole使用文件系统的授权通过RMI连接器连接到平台的MBean服务器上。这种从本地连接的监控能力只有Sun的JDK具有
- Remote：使用下面的URL通过RMIi连接器连接到一个JMX代理，servicejmxcrmi:///indi/rmi://hostName:portNum/mxrmi.JConsole为建立连接，需要在环境变量中设置mx.remote.credentials来指定用户名和密码，从而进行授权
- Advanced：使用一个特殊的URL连接JMX代理，一般情况使用自己定制的连接器而不是RMI提供的连接器来连接JMX代理，或者是一个使用JDK1.4的实现了JMX和JMXRmote的应用

##### 2.2.2 Visual VM

Visual VM：

- Visual VM是一个功能强大的多合一故障诊断和性能监控的可视化工具
- Visual VM集成了多个JDK命令行工具，使用Visual VM可用于显示虚拟机进程及进程的配置和环境信息(jps,jinfo)，监视应用程序的CPU、GC、堆、方法区及线程的信息(istat、istack)等，甚至代替JConsole
- 在JDK6 Update 7以后，Visual VM便作为JDK的一部分发布(VisualVM 在JDK/bin目录下)，即：它完全免费。此外，Visual VM也可以作为独立的软件安装。首页：https://visualvm.github.io/index.html

插件的安装：

- Visual VM的一大特点是支持插件扩展，并且插件安装非常方便。我们既可以通过离线下载插件文件*.nbm，然后在Plugin对话框的已下载页面下，添加已下载的插件。也可以在可用插件页面下，在线安装插件。(这里建议安装上：VisuailGC）。插件地址：https://visualvm.github.io/pluginscenters.html
- IDEA安装VisualVM Launcher插件：Preferences-->Plugins-->搜索VisualVM Launcher，安装重启即可

连接方式：

- 本地连接：监控本地Java进程的CPU、类、线程等
- 远程连接：
  1. 确定远程服务器的ip地址
  2. 添加JMX(通过JMX技术具体监控远端服务器哪个Java进程
  3. 修改binvcatalina.sh文件，连接远程的tomcat
  4. 在./conf中添加jmxremote.access和jmxremote.password文件
  5. 将服务器地址改为公网ip地址
  6. 设置阿里云安全策略和防火墙策略
  7. 启动tomcat，查看tomcat启动日志和端口监听
  8. JMX中输入端口号、用户名、密码登录

主要功能：

- 生成/读取堆内存快照
- 查看JVM参数和系统属性
- 查看运行中的虚拟机进程
- 生成/读取线程快照
- 程序资源的实时监控
- JMX代理连接
- 远程环境监控
- CPU分析和内存分析

VisualVM生成和查看堆dump文件：

- VisualVM生成dump文件

![image-20240812230100903](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240812230100903.png)

- 查看堆dump文件：

![image-20240812230441648](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240812230441648.png)

##### 2.2.3 eclipse MAT

###### 1.eclipse MAT | dump文件内容

 MAT：

- MAT(Memory Analyzer Tool)工具是一款功能强大的Java堆内存分析器。可以用于查找内存泄漏以及查看内存消耗情况
- MAT是基于eclipse 开发的，不仅可以单独使用，还可以作为插件的形式嵌入在Eclipse中使用。是一款免费的性能分析工具，使用起来非常方便。可以在https://www.eclipse.org/mat/downloads.php下载并使用MAT
- 只要确保机器上装有JDK并配置好相关的环境变量，MAT可正常启动。还可以在Eclipse中以插件的方式安装

dump文件内容：

- MAT可以分析heap dump文件，在进行内存分析时，只要获得了反映当前设备内存映像的hprof文件，通过MAT打开就可以直观地看到当前的内存信息
- heap dump文件中的内存信息包含：
  - 所有的对象信息，包括对象实例、成员变量、存储于栈中的基本类型值和存储于堆中的其他对象的引用值
  - 所有的类信息，包括classloader、类名称、父类、静态变量等
  - GCRoot到所有的这些对象的引用路径
  - 线程信息，包括线程的调用栈及此线程的线程局部变量(TLS)

两点说明：

- MAT缺点：MAT 不是一个万能工具，它并不能处理所有类型的堆存储文件。但是比较主流的厂家和格式，例如Sun，HP，SAP 所采用的 HPROF 二进制堆存储文件，以及 IBM 的 PHD 堆存储文件等都能被很好的解析
- MAT优点：最吸引人的还是能够快速为开发人员生成内存泄漏报表，方便定位问题和分析问题。虽然MAT有如此强大的功能，但是内存分析也没有简单到一键完成的程度，很多内存问题还是需要从MAT展现的信息当中通过经验和直觉来判断才能发现

###### 2.获取堆dump文件

获取堆dump文件：

- 方法一：通过前一章介绍的 jmap工具生成，可以生成任意一个java进程的dump文件
- 方法二：通过配置JVM参数生成
  - 选项"-XX:+HeapDumpOnOutOfMemoryError"或“-XX:+HeapDumpBeforeFullGC"
  - 选项"-XX:HeapDumpPath"所代表的含义就是当程序出现OutOfMemory时，将会在相应的目录下生成一份dump文件。如果不指定选项“XX:HeapDumpPath”则在当前目录下生成dump文件。对比：考虑到生产环境中几乎不可能在线对其进行分析，大都是采用离线分析，因此使用jmap+MAT工具是最常见的组合
- 方法三：使用VisualVM可以导出堆dump文件
- 方法四：
  - 使用MAT既可以打开一个已有的堆快照，也可以通过MAT直接从活动Java程序中导出堆快照
  - 该功能将借助jps列出当前正在运行的 Java 进程，以供选择并获取快照

![image-20240813214931663](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813214931663.png)

###### 3.分析堆dump文件

分析堆dump文件：

- histogram：
  - 展示了各个类的实例数目以及这些实例heap 或Retainedheap的总和
  - MAT 的直方图和jmap的-histo子命令一样，都能够展示各个类的实例数目以及这些实例的 shallow总和。但是，MAT 的直方图还能够计算 Retained heap，并支持基于实例数目或 Retainedheap
    heap 的排序方式(默认为 Shallow heap)
  - 此外，MAT 还可以将直方图中的类按照超类、类加载器或者包名分组
  - 当选中某个类时，MAT 界面左上角的 Inspector 窗口将展示该类的 Class 实例的相关信息，如类加载器等
- thread overview
  - 查看系统中的Java线程
  - 查看的是局部信息
- 获得对象相互引用的关系
  - with outgoing references
  - with incoming references

- 浅堆与深堆
  - 浅堆(Shallow Heap)：
    - 浅堆(Shallow Heap)是指一个对象所消耗的内存。在32位系统中，一个对象引用会占据4个字节，一个int类型会占据4个字节，long型变量会占据8个字节，每个对象头需要占用8个字节。根据堆快照格式不同，对象的大小可能会向8字节进行对齐
    - 以String为例：2个int值共占8字节，对象引用占用4字节，对象头8字节，合计20字节，向8字节对齐，故占24字节(jdk7中)。这24字节为string对象的浅堆大小。它与string的value实际取值无关，无论字符串长度如何，浅堆大小始终是24字节
  - 深堆(Retained Heap)：
    - 保留集(Retained Set)：对象A的保留集指当对象A被垃圾回收后，可以被释放的所有的对象集合(包括对象A本身)，即对象A的保留集可以被认为是只能通过对象A被直接或间接访问到的所有对象的集合。通俗地说，就是指仅被对象A所持有的对象的集合
    - 深堆是指对象的保留集(Retained Set)中所有的对象的浅堆大小之和
    - 注意：浅堆指对象本身占用的内存，不包括其内部引用对象的大小。一个对象的深堆指只能通过该对象访问到的(直接或间接)所有对象的浅堆之和，即对象被回收后，可以释放的真实空间
  - 补充：对象实际大小
    - 另外一个常用的概念是对象的实际大小。这里，对象的实际大小定义为一个对象所能触及的所有对象的浅堆大小之和，也就是通常意义上说的对象大小。与深堆相比，似乎这个在日常开发中更为直观和被人接受，但实际上，这个概念和垃圾回收无关
    - 下图显示了一个简单的对象引用关系图，对象A引用了C和D，对象B引用了C和E。那么对象A的浅堆大小只是A本身，不含C和D，而A的实际大小为A、C、D三者之和。而A的深堆大小为A与D之和，由于对象C还可以通过对象B访问到，因此不在对象A的深堆范围内
    - ![image-20240813223422716](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813223422716.png)
  - 看图理解Retained size：`GC Roots`直接引用了A和B两个对象
    - A对象的Retained size=A对象的Shallow Size
    - B对象的Retained size=B对象的Shallow Size +C对象的Shallow size
    - 这里不包括D对象，因为D对象被`GC Roots`直接引用

![image-20240813223857883](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813223857883.png)

- 支配树(Dominator Tree)
  - 支配树的概念源自图论
  - MAT提供了一个称为支配树(Dominator Tree)的对象图。支配树体现了对象实例间的支配关系。在对象引用图中，所有指向对象B的路径都经过对象A，则认为对象A支配对象B。如果对象A是离对象B最近的一个支配对象，则认为对象A为对象B的直接支配者。支配树是基于对象间的引用图所建立的，它有以下基本性质
    - 对象A的子树(所有被对象A支配的对象集合)表示对象A的保留集(retained set)，即深堆
    - 如果对象A支配对象B，那么对象A的直接支配者也支配对象B
    - 支配树的边与对象引用图的边不直接对应
  - 如下图所示：
    - 左图表示对象引用图，右图表示左图所对应的支配树。对象A和B由根对象直接支配，由于在到对象C的路径中，可以经过A，也可以经过B，因此对象C的直接支配者也是根对象。对象F与对象D相互引用，因为到对象F的所有路径必然经过对象D，因此，对象D是对象F的直接支配者。而到对象D的所有路径中，必然经过对象C，即使是从对象F到对象D的引用，从根节点出发，也是经过对象C的，所以，对象D的直接支配者为对象C
    - 同理，对象E支配对象6。到达对象H的可以通过对象D，也可以通过对象E，因此对象D和E都不能支配对象H，而经过对象C既可以到达D也可以到达E，因此对象C为对象H的直接支配者

![image-20240813230119037](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813230119037.png)

- 在MAT中，单击工具栏上的对象支配树按钮，可以打开对象支配树视图

![image-20240813230659507](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813230659507.png)

###### 4.案例：Tomcat堆溢出分析

案例：Tomcat堆溢出分析

- 说明：Tomcat是最常用的Java Servlet容器之一，同时也可以当做单独的Web服务器使用。Tomcat本身使用Java实现，并运行于Java虚拟机之上。在大规模请求时，Tomcat有可能会因为无法承受压力而发生内存溢出错误。这里根据一个被压垮的Tomcat的堆快照文件，来分析Tomcat在崩溃时的内部情况
- 分析过程：

图1：

![image-20240813231420399](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813231420399.png)

图2：

![image-20240813231552139](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813231552139.png)



图3：sessions对象，它占用了约17MB空间

![image-20240813232118773](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813232118773.png)

图4：可以看到sessions对象为ConcurrentHashMap，其内部分为16个Segment。从深堆大小看，每个Segment都比较平均，大约为1MB，合计17MB

![image-20240813232247975](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813232247975.png)

图5：

![image-20240813232329642](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813232329642.png)

图6：当前堆中含有9941个session，并且每一个session的深堆为1592字节，合计约15MB，达到当前堆大小的50%

![image-20240813232740258](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813232740258.png)

图7：

![image-20240813232905644](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813232905644.png)

图8：根据当前的session总数，可以计算每秒的平均压力为：9941/(1403324677648-1403324645728)*1000=311次/秒。由此推断，在发生Tomcat堆溢出时，Tomcat在连续38秒的时间内，平均每秒接收了约311次不同客户端的请求，创建了合计9941个session

![image-20240813233055716](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813233055716.png)

###### 5.OQL语言查询对象信息

MAT支持一种类似于SQL的查询语言OQL(Object Query Language)。OQL使用类SQL语法，可以在堆中进行对象的查找和筛选



SELECT子句：

- 在MAT中，Select子句的格式与SQL基本一致，用于指定要显示的列。Select子句中可以使用`"*"`查看结果对象的引用实例(相当于outgoing references)
- SELECT `"*"` FROM java.util.Vector v
- 使用“OBJECTS”关键字，可以将返回结果集中的项以对象的形式显示
  - SELECT objects v.elementData FROM java.util.Vector v
  - SELECT OBJECTS s.value FROM java.lang.string s
- 在Select子句中，使用“AS RETAINED SET”关键字可以得到所得对象的保留集
  - SELECT AS RETAINED SET * FROM com.atguigu.mat.student
- DISTINCT关键字用于在结果集中去除重复对象
  - SELECT DISTINCT OBJECTS classof(s) FROM java.lang.string s



FROM子句：

- From子句用于指定查询范围，它可以指定类名、正则表达式或者对象地址
  - SELECT * FROM java.lang.string s
- 下例使用正则表达式，限定搜索范围，输出所有com.atguigu包下所有类的实例
  - SELECT * FROM "com.atguigu.*"
- 也可以直接使用类的地址进行搜索。使用类的地址的好处是可以区分被不同ClassLoader加载的同一种类型
  - select  *  from 0x37a0b4d



WHERE子句：

- Where子句用于指定OQL的查询条件。OQL查询将只返回满足Where子句指定条件的对象

- Where子句的格式与传统SQL极为相似

- 下例返回长度大于10的char数组
  - SELECT * FROM char[]s WHERE s.@length>10

- 下例返回包含“java”子字符串的所有字符串，使用“LIKE”操作符，“LIKE”操作符的操作参数为正则表达式
  - SELECT * FROM java.lang.string s WHERE toString(s) LIKE ".*java.*"

- 下例返回所有value域不为null的字符串，使用“=”操作符
  - SELECT * FROM java.lang.string s where s.value != null

- Where子句支持多个条件的AND、OR运算。下例返回数组长度大于15，并且深堆大于1008字节的所有Vector对象
  - SELECT * FROM java.util.Vector v WHERE v.elementData.@length>15 AND v.@retainedHeapsize>1000

内置对象与方法：

- OQL中可以访问堆内对象的属性，也可以访问堆内代理对象的属性。访问堆内对象的属性时，格式如下：
  - `[<alias>.]<field>.<field>. <field>`  （其中alias为对象名称）

- 访问java.io.File对象的path属性，并进一步访问path的value属性：
  - SELECT toString(f.path.value)FROM java.io.File f

- 下例显示了string对象的内容、objectid和objectAddress
  - SELECT s.toString(),s.@objectId,s.@objectAddress FROM java.lang.String s

- 下例显示java.util.Vector内部数组的长度
  - SELECT v.elementData.@length FRoM java.util.Vector v

- 下例显示了所有的java.util.Vector对象及其子类型
  - select * from INSTANCEOF java.util.Vector

##### 2.2.4 `JProfiler `

###### 1.`JProfiler `简介 & 下载安装

`JProfiler `简介：

- 在运行Java的时候有时候想测试运行时占用内存情况，这时候就需要使用测试工具查看了。在eclipse里面有 Eclipse Memory Analyzer tool(MAT)插件可以测试，而在IDEA中也有这么一个插件，就是JProfiler
- JProfiler 是由 ej-technologies 公司开发的一款 Java 应用性能诊断工具。功能强大，但是收费
- 官网下载地址：https://www.ej-technologies.com/products/jprofiler/overview.html

`JProfiler `特点：

- 使用方便、界面操作友好 (简单且强大)
- 对被分析的应用影响小(提供模板)
- CPU、Thread、Memory分析功能尤其强大
- 支持对jdbc、noSql、jsp、servlet、socket等进行分析
- 支持多种模式(离线，在线)的分析
- 支持监控本地、远程的JVM
- 跨平台、拥有多种操作系统的安装版本

主要功能：

- 方法调用：对方法调用的分析可以帮助您了解应用程序正在做什么，并找到提高其性能的方法
- 内存分配：通过分析堆上对象、引用链和垃圾收集能帮您修复内存泄问题，优化内存使用
- 线程和锁：JProfier提供多种针对线程和锁的分析视图助您发现多线程问题
- 高级子系统：许多性能问题都发生在更高的语义级别上。例如，对于JDBC调用，您可能希望找出执行最慢的 SQL语句。JProfier支持对这些子系统进行集成分析

下载与安装：

- 安装与配置：
  - 下载地址：[ej-technologies - Download JProfiler](https://www.ej-technologies.com/jprofiler/download10)
- JProfiler 中配置IDEA

![image-20240819211852786](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240819211852786.png)



- IDEA集成JProfiler ：`setting -> Plugins` 下安装JProfiler 插件

###### 2.`JProfiler `使用

JProfiler 数据采集方式：

- JProfiler 数据采集方式分为两种：sampling(样本采集)和Instrumentation(重构模式)
- Instrumentation：这是JProfiler全功能模式。在class加载之前，JProfiler 把相关功能代码写入到需要分析的class的bytecode中，对正在运行的jvm有一定影响
  - 优点：功能强大。在此设置中,调用堆栈信息是准确的
  - 缺点：若要分析的class较多,则对应用的性能影响较大,CPU开销可能很高(取决于Filter的控制)。因此使用此模式一般配合Filter使用，只对特定的类或包进行分析

- Sampling：类似于样本统计，每隔一定时间(5ms)将每个线程栈中方法栈中的信息统计出来
  - 优点：对CPU的开销非常低，对应用影响小(即使你不配置任何Filter)
  - 缺点：一些数据/特性不能提供(例如:方法的调用次数、执行时间)

- 注：JProfiler本身没有指出数据的采集类型，这里的采集类型是针对方法调用的采集类型。因为JProfiler的绝大多数核心功能都依赖方法调用采集的数据，所以可以直接认为是JProfiler的数据采集类型



遥感监测 Telemetries(查看JVM的运行信息)：

- 整体视图 Overview：显示堆内存、cpu、线程以及GC等活动视图
- 内存 Memory：显示一张关于内存变化的活动时间表
- 记录的对象 Recorded objects：显示一张关于活动对象与数组的图表的活动时间表
- 记录吞吐量 Record Throughput：显示一段时间累计的JVM生产和释放的活动时间表
- 垃圾回收活动 GC Activity：显示一张关于垃圾回收活动的活动时间表
- 类Casses：显示一个与己装载类的图表的活动时间表
- 线程 Threads：显示一个与动态线程图表的活动时间表
- CPU负载CPU Load：显示一段时间中CPU的负载图表
- Telemetries使用：

![image-20240819214019764](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240819214019764.png)



内存视图 Live Memory：

- 内存视图 Live Memory作用：分析内存中的对象的如下情况

  - 频繁创建的Java对象：死循环、循环次数过多

  - 存在大的对象：读取文件时，byte[]应该边读边写。-->如果长时间不写出的话，导致byte[]过大

  - 存在内存泄漏

- Live memory 内存剖析：class/class instance的相关信息。 例如对象的个数，大小，对象创建的方法执行栈，对象创建的热点

- 所有对象 All Obiects：显示所有加载的类的列表和在堆上分配的实例数。只有Java 15(JVMTI)才会显示此视图

- 分配访问树 Allocation call Tree：显示一棵请求树或者方法、类、包或对已选择类有带注释的分配信息的J2EE组件

- 分配热点 Allocation Hot Spots：显示一个列表，包括方法、类、包或分配已选类的]2EE组件。你可以标注当前值并且显示差异值。对于每个热点都可以显示它的跟踪记录树

- 类追踪器 Class Tracker：类跟踪视图可以包含任意数量的图表，显示选定的类和包的实例与时间



![image-20240819214939197](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240819214939197.png)

堆遍历 heap walker：

- 类Classes：显示所有类和它们的实例，可以右击具体的类"Used Selected Instance"实现进一步跟踪
- 分配 Allocations：为所有记录对象显示分配树和分配热点
- 索引 References：为单个对象和 "显示到垃圾回收根目录的路径" 提供索引图的显示功能。还能提供合并输入视图和输出视图的功能
- 时间 Time：显示一个对已记录对象的解决时间的柱状图
- 检查 Inspections：显示了一个数量的操作，将分析当前对象集在某种条件下的子集，实质是一个筛选的过程
- 图表 Graph：需要在references视图和biggest视图手动添加对象到图表，它可以显示对象的传入和传出引用，能方便的找到垃圾收集器根源
- PS：在工具栏点击"Go To start"可以使堆内存重新计数，也就是回到初始状态

CPU视图 cpu views：

- JProfiler 提供不同的方法来记录访问树以优化性能和细节。线程或者线程组以及线程状况可以被所有的视图选择。所有的视图都可以聚集到方法、类、包或J2EE组件等不同层上
- 访问树Call Tree：显示一个积累的自顶向下的树，树中包含所有在JVM中已记录的访问队列。JDBC、JMS和INDI服务请求都被注释在请求树中。请求树可以根据Servlet和JSP对URL的不同需要进行拆分
- 热点 Hot spots：显示消耗时间最多的方法的列表。对每个热点都能够显示回溯树。该热点可以按照方法请求，JDBC，JMS和JNDI服务请求以及按照URL请求来进行计算
- 访问图 Call Graph：显示一个从已选方法、类、包或J2EE组件开始的访问队列的图
- 方法统计 Method statistis：显示一段时间内记录的方法的调用时间细节
- CPU视图：

![image-20240819222103309](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240819222103309.png)

线程视图 threads：

- 线程分析主要关心三个方面：
  - web容器的线程最大数。比如：Tomcat的线程容量应该略大于最大并发数
  - 线程阻塞
  - 线程死锁
- JProfiler通过对线程历史的监控判断其运行状态，并监控是否有线程阻塞产生，还能将一个线程所管理的方法以树状形式呈现。对线程剖析
- 线程历史 Thread History：显示一个与线程活动和线程状态在一起的活动时间表
- 线程监控 Thread Monitor：显示一个列表，包括所有的活动线程以及它们目前的活动状况
- 线程转储 Thread Dumps：显示所有线程的堆栈跟踪
- 线程视图 threads：







![image-20240819222219597](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240819222219597.png)

监视器&锁 Monitors&locks（所有线程持有锁的情况以及锁的信息）：

- Monitors&locks：观察JVM的内部线程并查看状态
- 死锁探测图表 Current Locking Graph：显示JVM中的当前死锁图表
- 目前使用的监测器 Current Monitors：显示目前使用的监测器并且包括它们的关联线程
- 锁定历史图表 Locking History Graph：显示记录在JVM中的锁定历史
- 历史检测记录 Monitor History：显示重大的等待事件和阻塞事件的历史记录
- 监控器使用统计 Monitor Usage statistics：显示分组监测，线程和监测类的统计监测数据

##### 2.2.5 Arthas

###### 1.Arthas基本概述

官方文档：[arthas (aliyun.com)](https://arthas.aliyun.com/)

前面介绍了jdk自带的jvisualvm等免费工具，以及商业化工具profiler。这两款工具在业界冠名度也比较高，他们的优点是可以图形界面上看到各维度的性能数据，使用者根据这些数据进行综合分析，然后判断哪里出现了性能问题

但是这两款工具也有个缺点，都必须在服务端项目进程中配置相关的监控参数。然后工具通过远程连接到项目进程，获取相关的数据。这样就会带来一些不便，比如线上环境的网络是隔离的，本地的监控工具根本连不上线上环境。并且类似于Jprofiler这样的商业工具，是需要付费的

那么有没有一款工具不需要远程连接，也不需要配置监控参数，同时也提供了丰富的性能监控数据呢。今天跟大家介绍一款阿里巴巴开源的性能分析神器Arthas

Arthas(阿尔萨斯)是Alibaba开源的Java诊断工具，深受开发者喜爱。在线排査问题，无需重启。动态跟踪Java代码。实时监控JVM状态。Arthas 支持JDK 6+，支持Linux/Mac/Windows，采用命令行交互模式，同时提供丰富的 Tab 自动补全功能，进一步方便进行问题的定位和诊断

当遇到以下类似问题而束手无策时，Arthas可以帮助你解决：

- 这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
- 我改的代码为什么没有执行到？难道是我没commit？分支搞错了？
- 遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗？
- 线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现
- 是否有一个全局视角来查看系统的运行状况？
- 有什么办法可以监控到JVM的实时运行状态？

Arthas基于哪些工具开发而来：

- greys-anatomy：Arthas代码基于Greys二次开发而来，非常感谢Greys之前所有的工作，以及Greys原作者对Arthas提出的意见和建议
- termd：Arthas的命令行实现基于termd开发，是一款优秀的命令行程序开发框架，感谢termd提供了优秀的框架
- crash：Arthas的文本渲染功能基于crash中的文本渲染功能开发，可以从这里看到源码，感谢crash在这方面所做的优秀工作
- cli：Arthas的命令行界面基于vert.x提供的cli库进行开发，感谢vert.x在这方面做的优秀工作
- compiler：Arthas里的内存编绎器代码来源
- Apache Commons Net： Arthas里的Telnet client代码来源
- JavaAgent：运行在 main方法之前的拦截器，它内定的方法名叫 premain ，也就是说先执行premain 方法然后再执行 main 方法
- ASM：一个通用的Java字节码操作和分析框架。它可以用于修改现有的类或直接以二进制形式动态生成类。ASM提供了一些常见的字节码转换和分析算法，可以从它们构建定制的复杂转换和代码分析工具。ASM提供了与其他Java字节码框架类似的功能，但是主要关注性能。因为它被设计和实现得尽可能小和快，所以非常适合在动态系统中使用(当然也可以以静态方式使用，例如在编译器中)

###### 2.安装与使用

安装方式一：

- 可以直接在Linux上通过命令下载
- 可以在官方 Github 上进行下载，如果速度较慢，可以尝试国内的码云 Gitee 下载
- github下载：wget https://alibaba.github.io/arthas/arthas-boot.jar
- Gitee 下载：wget https://arthas.gitee.io/arthas-boot.jar

安装方式二：

- 也可以在浏览器直接访问https://alibaba.github.io/arthas/arthas-boot.jar。等待下载成功后，上传到Linux服务器上

卸载：

- 在 Linux/Unix/Mac 平台删除下面文件：`rm -rf ~/.arthas/` 、`rm -rf ~/logs/arthas`
- Windows平台直接删除user home下面的.arthas和logs/arthas目录

arthas工程目录：

- arthas-agent：基于JavaAgent技术的代理
- bin：一些启动脚本
- arthas-boot：Java版本的一键安装启动脚本
- arthas-client：telnet client代码
- arthas-common：一些共用的工具类和枚举类
- arthas-core：核心库，各种arthas命令的交互和实现
- arthas-demo：示例代码
- arthas-memorycompiler：内存编绎器代码，Forkfrom    https://github.com/skalogs/SkaETL/tree/master/compiler
- arthas-packaging：maven打包相关的
- arthas-site：arthas站点
- arthas-spy：编织到目标类中的各个切面
- static：静态资源
- arthas-testcase：测试



Arthas启动 & 退出：

- Arthas 只是一个 java 程序，所以可以直接用 java -jar 运行。执行成功后，arthas提供了一种命令行方式的交互方式，arthas会检测当前服务器上的Java进程，并将进程列表展示出来，用户输入对应的编号(1、2、3、4…)进行选择，然后回车
- 启动方式1：java -jar arthas-boot.jar

```
#选择进程(输入[]内编号(不是PID)回车)
[INF0] arthas-boot version: 3.1.4
[INFO] Found existing java process, please choose one and hit RETURN.
*[1]: 11616 com.Arthas

[2]:8676

[3]:16200 org.jetbrains.jps.cmdline.Launcher
[4]:21032 org.jetbrains.idea.maven.server.RemoteMavenServer
```

- 启动方式2：运行时选择 Java 进程 PID

```
java -jar arthas-boot.jar
```

![image-20240820204340000](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240820204340000.png)

- 退出
  - 使用quit\exit：退出当前客户端
  - 使用stop\shutdown：关闭arthas服务端，并退出所有客户端

Arthas使用：

- 查看日志：`cat -/logs/arthas/arthas.log`
- 参看帮助：`java -jar arthas-boot.jar -h`
- web console：除了在命令行查看外，Arthas 目前还支持 web console。在成功启动连接进程之后就已经自动启动，可以直接访问 http://127.0.0.1:8563/访问，页面上的操作模式和控制台完全一样

###### 3.相关诊断指令

官方文档：https://arthas.aliyun.com/doc/commands.html

基础指令：

- help：查看命令帮助信息
- cat：打印文件内容，和linux里的cat命令类似
- echo：打印参数，和linux里的echo命令类似
- grep：匹配查找，和linux里的grep命令类似
- tee：复制标准输入到标准输出和指定的文件，和linux里的tee命令类似
- pwd：返回当前的工作目录，和linux命令类似
- cls：清空当前屏暮区域
- session：查看当前会话的信息
- reset：重置增强类，将被 Arthas,增强过的类全部还原，Arthas 服务端关闭时会重置所有增强过的类
- version：输出当前目标 Java 进程所加载的 Arthas 版本号
- history：打印命令历史
- quit：退出当前 Arthas 客户端，其他Arthas 客户端不受影响
- stop：关闭 Arthas服务端，所有 Arthas 客户端全部退出
- key map：Arthas快捷键列表及自定义快捷键

jvm相关：

- dashboard：当前系统的实时数据面板
- thread：查看当前 JVM 的线程堆栈信息
- jvm：查看当前 JVM 的信息
- sysprop：查看和修改JVM的系统属性
- sysenv：查看JVM的环境变量
- vmoption：查看和修改JVM里诊断相关的option
- perfcounter：查看当前JVM 的Perf Counter信息
- logger：查看和修改logger
- getstatic：查看类的静态屋性
- ognl：执行ognl表达式
- mbean：查看 Mbean 的信息
- heapdump：dumpjava heap,类似jmap命令的heap dump功能

class/classloader相关：

- sc：查看JVM已加载的类信息
  - SC命令：https://arthas.aliyun.com/doc/sc
  - 常用参数：
    - class-pattern 类名表达式匹配
    - -d 输出当前类的详细信息，包括这个类所加载的原始文件来源、类的声明、加载的ClassLoader等详细信息。如果一个类被多个ClassLoader所加载，则会出现多次开启正则表达式匹配，默认为通配符匹配
    - -E 输出当前类的成员变量信息(需要配合参数-d一起使用)
    - -x 指定输出静态变量时属性的遍历深度，默认为8，即直接使用 tostring 输出
  - 补充：
    - class-pattern支持全限定名，如com.test.AAA，也支持com/test/AAA这样的格式，这样，我们从异常堆栈里面把类名拷贝过来的时候，不需要在手动把/替换为.了
    - sc 默认开启了子类匹配功能，也就是说所有当前类的子类也会被搜索出来，想要精确的匹配，请打开options disable-sub-class true开关
- sm：查看已加载类的方法信息
  - 官方文档：https://arthas.aliyun.com/doc/sm
  - sm 命令只能看到由当前类所声明(declaring)的方法，父类则无法看到
  - 常用参数：
    - class-pattern 类名表达式匹配
    - method-pattern 方法名表达式匹配
    - -d 展示每个方法的详细信息
    - -E 开启正则表达式匹配，默认为通配符匹配
- jad：反编译指定已加载类的源码
  - 官方文档：https://arthas.aliyun.com/doc/jad
  - 在 Arthas Console 上，反编译出来的源码是带语法高亮的，阅读更方便
  - 当然，反编译出来的 java 代码可能会存在语法错误，但不影响阅读理解
- mc：内存编译器，内存编译.java 文件为.class 文件
- retransform：加载外部的.class 文件，retransform到JVM里
- redefine：加载外部的.class 文件，redefine到JVM里
- dump：dump 已加载类的 byte code 到特定目录
- classloader：查看classloader的继承树，urs，类加载信息，使用classloader去getResource
  - 官方文档：https://arthas.aliyun.com/doc/classloader
  - 了解当前系统中有多少类加载器，以及每个加载器加载的类数量，帮助您判断是否有类加载器泄漏
  - 常用参数：
    - -t：查看ClassLoader的继承树
    - -l：按类加载实例查看统计信息
    - -c：用classloader对应的hahcode 来查看对应的jar urls

monitor/watch/trace相关：

- monitor：方法执行监控
  - 官方文档：https://arthas.aliyun.com/doc/monitor
  - 对匹配 class-pattern/method-pattern的类、方法的调用进行监控。涉及方法的调用次数、执行时间、失败率等
  - monitor 命令是一个非实时返回命令
  - 常用参数：
    - 类名表达式匹配 class-pattern
    - method-pattern 方法名表达式匹配
    - 统计周期，默认值为128秒
- watch：方法执行数据观测
  - 官方文档：https://arthas.aliyun.com/doc/watch
  - 能方便的观察到指定方法的调用情况。能观察到的范围为:返回值、抛出异常、入参，通过编写 groovy 表达式进行对应变量的查看
- trace：方法内部调用路径，并输出方法路径上的每个节点上耗时
  - 官方文档：https://arthas.aliyun.com/doc/trace
  - trace 命令能主动搜索 class-pattern/method-pattern 对应的方法调用路径，渲染和统计整个调用链路上的所有性能开销和追踪调用链路
  - trace 能方便的定位和发现因 RT 高而导致的性能问题缺陷，但其每次只能跟踪一级方法的调用链路
  - trace 在执行的过程中本身是会有一定的性能开销，在统计的报告中并未像JProfiler 一样预先减去其自身的统计开销。所以这统计出来有些许的不准，渲染路径上调用的类、方法越多，性能偏差越大
- stack：输出当前方法被调用的调用路径
- t：方法执行数据的时空隧道，记录下指定方法每次调用的入参和返回信息，并能对这些不同的时间下调用进行观测

##### 2.2.6 JMC（Java Mission Control）

JMC简介：

- 在 Oracle 收购 Sun 之前，Oracle 的 JRockit 虚拟机提供了一款叫做 JRockit Mission Control 的虚拟机诊断工具
- 在Oracle收购Sun之后，Oracle公司同时拥有了Sun Hotspot和JRockit两款虚拟机。根据Oracle对于Java的战略，在今后的发展中，会将JRockit的优秀特性移植到Hotspot上。其中一个重要的改进就是在Sun的JDK中加入了JRockit的支持在Oracle JDK 7u40之后，Mission Control这款工具已经绑定在0racle JDK中发布
- 自 Java 11 开始，本节介绍的 JFR 已经开源。但在之前的 Java 版本，JFR 属于Commercial Feature，需要通过 Java 虚拟机参数-XX:+UnlockCommercialFeatures开启
- OpenJDK的Mission Control项目：https://github.com/JDKMissionControl/jmc

JMC启动：

- Mission Control 位于%JAVA HOME%/ bin/jmc.exe，打开这款软件

JMC作用：

- Java Mission Control(简称 JMC),Java官方提供的性能强劲的工具。是一个用于对Java 应用程序进行管理、监视、概要分析和故障排除的工具套件
- JMC包含一个 GUI 客户端，以及众多用来收集 Java 虚拟机性能数据的插件，如 JMX Console(能够访问用来存放虚拟机各个子系统运行数据的MXBeans)，以及虚拟机内置的高效profiling 具Java Flight Recorder(JFR)
- JMC 的另一个优点就是：采用取样，而不是传统的代码植入技术，对应用性能的影响非常非常小。完全可以开着 JMC 来做压测(唯一影响可能是 full gc 多了)

使用JMC实时监控 JVM 运行时的状态：

- 如果是远程服务器，使用前要开 JMX
- -Dcom.sun.management.jmxremote.port=${YOUR PORT}
- -Dcom.sun.management.jmxremote
- -Dcom.sun.management.jmxremote.authenticate=false
- -Dcom.sun.management.jmxremote.ssl=false
- -Djava.rmi.server.hostname=${YOUR HOST/IP}
- 文件 -> 连接  -> 创建新连接。填入上面 JMX 参数的 host 和 port

Java Flight Recorder：

- Java Flight Recorder是JMC 的其中一个组件：

  - Java Flight Recorder能够以极低的性能开销收集 Java 虚拟机的性能数据

  - JFR 的性能开销很小，在默认配置下平均低于 1%。与其他工具相比，JFR 能够直接访问虚拟机内的数据，并且不会影响虚拟机的优化。因此，它非常适用于生产环境下满负荷运行的 Java 程序

  - Java Flight Recorder和JDK Mission Control共同创建了一个完整的工具链。JDK Mission Control可对Java Flight Recorder连续收集低水平和详细的运行时信息进行高效详细的分析

- 事件类型：
  - 当启用时，JFR 将记录运行过程中发生的一系列事件。其中包括 Java 层面的事件，如线程事件、锁事件，以及 Java 虚拟机内部的事件，如新建对象、垃圾回收和即时编译事件
  - 按照发生时机以及持续时间来划分，JFR的事件共有四种类型，它们分别为以下四种：
    1. 瞬时事件(Instant Event)，用户关心的是它们发生与否，例如异常、线程启动事件
    2. 持续事件(Duration Event)，用户关心的是它们的持续时间，例如垃圾回收事件
    3. 计时事件(Timed Event)，是时长超出指定阈值的持续事件
    4. 取样事件(Sample Event)，是周期性取样的事件
  - 取样事件的其中一个常见例子便是方法抽样(Method sampling)，即每隔一段时间统计各个线程的栈轨迹。如果在这些抽样取得的栈轨迹中存在一个反复出现的方法，那么可以推测该方法是热点方法
- 启动方式：
  - 方式1：便用-XX:StartflightRecording=参数
  - 方式2：使用jcmd的IFR.*子命令
  - 方式3：JMC的JFR插件

- Java Flight Recorder 取样分析：
  - 要采用取样，必须先添加参数：
    - -XX:+UnlockCommercialFeatures
    - -XX:+FlightRecorder
  - 取样时间默认 1 分钟，可自行按需调整，事件设置选为profiling，然后可以设置取样profile 哪些信息，比如：
    - 加上对象数量的统计：Java Virtual Machine ->GC ->Detailed -> Object
    - Count/Object Count after GC
    - 方法调用采样的间隔从 10ms 改为 1ms(但不能低于 1ms，否则会影响性能了)
    - Java Virtual Machine ->Profiling -> Method Profiling Sample/Method Sampling Information

##### 2.2.7 其他工具

Flame Graphs(火焰图)：

- 在追求极致性能的场景下，了解你的程序运行过程中cpu在干什么很重要，火焰图就是一种非常直观的展示cpu在程序整个生命周期过程中时间分配的工具
- 火焰图对于现代的程序员不应该陌生，这个工具可以非常直观的显示出调用栈中的CPU消耗瓶颈
- 网上的关于java火焰图的讲解大部分来自于Brendan Gregg的博客：http://www.brendangregg.com/flamegraphs.html

Tprofiler：

- 使用 JDK 自身提供的工具进行 JVM 调优可以将 TPS 由 2.5 提升到 28(提升了 7 倍)，并准确定位系统瓶颈。系统瓶颈有：应用里静态对象不是太多、有大量的业务线程在频繁创建一些生命周期很长的临时对象，代码里有问题。那么，如何在海量业务代码里边准确定位这些性能代码？这里使用阿里开源工具 TProfiler 来定位这些性能代码，成功解决掉了 GC 过于频繁的性能瓶颈，并最终在上次优化的基础上将 TPS 再提升了4 倍，即提升到 100。TProfiler 配置部署、远程操作、日志阅读都不太复杂，操作还是很简单的。但是其却是能够起到一针见血、立竿见影的效果，帮我们解决了 GC 过于频繁的性能瓶颈
- TProfiler 最重要的特性就是能够统计出你指定时间段内 JVM 的 top method，这些 topmethod 极有可能就是造成你 JVM 性能瓶颈的元凶。这是其他大多数 JVM 调优工具所不具备的，包括 JRockit Mission Control。JRokit 首席开发者 Marcus Hirt 在其私人博客《Low Overhead Method Profiling with Java Mission Control》下的评论中曾明确指出JRMC 并不支持 TOP 方法的统计
- TProfiler的下载：https://github.com/alibaba/TProfiler



Btrace：Java运行时追踪工具

- 常见的动态追踪工具有BTrace、HouseMp(该项目已经停止开发)、Greys-Anatomy(国人开发，个人开发者)、Byteman(JBoss出品)，注意Java运行时追踪工具并不限于这几种，但是这几个是相对比较常用的
- BTrace是SUN Kenai云计算开发平台下的一个开源项目，旨在为iava提供安全可靠的动态跟踪分析工具。先看一下BTrace的官方定义：BTrace is a safe, dynamic tracing tool for the Java platform. BTrace can be used to dynamically trace a running Java program(similar to DTrace for OpenSolaris applications and 0s). BTrace dynamically instruments the classes of the target application to inject tracing code(“bytecode tracing” )
- 简洁明了，大意是一个]ava平台的安全的动态追踪工具。可以用来动态地追踪一个运行的]ava程序。BTrace动态调整目标应用程序的类以注入跟踪代码(“字节码跟踪”)

其他工具：YourKit、JProbe、Spring Insight



##### 2.2.8 再谈内存泄漏

###### 1.内存泄漏的理解与分类

内存泄漏(memory leak)：可达性分析算法来判断对象是否是不再使用的对象，本质都是判断一个对象是否还被引用。那么对于这种情况下，由于代码的实现不同就会出现很多种内存泄漏问题(让JVM误以为此对象还在引用中，无法回收，造成内存泄漏)

![image-20240813235938444](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240813235938444.png)

**内存泄漏(memory leak)的理解**：

- 严格来说，只有对象不会再被程序用到了，但是GC又不能回收他们的情况，才叫内存泄漏。但实际情况很多时候一些不太好的实践(或疏忽)会导致对象的生命周期变得很长甚至导致OOM，也可以叫做宽泛意义上的“内存泄漏”
- 对象 X引用对象 Y，X 的生命周期比 Y 的生命周期长。那么当Y生命周期结束的时候，X依然引用着Y，这时候，垃圾回收期是不会回收对象Y的。如果对象X还引用着生命周期比较短的A、B、C，对象A又引用着对象 a、b、c，这样就可能造成大量无用的对象不能被回收，进而占据了内存资源，造成内存泄漏，直到内存溢出

![image-20240815203727678](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240815203727678.png)

内存泄漏与内存溢出的关系：

- 内存泄漏(memory leak )：申请了内存用完了不释放。比如一共有 1024M 的内存，分配了 521M 的内存一直不回收，那么可以用的内存只有 521M 了，仿佛泄露掉了一部分。通俗一点讲的话，内存泄漏就是【占着茅坑不拉shi】
- 内存溢出(out of memory)：申请内存时，没有足够的内存可以使用。通俗一点儿讲，一个厕所就三个坑，有两个站着茅坑不走的(内存泄漏)，剩下最后一个坑，厕所表示接待压力很大，这时候一下子来了两个人，坑位(内存)就不够了，内存泄漏变成内存溢出了
- 可见，内存泄漏和内存溢出的关系:内存泄漏的增多，最终会导致内存溢出

泄漏的分类：

- 经常发生：发生内存泄露的代码会被多次执行，每次执行，泄露一块内存
- 偶然发生：在某些特定情况下才会发生
- 一次性：发生内存泄露的方法只会执行一次
- 隐式泄漏：一直占着内存不释放，直到执行结束。严格的说这个不算内存泄，因为最终释放掉了，但是如果执行时间特别长，也可能会导致内存耗尽



###### 2.Java中内存泄漏的8种情况

1-静志集合类

- 静态集合类，如HashMap、LinkedList等等。如果这些容器为静态的，那么它们的生命周期与JVM程序一致，则容器中的对象在程序结束之前将不能被释放，从而造成内存泄漏。简单而言长生命周期的对象持有短生命周期对象的引用，尽管短生命周期的对象不再使用，但是因为长生命周期对象持有它的引用而导致不能被回收

```java
public class Memoryleak {
        static List list = new ArrayList(); // 静态集合类
        public void oomTests(){
            Object obj=new object(); //局部变量
            list.add(obj);
        }
}
```

2-单例模式

- 单例模式，和静态集合导致内存泄露的原因类似，因为单例的静态特性，它的生命周期和 JVM 的生命周期一样长，所以如果单例对象如果持有外部对象的引用，那么这个外部对象也不会被回收，那么就会造成内存泄漏

3-内部类持有外部类

- 内部类持有外部类，如果一个外部类的实例对象的方法返回了一个内部类的实例对象。这个内部类对象被长期引用了，即使那个外部类实例对象不再被使用，但由于内部类持有外部类的实例对象，这个外部类对象将不会被垃圾回收，这也会造成内存泄漏

4-各种连接，如数据库连接、网络连接和IO连接等

- 在对数据库进行操作的过程中，首先需要建立与数据库的连接，当不再使用时，需要调用close方法来释放与数据库的连接。只有连接被关闭后，垃圾回收器才会回收对应的对象。否则，如果在访问数据库的过程中，对Connection、statement或Resultset不显性地关闭，将会造成大量的对象无法被回收，从而引起内存泄漏

```java
public static void main(string[] args){
        try {
                Connection conn = null;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getconnection("url"
                Statement stmt =conn.createstatement();
                ResultSetrs=stmt.executeQuery("....");
        }catch(Exception e){//异常日志

        } finally {
        //1.关闭结果集 statement
        // 2.关闭声明的对象 Resultset
        // 3.关闭连接 Connection
        }
}
```

5-变量不合理的作用域

- 变量不合理的作用域。一般而言，一个变量的定义的作用范围大于其使用范围，很有可能会造成内存泄漏。另一方面，如果没有及时地把对象设置为null，很有可能导致内存泄漏的发生

```java
public class UsingRandom{
        private String msg;
        public void receiveMsg(){
                readFromNet(); //从网络中接受数据保存到msg中
                saveDB(); // 把msg保存到数据库中
          }
}
```

- 如上面这个伪代码，通过readFromNet方法把接受的消息保存在变量msg中，然后调用saveDB方法把msg的内容保存到数据库中，此时msg已经就没用了，由于msg的生命周期与对象的生命周期相同，此时msg还不能回收，因此造成了内存泄漏
- 实际上这个msg变量可以放在receiveMsg方法内部，当方法使用完，那么msg的生命周期也就结束，此时就可以回收了。还有一种方法，在使用完msg后，把msg设置为null，这样垃圾回收器也会回收msg的内存空间



6-改变哈希值

- 改变哈希值，当一个对象被存储进Hashset集合中以后，就不能修改这个对象中的那些参与计算哈希值的字段了。否则，对象修改后的哈希值与最初存储进Hashset集合中时的哈希值就不同了，在这种情况下，即使在contains方法使用该对象的当前引用作为的参数去Hashset集合中检索对象，也将返回找不到对象的结果，这也会导致无法从Hashset集合中单独删除当前对象，造成内存泄漏。这也是 string 为什么被设置成了不可变类型，我们可以放心地把 string 存入 Hashset，或者把String 当做 HashMap 的 key 值。当我们想把自己定义的类保存到散列表的时候，需要保证对象的 hashcode 不可变

```java
/**
 * 演示内存泄漏
 */
public class ChangeHashCode1 {
    public static void main(String[] args) {
        HashSet<Point> hs = new HashSet<Point>();
        Point cc = new Point();
        cc.setX(10);//hashCode = 41
        hs.add(cc);

        cc.setX(20);//hashCode = 51  此行为导致了内存的泄漏

        System.out.println("hs.remove = " + hs.remove(cc));// hs.remove = false
        hs.add(cc);
        System.out.println("hs.size = " + hs.size());// hs.size = 2

        System.out.println(hs); // [Point{x=20}, Point{x=20}]
    }

}

class Point {
    int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        if (x != other.x) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                '}';
    }
}
```



```java
/**
 * 演示内存泄漏
 */
public class ChangeHashCode {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        set.add(p1);
        set.add(p2);

        p1.name = "CC";//导致了内存的泄漏
        set.remove(p1); //删除失败

        System.out.println(set);

        set.add(new Person(1001, "CC"));
        System.out.println(set);

        set.add(new Person(1001, "AA"));
        System.out.println(set);

    }
}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

7-缓存泄漏

- 内存泄漏的另一个常见来源是缓存，一旦把对象引用放入到缓存中，他就很容易遗忘。比如：之前项目在一次上线的时候，应用启动奇慢直到夯死，就是因为代码中会加载一个表中的数据到缓存(内存)中，测试环境只有几百条数据，但是生产环境有几百万的数据。对于这个问题，可以使用WeakHashMap代表缓存，此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值

```java
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
/**
 * 演示内存泄漏
 */
public class MapTest {
    static Map wMap = new WeakHashMap();
    static Map map = new HashMap();

    public static void main(String[] args) {
        init();
        testWeakHashMap();
        testHashMap();
    }

    public static void init() {
        String ref1 = new String("obejct1");
        String ref2 = new String("obejct2");
        String ref3 = new String("obejct3");
        String ref4 = new String("obejct4");
        wMap.put(ref1, "cacheObject1");
        wMap.put(ref2, "cacheObject2");
        map.put(ref3, "cacheObject3");
        map.put(ref4, "cacheObject4");
        System.out.println("String引用ref1，ref2，ref3，ref4 消失");

    }

    public static void testWeakHashMap() {

        System.out.println("WeakHashMap GC之前");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
        try {
            System.gc();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WeakHashMap GC之后");
        for (Object o : wMap.entrySet()) {
            System.out.println(o);
        }
    }

    public static void testHashMap() {
        System.out.println("HashMap GC之前");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
        try {
            System.gc();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HashMap GC之后");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }
    }

}
/**
 * 结果
 * String引用ref1，ref2，ref3，ref4 消失
 * WeakHashMap GC之前
 * obejct2=cacheObject2
 * obejct1=cacheObject1
 * WeakHashMap GC之后
 * HashMap GC之前
 * obejct4=cacheObject4
 * obejct3=cacheObject3
 * Disconnected from the target VM, address: '127.0.0.1:51628', transport: 'socket'
 * HashMap GC之后
 * obejct4=cacheObject4
 * obejct3=cacheObject3
 **/
```

8-监听器和回调

- 内存泄漏第三个常见来源是监听器和其他回调，如果客户端在实现的API中注册回调，却没有显示的取消，那么就会积聚。需要确保回调立即被当作垃圾回收的最佳方法是只保存它的弱引用，例如将他们保存成为WeakHashMap中的键

###### 3.内存泄漏案例分析

内存泄漏案例：下列程序并没有明显的错误，但是这段程序有一个内存泄漏，随着GC活动的增加，或者内存占用的不断增加，程序性能的降低就会表现出来，严重时可导致内存泄漏，但是这种失败情况相对较少。代码的主要问题在pop函数，当进行大量的pop操作时，由于引用未进行置空，gc是不会释放的。如果栈先增长，再收缩，那么从栈中弹出的对象将不会被当作垃圾回收，即使程序不再使用栈中的这些队象，他们也不会回收，因为中仍然保存这对象的引用，俗称过期引用，这个内存泄露很隐蔽

```java
import java.util.Arrays;
import java.util.EmptyStackException;
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) { //入栈
        ensureCapacity();
        elements[size++] = e;
    }
    //存在内存泄漏
//    public Object pop() { //出栈
//        if (size == 0)
//            throw new EmptyStackException();
//        return elements[--size];
//    }

    // 改进： 一旦引用过期，清空这些引用，将引用置空
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

## 3.JVM运行时参数

### 3.1 JVM参数选项类型

`JVM`参数选项类型：标准参数选项、-X参数选项、-XX参数选项

##### 3.1.1 标准参数选项

标准参数选项：

- 特点：比较稳定，后续版本基本不会变化。以-开头
- 运行java或者java -help可以看到所有的标准选项
- server与-client：
  - Hotspot JVM有两种模式，分别是server和client，分别通过-server和-client模式设置
  - 在32位Windows系统上，默认使用Client类型的JVM。要想使用Server模式，则机器配置至少有2个以上的CPU和2G以上的物理内存。 client模式适用于对内存要求较小的桌面应用程序，默认使用Serial串行垃圾收集器
  - 64位机器上只支持server模式的JVM，适用于需要大内存的应用程序，默认使用并行垃圾收集器
  - 关于server和client的官网介绍为：https://docs.oracle.com/javase/8/docs/technotes/guides/vm/server-class.html

各种选项：

```shell
C:\Users\18>java
用法: java [-options] class [args...]
           (执行类)
   或  java [-options] -jar jarfile [args...]
           (执行 jar 文件)
其中选项包括:
    -d32          使用 32 位数据模型 (如果可用)
    -d64          使用 64 位数据模型 (如果可用)
    -server       选择 "server" VM
                  默认 VM 是 server.

    -cp <目录和 zip/jar 文件的类搜索路径>
    -classpath <目录和 zip/jar 文件的类搜索路径>
                  用 ; 分隔的目录, JAR 档案
                  和 ZIP 档案列表, 用于搜索类文件。
    -D<名称>=<值>
                  设置系统属性
    -verbose:[class|gc|jni]
                  启用详细输出
    -version      输出产品版本并退出
    -version:<值>
                  警告: 此功能已过时, 将在
                  未来发行版中删除。
                  需要指定的版本才能运行
    -showversion  输出产品版本并继续
    -jre-restrict-search | -no-jre-restrict-search
                  警告: 此功能已过时, 将在
                  未来发行版中删除。
                  在版本搜索中包括/排除用户专用 JRE
    -? -help      输出此帮助消息
    -X            输出非标准选项的帮助
    -ea[:<packagename>...|:<classname>]
    -enableassertions[:<packagename>...|:<classname>]
                  按指定的粒度启用断言
    -da[:<packagename>...|:<classname>]
    -disableassertions[:<packagename>...|:<classname>]
                  禁用具有指定粒度的断言
    -esa | -enablesystemassertions
                  启用系统断言
    -dsa | -disablesystemassertions
                  禁用系统断言
    -agentlib:<libname>[=<选项>]
                  加载本机代理库 <libname>, 例如 -agentlib:hprof
                  另请参阅 -agentlib:jdwp=help 和 -agentlib:hprof=help
    -agentpath:<pathname>[=<选项>]
                  按完整路径名加载本机代理库
    -javaagent:<jarpath>[=<选项>]
                  加载 Java 编程语言代理, 请参阅 java.lang.instrument
    -splash:<imagepath>
                  使用指定的图像显示启动屏幕
有关详细信息, 请参阅 http://www.oracle.com/technetwork/java/javase/documentation/index.html。
```

##### 3.1.2 -X参数选项

-X参数选项：

- 特点：以-X开头，非标准化参数。功能还是比较稳定的。但官方说后续版本可能会变更
- 运行java -X命令可以看到所有的X选项

各种选项：
```shell
C:\Users\18>java -X
    -Xmixed           混合模式执行（默认）
    -Xint             仅解释模式执行
    -Xbootclasspath:<用 ; 分隔的目录和 zip/jar 文件>
                      设置引导类和资源的搜索路径
    -Xbootclasspath/a:<用 ; 分隔的目录和 zip/jar 文件>
                      附加在引导类路径末尾
    -Xbootclasspath/p:<用 ; 分隔的目录和 zip/jar 文件>
                      置于引导类路径之前
    -Xdiag            显示附加诊断消息
    -Xnoclassgc        禁用类垃圾收集
    -Xincgc           启用增量垃圾收集
    -Xloggc:<file>    将 GC 状态记录在文件中（带时间戳）
    -Xbatch           禁用后台编译
    -Xms<size>        设置初始 Java 堆大小
    -Xmx<size>        设置最大 Java 堆大小
    -Xss<size>        设置 Java 线程堆栈大小
    -Xprof            输出 cpu 分析数据
    -Xfuture          启用最严格的检查，预计会成为将来的默认值
    -Xrs              减少 Java/VM 对操作系统信号的使用（请参阅文档）
    -Xcheck:jni       对 JNI 函数执行其他检查
    -Xshare:off       不尝试使用共享类数据
    -Xshare:auto      在可能的情况下使用共享类数据（默认）
    -Xshare:on        要求使用共享类数据，否则将失败。
    -XshowSettings    显示所有设置并继续
    -XshowSettings:system
                      （仅限 Linux）显示系统或容器
                      配置并继续
    -XshowSettings:all
                      显示所有设置并继续
    -XshowSettings:vm 显示所有与 vm 相关的设置并继续
    -XshowSettings:properties
                      显示所有属性设置并继续
    -XshowSettings:locale
                      显示所有与区域设置相关的设置并继续

-X 选项是非标准选项。如有更改，恕不另行通知。
```

JVM的JIT编译模式相关的选项：

- -Xint：禁用JIT，所有字节码都被解释执行，这个模式的速度最慢的
- -Xcomp：所有字节码第一次使用就都被编译成本地代码，然后再执行
- -Xmixed：`-Xmixed` 是Java虚拟机（JVM）的JIT编译模式之一，它是默认的编译模式。在 `-Xmixed` 模式下，JVM会根据代码的执行情况动态地选择哪些方法进行即时编译（JIT编译），而哪些方法保持解释执行。这种混合模式的设计旨在根据程序的实际运行情况来优化性能，既可以利用JIT编译提升执行速度，又可以避免对所有代码都进行编译所带来的启动时间延长和额外的内存消耗

特别的：

- `-Xms<size>`：设置初始 Java 堆大小，等价于`-XX:InitialHeapSize`
- ``Xmx<size>`：设置最大 Java 堆大小，等价于`-XX:MaxHeapSize`
- `Xss<size>`：设置Java线程堆栈大小，`-XX:ThreadStackSize`

##### 3.1.3 -XX参数选项

 -XX参数选项：

- 特点：以-XX开头，非标准化参数，使用的最多的参数类型，这类选项属于实验性，不稳定
- 作用：用于开发和调试`JVM`

 -XX参数选项分类：

- Boolean类型格式
  - `-XX:+<option>`：表示启用 option 属性
  - `-XX:-<option>`：表示禁用 option 属性（有的指令默认是开启的，所以可以使用-关闭）
  - 举例：
    - `-XX:+UseParallelGC` ：选择垃圾收集器为并行收集器
    - `-XX:+UseG1GC`： 表示启用G1收集器
    - `-XX:+UseAdaptivesizePolicy`： 自动选择年轻代区大小和相应的Survivor区比例

- 非Boolean类型格式（key-value类型）
  - 子类型1：数值型格式：`-XX:<option>=<number>`。number表示数值，number可以带上单位，比如：'m'、'M’表示兆，'k’、'K'表示'g’、'G’表示 g(例如 32k跟32768是一样的效果)Kb
    - `-XX:Newsize=1024m`： 表示设置新生代初始大小为1024兆
    - `-XX:MaxGCPauseMillis=500`： 表示设置GC停顿时间：500毫秒
    - `-XX:GCTimeRatio=19`：表示设置吞吐量
    - `-XX:NewRatio=2`：表示新生代与老年代的比例
  - 子类型2：非数值型格式：`-XX:<name>=<string>`
    - `-XX:HeapDumpPath=/usr/local/heapdump.hprof`： 用来指定heap转存文件的存储路径

- 特别地，`-XX:+PrintFlagsFinal`
  - 输出所有参数的名称和默认值
  - 默认不包括Diagnostic和Experimental的参数
  - 可以配合`-XX:+UnlockDiagnosticVMOptions`和`-XX:UnlockExperimentalVMOptions`使用

### 3.2 添加`JVM`参数选项

1.IDEA：在`VM options`中进行添加

2.运行jar包：`java -Xms50m -Xmx50m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -jar demo.jar`

3.通过Tomcat运行war包：

- Linux系统下可以在`tomcat/bin/catalina.sh`中添加类似如下配置：`JAVA OPTS="-Xms512H -Xmx1824M"`
- Windows系统下在`catalina.bat`中添加类似如下配置：`set "JAVA OPTS=-Xms512M -Xmx1024M"`

4.程序运行过程中：

- 使用`jinfo -flag <name>=<value><pid>`： 设置非Boolean类型参数
- 使用`jinfo -flag[*|-]<name><pid>`： 设置Boolean类型参数

### 3.3 常用的JVM参数选项

##### 3.3.1 打印设置的XX选项及值

- `-XX:+PrintCommandlineFlags`: 可以让在程序运行前打印出用户手动设置或者JVM自动设置的XX选项
- `-XX:+PrintFlagsinitial`:  表示打印出所有XX选项的默认值
- `-XX:+PrintFlagsFinal`: 表示打印出XX选项在运行程序时生效的值
- `-XX:+PrintVMOptions`: 打印JVM的参数

##### 3.3.2 堆、栈、方法区等内存大小设置

栈：

- `-Xss128k`：设置每个线程的栈大小为128k。等价于`-XX:ThreadStackSize=128k`

堆内存：

- -Xms3550m：等价于-XX:initialHeapSize，设置JVM初始堆内存为3550M
- -Xmx3550m：等价于-XX:MaxHeapSize，设置JVM最大堆内存为3550M
- Xmn2g：设置年轻代大小为2G。官方推荐配置为整个堆大小的3/8
- -XX:NewSize=1024m：设置年轻代初始值为1024M
- -XX:MaxNewSize=1024m: 设置年轻代最大值为1024M
- -XX:SurvivorRatio=8：设置年轻代中Eden区与一个Survivor区的比值，默认为8
- -XX:+UseAdaptiveSizePolicy：自动选择各区大小比例
- -XX:NewRatio=4：设置老年代与年轻代(包括1个Eden和2个Survivor区)的比值
- -XX:PretenureSizeThreadshold=1024：设置让大于此阈值的对象直接分配在老年代，单位为字节。只对Serial、ParNew收集器有效
- -XX:MaxTenuringThreshold=15：默认值为15。新生代每次MinorGC后，还存活的对象年龄+1，当对象的年龄大于设置的这个值时就进入老年代

- -XX:-+PrintTenuringDistribution：让JVM在每次MinorGC后打印出当前使用的Survivor中对象的年龄分布
- -XX:TargetSurvivorRatio：表示MinorGC结束后Survivor区城中占用空间的期望比例

方法区：

- 永久代：
  - -XX:PermSize=256m。设置永久代初始值为256M
  - -XX:MaxPermSize=256m。设置永久代最大值为256M

- 元空间：
  - -XX:MetaspaceSize: 初始空间大小
  - -XX:MaxMetaspaceSize: 最大空间，默认没有限制
  - -XX:+UseCompressedoops: 压缩对象指针
  - -XX:+UseCompressedClassPointers: 压缩类指针
  - -XX:CompressedClassSpaceSize: 设置Klass Metaspace的大小，默认1G

直接内存：

- -XX:MaxDirectMemorySize：指定DirectMemory容量，若未指定，则默认与Java堆最大值一样

##### 3.3.3 OutofMemory相关的选项

-XX:+HeapDumpOnÃutOfMemoryEror：表示在内存出现OOM的时候，把Heap转存(Dump)到文件以便后续分析

-XX:+HeapDumpBeforeFullGC：表示在出现FullGC之前，生成Heap转储文件

`-XX:HeapDumpPath=<path>`：指定heap转存文件的存储路径

-XX:OnOutOfMemoryÉrror：指定一个可行性程序或者脚本的路径，当发生OOM的时候，去执行这个脚本。对OnOutOfMemoryÉrror的运维处理。以部署在linux系统/opt/Server目录下的Server.jar为例

```
1.在run.sh启动脚本中添加jvm参数:
        -XX:OnOutOfMemoryError=/opt/Server/restart.sh

2.restart.sh脚本
    linux环境:
                #!/bin/bash
                pid=$(ps -ef | grep Server.jar | awk '{if($8=="java") {print $2}}')
                kill -9 $pid
                cd /opt/Server/; sh run.sh
    Windows环境:
                echo off
                wmic process where Name='java.exe' delete
                cd D:\Server
                start run.bat
```

##### 3.3.4 垃圾收集器相关选项

7款经典收集器与垃圾分代之间的关系：

![image-20240826210718503](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240826210718503.png)

垃圾收集器的组合关系：

![image-20240826211011943](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240826211011943.png)

查看默认垃圾收集器：

- `-XX:+PrintCommandLineFlags`：查看命令行相关参数(包含使用的垃圾收集器)
- 使用命令行指令：`jinfo -flag` 相关垃圾回收器参数 进程ID

Serial回收器：

- Serial收集器作为HotSpot中client模式下的默认新生代垃圾收集器。Serial Old是运行在client模式下默认的老年代的垃圾回收器
- `-XX:+UseSerialGC`：指定年轻代和老年代都使用串行收集器。等价于 新生代用Serial GC，且老年代用Serial Old GC。可以获得最高的单线程收集效率

`ParNew`回收器：

- `-XX:+UseParNewGC`：手动指定使用ParNew收集器执行内存回收任务。它表示年轻代使用并行收集器，不影响老年代
- `-XX:ParallelGcThreads=N`：限制线程数量，默认开启和CPU数据相同的线程数

Parallel回收器：

- `-XX:ParallelGcThreads`： 设置年轻代并行收集器的线程数。一般地，最好与CPU数量相等，以避免过多的线程数影响垃圾收集性能。在默认情况下，当CPU 数量小于8个，`ParallelGCThreads` 的值等于CPU 数量。当CPU数量大于8个，ParallelGCThreads 的值等于3+[5*CPU_Count]/8]
- `-XX:MaxGCPauseMillis`：设置垃圾收集器最大停顿时间(即STW的时间)。单位是毫秒。为了尽可能地把停顿时间控制在MaxGCPauseMills以内，收集器在工作时会调整Java堆大小或者其他一些参数。对于用户来讲，停顿时间越短体验越好。但是在服务器端，我们注重高并发，整体的吞吐量。所以服务器端适合Parallel，进行控制。该参数使用需谨慎
- `-XX:GCTimeRatio`： 垃圾收集时间占总时间的比例(=1/(N +1))。用于衡量吞吐量的大小。取值范围(8,188)。默认值99，也就是垃圾回收时间不超过1%。与前一个`-XX:MaxGCPauseMillis`参数有一定矛盾性。暂停时间越长，Radio参数就容易超过设定的比例
- `-XX:+UseAdaptivesizePolicy`： 设置Parallel scavenge收集器具有自适应调节策略。在这种模式下，年轻代的大小、Eden和survivor的比例、晋升老年代的对象年龄等参数会被自动调整，已达到在堆大小、吞吐量和停顿时间之间的平衡点。在手动调优比较困难的场合，可以直接使用这种自适应的方式，仅指定虚拟机的最大堆、目标的吞吐量(GCTimeRatio)和停顿时间(MaxGCPauseMills)，让虚拟机自己完成调优工作

`CMS`回收器：

- `-XX:UseConcMarkSweepGC`：手动指定使用CMS 收集器执行内存回收任务。开启该参数后会自动将`-XX:+UseParNewGC`打开。即：ParNew(Young区用) + CMS(Old区用) + serial Old的组合
- `-XX:CMsinitiating0ccupanyFraction`： 设置堆内存使用率的阀值，一旦达到该阙值，便开始进行回收
  - JDK5及以前版本的默认值为68,即当老年代的空间使用率达到68%时，会执行一次CMS 回收。JDK6及以上版本默认值为92%
  - 如果内存增长缓慢，则可以设置一个稍大的值，大的值可以有效降低CMS的触发频率，减少老年代回收的次数可以较为明显地改善应用程序性能。反之，如果应用程序内存使用率增长很快，则应该降低这个阈值，以避免频繁触发老年代串行收集器。因此通过该选项便可以有效降低Full GC 的执行次数

- `-XX:+UseCMsCompactAtFullCollection`： 用于指定在执行完Full GC后对内存空间进行压缩整理，以此避免内存碎片的产生。不过由于内存压缩整理过程无法并发执行，所带来的问题就是停顿时间变得更长了
- `-XX:CMSFullGCsBeforecompaction`：设置在执行多少次Full GC后对内存空间进行压缩整理
- `-XX:ParallelcMsThreads`： 设置CMS的线程数。CMS 默认启动的线程数是(ParallelGcThreads+3)/4，ParallelGCThreads 是年轻代并行收集器的线程数。当CPU 资源比较紧张时，受到CMS收集器线程的影响，应用程序的性能在垃圾回收阶段可能会非常糟糕
- `-XX:ConcGCThreads`：设置并发垃圾收集的线程数，默认该值是基于ParallelGCThreads计算出来的
- `-XX:+UseCMSInitiating0ccupancyOnly`：是否动态可调，用这个参数可以使CMS一直按CMSInitiatingOccupancyFraction设定的值启动
- `-XX:+CMsscavengeBeforeRemark`：强制hotspot虛拟机在cms remark阶段之前做一次minorgc，用于提高remark阶段的速度
- `-XX:+CMsclassUnloadingEnable`：如果有的话，启用回收Perm 区(JDK8之前)
- `-XX:+CMSPara1lelInitialEnabled`：用于开启CMs initial-mark阶段采用多线程的方式进行标记，用于提高标记速度，在Java8开始已经默认开启
- `-XX:+CMSParallelRemarkEnabled`：用户开启CMS remark阶段采用多线程的方式进行重新标记，默认开启
- `-XX:+ExplicitGcInvokesConcurrent`、`-XX:+ExplicitGcInvokesConcurrentAndUnloadsClasses`：这两个参数用户指定hotspot虚拟在执行system.gc()时使用CMS周期
- `-XX:+CMSPrecleaningEnabled`：指定CMS是否需要进行Pre cleaning这个阶段
- 特别说明：
  - JDK9新特性：CMS被标记为Deprecate了(JEP291)。如果对JDK 9及以上版本的Hotspot虚拟机使用参数-XX:+UseConcMarkSweepGC来开启CMS收集器的话，用户会收到一个警告信息，提示CMS未来将会被废弃
  - JDK14新特性：删除CMS垃圾回收器(JEP363)。移除了CMS垃圾收集器，如果在JDK14中使用-XX:+UseConcMarkSweepGc的话，JVM不会报错，只是给出一个warning信息，但是不会exit。JVM会自动回退以默认GC方式启动JVM

G1回收器：

- `-XX:MaxGCPauseMillis`：设置期望达到的最大GC停顿时间指标(JVM会尽力实现，但不保证达到)。默认值是200ms
- `-XX:ParallelGCThread`：设置STW时GC线程数的值。最多设置为8
- `-XX:ConcGCThreads`：设置并发标记的线程数。将n设置为并行垃圾回收线程数(ParallelGCThreads)的1/4左右
- `-XX:InitiatingHeapOccupancyPercent`：设置触发并发GC周期的Java堆占用率值。超过此值，就触发GC。默认值是45
- `-XX:G1NewSizePercent`、`-XX:G1MaxNewSizePercent`：新生代占用整个堆内存的最小百分比(默认5%)、最大百分比(默认60%)
- `-XX:G1ReservePercent=10`：保留内存区域，防止 to space(Survivor中的to区)溢出
- Mixed GC调优参数：
  - 注意：G1收集器主要涉及到Mixed GC，Mixed GC会回收young 区和部分old区
  - G1关于Mixed GC调优常用参数：
    - `-XX:InitiatingHeapOccupancyPercent`：设置堆占用率的百分比(8到100)达到这个数值的时候触发global concurrent marking(全局并发标记)，默认为45%。值为8表示间断进行全局并发标记
    - `-XX:G1MixedGcLiveThresholdPercent`：设置Old区的region被回收时候的对象占比，默认占用率为85%。只有Old区的region中存活的对象占用达到了这个百分比，才会在Mixed GC中被回收
    - `-XX:G1HeapWastePercent`：在global concurrent marking(全局并发标记)结束之后，可以知道所有的区有多少空间要被回收，在每次youngGC之后和再次发生Mixed Gc之前，会检查垃圾占比是否达到此参数，只有达到了，下次才会发生Mixed GC
    - `-XX:G1MixedGCcountTarget`：一次global concurrent marking(全局并发标记)之后，最多执行Mixed GC的次数，默认是8
    - `-XX:G1OldcSetRegionThresholdPercent`：设置Mixed Gc收集周期中要收集的Old region数的上限。默认值是Java堆的18%

怎么选择垃圾回收器：

- 优先调整堆的大小让JVM自适应完成
- 如果内存小于100M，使用串行收集器
- 如果是单核、单机程序，并且没有停顿时间的要求，串行收集器
- 如果是多CPU、需要高吞吐量、允许停顿时间超过1秒，选择并行或者JVM自己选择
- 如果是多CPU、追求低停顿时间，需快速响应(比如延迟不能超过1秒，如互联网应用)，使用并发收集器。官方推荐G1，性能高。现在互联网的项目，基本都是使用G1

##### 3.3.5 GC日志相关选项

`-verbose:gC`：输出gc日志信息，默认输出到标准输出

`-XX:+PrintGC`：等同于-verbose:gc，表示打开简化的GC日志

`-XX:PrintGCDetails`：在发生垃圾回收时打印内存回收详细的日志，并在进程退出时输出当前内存各区域分配情况

`-XX:PrintGCTimeStamps`：输出GC发生时的时间截。不可以独立便用，需要配合`-XX:+PrintGCDetails`使用

`-XX:+PrintGCDateStamps`：输出GC发生时的时间截(以日期的形式，如2013-05-04T21:53:59.234+0800)。不可以独立使用，需要配合`-XX:+PrintGCDetails`使用

`-XX:+PrintHeapAtGC`：每一次GC前和GC后，都打印堆信息

`-Xloggc<file>`：把GC日志写入到一个文件中去，而不是打印到标准输出中

`-XX:+TraceClassloading`：监控类的加载

`-XX:+PrintGCApplicationstoppedTime`：打印GC时线程的停顿时间

`-XX:+PrintReferenceGC`：记录回收了多少种不同引用类型的引用

`-XX:+PrintGCApplicationConcurrentTime`：垃圾收集之前打印出应用未中断的执行时间

`-XX:+PrintTenuringDistribution`：让JVM在每次MinorGC后打印出当前使用的Survivor中对象的年龄分布

`-XX:+UseGCLogFileRotation`：启用GC日志文件的自动转储

`-XX:NumberOfGClogFiles=1`：GC日志文件的循环数目

`-XX:GCLogFileSize=1M`：控制GC日志文件的大小

##### 3.3.6 其他参数

`-XX:+DisableExplicitGC`：禁止hotspot执行System.gc()，默认禁用

`-XX:ReservedCodeCacheSize=<n>[glm|k]`、`-XX:initialCodeCacheSize=<n>[g|m|k]`：指定代码缓存的大小

`-XX:+UseCodeCacheFlushing`：使用该参数让JVM放弃一些被编译的代码，避免代码缓存被占满时JVM切换到interpreted-only的情况

`-XX:+DoEscapeAnalysis`：开启逃逸分析

`-XX+UseBiasedlocking`：开启偏向锁

`-XX:+UseLargePages`：开启使用大页面

`-XX:+UseTLAB`：使用TLAB，默认打开

`-XX:+PrntTLAB`：打印TLAB的使用情况

`-XX:TLABSize`：设置TLAB大小

### 3.4 通过Java代码获取JVM参数

java提供了java.lang.management包用于监视和管理Java虚拟机和]ava运行时中的其他组件，它允许本地和远程监控和管理运行的Java虚拟机。其中ManagementFactory这个类还是挺常用的。另外还有Runtime类也可以获取一些内存、CPU核数等相关的数据。通过这些api可以监控我们的应用服务器的堆内存使用情况，设置一些值进行报警等处理

```java
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
/**
 * 监控应用服务器的堆内存使用情况，设置一些阈值进行报警等处理
 */
public class MemoryMonitor {
    public static void main(String[] args) {
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit() / 1024 / 1024 + "m");
        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "m");
        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "m");
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: " + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + memorymbean.getNonHeapMemoryUsage());

        System.out.println("=======================通过java来获取相关系统状态============================ ");
        System.out.println("当前堆内存大小totalMemory " + (int) Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m");// 当前堆内存大小
        System.out.println("空闲堆内存大小freeMemory " + (int) Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");// 空闲堆内存大小
        System.out.println("最大可用总堆内存maxMemory " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m");// 最大可用总堆内存大小

    }
}
```

##  4.分析GC日志

### 4.1 GC日志参数

`-verbose:gc`：输出gc日志信息，默认输出到标准输出

`-XX:+PrintGC`：输出GC日志。类似：-verbose:gc

`-XX:+PrintGCDetails`：在发生垃圾回收时打印内存回收详细的日志，井在进程退出时输出当前内存各区城分配情况

`-XX:+PrintGCTimeStamps`：输出GC发生时的时间截

`-XX:+PrintGCDateStamps`：输出GC发生时的时间截(以日期的形式，如2013-05-04T21:53:59.234+0800

`-XX:+PrintHeapAtGC`：每一次GC前和GC后，都打印堆信息

`-Xxloggc<file>`：表示把GC日志写入到一个文件中去，而不是打印到标准输出中

### 4.2 GC日志格式

##### 4.2.1 GC分类（复习）

针对Hotspot VM的实现，它里面的GC按照回收区域又分为两大种类型：一种是部分收集(Partial GC)，一种是整堆收集(Full GC)

- 部分收集：不是完整收集整个Java堆的垃圾收集。其中又分为
  - 新生代收集(Minor Gc/Young GC)：只是新生代(Eden\s0，s1)的垃圾收集
  - 老年代收集(Major GC/Old GC)：只是老年代的垃圾收集
    - 目前，只有CMS GC会有单独收集老年代的行为
    - 注意，很多时候Major GC会和Full GC混淆使用，需要具体分辨是老年代回收还是整堆回收
  - 混合收集(Mixed GC)：收集整个新生代以及部分老年代的垃圾收集
    - 目前，只有G1 GC会有这种行为

- 整堆收集(Full GC)：收集整个java堆和方法区的垃圾收集

##### 4.2.2 GC日志分类

MinorGC：

```
MinorGC(或young GC或YGC)日志:
[6C(Allocation Failure)[PsYoungGen:31744K->2192K(36864K)]
31744K->2200K(121856K),0.0139308 secs][Times: user=0.05 sys=0.01, real=0.01 secs]
```

![image-20240826233211157](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240826233211157.png)

`FulIGC`：

```
Fu11 GC日志介绍:
Fu11 Gc(Metadata Gc Threshold)[PsYoungGen: 5104K->0K(132096K)]
[ParoldGen:416K->5453K(50176K)5520K->5453K(182272K)，[Metaspace:
20637K->20637K(1067008K)],0.0245883 secs][Times: user=0.06 sys=0.00
real=0.02 secs]
```

![image-20240826235413622](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/jvm/image-20240826235413622.png)



##### 4.2.3 GC日志结构剖析

垃圾收集器：

- 使用Serial收集器在新生代的名字是Default New Generation，因此显示的是"[DefNew
- 使用ParNew收集器在新生代的名字会变成"[ParNew",意思是"Parallel New Generation
- 使用Parallel scavenge收集器在新生代的名字是"[PSYoungGen"，这里的JDK1.7使用的就是PSYoungGen
- 使用Parallel Old Generation收集器在老年代的名字是"[ParOldGen"
- 使用G1收集器的话，会显示为"garbage-first heap"
- Allocation Failure：表明本次引起GC的原因是因为在年轻代中没有足够的空间能够存储新的数据了

GC前后情况：

- 通过图示，可以发现GC日志格式的规律一般都是：GC前内存占用一>GC后内存占用(该区域内存总大小)
- [PSYoungGen:5986K->696K(8704K)]5986K->704K(9216K)
- 中括号内：GC回收前年轻代堆大小，回收后大小，(年轻代堆总大小)
- 括号外：GC回收前年轻代和老年代大小，回收后大小，(年轻代和老年代总大小)

GC时间：

- GC日志中有三个时间：user，sys和real
- user- 进程执行用户态代码(核心之外)所使用的时间。这是执行此进程所使用的实际CPU 时间，其他进程和此进程阻塞的时间并不包括在内。在垃圾收集的情况下，表示GC 线程执行所使用的 CPU 总时间
- sys- 进程在内核态消耗的 CPU 时间，即在内核执行系统调用或等待系统事件所使用的CPU 时间
- real - 程序从开始到结束所用的时钟时间。这个时间包括其他进程使用的时间片和进程阻塞的时间(比如等待 I/O 完成)。对于并行gc，这个数字应该接近(用户时间+系统时间)除以垃圾收集器使用的线程数
- 由于多核的原因，一般的GC事件中，real time是小于sys +user time的，因为一般是多个线程并发的去做GC，所以real time是要小于sys+user time的。如果real > sys+user的话，则应用可能存在下列问题：IO负载非常重或者是CPU不够用

### 4.3 GC日志分析工具

上节介绍了GC日志的打印及含义，但是GC日志看起来比较麻烦，本节将会介绍一下GC日志可视化分析工具GCeasy和GCviewer等。通过GC日志可视化分析工具，我可以很方便的看到JVM各个分代的内存使用情况、垃圾回收次数、垃圾回收的原因、垃圾回收占用的时间、吞吐量等，这些指标在进行JVM调优的时候是很有用的

如果想把GC日志存到文件的话，是下面这个参数：

- `-Xloggc:/path/to/gc.log`
- 然后就可以用一些工具去分析这些gc日志



```java
import java.util.ArrayList;
/**
 * 测试生成详细的日志文件
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
 */
public class GCLogTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            byte[] arr = new byte[1024 * 50];//50KB
            list.add(arr);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
```



##### 4.3.1 GCeasy

Gceasy：一款超好用的在线分析GC日志的网站，官网地址：https://gceasy.io/

GCeasy是一款在线的GC日志分析器，可以通过GC日志分析进行内存泄漏检测、GC暂停原因分析、JVM配置建议优化等功能，而且是可以免费使用的(有一些服务是收费的)

##### 4.3.2 GCViewer

Gceasy是一款在线的GC日志分析器，下面介绍一个离线版的GCViewer

GCViewer是一个免费的、开源的分析小工具，用于可视化查看由SUN/Oracle、IBM、HP和BEA。Java虚拟机产生的垃圾收集器的日志

GCViewer用于可视化Java JVM选项 `-verbose:gc` 和.NET生成的数据`-Xloggc:<file>`。它还计算与垃圾回收相关的性能指标(吞吐量，累积的暂停，最长的暂停等)。当通过更改世代大小或设置初始堆大小来调整特定应用程序的垃圾回收时，此功能非常有用

1.下载GCViewer工具

- 源码下载：https://github.com/chewiebug/GCViewer
- 运行版本下载：https://github.com/chewiebug/GCViewer/wiki/Changelog

2.只需双击gcviewer-1.3x.jar或运行java -jar gcviewer-1.3x.jar(它需要运行java1.8 vm)，即可启动GCViewer(gui)



##### 4.3.3 其他工具

GChisto：

- GChisto是一款专业分析gc日志的工具，可以通过gc日志来分析:MinorGc、Full GC的次数、频率、持续时间等，通过列表、报表、图表等不同形式来反应gc的情况
- 虽然界面略显粗糙，但是功能还是不错的

HPjmeter：

- 工具很强大，但只能打开由以下参数生成的GCIog，`-verboseigc`、`-Xloggc:gc.log`，添加其他参数生成的gclog无法打开
- HPjmeter集成了以前的HPjtune功能，可以分析在HP机器上产生的垃圾回收日志文件

## 5.`OOM`常见各种场景及解决方案

```
案例1:堆溢出
案例2:元空间溢出
案例3: GC overhead limit exceeded
案例4:线程溢出
```




















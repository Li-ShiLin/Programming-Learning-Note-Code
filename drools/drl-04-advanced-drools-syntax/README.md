<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. Drools 高级 DRL 语法概述](#1-drools-%E9%AB%98%E7%BA%A7-drl-%E8%AF%AD%E6%B3%95%E6%A6%82%E8%BF%B0)
  - [1.1 本模块主题一览](#11-%E6%9C%AC%E6%A8%A1%E5%9D%97%E4%B8%BB%E9%A2%98%E4%B8%80%E8%A7%88)
  - [1.2 运行与阅读说明](#12-%E8%BF%90%E8%A1%8C%E4%B8%8E%E9%98%85%E8%AF%BB%E8%AF%B4%E6%98%8E)
- [2. 工程搭建](#2-%E5%B7%A5%E7%A8%8B%E6%90%AD%E5%BB%BA)
  - [2.1 第一步：配置 `pom.xml`](#21-%E7%AC%AC%E4%B8%80%E6%AD%A5%E9%85%8D%E7%BD%AE-pomxml)
  - [2.2 第二步：配置 `kmodule.xml`](#22-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E9%85%8D%E7%BD%AE-kmodulexml)
- [3. Java 源码（实体与服务）](#3-java-%E6%BA%90%E7%A0%81%E5%AE%9E%E4%BD%93%E4%B8%8E%E6%9C%8D%E5%8A%A1)
  - [3.1 `Student.java`](#31-studentjava)
  - [3.2 `UserService.java`](#32-userservicejava)
- [4. 分案例：规则文件与测试](#4-%E5%88%86%E6%A1%88%E4%BE%8B%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6%E4%B8%8E%E6%B5%8B%E8%AF%95)
  - [4.0 测试公共工具：`AgendaFilters.java`](#40-%E6%B5%8B%E8%AF%95%E5%85%AC%E5%85%B1%E5%B7%A5%E5%85%B7agendafiltersjava)
  - [4.1 RHS：`drools.getWorkingMemory()`、`drools.getRule()`（`DroolsMethodsTest`）](#41-rhsdroolsgetworkingmemorydroolsgetruledroolsmethodstest)
    - [4.1.1 说明](#411-%E8%AF%B4%E6%98%8E)
    - [4.1.2 规则文件 `droolsMethods.drl`](#412-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-droolsmethodsdrl)
    - [4.1.3 对应测试代码（`DroolsMethodsTest#testDroolsMethods`）](#413-%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81droolsmethodstesttestdroolsmethods)
  - [4.2 `function` 自定义函数（`FunctionTest`）](#42-function-%E8%87%AA%E5%AE%9A%E4%B9%89%E5%87%BD%E6%95%B0functiontest)
    - [4.2.1 说明](#421-%E8%AF%B4%E6%98%8E)
    - [4.2.2 规则文件 `function.drl`](#422-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-functiondrl)
    - [4.2.3 对应测试代码（`FunctionTest#testFunction`）](#423-%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81functiontesttestfunction)
  - [4.3 `global` 全局变量（`GlobalTest`）](#43-global-%E5%85%A8%E5%B1%80%E5%8F%98%E9%87%8Fglobaltest)
    - [4.3.1 说明](#431-%E8%AF%B4%E6%98%8E)
    - [4.3.2 规则文件 `global.drl`](#432-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-globaldrl)
    - [4.3.3 对应测试代码（`GlobalTest#testGlobal`）](#433-%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81globaltesttestglobal)
  - [4.4 RHS：`drools.halt()`（`HaltTest`）](#44-rhsdroolshalthalttest)
    - [4.4.1 说明](#441-%E8%AF%B4%E6%98%8E)
    - [4.4.2 规则文件 `halt.drl`](#442-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-haltdrl)
    - [4.4.3 对应测试代码（`HaltTest#testHalt`）](#443-%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81halttesttesthalt)
  - [4.5 LHS 加强：`in` / `not in`、`not`、`exists`、规则继承（`LhsEnhanceTest`）](#45-lhs-%E5%8A%A0%E5%BC%BAin--not-innotexists%E8%A7%84%E5%88%99%E7%BB%A7%E6%89%BFlhsenhancetest)
    - [4.5.1 说明](#451-%E8%AF%B4%E6%98%8E)
    - [4.5.2 规则文件 `lhsEnhance.drl`](#452-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-lhsenhancedrl)
    - [4.5.3 对应测试代码（`LhsEnhanceTest` 完整类）](#453-%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81lhsenhancetest-%E5%AE%8C%E6%95%B4%E7%B1%BB)
  - [4.6 `query` 查询（`QueryTest`）](#46-query-%E6%9F%A5%E8%AF%A2querytest)
    - [4.6.1 说明](#461-%E8%AF%B4%E6%98%8E)
    - [4.6.2 规则文件 `query.drl`](#462-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-querydrl)
    - [4.6.3 对应测试代码（`QueryTest#testQuery`）](#463-%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81querytesttestquery)
- [5. 运行与注意事项](#5-%E8%BF%90%E8%A1%8C%E4%B8%8E%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9)
  - [5.1 运行全部测试](#51-%E8%BF%90%E8%A1%8C%E5%85%A8%E9%83%A8%E6%B5%8B%E8%AF%95)
  - [5.2 注意事项小结](#52-%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E5%B0%8F%E7%BB%93)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. Drools 高级 DRL 语法概述

本模块 `drl-04-advanced-drools-syntax` 演示教程第 6 章相关内容：在规则 **RHS** 中使用 `drools` 内置 API、`function` 自定义函数、`global` 全局变量、`halt` 终止执行；在 **LHS** 中使用 `in` / `not in`、`not`、`exists`、规则继承；以及 **`query`** 对工作内存的查询。各主题对应独立 `.drl` 与 JUnit 测试类，并通过 `AgendaFilters` 按规则名前缀过滤执行，避免多文件规则互相干扰。

### 1.1 本模块主题一览

| 主题 | 规则文件 | 测试类 |
| :--- | :--- | :--- |
| RHS：`drools.getWorkingMemory()` / `getRule()` | `droolsMethods.drl` | `DroolsMethodsTest` |
| `function` 函数 | `function.drl` | `FunctionTest` |
| `global` 全局变量 | `global.drl` | `GlobalTest` |
| RHS：`drools.halt()` | `halt.drl` | `HaltTest` |
| LHS 加强（in、not、exists、extends） | `lhsEnhance.drl` | `LhsEnhanceTest` |
| `query` 查询 | `query.drl` | `QueryTest` |

### 1.2 运行与阅读说明

- **Drools 版本**：`7.10.0.Final`（见模块 `pom.xml` 中 `drools-compiler`）。
- **运行测试**：在模块目录执行 `mvn test`，或单独运行各 `*Test` 类。
- **代码一致性**：下文代码块与仓库源文件 **逐字一致**（含注释）；路径以 `src/main`、`src/test` 为根。

---

## 2. 工程搭建

### 2.1 第一步：配置 `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.action.drools</groupId>
        <artifactId>drools</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>drl-04-advanced-drools-syntax</artifactId>
    <packaging>jar</packaging>

    <name>drl-04-advanced-drools-syntax</name>
    <url>http://maven.apache.org</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-compiler</artifactId>
            <version>7.10.0.Final</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

### 2.2 第二步：配置 `kmodule.xml`

路径：`src/main/resources/META-INF/kmodule.xml`。

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<kmodule xmlns="http://www.drools.org/xsd/kmodule">

    <kbase name="advancedKbase" packages="rules" default="true">

        <ksession name="ksession-rule" default="true"/>

    </kbase>

</kmodule>

```

---

## 3. Java 源码（实体与服务）

### 3.1 `Student.java`

路径：`src/main/java/com/action/drools/entity/Student.java`。

```java
package com.action.drools.entity;



/**

 * 学生，用于 query/function/LHS 等演示

 */

public class Student {

    private int id;

    private String name;

    private int age;



    public int getId() {

        return id;

    }



    public void setId(int id) {

        this.id = id;

    }



    public String getName() {

        return name;

    }



    public void setName(String name) {

        this.name = name;

    }



    public int getAge() {

        return age;

    }



    public void setAge(int age) {

        this.age = age;

    }



    @Override

    public String toString() {

        return "Student{name='" + name + "', age=" + age + "}";

    }

}

```

### 3.2 `UserService.java`

路径：`src/main/java/com/action/drools/service/UserService.java`，供 **global** 案例调用。

```java
package com.action.drools.service;



/**

 * 用于 global 全局变量演示

 */

public class UserService {

    public void save() {

        System.out.println("UserService.save()...");

    }

}

```

---

## 4. 分案例：规则文件与测试

各案例在 `fireAllRules` 时传入 `AgendaFilters.filterByPrefix("前缀")`，只执行名称以该前缀开头的规则。公共工具类见 **4.0**。

### 4.0 测试公共工具：`AgendaFilters.java`

路径：`src/test/java/com/action/drools/AgendaFilters.java`。

```java
package com.action.drools;



import org.kie.api.runtime.rule.AgendaFilter;

import org.kie.api.runtime.rule.Match;



/**

 * 测试用：按规则名前缀过滤 agenda，便于只执行某一组 DRL 中的规则。

 */

public final class AgendaFilters {



    private AgendaFilters() {

    }



    public static AgendaFilter filterByPrefix(final String prefix) {

        return new AgendaFilter() {

            @Override

            public boolean accept(Match match) {

                return match.getRule().getName().startsWith(prefix);

            }

        };

    }

}

```

### 4.1 RHS：`drools.getWorkingMemory()`、`drools.getRule()`（`DroolsMethodsTest`）

#### 4.1.1 说明

在规则 **then** 中可通过绑定名 **`drools`** 调用引擎 API。本例打印当前 Working Memory 与当前 Rule 对象，便于调试与理解运行时上下文。

#### 4.1.2 规则文件 `droolsMethods.drl`

路径：`src/main/resources/rules/droolsMethods.drl`

```java
package testdrools
/*
    此规则文件用于测试 RHS 中 drools.getWorkingMemory()、drools.getRule()
*/
rule "rule_getWorkingMemory"
    when
    then
        System.out.println(drools.getWorkingMemory());
end

rule "rule_getRule"
    when
    then
        System.out.println(drools.getRule());
end
```

#### 4.1.3 对应测试代码（`DroolsMethodsTest#testDroolsMethods`）

路径：`src/test/java/com/action/drools/DroolsMethodsTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code droolsMethods.drl}：{@code drools.getWorkingMemory()}、{@code drools.getRule()}。
 */
public class DroolsMethodsTest {

    @Test
    public void testDroolsMethods() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_get"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.2 `function` 自定义函数（`FunctionTest`）

#### 4.2.1 说明

在 DRL 中可用 **`function`** 定义 Java 风格方法，在 **`then`** 中直接调用，适合抽取可复用的字符串/计算逻辑（复杂逻辑仍建议放在 Java 类中）。

#### 4.2.2 规则文件 `function.drl`

路径：`src/main/resources/rules/function.drl`

```java
package testfunction
import com.action.drools.entity.Student
/*
    此规则文件用于测试function函数
*/

//定义一个函数
function String sayHello(String name){
    return "hello " + name;
}

rule "rule_function_1"
    when
        $student:Student(name != null)
    then
        //调用上面定义的函数
        String ret = sayHello($student.getName());
        System.out.println(ret);
end
```

#### 4.2.3 对应测试代码（`FunctionTest#testFunction`）

路径：`src/test/java/com/action/drools/FunctionTest.java`

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code function.drl}：function 函数。
 */
public class FunctionTest {

    @Test
    public void testFunction() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student student = new Student();
        student.setName("小明");
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_function_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.3 `global` 全局变量（`GlobalTest`）

#### 4.3.1 说明

**`global`** 在 DRL 顶部声明，由 Java 在创建 `KieSession` 后 **`setGlobal(名称, 对象)`** 注入。名称与类型须与声明一致。注意：`global` 的事实不会触发规则重算；集合等可变对象在规则间共享，包装类型在规则内的 `+=` 等行为需结合引擎语义理解（本例演示注释已写在 DRL 中）。

#### 4.3.2 规则文件 `global.drl`

路径：`src/main/resources/rules/global.drl`

```java
package testglobal
/*
    此规则文件用于测试global全局变量
*/

global java.lang.Integer count //定义一个包装类型的全局变量
global com.action.drools.service.UserService userService //定义一个JavaBean类型的全局变量
global java.util.List gList //定义一个集合类型的全局变量

rule "rule_global_1"
    when
    then
        count += 10; //全局变量计算，只对当前规则有效，其他规则不受影响
        userService.save();//调用全局变量的方法
        gList.add("itcast");//向集合类型的全局变量中添加元素，Java代码和所有规则都受影响
        gList.add("itheima");
        System.out.println("count=" + count);
        System.out.println("gList.size=" + gList.size());
end

rule "rule_global_2"
    when
    then
        userService.save();
        System.out.println("count=" + count);
        System.out.println("gList.size=" + gList.size());
end
```

#### 4.3.3 对应测试代码（`GlobalTest#testGlobal`）

路径：`src/test/java/com/action/drools/GlobalTest.java`

```java
package com.action.drools;

import com.action.drools.service.UserService;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 对应 {@code global.drl}：global 全局变量。
 */
public class GlobalTest {

    @Test
    public void testGlobal() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        // 名称和类型须与规则文件中 global 声明一致
        kieSession.setGlobal("userService", new UserService());
        kieSession.setGlobal("count", Integer.valueOf(5));
        List list = new ArrayList();
        kieSession.setGlobal("gList", list);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_global_"));
        kieSession.dispose();

        assertEquals(2, list.size());
    }
}
```

### 4.4 RHS：`drools.halt()`（`HaltTest`）

#### 4.4.1 说明

**`drools.halt()`** 在当前规则执行后立即 **终止本轮** 后续规则执行（与 `fireUntilHalt` 配合时语义不同，本例为单次 `fireAllRules`）。高 salience 规则先执行，其中调用 `halt()` 可阻止低优先级规则运行。

#### 4.4.2 规则文件 `halt.drl`

路径：`src/main/resources/rules/halt.drl`

```java
package testhalt
/*
    此规则文件用于测试 RHS 中 drools.halt()
*/
rule "rule_halt_1"
    salience 10
    when
    then
        System.out.println("规则：rule_halt_1触发");
        drools.halt();//立即终止后面所有规则执行
end

rule "rule_halt_2"
    when
    then
        System.out.println("规则：rule_halt_2触发");
end
```

#### 4.4.3 对应测试代码（`HaltTest#testHalt`）

路径：`src/test/java/com/action/drools/HaltTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code halt.drl}：RHS 中 {@code drools.halt()}。
 */
public class HaltTest {

    @Test
    public void testHalt() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_halt_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.5 LHS 加强：`in` / `not in`、`not`、`exists`、规则继承（`LhsEnhanceTest`）

#### 4.5.1 说明

- **`in` / `not in`**：字段值是否在（或不在）给定集合中。
- **`not`**：否定模式；**不存在**满足条件的 Fact 时条件为真。
- **`exists`**：只要存在匹配 Fact 即触发，**整个规则 then 通常只执行一次**；无 `exists` 时每个匹配 Fact 可各触发一次。
- **`extends`**：子规则继承父规则条件，两者 **同时** 满足时才触发子规则。

#### 4.5.2 规则文件 `lhsEnhance.drl`

路径：`src/main/resources/rules/lhsEnhance.drl`

```java
package testlhsenhance
import com.action.drools.entity.Student
/*
    此规则文件用于测试 LHS 加强：in/not in、not、exists、规则继承
*/

// 6.4.1 in/not in
rule "rule_lhs_in"
    when
        $s:Student(name in ("张三","李四","王五"))
    then
        System.out.println("规则：rule_lhs_in触发 name=" + $s.getName());
end

rule "rule_lhs_notIn"
    when
        $s:Student(name not in ("张三","李四","王五"))
    then
        System.out.println("规则：rule_lhs_notIn触发 name=" + $s.getName());
end

// 6.4.3 not：不存在某 Fact 时为 true
rule "rule_lhs_not"
    when
        not Student(age < 10)
    then
        System.out.println("规则：rule_lhs_not触发（工作内存中不存在 age<10 的 Student）");
end

// 6.4.4 exists：存在则执行一次；不用 exists 则每个匹配 Fact 执行一次
rule "rule_lhs_exists"
    when
        exists Student()
    then
        System.out.println("规则：使用exists的规则触发");
end

rule "rule_lhs_noExists"
    when
        Student()
    then
        System.out.println("规则：没有使用exists的规则触发");
end

// 6.4.5 规则继承
rule "rule_lhs_1"
    when
        Student(age > 10)
    then
        System.out.println("规则：rule_lhs_1触发");
end

rule "rule_lhs_2" extends "rule_lhs_1"
    when
        Student(age < 20)
    then
        System.out.println("规则：rule_lhs_2触发");
end
```

#### 4.5.3 对应测试代码（`LhsEnhanceTest` 完整类）

路径：`src/test/java/com/action/drools/LhsEnhanceTest.java`。内含 **`testLhsInNotIn`**、**`testLhsNot`**、**`testLhsExists`**、**`testLhsExtends`** 四个用例，与工程 **逐字一致**。

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code lhsEnhance.drl}：LHS 加强（in/not in、not、exists、规则继承）。
 */
public class LhsEnhanceTest {

    @Test
    public void testLhsInNotIn() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(15);
        Student s2 = new Student();
        s2.setName("赵六");
        s2.setAge(18);
        kieSession.insert(s1);
        kieSession.insert(s2);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_in"));
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_notIn"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testLhsNot() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_not"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testLhsExists() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.insert(new Student());
        kieSession.insert(new Student());
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_exists"));
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_noExists"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testLhsExtends() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student s = new Student();
        s.setAge(15);
        kieSession.insert(s);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.6 `query` 查询（`QueryTest`）

#### 4.6.1 说明

**`query`** 定义在 DRL 中，通过 `KieSession.getQueryResults(查询名, 可选参数...)` 在 **不触发规则** 的情况下检索 Working Memory。无参查询 `query_1` 匹配 `age > 10` 的 `Student`；有参查询 `query_2` 额外约束 `name` 与传入字符串相等。

#### 4.6.2 规则文件 `query.drl`

路径：`src/main/resources/rules/query.drl`

```java
package testquery
import com.action.drools.entity.Student
/*
    此规则文件用于测试query查询
*/

//不带参数的查询
//当前query用于查询Working Memory中age>10的Student对象
query "query_1"
    $student:Student(age > 10)
end

//带有参数的查询
//当前query用于查询Working Memory中age>20同时name需要和传递的参数name相同的Student对象
query "query_2"(String sname)
    $student:Student(age > 20 && name == sname)
end
```

#### 4.6.3 对应测试代码（`QueryTest#testQuery`）

路径：`src/test/java/com/action/drools/QueryTest.java`

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import static org.junit.Assert.assertEquals;

/**
 * 对应 {@code query.drl}：query 查询。
 */
public class QueryTest {

    @Test
    public void testQuery() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(12);

        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(8);

        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(22);

        kieSession.insert(student1);
        kieSession.insert(student2);
        kieSession.insert(student3);

        QueryResults results1 = kieSession.getQueryResults("query_1");
        int size = results1.size();
        assertEquals(2, size);
        for (QueryResultsRow row : results1) {
            Student student = (Student) row.get("$student");
            System.out.println(student);
        }

        QueryResults results2 = kieSession.getQueryResults("query_2", "王五");
        size = results2.size();
        assertEquals(1, size);
        for (QueryResultsRow row : results2) {
            Student student = (Student) row.get("$student");
            System.out.println(student);
        }
        kieSession.dispose();
    }
}
```

---

## 5. 运行与注意事项

### 5.1 运行全部测试

在模块根目录执行：

```text
mvn test
```

### 5.2 注意事项小结

1. **`AgendaFilters`**：前缀过滤时，`rule_lhs_` 会同时匹配 `rule_lhs_1`、`rule_lhs_2` 等，与 `rule_lhs_in` 等不同；各测试已选用合适前缀。
2. **`global`**：`setGlobal` 必须在 `fireAllRules` 前完成，且名称、类型与 DRL 中 `global` 声明一致。
3. **`query`**：结果行中取变量名需与 query 内绑定一致（本例为 `"$student"`）。
4. 各 DRL 的 `package` 名与资源目录 `rules` 由 `kmodule` 的 `packages="rules"` 加载；与 Java 包名无强制一致要求。


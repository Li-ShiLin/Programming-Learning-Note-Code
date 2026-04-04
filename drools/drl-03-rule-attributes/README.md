<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. 规则属性（attributes）概述](#1-%E8%A7%84%E5%88%99%E5%B1%9E%E6%80%A7attributes%E6%A6%82%E8%BF%B0)
  - [1.1 本模块涉及的规则属性一览](#11-%E6%9C%AC%E6%A8%A1%E5%9D%97%E6%B6%89%E5%8F%8A%E7%9A%84%E8%A7%84%E5%88%99%E5%B1%9E%E6%80%A7%E4%B8%80%E8%A7%88)
  - [1.2 运行与阅读说明](#12-%E8%BF%90%E8%A1%8C%E4%B8%8E%E9%98%85%E8%AF%BB%E8%AF%B4%E6%98%8E)
- [2. 工程搭建](#2-%E5%B7%A5%E7%A8%8B%E6%90%AD%E5%BB%BA)
  - [2.1 第一步：配置 `pom.xml`](#21-%E7%AC%AC%E4%B8%80%E6%AD%A5%E9%85%8D%E7%BD%AE-pomxml)
  - [2.2 第二步：配置 `kmodule.xml`](#22-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E9%85%8D%E7%BD%AE-kmodulexml)
- [3. 实体类与测试工具](#3-%E5%AE%9E%E4%BD%93%E7%B1%BB%E4%B8%8E%E6%B5%8B%E8%AF%95%E5%B7%A5%E5%85%B7)
  - [3.1 实体类 `Student`](#31-%E5%AE%9E%E4%BD%93%E7%B1%BB-student)
  - [3.2 实体类 `ComparisonOperatorEntity`](#32-%E5%AE%9E%E4%BD%93%E7%B1%BB-comparisonoperatorentity)
  - [3.3 测试工具类 `AgendaFilters`](#33-%E6%B5%8B%E8%AF%95%E5%B7%A5%E5%85%B7%E7%B1%BB-agendafilters)
- [4. 案例：enabled 属性](#4-%E6%A1%88%E4%BE%8Benabled-%E5%B1%9E%E6%80%A7)
  - [4.1 规则介绍](#41-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [4.2 第一步：规则文件 `enabledAttribute.drl`](#42-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-enabledattributedrl)
  - [4.3 第二步：单元测试 `EnabledAttributeTest`](#43-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-enabledattributetest)
  - [4.4 第三步：运行与现象](#44-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [5. 案例：salience 属性](#5-%E6%A1%88%E4%BE%8Bsalience-%E5%B1%9E%E6%80%A7)
  - [5.1 规则介绍](#51-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [5.2 第一步：规则文件 `salience.drl`](#52-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-saliencedrl)
  - [5.3 第二步：单元测试 `SalienceTest`](#53-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-saliencetest)
  - [5.4 第三步：运行与现象](#54-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [6. 案例：no-loop 属性](#6-%E6%A1%88%E4%BE%8Bno-loop-%E5%B1%9E%E6%80%A7)
  - [6.1 规则介绍](#61-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [6.2 第一步：规则文件 `noloop.drl`](#62-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-noloopdrl)
  - [6.3 第二步：单元测试 `NoLoopTest`](#63-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-nolooptest)
  - [6.4 第三步：运行与现象](#64-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [7. 案例：agenda-group 与 auto-focus](#7-%E6%A1%88%E4%BE%8Bagenda-group-%E4%B8%8E-auto-focus)
  - [7.1 规则介绍](#71-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [7.2 第一步：规则文件 `agendagroup.drl`](#72-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-agendagroupdrl)
  - [7.3 第二步：单元测试 `AgendaGroupTest`](#73-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-agendagrouptest)
  - [7.4 第三步：运行与现象](#74-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [8. 案例：activation-group 属性](#8-%E6%A1%88%E4%BE%8Bactivation-group-%E5%B1%9E%E6%80%A7)
  - [8.1 规则介绍](#81-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [8.2 第一步：规则文件 `activationgroup.drl`](#82-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-activationgroupdrl)
  - [8.3 第二步：单元测试 `ActivationGroupTest`](#83-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-activationgrouptest)
  - [8.4 第三步：运行与现象](#84-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [9. 案例：date-effective 属性](#9-%E6%A1%88%E4%BE%8Bdate-effective-%E5%B1%9E%E6%80%A7)
  - [9.1 规则介绍](#91-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [9.2 第一步：规则文件 `dateeffective.drl`](#92-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-dateeffectivedrl)
  - [9.3 第二步：单元测试 `DateEffectiveTest`](#93-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-dateeffectivetest)
  - [9.4 第三步：运行与现象](#94-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [10. 案例：date-expires 属性](#10-%E6%A1%88%E4%BE%8Bdate-expires-%E5%B1%9E%E6%80%A7)
  - [10.1 规则介绍](#101-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [10.2 第一步：规则文件 `dateexpires.drl`](#102-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-dateexpiresdrl)
  - [10.3 第二步：单元测试 `DateExpiresTest`](#103-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-dateexpirestest)
  - [10.4 第三步：运行与现象](#104-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [11. 案例：timer 属性](#11-%E6%A1%88%E4%BE%8Btimer-%E5%B1%9E%E6%80%A7)
  - [11.1 规则介绍](#111-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [11.2 第一步：规则文件 `timer.drl`](#112-%E7%AC%AC%E4%B8%80%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-timerdrl)
  - [11.3 第二步：单元测试 `TimerTest`](#113-%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-timertest)
  - [11.4 第三步：运行与现象](#114-%E7%AC%AC%E4%B8%89%E6%AD%A5%E8%BF%90%E8%A1%8C%E4%B8%8E%E7%8E%B0%E8%B1%A1)
- [12. 运行全部测试与注意事项](#12-%E8%BF%90%E8%A1%8C%E5%85%A8%E9%83%A8%E6%B5%8B%E8%AF%95%E4%B8%8E%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9)
  - [12.1 运行全部测试](#121-%E8%BF%90%E8%A1%8C%E5%85%A8%E9%83%A8%E6%B5%8B%E8%AF%95)
  - [12.2 注意事项小结](#122-%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E5%B0%8F%E7%BB%93)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. 规则属性（attributes）概述

Drools 规则除 `when` / `then` 外，还可配置 **规则属性**，用于控制是否参与匹配、执行优先级、互斥分组、议程分组、定时触发、生效与失效时间等。本模块 `drl-03-rule-attributes` 通过多份独立 `.drl` 与对应的 JUnit 测试类演示各属性行为。

### 1.1 本模块涉及的规则属性一览

| 属性 | 作用概要 |
| :--- | :--------- |
| `enabled` | 为 `false` 时规则被禁用，不参与匹配与执行 |
| `salience` | 整数优先级，数值越大越先执行 |
| `no-loop` | 为 `true` 时，防止规则因修改事实而反复触发自身导致死循环 |
| `activation-group` | 同组规则互斥，同一时刻最多触发其中一个 |
| `agenda-group` | 将规则分到议程组；需 `setFocus` 或 `auto-focus` 才可能执行 |
| `auto-focus` | 规则激活时自动将其所在 `agenda-group` 设为焦点 |
| `timer` | 延迟、周期或 cron 方式调度规则触发 |
| `date-effective` | 仅当系统时间 **不早于** 指定日期时规则才可能触发 |
| `date-expires` | 仅当系统时间 **早于** 指定日期时规则才可能触发 |

### 1.2 运行与阅读说明

- **Drools 版本**：与本模块 `pom.xml` 一致，为 `7.10.0.Final`（经 `drools-compiler` 传递依赖引入核心组件）。
- **运行测试**：在模块目录执行 `mvn test`，或在 IDE 中运行 `src/test/java/com/action/drools/` 下各 `*Test.java` 中的 `@Test` 方法。
- **代码引用**：下文代码块与仓库中对应文件 **逐字符一致**（含注释、空行与仅含空格的行）；路径以模块 `src/main` / `src/test` 为根。

---

## 2. 工程搭建

### 2.1 第一步：配置 `pom.xml`

模块父工程为 `com.action.drools:drools:1.0-SNAPSHOT`，坐标与依赖如下。

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.action.drools</groupId>
        <artifactId>drools</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>drl-03-rule-attributes</artifactId>
    <packaging>jar</packaging>

    <name>drl-03-rule-attributes</name>
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

路径：`src/main/resources/META-INF/kmodule.xml`。声明 kbase `attributesKbase` 扫描 `rules` 包下资源，默认 ksession 名为 `ksession-rule`。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<kmodule xmlns="http://www.drools.org/xsd/kmodule">
    <kbase name="attributesKbase" packages="rules" default="true">
        <ksession name="ksession-rule" default="true"/>
    </kbase>
</kmodule>
```

---

## 3. 实体类与测试工具

### 3.1 实体类 `Student`

路径：`src/main/java/com/action/drools/entity/Student.java`，用于 **no-loop** 等示例。

```java
package com.action.drools.entity;

/**
 * 学生，用于测试 no-loop 等属性
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
}


```

### 3.2 实体类 `ComparisonOperatorEntity`

路径：`src/main/java/com/action/drools/entity/ComparisonOperatorEntity.java`，用于 **enabled** 等示例。

```java
package com.action.drools.entity;

import java.util.List;

/**
 * 实体类，用于测试比较操作符及 enabled 属性
 */
public class ComparisonOperatorEntity {
    private String names;
    private List<String> list;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}


```

### 3.3 测试工具类 `AgendaFilters`

路径：`src/test/java/com/action/drools/AgendaFilters.java`。按规则名前缀构造 `AgendaFilter`，便于 `fireAllRules` / `fireUntilHalt` 时只执行某一组 `.drl` 中的规则，避免不同规则文件互相干扰。

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

---

## 4. 案例：enabled 属性

### 4.1 规则介绍

`enabled false` 时，该规则被禁用：无论 LHS 是否匹配都不会执行 RHS。本例 LHS 本可匹配（`names not memberOf list`），但因禁用而无控制台输出。

### 4.2 第一步：规则文件 `enabledAttribute.drl`

路径：`src/main/resources/rules/enabledAttribute.drl`

```drl
package testenabled
import com.action.drools.entity.ComparisonOperatorEntity
/*
 此规则文件用于测试 enabled 属性
*/
rule "rule_enabled_notMemberOf"
    //指定当前规则不可用，当前规则无论是否匹配成功都不会执行
    enabled false
    when
        ComparisonOperatorEntity(names not memberOf list)
    then
        System.out.println("规则rule_enabled_notMemberOf触发");
end

```

### 4.3 第二步：单元测试 `EnabledAttributeTest`

路径：`src/test/java/com/action/drools/EnabledAttributeTest.java`

```java
package com.action.drools;

import com.action.drools.entity.ComparisonOperatorEntity;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
 
/**
 * 对应 {@code enabledAttribute.drl}：enabled 属性。
 */
public class EnabledAttributeTest {

    @Test
    public void testEnabled() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("王五");
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        entity.setList(list);
        kieSession.insert(entity);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_enabled_"));
        kieSession.dispose();
        // enabled false，规则不触发，无输出
        assertTrue(true);
    }
}


```

### 4.4 第三步：运行与现象

在模块根目录执行 `mvn test -Dtest=EnabledAttributeTest`，或在 IDE 中运行 `testEnabled`。预期：规则不打印，用例通过。

---

## 5. 案例：salience 属性

### 5.1 规则介绍

`salience` 为整数，**越大越先执行**。本例三条规则均 `eval(true)`，执行顺序应为：`rule_salience_2`(10) → `rule_salience_1`(9) → `rule_salience_3`(8)。

### 5.2 第一步：规则文件 `salience.drl`

路径：`src/main/resources/rules/salience.drl`

```drl
package testsalience
/*
 此规则文件用于测试 salience 属性：数值越大越优先执行
*/
rule "rule_salience_1"
    salience 9
    when
        eval(true)
    then
        System.out.println("规则rule_salience_1触发");
end

rule "rule_salience_2"
    salience 10
    when
        eval(true)
    then
        System.out.println("规则rule_salience_2触发");
end

rule "rule_salience_3"
    salience 8
    when
        eval(true)
    then
        System.out.println("规则rule_salience_3触发");
end

```

### 5.3 第二步：单元测试 `SalienceTest`

路径：`src/test/java/com/action/drools/SalienceTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code salience.drl}：salience 优先级。
 */
public class SalienceTest {

    @Test
    public void testSalience() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_salience_"));
        kieSession.dispose();
        // 预期顺序：rule_salience_2(10) -> rule_salience_1(9) -> rule_salience_3(8)
        assertTrue(true);
    }
}


```

### 5.4 第三步：运行与现象

执行 `mvn test -Dtest=SalienceTest`。控制台应依次打印 2 → 1 → 3 对应的三行输出。

---

## 6. 案例：no-loop 属性

### 6.1 规则介绍

`then` 中对事实 `update` 会导致规则可能被再次激活。`no-loop true` 可防止 **同一条规则** 因自身引起的更新而无限重复触发，从而避免死循环。

### 6.2 第一步：规则文件 `noloop.drl`

路径：`src/main/resources/rules/noloop.drl`

```drl
package testnoloop
import com.action.drools.entity.Student
/*
 此规则文件用于测试 no-loop 属性
*/
rule "rule_noloop"
    no-loop true
    when
        $student:Student(age == 25)
    then
        update($student);//注意此处执行update会导致当前规则重新被激活，no-loop true 防止死循环
        System.out.println("规则rule_noloop触发");
end

```

### 6.3 第二步：单元测试 `NoLoopTest`

路径：`src/test/java/com/action/drools/NoLoopTest.java`

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code noloop.drl}：no-loop 属性。
 */
public class NoLoopTest {

    @Test
    public void testNoLoop() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student student = new Student();
        student.setAge(25);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_noloop"));
        kieSession.dispose();
        // no-loop true，只触发一次，不死循环
        assertTrue(true);
    }
}


```

### 6.4 第三步：运行与现象

执行 `mvn test -Dtest=NoLoopTest`。预期：`规则rule_noloop触发` 仅出现一次。

---

## 7. 案例：agenda-group 与 auto-focus

### 7.1 规则介绍

- **agenda-group**：规则被分到不同议程组；只有 **获得焦点** 的组内规则才会在 `fireAllRules` 时被考虑执行。
- **auto-focus**：为 `true` 时，规则激活会自动将其所在 `agenda-group` 设为焦点，从而在不手动 `setFocus` 时也能执行该组规则。

### 7.2 第一步：规则文件 `agendagroup.drl`

路径：`src/main/resources/rules/agendagroup.drl`

```drl
package testagendagroup
/*
 此规则文件用于测试 agenda-group 与 auto-focus 属性
*/
rule "rule_agendagroup_1"
    agenda-group "myagendagroup_1"
    when
    then
        System.out.println("规则rule_agendagroup_1触发");
end

rule "rule_agendagroup_2"
    agenda-group "myagendagroup_1"
    when
    then
        System.out.println("规则rule_agendagroup_2触发");
end
//========================================================
rule "rule_agendagroup_3"
    agenda-group "myagendagroup_2"
    auto-focus true //自动获取焦点
    when
    then
        System.out.println("规则rule_agendagroup_3触发");
end

rule "rule_agendagroup_4"
    agenda-group "myagendagroup_2"
    auto-focus true //自动获取焦点
    when
    then
        System.out.println("规则rule_agendagroup_4触发");
end

```

### 7.3 第二步：单元测试 `AgendaGroupTest`

路径：`src/test/java/com/action/drools/AgendaGroupTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code agendagroup.drl}：agenda-group 与 auto-focus 属性。
 */
public class AgendaGroupTest {

    @Test
    public void testAgendaGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        // 设置焦点，对应 agenda-group 分组中的规则才可能被触发
        kieSession.getAgenda().getAgendaGroup("myagendagroup_1").setFocus();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_agendagroup_"));
        kieSession.dispose();
        // 只有 myagendagroup_1 中规则触发
        assertTrue(true);
    }

    @Test
    public void testAutoFocus() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_agendagroup_"));
        kieSession.dispose();
        // myagendagroup_2 设置了 auto-focus true，其规则会触发
        assertTrue(true);
    }
}


```

### 7.4 第三步：运行与现象

- `testAgendaGroup`：仅 `myagendagroup_1` 下两条规则打印。
- `testAutoFocus`：未手动 `setFocus` 时，依赖 `auto-focus`，`myagendagroup_2` 下规则会触发。

---

## 8. 案例：activation-group 属性

### 8.1 规则介绍

同一 `activation-group` 内的规则 **互斥**：议程中即便多条可激活，**同一轮执行中只会触发其中一条**（具体哪条由引擎策略决定）。

### 8.2 第一步：规则文件 `activationgroup.drl`

路径：`src/main/resources/rules/activationgroup.drl`

```drl
package testactivationgroup
/*
 此规则文件用于测试 activation-group 属性：同组只能有一个规则被触发
*/
rule "rule_activationgroup_1"
    activation-group "mygroup"
    when
    then
        System.out.println("规则rule_activationgroup_1触发");
end

rule "rule_activationgroup_2"
    activation-group "mygroup"
    when
    then
        System.out.println("规则rule_activationgroup_2触发");
end

```

### 8.3 第二步：单元测试 `ActivationGroupTest`

路径：`src/test/java/com/action/drools/ActivationGroupTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code activationgroup.drl}：activation-group 属性。
 */
public class ActivationGroupTest {

    @Test
    public void testActivationGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_activationgroup_"));
        kieSession.dispose();
        // 同组只能触发一个
        assertTrue(true);
    }
}


```

### 8.4 第三步：运行与现象

执行 `mvn test -Dtest=ActivationGroupTest`。控制台只应出现一条 `rule_activationgroup_*` 的打印。

---

## 9. 案例：date-effective 属性

### 9.1 规则介绍

仅当 **当前系统时间 ≥ `date-effective` 指定日期** 时，规则才可能被触发。本例为 `"01-Oct-2020"`；在 2026 年运行测试时，该规则应能触发。

### 9.2 第一步：规则文件 `dateeffective.drl`

路径：`src/main/resources/rules/dateeffective.drl`

```drl
package testdateeffective
/*
 此规则文件用于测试 date-effective 属性：只有当前系统时间大于等于设置的时间规则才有可能触发
*/
rule "rule_dateeffective_1"
    date-effective "01-Oct-2020"
    when
    then
        System.out.println("规则rule_dateeffective_1触发");
end

```

### 9.3 第二步：单元测试 `DateEffectiveTest`

路径：`src/test/java/com/action/drools/DateEffectiveTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code dateeffective.drl}：date-effective 属性。
 */
public class DateEffectiveTest {

    @Test
    public void testDateEffective() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_dateeffective_"));
        kieSession.dispose();
        assertTrue(true);
    }
}


```

### 9.4 第三步：运行与现象

执行 `mvn test -Dtest=DateEffectiveTest`。若当前日期早于 2020-10-01，规则不会触发；日期解析与时区以 JVM / Drools 配置为准。

---

## 10. 案例：date-expires 属性

### 10.1 规则介绍

仅当 **当前系统时间 < `date-expires` 指定日期** 时，规则才可能被触发。本例为 `"01-Oct-2030"`；在 2026 年运行测试时，规则仍有效；2030-10-01 当日及之后行为以 Drools 解析为准。

### 10.2 第一步：规则文件 `dateexpires.drl`

路径：`src/main/resources/rules/dateexpires.drl`

```drl
package testdateexpires
/*
 此规则文件用于测试 date-expires 属性：只有当前系统时间小于设置的时间规则才有可能触发
*/
rule "rule_dateexpires_1"
    date-expires "01-Oct-2030"
    when
    then
        System.out.println("规则rule_dateexpires_1触发");
end

```

### 10.3 第二步：单元测试 `DateExpiresTest`

路径：`src/test/java/com/action/drools/DateExpiresTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code dateexpires.drl}：date-expires 属性。
 */
public class DateExpiresTest {

    @Test
    public void testDateExpires() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_dateexpires_"));
        kieSession.dispose();
        assertTrue(true);
    }
}


```

### 10.4 第三步：运行与现象

执行 `mvn test -Dtest=DateExpiresTest`。

---

## 11. 案例：timer 属性

### 11.1 规则介绍

`timer` 支持 **延迟 + 周期**（如 `5s 2s`：5 秒后首次触发，之后每 2 秒一次）或 **cron** 表达式。定时规则常在单独线程中通过 `fireUntilHalt` 持续运行，结束时需 `halt()` 停止引擎。

### 11.2 第一步：规则文件 `timer.drl`

路径：`src/main/resources/rules/timer.drl`

```drl
package testtimer
import java.text.SimpleDateFormat
import java.util.Date
/*
 此规则文件用于测试 timer 属性
*/
rule "rule_timer_1"
    timer (5s 2s) //含义：5秒后触发，然后每隔2秒触发一次
    when
    then
        System.out.println("规则rule_timer_1触发，触发时间为：" +
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
end

rule "rule_timer_2"
    timer (cron:0/1 * * * * ?) //含义：每隔1秒触发一次
    when
    then
        System.out.println("规则rule_timer_2触发，触发时间为：" +
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
end

```

### 11.3 第二步：单元测试 `TimerTest`

路径：`src/test/java/com/action/drools/TimerTest.java`

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code timer.drl}：timer 属性。
 */
public class TimerTest {

    @Test
    public void testTimer() throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        final KieSession kieSession = kieContainer.newKieSession();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 启动规则引擎进行规则匹配，直到调用 halt 才结束（仅执行 timer 规则）
                kieSession.fireUntilHalt(AgendaFilters.filterByPrefix("rule_timer_"));
            }
        }).start();

        Thread.sleep(10000);
        kieSession.halt();
        kieSession.dispose();
        assertTrue(true);
    }
}


```

### 11.4 第三步：运行与现象

执行 `mvn test -Dtest=TimerTest`。用例约休眠 10 秒；控制台可见 cron 规则约每秒打印，`rule_timer_1` 在延迟后周期性打印。CI 若对单测时长敏感，可单独跳过或缩短休眠（需理解输出变化）。

---

## 12. 运行全部测试与注意事项

### 12.1 运行全部测试

在模块根目录执行：

```text
mvn test
```

### 12.2 注意事项小结

1. **`TimerTest` 耗时约 10 秒**，且依赖后台线程与 `halt()`。
2. **`date-effective` / `date-expires`** 与 JVM 默认时区、Drools 日期解析格式相关；若系统日期超出示例区间，行为可能变化，属预期语义。
3. 各 `.drl` 的 `package` 名（如 `testenabled`、`testsalience`）与 **物理路径** 由 `kmodule` 的 `packages="rules"` 加载；与 Java `package` 无强制同名要求，但需保证资源在 classpath 的 `rules` 目录下。

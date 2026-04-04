<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. Drools 基础语法模块概述](#1-drools-%E5%9F%BA%E7%A1%80%E8%AF%AD%E6%B3%95%E6%A8%A1%E5%9D%97%E6%A6%82%E8%BF%B0)
  - [1.1 规则文件与测试类对照](#11-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6%E4%B8%8E%E6%B5%8B%E8%AF%95%E7%B1%BB%E5%AF%B9%E7%85%A7)
  - [1.2 运行与阅读说明](#12-%E8%BF%90%E8%A1%8C%E4%B8%8E%E9%98%85%E8%AF%BB%E8%AF%B4%E6%98%8E)
- [2. 工程搭建](#2-%E5%B7%A5%E7%A8%8B%E6%90%AD%E5%BB%BA)
  - [2.1 配置 `pom.xml`](#21-%E9%85%8D%E7%BD%AE-pomxml)
  - [2.2 配置 `kmodule.xml`](#22-%E9%85%8D%E7%BD%AE-kmodulexml)
- [3. 实体类（Java）](#3-%E5%AE%9E%E4%BD%93%E7%B1%BBjava)
  - [3.1 `Student.java`](#31-studentjava)
  - [3.2 `Order.java`](#32-orderjava)
  - [3.3 `Customer.java`](#33-customerjava)
  - [3.4 `ComparisonOperatorEntity.java`](#34-comparisonoperatorentityjava)
- [4. 测试公共工具与各案例](#4-%E6%B5%8B%E8%AF%95%E5%85%AC%E5%85%B1%E5%B7%A5%E5%85%B7%E4%B8%8E%E5%90%84%E6%A1%88%E4%BE%8B)
  - [4.0 `AgendaFilters.java`](#40-agendafiltersjava)
  - [4.1 注释](#41-%E6%B3%A8%E9%87%8A)
    - [4.1.1 说明](#411-%E8%AF%B4%E6%98%8E)
    - [4.1.2 规则文件 `comment.drl`](#412-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-commentdrl)
    - [4.1.3 测试类 `CommentTest.java`](#413-%E6%B5%8B%E8%AF%95%E7%B1%BB-commenttestjava)
  - [4.2 Pattern 模式匹配](#42-pattern-%E6%A8%A1%E5%BC%8F%E5%8C%B9%E9%85%8D)
    - [4.2.1 说明](#421-%E8%AF%B4%E6%98%8E)
    - [4.2.2 规则文件 `patternMatch.drl`](#422-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-patternmatchdrl)
    - [4.2.3 测试类 `PatternMatchTest.java`](#423-%E6%B5%8B%E8%AF%95%E7%B1%BB-patternmatchtestjava)
  - [4.3 比较操作符与执行指定规则](#43-%E6%AF%94%E8%BE%83%E6%93%8D%E4%BD%9C%E7%AC%A6%E4%B8%8E%E6%89%A7%E8%A1%8C%E6%8C%87%E5%AE%9A%E8%A7%84%E5%88%99)
    - [4.3.1 说明](#431-%E8%AF%B4%E6%98%8E)
    - [4.3.2 规则文件 `comparisonOperator.drl`](#432-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-comparisonoperatordrl)
    - [4.3.3 测试类 `ComparisonOperatorTest.java`](#433-%E6%B5%8B%E8%AF%95%E7%B1%BB-comparisonoperatortestjava)
  - [4.4 内置方法 `update`](#44-%E5%86%85%E7%BD%AE%E6%96%B9%E6%B3%95-update)
    - [4.4.1 规则文件 `studentUpdate.drl`](#441-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-studentupdatedrl)
    - [4.4.2 测试类 `StudentUpdateTest.java`](#442-%E6%B5%8B%E8%AF%95%E7%B1%BB-studentupdatetestjava)
  - [4.5 内置方法 `insert`](#45-%E5%86%85%E7%BD%AE%E6%96%B9%E6%B3%95-insert)
    - [4.5.1 规则文件 `studentInsert.drl`](#451-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-studentinsertdrl)
    - [4.5.2 测试类 `StudentInsertTest.java`](#452-%E6%B5%8B%E8%AF%95%E7%B1%BB-studentinserttestjava)
  - [4.6 内置方法 `retract`](#46-%E5%86%85%E7%BD%AE%E6%96%B9%E6%B3%95-retract)
    - [4.6.1 规则文件 `studentRetract.drl`](#461-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-studentretractdrl)
    - [4.6.2 测试类 `StudentRetractTest.java`](#462-%E6%B5%8B%E8%AF%95%E7%B1%BB-studentretracttestjava)
- [5. 运行与注意事项](#5-%E8%BF%90%E8%A1%8C%E4%B8%8E%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9)
  - [5.1 运行全部测试](#51-%E8%BF%90%E8%A1%8C%E5%85%A8%E9%83%A8%E6%B5%8B%E8%AF%95)
  - [5.2 注意事项小结](#52-%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E5%B0%8F%E7%BB%93)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. Drools 基础语法模块概述

模块 `drl-02-drools-syntax` 演示 **DRL 基础语法**：规则 **注释**、**Pattern** 模式匹配、**比较操作符**、**按规则名过滤执行**、RHS 内置方法 **`update` / `insert` / `retract`**。规则位于 `src/main/resources/rules/`，测试按案例拆分为多个 JUnit 类，公共过滤逻辑集中在 **`AgendaFilters`**。

### 1.1 规则文件与测试类对照

| 案例 | 规则文件 | 测试类 |
| :--- | :--- | :--- |
| 注释 | `comment.drl` | `CommentTest` |
| Pattern 模式匹配 | `patternMatch.drl` | `PatternMatchTest` |
| 比较操作符与指定规则 | `comparisonOperator.drl` | `ComparisonOperatorTest` |
| `update` | `studentUpdate.drl` | `StudentUpdateTest` |
| `insert` | `studentInsert.drl` | `StudentInsertTest` |
| `retract` | `studentRetract.drl` | `StudentRetractTest` |

### 1.2 运行与阅读说明

- **Drools**：`7.10.0.Final`（`drools-compiler`）。
- **测试**：模块根目录执行 `mvn test`。
- **代码**：下文与仓库源文件 **逐字一致**（含注释）。

---

## 2. 工程搭建

### 2.1 配置 `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.action.drools</groupId>
        <artifactId>drools</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>drl-02-drools-syntax</artifactId>
    <packaging>jar</packaging>

    <name>drl-02-drools-syntax</name>
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

### 2.2 配置 `kmodule.xml`

路径：`src/main/resources/META-INF/kmodule.xml`。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<kmodule xmlns="http://www.drools.org/xsd/kmodule">
    <!--
        name:指定kbase的名称，可以任意，但是需要唯一
        packages:指定规则文件的目录，需要根据实际情况填写，否则无法加载到规则文件
        default:指定当前kbase是否为默认
    -->
    <kbase name="syntaxKbase" packages="rules" default="true">
        <!--
            name:指定ksession名称，可以任意，但是需要唯一
            default:指定当前session是否为默认
        -->
        <ksession name="ksession-rule" default="true"/>
    </kbase>
</kmodule>
```

---

## 3. 实体类（Java）

### 3.1 `Student.java`

路径：`src/main/java/com/action/drools/entity/Student.java`。

```java
package com.action.drools.entity;

/**
 * 学生
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

### 3.2 `Order.java`

路径：`src/main/java/com/action/drools/entity/Order.java`。

```java
package com.action.drools.entity;

/**
 * 订单
 */
public class Order {
    private Double originalPrice;//订单原始价格，即优惠前价格
    private Double realPrice;//订单真实价格，即优惠后价格

    @Override
    public String toString() {
        return "Order{" +
                "originalPrice=" + originalPrice +
                ", realPrice=" + realPrice +
                '}';
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }
}
```

### 3.3 `Customer.java`

路径：`src/main/java/com/action/drools/entity/Customer.java`。

```java
package com.action.drools.entity;

/**
 * 客户（用于 Pattern 多条件演示）
 */
public class Customer {
    private int age;
    private String gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
```

### 3.4 `ComparisonOperatorEntity.java`

路径：`src/main/java/com/action/drools/entity/ComparisonOperatorEntity.java`。

```java
package com.action.drools.entity;



import java.util.List;



/**

 * 实体类

 * 用于测试比较操作符

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

---

## 4. 测试公共工具与各案例

### 4.0 `AgendaFilters.java`

路径：`src/test/java/com/action/drools/AgendaFilters.java`。提供 **`filterByPrefix`**、**`filterByRuleName`**，供 Pattern / Student 等测试统一过滤议程。

```java
package com.action.drools;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

/**
 * 测试用：按规则名过滤 agenda。
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

    public static AgendaFilter filterByRuleName(final String ruleName) {
        return new AgendaFilter() {
            @Override
            public boolean accept(Match match) {
                return ruleName.equals(match.getRule().getName());
            }
        };
    }
}
```

### 4.1 注释

#### 4.1.1 说明

`CommentTest` 使用 **`fireAllRules()` 无过滤器** 且 **未 `insert` 事实** 时，通常只有 **`when` 为空** 的 `rule1`、`rule2` 会触发；若在会话中插入可被其它 DRL 匹配的对象，则 **其它规则也可能执行**。

#### 4.1.2 规则文件 `comment.drl`

路径：`src/main/resources/rules/comment.drl`

```java
//规则文件注释示例：单行注释与多行注释
package comment

//规则rule1的注释，这是一个单行注释
rule "rule1"
    when
    then
        System.out.println("rule1触发");
end

/*
规则rule2的注释，
这是一个多行注释
*/
rule "rule2"
    when
    then
        System.out.println("rule2触发");
end
```

#### 4.1.3 测试类 `CommentTest.java`

路径：`src/test/java/com/action/drools/CommentTest.java`。

```java
package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code comment.drl}：规则文件注释示例。
 */
public class CommentTest {

    @Test
    public void testComment() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.2 Pattern 模式匹配

#### 4.2.1 说明

- **`testPatternMatch`**：`AgendaFilters.filterByPrefix("rule_pattern_")` 执行 `rule_pattern_basic` 与 `rule_pattern_binding`，两条规则均对原价 150 的订单减 20，**实付 130**（与断言一致）。
- **`testPatternMatchMulti`**：`filterByRuleName("rule_pattern_multi")` 仅执行多 pattern 规则。

#### 4.2.2 规则文件 `patternMatch.drl`

路径：`src/main/resources/rules/patternMatch.drl`

```java
//Pattern模式匹配演示：类型约束、属性约束、绑定变量、多 pattern 与 and
package patternMatch
import com.action.drools.entity.Order
import com.action.drools.entity.Customer

//演示：类型约束 + 属性约束，绑定对象变量 $order
rule "rule_pattern_basic"
    when
        //Order为类型约束，originalPrice为属性约束
        $order:Order(originalPrice < 200 && originalPrice >= 100)
    then
        $order.setRealPrice($order.getOriginalPrice() - 20);
        System.out.println("规则rule_pattern_basic：所购图书总价在100到200元的优惠20元");
end

//演示：绑定变量用在对象属性上，RHS 中可使用 $op
rule "rule_pattern_binding"
    when
        $order:Order($op:originalPrice < 200 && originalPrice >= 100)
    then
        System.out.println("$op=" + $op);
        $order.setRealPrice($order.getOriginalPrice() - 20);
        System.out.println("规则rule_pattern_binding：所购图书总价在100到200元的优惠20元");
end

//演示：多个 pattern 使用 and 连接
rule "rule_pattern_multi"
    when
        $order:Order($op:originalPrice < 200 && originalPrice >= 100) and
        $customer:Customer(age > 20 && gender=='male')
    then
        System.out.println("$op=" + $op + ", 客户年龄=" + $customer.getAge());
        $order.setRealPrice($order.getOriginalPrice() - 20);
        System.out.println("规则rule_pattern_multi：订单100-200元且客户男>20岁，优惠20元");
end
```

#### 4.2.3 测试类 `PatternMatchTest.java`

路径：`src/test/java/com/action/drools/PatternMatchTest.java`。

```java
package com.action.drools;

import com.action.drools.entity.Customer;
import com.action.drools.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code patternMatch.drl}：Pattern 模式匹配。
 */
public class PatternMatchTest {

    @Test
    public void testPatternMatch() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Order order = new Order();
        order.setOriginalPrice(150D);
        kieSession.insert(order);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_pattern_"));
        kieSession.dispose();
        assertTrue(order.getRealPrice() != null && order.getRealPrice() == 130D);
    }

    @Test
    public void testPatternMatchMulti() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Order order = new Order();
        order.setOriginalPrice(150D);
        Customer customer = new Customer();
        customer.setAge(25);
        customer.setGender("male");
        kieSession.insert(order);
        kieSession.insert(customer);

        kieSession.fireAllRules(AgendaFilters.filterByRuleName("rule_pattern_multi"));
        kieSession.dispose();
        assertTrue(order.getRealPrice() != null && order.getRealPrice() == 130D);
    }
}
```

### 4.3 比较操作符与执行指定规则

#### 4.3.1 说明

- **`testComparisonOperator`**：`fireAllRules()` 无过滤，会执行 kbase 内所有可匹配规则。
- **`testComparisonOperatorFilter`**：`AgendaFilters.filterByRuleName("rule_comparison_memberOf")`。
- **`testComparisonOperatorFilterWithRuleNameEquals`**：`RuleNameEqualsAgendaFilter` 等价写法。

#### 4.3.2 规则文件 `comparisonOperator.drl`

路径：`src/main/resources/rules/comparisonOperator.drl`

```java
package comparisonOperator
import com.action.drools.entity.ComparisonOperatorEntity
/*
 当前规则文件用于测试Drools提供的比较操作符
*/

//测试比较操作符contains
rule "rule_comparison_contains"
    when
        ComparisonOperatorEntity(names contains "张三")
        ComparisonOperatorEntity(list contains names)
    then
        System.out.println("规则rule_comparison_contains触发");
end

//测试比较操作符not contains
rule "rule_comparison_notContains"
    when
        ComparisonOperatorEntity(names not contains "张三")
        ComparisonOperatorEntity(list not contains names)
    then
        System.out.println("规则rule_comparison_notContains触发");
end

//测试比较操作符memberOf
rule "rule_comparison_memberOf"
    when
        ComparisonOperatorEntity(names memberOf list)
    then
        System.out.println("规则rule_comparison_memberOf触发");
end

//测试比较操作符not memberOf
rule "rule_comparison_notMemberOf"
    when
        ComparisonOperatorEntity(names not memberOf list)
    then
        System.out.println("规则rule_comparison_notMemberOf触发");
end

//测试比较操作符matches
rule "rule_comparison_matches"
    when
        ComparisonOperatorEntity(names matches "张.*")
    then
        System.out.println("规则rule_comparison_matches触发");
end

//测试比较操作符not matches
rule "rule_comparison_notMatches"
    when
        ComparisonOperatorEntity(names not matches "张.*")
    then
        System.out.println("规则rule_comparison_notMatches触发");
end
```

#### 4.3.3 测试类 `ComparisonOperatorTest.java`

路径：`src/test/java/com/action/drools/ComparisonOperatorTest.java`。

```java
package com.action.drools;

import com.action.drools.entity.ComparisonOperatorEntity;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code comparisonOperator.drl}：比较操作符与执行指定规则。
 */
public class ComparisonOperatorTest {

    private static ComparisonOperatorEntity sampleEntity() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("张三");
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        entity.setList(list);
        return entity;
    }

    @Test
    public void testComparisonOperator() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.insert(sampleEntity());
        kieSession.fireAllRules();
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testComparisonOperatorFilter() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.insert(sampleEntity());
        kieSession.fireAllRules(AgendaFilters.filterByRuleName("rule_comparison_memberOf"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testComparisonOperatorFilterWithRuleNameEquals() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.insert(sampleEntity());
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_memberOf"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.4 内置方法 `update`

#### 4.4.1 规则文件 `studentUpdate.drl`

路径：`src/main/resources/rules/studentUpdate.drl`

```java
package studentUpdate
import com.action.drools.entity.Student

/*
 当前规则文件用于测试Drools提供的内置方法 update
 update方法用于更新Fact对象，会导致相关规则重新匹配
*/

rule "rule_student_update_age小于10岁"
    when
        $s:Student(age < 10)
    then
        $s.setAge(15);
        update($s);//更新数据，导致相关的规则会重新匹配
        System.out.println("规则rule_student_update_age小于10岁触发");
end

rule "rule_student_update_age小于20岁同时大于10岁"
    when
        $s:Student(age < 20 && age > 10)
    then
        $s.setAge(25);
        update($s);//更新数据，导致相关的规则会重新匹配
        System.out.println("规则rule_student_update_age小于20岁同时大于10岁触发");
end

rule "rule_student_update_age大于20岁"
    when
        $s:Student(age > 20)
    then
        System.out.println("规则rule_student_update_age大于20岁触发");
end
```

#### 4.4.2 测试类 `StudentUpdateTest.java`

路径：`src/test/java/com/action/drools/StudentUpdateTest.java`。

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code studentUpdate.drl}：内置方法 {@code update}。
 */
public class StudentUpdateTest {

    @Test
    public void testStudentUpdate() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Student student = new Student();
        student.setAge(5);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_student_update_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.5 内置方法 `insert`

#### 4.5.1 规则文件 `studentInsert.drl`

路径：`src/main/resources/rules/studentInsert.drl`

```java
package studentInsert
import com.action.drools.entity.Student

/*
 当前规则文件用于测试Drools提供的内置方法 insert
*/

rule "rule_student_insert_age等于10岁"
    when
        $s:Student(age == 10)
    then
        Student student = new Student();
        student.setAge(5);
        insert(student);//插入数据，导致相关的规则会重新匹配
        System.out.println("规则rule_student_insert_age等于10岁触发");
end

rule "rule_student_insert_age小于10岁"
    when
        $s:Student(age < 10)
    then
        $s.setAge(15);
        update($s);
        System.out.println("规则rule_student_insert_age小于10岁触发");
end

rule "rule_student_insert_age小于20岁同时大于10岁"
    when
        $s:Student(age < 20 && age > 10)
    then
        $s.setAge(25);
        update($s);
        System.out.println("规则rule_student_insert_age小于20岁同时大于10岁触发");
end

rule "rule_student_insert_age大于20岁"
    when
        $s:Student(age > 20)
    then
        System.out.println("规则rule_student_insert_age大于20岁触发");
end
```

#### 4.5.2 测试类 `StudentInsertTest.java`

路径：`src/test/java/com/action/drools/StudentInsertTest.java`。

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code studentInsert.drl}：内置方法 {@code insert}。
 */
public class StudentInsertTest {

    @Test
    public void testStudentInsert() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Student student = new Student();
        student.setAge(10);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_student_insert_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

### 4.6 内置方法 `retract`

#### 4.6.1 规则文件 `studentRetract.drl`

路径：`src/main/resources/rules/studentRetract.drl`

```java
package studentRetract
import com.action.drools.entity.Student

/*
 当前规则文件用于测试Drools提供的内置方法 retract
*/

rule "rule_student_retract_age等于10岁时删除数据"
    /*
    salience：设置当前规则的执行优先级，数值越大越优先执行，默认值为0.
    因为当前规则的匹配条件和下面规则的匹配条件相同，为了保证先执行当前规则，需要设置优先级
    */
    salience 100
    when
        $s:Student(age == 10)
    then
        retract($s);//retract方法的作用是删除工作内存中的数据，并让相关的规则重新匹配。
        System.out.println("规则rule_student_retract_age等于10岁时删除数据触发");
end

rule "rule_student_retract_age等于10岁"
    when
        $s:Student(age == 10)
    then
        Student student = new Student();
        student.setAge(5);
        insert(student);
        System.out.println("规则rule_student_retract_age等于10岁触发");
end

rule "rule_student_retract_age小于10岁"
    when
        $s:Student(age < 10)
    then
        $s.setAge(15);
        update($s);
        System.out.println("规则rule_student_retract_age小于10岁触发");
end

rule "rule_student_retract_age小于20岁同时大于10岁"
    when
        $s:Student(age < 20 && age > 10)
    then
        $s.setAge(25);
        update($s);
        System.out.println("规则rule_student_retract_age小于20岁同时大于10岁触发");
end

rule "rule_student_retract_age大于20岁"
    when
        $s:Student(age > 20)
    then
        System.out.println("规则rule_student_retract_age大于20岁触发");
end
```

#### 4.6.2 测试类 `StudentRetractTest.java`

路径：`src/test/java/com/action/drools/StudentRetractTest.java`。

```java
package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code studentRetract.drl}：内置方法 {@code retract}。
 */
public class StudentRetractTest {

    @Test
    public void testStudentRetract() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Student student = new Student();
        student.setAge(10);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_student_retract_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
```

---

## 5. 运行与注意事项

### 5.1 运行全部测试

```text
mvn test
```

### 5.2 注意事项小结

1. **`ComparisonOperatorTest#testComparisonOperator`**：`fireAllRules()` 无过滤且插入了可匹配事实，**多条比较操作符规则**会一起触发；**`CommentTest`** 在无事实时一般仅触发 `rule1` / `rule2`。
2. **`AgendaFilters.filterByRuleName`** 与 **`RuleNameEqualsAgendaFilter`**：均可实现只执行单条规则，按项目习惯二选一或对照学习。
3. **`PatternMatchTest#testPatternMatch`**：`rule_pattern_basic` 与 `rule_pattern_binding` 均匹配同一订单时，各执行一次 **`originalPrice - 20`**，**实付仍为 130**（与断言一致）。


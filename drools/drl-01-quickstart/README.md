<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [3. 开发实现](#3-%E5%BC%80%E5%8F%91%E5%AE%9E%E7%8E%B0)
  - [3.1 Maven 依赖](#31-maven-%E4%BE%9D%E8%B5%96)
  - [3.2 kmodule.xml 配置](#32-kmodulexml-%E9%85%8D%E7%BD%AE)
  - [3.3 实体类 Order](#33-%E5%AE%9E%E4%BD%93%E7%B1%BB-order)
  - [3.4 规则文件 bookDiscount.drl](#34-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-bookdiscountdrl)
  - [3.5 主类 BookDiscountApp](#35-%E4%B8%BB%E7%B1%BB-bookdiscountapp)
  - [3.6 单元测试 BookDiscountAppTest](#36-%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95-bookdiscountapptest)
- [4. 运行与验证](#4-%E8%BF%90%E8%A1%8C%E4%B8%8E%E9%AA%8C%E8%AF%81)
  - [4.1 运行主类](#41-%E8%BF%90%E8%A1%8C%E4%B8%BB%E7%B1%BB)
  - [4.2 运行单元测试](#42-%E8%BF%90%E8%A1%8C%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95)
  - [4.3 仅运行某一规则对应测试](#43-%E4%BB%85%E8%BF%90%E8%A1%8C%E6%9F%90%E4%B8%80%E8%A7%84%E5%88%99%E5%AF%B9%E5%BA%94%E6%B5%8B%E8%AF%95)
- [5. 小结](#5-%E5%B0%8F%E7%BB%93)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

本模块为 Drools 规则引擎入门案例，通过图书商城订单优惠场景演示 Drools 的基本使用方式与代码结构。

## 3. 开发实现

### 3.1 Maven 依赖

在模块 `pom.xml` 中引入 Drools 与 JUnit 依赖（代码与工程一致）：

```xml
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
```

- **drools-compiler**：规则编译与运行所需。
- **junit**：单元测试，用于验证四条规则覆盖。

### 3.2 kmodule.xml 配置

根据 Drools 要求，在 `src/main/resources/META-INF/kmodule.xml` 中配置知识库与会话（名称与位置固定，不可更改）。

**路径**：`src/main/resources/META-INF/kmodule.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<kmodule xmlns="http://www.drools.org/xsd/kmodule">
    <!--
        name:指定kbase的名称，可以任意，但是需要唯一
        packages:指定规则文件的目录，需要根据实际情况填写，否则无法加载到规则文件
        default:指定当前kbase是否为默认
    -->
    <kbase name="myKbase1" packages="rules" default="true">
        <!--
            name:指定ksession名称，可以任意，但是需要唯一
            default:指定当前session是否为默认
        -->
        <ksession name="ksession-rule" default="true"/>
    </kbase>
</kmodule>
```

**说明**：

- **kbase**：`packages="rules"` 表示从 classpath 下 `rules` 目录加载规则文件（对应 `resources/rules/`）。
- **ksession**：应用通过默认的 `ksession-rule` 获取 KieSession，与规则引擎交互。

### 3.3 实体类 Order

订单实体用于承载「原始价格」与「优惠后价格」，作为 Fact 插入工作内存供规则匹配与修改。

**路径**：`src/main/java/com/action/drools/entity/Order.java`

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

- **originalPrice**：订单原始价格（优惠前），由业务设置。
- **realPrice**：订单真实价格（优惠后），由规则引擎在匹配到的规则中通过 `setRealPrice` 写入。

### 3.4 规则文件 bookDiscount.drl

规则文件定义四条图书优惠规则，与「2.2 优惠规则表」一一对应。规则由 `when` 条件与 `then` 动作组成：条件匹配时执行动作，设置订单的 `realPrice`。

**路径**：`src/main/resources/rules/bookDiscount.drl`

```java
//图书优惠规则
package book.discount
import com.action.drools.entity.Order

//规则一：所购图书总价在100元以下的没有优惠
rule "book_discount_1"
    when
        $order:Order(originalPrice < 100)
    then
        $order.setRealPrice($order.getOriginalPrice());
        System.out.println("成功匹配到规则一：所购图书总价在100元以下的没有优惠");
end

//规则二：所购图书总价在100到200元的优惠20元
rule "book_discount_2"
    when
        $order:Order(originalPrice < 200 && originalPrice >= 100)
    then
        $order.setRealPrice($order.getOriginalPrice() - 20);
        System.out.println("成功匹配到规则二：所购图书总价在100到200元的优惠20元");
end

//规则三：所购图书总价在200到300元的优惠50元
rule "book_discount_3"
    when
        $order:Order(originalPrice <= 300 && originalPrice >= 200)
    then
        $order.setRealPrice($order.getOriginalPrice() - 50);
        System.out.println("成功匹配到规则三：所购图书总价在200到300元的优惠50元");
end

//规则四：所购图书总价在300元以上的优惠100元
rule "book_discount_4"
    when
        $order:Order(originalPrice >= 300)
    then
        $order.setRealPrice($order.getOriginalPrice() - 100);
        System.out.println("成功匹配到规则四：所购图书总价在300元以上的优惠100元");
end
```

**说明**：

- **package**：规则包名，与 Java 包名无必然关系。
- **import**：引入订单实体，与项目包名 `com.action.drools.entity.Order` 一致。
- 每条规则绑定一个 `Order` 实例（`$order`），在 `then` 中修改其 `realPrice` 并可选打印日志。每个订单只会匹配一条规则（区间互斥），因此只需一次 `fireAllRules()` 即可得到最终实付价。

### 3.5 主类 BookDiscountApp

主类演示从获取 KieSession、插入订单、触发规则到输出结果的全流程，便于直接运行查看效果。

**路径**：`src/main/java/com/action/drools/BookDiscountApp.java`

```java
package com.action.drools;

import com.action.drools.entity.Order;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools 入门案例：图书优惠规则演示。
 * 根据订单原始价格，通过规则引擎计算优惠后价格。
 */
public class BookDiscountApp {

    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession kieSession = kieContainer.newKieSession();
        //Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(210D);

        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
    }
}
```

**流程简述**：

1. **KieServices.Factory.get()**：获取 KIE 服务入口。
2. **getKieClasspathContainer()**：从 classpath 加载 kmodule 与规则（即 `rules` 下的 `bookDiscount.drl`）。
3. **newKieSession()**：创建默认会话，用于与规则引擎交互。
4. **insert(order)**：将订单放入工作内存（Fact）。
5. **fireAllRules()**：触发全部规则，匹配到的规则会设置 `order.realPrice`。
6. **dispose()**：关闭会话，释放资源。

示例中 210 元会匹配规则三，优惠后价格为 160 元。

### 3.6 单元测试 BookDiscountAppTest

测试类对四条规则分别编写用例，保证每条规则至少被覆盖一次，且优惠后价格与预期一致。

**路径**：`src/test/java/com/action/drools/BookDiscountAppTest.java`

```java
package com.action.drools;

import com.action.drools.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 图书优惠规则单元测试，覆盖全部四条规则。
 */
public class BookDiscountAppTest {

    private Order runRules(Double originalPrice) {
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession kieSession = kieContainer.newKieSession();
        //Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(originalPrice);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        return order;
    }

    /**
     * 规则一：所购图书总价在100元以下的没有优惠
     */
    @Test
    public void testRule1() {
        Order order = runRules(80D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(80D), order.getRealPrice());
    }

    /**
     * 规则二：所购图书总价在100到200元的优惠20元
     */
    @Test
    public void testRule2() {
        Order order = runRules(150D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(130D), order.getRealPrice());
    }

    /**
     * 规则三：所购图书总价在200到300元的优惠50元
     */
    @Test
    public void testRule3() {
        Order order = runRules(210D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(160D), order.getRealPrice());
    }

    /**
     * 规则四：所购图书总价在300元以上的优惠100元
     */
    @Test
    public void testRule4() {
        Order order = runRules(350D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(250D), order.getRealPrice());
    }
}
```

**测试与规则对应关系**：

| 测试方法   | 对应规则     | 原始价格 | 预期实付 |
|------------|--------------|----------|----------|
| testRule1  | 规则一 &lt;100 | 80       | 80       |
| testRule2  | 规则二 100~200 | 150      | 130      |
| testRule3  | 规则三 200~300 | 210      | 160      |
| testRule4  | 规则四 ≥300   | 350      | 250      |

通过 `runRules(originalPrice)` 复用「创建会话 → 插入订单 → 执行规则 → 关闭会话」的逻辑，避免重复代码，并保证 README 中的代码与工程实现一致（含注释）。

---

## 4. 运行与验证

### 4.1 运行主类

在项目根目录执行（指定主类所在模块）：

```bash
mvn exec:java -pl drl-01-quickstart -Dexec.mainClass="com.action.drools.BookDiscountApp"
```

或在 IDE 中直接运行 `BookDiscountApp` 的 `main` 方法。

**预期输出**：控制台打印规则匹配日志及一行结果，例如：

```
成功匹配到规则三：所购图书总价在200到300元的优惠50元
优惠前原始价格：210.0，优惠后价格：160.0
```

### 4.2 运行单元测试

在项目根目录执行：

```bash
mvn test -pl drl-01-quickstart -Dtest=BookDiscountAppTest
```

**预期结果**：4 个测试全部通过（testRule1～testRule4），即四条规则均被覆盖且优惠后价格与上表一致。

### 4.3 仅运行某一规则对应测试

例如只验证规则三：

```bash
mvn test -pl drl-01-quickstart -Dtest=BookDiscountAppTest#testRule3
```

---

## 5. 小结

本案例要点：

1. **规则与代码分离**：优惠逻辑写在 `bookDiscount.drl` 中，业务代码只负责准备订单、调用 API 与使用结果。
2. **固定配置**：`kmodule.xml` 必须位于 `resources/META-INF/kmodule.xml`，且 `packages` 指向规则所在资源目录（本模块为 `rules`）。
3. **Fact 与工作内存**：将 `Order` 通过 `kieSession.insert(order)` 插入工作内存后，即成为 Fact；规则在 `when` 中匹配 Fact，在 `then` 中修改其属性。
4. **一次匹配**：本案例四条规则按价格区间互斥，每个订单只会命中一条规则，因此一次 `fireAllRules()` 即可得到最终实付价。

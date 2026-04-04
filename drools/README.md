本仓库汇总 Drools 规则引擎相关教程文档与可运行示例代码。

---

## 概念与教程

| 文档                         | 核心内容                                                  |
|----------------------------|-------------------------------------------------------|
| [tutorial.md](tutorial.md) | 规则引擎与 Drools 概念、业务场景、基础/高级语法、Spring / Spring Boot 整合等 |

---

## 示例模块

| 文档                                                         | 核心内容                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [drl-01-quickstart/README.md](drl-01-quickstart/README.md)   | 入门：订单优惠场景，`kmodule.xml`、DRL 编写与 KieSession 调用 |
| [drl-02-drools-syntax/README.md](drl-02-drools-syntax/README.md) | 基础语法：注释、Pattern、比较操作符、指定规则执行、update/insert/retract |
| [drl-03-rule-attributes/README.md](drl-03-rule-attributes/README.md) | 规则属性：salience、no-loop、activation-group、agenda-group、timer、日期生效等 |
| [drl-04-advanced-drools-syntax/README.md](drl-04-advanced-drools-syntax/README.md) | 高级语法：global、query、function、LHS/RHS 加强及规则继承等  |
| [drl-05-spring-integration-drools/README.md](drl-05-spring-integration-drools/README.md) | Spring 整合：kie-spring 配置、单元测试与 Spring MVC + WAR Web 示例 |
| [drl-06-springboot-integration-drools/README.md](drl-06-springboot-integration-drools/README.md) | Spring Boot 整合：`DroolsConfig`、`KieFileSystem`/`KieContainer`、REST 触发规则 |
| [drl-07-drools-workbench/README.md](drl-07-drools-workbench/README.md) | JDK / Tomcat 8 准备、Drools WorkBench 安装部署、访问与基本使用 |
| [drl-08-personal-income-tax-calculator/README.md](drl-08-personal-income-tax-calculator/README.md) | 实战1：个人所得税计算，分档税率、`activation-group` 与 REST/页面试算 |
| [drl-09-credit-card-approval/README.md](drl-09-credit-card-approval/README.md) | 实战2：信用卡申请审批，合法性校验、`drools.halt()` 与授信额度规则 |
| [drl-10-insurance-product-access-rules-based-on-decision-tables/README.md](drl-10-insurance-product-access-rules-based-on-decision-tables/README.md) | 决策表（Excel/xls）语法与编译、保险产品准入规则、Spring Boot 集成 |

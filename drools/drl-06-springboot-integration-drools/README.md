<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. Spring Boot 整合 Drools 模块概述](#1-spring-boot-%E6%95%B4%E5%90%88-drools-%E6%A8%A1%E5%9D%97%E6%A6%82%E8%BF%B0)
  - [1.1 技术栈](#11-%E6%8A%80%E6%9C%AF%E6%A0%88)
  - [1.2 运行说明](#12-%E8%BF%90%E8%A1%8C%E8%AF%B4%E6%98%8E)
- [2. 工程搭建](#2-%E5%B7%A5%E7%A8%8B%E6%90%AD%E5%BB%BA)
  - [2.1 配置 `pom.xml`](#21-%E9%85%8D%E7%BD%AE-pomxml)
  - [2.2 配置 `application.yml`](#22-%E9%85%8D%E7%BD%AE-applicationyml)
- [3. Drools 编程式配置（`DroolsConfig`）](#3-drools-%E7%BC%96%E7%A8%8B%E5%BC%8F%E9%85%8D%E7%BD%AEdroolsconfig)
  - [3.1 说明](#31-%E8%AF%B4%E6%98%8E)
    - [3.1.1 职责概要](#311-%E8%81%8C%E8%B4%A3%E6%A6%82%E8%A6%81)
    - [3.1.2 与 XML `kmodule` 的关系](#312-%E4%B8%8E-xml-kmodule-%E7%9A%84%E5%85%B3%E7%B3%BB)
  - [3.2 `DroolsConfig.java`](#32-droolsconfigjava)
- [4. 规则与业务代码](#4-%E8%A7%84%E5%88%99%E4%B8%8E%E4%B8%9A%E5%8A%A1%E4%BB%A3%E7%A0%81)
  - [4.1 规则文件 `helloworld.drl`](#41-%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-helloworlddrl)
  - [4.2 `RuleService.java`](#42-ruleservicejava)
  - [4.3 `HelloController.java`](#43-hellocontrollerjava)
  - [4.4 启动类 `DroolsApplication.java`](#44-%E5%90%AF%E5%8A%A8%E7%B1%BB-droolsapplicationjava)
- [5. 测试与运行](#5-%E6%B5%8B%E8%AF%95%E4%B8%8E%E8%BF%90%E8%A1%8C)
  - [5.1 `DroolsApplicationTests.java`](#51-droolsapplicationtestsjava)
  - [5.2 命令行](#52-%E5%91%BD%E4%BB%A4%E8%A1%8C)
  - [5.3 验证规则触发](#53-%E9%AA%8C%E8%AF%81%E8%A7%84%E5%88%99%E8%A7%A6%E5%8F%91)
  - [5.4 注意事项小结](#54-%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E5%B0%8F%E7%BB%93)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. Spring Boot 整合 Drools 模块概述

模块 `drl-06-springboot-integration-drools` 对应教程 **7.3**：在 **Spring Boot 2.7.x** 中通过 **Java 配置类** 将 `classpath:rules/` 下的规则文件写入 **KieFileSystem**，构建 **KieContainer** / **KieBase**，再由 **Service** 注入 `KieBase` 创建 **KieSession** 执行规则；对外通过 **REST Controller** 暴露 HTTP 接口。与 `drl-05-spring-integration-drools` 使用 XML `kie:kmodule` 的方式不同，本模块以 **`DroolsConfig`** 完成等价装配。

### 1.1 技术栈

| 项 | 版本 / 说明 |
| :--- | :--- |
| Spring Boot | `2.7.18`（父 POM） |
| JDK | `1.8` |
| Drools / KIE | `7.10.0.Final`（`drools-core`、`drools-compiler`、`kie-api`、`kie-spring` 等） |
| 规则位置 | `src/main/resources/rules/`（配置类中常量 `rules/`） |

### 1.2 运行说明

- **启动应用**：模块根目录执行 `mvn spring-boot:run`，或使用 IDE 运行 `DroolsApplication`。
- **单元测试**：`mvn test`（默认加载 `DroolsApplicationTests`）。
- **HTTP 演示**：启动后访问 `http://localhost:8080/hello/rule`（端口以 `application.yml` 为准）。
- **代码引用**：下文代码块与仓库源文件 **逐字一致**（含注释）。

---

## 2. 工程搭建

### 2.1 配置 `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.18</version>
        <relativePath/>
    </parent>
    <groupId>com.action.drools</groupId>
    <artifactId>drl-06-springboot-integration-drools</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>drl-06-springboot-integration-drools</name>
    <description>Spring Boot整合Drools（教程 7.3）</description>
    <properties>
        <java.version>1.8</java.version>
        <drools.version>7.10.0.Final</drools.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
        <!--drools规则引擎-->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-core</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-compiler</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-templates</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <dependency>
            <groupId>org.kie</groupId>
            <artifactId>kie-api</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <dependency>
            <groupId>org.kie</groupId>
            <artifactId>kie-spring</artifactId>
            <version>${drools.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-tx</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-beans</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-core</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
    <build>
        <finalName>${project.artifactId}</finalName>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.*</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2.2 配置 `application.yml`

路径：`src/main/resources/application.yml`。

```yaml
server:
  port: 8080
spring:
  application:
    name: drools_springboot
```

---

## 3. Drools 编程式配置（`DroolsConfig`）

### 3.1 说明

#### 3.1.1 职责概要

- **`kieFileSystem()`**：用 `PathMatchingResourcePatternResolver` 扫描 **`classpath*:rules/*.*`**，将每个文件写入 **KieFileSystem**（路径形如 `rules/xxx.drl`）。
- **`kieContainer()`**：基于当前 **KieFileSystem** 执行 **`KieBuilder.buildAll()`**，得到 **KieContainer**。
- **`kieBase()`**：从容器取出默认 **KieBase**（供 Spring 注入）。
- **`kiePostProcessor()`**：注册 **`KModuleBeanFactoryPostProcessor`**，与 **kie-spring** 集成保持一致。

#### 3.1.2 与 XML `kmodule` 的关系

本模块 **未使用** `META-INF/kmodule.xml`，规则加载与 **KieBase** 构建均在 **`DroolsConfig`** 中完成；新增规则时只需将 `.drl` 放入 **`src/main/resources/rules/`** 并重新编译运行。

### 3.2 `DroolsConfig.java`

路径：`src/main/java/com/action/drools/config/DroolsConfig.java`。

```java
package com.action.drools.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * 规则引擎配置类
 */
@Configuration
public class DroolsConfig {
    //指定规则文件存放的目录
    private static final String RULES_PATH = "rules/";
    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    @ConditionalOnMissingBean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        ResourcePatternResolver resourcePatternResolver =
                new PathMatchingResourcePatternResolver();
        Resource[] files =
                resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "*.*");
        String path = null;
        for (Resource file : files) {
            path = RULES_PATH + file.getFilename();
            kieFileSystem.write(ResourceFactory.newClassPathResource(path, "UTF-8"));
        }
        return kieFileSystem;
    }

    @Bean
    @ConditionalOnMissingBean
    public KieContainer kieContainer() throws IOException {
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    @ConditionalOnMissingBean
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

    @Bean
    @ConditionalOnMissingBean
    public static KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }
}

```

---

## 4. 规则与业务代码

### 4.1 规则文件 `helloworld.drl`

路径：`src/main/resources/rules/helloworld.drl`。

```java
package helloworld
rule "rule_helloworld"
    when
        eval(true)
    then
        System.out.println("规则：rule_helloworld触发...");
end
```

### 4.2 `RuleService.java`

路径：`src/main/java/com/action/drools/service/RuleService.java`。通过 **`@Autowired`** 注入 **`KieBase`**，与 XML 中 **`@KBase`** 注入方式不同，效果等价。

```java
package com.action.drools.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    @Autowired
    private KieBase kieBase;

    public void rule() {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
```

### 4.3 `HelloController.java`

路径：`src/main/java/com/action/drools/controller/HelloController.java`。

```java
package com.action.drools.controller;

import com.action.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/rule")
    public String rule() {
        ruleService.rule();
        return "OK";
    }
}
```

### 4.4 启动类 `DroolsApplication.java`

路径：`src/main/java/com/action/drools/DroolsApplication.java`。

```java
package com.action.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsApplication.class, args);
    }
}
```

---

## 5. 测试与运行

### 5.1 `DroolsApplicationTests.java`

路径：`src/test/java/com/action/drools/DroolsApplicationTests.java`。默认仅校验 **Spring 上下文** 能加载（含 **Drools** 相关 Bean）。

```java
package com.action.drools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsApplicationTests {

    @Test
    void contextLoads() {
    }
}
```

### 5.2 命令行

```text
mvn test
```

```text
mvn spring-boot:run
```

### 5.3 验证规则触发

1. 执行 **5.2** 启动应用。
2. 浏览器或 `curl` 访问：`http://localhost:8080/hello/rule`。
3. 控制台应输出 **`规则：rule_helloworld触发...`**，响应体为 **`OK`**。

### 5.4 注意事项小结

1. **`kie-spring` 排除传递 Spring**：与 Boot 管理的 Spring 版本对齐，避免 **bean 定义冲突**；请勿随意删除 `pom.xml` 中的 **`exclusions`**。
2. **`kieContainer()` 与 `kieFileSystem()`**：配置类中 **`kieContainer()`** 调用 **`kieFileSystem()`** 会再执行一遍文件扫描与写入；当前工程规则量少，可接受；规则很多时可考虑合并为单 Bean 构建链。
3. **规则文件编码**：`ResourceFactory.newClassPathResource(path, "UTF-8")` 与 DRL 中文内容需保存为 **UTF-8**。


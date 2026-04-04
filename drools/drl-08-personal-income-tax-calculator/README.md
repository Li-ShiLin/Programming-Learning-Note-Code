<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. 模块说明](#1-%E6%A8%A1%E5%9D%97%E8%AF%B4%E6%98%8E)
- [2. 规则说明](#2-%E8%A7%84%E5%88%99%E8%AF%B4%E6%98%8E)
- [3. 实现步骤](#3-%E5%AE%9E%E7%8E%B0%E6%AD%A5%E9%AA%A4)
  - [第一步：配置 `pom.xml`](#%E7%AC%AC%E4%B8%80%E6%AD%A5%E9%85%8D%E7%BD%AE-pomxml)
  - [第二步：创建 `src/main/resources/application.yml`](#%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%88%9B%E5%BB%BA-srcmainresourcesapplicationyml)
  - [第三步：实体类 `Calculation`](#%E7%AC%AC%E4%B8%89%E6%AD%A5%E5%AE%9E%E4%BD%93%E7%B1%BB-calculation)
  - [第四步：规则文件 `src/main/resources/rules/calculation.drl`](#%E7%AC%AC%E5%9B%9B%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-srcmainresourcesrulescalculationdrl)
  - [第五步：`DroolsConfig`](#%E7%AC%AC%E4%BA%94%E6%AD%A5droolsconfig)
  - [第六步：`RuleService`](#%E7%AC%AC%E5%85%AD%E6%AD%A5ruleservice)
  - [第七步：`RuleController`](#%E7%AC%AC%E4%B8%83%E6%AD%A5rulecontroller)
  - [第八步：启动类 `DroolsApplication`](#%E7%AC%AC%E5%85%AB%E6%AD%A5%E5%90%AF%E5%8A%A8%E7%B1%BB-droolsapplication)
  - [第九步：前端页面 `src/main/resources/static/calculation.html`](#%E7%AC%AC%E4%B9%9D%E6%AD%A5%E5%89%8D%E7%AB%AF%E9%A1%B5%E9%9D%A2-srcmainresourcesstaticcalculationhtml)
- [4. 运行与测试](#4-%E8%BF%90%E8%A1%8C%E4%B8%8E%E6%B5%8B%E8%AF%95)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. 模块说明

本模块为 **个人所得税计算器**：在 Spring Boot 中集成 Drools，用 DRL 描述个税计算流程（应纳税所得额、分档税率与速算扣除、税后工资），并通过简单页面调用 REST 接口完成试算。规则采用 `salience` 控制执行顺序，税率分档使用 `activation-group` 保证同组仅激活一条。

技术栈：Spring Boot 2.7.18、Java 8、Drools 7.10.0.Final（`drools-core` / `drools-compiler` / `kie-api` / `kie-spring` 等，与仓库 `pom.xml` 一致）。

## 2. 规则说明

**名词解释**：

税前月收入：即税前工资，指交纳个人所得税之前的总工资

应纳税所得额：指按照税法规定确定纳税人在一定期间所获得的所有应税收入减除在该纳税期间依法允许减除的各种支出后的余额

税率：是对征税对象的征收比例或征收额度

速算扣除数：指为解决超额累进税率分级计算税额的复杂技术问题，而预先计算出的一个数据，可以简化计算过程

扣税额：是指实际缴纳的税额

税后工资：是指扣完税后实际到手的工资收入

**计算规则**：

要实现个人所得税计算器，需要了解如下计算规则：（2011）

| 规则编号 | 名称                                     | 描述                                                         |
| :------- | :--------------------------------------- | :----------------------------------------------------------- |
| 1        | 计算应纳税所得额                         | 应纳税所得额为税前工资减去3500                               |
| 2        | 设置税率，应纳税所得额<=1500             | 税率为0.03，速算扣除数为0                                    |
| 3        | 设置税率，应纳税所得额在1500至4500之间   | 税率为0.1，速算扣除数为105                                   |
| 4        | 设置税率，应纳税所得额在4500志9000之间   | 税率为0.2，速算扣除数为555                                   |
| 5        | 设置税率，应纳税所得额在9000志35000之间  | 税率为0.25，速算扣除数为1005                                 |
| 6        | 设置税率，应纳税所得额在35000至55000之间 | 税率为0.3，速算扣除数为2755                                  |
| 7        | 设置税率，应纳税所得额在55000至80000之间 | 税率为0.35，速算扣除数为5505                                 |
| 8        | 设置税率，应纳税所得额在80000以上        | 税率为0.45，速算扣除数为13505                                |
| 9        | 计算税后工资                             | 扣税额=应纳税所得额*税率-速算扣除数 税后工资=税前工资-扣税额 |



税前工资：10000

应缴纳所得税：10000-3500 = 6500

税率：0.2

速算扣除数：555

扣税额：6500 * 0.2 - 555 = 745

税后工资：10000 - 745 = 9255

- 起征点按规则中 `wage - 3500` 计算应纳税所得额（对应历史口径示例，见 `calculation.drl` 中 `date-effective`）。
- 多档税率互斥，通过 `activation-group "SETCess_Group"` 选中一条后，再由低优先级规则计算扣税额与税后工资。

规则文件路径：`src/main/resources/rules/calculation.drl`。

## 3. 实现步骤

### 第一步：配置 `pom.xml`

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
    <artifactId>drl-08-personal-income-tax-calculator</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>drl-08-personal-income-tax-calculator</name>
    <description>个人所得税计算器（教程 9.1）</description>
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

### 第二步：创建 `src/main/resources/application.yml`

```yml
server:
  port: 8080
spring:
  application:
    name: calculation
```

### 第三步：实体类 `Calculation`

```java
package com.action.drools.entity;

public class Calculation {
    private double wage;//税前工资
    private double wagemore;//应纳税所得额
    private double cess;//税率
    private double preminus;//速算扣除数
    private double wageminus;//扣税额
    private double actualwage;//税后工资

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getActualwage() {
        return actualwage;
    }

    public void setActualwage(double actualwage) {
        this.actualwage = actualwage;
    }

    public double getWagemore() {
        return wagemore;
    }

    public void setWagemore(double wagemore) {
        this.wagemore = wagemore;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }

    public double getPreminus() {
        return preminus;
    }

    public void setPreminus(double preminus) {
        this.preminus = preminus;
    }

    public double getWageminus() {
        return wageminus;
    }

    public void setWageminus(double wageminus) {
        this.wageminus = wageminus;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "wage=" + wage +
                ", actualwage=" + actualwage +
                ", wagemore=" + wagemore +
                ", cess=" + cess +
                ", preminus=" + preminus +
                ", wageminus=" + wageminus +
                '}';
    }
}
```

### 第四步：规则文件 `src/main/resources/rules/calculation.drl`

```java
package calculation
import com.action.drools.entity.Calculation

rule "个人所得税：计算应纳税所得额"
    enabled true
    salience 3
    no-loop true
    date-effective "2011-09-01" //生效日期
    when
        $cal : Calculation(wage>0)
    then
        $cal.setWagemore($cal.getWage()-3500);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额<=1500"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore <= 1500)
    then
        $cal.setCess(0.03);
        $cal.setPreminus(0);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额在1500至4500之间"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore > 1500 && wagemore <= 4500)
    then
        $cal.setCess(0.1);
        $cal.setPreminus(105);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额在4500志9000之间"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore > 4500 && wagemore <= 9000)
    then
        $cal.setCess(0.2);
        $cal.setPreminus(555);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额在9000志35000之间"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore > 9000 && wagemore <= 35000)
    then
        $cal.setCess(0.25);
        $cal.setPreminus(1005);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额在35000至55000之间"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore > 35000 && wagemore <= 55000)
    then
        $cal.setCess(0.3);
        $cal.setPreminus(2755);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额在55000至80000之间"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore > 55000 && wagemore <= 80000)
    then
        $cal.setCess(0.35);
        $cal.setPreminus(5505);
        update($cal);
end

rule "个人所得税：设置税率-->>应纳税所得额在80000以上"
    salience 2
    no-loop true
    activation-group "SETCess_Group"
    when
        $cal : Calculation(wagemore > 80000)
    then
        $cal.setCess(0.45);
        $cal.setPreminus(13505);
        update($cal);
end

rule "个人所得税：计算税后工资"
    salience 1
    when
        $cal : Calculation(wage > 0 && wagemore > 0 && wagemore > 0 && cess > 0)
    then
        $cal.setWageminus($cal.getWagemore()*$cal.getCess()-$cal.getPreminus());
        $cal.setActualwage($cal.getWage()-$cal.getWageminus());

        System.out.println("-----税前工资："+$cal.getWage());
        System.out.println("-----应纳税所得额："+$cal.getWagemore());
        System.out.println("-----税率：" + $cal.getCess());
        System.out.println("-----速算扣除数：" + $cal.getPreminus());
        System.out.println("-----扣税额：" + $cal.getWageminus());
        System.out.println("-----税后工资：" + $cal.getActualwage());
end
```

### 第五步：`DroolsConfig`

```java
package com.action.drools.config;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.Resource;
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
        System.setProperty("drools.dateformat","yyyy-MM-dd");
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
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }
}
```

### 第六步：`RuleService`

```java
package com.action.drools.service;

import com.action.drools.entity.Calculation;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用规则引擎，执行规则
 */
@Service
public class RuleService {
    @Autowired
    private KieBase kieBase;

    //个人所得税计算
    public Calculation calculate(Calculation calculation){
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(calculation);
        kieSession.fireAllRules();
        kieSession.dispose();
        return calculation;
    }
}
```

### 第七步：`RuleController`

```java
package com.action.drools.controller;

import com.action.drools.entity.Calculation;
import com.action.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/calculate")
    public Calculation calculate(double wage){
        Calculation calculation = new Calculation();
        calculation.setWage(wage);
        calculation = ruleService.calculate(calculation);
        System.out.println(calculation);
        return calculation;
    }
}
```

### 第八步：启动类 `DroolsApplication`

```java
package com.action.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsApplication.class);
    }
}
```

### 第九步：前端页面 `src/main/resources/static/calculation.html`

```html
<!DOCTYPE html>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <title>个人所得税计算</title>
    <meta name="description" content="个人所得税计算">
    <meta name="keywords" content="个人所得税计算">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<body class="mainBg">
<div id="app">
    <h3 align="center">个人所得税计算器（2011版）</h3>
    <table align="center" width="25%" border="0">
        <tr>
            <td>税前月收入</td>
            <td>
                <input type="text" v-model="cal.wage">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="计  算" @click="calculate()">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
            </td>
        </tr>
        <tr>
            <td>应纳税所得额</td>
            <td>
                <input type="text" v-model="cal.wagemore">
            </td>
        </tr>
        <tr>
            <td>税率</td>
            <td>
                <input type="text" v-model="cal.cess">
            </td>
        </tr>
        <tr>
            <td>速算扣除数</td>
            <td>
                <input type="text" v-model="cal.preminus">
            </td>
        </tr>
        <tr>
            <td>扣税额</td>
            <td>
                <input type="text" v-model="cal.wageminus">
            </td>
        </tr>
        <tr>
            <td>税后工资</td>
            <td>
                <input type="text" v-model="cal.actualwage">
            </td>
        </tr>
    </table>
</div>
</body>
<!-- 引入组件库 -->
<script src="js/vue.js"></script>
<script src="js/axios.js"></script>
<script>
    new Vue({
        el: '#app',
        data:{
            cal:{}
        },
        methods: {
            calculate(){
                if(this.cal.wage <= 3500){
                    alert("税前月收入需要大于3500!");
                    return;
                }
                axios.get("/rule/calculate?wage=" + this.cal.wage).then((res) => {
                    console.log(res);
                    this.cal = res.data;
                });
            }
        }
    });
</script>
</html>
```

## 4. 运行与测试

1. 在项目根目录执行：`mvn spring-boot:run`（或运行 `DroolsApplication`）。
2. 浏览器访问：`http://localhost:8080/calculation.html`。
3. 输入税前月收入（页面校验需大于 3500），点击「计算」；前端请求 `GET /rule/calculate?wage=...`，返回的 JSON 会回填各字段。

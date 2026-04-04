<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. 模块说明](#1-%E6%A8%A1%E5%9D%97%E8%AF%B4%E6%98%8E)
- [2. 规则说明](#2-%E8%A7%84%E5%88%99%E8%AF%B4%E6%98%8E)
- [3. 实现步骤](#3-%E5%AE%9E%E7%8E%B0%E6%AD%A5%E9%AA%A4)
  - [第一步：配置 `pom.xml`](#%E7%AC%AC%E4%B8%80%E6%AD%A5%E9%85%8D%E7%BD%AE-pomxml)
  - [第二步：创建 `src/main/resources/application.yml`](#%E7%AC%AC%E4%BA%8C%E6%AD%A5%E5%88%9B%E5%BB%BA-srcmainresourcesapplicationyml)
  - [第三步：实体类 `CreditCardApplyInfo`](#%E7%AC%AC%E4%B8%89%E6%AD%A5%E5%AE%9E%E4%BD%93%E7%B1%BB-creditcardapplyinfo)
  - [第四步：规则文件 `src/main/resources/rules/creditCardApply.drl`](#%E7%AC%AC%E5%9B%9B%E6%AD%A5%E8%A7%84%E5%88%99%E6%96%87%E4%BB%B6-srcmainresourcesrulescreditcardapplydrl)
  - [第五步：`DroolsConfig`](#%E7%AC%AC%E4%BA%94%E6%AD%A5droolsconfig)
  - [第六步：`RuleService`](#%E7%AC%AC%E5%85%AD%E6%AD%A5ruleservice)
  - [第七步：`RuleController`](#%E7%AC%AC%E4%B8%83%E6%AD%A5rulecontroller)
  - [第八步：启动类 `DroolsCreditCardApprovalApplication`](#%E7%AC%AC%E5%85%AB%E6%AD%A5%E5%90%AF%E5%8A%A8%E7%B1%BB-droolscreditcardapprovalapplication)
  - [第九步：前端页面 `src/main/resources/static/creditCardApply.html`](#%E7%AC%AC%E4%B9%9D%E6%AD%A5%E5%89%8D%E7%AB%AF%E9%A1%B5%E9%9D%A2-srcmainresourcesstaticcreditcardapplyhtml)
- [4. 运行与测试](#4-%E8%BF%90%E8%A1%8C%E4%B8%8E%E6%B5%8B%E8%AF%95)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. 模块说明

本模块为 **信用卡申请审批**：在 Spring Boot 中集成 Drools，根据申请人学历、房产车辆、月收入、已有信用卡数量等信息，先做合法性校验（不通过则 `drools.halt()`），再在通过的申请上通过 `activation-group` 互斥规则确定授信额度。

技术栈：Spring Boot 2.7.18、Java 8、Drools 7.10.0.Final，依赖与仓库 `pom.xml` 一致。

## 2. 规则说明

合法性检查规则如下：

| 规则编号 | 名称                       | 描述                                                         |
| :------- | :------------------------- | :----------------------------------------------------------- |
| 1        | 检查学历与薪水1            | 如果申请人既没房也没车，同时学历为大专以下，并且月薪少于5000，那么不通过 |
| 2        | 检查学历与薪水2            | 如果申请人既没房也没车，同时学历为大专或本科，并且月薪少于3000，那么不通过 |
| 3        | 检查学历与薪水3            | 如果申请人既没房也没车，同时学历为本科以上，并且月薪少于2000，同时之前没有信用卡的，那么不通过 |
| 4        | 检查申请人已有的信用卡数量 | 如果申请人现有的信用卡数量大于10，那么不通过                 |

信用卡额度确定规则：

| 规则编号 | 名称  | 描述                                                         |
| :------- | :---- | :----------------------------------------------------------- |
| 1        | 规则1 | 如果申请人有房有车，或者月收入在20000以上，那么发放的信用卡额度为15000 |
| 2        | 规则2 | 如果申请人没房没车，但月收入在10000~20000之间，那么发放的信用卡额度为6000 |
| 3        | 规则3 | 如果申请人没房没车，月收入在10000以下，那么发放的信用卡额度为3000 |
| 4        | 规则4 | 如果申请人有房没车或者没房但有车，月收入在10000以下，那么发放的信用卡额度为5000 |
| 5        | 规则5 | 如果申请人有房没车或者是没房但有车，月收入在10000~20000之间，那么发放的信用卡额度为8000 |

#### 

- **合法性检查**（`salience 10`）：不满足准入条件时将 `checkResult` 置为 `false` 并终止后续规则。
- **额度计算**（`salience 1`，`activation-group "quota_group"`）：在 `checkResult == true` 的前提下，按有房有车、收入区间等条件设置 `quota`。

规则文件路径：`src/main/resources/rules/creditCardApply.drl`。实体中与学历相关的常量定义在 `CreditCardApplyInfo` 中，规则里与 DRL 引用保持一致。

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
    <artifactId>drl-09-credit-card-approval</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>drl-09-credit-card-approval</name>
    <description>信用卡申请</description>
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
    name: creditCardApply
```

### 第三步：实体类 `CreditCardApplyInfo`

```java
package com.action.drools.entity;
/**
 * 信用卡申请信息
 */
public class CreditCardApplyInfo {
    public static final String EDUCATION_1 = "专科以下";
    public static final String EDUCATION_2 = "专科";
    public static final String EDUCATION_3 = "本科";
    public static final String EDUCATION_4 = "本科以上";

    private String name;
    private String sex;
    private int age;
    private String education;
    private String telephone;
    private double monthlyIncome = 0;//月收入
    private String address;

    private boolean hasHouse = false;//是否有房
    private boolean hasCar = false;//是否有车
    private int hasCreditCardCount = 0;//现持有信用卡数量

    private boolean checkResult = true;//审核是否通过
    private double quota = 0;//额度

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public int getHasCreditCardCount() {
        return hasCreditCardCount;
    }

    public void setHasCreditCardCount(int hasCreditCardCount) {
        this.hasCreditCardCount = hasCreditCardCount;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public String toString() {
        if(checkResult){
            return "审核通过，信用卡额度为：" + quota;
        }else {
            return "审核不通过";
        }
    }
}
```

### 第四步：规则文件 `src/main/resources/rules/creditCardApply.drl`

```java
package com.action.drools.creditCardApply
import com.action.drools.entity.CreditCardApplyInfo

//合法性检查
rule "如果申请人既没房也没车，同时学历为大专以下，并且月薪少于5000，那么不通过"
    salience 10
    no-loop true
    when
        $c:CreditCardApplyInfo(hasCar == false &&
                                hasHouse == false &&
                                education == CreditCardApplyInfo.EDUCATION_1 &&
                                monthlyIncome < 5000)
    then
        $c.setCheckResult(false);
        drools.halt();
end
rule "如果申请人既没房也没车，同时学历为大专或本科，并且月薪少于3000，那么不通过"
    salience 10
    no-loop true
    when
        $c:CreditCardApplyInfo(hasCar == false &&
                                hasHouse == false &&
                                (education == CreditCardApplyInfo.EDUCATION_2  ||
                                education == CreditCardApplyInfo.EDUCATION_3) &&
                                monthlyIncome < 3000)
    then
        $c.setCheckResult(false);
        drools.halt();
end
rule "如果申请人既没房也没车，同时学历为本科以上，并且月薪少于2000，同时之前没有信用卡的，那么不通过"
    salience 10
    no-loop true
    when
        $c:CreditCardApplyInfo(hasCar == false &&
                                hasHouse == false &&
                                education == CreditCardApplyInfo.EDUCATION_4 &&
                                monthlyIncome < 2000 &&
                                hasCreditCardCount == 0)
    then
        $c.setCheckResult(false);
        drools.halt();
end
rule "如果申请人现有的信用卡数量大于10，那么不通过"
    salience 10
    no-loop true
    when
        $c:CreditCardApplyInfo(hasCreditCardCount > 10)
    then
        $c.setCheckResult(false);
        drools.halt();
end
//--------------------------------------------------------------------------
//确定额度
rule "如果申请人有房有车，或者月收入在20000以上，那么发放的信用卡额度为15000"
    salience 1
    no-loop true
    activation-group "quota_group"
    when
        $c:CreditCardApplyInfo(checkResult == true &&
                                ((hasHouse == true && hasCar == true) ||
                                (monthlyIncome > 20000)))
    then
        $c.setQuota(15000);
end
rule "如果申请人没房没车，但月收入在10000~20000之间，那么发放的信用卡额度为6000"
    salience 1
    no-loop true
    activation-group "quota_group"
    when
        $c:CreditCardApplyInfo(checkResult == true &&
                                hasHouse == false &&
                                hasCar == false &&
                                monthlyIncome >= 10000 &&
                                monthlyIncome <= 20000)
    then
        $c.setQuota(6000);
end
rule "如果申请人没房没车，月收入在10000以下，那么发放的信用卡额度为3000"
    salience 1
    no-loop true
    activation-group "quota_group"
    when
        $c:CreditCardApplyInfo(checkResult == true &&
                                        hasHouse == false &&
                                        hasCar == false &&
                                        monthlyIncome < 10000)
    then
        $c.setQuota(3000);
end
rule "如果申请人有房没车或者没房但有车，月收入在10000以下，那么发放的信用卡额度为5000"
    salience 1
    no-loop true
    activation-group "quota_group"
    when
        $c:CreditCardApplyInfo(checkResult == true &&
                                ((hasHouse == true && hasCar == false) ||
                                (hasHouse == false && hasCar == true)) &&
                                monthlyIncome < 10000)
    then
        $c.setQuota(5000);
end
rule "如果申请人有房没车或者是没房但有车，月收入在10000~20000之间，那么发放的信用卡额度为8000"
    salience 1
    no-loop true
    activation-group "quota_group"
    when
        $c:CreditCardApplyInfo(checkResult == true &&
                                ((hasHouse == true && hasCar == false) ||
                                (hasHouse == false && hasCar == true)) &&
                                monthlyIncome >= 10000 &&
                                monthlyIncome <= 20000)
    then
        $c.setQuota(8000);
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

import com.action.drools.entity.CreditCardApplyInfo;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    @Autowired
    private KieBase kieBase;

    //调用Drools规则引擎实现信用卡申请
    public CreditCardApplyInfo creditCardApply(CreditCardApplyInfo creditCardApplyInfo){
        KieSession session = kieBase.newKieSession();
        session.insert(creditCardApplyInfo);
        session.fireAllRules();
        session.dispose();
        return creditCardApplyInfo;
    }
}
```

### 第七步：`RuleController`

```java
package com.action.drools.controller;

import com.action.drools.entity.CreditCardApplyInfo;
import com.action.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/creditCardApply")
    public CreditCardApplyInfo creditCardApply(@RequestBody 
        CreditCardApplyInfo creditCardApplyInfo){
        creditCardApplyInfo = ruleService.creditCardApply(creditCardApplyInfo);
        return creditCardApplyInfo;
    }
}
```

### 第八步：启动类 `DroolsCreditCardApprovalApplication`

```java
package com.action.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsCreditCardApprovalApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsCreditCardApprovalApplication.class);
    }
}
```

### 第九步：前端页面 `src/main/resources/static/creditCardApply.html`

```html
<!DOCTYPE html>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <title>XX银行信用卡申请</title>
    <meta name="description" content="个人所得税计算">
    <meta name="keywords" content="个人所得税计算">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<body class="mainBg">
<div id="app">
    <h3 align="center">XX银行信用卡申请</h3>
    <table align="center" width="50%" border="0">
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.name">
            </td>
            <td>性别</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.sex">
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.age">
            </td>
            <td>手机号</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.telephone">
            </td>
        </tr>
        <tr>
            <td>住址</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.address">
            </td>
            <td>学历</td>
            <td>
                <select v-model="creditCardApplyInfo.education">
                    <option value="专科以下">专科以下</option>
                    <option value="专科">专科</option>
                    <option value="本科">本科</option>
                    <option value="本科以上">本科以上</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>月收入</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.monthlyIncome">
            </td>
            <td>现持有信用卡数量</td>
            <td>
                <input type="text" v-model="creditCardApplyInfo.hasCreditCardCount">
            </td>
        </tr>
        <tr>
            <td>是否有房</td>
            <td>
                <select v-model="creditCardApplyInfo.hasHouse">
                    <option value="true">是</option>
                    <option value="false">否</option>
                </select>
            </td>
            <td>是否有车</td>
            <td>
                <select v-model="creditCardApplyInfo.hasCar">
                    <option value="true">是</option>
                    <option value="false">否</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <br>
                <input type="button" value="   申请   " @click="creditCardApply()">
            </td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                {{applyResultMessage}}
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
            creditCardApplyInfo:{
                education:"本科",
                hasHouse:true,
                hasCar:true
            },
            applyResultMessage:''
        },
        methods: {
            creditCardApply(){
                axios.post("/rule/creditCardApply",this.creditCardApplyInfo).then((res) => {
                    if(res.data.checkResult){
                        //审核通过
                        this.applyResultMessage = "恭喜你信用卡申请成功，信用卡额度为：" + res.data.quota;
                    }else{
                        //审核失败
                        this.applyResultMessage = "抱歉，您提交的信息审核未通过，您不能申请我行信用卡!";
                    }
                });
            }
        }
    });
</script>
</html>
```

## 4. 运行与测试

1. 在项目根目录执行：`mvn spring-boot:run`（或运行 `DroolsCreditCardApprovalApplication`）。
2. 浏览器访问：`http://localhost:8080/creditCardApply.html`。
3. 填写表单后点击「申请」；前端 `POST /rule/creditCardApply`，请求体为 JSON，与 `CreditCardApplyInfo` 字段对应，页面根据 `checkResult` 与 `quota` 展示结果。


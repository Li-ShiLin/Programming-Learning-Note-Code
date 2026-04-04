<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.决策表](#1%E5%86%B3%E7%AD%96%E8%A1%A8)
- [2.决策表入门案例](#2%E5%86%B3%E7%AD%96%E8%A1%A8%E5%85%A5%E9%97%A8%E6%A1%88%E4%BE%8B)
- [3.基于决策表的保险产品准入规则](#3%E5%9F%BA%E4%BA%8E%E5%86%B3%E7%AD%96%E8%A1%A8%E7%9A%84%E4%BF%9D%E9%99%A9%E4%BA%A7%E5%93%81%E5%87%86%E5%85%A5%E8%A7%84%E5%88%99)
  - [3.1 规则介绍](#31-%E8%A7%84%E5%88%99%E4%BB%8B%E7%BB%8D)
  - [3.2 实现步骤](#32-%E5%AE%9E%E7%8E%B0%E6%AD%A5%E9%AA%A4)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.决策表

前面编写的规则文件都是drl形式的文件，Drools除了支持drl形式的文件外还支持xls格式的文件（即Excel文件）。这种xls格式的文件通常称为决策表（decision table）。

决策表（decision table）是一个“精确而紧凑的”表示条件逻辑的方式，非常适合商业级别的规则。决策表与现有的drl文件可以无缝替换。Drools提供了相应的API可以将xls文件编译为drl格式的字符串。

一个决策表的例子如下：

![image-20200217160930138](../pics/image-20200217160930138.png)

 

决策表语法：

| 关键字       | 说明                                                         | 是否必须                                                     |
| :----------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| RuleSet      | 相当于drl文件中的package                                     | 必须，只能有一个。如果没有设置RuleSet对应的值则使用默认值rule_table |
| Sequential   | 取值为Boolean类型。true表示规则按照表格自上到下的顺序执行，false表示乱序 | 可选                                                         |
| Import       | 相当于drl文件中的import，如果引入多个类则类之间用逗号分隔    | 可选                                                         |
| Variables    | 相当于drl文件中的global，用于定义全局变量，如果有多个全局变量则中间用逗号分隔 | 可选                                                         |
| RuleTable    | 它指示了后面将会有一批rule，RuleTable的名称将会作为以后生成rule的前缀 | 必须                                                         |
| CONDITION    | 规则条件关键字，相当于drl文件中的when。下面两行则表示 LHS 部分，第三行则为注释行，不计为规则部分，从第四行开始，每一行表示一条规则 | 每个规则表至少有一个                                         |
| ACTION       | 规则结果关键字，相当于drl文件中的then                        | 每个规则表至少有一个                                         |
| NO-LOOP      | 相当于drl文件中的no-loop                                     | 可选                                                         |
| AGENDA-GROUP | 相当于drl文件中的agenda-group                                | 可选                                                         |

在决策表中还经常使用到占位符，语法为$后面加数字，用于替换每条规则中设置的具体值。

 

上面的决策表例子转换为drl格式的规则文件内容如下：

```java
package rules;

import com.itheima.drools.entity.PersonInfoEntity;
import java.util.List;
global java.util.List listRules;

rule "personCheck_10"
    salience 65535
    agenda-group "sign"
    when
        $person : PersonInfoEntity(sex != "男")
    then
        listRules.add("性别不对");
end

rule "personCheck_11"
    salience 65534
    agenda-group "sign"
    when
        $person : PersonInfoEntity(age < 22 || age > 25)
    then
        listRules.add("年龄不合适");
end

rule "personCheck_12"
    salience 65533
    agenda-group "sign"
    when
        $person : PersonInfoEntity(salary < 10000)
    then
        listRules.add("工资太低了");
end
```



要进行决策表相关操作，需要导入如下maven坐标：

```xml
<dependency>
    <groupId>org.drools</groupId>
    <artifactId>drools-decisiontables</artifactId>
    <version>7.10.0.Final</version>
</dependency>
```



通过下图可以发现，由于maven的依赖传递特性在导入drools-decisiontables坐标后，drools-core和drools-compiler等坐标也被传递了过来

![image-20200218105819932](../pics/image-20200218105819932.png)

 

Drools提供的将xls文件编译为drl格式字符串的API如下：

```java
String realPath = "C:\\testRule.xls";//指定决策表xls文件的磁盘路径
File file = new File(realPath);
InputStream is = new FileInputStream(file);
SpreadsheetCompiler compiler = new SpreadsheetCompiler();
String drl = compiler.compile(is, InputType.XLS);
```



Drools还提供了基于drl格式字符串创建KieSession的API：

```java
KieHelper kieHelper = new KieHelper();
kieHelper.addContent(drl, ResourceType.DRL);
KieSession session = kieHelper.build().newKieSession();
```

## 2.决策表入门案例

基于决策表的入门案例：

第一步：配置pom.xml文件

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.18</version>
        <relativePath/>
    </parent>
    <groupId>com.action.drools</groupId>
    <artifactId>drl-10-insurance-product-access-rules-based-on-decision-tables</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>drl-10-insurance-product-access-rules-based-on-decision-tables</name>
    <description>保险产品准入规则（决策表，教程 9.3）</description>
    <properties>
        <java.version>1.8</java.version>
        <drools.version>7.10.0.Final</drools.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <dependencies>
        <!-- Spring Web：REST、Spring MVC、内嵌 Tomcat -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- AOP：切面、拦截器（如日志、权限等横切逻辑） -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!-- 测试：JUnit、Mockito、Spring Test 等，仅测试作用域 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Apache Commons Lang：字符串、对象等通用工具类 -->
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
        <!-- Drools 决策表：将 Excel 等决策表编译为可执行规则 -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-decisiontables</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- Drools 核心：规则运行时、Working Memory、议程等 -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-core</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- Drools 编译器：编译 DRL、决策表等为 Knowledge Package -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-compiler</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- Drools 模板引擎：.drt 等模板化规则生成 -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-templates</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- KIE API：KieServices、KieContainer、KieSession 等统一入口 -->
        <dependency>
            <groupId>org.kie</groupId>
            <artifactId>kie-api</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- KIE 与 Spring 集成：在 Spring 中装配 KieModule/KieContainer；排除自带 Spring 以免与 Boot 管理版本冲突 -->
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
```

第二步：创建`/resources/application.yml`文件

```yml
server:
  port: 8080
spring:
  application:
    name: insuranceInfoCheck
drools:
  person:
    xls-path: classpath:rules/testRule.xls
```

第三步：创建实体类PersonInfoEntity

```java
package com.action.drools.entity;


public class PersonInfoEntity {
    private String sex;
    private int age;
    private double salary;

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
```



第四步：创建xls规则文件testRule.xls文件

`src/main/resources/rules/testRule.xls`

![image-20260404120247226](../pics/image-20260404120247226.png)

第五步：创建`PersonInfoCheckRuleController`

```java
package com.action.drools.controller;

import com.action.drools.entity.PersonInfoEntity;
import com.action.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class PersonInfoCheckRuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/personInfoCheck")
    public Map<String, Object> personInfoCheck(@RequestBody PersonInfoEntity personInfoEntity) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<String> list = ruleService.personInfoCheck(personInfoEntity);
            if (list != null && !list.isEmpty()) {
                map.put("checkResult", false);
                map.put("msg", "校验失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "校验通过");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult", false);
            map.put("msg", "未知错误");
            return map;
        }
    }
}
```



第六步：`RuleService`

```java
package com.action.drools.service;

import com.action.drools.entity.InsuranceInfo;
import com.action.drools.entity.PersonInfoEntity;
import com.action.drools.utils.KieSessionUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    @Value("${drools.person.xls-path:classpath:rules/testRule.xls}")
    private String personRulesPath;

    public List<String> personInfoCheck(PersonInfoEntity personInfo) throws Exception {
        KieSession session = KieSessionUtils.getKieSessionFromXLS(personRulesPath);
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(personInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();
        session.dispose();

        return listRules;
    }
}
```



第七步：工具类`KieSessionUtils`

```java
package com.action.drools.utils;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;

public class KieSessionUtils {
    private KieSessionUtils() {

    }

    // 把xls文件解析为String
    public static String getDRL(String realPath) throws IOException {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        try (InputStream is = getInputStream(realPath)) {
            return compiler.compile(is, InputType.XLS);
        }
    }

    private static InputStream getInputStream(String path) throws IOException {
        if (path != null && path.startsWith("classpath:")) {
            String classpathLocation = path.substring("classpath:".length());
            if (classpathLocation.startsWith("/")) {
                classpathLocation = classpathLocation.substring(1);
            }
            return new ClassPathResource(classpathLocation).getInputStream();
        }
        File file = new File(path); // 例如：C:\\abc.xls
        return new FileInputStream(file);
    }

    // drl为含有内容的字符串
    public static KieSession createKieSessionFromDRL(String drl) throws Exception {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }
            // throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        return kieHelper.build().newKieSession();
    }

    // realPath为Excel文件绝对路径
    public static KieSession getKieSessionFromXLS(String realPath) throws Exception {
        return createKieSessionFromDRL(getDRL(realPath));
    }
}
```

第八步：启动类`DroolsInsuranceAccessBasedOnDecisionTableApplication`

```java
package com.action.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsInsuranceAccessBasedOnDecisionTableApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsInsuranceAccessBasedOnDecisionTableApplication.class);
    }
}
```

第九步：前端页面

`src/main/resources/static/personInfoCheck.html`

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>人员规则校验</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <style>
        body { font-family: Arial, sans-serif; }
        table { border-collapse: collapse; margin: 20px auto; width: 50%; }
        td { padding: 8px; border: 1px solid #ddd; }
        h3 { text-align: center; }
        .result { margin-top: 16px; text-align: center; }
        .error-list { width: 50%; margin: 12px auto 0; color: #c00; }
    </style>
</head>
<body>
<div id="app">
    <h3>人员规则校验（testRule.xls）</h3>
    <table>
        <tr>
            <td>性别</td>
            <td>
                <select v-model="person.sex">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="number" v-model.number="person.age" placeholder="22-25"></td>
        </tr>
        <tr>
            <td>月薪</td>
            <td><input type="number" v-model.number="person.salary" placeholder=">=10000"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center;">
                <input type="button" value="开始校验" @click="personInfoCheck()">
            </td>
        </tr>
    </table>

    <div class="result">{{ resultMessage }}</div>
    <ul class="error-list" v-if="detailList.length > 0">
        <li v-for="(item, idx) in detailList" :key="idx">{{ item }}</li>
    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios@0.21.4/dist/axios.min.js"></script>
<script>
    new Vue({
        el: '#app',
        data: {
            person: {
                sex: '男',
                age: 23,
                salary: 12000
            },
            resultMessage: '',
            detailList: []
        },
        methods: {
            personInfoCheck: function () {
                axios.post('/rule/personInfoCheck', this.person).then((res) => {
                    if (res.data.checkResult) {
                        this.resultMessage = '校验通过';
                        this.detailList = [];
                    } else {
                        this.resultMessage = res.data.msg || '校验失败';
                        this.detailList = res.data.detail || [];
                    }
                }).catch(() => {
                    this.resultMessage = '调用失败，请检查后端服务或规则文件';
                    this.detailList = [];
                });
            }
        }
    });
</script>
</body>
</html>
```

第十步：测试

1. 启动项目
2. 访问：`http://localhost:8080/personInfoCheck.html`
3. 填写 `sex/age/salary` 点击“开始校验”

![image-20260404122748248](../pics/image-20260404122748248.png)





## 3.基于决策表的保险产品准入规则

### 3.1 规则介绍

各保险公司针对人身、财产推出了不同的保险产品，作为商业保险公司，筛选出符合公司利益最大化的客户是非常重要的，即各保险产品的准入人群是不同的，也就是说保险公司会针对不同的人群特征，制定不同的产品缴费和赔付规则。

我们来看一下某保险产品准入规则的简化版，当不满足以下规则时，系统模块需要返回准入失败标识和失败原因

```
规则1：  保险公司是：PICC
规则2：  销售区域是：北京、天津
规则3：  投保人年龄：0 ~ 17岁
规则4：  保险期间是：20年、25年、30年
规则5：  缴费方式是：趸交（一次性交清）或年交
规则6：  保险期与交费期规则一：保险期间为20年期交费期间最长10年交且不能选择[趸交]
规则7：  保险期与交费期规则二：保险期间为25年期交费期间最长15年交且不能选择[趸交]
规则8：  保险期与交费期规则三：保险期间为30年期交费期间最长20年交且不能选择[趸交]
规则9：  被保人要求：（投保年龄+保险期间）不得大于40周岁
规则10： 保险金额规则：投保时约定，最低为5万元，超过部分必须为1000元的整数倍
规则11： 出单基本保额限额规则：线上出单基本保额限额62.5万元，超62.5万元需配合契调转线下出单
```



在本案例中规则文件是一个Excel文件，业务人员可以直接更改这个文件中指标的值，系统不需要做任何变更。

### 3.2 实现步骤

本案例还是基于Spring Boot整合Drools的架构来实现。

第一步：配置pom.xml文件

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
    <artifactId>drl-10-insurance-product-access-rules-based-on-decision-tables</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>drl-10-insurance-product-access-rules-based-on-decision-tables</name>
    <description>保险产品准入规则（决策表，教程 9.3）</description>
    <properties>
        <java.version>1.8</java.version>
        <drools.version>7.10.0.Final</drools.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <dependencies>
        <!-- Spring Web：REST、Spring MVC、内嵌 Tomcat -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- AOP：切面、拦截器（如日志、权限等横切逻辑） -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!-- 测试：JUnit、Mockito、Spring Test 等，仅测试作用域 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Apache Commons Lang：字符串、对象等通用工具类 -->
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
        <!-- Drools 决策表：将 Excel 等决策表编译为可执行规则 -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-decisiontables</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- Drools 核心：规则运行时、Working Memory、议程等 -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-core</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- Drools 编译器：编译 DRL、决策表等为 Knowledge Package -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-compiler</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- Drools 模板引擎：.drt 等模板化规则生成 -->
        <dependency>
            <groupId>org.drools</groupId>
            <artifactId>drools-templates</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- KIE API：KieServices、KieContainer、KieSession 等统一入口 -->
        <dependency>
            <groupId>org.kie</groupId>
            <artifactId>kie-api</artifactId>
            <version>${drools.version}</version>
        </dependency>
        <!-- KIE 与 Spring 集成：在 Spring 中装配 KieModule/KieContainer；排除自带 Spring 以免与 Boot 管理版本冲突 -->
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



第二步：创建/resources/application.yml文件

```
server:
  port: 8080
spring:
  application:
    name: insuranceInfoCheck
drools:
  insurance:
    xls-path: classpath:rules/insuranceInfoCheck.xls
```



第三步：创建实体类InsuranceInfo

```java
package com.action.drools.entity;

/**
 * 保险信息
 */
public class InsuranceInfo {
    private String param1;//保险公司
    private String param2;//方案代码
    private String param3;//渠道号
    private String param4;//销售区域
    private String param5;//投保年龄
    private String param6;//保险期间
    private String param7;//缴费期间
    private String param8;//缴费方式
    private String param9;//保障类型
    private String param10;//等待期
    private String param11;//犹豫期
    private String param12;//职业类型
    private String param13;//保额限制
    private String param14;//免赔额
    private String param15;//主险保额
    private String param16;//主险保费
    private String param17;//附加险保额
    private String param18;//附加险保费
    private String param19;//与投保人关系
    private String param20;//与被保人关系
    private String param21;//性别
    private String param22;//证件
    private String param23;//保费
    private String param24;//保额

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getParam6() {
        return param6;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    public String getParam7() {
        return param7;
    }

    public void setParam7(String param7) {
        this.param7 = param7;
    }

    public String getParam8() {
        return param8;
    }

    public void setParam8(String param8) {
        this.param8 = param8;
    }

    public String getParam9() {
        return param9;
    }

    public void setParam9(String param9) {
        this.param9 = param9;
    }

    public String getParam10() {
        return param10;
    }

    public void setParam10(String param10) {
        this.param10 = param10;
    }

    public String getParam11() {
        return param11;
    }

    public void setParam11(String param11) {
        this.param11 = param11;
    }

    public String getParam12() {
        return param12;
    }

    public void setParam12(String param12) {
        this.param12 = param12;
    }

    public String getParam13() {
        return param13;
    }

    public void setParam13(String param13) {
        this.param13 = param13;
    }

    public String getParam14() {
        return param14;
    }

    public void setParam14(String param14) {
        this.param14 = param14;
    }

    public String getParam15() {
        return param15;
    }

    public void setParam15(String param15) {
        this.param15 = param15;
    }

    public String getParam16() {
        return param16;
    }

    public void setParam16(String param16) {
        this.param16 = param16;
    }

    public String getParam17() {
        return param17;
    }

    public void setParam17(String param17) {
        this.param17 = param17;
    }

    public String getParam18() {
        return param18;
    }

    public void setParam18(String param18) {
        this.param18 = param18;
    }

    public String getParam19() {
        return param19;
    }

    public void setParam19(String param19) {
        this.param19 = param19;
    }

    public String getParam20() {
        return param20;
    }

    public void setParam20(String param20) {
        this.param20 = param20;
    }

    public String getParam21() {
        return param21;
    }

    public void setParam21(String param21) {
        this.param21 = param21;
    }

    public String getParam22() {
        return param22;
    }

    public void setParam22(String param22) {
        this.param22 = param22;
    }

    public String getParam23() {
        return param23;
    }

    public void setParam23(String param23) {
        this.param23 = param23;
    }

    public String getParam24() {
        return param24;
    }

    public void setParam24(String param24) {
        this.param24 = param24;
    }
}
```



第四步：创建决策表文件insuranceInfoCheck.xls文件

`src/main/resources/rules/insuranceInfoCheck.xls`

![image-20260404124508721](../pics/image-20260404124508721.png)

第五步：封装工具类KieSessionUtils

```java
package com.action.drools.utils;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;

public class KieSessionUtils {
    private KieSessionUtils() {

    }

    // 把xls文件解析为String
    public static String getDRL(String realPath) throws IOException {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        try (InputStream is = getInputStream(realPath)) {
            return compiler.compile(is, InputType.XLS);
        }
    }

    private static InputStream getInputStream(String path) throws IOException {
        if (path != null && path.startsWith("classpath:")) {
            String classpathLocation = path.substring("classpath:".length());
            if (classpathLocation.startsWith("/")) {
                classpathLocation = classpathLocation.substring(1);
            }
            return new ClassPathResource(classpathLocation).getInputStream();
        }
        File file = new File(path); // 例如：C:\\abc.xls
        return new FileInputStream(file);
    }

    // drl为含有内容的字符串
    public static KieSession createKieSessionFromDRL(String drl) throws Exception {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }
            // throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        return kieHelper.build().newKieSession();
    }

    // realPath为Excel文件绝对路径
    public static KieSession getKieSessionFromXLS(String realPath) throws Exception {
        return createKieSessionFromDRL(getDRL(realPath));
    }
}
```



第六步：创建RuleService类

```java
package com.action.drools.service;

import com.action.drools.entity.InsuranceInfo;
import com.action.drools.entity.PersonInfoEntity;
import com.action.drools.utils.KieSessionUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    @Value("${drools.insurance.xls-path:classpath:rules/insuranceInfoCheck.xls}")
    private String insuranceRulesPath;
    @Value("${drools.person.xls-path:classpath:rules/testRule.xls}")
    private String personRulesPath;

    public List<String> insuranceInfoCheck(InsuranceInfo insuranceInfo) throws Exception {
        KieSession session = KieSessionUtils.getKieSessionFromXLS(insuranceRulesPath);
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(insuranceInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();
        session.dispose();

        return listRules;
    }

    public List<String> personInfoCheck(PersonInfoEntity personInfo) throws Exception {
        KieSession session = KieSessionUtils.getKieSessionFromXLS(personRulesPath);
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(personInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();
        session.dispose();

        return listRules;
    }
}
```



第七步：创建RuleController类

```java
package com.action.drools.controller;

import com.action.drools.entity.InsuranceInfo;
import com.action.drools.entity.PersonInfoEntity;
import com.action.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class InsuranceRuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/insuranceInfoCheckTest")
    public Map insuranceInfoCheckTest() {
        Map map = new HashMap();
        //模拟数据，实际应为页面传递过来
        InsuranceInfo insuranceInfo = new InsuranceInfo();
        insuranceInfo.setParam1("picc");
        insuranceInfo.setParam4("上海");
        insuranceInfo.setParam5("101");
        insuranceInfo.setParam6("12");
        insuranceInfo.setParam7("222");
        insuranceInfo.setParam8("1");
        insuranceInfo.setParam13("3");

        try {
            List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
            if (list != null && list.size() > 0) {
                map.put("checkResult", false);
                map.put("msg", "准入失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "准入成功");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult", false);
            map.put("msg", "未知错误");
            return map;
        }
    }


    @PostMapping("/insuranceInfoCheck")
    public Map<String, Object> insuranceInfoCheck(@RequestBody InsuranceInfo insuranceInfo) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
            if (list != null && !list.isEmpty()) {
                map.put("checkResult", false);
                map.put("msg", "准入失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "准入成功");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult", false);
            map.put("msg", "未知错误");
            return map;
        }
    }

}
```



第八步：创建启动类DroolsInsuranceAccessBasedOnDecisionTableApplication

```java
package com.action.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsInsuranceAccessBasedOnDecisionTableApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsInsuranceAccessBasedOnDecisionTableApplication.class);
    }
}
```

第九步：测试

1. 启动项目
2. 浏览器访问：`http://localhost:8080/insuranceInfoCheck.html`
3. 填写参数后点击“开始校验”

![image-20260404124919754](../pics/image-20260404124919754.png)
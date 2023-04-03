## 商品服务

## 1.商品三级分类

### 1.1 数据库设计和后端接口实现

三级分类：电商项目中每个商品都会所属于一个类别，常见三级分类结构如下图

![image-20230401074411684](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040142302.png)

商品三级分类表：`pms_category`

```sql
USE `gulimail_pms`;
DROP TABLE IF EXISTS `pms_category`;

CREATE TABLE `pms_category` (
  `cat_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` char(50) DEFAULT NULL COMMENT '分类名称',
  `parent_cid` bigint(20) DEFAULT NULL COMMENT '父分类id',
  `cat_level` int(11) DEFAULT NULL COMMENT '层级',
  `show_status` tinyint(4) DEFAULT NULL COMMENT '是否显示[0-不显示，1显示]',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` char(255) DEFAULT NULL COMMENT '图标地址',
  `product_unit` char(50) DEFAULT NULL COMMENT '计量单位',
  `product_count` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1433 DEFAULT CHARSET=utf8mb4 COMMENT='商品三级分类';
```

建表语句、添加商品服务相关数据的Insert语句都在`006_gulimail`下`pms_catelog.sql`文件：

![image-20230331010800623](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040142649.png)

**商品服务`gulimail-product`三级分类后端接口编写：**

- 给`CategoryEntity` 新增属性`List<CategoryEntity> children;`

```java
package com.atguigu.gulimail.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId
    private Long catId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类id
     */
    private Long parentCid;
    /**
     * 层级
     */
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String productUnit;
    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 当前分类的子分类
     */
    //  @TableField(exist = false)  -> 表明此属性不是数据库中的字段
    // 表示数据库表中不存在
    @TableField(exist = false)
    private List<CategoryEntity> children;

}
```

- Controller层：CategoryController

```java
package com.atguigu.gulimail.product.controller;
import com.atguigu.common.utils.R;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * 商品三级分类
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有分类以及子分类，然后以树形结构组装起来
     */
    @RequestMapping("/list/tree")
    public R list() {
        List<CategoryEntity> entities = categoryService.listWithTree();
        return R.ok().put("data", entities);
    }
}

```

- 接口：CategoryService

```java
package com.atguigu.gulimail.product.service;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * 商品三级分类
 */
public interface CategoryService extends IService<CategoryEntity> {
    List<CategoryEntity> listWithTree();
}
```

- 实现类：CategoryServiceImpl

```java
package com.atguigu.gulimail.product.service.impl;

import com.atguigu.gulimail.product.dao.CategoryDao;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Override
    public List<CategoryEntity> listWithTree() {
        // 1、查出所有分类 (查询条件nul表示查询所有数据)
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2、组装成父子的树形结构
        // 2.1 找到所有的一级分类
        List<CategoryEntity> levelOneMenus = entities
                .stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)  // 找到所有的一级分类
                .map((memu) -> {
                    memu.setChildren(getChildrens(memu, entities));
                    return memu;
                }).sorted((menu1, menu2) -> {
                    // 注意要防止空指针异常
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());

        return levelOneMenus;
    }

    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> childrens = all
                .stream()
                .filter(categoryEntity -> {
                    return categoryEntity.getParentCid() == root.getCatId();
                })
                .map(categoryEntity -> {
                    // 1、找到子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                })
                .sorted((menu1, menu2) -> {
                    // 2、菜单的排序
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                }).collect(Collectors.toList());
        return childrens;
    }

}
```

**测试：**

访问`http://localhost:10001/product/category/list/tree`，f12打开开发者模式，依次点击network、all、tree、preview即可看到返回的数据详情：

![image-20230401073325409](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040143901.png)



### 1.2 配置网关路由及路径重写

在`renren-fast`项目中添加`gulimail-common`，即引入nacos等依赖

```xml
		<dependency>
			<groupId>com.atguigu.gulimail</groupId>
			<artifactId>gulimail-common</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```

在`renren-fast`的`application.yml`文件中添加配置:

```yaml
spring:
  application:
    name: renren-fast
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```

在启动类上添加`@EnableDiscoveryClient`注解：

```java
package io.renren;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class RenrenApplication {

   public static void main(String[] args) {
      SpringApplication.run(RenrenApplication.class, args);
   }

}
```

`gulimall-gateway`项目的配置文件`application.yml`

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: admin_route
          uri: lb://renren-fast
          predicates:
            ## 前端项目发送请求都带上一个api前缀
            - Path=/api/**
```

以上配置会将`http://localhost:88/api/captcha.jpg` 路由到 `http://renren-fast:8080/api/captcha.jpg` 但是最终希望网关路由到`http://localhost:8080/renren-fast/captcha.jpg`。参考spring cloud gateway文档进行路径重写，实现正确的请求路由：

spring cloud gateway文档 路径重写：

![image-20230401230643991](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040147032.png)

依据路径重写规则,将配置做如下改动，即可将前端发出的`http://localhost:88/api/captcha.jpg` 请求经过路径重写而路由到`http://localhost:8080/renren-fast/captcha.jpg`

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: admin_route
          uri: lb://renren-fast
          predicates:
            ## 前端项目发送请求都带上一个api前缀
            - Path=/api/**
          filters:
            - RewritePath=/api/(?<segment>.*),/renren-fast/$\{segment}
```

如果此时前端项目发起请求，还会出现浏览器跨域问题：

![image-20230401234152943](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040147583.png)

### 1.3 网关配置跨域

**跨域:**  指的是浏览器不能执行其他网站的脚本。它是由浏览器的同源策略造成的，是**浏览器对javascript施加的安全限制**

**同源策略:**  是指协议，域名，端口都要相同，其中有一个不同都会产生跨域

![image-20230401174935236](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040148862.png)



**跨域流程：**

![image-20230401175503559](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040148132.png)



更多关于跨域的知识详见： `https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS`



**解决跨域的方法：**

**方法一**：使用nginx部署为同一域

将前端项目和后端项目部署到nginx服务器上，统一向nginx发起请求，nginx会把静态请求路由给前端项目，把动态请求路由到网关

![image-20230401184556533](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040150144.png)

**方法二**：配置当次请求允许跨域

- 跨域时浏览器会先向服务器发送一个预检请求询问服务器是否允许跨域

- 因此另一种办法就是在服务器端配置当次请求允许跨域。只需按如下规则配置响应头即可：

- 添加响应头：

  - Access-Control-Allow-Origin:支持哪些来源的请求跨域

  - Access-Control-Allow-Methods:支持哪些方法跨域

  - Access-Control-Allow-Credentials: 跨域请求默认不包含cookie，设置为true可以包含cookie

  - Access-Control-Expose-Headers: 跨域请求暴露的字段
    - CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段:
      - Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma
    - 如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定

  - Access-Control-Max-Age: 表明该响应的有效时间为多少秒。在有效时间内，浏览器无须为同一请求再次发起预检请求。请注意，浏览器自身维护了一个最大有效时间，如果该首部字段的值超过了最大有效时间，将不会生效



在网关里统一配置跨域：

在网关服务`gulimall-gateway`中新建`GulimallCorsConfiguration.java`：

```java
package com.atguigu.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
@Configuration
public class GulimallCorsConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1、配置跨域
        corsConfiguration.addAllowedHeader("*");    // 允许所有请求头跨域
        corsConfiguration.addAllowedMethod("*");    // 允许所有请求方式（get\post\delete\...）跨域
        corsConfiguration.addAllowedOrigin("*");    // 允许所有请求来源跨域
        corsConfiguration.setAllowCredentials(true); // 允许携带Cookie跨域

        // 任意路径都使用上述配置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
```

配置好网关跨域之后，将`renren-fast`中的跨域配置类`src/main/java/io/renren/config/CorsConfig.java`注释掉



配置网关模块`gulimall-product`，将商品有关的请求都转到商品服务，注意：精确的路由地址`/api/product/**`尽量发在不精确的路由`/api/**`前

```yaml
spring:
  cloud:
    gateway:
      routes:

## 转发到商品服务
        - id: product_route
          uri: lb://gulimall-product
          predicates:
            - Path=/api/product/**,/hello
          filters:
            - RewritePath=/api/(?<segment>.*),/$\{segment}

        - id: admin_route
          uri: lb://renren-fast
          predicates:
            ## 前端项目发送请求都带上一个api前缀
            - Path=/api/**
          filters:
            - RewritePath=/api/(?<segment>.*),/renren-fast/$\{segment}
```

添加商品服务命名空间，并将命名空间id拷贝到`src/main/resources/bootstrap.properties`配置文件

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040153244.png" > <b>添加商品服务命名空间</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202304040154217.png" > <b>拷贝命名空间id到配置文件</b></td>
    </tr>
    </table>

`src/main/resources/bootstrap.properties`配置：

```properties
spring.application.name=gulimall-product
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
spring.cloud.nacos.config.namespace=320c3af9-0870-42c9-aba3-f0d54f9cbc63
```


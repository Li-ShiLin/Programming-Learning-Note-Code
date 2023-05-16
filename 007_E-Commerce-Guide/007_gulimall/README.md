**目录：**

<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [1.商品服务-属性分组](#1-)
  * [1.1 SPU和SKU | 属性分组概念](#11-spusku-)
  * [1.2 创建菜单，导入前端代码](#12-)
  * [1.3 获取分类属性分组](#13-)
  * [1.4 属性分组新增功能\修改回显](#14-)
  * [1.5 实现分页-引入插件](#15-)
  * [1.6 品牌分页查询](#16-)
  * [1.7 关联分类查询、新增](#17-)
- [2.商品服务-规格参数](#2-)
  * [2.1 Object划分|VO](#21-objectvo)
  * [2.2 规格参数新增](#22-)
  * [2.3 获取规格参数分页](#23-)
  * [2.4 查询属性详情](#24-)
  * [2.5 修改属性](#25-)
- [3.商品服务-销售属性](#3-)
  * [3.1 获取销售属性分页](#31-)
- [4. 商品服务-属性分组和属性的关联](#4-)
  * [4.1 获取属性分组关联的所有属性](#41-)
  * [4.2 删除属性与分组的关联关系](#42-)
  * [4.3 获取和当前属性分组没有关联的其他属性](#43-)
  * [4.4 添加属性与分组关联关系](#44-)
  * [4.5 属性与分组相关功能测试](#45-)
- [5.商品服务-功能完善](#5-)
  * [5.1 会员服务-基础配置](#51-)
  * [5.2 获取分类关联的品牌](#52-)
  * [5.3 获取分类下所有分组&关联属性](#53-)
  * [5.4 新增商品](#54-)
    + [5.4.1.接口描述](#541)
    + [5.4.2 新增商品接口实现](#542-)
    + [5.4.3 feign远程调用访问`gulimall-coupon`优惠卷服务接口](#543-feigngulimall-coupon)
  * [5.5 spu检索](#55-spu)
  * [5.6 sku检索](#56-sku)
- [6. 库存服务](#6-)
  * [6.1 库存服务-基础配置](#61-)
  * [6.2 获取库存列表](#62-)
  * [6.3 库存服务：查询商品库存服务](#63-)
  * [6.4 查询采购需求](#64-)
  * [6.5 查询未领取的采购单](#65-)
  * [6.6 合并采购需求](#66-)
  * [6.7 领取采购单](#67-)
  * [6.8 完成采购](#68-)
  * [6.9 商品服务-spu管理-获取spu规格](#69-spu-spu)
  * [6.10 商品服务补充-批量修改商品规格](#610-)
- [7.分布式基础篇总结](#7)

<!-- TOC end -->

<!-- TOC --><a name="1-"></a>
## 1.商品服务-属性分组

<!-- TOC --><a name="11-spusku-"></a>
### 1.1 SPU和SKU | 属性分组概念

**SPU: Standard Product Unit(标准化产品单元)**

- 是商品信息聚合的最小单位，是一组可复用、易检索的标准化信息的集合，该集合描述了一个产品的特性

**SKU: Stock Keeping Unit(库存量单位)**

- 即库存进出计量的基本单元，可以是以件，盒，托盘等为单位。
- SKU这是对于大型连锁超市DC(配送中心）物流管理的一个必要的方法。
- 现在已经被引申为产品统一编号的简称，每种产品均对应有唯一的SKU号

**基本属性【规格参数】与销售属性**

- 每个分类下的商品共享规格参数,与销售属性。只是有些商品不一定要用这个分类下全部的属性,
- 属性是以三级分类组织起来的

- 规格参数中有些是可以提供检索的
- 规格参数也是基本属性,他们具有自己的分组
- 属性的分组也是以三级分类组织起来的
- 属性名确定的，但是值是每一个商品不同来决定的



**【属性分组-规格参数-销售属性-三级分类】关联关系**

![image-20230430173227583](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162048828.png)



**SPU-SKU-属性表**

![image-20230430174119973](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162049817.png)

<!-- TOC --><a name="12-"></a>
### 1.2 创建菜单，导入前端代码

1.在`gulimall_admin`数据库下，运行一下SQL，运行`sys_menus.sql`创建全部菜单

2.sys_menus.sql。菜单相关SQL语句:

```sql
/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.27 : Database - gulimall_admin
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gulimail_admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gulimail_admin`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
                            `menu_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
                            `name` VARCHAR(50) DEFAULT NULL COMMENT '菜单名称',
                            `url` VARCHAR(200) DEFAULT NULL COMMENT '菜单URL',
                            `perms` VARCHAR(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `type` INT(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
                            `icon` VARCHAR(50) DEFAULT NULL COMMENT '菜单图标',
                            `order_num` INT(11) DEFAULT NULL COMMENT '排序',
                            PRIMARY KEY (`menu_id`)
) ENGINE=INNODB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

INSERT  INTO `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) VALUES (1,0,'系统管理',NULL,NULL,0,'system',0),(2,1,'管理员列表','sys/user',NULL,1,'admin',1),(3,1,'角色管理','sys/role',NULL,1,'role',2),(4,1,'菜单管理','sys/menu',NULL,1,'menu',3),(5,1,'SQL监控','http://localhost:8080/renren-fast/druid/sql.html',NULL,1,'sql',4),(6,1,'定时任务','job/schedule',NULL,1,'job',5),(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),(20,3,'新增',NULL,'sys:role:save,sys:menu:list',2,NULL,0),(21,3,'修改',NULL,'sys:role:update,sys:menu:list',2,NULL,0),(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),(27,1,'参数管理','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'config',6),(29,1,'系统日志','sys/log','sys:log:list',1,'log',7),(30,1,'文件上传','oss/oss','sys:oss:all',1,'oss',6),(31,0,'商品系统','','',0,'editor',0),(32,31,'分类维护','product/category','',1,'menu',0),(34,31,'品牌管理','product/brand','',1,'editor',0),(37,31,'平台属性','','',0,'system',0),(38,37,'属性分组','product/attrgroup','',1,'tubiao',0),(39,37,'规格参数','product/baseattr','',1,'log',0),(40,37,'销售属性','product/saleattr','',1,'zonghe',0),(41,31,'商品维护','product/spu','',0,'zonghe',0),(42,0,'优惠营销','','',0,'mudedi',0),(43,0,'库存系统','','',0,'shouye',0),(44,0,'订单系统','','',0,'config',0),(45,0,'用户系统','','',0,'admin',0),(46,0,'内容管理','','',0,'sousuo',0),(47,42,'优惠券管理','coupon/coupon','',1,'zhedie',0),(48,42,'发放记录','coupon/history','',1,'sql',0),(49,42,'专题活动','coupon/subject','',1,'tixing',0),(50,42,'秒杀活动','coupon/seckill','',1,'daohang',0),(51,42,'积分维护','coupon/bounds','',1,'geren',0),(52,42,'满减折扣','coupon/full','',1,'shoucang',0),(53,43,'仓库维护','ware/wareinfo','',1,'shouye',0),(54,43,'库存工作单','ware/task','',1,'log',0),(55,43,'商品库存','ware/sku','',1,'jiesuo',0),(56,44,'订单查询','order/order','',1,'zhedie',0),(57,44,'退货单处理','order/return','',1,'shanchu',0),(58,44,'等级规则','order/settings','',1,'system',0),(59,44,'支付流水查询','order/payment','',1,'job',0),(60,44,'退款流水查询','order/refund','',1,'mudedi',0),(61,45,'会员列表','member/member','',1,'geren',0),(62,45,'会员等级','member/level','',1,'tubiao',0),(63,45,'积分变化','member/growth','',1,'bianji',0),(64,45,'统计信息','member/statistics','',1,'sql',0),(65,46,'首页推荐','content/index','',1,'shouye',0),(66,46,'分类热门','content/category','',1,'zhedie',0),(67,46,'评论管理','content/comments','',1,'pinglun',0),(68,41,'spu管理','product/spu','',1,'config',0),(69,41,'发布商品','product/spuadd','',1,'bianji',0),(70,43,'采购单维护','','',0,'tubiao',0),(71,70,'采购需求','ware/purchaseitem','',1,'editor',0),(72,70,'采购单','ware/purchase','',1,'menu',0),(73,41,'商品管理','product/manager','',1,'zonghe',0),(74,42,'会员价格','coupon/memberprice','',1,'admin',0),(75,42,'每日秒杀','coupon/seckillsession','',1,'job',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
```

刷新前端，可以看到菜单新增成功：

![image-20230430235158191](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162049066.png)

3.以后前端代码不自己编写了，复制/代码/前端/modules/文件夹里面的内容复制到vs中。后续的前端代码可以从资料中拷贝，要想使用资料中的前端接口，就必须按照写好的接口进行开发，具体前后端接口定义见接口文档，文档地址：https://easydoc.xyz/s/78237135 。后续的前端代码不用编写了，直接从资料中将common、product文件拷贝到前端项目的`src\views\modules`目录下：

![image-20230501180056941](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162050706.png)

4.**属性与属性分组的维护** 目标：选中三级分类，为分类新增属性分组，每个分组下可以关联规则参数。属性分组功能:选中三级分类后会查出已有的分组。同时也可以点击新增来添加分组。三级分类的菜单树在属性分组、规格参数、销售属性中都要重复使用，用来展示商品信息。所以可以将其抽取出来（复制的代码中已实现）

![image-20230501174409930](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162050354.png)

<!-- TOC --><a name="13-"></a>
### 1.3 获取分类属性分组

接口描述：获取分类属性分组

请求参数：

```json
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为'//检索关键字
}
```

响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"attrGroupId": 0, //分组id
			"attrGroupName": "string", //分组名
			"catelogId": 0, //所属分类
			"descript": "string", //描述
			"icon": "string", //图标
			"sort": 0 //排序
			"catelogPath": [2,45,225] //分类完整路径
		}]
	}
}
```

后端实现，在`gulimall-product`的`AttrGroupController`类中添加接口

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId){
        PageUtils page = attrGroupService.queryPage(params,catelogId);
        return R.ok().put("page", page);
    }

}
```

`AttrGroupService`接口：

```java
public interface AttrGroupService extends IService<AttrGroupEntity> {
    PageUtils queryPage(Map<String, Object> params, Long catelogId);
}
```

`AttrGroupServiceImpl`实现类：

```java
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)


        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if(!StringUtils.isEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }

        if( catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }else {
            wrapper.eq("catelog_id",catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }

    }

}
```

postMan测试：访问`http://localhost:88/api/product/attrgroup/list/1?page=1&key=aa` ，返回如下：

```json
{
    "msg": "success",
    "code": 0,
    "page": {
        "totalCount": 0,
        "pageSize": 10,
        "totalPage": 0,
        "currPage": 1,
        "list": []
    }
}
```

<!-- TOC --><a name="14-"></a>
### 1.4 属性分组新增功能\修改回显

**问题一**：   新增时发现返回的数据，三级菜单下面也有children（为空）导致前端显示的时候出现多余的层级。

解决方法：设置后端，当children为空时，不返回children字段。在children字段上添加注解：当值为空时，不返回当前字段

![image-20230502025324352](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162051571.png)

解决：在`children`字段上添加`@JsonInclude(JsonInclude.Include.NON_EMPTY)`注解

```java
@TableName("pms_category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前分类的子分类
     */
    //  @TableField(exist = false)  -> 表明此属性不是数据库中的字段
    // 表示数据库表中不存在
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<CategoryEntity> children;

}
```

**问题二**：修改分组时所属分类的没有回显成功 。 解决：后端`AttrGroupEntity`新增完整路径属性，维护三级分类节点的id数组，将id数组返回给前端进行回显

![image-20230502034558558](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162051489.png)

`AttrGroupEntity`新增完整路径属性：

```java
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 完整路径
	 */
	@TableField(exist = false)
	private Long[] catelogPath;

}
```

控制层`AttrGroupController`：

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();

        Long[] path = categoryService.findCatelogPath(catelogId);

        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }
}

```

`CategoryService`接口：

```java
public interface CategoryService extends IService<CategoryEntity> {
    /**
     * 找到catelogId的完整路径
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);
}

```

`CategoryServiceImpl`实现类： 

```java
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    /**
     * 找到catelogId的完整路径
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();

        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);

        return  parentPath.toArray(new Long[parentPath.size()]);
    }


    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        // 1.收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;
    }
}
```

测试：

```java
    @Test
    public void testFindPath(){
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("catelogPath:{}", Arrays.asList(catelogPath));
        
        // 日志打印 ： catelogPath:[2, 34, 225]
    }
```

<!-- TOC --><a name="15-"></a>
### 1.5 实现分页-引入插件

发现自动生成的分页条不好使，原因是没有引入mybatis-plus的分页插件。在`gulimail-product`服务中新建配置类，引入如下配置

```java
package com.atguigu.gulimail.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement  // 开启事务功能
@MapperScan("com.atguigu.gulimail.product.dao")  // 扫描mapper所在包
public class MybatisConfig {

    //引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }
}
```

<!-- TOC --><a name="16-"></a>
### 1.6 品牌分页查询

需求：通过品牌id或者品牌名进行查询，品牌名支持模糊查询

![image-20230502134838844](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162051847.png)

`BrandController` 类：

```java
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }
}
```

`BrandService`接口：

```java
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
```

`BrandServiceImpl`实现类：

```java
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 1、获取key
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<BrandEntity>();
        if (!StringUtils.isEmpty(key)){
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="17-"></a>
### 1.7 关联分类查询、新增

**数据库表关系：**

小米品牌下面可能包括手机、电器等分类，同样手机分类可能包括小米、华为等多个品牌。所以品牌与分类是多对多的关系。表`pms_category_brand_relation`保存品牌与分类的多对多关系

![image-20230502142509000](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162052780.png)



**需求：实现关联分类的查询和新增功能**

![image-20230502150137617](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162052213.png)

实现关联分类的查询和新增功能 ：`CategoryBrandRelationController`类

```java
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    /**
     * 获取当前品牌关联的所有分类列表
     */
//    @RequestMapping(value = "/catelog/list",method = RequestMethod.GET)
    @GetMapping("/catelog/list")
    public R cateloglist(@RequestParam("brandId")Long brandId){
        List<CategoryBrandRelationEntity> data = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId)
        );
        return R.ok().put("data", data);
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        categoryBrandRelationService.saveDetail(categoryBrandRelation);
        return R.ok();
    }
}
```

实现关联分类的查询和新增功能： `CategoryBrandRelationService`接口：

```java
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);
}
```

实现关联分类的查询和新增功能：`CategoryBrandRelationServiceImpl`实现类：

```java
@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        //1、查询详细名字
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);
    }
}
```

**解决数据一致性问题：**

为了方便检索，我们在新增关联分类时直接在`pms_category_brand_relation`表中做了冗余存储，这样做会出现数据一致性问题：在修改`pms_brand表`  和  `pms_category表`时 ，必须保证`pms_category_brand_relation表`中的数据也做相应变化。业务逻辑修改：当修改`品牌brand`或这`三级分类Category`时需要同步更新`CategoryBrandRelationEntity`。

解决数据一致性问题 ：`BrandController`类：

```java
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
        brandService.updateDetail(brand);
        return R.ok();
    }
}
```

解决数据一致性问题 ：`BrandService`接口：

```java
public interface BrandService extends IService<BrandEntity> {

    void updateDetail(BrandEntity brand);
}
```

解决数据一致性问题：`BrandServiceImpl`实现类：

```java
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        // 1.修改brand表中的数据
        this.updateById(brand);
        // 2.保证冗余字段的数据一致
        if (!StringUtils.isEmpty(brand.getName())) {
            //同步更新其他关联表中的数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            // 如果还有冗余，则继续更新其他关联：
        }
    }
}
```

解决数据一致性问题：`CategoryController`类

```java
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
        categoryService.updateCascade(category);
        return R.ok();
    }
}
```

解决数据一致性问题：` CategoryService`接口

```java
public interface CategoryService extends IService<CategoryEntity> {
    void updateCascade(CategoryEntity category);
}
```

解决数据一致性问题：`CategoryServiceImpl`实现类

```java
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    
    /**
     * 级联更新所有关联的数据
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

}
```

解决数据一致性问题：`CategoryBrandRelationService`接口

```java
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {
    
    void updateBrand(Long brandId, String name);

    void updateCategory(Long catId, String name);
}
```

解决数据一致性问题：`CategoryBrandRelationServiceImpl`实现类

```java
@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setBrandId(brandId);
        relationEntity.setBrandName(name);
        this.update(relationEntity,new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId));
    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId,name);
    }

}
```

解决数据一致性问题：`CategoryBrandRelationDao`类

```java
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
    void updateCategory(@Param("catId") Long catId, @Param("name") String name);
}
```

解决数据一致性问题：`CategoryBrandRelationDao.xml`配置如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimail.product.dao.CategoryBrandRelationDao">

	<!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimail.product.entity.CategoryBrandRelationEntity" id="categoryBrandRelationMap">
        <result property="id" column="id"/>
        <result property="brandId" column="brand_id"/>
        <result property="catelogId" column="catelog_id"/>
        <result property="brandName" column="brand_name"/>
        <result property="catelogName" column="catelog_name"/>
    </resultMap>

    <update id="updateCategory">
        UPDATE `pms_category_brand_relation` SET catelog_name=#{name} WHERE catelog_id=#{catId}
    </update>

</mapper>
```

<!-- TOC --><a name="2-"></a>
## 2.商品服务-规格参数

<!-- TOC --><a name="21-objectvo"></a>
### 2.1 Object划分|VO

**1.PO(persistant object)持久对象**

```
PO 就是对应数据库中某个表中的一条记录,多个记录可以用Po 的集合。PO中应该不包含任何对数据库的操作
```

**2.Do (Domain object领域对象**

```
就是从现实世界中抽象出来的有形或无形的业务实体
```

**3.TO(Transfer Object)，数据传输对象**

```
不同的应用程序之间传输的对象
```

**4.DTO (Data Transfer Obiect）数据传输对象**

```
 这个概念来源于J2EE的设计模式，
 原来的目的是为了EJB 的分布式应用提供粗粒度的数据实体，以减少分布式调用的次数，
 从而提高分布式调用的性能和降低网络负载，但在这里，泛指用于展示层与服务层之间的数据传输对象
```

**5.vo(value object)值对象**

    1.通常用于业务层之间的数据传递，和 PO一样也是仅仅包含数据而已。但应是抽象出的业务对象，
       可以和表对应，也可以不，这根据业务的需要。用new关键字创建，由GC回收的
    2.View object:视图对象
    3.接受页面传递来的数据，封装对象。将业务处理完成的对象，封装成页面要用的数据

**6.BO(business object)业务对象**

```
1.从业务模型的角度看，见 UML 元件领域模型中的领域对象。
2.封装业务逻辑的java对象，通过调用DAO方法，结合 PO,Vo 进行业务操作。
3.business object:业务对象主要作用是把业务逻辑封装为一个对象。
这个对象可以包括一个或多个其它的对象。比如一个简历，有教育经历、工作经历、杜会关系等等。
我们可以把教育经历对应一个PO，工作经历对应一个PO，社会关系对应一个PO。建立一个对应简历的 BO对象处理简历，
每个BO包含这些PO。这样处理业务逻辑时，我们就可以针对 BO去处理
```

**7.POJO(plain ordinary java object)简单无规则java对象**

```
传统意义的 java对象。就是说在一些Object/Relation Mapping 工具中，
能够做到维护数据库表记录的 persisent object完全是一个符合Java Bean规范的纯 Java对象，
没有增加别的属性和方法。我的理解就是最基本的java Bean ，只有属性字段及setter和getter,方法!
POJo是DO/DTO/BO/Vo的统称。
```

**8.DAO(data access object)数据访问对象**

```
是一个sun的一个标准 j2ee设计模式,这个模式中有个接口就是DAO，它负持久层的操作。
为业务层提供接口。此对象用于访问数据库。通常和 Po 结合使用，DAO中包含了各种数据库的操作方法。
通过它的方法，结合PO对数据库进行相关的操作。夹在业务逻辑与数据库资源中间。
配合 Vo，提供数据库的 CRUD 操作
```

<!-- TOC --><a name="22-"></a>
### 2.2 规格参数新增

![image-20230503011123022](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162053116.png)

新增`AttrVo`类，接收`AttrEntity` 和`AttrAttrgroupRelationEntity`的字段信息来更新这两个类，保证数据的一致性

```java
package com.atguigu.gulimail.product.vo;
import lombok.Data;
@Data
public class AttrVo {
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;
    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    private Integer valueType;
    /**
     * 属性图标
     */
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;
    /**
     * 所属分类
     */
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

    private Long attrGroupId;
}
```

` AttrController`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);
        return R.ok();
    }
}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {
    void saveAttr(AttrVo attr);
}
```

`AttrServiceImpl`实现类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
//        attrEntity.setAttrName(attr.getAttrName());
        BeanUtils.copyProperties(attr, attrEntity);
        //1、保存基本数据
        this.save(attrEntity);
        //2、保存关联关系
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attr.getAttrGroupId());
        relationEntity.setAttrId(attrEntity.getAttrId());
        relationDao.insert(relationEntity);
    }
}
```

<!-- TOC --><a name="23-"></a>
### 2.3 获取规格参数分页

1、URL：`/product/attr/base/list/{catelogId}`

2、请求参数

```json
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为'//检索关键字
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"attrId": 0, //属性id
			"attrName": "string", //属性名
			"attrType": 0, //属性类型，0-销售属性，1-基本属性
			"catelogName": "手机/数码/手机", //所属分类名字
			"groupName": "主体", //所属分组名字
			"enable": 0, //是否启用
			"icon": "string", //图标
			"searchType": 0,//是否需要检索[0-不需要，1-需要]
			"showDesc": 0,//是否展示在介绍上；0-否 1-是
			"valueSelect": "string",//可选值列表[用逗号分隔]
			"valueType": 0//值类型[0-为单个值，1-可以选择多个值]
		}]
	}
}
```

响应数据中的字段不仅来自`pms_attr`，也和`pms_attr_attrgroup_relation`表和`pms_attr_group`表，因此可以新增`AttrRespVo`来继承`AttrVo`，并增加多余的字段

![image-20230503045229666](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162054763.png)

`AttrRespVo`类：

```java
@Data
public class AttrRespVo extends AttrVo {
    /**
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */
    // 所属分类
    private String catelogName;

    // 所属分组
    private String groupName;

    private Long[] catelogPath;
}
```

`AttrController`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    
    // /product/attr/base/list/{catelogId}
    @GetMapping("/base/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId);
        return R.ok().put("page", page);
    }
}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {
    
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId);
}
```

`AttrServiceImpl`实现类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;


    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId) {

        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (catelogId != 0) {
            // 如果有分类条件、则将分类条件加上
            queryWrapper.eq("catelog_id", catelogId);
        }

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            // attr_id   attr_name
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        //封装分页数据
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVoList = records.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);

            // 1.设置分类和分组的名字
            AttrAttrgroupRelationEntity attrId = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            if (attrId != null) {
                // 设置分组的名字
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                String attrGroupName = attrGroupEntity.getAttrGroupName();
                if (attrGroupName != null) {
                    attrRespVo.setGroupName(attrGroupName);
                }
            }

            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null && categoryEntity.getName() != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(attrRespVoList);
        return pageUtils;
    }
    
}
```

效果：可根据`id`或通过`属性名`进行查询、且可以通过三级分类目录进行查询

![image-20230503050325693](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162054215.png)

<!-- TOC --><a name="24-"></a>
### 2.4 查询属性详情

1、url地址：`/product/attr/info/{attrId}`

2、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"attr": {
		"attrId": 4,
		"attrName": "aad",
		"searchType": 1,
		"valueType": 1,
		"icon": "qq",
		"valueSelect": "v;q;w",
		"attrType": 1,
		"enable": 1,
		"showDesc": 1,
		"attrGroupId": 1, //分组id
		"catelogId": 225, //分类id
		"catelogPath": [2, 34, 225] //分类完整路径
	}
}
```

`AttrController`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrRespVo respVo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", respVo);
    }
}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {
    AttrRespVo getAttrInfo(Long attrId);
}
```

`AttrServiceImpl`类：

```java
package com.atguigu.gulimail.product.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimail.product.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimail.product.dao.AttrDao;
import com.atguigu.gulimail.product.dao.AttrGroupDao;
import com.atguigu.gulimail.product.dao.CategoryDao;
import com.atguigu.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimail.product.entity.AttrEntity;
import com.atguigu.gulimail.product.entity.AttrGroupEntity;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.AttrService;
import com.atguigu.gulimail.product.service.CategoryService;
import com.atguigu.gulimail.product.vo.AttrRespVo;
import com.atguigu.gulimail.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, respVo);

        // 1.设置分组信息
        AttrAttrgroupRelationEntity attrgroupRelation = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
        if (attrgroupRelation != null) {
            respVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupRelation.getAttrGroupId());
            if (attrGroupEntity != null) {
                respVo.setGroupName(attrGroupEntity.getAttrGroupName());
            }
        }
        // 2、设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        respVo.setCatelogPath(catelogPath);

        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if (categoryEntity != null && categoryEntity.getName() != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }
        return respVo;
    }

}
```



<!-- TOC --><a name="25-"></a>
### 2.5 修改属性

1、url:`/product/attr/update`

2、请求参数：

```json
{
  "attrId": 0, //属性id
  "attrGroupId": 0, //属性分组id
  "attrName": "string",//属性名
  "attrType": 0, //属性类型
  "catelogId": 0, //分类id
  "enable": 0, //是否可用 
  "icon": "string", //图标
  "searchType": 0, //是否检索
  "showDesc": 0, //快速展示
  "valueSelect": "string", //可选值列表
  "valueType": 0 //可选值模式
}
```

`AttrController`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateAttr(attr);
        return R.ok();
    }
}

```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {

    void updateAttr(AttrVo attr);
}
```

`AttrServiceImpl`实现类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        // 1.修改分组关联
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attr.getAttrGroupId());
        relationEntity.setAttrId(attr.getAttrId());

        Integer count = relationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));

        if (count > 0) {
            relationDao.update(relationEntity, new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
        }else {
            relationDao.insert(relationEntity);
        }
    }

}
```

<!-- TOC --><a name="3-"></a>
## 3.商品服务-销售属性

<!-- TOC --><a name="31-"></a>
### 3.1 获取销售属性分页

1、url：`/product/attr/sale/list/{catelogId}`

2、请求参数：

```json
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为'//检索关键字
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"attrId": 0, //属性id
			"attrName": "string", //属性名
			"attrType": 0, //属性类型，0-销售属性，1-基本属性
			"catelogName": "手机/数码/手机", //所属分类名字
			"groupName": "主体", //所属分组名字
			"enable": 0, //是否启用
			"icon": "string", //图标
			"searchType": 0,//是否需要检索[0-不需要，1-需要]
			"showDesc": 0,//是否展示在介绍上；0-否 1-是
			"valueSelect": "string",//可选值列表[用逗号分隔]
			"valueType": 0//值类型[0-为单个值，1-可以选择多个值]
		}]
	}
}
```

分析：`销售属性`的获取和`属性详情`是一致的，因为`pms_attr`表中同时存储了`销售属性`和`基本属性`,只是利用`attr_type`字段来区分二者。由于查询`规格参数`的接口URL为`/product/attr/base/list/{catelogId}` ,查询`销售属性`的URL为`/product/attr/sale/list/{catelogId}` ,所以这里我们可以让`销售属性`和`规格参数`的查询共用一个接口和方法：在路径变量上添加`{attrType}`来让`销售属性`和`规格参数`的查询共用一个方法，最终的接口路径写成`/product/attr/{attrType}/list/{catelogId}`即可

![image-20230503101934525](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162055459.png)

`AttrController`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    
    // 销售属性URL:   /product/attr/base/list/{catelogId}
    // 规格参数URL:   /product/attr/sale/list/{catelogId}
    // 共用URL:      /product/attr/{attrType}/list/{catelogId}

    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("catelogId") Long catelogId,
                          @PathVariable("attrType") String type) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, type);
        return R.ok().put("page", page);
    }

}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);
}
```

`AttrServiceImpl`实现类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {

        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());
        if (catelogId != 0) {
            // 如果有分类条件、则将分类条件加上
            queryWrapper.eq("catelog_id", catelogId);
        }

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            // attr_id   attr_name
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        //封装分页数据
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVoList = records.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);

            if ("base".equalsIgnoreCase(type)) {
                // 1.设置分类和分组的名字
                AttrAttrgroupRelationEntity attrId = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (attrId != null) {
                    // 设置分组的名字
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    String attrGroupName = attrGroupEntity.getAttrGroupName();
                    if (attrGroupName != null) {
                        attrRespVo.setGroupName(attrGroupName);
                    }
                }
            }

            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null && categoryEntity.getName() != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(attrRespVoList);
        return pageUtils;
    }

}
```

为了规范和代码复用，在`gulimall-common`中新添`ProductConstant`来记录常量，需要区分`基本属性`和`销售属性`时即可引用该常量。在`saveAttr,getAttrInfo,updateAttr`中，涉及`属性分组`信息的地方都要做一下判断，详细的细微改动参见代码。

```java
package com.atguigu.common.constant;

public class ProductConstant {

    public enum  AttrEnum{
        ATTR_TYPE_BASE(1,"基本属性"),ATTR_TYPE_SALE(0,"销售属性");
        private int code;
        private String msg;

        AttrEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum  StatusEnum{
        NEW_SPU(0,"新建"),SPU_UP(1,"商品上架"),SPU_DOWN(2,"商品下架");
        private int code;
        private String msg;

        StatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
```

<!-- TOC --><a name="4-"></a>
## 4. 商品服务-属性分组和属性的关联

<!-- TOC --><a name="41-"></a>
### 4.1 获取属性分组关联的所有属性

每一个`属性分组`都可以关联`规格参数（也即基本属性）`，点击`关联`按钮，会列出当前分组关联的所有属性：

![image-20230503194714886](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162055322.png)

1、URL：`/product/attrgroup/{attrgroupId}/attr/relation`

2、响应数据：

```json
{
  "msg": "success",
  "code": 0,
  "data": [
    {
      "attrId": 4,
      "attrName": "aad",
      "searchType": 1,
      "valueType": 1,
      "icon": "qq",
      "valueSelect": "v;q;w",
      "attrType": 1,
      "enable": 1,
      "catelogId": 225,
      "showDesc": 1
    }
  ]
}
```

思路：在根据分组id`attr_group_id`在中间表`pms_category_brand_relation`获取对应的属性id`attr_id`，再根据`attr_id`到`pms_attr`中查询出对应的关联属性

![image-20230503180811529](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162056226.png)



`AttrGroupController`类：

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    /**
     * 根据分组id获取属性分组关联的所有基本属性（规格参数）
     * /product/attrgroup/{attrgroupId}/attr/relation
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R getAttrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data", entities);
    }
}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {
    List<AttrEntity> getRelationAttr(Long attrgroupId);
}
```

`AttrServiceImpl`类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    /**
     * 接口功能：根据分组id获取属性分组关联的所有基本属性（规格参数）
     * 思路：根据分组id`attr_group_id`在中间表`pms_category_brand_relation`获取对应的属性id`attr_id`，
     * 再根据`attr_id`到`pms_attr`中查询出对应的关联属性
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

}
```

<!-- TOC --><a name="42-"></a>
### 4.2 删除属性与分组的关联关系

1、查询到关联属性之后可以删除无用的关联，支持批量删除

![image-20230503194920159](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162056162.png)

2、URL: `/product/attrgroup/attr/relation/delete`

3、请求参数：

```json
[{"attrId":1,"attrGroupId":2}]
```

`AttrGroupController`类：

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    /**
     * 删除属性与分组的关联关系
     */
    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }
    
}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {
    void deleteRelation(AttrGroupRelationVo[] vos);
}
```

`AttrServiceImpl`实现类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void deleteRelation(AttrGroupRelationVo[] vos) {
        List<AttrAttrgroupRelationEntity> entities = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        relationDao.deleteBatchRelation(entities);
    }

}
```

在`AttrAttrgroupRelationDao`新增自定义的批量删除SQL

```java
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {
    void deleteBatchRelation(@Param("entities") List<AttrAttrgroupRelationEntity> entities);
}
```

在mapper文件`AttrAttrgroupRelationDao.xml`中自定义SQL:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimail.product.dao.AttrAttrgroupRelationDao">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimail.product.entity.AttrAttrgroupRelationEntity" id="attrAttrgroupRelationMap">
        <result property="id" column="id"/>
        <result property="attrId" column="attr_id"/>
        <result property="attrGroupId" column="attr_group_id"/>
        <result property="attrSort" column="attr_sort"/>
    </resultMap>

    <delete id="deleteBatchRelation">
        DELETE FROM `pms_attr_attrgroup_relation` WHERE
        <foreach collection="entities" item="item" separator=" OR ">
            (attr_id=#{item.attrId} AND attr_group_id=#{item.attrGroupId})
        </foreach>
    </delete>
    
</mapper>
```

<!-- TOC --><a name="43-"></a>
###  4.3 获取和当前属性分组没有关联的其他属性

1、URL：`/product/attrgroup/{attrgroupId}/noattr/relation`

2、请求参数：{

```json
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为'//检索关键字
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 3,
		"pageSize": 10,
		"totalPage": 1,
		"currPage": 1,
		"list": [{
			"attrId": 1,
			"attrName": "aaa",
			"searchType": 1,
			"valueType": 1,
			"icon": "aa",
			"valueSelect": "aa;ddd;sss;aaa2",
			"attrType": 1,
			"enable": 1,
			"catelogId": 225,
			"showDesc": 1
		}]
	}
}
```

4、接口描述：获取属性分组里面还没有关联的本分类里面的其他基本属性，方便添加新的关联。点击`新建关联`按钮，界面展示当前分组可以关联的但是却没有关联的所有属性，选择对应属性后点击`确认新增`按钮，那么分组就会和这些属性进行关联。本节接口用于获取和当前属性分组没有关联的其他属性，这些属性可以通过点击`新增关联`按钮和本分组进行关联

![image-20230504020730726](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162056993.png)

5、分析：哪些属性时是当前分组能关联的？当前分组能关联的属性必须是本分类下的，且是本分类下没有被其他分组关联的属性

`AttrGroupController`类：

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrService attrService;

    /**
     * 获取属性分组没有关联的其他属性
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R getAttrNoRelation(@RequestParam Map<String, Object> params,
                               @PathVariable("attrgroupId") Long attrgroupId) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("page", page);
    }

}
```

`AttrService`类：

```java
public interface AttrService extends IService<AttrEntity> {

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);
}
```

`AttrServiceImpl`实现类：

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取当前属性分组没有关联的其他所有属性
     */
    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        // 1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        Long catelogId = attrGroupEntity.getCatelogId();
        // 2、当前分组只能关联别的分组没有引用的属性
        // 2.1 找到当前分类下的其他分组
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<Long> collect = group.stream().map((item) -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());
        // 2.2 找到这些分类关联的属性
        List<AttrAttrgroupRelationEntity> groupId = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map((item) -> {
            return item.getAttrId();
        }).collect(Collectors.toList());


        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", catelogId)
                .eq("attr_type",ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());

        // 2.3 从当前分类的所有属性中移除这些属性
        if (attrIds != null && attrIds.size() > 0) {
            wrapper.notIn("attr_id", attrIds);
        }

        // 如果页面传过来的参数中包含模糊查询的参数或属性id，则添加查询条件
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> {
                w.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }
    
    
        @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="44-"></a>
### 4.4 添加属性与分组关联关系

1、URL:`/product/attrgroup/attr/relation`

2、请求参数：

```java
[{
  "attrGroupId": 0, //分组id
  "attrId": 0, //属性id
}]
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0
}
```



`AttrGroupController`类：

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrAttrgroupRelationService relationService;

    /**
     * 添加属性与分组关联关系
     * /product/attrgroup/attr/relation
     */
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {
        relationService.saveBatch(vos);
        return R.ok();
    }
    
}
```

`AttrAttrgroupRelationService`类：

```java
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {
    void saveBatch(List<AttrGroupRelationVo> vos);
}

```

`AttrAttrgroupRelationServiceImpl`类：

```java
@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public void saveBatch(List<AttrGroupRelationVo> vos) {
        // 要将前端传来的List<AttrGroupRelationVo> 类型转成 List<AttrAttrgroupRelationEntity>才能调用mybatisPlus自带的saveBatch
        List<AttrAttrgroupRelationEntity> collect = vos.stream().map((item) -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}
```

<!-- TOC --><a name="45-"></a>
### 4.5 属性与分组相关功能测试

在`规格参数`和`属性分组`中添加数据：

![image-20230505074819475](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162057432.png)

数据库变化：

![image-20230505073917720](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162057777.png)

属性与分组相关功能测试：

![image-20230505081524548](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162058131.png)



<!-- TOC --><a name="5-"></a>
## 5.商品服务-功能完善

<!-- TOC --><a name="51-"></a>
### 5.1 会员服务-基础配置

1.在`gulimall-gateway`中添加

```properties
spring:
  cloud:
    gateway:
      routes:
## 转发到会员服务
        - id: member_route
          uri: lb://gulimall-member
          predicates:
            - Path=/api/member/**
          filters:
            - RewritePath=/api/(?<segment>.*),/$\{segment}
```

2.将资料中编写好的前端项目代码拷贝到`src\views\modules`目录下：

![image-20230506035136733](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162058770.png)

3、新增会员等级：在用户系统的会员等级下新增会员等级

![image-20230506131942781](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162059779.png)

<!-- TOC --><a name="52-"></a>
### 5.2 获取分类关联的品牌

1、URL:`/product/categorybrandrelation/brands/list`

2、请求参数：catId  long  分类id  必填

3、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"data": [{
		"brandId": 0,
		"brandName": "string"
	}]
}
```

4.后端实现：

- `CategoryBrandRelationController`类：

```java
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 获取分类关联的品牌  /product/categorybrandrelation/brands/list
     *  1、Controller：处理请求，接受和校验数据
     *  2、Service接受controller传来的数据，进行业务处理
     *  3、Controller接受Service处理完的数据，封装页面指定的vo
     */
    @GetMapping("/brands/list")
    public R relationBrandsList(@RequestParam(value = "catId", required = true) Long catId) {
        List<BrandEntity> vos = categoryBrandRelationService.getBrandsByCatId(catId);
        List<BrandVo> collect = vos.stream().map((item) -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(item.getBrandId());
            brandVo.setBrandName(item.getName());
            return brandVo;
        }).collect(Collectors.toList());
        return R.ok().put("data", collect);
    }
}
```

- `CategoryBrandRelationService`类：

```java
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {
    List<BrandEntity> getBrandsByCatId(Long catId);
}
```

- `CategoryBrandRelationServiceImpl`类：

```java
@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Autowired
    private BrandService brandService;

    @Override
    public List<BrandEntity> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelationEntity> catelogId = categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<BrandEntity> collect = catelogId.stream().map((item) -> {
            Long brandId = item.getBrandId();
            BrandEntity byId = brandService.getById(brandId);
            return byId;
        }).collect(Collectors.toList());
        return collect;
    }

}
```

**5、前端控制台报错**

前端控制台报错 [Vue warn]: Error in mounted hook: "ReferenceError: PubSub is not defined"

原因：未安装的发布订阅插件

解决：安装插件 `npm install --save pubsub-js`

![image-20230506073047740](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162059986.png)

2. 在需要的地方导入或者在全局挂载

- （1）在需要的地方导入`import PubSub from "pubsub-js"`
- （2）在全局挂载，此方式不必在需要的地方再次导入，在src目录下找到main.js文件，添加如下内容：

![image-20230506073325938](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162100842.png)



6、测试：勾选上"选择分类"的值之后，点击选择品牌，就会查询到品牌信息

![image-20230506133439340](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162100553.png)

<!-- TOC --><a name="53-"></a>
### 5.3 获取分类下所有分组&关联属性

1、URL：`/product/attrgroup/{catelogId}/withattr`

2、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"data": [{
		"attrGroupId": 1,
		"attrGroupName": "主体",
		"sort": 0,
		"descript": "主体",
		"icon": "dd",
		"catelogId": 225,
		"attrs": [{
			"attrId": 7,
			"attrName": "入网型号",
			"searchType": 1,
			"valueType": 0,
			"icon": "xxx",
			"valueSelect": "aaa;bb",
			"attrType": 1,
			"enable": 1,
			"catelogId": 225,
			"showDesc": 1,
			"attrGroupId": null
			}, {
			"attrId": 8,
			"attrName": "上市年份",
			"searchType": 0,
			"valueType": 0,
			"icon": "xxx",
			"valueSelect": "2018;2019",
			"attrType": 1,
			"enable": 1,
			"catelogId": 225,
			"showDesc": 0,
			"attrGroupId": null
			}]
		},
		{
		"attrGroupId": 2,
		"attrGroupName": "基本信息",
		"sort": 0,
		"descript": "基本信息",
		"icon": "xx",
		"catelogId": 225,
		"attrs": [{
			"attrId": 11,
			"attrName": "机身颜色",
			"searchType": 0,
			"valueType": 0,
			"icon": "xxx",
			"valueSelect": "黑色;白色",
			"attrType": 1,
			"enable": 1,
			"catelogId": 225,
			"showDesc": 1,
			"attrGroupId": null
		}]
	}]
}
```

在`gulimall-product`商品服务中添加`AttrGroupWithAttrsVo`:

```java
package com.atguigu.gulimail.product.vo;
import com.atguigu.gulimail.product.entity.AttrEntity;
import lombok.Data;
import java.util.List;

@Data
public class AttrGroupWithAttrsVo {

    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    private List<AttrEntity> attrs;
}
```

`AttrGroupController`类:

```java
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    /**
     * 获取分类下所有分组&关联属性
     * /product/attrgroup/{catelogId}/withattr
     */
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {
        // 1.查出当前分类下的所有属性分组
        // 2.查出每个属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data", vos);
    }
}
```

`AttrGroupService`类：

```java
public interface AttrGroupService extends IService<AttrGroupEntity> {
    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
}
```

`AttrGroupServiceImpl`类：

```java
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrService attrService;

    /**
     * 根据分类id查出所有的分组以及这些组里面的属性
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        // 1、查询分组信息
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        // 2、查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group, attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());
        return collect;
    }

}
```



```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;


    /**
     * 接口功能：根据分组id获取属性分组关联的所有基本属性（规格参数）
     * 思路：根据分组id`attr_group_id`在中间表`pms_category_brand_relation`获取对应的属性id`attr_id`，
     * 再根据`attr_id`到`pms_attr`中查询出对应的关联属性
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

}
```

<!-- TOC --><a name="54-"></a>
### 5.4 新增商品

<!-- TOC --><a name="541"></a>
#### 5.4.1.接口描述

1、URL:`/product/spuinfo/save`

2、请求参数：

```json
{
	"spuName": "Apple XR",
	"spuDescription": "Apple XR",
	"catalogId": 225,
	"brandId": 12,
	"weight": 0.048,
	"publishStatus": 0,
	"decript": ["https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-22//66d30b3f-e02f-48b1-8574-e18fdf454a32_f205d9c99a2b4b01.jpg"],
	"images": ["https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-22//dcfcaec3-06d8-459b-8759-dbefc247845e_5b5e74d0978360a1.jpg", "https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-22//5b15e90a-a161-44ff-8e1c-9e2e09929803_749d8efdff062fb0.jpg"],
	"bounds": {
		"buyBounds": 500,
		"growBounds": 6000
	},
	"baseAttrs": [{
		"attrId": 7,
		"attrValues": "aaa;bb",
		"showDesc": 1
	}, {
		"attrId": 8,
		"attrValues": "2019",
		"showDesc": 0
	}],
	"skus": [{
		"attr": [{
			"attrId": 9,
			"attrName": "颜色",
			"attrValue": "黑色"
		}, {
			"attrId": 10,
			"attrName": "内存",
			"attrValue": "6GB"
		}],
		"skuName": "Apple XR 黑色 6GB",
		"price": "1999",
		"skuTitle": "Apple XR 黑色 6GB",
		"skuSubtitle": "Apple XR 黑色 6GB",
		"images": [{
			"imgUrl": "https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-22//dcfcaec3-06d8-459b-8759-dbefc247845e_5b5e74d0978360a1.jpg",
			"defaultImg": 1
			}, {
			"imgUrl": "https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-22//5b15e90a-a161-44ff-8e1c-9e2e09929803_749d8efdff062fb0.jpg",
			"defaultImg": 0
		}],
		"descar": ["黑色", "6GB"],
		"fullCount": 5,
		"discount": 0.98,
		"countStatus": 1,
		"fullPrice": 1000,
		"reducePrice": 10,
		"priceStatus": 0,
		"memberPrice": [{
			"id": 1,
			"name": "aaa",
			"price": 1998.99
		}]
		}, {
		"attr": [{
			"attrId": 9,
			"attrName": "颜色",
			"attrValue": "黑色"
		}, {
			"attrId": 10,
			"attrName": "内存",
			"attrValue": "12GB"
		}],
		"skuName": "Apple XR 黑色 12GB",
		"price": "2999",
		"skuTitle": "Apple XR 黑色 12GB",
		"skuSubtitle": "Apple XR 黑色 6GB",
		"images": [{
			"imgUrl": "",
			"defaultImg": 0
		}, {
			"imgUrl": "",
			"defaultImg": 0
		}],
		"descar": ["黑色", "12GB"],
		"fullCount": 0,
		"discount": 0,
		"countStatus": 0,
		"fullPrice": 0,
		"reducePrice": 0,
		"priceStatus": 0,
		"memberPrice": [{
			"id": 1,
			"name": "aaa",
			"price": 1998.99
		}]
	}, {
		"attr": [{
			"attrId": 9,
			"attrName": "颜色",
			"attrValue": "白色"
		}, {
			"attrId": 10,
			"attrName": "内存",
			"attrValue": "6GB"
		}],
		"skuName": "Apple XR 白色 6GB",
		"price": "1998",
		"skuTitle": "Apple XR 白色 6GB",
		"skuSubtitle": "Apple XR 黑色 6GB",
		"images": [{
			"imgUrl": "",
			"defaultImg": 0
		}, {
			"imgUrl": "",
			"defaultImg": 0
		}],
		"descar": ["白色", "6GB"],
		"fullCount": 0,
		"discount": 0,
		"countStatus": 0,
		"fullPrice": 0,
		"reducePrice": 0,
		"priceStatus": 0,
		"memberPrice": [{
			"id": 1,
			"name": "aaa",
			"price": 1998.99
		}]
	}, {
		"attr": [{
			"attrId": 9,
			"attrName": "颜色",
			"attrValue": "白色"
		}, {
			"attrId": 10,
			"attrName": "内存",
			"attrValue": "12GB"
		}],
		"skuName": "Apple XR 白色 12GB",
		"price": "2998",
		"skuTitle": "Apple XR 白色 12GB",
		"skuSubtitle": "Apple XR 黑色 6GB",
		"images": [{
			"imgUrl": "",
			"defaultImg": 0
		}, {
			"imgUrl": "",
			"defaultImg": 0
		}],
		"descar": ["白色", "12GB"],
		"fullCount": 0,
		"discount": 0,
		"countStatus": 0,
		"fullPrice": 0,
		"reducePrice": 0,
		"priceStatus": 0,
		"memberPrice": [{
			"id": 1,
			"name": "aaa",
			"price": 1998.99
		}]
	}]
}
```

3、接口描述：用户填好`基本信息`，选择对应的`规格参数`和`销售属性`，补充`sku信息`，就可以将商品信息保存发布

![image-20230510020854249](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162101587.png)

<!-- TOC --><a name="542-"></a>
####  5.4.2 新增商品接口实现

`SpuSaveVo`类：

```java
@Data
public class SpuSaveVo {

    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;
}
```

`SpuInfoController`类：

```java
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SpuSaveVo vo) {
        spuInfoService.saveSpuInfo(vo);

        return R.ok();
    }
}
```

`SpuInfoService`类：

```java
public interface SpuInfoService extends IService<SpuInfoEntity> {
    void saveSpuInfo(SpuSaveVo vo);
}
```

`SpuInfoService`接口实现类：保存商品信息

```java
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * TODO 高级部分完善
     */
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        // 1.保存spu基本信息 pms_spu_info
        SpuInfoEntity infoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo, infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);


        // 2.保存spu的描述图片  pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(String.join(",", decript));
        spuInfoDescService.saveSpuInfoDesc(descEntity);


        // 3.保存spu的图片集  pms_spu_images
        List<String> images = vo.getImages();
        spuImagesService.saveImages(infoEntity.getId(), images);


        // 4.保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(infoEntity.getId());
            return valueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(collect);


        //5）保存spu的基本信息；gulimall_sms->sms_spu_bounds

        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(infoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if (r.getCode() != 0) {
            log.error("远程保存spu积分信息失败");
        }

        // 6、保存当前spu对应的所有sku信息
        List<Skus> skus = vo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                String defautImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defautImg = image.getImgUrl();
                    }
                }
                // 6.1) sku的基本信息：pms_sku_info
                skuInfoEntity.setSkuDefaultImg(defautImg);
                skuInfoService.saveSkuInfo(skuInfoEntity);

                // 6.2) 保存sku的图片信息：pms_sku_images
                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity -> {
                    // 返回true就是需要，false就是剔除,false就会被过滤掉
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                // 6.2) 保存sku的图片信息：pms_sku_images
                skuImagesService.saveBatch(imagesEntities);
                //TODO 没有图片路径的无需保存


                // 6.3) 保存sku的销售属性信息：pms_sku_sale_attr_value
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);
                    return attrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);


                // 6.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if (r1.getCode() != 0) {
                        log.error("远程保存sku积分信息失败");
                    }
                }

            });

        }

    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

}
```

`SpuInfoDescService`实现类：保存spu详细信息

```java
@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService {
    @Override
    public void saveSpuInfoDesc(SpuInfoDescEntity descEntity) {
        this.baseMapper.insert(descEntity);
    }

}
```

`SpuImagesService`实现类：保存spu的图片集 ,对应数据库`pms_spu_images`

```java
@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public void saveImages(Long id, List<String> images) {
        if (images==null||images.size()==0){
            return;
        }else {
            List<SpuImagesEntity> collect = images.stream().map(img -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setId(id);
                spuImagesEntity.setImgUrl(img);
                return spuImagesEntity;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }

}
```

<!-- TOC --><a name="543-feigngulimall-coupon"></a>
#### 5.4.3 feign远程调用访问`gulimall-coupon`优惠卷服务接口

在新增商品时为了保证数据一致性，通过feign远程调用访问`gulimall-coupon`优惠卷服务接口，实现对数据的同步更新

1.在商品服务`gulimail-product`中创建`feign`包，新建`CouponFeignService`接口来访问优惠券服务`gulimall-coupon`，访问的接口要一一对应

```java
package com.atguigu.gulimail.product.feign;
import com.atguigu.common.to.SkuReductionTo;
import com.atguigu.common.to.SpuBoundTo;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "gulimall-coupon")
public interface CouponFeignService {

    /**
     * 1、CouponFeignService.saveSpuBounds(spuBoundTo);
     * 1）、@RequestBody将spuBoundTo这个对象转为json。
     * 2）、找到gulimall-coupon服务，给/coupon/spubound/feignSave发送请求。
     * 将上一步转的json放在请求体位置，发送请求；
     * 3）、对方服务收到请求。请求体里有json数据。
     * (@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     * 只要json数据模型是兼容的。双方服务无需使用同一个to
     * @param spuBoundTo
     * @return
     */
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    /**
     *
     * @param skuReductionTo
     */
    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
```

2.在商品服务`gulimail-produc`的启动类上添加`@EnableFeignClients`来开启`feign`调用功能

```java
@EnableFeignClients(basePackages = "com.atguigu.gulimail.product.feign")
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimail.product.dao")
@SpringBootApplication
public class GulimailProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }
}
```

3.优惠券服务`gulimall-coupon`的`SpuBoundsController `类：

```java
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {
    @Autowired
    private SpuBoundsService spuBoundsService;
    
    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SpuBoundsEntity spuBounds) {
        spuBoundsService.save(spuBounds);
        return R.ok();
    }

}
```

4.优惠券服务`gulimall-coupon`的`SkuFullReductionController`类：

```java
@RestController
@RequestMapping("/coupon/skufullreduction")
public class SkuFullReductionController {
    @Autowired
    private SkuFullReductionService skuFullReductionService;
    /**
     * 列表
     */
    @PostMapping("/saveinfo")
    public R saveInfo(@RequestBody SkuReductionTo skuReductionTo) {
        skuFullReductionService.saveSkuRedution(skuReductionTo);
        return R.ok();
    }
}
```

5.优惠券服务`gulimall-coupon`的`SkuFullReductionService`类：

```java
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {
    void saveSkuRedution(SkuReductionTo skuReductionTo);
}
```

6.优惠券服务`gulimall-coupon`的`SkuFullReductionServiceImpl`类：

```java
@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;

    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuRedution(SkuReductionTo reductionTo) {
        // 1.保存满减打折，会员价
        // sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
        //sms_sku_ladder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(reductionTo.getSkuId());
        skuLadderEntity.setFullCount(reductionTo.getFullCount());
        skuLadderEntity.setDiscount(reductionTo.getDiscount());
        skuLadderEntity.setAddOther(reductionTo.getCountStatus());
        if (reductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

        //2、sms_sku_full_reduction
        SkuFullReductionEntity reductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(reductionTo, reductionEntity);
        if (reductionEntity.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
            this.save(reductionEntity);
        }

        //3、sms_member_price
        List<MemberPrice> memberPrice = reductionTo.getMemberPrice();

        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
            MemberPriceEntity priceEntity = new MemberPriceEntity();
            priceEntity.setSkuId(reductionTo.getSkuId());
            priceEntity.setMemberLevelId(item.getId());
            priceEntity.setMemberLevelName(item.getName());
            priceEntity.setMemberPrice(item.getPrice());
            priceEntity.setAddOther(1);
            return priceEntity;
        }).filter(item->{
            return item.getMemberPrice().compareTo(new BigDecimal("0")) == 1;
        }).collect(Collectors.toList());

        memberPriceService.saveBatch(collect);

    }

}
```

<!-- TOC --><a name="55-spu"></a>
### 5.5 spu检索

1、URL:`/product/spuinfo/list`

2、请求参数：

```json
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为',//检索关键字
   catelogId: 6,//三级分类id
   brandId: 1,//品牌id 
   status: 0,//商品状态
}
```

3、响应数据

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"brandId": 0, //品牌id
			"brandName": "品牌名字",
			"catalogId": 0, //分类id
			"catalogName": "分类名字",
			"createTime": "2019-11-13T16:07:32.877Z", //创建时间
			"id": 0, //商品id
			"publishStatus": 0, //发布状态
			"spuDescription": "string", //商品描述
			"spuName": "string", //商品名字
			"updateTime": "2019-11-13T16:07:32.877Z", //更新时间
			"weight": 0 //重量
		}]
	}
}
```

返回的时间没有经过格式化：

![image-20230510230354694](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162102394.png)



在`application.yml`中添加日期格式化配置：

```yaml
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
```

`SpuInfoController`类：

```java
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

}
```

`SpuInfoService`类：

```java
public interface SpuInfoService extends IService<SpuInfoEntity> {
    PageUtils queryPageByCondition(Map<String, Object> params);
}
```

`SpuInfoServiceImpl`类：

```java
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        /**
         * status: 2
         * key:
         * brandId: 9
         * catelogId: 225
         */
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        // status=1 and (id=1 or spu_name like xxx)
        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("publish_status", status);
        }

        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            wrapper.eq("brand_id", brandId);
        }

        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            wrapper.eq("catalog_id", catelogId);
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="56-sku"></a>
### 5.6 sku检索

1、URL:`/product/skuinfo/list`

2、请求参数：

```json
{
page: 1,//当前页码
limit: 10,//每页记录数
sidx: 'id',//排序字段
order: 'asc/desc',//排序方式
key: '华为',//检索关键字
catelogId: 0,
brandId: 0,
min: 0,
max: 0
}
```

3、响应数据

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 26,
		"pageSize": 10,
		"totalPage": 3,
		"currPage": 1,
		"list": [{
			"skuId": 1,
			"spuId": 11,
			"skuName": "华为 HUAWEI Mate 30 Pro 星河银 8GB+256GB",
			"skuDesc": null,
			"catalogId": 225,
			"brandId": 9,
			"skuDefaultImg": "https://gulimall-hello.oss-cn-beijing.aliyuncs.com/2019-11-26/60e65a44-f943-4ed5-87c8-8cf90f403018_d511faab82abb34b.jpg",
			"skuTitle": "华为 HUAWEI Mate 30 Pro 星河银 8GB+256GB麒麟990旗舰芯片OLED环幕屏双4000万徕卡电影四摄4G全网通手机",
			"skuSubtitle": "【现货抢购！享白条12期免息！】麒麟990，OLED环幕屏双4000万徕卡电影四摄；Mate30系列享12期免息》",
			"price": 6299.0000,
			"saleCount": 0
		}]
	}
}
```

3、接口描述：通过输入的内容进行检索

![image-20230510223843413](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162102977.png)



`gulimall-product`商品服务:  `SkuInfoController`类

```java
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuInfoService.queryPageByCondition(params);
        return R.ok().put("page", page);
    }

}
```

`SkuInfoService`类：

```java
public interface SkuInfoService extends IService<SkuInfoEntity> {
    PageUtils queryPageByCondition(Map<String, Object> params);
}
```

`SkuInfoServiceImpl`类：

```java
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        /**
         * key:
         * catelogId: 0
         * brandId: 0
         * min: 0
         * max: 0
         */
        // 拼装模糊查询条件key ： 根据sku_id 查询或者 sku_name 查询
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("sku_id", key).or().like("sku_name", key);
            });
        }

        // 前端默认传0，所以要判断是否是0，为0就不拼接该查询条件
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            queryWrapper.eq("catalog_id", catelogId);
        }

        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(catelogId)) {
            queryWrapper.eq("brand_id", brandId);
        }

        String min = (String) params.get("min");
        if (!StringUtils.isEmpty(min)) {
            queryWrapper.ge("price", min);
        }

        // 用户可能会填一些非数字的值，如果填非数值就捕获异常，让
        String max = (String) params.get("max");
        if (!StringUtils.isEmpty(max)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(max);

                if (bigDecimal.compareTo(new BigDecimal("0")) == 1) {
                    queryWrapper.le("price", max);
                }
            } catch (Exception e) {

            }
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="6-"></a>
## 6. 库存服务

<!-- TOC --><a name="61-"></a>
### 6.1 库存服务-基础配置

1.在`gulimall-gateway`网关服务添加如下配置，加请求路由到仓库服务`gulimall-ware`:

```yaml
spring:
  cloud:
    gateway:
      routes:
# 转发到仓库服务
        - id: ware_route
          uri: lb://gulimall-ware
          predicates:
            - Path=/api/ware/**
          filters:
            - RewritePath=/api/(?<segment>.*),/$\{segment}
```

2.将仓库服务注册到nacos,在仓库服务的启动类上添加注解：

```java
@EnableTransactionManagement
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimail.ware.dao")
@SpringBootApplication
public class GulimailWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailWareApplication.class, args);
    }

}
```

<!-- TOC --><a name="62-"></a>
### 6.2 获取库存列表

1、URL:`/ware/wareinfo/list`

2、请求参数：

```json
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为'//检索关键字
}
```

3、响应数据：

```java
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"id": 2,
			"name": "aa",
			"address": "bbb",
			"areacode": "124"
		}]
	}
}
```

3、接口描述：通过id或者仓库名\仓库地址\区域编码来获取仓库详情列表

![image-20230511020800062](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162103227.png)

`WareInfoController`类：

```java
@RestController
@RequestMapping("ware/wareinfo")
public class WareInfoController {
    @Autowired
    private WareInfoService wareInfoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareInfoService.queryPage(params);
        return R.ok().put("page", page);
    }
}
```

`WareInfoService`类：

```java
public interface WareInfoService extends IService<WareInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
```

`WareInfoServiceImpl`接口实现类：

```java
@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key)
                    .or().like("name", key)
                    .or().like("address", key)
                    .or().like("areacode", key);
        }
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="63-"></a>
### 6.3 库存服务：查询商品库存服务

1、URL:`/ware/waresku/list`

2、请求参数：

```java
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   wareId: 123,//仓库id
   skuId: 123//商品id
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"id": 1,
			"skuId": 1,
			"wareId": 1,
			"stock": 1,
			"skuName": "dd",
			"stockLocked": 1
		}]
	}
}
```

4、接口描述：按照仓库名、sku或者这两者结合起来，获取某个sku在某个仓库的库存量等信息

![image-20230511034806464](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162103407.png)



`WareSkuController`类：

```java
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }

}
```

`WareSkuService`类：

```java
public interface WareSkuService extends IService<WareSkuEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
```

`WareSkuServiceImpl`类：

```java
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * skuId: 1
         * wareId: 2
         */
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }

        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }


        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="64-"></a>
### 6.4 查询采购需求

1、URL:`/ware/purchasedetail/list`

2、请求参数：

```java
{
   page: 1,//当前页码
   limit: 10,//每页记录数
   sidx: 'id',//排序字段
   order: 'asc/desc',//排序方式
   key: '华为',//检索关键字
   status: 0,//状态    
   wareId: 1,//仓库id
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"id": 2,
			"purchaseId": 1,
			"skuId": 1,
			"skuNum": 2,
			"skuPrice": 22.0000,
			"wareId": 1,
			"status": 1
		}]
	}
}
```

4、接口描述：查询采购需求详情

![image-20230511040609093](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162104258.png)



`PurchaseDetailController`类：

```java
@RestController
@RequestMapping("ware/purchasedetail")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseDetailService.queryPage(params);

        return R.ok().put("page", page);
    }
}
```

`PurchaseDetailService`类：

```java
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
```

`PurchaseDetailServiceImpl`类：

```java
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        /**
         * status: 0,//状态
         * wareId: 1,//仓库id
         */
        QueryWrapper<PurchaseDetailEntity> queryWrapper = new QueryWrapper<PurchaseDetailEntity>();

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            //purchase_id  sku_id
            queryWrapper.and(w -> {
                w.eq("purchase_id", key).or().eq("sku_id", key);
            });
        }

        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            //purchase_id  sku_id
            queryWrapper.eq("status", status);
        }

        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            //purchase_id  sku_id
            queryWrapper.eq("ware_id", wareId);
        }

        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="65-"></a>
### 6.5 查询未领取的采购单

1、URL:`/ware/purchase/unreceive/list`

2、响应数据：

```java
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": [{
			"id": 1,
			"assigneeId": 1,
			"assigneeName": "aa",
			"phone": "123",
			"priority": 1,
			"status": 1,
			"wareId": 1,
			"amount": 22.0000,
			"createTime": "2019-12-12",
			"updateTime": "2019-12-12"
		}]
	}
}
```

`PurchaseController`类：

```java
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    /**
     * 查询未领取的采购单列表
     * /ware/purchase/unreceive/list
     */
    @RequestMapping("/unreceive/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPageUnreceivePurchase(params);

        return R.ok().put("page", page);
    }
}
```

`PurchaseService`类：

```java
public interface PurchaseService extends IService<PurchaseEntity> {
    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);
}
```

`PurchaseServiceImpl`类：

```java
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {
    @Override
    public PageUtils queryPageUnreceivePurchase(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>().eq("status",0).or().eq("status",1)
        );

        return new PageUtils(page);
    }

}
```

<!-- TOC --><a name="66-"></a>
### 6.6 合并采购需求

采购流程：人工创建或者系统创建的采购需求可以通过人工或系统，合并成采购单，采购单可以分配给固定的采购人员进行后续采购和入库

![image-20230511041517894](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162104306.png)

新增采购需求后，勾选采购需求，点击`批量操作`按钮下的`合并整单`就可以将采购需求合并为采购单

![image-20230511213801178](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162105041.png)

在`管理员列表`下创建管理员

![image-20230511224309109](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162105017.png)

在`采购单`模块点击`分配`按钮，可以将合并好的采购单分配给具体的系统管理员：

![image-20230511224423091](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162105743.png)



1、URL:`/ware/purchase/merge`

2、请求参数：

```json
{
  purchaseId: 1, //整单id
  items:[1,2,3,4] //合并项集合
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0
}
```

` MergeVo`类：

```
@Data
public class MergeVo {

   private Long purchaseId; //整单id
   private List<Long> items;//[1,2,3,4] //合并项集合
}
```

`PurchaseController`类：

```java
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    /**
     * 合并采购单需求
     * ///ware/purchase/merge
     */
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVo mergeVo) {
        purchaseService.mergePurchase(mergeVo);
        return R.ok();
    }
}
```

`PurchaseService`类：

```java
public interface PurchaseService extends IService<PurchaseEntity> {
    void mergePurchase(MergeVo mergeVo);
}
```

`PurchaseServiceImpl`类：

```java
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    private PurchaseDetailService detailService;

    @Transactional
    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null) {
            //1、新建一个
            PurchaseEntity purchaseEntity = new PurchaseEntity();

            purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }

        //TODO 确认采购单状态是0,1才可以合并

        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> collect = items.stream().map(i -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();

            detailEntity.setId(i);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return detailEntity;
        }).collect(Collectors.toList());

        detailService.updateBatchById(collect);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

}
```

在`gulimall-common`公共包中添加仓库服务要用到的常量:

```java
package com.atguigu.common.constant;
public class WareConstant {
    public enum  PurchaseStatusEnum{
        CREATED(0,"新建"),ASSIGNED(1,"已分配"),
        RECEIVE(2,"已领取"),FINISH(3,"已完成"),
        HASERROR(4,"有异常");
        private int code;
        private String msg;

        PurchaseStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum  PurchaseDetailStatusEnum{
        CREATED(0,"新建"),ASSIGNED(1,"已分配"),
        BUYING(2,"正在采购"),FINISH(3,"已完成"),
        HASERROR(4,"采购失败");
        private int code;
        private String msg;

        PurchaseDetailStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
```

选择要合并的采购需求，然后合并到整单

![image-20230512021238374](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162105033.png)



如果不选择整单id，则自动创建新的采购单

![image-20230512021303623](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162106649.png)

测试成功

![image-20230512021330391](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162106809.png)



<!-- TOC --><a name="67-"></a>
### 6.7 领取采购单

1、URL:`/ware/purchase/received`

2、请求参数：

```json
[1,2,3,4]//采购单id
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0
}
```

4、接口描述：采购单分配给了采购人员，采购人员在手机端领取采购单，此时的采购单应该为`新建`或`已分配`状态，在采购人员领取后`采购单`的状态变为`已领取`，`采购需求`的状态变为`正在采购`

`PurchaseController`类:

```java
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    /**
     * 领取采购单
     * @return
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids){
        purchaseService.received(ids);
        return R.ok();
    }

}
```

`PurchaseService`类：

```java
public interface PurchaseService extends IService<PurchaseEntity> {
    void received(List<Long> ids);
}
```

`PurchaseServiceImpl`类：

```java
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {
    @Autowired
    private PurchaseDetailService detailService;
    /**
     * 领取采购单
     * @param ids 采购单id
     */
    @Override
    public void received(List<Long> ids) {
        //1、确认当前采购单是新建或者已分配状态
        List<PurchaseEntity> collect = ids.stream().map(id -> {
            PurchaseEntity byId = this.getById(id);
            return byId;
        }).filter(item -> {
            if (item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                    item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                return true;
            }
            return false;
        }).map(item -> {
            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
            item.setUpdateTime(new Date());
            return item;
        }).collect(Collectors.toList());

        //2、改变采购单的状态
        this.updateBatchById(collect);


        //3、改变采购项的状态
        collect.forEach((item) -> {
            List<PurchaseDetailEntity> entities = detailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetailEntity> detailEntities = entities.stream().map(entity -> {
                PurchaseDetailEntity entity1 = new PurchaseDetailEntity();
                entity1.setId(entity.getId());
                entity1.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return entity1;
            }).collect(Collectors.toList());
            detailService.updateBatchById(detailEntities);
        });
    }

}
```

`PurchaseDetailService`类：

```java
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {
    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}
```

`PurchaseDetailServiceImpl`类：

```java
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {
    @Override
    public List<PurchaseDetailEntity> listDetailByPurchaseId(Long id) {

        List<PurchaseDetailEntity> purchaseId = this.list(new QueryWrapper<PurchaseDetailEntity>().eq("purchase_id", id));

        return purchaseId;
    }
}
```

测试：真实场景中采购员通过手机端发送请求领取采购单，此处路由postman发送请求进行模拟，向`http://localhost:88/api/ware/purchase/received`发送请求，json数组为:   `[3]`

![image-20230512013533914](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162106626.png)

成功请求后id为3的采购单下的采购需求的状态全部由`已分配`变成`正在采购`

![image-20230512014026183](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162107315.png)



<!-- TOC --><a name="68-"></a>
### 6.8 完成采购

1、URL:`/ware/purchase/done`

2、请求参数：

```json
{
   id: 123,//采购单id
   items: [{itemId:1,status:4,reason:""}]//完成/失败的需求详情
}
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0
}
```

4、接口描述：采购人员领取采购单后完成采购，接着在手机端点击完成，对应的采购需求及采购单中的采购数量、采购的商品就被加到库存

`PurchaseController`类：

```java
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    /**
     * 完成采购
     * ///ware/purchase/done
     */
    @PostMapping("/done")
    public R finish(@RequestBody PurchaseDoneVo doneVo) {

        purchaseService.done(doneVo);
        return R.ok();
    }
}
```

`PurchaseService`类：

```java
public interface PurchaseService extends IService<PurchaseEntity> {
    void done(PurchaseDoneVo doneVo);
}
```

`PurchaseServiceImpl`类：

```java
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {
    @Autowired
    private PurchaseDetailService detailService;
    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 完成采购
     * @param doneVo
     */
    @Transactional
    @Override
    public void done(PurchaseDoneVo doneVo) {

        Long id = doneVo.getId();

        //1、改变采购项的状态
        Boolean flag = true;
        List<PurchaseItemDoneVo> items = doneVo.getItems();

        List<PurchaseDetailEntity> updates = new ArrayList<>();
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            if (item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
                flag = false;
                detailEntity.setStatus(item.getStatus());
            } else {
                detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                ////2、将成功采购的进行入库
                PurchaseDetailEntity entity = detailService.getById(item.getItemId());
                wareSkuService.addStock(entity.getSkuId(), entity.getWareId(), entity.getSkuNum());

            }
            detailEntity.setId(item.getItemId());
            updates.add(detailEntity);
        }

        detailService.updateBatchById(updates);

        //3、改变采购单状态
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(flag ? WareConstant.PurchaseStatusEnum.FINISH.getCode() : WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

}
```

`WareSkuService`类：

```java
public interface WareSkuService extends IService<WareSkuEntity> {
    void addStock(Long skuId, Long wareId, Integer skuNum);
}
```

`WareSkuServiceImpl`类： 将采购成功的商品进行入库

```java
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareSkuDao wareSkuDao;

    @Autowired
    private ProductFeignService productFeignService;
    /**
     * 将采购成功的商品进行入库
     * @param skuId  采购项的id
     * @param wareId
     * @param skuNum
     */
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断:如果还没有这个库存记录就新增，否则才修改
        List<WareSkuEntity> entities = wareSkuDao.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (entities == null || entities.size() == 0) {
            WareSkuEntity skuEntity = new WareSkuEntity();
            skuEntity.setSkuId(skuId);
            skuEntity.setStock(skuNum);
            skuEntity.setWareId(wareId);
            skuEntity.setStockLocked(0);
            // 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            // 还可以用什么办法让异常出现以后不回滚？高级
            try {
                R info = productFeignService.info(skuId);
                Map<String, Object> data = (Map<String, Object>) info.get("skuInfo");

                if (info.getCode() == 0) {
                    skuEntity.setSkuName((String) data.get("skuName"));
                }
            } catch (Exception e) {

            }
            wareSkuDao.insert(skuEntity);
        } else {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        }

    }
}
```

`ProductFeignService`类：远程调用`gulimall-product`服务获取信息

```java
package com.atguigu.gulimail.ware.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-product")
public interface ProductFeignService {
    /**
     *  /product/skuinfo/info/{skuId}
     * 此处获取信息的接口有两种写法：
     *   1)、让所有请求过网关；
     *          1、@FeignClient("gulimall-gateway")：给gulimall-gateway所在的机器发请求
     *          2、/api/product/skuinfo/info/{skuId}
     *   2）、直接让后台指定服务处理
     *          1、@FeignClient("gulimall-product")
     *          2、/product/skuinfo/info/{skuId}
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}
```

`WareSkuDao`类：

```java
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);
}
```

`WareSkuDao.xml`文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimail.ware.dao.WareSkuDao">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimail.ware.entity.WareSkuEntity" id="wareSkuMap">
        <result property="id" column="id"/>
        <result property="skuId" column="sku_id"/>
        <result property="wareId" column="ware_id"/>
        <result property="stock" column="stock"/>
        <result property="skuName" column="sku_name"/>
        <result property="stockLocked" column="stock_locked"/>
    </resultMap>
    <update id="addStock">
        UPDATE `wms_ware_sku`
        SET stock=stock + #{skuNum}
        WHERE sku_id = #{skuId}
          AND ware_id = #{wareId}
    </update>
</mapper>
```

**5.测试:**       当前状态—>    id为3的采购单已经分配给采购人员进行采购，该采购单下的采购需求id分别为4、5，状态显示为正在采购

![image-20230512220914755](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162107090.png)





![image-20230512221036675](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162108257.png)



模拟手机端向后台发送如下请求完成采购：请求参数表示要完成id为3的采购单，该采购单下存在id为4\5的采购需求，其采购中需求4采购成功，需求5由于无货导致采购异常。请求URL路径：`http://localhost:88/api/ware/purchase/done`   ,  请求数据：

```json
{
   "id": 3,
   "items": 
   [
       {
           "itemId":4,
           "status":3,
           "reason":""
           },
           {
           "itemId":5,
           "status":4,
           "reason":"无货"
           }
    ]
}
```





![image-20230512220846998](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162108666.png)

验证结果：查询`采购单`和`采购需求`，结果符合业务逻辑

![image-20230512221151919](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162109098.png)



![image-20230512221211325](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305162109665.png)



<!-- TOC --><a name="69-spu-spu"></a>
### 6.9 商品服务-spu管理-获取spu规格

1、URL:`/product/attr/base/listforspu/{spuId}`

2、响应数据：

```json
{
	"msg": "success",
	"code": 0,
	"data": [{
		"id": 43,
		"spuId": 11,
		"attrId": 7,
		"attrName": "入网型号",
		"attrValue": "LIO-AL00",
		"attrSort": null,
		"quickShow": 1
	}]
}
```

`AttrController`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private ProductAttrValueService productAttrValueService;

    /**
     * 获取spu规格
     * /product/attr/base/listforspu/{spuId}
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId) {
        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrlistforspu(spuId);
        return R.ok().put("data", entities);
    }

}
```

`ProductAttrValueService`类：

```java
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {
    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);
}
```

`ProductAttrValueServiceImpl`类：

```java
@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId) {
        List<ProductAttrValueEntity> entities = this.baseMapper.selectList(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        return entities;
    }

}
```

错误处理：如果点击`规格`按钮后出现404，则需要在`gulimall-admin`数据库的 `sys_menu`表中加入如下sql语句：

```sql
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('76','37','规格维护','product/attrupdate',NULL,'1','log','0');
```

<!-- TOC --><a name="610-"></a>
### 6.10 商品服务补充-批量修改商品规格

1、URL:`/product/attr/update/{spuId}`

2、请求参数：

```json
[{
	"attrId": 7,
	"attrName": "入网型号",
	"attrValue": "LIO-AL00",
	"quickShow": 1
}, {
	"attrId": 14,
	"attrName": "机身材质工艺",
	"attrValue": "玻璃",
	"quickShow": 0
}, {
	"attrId": 16,
	"attrName": "CPU型号",
	"attrValue": "HUAWEI Kirin 980",
	"quickShow": 1
}]
```

3、响应数据：

```json
{
	"msg": "success",
	"code": 0
}
```

`ProductAttrValueService`类：

```java
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private ProductAttrValueService productAttrValueService;
    /**
     * 批量修改商品规格
     * /product/attr/update/{spuId}
     * @return
     */
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }
}
```

`ProductAttrValueService`类：

```java
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {
    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);
}
```

`ProductAttrValueServiceImpl`类：

```java
@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {
    @Transactional
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities) {
        //1、删除这个spuId之前对应的所有属性
        this.baseMapper.delete(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));

        List<ProductAttrValueEntity> collect = entities.stream().map(item -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }
}
```

<!-- TOC --><a name="7"></a>
## 7.分布式基础篇总结

```
1、分布式基础概念
      微服务、注册中心、配置中心、远程调用、Feign、网关
2、基础开发
      SpringBoot2.0、SpringCloud、 Mybatis-Plus、Vue组件化、阿里云对象存储
3、环境
      Vagrant、Linux、Docker、MySQL、Redis、逆向工程&人人开源
4、开发规范
      数据校验JSR303、全局异常处理、全局统一返回、全局跨域处理
      枚举状态、业务状态码、Vo与TO与PO划分、逻辑删除 、Lombok: @Data、@Slf4j
```

<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [1.简介](#1)
- [2. 基本概念：](#2-)
- [3.安装elasticsearch\kibana](#3elasticsearchkibana)
- [4.初步检索](#4)
  * [4.1    _cat](#41-_cat)
  * [4.2 索引一个文档（相当于数据库保存\sava记录)](#42-sava)
  * [4.3 查询文档](#43-)
  * [4.4 更新文档 ](#44-)
  * [4.5 删除文档&索引](#45-)
  * [4.6 bulk批量API](#46-bulkapi)
- [5. 进阶检索](#5-)
  * [5.1 SearchAPI](#51-searchapi)
  * [5.2 Query DSL](#52-query-dsl)
      - [1. 基本语法格式](#1-)
      - [2. 只返回部分字段](#2--1)
      - [3. match[ 匹配查询 ]](#3-match-)
      - [4. match_phrase [ 短语匹配 ]](#4-match_phrase-)
      - [5. multi_match [多字段匹配]](#5-multi_match-)
      - [6. bool [复合查询]](#6-bool-)
      - [7. filter[结果过滤]](#7-filter)
      - [8. term](#8-term)
      - [9. aggregations（执行聚合)](#9-aggregations)
  * [5.3 Mapping 映射](#53-mapping-)
      - [1.字段类型](#1-1)
      - [2.映射Mapping](#2mapping)
      - [3.新版本改变](#3)
  * [5.4 mapping 新版本改变](#54-mapping-)
      - [1.创建索引并指定映射](#1-2)
      - [2.添加新的字段映射](#2)
      - [3.更新映射](#3-1)
      - [4.数据迁移](#4-1)
  * [5.5 分词](#55-)
      - [1.分词简介](#1-3)
      - [2.安装ik分词器](#2ik)
      - [3.自定义词库](#3-2)
- [6. Elasticsearch-Rest-Client](#6-elasticsearch-rest-client)
  * [6.1 Elasticsearch-Rest-Client简介](#61-elasticsearch-rest-client)
  * [6.2 springboot整合elasticsearch-rest-high-level-client](#62-springbootelasticsearch-rest-high-level-client)
  * [6.3 保存或更新数据到es](#63-es)
  * [6.4 从es中检索数据](#64-es)
- [7.商品上架](#7)
  * [7.1 商品上架-分析sku\spu在es中如何存储](#71-skuspues)
  * [7.2 nested数据类型-数组的扁平化处理](#72-nested-)
  * [7.3 商品上架实现](#73-)
      - [1.es数据模型](#1es)
      - [2.查询SkuInfo来es数据模型](#2skuinfoes)
      - [3.查询可检索属性](#3-3)
      - [4.挑出可检索的属性](#4-2)
      - [5.远程调用判断库存系统是否有库存](#5)
      - [6.将数据发送给ES进行保存](#6es)
      - [7.修改当前spu的状态](#7spu)
      - [8.填充ES属性模型并发送给ES保存](#8eses)

- [8.商品服务页面](#8)
  * [8.1 项目的微服务架构](#81-)
  * [8.2 整合thymeleaf](#82-thymeleaf)
  * [8.3 渲染一级分类数据](#83-)
  * [8.4 渲染二级、三级分类数据](#84-)
- [9.搭建域名访问环境](#9)
  * [9.1 反向代理配置](#91-)
    - [1.正向代理与反向代理](#1)
    - [2.Nginx+Windows搭建域名访问环境](#2nginxwindows)
    - [3.nginx配置文件](#3nginx)
    - [4.配置nginx将请求转发到网关](#4nginx)
    - [5.当前域名映射的效果](#5)

<!-- TOC end -->

<!-- TOC --><a name="1"></a>
## 1.简介

- `elasticsearch`官方介绍：`https://www.elastic.co/cn/what-is/elasticsearch`
- 全文搜索属于最常见的需求，开源的`Elasticsearch`是目前全文搜索引擎的首选
- `elasticsearch`可以快速地储存、搜索和分析海量数据。维基百科、Stack Overflow、Github 都采用它
- Elastic的底层是开源库Lucene. 但是，你没法直接用Lucene, 必须自己写代码去调用它的
  接口
- Elastic 是Lucene 的封装，提供了RESTAPI 的操作接口，开箱即用。
- REST API:天然的跨平台
- 官方文档：`https://www.elastic.co/guide/index.html`
- 官方文档: `https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html`
- 官方中文:`https://www.elastic.co/guide/cn/elasticsearch/guide/current/foreword_id.html`
- 社区中文:
  - `https://es.xiaoleilu.com/index.html`
  - `http://doc.codingdict.com/elasticsearch/0/`

<!-- TOC --><a name="2-"></a>
## 2. 基本概念：

1、Index (索引)

- 动词，相当于MySQL中的insert   ->   index命令可以插入数据
- 名词，相当于MySQL中的Database   ->  index类似于一个数据库

2、Type (类型)

- 在Index (索引)中，可以定义一个或多个类型
- 类似于MySQL中的Table：每一种类型的数据放在一起 
- Type (类型)类似于一个数据库表

3、Document (文档)

- 保存在某个索引(Index) 下，某种类型(Type) 的一个数据(Document) 
- 文档是JSON格式的，Document就像是MySQL中的某个Table里面的内容

4、`elasticsearch`概念图(对比mySQL)：Index对应数据库、Type对应数据表、Document对应一条条数据

![image-20230513101530897](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240306137.png)

5、`ElasticSearch`概念-倒排索引

- `ElasticSearch`将存入的数据进行分词。分词：将整句分拆为单词
- 例如将如下记录保存到`ElasticSearch`
  - 1-红海行动
  - 2-探索红海行动
  - 3-红海特别行动
  - 4-红海记录篇
  - 5-特工红海特别探索
- 倒排索引机制会将记录存为如下形式：

| 词     | 记录      |
| ------ | --------- |
| 红海   | 1,2,3,4,5 |
| 行动   | 1,2,3     |
| 探索   | 2,5       |
| 特别   | 3,5       |
| 记录篇 | 4         |
| 特工   | 5         |

- 检索时根据`相关性得分`获取所需数据：


```
检索:
     1)、红海特工行动?
     2)、红海行动?
相关性得分:
```

<!-- TOC --><a name="3elasticsearchkibana"></a>
## 3.安装elasticsearch\kibana

1、docker下载镜像文件（`elasticsearch`: 存储和检索数据  ，`kibana`: 可视化检索数据）

```shell
docker pull elasticsearch:7.4.2 
docker pull kibana:7.4.2
```

2、创建实例 `ElasticSearch`

```sh
#先将es的数据和配置与需要映射的文件夹创建好
mkdir -p /mydata/elasticsearch/config
mkdir -p /mydata/elasticsearch/data
#配置es地址
echo "http.host: 0.0.0.0" >> /mydata/elasticsearch/config/elasticsearch.yml
#保证权限
chmod -R 777 /mydata/elasticsearch/ 
#创建并启动实例
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \-e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
-v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \
-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.4.2
```

访问虚拟机的9200端口：`http://192.168.56.10:9200/` ，返回如下信息，说明`elasticsearch`安装成功：

```json
{
  "name" : "78955d1dfc1e",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "iPPZkEmDRBKpHBAXDOl0TA",
  "version" : {
    "number" : "7.4.2",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "2f90bbf7b93631e52bafb59b3b049cb44ec25e96",
    "build_date" : "2019-10-28T20:40:44.881551Z",
    "build_snapshot" : false,
    "lucene_version" : "8.2.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

3、创建kibana实例

```sh
#192.168.56.10 一定要改成自己的虚拟机地址
docker run --name kibana -e ELASTICSEARCH_HOSTS=http://192.168.56.10:9200 -p 5601:5601 \
-d kibana:7.4.2
```

更改kibana的配置：

```
docker exec -it kibana /bin/bash
vi /opt/kibana/config/kibana.yml
```

将kibana.yml配置更改如下：

```sh
server.name: kibana
server.host: "0"
elasticsearch.hosts: [ "http://192.168.56.10:9200" ]
xpack.monitoring.ui.container.elasticsearch.enabled: true
elasticsearch.requestTimeout: 90000
```



<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240308408.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240308783.png" > <b>2</b></td>
</tr>
</table>



```sh
# 让elasticsearch开机重启
docker update elasticsearch --restart=always
```

<!-- TOC --><a name="4"></a>
## 4.初步检索

<!-- TOC --><a name="41-_cat"></a>
###  4.1    _cat

- `GET /_cat/nodes`:  查看所有节点
- `GET/_cat/health`:  查看es 健康状况
- `GET/_cat/master`:  查看主节点
- `GET/_cat/indices`: 查看所有索引 （相当于数据库 `show databases;`）

<!-- TOC --><a name="42-sava"></a>
### 4.2 索引一个文档（相当于数据库保存\sava记录)

- 保存一个数据，保存在哪个索引的哪个类型下，指定用哪个唯一标识

- `PUT customer/external/1`   :    在customer索引下的 external类型下保存1号数据

- PUT和 POST都可以

  - PUT可以新增可以修改。PUT必须指定 id;由于PUT需要指定id，我们一般都用来做修改操作,不指定id会报错

  - POST新增。如果不指定通，会自动生成id。指定 id就会修改这个数据，并新增版本号

- 用`PUT`方式请求`http://192.168.56.10:9200/customer/external/1`  , 请求数据`{"name": "Zhang shan"}`,响应如下：

```json
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 1,
    "result": "created",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 0,
    "_primary_term": 1
}
```

- 第二次发送上面的请求，响应如下：


```json
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 2,
    "result": "updated",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 1,
    "_primary_term": 1
}
```

- `POST`方式访问`http://192.168.56.10:9200/customer/external/1`  （带上id），并携带请求数据`{"name": "Zhang shan"}`,数据被更新：

```json
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 5,
    "result": "updated",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 4,
    "_primary_term": 2
}
```

- `POST`方式访问`http://192.168.56.10:9200/customer/external`  （不带id），并携带请求数据`{"name": "Zhang shan"}`,数据被新增：

```json
{
    "_index": "customer",
    "_type": "external",
    "_id": "JRojFogBnGs4-_U2Ynpk",
    "_version": 1,
    "result": "created",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 5,
    "_primary_term": 2
}
```

<!-- TOC --><a name="43-"></a>
### 4.3 查询文档

| 请求类型 | URL(前缀`http://192.168.56.10:9200`) |
| :------: | :----------------------------------: |
|   GET    |        `/customer/external/1`        |

```json
# 响应数据：
{
    "_index": "customer",  //在哪个索引
    "_type": "external",   //在哪个类型
    "_id": "1",            //记录id
    "_version": 5,         //版本号
    "_seq_no": 4,          //并发控制空段，每次更新就会+1，用来做乐观锁
    "_primary_term": 2,    //同上，主分片重新分配，如重启，就会变化
    "found": true,
    "_source": {           //真正的内容
        "name": "Zhang shan"
    }
}
```

利用`if_seq_no`和`if_primary_term`字段可以控制并发问题：第一个修改该记录版本的修改请求能够成功修改

| 请求类型 |         URL(前缀`http://192.168.56.10:9200`)         |       请求体       |
| :------: | :--------------------------------------------------: | :----------------: |
|   POST   | `/customer/external/1?if_seq_no=4&if_primary_term=2` | `{"name": "lisi"}` |

```json
# 响应数据：
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 6,
    "result": "updated",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 6,
    "_primary_term": 2
}
```

第二个修改该版本的请求修改失败：

| 请求类型 |         URL(前缀`http://192.168.56.10:9200`)         |       请求体       |
| :------: | :--------------------------------------------------: | :----------------: |
|   POST   | `/customer/external/1?if_seq_no=4&if_primary_term=2` | `{"name": "王五"}` |

```json
# 响应数据：
{
    "error": {
        "root_cause": [
            {
                "type": "version_conflict_engine_exception",
                "reason": "[1]: version conflict, required seqNo [4], primary term [2]. current document has seqNo [6] and primary term [2]",
                "index_uuid": "13GdFLkVRDOYEv3TdVJWLA",
                "shard": "0",
                "index": "customer"
            }
        ],
        "type": "version_conflict_engine_exception",
        "reason": "[1]: version conflict, required seqNo [4], primary term [2]. current document has seqNo [6] and primary term [2]",
        "index_uuid": "13GdFLkVRDOYEv3TdVJWLA",
        "shard": "0",
        "index": "customer"
    },
    "status": 409
}
```

<!-- TOC --><a name="44-"></a>
### 4.4 更新文档 

| 描述                | 请求类型 | URL(前缀`http://192.168.56.10:9200`) | 请求体                                                      |
| ------------------- | :------- | :----------------------------------- | :---------------------------------------------------------- |
| 更新文档带`_update` | POST     | `/customer/external/1/_update`       | {<br/>    "doc":{<br/>        "name":"王五"<br/>    }<br/>} |

```json
# 查询语句中包含_update: 对比新数据和原数据，如果与原来―样就什么都不做，version,seq_no都不变
# 查询语句中不包含_update:不对比新数据和原数据，且会对version,seq_no做更新
# 响应数据：
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 8,
    "result": "noop",
    "_shards": {
        "total": 0,
        "successful": 0,
        "failed": 0
    },
    "_seq_no": 8,
    "_primary_term": 2
}
```

| 描述                  | 请求类型  | URL(前缀`http://192.168.56.10:9200`) | 请求体                            |
| --------------------- | :-------- | :----------------------------------- | :-------------------------------- |
| 更新文档不带`_update` | POST或PUT | `/customer/external/1`               | {<br/>        "name":"王五"<br/>} |

```json
# 查询语句中包含_update: 对比新数据和原数据，如果与原来―样就什么都不做，version,seq_no都不变
# 查询语句中不包含_update:不对比新数据和原数据，且会对version,seq_no做更新
# 响应数据：
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 8,
    "result": "updated",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 8,
    "_primary_term": 2
}
```

```
总结：
1. POST操作会对比源文档数据，如果相同不会有什么操作，文档version不增加
2. 带_update对比元数据如果一样就不进行任何操作
3. PUT 操作总会将数据重新保存并增加version 版本
4. 开发中如何选择：
        1）对于大并发更新,不带update
        2）对于大并发查询偶尔更新，带update ，对比更新，重新计算分配规则
```

| 描述               | 请求类型 | URL(前缀`http://192.168.56.10:9200`) | 请求体                                                       |
| ------------------ | :------- | :----------------------------------- | :----------------------------------------------------------- |
| 更新的同时增加属性 | POST     | `/customer/external/1/_update`       | {<br/>    "doc":{<br/>        "name":"王五",<br/>        "age":19<br/>    }<br/>} |
| 更新的同时增加属性 | PUT      | `/customer/external/1`               | {<br/>    "name":"王五",<br/>    "age":19<br/>}              |

```json
# 响应数据
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 9,
    "result": "updated",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 14,
    "_primary_term": 2
}
```

<!-- TOC --><a name="45-"></a>
### 4.5 删除文档&索引

| 描述     | 请求类型 | URL(前缀`http://192.168.56.10:9200`) |
| -------- | :------- | :----------------------------------- |
| 删除文档 | DELETE   | `/customer/external/1`               |

```json
# 响应数据：
{
    "_index": "customer",
    "_type": "external",
    "_id": "1",
    "_version": 11,
    "result": "deleted",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 16,
    "_primary_term": 2
}
```

| 描述     | 请求类型 | URL(前缀`http://192.168.56.10:9200`) |
| -------- | :------- | :----------------------------------- |
| 删除索引 | DELETE   | `/customer`                          |

```json
# 响应数据：
{
    "acknowledged": true
}
```

<!-- TOC --><a name="46-bulkapi"></a>
### 4.6 bulk批量API

```sql
# bulk批量API
# kibana下执行语句：
POST /customer/external/_bulk
{"index":{"_id":"1"}}
{"name":{"name":"John"}}
{"index":{"_id":"2"}}
{"name":{"name":"Jack"}}

# 复杂示例：
POST /_bulk
{ "delete": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "create": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "title": "My first blog post" }
{ "index": { "_index": "website", "_type": "blog" }}				  
{ "title": "My second blog post" }
{ "update": { "_index": "website", "_type": "blog", "_id": "123"}}
{ "doc" : {"title" : "My updated blog post"} }

# 说明：
        # bulk API以此按顺序执行所有的 action(动作）。如果一个单个的动作因任何原因而失败，
        # 它将继续处理它后面剩余的动作。当 bulk API返回时，它将提供每个动作的状态（与发送的顺序相同）
        # 所以您可以检查是否一个指定的动作是不是失败了
        
```

<!-- TOC --><a name="5-"></a>
## 5. 进阶检索

数据准备：  准备一份顾客银行账户信息的虚构的JSON文档样本（json数据已放到本仓库的`001批量测试数据.json`文件）

导入测试数据：  `POST bank/account/_bulk`   `测试数据`

![image-20230514140632932](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240310513.png)

<!-- TOC --><a name="51-searchapi"></a>
### 5.1 SearchAPI

ES支持两种基本方式检索:

- 一个是通过使用REST request URI 发送搜索参数（uri+检索参数)

- 另一个是通过使用REST request body来发送它们（uri+请求体)
- **一切检索从_search 开始 **

**uri+检索参数**

| `GET bank/_search`                             | 检索bank下所有信息，包括type和 docs |
| ---------------------------------------------- | ----------------------------------- |
| `GET bank/_search?q=*&sort=account_number:asc` | 请求参数方式检索                    |

```sql
响应结果解释:
      took- Elasticsearch    执行搜索的时间（毫秒)
      time_out               告诉我们搜索是否超时
      _shards                有多少个分片被搜索了，以及统计了成功/失败的搜索分片hits-搜索结果
      hits.total             搜索结果
      hits.hits              实际的搜索结果数组（默认为前10的文档)
      sort                   结果的排序key（键）（没有则按score排序)
      score和 max_score      相关性得分和最高得分（全文检索用）
```

**uri+请求体进行检索**  

```sql
#uri+请求体进行检索  
GET bank/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "account_number": "asc"
    },
    {
      "balance": "desc"
    }
  ]
}
```

```sql
1.HTTP 客户端工具（POSTMAN), get 请求不能携带请求体。变为post也是一样的，POST一个JSON风格的查询请求体到_search API
2.需要了解，一旦搜索的结果被返回，Elasticsearch就完成了这次请求，并且不会维护任何服务端的资源或者结果的 cursor（游标)
```

<!-- TOC --><a name="52-query-dsl"></a>
### 5.2 Query DSL

<!-- TOC --><a name="1-"></a>
#####   1. 基本语法格式

- `Elasticsearch`提供了一个可以执行查询的`Json`风格的`DSL` (domain-specific language领域特定语言)
- 这个被称为Query DSL。该查询语言非常全面，并且刚开始的时候感觉有点复杂，真正学好它的方法是从一些基础的示例开始
- 一个查询语句的典型结构:

```sql
#一个查询语句 的典型结构
{
	QUERY_NAME: {
	ARGUMENT: VALUE,
	ARGUMENT: VALUE,...
	}
}
```

- 如果是针对某个字段， 那么它的结构如下：

```sql
#如果是针对某个字段， 那么它的结构如下：
{
	QUERY_NAME: {
		FIELD_NAME: {
		ARGUMENT: VALUE,
		ARGUMENT: VALUE,...
		}
	}
}
```

- 基本查询示例

```sql
# 基本查询示例
GET bank/_search
{
  "query": {
    "match_all": {}
  },
  "from": 0,
  "size": 5,
  "sort": [
    {
      "account_number": {
        "order": "desc"
      }
    }
  ]
}
#解释：
    #  query定义如何查询
    #  match_all查询类型【代表查询所有的所有】，es 中可以在query中组合非常多的查询类型完成复杂查询
    #  除了query参数之外，我们也可以传递其它的参数以改变查询结果。如sort，sizefrom-size限定，完成分页功能
    #  sort排序，多字段排序，会在前序字段相等时后续字段内部排序，否则以前序为准
```

<!-- TOC --><a name="2--1"></a>
#####   2. 只返回部分字段

```sql
# 只返回部分字段
# _source 条件说明要查询的字段 
GET bank/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
  {
    "account_number": "asc"
  },
  {
    "balance": "desc"
  }
  ],
  "from": 0,
  "size": 5,
  "_source": ["balance", "firstname"]
}
```

<!-- TOC --><a name="3-match-"></a>
#####  3. match[ 匹配查询 ]

```sql
# 如果匹配的字段是数字，则为精确查询：只查询相等的
GET bank/_search
{
  "query": {
    "match": {
      "balance": 16418
    }
  }
}
```

```sql
# 如果匹配的字段是字符串，则为模糊查询
# 如下查询可以匹配到 "address" : "282 Kings Place" 
GET bank/_search
{
  "query": {
    "match": {
      "address": "kings"
    }
  }
}
```

```sql
# 全文检索按照评分进行排序，会对检索条件进行分词匹配
GET bank/_search
{
  "query": {
    "match": {
      "address": "mill lane"
    }
  }
}
# 最终查询出address中包含mill或者lane或者mill lane 的所有记录，并给出相关性得分
# 此查询可以匹配到
                    #  "address" : "198 Mill Lane" 
                    #  "address" : "990 Mill Road"
                    #  "address" : "715 Mill Avenue"
```

<!-- TOC --><a name="4-match_phrase-"></a>
#####  4. match_phrase [ 短语匹配 ]

```sql
# 将需要匹配的值当成一个整体单词（不分词）进行检索
GET bank/_search
{
  "query": {
    "match_phrase": {
      "address": "mill road"
    }
  }
}
# 此查询匹配到 "address" : "990 Mill Road"
```

<!-- TOC --><a name="5-multi_match-"></a>
#####  5. multi_match [多字段匹配]

```sql
# multi_match 多字段匹配
GET bank/_search
{
  "query": {
    "multi_match": {
      "query": "mill Lopezo",
      "fields": ["address","city"]
    }
  }
}
```

<!-- TOC --><a name="6-bool-"></a>
#####  6. bool [复合查询]

```sql
# bool用来做复合查询:
        # 复合语句可以合并任何其它查询语句，包括复合语句
        # 复合语句之间可以互相嵌套，可以表达非常复杂的逻辑

# must:       必须达到 must 列举的所有条件
# must_not:   必须不是指定的情况
# should: 
        # 应该达到 should 列举的条件， 如果达到会增加相关文档的评分， 并不会改变查询的结果
        # 如果query中只有should且只有一种匹配规则，那么should的条件就会被作为默认匹配条件而去改变查询结果

GET bank/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "address": "Mill"
          }
        },
        {
          "match": {
            "gender": "M"
          }
        }
      ]
      , "must_not": [
        {"match": {
          "age": "28"
        }}
      ]
      , "should": [
        {"match": {
          "lastname":"Wallace"
        }
        }
      ]
    }
  }
}
```

<!-- TOC --><a name="7-filter"></a>
#####  7. filter[结果过滤]

```sql
#   并不是所有的查询都需要产生分数，特别是那些仅用于“filtering”（过滤）的文档
#   为了不计算分数 Elasticsearch会自动检查场景并且优化查询的执行
GET bank/_search
{
  "query": {
    "bool":{
      "filter": {
        "range": {
          "age": {
            "gte": 18,
            "lte": 30
          }
        }
      }
    }
  }
}
```

<!-- TOC --><a name="8-term"></a>
#####  8. term

```sql
# 和match一样。匹配某个属性的值
# 全文检索字段用match，其他非text字段匹配尽量用term 
GET bank/_search
{
  "query": {
		"term": {
			"age": "28"  
		} 
	}
}
```

<!-- TOC --><a name="9-aggregations"></a>
##### 9. aggregations（执行聚合)

```sql
1. 聚合提供了从数据中分组和提取数据的能力。最简单的聚合方法大致等于SQL GROUP BY和SQL聚合函数

2. 在Elasticsearch 中，执行搜索返回hits(命中结果)，并且同时返回聚合结果，把一个响应中的
   所有hits(命中结果)分隔开的能力

3. 这是非常强大且有效的，您可以执行查询和多个聚合，并且在一次使用中得到各自的(任何一个的）
   返回结果，使用一次简洁和简化的API来避免网络往返
```

```sql
# 搜索address 中包含mill 的所有人的年龄分布以及平均年龄，但不显示这些人的详情
GET bank/_search
{
  "query": {
    "bool": {
      "must": [
        {"match": {
          "address": "Mill"
        }}
      ]
    }
  },
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "age",
        "size": 10
      }
    },
    "avg_age": {
      "avg": {
        "field": "age"
      }
    }
  },
  "size":0
}
#  aggregations 执行聚合年龄分布及平均值 聚合
#  size： 0 不显示搜索数据
#  aggs： 执行聚合。 聚合语法如下
        “aggs”: {
        “aggs_name 这次聚合的名字， 方便展示在结果集中”: {
        "AGG_TYPE 聚合的类型（ avg,term,terms） ": {}
        }
        },
```

```sql
# 子聚合：
# 按照年龄聚合，并且请求这些年龄段的这些人的平均薪资
GET bank/_search
{
  "query": {
    "match_all": {}
  },
  "aggs": {
    "ageAgg": {
      "terms": {
        "field": "age",
        "size": 100
      },
      "aggs": {
        "ageAvg": {
          "avg": {
            "field": "balance"
          }
        }
      }
    }
  }
}
```

```sql
# 查出所有年龄分布，并且这些年龄段中M的平均薪资和F的平均薪资以及这个年龄段的总体平均薪资
GET bank/_search
{
  "query": {
    "match_all": {}
  },
  "aggs": {
    "ageAvg": {
      "terms": {
        "field": "age",
        "size": 100
      },
      "aggs": {
        "genderAgg": {
          "terms": {
            "field": "gender.keyword",
            "size": 10
          },
          "aggs": {
            "balanceAvg": {
              "avg": {
                "field": "balance"
              }
            }
          }
        },
        "ageBalanceAvg":{
          "avg": {
            "field": "balance"
          }
        }
      }
    }
  }
}
```

<!-- TOC --><a name="53-mapping-"></a>
###  5.3 Mapping 映射

<!-- TOC --><a name="1-1"></a>
#####  1.字段类型

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240310039.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240311191.png" > <b>2</b></td>
</tr>
</table>



<!-- TOC --><a name="2mapping"></a>
#####  2.映射Mapping

- 映射Mapping相关内容官方文档：
  - `https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html`

- Mapping（映射)
- Mapping 是用来定义一个文档(document)，以及它所包含的属性（field）是如何存储和索引的
- 比如，使用mapping来定义：
  - 哪些字符串属性应该被看做全文本属性（full text fields）
  - 哪些属性包含数字，日期或者地理位置
  - 文档中的所有属性是否都能被索引 (_all配置)
  - 日期的格式
  - 自定义映射规则来执行动态添加属性

- 查看mapping信息: `GET bank/_mapping`
- 修改mapping信息
- 自动猜测的映射类型

![image-20230514195151948](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240312966.png)

<!-- TOC --><a name="3"></a>
##### 3.新版本改变

**一、ElasticSearch7-去掉type概念**

- 关系型数据库中两个数据表示是独立的，即使他们里面有相同名称的列也不影响使用，但ES中不是这样的
- elasticsearch是基于Lucene开发的搜索引擎，而ES中不同type下名称相同的filed最终在Lucene中的处理方式是一样的
- 两个不同type下的两个user_name，在ES同一个索引下其实被认为是同一个filed，你必须在两个不同的type中定义相同的filed映射。否则，不同type中的相同字段名称就会在处理中出现冲突的情况，导致Lucene处理效率下降
- 去掉type就是为了提高ES处理数据的效率
- Elasticsearch 7.x
- URL中的type参数为可选。比如，索引一个文档不再要求提供文档类型
- Elasticsearch 8.x
  - 不再支持URL中的type参数
  - 解决: 将索引从多类型迁移到单类型，每种类型文档一个独立索引

<!-- TOC --><a name="54-mapping-"></a>
### 5.4 mapping 新版本改变

<!-- TOC --><a name="1-2"></a>
##### 1.创建索引并指定映射

```sql
#创建索引并指定映射
PUT /my_index
{
  "mappings": {
    "properties": {
      "age":{
        "type": "integer"
      },
      "email":{
        "type": "keyword"
      },
      "name":{
        "type": "text"
      }
    }
  }
}
```

<!-- TOC --><a name="2"></a>
#####  2.添加新的字段映射

```sql
#添加新的字段映射
PUT /my_index/_mapping
{
  "properties": {
    "employee_id": {
      "type": "keyword",
      "index": false
    }
  }
}

# 查看my_index的映射
GET /my_index/_mapping
```

<!-- TOC --><a name="3-1"></a>
##### 3.更新映射

- 对于已经存在的映射字段，我们不能更新。更新必须创建新的索引进行数据迁移
- 如果更改了原有索引中映射字段，映射规则就发生变化，原有的数据也就失效了

<!-- TOC --><a name="4-1"></a>
#####  4.数据迁移

先创建出newbank的正确映射。然后使用如下方式进行数据迁移

```sql
# 创建新索引并指定映射
PUT /newbank
{
  "mappings": {
    "properties": {
      "account_number": {
        "type": "long"
      },
      "address": {
        "type": "text"
      },
      "age": {
        "type": "integer"
      },
      "balance": {
        "type": "long"
      },
      "city": {
        "type": "keyword"
      },
      "email": {
        "type": "keyword"
      },
      "employer": {
        "type": "keyword"
      },
      "firstname": {
        "type": "text"
      },
      "gender": {
        "type": "keyword"
      },
      "lastname": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword",
            "ignore_above": 256
          }
        }
      },
      "state": {
        "type": "keyword"
      }
    }
  }
}

# 将旧索引的type下的数据进行迁移
POST _reindex
{
  "source": {
    "index": "bank",
    "type": "account"
  }
  , "dest": {
    "index": "newbank"
  }
}

# 查询新数据
GET /newbank/_search
```

<!-- TOC --><a name="55-"></a>
### 5.5 分词

<!-- TOC --><a name="1-3"></a>
##### 1.分词简介

一个`tokenizer`(分词器）接收一个字符流，将之分割为独立的 tokens（词元，通常是独立的单词)，然后输出tokens流

例如，`whitespace tokenizer`遇到空白字符时分割文本。它会将文本`"Quick brown fox!"`分割为`[Quick, brown, fox!]`

该`tokenizer`(分词器）还负责记录各个`term`(词条)的顺序或`position`位置(用于`phrase`短语和`word proximity`词近邻查询），以及`term`（词条）所代表的原始`word`（单词）的`start`(起始）和`end`(结束）的`character offsets`(字符偏移量）(用于高亮显示搜索的内容）

`Elasticsearch`提供了很多内置的分词器，可以用来构建`custom analyzers`(自定义分词器）。

**分词测试**      ：

测试文本`The 2 QUICK Brown-Foxes jumped over the lazy dog's bone.`是如何被分词的：

```sql
# 分词测试 
POST _analyze
{
	"analyzer": "standard",
	"text": "The 2 QUICK Brown-Foxes jumped over the lazy dog's bone."
}

#  响应数据： 
{
  "tokens" : [
    {
      "token" : "the",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "<ALPHANUM>",
      "position" : 0
    },
    {
      "token" : "2",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "<NUM>",
      "position" : 1
    },
    {
      "token" : "quick",
      "start_offset" : 6,
      "end_offset" : 11,
      "type" : "<ALPHANUM>",
      "position" : 2
    },
    {
      "token" : "brown",
      "start_offset" : 12,
      "end_offset" : 17,
      "type" : "<ALPHANUM>",
      "position" : 3
    },
    {
      "token" : "foxes",
      "start_offset" : 18,
      "end_offset" : 23,
      "type" : "<ALPHANUM>",
      "position" : 4
    },
    {
      "token" : "jumped",
      "start_offset" : 24,
      "end_offset" : 30,
      "type" : "<ALPHANUM>",
      "position" : 5
    },
    {
      "token" : "over",
      "start_offset" : 31,
      "end_offset" : 35,
      "type" : "<ALPHANUM>",
      "position" : 6
    },
    {
      "token" : "the",
      "start_offset" : 36,
      "end_offset" : 39,
      "type" : "<ALPHANUM>",
      "position" : 7
    },
    {
      "token" : "lazy",
      "start_offset" : 40,
      "end_offset" : 44,
      "type" : "<ALPHANUM>",
      "position" : 8
    },
    {
      "token" : "dog's",
      "start_offset" : 45,
      "end_offset" : 50,
      "type" : "<ALPHANUM>",
      "position" : 9
    },
    {
      "token" : "bone",
      "start_offset" : 51,
      "end_offset" : 55,
      "type" : "<ALPHANUM>",
      "position" : 10
    }
  ]
}
```

<!-- TOC --><a name="2ik"></a>
##### 2.安装ik分词器

1.注意：不能用默认`elasticsearch-plugin install xxx.zip`进行自动安装

2.github地址： `https://github.com/medcl/elasticsearch-analysis-ik/`  找到`elasticsearch`对应版本进行安装（本教程为7.4.2）

3.执行下列命令安装ik分词器

```sh
# 进入es容器内部plugins目录：
docker exec -it 容器 id /bin/bash

# 创建ik目录
mkdir -p /mydata/elasticsearch/plugins/ik

# cd 到plugins下的ik目录
cd /mydata/elasticsearch/plugins/ik

# 下载安装包
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.4.2/elasticsearch-analysis-ik-7.4.2.zip

# 解压下载的文件
unzip elasticsearch-analysis-ik-7.4.2.zip

# 删除zip文件
rm –rf *.zip

# 修改权限
chmod -R 777 /mydata/elasticsearch/plugins/ik/
```

验证是否安装成功：

```sh
# 验证是否安装成功：
cd /usr/share/elasticsearch/bin

# 执行elasticsearch-plugin list 如果显示ik则安装成功
elasticsearch-plugin list
```

重启elasticsearch：

```sh
# 发现ik后重启容器
docker restart elasticsearch
```

分词测试：

```sql
POST my_index/_analyze
{
	"analyzer": "ik_smart",
	"text": "尚硅谷电商项目"
}

POST my_index/_analyze
{
	"analyzer": "ik_max_word",
	"text": "我是社会主义中国人"
}

POST  _analyze
{
	"analyzer": "ik_max_word",
	"text": "我是社会主义中国人"
}
```

<!-- TOC --><a name="3-2"></a>
##### 3.自定义词库

- **搭建自定义词库**
  - 安装nginx，将最新词库放到nginx，让ik分词器向nginx发送请求，从而使用自定义词库以及原来的词库
- **复制一份nginx的配置文件出来**

```sh
# 删除掉nginx镜# 创建nginx目录：
cd /mydata/nginx/

# 拉取、创建一个容器，并运行
docker run -p 80:80 --name nginx -d nginx:1.10

# cd 到mydata目录
cd /mydata/

# 从nginx容器中复制nginx文件到当前目录的nginx下（不用漏了最后的点.）
docker container cp nginx:/etc/nginx .

cd /mydata/nginx/
ls
#/mydata/nginx/ 下显示 conf.d  koi-utf  mime.types  nginx.conf   uwsgi_params fastcgi_params  koi-win  modules     scgi_params  win-utf

# 停掉nginx
docker stop nginx

# 删除掉nginx容器
docker stop nginx

# 返回到 /mydata/目录
cd ../

# 将复制出来的nginx文件改名为conf
mv nginx conf

# 重新创建nginx目录
mkdir nginx

# 将复制出来的conf移动到nginx
mv conf nginx/
```

- **创建新的nginx容器**

```sh
docker run -p 80:80 --name nginx \
-v /mydata/nginx/html:/usr/share/nginx/html \
-v /mydata/nginx/logs:/var/log/nginx \
-v /mydata/nginx/conf:/etc/nginx \
-d nginx:1.10
```

测试：访问`http://192.168.56.10:80`，看到nginx欢迎页（默认页面显示403 Forbidden）

```sh
cd /mydata/nginx/html

mkdir es

cd es/

# 创建fenci.txt并添加内容
vi fenci.txt
# 在该文件中添加 
尚硅谷 
乔碧罗
```

给nginx的html下面放的所有资源可以直接访问 ： 访问`http://192.168.56.10/es/fenci.txt`可以看到填写的内容

- **配置IK分词器的远程词库地址：**

```sh
# cd 到ik的config目录,对应 es容器的 /usr/share/elasticseardh/plugins/ik/config
cd /mydata/elasticsearch/plugins/ik/config

# 修改`/usr/share/elasticsearch/plugins/ik/config/`中的 `IKAnalyzer.cfg.xml`
 vi IKAnalyzer.cfg.xml
```

![image-20230516034415846](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240312278.png)

```sh
 # 重启elasticsearch
  docker restart elasticsearch
```

- **测试自定义IK分词器：**

```json
POST  _analyze
{
	"analyzer": "ik_max_word",
	"text": "乔碧罗殿下"
}

# 响应数据：
{
  "tokens" : [
    {
      "token" : "乔碧罗",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "殿下",
      "start_offset" : 3,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 1
    }
  ]
}
```

<!-- TOC --><a name="6-elasticsearch-rest-client"></a>
## 6. Elasticsearch-Rest-Client

<!-- TOC --><a name="61-elasticsearch-rest-client"></a>
###  6.1 Elasticsearch-Rest-Client简介

使用java操作`elasticsearch`有三种方式，第一种是操作es的9300端口（TCP端口），第二种是操作9200端口（HTTP端口）。用java操作`elasticsearch`   ，一般都使用`Elasticsearch-Rest-Client`

- **9300:TCP**

  - spring-data下的transport-api可以操作elasticsearch：
    -  `spring-data-elasticsearch:transport-api.jar`  

  - 不建议使用`9300:TCP`的方法操作`elasticsearch`,原因：

    - springboot 版本不同， transport-api.jar 不同， 不能适配 es 版本

    - 7.x 已经不建议使用， 8 以后就要废弃

- **9200： HTTP**
  - JestClient： 非官方， 更新慢
  - RestTemplate： 模拟发 HTTP 请求，ES 很多操作需要自己封装， 麻烦
  - HttpClient： 同上

- **Elasticsearch-Rest-Client：** 
  - 官方 RestClient， 封装了 ES 操作， API 层次分明， 上手简单
  - 本项目最终选择 `Elasticsearch-Rest-Client (elasticsearch-rest-high-level.client)`
  - 官方文档：
    - `https://www.elastic.co/guide/en/elasticsearch/client/index.html`
    - `https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high.html`

<!-- TOC --><a name="62-springbootelasticsearch-rest-high-level-client"></a>
### 6.2 springboot整合elasticsearch-rest-high-level-client

创建gulimall-search`检索服务`模块，引入依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.8.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.atguigu.gulimall</groupId>
    <artifactId>gulimall-search</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>gulimall-search</name>
    <description>gulimall-search</description>
    <properties>
        <java.version>1.8</java.version>
        <elasticsearch.version>7.4.2</elasticsearch.version>
    </properties>
    <dependencies>
        <!--导入es的 rest-high-level-client-->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.4.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.atguigu.gulimail</groupId>
            <artifactId>gulimail-common</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

将检索服务`gulimall-search`注册到nacos注册中心：

```properties
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
spring.application.name=gulimall-search
```

启动类上添加`@EnableDiscoveryClient`,排除数据源的配置：

```java
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GulimallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallSearchApplication.class, args);
    }

}
```

添加`elasticsearch`的配置类：

```java
package com.atguigu.gulimall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 1、导入依赖
 * 2、编写配置，给容器中注入一个RestHighLevelClient
 * 3、参照API https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high.html
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient esRestClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.56.10", 9200, "http")));
        return client;
    }

}
```

测试`RestHighLevelClient`配置是否可用：

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;
    
    @Test
    public void contextLoads() {
        System.out.println(client);
    }
}
```

<!-- TOC --><a name="63-es"></a>
###  6.3 保存或更新数据到es

修改配置类，添加` RequestOptions`:

```java
@Configuration
public class ElasticSearchConfig {

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient esRestClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.56.10", 9200, "http")));
        return client;
    }

}
```

 测试: 存储或更新数据到es

```java
package com.atguigu.gulimall.search;


import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.search.config.ElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallSearchApplicationTest {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 测试: 存储或更新数据到es
     */
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");  // 数据的id
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user); // 要保存的数据
        indexRequest.source(jsonString, XContentType.JSON);


        // 执行操作
        IndexResponse index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // 提取有用的响应数据
        System.out.println(index);
        // 控制台打印：  
        // IndexResponse[index=users,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
    }

    @Data
    class User {
        private String userName;
        private String gender;
        private Integer age;
    }
}
```

测试：在kibana查询数据，返回如下响应

```sql
# 查询
GET users/_search

# 响应数据：
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 1,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "users",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "age" : 18,
          "gender" : "男",
          "userName" : "zhangsan"
        }
      }
    ]
  }
}
```

<!-- TOC --><a name="64-es"></a>
### 6.4 从es中检索数据

检索官方文档：`https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-search.html`

```java
package com.atguigu.gulimall.search;
import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.search.config.ElasticSearchConfig;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallSearchApplicationTest {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 测试： 检索数据
     */
    @Test
    public void searchData() throws IOException {
        // 1.创建一个检索请求
        SearchRequest searchRequest = new SearchRequest();

        // 指定索引
        searchRequest.indices("bank");

        // 指定DSL,检索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); // 封装的条件
        searchRequest.source(sourceBuilder);

        // 1.1） 构造检索条件：
        sourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));

        // 1.2) 构造聚合条件 : 按照年龄的值发布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(ageAgg);

        // 1.3）计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);

        // 2.执行检索
        SearchResponse searchResponse = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // 3.分析检索结果 searchResponse
        System.out.println(searchResponse.toString());
        /*
        {"took":83,"timed_out":false,"_shards":{"total":1,"successful":1,"skipped":0,"failed":0},"hits":{"total":{"value":4,"relation":"eq"},"max_score":5.4032025,"hits":[{"_index":"bank","_type":"account","_id":"970","_score":5.4032025,"_source":{"account_number":970,"balance":19648,"firstname":"Forbes","lastname":"Wallace","age":28,"gender":"M","address":"990 Mill Road","employer":"Pheast","email":"forbeswallace@pheast.com","city":"Lopezo","state":"AK"}},{"_index":"bank","_type":"account","_id":"136","_score":5.4032025,"_source":{"account_number":136,"balance":45801,"firstname":"Winnie","lastname":"Holland","age":38,"gender":"M","address":"198 Mill Lane","employer":"Neteria","email":"winnieholland@neteria.com","city":"Urie","state":"IL"}},{"_index":"bank","_type":"account","_id":"345","_score":5.4032025,"_source":{"account_number":345,"balance":9812,"firstname":"Parker","lastname":"Hines","age":38,"gender":"M","address":"715 Mill Avenue","employer":"Baluba","email":"parkerhines@baluba.com","city":"Blackgum","state":"KY"}},{"_index":"bank","_type":"account","_id":"472","_score":5.4032025,"_source":{"account_number":472,"balance":25571,"firstname":"Lee","lastname":"Long","age":32,"gender":"F","address":"288 Mill Street","employer":"Comverges","email":"leelong@comverges.com","city":"Movico","state":"MT"}}]},"aggregations":{"lterms#ageAgg":{"doc_count_error_upper_bound":0,"sum_other_doc_count":0,"buckets":[{"key":38,"doc_count":2},{"key":28,"doc_count":1},{"key":32,"doc_count":1}]},"avg#balanceAvg":{"value":25208.0}}}
         */


        // 也可以将检索结果封装成对象
        Map map = JSON.parseObject(searchResponse.toString(), Map.class);


        //  3.1） 获取所有查询到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            /**
             * "_index": "bank",
             * 			"_type": "account",
             * 			"_id": "345",
             * 			"_score": 5.4032025,
             * 			"_source":
             */
//            hit.getIndex();hit.getType();hit.getId();
            String string = hit.getSourceAsString();
            Accout accout = JSON.parseObject(string, Accout.class);
            System.out.println("accout：" + accout);
            /*
            accout��GulimallSearchApplicationTest.Accout(account_number=970, balance=19648, firstname=Forbes, lastname=Wallace, age=28, gender=M, address=990 Mill Road, employer=Pheast, email=forbeswallace@pheast.com, city=Lopezo, state=AK)
            accout��GulimallSearchApplicationTest.Accout(account_number=136, balance=45801, firstname=Winnie, lastname=Holland, age=38, gender=M, address=198 Mill Lane, employer=Neteria, email=winnieholland@neteria.com, city=Urie, state=IL)
            accout��GulimallSearchApplicationTest.Accout(account_number=345, balance=9812, firstname=Parker, lastname=Hines, age=38, gender=M, address=715 Mill Avenue, employer=Baluba, email=parkerhines@baluba.com, city=Blackgum, state=KY)
            accout��GulimallSearchApplicationTest.Accout(account_number=472, balance=25571, firstname=Lee, lastname=Long, age=32, gender=F, address=288 Mill Street, employer=Comverges, email=leelong@comverges.com, city=Movico, state=MT)
             */
        }

        //3.2）、获取这次检索到的分析信息；
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations.asList()) {
//            System.out.println("当前聚合："+aggregation.getName());
////            aggregation.get
//
//        }
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄：" + keyAsString + "==>" + bucket.getDocCount());
        }

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资：" + balanceAvg1.getValue());

//        Aggregation balanceAvg2 = aggregations.get("balanceAvg");
    }

    @ToString
    @Data
    static class Accout {

        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;

    }
}
```

<!-- TOC --><a name="7"></a>
##  7.商品上架

<!-- TOC --><a name="71-skuspues"></a>
###  7.1 商品上架-分析sku\spu在es中如何存储

**需求：**

- 上架的商品才可以在网站展示

- 上架的商品需要可以被检索

![image-20230520011550954](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240314270.png)



**商品 Mapping**

- 分析: `商品上架`在es中是存sku还是spu?
- 检索的时候输入名字，是需要按照sku的 title进行全文检索的

- 检索使用商品规格，规格是spu的公共属性，每个spu是一样的

- 按照分类id进去的都是直接列出 spu的，还可以切换
- 我们如果将sku的全量信息保存到es中(包括spu属性）就太多量字段了
- 我们如果将spu以及他包含的sku信息保存到es中，也可以方便检索。但是sku属于spu的级联对象，在es中需要nested模型，这种性能差点
- 但是存储与检索我们必须性能折中，权衡空间和时间
- 如果我们分拆存储，spu和 attr一个索引，sku单独一个索引可能涉及的问题

检索商品的名字，如“手机”，对应的spu有很多，我们要分析出这些spu的所有关联属性,再做一次查询,就必须将所有`spu_id`都发出去。假设有1万个数据，数据传输一次就`10000*4=4MB`;并发情况下假设1000检索请求，那就是`4GB`的数据，，传输阻塞时间会很长，业务更加无法继续

方案一：

```sql
(1) 优点：方便检索
(2) 缺点：
         冗余：如果每个sku都存储规格参数(如尺寸)，会有冗余存储，因为每个spu对应的sku的规格参数都一样
         100万*20=1000000*2KB=2000MB=2G 20
{
     skuId:1
     spuId:11
     skuTitle:华为xx
     price:998
     saleCount:99
     attrs:[
            {尺寸：5寸},
            {CPU：高通945},
            {分辨率：全高清}
           ]
}
缺点： 冗余，如果每个sku都存储规格参数(如尺寸)，会有冗余存储，因为每个spu对应的sku的规格参数都一样
```

方案二：将规格参数单独建立索引。如果将规格参数单独建立索引，会出现检索时出现大量数据传输的问题，会引起网络网络阻塞

```sql
sku索引 :
            {
             skuId:1
             spuId:11
              xxxxx
            }

attr索引
            {
               spuId:11,
               attrs:[
                      {尺寸：5寸},
                     {CPU：高通945},
                      {分辨率：全高清}
              ]
            }

搜索 小米； 粮食，手机，电器
10000个，4000个spu
分步，4000个spu对应的所有可能属性；
esClient： spuId:[4000个spuid] 4000*8=32000byte=32kb
32kb*10000=32000mb;=32GB

先找到4000个符合要求的spu，再根据4000个spu查询对应的属性，封装了4000个id，long 8B*4000=32000B=32KB
1K个人检索，就是32MB

结论：如果将规格参数单独建立索引，会出现检索时出现大量数据传输的问题，会引起网络阻塞
```

所以，我们如下设计，这样才是文档区别于关系型数据库的地方，宽表设计，不能去考虑数据库范式

```json
PUT product
{
  "mappings": {
    "properties": {
      "skuId": {
        "type": "long"
      },
      "spuId": {
        "type": "keyword"
      },
      "skuTitle": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "skuPrice": {
        "type": "keyword"
      },
      "skuImg": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "saleCount": {
        "type": "long"
      },
      "hasStock": {
        "type": "boolean"
      },
      "hotScore": {
        "type": "long"
      },
      "brandId": {
        "type": "long"
      },
      "catalogId": {
        "type": "long"
      },
      "brandName": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "brandImg": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "catalogName": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "attrs": {
        "type": "nested",
        "properties": {
          "attrId": {
            "type": "long"
          },
          "attrName": {
            "type": "keyword",
            "index": false,
            "doc_values": false
          },
          "attrValue": {
            "type": "keyword"
          }
        }
      }
    }
  }
}
```

一些字段类型的解释：

```sql
      "skuImg":{
        "type": "keyword",
        "index": false,    # index 设为false 表示此字段不可被用来检索，但是可以查询。此段相当于冗余字段
        "doc_values": false  # doc_values设为false表示此字段不用做聚合操作
      },
      "skuPrice":{
        "type":"keyword"  # 防止精度问题
      },
            "hotScore":{   # 模拟访问量最多的商品
        "type":"long" 
      },
```

总结：冗余字段(只做页面使用)最好将 index 设为false ，将 doc_values设为false ，es就不会维护一些额外的信息

<!-- TOC --><a name="72-nested-"></a>
###  7.2 nested数据类型-数组的扁平化处理

**Nested datatype : 嵌套数据类型**

- 嵌套类型是对象数据类型的专门版本，它允许对对象数组进行索引，使它们可以相互独立地查询

**对象数组是如何扁平的**

内部对象字段数组的工作方式可能与您预期的不一样。Lucene没有内在对象的概念，所以ElasticSearch将对象层次结构扁平化为字段名和值的简单列表。例如，以下内容:

```json
#  将用户字段动态添加为类型为Object的字段
PUT my_index/_doc/1
{
  "group" : "fans",
  "user" : [ 
    {
      "first" : "John",
      "last" :  "Smith"
    },
    {
      "first" : "Alice",
      "last" :  "White"
    }
  ]
}
```

将在内部转换为更类似于以下内容的文档：

```json
{
  "group" :        "fans",
  "user.first" : [ "alice", "john" ],
  "user.last" :  [ "smith", "white" ]
}
```

`user.first`字段和`user.last `字段被压缩为多值字段。`alice`和`white`之间的关联也消失了。这里的`document`会错误地匹配对`Alice`和`Smith`的查询：

```json
GET my_index/_search
{
  "query": {
    "bool": {
      "must": [
        { "match": { "user.first": "Alice" }},
        { "match": { "user.last":  "Smith" }}
      ]
    }
  }
}
```

**Es-数组的扁平化处理**

![image-20230520101317003](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240314333.png)

**对象数组使用嵌套字段**

如果需要索引对象数组并维护数组中每个对象的独立性，则应使用嵌套数据类型而不是对象数据类型。在内部，嵌套对象索引数组中的每个对象。作为一个单独的隐藏文档，这意味着可以独立地查询每个嵌套对象，使用嵌套查询：

```json
# 用户字段被映射为类型嵌套，而不是类型对象
PUT my_index
{
  "mappings": {
    "properties": {
      "user": {
        "type": "nested" 
      }
    }
  }
}

PUT my_index/_doc/1
{
  "group" : "fans",
  "user" : [
    {
      "first" : "John",
      "last" :  "Smith"
    },
    {
      "first" : "Alice",
      "last" :  "White"
    }
  ]
}

# 此查询不匹配，因为Alice和Smith不在同一个嵌套对象中
GET my_index/_search
{
  "query": {
    "nested": {
      "path": "user",
      "query": {
        "bool": {
          "must": [
            { "match": { "user.first": "Alice" }},
            { "match": { "user.last":  "Smith" }} 
          ]
        }
      }
    }
  }
}

# 此查询匹配，因为Alice和White位于同一个嵌套对象中
# `inside_hits`允许我们突出显示匹配的嵌套文档
GET my_index/_search
{
  "query": {
    "nested": {
      "path": "user",
      "query": {
        "bool": {
          "must": [
            { "match": { "user.first": "Alice" }},
            { "match": { "user.last":  "White" }} 
          ]
        }
      },
      "inner_hits": { 
        "highlight": {
          "fields": {
            "user.first": {}
          }
        }
      }
    }
  }
} 
```

**Es-数组的扁平化处理**

![image-20230520101317003](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240315221.png)

`Nested documents`嵌套文档可以是：

- 使用嵌套查询查询
- 使用嵌套和反向嵌套聚合进行分析
- 用嵌套排序排序
- 检索并突出显示嵌套的内部命中

<!-- TOC --><a name="73-"></a>
###  7.3 商品上架实现

<!-- TOC --><a name="1es"></a>
#####  1.es数据模型

1、URL:`/product/spuinfo/{spuId}/up`

2、请求参数：路径变量中的`spuId`充当请求变量

3、响应数据：

```json
{
	"msg": "success",
	"code": 0
}
```

1.商品服务`gulimall-product`中的`SpuInfoController`类：

```java
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;
    /**
     * 商品上架功能
     * /product/spuinfo/{spuId}/up
     */
    @PostMapping("/{spuId}/up")
    public R spuUp(@PathVariable("spuId") Long spuId) {
        spuInfoService.up(spuId);
        return R.ok();
    }
}
```

2.商品服务`gulimall-product`中的`SpuInfoService`类：

```java
public interface SpuInfoService extends IService<SpuInfoEntity> {
    void up(Long spuId);
}
```

3.为es数据模型建立一个`bean`，由于`gulimall-product`服务和`gulimall-search`服务都要使用到这个`bean`，所以此处将这个`bean`放到`gulimall-common`包下（实际开发中也可以在`gulimall-product`服务和`gulimall-search`服务下都写一份）,具体的数据模型`SkuEsModel`如下：

```java
package com.atguigu.common.to.es;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
@Data
public class SkuEsModel {

    private Long skuId;

    private Long spuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long brandId;

    private Long catalogId;

    private String brandName;

    private String brandImg;

    private String catalogName;

    private List<Attrs> attrs;


    @Data
    public static class Attrs{

        private Long attrId;
        private String attrName;
        private String attrValue;
    }

}
```

<!-- TOC --><a name="2skuinfoes"></a>
#####   2.查询SkuInfo来es数据模型

1.`SkuInfoService`：查出当前`spuId`对应的所有`sku`信息，品牌的名字。查询`skuInfo`信息来填充`EsModel`数据

```java
public interface SkuInfoService extends IService<SkuInfoEntity> {
    List<SkuInfoEntity> getSkusBySpuId(Long spuId);
}
```

2 .`SkuInfoServiceImpl`实现类：查出当前`spuid`对应的所有sku信息，品牌的名字。查询`skuInfo`信息来填充`SkuEsModel`数据

```java
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        List<SkuInfoEntity> list = this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));
 	       return list;
    }

}
```

<!-- TOC --><a name="3-3"></a>
#####  3.查询可检索属性

1、 `ProductAttrValueService`的`baseAttrlistforspu`方法：查询当前sku的所有可以被用来检索的规格属性

```java
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {
    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);
}
```

2 、`ProductAttrValueServiceImpl`实现类的`baseAttrlistforspu`方法：查询当前sku的所有可以被用来检索的规格属性

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

<!-- TOC --><a name="4-2"></a>
#####  4.挑出可检索的属性

1、`AttrService`的`selectSearchAttrIds`方法：在指定的所有属性集合里面挑出可检索的属性

```java
public interface AttrService extends IService<AttrEntity> {
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}
```

2 、`AttrServiceImpl`的`selectSearchAttrIds`方法：在指定的所有属性集合里面挑出可检索的属性

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    /**
     * 在指定的所有属性集合里面，挑出检索属性
     */
    @Override
    public List<Long> selectSearchAttrIds(List<Long> attrIds) {
        /**
         * SELECT attr_id FROM `pms_attr` WHERE attr_id IN(?) AND search_type = 1
         */
        return baseMapper.selectSearchAttrIds(attrIds);
    }
}
```

3 、`mapper`接口：在指定的所有属性集合里面，挑出检索属性

```java
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
```

4、 `mapper`接口文件：在指定的所有属性集合里面，挑出检索属性

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimail.product.dao.AttrDao">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimail.product.entity.AttrEntity" id="attrMap">
        <result property="attrId" column="attr_id"/>
        <result property="attrName" column="attr_name"/>
        <result property="searchType" column="search_type"/>
        <result property="icon" column="icon"/>
        <result property="valueSelect" column="value_select"/>
        <result property="attrType" column="attr_type"/>
        <result property="enable" column="enable"/>
        <result property="catelogId" column="catelog_id"/>
        <result property="showDesc" column="show_desc"/>
    </resultMap>

    <select id="selectSearchAttrIds" resultType="java.lang.Long">
        SELECT attr_id FROM `pms_attr` WHERE attr_id IN
        <foreach collection="attrIds" item="id" separator="," open="(" close=")">
            #{id}
        </foreach>
        AND search_type = 1
    </select>
</mapper>
```

<!-- TOC --><a name="5"></a>
##### 5.远程调用判断库存系统是否有库存

1、在`gulimall-ware`库存服务中添加`WareSkuController`：判断库存系统是否有库存

```java
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;
    // 查询sku是否有库存
    @PostMapping("/hasstock")
    public R getSkuHasStock(@RequestBody List<Long> skuIds) {
        // 返回sku_id , stock
        List<SkuHasStockVo> vos = wareSkuService.getSkusHasStock(skuIds);
        return R.ok().setData(vos);
    }
}
```

2、在`gulimall-ware`库存服务中添加` WareSkuService`：判断库存系统是否有库存

```java
public interface WareSkuService extends IService<WareSkuEntity> {
    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);
}
```

3、在`gulimall-ware`库存服务中添加` WareSkuServiceImpl`：判断库存系统是否有库存

```java
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareSkuDao wareSkuDao;

    @Override
    public List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds) {


        List<SkuHasStockVo> collect = skuIds.stream().map(skuId -> {
            SkuHasStockVo vo = new SkuHasStockVo();

            //查询当前sku的总库存量
            //SELECT SUM(stock-stock_locked) FROM `wms_ware_sku` WHERE sku_id=1
            Long count = baseMapper.getSkuStock(skuId);

            vo.setSkuId(skuId);
            vo.setHasStock(count == null ? false : count > 0);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

}
```

4、`mapper`接口 `WareSkuDao`：

```java
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    Long getSkuStock(Long skuId);
}
```

5、`WareSkuDao.xml`文件：

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

    <select id="getSkuStock" resultType="java.lang.Long">
        SELECT SUM(stock - stock_locked)
        FROM `wms_ware_sku`
        WHERE sku_id = #{skuId}
    </select>

</mapper>
```

6、在`gulimall-product`商品服务的`feign`包下添加远程调用接口`WareFeignService`：

```java
@FeignClient("gulimall-ware")
public interface WareFeignService {
    /**
     * 1、R设计的时候可以加上泛型
     * 2、直接返回我们想要的结果
     * 3、自己封装解析结果
     */
    @PostMapping("/ware/waresku/hasstock")
    R getSkusHasStock(@RequestBody List<Long> skuIds);
}
```

<!-- TOC --><a name="6es"></a>
##### 6.将数据发送给ES进行保存

1、在`gulimall-search`检索服务中添加`ElasticSaveController`类，进行上架商品的ES模型保存

```java
@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {

    @Autowired
    ProductSaveService productSaveService;

    //上架商品
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        boolean b = false;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        } catch (Exception e) {
            log.error("ElasticSaveController商品上架错误：{}", e);
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }

        if (!b) {
            return R.ok();
        } else {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }
}
```

2、`ProductSaveService`类：

```java
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
```

3、`ProductSaveServiceImpl`实现类：

```java
@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

        //保存到es
        //1、给es中建立索引。product，建立好映射关系。

        //2、给es中保存这些数据
        //BulkRequest bulkRequest, RequestOptions options
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            //1、构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String s = JSON.toJSONString(model);
            indexRequest.source(s, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        //   如果批量错误
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        log.info("商品上架完成：{}，返回数据：{}", collect, bulk.toString());

        return b;
    }
}
```

4、在`gulimall-product`商品服务的`feign`包下添加远程调用接口：

```java
@FeignClient("gulimall-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
```

<!-- TOC --><a name="7spu"></a>
##### 7.修改当前spu的状态

`SpuInfoDao`类的`updateSpuStatus`方法：

```java
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {
    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}
```

`SpuInfoDao.xml`配置文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimail.product.dao.SpuInfoDao">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimail.product.entity.SpuInfoEntity" id="spuInfoMap">
        <result property="id" column="id"/>
        <result property="spuName" column="spu_name"/>
        <result property="spuDescription" column="spu_description"/>
        <result property="catalogId" column="catalog_id"/>
        <result property="brandId" column="brand_id"/>
        <result property="weight" column="weight"/>
        <result property="publishStatus" column="publish_status"/>
        <result property="createTime" column="create_time"/>
        <result property="updateTime" column="update_time"/>
    </resultMap>
    
    <update id="updateSpuStatus">
        UPDATE `pms_spu_info`
        SET publish_status=#{code},
            update_time=NOW()
        WHERE id = #{spuId}
    </update>
    
</mapper>
```

<!-- TOC --><a name="8eses"></a>
##### 8.填充ES属性模型并发送给ES保存

1、完整的`SpuInfoServiceImpl`保存实现：填充ES属性模型并发送给ES保存

```java
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private SearchFeignService searchFeignService;
    /**
     * 商品上架
     */
    @Override
    public void up(Long spuId) {
        // 1.查出当前spuid对应的所有sku信息，品牌的名字
        List<SkuInfoEntity> skus = skuInfoService.getSkusBySpuId(spuId);
        List<Long> skuIdList = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());


        // 2.查询当前sku的所有可以被用来检索的规格属性
        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(attr -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        // 在指定的所有属性集合里面挑出可检索的属性
        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);


        Set<Long> idSet = new HashSet<>(searchAttrIds);


        List<SkuEsModel.Attrs> attrsList = baseAttrs.stream().filter(item -> {
            return idSet.contains(item.getAttrId());
        }).map(item -> {
            SkuEsModel.Attrs attrs1 = new SkuEsModel.Attrs();
            BeanUtils.copyProperties(item, attrs1);
            return attrs1;
        }).collect(Collectors.toList());


        // 发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            R r = wareFeignService.getSkusHasStock(skuIdList);
            TypeReference<List<SkuHasStockVo>> typeReference = new TypeReference<List<SkuHasStockVo>>() {};
            stockMap = r.getData(typeReference).stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));
        } catch (Exception e) {
            log.error("库存服务查询异常，原因：{}", e);
        }
        Map<Long, Boolean> finalStockMap = stockMap;

        // 2.封装每个sku的信息
        List<SkuEsModel> upProducts = skus.stream().map(sku -> {
            // 组装需要的数据
            SkuEsModel esModel = new SkuEsModel();
            // 属性对拷
            BeanUtils.copyProperties(sku, esModel);
            // 单独处理SkuEsModel与SkuInfoEntity中字段含义相同但是命名不同的字段
            // （skuPrice,skuImg，hasStock,hotScore、brandName、brandImg）
            esModel.setSkuPrice(sku.getPrice());
            esModel.setSkuImg(sku.getSkuDefaultImg());

            // 当前sku商品是否有库存
            if (finalStockMap == null) {
                // 如果远程调用出现问题，设置为有库存
                esModel.setHasStock(true);
            } else {
                // 如果远程成功，设置是否有库存
                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
            }


            // 热度评分。刚上架的商品默认给成0.这个操作此处就不实现了
            esModel.setHotScore(0L);

            // 3. 查询品牌和分类的名字和信息
            BrandEntity brand = brandService.getById(esModel.getBrandId());
            esModel.setBrandName(brand.getName());
            esModel.setBrandImg(brand.getLogo());

            CategoryEntity category = categoryService.getById(esModel.getCatalogId());
            esModel.setCatalogName(category.getName());
            // 设置检索属性
            esModel.setAttrs(attrsList);


            return esModel;
        }).collect(Collectors.toList());

        // 5.将数据发送给es进行保存，请求检索服务 gulimall-search 进行保存
        R r = searchFeignService.productStatusUp(upProducts);
        if (r.getCode() == 0) {
            //远程调用成功
            // 6、修改当前spu的状态
            baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
        } else {
            //远程调用失败
            //   7、Todo 重复调用？接口幂等性；重试机制？xxx
            //Feign调用流程
            /**
             * 1、构造请求数据，将对象转为json；
             *      RequestTemplate template = buildTemplateFromArgs.create(argv);
             * 2、发送请求进行执行（执行成功会解码响应数据）：
             *      executeAndDecode(template);
             * 3、执行请求会有重试机制
             *      while(true){
             *          try{
             *            executeAndDecode(template);
             *          }catch(){
             *              try{retryer.continueOrPropagate(e);}catch(){throw ex;}
             *              continue;
             *          }
             *
             *      }
             */
        }

    }

}
```

2、调试时对R对象的更改：利用fastjson对进行逆转，使其变为指定类型

```java
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    //利用fastjson进行逆转
    public <T> T getData(String key, TypeReference<T> typeReference){
        Object data = get(key);//默认是map
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }

    //利用fastjson进行逆转
    public <T> T getData(TypeReference<T> typeReference){
        Object data = get("data");//默认是map
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }


    public R setData(Object data){
        put("data",data);
        return this;
    }

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {

        return (Integer) this.get("code");
    }

}
```

测试：点击上架，上架状态变为`已上架`



![image-20230523035605765](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305240315053.png)



<!-- TOC --><a name="8"></a>

##  8.商品服务页面

<!-- TOC --><a name="81-"></a>

###  8.1 项目的微服务架构

本课程的微服务项目原本都是前后端分离项目，但是为了不让前端页面的设计细节被忽视，处于教学的考虑，之后会将前端页面(视图)放到各自的微服务中，但是出于性能的考虑，将静态资源放到`nginx`中,完整架构图如下：

![image-20230524034530691](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271846015.png)

用户的使用请求都经过`nginx`进行处理，`nginx`作为反向代理将请求全部转发给网关，网关再路由到各个微服务，网关的好处是可以做统一的鉴权认证、限流工作。添加`nginx`以后，我们可以将页面放到各个微服务中，页面引用的静态资源则放到`nginx`中，由此实现部署的动静分离，这么做的好处是分担微服务的压力：假设将静态资源放到微服务，那请求静态资源时`请求`也要转到微服务，微服务的`tomcat`都要建立连接进行处理。如果不设计为`动静分离`的形式，假设有3000个请求到达微服务，可能有2000个都是处理图片等静态资源的，那系统的并发度就会降低很多

<!-- TOC --><a name="82-thymeleaf"></a>
### 8.2 整合thymeleaf

**Thymeleaf**

- 优点：自然化语言，编写好以后前后端都可以修改。方便前后端人员的分工合作

- 缺点：性能较其他模板引擎来说较差。但是如果在生产环境开启缓存功能，其性能也较优

将`分布式高级篇`中`首页资源`下的文件拷贝到`gulimall-product`的`resources\static`目录下,将`index.html`拷贝到`template`目录下：

![image-20230524042904532](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271846814.png)

引入`thymeleaf `依赖：

```xml
       <!-- 模板引擎： thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

配置：关闭缓存，配置关闭缓存，这样在开发期间就可以实时的看到变化的数据

```yaml
spring:
  thymeleaf:
    cache: falses
```

引入dev-tools：页面修改不重启服务器实时更新

```xml
        <!-- devtools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
```

`thymeleaf`模板引擎整合：

```sql
5、模板引擎
        1）、thymeleaf-starter：关闭缓存
        2）、静态资源都放在static文件夹下就可以按照路径直接访问
        3）、页面放在templates下，直接访问
               SpringBoot，访问项目的时候，默认会找index
        4）、页面修改不重启服务器实时更新
               1）、引入dev-tools
               2)、修改完页面 controller shift f9重新自动编译下页面，代码配置，推荐重启
```

<!-- TOC --><a name="83-"></a>
### 8.3 渲染一级分类数据

在`gulimall-product`商品服务下新建`web`包，在`web`包下新建`IndexController`用于首页的跳转

**1.`IndexController`类：**

- 访问首页时查出一级分类，并将这些一级分类相关的数据放到视图中，在`index.html`中就可以取出这些数据进行渲染

```java
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "index.html"})
    public String indexPage(Model model) {
        // 1.查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();

        // 2.将查出的数据放到视图中，之后就可以在index.html 文件中取出这些数据对页面进行渲染
        model.addAttribute("categorys",categoryEntities);

        // 视图解析器进行拼串：默认前缀classpath:/templates/  默认后缀.html
        // classpath:/templates/ +返回值+  .html
        return "index";
    }
}
```

**2.在`index.html`中对数据进行渲染**

- 在`templates\index.html`中加入`thymeleaf`的名称空间，有了`thymeleaf`的名称空间就可以在html中使用`thymeleaf`的语法进行代码编写：

```xml
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <!--轮播主体内容-->
    <div class="header_main">
        <!--   通过$符获取categorys：<div th:text="${categorys}"> </div>-->
        <div class="header_banner">
            <div class="header_main_left">
                <ul>
                    <li th:each="category : ${categorys}">
                        <a href="#" class="header_main_left_a" th:attr="ctg-data=${category.catId}"><b th:text="${category.name}">家用电器</b></a>
                    </li>
                </ul>
            </div>
    </div>
```



<!-- TOC --><a name="84-"></a>
###  8.4 渲染二级、三级分类数据

2级分类vo：

```java
//2级分类vo
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catelog2Vo {
    private String catalog1Id; //1级父分类id
    private List<Catelog3Vo> catalog3List;  //三级子分类
    private String id;
    private String name;
    /**
     * 三级分类vo
     *  "catalog2Id":"1",
     *                     "id":"1",
     *                     "name":"电子书"
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Catelog3Vo{
        private String catalog2Id;//父分类，2级分类id
        private String id;
        private String name;
    }

}
```

`IndexController`类：

```java
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 渲染二级、三级分类数据
     * /index/catalog.json
     */
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }
}
```

`CategoryService`类：

```java
public interface CategoryService extends IService<CategoryEntity> {
    Map<String, List<Catelog2Vo>> getCatalogJson();
}
```

`CategoryServiceImpl`类：

```java
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    /**
     * 获取一级分类列表
     */
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        // 1.查出所有分类1级分类
        List<CategoryEntity> level1Categorys = getLevel1Categorys();


        // 2.封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));

            // 3.封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    // 4、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", l2.getCatId()));
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            // 封装成指定格式
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(),l3.getCatId().toString(),l3.getName());

                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);

                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));

        return parent_cid;
    }

}
```

启动`gulimall-product`商品服务，访问`http://localhost:10001/#`   ,看到如下效果，可以看到一级、二级分类、三级分类渲染成功：

![image-20230526232440980](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271847503.png)

<!-- TOC --><a name="9"></a>
## 9.搭建域名访问环境

希望访问首页的时候不是通过`localhost:10001`这个URL,而是`gulimall.com`。

按照正常流程，如果项目要上线，就需要买一台服务器，再申请一个公网IP地址，为这个公网IP地址绑定域名，同时需要做一些备案操作 ，用户通过该公网IP(域名)访问我们的服务器。由于现在是开发环境，我们先搭建基本环境，上线后才做正规流程

<!-- TOC --><a name="91-"></a>

### 9.1 反向代理配置

<!-- TOC --><a name="1"></a>
#####  1.正向代理与反向代理

正向与反向是对于我们的电脑来说的，如果它帮我们上网，则是正向代理。如果是帮对方对方服务器则是反向代理

![image-20230527104513541](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271847028.png)

正向代理的例子：科学上网。我们访问Google访问不上，那么我们可以搭建一台代理服务器，为我们的电脑配置上代理服务器的地址，之后访问所有的网址时，代理服务器都帮我们来访问，代理服务器拿到数据后再返回给我们的电脑。搭建的这个服务器是帮我们上网，所以这个场景就是正向代理。科学上网访问互联网时，由于是代理服务器帮我们上网，所以互联网上查看访问来自哪个IP地址时，看到的来源就是反向代理服务器的IP

![image-20230527104536339](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271847441.png)

反向代理：反向代理在我们搭建集群环境时发挥作用。我们的`谷粒商城` 有各种后台集群，为了安全起见，不可能把每个服务器的内外ip暴露给用户访问(这样做任意引起攻击)，所以这些集群的服务器都要在内网部署。  为了让用户可以找到我们的服务集群，可以在这些集群的前面放置一个反向代理服务器，比如放置一个nginx,这个nginx具有一个公网IP可以将请求代理到集群。这个代理服务器可以屏蔽内网服务器信息，并实现负载均衡访问

<!-- TOC --><a name="2nginxwindows"></a>
##### 2.Nginx+Windows搭建域名访问环境

- 在windows的hosts文件中配置域名与虚拟机IP地址的映射
- 在浏览器中访问这个域名时，先查看hosts文件中的域名映射规则，如果有映射就直接访问该地址。如果hosts中没有映射规则，则需要去网络上的DNS对域名进行解析，获取到对应的IP地址后再进行访问
- 由于我们将nginx装到了虚拟机上，所以将域名`gulimall.com`指定到虚拟机IP地址`192.168.56.10`

![image-20230527001850177](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271847257.png)



**配置hosts:**

- 可在`C:\Windows\System32\drivers\etc\hosts`直接进行修改,但此处我们利用`switchhost`软件进行修改：安装`switchhost`软件,解压后以管理员身份打开，打开后在本地方案中添加方案并命名为`gilimall`,添加域名映射规则后点击勾号使系统应用这个方案。测试：此时访问`http://gulimall.com:9200/`,成功访问到部署在虚拟机上的`elasticsearch`,说明配置生效

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271848400.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271848953.png" > <b>2</b></td>
</tr>
</table>  

<!-- TOC --><a name="3nginx"></a>
#####   3.nginx配置文件

- 基于上面的配置，启动虚拟机上的nginx,那访问`gulimall.com`时就会来到nginx的首页。接下来配置nginx ,让nginx帮我们进行反向代理。让nginx将来自`gulimall.com`的请求都转发到网关

```sh
docker update nginx --restart=always
docker restart nginx
```

- 来到虚拟机中`nginx`的`conf`目录,其中`nginx.conf`文件就是`nginx`的总配置。另外还有一个`conf.d`目录，`conf.d`目录下的所有配置都会被合并到`nginx.conf`中并生效，进而起作用(`nginx.conf`中有一段配置为`include /etc/nginx/conf. d/*.conf;`)

```sh
cd /mydata/nginx/conf
ls
# conf.d          koi-utf  mime.types  nginx.conf   uwsgi_params
# fastcgi_params  koi-win  modules     scgi_params  win-utf
```

- `nginx`的总配置文件`nginx.conf`的具体配置及作用见下表：



![image-20230527010137087](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271850108.png)

- 配置文件使用案例：将配置`conf.d`下的配置文件`default.conf`拷贝一份并命名为`gulimall.conf`，更改其中的配置，使得访问`gulimall.com`域名时可以访问到正在windows上运行的`gulimall-product`商品服务的首页

```sh
cd /mydata/nginx/conf/conf.d
sudo cp default.conf gulimall.conf
sudo vi gulimall.conf
sudo docker restart nginx
```

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271851718.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271851366.png" > <b>2</b></td>
</tr>
</table>  

测试：访问`http://gulimall.com/`或`http://192.168.28.186:10001/`，发现都可以访问到商品服务`gulimall-product`的首页

<!-- TOC --><a name="4nginx"></a>
#####  4.配置nginx将请求转发到网关

在分布式情况下，每个微服务都可能不止部署一个，如果nginx直接将请求路由给各个服务就比较麻烦。所以这里配置nginx，让nginx把请求路由到网关，网关再把请求负载均衡到各个微服务

1、在`nginx.conf`中配置上游`upstream`，此`upstream`对应windows本地的88端口(即我们的网关端口) 

2、在`gulimall.conf`中监听80端口，并将请求路由到本地的网关

3、**重要** ：nginx代理给网关的时候，会丢失请求的host信息  。**解决方法**：`proxy_set_header Host $host`

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271853578.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305271853900.png" > <b>2</b></td>
</tr>
</table>  



4、在网关服务`gulimall-gateway`的`application.yml`配置文件中配置转发规则：

```yaml
spring:
  cloud:
    gateway:
      routes:
## 将nginx过来的请求转发到商品服务
        - id: gulimall_host_route
          uri: lb://gulimall-product
          predicates:
            - Host=gulimall.com,item.gulimall.com
```

5、测试：访问`http://gulimall.com/`或`http://192.168.28.186:10001/`，发现都可以访问到商品服务`gulimall-product`的首页

<!-- TOC --><a name="5"></a>
#####   5.当前域名映射的效果

经过上面的配置，当前的域名映射的效果为

1、`gulimall.com`:  可以请求接口

2、`gulimall.com` :   可以请求首页页面

3、nginx直接代理给网关，网关判断

- 如果/api/**，转交给对应的服务器
- 如果是满足域名，转交给对应的服务

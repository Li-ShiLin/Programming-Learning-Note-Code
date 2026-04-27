本目录汇总 ShardingSphere 学习路径中的环境准备、核心原理、Sharding-JDBC 分库分表实践、Sharding-Proxy 配置与测试文档。

---

## 环境与基础文档

| 文档 | 核心内容 |
| --- | --- |
| [mysql-5.7.38.md](mysql-5.7.38.md) | MySQL 5.7.38 安装与基础配置，支撑后续 ShardingSphere 实验环境准备 |
| [tutorial.md](tutorial.md) | ShardingSphere 核心概念总览：分库分表、读写分离、Sharding-JDBC 与 Sharding-Proxy 实践教程 |

---

## 示例模块

| 文档 | 核心内容 |
| --- | --- |
| [SS-01-Table-Horizontal-Sharding/README.md](SS-01-Table-Horizontal-Sharding/README.md) | 水平分表示例：同库多表分片规则配置、路由策略与代码验证 |
| [SS-02-Databases-Horizontal-Sharding/README.md](SS-02-Databases-Horizontal-Sharding/README.md) | 水平分库示例：多数据源分片配置、分库路由与查询验证 |
| [SS-03-Database-Vertical-Sharding/README.md](SS-03-Database-Vertical-Sharding/README.md) | 垂直分库示例：按业务拆分数据库实现专库专表 |
| [SS-04-BroadCast-Table/README.md](SS-04-BroadCast-Table/README.md) | 广播表（公共表）示例：跨库字典表同步与查询实践 |
| [SS-05-Read-Write-Separation/README.md](SS-05-Read-Write-Separation/README.md) | 读写分离示例：主从数据源配置、读写路由与测试验证 |
| [SS-06-Sharding-Proxy-Table-Sharding/README.md](SS-06-Sharding-Proxy-Table-Sharding/README.md) | Sharding-Proxy 分表与分库配置说明、连接方式与验证步骤 |
| [SS-06-Sharding-Proxy-Table-Sharding/测试步骤.md](SS-06-Sharding-Proxy-Table-Sharding/测试步骤.md) | 分表测试执行清单：建表、插入、路由验证与日志核查 |

---

## 官方文档资料

| 文档 | 核心内容 |
| --- | --- |
| 快速入门 | 1. ShardingSphere 产品定位与总体能力 [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)  2. JDBC 侧接入与规则配置入口 [ShardingSphere-JDBC 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/)  3. Proxy 侧部署与治理入口 [ShardingSphere-Proxy 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-proxy/) |
| 数据分片 | 1. 分片规则 YAML 配置总览 [ShardingSphere 数据分片](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/)  2. 分片策略配置与路由方式 [分片策略](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/#分片策略)  3. 内置与自定义分片算法 [分片算法](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/#分片算法) |
| 读写分离 | 1. 读写分离规则配置与语义 [ShardingSphere 读写分离](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/readwrite-splitting/)  2. 主从读写规则示例 [配置示例](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/readwrite-splitting/#配置示例) |
| 分布式事务 | 1. 事务规则配置总览 [ShardingSphere 分布式事务](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/transaction/)  2. 强一致 XA 事务模式 [XA 事务](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/transaction/#xa-事务)  3. 柔性事务 BASE 模式 [BASE 事务](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/transaction/#base-事务) |
| Sharding-Proxy | 1. Proxy YAML 配置与治理能力 [Sharding-Proxy 配置](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-proxy/yaml-config/) |


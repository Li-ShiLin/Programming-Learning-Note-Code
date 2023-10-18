<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1. Linux 文件与目录结构](#1-linux-%E6%96%87%E4%BB%B6%E4%B8%8E%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84)
  - [1.1 Linux文件](#11-linux%E6%96%87%E4%BB%B6)
  - [1.2 Linux目录结构](#12-linux%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84)
- [2. VIM、VI编辑器](#2-vimvi%E7%BC%96%E8%BE%91%E5%99%A8)
  - [2.1 简介](#21-%E7%AE%80%E4%BB%8B)
  - [2.2 一般模式](#22-%E4%B8%80%E8%88%AC%E6%A8%A1%E5%BC%8F)
  - [2.3 编辑模式](#23-%E7%BC%96%E8%BE%91%E6%A8%A1%E5%BC%8F)
  - [2.4 指令模式](#24-%E6%8C%87%E4%BB%A4%E6%A8%A1%E5%BC%8F)
  - [2.5 模式间转换](#25-%E6%A8%A1%E5%BC%8F%E9%97%B4%E8%BD%AC%E6%8D%A2)
- [3.linux虚拟机网络配置](#3linux%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%BD%91%E7%BB%9C%E9%85%8D%E7%BD%AE)
  - [3.1 网络配置](#31-%E7%BD%91%E7%BB%9C%E9%85%8D%E7%BD%AE)
  - [3.2 补充: 桥接模式 |NAT模式 | 仅主机模式](#32-%E8%A1%A5%E5%85%85-%E6%A1%A5%E6%8E%A5%E6%A8%A1%E5%BC%8F-nat%E6%A8%A1%E5%BC%8F--%E4%BB%85%E4%B8%BB%E6%9C%BA%E6%A8%A1%E5%BC%8F)
  - [3.3 配置网络ip地址](#33-%E9%85%8D%E7%BD%AE%E7%BD%91%E7%BB%9Cip%E5%9C%B0%E5%9D%80)
      - [3.3.1 ifconfig 查看网络接口配置信息](#331-ifconfig-%E6%9F%A5%E7%9C%8B%E7%BD%91%E7%BB%9C%E6%8E%A5%E5%8F%A3%E9%85%8D%E7%BD%AE%E4%BF%A1%E6%81%AF)
      - [3.3.2 ping 测试主机之间网络连通性](#332-ping-%E6%B5%8B%E8%AF%95%E4%B8%BB%E6%9C%BA%E4%B9%8B%E9%97%B4%E7%BD%91%E7%BB%9C%E8%BF%9E%E9%80%9A%E6%80%A7)
      - [3.3.3 修改IP地址](#333-%E4%BF%AE%E6%94%B9ip%E5%9C%B0%E5%9D%80)
      - [3.3.4 修改IP地址后可能会遇到的问题](#334-%E4%BF%AE%E6%94%B9ip%E5%9C%B0%E5%9D%80%E5%90%8E%E5%8F%AF%E8%83%BD%E4%BC%9A%E9%81%87%E5%88%B0%E7%9A%84%E9%97%AE%E9%A2%98)
  - [3.4 配置主机名](#34-%E9%85%8D%E7%BD%AE%E4%B8%BB%E6%9C%BA%E5%90%8D)
  - [3.5 修改hosts映射文件](#35-%E4%BF%AE%E6%94%B9hosts%E6%98%A0%E5%B0%84%E6%96%87%E4%BB%B6)
  - [3.6 远程登录](#36-%E8%BF%9C%E7%A8%8B%E7%99%BB%E5%BD%95)
- [4. 系统管理](#4-%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86)
  - [4.1 service & systemctl  服务管理](#41-service--systemctl--%E6%9C%8D%E5%8A%A1%E7%AE%A1%E7%90%86)
      - [4.1.1 CentOS 6版本](#411-centos-6%E7%89%88%E6%9C%AC)
      - [4.1.2 CentOS 7版本](#412-centos-7%E7%89%88%E6%9C%AC)
  - [4.2 系统运行级别](#42-%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E7%BA%A7%E5%88%AB)
  - [4.3 关闭防火墙](#43-%E5%85%B3%E9%97%AD%E9%98%B2%E7%81%AB%E5%A2%99)
  - [4.4 关机重启命令](#44-%E5%85%B3%E6%9C%BA%E9%87%8D%E5%90%AF%E5%91%BD%E4%BB%A4)
- [5. 常用基本命令](#5-%E5%B8%B8%E7%94%A8%E5%9F%BA%E6%9C%AC%E5%91%BD%E4%BB%A4)
  - [5.1 帮助命令](#51-%E5%B8%AE%E5%8A%A9%E5%91%BD%E4%BB%A4)
      - [5.1.1  man 获得帮助信息](#511--man-%E8%8E%B7%E5%BE%97%E5%B8%AE%E5%8A%A9%E4%BF%A1%E6%81%AF)
      - [5.1.2  help 获得shell内置命令的帮助信息](#512--help-%E8%8E%B7%E5%BE%97shell%E5%86%85%E7%BD%AE%E5%91%BD%E4%BB%A4%E7%9A%84%E5%B8%AE%E5%8A%A9%E4%BF%A1%E6%81%AF)
      - [5.1.3 history 查看运行过的所有命令](#513-history-%E6%9F%A5%E7%9C%8B%E8%BF%90%E8%A1%8C%E8%BF%87%E7%9A%84%E6%89%80%E6%9C%89%E5%91%BD%E4%BB%A4)
      - [5.1.4 常用快捷键](#514-%E5%B8%B8%E7%94%A8%E5%BF%AB%E6%8D%B7%E9%94%AE)
  - [5.2 文件目录类](#52-%E6%96%87%E4%BB%B6%E7%9B%AE%E5%BD%95%E7%B1%BB)
      - [5.2.1 pwd 显示当前工作目录的绝对路径](#521-pwd-%E6%98%BE%E7%A4%BA%E5%BD%93%E5%89%8D%E5%B7%A5%E4%BD%9C%E7%9B%AE%E5%BD%95%E7%9A%84%E7%BB%9D%E5%AF%B9%E8%B7%AF%E5%BE%84)
      - [5.2.2  ls 列出目录的内容](#522--ls-%E5%88%97%E5%87%BA%E7%9B%AE%E5%BD%95%E7%9A%84%E5%86%85%E5%AE%B9)
      - [5.2.3 cd切换目录](#523-cd%E5%88%87%E6%8D%A2%E7%9B%AE%E5%BD%95)
      - [5.2.4  mkdir 创建一个新的目录](#524--mkdir-%E5%88%9B%E5%BB%BA%E4%B8%80%E4%B8%AA%E6%96%B0%E7%9A%84%E7%9B%AE%E5%BD%95)
      - [5.2.5 rmdir 删除一个空的目录](#525-rmdir-%E5%88%A0%E9%99%A4%E4%B8%80%E4%B8%AA%E7%A9%BA%E7%9A%84%E7%9B%AE%E5%BD%95)
      - [5.2.6 touch创建空文件](#526-touch%E5%88%9B%E5%BB%BA%E7%A9%BA%E6%96%87%E4%BB%B6)
      - [5.2.7  cp 复制文件或目录](#527--cp-%E5%A4%8D%E5%88%B6%E6%96%87%E4%BB%B6%E6%88%96%E7%9B%AE%E5%BD%95)
      - [5.2.8 rm 删除文件或目录](#528-rm-%E5%88%A0%E9%99%A4%E6%96%87%E4%BB%B6%E6%88%96%E7%9B%AE%E5%BD%95)
      - [5.2.9 mv移动文件与目录或重命名](#529-mv%E7%A7%BB%E5%8A%A8%E6%96%87%E4%BB%B6%E4%B8%8E%E7%9B%AE%E5%BD%95%E6%88%96%E9%87%8D%E5%91%BD%E5%90%8D)
      - [5.2.10 cat 查看文件内容](#5210-cat-%E6%9F%A5%E7%9C%8B%E6%96%87%E4%BB%B6%E5%86%85%E5%AE%B9)
      - [5.2.11 more 文件内容分屏查看器](#5211-more-%E6%96%87%E4%BB%B6%E5%86%85%E5%AE%B9%E5%88%86%E5%B1%8F%E6%9F%A5%E7%9C%8B%E5%99%A8)
      - [5.2.12 less 分屏显示文件内容](#5212-less-%E5%88%86%E5%B1%8F%E6%98%BE%E7%A4%BA%E6%96%87%E4%BB%B6%E5%86%85%E5%AE%B9)
      - [5.2.13 echo 输出内容到控制台](#5213-echo-%E8%BE%93%E5%87%BA%E5%86%85%E5%AE%B9%E5%88%B0%E6%8E%A7%E5%88%B6%E5%8F%B0)
      - [5.2.14 head 显示开头部分内容](#5214-head-%E6%98%BE%E7%A4%BA%E5%BC%80%E5%A4%B4%E9%83%A8%E5%88%86%E5%86%85%E5%AE%B9)
      - [5.2.15 tail 输出文件尾部内容](#5215-tail-%E8%BE%93%E5%87%BA%E6%96%87%E4%BB%B6%E5%B0%BE%E9%83%A8%E5%86%85%E5%AE%B9)
      - [5.2.16 `>`  覆写   `>>`  追加 （输出重定向）](#5216---%E8%A6%86%E5%86%99-----%E8%BF%BD%E5%8A%A0-%E8%BE%93%E5%87%BA%E9%87%8D%E5%AE%9A%E5%90%91)
      - [5.2.17  ln  软链接](#5217--ln--%E8%BD%AF%E9%93%BE%E6%8E%A5)
      - [5.2.18 history查看已经执行过历史命令](#5218-history%E6%9F%A5%E7%9C%8B%E5%B7%B2%E7%BB%8F%E6%89%A7%E8%A1%8C%E8%BF%87%E5%8E%86%E5%8F%B2%E5%91%BD%E4%BB%A4)
  - [5.3 时间日期类](#53-%E6%97%B6%E9%97%B4%E6%97%A5%E6%9C%9F%E7%B1%BB)
      - [5.3.1 date 显示当前日期](#531-date-%E6%98%BE%E7%A4%BA%E5%BD%93%E5%89%8D%E6%97%A5%E6%9C%9F)
      - [5.3.2 date 显示非当前时间](#532-date-%E6%98%BE%E7%A4%BA%E9%9D%9E%E5%BD%93%E5%89%8D%E6%97%B6%E9%97%B4)
      - [5.3.3 date 设置系统时间](#533-date-%E8%AE%BE%E7%BD%AE%E7%B3%BB%E7%BB%9F%E6%97%B6%E9%97%B4)
      - [5.3.4 cal查看日历](#534-cal%E6%9F%A5%E7%9C%8B%E6%97%A5%E5%8E%86)
  - [5.4 用户管理命令](#54-%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86%E5%91%BD%E4%BB%A4)
      - [5.4.1 useradd 添加新用户](#541-useradd-%E6%B7%BB%E5%8A%A0%E6%96%B0%E7%94%A8%E6%88%B7)
      - [5.4.2 passwd 设置用户密码](#542-passwd-%E8%AE%BE%E7%BD%AE%E7%94%A8%E6%88%B7%E5%AF%86%E7%A0%81)
      - [5.4.3 id 查看用户是否存在](#543-id-%E6%9F%A5%E7%9C%8B%E7%94%A8%E6%88%B7%E6%98%AF%E5%90%A6%E5%AD%98%E5%9C%A8)
      - [5.4.4  cat /etc/passwd   查看系统创建了哪些用户](#544--cat-etcpasswd---%E6%9F%A5%E7%9C%8B%E7%B3%BB%E7%BB%9F%E5%88%9B%E5%BB%BA%E4%BA%86%E5%93%AA%E4%BA%9B%E7%94%A8%E6%88%B7)
      - [5.4.5 su 切换用户](#545-su-%E5%88%87%E6%8D%A2%E7%94%A8%E6%88%B7)
      - [5.4.6 userdel 删除用户](#546-userdel-%E5%88%A0%E9%99%A4%E7%94%A8%E6%88%B7)
      - [5.4.7 who查看登录用户信息](#547-who%E6%9F%A5%E7%9C%8B%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF)
      - [5.4.8  sudo 设置普通用户具有root权限](#548--sudo-%E8%AE%BE%E7%BD%AE%E6%99%AE%E9%80%9A%E7%94%A8%E6%88%B7%E5%85%B7%E6%9C%89root%E6%9D%83%E9%99%90)
      - [5.4.9  usermod  修改用户](#549--usermod--%E4%BF%AE%E6%94%B9%E7%94%A8%E6%88%B7)
  - [5.5 用户组管理命令](#55-%E7%94%A8%E6%88%B7%E7%BB%84%E7%AE%A1%E7%90%86%E5%91%BD%E4%BB%A4)
      - [5.5.1 groupadd  新增组](#551-groupadd--%E6%96%B0%E5%A2%9E%E7%BB%84)
      - [5.5.2  groupdel 删除组](#552--groupdel-%E5%88%A0%E9%99%A4%E7%BB%84)
      - [5.5.3 groupmod 修改组](#553-groupmod-%E4%BF%AE%E6%94%B9%E7%BB%84)
      - [5.5.4  cat /etc/group 查看创建了哪些组](#554--cat-etcgroup-%E6%9F%A5%E7%9C%8B%E5%88%9B%E5%BB%BA%E4%BA%86%E5%93%AA%E4%BA%9B%E7%BB%84)
      - [5.5.5 综合练习](#555-%E7%BB%BC%E5%90%88%E7%BB%83%E4%B9%A0)
  - [5.6 文件权限类](#56-%E6%96%87%E4%BB%B6%E6%9D%83%E9%99%90%E7%B1%BB)
      - [5.6.1 文件属性](#561-%E6%96%87%E4%BB%B6%E5%B1%9E%E6%80%A7)
      - [5.6.2 chmod  改变权限](#562-chmod--%E6%94%B9%E5%8F%98%E6%9D%83%E9%99%90)
      - [5.6.3 chown  改变所属者](#563-chown--%E6%94%B9%E5%8F%98%E6%89%80%E5%B1%9E%E8%80%85)
      - [5.6.4 chgrp  改变所属组](#564-chgrp--%E6%94%B9%E5%8F%98%E6%89%80%E5%B1%9E%E7%BB%84)
  - [5.7 搜索查找类](#57-%E6%90%9C%E7%B4%A2%E6%9F%A5%E6%89%BE%E7%B1%BB)
      - [5.7.1 find 查找文件或者目录](#571-find-%E6%9F%A5%E6%89%BE%E6%96%87%E4%BB%B6%E6%88%96%E8%80%85%E7%9B%AE%E5%BD%95)
      - [5.7.2 locate  快速定位文件路径](#572-locate--%E5%BF%AB%E9%80%9F%E5%AE%9A%E4%BD%8D%E6%96%87%E4%BB%B6%E8%B7%AF%E5%BE%84)
      - [5.7.3  grep  过滤查找](#573--grep--%E8%BF%87%E6%BB%A4%E6%9F%A5%E6%89%BE)
      - [5.7.4   |  管道](#574-----%E7%AE%A1%E9%81%93)
  - [5.8  压缩和解压类](#58--%E5%8E%8B%E7%BC%A9%E5%92%8C%E8%A7%A3%E5%8E%8B%E7%B1%BB)
      - [5.8.1 gzip/gunzip压缩](#581-gzipgunzip%E5%8E%8B%E7%BC%A9)
      - [5.8.2 zip/unzip 打包和压缩](#582-zipunzip-%E6%89%93%E5%8C%85%E5%92%8C%E5%8E%8B%E7%BC%A9)
      - [5.8.3 tar 打包](#583-tar-%E6%89%93%E5%8C%85)
  - [5.9  磁盘查看和分区类](#59--%E7%A3%81%E7%9B%98%E6%9F%A5%E7%9C%8B%E5%92%8C%E5%88%86%E5%8C%BA%E7%B1%BB)
      - [5.9.1 du   查看文件和目录占用的磁盘空间](#591-du---%E6%9F%A5%E7%9C%8B%E6%96%87%E4%BB%B6%E5%92%8C%E7%9B%AE%E5%BD%95%E5%8D%A0%E7%94%A8%E7%9A%84%E7%A3%81%E7%9B%98%E7%A9%BA%E9%97%B4)
      - [5.9.2 df  查看磁盘空间使用情况](#592-df--%E6%9F%A5%E7%9C%8B%E7%A3%81%E7%9B%98%E7%A9%BA%E9%97%B4%E4%BD%BF%E7%94%A8%E6%83%85%E5%86%B5)
      - [5.9.3  lsblk  查看设备挂载情况](#593--lsblk--%E6%9F%A5%E7%9C%8B%E8%AE%BE%E5%A4%87%E6%8C%82%E8%BD%BD%E6%83%85%E5%86%B5)
      - [5.9.4 mount/umount  挂载/卸载](#594-mountumount--%E6%8C%82%E8%BD%BD%E5%8D%B8%E8%BD%BD)
      - [5.9.5 fdisk 磁盘分区](#595-fdisk-%E7%A3%81%E7%9B%98%E5%88%86%E5%8C%BA)
  - [5.10 进程管理类](#510-%E8%BF%9B%E7%A8%8B%E7%AE%A1%E7%90%86%E7%B1%BB)
      - [5.10.1 ps  查看当前系统进程状态](#5101-ps--%E6%9F%A5%E7%9C%8B%E5%BD%93%E5%89%8D%E7%B3%BB%E7%BB%9F%E8%BF%9B%E7%A8%8B%E7%8A%B6%E6%80%81)
      - [5.10.2  kill 终止进程](#5102--kill-%E7%BB%88%E6%AD%A2%E8%BF%9B%E7%A8%8B)
      - [5.10.3 pstree 查看进程树](#5103-pstree-%E6%9F%A5%E7%9C%8B%E8%BF%9B%E7%A8%8B%E6%A0%91)
      - [5.10.4  top 实时监控系统进程状态](#5104--top-%E5%AE%9E%E6%97%B6%E7%9B%91%E6%8E%A7%E7%B3%BB%E7%BB%9F%E8%BF%9B%E7%A8%8B%E7%8A%B6%E6%80%81)
      - [5.10.5 netstat 显示网络状态和端口占用信息](#5105-netstat-%E6%98%BE%E7%A4%BA%E7%BD%91%E7%BB%9C%E7%8A%B6%E6%80%81%E5%92%8C%E7%AB%AF%E5%8F%A3%E5%8D%A0%E7%94%A8%E4%BF%A1%E6%81%AF)
  - [5.11 crontab 系统定时任务](#511-crontab-%E7%B3%BB%E7%BB%9F%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1)
      - [5.11.1 crontab 服务管理](#5111-crontab-%E6%9C%8D%E5%8A%A1%E7%AE%A1%E7%90%86)
      - [5.11.2 crontab 定时任务设置](#5112-crontab-%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1%E8%AE%BE%E7%BD%AE)
- [6.软件包管理](#6%E8%BD%AF%E4%BB%B6%E5%8C%85%E7%AE%A1%E7%90%86)
  - [6.1 RPM](#61-rpm)
      - [6.1.1  RPM概述](#611--rpm%E6%A6%82%E8%BF%B0)
      - [6.1.2 RPM 查询命令](#612-rpm-%E6%9F%A5%E8%AF%A2%E5%91%BD%E4%BB%A4)
      - [6.1.3 RPM 卸载命令](#613-rpm-%E5%8D%B8%E8%BD%BD%E5%91%BD%E4%BB%A4)
      - [6.1.4 RPM 安装命令](#614-rpm-%E5%AE%89%E8%A3%85%E5%91%BD%E4%BB%A4)
  - [6.2 YUM 仓库配置](#62-yum-%E4%BB%93%E5%BA%93%E9%85%8D%E7%BD%AE)
      - [6.2.1 YUM概述](#621-yum%E6%A6%82%E8%BF%B0)
      - [6.2.2 YUM的常用命令](#622-yum%E7%9A%84%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4)
      - [6.2.3 修改网络YUM源](#623-%E4%BF%AE%E6%94%B9%E7%BD%91%E7%BB%9Cyum%E6%BA%90)
- [7.shell入门](#7shell%E5%85%A5%E9%97%A8)
  - [7.1 shell 概述](#71-shell-%E6%A6%82%E8%BF%B0)
  - [7.2 shell脚本格式](#72-shell%E8%84%9A%E6%9C%AC%E6%A0%BC%E5%BC%8F)
  - [7.3 脚本的常用执行方式](#73-%E8%84%9A%E6%9C%AC%E7%9A%84%E5%B8%B8%E7%94%A8%E6%89%A7%E8%A1%8C%E6%96%B9%E5%BC%8F)
- [8. shell变量](#8-shell%E5%8F%98%E9%87%8F)
  - [8.1 系统预定义变量](#81-%E7%B3%BB%E7%BB%9F%E9%A2%84%E5%AE%9A%E4%B9%89%E5%8F%98%E9%87%8F)
  - [8.2 自定义变量](#82-%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8F%98%E9%87%8F)
  - [8.3 特殊变量](#83-%E7%89%B9%E6%AE%8A%E5%8F%98%E9%87%8F)
      - [8.3.1 $n](#831-n)
      - [8.3.2 $#](#832-)
      - [8.3.3 $*、$@](#833-)
      - [8.3.4 $？](#834-)
- [9. shell 运算符](#9-shell-%E8%BF%90%E7%AE%97%E7%AC%A6)
- [10. shell 条件判断](#10-shell-%E6%9D%A1%E4%BB%B6%E5%88%A4%E6%96%AD)
- [11. shell 流程控制](#11-shell-%E6%B5%81%E7%A8%8B%E6%8E%A7%E5%88%B6)
  - [11.1 if 判断](#111-if-%E5%88%A4%E6%96%AD)
  - [11.2 case 语句](#112-case-%E8%AF%AD%E5%8F%A5)
  - [11.3 for 循环](#113-for-%E5%BE%AA%E7%8E%AF)
  - [11.4 while 循环](#114-while-%E5%BE%AA%E7%8E%AF)
- [12. 读取控制台输入](#12-%E8%AF%BB%E5%8F%96%E6%8E%A7%E5%88%B6%E5%8F%B0%E8%BE%93%E5%85%A5)
- [13. 函数](#13-%E5%87%BD%E6%95%B0)
  - [13.1 系统函数](#131-%E7%B3%BB%E7%BB%9F%E5%87%BD%E6%95%B0)
      - [13.1.1   basename 获取路径里的文件名称](#1311---basename-%E8%8E%B7%E5%8F%96%E8%B7%AF%E5%BE%84%E9%87%8C%E7%9A%84%E6%96%87%E4%BB%B6%E5%90%8D%E7%A7%B0)
      - [13.1.2 dirname  获取文件路径的绝对路径](#1312-dirname--%E8%8E%B7%E5%8F%96%E6%96%87%E4%BB%B6%E8%B7%AF%E5%BE%84%E7%9A%84%E7%BB%9D%E5%AF%B9%E8%B7%AF%E5%BE%84)
  - [13.2 自定义函数](#132-%E8%87%AA%E5%AE%9A%E4%B9%89%E5%87%BD%E6%95%B0)
- [14. 综合应用案例一](#14-%E7%BB%BC%E5%90%88%E5%BA%94%E7%94%A8%E6%A1%88%E4%BE%8B%E4%B8%80)
  - [14.1 归档练习](#141-%E5%BD%92%E6%A1%A3%E7%BB%83%E4%B9%A0)
- [15. 正则表达式](#15-%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F)
  - [15.1 常规匹配](#151-%E5%B8%B8%E8%A7%84%E5%8C%B9%E9%85%8D)
  - [15.2 常用特殊字符](#152-%E5%B8%B8%E7%94%A8%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6)
      - [15.2.1 特殊字符 ^](#1521-%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6-%5E)
      - [15.2.2 特殊字符 $](#1522-%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6-)
      - [15.2.3 特殊字符 .](#1523-%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6-)
      - [15.2.4 特殊字符 *](#1524-%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6-)
      - [15.2.5 字符区间 [ ]](#1525-%E5%AD%97%E7%AC%A6%E5%8C%BA%E9%97%B4--)
      - [15.2.6 特殊字符  \](#1526-%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6--%5C)
- [16. 文本处理工具](#16-%E6%96%87%E6%9C%AC%E5%A4%84%E7%90%86%E5%B7%A5%E5%85%B7)
  - [16.1 cut](#161-cut)
  - [16.2 awk](#162-awk)
      - [16.2.1 awk 初步使用](#1621-awk-%E5%88%9D%E6%AD%A5%E4%BD%BF%E7%94%A8)
      - [16.2.1  awk的内置变量](#1621--awk%E7%9A%84%E5%86%85%E7%BD%AE%E5%8F%98%E9%87%8F)
- [17. 综合案例练习二](#17-%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B%E7%BB%83%E4%B9%A0%E4%BA%8C)
  - [17.1 发送消息](#171-%E5%8F%91%E9%80%81%E6%B6%88%E6%81%AF)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

##  1. Linux 文件与目录结构

###  1.1 Linux文件

Linux系统中一切皆文件

###  1.2 Linux目录结构

![image-20231008233527967](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190439413.png)

**Linux目录结构**：

| 目录                | 作用                                                         |
| ------------------- | ------------------------------------------------------------ |
| **/root**           | 该目录为系统管理员，也称作**超级权限者的用户主目录**         |
| **bin**             | 是Binary的缩写,这个目录**存放着最经常使用的命令**            |
| **/sbin**           | s就是Super User的意思，这里**存放系统管理员使用的系统管理程序** |
| **/home**           | **存放普通用户的主目录**，在Linux中每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的 |
| **lib**             | **系统开机所需要最基本的动态连接共享库**，其作用类似于Windows里的DLL文件。几乎所有的应用程序都需要用到这些共享库 |
| **/lost+found**     | 这个目录一般情况下是空的，当**系统非法关机后，这里就存放了一些文件** |
| **/etc**            | 所有的系统管理所需要的配置文件和子目录                       |
| **/usr**            | 这是一个非常重要的目录，用户的很多**应用程序和文件都放在这个目录**下，类似于windows下的program files目录 |
| **/boot**           | 这里**存放的是启动Linux时使用的一些核心文件**，包括一些连接文件以及镜像文件**，自己的安装别放这里** |
| **/proc**           | **这个目录是一个虚拟的目录，它是系统内存的映射**，我们**可以通过直接访问这个目录来获取系统信息** |
| **/srv**            | service缩写，该目录**存放一些服务启动之后需要提取的数据**    |
| **/sys**            | 这是linux2.6内核的一个很大的变化。该目录下**安装了2.6内核中新出现的一个文件系统sysfs** |
| **/tmp**            | 这个目录是用来**存放一些临时文件**的                         |
| **/dev**            | 类似于windows的设备管理器，把**所有的硬件用文件的形式存储**  |
| **/media(CentOs6)** | linux系统会自动识别一些设备，例如U盘、光驱等等，当识别后，**linux会把识别的设备挂载到这个目录**下。CentOS7迁移到`/run/media` |
| **/mnt**            | 系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以**将外部的存储挂载在`/mnt/`上**，然后进入该目录就可以查看里的内容了 |
| **/opt**            | 这是给**主机额外安装软件所摆放的目录**。比如你安装一个mysql数据库则就可以放到这个目录下。默认是空的 |
| **/var**            | 这个目录中存放着在不断扩充着的东西，我们习惯将那些经常被修改的目录放在这个目录下。包括各种日志文件 |



**补充**：在 Linux 系统中，有几个目录是比较重要的，平时需要注意不要误删除或者随意更改内部文件

- **/etc**： 上边也提到了，这个是系统中的配置文件，如果你更改了该目录下的某个文件可能会导致系统不能启动
- **/bin, /sbin, /usr/bin, /usr/sbin**: 这是系统预设的执行文件的放置目录，比如 **ls** 就是在 **/bin/ls** 目录下的
- 值得提出的是 **/bin**、**/usr/bin** 是给系统用户使用的指令（除 root 外的通用用户），而/sbin, /usr/sbin 则是给 root 使用的指令
- **/var**： 这是一个非常重要的目录，系统上跑了很多程序，那么每个程序都会有相应的日志产生，而这些日志就被记录到这个目录下，具体在`/var/log`目录下，另外 mail 的预设放置也是在这里

##  2. VIM、VI编辑器

###  2.1 简介

VI是Unix 操作系统和类Unix 操作系统中最通用的文本编辑器

VIM编辑器是从VI发展出来的一个性能更强大的文本编辑器。可以主动的以字体颜色辨别语法的正确性，方便程序设计。VIM 与VI编辑器完全兼容

### 2.2 一般模式

以vi打开一个档案就直接进入一般模式了(这是默认的模式)

在这个模式中，你可以使用『上下左右』按键来移动光标，你可以使用『删除字符』或『删除整行』来处理档案内容，也可以使用『复制、粘贴』来处理你的文件数据

常用语法：

| 语法         | 功能描述                      |
| ------------ | ----------------------------- |
| yy           | 复制光标当前一行              |
| y数字y       | 复制一段（从第几行到第几行）  |
| p            | 箭头移动到目的行粘贴          |
| u            | 撤销上一步                    |
| dd           | 删除光标当前行                |
| d数字d       | 删除光标(含）后多少行         |
| x            | 剪切一个字母，相当于del       |
| X            | 剪切一个字母，相当于Backspace |
| yw           | 复制一个词                    |
| dw           | 删除一个词                    |
| shift+6 (^)  | 移动到行头                    |
| shift+4 ($)  | 移动到行尾                    |
| gg           | 移动到页头，数字              |
| shift+g      | 移动到页尾                    |
| 数字+shift+g | 移动到目标行                  |


![image-20231009024839305](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190440697.png)


###  2.3 编辑模式

在一般模式中可以进行删除、复制、粘贴等的动作，但是却无法编辑文件内容的。要等到你按下『i,I, o,O, a,A」等任何一个字母之后才会进入编辑模式

**注意**：通常在Linux中，按下这些按键时，在画面的左下方会出现`INSERT`或`REPLACE`的字样，此时才可以进行编辑。而**如果要回到一般模式**时，则必须要**按下【ESC】这个按键**即可退出编辑模式

**进入编辑模式常用语法**：

| 按键 | 功能               |
| ---- | ------------------ |
| i    | 当前光标前         |
| a    | 当前光标后         |
| o    | 当前光标行的下一行 |
| l    | 光标所在行最前     |
| A    | 光标所在行最后     |
| o    | 当前光标行的上一行 |

**退出编辑模式**：

按「Esc」键退出编辑模式，之后所在的模式为一般模式

###  2.4 指令模式

在一般模式当中，输入【: /  ?】3个中的任何一个按钮，就可以将光标移动到最底下那一行。
在这个模式当中，可以提供你『搜寻资料』的动作，而读取、存盘、大量取代字符、离开vi、显示行号等动作是在此模式中达成的

**基本语法**：

| 命令          | 功能                                     |
| ------------- | ---------------------------------------- |
| :w            | 保存                                     |
| :q            | 退出                                     |
| :!            | 强制执行                                 |
| /要查找的词   | n查找下一个，N往上查找                   |
| :noh          | 取消高亮显示                             |
| :set nu       | 显示行号                                 |
| :set nonu     | 关闭行号                                 |
| :/old/new     | 替换当前行匹配到的第一个old 为new        |
| :/old/new/g   | 替换当前行匹配到的所有old 为new          |
| :%s/old/new   | 替换文档中每一行匹配到的第一个old为newe- |
| :%s/old/new/g | 替换内容/g 替换匹配到的所有内容          |

###  2.5 模式间转换

![image-20231009030907514](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190440299.png)

##  3.linux虚拟机网络配置

###  3.1 网络配置

**一、查看虚拟网络编辑器**

![image-20231011031409252](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190440410.png)

**二、修改虚拟网卡Ip**

![image-20231011031534352](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190440378.png)



**三、查看网关**

![image-20231011031714515](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190440079.png)

**四、查看windows环境的中 VMnet8网络配置**

![image-20231011031805438](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190441789.png)



###  3.2 补充: 桥接模式 |NAT模式 | 仅主机模式

VMware提供了三种网络连接模式：

- 桥接模式、NAT模式、仅主机模式

**桥接模式**：

- 虚拟机直接连接外部物理网络的模式，主机起到了网桥的作用。这种模式下，虚拟机可以直接访问外部网络，并且对外部网络是可见的

**NAT模式**：

- 虚拟机和主机构建一个专用网络，并通过虚拟网络地址转换(NAT）设备对IP进行转换。虚拟机通过共享主机P可以访问外部网络，但外部网络无法访问虚拟机

**仅主机模式**：

- 虚拟机只与主机共享一个专用网络，与外部网络无法通信

###  3.3 配置网络ip地址

#####  3.3.1 ifconfig 查看网络接口配置信息

`ifconfig`命令:  网络接口配置 `network interfaces configuring`

| 基本语法 | 功能描述                   |
| -------- | -------------------------- |
| ifconfig | 显示所有网络接口的配置信息 |

演示：查看当前网络ip

```
[root@hadoop100 桌面]# ifconfig
```

#####  3.3.2 ping 测试主机之间网络连通性

| 基本语法      | 功能描述                           |
| ------------- | ---------------------------------- |
| ping 目的主机 | 测试当前服务器是否可以连接目的主机 |

```
# 演示：测试当前服务器是否可以连接百度
ping www.baidu. com
```

##### 3.3.3 修改IP地址

**一、查看IP配置文件**：

```
vim /etc/sysconfig/network-scripts/ifcfg-ens33
```

**二、修改IP配置**：

以下标红的项必须修改，有值的按照下面的值修改，没有该项的要增加

![image-20231011033503902](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190441148.png)

修改后：

```
TYPE="Ethernet" #网络类型（通常是 Ethemet）
PROXY_METHOD="none"
BROWSER_ONLY="no"
BOOTPROTO="static" #IP 的配置方法[none|static|bootp|dhcp]（引导
时不 使用协议|静态分配 IP|BOOTP 协议|DHCP 协议）
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
IPV6_ADDR_GEN_MODE="stable-privacy"
NAME="ens33"
UUID="e83804c1-3257-4584-81bb-660665ac22f6" #随机 id
DEVICE="ens33" #接口名（设备,网卡）
ONBOOT="yes" #系统启动的时候网络接口是否有效（yes/no）
#IP 地址
IPADDR=192.168.1.100
#网关
GATEWAY=192.168.1.2
#域名解析器
DNS1=192.168.1.2
```

**三、执行service network restart重启网络**

#####  3.3.4 修改IP地址后可能会遇到的问题

1.物理机能 ping 通虚拟机，但是虚拟机 ping 不通物理机,一般都是因为物理机的 防火墙问题,把防火墙关闭就行

2.虚拟机能 Ping 通物理机，但是虚拟机 Ping 不通外网,一般都是因为 DNS 的设置有 问题 

3.虚拟机`Ping www.baidu.com `显示域名未知等信息,一般查看 GATEWAY 和 DNS 设 置是否正确 

4.如果以上全部设置完还是不行，需要关闭 NetworkManager 服务

- `systemctl stop NetworkManager` 关闭

- `systemctl disable NetworkManager` 禁用 

5.如果检查发现 systemctl status network 有问题 需要检查ifcfg-ens33

###  3.4 配置主机名

**一、查看主机名**

| 命令     | 功能描述                 |
| -------- | ------------------------ |
| hostname | 查看当前服务器的主机名称 |

**二、修改主机名**

通过编辑/etc/hostname文件，修改完成后重启生效

###  3.5 修改hosts映射文件

**一、修改linux 的主机映射文件(hosts文件)**

后续在 hadoop阶段，虚拟机会比较多，配置时通常会采用主机名的方式配置，比较简单方便。不用刻意记 ip地址

**一、编辑linux虚拟机的`/etc/hosts`文件**

```
vim /etc/hosts
# 添加以下内容
192.168.2.100 hadoop100
192.168.2.101 hadoop101
192.168.2.102 hadoop102
192.168.2.103 hadoop103
192.168.2.104 hadoop104
192.168.2.105 hadoop105
```

二、修改windows的主机映射文件(hosts文件)

1.进入`C:\Windows\System32\drivers\etc`路径 

2.打开 hosts 文件并添加如下内容

```
192.168.2.100 hadoop100
192.168.2.101 hadoop101
192.168.2.102 hadoop102
192.168.2.103 hadoop103
192.168.2.104 hadoop104
192.168.2.105 hadoop105
```

###  3.6 远程登录

通常在工作过程中，公司中使用的真实服务器或者是云服务器，都不允许除运维人员之外的员工直接接触，因此就需要通过远程登录的方式来操作。所以，远程登录工具就是必不可缺的，目前，比较主流的有Xshell, SSH Secure Shell, SecureCRT,FinalShell等

##  4. 系统管理

### 4.1 service & systemctl  服务管理

**Linux中的进程和服务**：

- 一个正在执行的程序或命令，被叫做“进程”( process)
- 启动之后一只存在、常驻内存的进程，一般被称作“服务”( service)

#####  4.1.1 CentOS 6版本

**service 服务管理**：

基本语法：`service 服务名 start | stop |  restart | status`

| 命令                      | 功能描述           |
| ------------------------- | ------------------ |
| `service network status`  | 查看网络服务的状态 |
| `service network stop`    | 停止网络服务       |
| `service network start`   | 启动网络服务       |
| `service network restart` | 重启网络服务       |

**chkconfig 设置后台服务的自启**：

| 基本语法               | 功能描述               |
| ---------------------- | ---------------------- |
| chkconfig              | 查看所有服务器自启配置 |
| chkconfig 服务名 off   | 关掉指定服务的自动启动 |
| chkconfig 服务名on     | 开启指定服务的自动启动 |
| chkconfig 服务名--list | 查看服务开机启动状态   |

#####  4.1.2 CentOS 7版本

**systemctl 服务管理（CentOS 7版本）**：

基本语法：`systemctl start | stop | restart | status 服务名`

示例：

| 命令                          | 描述                 |
| ----------------------------- | -------------------- |
| `systemctl status firewalld`  | 查看防火墙服务的状态 |
| `systemctl stop firewalld`    | 停止防火墙服务       |
| `systemctl start firewalld`   | 启动防火墙服务       |
| `systemctl restart firewalld` | 重启防火墙服务       |

**systemctl设置后台服务的自启**：

| 基本语法                         | 功能描述               |
| -------------------------------- | ---------------------- |
| `systemctl list-unit-files`      | 查看服务开机启动状态   |
| `systemctl disable service_name` | 关掉指定服务的自动启动 |
| `systemctl enable service_name`  | 开启指定服务的自动启动 |

开启/关闭iptables(防火墙)服务的自动启动

```
systemctl enable firewalld.service
systemctl disable firewalld.servic
```

### 4.2 系统运行级别

**Linux进程运行级别（CentOS 6）**：

![image-20231011045830518](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190441348.png)

查看默认级别: `vi /etc/inittab`

Linux系统有7种运行级别(runlevel)：**常用的是级别3和5**

- **运行级别0**：系统停机状态，系统默认运行级别不能设为0，否则不能正常启动
- **运行级别1**：单用户工作状态，root权限，用于系统维护，禁止远程登陆
- **运行级别2**：多用户状态(没有NFS)，不支持网络
- **运行级别3**：完全的多用户状态(有NFS)，登陆后进入控制台命令行模式
- **运行级别4**：系统未使用，保留
- **运行级别5**：X11控制台，登陆后进入图形GUI模式
- **运行级别6**：系统正常关闭并重启，默认运行级别不能设为6，否则不能正常启动

**CentOS 7的运行级别简化为 ** :

`multi-user.target`  等价于原运行级别3（多用户有网，无图形界面)

`graphical.target` 等价于原运行级别5 (多用户有网，有图形界面)

**查看当前运行级别**：

`systemctl get-default`

**修改当前运行级别**：

`systemctl set-default TARGET.target` (这里TARGET取 multi-user或者graphical)

###  4.3 关闭防火墙

**临时关闭防火墙**：

查看防火墙状态

```sh
systemctl status firewalld
```

临时关闭防火墙

```sh
systemctl stop firewalld
```

**开机启动时关闭防火墙**：

查看防火墙开机启动状态

```sh
systemctl enable firewalld.service
```

设置开机时关闭防火墙

```sh
systemctl disable firewalld.service
```

###  4.4 关机重启命令

在linux 领域内大多用在服务器上，很少遇到关机的操作。毕竟服务器上跑一个服务是
永无止境的，除非特殊情况下，不得已才会关机

| 基本命令语法         | 功能描述                        |
| -------------------- | ------------------------------- |
| sync                 | 将数据由内存同步到硬盘中        |
| halt                 | 停机，关闭系统，但不断电        |
| poweroff             | 关机，断电                      |
| reboot               | 就是重启，等同于shutdown -r now |
| shutdown [选项] 时间 | 在指定的时间关机                |

shutdown命令参数：

| 选项 | 功能                           |
| ---- | ------------------------------ |
| -H   | 相当于--halt，停机             |
| -r   | -r=reboot重启                  |
| now  | 立刻关机                       |
| 时间 | 等待多久后关机(时间单位是分钟) |

**示例**：

让计算机在 1 分钟后关机，并且会显示在登录用户的当前屏幕中

```
shutdown -h 1
```

立马关机（等同于 poweroff）

```sh
shutdown -h now
```

系统立马重启（等同于 reboot）

```sh
shutdown -r now
```

**经验技巧**：

Linux系统中为了提高磁盘的读写效率，对磁盘采取了“预读迟写”操作方式。当用户保存文件时，Linux核心并不一定立即将保存数据写入物理磁盘中，而是将数据保存在缓冲区中，等缓冲区满时再写入磁盘，这种方式可以极大的提高磁盘写入数据的效率。但是，也带来了安全隐患，如果数据还未写入磁盘时，系统掉电或者其他严重问题出现，则将导致数据丢失。使用sync指令可以立即将缓冲区的数据写入磁盘



## 5. 常用基本命令
Shell可以看作是一个命令解释器，为我们提供了交互式的文本控制台界面。我们可以通过终端控制台来输入命令，由shell进行解释并最终交给内核执行。本章就将分类介绍常用的基本shell命令

###  5.1 帮助命令

#####  5.1.1  man 获得帮助信息

基本语法
```sh
man [命令或配置文件]
```

命令的显示说明

| 信息        | 功能                    |
| ----------- | ----------------------- |
| NAME        | 命令的名称和单行描述    |
| SYNOPSIS    | 怎样使用命令            |
| DESCRIPTION | 命令功能的深入讨论      |
| EXAMPLES    | 怎样使用命令的例子      |
| SEE ALSO    | 相关主题（通常是手册页) |

案例实操：查看ls命令的帮助信息

```sh
man ls
```

#####  5.1.2  help 获得shell内置命令的帮助信息

一部分基础功能的系统命令是直接内嵌在shell中的，系统加载启动之后会随着shell一起加载，常驻系统内存中。这部分命令被称为“内置(built-in）命令”;相应的其它命令被称为“外部命令”

基本语法
```sh
help 命令
```

案例实操：查看cd命令的帮助信息

```sh
help cd
```

#####  5.1.3 history 查看运行过的所有命令

```sh
#查看运行过的所有命令
history
```

##### 5.1.4 常用快捷键

| 常用快捷键  | 功能                                |
| ----------- | ----------------------------------- |
| ctrl + c    | 停止进程                            |
| ctrl+l      | 清屏，等同于clear;彻底清屏是: reset |
| 善于用tab键 | 提示(更重要的是可以防止敲错)        |
| 上下键      | 查找执行过的命令                    |

###  5.2 文件目录类

##### 5.2.1 pwd 显示当前工作目录的绝对路径

```sh
pwd
```

#####  5.2.2  ls 列出目录的内容

```sh
ls [选项][目录或是文件]
```

选项说明

| 选项 | 功能                                                      |
| ---- | --------------------------------------------------------- |
| -a   | 全部的文件，连同隐藏文档(开头为.的文件)一起列出来(常用)   |
| -l   | 长数据串列出，包含文件的属性与权限等等数据;(常用)等价于ll |

示例：查看当前目录的所有内容信息

```sh
ls -al
```

显示说明：每行列出的信息依次是: 文件类型与权限 、链接数 、文件属主 、文件属组、文件大小用byte来表示、建立或最近修改的时间、名字

#####  5.2.3 cd切换目录

```sh
cd [参数]
```

参数说明：

| 参数        | 功能                                 |
| ----------- | ------------------------------------ |
| cd 绝对路径 | 切换路径                             |
| cd 相对路径 | 切换路径                             |
| cd~或者cd   | 回到自己的家目录                     |
| cd -        | 回到上一次所在目录                   |
| cd ..       | 回到当前目录的上一级目录             |
| cd -p       | 跳转到实际物理路径，而非快捷方式路径 |

#####  5.2.4  mkdir 创建一个新的目录

```sh
mkdir [选项] 要创建的目录
```

选项说明：

| 选项 | 功能         |
| ---- | ------------ |
| -p   | 创建多层目录 |

创建一个目录

```sh
mkdir xiyou
mkdir xiyou/mingjie
```

创建一个多级目录

```sh
mkdir -p xiyou/dssz/meihouwang
```

#####  5.2.5 rmdir 删除一个空的目录

```sh
rmdir 要删除的空目录
```

删除一个空的文件夹：

```sh
rmdir xiyou/dssz/meihouwang
```

#####  5.2.6 touch创建空文件

```sh
touch 文件名称
touch xiyou/dssz/sunwukong.txt
```

#####  5.2.7  cp 复制文件或目录

```sh
# 功能描述: 复制source文件到dest
cp [选项] source dest
# 案例：
cp xiyou/dssz/suwukong.txt xiyou/mingjie
```

选项说明：

| 选项 | 功能               |
| ---- | ------------------ |
| -r   | 递归复制整个文件夹 |

强制覆盖不提示的方法：

```sh
/cp
```

#####  5.2.8 rm 删除文件或目录

```sh
# 语法：
rm [选项] deleteFile
# 案例：删除目录中的内容
rm xiyou/mingjie/sunwukong.txt
# 案例：强制删除a/
rm -rf a/
# 案例：强制删除当前目录下的所有文件
rm -rf ./*
```

选项说明：

| 选项 | 功能                                   |
| ---- | -------------------------------------- |
| -r   | 递归删除目录中所有内容                 |
| -f   | 强制执行删除操作，而不提示用于进行确认 |
| -V   | 显示指令的详细执行过程                 |

##### 5.2.9 mv移动文件与目录或重命名

```sh
# 语法1： 重命名
mv oldNameFile newNameFile 
# 语法2:  移动文件
mv /temp/movefile /targetFolder  
# 示例：重命名
mv xiyou/dssz/suwukong.txt  xiyou/dssz/houge.txt
# 示例
mv xiyou/dssz/houge.txt  ./
```

#####  5.2.10 cat 查看文件内容

查看文件内容，从第一行开始显示。一般利用cat查看比较小的文件，一屏幕能显示全的

```sh
cat [选项] 要查看的文件
# 示例：查看文件内容并显示行号
cat -n houge.txt
```

选项说明：

| 选项 | 功能描述                   |
| ---- | -------------------------- |
| -n   | 显示所有行的行号，包括空行 |

#####  5.2.11 more 文件内容分屏查看器

more指令是一个基于VI编辑器的文本过滤器，它以全屏幕的方式按页显示文本文件的内容。more指令中内置了若干快捷键，详见操作说明

```sh
# 基本语法
more 要查看的文件
```

操作说明：

| 操作          | 功能说明                              |
| ------------- | ------------------------------------- |
| 空白键(space) | 代表向下翻一页                        |
| Enter         | 代表向下翻一行                        |
| q             | 代表立刻离开more ，不再显示该文件内容 |
| Ctrl+F        | 向下滚动一屏                          |
| Ctrl+B        | 返回上一屏                            |
| =             | 输出当前行的行号                      |
| :f            | 输出文件名和当前行的行号              |

#####  5.2.12 less 分屏显示文件内容

less指令用来分屏查看文件内容，它的功能与more指令类似，但是比 more指令更加强大，支持各种显示终端。less 指令在显示文件内容时，并不是一次将整个文件加载之后才显示，而是根据显示需要加载内容，对于显示大型文件具有较高的效率

```sh
# 基本语法
less 要查看的文件
# 案例：采用less查看文件
less smartd.conf
```

操作说明：

| 操作       | 功能说明                                       |
| ---------- | ---------------------------------------------- |
| 空白键     | 向下翻动一页                                   |
| [pagedown] | 向下翻动一页                                   |
| [pageup]   | 向上翻动一页                                   |
| /字串      | 向下搜寻字串的功能。 n: 向下查找;  N：向上查找 |
| ?字串      | 向上搜寻字串的功能。  n：向上查找; N：向下查找 |
| q          | 离开less这个程序                               |

用`SecureCRT`时[pagedown]和[pageup]可能会出现无法识别的问题

#####  5.2.13 echo 输出内容到控制台

```sh
# 基本语法
echo [选项][输出内容]

# 使用示例：
[root@localhost test]# echo "hello\tword"
hello\tword
[root@localhost test]# echo -e "hello\tword"
hello   word
```

选项：

| 选项 | 选项描述                 |
| ---- | ------------------------ |
| -e   | 支持反斜线控制的字符转换 |

控制字符：

| 控制字符 | 作用                |
| -------- | ------------------- |
| `\\`     | 输出\本身           |
| \n       | 换行符              |
| \t       | 制表符，也就是Tab键 |

#####  5.2.14 head 显示开头部分内容
默认情况下head指令显示文件的前10行内容

```sh
# 基本语法
head 文件
# 案例：查看文件头5行内容
head -n 5 文件
# 查看文件的头2行
head -n 2 smartd.conf
```

选项说明：

| 选项     | 功能                   |
| -------- | ---------------------- |
| -n  行数 | 指定显示头部内容的行数 |

#####  5.2.15 tail 输出文件尾部内容

默认情况下tail指令显示文件的后10行内容

```sh
# 查看文件尾部10行内容
tail 文件

# 查看文件尾部5行内容
tail -n 5 文件

# 实时追踪该文档的所有更新
tail -f 文件

# 查看文件尾1行内容
tail -n l smartd.conf

# 实时追踪该档的所有更新
tail -f houge.txt
```

选项说明：

| 选项    | 功能                                 |
| ------- | ------------------------------------ |
| -n 行数 | 输出文件尾部n行内容                  |
| -f      | 显示文件最新追加的内容，监视文件变化 |

#####  5.2.16 `>`  覆写   `>>`  追加 （输出重定向）

```sh
# 列表的内容写入文件（覆盖写）
ls -1 > 文件
# 列表的内容写到文件的末尾（追加）
ls -al >> 文件
# 将 ls 查看信息写入到文件
ls -l > houge.txt
# 将 ls 查看信息追加到文件
ls -l >> houge.txt
# 采用 echo 将 hello 单词追加
echo hello >> houge
```

#####  5.2.17  ln  软链接

软链接也称为符号链接，类似于windows里的快捷方式，有自己的数据块，主要存放了链接其他文件的路径。也类似C语言中的指针

```sh
# 基本语法: 给原文件创建一个软链接
ln -s [原文件或目录] [软链接名]
```

创建软连接

```sh
[root@hadoop101 ~]# mv houge.txt xiyou/dssz/
[root@hadoop101 ~]# ln -s xiyou/dssz/houge.txt ./houzi
[root@hadoop101 ~]# ll
lrwxrwxrwx. 1 root root 20 6 月 17 12:56 houzi ->
xiyou/dssz/houge.txt
```

经验技巧：

- 删除软链接: `rm -rf  软链接名`，而不是`rm -rf  软链接名/`
- 如果使用`rm -rf 软链接名/` 删除，会把软链接对应的真实目录下内容删掉
- 修改：对软连接内容进行vim或vi编辑，原文件也会进行更改
- 查询：通过ll就可以查看，列表属性第1位是l，尾部会有位置指向

#####  5.2.18 history查看已经执行过历史命令

```sh
# 查看已经执行过历史命令
history
# 清除历史命令记录
history -c
```

###  5.3 时间日期类

#####  5.3.1 date 显示当前日期

```sh
# 基本语法
date [OPTION]... [+FORMAT]

# 显示当前时间
date

# 显示当前年份
date +%Y

# 显示当前月份
date +%m

# 显示当前是哪一天
date +%d
# 显示年月日时分秒
date "+%Y-%m-%d %H:%M:%S"
```

选项说明：

| 选项            | 功能                                           |
| --------------- | ---------------------------------------------- |
| -d <时间字符串> | 显示指定的“时间字符串”表示的时间，而非当前时间 |
| -s <日期时间>   | 设置系统日期时间                               |

参数说明：

| 参数            | 功能                         |
| --------------- | ---------------------------- |
| <+日期时间格式> | 指定显示时使用的日期时间格式 |

#####  5.3.2 date 显示非当前时间

```sh
# 显示前一天时间
date -d '1 days ago'

# 显示明天时间
date -d '-1 days ago'
```

#####  5.3.3 date 设置系统时间

```sh
# 设置系统时间
date -s 字符串时间

# 设置系统当前时间
date -s "2017-06-19 20:52:18”
```

#####  5.3.4 cal查看日历

```sh
# 基本语法
cal [选项]

# 不加选项，显示本月日历
cal

# 查看 2017 年的日历
cal 2017

# 查看前三个月的日历
cal -3
```

选项说明：

| 选项       | 功能             |
| ---------- | ---------------- |
| 具体某一年 | 显示这一年的日历 |

###   5.4 用户管理命令

#####  5.4.1 useradd 添加新用户

```sh
# 添加新用户
useradd 用户名

# 添加新用户到某个组
useradd -g 组名 用户名

# 添加一个用户
useradd tangseng

# 创建一个新用户david,并指定他的主目录为/home/dave
useradd -d /home/dave david
```

#####  5.4.2 passwd 设置用户密码

```sh
# 设置用户密码
passwd 用户名

# 修改david的密码
[root@localhost home]# passwd david
Changing password for user david.
New password:
```

#####  5.4.3 id 查看用户是否存在
```sh
# 查看用户是否存在
id 用户名

# 查看david用户是否存在
[root@localhost vagrant]# id david
uid=1001(david) gid=1001(david) groups=1001(david)
```

#####  5.4.4  cat /etc/passwd   查看系统创建了哪些用户

```sh
# 查看系统创建了哪些用户
cat /etc/passwd
less /etc/passwd
```

#####  5.4.5 su 切换用户

```sh
# 切换用户，只能获得用户的执行权限，不能获得环境变量
su 用户名称

# 切换到用户并获得该用户的环境变量及执行权限
su -用户名称

# 切换用户
[root@hadoop101 ~]#su - tangseng
[root@hadoop101 ~]#echo $PATH
/usr/lib64/qt3.3/bin:/usr/local/bin:/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/sbin:/home/t
angseng/bin
```

#####  5.4.6 userdel 删除用户

```sh
# 删除用户但保存用户主目录
userdel 用户名

# 用户和用户主目录都删除
userdel -r 用户名
```

选项说明：

| 选项 | 功能                                     |
| ---- | ---------------------------------------- |
| -r   | 删除用户的同时，删除与用户相关的所有文件 |

#####  5.4.7 who查看登录用户信息

```sh
# 显示自身用户名称
whoami

# 显示登录用户的用户名以及登陆时间
who am i
```

#####  5.4.8  sudo 设置普通用户具有root权限

```sh
# 添加 atguigu 用户，并对其设置密码
[root@hadoop101 ~]#useradd atguigu
[root@hadoop101 ~]#passwd atguigu

# 修改配置文件
[root@hadoop101 ~]#vi /etc/sudoers

# 修改 /etc/sudoers 文件，找到下面一行(91 行)，在 root 下面添加一行，如下：
#    ## Allow root to run any commands anywhere
#    root ALL=(ALL) ALL
#    atguigu ALL=(ALL) ALL

# 或者配置成采用 sudo 命令时，不需要输入密码：
#    ## Allow root to run any commands anywhere
#    root ALL=(ALL) ALL
#    atguigu ALL=(ALL) NOPASSWD:ALL

# 修改完毕，现在可以用 atguigu 帐号登录，然后用命令 sudo ，
# 即可获得 root 权限进行操作


# 用普通用户在/opt目录下创建一个文件夹
sudo mkdir module
```

#####  5.4.9  usermod  修改用户

```sh
# 基本语法
usermod -g 用户组 用户名

# 将用户加入到用户组
usermod -g root david
```

选项说明：

| 选项 | 功能                                                |
| ---- | --------------------------------------------------- |
| -g   | 修改用户的初始登录组，给定的组必须存在。默认组id是1 |

###  5.5 用户组管理命令

每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同Linux系统对用户组的规定有所不同，如Linux下的用户属于与它同名的用户组，这个用户组在创建用户时同时创建。用户组的管理涉及用户组的添加、删除和修改。组的增加、删除和修改实际上就是对`/etc/group`文件的更新

##### 5.5.1 groupadd  新增组

```sh
#  新增组
groupadd 组名
```

#####  5.5.2  groupdel 删除组

```sh
groupdel 组名
```

#####  5.5.3 groupmod 修改组

```sh
groupmod -n  新组名  老组名
```

#####  5.5.4  cat /etc/group 查看创建了哪些组

```sh
cat /etc/group
```

#####  5.5.5 综合练习

```sh
# 创建两个组
groupadd bigdata
groupadd testing

# 查看组
cat /etc/group

# 给组添加用户
useradd -g bigdata xiaoming
id xiaoming
# 输出 uid=1002(xiaoming) gid=1002(bigdata) groups=1002(bigdata)
useradd -g testing xiaoliang
id xiaoliang
# 输出：uid=1003(xiaoliang) gid=1003(testing) groups=1003(testing)

# 切换用户
 su xiaoliang
```



###  5.6 文件权限类 

#####  5.6.1 文件属性

Linux系统是一种典型的多用户系统，不同的用户处于不同的地位，拥有不同的权限。为了保护系统的安全性，Linux系统对不同的用户访问同一文件(包括目录文件）的权限做了不同的规定。在Linux中我们可以使用ll或者ls -l命令来显示一个文件的属性以及文件所属的用户和组，从左到右的10个字符表示含义如下：

![image-20231013043908925](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190442155.png)

**一、如果没有权限，就会出现减号`-`。从左至右用0-9这些数字来表示**：

0首位表示类型：

- 在Linux中第一个字符代表这个文件是目录、文件或链接文件等等
- `-`代表文件
- `d`代表目录
- `l`链接文档(link file)

第1-3位确定属主(User)：

- 该文件的所有者拥有该文件的权限

第4-6位确定属组(Group)：

- 所有者的同组用户拥有该文件的权限

第7-9位确定其他用户权限(other)：

- 确定其他用户拥有该文件的权限

**二、rwx 作用文件和目录的不同解释**：

作用到文件：

- `r`代表可读(read)：可以读取，查看

- `w`代表可写(write)：可以修改，但是不代表可以删除该文件，删除一个文件的前提条件是对该文件所在的目录有写权限，才能删除该文件
- `x`代表可执行(execute)：可以被系统执行

作用到目录：

- `r`代表可读(read)：可以读取，ls查看目录内容
- `w`代表可写(write)：可以修改，目录内创建+删除+重命名目录
- `x`代表可执行(execute)：可以进入该目录

**三、ll查看文件权限**：

```sh
[root@hadoop101 ~]# ll
总用量 104
-rw-------. 1 root root 1248 1 月 8 17:36 anaconda-ks.cfg
drwxr-xr-x. 2 root root 4096 1 月 12 14:02 dssz
lrwxrwxrwx. 1 root root 20 1 月 12 14:32 houzi -> xiyou/dssz/houge.txt
```



![image-20231014054519180](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190442331.png)

关于链接数的说明：

- 如果查看到是文件：链接数指的是硬链接个数

- 如果查看的是文件夹：链接数指的是子文件夹个数

#####  5.6.2 chmod  改变权限



![image-20231014060618595](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190442077.png)

第一种方式变更权限：

```sh
chmod [{ugoa} {+-=} {rwx}]  文件或目录 
```

第二种方式变更权限：

```sh
chmod [mode=421] [文件或目录]
```

说明：

```sh
u：所属者 g：所有组 o：其他人 a：所有人 (u、g、o的总和)
r=4    w=2     x=1     rwx=4+2+1=7
+: 添加权限  - ：删除权限   =：设置权限
```

使用案例：

```sh
# 修改文件使其所属主用户具有执行权限
chmod u+x houge.txt

# 修改文件使其所属组用户具有执行权限
chmod g+x houge.txt

# 修改文件所属主用户执行权限,并使其他用户具有执行权限
chmod u-x,o+x houge.txt

# 设置文件所有者、所属组、其他用户都具有读写权限
chmod a=rw gulimall_ums_dml.sql

# 采用数字的方式，设置文件所有者、所属组、其他用户都具有可读可写可执行权限
chmod 777 houge.txt

# 修改整个文件夹里面的所有文件的所有者、所属组、其他用户都具有可读可写可执行权限
chmod -R 777 xiyou/
```

#####   5.6.3 chown  改变所属者

```sh
# 功能描述: 改变文件或者目录的所有
chown [选项] [最终用户] [文件或目录]

# 修改文件所有者
chown atguigu houge.txt
ls -al
# 显示：-rwxrwxrwx. 1 atguigu root 551 5 月 23 13:02 houge

# 递归改变文件所属者和所属组
chown -R atguigu:atguigu xiyou/
ll
# 显示: drwxrwxrwx. 2 atguigu atguigu 4096 9 月 3 21:20 xiyou
```

选项说明：

| 选项 | 功能     |
| ---- | -------- |
| -R   | 递归操作 |

#####  5.6.4 chgrp  改变所属组

```sh
# 改变文件或者目录的所属组
chgrp [最终用户组] [文件或目录]

# 修改文件的所属组
chgrp root houge.txt
ls -al
# 输出: -rwxrwxrwx. 1 atguigu root 551 5 月 23 13:02 houge.txt
```

###  5.7 搜索查找类

#####  5.7.1 find 查找文件或者目录

find指令将从指定目录向下递归地遍历其各个子目录，将满足条件的文件显示在终端

```sh
# 基本语法
find [搜索范围][选项]

# 按文件名：根据名称查找/目录下的filename.txt文件
find xiyou/ -name "*.txt"

# 按拥有者：查找/opt目录下，用户名称为-user的文件
find xiyou/ -user atguigu

# 按文件大小：在/home目录下查找大于200m的文件(+n 大于 -n小于 n等于)
find /home -size +204800
```

选项说明：

| 选项             | 功能                                                         |
| ---------------- | ------------------------------------------------------------ |
| -name <查询方式> | 按照指定的文件名查找模式查找文件                             |
| -user <用户名>   | 查找属于指定用户名所有文件                                   |
| -size <文件大小> | 按照指定的文件大小查找文件,单位为：b—块(512字节)、c—字节、w—字(2字节)、k —千字节、M—兆字节、G—吉字节 |

#####  5.7.2 locate  快速定位文件路径

locate指令利用事先建立的系统中所有文件名称及路径的locate数据库实现快速定位给定的文件。Locate指令无需遍历整个文件系统，查询速度较快。为了保证查询结果的准确度，管理员必须定期更新locate时刻

```sh
# 基本语法
locate 搜索文件

updatedb

# 查询文件夹
locate temp
```

注意：由于locate指令基于数据库进行查询，所以第一次运行前，必须使用updatedb指令创建locate数据库

#####  5.7.3  grep  过滤查找

```sh
# 基本语法
grep   选项   查找内容   源文件

# 查找文件中“user”出现的行和所在行内容
grep -n user gulimall_ums_dml.sql

# 管道符号使用，查找某文件在第几行
ls | grep -n test

# 在/mydata/目录下递归查找包含“商城”的文件
grep  -r 商城 /mydata/
# 输出：
#    /mydata/elasticsearch/plugins/ik/config/main.dic:商城
#    /mydata/elasticsearch/plugins/ik/config/main.dic:商城价
```

选项说明：

| 选项 | 功能             |
| ---- | ---------------- |
| -n   | 显示匹配行及行号 |
| -i   | 忽略大小写       |

#####  5.7.4   |  管道

**一、管道简介**：

管道是一种通信机制，通常用于进程间的通信。作用：**将前一个命令的处理结果输出传递给后面的命令处理**

![image-20231014084548488](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190442809.png)

0︰标准输入，程序或命令需要外部的某些程序传递相应的参数，才能正常运行

1︰标准输出，程序或命令正确的执行结果，我们就称之为标准输出

2︰标准错误，程序或命令错误的执行结果，我们就称之为标准错误

管道就是将前一个命令的结果当成后一个命令的输入参数

**二、管道的筛选过滤功能**：

```sh
# 基本语法
前一个命令 | 后一个命令

# 获取/根目录下文件名包含“y”的文件名称
ls / | grep y

# 在系统中的进程中进行查找，查找与docker相关的进程信息
ps -ef | grep docker
# 注: ps -ef 查询系统中所有正在运行的进程
```

**三、管道的特殊功能**：

```sh
# 将搜索到的结果放到less中来进行分屏查看
ps -ef | less
```

**四、管道的统计功能**：

```sh
# wc命令功能:  word count ，文件统计功能

# wc命令语法
wc [选项] 文件名称

# wc命令选项说明
    -l: 统计总行数
    -w: 总单词数
    -c: 统计总的字节数

# 案例：统计/mydata目录下一共有多少个文件
ls /mydata | wc -l

# 统计当前系统有多少用户
cat /etc/passwd | wc -l
```

**五、xargs命令扩展**：

之所以要用这个命令，是因为很多命令不支持|管道来传递参数，而工作中又有这个必要，所以就有了xargs命令。简单来说，xargs命令就相当于对管道命令进行了一个扩展，让所有命令都支持管道

案例∶搜索/mydata目录下的所有".conf"结尾的文件信息，然后以详细列表形式显示

```sh

# 单独执行find /mydata/ -name "*.conf"
[root@localhost vagrant]# find /mydata -name "*.conf"
/mydata/redis/conf/redis.conf
/mydata/mysql/conf/my.conf
/mydata/nginx/conf/conf.d/default.conf
/mydata/nginx/conf/conf.d/gulimall.conf
/mydata/nginx/conf/nginx.conf

# 试图使用管道将find命令的输出传给ls命令，发现ls列出的不是/mydata下的内容
[root@localhost vagrant]# find /mydata -name "*.conf" | ls -l
total 0
drwxrwxrwx. 5 root root 47 Jun 29 23:49 test

# 通过xargs命令使得ls能接收管道的参数
[root@localhost vagrant]# find /mydata -name "*.conf" | xargs ls -l
-rw-r--r--. 1 root root  287 Jul  1 05:14 /mydata/mysql/conf/my.conf
-rw-r--r--. 1 root root 1097 Jan 31  2017 /mydata/nginx/conf/conf.d/default.conf
-rw-r--r--. 1 root root 1181 Jul 19 19:17 /mydata/nginx/conf/conf.d/gulimall.conf
-rw-r--r--. 1 root root  709 Jul  3 20:57 /mydata/nginx/conf/nginx.conf
-rw-r--r--. 1 root root   41 Jun 29 22:58 /mydata/redis/conf/redis.conf
```

###   5.8  压缩和解压类

#####  5.8.1 gzip/gunzip压缩

```sh
# 压缩文件，只能将文件压缩为*.gz文件
gzip 文件

# 解压缩文件命令
gunzip  文件.gz

# gzip压缩
gzip houge.txt

# gunzip解压缩文件
gunzip houge.txt.gz
```

说明：

- 只能压缩文件不能压缩目录
- 不保留原来的文件
- 同时多个文件会产生多个压缩包

#####  5.8.2 zip/unzip 打包和压缩

zip压缩命令在windows/linux都通用，可以压缩目录且保留源文件

```sh
# 压缩文件和目录的命令
zip [选项] XXX.zip 将要压缩的内容 

# 解压缩文件
unzip [选项] XXX.zip

# 压缩houge.txt和bailongma.txt，压缩后的名称为mypackage.zip
touch bailongma.txt
zip mypackage.zip houge.txt bailongma.txt

# 解压 mypackage.zip
unzip mypackage.zip

# 解压mypackage.zip到指定目录-d
unzip mypackage.zip -d /opt
```

| zip[选项] | 功能     |
| --------- | -------- |
| -r        | 压缩目录 |

| unzip选项 | 功能                     |
| --------- | ------------------------ |
| -d <目录> | 指定解压后文件的存放目录 |

#####  5.8.3 tar 打包

```sh
# 打包目录。  压缩后的文件格式.tar.gz
tar [选项] XXX.tar.gz  将要打包进去的内容

# 压缩多个文件
tar -zcvf houma.tar.gz houge.txt bailongma.txt houge.txt
ls
# 输出: houma.tar.gz houge.txt bailongma.txt

# 压缩目录
tar -zcvf xiyou.tar.gz xiyou/

# 解压到当前目录
tar -zxvf houma.tar.g

# 解压到指定目录
tar -zxvf xiyou.tar.gz -C /opt
```

| 选项 | 功能               |
| ---- | ------------------ |
| -c   | 产生.tar打包文件   |
| -v   | 显示详细信息       |
| -f   | 指定压缩后的文件名 |
| -z   | 打包同时压缩       |
| -x   | 解包.tar文件       |
| -C   | 解压到指定目录     |

### 5.9  磁盘查看和分区类

#####   5.9.1 du   查看文件和目录占用的磁盘空间

du(disk usage)：磁盘占用情况

```sh
# 显示目录下每个子目录的磁盘使用情况
du 目录/文件

# 查看当前目录占用的磁盘空间大小
du -sh
```

| 选项          | 功能                                               |
| ------------- | -------------------------------------------------- |
| -h            | 以人们较易阅读的GBytes,MBytes,KBytes等格式自行显示 |
| -a            | 不仅查看子目录大小，还要包括文件                   |
| -c            | 显示所有的文件和子目录大小后，显示总和             |
| -s            | 只显示总和                                         |
| --max-depth=n | 指定统计子目录的深度为第n层                        |

#####  5.9.2 df  查看磁盘空间使用情况

df(disk free)：空余磁盘

```sh
# 列出文件系统的整体磁盘使用量，检查文件系统的磁盘空间占用情况
df 选项
```

| 选项 | 功能                                                |
| ---- | --------------------------------------------------- |
| -h   | 以人们较易阅读的GBytes,MBytes, KBytes等格式自行显示 |

#####  5.9.3  lsblk  查看设备挂载情况

```sh
lsblk
```

| 选项 | 功能                                     |
| ---- | ---------------------------------------- |
| -f   | 查看详细的设备挂载情况，显示文件系统信息 |

#####  5.9.4 mount/umount  挂载/卸载

**一、挂载简介**：

对于Linux用户来讲，不论有几个分区，分别分给哪一个目录使用，它总归就是一个根目录、一个独立且唯一的文件结构

Linux中每个分区都是用来组成整个文件系统的一部分，它在用一种叫做“挂载”的处理方法，它整个文件系统中包含了一整套的文件和目录，并将一个分区和一个目录联系起来，要载入的那个分区将使它的存储空间在这个目录下获得

**二、挂载前准备**：必须要有光盘或者已经连接镜像文件

在VMware中进行设置：

![image-20231014221348376](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190442881.png)

**三、挂载设备**：


```sh
# 挂载设备
mount [-t vfstype] [-o options] device dir 

# 卸载设备
umount 设备文件名或挂载点 

# 案例：挂载光盘镜像文件
# 建立挂载点
mkdir /mnt/cdrom/ 
# 设备/dev/cdrom 挂载到 挂载点 ： /mnt/cdrom 中
mount -t iso9660 /dev/cdrom /mnt/cdrom/ 

# 卸载光盘镜像文件
umount /mnt/cdro
```

| 参数       | 功能                                                         |
| ---------- | ------------------------------------------------------------ |
| -t vfstype | 指定文件系统的类型，通常不必指定。mount 会自动选择正确的类型。常用类型有：<br/>光盘或光盘镜像：iso9660<br/>Dos fat16文件系统: msdos<br/>windows 9x fat32文件系统: vfat<br/>windows NT ntfs 文件系统: ntfs<br/>Mount Windows文件网络共享: smbfs<br/>UNIX(LINUX)文件网络共享:nfs |
| -o options | 主要用来描述设备或档案的挂接方式。常用的参数有<br/>loop：用来把一个文件当成硬盘分区挂接上系统<br/>ro：采用只读方式挂接设备<br/>rw：采用读写方式挂接设备<br/>iocharset：指定访问文件系统所用字符集 |
| device     | 要挂接(mount)的设备                                          |
| dir        | 设备在系统上的挂接点(mount point)                            |

**四、设置开机自动挂载**：

```sh
vi /etc/fsta
```

添加红框中内容，保存退出

![image-20231014222514208](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190443006.png)

#####  5.9.5 fdisk 磁盘分区

该命令必须在root用户下才能使用

```sh
# 查看磁盘分区详情
fdisk -l

# 对新增硬盘进行分区操作
fdisk 硬盘设备名

# 查看系统分区情况
fdisk -l
```

| 选项 | 功能                   |
| ---- | ---------------------- |
| -l   | 显示所有硬盘的分区列表 |

```
功能说明:

Linux 分区：
        Device：分区序列
        Boot：引导
        Start：从X磁柱开始
        End：到Y磁柱结束
        Blocks：容量
        ld：分区类型ID
        System：分区类型

分区操作按键说明：
        m：显示命令列表
        p：显示当前磁盘分区
        n：新增分区
        w：写入分区信息并退出
```



###  5.10 进程管理类

进程是正在执行的一个程序或命令，每一个进程都是一个运行的实体，都有自己的地址空间，并占用一定的系统资源

如果想查看进程的CPU占用率和内存占用率，可以使用aux 。如果想查看进程的父进程ID可以使用ef

#####   5.10.1 ps  查看当前系统进程状态

ps(process status)：进程状态

```sh
# ps [选项]

# 查看系统中所有进程
ps aux | grep xxx

# 查找指定进程格式
ps -ef | grep 进程关键字
```

选项说明：

| 选项 | 功能                                       |
| ---- | ------------------------------------------ |
| a    | 列出带有终端的所有用户的进程               |
| x    | 列出当前用户的所有进程，包括没有终端的进程 |
| u    | 面向用户友好的显示风格                     |
| -e   | 列出所有进程                               |
| -u   | 列出某个用户关联的所有进程                 |
| -f   | 显示完整格式的进程列表                     |
| -aux | 显示所有包含其他使用者的进程               |

各列显示信息的含义：

```sh
# 查看系统中所有进程
ps -aux

# 输出：
# USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
# root         1  0.0  0.0 128200  6744 ?        Ss   Oct13   0:01 /usr/lib

# ps aux显示信息说明：
        USER: 该进程是由哪个用户产生的
        PID: 进程的ID号
        %CPU：该进程占用CPU资源的百分比，占用越高，进程越耗费资源
        %MEM：该进程占用物理内存的百分比，占用越高，进程越耗费资源
        VSZ:该进程占用虚拟内存的大小，单位KB
        RSS:该进程占用实际物理内存的大小，单位KB
        TTY:该进程是在哪个终端中运行的。对于CentOS来说，tty1是图形化终端，tty2-tty6是本地的字符界面终端。pts/O-255代表虚拟终端
        STAT:进程状态。常见的状态有:R:运行状态、S:睡眠状态、T:暂停状态、Z:僵尸状态、s:包含子进程、l:多线程、+:前台显示
        START:该进程的启动时间
        TIME:该进程占用CPU的运算时间，注意不是系统时间
        COMMAND:产生此进程的命令名
        

ps -ef
# ps -ef 输出：
# UID        PID  PPID  C STIME TTY          TIME CMD

# ps -ef 显示信息说明:
        UID:用户ID
        PID:进程ID
        PPID:父进程ID
        C:CPU用于计算执行优先级的因子。数值越大，表明进程是CPU密集型运算，执行优先级会降低;数值越小，表明进程是IO密集型运算，执行优先级会提高
        STIME:进程启动的时间
        TTY:完整的终端名称
        TIME:CPU时间
        CMD:启动进程所用的命令和参数
```

#####  5.10.2  kill 终止进程

```shell
# 通过进程号杀死进程
kill [选项] 进程号 

# 通过进程名称杀死进程，也支持通配符，这在系统因负载过大而变得很慢时很有用
killall 进程名称

# 杀死进程
kill -9 5102

# 通过进程名称杀死进程
killall firefox
```

| 选项 | 功能                 |
| ---- | -------------------- |
| -9   | 表示强迫进程立即停止 |

#####  5.10.3 pstree 查看进程树

```shell
# 查看进程树
pstree [选项]

# 显示进程pid
pstree -p
 
# 显示进程所属用户
pstree -u
```

| 选项 | 功能               |
| ---- | ------------------ |
| -p   | 显示进程的PID      |
| -u   | 显示进程的所属用户 |

#####  5.10.4  top 实时监控系统进程状态

```shell
# 实时监控系统进程状态
top [选项]

top -d 1

top -i

top -p 2575
# 执行上述命令后，可以按 P、M、N 对查询出的进程结果进行排序
```

| 选项    | 功能                                                         |
| ------- | ------------------------------------------------------------ |
| -d 秒数 | 指定top命令每隔几秒更新。默认是3秒在 top命令的交互模式当中可以执行的命令 |
| -i      | 使top不显示任何闲置或者僵死进程                              |
| -p      | 通过指定监控进程ID来仅仅监控某个进程的状态                   |

操作说明：

| 操作 | 功能                          |
| ---- | ----------------------------- |
| p    | 以CPU使用率排序，默认就是此项 |
| M    | 以内存的使用率排序            |
| N    | 以PID排序                     |
| q    | 退出top                       |



```shell
# 实时监控系统进程状态
[root@localhost test]# top
top - 15:36:08 up 17:04,  1 user,  load average: 0.19, 0.10, 0.06
Tasks: 128 total,   1 running, 127 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.3 us,  0.3 sy,  0.0 ni, 99.3 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  8009180 total,  4842812 free,  1650844 used,  1515524 buff/cache
KiB Swap:  2097148 total,  2097148 free,        0 used.  6037000 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
 1884 vagrant   20   0 1741736 352672  21264 S  0.7  4.4   9:30.87 node
  167 root      20   0       0      0      0 S  0.3  0.0   0:52.49 xfsaild/sda1
  696 root      20   0  474024  19440   6136 S  0.3  0.2   0:11.05 tuned
```

top命令查询结果：

![image-20231015043543930](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190443606.png)



```shell
# top命令查询结果字段解释：

# 第一行信息为任务队列信息
        12:26:46                           系统当前时间
        up 1 day, 13:32                    系统的运行时间，本机已经运行1天13小时32分钟
        2 users                            当前登录了两个用户
        load average: 0.00, 0.00,0.00      系统在之前1分钟，5分钟，15分钟的平均负载。
                                           一般认为小于1时，负载较小。如果大于1，系统已经超出负荷
# 第二行为进程信息
        Tasks: 95 total      系统中的进程总数
        l running            正在运行的进程数
        94 sleeping          睡眠的进程
        0 stopped            正在停止的进程
        0 zombie             僵尸进程。如果不是0，需要手工检查僵尸进程

# 第三行为CPU信息
        Cpu(s): 0.1%us     用户模式占用的CPU百分比
        0.1%sy             系统模式占用的CPU百分比
        0.0%ni             改变过优先级的用户进程占用的CPU百分比
        99.7%id            空闲CPU的CPU百分比
        0.1%wa             等待输入/输出的进程的占用CPU百分比
        0.0%hi             硬中断请求服务占用的CPU百分比
        0.1%si             软中断请求服务占用的CPU百分比
        0.0%st             st ( Steal time）虚拟时间百分比。就是当有虚拟机时，虚拟CPU等待实际CPU的时间百分比


# 第四行为物理内存信息
        Mem:625344k total   物理内存的总量，单位KB
        571504k used        已经使用的物理内存数量
        53840k free         空闲的物理内存数量，我们使用的是虚拟机，总共只分配了628MB内存，所以只有53MB的空闲内存了
        65800k buffers      作为缓冲的内存数量

# 第五行为交换分区(swap）信息
        Swap: 524280k total    交换分区（虚拟内存）的总大小
        0k used                已经使用的交互分区的大小
        524280k free           空闲交换分区的大小
        409280k cached         作为缓存的交互分区的大小

```

#####   5.10.5 netstat 显示网络状态和端口占用信息

```shell
# 查看该进程网络信息
netstat -anp | grep 进程号

# 查看网络端口号占用情况
netstat —nlp | grep 端口号

# 通过进程号查看sshd进程的网络信息
netstat -anp | grep sshd


netstat -nltp

# 查看某端口号是否被占用
netstat -nltp | grep 22
```

选项说明：

| 选项 | 功能                                                 |
| ---- | ---------------------------------------------------- |
| -a   | 显示所有正在监听( listen）和未监听的套接字（ socket) |
| -n   | 拒绝显示别名，能显示数字的全部转化成数字             |
| -l   | 仅列出在监听的服务状态                               |
| -p   | 表示显示哪个进程在调用                               |
| -t   | 表示显示TCP连接                                      |
| -u   | 表示UDP                                              |

### 5.11 crontab 系统定时任务

#####   5.11.1 crontab 服务管理

```shell
# 重新启动crond服务
systemctl restart crond
```

#####  5.11.2 crontab 定时任务设置

```shell
# 语法：
crontab [选项]

# 查询所有定时任务
crontab -l

# 编辑定时任务
# 执行crontab -e会进入crontab编辑界面，打开vim编辑定时任务
crontab -e

# 在vim编辑中添加定时任务
# 每隔 1 分钟往gulimall_ums_dml.sql中追加内容
*/1 * * * * echo "hello,word" >> /mydata/test/gulimall_ums_dml.sql
```

选项说明：

| 选项 | 功能                          |
| ---- | ----------------------------- |
| -e   | 编辑crontab定时任务           |
| -l   | 查询crontab任务               |
| -r   | 删除当前用户所有的crontab任务 |

定时任务的设置：

```shell
# 编辑定时任务
# 执行crontab -e会进入crontab编辑界面，打开vim编辑定时任务
crontab -e

定时任务的时间设置
     1.定时任务语法： [时间表达式] [执行的任务]
     
     2.时间表达式：
            第一个“*”:       含义: 一小时当中的第几分钟     范围：0-59
            第二个“*”:       含义: 一天当中的第几小时      范围：0-23
            第三个“*”:       含义: 一个月当中的第几天      范围：1-31
            第四个“*”:       含义：一年当中的第几月        范围：1-12
            第五个“*”：      含义：一周当中的星期几        范围：0-7( 0和7都代表星期日)
            
            
     3.特殊符号
            *       含义：代表任何时间。比如第一个“*”就代表一小时中每分钟都执行一次的意思
            ,       含义：代表不连续的时间。比如“0 8,12,16 * * * 命令”，就代表在每天的 8 点 0 分，12 点 0 分，16 点 0 分都执行一次
            -       含义：代表连续的时间范围。比如“0 5 * * 1-6 命令”，代表在周一到周六的凌晨 5 点 0 分执行命令
            */n     含义： 代表每隔多久执行一次。比如“*/10 * * * * 命令”，代表每隔 10 分钟就执行一遍命令

      4.定时任务设置示例：
           45 22 * * *  [命令]              含义：每天 22 点 45 分执行命令
           0 17 * * 1   [命令]              含义：每周 1 的 17 点 0 分执行命
           0 5 1,15 * * [命令]              含义：每月 1 号和 15 号的凌晨 5 点 0 分执行命令
           40 4 * * 1-5 [命令]              含义：每周一到周五的凌晨 4 点 40 分执行命令
           */10 4 * * * [命令]              含义：每天的凌晨 4 点，每隔 10 分钟执行一次命令
           0 0 1,15 * 1 [命令]              含义：每月 1 号和 15 号，每周 1 的 0 点 0 分都会执行命令。
                                           注意：星期几和几号最好不要同时出现，因为他们定义的都是天。非常容易让管理员混乱                                   
```

##  6.软件包管理

###  6.1 RPM

#####  6.1.1  RPM概述

RPM（RedHat Package Manager)，RedHat软件包管理工具，类似windows里面的setup.exe，是Linux这系列操作系统里面的打包安装工具，它虽然是RedHat的标志，但理念是通用的

RPM包的名称格式：

- 例子：Apache-1.3.23-11.i386.rpm

- “apache”软件名称

- “1.3.23-11”软件的版本号，主版本和此版本
- “i386"是软件所运行的硬件平台，Intel 32位处理器的统称-
- “rpm”文件扩展名，代表RPM包

#####  6.1.2 RPM 查询命令

```shell
# 查询当前系统已经安装的所有rpm软件包
rpm -qa

# 由于软件包比较多，一般都会采取过滤,查询特定软件
rpm -qa | grep rpm 软件包

# 查询firefox软件安装情况
rpm -qa |grep firefox
```

#####  6.1.3 RPM 卸载命令

```shell
# RPM卸载命令
rpm -e RPM 软件包

# RPM卸载命令
rpm -e --nodeps 软件包

# 卸载firefox软件
rpm -e firefox
```

| 选项     | 功能                                                         |
| -------- | ------------------------------------------------------------ |
| -e       | 卸载软件包                                                   |
| --nodeps | 卸载软件时，不检查依赖。这样的话，那些使用该软件包的软件在此之后可能就不能正常工作了 |

#####   6.1.4 RPM 安装命令

```shell
# RPM 安装命令
rpm -ivh RPM [包全名]

# 安装firefox软件
rpm -ivh firefox-45.0.1-1.el6.centos.x86_64.rpm
```

| 选项     | 功能                    |
| -------- | ----------------------- |
| -i       | install，安装           |
| -v       | --verbose，显示详细信息 |
| -h       | --hash，进度条          |
| --nodeps | 安装前不检查依赖        |

###   6.2 YUM 仓库配置

#####   6.2.1 YUM概述

YUM（全称为Yellow dog Updater, Modified）是一个在 Fedora和 RedHat 以及CentOS中的Shell前端软件包管理器。基于RPM包管理，能够从指定的服务器自动下载RPM包并且安装，可以自动处理依赖性关系，并且一次安装所有依赖的软件包，无须繁琐地一次次下载、安装：

![image-20231015172214080](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190444137.png)



#####  6.2.2 YUM的常用命令

```shell
yum [选项] [参数]

# 采用yum方式安装firefox
yum -y install firefox
```

选项说明：

| 选项 | 功能                  |
| ---- | --------------------- |
| -y   | 对所有提问都回答“yes” |

参数说明：

| 参数         | 功能                          |
| ------------ | ----------------------------- |
| install      | 安装rpm软件包                 |
| update       | 更新rpm软件包                 |
| check-update | 检查是否有可用的更新rpm软件包 |
| remove       | 删除指定的rpm软件包           |
| list         | 显示软件包信息                |
| clean        | 清理yum过期的缓存             |
| deplist      | 显示yum软件包的所有依赖关系   |

#####  6.2.3 修改网络YUM源

默认的系统YUM源，需要连接国外apache 网站，网速比较慢，可以修改关联的网络YUM源为国内镜像的网站，比如网易163,aliyun等

```shell
# 安装wget, wget用来从指定的URL下载文件
yum install wget

# 在/etc/yum.repos.d/目录下，备份默认的 repos 文件
[root@hadoop101 yum.repos.d] pwd
/etc/yum.repos.d
[root@hadoop101 yum.repos.d] cp CentOS-Base.repo CentOS-Base
.repo.backup


# 下载网易 163 或者是 aliyun 的 repos 文件,任选其一
# 阿里云
wget http://mirrors.aliyun.com/repo/Centos-7.repo 

# 网易 163
wget http://mirrors.163.com/.help/CentOS7-Base-163.repo

# 使用下载好的 repos 文件替换默认的 repos 文件
# 例如:用 CentOS7-Base-163.repo 替换 CentOS-Base.repo
mv CentOS7-Base-163.repo CentOS-Base.repo

# 清理旧缓存数据，缓存新数据
yum clean all
yum makecache
# yum makecache 就是把服务器的包信息下载到本地电脑缓存起来


# 测试
yum list | grep firefox
yum -y install firefox
```

##  7.shell入门

###  7.1 shell 概述

Shell是一个命令行解释器，它接收应用程序/用户命令，然后调用操作系统内核

![image-20231015194242768](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/202310190444919.png)

Shell还是一个功能相当强大的编程语言，易编写、易调试、灵活性强

```shell
# 查询当前系统提供的Shell解析器
cat /etc/shells
# 输出：
#        /bin/sh
#        /bin/bash
#        /usr/bin/sh
#        /usr/bin/bash
```

###  7.2 shell脚本格式

**1.shell脚本格式**：

```shell
shell脚本以#!/bin/bash开头（指定解析器）
```

**2.编写第一个 Shell脚本**： 

> 需求：创建一个 Shell脚本，输出helloworld
>
>  实现：

```shell
touch helloworld.sh
vim helloworld.sh
# 在 helloworld.sh 中输入如下内容:
#!/bin/bash
echo "helloworld"
```

###   7.3 脚本的常用执行方式

  **第一种**：采用 bash 或 sh  +   脚本的相对路径或绝对路径（不用赋予脚本 +x 权限)

```shell
# sh + 脚本的相对路径
[root@localhost test]# sh ./helloworld.sh
helloworld

# sh + 脚本的绝对路径
[root@localhost test]# sh /mydata/test/helloworld.sh
helloworld

# bash + 脚本的相对路径
[root@localhost test]# bash ./helloworld.sh
helloworld

# bash + 脚本的绝对路径
[root@localhost test]# bash /mydata/test/helloworld.sh
helloworld
```

注：第一种执行方法，本质是 bash 解析器帮你执行脚本，所以脚本本身不需要执行权限



**第二种**：采用输入脚本的绝对路径或相对路径执行脚本（必须具有可执行权限+x)

```shell
# 首先要赋予 helloworld.sh 脚本的 + x权限
chmod +x helloworld.sh

# 利用相对路径执行脚本
./helloworld.sh

# 利用绝对路径执行脚本
/mydata/test/helloworld.sh
```

注：第二种执行方法，本质是脚本需要自己执行，所以需要执行权限



**第三种**：在脚本的路径前加上“.”或者 source

```shell
source helloworld.sh
```

**三种执行方式比较**：

```shell
# 有以下脚本test.sh
cat test.sh
# test.sh内容：
#            #!/bin/bash
#            A=5
#            echo $A

# 分别使用 sh，bash，./ 和 . 的方式来执行，结果如下：
[atguigu@hadoop101 shells]$ bash test.sh
[atguigu@hadoop101 shells]$ echo $A

[atguigu@hadoop101 shells]$ sh test.sh
[atguigu@hadoop101 shells]$ echo $A

[atguigu@hadoop101 shells]$ ./test.sh
[atguigu@hadoop101 shells]$ echo $A

[atguigu@hadoop101 shells]$ . test.sh
[atguigu@hadoop101 shells]$ echo $A
5
```

结论：`sh`、`bash`命令都是在当前shell 中打开一个子shell来执行脚本内容，当脚本内容结束，则子shell关闭，回到父shell 中。在脚本路径前加“.”或者 source的方式可以使脚本内容在当前shell里执行，而无需打开子shell。这也是为什么我们每次要修改完/etc/profile文件以后，需要source一下的原因。开子shell 与不开子shell 的区别就在于，环境变量的继承关系，如在子shell中设置的当前变量，父shell是不可见的

**执行方式总结**：

在Shell中，以下是Source、sh、bash和./执行脚本的区别：

1. **source命令**：source命令（或"."命令）用于在当前Shell会话中执行指定的脚本文件。它会将脚本文件中的命令依次作为当前Shell会话的一部分来执行。使用source命令执行脚本，脚本中的变量、函数等定义会在当前Shell中生效，对当前Shell环境做出的修改也会生效。source命令通常用于加载配置文件或执行需要在当前Shell环境中生效的脚本

2. **sh命令**：sh命令是Shell的缩写，它是一个用于执行Shell脚本的解释器。使用sh命令来执行脚本时，它会以新的子Shell进程的方式运行脚本，并在脚本执行完后退出该子Shell进程。启动的子Shell进程与当前Shell进程相互独立，它们有各自的环境变量和状态。sh命令通常用于执行独立的Shell脚本文件

3. **bash命令**：bash命令是Bourne Again Shell的缩写，它是一个流行的Shell解释器，提供了对POSIX Shell的扩展和增强功能。与sh命令类似，使用bash命令来执行脚本时，它也会启动一个新的子Shell进程，并在脚本执行完后退出该子Shell进程。bash命令通常用于执行具有特定Bash语法和功能需求的脚本文件

4. **./命令**：使用"./"命令表示当前目录下的可执行文件。当你在Shell中键入"./script.sh"时，它会尝试在当前目录下查找名为"script.sh"的可执行文件，并尝试以当前Shell的环境来运行该脚本。"./"命令通常用于执行当前目录下的可执行脚本或程序

总结起来，区别如下：

- `source`命令将脚本文件作为当前Shell会话的一部分执行，修改会话环境和变量
- `sh`命令在一个子Shell进程中执行脚本，与当前Shell环境隔离
- `bash`命令类似于`sh`命令，但使用Bash解释器，提供了扩展功能
- `./`命令用于执行当前目录下的可执行文件

##  8. shell变量

###  8.1 系统预定义变量

```shell
# 常用系统变量: $HOME、$PWD、$SHELL、$USER等

# 查看系统变量的值
[root@localhost test]# echo $HOME
/root

# 显示当前Shell中所有变量：set
set

# 查看系统全局环境变量
env

# 查看my_var是不是全局变量
env | grep my_var

# 查看my_var是不是变量
set | grep my_var
```

###  8.2 自定义变量

基本语法：

- 定义变量：变量名=变量值，注意，=号前后不能有空格

- 撤销变量: unset变量名

- 声明静态变量：readonly 变量，注意：不能unset

变量定义规则：

- 变量名称可以由字母、数字和下划线组成，但是不能以数字开头，环境变量名建议大写
- 等号两侧不能有空格
- 在 bash中，变量默认类型都是字符串类型，无法直接进行数值运算
- 变量的值如果有空格，需要使用双引号或单引号括起来

shell变量使用演示：

```shell
# 定义变量A
A=5
echo $A  
# 输出：5


# 给变量 A 重新赋值
A=8
echo $A  
# 输出：8

# 撤销变量 A
unset A
echo $A  
# 无输出

# 声明静态的变量 B=2，不能 unset
readonly B=2
echo $B
# 输出：2
B=9
-bash: B: readonly variable

#在bash中，变量默认类型都是字符串类型，无法直接进行数值运算
C=1+2
echo $C
# 输出：1+2

# 变量的值如果有空格，需要使用双引号或单引号括起来
[atguigu@hadoop102 ~]$ D=I love banzhang
-bash: world: command not found
[atguigu@hadoop102 ~]$ D="I love banzhang"
[atguigu@hadoop102 ~]$ echo $D
I love banzhang
```

```shell
# 可把变量提升为全局环境变量，可供其他Shell
# export 变量名   
[atguigu@hadoop101 shells]$ vim helloworld.sh

# 在 helloworld.sh 文件中增加 echo $B
#!/bin/bash
echo "helloworld"
echo $B


[atguigu@hadoop101 shells]$ ./helloworld.sh
Helloworld

# 发现并没有打印输出变量 B 的值
[atguigu@hadoop101 shells]$ export B
[atguigu@hadoop101 shells]$ ./helloworld.sh
helloworld
2
```

###  8.3 特殊变量

#####  8.3.1 $n

$n 功能描述：n为数字，$0 代表该脚本名称，$1-$9 代表第一到第九个参数。十以上的参数需要用大括号包含，如${10}

```shell
# 基本语法 
$n 

# 演示：
[atguigu@hadoop101 shells]$ touch parameter.sh
[atguigu@hadoop101 shells]$ vim parameter.sh
#!/bin/bash
echo '==========$n=========='
echo $0
echo $1
echo $2

[atguigu@hadoop101 shells]$ chmod 777 parameter.sh
[atguigu@hadoop101 shells]$ ./parameter.sh cls xz
==========$n==========
./parameter.sh
cls
xz
```

#####  8.3.2 $#

功能描述：获取所有输入参数个数，常用于循环,判断参数的个数是否正确以及加强脚本的健壮性

```shell
# 基本语法 
$# 

# 案例演示：
[atguigu@hadoop101 shells]$ vim parameter.sh
#!/bin/bash
echo '==========$n=========='
echo $0
echo $1
echo $2
echo '==========$#=========='
echo $#

[atguigu@hadoop101 shells]$ chmod 777 parameter.sh
[atguigu@hadoop101 shells]$ ./parameter.sh cls xz
==========$n==========
./parameter.sh
cls
xz
==========$#==========
2
```

#####  8.3.3 $*、$@

**$***  ：这个变量代表命令行中所有的参数，$*把所有的参数看成一个整体

**$@** ：这个变量也代表命令行中所有的参数，不过$@把每个参数区分对待

$*和$@区别：

- `$*`：`$*`将所有位置参数看作一个单词（字符串）并用空格连接起来。 例如，假设脚本被调用为 `./script.sh arg1 arg2 arg3`，那么使用`$*`将会得到一个包含所有参数的字符串，类似于 `"arg1 arg2 arg3"`。
- `$@`：`$@`将每个位置参数视为一个独立的单词（字符串）。使用`$@`时，每个参数会作为独立的项进行处理。 例如，假设脚本被调用为 `./script.sh arg1 arg2 arg3`，那么使用`$@`将会得到一个包含所有参数的字符串列表，类似于 `"arg1" "arg2" "arg3"`

```shell
[atguigu@hadoop101 shells]$ vim parameter.sh
#!/bin/bash
echo '==========$n=========='
echo $0
echo $1
echo $2
echo '==========$#=========='
echo $#
echo '==========$*=========='
echo $*
echo '==========$@=========='
echo $@


[atguigu@hadoop101 shells]$ ./parameter.sh a b c d e f g
==========$n==========
./parameter.sh
a
b
==========$#==========
7
==========$*==========
a b c d e f g
==========$@==========
a b c d e f g
```

#####  8.3.4 $？

**$？**：最后一次执行的命令的返回状态。如果这个变量的值为 0，证明上一 个命令正确执行。如果这个变量的值为非 0（具体是哪个数，由命令自己来决定），则证明 上一个命令执行不正确了

```shell
# 判断 helloworld.sh 脚本是否正确
[atguigu@hadoop101 shells]$ ./helloworld.sh
hello world
[atguigu@hadoop101 shells]$ echo $?
0
```

## 9. shell 运算符

```shell
#  运算符语法：
“$((运算式))” 或 “$[运算式]”

# 计算（2+3）* 4 的值
[root@localhost test]# S=$[(2+3)*4]
[root@localhost test]# echo $S
20


[root@localhost test]# vim add.sh
#!/bin/bash
sum=$[$1+$2]
echo sum=$sum


[root@localhost test]# chmod +x add.sh
[root@localhost test]# ./add.sh 25 89
sum=114
```

## 10. shell 条件判断

1.基本语法

```shell
test condition  # test [条件表达式]
# 或
[ condition ]  # 注意：condition前后要有空格
# 注意：条件非空即为 true，[ Linux ]返回 true，[ ] 返回 false


# 判断a的值是否是hello
[root@localhost test]# a=hello
[root@localhost test]# test $a = hello
[root@localhost test]# echo $?
0

# 判断a的值是否是hello
[root@localhost test]# [ $a = hello ]
[root@localhost test]# echo $?
0

# 判断2和8是否相等
[root@localhost test]# [ 2 -eq 8 ]
[root@localhost test]# echo $?
1

# 23 是否大于等于 22
[atguigu@hadoop101 shells]$ [ 23 -ge 22 ]
[atguigu@hadoop101 shells]$ echo $?
0

# helloworld.sh 是否具有写权限
[atguigu@hadoop101 shells]$ [ -w helloworld.sh ]
[atguigu@hadoop101 shells]$ echo $?
0

# 判断是否有执行文件add.sh的权限
[root@localhost test]# [ -x add.sh ]
[root@localhost test]# echo $?
0

# 判断是否存在helloworld.sh文件
[root@localhost test]# [ -e helloworld.sh ]
[root@localhost test]# echo $?
0

# 多条件判断:    && 表示前一条命令执行成功时，才执行后一条命令
#              || 表示上一条命令执行失败后，才执行下一条命令
[root@localhost test]# a=15
[root@localhost test]# [ $a -lt 20 ] && echo "$a < 20" || echo "$a >= 20"
15 < 20
```

2.常用判断条件

```shell
1.两个整数之间比较
        -eq 等于
        -ne 不等于
        -lt 小于
        -le 小于等于
        -gt 大于      
        -ge 大于等于
        注：如果是字符串之间的比较 ，用等号“=”判断相等；用“!=”判断不等     

2.按照文件权限进行判断
        -r 有读的权限
        -w 有写的权限
        -x 有执行的权限
        
3.按照文件类型进行判断
        -e 文件存在
        -f 文件存在并且是一个常规的文件
        -d 文件存在并且是一个目录       
```

## 11. shell 流程控制

### 11.1 if 判断

单分支

```shell
if [ 条件判断式 ];then
    程序 
fi 

# 或者

if [ 条件判断式 ] 
then 
    程序 
fi 
```

多分支

```shell
if [ 条件判断式 ] 
then 
    程序 
elif [ 条件判断式 ] 
then 
    程序 
else 
    程序 
fi 
```

if语句使用：

```shell
# 判断a是否大于18
[root@localhost test]# a=25
[root@localhost test]# if [ $a -gt 18 ]; then echo OK; fi
OK


# 判断a是否 大于18且小于35 （-a 等同于c++中的 &&）
[root@localhost test]# if [ $a -gt 18 -a $a -lt 35 ]; then echo OK; fi
OK


# 在if_test.sh中编写if语句
[root@localhost test]# vim if_test.sh
#!/bin/bash
if [ "$1"x = "root"x ]
then
      echo "welcome,root"
fi

[root@localhost test]# chmod +x if_test.sh
[root@localhost test]# ./if_test.sh root
welcome,root

# 多分支处理
[root@localhost test]# vim if_test.sh
#!/bin/bash
if [ "$1"x = "root"x ]
then
      echo "welcome,root"
fi
# 输入第二个参数，判断属于哪个年龄段
if [ $2 -lt 18 ]
then
      echo "未成年人"
elif [ $2 -lt 35 ]
then
    echo "青年人"
elif [ $2 -lt 60 ]
then
    echo "中年人"
else
    echo "老年人"
fi

[root@localhost test]# ./if_test.sh root 30
welcome,root
青年人
```

### 11.2 case 语句

```shell
#case语句语法：
    case $变量名 in
    "值 1"）
    如果变量的值等于值 1，则执行程序 1
    ;;
    "值 2"）
    如果变量的值等于值 2，则执行程序 2
    ;;
    …省略其他分支…
    *）
    如果变量的值都不是以上的值，则执行此程序
    ;;
    esac
    
#case案例：    
[root@localhost test]# vim case_test.sh
#!/bin/bash
case $1 in
1)
    echo "one"
;;
2)
    echo "two"
;;
3)
    echo "three"
;;
*)
    echo "number other"
;;
esac
[root@localhost test]# chmod +x case_test.sh
[root@localhost test]# ./case_test.sh 3
three
[root@localhost test]# ./case_test.sh 2
two
[root@localhost test]# ./case_test.sh 9
number other    
```

注意事项：

- case 行尾必须为单词“in”，每一个模式匹配必须以右括号“）”结束
- 双分号“;;”表示命令序列结束，相当于 java 中的 break
- 最后的“*）”表示默认模式，相当于 java 中的 default

###  11.3 for 循环

```shell
#for循环语法一：
for (( 初始值;循环控制条件;变量变化 )) 
do 
    程序 
done

#for循环解决累加问题
[root@localhost test]# vim for_test.sh
#!/bin/bash
for (( i=1; i <= $1;i++ ))
do
    sum=$[ $sum + $i ]
done
echo $sum

[root@localhost test]# chmod +x for_test.sh
[root@localhost test]# ./for_test.sh 100
5050
[root@localhost test]# ./for_test.sh 10
55


#for循环语法二：
for 变量 in 值 1 值 2 值 3…
do
    程序
done

#for循环使用：
[root@localhost test]# for os in linux windows macos; do echo $os; done
linux
windows
macos

#for循环使用：
[root@localhost test]# for i in {1..100}; do sum=$[$sum+$i]; done; echo $sum
5050
```

**测试 $* 和 $@ 的区别**：

- 一、$*和$@都表示传递给函数或脚本的所有参数，不被双引号“”包含时，都以$1 $2 …$n的形式输出所有参数
- 二、当它们被双引号“”包含时，$*会将所有的参数作为一个整体，以“$1 $2 …$n”的形式输出所有参数；$@会将各个参数分开，以“$1” “$2”…“$n”的形式输出所有参数

```shell
# 测试 $* 和 $@ 的区别：
#一、$*和$@都表示传递给函数或脚本的所有参数，不被双引号“”包含时，都以$1 $2 …$n的形式输出所有参数
[root@localhost test]# vim parameter_for_test.sh
#!/bin/bash
# 测试$*
echo '========$*======='
for para in $*
do
   echo $para
done

# 测试$@
echo '========$@======='
for para in $@
do
   echo $para
done
[root@localhost test]# chmod +x parameter_for_test.sh
[root@localhost test]# ./parameter_for_test.sh a b c d f g
========$*=======
a
b
c
d
f
g
========$@=======
a
b
c
d
f
g



# 二、当它们被双引号“”包含时，$*会将所有的参数作为一个整体，以“$1 $2 …$n”的形式输出所有参数；$@会将各个参数分开，以“$1” “$2”…“$n”的形式输出所有参数
[root@localhost test]# vim parameter_for_test.sh、
#!/bin/bash
# 测试$*
echo '========$*======='
for para in "$*"
do
   echo $para
done

# 测试$@
echo '========$@======='
for para in "$@"
do
   echo $para
done
[root@localhost test]# chmod +x parameter_for_test.sh
[root@localhost test]# ./parameter_for_test.sh a b c d f g
========$*=======
a b c d f g
========$@=======
a
b
c
d
f
g
```



###  11.4 while 循环

```shell
#while循环语法：
while [ 条件判断式 ]
do
    程序
done

#while循环实现累加
[root@localhost test]# vim while_test.sh
#!/bin/bash
#方法一
a=1
while [ $a -le $1 ]
do
   sum=$[ $sum + $a]
   a=$[$a + 1]
done
echo $sum

#方法二
b=1
while [ $b -le $1 ]
do
    let sum2+=b
    let b++
done
echo $sum2
[root@localhost test]# chmod +x while_test.sh
[root@localhost test]# ./while_test.sh 100
5050
5050
```

## 12. 读取控制台输入

```shell
#基本语法 
read (选项) (参数) 


#选项说明：
    选项         功能
    -p          指定读取值时的提示符
    -t          指定读取值时等待的时间（秒）如果-t 不加表示一直等待

#参数说明：指定读取值的变量名

#读取控制台参数
[root@localhost test]# vim read_test.sh
#!/bin/bash
read -t 10 -p "请输入您的用户名：" name
echo "welcome,$name"

[root@localhost test]# chmod +x read_test.sh
[root@localhost test]# ./read_test.sh
请输入您的用户名：lsls
welcome,lsls
```

##  13. 函数

###  13.1 系统函数

#####  13.1.1   basename 获取路径里的文件名称

```shell
# 基本语法、功能描述：
# basename命令会删掉所有的前缀包括最后一个（‘/’）字符，然后将字符串显示出来
basename [string / pathname] [suffix]
#选项: 
suffix为后缀，如果suffix被指定了，basename会将pathname或string中的suffix去掉
# basename可以理解为取路径里的文件名称


# basename的使用示例：
[root@localhost test]# basename /mydata/test/func_one.sh
func_one.sh
[root@localhost test]# basename /mydata/test/func_one.sh .sh
func_one
[root@localhost test]# basename /mydata/test/func
func

# 使用basename获取脚本名称，并去除.sh后缀
[root@localhost test]# vim basename_test.sh
#!/bin/bash
echo scriptname: $(basename $0 .sh)

[root@localhost test]# chmod +x basename_test.sh
[root@localhost test]# ./basename_test.sh
scriptname: basename_test



# 调用系统函数date为日志文件生成时间戳后缀
[root@localhost test]# vim func_one.sh
#!/bin/bash
filename="$1"_log_$(date +%s)
echo $filename

[root@localhost test]# chmod +x func_one.sh
[root@localhost test]# ./func_one.sh prd
prd_log_1697473443
```

#####   13.1.2 dirname  获取文件路径的绝对路径

```shell
# dirname功能描述：从给定的包含绝对路径的文件名中去除文件名 （非目录的部分），然后返回剩下的路径（目录的部分）
# dirname可以理解为取文件路径的绝对路径名称
# 语法：
dirname 文件绝对路径 

# 获取/mydata/test/func_one.sh的绝对路径
dirname /mydata/test/func_one.sh
/mydata/test


# 使用dirname获取文件的绝对路径
[root@localhost test]# vim dirname_test.sh
#!/bin/bash
echo script path: $(cd $(dirname $0); pwd)

[root@localhost test]# chmod +x dirname_test.sh
[root@localhost test]# ./dirname_test.sh
script path: /mydata/test

# 获取 banzhang.txt 文件的路径
[root@localhost test]# dirname /home/atguigu/bangzhang.txt
/home/atguigu
```

### 13.2 自定义函数

**shell 自定义函数**：

- 必须在调用函数之前声明函数
  - shell脚本是逐行运行
  - 不会像其它语言一样先编译
- 函数返回值
  - 只能通过$?系统变量获得
  - 可以加return返回。如果不加return会以最后一条命令的运行结果作为返回值
  - return后跟数值n
  - return后的数值的取值范围只能是0-255

基本语法：

```shell
#基本语法
[ function ] funname[()]
{
Action;
[return int;]
}
```

自定义函数：

```shell
#自定义han'shu计算两个输入参数的和
[root@localhost test]# vim  fun_add_no_return.sh
#!/bin/bash
function add(){
      s=$[$1 + $2]
      echo "sum: "$s
}

read -p "请输入第一个参数: " a
read -p "请输入第二个参数: " b

add $a $b

[root@localhost test]# chmod +x fun_add_no_return.sh
[root@localhost test]# ./fun_add_no_return.sh
请输入第一个参数: 55
请输入第二个参数: 45
sum: 100
```

自定义函数：有返回值，但是返回值受限,返回值只能在0-255

```shell
#自定义函数计算两个输入参数的和（有返回值）
[root@localhost test]# vim fun_add_return.sh
#!/bin/bash
function add(){
     s=$[$1 + $2]
     return $s
}

read -p "请输入第一个参数: " a
read -p "请输入第二个参数: " b

add $a $b
echo $?

[root@localhost test]# chmod +x fun_add_return.sh
[root@localhost test]# ./fun_add_return.sh
请输入第一个参数: 67
请输入第二个参数: 37
104

[root@localhost test]# ./fun_add_return.sh
请输入第一个参数: 485
请输入第二个参数: 4554
175
#发现
```

自定义函数：利用echo返回，取值不会受到限制

```shell
[root@localhost test]# vim fun_return_no_limit.sh
#!/bin/bash
function add(){
     s=$[$1 + $2]
     echo $s
}

read -p "请输入第一个参数: " a
read -p "请输入第二个参数: " b

sum=$(add $a $b)
echo $[$sum * $sum]

[root@localhost test]# chmod +x fun_return_no_limit.sh
[root@localhost test]# ./fun_return_no_limit.sh
请输入第一个参数: 372
请输入第二个参数: 233
366025
```

##  14. 综合应用案例一

###  14.1 归档练习

场景：

- 实际生产应用中，往往需要对重要数据进行归档备份

需求：

- 实现一个每天对指定目录归档备份的脚本，输入一个目录名称(末尾不带/)，将目录下所有文件按天归档保存，并将归档日期附加在归档文件名上，放在/root/archive下

实现思路：

- 利用归档命令: tar

- 后面可以加上-c选项表示归档，加上-z选项表示同时进行压缩，得到的文件后缀名为`.tar.gz`

**脚本实现**：

```shell
# 创建/root/archive目录
[root@localhost test]# mkdir /root/archive
```

```shell
# 创建并编辑daily_archive.sh脚本
[root@localhost test]# vim daily_archive.sh
```

编辑`daily_archive.sh`脚本，内容如下：

```shell
#!/bin/bash

# 首先判断输入参数的个数是否为1
if [ $# -ne 1 ]
then
    echo "参数个数错误！应该输入一个参数，作为归档目录名"
    exit
fi

# 从参数中获取目录名称
if [ -d $1 ]
then
    echo
else
    echo
    echo "目录不存在！"
    echo
    exit
fi

DIR_NAME=$(basename $1)
DIR_PATH=$(cd $(dirname $1); pwd)

# 获取当前日期
DATE=$(date +%y%m%d)

# 定义生成的归档文件的名称和文件路径
FILE=archive_${DIR_NAME}_$DATE.tar.gz
DEST=/root/archive/$FILE

# 开始归档目录文件
echo "开始归档..."
echo
tar -czf $DEST $DIR_PATH/$DIR_NAME

if [ $? -eq 0 ]
then
    echo
    echo "归档成功！"
    echo "归档文件为：$DEST"
    echo
else
    echo
    echo "归档出现问题"
    echo
fi

exit
```

开启脚本`daily_archive.sh`的执行权限：

```shell
# 开启脚本daily_archive.sh的执行权限
[root@localhost test]# chmod u+x daily_archive.sh
```

执行脚本对文件进行归档：

```shell
[root@localhost test]# ./daily_archive.sh ../test

开始归档...

tar: Removing leading `/' from member names

归档成功！
归档文件为：/root/archive/archive_test_231016.tar.gz
```

查看归档文件：

```shell
[root@localhost test]# cd /root/archive/
[root@localhost archive]# ls
archive_test_231016.tar.gz
```

将归档文件设为定时任务，明天凌晨两点进行归档：

```shell
[root@localhost archive]# crontab -e
0 2 * * *  /mydata/test/daily_archive.sh /root/archive


[root@localhost archive]# crontab -l
0 2 * * *  /mydata/test/daily_archive.sh /root/archive
```

##   15. 正则表达式

正则表达式使用单个字符串来描述、匹配一系列符合某个语法规则的字符串。在很多文本编辑器里，正则表达式通常被用来检索、替换那些符合某个模式的文本。在 Linux 中, grep,sed，awk 等文本处理工具都支持通过正则表达式进行模式匹配

###  15.1 常规匹配

一串不包含特殊字符的正则表达式匹配它自己，例如：

```shell
# 匹配包含字符串DIR_NAME的内容
[root@localhost test]# cat daily_archive.sh | grep DIR_NAME
DIR_NAME=$(basename $1)
FILE=archive_${DIR_NAME}_$DATE.tar.gz
tar -czf $DEST $DIR_PATH/$DIR_NAME
```

###  15.2 常用特殊字符

#####  15.2.1 特殊字符 ^

```shell
特殊字符 ^ 作用：匹配一行的开头

# 会匹配出所有以 a 开头的行
cat /etc/passwd | grep ^a

# 匹配所有以echo开头的行
[root@localhost test]# cat daily_archive.sh | grep ^echo
echo "开始归档..."
echo
```

#####  15.2.2 特殊字符 $

```shell
特殊字符 $ 作用：匹配一行的结束

# 匹配所有以t结尾的行
cat /etc/passwd | grep t$

# 匹配以echo结尾的行的内容
cat daily_archive.sh | grep echo$
    echo
    
# 思考：^$ 匹配什么？
# ^$匹配的是文件中的所有空行 （也就是匹配所有不包含任何字符的行）
cat daily_archive.sh | grep ^$

# 查看空行和空行所在的行号
cat daily_archive.sh | grep -n ^$
```

#####  15.2.3 特殊字符 .

```shell
特殊字符 .  作用:匹配一个任意的字符

# 匹配 r[一个任意字符][一个任意字符]t
# 如rabt,rbbt,rxdt,root 
cat daily_archive.sh | grep r..t
# 输出：DEST=/root/archive/$FILE
```

#####  15.2.4 特殊字符 *

```shell
# 特殊字符：* 
# 说明：不单独使用，他和上一个字符连用，表示匹配上一个字符0次或多次

# 匹配 rt, rot, root, rooot, roooot 等所有行 
cat daily_archive.sh | grep ro*t
DEST=/root/archive/$FILE

# 思考：.* 匹配什么？ 答: .*匹配任意字符
```

#####  15.2.5 字符区间 [ ]

```shell
[ ]          表示匹配某个范围内的一个字符
[6,8]        匹配6或者8
[0-9]        匹配一个0-9的数字[0-9]
[0-9]*       匹配任意长度的数字字符串
[a-z]        匹配一个 a-z 之间的字符
[a-z]*       匹配任意长度的小写字母字符串
[a-c, e-f]   匹配 a-c 或者 e-f 之间的任意字符

# 匹配 rt,rat, rbt, rabt, rbact,rabccbaaacbt 等等
cat /etc/passwd | grep r[a,b,c]*t

# 匹配rt,rot,root
cat daily_archive.sh | grep r[o]*t
# 输出：DEST=/root/archive/$FILE
```

#####  15.2.6 特殊字符  \ 

\ 表示转义，并不会单独使用

由于所有特殊字符都有其特定匹配模式，当我们想匹配 某一特殊字符本身时（例如想找出所有包含 '$' 的行），就会碰到困难

此时我们就要 将转义字符和特殊字符连用，来表示特殊字符本身

```shell




# 匹配特殊字符$ (注意需要使用单引号将表达式引起来)
[root@localhost test]# cat daily_archive.sh | grep '\$'
if [ $# -ne 1 ]
if [ -d $1 ]
DIR_NAME=$(basename $1)
DIR_PATH=$(cd $(dirname $1); pwd)
DATE=$(date +%y%m%d)
FILE=archive_${DIR_NAME}_$DATE.tar.gz
DEST=/root/archive/$FILE
tar -czf $DEST $DIR_PATH/$DIR_NAME
if [ $? -eq 0 ]
    echo "归档文件为：$DEST"
    
# 匹配所有包含 a$b 的行
[root@localhost test]# cat daily_archive.sh | grep 'a$b'
```

##  16. 文本处理工具

###  16.1 cut

```shell
# cut简介： 
cut的作用就是“剪”，具体的说就是在文件中负责剪切数据用的
cut命令从文件的每一行剪切字节、字符和字段并将这些字节、字符和字段输出

# 基本用法：
cut [选项参数] filename # 说明：默认分隔符是制表符

# 选项参数说明：
          选项参数         功能
            -f           列号，提取第几列
            -d           分隔符，按照指定分隔符分割列，默认是制表符“\t”
            -c           按字符进行切割后加上n 表示取第几列。比如 -c 1


# cut使用案例：
# 创建cut.txt文本并写入内容
[root@localhost test]# vim cut.txt
dong shen
guan zhen
wo wo
lai lai
le le

# 用空格对文本进行分割，并将分割后的第1列提取出来
[root@localhost test]# cut -d " " -f 1 cut.txt
dong
guan
wo
lai
le


# 用空格对文本进行分割，并将分割后的第2列提取出来
[root@localhost test]# cut -d " " -f 2 cut.txt
shen
zhen
wo
lai
le

# 提取系统中用户的用户名称
[root@localhost test]# cat /etc/passwd | grep bash$ | cut -d ":" -f 1
root
vagrant
david
xiaoming
xiaoliang

# 提取系统中用户的用户名称、id、目录，并指定输出分隔符为"  "
[root@localhost test]# cat /etc/passwd | grep bash$ | cut -d ":" -f 1,4,6  --output-delimiter "  "
root  0  /root
vagrant  1000  /home/vagrant
david  1001  /home/david
xiaoming  1002  /home/xiaoming
xiaoliang  1003  /home/xiaoliang

# 搜索passwd文件以root关键字开头的所有行，并输出该行的第7列
[root@localhost test]# cat /etc/passwd | grep ^root | cut -d ":" -f 7
/bin/bash
```

###  16.2 awk

#####  16.2.1 awk 初步使用

awk 一个强大的文本分析工具，把文件逐行的读入，以空格为默认分隔符将每行切片，切开的部分再进行分析处理

```shell
# 基本用法
# pattern：表示 awk 在数据中查找的内容，就是匹配模式 
# action： 在找到匹配内容时所执行的一系列命令
# 注意：只有匹配了pattern的行才会执行action
awk [选项参数] '/pattern1/{action1} /pattern2/{action2}...' filename 


# 选项参数说明： 
            选项参数           功能 
            -F               指定输入文件分隔符 
            -v               赋值一个用户定义变量 

# 搜索passwd文件以root关键字开头的所有行，并输出该行的第7列
[root@localhost test]# cat /etc/passwd | awk -F ":" '/^root/ {print $7}'
/bin/bash


# 搜索passwd文件以root关键字开头的所有行，并输出该行的第1、6、7列，中间以“,”号分割
[root@localhost test]# cat /etc/passwd | awk -F ":"  '/^root/ {print $1"," $6"," $7}'
root,/root,/bin/bash


# 只显示/etc/passwd的第一列和第七列，以逗号分割
# 且在所有行前面添加列名user,shell 在最后一行添加"END OF FILE"
[root@localhost test]# cat /etc/passwd | awk -F ":"  'BEGIN{print "user, shell"}{print $1","$7} END{print "END OF FILE"}'
user, shell
root,/bin/bash
bin,/sbin/nologin
daemon,/sbin/nologin
adm,/sbin/nologin
lp,/sbin/nologin
sync,/bin/sync
postfix,/sbin/nologin
chrony,/sbin/nologin
vagrant,/bin/bash
david,/bin/bash
xiaoming,/bin/bash
xiaoliang,/bin/bash
END OF FILE

# 注意:BEGIN在所有数据读取行之前执行;END在所有数据执行之后执行


# 将passwd文件中的用户id增加数值1并输出
# 实现方法一：
cat /etc/passwd | awk -F ":"  '{print $3+1}'
# 实现方法二：（可以改变参数i的值来实现数字增加i）
[root@localhost test]# cat /etc/passwd | awk -v i=1 -F ":"  '{print $3+i}'
```

#####  16.2.1  awk的内置变量

| 变量     | 说明                                   |
| -------- | -------------------------------------- |
| FILENAME | 文件名                                 |
| NR       | 已读的记录数（行号)                    |
| NF       | 浏览记录的域的个数（切割后，列的个数） |

```shell
# 案例演示

# 统计passwd文件名，每行的行号，每行的列数
[root@localhost vagrant]# awk -F ":"  '{print "filename: "FILENAME " line: "NR " columnNum: "NF}' /etc/passwd
filename: /etc/passwd line: 1 columnNum: 7
filename: /etc/passwd line: 2 columnNum: 7


# 查询ifconfig 命令输出结果中的空行所在的行号
[root@localhost vagrant]# ifconfig | awk '/^$/ {print NR}'
9
18
27
```

##  17. 综合案例练习二

###  17.1 发送消息

**linux系统发送消息**：

- 我们可以利用Linux自带的mesg 和 write工具，向其它用户发送消息。同一个linux系统可以同时登录很多个用户，这些用户之间是可以相互之间发送消息的


**需求**：

- 实现一个向某个用户快速发送消息的脚本，输入用户名作为第一个参数，后面直接跟要发送的消息。脚本需要检测用户是否登录在系统中、是否打开消息功能，以及当前发送消息是否为空

**相关命令回顾、补充**：

```shell
# 查看系统当前的登录用户
[root@localhost ~]# who am i
root     pts/1        2023-10-17 01:57 (192.168.56.1)


# 查看登录当前linux系统的使用用户
[root@localhost ~]# who
vagrant  pts/0        2023-10-17 01:33 (10.0.2.2)
root     pts/1        2023-10-17 01:57 (192.168.56.1)
xiaoming pts/2        2023-10-17 01:54 (192.168.56.1)

# 查看系统是否打开消息发送功能
[root@localhost ~]# mesg
is y

# 在root用户的终端对用户名为xiaoming的用户发送消息
[root@localhost ~]# write xiaoming
hi,xiaoming
hello
how are you

# 用户名为xiaoming的用户会立即接收到root用户发送的消息
[xiaoming@localhost ~]$
Message from root@localhost.localdomain on pts/1 at 02:13 ...
hi,xiaoming
hello
how are you
EOF
```

**脚本实现如下**：

创建发送消息的脚本文件`send_msg.sh`

```shell
[root@localhost vagrant]# vim send_msg.sh
```

编写`send_msg.sh`进行消息发送

```shell
#!/bin/bash

# 查看用户是否登录(grep -i 忽略大小写)
login_user=$(who | grep -i -m 1 $1 | awk '{print $1}')

# -z 判断字符串是否为空
if [ -z $login_user ]
then
    echo "$1 不在线！"
    echo "脚本退出..."
    exit
fi


# 查看用户是否开启了消息发送功能
is_allowed=$(who -T | grep -i -m 1 $1 | awk '{print $2}')


if [ $is_allowed != "+" ]
then
    echo "$1 没有开启消息功能"
    echo "脚本退出..."
    exit
fi

# 确认是否有消息发送

if [ -z $2 ]
then
    echo "没有消息发送"
    echo "脚本退出..."
    exit
fi

# 从参数中获取要发送的消息
whole_msg=$(echo $* | cut -d " " -f 2-)

# 获取用户登录的终端
user_terminal=$(who | grep -i -m $1 | awk '{print $2}')


# 写入要发送的消息
echo $whole_msg | write $login_user $user_terminal

if [ $? != 0 ]
then
     echo "发送失败！"
else
     echo "发送成功！"
fi

exit
```

修改文件权限：

```shell
[root@localhost vagrant]# chmod +x send_msg.sh
```

调用脚本`send_msg.sh`进行消息发送

```shell
# 用户root向用户xiaoming发送消息
[root@localhost test]# ./send_msg.sh xiaoming hello xiaoming,you are so great!


# 用户xiaoming接收到消息
[xiaoming@localhost ~]$
Message from vagrant@localhost.localdomain (as root) on pts/0 at 02:44 ...
hello xiaoming,you are so great!
EOF
```












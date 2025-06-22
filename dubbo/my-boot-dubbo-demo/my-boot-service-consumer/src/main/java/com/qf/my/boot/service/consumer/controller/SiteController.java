package com.qf.my.boot.service.consumer.controller;

import com.qf.boot.api.SiteService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/site")
public class SiteController {
    //<dubbo:reference : 两件事情：1.服务启动时向注册中心订阅SiteService服务地址列表 2.生成SiteService服务的代理对象
    @Reference
    private SiteService siteService;
    @GetMapping("/name")
    public String getName(String name){
        return siteService.getName(name);
    }

}

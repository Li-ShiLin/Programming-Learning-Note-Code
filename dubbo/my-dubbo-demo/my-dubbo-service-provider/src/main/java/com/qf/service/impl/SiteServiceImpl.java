package com.qf.service.impl;
import com.qf.api.SiteService;
// 要把这个服务交给dubbo容器-》在项目中整合dubbo
public class SiteServiceImpl implements SiteService {
    @Override
    public String getName(String name) {
        return "hello:" + name;
    }
}

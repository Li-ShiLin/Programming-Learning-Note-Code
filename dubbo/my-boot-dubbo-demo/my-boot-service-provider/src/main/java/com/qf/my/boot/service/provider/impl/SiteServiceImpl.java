package com.qf.my.boot.service.provider.impl;

import com.qf.boot.api.SiteService;
import org.apache.dubbo.config.annotation.Service;
@Service
public class SiteServiceImpl implements SiteService {
    @Override
    public String getName(String name) {
        return "hello boot:" + name;
    }
}

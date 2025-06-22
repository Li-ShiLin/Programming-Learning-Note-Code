package com.qf.my.dubbo.provider.impl;

import com.qf.site.SiteService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 
 * 
 */
@Service(version = "timeout", timeout = 4000)
public class TimeoutSiteServiceImpl implements SiteService {
    @Override
    public String siteName(String name) {
        try {
            //如果服务执行时间超过了指定的超时时间则会打印warn日志
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("serving...");
        return "timeout site service:"+name;
    }
}

package com.qf.my.dubbo.provider.impl;

import com.qf.site.SiteService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 
 * 
 */
@Service(version = "async")
public class AsyncSiteServiceImpl implements SiteService {

    @Override
    public String siteName(String name) {
        return "async:" + name;
    }

    @Override
    public CompletableFuture<String> siteNameAsync(String name) {
        System.out.println("异步调用：" + name);
        return CompletableFuture.supplyAsync(() -> {
            return siteName(name);
        });
    }
}

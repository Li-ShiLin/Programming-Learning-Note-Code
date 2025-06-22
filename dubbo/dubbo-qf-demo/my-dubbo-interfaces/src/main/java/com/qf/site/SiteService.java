package com.qf.site;

import java.util.concurrent.CompletableFuture;

/**
 * 
 * 
 */
public interface SiteService {
    //同步调用方法
    String siteName(String name);

    //回调方法
    default String siteName(String name, String key, SiteServiceListener siteServiceListener){
        return null;
    }

    //异步调用方法
    default CompletableFuture<String> siteNameAsync(String name){
        return null;
    }
}

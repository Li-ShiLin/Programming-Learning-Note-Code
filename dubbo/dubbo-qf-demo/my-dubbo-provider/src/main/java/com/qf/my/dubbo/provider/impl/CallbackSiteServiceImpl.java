package com.qf.my.dubbo.provider.impl;

import com.qf.site.SiteService;
import com.qf.site.SiteServiceListener;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;

/**
 * 
 * 
 */
@Service(version = "callback", methods = {@Method(name = "siteName", arguments = {@Argument(index = 2, callback = true)})}, callbacks = 3)
public class CallbackSiteServiceImpl implements SiteService {
    @Override
    public String siteName(String name) {
        return null;
    }

    @Override
    public String siteName(String name, String key, SiteServiceListener siteServiceListener) {
        siteServiceListener.changed("provider data");
        return "callback:"+name;
    }
}

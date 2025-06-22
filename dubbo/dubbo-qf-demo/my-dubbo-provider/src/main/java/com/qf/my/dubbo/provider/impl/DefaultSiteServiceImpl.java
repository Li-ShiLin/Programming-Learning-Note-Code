package com.qf.my.dubbo.provider.impl;

import com.qf.site.SiteService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 
 * 
 */
@Service(version = "default",loadbalance = "roundrobin")
public class DefaultSiteServiceImpl implements SiteService {
    @Override
    public String siteName(String name) {
        return "default:"+name;

//        URL url = RpcContext.getContext().getUrl();
//        return String.format("%s：%s, Hello, %s", url.getProtocol(), url.getPort(), name);
    }
}

package com.qf.my.dubbo.provider.impl;

import com.qf.site.SiteService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.protocol.rest.support.ContentType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
//@Service(version = "rest", protocol = "protocol1")
@Path("site")
public class RestSiteService implements SiteService {
    @Override
    @GET
    @Path("name")
    @Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_PLAIN_UTF_8})
    public String siteName(@QueryParam("name") String name) {
        return "rest:" + name;
    }
}

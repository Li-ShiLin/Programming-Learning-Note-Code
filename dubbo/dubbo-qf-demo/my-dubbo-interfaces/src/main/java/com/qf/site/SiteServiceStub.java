package com.qf.site;

import com.qf.site.SiteService;

/**
 * 
 * 
 */
public class SiteServiceStub implements SiteService {

    private final SiteService siteService;

    public SiteServiceStub(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public String siteName(String name) {
        try {
            return siteService.siteName(name);
        } catch (Exception e) {
            return "stub:"+name;
        }
    }
}

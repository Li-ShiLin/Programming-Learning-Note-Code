package com.qf.site;

import com.qf.site.SiteServiceListener;

import java.io.Serializable;

/**
 * 
 * 
 */
public class SiteServiceListenerImpl implements SiteServiceListener, Serializable {
    @Override
    public void changed(String data) {
        System.out.println("changed:" + data);
    }
}

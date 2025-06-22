package com.qf.provider.api.common.protocol.dubbo;

import com.qf.provider.api.common.Invocation;
import com.qf.provider.api.common.Protocol;
import com.qf.provider.api.common.URL;

/**
 * 
 * 
 */
public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
       /* NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());*/

    }

    @Override
    public String send(URL url, Invocation invocation) {
       /* NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(), invocation);*/
        return "";
    }
}

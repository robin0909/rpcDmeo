package com.robin.rpc.container.impl;

import com.robin.rpc.container.Container;
import com.robin.rpc.invoke.ProviderConfig;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;

/**
 * Created by robin on 2016/10/8.
 */
public class HttpContainer extends Container {

    private ProviderConfig config;
    private AbstractHandler httpHandler;

    public HttpContainer(AbstractHandler handler) {
        this(new ProviderConfig("/invoke", 8080), handler);
    }

    public HttpContainer(ProviderConfig config, AbstractHandler handler) {
        this.config = config;
        this.httpHandler = handler;
        Container.container = this;
    }

    @Override
    public void start() {
        Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        //connector.setHost("127.0.0.1"); //设置host
        server.setConnectors(new Connector[]{connector});

        server.setHandler(httpHandler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

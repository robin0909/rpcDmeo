package com.robin.rpc.invoke;

/**
 * Created by robin on 2016/10/7.
 */
public class ProviderConfig {

    private String url;
    private int port;

    public ProviderConfig() {
    }

    public ProviderConfig(String url, int port) {

        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

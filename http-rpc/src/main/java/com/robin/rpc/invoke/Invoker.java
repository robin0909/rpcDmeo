package com.robin.rpc.invoke;

import java.io.OutputStream;

/**
 * 1.发送报文跟服务器交互
 * 2.响应服务器的请求
 * Created by robin on 2016/10/7.
 */
public interface Invoker {

    /**
     * 请求调用
     * @param param 请求报文
     * @param consumerConfig    请求配置（url、port）
     * @return  返回结果报文
     * @throws Exception
     */
    String request(String param, ConsumerConfig consumerConfig) throws Exception;

    /**
     * 响应请求，将服务器端的结果流响应输出
     * @param param
     * @param out
     */
    void response(String param, OutputStream out);
}

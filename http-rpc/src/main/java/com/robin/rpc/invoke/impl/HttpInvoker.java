package com.robin.rpc.invoke.impl;

import com.robin.rpc.invoke.ConsumerConfig;
import com.robin.rpc.invoke.Invoker;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * 调用层，这里会将报文通过网络发送给
 * Created by robin on 2016/10/7.
 */
public class HttpInvoker implements Invoker {
    private final static HttpClient httpClient = HttpInvoker.getHttpClient();

    public static final Invoker invoker = new HttpInvoker();

    @Override
    public String request(String request, ConsumerConfig consumerConfig) throws Exception {
        HttpPost httpPost = new HttpPost(consumerConfig.getUrl());  //创建一个请求报文
        httpPost.setHeader("Connection", "Keep-Alive");

        try {
            ArrayList<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("data", request));
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(),"UTF-8");
            }
            throw new Exception("http请求异常！");
        } catch (Exception e) {
            throw new Exception("http调用异常："+e);
        }
    }

    @Override
    public void response(String param, OutputStream out) {
        try {
            out.write(param.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpClient getHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);    //最大连接数
        cm.setDefaultMaxPerRoute(20);   //默认的最大连接数

        HttpHost localhost = new HttpHost("127.0.0.1", 8080);
        //指定路由，最大连接数50
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);
        return HttpClients.custom().setConnectionManager(cm).build();
    }
}

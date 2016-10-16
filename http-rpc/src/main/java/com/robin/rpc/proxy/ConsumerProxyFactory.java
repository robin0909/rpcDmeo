package com.robin.rpc.proxy;

import com.robin.rpc.invoke.ConsumerConfig;
import com.robin.rpc.invoke.Invoker;
import com.robin.rpc.invoke.impl.HttpInvoker;
import com.robin.rpc.serialize.Formater;
import com.robin.rpc.serialize.Parser;
import com.robin.rpc.serialize.Request;
import com.robin.rpc.serialize.json.JsonFormater;
import com.robin.rpc.serialize.json.JsonParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 根据传入的接口，来造代理代理对象
 * Created by robin on 2016/10/8.
 */
public class ConsumerProxyFactory implements InvocationHandler{

    private ConsumerConfig consumerConfig;
    private String clazz;

    private Invoker invoker = HttpInvoker.invoker;
    private Formater formater = JsonFormater.formater;
    private Parser parser = JsonParser.parser;

    public Object create() throws ClassNotFoundException {
        Class interfaceClass = Class.forName(clazz);
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class clazz = proxy.getClass().getInterfaces()[0];  //拿到的是代理对象所实现的服务接口
        Request request = new Request(clazz, method.getName(), args[0]);
        String response = invoker.request(formater.reqFormat(request), consumerConfig);
        return parser.rsbParse(response);
    }

    public ConsumerConfig getConsumerConfig() {
        return consumerConfig;
    }

    public void setConsumerConfig(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}

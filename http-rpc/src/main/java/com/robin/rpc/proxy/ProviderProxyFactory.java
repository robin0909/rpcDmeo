package com.robin.rpc.proxy;

import com.robin.rpc.container.Container;
import com.robin.rpc.container.impl.HttpContainer;
import com.robin.rpc.invoke.Invoker;
import com.robin.rpc.invoke.impl.HttpInvoker;
import com.robin.rpc.serialize.Formater;
import com.robin.rpc.serialize.Parser;
import com.robin.rpc.serialize.Request;
import com.robin.rpc.serialize.json.JsonFormater;
import com.robin.rpc.serialize.json.JsonParser;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * 提供者将将自己的服务注册进来
 * Created by robin on 2016/10/8.
 */
public class ProviderProxyFactory extends AbstractHandler {

    private static Logger log = Logger.getLogger(ProviderProxyFactory.class.getName());

    private Map providers = new ConcurrentHashMap<Class, Object>();

    private static ProviderProxyFactory factory;

    private Parser parser = JsonParser.parser;
    private Formater formater = JsonFormater.formater;
    private Invoker invoker = HttpInvoker.invoker;

    public ProviderProxyFactory(Map<Class, Object> providers) {
        for(Map.Entry<Class, Object> entry : providers.entrySet()) {
            register(entry.getKey(), entry.getValue());
        }
        factory = this;

        if(Container.container == null) {
            new HttpContainer(this).start();
        }

    }

    //注册服务
    public void register(Class clazz, Object object) {
        providers.put(clazz, object);
        log.info(clazz.getSimpleName()+"-------服务注册成功！");
    }

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
        String data = request.getParameter("data");
        try {
            Request reqParse = parser.reqParse(data);   //将json解析成Request对象
            Object res = reqParse.invoke(ProviderProxyFactory.getInstance().getBeanByClass(reqParse.getClazz()));
            invoker.response(formater.rsbFormat(res), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBeanByClass(Class clazz) throws Exception {
        Object bean = providers.get(clazz);
        if(bean != null) {
            return bean;
        }
        throw new Exception("没有找到服务");
    }

    public static ProviderProxyFactory getInstance() {
        return factory;
    }
}

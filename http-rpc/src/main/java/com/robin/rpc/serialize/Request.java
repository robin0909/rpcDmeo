package com.robin.rpc.serialize;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 请求的实体
 * Created by robin on 2016/10/6.
 */
public class Request implements Serializable {

    private Class clazz;    //请求的借口

    private String method;  //请求的方法

    private Object param;   //请求的参数

    public Request(Class clazz, String method, Object param) {
        this.clazz = clazz;
        this.method = method;
        this.param = param;
    }

    public Request() {
    }

    public Class getClazz() {
        return clazz;

    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Object invoke(Object bean)throws Exception {
        Method method = findMethod(this.method, clazz);
        return method.invoke(bean, param);
    }

    public Method findMethod(String methodName, Class clazz) throws NoSuchMethodException {
        String methodName1 = methodName.intern();
        Method[] methods = clazz.getMethods();
        for (Method method:methods) {
            if(method.getName().intern()==methodName1){
                return method;
            }
        }
        throw new NoSuchMethodException("没有找到方法");
    }
}

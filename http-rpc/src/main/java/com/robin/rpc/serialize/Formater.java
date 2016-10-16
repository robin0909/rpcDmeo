package com.robin.rpc.serialize;

/**
 * 将对象封装成报文
 * Created by robin on 2016/10/6.
 */
public interface Formater {

    /**
     * 请求的实体封装成报文
     * @param req   请求的实体，里面包含请求的接口、方法，参数
     * @return
     */
    String reqFormat(Request req);

    /**
     * 将响应的对象封装成报文
     * @param param 响应的结果
     * @return
     */
    String rsbFormat(Object param);
}

package com.robin.rpc.serialize;

/**
 * 将报文解析成对象
 * Created by robin on 2016/10/6.
 */
public interface Parser {

    /**
     * 将请求的报文解析成实体Request对象
     * @param param 报文实体
     * @return
     */
    Request reqParse(String param) throws Exception;

    /**
     * 将结果报文解析成对应的实体对象
     * @param param
     * @param <T>
     * @return
     */
    public <T> T rsbParse(String param);

}

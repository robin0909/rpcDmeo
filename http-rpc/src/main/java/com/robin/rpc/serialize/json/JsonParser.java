package com.robin.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.robin.rpc.serialize.Parser;
import com.robin.rpc.serialize.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by robin on 2016/10/7.
 */
public class JsonParser implements Parser {

    private static Logger log = LoggerFactory.getLogger(JsonParser.class);

    public static final Parser parser = new JsonParser();

    @Override
    public Request reqParse(String param) throws Exception {
        try {
            log.debug("调用参数为{}", param);
            return (Request) JSON.parse(param);
        } catch (Exception e) {
            log.error("json解析异常");
            throw new Exception("json解析异常:"+e.getMessage());
        }

    }

    @Override
    public <T> T rsbParse(String param) {
        return (T) JSON.parse(param);
    }
}

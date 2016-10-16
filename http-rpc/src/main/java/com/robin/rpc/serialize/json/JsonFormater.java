package com.robin.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.robin.rpc.serialize.Formater;
import com.robin.rpc.serialize.Request;

import java.util.ArrayList;

/**
 * Created by robin on 2016/10/6.
 */
public class JsonFormater implements Formater {

    public static final Formater formater = new JsonFormater();

    @Override
    public String reqFormat(Request req) {
        return JSON.toJSONString(req, SerializerFeature.WriteClassName);
    }

    @Override
    public String rsbFormat(Object param) {
        return JSON.toJSONString(param, SerializerFeature.WriteClassName);
    }

}

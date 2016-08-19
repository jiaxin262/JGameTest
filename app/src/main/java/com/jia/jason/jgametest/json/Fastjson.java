package com.jia.jason.jgametest.json;

/**
 * Created by xin.jia
 * since 2016/8/19
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jia.jason.jgametest.util.JsonUtils;

import java.util.List;
import java.util.Map;

public class Fastjson implements JsonUtils.IParser{
    public Fastjson() {
    }

    public Object parseObject(String text, Class<?> clazz) {
        return JSON.parseObject(text, clazz);
    }

    public List parseArray(String text, Class<?> clazz) {
        return JSON.parseArray(text, clazz);
    }

    public String toJsonString(Object object) {
        return JSON.toJSONString(object, new SerializerFeature[]{SerializerFeature.WriteTabAsSpecial});
    }

    public Map<String, Object> fromJson(String jsonstr) {
        return (Map)JSON.parse(jsonstr);
    }

    public Map<String, Object> fromBean(Object object) {
        return (Map)JSON.toJSON(object);
    }
}

package com.jia.jason.jgametest.json;

/**
 * Created by xin.jia
 * since 2016/8/19
 */
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jia.jason.jgametest.util.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Jackson implements JsonUtils.IParser {
    private ObjectMapper mapper = new ObjectMapper();

    public Jackson() {
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    public Object parseObject(String text, Class<?> clazz) {
        try {
            return this.mapper.readValue(text, clazz);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public List parseArray(String text, Class<?> clazz) {
        throw new UnsupportedOperationException("do it later");
    }

    public String toJsonString(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> fromJson(String jsonstr) {
        try {
            return (Map)this.mapper.readValue(jsonstr, Map.class);
        } catch (JsonParseException var3) {
            var3.printStackTrace();
        } catch (JsonMappingException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public Map<String, Object> fromBean(Object object) {
        return this.fromJson(this.toJsonString(object));
    }
}

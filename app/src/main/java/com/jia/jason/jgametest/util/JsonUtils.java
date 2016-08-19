package com.jia.jason.jgametest.util;

/**
 * Created by xin.jia
 * since 2016/8/19
 */
import com.jia.jason.jgametest.json.Fastjson;
import com.jia.jason.jgametest.json.Jackson;
import com.jia.jason.jgametest.json.JsonwireException;

import java.util.List;
import java.util.Map;

public class JsonUtils {
    static JsonUtils.IParser parser = new JsonUtils.ParserProxy();

    public JsonUtils() {
    }

    public static void chooseParser(String name) {
        try {
            if("fastjson".equals(name)) {
                parser = new Fastjson();
            } else {
                if(!"jackson".equals(name)) {
                    throw new IllegalArgumentException("no such parser found");
                }

                parser = new Jackson();
            }

        } catch (Exception var2) {
            throw new JsonwireException(name + "not found in library");
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return (T)parser.parseObject(text, clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return parser.parseArray(text, clazz);
    }

    public static String toJsonString(Object object) {
        return parser.toJsonString(object);
    }

    public static Map<String, Object> fromJson(String jsonstr) {
        return parser.fromJson(jsonstr);
    }

    public static Map<String, Object> fromBean(Object object) {
        return parser.fromBean(object);
    }

    public static String getParserName() {
        return parser.getClass().getSimpleName().toLowerCase();
    }

    static class ParserProxy implements JsonUtils.IParser {
        JsonUtils.IParser ip;

        ParserProxy() {
            Class e;
            try {
                e = Class.forName("com.alibaba.fastjson.JSON");
                this.ip = new Fastjson();
            } catch (ClassNotFoundException var3) {
                try {
                    e = Class.forName("com.fasterxml.jackson.databind.ObjectMapper");
                    this.ip = new Jackson();
                } catch (ClassNotFoundException var2) {
                    if(this.ip == null) {
                        throw new NullPointerException("No json found");
                    }
                }
            }
        }

        public String toJsonString(Object object) {
            return this.ip.toJsonString(object);
        }

        public Object parseObject(String text, Class<?> clazz) {
            return this.ip.parseObject(text, clazz);
        }

        public List parseArray(String text, Class<?> clazz) {
            return this.ip.parseArray(text, clazz);
        }

        public Map<String, Object> fromJson(String jsonstr) {
            return this.ip.fromJson(jsonstr);
        }

        public Map<String, Object> fromBean(Object object) {
            return this.ip.fromBean(object);
        }
    }

    public interface IParser {
        Object parseObject(String var1, Class<?> var2);

        List parseArray(String var1, Class<?> var2);

        String toJsonString(Object var1);

        Map<String, Object> fromJson(String var1);

        Map<String, Object> fromBean(Object var1);
    }
}


package com.actor.mybatis_test.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Jackson序列化/反序列化
 * 1.添加依赖
 * <!-- Github修复漏洞: Bumps jackson-databind from 2.8.7 to 2.9.10.7 -->
 * <dependency>
 *     <groupId>com.fasterxml.jackson.core</groupId>
 *     <artifactId>jackson-databind</artifactId>
 *     <version>2.9.10.7</version>
 *     <scope>compile</scope>
 * </dependency>
 */
public class JacksonUtils {

    protected static ObjectMapper mapper = new ObjectMapper();

    static {
        //配置private 的属性也能序列化, 否则如果实体连 1个public字段/set方法 都没有的话, 会报错
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    /**
     * 对象转Json
     */
    public static String object2Json(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Json转对象
     */
    public static <T> T json2Object(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Json转List
     */
    public static <T> List<T> json2List(String json) {
        return json2Object(json, List.class);
    }

    /**
     * Json转Map, 如果Map里面是简单类型, 例: Map<String, Integer>
     */
    public static <K, V> Map<K, V> json2Map(String json) {
        return json2Object(json, Map.class);
    }

    /**
     * Json转Map, 如果Map里面是复杂类型
     * @param typeReference 类型引用, 示例: new TypeReference<Map<String, ResultValue>>() { }
     * @return 返回示例: Map<String, ResultValue>
     */
    public static <K, V> Map<K, V> json2MapWithType(String json, TypeReference<Map<K, V>> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

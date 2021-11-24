package com.qy23.sm.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JSONUtils
 * @Author 刘伟
 * @Date 2020/10/31 20:25
 * @Description
 * @Version 1.0
 **/
@Component
public class JSONUtils {

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转JSON
     *
     * @param obj
     * @return
     */
    public String obj2str(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 转普通对象
     *
     * @param jsonstr
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T str2obj(String jsonstr, Class<T> tClass) {
        T t = null;
        try {
            if (!StringUtils.isEmpty(jsonstr)) {
                t = objectMapper.readValue(jsonstr, tClass);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 转List集合
     *
     * @param jsonStr
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> str2List(String jsonStr, Class<T> tClass) {
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, tClass);
        List<T> list = null;
        try {
            list = objectMapper.readValue(jsonStr, collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转为map
     *
     * @param jsonStr
     * @param kClass
     * @param tClass
     * @param <K>
     * @param <T>
     * @return
     */
    public <K, T> Map<K, T> str2map(String jsonStr, Class<K> kClass, Class<T> tClass) {
        MapType mapType = objectMapper.getTypeFactory().constructMapType(Map.class, kClass, tClass);
        Map<K, T> map = null;
        try {
            map = objectMapper.readValue(jsonStr, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }


}

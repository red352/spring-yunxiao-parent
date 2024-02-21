package com.yunxiao.spring.core.util;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/10/20 0:51
 */
public class JsonUtils {
    @Nullable
    public static String getJsonValue(JSON json, List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return json.toJSONString(0);
        }
        return walkJson(json, keys, 0);
    }

    @Nullable
    public static String getJsonValue(Object object, List<String> keys) {
        JSON json = JSONUtil.parse(object);
        if (keys == null || keys.isEmpty()) {
            return json.toJSONString(0);
        }
        return walkJson(json, keys, 0);
    }

    private static String walkJson(JSON json, List<String> keys, int index) {
        if (json instanceof JSONObject jsonObject) {
            if (index >= keys.size() - 1) {
                return jsonObject.getStr(keys.getLast());
            }
            if (jsonObject.get(keys.get(index)) instanceof JSON json1) {
                return walkJson(json1, keys, ++index);
            }
            if (index != keys.size() - 1) {
                return null;
            }
            return jsonObject.getStr(keys.get(index));
        }
        return json.toJSONString(0);
    }
}

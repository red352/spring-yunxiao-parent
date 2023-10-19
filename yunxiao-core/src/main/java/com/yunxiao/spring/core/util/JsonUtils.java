package com.yunxiao.spring.core.util;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/10/20 0:51
 */
public class JsonUtils {
    @Nullable
    public static String getJsonValue(JSON json, List<String> treeKeys) {
        if (treeKeys == null || treeKeys.isEmpty()) {
            return json.toJSONString(0);
        }
        return walkJson(json, treeKeys, 0);
    }

    private static String walkJson(JSON json, List<String> treeKeys, int index) {
        if (json instanceof JSONObject jsonObject) {
            if (index >= treeKeys.size() - 1) {
                return jsonObject.getStr(treeKeys.getLast());
            }
            if (jsonObject.get(treeKeys.get(index)) instanceof JSON json1) {
                return walkJson(json1, treeKeys, ++index);
            }
            if (index != treeKeys.size() - 1) {
                return null;
            }
            return jsonObject.getStr(treeKeys.get(index));
        }
        return json.toJSONString(0);
    }
}

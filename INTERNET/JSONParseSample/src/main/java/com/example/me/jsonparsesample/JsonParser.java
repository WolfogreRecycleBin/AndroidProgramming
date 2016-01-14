package com.example.me.jsonparsesample;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/1/10.
 */
public class JsonParser {
    /**
     * 得到一个json类型的字符串对象
     * @param key
     * @param value
     * @return */
    public static String getJsonString(String key, Object value) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        //put和element都是往JSONObject对象中放入 key/value 对
        jsonObject.put(key, value);       //  jsonObject.element(key, value);
        return jsonObject.toString();
    }

    /**
     * 得到一个json对象
     * @param key
     * @param value
     * @return */
    public static JSONObject getJsonObject(String key, Object value) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject;
    }
}

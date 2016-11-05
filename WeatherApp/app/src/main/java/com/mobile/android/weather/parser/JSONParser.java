package com.mobile.android.weather.parser;

import org.json.JSONObject;

/**
 *
 *
 */
public interface JSONParser<T> {
    public T parse(JSONObject jsonObj);
}

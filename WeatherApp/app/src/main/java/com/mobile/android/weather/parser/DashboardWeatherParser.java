package com.mobile.android.weather.parser;

import com.mobile.android.weather.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class DashboardWeatherParser implements JSONParser<List<Weather>> {

    public static String KEY_WEATHER_LIST = "list";
    public static String KEY_DASHBOARD_VIEW_CONTENT = "view_content";
    public static String KEY_TEXT_TYPE = "text_type";
    public static String KEY_IMG_TYPE = "img_type";
    public static String KEY_VIDEO_TYPE = "video_type";
    public static String KEY_DASHBOARD_HEADER = "header";
    public static String KEY_IMG_SMALL = "small";
    public static String KEY_IMG_MEDIUM = "medium";
    public static String KEY_IMG_LARGE = "large";
    public static String KEY_CONTENT = "content";
    public static String KEY_TITLE = "title";
    public static String KEY_DESCRIPTION = "description";
    public static String KEY_SUMMARY = "summary";
    public static String KEY_VIDEO_URL = "videourl";
    private List<Weather> templateResponse;

    @Override
    public List<Weather> parse(JSONObject jsonObj) {
        if (templateResponse == null) {
            templateResponse = new ArrayList<Weather>();
        } else {
            templateResponse.clear();
        }

        if (jsonObj != null) {

            if (jsonObj.has("city")) {
                try {
                    final JSONObject cityJson = jsonObj.getJSONObject("city");
                    final String country = cityJson.getString("country");
                    final String city = cityJson.getString("name");

                    if (jsonObj.has(KEY_WEATHER_LIST)) {
                        final JSONArray dashboardContent = jsonObj.getJSONArray(KEY_WEATHER_LIST);

                        for (int index = 0; index < dashboardContent.length(); index++) {
                            final JSONObject jsonObject = dashboardContent.getJSONObject(index);

                            if (jsonObject != null) {
                                final Weather content = new Weather();
                                content.setCountryName(country);
                                content.setCityName(city);
                                if (jsonObject.has("main")) {
                                    final JSONObject object = jsonObject.getJSONObject("main");
                                    content.setTemperature(object.getString("temp"));
                                    content.setTemperatureMin(object.getString("temp_min"));
                                    content.setTemperatureMax(object.getString("temp_max"));
                                    content.setGroundLevel(object.getString("grnd_level"));
                                    content.setSeaLevel(object.getString("sea_level"));
                                    content.setHumidity(object.getString("humidity"));
                                }
                                if (jsonObject.has("wind")) {
                                    final JSONObject windJson = jsonObject.getJSONObject("wind");
                                    content.setWindSpeed(windJson.getString("speed"));
                                }
                                if (jsonObject.has("dt_txt")) {
                                    content.setDateTime(jsonObject.getString("dt_txt"));
                                }
                                templateResponse.add(content);
                            }

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return templateResponse;
    }
}
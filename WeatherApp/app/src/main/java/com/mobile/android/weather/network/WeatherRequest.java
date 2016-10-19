package com.mobile.android.weather.network;


import com.loopj.android.http.RequestParams;
import com.mobile.android.weather.utils.Constants;

import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class WeatherRequest extends Request {

    private String cityName;
    private String countryName;
    private String responseType;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public WeatherRequest() {
    }

    public WeatherRequest(final String baseURL) {
        this.baseURL = baseURL;
    }

    public WeatherRequest(String baseUrl, String username, String password) {
        this(baseUrl);
        this.cityName = username;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    @Override
    public List<NameValuePair> getPostData() {
        return null;
    }

    @Override
    public RequestParams getRequestParams() {
        return null;
    }

    @Override
    public Map<String, String> getHttpHeaders() {
        final Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put("Content-Type", "application/json;charset=utf-8");
        return requestHeaders;
    }

    @Override
    public String getUrl() {
        return baseURL + "q=" + cityName + "," + countryName + "&mode=" + responseType + "&appid=" + Constants.WEATHER_API_APP_ID;
    }

    @Override
    public String getRequestBody() {

        return null;
    }
}

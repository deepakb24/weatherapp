package com.mobile.android.weather.interfaces;

import android.content.Context;

import com.mobile.android.weather.model.Weather;
import com.mobile.android.weather.network.NetworkHandler;
import com.mobile.android.weather.network.WeatherRequest;
import com.mobile.android.weather.parser.DashboardWeatherParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 *
 */
public class WeatherEngine {

    private static WeatherEngine instance;
    public String baseURL = "http://api.openweathermap.org/data/2.5/forecast?";
    private NetworkHandler networkLayer;
    private Context context;

    private WeatherEngine() {
        setup();
    }

    public static WeatherEngine getEngineInstance() {
        if (instance == null) {
            instance = new WeatherEngine();
        }
        return instance;
    }

    private void setup() {
        networkLayer = NetworkHandler.getInstance();
    }

    public void setContext(final Context context) {
        this.context = context;
        if (networkLayer != null) {
            networkLayer.setContext(context);
        }
    }

    public void fetchDashboardContent(final WeatherRequest request, final ParserModelCallback<List<Weather>>
                                              contentItemList) {

        networkLayer.setBaseURL(baseURL);

        final NetworkRequestCallback callback = new NetworkRequestCallback() {
            @Override
            public void onSuccess(String data) {
                // TODO (Deepak): Need to update
                final DashboardWeatherParser parser = new DashboardWeatherParser();
                try {
                    final List<Weather> contents = parser.parse(new JSONObject(data));
                    contentItemList.onSuccess(contents);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ;
            }

            @Override
            public void onFailure(String reason) {
                contentItemList.onFailure(reason);
            }
        };
        try {
            networkLayer.fetchDashboardContent(request, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}

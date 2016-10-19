package com.mobile.android.weather.activity;

import android.app.Application;

import com.mobile.android.weather.interfaces.WeatherEngine;

/**
 */
public class WeatherApp extends Application {

    private static WeatherApp instance;
    private WeatherEngine engine;


    public static WeatherApp getInstance() {
        return instance;
    }

    public WeatherEngine getHealthCareEngine() {
        return engine;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        engine = WeatherEngine.getEngineInstance();
        engine.setContext(this);
    }

}

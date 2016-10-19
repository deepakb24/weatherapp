package com.mobile.android.weather.network;

/**
 */
public class NetworkRequestFactory {

    public static final int REQUEST_CODE_WEATHER_5DAYS = 1001;
    public static final int REQUEST_CODE_WEATHER_15DAYS = 1002;
    public static final int REQUEST_CODE_WEATHER_CURRENT_STATUS = 1003;

    private static NetworkRequestFactory factory;
    private String baseURL;

    private NetworkRequestFactory() {

    }

    public static NetworkRequestFactory getNetworkFacotry() {

        if (factory == null) {
            factory = new NetworkRequestFactory();
        }
        return factory;
    }

    public void setBaseURL(final String baseURL) {
        this.baseURL = baseURL;
    }

    public Request getRequest(final int requestCode) {
        switch (requestCode) {
            case REQUEST_CODE_WEATHER_5DAYS:
                return new WeatherRequest(baseURL);

            case REQUEST_CODE_WEATHER_15DAYS:
                return null;

            case REQUEST_CODE_WEATHER_CURRENT_STATUS:
                return null;

            default:
                break;
        }

        return null;
    }
}

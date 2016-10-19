package com.mobile.android.weather.network;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mobile.android.weather.interfaces.NetworkRequestCallback;
import com.mobile.android.weather.utils.Constants;
import com.mobile.android.weather.utils.WeatherUtils;

import org.apache.http.Header;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 */
public class NetworkHandler {

    private final static String TAG = "NetworkHandler";
    private static NetworkHandler instance;
    private static AsyncHttpClient networkClient;
    private String baseURL;
    private Context mContext;

    private NetworkHandler() {

    }

    public static NetworkHandler getInstance() {
        if (instance == null) {
            instance = new NetworkHandler();
        }

        return instance;
    }

    public AsyncHttpClient getHttpClient() {
        synchronized (this) {
            if (networkClient == null) {
                networkClient = new AsyncHttpClient();
            }
        }
        return networkClient;
    }

    public void setContext(final Context context) {
        this.mContext = context;
    }

    public String getBaseURL() {
        return this.baseURL;
    }

    public void setBaseURL(final String url) {
        this.baseURL = url;
    }


    public void fetchDashboardContent(WeatherRequest request, final NetworkRequestCallback callback) throws RuntimeException, UnsupportedEncodingException {
        if (mContext == null) {
            throw new RuntimeException();
        }

        if (!Constants.DEBUG_ENABLE) {
            final AsyncHttpClient client = getHttpClient();

            Log.d(TAG, "fetchDashboardContent, URL :"  + request.getUrl());
            if (request != null && request instanceof WeatherRequest) {
                client.get(request.getUrl(),
                        new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                if (callback != null) {
                                    callback.onSuccess(new String(bytes));
                                }
                            }

                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                if (callback != null && bytes != null && bytes.length > 0) {
                                    callback.onFailure(new String(bytes));
                                }
                            }

                        });
            } else {
                throw new NullPointerException();
            }
        } else {
            if (callback != null) {
                try {
                    callback.onSuccess(WeatherUtils.getAssetJSONFile(Constants.DEV_MODE_WEATHER_5DAYS_JSON,
                                                                     mContext));
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFailure("fail");
                }
            }
        }
    }


}
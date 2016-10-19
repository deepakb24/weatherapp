package com.mobile.android.weather.interfaces;

/**
 */
public interface NetworkRequestCallback {

    public void onSuccess(final String data);

    public void onFailure(final String reason);
}

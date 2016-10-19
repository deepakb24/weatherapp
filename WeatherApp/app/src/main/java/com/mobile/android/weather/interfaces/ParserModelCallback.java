package com.mobile.android.weather.interfaces;

/**
 */
public interface ParserModelCallback<T> {

    public void onSuccess(final T model);

    public void onFailure(final String response);
}

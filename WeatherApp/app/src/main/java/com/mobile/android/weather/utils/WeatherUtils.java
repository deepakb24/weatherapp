package com.mobile.android.weather.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */
public class WeatherUtils {

    private static String TAG = "WeatherUtils";

    public static String getAssetJSONFile(final String filename, Context context) throws
            IOException {
        final AssetManager manager = context.getAssets();
        final InputStream file = manager.open(filename);
        final byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    public static String isNULL(final String value) {
        if (value == null) {
            return "";
        }

        return value.trim();
    }

    public static void showMessage(final Context context, final int message, final int length) {
        Toast.makeText(context, message, length).show();
    }

    /**
     * Method to check network availability
     *
     * @param context
     * @return true or false
     */
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        if (info.getState() != NetworkInfo.State.CONNECTED) {
            return false;
        }
        return true;
    }

    public static String getDate(final String dateString) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final SimpleDateFormat ddMMMyyFormat = new SimpleDateFormat("dd-MMM, HH:mm");


        Date date = new Date();
        String dateValue = "";
        try {
            date = format.parse(dateString);
            dateValue = ddMMMyyFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "" + e.getMessage());
        }
        return dateValue;
    }
}

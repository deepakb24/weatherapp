package com.mobile.android.weather.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mobile.android.weather.R;
import com.mobile.android.weather.fragment.DashboardWeatherFragment;

public class HomeActivity extends BaseActivity {

    private static final int DASHBOARD_WEATHER_5DAY_FRAGMENT = 0;
    private static int loadedFragment;
    private DashboardWeatherFragment dashboardWeatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadDashboardContent();
    }

    private void loadDashboardContent() {
        if (dashboardWeatherFragment == null) {
            dashboardWeatherFragment = DashboardWeatherFragment.newInstance();
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.dashboardContent, dashboardWeatherFragment);
        loadedFragment = DASHBOARD_WEATHER_5DAY_FRAGMENT;
        fragmentTransaction.commit();
    }

    //TODO (Deepak) : later on if it more requirement comes up.
    private void loadView(int viewID) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (viewID) {
            case DASHBOARD_WEATHER_5DAY_FRAGMENT:
                if (loadedFragment != DASHBOARD_WEATHER_5DAY_FRAGMENT) {
                    if (dashboardWeatherFragment == null) {
                        dashboardWeatherFragment = DashboardWeatherFragment.newInstance();
                    }
                    transaction.replace(R.id.dashboardContent, dashboardWeatherFragment);
                    loadedFragment = DASHBOARD_WEATHER_5DAY_FRAGMENT;
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }
}

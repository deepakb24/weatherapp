package com.mobile.android.weather.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobile.android.weather.R;
import com.mobile.android.weather.activity.WeatherApp;
import com.mobile.android.weather.adapter.WeatherAdapter;
import com.mobile.android.weather.interfaces.ParserModelCallback;
import com.mobile.android.weather.interfaces.WeatherEngine;
import com.mobile.android.weather.model.Weather;
import com.mobile.android.weather.network.WeatherRequest;
import com.mobile.android.weather.utils.Constants;
import com.mobile.android.weather.utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class DashboardWeatherFragment extends Fragment {

    private static String TAG = "DashboardWeatherFragment";
    private FrameLayout rootLayout;
    private ViewPager dashboardViewPager;
    private WeatherAdapter dashboardAdapter;
    private LinearLayout dotsLayout;
    private int totalPages;
    private List<View> dots;
    private ProgressBar loaderIndicator;

    // Remove once the UX finalization...
    private static int SPEC_SPACING = 5;
    /*
    * TODO(Deepak): Keep it for furture in case requirement changes.
    *
    */
    // private DashboardContentAdapter contentAdapter;

    public static DashboardWeatherFragment newInstance() {
        final DashboardWeatherFragment fragment = new DashboardWeatherFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_dashboard_content, container, false);

        rootLayout = (FrameLayout) rootView.findViewById(R.id.rootContent);
        dashboardViewPager = (ViewPager) rootView.findViewById(R.id.dashboardViewPagerContainer);

        dotsLayout = (LinearLayout) rootView.findViewById(R.id.dots);
        loaderIndicator = (ProgressBar) rootView.findViewById(R.id.progressBar);
        loaderIndicator.setVisibility(View.VISIBLE);
        bindviews();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Checking connectivity...
        if (WeatherUtils.isInternetConnected(getActivity())) {
            loaderIndicator.setVisibility(View.GONE);
            fetchDashboardContent();
        } else {
            WeatherUtils.showMessage(getActivity(), R.string.error_message_wifi, Toast.LENGTH_SHORT);
        }
    }

    private void bindviews() {

    }

    private void fetchDashboardContent() {

        final WeatherEngine engine = WeatherApp.getInstance().getHealthCareEngine();
        final FragmentManager mgr = getFragmentManager();
        final WeatherRequest request = new WeatherRequest(Constants.BASE_URL_WEATHER_API);
        request.setCityName("london");
        request.setCountryName("US");
        request.setResponseType(Constants.MODE_JSON);
        engine.fetchDashboardContent(request, new ParserModelCallback<List<Weather>>() {
            @Override
            public void onSuccess(final List<Weather> model) {
                final Activity activity = getActivity();
                if (activity != null) {

                    if (dashboardAdapter == null) {
                        dashboardAdapter = new WeatherAdapter(activity, model);
                    }

                    totalPages = model.size();
                    Log.e(TAG, "Success Size " + model.size());
                    /*
                    * TODO(Deepak) : keep it for Future in case of requirement changes
                    *
                    *
                    * /
                    /*if (contentAdapter == null) {
                        contentAdapter = new DashboardContentAdapter(activity, model);
                    }*/
                    if (dashboardViewPager != null) {
                        dashboardViewPager.setAdapter(dashboardAdapter);
                    }
                    addDots();
                }
            }

            @Override
            public void onFailure(String response) {
                Log.e(TAG, "Error :" + response);
            }
        });

    }

    public void addDots() {
        if (dots == null) {
            dots = new ArrayList<View>();
        } else {
            dots.clear();
        }

        LinearLayout dotsLaNUM_PAGESyout = (LinearLayout) getActivity().findViewById(R.id.dots);

        for (int i = 0; i < totalPages; i++) {
            final View dot = new View(getActivity());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SPEC_SPACING, SPEC_SPACING);

            params.leftMargin = SPEC_SPACING;
            params.rightMargin = SPEC_SPACING;
            params.gravity = Gravity.CENTER_HORIZONTAL;
            LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(SPEC_SPACING * 2, SPEC_SPACING * 2);
            viewParams.gravity = Gravity.CENTER_HORIZONTAL;
            viewParams.rightMargin = SPEC_SPACING;
            dot.setLayoutParams(viewParams);

            dot.setBackgroundColor(getResources().getColor(R.color.white));
            dotsLayout.addView(dot, params);

            dots.add(dot);
        }
        if (totalPages > 0) {
            selectDot(0);
        }
        dashboardViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < totalPages; i++) {
            int colorVal = (i == idx) ? (R.color.thick_blue) : (R.color.white);
            dots.get(i).setBackgroundColor(res.getColor(colorVal));
        }
    }
}

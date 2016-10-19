package com.mobile.android.weather.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.weather.R;
import com.mobile.android.weather.model.Weather;
import com.mobile.android.weather.utils.Temperature;
import com.mobile.android.weather.utils.WeatherUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 */
public class WeatherAdapter extends PagerAdapter {

    private List<Weather> weathers;
    private Context context;
    private LayoutInflater layoutInflater;

    public WeatherAdapter(final Context context, final List<Weather> weathers) {
        this.context = context;
        this.weathers = weathers;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Object instantiateItem(View container, int position) {
        final View layout = layoutInflater.inflate(R.layout.dashboard_content_item, null);
        renderViews(layout, weathers.get(position));
        ((ViewPager) container).addView(layout, 0);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void renderViews(final View view, final Weather content) {
        final Temperature temperature = new Temperature();
        temperature.setKelvin(Double.parseDouble(content.getTemperature()));
        ((TextView) view.findViewById(R.id.temperature)).setText(String.valueOf(new DecimalFormat("##.##").format(temperature.getCelsius())));
        ((TextView) view.findViewById(R.id.humidity)).setText("Humidity: " + content.getHumidity() + "%");
        ((TextView) view.findViewById(R.id.wind)).setText("Wind Speed: " +content.getWindSpeed() + " M/HR");
        ((TextView) view.findViewById(R.id.groundLevel)).setText(content.getGroundLevel());
        ((TextView) view.findViewById(R.id.groundLevel)).setText("Ground Level: " + content.getSeaLevel());
        ((TextView) view.findViewById(R.id.cityCountryName)).setText(content.getCityName() +", " +content.getCountryName());
        ((TextView) view.findViewById(R.id.seaLeavel)).setText("Sea Level: " + content.getSeaLevel());

        final String date = WeatherUtils.getDate(content.getDateTime());
        ((TextView) view.findViewById(R.id.dateTime)).setText(date.toString());

        //final LinearLayout viewContainer = (LinearLayout) view.findViewById(R.id.viewContainer);
        // loadViews(viewContainer, content);
    }

}

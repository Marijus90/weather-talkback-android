package accessibility.forecast.marijus.weathertalkback2;

import android.os.Bundle;

import javax.inject.Inject;

import accessibility.forecast.marijus.weathertalkback2.helper.utils.ActivityUtils;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    @Inject
    WeatherPresenter presenter;
    @Inject
    Lazy<WeatherFragment> weatherFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (weatherFragment == null) {
            weatherFragment = weatherFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.container);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //TODO - Preserve data on device orientation change
        super.onSaveInstanceState(outState);
    }

}

package accessibility.forecast.marijus.weathertalkback2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.data.api.DarkSkyWeatherAPISource;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherDatabase;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherItemLocalSource;
import accessibility.forecast.marijus.weathertalkback2.helper.ActivityUtils;
import accessibility.forecast.marijus.weathertalkback2.helper.AppExecutors;

public class WeatherActivity extends AppCompatActivity {

    private WeatherPresenter presenter;
    private WeatherDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.container);
        }

        //TODO: Use DI framework to decouple the classes
        database = WeatherDatabase.getInstance(getApplicationContext());
        presenter = new WeatherPresenter(WeatherItemsRepository.getInstance(DarkSkyWeatherAPISource.getInstance(),
                WeatherItemLocalSource.getInstance(new AppExecutors(), database.weatherDAO())), weatherFragment);

        // Load previously saved state, if available
//        if (savedInstanceState != null) {
//            //Load things
//        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //TODO - Preserve data on device orientation change
        super.onSaveInstanceState(outState);
    }

}

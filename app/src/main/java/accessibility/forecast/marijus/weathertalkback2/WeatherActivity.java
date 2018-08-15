package accessibility.forecast.marijus.weathertalkback2;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.data.api.OpenWeatherAPISource;
import accessibility.forecast.marijus.weathertalkback2.helper.ActivityUtils;

public class WeatherActivity extends AppCompatActivity {

    private WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
//TODO: Add firebase, analytics, index the app etc.

        // Init fragment (view)
        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.container);
        }

        //TODO: Use DI framework to decouple the classes
        presenter = new WeatherPresenter(WeatherItemsRepository.getInstance(OpenWeatherAPISource.getInstance(), null),
                weatherFragment);

        // Load previously saved state, if available
//                if (savedInstanceState != null) {
//            TasksFilterType currentFiltering =
//                    (TasksFilterType) savedInstanceState.getSerializable(BUNDLE_KEY);
//            mTasksPresenter.setFiltering(currentFiltering);
//        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        outState.putSerializable(BUNDLE_KEY, mTasksPresenter.getFiltering());
        super.onSaveInstanceState(outState);
    }

    //TODO - Preserve data on device orientation change
}

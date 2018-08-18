package accessibility.forecast.marijus.weathertalkback2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.data.api.DarkSkyWeatherAPISource;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherDatabase;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherItemLocalSource;
import accessibility.forecast.marijus.weathertalkback2.helper.ActivityUtils;
import accessibility.forecast.marijus.weathertalkback2.helper.AppExecutors;

public class WeatherActivity extends AppCompatActivity {

    private WeatherPresenter presenter;
    private WeatherDatabase database;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //TODO: Add Firebase, analytics, index the app etc.

        // Init fragment (view)
        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.container);
        }

        //TODO: Use DI framework to decouple the classes!
        database = WeatherDatabase.getInstance(getApplicationContext());
        presenter = new WeatherPresenter(WeatherItemsRepository.getInstance(DarkSkyWeatherAPISource.getInstance(),
                WeatherItemLocalSource.getInstance(new AppExecutors(), database.weatherDAO())), weatherFragment);

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

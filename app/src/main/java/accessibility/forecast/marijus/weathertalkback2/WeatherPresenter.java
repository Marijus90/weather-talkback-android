package accessibility.forecast.marijus.weathertalkback2;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.helper.di.ActivityScoped;

/**
 * This class listens to user actions from the UI ({@link WeatherFragment}), retrieves the data and updates the
 * UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the WeatherPresenter (if it fails, it emits a compiler error).  It uses
 * {@link WeatherModule} to do so.
 **/
@ActivityScoped
public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherItemsRepository weatherRepository;
    @Nullable
    private WeatherContract.View view;

    @Inject
    WeatherPresenter(WeatherItemsRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void refreshData(boolean isForced) {
        if (view != null) {
            view.setLoadingIndicator(true);
        }

        weatherRepository.getWeatherData(new WeatherDataSource.GetWeatherDataCallback() {

            @Override
            public void onDataLoaded(WeatherItem data) {
                if (!data.isEmpty()) {
                    processWeatherData(data);
                } else {
                    if (view != null) {
                        view.showNoDataLayout(true);
                    }
                }
                if (view != null) {
                    view.setLoadingIndicator(false);
                }
            }

            @Override
            public void onDataNotAvailable(String message) {
                if (message != null) {
                    if (view != null) {
                        view.showErrorMessage(message);
                    }
                }

                if (view != null) {
                    view.showNoDataLayout(true);
                    view.setLoadingIndicator(false);
                }
            }
        }, isForced);
    }

    private void processWeatherData(WeatherItem data) {
        ArrayList<WeatherItem> weatherData = new ArrayList<>();
        weatherData.add(data);
        if (view != null) {
            view.displayWeatherData(weatherData);
        }
    }

    @Override
    public void updateCachedData(WeatherItem forecast) {
        weatherRepository.cacheData(forecast);
    }


    @Override
    public void takeView(WeatherContract.View view) {
        this.view = view;
        refreshData(false);
    }

    @Override
    public void dropView() {
        view = null;
    }
}

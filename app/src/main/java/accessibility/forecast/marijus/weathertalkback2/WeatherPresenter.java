package accessibility.forecast.marijus.weathertalkback2;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherItemsRepository weatherRepository;
    private final WeatherContract.View view;

    public WeatherPresenter(@NonNull WeatherItemsRepository weatherRepository, @NonNull WeatherContract.View view) {
        this.weatherRepository = checkNotNull(weatherRepository, "repository cannot be null");
        this.view = checkNotNull(view, "view must be implemented");

        this.view.setPresenter(this);
    }

    @Override
    public void refreshData(boolean isForced) {
        view.setLoadingIndicator(true);

        weatherRepository.getWeatherData(new WeatherDataSource.GetWeatherDataCallback() {

            @Override
            public void onDataLoaded(WeatherItem data) {
                if (!data.isEmpty()) {
                    processWeatherData(data);
                } else {
                    view.showNoDataLayout(true);
                }
                view.setLoadingIndicator(false);
            }

            @Override
            public void onDataNotAvailable(String message) {
                if (message != null) {
                    view.showErrorMessage(message);
                }

                view.showNoDataLayout(true);
                view.setLoadingIndicator(false);
            }
        }, isForced);
    }

    private void processWeatherData(WeatherItem data) {
        ArrayList<WeatherItem> weatherData = new ArrayList<>();
        weatherData.add(data);
        view.displayWeatherData(weatherData);
    }

    @Override
    public void updateCachedData(WeatherItem forecast) {
        weatherRepository.cacheData(forecast);
    }

    @Override
    public void start() {
        refreshData(false);
    }

}

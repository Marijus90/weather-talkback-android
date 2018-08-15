package accessibility.forecast.marijus.weathertalkback2;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.models.Forecast;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherItemsRepository weatherRepository;
    private final WeatherContract.View view;

    Forecast forecastData;

//    private boolean firstLoad = true;

    public WeatherPresenter(@NonNull WeatherItemsRepository weatherRepository, @NonNull WeatherContract.View view) {
        this.weatherRepository = checkNotNull(weatherRepository, "repository cannot be null");
        this.view = checkNotNull(view, "view must be implemented");

        this.view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        //TODO: Get the forecast from API only (for now)
//        if(firstLoad) {
        getDataFromAPI();
//        }

        updateCachedData();
    }

    private void getDataFromAPI() {
        view.setLoadingIndicator(true);

        weatherRepository.getWeatherData(new WeatherDataSource.GetAPIDataCallback() {
            @Override
            public void onDataLoaded(Forecast forecast) {
                if (forecast != null) {
                    processWeatherData(forecast);
                } else {
                    //TODO: Check if this is done right
                    view.showNoDataError("No data");
                }
                view.setLoadingIndicator(false);
            }

            @Override
            public void onDataNotAvailable() {
                view.showErrorMessage("Server Error");
                view.setLoadingIndicator(false);
            }
        });
    }

    private void processWeatherData(Forecast forecast) {
        //TODO: Process data - get 7 day items (Data points)

        //TODO: Update the UI (Fragment list)
//        view.displayWeatherData(forecast);
    }

    @Override
    public void updateCachedData() {
        //TODO: Implement this
    }

    @Override
    public void start() {
        refreshData();
    }
}

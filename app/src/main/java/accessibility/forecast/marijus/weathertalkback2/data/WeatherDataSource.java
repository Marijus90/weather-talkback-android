package accessibility.forecast.marijus.weathertalkback2.data;

import java.util.List;

import io.reactivex.Observable;

/**
 * Main entry point for accessing weather data.
 */
public interface WeatherDataSource {

    Observable<List<WeatherItem>> getRxWeatherData(boolean isForced);

    void cacheData(WeatherItem data);

    void refreshData();

    void clearCachedData();

}

package accessibility.forecast.marijus.weathertalkback2.data;

import android.support.annotation.NonNull;

/**
 * Main entry point for accessing weather data.
 */
public interface WeatherDataSource {

    interface GetWeatherDataCallback {

        void onDataLoaded(WeatherItem data);

        void onDataNotAvailable();

    }

    void getWeatherData(@NonNull GetWeatherDataCallback callback);

    void cacheData(WeatherItem data);

    void clearCachedData();

    void refreshCachedData();

}

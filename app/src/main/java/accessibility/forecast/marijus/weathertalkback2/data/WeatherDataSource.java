package accessibility.forecast.marijus.weathertalkback2.data;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.models.Forecast;

/**
 * Main entry point for accessing weather data.
 */
public interface WeatherDataSource {

    interface GetAPIDataCallback {

        void onDataLoaded(Forecast body);

        void onDataNotAvailable();

    }

    //TODO: Remove comments
    //    void getWeatherData(@NonNull LoadTasksCallback callback);
    void getWeatherData(@NonNull GetAPIDataCallback callback);

    //    void cacheData(@NonNull Task task);
    void cacheData();

    void clearCachedData();

    void clearAllData();

    void refreshCachedData();

}

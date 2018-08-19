package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.helper.AppExecutors;

/**
 * Concrete implementation of the data source as a database.
 */
public class WeatherItemLocalSource implements WeatherDataSource {

    private static volatile WeatherItemLocalSource INSTANCE;

    private WeatherDAO weatherDAO;

    private AppExecutors appExecutors;

    private WeatherItemLocalSource(@NonNull AppExecutors appExecutors,
                                   @NonNull WeatherDAO weatherDAO) {
        this.appExecutors = appExecutors;
        this.weatherDAO = weatherDAO;
    }

    public static WeatherItemLocalSource getInstance(@NonNull AppExecutors appExecutors,
                                                     @NonNull WeatherDAO tasksDao) {
        if (INSTANCE == null) {
            synchronized (WeatherItemLocalSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WeatherItemLocalSource(appExecutors, tasksDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getWeatherData(@NonNull final GetWeatherDataCallback callback, boolean isForced) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final WeatherItem item = weatherDAO.getWeather();
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (item == null || item.isEmpty()) {
                            callback.onDataNotAvailable(null);
                        } else {
                            callback.onDataLoaded(item);
                        }
                    }
                });
            }
        };

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void cacheData(WeatherItem data) {
        weatherDAO.deleteTasks();
        weatherDAO.insertTask(data);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void clearCachedData() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                weatherDAO.deleteTasks();
            }
        };
        appExecutors.diskIO().execute(deleteRunnable);
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }

}

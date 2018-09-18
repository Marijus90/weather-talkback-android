package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.api.models.WeatherResponseItem;
import accessibility.forecast.marijus.weathertalkback2.helper.AppExecutors;
import io.reactivex.Observable;

/**
 * Concrete implementation of the data source as a database.
 */
@Singleton
public class WeatherItemLocalSource implements WeatherDataSource {

    private final WeatherDAO weatherDAO;

    private final AppExecutors appExecutors;

    @Inject
    public WeatherItemLocalSource(@NonNull AppExecutors appExecutors,
                                   @NonNull WeatherDAO weatherDAO) {
        this.appExecutors = appExecutors;
        this.weatherDAO = weatherDAO;
    }

//    @Override
//    public void getWeatherData(@NonNull final GetWeatherDataCallback callback, boolean isForced) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                final WeatherItem item = weatherDAO.getWeather();
//                appExecutors.mainThread().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (item == null || item.isEmpty()) {
//                            callback.onDataNotAvailable(null);
//                        } else {
//                            callback.onDataLoaded(item);
//                        }
//                    }
//                });
//            }
//        };
//
//        appExecutors.diskIO().execute(runnable);
//    }

    @Override
    public Observable<List<WeatherResponseItem>> getRxWeatherData(boolean isForced) {
        return null;
    }

    @Override
    public void cacheData(WeatherItem data) {
        weatherDAO.deleteWeatherItems();
        weatherDAO.insertWeatherItem(data);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void clearCachedData() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                weatherDAO.deleteWeatherItems();
            }
        };
        appExecutors.diskIO().execute(deleteRunnable);
    }

}

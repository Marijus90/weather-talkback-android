package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
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

    @Override
    public Observable<List<WeatherItem>> getRxWeatherData(boolean isForced) {
        //TODO: Use Flowable in API and local data classes so that they both return same RX object
        return Observable.just(weatherDAO.getWeather());
    }

    @Override
    public void cacheData(WeatherItem weatherItem) {
        weatherDAO.insertWeatherItem(weatherItem);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void clearCachedData() {
        //TODO: Remove runnable
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                weatherDAO.deleteWeatherItems();
            }
        };
        appExecutors.diskIO().execute(deleteRunnable);
    }

}

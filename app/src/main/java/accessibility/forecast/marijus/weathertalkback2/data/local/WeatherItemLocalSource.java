package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Concrete implementation of the data source as a database.
 */
@Singleton
public class WeatherItemLocalSource implements WeatherDataSource {

    private final WeatherDAO weatherDAO;

    @Inject
    public WeatherItemLocalSource(@NonNull WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    @Override
    public Observable<List<WeatherItem>> getRxWeatherData(boolean isForced) {
        return weatherDAO.getWeather().toObservable();
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
        Completable.fromAction(this::clearDataInDatabase).subscribeOn(Schedulers.single()).subscribe();
    }

    private void clearDataInDatabase() {
        weatherDAO.deleteWeatherItems();
    }

}

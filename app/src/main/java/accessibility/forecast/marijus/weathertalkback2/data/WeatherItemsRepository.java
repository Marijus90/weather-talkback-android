package accessibility.forecast.marijus.weathertalkback2.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.helper.utils.DeviceStateUtils;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Concrete implementation to load weather data from the locally persisted data and data
 * obtained from the server, by using the remote data source on the first load and if the local
 * database doesn't exist is empty or expired.
 * <p>
 * To improve the implementation it is recommended to create the NetworkBoundResource class
 * https://developer.android.com/jetpack/docs/guide#addendum
 */
@Singleton
public class WeatherItemsRepository implements WeatherDataSource {

    private final WeatherDataSource remoteDataSource;
    private final WeatherDataSource localDataSource;
    private ArrayList<WeatherItem> inMemoryCache;

    @Inject
    WeatherItemsRepository(@Remote WeatherDataSource remoteDataSource,
                           @Local WeatherDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        inMemoryCache = new ArrayList<>();
    }

    @Override
    public Observable<List<WeatherItem>> getRxWeatherData(boolean isForced) {
        if (isForced) {
            return getAndCacheRemoteWeatherData();
        }

        if (inMemoryCache.isEmpty()) {
            return Observable.concat(getAndCacheLocalWeatherData(), getAndCacheRemoteWeatherData())
                    .filter(list -> !list.isEmpty())
                    .firstElement()
                    .toObservable();
        } else {
            return Observable.just(inMemoryCache);
        }
    }

    private Observable<List<WeatherItem>> getAndCacheRemoteWeatherData() {
        return remoteDataSource.getRxWeatherData(true)
                .subscribeOn(Schedulers.io())
                .flatMap(weatherItems -> Flowable.fromIterable(weatherItems)
                        .doOnNext(this::cacheData).toList().toObservable());
    }

    private Observable<List<WeatherItem>> getAndCacheLocalWeatherData() {
        return localDataSource.getRxWeatherData(false)
                .subscribeOn(Schedulers.io())
                .flatMap(weatherItems -> Flowable.fromIterable(weatherItems)
                        .doOnNext(item -> inMemoryCache.add(item)).toList().toObservable());
    }

    public void cacheData(WeatherItem item) {
        localDataSource.cacheData(item);
        inMemoryCache.add(item);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void clearCachedData() {
        inMemoryCache.clear();
        localDataSource.clearCachedData();
    }

    //TODO: Implement this not to load weather data from DB if it's older than 1 day
    private boolean isCacheValid(String dateCreated) {
        return !DeviceStateUtils.isOlderThanOneDay(dateCreated);
    }

}

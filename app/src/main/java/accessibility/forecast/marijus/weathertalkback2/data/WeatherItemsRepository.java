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
 * Concrete implementation to load weather data from the data sources.
 * <p>
 * //TODO: Update the comment
 * This implements a dumb synchronisation between locally persisted data and data
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
    private ArrayList<WeatherItem> localCache;
    private boolean isLocalCacheDirty;

    @Inject
    WeatherItemsRepository(@Remote WeatherDataSource remoteDataSource,
                           @Local WeatherDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        localCache = new ArrayList<>();
    }

    @Override
    public Observable<List<WeatherItem>> getRxWeatherData(boolean isForced) {
        //TODO: Remove this toggle after UI is refactored
        //TODO: if local cache is not null and is not dirty - get cached data,
        //TODO: then possibly still load api and update the values and cache if needed?
//        if (shouldFetchRemote(isForced)) {
        if (true) {
            return getAndCacheRxWeatherData();
        }
        return localDataSource.getRxWeatherData(false);
    }

    private Observable<List<WeatherItem>> getAndCacheRxWeatherData() {
        return remoteDataSource.getRxWeatherData(true)
                .subscribeOn(Schedulers.io())
                .flatMap(weatherItems -> Flowable.fromIterable(weatherItems)
                        .doOnNext(this::cacheData).toList().toObservable())
                .doOnComplete(() -> isLocalCacheDirty = false);
    }

    private boolean shouldFetchRemote(boolean isForced) {
        //TODO: Add checking if cached data is valid and if there's network connection
        return isForced;
    }

    public void cacheData(WeatherItem item) {
        localDataSource.cacheData(item);
        localCache.add(item);
    }

    @Override
    public void refreshData() {
        isLocalCacheDirty = true;
    }

    @Override
    public void clearCachedData() {
        //TODO: Implement in-memory cache
        localCache.clear();
        localDataSource.clearCachedData();
    }

    //TODO: Implement the methods below?
    public Observable<List<WeatherItem>> getLocalRxWeatherData() {
//        isCacheValid(data.getmDateCreated())
        return localDataSource.getRxWeatherData(true);
    }

    private boolean isCacheValid(String dateCreated) {
        return !DeviceStateUtils.isOlderThanOneDay(dateCreated);
    }

}

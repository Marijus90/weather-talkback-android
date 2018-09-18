package accessibility.forecast.marijus.weathertalkback2.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.api.models.WeatherResponseItem;
import accessibility.forecast.marijus.weathertalkback2.helper.utils.DeviceStateUtils;
import io.reactivex.Observable;

/**
 * Concrete implementation to load weather data from the data sources.
 * <p>
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

    @Inject
    WeatherItemsRepository(@Remote WeatherDataSource remoteDataSource,
                           @Local WeatherDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Observable<List<WeatherResponseItem>> getRxWeatherData(boolean isForced) {
        //TODO: Remove this toggle after UI is refactored
//        if (shouldFetchRemote(isForced)) {
        if (true) {
            return remoteDataSource.getRxWeatherData(true);
        }

        return localDataSource.getRxWeatherData(false);
    }

    private boolean shouldFetchRemote(boolean isForced) {
        //TODO: Add checking if cached data if valid and if there's network connection
        return isForced;
    }

    @Override
    public void cacheData(WeatherItem data) {
        //TODO: Remove old data then bulk insert new one
        localDataSource.cacheData(data);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void clearCachedData() {
        //TODO: Implement in-memory cache
        //        remoteDataSource.clear();
        //        localDataSource.clear();
    }

    public Observable<List<WeatherResponseItem>> getLocalRxWeatherData() {
        //TODO: Implement this
//        isCacheValid(data.getmDateCreated())
        return localDataSource.getRxWeatherData(true);
    }

    private boolean isCacheValid(String dateCreated) {
        return !DeviceStateUtils.isOlderThanOneDay(dateCreated);
    }

}

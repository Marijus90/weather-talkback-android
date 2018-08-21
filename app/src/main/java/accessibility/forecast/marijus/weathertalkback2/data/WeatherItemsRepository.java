package accessibility.forecast.marijus.weathertalkback2.data;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.helper.utils.DeviceStateUtils;

import static com.google.common.base.Preconditions.checkNotNull;

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

    private static WeatherItemsRepository INSTANCE = null;

    private final WeatherDataSource remoteDataSource;
    private final WeatherDataSource localDataSource;

    @Inject
    WeatherItemsRepository(@Remote WeatherDataSource remoteDataSource,
                    @Local WeatherDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void getWeatherData(@NonNull GetWeatherDataCallback callback, boolean isForced) {
        checkNotNull(callback);
        getDataFromRemoteDataSource(callback, isForced);
    }

    @Override
    public void cacheData(WeatherItem data) {
        localDataSource.cacheData(data);
    }

    @Override
    public void refreshData() {
    }

    private void getDataFromRemoteDataSource(@NonNull final GetWeatherDataCallback callback, final boolean isForced) {
        remoteDataSource.getWeatherData(new GetWeatherDataCallback() {

            @Override
            public void onDataLoaded(WeatherItem data) {
                if (!data.isEmpty()) {
                    cacheData(data);
                    callback.onDataLoaded(data);
                } else {
                    callback.onDataNotAvailable(null);
                }
            }

            @Override
            public void onDataNotAvailable(String message) {
                // If client is offline - we are getting data from cache (local repo)
                if (!isForced) {
                    getDataFromLocalDataSource(callback);
                } else {
                    callback.onDataNotAvailable("Could not connect to internet.");
                }

            }
        }, isForced);
    }


    private void getDataFromLocalDataSource(@NonNull final GetWeatherDataCallback callback) {
        localDataSource.getWeatherData(new GetWeatherDataCallback() {

            @Override
            public void onDataLoaded(WeatherItem data) {
                if (isCacheValid(data.getmDateCreated())) {
                    callback.onDataLoaded(data);
                } else {
                    callback.onDataNotAvailable(null);
                }
            }

            private boolean isCacheValid(String dateCreated) {
                return !DeviceStateUtils.isOlderThanOneDay(dateCreated);
            }

            @Override
            public void onDataNotAvailable(String message) {
                callback.onDataNotAvailable(null);
            }
        }, false);
    }

    @Override
    public void clearCachedData() {
        //TODO: Implement in-memory cache
        //        remoteDataSource.clear();
        //        localDataSource.clear();
    }

}

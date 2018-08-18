package accessibility.forecast.marijus.weathertalkback2.data;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.models.Forecast;

import static com.google.common.base.Preconditions.checkNotNull;

public class WeatherItemsRepository implements WeatherDataSource {

    private static WeatherItemsRepository INSTANCE = null;

    private final WeatherDataSource remoteDataSource;
    private final WeatherDataSource localDataSource;

    Forecast cachedForecast;

    //TODO: Set time of expiry to refresh cache after 24hrs
    boolean isCacheInvalid = false;

    // Prevent direct instantiation
    private WeatherItemsRepository(@NonNull WeatherDataSource remoteDataSource,
                                   @NonNull WeatherDataSource localDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
        this.localDataSource = checkNotNull(localDataSource);
    }

    public static WeatherItemsRepository getInstance(WeatherDataSource remoteDataSource, WeatherDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new WeatherItemsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
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
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onDataNotAvailable() {
                // If client is offline - were we call cache (local repo)
                if (!isForced) {
                    getDataFromLocalDataSource(callback);
                } else {
                    callback.onDataNotAvailable();
                }

            }
        }, isForced);
    }


    private void getDataFromLocalDataSource(@NonNull final GetWeatherDataCallback callback) {
        localDataSource.getWeatherData(new GetWeatherDataCallback() {

            @Override
            public void onDataLoaded(WeatherItem data) {
                //TODO: Check if data came from cache = true
                callback.onDataLoaded(data);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        }, false);
    }

    @Override
    public void clearCachedData() {
        cachedForecast = null; //TODO: Did I forget to implement this?
        //        remoteDataSource.clear();
        //        localDataSource.clear();
    }

    @Override
    public void refreshCachedData() {
    }

}

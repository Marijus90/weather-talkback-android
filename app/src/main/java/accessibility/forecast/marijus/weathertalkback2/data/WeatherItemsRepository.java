package accessibility.forecast.marijus.weathertalkback2.data;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.models.Forecast;

import accessibility.forecast.marijus.weathertalkback2.helper.DeviceStateUtils;

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
    public void getWeatherData(@NonNull GetWeatherDataCallback callback) {
        checkNotNull(callback);

//        if (DeviceStateUtils.isDeviceOnline()) {
            getDataFromRemoteDataSource(callback);
//        } else {
//            getDataFromRemoteDataSource(callback);
//        }

        //TODO: Reduce API timeout

        //TODO: Implement on forced refresh
    }

    @Override
    public void cacheData(WeatherItem data) {
        localDataSource.cacheData(data);
    }

    private void getDataFromRemoteDataSource(@NonNull final GetWeatherDataCallback callback) {
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
                //TODO: Here means you're offline - check cache
                //TODO: To reach this takes 10s
                getDataFromLocalDataSource(callback);
            }
        });
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
        });
    }

    @Override
    public void clearCachedData() {
        cachedForecast = null; //TODO: Did I forget to implement this?
        //        remoteDataSource.clear();
        //        localDataSource.clear();
    }

    @Override
    public void refreshCachedData() {
        //TODO: Update DB if call is coming from API
    }

}

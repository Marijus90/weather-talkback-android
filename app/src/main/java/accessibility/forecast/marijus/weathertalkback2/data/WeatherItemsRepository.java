package accessibility.forecast.marijus.weathertalkback2.data;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.models.Forecast;

import static com.google.common.base.Preconditions.checkNotNull;

public class WeatherItemsRepository implements WeatherDataSource {
    private static WeatherItemsRepository INSTANCE = null;

    private final WeatherDataSource remoteDataSource;
    //TODO: Add local DB (data source)
//    private final WeatherDataSource localDataSource;

    Forecast cachedForecast;

    //TODO: Set time of expiry to refresh cache after 24hrs
    boolean isCacheInvalid = false;

    // Prevent direct instantiation
    private WeatherItemsRepository(@NonNull WeatherDataSource remoteDataSource,
                                   @NonNull WeatherDataSource localDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
//        this.localDataSource = checkNotNull(localDataSource);
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
    public void getWeatherData(@NonNull GetAPIDataCallback callback) {
        //TODO: Get cached values

        //TODO: Get values from local DB

        // Getting data from API
        getDataFromRemoteDataSource(callback);
    }

    private void getDataFromRemoteDataSource(@NonNull final GetAPIDataCallback callback) {
        remoteDataSource.getWeatherData(new GetAPIDataCallback() {

            @Override
            public void onDataLoaded(Forecast forecastData) {
                //TODO: Refresh data in cache
//                refreshCache(forecastData);
                //TODO: Refresh data in DB
//                refreshLocalDataSource(forecastData);

                callback.onDataLoaded(forecastData);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void cacheData() {

    }

    @Override
    public void clearCachedData() {

    }

    @Override
    public void clearAllData() {
//        remoteDataSource.clear();
//        localDataSource.clear();

        cachedForecast = null;
    }

    @Override
    public void refreshCachedData() {

    }
}

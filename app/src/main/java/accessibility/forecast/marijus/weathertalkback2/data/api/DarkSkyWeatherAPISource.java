package accessibility.forecast.marijus.weathertalkback2.data.api;

import java.util.List;

import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implementation of data source that gets the data from a Dark Sky API
 */
@Singleton
public class DarkSkyWeatherAPISource implements WeatherDataSource {

    private String latitude;
    private String longitude;

    public DarkSkyWeatherAPISource() {
        getDeviceLocation();
    }

    private WeatherAPIService configureRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WeatherAPIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(WeatherAPIService.class);
    }

    @Override
    public Observable<List<WeatherItem>> getRxWeatherData(boolean isForced) {
        //TODO: Investigate why API call takes longer
        return configureRetrofit().getRxCurrentWeather3(WeatherAPIService.API_KEY,
                latitude, longitude, WeatherAPIService.UNITS, WeatherAPIService.EXCLUDED_BLOCKS)
                .map(forecast -> forecast.getDaily().getItems());
    }

    private void getDeviceLocation() {
        //TODO: Move this to helper class
        // Hardcoded location of London, UK
        latitude = String.valueOf(51.5);
        longitude = String.valueOf(-0.08);
    }

    @Override
    public void cacheData(WeatherItem data) {

    }

    @Override
    public void refreshData() {

    }

    @Override
    public void clearCachedData() {

    }

}

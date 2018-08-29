package accessibility.forecast.marijus.weathertalkback2.data.api;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.api.models.Forecast;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implementation of data source that gets the data from a Dark Sky API
 */
@Singleton
public class DarkSkyWeatherAPISource implements WeatherDataSource {

    private final String API_KEY = "637f66044420531db6fd59fd99c20771";
    private final String BASE_URL = "https://api.darksky.net/forecast/";
    private final String UNITS = "uk2";

    private String latitude;
    private String longitude;

    public DarkSkyWeatherAPISource() {

    }

    @Override
    public void getWeatherData(final @NonNull GetWeatherDataCallback callback, boolean isForced) {

        getDeviceLocation();

        WeatherAPIService service = configureRetrofit();

        Call<Forecast> call = service.getCurrentWeather(API_KEY, latitude, longitude, UNITS);

        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful()) {
                    callback.onDataLoaded(new WeatherItem(response.body().getCurrently()));
                } else {
                    callback.onDataNotAvailable(null);
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                callback.onDataNotAvailable(null);
            }
        });
    }

    private WeatherAPIService configureRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(4, TimeUnit.SECONDS)
                .connectTimeout(4, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WeatherAPIService.class);
    }

    private void getDeviceLocation() {
        //TODO: Move this to helper class
        // Hardcoded location of London
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

package accessibility.forecast.marijus.weathertalkback2.data.api;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;
import android.zetterstrom.com.forecast.models.Forecast;
import android.zetterstrom.com.forecast.models.Language;
import android.zetterstrom.com.forecast.models.Unit;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenWeatherAPISource implements WeatherDataSource {

    private static OpenWeatherAPISource INSTANCE;

    private ForecastConfiguration configuration;
    private Double latitude;
    private Double longitude;

    public static OpenWeatherAPISource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OpenWeatherAPISource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private OpenWeatherAPISource() {
    }

    @Override
    public void getWeatherData(final @NonNull GetAPIDataCallback callback) {
        if (configuration == null) {
            configureForecastClient();
        }

        ForecastClient.getInstance()
                .getForecast(latitude, longitude, new Callback<Forecast>() {
                    @Override
                    public void onResponse(Call<Forecast> forecastCall, Response<Forecast> response) {
                        if (response.isSuccessful()) {
                            callback.onDataLoaded(response.body());
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<Forecast> forecastCall, Throwable throwable) {
                        // TODO: Add different error message?
                        callback.onDataNotAvailable();
                    }
                });
    }

    private void configureForecastClient() {
        getDeviceLocation();

        configuration =
                new ForecastConfiguration.Builder("637f66044420531db6fd59fd99c20771")
                        .setDefaultLanguage(Language.ENGLISH)
                        .setDefaultUnit(Unit.UK)
//                        .setCacheDirectory(App.getContext().getCacheDir())
                        .build();
        ForecastClient.create(configuration);
    }

    private void getDeviceLocation() {
        //TODO - Move this to helper class
        // Hardcoded location of London
        latitude = 40.2712;
        longitude = -74.7829;
    }

    @Override
    public void cacheData() {

    }

    @Override
    public void clearCachedData() {

    }

    @Override
    public void clearAllData() {

    }

    @Override
    public void refreshCachedData() {

    }
}

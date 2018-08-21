package accessibility.forecast.marijus.weathertalkback2.data.api;

import android.support.annotation.NonNull;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;
import android.zetterstrom.com.forecast.models.Language;
import android.zetterstrom.com.forecast.models.Unit;

import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.helper.utils.DeviceStateUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of data source that gets the data from a Dark Sky API
 */
@Singleton
public class DarkSkyWeatherAPISource implements WeatherDataSource {

    private ForecastConfiguration configuration;
    private Double latitude;
    private Double longitude;

    public DarkSkyWeatherAPISource() {

    }

    @Override
    public void getWeatherData(final @NonNull GetWeatherDataCallback callback, boolean isForced) {
        if (configuration == null) {
            configureForecastClient();
        }

        ForecastClient.getInstance()
                .getForecast(latitude, longitude, new Callback<Forecast>() {
                    @Override
                    public void onResponse(Call<Forecast> forecastCall, Response<Forecast> response) {
                        if (response.isSuccessful()) {
                            //TODO: Use adapter pattern ?
                            DataPoint responseItem = response.body().getCurrently();
                            callback.onDataLoaded(new WeatherItem(responseItem.getSummary(),
                                    responseItem.getIcon(), responseItem.getTemperature(),
                                    responseItem.getWindSpeed(), responseItem.getWindBearing(),
                                    DeviceStateUtils.getCurrentTimeAsString(),
                                    DeviceStateUtils.getCurrentDateAsString()));
                        } else {
                            callback.onDataNotAvailable(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<Forecast> forecastCall, Throwable throwable) {
                        callback.onDataNotAvailable(null);
                    }
                });
    }

    private void configureForecastClient() {
        getDeviceLocation();

        configuration =
                new ForecastConfiguration.Builder("637f66044420531db6fd59fd99c20771")
                        .setDefaultLanguage(Language.ENGLISH)
                        .setDefaultUnit(Unit.UK)
                        .setConnectionTimeout(4)
                        .build();
        ForecastClient.create(configuration);
    }

    private void getDeviceLocation() {
        //TODO: Move this to helper class
        // Hardcoded location of London
        latitude = 51.5;
        longitude = -0.08;
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

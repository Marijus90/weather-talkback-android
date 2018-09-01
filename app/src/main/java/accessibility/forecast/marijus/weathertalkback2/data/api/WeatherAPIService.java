package accessibility.forecast.marijus.weathertalkback2.data.api;

import accessibility.forecast.marijus.weathertalkback2.data.api.models.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPIService {

    @GET("/forecast/{key}/{latitude},{longitude}")
    Call<Forecast> getCurrentWeather(@Path("key") String key, @Path("latitude") String latitude,
                                     @Path("longitude") String longitude, @Query("units") String units);

}

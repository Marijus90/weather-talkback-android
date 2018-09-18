package accessibility.forecast.marijus.weathertalkback2.data.api;

import accessibility.forecast.marijus.weathertalkback2.data.api.models.Forecast;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPIService {

    String API_KEY = "637f66044420531db6fd59fd99c20771";
    String BASE_URL = "https://api.darksky.net/forecast/";
    String UNITS = "uk2";
    String EXCLUDED_BLOCKS = "minutely,hourly,currently,flags,alerts";
    //TODO: Include language in the call


    @GET("/forecast/{key}/{latitude},{longitude}")
    Observable<Forecast> getRxCurrentWeather3(@Path("key") String key, @Path("latitude") String latitude,
                                              @Path("longitude") String longitude, @Query("units") String units,
                                              @Query("exclude") String excludedBlocks);

}

package accessibility.forecast.marijus.weathertalkback2.data.api.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Forecast {

    @SerializedName("daily")
    private Daily daily;

    public Daily getDaily() {
        return daily;
    }

}

package accessibility.forecast.marijus.weathertalkback2.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Daily {

    @SerializedName("data")
    private List<WeatherResponseItem> data;

    public List<WeatherResponseItem> getItems() {
        return data;
    }

}

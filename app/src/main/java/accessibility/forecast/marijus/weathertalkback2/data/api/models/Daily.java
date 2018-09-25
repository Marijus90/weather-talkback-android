package accessibility.forecast.marijus.weathertalkback2.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

@SuppressWarnings("unused")
public class Daily {

    @SerializedName("data")
    private List<WeatherItem> data;

    public List<WeatherItem> getItems() {
        return data;
    }

}

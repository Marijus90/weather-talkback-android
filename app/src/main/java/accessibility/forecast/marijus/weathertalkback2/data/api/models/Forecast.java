package accessibility.forecast.marijus.weathertalkback2.data.api.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Forecast {

    @SerializedName("currently")
    private Currently currently;

    public Currently getCurrently() {
        return currently;
    }

}

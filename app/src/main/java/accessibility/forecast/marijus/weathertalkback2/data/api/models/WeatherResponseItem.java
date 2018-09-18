package accessibility.forecast.marijus.weathertalkback2.data.api.models;

import com.google.gson.annotations.SerializedName;

public class WeatherResponseItem {

    @SerializedName("time")
    private Long time;

    @SerializedName("summary")
    private String summary;

    @SerializedName("icon")
    private String icon;

    @SerializedName("temperatureHigh")
    private Double temperatureHigh;

    @SerializedName("temperatureLow")
    private Double temperatureLow;

    @SerializedName("windSpeed")
    private Double windSpeed;

    @SerializedName("windBearing")
    private Double windBearing;

    public Long getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getWindBearing() {
        return windBearing;
    }

}

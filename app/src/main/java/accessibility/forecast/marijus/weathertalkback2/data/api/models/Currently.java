
package accessibility.forecast.marijus.weathertalkback2.data.api.models;

import com.google.gson.annotations.SerializedName;

public class Currently {

    @SerializedName("time")
    private Integer time;

    @SerializedName("summary")
    private String summary;

    @SerializedName("icon")
    private String icon;

    @SerializedName("precipIntensity")
    private Integer precipIntensity;

    @SerializedName("precipProbability")
    private Integer precipProbability;

    @SerializedName("temperature")
    private Double temperature;

    @SerializedName("apparentTemperature")
    private Double apparentTemperature;

    @SerializedName("dewPoint")
    private Double dewPoint;

    @SerializedName("humidity")
    private Double humidity;

    @SerializedName("pressure")
    private Double pressure;

    @SerializedName("windSpeed")
    private Double windSpeed;

    @SerializedName("windGust")
    private Double windGust;

    @SerializedName("windBearing")
    private Double windBearing;

    @SerializedName("cloudCover")
    private Double cloudCover;

    @SerializedName("uvIndex")
    private Integer uvIndex;

    @SerializedName("visibility")
    private Double visibility;

    @SerializedName("ozone")
    private Double ozone;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Integer precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Integer getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Integer precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public Double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

}

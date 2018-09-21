package accessibility.forecast.marijus.weathertalkback2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "items")
public class WeatherItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "time")
    @SerializedName("time")
    private Long time;

    @Nullable
    @ColumnInfo(name = "summary")
    @SerializedName("summary")
    private String summary;

    @Nullable
    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    private String icon;

    @Nullable
    @ColumnInfo(name = "temperatureHigh")
    @SerializedName("temperatureHigh")
    private Double temperatureHigh;

    @Nullable
    @ColumnInfo(name = "temperatureLow")
    @SerializedName("temperatureLow")
    private Double temperatureLow;

    @Nullable
    @ColumnInfo(name = "windSpeed")
    @SerializedName("windSpeed")
    private Double windSpeed;

    @Nullable
    @ColumnInfo(name = "windBearing")
    @SerializedName("windBearing")
    private Double windBearing;

    public WeatherItem() {
    }

    /**
     * @param summary         summary of the weather (ex. cloudy, windy etc.)
     * @param icon            icon of the weather corresponding to condition
     * @param temperatureHigh temperature at day
     * @param temperatureLow  temperature at night
     * @param windBearing     direction of the wind (degrees)
     * @param windSpeed       speed of the wind (mph)
     */
    @Ignore
    public WeatherItem(Long time, @Nullable String summary, @Nullable String icon, @Nullable Double temperatureHigh, @Nullable Double temperatureLow,
                       @Nullable Double windSpeed, @Nullable Double windBearing) { //, @Nullable String timeCreated, @Nullable String dateCreated) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
//        mTimeOfDayCreated = timeCreated;
//        mDateCreated = dateCreated;
    }

    @Nullable
    public Long getTime() {
        return time;
    }

    public void setTime(@Nullable Long time) {
        this.time = time;
    }

    @Nullable
    public String getSummary() {
        return summary;
    }

    public void setSummary(@Nullable String summary) {
        this.summary = summary;
    }

    @Nullable
    public String getIcon() {
        return icon;
    }

    public void setIcon(@Nullable String icon) {
        this.icon = icon;
    }

    @Nullable
    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(@Nullable Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    @Nullable
    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(@Nullable Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    @Nullable
    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(@Nullable Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Nullable
    public Double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(@Nullable Double windBearing) {
        this.windBearing = windBearing;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WeatherItem item = (WeatherItem) object;

        return Objects.equal(time, item.getTime()) &&
                Objects.equal(summary, item.getSummary()) &&
                Objects.equal(time, item.getTime()) &&
                Objects.equal(icon, item.getIcon()) &&
                Objects.equal(temperatureHigh, item.getTemperatureHigh()) &&
                Objects.equal(temperatureLow, item.getTemperatureLow()) &&
                Objects.equal(windSpeed, item.getWindSpeed()) &&
                Objects.equal(windBearing, item.getWindBearing());
    }

}

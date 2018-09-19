package accessibility.forecast.marijus.weathertalkback2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

@Entity(tableName = "items")
public class WeatherItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    public String mId;

    //TODO: Use time as Primary Key
    @Nullable
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
        setmId(UUID.randomUUID().toString());
    }

    /**
     * Using this constructor to create a new weather item.
     */
    @Ignore
    public WeatherItem(@Nullable Long time, @Nullable String summary, @Nullable String icon, @Nullable Double temperatureHigh, @Nullable Double temperatureLow,
                       @Nullable Double windSpeed, @Nullable Double windBearing){
//    , @Nullable String timeCreated, @Nullable String dateCreated) {

        this(UUID.randomUUID().toString(), time, summary, icon, temperatureHigh, temperatureLow, windSpeed, windBearing); //, timeCreated, dateCreated);
    }

    /**
     * @param summary     summary of the weather (ex. cloudy, windy etc.)
     * @param icon          icon of the weather corresponding to condition
     * @param temperatureHigh   temperature at day
     * @param temperatureLow   temperature at night
     * @param windBearing direction of the wind (degrees)
     * @param windSpeed     speed of the wind (mph)
     */
    @Ignore
    public WeatherItem(@NonNull String id, @Nullable Long time, @Nullable String summary, @Nullable String icon, @Nullable Double temperatureHigh, @Nullable Double temperatureLow,
                       @Nullable Double windSpeed, @Nullable Double windBearing){ //, @Nullable String timeCreated, @Nullable String dateCreated) {
        this.mId = id;
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

    @NonNull
    public String getmId() {
        return mId;
    }

    public void setmId(@NonNull String mId) {
        this.mId = mId;
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
        return Objects.equal(summary, item.summary) &&
//                Objects.equal(mId, item.mId) &&
                Objects.equal(icon, item.icon) &&
                Objects.equal(temperatureHigh, item.temperatureHigh) &&
                Objects.equal(temperatureLow, item.temperatureLow) &&
                Objects.equal(windSpeed, item.windSpeed) &&
                Objects.equal(windBearing, item.windBearing);
    }

}

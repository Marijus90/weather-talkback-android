package accessibility.forecast.marijus.weathertalkback2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

import accessibility.forecast.marijus.weathertalkback2.data.api.models.Currently;
import accessibility.forecast.marijus.weathertalkback2.helper.utils.DeviceStateUtils;

/**
 * Immutable model class for a weather item db.
 */
@Entity(tableName = "items")
public final class WeatherItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    public String mId;

    @Nullable
    @ColumnInfo(name = "condition")
    public String mSummary;

    @Nullable
    @ColumnInfo(name = "icon")
    public String mIcon;

    @Nullable
    @ColumnInfo(name = "temperature")
    public Double mTemperature;

    @Nullable
    @ColumnInfo(name = "windSpeed")
    public Double mWindSpeed;

    @Nullable
    @ColumnInfo(name = "windDirection")
    public Double mWindBearing;

    @Nullable
    @ColumnInfo(name = "timeWhenCreated")
    public String mTimeOfDayCreated;

    @Nullable
    @ColumnInfo(name = "dateWhenCreated")
    public String mDateCreated;

    public WeatherItem() {

    }

    /**
     * Using this constructor to create a new weather item.
     */
    @Ignore
    public WeatherItem(@Nullable String mSummary, @Nullable String mIcon, @Nullable Double mTemperature,
                       @Nullable Double mWindSpeed, @Nullable Double mWindBearing, @Nullable String timeCreated, @Nullable String dateCreated) {
        this(mSummary, mIcon, UUID.randomUUID().toString(), mTemperature, mWindSpeed, mWindBearing, timeCreated, dateCreated);
    }

    /**
     * @param condition     summary of the weather (ex. cloudy, windy etc.)
     * @param icon          icon of the weather corresponding to condition
     * @param temperature   temperature
     * @param windDirection direction of the wind (degrees)
     * @param windSpeed     speed of the wind (mph)
     */
    @Ignore
    public WeatherItem(@Nullable String condition, @Nullable String icon, @NonNull String id,
                       @Nullable Double temperature, @Nullable Double windSpeed, @Nullable Double windDirection, @Nullable String timeCreated, @Nullable String dateCreated) {
        mId = id;
        mSummary = condition;
        mIcon = icon;
        mTemperature = temperature;
        mWindSpeed = windSpeed;
        mWindBearing = windDirection;
        mTimeOfDayCreated = timeCreated;
        mDateCreated = dateCreated;
    }

    /**
     * Using this constructor for testing only.
     */
    @Ignore
    public WeatherItem(String condition, String icon, double temperature, double windSpeed, double windDirection, String timeCreated, String dateCreated) {
        mId = UUID.randomUUID().toString();
        mSummary = condition;
        mIcon = icon;
        mTemperature = temperature;
        mWindSpeed = windSpeed;
        mWindBearing = windDirection;
        mTimeOfDayCreated = timeCreated;
        mDateCreated = dateCreated;
    }

    /**
     * Using this constructor to convert Currently item to a weather item.
     */
    @Ignore
    public WeatherItem(Currently responseItem) {
        this(responseItem.getSummary(), responseItem.getIcon(), UUID.randomUUID().toString(),
                responseItem.getTemperature(), responseItem.getWindSpeed(), responseItem.getWindBearing(),
                DeviceStateUtils.getCurrentTimeAsString(), DeviceStateUtils.getCurrentDateAsString());
    }

    @NonNull
    public String getmId() {
        return mId;
    }

    @Nullable
    public String getmSummary() {
        return mSummary;
    }

    @Nullable
    public String getmIcon() {
        return mIcon;
    }

    @Nullable
    public Double getmTemperature() {
        return mTemperature;
    }

    @Nullable
    public Double getmWindSpeed() {
        return mWindSpeed;
    }

    @Nullable
    public Double getmWindBearing() {
        return mWindBearing;
    }

    @Nullable
    public String getmTimeOfDayCreated() {
        return mTimeOfDayCreated;
    }

    @Nullable
    public String getmDateCreated() {
        return mDateCreated;
    }

    public void setmId(@NonNull String mId) {
        this.mId = mId;
    }

    public void setmSummary(@Nullable String mSummary) {
        this.mSummary = mSummary;
    }

    public void setmIcon(@Nullable String mIcon) {
        this.mIcon = mIcon;
    }

    public void setmTemperature(@Nullable Double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public void setmWindSpeed(@Nullable Double mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public void setmWindBearing(@Nullable Double mWindBearing) {
        this.mWindBearing = mWindBearing;
    }

    public void setmTimeOfDayCreated(@Nullable String mTimeOfDayCreated) {
        this.mTimeOfDayCreated = mTimeOfDayCreated;
    }

    public void setmDateCreated(@Nullable String mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WeatherItem item = (WeatherItem) object;
        return Objects.equal(mSummary, item.mSummary) &&
//                Objects.equal(mId, item.mId) &&
                Objects.equal(mIcon, item.mIcon) &&
                Objects.equal(mTemperature, item.mTemperature) &&
                Objects.equal(mWindSpeed, item.mWindSpeed) &&
                Objects.equal(mWindBearing, item.mWindBearing);
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mSummary) &&
                Strings.isNullOrEmpty(mIcon) &&
                Strings.isNullOrEmpty(mDateCreated) &&
                Strings.isNullOrEmpty(mTimeOfDayCreated) &&
                mTemperature != null &&
                mWindSpeed != null &&
                mWindBearing != null;
    }

}

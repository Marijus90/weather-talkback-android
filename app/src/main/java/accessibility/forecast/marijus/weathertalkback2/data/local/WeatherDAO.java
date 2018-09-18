package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

/**
 * Data Access Object for the weather item table.
 */
@Dao
public interface WeatherDAO {

    /**
     * Select all weather items from the table.
     *
     * @return all weather items.
     */
    @Query("SELECT * FROM items")
    List<WeatherItem> getWeather();

    /**
     * Insert a weather item in the database. If the item already exists, replace it.
     *
     * @param item the weather item to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherItem(WeatherItem item);

    /**
     * Delete all data in the items table.
     */
    @Query("DELETE FROM items")
    void deleteWeatherItems();

}

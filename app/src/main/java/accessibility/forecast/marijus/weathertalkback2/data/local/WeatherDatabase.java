package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

/**
 * The Room Database that contains the weather item table.
 * It was implemented to allow easy extension as in the future more than one item will be cached
 */
@Database(entities = {WeatherItem.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDAO weatherDAO();

}

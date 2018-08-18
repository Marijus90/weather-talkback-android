package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

/**
 * The Room Database that contains the weather item table.
 * It was implemented to allow easy extension as in the future more than one item will be cached
 */
@Database(entities = {WeatherItem.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    private static WeatherDatabase INSTANCE;

    public abstract WeatherDAO weatherDAO();

    private static final Object sLock = new Object();

    public static WeatherDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WeatherDatabase.class, "WeatherLocal.db")
                        .allowMainThreadQueries() //TODO: Remove this and fix properly
                        .build();
            }
            return INSTANCE;
        }
    }

}

package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for the {@link WeatherDAO}.
 */
@RunWith(AndroidJUnit4.class)
public class WeatherDAOTest {

    private final WeatherItem testWeatherItem = new WeatherItem(123L, "Sunny",
            "icon", 21.0, 20.0, 180.0, 20.0);
    private WeatherDatabase database;

    @Before
    public void initDB() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                WeatherDatabase.class).build();
    }

    @After
    public void closeDB() {
        database.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(database);
    }

    @Test
    public void getWeatherItems() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        TestObserver<List<WeatherItem>> testObserver = new TestObserver<>();
        database.weatherDAO().getWeather().subscribe(testObserver);

        List<WeatherItem> result = testObserver.values().get(0);
        assertThat(result, hasItem(testWeatherItem));
    }

    @Test
    public void insertedItemReplacesOnConflict() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        TestObserver<List<WeatherItem>> testObserver = new TestObserver<>();
        database.weatherDAO().getWeather().subscribe(testObserver);

        List<WeatherItem> result = testObserver.values().get(0);

        assertThat(result, hasItem(testWeatherItem));
        assertTrue(result.size() == 1);
    }

    @Test
    public void deleteAllItemsAndGettingAllItems() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        database.weatherDAO().deleteWeatherItems();

        TestObserver<List<WeatherItem>> testObserver = new TestObserver<>();
        database.weatherDAO().getWeather().subscribe(testObserver);

        List<WeatherItem> result = testObserver.values().get(0);

        assertTrue(result.isEmpty());
    }

}

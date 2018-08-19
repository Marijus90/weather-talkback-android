package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Integration test for the {@link WeatherDAO}.
 */
@RunWith(AndroidJUnit4.class)
public class WeatherDAOTest {

    private WeatherDatabase database;
    private final WeatherItem testWeatherItem
            = new WeatherItem("Sunny", "icon", 21.0,
            20.0, 180.0, "00:00:00", "12/01/01/ 11:11:11");

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
    public void getWeatherItem() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        WeatherItem retrievedItem = database.weatherDAO().getWeather();

        assertWeatherItem(retrievedItem, "Sunny", "icon", 21.0,
                20.0, 180.0, "00:00:00", "12/01/01/ 11:11:11");
    }

    @Test
    public void insertedItemReplacesOnConflict() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        WeatherItem duplicateItem = testWeatherItem;
        database.weatherDAO().insertWeatherItem(duplicateItem);

        WeatherItem retrievedItem = database.weatherDAO().getWeather();

        assertWeatherItem(retrievedItem, "Sunny", "icon", 21.0,
                20.0, 180.0, "00:00:00", "12/01/01/ 11:11:11");
    }

    @Test
    public void deleteAllItemsAndGettingAllItems() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        database.weatherDAO().deleteWeatherItems();

        WeatherItem retrievedItem = database.weatherDAO().getWeather();

        assertNull(retrievedItem);
    }

    private void assertWeatherItem(WeatherItem item, String summary, String icon, double temperature,
                                   double windSpeed, double windDirection, String timeCreated, String dateCreated) {
        assertThat(item, notNullValue());
        assertThat(item.getmSummary(), is(summary));
        assertThat(item.getmIcon(), is(icon));
        assertThat(item.getmTemperature(), is(temperature));
        assertThat(item.getmWindSpeed(), is(windSpeed));
        assertThat(item.getmWindBearing(), is(windDirection));
        assertThat(item.getmTimeOfDayCreated(), is(timeCreated));
        assertThat(item.getmDateCreated(), is(dateCreated));
    }

}


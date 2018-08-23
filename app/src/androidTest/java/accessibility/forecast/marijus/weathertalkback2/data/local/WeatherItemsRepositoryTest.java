package accessibility.forecast.marijus.weathertalkback2.data.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.local.util.SingleExecutor;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Integration test for the {@link WeatherItemsRepositoryTest}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class WeatherItemsRepositoryTest {

    private final WeatherItem testWeatherItem
            = new WeatherItem("Sunny", "icon", 21.0,
            20.0, 180.0, "00:00:00", "12/01/01/ 11:11:11");

    private WeatherItemLocalSource localRepository;
    private WeatherDatabase database;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                WeatherDatabase.class).build();
        WeatherDAO weatherDAO = database.weatherDAO();

        localRepository = new WeatherItemLocalSource(new SingleExecutor(), weatherDAO);
    }

    @After
    public void clean() {
        database.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(localRepository);
    }

    @Test
    public void insertItem_retrieveItFromLocalDataSource() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        localRepository.getWeatherData(new WeatherDataSource.GetWeatherDataCallback() {
            @Override
            public void onDataLoaded(WeatherItem response) {
                assertThat(testWeatherItem, is(response));
            }

            @Override
            public void onDataNotAvailable(String message) {
                fail("Callback error");
            }
        }, false);

    }

    @Test
    public void databaseIsCleared() {
        database.weatherDAO().insertWeatherItem(testWeatherItem);

        database.weatherDAO().deleteWeatherItems();

        localRepository.getWeatherData(new WeatherDataSource.GetWeatherDataCallback() {
            @Override
            public void onDataLoaded(WeatherItem response) {
                fail("Data should not be returned");
            }

            @Override
            public void onDataNotAvailable(String message) {
                // Test passed
                return;
            }
        }, false);
    }

}

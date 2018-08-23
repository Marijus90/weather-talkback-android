package accessibility.forecast.marijus.weathertalkback2.weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import accessibility.forecast.marijus.weathertalkback2.WeatherContract;
import accessibility.forecast.marijus.weathertalkback2.WeatherPresenter;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link accessibility.forecast.marijus.weathertalkback2.WeatherPresenter}
 */
public class WeatherPresenterTest {

    private static final int NUMBER_OF_ITEMS_IN_PRESENTER_LIST = 1;
    private final WeatherItem testWeatherItem
            = new WeatherItem("Sunny", "icon", 21.0,
            20.0, 180.0, "00:00:00", "12/01/01/ 11:11:11");

    @Mock
    private WeatherItemsRepository repository;

    @Mock
    private WeatherContract.View view;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<WeatherDataSource.GetWeatherDataCallback> getAPICallbackCaptor;

    private WeatherPresenter weatherPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        weatherPresenter = new WeatherPresenter(
                repository);
        weatherPresenter.takeView(view);

        when(view.isActive()).thenReturn(true);
    }

    @Test
    public void loadWeatherDataFromRepositoryAndLoadIntoView() {
        // Getting weather data from API
        weatherPresenter.refreshData(true);

        verify(repository).getWeatherData(getAPICallbackCaptor.capture(), ArgumentMatchers.eq(true));
        getAPICallbackCaptor.getValue().onDataLoaded(testWeatherItem);

        // Progress indicator is shown
        verify(view,times(2)).setLoadingIndicator(true);
        // Progress indicator is hidden and all items are shown in UI
        verify(view,times(1)).setLoadingIndicator(false);
        ArgumentCaptor<ArrayList<WeatherItem>> showTasksArgumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(view).displayWeatherData(showTasksArgumentCaptor.capture());

        assertTrue(showTasksArgumentCaptor.getValue().size() == NUMBER_OF_ITEMS_IN_PRESENTER_LIST);
    }

}

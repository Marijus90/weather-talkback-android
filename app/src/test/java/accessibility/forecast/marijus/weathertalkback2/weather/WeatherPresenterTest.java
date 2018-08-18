package accessibility.forecast.marijus.weathertalkback2.weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import accessibility.forecast.marijus.weathertalkback2.WeatherContract;
import accessibility.forecast.marijus.weathertalkback2.WeatherPresenter;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link accessibility.forecast.marijus.weathertalkback2.WeatherPresenter}
 */
public class WeatherPresenterTest {

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

        when(view.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        weatherPresenter = new WeatherPresenter(
                repository, view);

        verify(view).setPresenter(weatherPresenter);
    }

    //TODO: Test if data is loaded in to view using getAPICallbackCaptor
}

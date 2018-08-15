package accessibility.forecast.marijus.weathertalkback2;

import android.zetterstrom.com.forecast.models.Forecast;

import accessibility.forecast.marijus.weathertalkback2.base.BasePresenter;
import accessibility.forecast.marijus.weathertalkback2.base.BaseView;

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void displayWeatherData(Forecast forecast);

        void hideWeatherData();

        void showNoDataError(String description);

        void showErrorMessage(String description);

        void showTimeSinceLastDataRefresh();

    }

    interface Presenter extends BasePresenter {

        void refreshData();

        void updateCachedData();

    }
}

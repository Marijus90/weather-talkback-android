package accessibility.forecast.marijus.weathertalkback2;

import java.util.ArrayList;

import accessibility.forecast.marijus.weathertalkback2.base.BasePresenter;
import accessibility.forecast.marijus.weathertalkback2.base.BaseView;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void displayWeatherData(ArrayList<WeatherItem> forecast);

        void hideWeatherData();

        void showNoDataLayout(boolean active);

        void showErrorMessage(String message);

        boolean isActive();

    }

    interface Presenter extends BasePresenter<View> {

        void refreshData(boolean isForced);

        void updateCachedData(WeatherItem forecast);

        void takeView(WeatherContract.View view);

        void dropView();

    }

}

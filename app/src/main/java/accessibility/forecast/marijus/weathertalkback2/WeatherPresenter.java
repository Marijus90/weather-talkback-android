package accessibility.forecast.marijus.weathertalkback2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.data.api.models.WeatherResponseItem;
import accessibility.forecast.marijus.weathertalkback2.helper.di.ActivityScoped;
import accessibility.forecast.marijus.weathertalkback2.helper.di.modules.WeatherModule;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * This class listens to user actions from the UI ({@link WeatherFragment}), retrieves the data and updates the
 * UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the WeatherPresenter (if it fails, it emits a compiler error).  It uses
 * {@link WeatherModule} to do so.
 **/
@ActivityScoped
public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherItemsRepository weatherRepository;

    @Nullable
    private WeatherContract.View view;

    @NonNull
    private CompositeDisposable disposable;

    @Inject
    public WeatherPresenter(WeatherItemsRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        disposable = new CompositeDisposable();
    }

    @Override
    public void refreshData(boolean isForced) {
        if (view != null) {
            view.setLoadingIndicator(true);
        }

        disposable.clear();

        disposable.add(weatherRepository.getRxWeatherData(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ArrayList::new)
                .subscribe(
                        response -> {
                            if (view != null) {
                                processWeatherData(response);
                                view.setLoadingIndicator(false);
                            }
                        },
                        throwable -> {
                            if (view != null) {
                                view.showErrorMessage(throwable.getMessage());
                                view.showNoDataLayout(true);
                                view.setLoadingIndicator(false);
                            }
                        })
        );

        /*
        weatherRepository.getWeatherData(new WeatherDataSource.GetWeatherDataCallback() {

            @Override
            public void onDataLoaded(WeatherItem data) {
                if (!data.isEmpty()) {
                    processWeatherData(data);
                } else {
                    if (view != null) {
                        view.showNoDataLayout(true);
                    }
                }
                if (view != null) {
                    view.setLoadingIndicator(false);
                }
            }

            @Override
            public void onDataNotAvailable(String message) {
                if (message != null) {
                    if (view != null) {
                        view.showErrorMessage(message);
                    }
                }

                if (view != null) {
                    view.showNoDataLayout(true);
                    view.setLoadingIndicator(false);
                }
            }
        }, isForced);
        */
    }

    private void processWeatherData(@NonNull ArrayList<WeatherResponseItem> data) {
        if (view != null) {
            view.displayWeatherData(data);
        }
    }

    @Override
    public void updateCachedData(WeatherItem forecast) {
        weatherRepository.cacheData(forecast);
    }

    @Override
    public void takeView(WeatherContract.View view) {
        this.view = view;
        refreshData(false);
    }

    @Override
    public void dropView() {
        view = null;
        disposable.clear();
    }

}

package accessibility.forecast.marijus.weathertalkback2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
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

        //TODO: Make sure calls aren't duplicated if device is rotated while executing a call
        disposable.add(weatherRepository.getRxWeatherData(isForced)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ArrayList::new)
                .subscribe(
                        response -> {
                            if (response.size() == 0) {
                                showErrorMessage("No data returned");
                            } else if (view != null) {
                                processWeatherData(response);
                                view.setLoadingIndicator(false);
                            }
                        },
                        throwable -> showErrorMessage(throwable.getMessage())) //TODO: Make the Error messages user friendly
        );
    }

    private void showErrorMessage(String message) {
        if (view != null) {
            view.showErrorMessage(message);
            //TODO: Show a SnackBar instead
            view.showNoDataLayout(true);
            view.setLoadingIndicator(false);
        }
    }

    private void processWeatherData(@NonNull ArrayList<WeatherItem> data) {
        if (view != null) {
            view.displayWeatherData(data);
        }
    }

    @Override
    public void updateCachedData(ArrayList<WeatherItem> weatherData) {

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

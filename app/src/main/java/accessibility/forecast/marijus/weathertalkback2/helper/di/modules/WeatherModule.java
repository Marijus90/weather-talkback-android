package accessibility.forecast.marijus.weathertalkback2.helper.di.modules;

import accessibility.forecast.marijus.weathertalkback2.WeatherContract;
import accessibility.forecast.marijus.weathertalkback2.WeatherFragment;
import accessibility.forecast.marijus.weathertalkback2.WeatherPresenter;
import accessibility.forecast.marijus.weathertalkback2.helper.di.ActivityScoped;
import accessibility.forecast.marijus.weathertalkback2.helper.di.FragmentScoped;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link WeatherPresenter}.
 */
@Module
public abstract class WeatherModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract WeatherFragment weatherFragment();

    @ActivityScoped
    @Binds
    abstract WeatherContract.Presenter weatherPresenter(WeatherPresenter presenter);

}

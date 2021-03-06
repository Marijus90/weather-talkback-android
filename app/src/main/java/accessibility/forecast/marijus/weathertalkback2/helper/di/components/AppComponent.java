package accessibility.forecast.marijus.weathertalkback2.helper.di.components;

import android.app.Application;

import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.base.WeatherApp;
import accessibility.forecast.marijus.weathertalkback2.helper.di.modules.ActivityBindingModule;
import accessibility.forecast.marijus.weathertalkback2.helper.di.modules.ApplicationModule;
import accessibility.forecast.marijus.weathertalkback2.helper.di.modules.WeatherRepositoryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * This is a Dagger component. Refer to {@link WeatherApp} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link WeatherApp}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = {
        WeatherRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})

public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(WeatherApp application);

//    WeatherItemsRepository getWeatherRepository();

    @Override
    void inject(DaggerApplication instance);

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();

    }

}
